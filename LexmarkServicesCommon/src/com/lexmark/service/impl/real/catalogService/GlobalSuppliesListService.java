package com.lexmark.service.impl.real.catalogService;

import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToPartsList;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotBlank;
import static com.lexmark.util.LangUtil.trim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.domain.OrderPart;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.CatalogListDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

/**
 * Service class for Supplies call
 * 
 * @author Ivan Mdzeluri
 * @since 9/15/15
 */

public class GlobalSuppliesListService {
	private static final Logger logger = LogManager.getLogger(CatalogListService.class);

	private final GlobalCatalogListContract contract;

	private String searchExpression;
	private Session session;
	private String agreementId;
	private String productType;
	private String partType;
	private String productModel;
	private String partNumber;
	private String soldToNumber;
	private String paymentType;
	private String contractNumber;
    private int totalCountOfOrderParts = -1;
    private QueryObject criteria;
    private Date effectiveDate;
	
	public GlobalSuppliesListService(GlobalCatalogListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}
		this.contract = contract;
		this.agreementId = contract.getSupAgreementId();
		this.productType = contract.getSupProductType();
		this.productModel = contract.getSupProductModel();
		this.partType = contract.getSupPartType();
		this.partNumber = contract.getSupPartNumber();
		this.soldToNumber = contract.getSupSoldToNumber();
		this.paymentType = contract.getSupPaymentType();
		this.contractNumber = contract.getSupContractNumber();
		contract.isSupHardwareFlag();
		this.effectiveDate = contract.getSupEffectiveDate();
	}

	public void checkRequiredFields() {
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("Agreement Id is null or empty!");
		}
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("Payment type must be specified");
		}
	}

	
	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();
		builder.append("[agreementId] = '" + agreementId  + "'");
		if(isNotBlank(productType)) {
			builder.append(" AND [productType] = '" + trim(productType) + "'");
		}
		if(isNotBlank(productModel)) {
			builder.append(" AND [productModel] = '" + trim(productModel) + "'");
		}
		if(isNotBlank(partType)) {
			builder.append(" AND [partType] = '" + trim(partType) + "'");
		}
		if(isNotBlank(partNumber)) {
			builder.append(" AND ([partNumber] ~= '" + trim(partNumber) + "'" +" or [LXK B2B Marketing Short Description] ~= '"+ trim(partNumber) + "'" + "or [LXK B2B Model] ~= '"+ trim(partNumber) + "'" + " or [LXK B2B Marketing Name] ~= '"+ trim(partNumber) +"')"); // case insensitive search 
			builder.append(" AND [implicitFlag] <> 'Y'" );
		}
		
		builder.append(" AND [portalFlag] = 'Y'");
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		builder.append(" AND ([LXK MPS Effective End Date] >= '");
		builder.append(formatter.format(effectiveDate));
		builder.append("' OR [LXK MPS Effective End Date] IS NULL)");
		builder.append("AND ([LXK MPS Type] IS NULL OR [LXK MPS Type] = 'Supply Components') AND [LXK MPS Part Type] IS NOT NULL");
		if (isNotBlank(contractNumber)) {
			builder.append(" AND EXISTS ([LXK MPS Contract #] ='" + trim(contractNumber) + "')");
		}
		criteria = new QueryObject(CatalogListDo.class, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(CatalogListDo.class, builder.toString());
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(contract.getSupIncrement());
		criteria.setStartRowIndex(contract.getSupStartRecordNumber());
		
		searchExpression = builder.toString()
		         .replace("[agreementId]", "[LXK MPS Agreement Id]")
		         .replace("[productType]", "[LXK MPS Product Type]")
		         .replace("[productModel]", "[LXK MPS Catalog Product Model]")
		         .replace("[partType]", "[LXK MPS Catalog Consumable Type Explicit]")
		         .replace("[partNumber]", "[LXK MPS Supply Part#]")
		         .replace("[portalFlag]", "[LXK MPS Display On Portal]")
		         .replace("[implicitFlag]", "[LXK MPS Implicit Flag]")
		         .replace("[consumableType]","[LXK MPS Part Type]")
		          .replace("[materialLine]", "[LXK MPS Material Line]") ;

	}
	

	public List<OrderPart> queryAndGetResultList() {
        List<CatalogListDo> doList = querySiebel(this.session, criteria, contract);
        this.totalCountOfOrderParts = processRecordsTotalCount(contract, (AmindCrmSessionHandle) contract.getSessionHandle(), this.session, searchExpression, CatalogListDo.GLOBAL_BO, CatalogListDo.GLOBAL_BC);

		return convertPartsDoListToPartsList(doList);
	}
	
	
	@SuppressWarnings("unchecked")
    private static <T> List<T> querySiebel(Session session, QueryObject criteria, GlobalCatalogListContract contract) {
        logger.debug("[IN] querySiebel(...)");
        logger.debug("[IN] criteria componentSearchSpecMap = " + criteria.getComponentSearchSpecMap());
        logger.debug("[IN] criteria sortingString =" + criteria.getSortString());
        IDataManager dataManager =  session.getDataManager();
        List<T> assetList = null;
        if (contract.isSupNewQueryIndicator()) {
            assetList = dataManager.query(criteria);
         } else {
            assetList = dataManager.queryNext(criteria);
         }
        logger.debug("[OUT] querySiebel(...)");
        return assetList;
    }  

    private int processRecordsTotalCount(GlobalCatalogListContract contract, AmindCrmSessionHandle crmSessionHandle, Session session, String searchExpression,  String BO, String BC) {
        int count = 0;
        if (contract.isSupNewQueryIndicator()) {
            logger.debug("[IN] Count Method");
            count = countRecords(session, searchExpression,BO, BC );
            crmSessionHandle.setOrderPartCount(count);
            logger.debug("[OUT] Count Method");
        } else {
            count = crmSessionHandle.getOrderPartCount(); 
        }
        return count;
    }
    
    static int countRecords(Session session, String searchExpression,String BO, String BC) {
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        	 return AmindServiceUtil.getTotalCount(BO, BC, searchExpression, businessServiceProxy);
       
    }

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not initialized!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null");
		}
		this.session = session;
	}

    public int getTotalCountOfOrderParts() {
        return totalCountOfOrderParts;
    }

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
}
