package com.lexmark.service.impl.real.catalogService;

import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertHardwareCatalogDoToListOfValues;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertHardwareCatalogDoToListOfValuesB2B;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertProductDoToListOfValues;
import static com.lexmark.service.impl.real.catalogService.AmindOrderSuppliesCatalogConversionUtil.convertProductDoToListOfValuesB2B;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotBlank;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.trim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.service.impl.real.domain.AgreementAccountDo;
import com.lexmark.service.impl.real.domain.HardwareCatalogPaymentTypeDo;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.service.impl.real.domain.PartTypeDo;
import com.lexmark.service.impl.real.domain.ProductModelDo;
import com.lexmark.service.impl.real.domain.ProductTypeDo;
import com.lexmark.util.LangUtil;


public class CatalogFieldListService {
    
    //public static final Log logger = LogFactory.getLog(CatalogFieldListService.class);
	public static final Logger logger = LogManager.getLogger(CatalogFieldListService.class);

	private final String accountId;
	private final String productType;
	private final String productModel;
	private String agreementId;
	private String soldToNumber;
	private String paymentType;
	private boolean catalogFlag;
	private boolean hardwareFlag;
	private boolean hardwareAccessories;

	private String searchExpression;
	private Class<?> doClass;
	private Session session;
	private IDataManager dataManager;
	private String childSearchExpression;
	private String contractNumber;
	private Date effectiveDate;

	public CatalogFieldListService(CatalogListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}
		accountId = contract.getAccountId();
		productType = contract.getProductType();
		productModel = contract.getProductModel();

		agreementId = contract.getAgreementId();
		soldToNumber = contract.getSoldToNumber();
		paymentType = contract.getPaymentType();
		
		catalogFlag = contract.isCatalogFlag();
		hardwareFlag = contract.isHardwareFlag();
		
		contractNumber = contract.getContractNumber();
		hardwareAccessories = contract.isHardwareAccessoriesFlag();
		
		this.effectiveDate = contract.getEffectiveDate();
	}

	public void checkRequiredFields() {
		if (isEmpty(accountId) && isEmpty(agreementId) ) {
			throw new IllegalArgumentException("accountId or agreementId is empty or null");
		}
		if(effectiveDate == null) {
			throw new IllegalArgumentException("Effective date is null");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();

		if (isEmpty(agreementId)) {
			agreementId = queryAgreementId();
			if (isEmpty(agreementId)) {
				throw new IllegalArgumentException("agreement is empty or null");
			}
		}

		if (hardwareFlag) {
			builder.append("[agreementId]='");
			builder.append(agreementId);
			builder.append("'");
			doClass = HardwareSapCatalogDo.class;

			if (isNotBlank(paymentType)) {
				builder.append(" AND  [billingModel] = '");
				builder.append(paymentType);
				builder.append("'");
			}

			if (isNotBlank(soldToNumber)) {
				builder.append(" AND [sapSoldTo]='");
				builder.append(soldToNumber);
				builder.append("'");
			}

			if (isNotBlank(contractNumber)) {
				builder.append(" AND [sapContract]= '");
				builder.append(contractNumber);
				builder.append("'");
			}
			
			if (isNotBlank(productType)) {
				if (hardwareAccessories) {
					builder.append(" AND (([LXK MPS Material Line] = 'Printers'");
				} else {
					builder.append(" AND ((([LXK MPS Material Line] = 'Printers' OR [LXK MPS Material Line] = 'Options' OR [LXK MPS Material Line] = 'Service Parts')");
				}
				builder.append(" AND [productType] = '");
				builder.append(productType);
				builder.append("')");
				builder.append(" OR (EXISTS ([LXK MPS Product Type MVF] = '");
				builder.append(productType);
				builder.append("')))");
				childSearchExpression = " [LXK MPS Part Type] = 'Printers' OR [LXK MPS Part Type] = 'Service Parts' ";
			}
			builder.append(" AND [LXK MPS Display On Portal]='Y'");
		} else {
			builder.append("[agreementId]='");
			builder.append(agreementId);
			builder.append("'");
			doClass = ProductTypeDo.class;

			if (isNotBlank(productType)) {
				builder.append(" AND [productType]='");
				builder.append(trim(productType));
				builder.append("'");
				doClass = ProductModelDo.class;
				
				if (isNotBlank(productModel)) {
					builder.append(" AND [productModel]='");
					builder.append(trim(productModel));
					builder.append("'");
					doClass = PartTypeDo.class;
				}
			}

			if (LangUtil.isNotBlank(paymentType)
					&& LangUtil.isNotBlank(soldToNumber)) {
				builder.append(" AND (EXISTS (([LXK MPS Billing Model MVF] = '"
						+ paymentType + "'");

				if (!"Ship and Bill".equalsIgnoreCase(paymentType)) {
					builder.append(" OR [LXK MPS Billing Model MVF] IS NULL");
				}

				builder.append(")");

				// builder.append(" AND [LXK MPS Sold To MVF] = '" +
				// soldToNumber + "'");

				if (isNotBlank(contractNumber)) {
					builder.append(" AND [LXK MPS SAP Contract MVF] = '");
					builder.append(contractNumber);
					builder.append("'");
				}
			
				builder.append("))");
				builder.append(" AND [LXK MPS Display On Portal]='Y'");
			}

			else if (isNotBlank(contractNumber)) {
				builder.append(" AND EXISTS ([LXK MPS SAP Contract MVF] = '");
				builder.append(contractNumber);
				builder.append("')");
			}
			
		}
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		builder.append(" AND ([LXK MPS Effective End Date] >= '");
		builder.append(formatter.format(effectiveDate));
		builder.append("' OR [LXK MPS Effective End Date] IS NULL)");
		
		searchExpression = builder.toString();
	}
	
	public void buildSearchExpressionB2B() {
		StringBuilder builder = new StringBuilder();

		if (isEmpty(agreementId)) {
			agreementId = queryAgreementId();
			if (isEmpty(agreementId)) {
				throw new IllegalArgumentException("agreement is empty or null");
			}
		}

		if (hardwareFlag) {
			builder.append("[agreementId]='");
			builder.append(agreementId);
			builder.append("'");
			doClass = HardwareSapCatalogDo.class;

			if (isNotBlank(paymentType)) {
				builder.append(" AND  [billingModel] = '");
				builder.append(paymentType);
				builder.append("'");
			}

			if (isNotBlank(soldToNumber)) {
				builder.append(" AND [sapSoldTo]='");
				builder.append(soldToNumber);
				builder.append("'");
			}

			if (isNotBlank(contractNumber)) {
				builder.append(" AND [sapContract]= '");
				builder.append(contractNumber);
				builder.append("'");
			}
			
			if (isNotBlank(productType)) {
				if (hardwareAccessories) {
					builder.append(" AND (([LXK MPS Material Line] = 'Printers'");
				} else {
					builder.append(" AND ((([LXK MPS Material Line] = 'Printers' OR [LXK MPS Material Line] = 'Options' OR [LXK MPS Material Line] = 'Service Parts')");
				}
					builder.append(" AND [LXK MPS B2B Part Type] = '");
					builder.append(productType);
					builder.append("')");
					builder.append(" OR (EXISTS ([LXK MPS B2B Part Type MVF] = '");
					builder.append(productType);
					builder.append("')))");
				childSearchExpression = " [LXK MPS Part Type] = 'Printers' OR [LXK MPS Part Type] = 'Service Parts' ";
			}
			builder.append(" AND [LXK MPS Display On Portal]='Y'");
		} else {
			builder.append("[agreementId]='");
			builder.append(agreementId);
			builder.append("'");
			doClass = ProductTypeDo.class;

			if (isNotBlank(productType)) {
				builder.append(" AND [productType]='");
				builder.append(trim(productType));
				builder.append("'");
				doClass = ProductModelDo.class;
				
				if (isNotBlank(productModel)) {
					builder.append(" AND [productModel]='");
					builder.append(trim(productModel));
					builder.append("'");
					doClass = PartTypeDo.class;
				}
			}

			if (LangUtil.isNotBlank(paymentType)
					&& LangUtil.isNotBlank(soldToNumber)) {
				builder.append(" AND (EXISTS (([LXK MPS Billing Model MVF] = '"
						+ paymentType + "'");

				if (!"Ship and Bill".equalsIgnoreCase(paymentType)) {
					builder.append(" OR [LXK MPS Billing Model MVF] IS NULL");
				}

				builder.append(")");

				// builder.append(" AND [LXK MPS Sold To MVF] = '" +
				// soldToNumber + "'");

				if (isNotBlank(contractNumber)) {
					builder.append(" AND [LXK MPS SAP Contract MVF] = '");
					builder.append(contractNumber);
					builder.append("'");
				}
			
				builder.append("))");
				builder.append(" AND [LXK MPS Display On Portal]='Y'");
			}

			else if (isNotBlank(contractNumber)) {
				builder.append(" AND EXISTS ([LXK MPS SAP Contract MVF] = '");
				builder.append(contractNumber);
				builder.append("')");
			}
			
		}
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		builder.append(" AND ([LXK MPS Effective End Date] >= '");
		builder.append(formatter.format(effectiveDate));
		builder.append("' OR [LXK MPS Effective End Date] IS NULL)");
		
		searchExpression = builder.toString();
	}

	@SuppressWarnings("unchecked")
	private String queryAgreementId() {
		List<AgreementAccountDo> agreementDoList = getDataManager().queryBySearchExpression(
				AgreementAccountDo.class, "[id]='" + accountId + "'");
		String agreementId = "";
		if (isNotEmpty(agreementDoList)) {
			agreementId =  agreementDoList.get(0).getAgreementId();
		}

		return agreementId;
	}

	@SuppressWarnings("unchecked")
	public List<ListOfValues> queryAndGetResultList() {
	    logger.debug("searchSpec: " + searchExpression);
	    QueryObject query = new QueryObject(doClass, ActionEvent.QUERY);
	    query.addComponentSearchSpec(doClass, searchExpression);
	    if (hardwareFlag) {
	    	query.addComponentSearchSpec(HardwareCatalogPaymentTypeDo.class, childSearchExpression);
			return convertHardwareCatalogDoToListOfValues(getDataManager().query(query),productType,productModel);
		} else {
			return convertProductDoToListOfValues(getDataManager().query(query));
		}
	}

	@SuppressWarnings("unchecked")
	public List<ListOfValues> queryAndGetResultListB2B() {
	    logger.debug("searchSpec: " + searchExpression);
	    QueryObject query = new QueryObject(doClass, ActionEvent.QUERY);
	    query.addComponentSearchSpec(doClass, searchExpression);
	    if (hardwareFlag) {
	    	query.addComponentSearchSpec(HardwareCatalogPaymentTypeDo.class, childSearchExpression);
			return convertHardwareCatalogDoToListOfValuesB2B(getDataManager().query(query),productType,productModel);
		} else {
			return convertProductDoToListOfValuesB2B(getDataManager().query(query));
		}
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

	private IDataManager getDataManager() {
		if (dataManager == null) {
			dataManager = getSession().getDataManager();
		}
		return dataManager;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	
	
}
