package com.lexmark.services.portlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("VIEW")
public class AccountInformationSelected {
	@RequestMapping
	public String showAccountInfo(){
		return "common/commonAccountNameInfo";
	}
}
