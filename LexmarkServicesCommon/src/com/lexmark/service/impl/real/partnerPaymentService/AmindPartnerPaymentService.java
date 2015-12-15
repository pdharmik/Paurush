package com.lexmark.service.impl.real.partnerPaymentService;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.result.AccountReceivableListResult;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindSiebelCrmService;
import com.lexmark.service.impl.real.domain.PartnerPaymentDo;
import com.lexmark.service.impl.real.domain.PartnerPaymentRequestDO;
import com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindPartnerPaymentService extends AmindSiebelCrmService implements PaymentsService {

	private Map<String, String> PARTNER_PAYMENT_FIELD_MAP = new HashMap<String, String>();
	private Map<String, String> PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP = new HashMap<String, String>();
		
	private SessionFactory statelessSessionFactory;
	
	public AmindPartnerPaymentService() {
		populatePartnerPaymentFieldMap();
		populatePartnerPaymentLineItemAndRequestFieldMap();
	}

	private void populatePartnerPaymentFieldMap() {
		PARTNER_PAYMENT_FIELD_MAP.put("mdmLevel1AccountId","LXK Global DUNS Number");
		PARTNER_PAYMENT_FIELD_MAP.put("mdmLevel2AccountId","LXK Domestic DUNS Number");
		PARTNER_PAYMENT_FIELD_MAP.put("mdmLevel3AccountId","LXK Legal Account Number");
		PARTNER_PAYMENT_FIELD_MAP.put("mdmLevel4AccountId","LXK MDM Account Number");
		PARTNER_PAYMENT_FIELD_MAP.put("mdmLevel5AccountId","Account Id");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.dateCreated","Created");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.paymentNumber","Invoice Number");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.partnerAgreement","Agreement Name");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.checkNumber","LXK SD EBuyer Transaction Id");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.paymentTotal","Total Amount");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.payableToName","Payable To Account");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.partnerAccount.accountName","LXK  Agreement Account Name");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.addressLine1","Payable To Address");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.addressLine2","Payable To Address2");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.addressLine3","Payable To Address3");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.city","Payable To City");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.state","Payable To State");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.province","Payable To Province");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.country","Payable To Country");
		PARTNER_PAYMENT_FIELD_MAP.put("Payment.address.postalCode","Payable To Postal Code");

	}
	
	private void populatePartnerPaymentLineItemAndRequestFieldMap() {
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.activityDate", "Creation Date");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.partnerAgreementName", "Agreement Name");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceRequest.serviceRequestNumber", "SR Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.payment.paymentNumber", "LXK SD Invoice Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.paymentServiceType", "LXK SD SR Type");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceRequest.serviceRequestStatus", "SR Status");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.eligibleToPay", "LXK R Payable");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.payEligiblityOverride", "LXK SD Pay Eligibility Override");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.laborPayment", "LXK SD Labor Total");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.partsPayment", "LXK SD Extended Line Total");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.partnerAccount.accountName", "LXK SD Agreement Account Name");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.customerAccount.accountName", "Account Name");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceRequest.asset.serialNumber", "LXK R Serial Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceRequest.asset.productTLI", "LXK R SR Product Name");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceRequest.asset.modelNumber", "LXK R SR Model");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceRequest.asset.productLine", "LXK R SR Product Line");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.partnerFee", "LXK SD Partner Fee");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("Activity.serviceProviderReferenceNumber", "LXK R SP Reference #");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("mdmLevel1AccountId","LXK Global DUNS Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("mdmLevel2AccountId","LXK Domestic DUNS Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("mdmLevel3AccountId","LXK MDM Legal Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("mdmLevel4AccountId","LXK MDM Account Number");
		PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP.put("mdmLevel5AccountId","Account Id");
		
	}
	
	@Override
	public PaymentListResult retreivePaymentList(PaymentListContract contract) throws Exception {
		logger.debug("[IN] retreivePaymentList");
	
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		PaymentListResult result = new PaymentListResult();
		try {
		    checkPaymentListRequiredFields(contract);
			session = crmSessionHandle.acquireMultiple();
			IDataManager dataManager = session.getDataManager();
			String searchExpression = buildSearchSpecforPaymentList(contract,dataManager);
			
			QueryObject criteria =  new QueryObject(PartnerPaymentDo.class, ActionEvent.QUERY);
			criteria = AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(),contract.getStartRecordNumber());
			criteria.addComponentSearchSpec(PartnerPaymentDo.class,searchExpression );
			if(contract.getSortCriteria() != null && contract.getSortCriteria().size() > 0) {
				criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), PARTNER_PAYMENT_FIELD_MAP));
			}
			
			result = AmindPartnerDataManagerUtil.queryandgetPaymentList (criteria, crmSessionHandle, contract, searchExpression, session, dataManager );

		}
		catch (Exception e) 
		{
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retreivePaymentList failed", e);
		} 	finally
		{
			if(session != null) {
				crmSessionHandle.releaseMultipleSession(session);
		}

	}
		logger.debug("[OUT] retreivePaymentList");
		return result;
	}
	


	private void checkPaymentListRequiredFields(PaymentListContract contract) {
		if(contract.getSessionHandle() != null && StringUtils.isEmpty(contract.getSessionHandle().toString())) {
			throw new IllegalArgumentException("Session Handle can not be empty or null");
		}
		if(StringUtils.isEmpty(contract.getMdmId()) ) {
				throw new IllegalArgumentException("Employee Flag is false so MDM Id can not be empty or null");
			}
			if(StringUtils.isEmpty(contract.getMdmLevel()) ) {
				throw new IllegalArgumentException("Employee Flag is false so MDM Level can not be empty or null");
			}
	}
	
	private String buildSearchSpecforPaymentList(PaymentListContract contract, IDataManager dataManager) throws Exception {
		StringBuilder searchExpression = new StringBuilder();
			searchExpression.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel
					(contract.getMdmId(), contract.getMdmLevel(), 
							PARTNER_PAYMENT_FIELD_MAP, false, "LXK Account Level"));
		if(contract.getFilterCriteria() != null && contract.getFilterCriteria().size() > 0) {
			
			Map<String, Object> filterMap = contract.getFilterCriteria(); 
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date activityStartDate = (Date) filterMap.get("Payment.startDate");
			if(activityStartDate != null) {
				searchExpression.append(buildPaymentStartDate(dateFormat.format(activityStartDate)));
				filterMap.remove("Payment.startDate");
			}
			
			Date activityEndDate = (Date) filterMap.get("Payment.endDate");
			if(activityEndDate != null) {
				searchExpression.append(buildPaymentEndDate(dateFormat.format(activityEndDate)));
				filterMap.remove("Payment.endDate");
			}
			
			searchExpression.append(AmindServiceUtil.buildCriteria(filterMap, PARTNER_PAYMENT_FIELD_MAP, 
					true, true));
		}
		
		return searchExpression.toString();
	}
	private String buildPaymentStartDate(String paymentStartDate) {
		String searchSpec= "";
		searchSpec = " AND [Created] >= '" + paymentStartDate + "'";  
		return searchSpec;
	}
	
	private String buildPaymentEndDate(String paymentEndDate) {
		String searchSpec= "";
		searchSpec = " AND [Created] <= '" + paymentEndDate + "'";  
		return searchSpec;
	}
	
	private String buildPaymentRequestStartDate(String paymentStartDate) {
		String searchSpec= "";
		searchSpec = " AND [Creation Date] >= '" + paymentStartDate + "'";  
		return searchSpec;
	}
	
	private String buildPaymentRequestEndDate(String paymentEndDate) {
		String searchSpec= "";
		searchSpec = " AND [Creation Date] <= '" + paymentEndDate + "'";  
		return searchSpec;
	}
	
	
	@Override
	public PaymentLineItemDetailsResult retreivePaymentLineItemList(PaymentLineItemDetailsContract contract) throws Exception{
		logger.debug("[IN] retreivePaymentLineItemList");
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		PaymentLineItemDetailsResult result = new PaymentLineItemDetailsResult();
		try {
            if (contract.getSessionHandle() != null && StringUtils.isEmpty(contract.getSessionHandle().toString())) {
                throw new IllegalArgumentException("Session Handle can not be empty or null");
            }
            if (StringUtils.isEmpty(contract.getPaymentId())) {
                throw new IllegalArgumentException("Payment Id can not be empty or null");
            }
		    
			session = crmSessionHandle.acquireMultiple();
			StringBuilder searchExpression = new StringBuilder();
			searchExpression.append("[LXK SD Invoice Id] = '" +  contract.getPaymentId() + "'");
			
			QueryObject criteria = new QueryObject(PartnerPaymentRequestDO.class, ActionEvent.QUERY);
			criteria = AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(),contract.getStartRecordNumber());
			
			if(contract.getFilterCriteria() != null && contract.getFilterCriteria().size() > 0) {
				
				searchExpression.append(AmindServiceUtil.buildCriteria(
						contract.getFilterCriteria(), PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP, 
						true, true));
			}
			
			if(contract.getSortCriteria() != null && contract.getSortCriteria().size() > 0) {
				if(!(contract.getSortCriteria().containsKey("Activity.totalPayment") || contract.getSortCriteria().containsKey("Activity.additionalPayments"))) {
					criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP));
				}
			}
			
			criteria.addComponentSearchSpec(PartnerPaymentRequestDO.class, searchExpression.toString());
			result = AmindPartnerDataManagerUtil.queryandgetPaymentLineItemList(criteria, crmSessionHandle, contract, searchExpression.toString(), session);

		}
		catch (Exception e) 
		{
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retreivePaymentLineItemList failed", e);
		} 	finally
		{
			if(session != null) {
				crmSessionHandle.releaseMultipleSession(session);
		}

	}
		logger.debug("[OUT] retreivePaymentLineItemList");
		return result;
	}

	@Override
	public PaymentDetailsResult retrievePaymentDetails(PaymentDetailsContract contract) throws Exception{
		logger.debug("[IN] retrievePaymentDetails");

		Session session = null;
		PaymentDetailsResult result = new PaymentDetailsResult();
		try {
            if (StringUtils.isEmpty(contract.getPaymentId())) {
                throw new IllegalArgumentException("PaymentId can not be empty or null");
            }
            
			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			result = AmindPartnerDataManagerUtil.queryandGetPaymentDetails(dataManager, contract);
		}
		catch (Exception e) 
		{
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrievePaymentDetails failed", e);
		}
		finally
		{
			if(session != null)
			{
				AmindServiceUtil.releaseSession(session);
			}
		}

		
		logger.debug("[IN] retrievePaymentDetails");
		return result;
	}

	public void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

	public SessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}

	@Override
	public PaymentRequestListResult retrievePaymentRequestList(PaymentRequestListContract contract) throws Exception {
		logger.debug("[IN] retrievePaymentRequestList");
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		PaymentRequestListResult result = new PaymentRequestListResult();

		try {
            if (contract.getSessionHandle() != null && StringUtils.isEmpty(contract.getSessionHandle().toString())) {
                throw new IllegalArgumentException("Session Handle can not be empty or null");
            }
            if (StringUtils.isEmpty(contract.getMdmId())) {
                throw new IllegalArgumentException("Employee Flag is false so MDM Id can not be empty or null");
            }
            if (StringUtils.isEmpty(contract.getMdmLevel())) {
                throw new IllegalArgumentException("Employee Flag is false so MDM Level can not be empty or null");
            }

			session = crmSessionHandle.acquireMultiple();
			IDataManager dataManager = session.getDataManager();

			StringBuilder searchExpression = new StringBuilder();
			searchExpression.append(buildSearchSpecForPaymentRequest(contract, dataManager));
			
			QueryObject criteria = new QueryObject(PartnerPaymentRequestDO.class, ActionEvent.QUERY);
			criteria = AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());

			criteria.addComponentSearchSpec(PartnerPaymentRequestDO.class, searchExpression.toString());
			
			if(contract.getSortCriteria() != null && contract.getSortCriteria().size() > 0) {
				if(!(contract.getSortCriteria().containsKey("Activity.totalPayment") || contract.getSortCriteria().containsKey("Activity.additionalPayments"))) {
					criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP));
				}
				
			}
			result  = AmindPartnerDataManagerUtil.queryandgetPaymentRequestList (criteria, crmSessionHandle, contract, searchExpression.toString(),	session,  dataManager );
		}
		catch (Exception e) 
		{
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrievePaymentRequestList failed", e);
		} 	finally
		{
			if(session != null) {
				crmSessionHandle.releaseMultipleSession(session);
		}

	}
		logger.debug("[OUT] retrievePaymentRequestList");
		return result;
	} 
	
	private String buildSearchSpecForPaymentRequest(PaymentRequestListContract contract, IDataManager dataManager) throws Exception {
		StringBuilder searchExpression = new StringBuilder();
		
		searchExpression.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel
					(contract.getMdmId(), contract.getMdmLevel(), 
							PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP	, false, "LXK Account Level"));
			if(!StringUtils.isEmpty(contract.getPaymentStatus()) ) {
				searchExpression.append(buildContractPaymentStatus(contract.getPaymentStatus()));
				
			}
		
		if(contract.getFilterCriteria() != null && contract.getFilterCriteria().size() > 0) {
			
			Map<String , Object> filterMap = contract.getFilterCriteria();
			String statusValue = (String) filterMap.get("Activity.payment.paymentStatus");
			if(statusValue != null && !StringUtils.isEmpty(statusValue)) {
				searchExpression.append(buildFilterPaymentStatus(statusValue));
				filterMap.remove("Activity.payment.paymentStatus");
			}
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date activityStartDate = (Date) filterMap.get("Activity.startDate");
			if(activityStartDate != null) {
				searchExpression.append(buildPaymentRequestStartDate(dateFormat.format(activityStartDate)));
				filterMap.remove("Activity.startDate");
			}
			
			Date activityEndDate = (Date) filterMap.get("Activity.endDate");
			if(activityEndDate != null) {
				searchExpression.append(buildPaymentRequestEndDate(dateFormat.format(activityEndDate)));
				filterMap.remove("Activity.endDate");
			}

			searchExpression.append(AmindServiceUtil.buildCriteria(
					contract.getFilterCriteria(), PARTNER_PAYMENT_LINEITEM_REQUEST_FIELD_MAP, 
					true, true));
		}

		return searchExpression.toString();
	}
	private String buildContractPaymentStatus(String paymentStatus) {
		StringBuilder searchSpec = new StringBuilder();
		if ("Paid".equalsIgnoreCase(paymentStatus)) {
			searchSpec.append(" AND [LXK R Payment Status] = 'Paid'");
		} else if ("UnPaid".equalsIgnoreCase(paymentStatus)) {
			searchSpec.append(" AND [LXK R Payment Status] <> 'Paid'");
		}
		return searchSpec.toString();
	}
	
	private String buildFilterPaymentStatus(String paymentStatus) {
		StringBuilder searchSpec = new StringBuilder();
		if("Draft".equalsIgnoreCase(paymentStatus)) {
			searchSpec.append(" AND ([LXK R Payment Status] = 'Draft' " +
					" OR [LXK R Payment Status] = 'Not Applicable')");
		} else if ("Reviewed".equalsIgnoreCase(paymentStatus)) {
			searchSpec.append(" AND ([paymentStatus] = 'Reviewed' " +
					" OR [LXK R Payment Status] = 'Approved' " +
					" OR [LXK R Payment Status] = 'Hold'" +
					" OR [LXK R Payment Status] = 'Pay'" +
					" OR [LXK R Payment Status] = 'Recommend Pay')") ;

		} else if ("Not Paid".equalsIgnoreCase(paymentStatus)) {
			searchSpec.append(" AND ([LXK R Payment Status] = 'Not Paid' " +
					" OR [LXK R Payment Status] = 'NoPay') ");
		} else {
			searchSpec.append(" AND [LXK R Payment Status] = '" + paymentStatus + "'");
		}
		
		return searchSpec.toString();
	}

    public AccountPayableListResult retrieveAccountPayableList(AccountPayableListContract contract) {
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = AmindServiceUtil.acquireMultipleSession(crmSessionHandle);
            return new AccountPayableListService(session, crmSessionHandle).retrieveAccountPayableList(contract); 
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAccountPayableList failed", e);
        } finally {
        	AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        }
    }
    
    
    public AccountReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract) {
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = AmindServiceUtil.acquireMultipleSession(crmSessionHandle);
            return new AccountReceivableListService(session, crmSessionHandle).retrieveAccountReceivableList(contract); 
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);            
            throw new SiebelCrmServiceException("retrieveAccountReceivableList failed", e);
        } finally {
        	AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
        }
    }    
    
    public AccountPayableDetailResult retrieveAccountPayableDetail(AccountPayableDetailContract contract) {
        Session session = null;
        try {
            session = statelessSessionFactory.attachSession();
            return new AccountPayableDetailService(session).retrieveAccountPayableDetail(contract); 
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveAccountPayableDetail failed", e); // TODO(Viktor) automatically insert method description
        } finally {
        	AmindServiceUtil.releaseSession(session);
        }
    }
}
