package com.lexmark.service.api;

import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.DeleteAttachmentContract;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.AttachmentResult;

public interface AttachmentService {
	public AttachmentListResult retrieveAttachmentList(AttachmentContract contract) throws Exception;
	public void uploadAttachments(AttachmentContract contract) throws Exception;
	public AttachmentResult downloadAttachment(AttachmentContract contract) throws Exception;
	public void deleteAttachment(AttachmentContract contract) throws Exception;
	public void deleteTempFileAfterDownload(DeleteAttachmentContract contract);
}
