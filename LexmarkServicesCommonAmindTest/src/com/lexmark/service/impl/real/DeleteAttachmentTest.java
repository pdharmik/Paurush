package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AttachmentContract;

/**
 * @author pkozlov
 */
@RunWith(Parameterized.class)
public class DeleteAttachmentTest extends AttachmentServiceStatefulBase {
	
	private final AttachmentContract contract;
	
	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
//		list.add(new Object[] { "1-ULD6AL" });
		list.add(new Object[] { "1-4L3IF69" });
//		list.add(new Object[] { "1-ULD6B5" });
		return list;
	}
	
	public DeleteAttachmentTest(String identifier) {
		contract = new AttachmentContract();
		contract.setIdentifier(identifier);
	}
	
	@Test
	public void testDeleteAttachment() {
		contract.setSessionHandle(handle);
		((AmindAttachmentService)service).deleteAttachment(contract);
	}

}
