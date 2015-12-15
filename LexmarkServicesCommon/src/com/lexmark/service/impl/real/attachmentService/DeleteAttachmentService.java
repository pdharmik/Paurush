package com.lexmark.service.impl.real.attachmentService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.*;
import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.service.impl.real.domain.ServiceRequestAttachmentDo;
import org.apache.logging.log4j.Logger;


public class DeleteAttachmentService {

	//protected static final Log logger = LogFactory.getLog(DeleteAttachmentService.class);
	protected static final Logger logger = LogManager.getLogger(DeleteAttachmentService.class);

	private final String identifier;
	private Session session;

	public DeleteAttachmentService(AttachmentContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		identifier = contract.getIdentifier();
	}

	public void checkRequiredFields() {
		checkIdentifier(identifier);
	}

	public void queryAndDelete() {
		IDataManager dataManager = getSession().getDataManager();
		ServiceRequestAttachmentDo attachmentDo = (ServiceRequestAttachmentDo) dataManager.queryById(ServiceRequestAttachmentDo.class, identifier);
		if(attachmentDo != null){
			attachmentDo.setId(identifier);
			attachmentDo.setVisibility("Lexmark Only");
			dataManager.update(attachmentDo);
		} else {
			throw new IllegalStateException("Attachment not found!");
		}
		
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not set!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		}
		this.session = session;
	}

}
