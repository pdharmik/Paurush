package com.lexmark.service.impl.real.hardwareService;

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

import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.domain.HardwareCatalog;
import com.lexmark.service.impl.real.catalogService.CatalogListService;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class HardwareCatalogService {

	//private static final Log logger = LogFactory
	//		.getLog(CatalogListService.class);
	private static final Logger logger = LogManager
			.getLogger(CatalogListService.class);

	private final HardwareCatalogContract contract;
	private Session session;
	private String agreementId;
	private String productType;
	private String productModel;
	private String partNumber;
	private String soldToNumber;
	private String paymenttype;
	private String contractNumber;
	private Date effectiveDate;

	public HardwareCatalogService(HardwareCatalogContract contract) {

		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}

		this.contract = contract;
		this.agreementId = contract.getAgreementId();
		this.productType = contract.getProductType();
		this.productModel = contract.getProductModel();
		this.partNumber = contract.getPartNumber();
		this.soldToNumber = contract.getSoldToNumber();
		this.paymenttype = contract.getPaymentType();
		this.contractNumber = contract.getContractNumber();
		this.effectiveDate = contract.getEffectiveDate();
	}

	public void checkRequiredFields() {
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("Agreement Id is null or empty!");
		}
		if(effectiveDate == null) {
			throw new IllegalArgumentException("Effective date is null!");
		}
	}

	public HardwareCatalog queryAndGetResultList() {
		StringBuilder mainSearchspec = new StringBuilder();

		mainSearchspec.append("[agreementId] = '" + agreementId + "'");

		if (isNotBlank(contractNumber)) {
			mainSearchspec.append(" AND [sapContract] = '" + contractNumber + "'");
		}
		if (isNotBlank(paymenttype)) {
			mainSearchspec.append(" AND [billingModel] = '" + paymenttype +"'");
		}

		mainSearchspec.append(" AND [LXK MPS Display On Portal] ='Y'");
		
		if (isNotBlank(productType) && isNotBlank(productModel)) {
			mainSearchspec.append(" AND (([productModel] = '" + productModel + "' AND [LXK MPS Material Line] = 'Printers')"
					+ " OR EXISTS ([LXK MPS Product Model MVF] = '"
					+ productModel + "' AND [LXK MPS Material Line MVF] = 'Printers'))");

		}
		if (isNotBlank(partNumber)) {
			mainSearchspec.append(" AND ([componentPart] = '"
					+ trim(partNumber) + "' "
					+ "OR EXISTS ([LXK MPS Supply Part# MVF] = '"
					+ trim(partNumber) + "'))");
		}
		
		mainSearchspec.append(" AND ([sapStatus] <> 'Inactive' OR [sapStatus] IS NULL)");
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		mainSearchspec.append(" AND ([LXK MPS Effective End Date] >= '");
		mainSearchspec.append(formatter.format(effectiveDate));
		mainSearchspec.append("' OR [LXK MPS Effective End Date] IS NULL)");
		
		QueryObject queryObject = AmindServiceUtil.newQueryObject(
				HardwareSapCatalogDo.class, mainSearchspec.toString(),
				contract.getIncrement(), contract.getStartRecordNumber());

		List<HardwareSapCatalogDo> doList = querySiebel(this.session,
				queryObject, contract);
		
		return HardwareCatalogServiceUtil.convertPickSapCatalogDoToHardwareCatalog(doList);
		
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> querySiebel(Session session,
			QueryObject criteria, MdmSearchContractBase contract) {
		logger.debug("[IN] querySiebel(...)");
		logger.debug("[IN] criteria componentSearchSpecMap = "
				+ criteria.getComponentSearchSpecMap());
		logger.debug("[IN] criteria sortingString =" + criteria.getSortString());
		IDataManager dataManager = session.getDataManager();
		List<T> hardwareList = null;
		if (contract.isNewQueryIndicator()) {
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
