package com.lexmark.service.impl.real.attachmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexmark.domain.Attachment;
import com.lexmark.service.impl.real.domain.AttachmentDo;
import com.lexmark.service.impl.real.domain.RequestAttachment;
import com.lexmark.service.impl.real.domain.ServiceRequestAttachmentDo;
import com.lexmark.util.LangUtil;

public class AmindAttachmentConversionUtil {

	public static List<Attachment> convertAttachmentDoToAttachmentList(List<? extends com.amind.common.domain.Attachment> attachmentList) {
		List<Attachment> resultList = new ArrayList<Attachment>();

		for (com.amind.common.domain.Attachment attachmentBaseDo : attachmentList) {
			Attachment attachment = new Attachment();
			
			attachment.setDescription(attachmentBaseDo.getComments());
			attachment.setAttachmentName(attachmentBaseDo.getFileName());
			attachment.setSize(populateSize(attachmentBaseDo.getFileSize()));
			attachment.setActivityId(attachmentBaseDo.getParentId());
	
			if (attachmentBaseDo instanceof AttachmentDo) {
				AttachmentDo attachmentDo = (AttachmentDo) attachmentBaseDo;
				attachment.setSubmittedOn(LangUtil.convertStringToGMTDate(attachmentDo.getSubmittedOn()));
				attachment.setCompletedOn(LangUtil.convertStringToGMTDate(attachmentDo.getCompletedOn()));
				attachment.setStatus(attachmentDo.getStatus());
			} else if (attachmentBaseDo instanceof ServiceRequestAttachmentDo) {
				ServiceRequestAttachmentDo attachmentDo = (ServiceRequestAttachmentDo) attachmentBaseDo;
				attachment.setDescription(attachmentDo.getDescription());
			} 

			resultList.add(attachment);
		}

		return resultList;
	}


	public static List<Attachment> convertRequestAttachmentToAttachmentList(List<? extends com.amind.common.domain.Attachment> attachmentList) {

		List<Attachment> attachments = new ArrayList<Attachment>();
	
		for (com.amind.common.domain.Attachment attachmentDo : attachmentList) {
			Attachment attachment = new Attachment();
			
			if (attachmentDo instanceof RequestAttachment) {
				
				RequestAttachment requestAttachment = (RequestAttachment) attachmentDo;
				if (LangUtil.isBlank(requestAttachment.getStatus())) {
					attachment.setStatus("Submitted");
				} else {
					attachment.setStatus(requestAttachment.getStatus());
				}
				Date completedOn;
				if(LangUtil.isNotBlank(requestAttachment.getStatus())  && "Completed".equalsIgnoreCase(requestAttachment.getStatus()))
				{
					completedOn = LangUtil.convertStringToGMTDate(requestAttachment.getLastUpdatedOn());
					attachment.setCompletedOn(completedOn);
				}
				
				Date submittedOn = LangUtil.convertStringToGMTDate(requestAttachment.getSubmittedOn());
				attachment.setSubmittedOn(submittedOn);
				
				
				attachment.setRequestNumber(requestAttachment.getRequestNumber());
				attachment.setSize(populateSize(requestAttachment.getSize()));
				attachment.setType(requestAttachment.getType());
				attachment.setFileName(requestAttachment.getFileName());
				attachment.setSubArea(requestAttachment.getSubArea());
				attachment.setSrRowId(requestAttachment.getSrRowId());
				attachment.setExtension(requestAttachment.getFileExt());
			}
			
			if(!LangUtil.isBlank(attachment.getFileName())) {
				attachments.add(attachment);
			}
		}
		
		return attachments;
	}

	
	
	private static int populateSize(Integer fileSize) {
		
		if(fileSize == null ) {
			return 0;
		}
		
		return fileSize;
	}
}
