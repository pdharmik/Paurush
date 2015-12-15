package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AttachmentContract;
import com.lexmark.result.AttachmentResult;

@RunWith(Parameterized.class)
public class DownloadAttachmentServiceTest extends AttachmentServiceStatefulBase {
	
	private final AttachmentContract contract;
	
	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
//
		//
		list.add(new Object[] {"Service Request", "RFC_Template_Add(1)_1351276936477.xls", "1-4M473X3"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@file1.1", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@file2.1", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@file4", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@file5", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@file6", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@lxkssbliapp06a_1694_125_0_BP10002889~Claims_retest124~20120222034428044", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@lxkssbliapp06a_1704_385_0_BP10002816~Claims~20120213035532055", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@onenewtest0405", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@test03052012", "1-U3M84E"});
//		list.add(new Object[] {"Service Request", "1-U3M84E@test04052012", "1-U3M84E"});
		
//		list.add(new Object[] {"Service Request", "1-U3M84E@testCSV", "1-U3M84E"});
		
		return list;
	}
	
	public DownloadAttachmentServiceTest(String requestType, String userFileName, String identifier) {
		contract = new AttachmentContract();
		contract.setRequestType(requestType);
		contract.setUserFileName(userFileName);
		contract.setIdentifier(identifier);
	}

	@Test
	public void testDownloadAttachment() throws Exception {
		contract.setSessionHandle(handle);
		AttachmentResult result = service.downloadAttachment(contract);
		assertNotNull("file stream is null",result.getFileStream());
	}
}
