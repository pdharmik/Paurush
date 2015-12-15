package com.lexmark.service.impl.real.catalogService;

import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToAccessoriesList;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToAccessoriesListB2b;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToPartsList;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToPartsListWithContractNumber;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToSuppliesList;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertPartsDoListToPartsListWithContractNumberB2b;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotBlank;
import static com.lexmark.util.LangUtil.trim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.domain.OrderPart;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AgreementRelatedAccountsDO;
import com.lexmark.service.impl.real.domain.CatalogListDo;
import com.lexmark.service.impl.real.domain.CatalogListWithContractNumberDo;
import com.lexmark.service.impl.real.domain.SuppliesSplitterCatalogDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

import org.apache.logging.log4j.Logger;


public class CatalogListService {
    
    //private static final Log logger = LogFactory.getLog(CatalogListService.class);
	private static final Logger logger = LogManager.getLogger(CatalogListService.class);

	private final CatalogListContract contract;

	private String searchExpression;
	private String searchExpressionWithContractNumber;
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
    private QueryObject criteriaWithContractNumber;
    private boolean hardwareFlag;
    private Date effectiveDate;
	
	public CatalogListService(CatalogListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}
		this.contract = contract;
		this.agreementId = contract.getAgreementId();
		this.productType = contract.getProductType();
		this.productModel = contract.getProductModel();
		this.partType = contract.getPartType();
		this.partNumber = contract.getPartNumber();
		this.soldToNumber = contract.getSoldToNumber();
		this.paymentType = contract.getPaymentType();
		this.contractNumber = contract.getContractNumber();
		this.hardwareFlag = contract.isHardwareFlag();
		this.effectiveDate = contract.getEffectiveDate();
	}

	public void checkRequiredFields() {
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("Agreement Id is null or empty!");
		}
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("Payment type must be specified");
		}
	}

	public void buildSearchExpressionAccessoriesB2B(boolean suppliesFlag, boolean accessoriesFlag) {
		StringBuilder builder = new StringBuilder();
		builder.append("[agreementId] = '" + agreementId  + "'");
		if(isNotBlank(productType)) {
			builder.append(" AND [productType] = '" + trim(productType) + "'");
		}
		if(isNotBlank(productModel)) {
			builder.append(" AND ([LXK MPS Product Model] = '" + trim(productModel) + "' OR [LXK MPS Supplies Model] = '" + trim(productModel) + "')");
		}
		if (suppliesFlag) {
			builder.append(" AND [materialLine] = 'Supplies'");
		} else if (accessoriesFlag) {
			builder.append(" AND ( [materialLine] = 'Options' OR [materialLine] = 'Features' OR [materialLine] = 'Service Parts' )");
			builder.append("AND [LXK MPS Type]= 'Hardware Components'");
		}
		else if(isNotBlank(partType)) {
			builder.append(" AND [partType] = '" + trim(partType) + "'");
		}
		if(isNotBlank(partNumber)) {
			builder.append(" AND [partNumber] ~= '" + trim(partNumber) + "'"); // case insensitive search 
			builder.append(" AND [implicitFlag] <> 'Y'" );
		}
		
		builder.append(" AND [portalFlag] = 'Y'");
	
		if(!suppliesFlag && !accessoriesFlag) {
//			builder.append(" AND [LXK MPS Material Line] <>'Options'");
			builder.append(" AND ([LXK MPS Material Line] = 'Options' OR [LXK MPS Material Line] = 'Features' OR [LXK MPS Material Line] = 'Service Parts')");
			builder.append(" AND [LXK MPS Type]= 'Hardware Components'");
		}
		
		StringBuilder childSearchExpression = new StringBuilder();
		
		if(isNotBlank(contract.getContractNumber())) {
			childSearchExpression.append(" AND EXISTS ([LXK MPS Contract #] = '");
			childSearchExpression.append(contractNumber);
			childSearchExpression.append("'");
			
			if(isNotBlank(contract.getPaymentType())) {
				childSearchExpression.append(" AND ([LXK MPS Billing Model] = '");
				childSearchExpression.append(paymentType);
				childSearchExpression.append("'");
				
				if(!"Ship and Bill".equalsIgnoreCase(contract.getPaymentType())) {
					childSearchExpression.append(" OR [LXK MPS Billing Model] IS NULL");
				}
				
				childSearchExpression.append(")");
			}
		}
		
		else {
			if(isNotBlank(contract.getPaymentType())) {
				childSearchExpression.append(" AND EXISTS (");
				
				childSearchExpression.append("EXISTS (");
				
				childSearchExpression.append("[LXK MPS Billing Model] = '");
				childSearchExpression.append(paymentType);
				childSearchExpression.append("'");
				
				if(!"Ship and Bill".equalsIgnoreCase(contract.getPaymentType())) {
					childSearchExpression.append(" OR [LXK MPS Billing Model] IS NULL");
				}
				
				childSearchExpression.append(")");
			}
		}
	
		if(childSearchExpression.length()>0) {
			childSearchExpression.append(")");
		}
		
		builder.append(childSearchExpression);
		StringBuilder childSearchExp = new StringBuilder();
		
		if(isNotBlank(contract.getContractNumber())) {							//child search expression added as per 10936
			childSearchExp.append("[LXK MPS SAP Contract #] = '");
			childSearchExp.append(contractNumber);
			childSearchExp.append("'");
			
			if(isNotBlank(contract.getPaymentType())) {
				childSearchExp.append(" AND ([LXK MPS Billing Model] = '");
				childSearchExp.append(paymentType);
				childSearchExp.append("'");
				
				if(!"Ship and Bill".equalsIgnoreCase(contract.getPaymentType())) {
					childSearchExp.append(" OR [LXK MPS Billing Model] IS NULL ");
				}
				
				childSearchExp.append(")");
			}
			
		}
		criteriaWithContractNumber = new QueryObject(CatalogListWithContractNumberDo.class, ActionEvent.QUERY);
		criteriaWithContractNumber.addComponentSearchSpec(CatalogListWithContractNumberDo.class, builder.toString());
		criteriaWithContractNumber.addComponentSearchSpec(SuppliesSplitterCatalogDo.class,								//child search expression added as per 10936
				childSearchExp.toString());
		criteriaWithContractNumber.setExecutionMode(ExecutionMode.BiDirectional);
		criteriaWithContractNumber.setNumRows(contract.getIncrement());
		criteriaWithContractNumber.setStartRowIndex(contract.getStartRecordNumber());
//		criteriaWithContractNumber.addComponentSearchSpec(SuppliesSplitterCatalogDo.class, childSearchExpression.toString());
		
		searchExpressionWithContractNumber = builder.toString()
		         .replace("[agreementId]", "[LXK MPS Agreement Id]")
		         .replace("[productType]", "[LXK MPS B2B Part Type]")
		         .replace("[productModel]", "[LXK MPS Catalog Product Model]")
		         .replace("[partType]", "[LXK MPS Catalog Consumable Type Explicit]")
		         .replace("[partNumber]", "[LXK MPS Supply Part#]")
		         .replace("[portalFlag]", "[LXK MPS Display On Portal]")
		         .replace("[implicitFlag]", "[LXK MPS Implicit Flag]")
		         .replace("[consumableType]","[LXK MPS Part Type]")
		          .replace("[materialLine]", "[LXK MPS Material Line]") ;
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
			builder.append(" AND [partNumber] ~= '" + trim(partNumber) + "'"); // case insensitive search 
			builder.append(" AND [implicitFlag] <> 'Y'" );
		}
		
		builder.append(" AND [portalFlag] = 'Y'");
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		builder.append(" AND ([LXK MPS Effective End Date] >= '");
		builder.append(formatter.format(effectiveDate));
		builder.append("' OR [LXK MPS Effective End Date] IS NULL)");
		builder.append("AND ([LXK MPS Type] IS NULL OR [LXK MPS Type] = 'Supply Components') AND [LXK MPS Part Type] IS NOT NULL");
		criteria = new QueryObject(CatalogListDo.class, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(CatalogListDo.class, builder.toString());
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(contract.getIncrement());
		criteria.setStartRowIndex(contract.getStartRecordNumber());
		
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
	
	public void buildSearchExpressionWithContractNumber(boolean suppliesFlag, boolean accessoriesFlag) {
		StringBuilder builder = new StringBuilder();
		builder.append("[agreementId] = '" + agreementId  + "'");
		if(isNotBlank(productType)) {
			builder.append(" AND [productTypeNonB2B] = '" + trim(productType) + "'");
		}
		if(isNotBlank(productModel)) {
			builder.append(" AND ([LXK MPS Product Model] = '" + trim(productModel) + "' OR [LXK MPS Supplies Model] = '" + trim(productModel) + "')");
		}
		if (suppliesFlag) {
			builder.append(" AND [materialLine] = 'Supplies'");
		} else if (accessoriesFlag) {
			builder.append(" AND ( [materialLine] = 'Options' OR [materialLine] = 'Features' OR [materialLine] = 'Service Parts' )");
			builder.append("AND [LXK MPS Type]= 'Hardware Components'");
		}
		else if(isNotBlank(partType)) {
			builder.append(" AND [partType] = '" + trim(partType) + "'");
		}
		if(isNotBlank(partNumber)) {
			builder.append(" AND [partNumber] ~= '" + trim(partNumber) + "'"); // case insensitive search 
			builder.append(" AND [implicitFlag] <> 'Y'" );
		}
		
		builder.append(" AND [portalFlag] = 'Y'");
	
		if(!suppliesFlag && !accessoriesFlag) {
			builder.append(" AND [LXK MPS Material Line] <>'Options'");
		}
		
		StringBuilder childSearchExpression = new StringBuilder();
		
		if(isNotBlank(contract.getContractNumber())) {
			childSearchExpression.append(" AND EXISTS ([LXK MPS Contract #] = '");
			childSearchExpression.append(contractNumber);
			childSearchExpression.append("'");
			
			if(isNotBlank(contract.getPaymentType())) {
				childSearchExpression.append(" AND ([LXK MPS Billing Model] = '");
				childSearchExpression.append(paymentType);
				childSearchExpression.append("'");
				
				if(!"Ship and Bill".equalsIgnoreCase(contract.getPaymentType())) {
					childSearchExpression.append(" OR [LXK MPS Billing Model] IS NULL");
				}
				
				childSearchExpression.append(")");
			}
		}
		
		else {
			if(isNotBlank(contract.getPaymentType())) {
				childSearchExpression.append(" AND EXISTS (");
				
				childSearchExpression.append("EXISTS (");
				
				childSearchExpression.append("[LXK MPS Billing Model] = '");
				childSearchExpression.append(paymentType);
				childSearchExpression.append("'");
				
				if(!"Ship and Bill".equalsIgnoreCase(contract.getPaymentType())) {
					childSearchExpression.append(" OR [LXK MPS Billing Model] IS NULL");
				}
				
				childSearchExpression.append(")");
			}
		}
	
		if(childSearchExpression.length()>0) {
			childSearchExpression.append(")");
		}
		
		builder.append(childSearchExpression);
		StringBuilder childSearchExp = new StringBuilder();
		
		if(isNotBlank(contract.getContractNumber())) {							//child search expression added as per 10936
			childSearchExp.append("[LXK MPS SAP Contract #] = '");
			childSearchExp.append(contractNumber);
			childSearchExp.append("'");
			
			if(isNotBlank(contract.getPaymentType())) {
				childSearchExp.append(" AND ([LXK MPS Billing Model] = '");
				childSearchExp.append(paymentType);
				childSearchExp.append("'");
				
				if(!"Ship and Bill".equalsIgnoreCase(contract.getPaymentType())) {
					childSearchExp.append(" OR [LXK MPS Billing Model] IS NULL ");
				}
				
				childSearchExp.append(")");
			}
			
		}
		criteriaWithContractNumber = new QueryObject(CatalogListWithContractNumberDo.class, ActionEvent.QUERY);
		criteriaWithContractNumber.addComponentSearchSpec(CatalogListWithContractNumberDo.class, builder.toString());
		criteriaWithContractNumber.addComponentSearchSpec(SuppliesSplitterCatalogDo.class,								//child search expression added as per 10936
				childSearchExp.toString());
		criteriaWithContractNumber.setExecutionMode(ExecutionMode.BiDirectional);
		criteriaWithContractNumber.setNumRows(contract.getIncrement());
		criteriaWithContractNumber.setStartRowIndex(contract.getStartRecordNumber());
//		criteriaWithContractNumber.addComponentSearchSpec(SuppliesSplitterCatalogDo.class, childSearchExpression.toString());
		searchExpressionWithContractNumber = builder.toString()
		         .replace("[agreementId]", "[LXK MPS Agreement Id]")
		         .replace("[productTypeNonB2B]", "[LXK MPS Product Type]")
		         .replace("[productModel]", "[LXK MPS Catalog Product Model]")
		         .replace("[partType]", "[LXK MPS Catalog Consumable Type Explicit]")
		         .replace("[partNumber]", "[LXK MPS Supply Part#]")
		         .replace("[portalFlag]", "[LXK MPS Display On Portal]")
		         .replace("[implicitFlag]", "[LXK MPS Implicit Flag]")
		         .replace("[consumableType]","[LXK MPS Part Type]")
		          .replace("[materialLine]", "[LXK MPS Material Line]") ;

	}
	

	public List<OrderPart> queryAndGetResultList() {
//        QueryObject queryObject = AmindServiceUtil.newQueryObject(OrderPartDo.class, searchExpression, contract.getIncrement(), contract.getStartRecordNumber()); 
        List<CatalogListDo> doList = querySiebel(this.session, criteria, contract);
        this.totalCountOfOrderParts = processRecordsTotalCount(contract, (AmindCrmSessionHandle) contract.getSessionHandle(), this.session, searchExpression, CatalogListDo.BO, CatalogListDo.BC);

		return convertPartsDoListToPartsList(doList);
	}
	
	public List<OrderPart> queryAndGetResultListWithContractNumber(boolean accessoriesFlag, boolean isB2bCall) {
		List<CatalogListWithContractNumberDo> doList = querySiebel(this.session, criteriaWithContractNumber, contract);
		//searchExpressionWithContractNumber
		
		this.totalCountOfOrderParts = processRecordsTotalCount(contract, (AmindCrmSessionHandle) contract.getSessionHandle(), this.session,searchExpressionWithContractNumber , CatalogListWithContractNumberDo.BO, CatalogListWithContractNumberDo.BC);
		//this.totalCountOfOrderParts =0;
		if (contract.isHardwareAccessoriesFlag()) {
			return isB2bCall?convertPartsDoListToAccessoriesListB2b(doList):convertPartsDoListToAccessoriesList(doList) ;
		} else if (contract.isHardwareSuppliesFlag()) {
			return convertPartsDoListToSuppliesList(doList);
		} else {
			return isB2bCall?convertPartsDoListToPartsListWithContractNumberB2b(doList, hardwareFlag, paymentType):convertPartsDoListToPartsListWithContractNumber(doList, hardwareFlag, paymentType);
		}
	}
	
	@SuppressWarnings("unchecked")
    private static <T> List<T> querySiebel(Session session, QueryObject criteria, MdmSearchContractBase contract) {
        logger.debug("[IN] querySiebel(...)");
        logger.debug("[IN] criteria componentSearchSpecMap = " + criteria.getComponentSearchSpecMap());
        logger.debug("[IN] criteria sortingString =" + criteria.getSortString());
        IDataManager dataManager =  session.getDataManager();
        List<T> assetList = null;
        if (contract.isNewQueryIndicator()) {
            assetList = dataManager.query(criteria);
         } else {
            assetList = dataManager.queryNext(criteria);
         }
        logger.debug("[OUT] querySiebel(...)");
        return assetList;
    }  

    private int processRecordsTotalCount(MdmSearchContractBase contract, AmindCrmSessionHandle crmSessionHandle, Session session, String searchExpression,  String BO, String BC) {
        int count = 0;
        if (contract.isNewQueryIndicator()) {
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
