package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.composeFilePath;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.detachSession;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.io.File;

import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.domain.Attachment;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindSiebelCrmService;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelBusComp;
import com.siebel.data.SiebelBusObject;
import com.siebel.data.SiebelException;

public class UploadRunnable extends AmindSiebelCrmService implements Runnable {

	private SessionFactory sessionFactory;
	private final String parentId;
	private final String renameFileName;
	private final String comment;
	private final AttachmentProperties properties;
	private final String visibility;
	private final String displayFileName;
	private AmindAttachmentServiceUtil attachmentServiceUtil;
	
	public UploadRunnable(String parentId, Attachment attachment, AttachmentProperties properties) {
		if (properties == null) {
			throw new IllegalArgumentException("Properties can not be null!");
		}
		String fileName = attachment.getAttachmentName();
		String comment = attachment.getDescription();
		String visibility = attachment.getVisibility();
		String displayFileName = attachment.getDisplayAttachmentName();
		this.displayFileName = (displayFileName == null) ? "" : displayFileName;
		this.renameFileName = (fileName == null) ? "" : fileName;
		this.comment = (comment == null) ? "" : comment;
		this.parentId = (parentId == null) ? "" : parentId;
		this.visibility = (visibility == null || (visibility != null && "Employee".equalsIgnoreCase(visibility))) ? "Both" : visibility;
		this.properties = properties;
		attachmentServiceUtil = new AmindAttachmentServiceUtil();
	}

	@Override
	public void run() {
		Session session = getSessionFactory().attachSession();
		SiebelBusComp busComp = null;
		
		try {
			busComp = attachmentServiceUtil.setupBC(session.getDataSource(), properties);
			if (busComp.newRecord(false)) {
				busComp.setFieldValue(properties.getBcParentFieldName(), this.parentId);
				if(LangUtil.isNotEmpty(properties.getBcDeferFlag())){
					busComp.setFieldValue(properties.getBcDeferFlag(), "R");
				}
				if(LangUtil.isNotEmpty(properties.getBcFileFieldName())){
					busComp.setFieldValue(properties.getBcFileFieldName(), this.renameFileName);
				}
				if(LangUtil.isNotEmpty(properties.getBcDisplayName())){
					busComp.setFieldValue(properties.getBcDisplayName(), this.displayFileName);
				}
				if(LangUtil.isNotEmpty(properties.getBcFileSourceType())){
					busComp.setFieldValue(properties.getBcFileSourceType(),"FILE");
				}
				if(LangUtil.isNotEmpty(properties.getBcDockFlag())){
					busComp.setFieldValue(properties.getBcDockFlag(),"N");
				}
				if(LangUtil.isNotEmpty(properties.getBcUploadFlag())){
					busComp.setFieldValue(properties.getBcUploadFlag(),"Y");
				}
				if(LangUtil.isNotEmpty(properties.getBcDockStarFlag())){
					busComp.setFieldValue(properties.getBcDockStarFlag(),"E");
				}
				if ("Success".equalsIgnoreCase(createSiebelFile(busComp))) {
					logger.debug("Attachment Id" + busComp.getFieldValue("Id"));
					setFileComment(busComp, properties.getBcDescription());
					setFileVisibility(busComp, properties.getBcVisibility());
					busComp.writeRecord();
					if (!deleteLocalFile()) {
						logger.error("Error occurred while trying to delete temporary file for Siebel file upload");
					}
				}
			}
		} catch (Exception e) {
			
			throw new SiebelCrmServiceException("Upload attachment failed after renaming attachment", e);
		} finally {

			if (busComp != null) {
				busComp.release();
			}
			SiebelBusObject siebelObject = attachmentServiceUtil.getSiebelBusObject();
			if( siebelObject != null){
				siebelObject.release();
				
			}
			/*try {
				ServiceUtil.getSiebelLogNumber(session);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			detachSession(session);
		}
	}

	private String createSiebelFile(SiebelBusComp busComp) throws SiebelException {
		String siebelFileDestination = composeFilePath(properties.getSiebelFileDestination(), this.displayFileName) ;
		String[] parameters = { siebelFileDestination, properties.getBcFileFieldName(), "NO", this.renameFileName };
		return busComp.invokeMethod("CreateFile", parameters);
	}
	
	private void setFileComment(SiebelBusComp busComp, String bcDescription) throws SiebelException {
		if (isNotEmpty(bcDescription)) {
			busComp.setFieldValue(bcDescription, this.comment);
		}
	}
	
	private void setFileVisibility(SiebelBusComp busComp, String bcVisibility) throws SiebelException {
		if (isNotEmpty(bcVisibility)) {
			busComp.setFieldValue(bcVisibility, this.visibility);
		}
	}

	private boolean deleteLocalFile() {
		String localWebPath = composeFilePath(properties.getFileUploadDestination(), this.displayFileName);
		File file = new File(localWebPath);
		return file.delete();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		if (sessionFactory == null) {
			throw new IllegalArgumentException("sessionFactory can not be null!");
		}
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new IllegalStateException("sessionFactory is not initialized!");
		}
		return sessionFactory;
	}
}
