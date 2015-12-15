package com.lexmark.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.service.MailService;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;

public class MailUtil {
	Properties props = getMailConfigProperties();
    
	 private static final String MAIL_PROPERTIES_FILE = "/mail.properties";
	 private static final String MAIL_FROM_PROPERTY = "mail.from";
	 private static String mailFrom;
	
	 static {
		 Properties mailProperty = getMailConfigProperties();
		 mailFrom = mailProperty.getProperty(MAIL_FROM_PROPERTY);
	 }
	 
	 private static Properties getMailConfigProperties() {
	      Properties props = new Properties();
	      InputStream in;
	      try {
	        in = MailUtil.class.getResourceAsStream(MAIL_PROPERTIES_FILE);
	        props.load(in);
	        in.close();
	      } catch (IOException e) {
	        new RuntimeException("Fail to get Mail Property configuration file.", e);
	      }
	      return props;
	} 
	 
	public static void sendEmail(String[] toAddresses, final String subject, final String content, final boolean isHtml) {
		
		if(toAddresses == null || toAddresses.length == 0) {
			throw new InvalidParameterException("mail to address is empty");
		}
		
		InternetAddress from = null;
		InternetAddress[] tos = new InternetAddress[toAddresses.length];
		try {
			from = new InternetAddress(mailFrom);
		}  catch (AddressException e) {
			new InvalidParameterException(e.getMessage());
		}
		try {
			for(int i=0; i<toAddresses.length; i++){
				String address = toAddresses[i];
				tos[i] = new InternetAddress(address);
			}
		}  catch (AddressException e) {
			new InvalidParameterException(e.getMessage());
		}
		
		MailService mailService = MailServiceUtil.getService();
		MailMessage mailMessage = new MailMessage(from,  subject, content, isHtml);
		mailMessage.setTo(tos);
		mailService.sendEmail(mailMessage);
	}
	
	public static void sendEmail(String toAddress, final String subject, final String content, final boolean isHtml) {
		String[] toAddresses = new String[1];
		toAddresses[0] = toAddress;
		sendEmail(toAddresses, subject, content, isHtml);
	}
	
	
}
