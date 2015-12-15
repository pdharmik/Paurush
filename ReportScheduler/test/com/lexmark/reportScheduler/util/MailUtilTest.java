package com.lexmark.reportScheduler.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.lexmark.util.report.PropertiesUtil;

public class MailUtilTest {

	@Test
	//private static final String  MAIL_CONFIGURATION = "/mail.properties";
	//private static Logger logger = Logger.getLogger(MailUtil.class);
	
	public void testSendMail() throws Exception {

		String content = "content -" + new Date().toString();
		String subject  = new Date().toString();
		//MailUtil.sendMail("sc_0571@163.com",  "test", content);
		
		
		
		MailUtil.sendMail(PropertiesUtil.SCHEDULER_RESULT_MAIL_LIST,subject, content);
		
		
		
		//logger.debug("Subject (timestamp): "+ subject);
		
	}

}
