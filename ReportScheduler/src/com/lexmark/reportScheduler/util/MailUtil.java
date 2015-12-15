package com.lexmark.reportScheduler.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.util.report.PropertiesUtil;

public class MailUtil {
	private static final String  MAIL_CONFIGURATION = "/mail.properties";
	private static Logger logger = LogManager.getLogger(MailUtil.class);
	
	public static void sendMail(String receipts, String subject, String mailContent)  throws Exception{
		InternetAddress[] recipients =	InternetAddress.parse(receipts);//must be a comma separated addresses
	   
		Properties props = loadProperties();
	    String auth = props.getProperty("mail.smtp.auth", "false");
	    //String auth = props.getProperty("mail.session.mail.smtp.auth", "false");
		
	    
	    Authenticator authenticator = null;
	    String from = props.getProperty("mail.smtp.user");
	    if(auth !=null && auth.trim().equalsIgnoreCase("true")) {
	    final String userName = props.getProperty("mail.smtp.user");
	    final String password = props.getProperty("mail.smtp.password");
	    authenticator = new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{ return new PasswordAuthentication(userName, password);	}
			};
	    };
	    
	    Session mailConnection = Session.getInstance(props, authenticator);	
	    
	    Message msg = new MimeMessage(mailConnection);

	   //Email address alias  (added to mail.smtp.user)
	    //Address fromAddress = new InternetAddress(from, "ReportScheduler");
	    Address fromAddress = new InternetAddress(from, props.getProperty("mail.smtp.userName"));


	    msg.setContent(mailContent, "text/html");
	    msg.setFrom(fromAddress);
	    msg.setRecipients(Message.RecipientType.TO, recipients);
	    msg.setSubject(subject);
	    //msg.setText(mailContent);
        

	    Transport.send(msg);
	    logger.info("Mail sent to recipientEmailIds "+ receipts);
	}
	
	private static Properties loadProperties() {
		Properties prop = new Properties();
		InputStream in;
		try {
			in = MailUtil.class.getResourceAsStream(MAIL_CONFIGURATION);
			prop.load(in);
			in.close();
		} catch (IOException e) {
			new RuntimeException("Fail to get reportScheduler.properties Property configuration file.", e);
		}
	    return prop;
	}
}
