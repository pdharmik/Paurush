package com.lexmark.services.portlet;



import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author wipro
 * @version 2.1
 *
 */
@Controller
@RequestMapping("VIEW")
public class CreditCardController extends BaseController{
	

	private static Logger logger = LogManager.getLogger(CreditCardController.class);

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping
	public String getChartName(Model model, RenderRequest request, RenderResponse response) {
		logger.debug("--------------Entry to Credit Card Controller------------------");		
		return "creditCard/processResponse";
	}



}
