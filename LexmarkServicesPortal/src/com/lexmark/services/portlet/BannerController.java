package com.lexmark.services.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("VIEW")

public class BannerController extends BaseController {
	/**
	 * @param model    
	 * @param request    
	 * @param response    
	 * @return String    
	 * @throws Exception    
	 */
	@RequestMapping
	public String showBannerPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		return "banner";
	}
	 
}
