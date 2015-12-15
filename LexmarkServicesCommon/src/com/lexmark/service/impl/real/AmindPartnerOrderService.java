package com.lexmark.service.impl.real;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.MassUploadTemplateResult;
import com.lexmark.result.source.OrderAcceptResult;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.api.PartnerOrderService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.attachmentService.AmindMassUploadTemplateService;
import com.lexmark.service.impl.real.attachmentService.AttachmentListService;
import com.lexmark.service.impl.real.partnerOrderService.AcceptOrderService;
import com.lexmark.service.impl.real.partnerOrderService.OrderServiceList;
import com.lexmark.service.impl.real.partnerOrderService.PartnerOrderDetailService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-17
 */
public class AmindPartnerOrderService extends AmindSiebelCrmService implements PartnerOrderService {
    
    private StatelessSessionFactory statelessSessionFactory;
    
    private static final Map<String, String> UPLOAD_HISTORY_FITER_FIELD_MAP = populateUploadHistoryFilterFieldMap();
	private static final Map<String, String> UPLOAD_HISTORY_SORT_FIELD_MAP = populateUploadHistorySortFieldMap();

    public StatelessSessionFactory getStatelessSessionFactory() {
        return statelessSessionFactory;
    }

	public void setStatelessSessionFactory(StatelessSessionFactory statelessSessionFactory) {
        this.statelessSessionFactory = statelessSessionFactory;
    }

    
    /**
     * @see com.lexmark.service.impl.real.util.AmindPartnerOrderDetailConversionUtil#toOrder(com.lexmark.service.impl.real.domain.SupplyRequestDetailDo, String)
     */
    @Override
    public OrderDetailResult retrieveOrderDetail(OrderDetailContract contract) throws LGSCheckedException,
            LGSRuntimeException {
        logger.debug("[IN] Retrieve Order Detail ");
        Session session = null;
        try {
            PartnerOrderDetailService service = new PartnerOrderDetailService(contract);
            service.checkRequiredFields();
            service.buildSearchExpression();

            session = getStatelessSessionFactory().attachSession();
            service.setSession(session);
            return  service.queryAndGetResult();
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new LGSCheckedException("retrieveOrderDetail failed", e);
        } finally {
            if (session != null) {
            	AmindServiceUtil.releaseSession(session);
            }
            logger.debug("[OUT] Retrieve Order Detail ");
        }
    }
   
    
    @Override
    public OrderListResult retrieveOrderList(OrderListContract contract) throws Exception {
        logger.debug("[IN] Retrieve Order List");
        AmindCrmSessionHandle crmSessionHandle = null;
        Session session = null;
        Session  totalCountSession= null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = AmindServiceUtil.acquireMultipleSession(crmSessionHandle);
            totalCountSession = getStatelessSessionFactory().attachSession();
            return new OrderServiceList(session,totalCountSession, crmSessionHandle).retrieveOrderList(contract); 
        } catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("retrieveOrderList failed", e);
        } finally {
            AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
            AmindServiceUtil.releaseSession(totalCountSession);
            
            logger.debug("[OUT] Retrieve Order List");
        } 
       
    }    

    @Override
    public OrderAcceptResult acceptServiceOrderRequest(OrderAcceptContract contract) throws LGSCheckedException, LGSRuntimeException {
        OrderAcceptResult result = new OrderAcceptResult();
        logger.debug("[IN] Accept Service Order ");
        result.setResult(true);
        Session session = statelessSessionFactory.attachSession();
        AcceptOrderService service = new AcceptOrderService(session, contract);
        try{
            service.buildSearchExpression();
            service.updateAcceptOrders(service.queryAndGetAcceptOrders());

        }catch (Exception e) {
            result.setResult(false);
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("update accept orders failed", e);
        }finally{
            if(session != null){
            	AmindServiceUtil.releaseSession(session);
            }
        }
        logger.debug("[OUT] Accept Service Order ");
        return result;
    }
    
    @Override
    public AttachmentListResult retrieveRequestAttachmentList (AttachmentContract contract) throws Exception {
        AmindCrmSessionHandle crmSessionHandle = null;
        logger.debug("[IN] Retrieve Request Attachment List");
        AttachmentListResult result = new AttachmentListResult();
        Session session = null;
        try {
            crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
            session = AmindServiceUtil.acquireMultipleSession(crmSessionHandle);
            AttachmentListService service = new AttachmentListService(contract);
            service.setFilterMap(UPLOAD_HISTORY_FITER_FIELD_MAP);
            service.setSortMap(UPLOAD_HISTORY_SORT_FIELD_MAP);
            service.buildSearchExpressionRequestAttachment();
            service.setSession(session);

            result.setAttachmentList(service.queryAndGetResultRequestAttachment());
            
            if (contract.isNewQueryIndicator()) {
				int count = service.processTotalCount();
				result.setTotalCount(count);
				crmSessionHandle.setRequestTypeCount(count);
            } else {
				result.setTotalCount(crmSessionHandle.getRequestTypeCount());
			}
            
		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            throw new SiebelCrmServiceException("Retrieve Request Attachment List failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] Retrieve Request Attachment List");
		}        
    	return result;
    }
    
	@Override
	public MassUploadTemplateResult retrieveMassUploadTemplateList(MassUploadTemplateContract contract) throws Exception {
		logger.debug("[IN] retrieveMassUploadTemplateList");
		Session session = null;
		
		try {
			AmindMassUploadTemplateService service = new AmindMassUploadTemplateService(contract);
			service.checkRequredField();
			service.buildSearchExpression();

			session = statelessSessionFactory.attachSession();
			service.setSession(session);

			MassUploadTemplateResult result = new MassUploadTemplateResult();
			result.setOrderLineItemsList(service.queryAndGetResult());
			
			return result;
			
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveMassUploadTemplateList failed", e);
		} finally {
			AmindServiceUtil.releaseSession(session);
			logger.debug("[OUT] retrieveMassUploadTemplateList");
		}
	}
	
    private static Map<String, String> populateUploadHistorySortFieldMap() {
    	
    	Map<String,String> uploadHistorySortFieldMap = new HashMap<String, String>();

    	uploadHistorySortFieldMap.put("requestNumber", "SR Number");
    	uploadHistorySortFieldMap.put("status", "Attachment Status");
    	uploadHistorySortFieldMap.put("submittedOn","ActivityFileDate");
    	uploadHistorySortFieldMap.put("completedOn","LXK SW Web SR Status Date");
    	uploadHistorySortFieldMap.put("size", "ActivityFileSize");
    	uploadHistorySortFieldMap.put("fileName", "ActivityFileName");
    	
		return Collections.unmodifiableMap(uploadHistorySortFieldMap);
	}

	private static Map<String, String> populateUploadHistoryFilterFieldMap() {
		
		Map<String,String> uploadHistoryFilterFieldMap = new HashMap<String, String>();

		uploadHistoryFilterFieldMap.put("requestNumber", "SR Number");
    	uploadHistoryFilterFieldMap.put("status", "Attachment Status");
    	uploadHistoryFilterFieldMap.put("fileName", "ActivityFileName");
    	uploadHistoryFilterFieldMap.put("type", "LXK MPS Sub_Area");
    	
		return Collections.unmodifiableMap(uploadHistoryFilterFieldMap);
	}

}
