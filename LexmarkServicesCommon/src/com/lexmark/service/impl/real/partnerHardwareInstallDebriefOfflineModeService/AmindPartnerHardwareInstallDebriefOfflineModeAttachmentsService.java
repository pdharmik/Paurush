package com.lexmark.service.impl.real.partnerHardwareInstallDebriefOfflineModeService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.OfflineModeAttachmentContract;
import com.lexmark.service.impl.real.domain.OfflineModeServiceRequestAttachmentDO;
import com.lexmark.util.LangUtil;


public class AmindPartnerHardwareInstallDebriefOfflineModeAttachmentsService {
	
	private Session session;
	private String activityId;
	
	public AmindPartnerHardwareInstallDebriefOfflineModeAttachmentsService(OfflineModeAttachmentContract contract) {
		activityId = contract.getActivityId();
	}

	public void checkRequiredFields() {
		if(LangUtil.isEmpty(activityId)) {
			throw new IllegalArgumentException("Activity id can not be empty or null!");
		}
	}
	
	public void generateInstallationDoc() {

		IDataManager dataManager = session.getDataManager();
		
		OfflineModeServiceRequestAttachmentDO attachmentDO = new OfflineModeServiceRequestAttachmentDO();
		attachmentDO.setFileDerefFlg("R");
		attachmentDO.setFileDockStatFlg("E");
		attachmentDO.setFileExtension("URL");
		attachmentDO.setFileSourceType("URL");

		String generatedFileName = generateFileName();
		attachmentDO.setFileName(generatedFileName);
		attachmentDO.setFileSourcePath(generatedFileName);
		
		attachmentDO.setActivityId(activityId);
		
		dataManager.create(attachmentDO);
        
	}
	
	private String generateFileName() {
		String generatedFileName = "Install_Debrief";
		
		DateFormat dateFormat = new SimpleDateFormat("_MMddyyyy_HHmmss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		generatedFileName += dateFormat.format(new Date()).toString();
		
		return generatedFileName;
	}
	
	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("Session has not been initialized!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("Session can not be null");
		}
		this.session = session;
	}
}
