package com.lexmark.service.impl.real.hardwareService;

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
import com.lexmark.domain.Bundle;
import com.lexmark.service.impl.real.catalogService.CatalogListService;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

/**
 * Service class for Bundles call
 * 
 * @author Ivan Mdzeluri
 * @since 9/15/15
 */

public class GlobalBundleListService {
	private static final Logger logger = LogManager.getLogger(CatalogListService.class);

	private final GlobalCatalogListContract contract;
	private Session session;
	private String agreementId;
	private String productType;
	private String productModel;
	private String partNumber;
	private String soldToNumber;
	private String paymenttype;
	private String contractNumber;
	private Date effectiveDate;
	private String partType;
	private Session totalCountSession;
	private String searchExpression;
	private QueryObject criteria;
	private final int startRecordNumber;
	private final int increment;
	private String locationType;

	public GlobalBundleListService(GlobalCatalogListContract contract) {

		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}

		this.contract = contract;
		this.agreementId = contract.getBunAgreementId();
		this.productType = contract.getBunProductType();
		this.productModel = contract.getBunProductModel();
		this.partNumber = contract.getBunPartNumber();
		this.soldToNumber = contract.getBunSoldToNumber();
		this.paymenttype = contract.getBunPaymentType();
		this.contractNumber = contract.getBunContractNumber();
		this.effectiveDate = contract.getBunEffectiveDate();
		this.increment = contract.getBunIncrement();
		this.startRecordNumber = contract.getBunStartRecordNumber();
		this.partType = contract.getBunPartType();
		this.locationType= contract.getBunLocationType();
	}

	public void checkRequiredFields() {
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("Agreement Id is null or empty!");
		}
		if(effectiveDate == null) {
			throw new IllegalArgumentException("Effective date is null!");
		}
	}

	public List<Bundle> queryAndGetResultList() {
		List<HardwareSapCatalogDo> doList = querySiebel(this.session, criteria, contract);
		return BundleListServiceUtil.convertPickSapCatalogDoToHardwareCatalog(doList);
	}
	
	public void buildSearchExpression() {
		searchExpression = buildCatalogSearchExpression();
		criteria = buildCatalogCriteria();
	}
	
	private QueryObject buildCatalogCriteria() {
		QueryObject criteria = new QueryObject(HardwareSapCatalogDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		criteria.addComponentSearchSpec(HardwareSapCatalogDo.class, searchExpression);
		
		return criteria;
	}

	private String buildCatalogSearchExpression() {
		StringBuilder mainSearchspec = new StringBuilder();

		mainSearchspec.append("[LXK MPS Agreement Id] = '" + agreementId + "'");

		if (isNotBlank(contractNumber)) {
			mainSearchspec.append(" AND [LXK MPS SAP Contract #] = '" + contractNumber + "'");
		}
		if (isNotBlank(paymenttype)) {
			mainSearchspec.append(" AND [LXK MPS Billing Model] = '" + paymenttype +"'");
		}

		mainSearchspec.append(" AND [LXK MPS Display On Portal] ='Y'");
		if(isNotBlank(productType)){
			mainSearchspec.append(" AND ((([LXK MPS Material Line] = 'Printers' OR [LXK MPS Material Line] = 'Options' OR [LXK MPS Material Line] = 'Service Parts')");
			mainSearchspec.append(" AND [LXK MPS Product Type] = '" +productType+"')");
			mainSearchspec.append(" OR (EXISTS ([LXK MPS Product Type MVF] = '" +productType+"')))");
		}
		if (isNotBlank(productType) && isNotBlank(productModel)) {
			mainSearchspec.append(" AND (([LXK MPS Product Model] = '" + productModel + "' AND [LXK MPS Material Line] = 'Printers')"
					+ " OR EXISTS ([LXK MPS Product Model MVF] = '"
					+ productModel + "' AND [LXK MPS Material Line MVF] = 'Printers'))");

		}
		
		if (isNotBlank(partNumber)) {
			mainSearchspec.append(" AND (([LXK MPS Component Part #] = '"
					+ trim(partNumber) + "' "+ " or [LXK B2B Marketing Short Description] ~= '"+ trim(partNumber) + "'" + " or [LXK B2B Model] ~= '"+ trim(partNumber) + "'" + " or [LXK B2B Marketing Name] ~= '"+ trim(partNumber) + "'" + ")"
					+ " OR EXISTS ([LXK MPS Supply Part# MVF] = '"
					+ trim(partNumber) + "'"+" or [LXK B2B Marketing Short Description MVF]='"+ trim(partNumber) + "'" + " or [LXK MPS Product Model MVF]='"+ trim(partNumber) + "'" + " or [LXK B2B Marketing Name MVF] ='"+ trim(partNumber) + "'" + "))");
		}
		
		if (isNotBlank(partType)) {
			mainSearchspec.append(" AND ([LXK MPS B2B Part Type] = '" + partType + "'");
			mainSearchspec.append(" OR EXISTS([LXK MPS B2B Part Type MVF] = '" +partType+ "'))");
		}
		
		mainSearchspec.append(" AND ([LXK MPS Status] <> 'Inactive' OR [LXK MPS Status] IS NULL)");
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		mainSearchspec.append(" AND ([LXK MPS Effective End Date] >= '");
		mainSearchspec.append(formatter.format(effectiveDate));
		mainSearchspec.append("' OR [LXK MPS Effective End Date] IS NULL)");
		if(isNotBlank(locationType)){
			if("NCAL".equalsIgnoreCase(locationType)){
				mainSearchspec.append(" AND [LXK MPS B2B Subtype] = 'L1'");
			}
			else if("SCAL".equalsIgnoreCase(locationType)){
				mainSearchspec.append(" AND [LXK MPS B2B High] = 'L2'");
			}
			else if("Pharmacy".equalsIgnoreCase(locationType)){
				mainSearchspec.append(" AND [LXK MPS B2B Low] = 'L3'");
			}
		}
		
		return mainSearchspec.toString();
	}

	public Integer processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getTotalCountSession().getSiebelBusServiceProxy();
		totalCount = AmindServiceUtil.getTotalCount("LXK MPS Hardware Catalog Portal", "LXK MPS Hardware Catalog Portal",
				searchExpression, proxy);
		return totalCount;
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> querySiebel(Session session,
			QueryObject criteria, GlobalCatalogListContract contract) {
		logger.debug("[IN] querySiebel(...)");
		logger.debug("[IN] criteria componentSearchSpecMap = "
				+ criteria.getComponentSearchSpecMap());
		logger.debug("[IN] criteria sortingString =" + criteria.getSortString());
		IDataManager dataManager = session.getDataManager();
		List<T> hardwareList = null;
		if (contract.isBunNewQueryIndicator()) {
			hardwareList = dataManager.query(criteria);
		} else {
			hardwareList = dataManager.queryNext(criteria);
		}
		logger.debug("[OUT] querySiebel(...)");
		return hardwareList;
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not been initialized!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null");
		}
		this.session = session;
	}
	
	public Session getTotalCountSession() {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session has not been set!");
		} else {
			return totalCountSession;
		}
	}

	public void setTotalCountSession(Session totalCountSession) {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session can not be null!");
		} else {
			this.totalCountSession = totalCountSession;
		}
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

}
