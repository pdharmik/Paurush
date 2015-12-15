package com.lexmark.service.impl.real;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.result.AttachmentListResult;

@RunWith(Parameterized.class)
public class AttachmentListTest extends AttachmentServiceStatefulBase {

	private final AttachmentContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "1-44RDQN9" });
		return list;
	}

	public AttachmentListTest(String identifier) {
		contract = new AttachmentContract();
		contract.setIdentifier(identifier);
	}

	@Test
	public void testRetrieveAttachmentList() throws Exception {
		contract.setSessionHandle(handle);
		AttachmentListResult result = service.retrieveAttachmentList(contract);
		assertNotNull("result is null!", result);
		List<Attachment> list = result.getAttachmentList();
		assertNotNull("list is null!", list);
		assertFalse("list is empty!", list.isEmpty());

		debugAttachments(list);
	}

	private void debugAttachments(List<Attachment> list) {
		for (Attachment attachment : list) {
			logger.debug("AttachmentName: ");
			logger.debug(attachment.getAttachmentName() );
//			logger.debug("Comment: ");
//			logger.debug(attachment.getComment());
//			logger.debug("CompletedOn: ");
//			logger.debug(attachment.getCompletedOn());
//			logger.debug("Description: ");
//			logger.debug(attachment.getDescription());
//			logger.debug("Size: ");
//			logger.debug(attachment.getSize());
//			logger.debug("Status: ");
//			logger.debug(attachment.getStatus());
//			logger.debug("SubmittedOn: ");
//			logger.debug(attachment.getSubmittedOn());
		}
	}

}
