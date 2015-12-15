package com.lexmark.services.portlet.notifications;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: NotificationController.java
 * Package     		: com.lexmark.services.portlet.notifications
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */

/**
 * @author wipro 
 * @version 1.0 
 */
@Controller
@RequestMapping("VIEW")
public class NotificationController {

	private static Logger LOGGER = LogManager.getLogger(NotificationController.class);
	
	/**
	 * @return String 
	 */
	@RequestMapping
	public String showDefaultView(){
		LOGGER.debug("[ inside  showDefaultView ]");
		LOGGER.debug("[ inside  showDefaultView ]");
	
		return "notifications/notification";
	}
	
	
}

