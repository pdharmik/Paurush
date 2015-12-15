package com.lexmark.service.impl.real;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.DeleteAttachmentContract;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.AttachmentResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.attachmentService.AttachmentListService;
import com.lexmark.service.impl.real.attachmentService.DeleteAttachmentService;
import com.lexmark.service.impl.real.attachmentService.DownloadAttachmentService;
import com.lexmark.service.impl.real.attachmentService.UploadArchiveAttachmentService;
import com.lexmark.service.impl.real.attachmentService.UploadAttachmentService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.service.impl.real.util.LogUtil;

public class AmindAttachmentService extends AmindSiebelCrmService implements AttachmentService {

	private SessionFactory statelessSessionFactory;
	
	public void deleteAttachment(AttachmentContract contract) {
		logger.debug("[IN] deleteAttachment");
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			DeleteAttachmentService service = new DeleteAttachmentService(contract);
			service.checkRequiredFields();		
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
			service.queryAndDelete();
		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		logger.debug("[OUT] deleteAttachment");
	}

	public void uploadArchiveAttachments() throws Exception {
		logger.debug("[IN] uploadArchiveAttachments");
		
		AttachmentProperties properties = new AttachmentProperties("Service Request");
		String uploadDestination = properties.getFileUploadDestination();
		UploadArchiveAttachmentService service = new UploadArchiveAttachmentService(uploadDestination,properties,this.getStatelessSessionFactory());
		service.uploadAttachment();
	
		logger.debug("[OUT] uploadArchiveAttachments");
	}
	
	/**
	 * TESTS: AttachmentListTest.class
	 */
	@Override
	public AttachmentListResult retrieveAttachmentList(AttachmentContract contract) throws Exception {
		logger.debug("[IN] retrieveAttachmentList");
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			AttachmentListService service = new AttachmentListService(contract);
			service.checkRequiredFields();
			service.buildSearchExpression();			
			session = crmSessionHandle.acquireMultiple();
			service.setSession(session);
			
			AttachmentListResult result = new AttachmentListResult();
			result.setAttachmentList(service.queryAndGetResultList());
			return result;
		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieve attachments list has failed.", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			logger.debug("[OUT] retrieveAttachmentList");
		}
	}
	
	/**
	 * TESTS: UploadAttachmentServiceTest.class
	 * @throws Exception 
	 */
	@Override
	public void uploadAttachments(AttachmentContract contract) throws Exception {
		logger.debug("[IN] uploadAttachment");
		logger.info("[IN] uploadAttachment");
    	UploadAttachmentService service = null;
		try {
			service = new UploadAttachmentService(contract);
			service.checkRequiredFields();
			service.renameAttachments();
			service.executeService(statelessSessionFactory);
		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
//			logger.error("upload attachment has failed after calling rename attachment with Visibility", e);
            logger.error("upload attachment has failed", e);
            if (service != null) {
            	service.renameAttachmentsWithVisibility();
            }
            
            throw e;
            
		} finally {
            if (service != null) {
            	service.shutdownService();	
            }
            logger.debug("[OUT] uploadAttachment");
            logger.info("[OUT] uploadAttachment");
		}
	}

	/**
	 * TESTS: DownloadAttachmentServiceTest.class
	 */
	@Override
	public AttachmentResult downloadAttachment(AttachmentContract contract) throws Exception {
		logger.debug("[IN] downloadAttachment");
		DownloadAttachmentService service = null;
		Session session = null;
		try {
			session = getStatelessSessionFactory().attachSession();
			
			service = new DownloadAttachmentService(contract);
			service.checkRequiredFields();
			service.buildSearchExpression();
			service.setSession(session);
			service.setUpBC();
			
			AttachmentResult result = new AttachmentResult();
			result.setFileStream(service.getInputStream());
			result.setFile(service.getDestPath());
			return result;
		} catch (Exception e) {
			if(service!=null) {
				service.removeTempFile();
			}
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("download attachment has failed", e);
		} finally {
			if (service != null) {
				service.releaseBusComp();
			}
			AmindServiceUtil.releaseSession(session);
			logger.debug("[OUT] downloadAttachment");
		}
	}
	
	@Override
	public void deleteTempFileAfterDownload(DeleteAttachmentContract contract) {
		logger.debug("[IN] deleteTempFileAfterDownload");
		try {
			if (contract.getFileStream() == null || contract.getFile() == null) {
				throw new IllegalArgumentException("None of InputStream and File can be null!");
			}
			AmindServiceUtil.deleteTempFileAfterDownload(contract.getFileStream(), contract.getFile());
		} catch (Exception e) {
			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("deleteTempFileAfterDownload failed", e);
		}
		finally {
			logger.debug("[OUT] deleteTempFileAfterDownload");
		}
	}
	
	public SessionFactory getStatelessSessionFactory() {
		if (statelessSessionFactory == null) {
			statelessSessionFactory = new StatelessSessionFactory();
		}
		return statelessSessionFactory;
	}

	public void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

}
