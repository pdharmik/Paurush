package com.lexmark.service.api;

import com.lexmark.contract.DatabaseAttachmentContract;
import com.lexmark.result.DatabaseAttachmentResult;

/**
 * This is interface for NotificationService. It defines all services
 * that related to Notification. 
 * @author Roger.Lin
 *
 */
public interface DatabaseAttachmentService {

	public DatabaseAttachmentResult retrieveDatabaseAttachmentList() throws Exception;

	public boolean deleteDatabaseAttachment(
			DatabaseAttachmentContract contract) throws Exception;
	
	public boolean saveDatabaseAttachment(
			DatabaseAttachmentContract contract) throws Exception;
}
