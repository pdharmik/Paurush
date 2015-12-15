package com.lexmark.portlet.source;


import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.form.ClaimDebriefForm;
import com.lexmark.result.ClaimDebriefSubmitResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.MailUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.ClaimService;

@Controller
@RequestMapping("VIEW")
public class AcceptOrderController {
	
	private PartnerRequestsService partnerRequestsService = null;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ClaimService claimWebService;


	private static Logger logger = LogManager.getLogger(AcceptOrderController.class);
	
	@RequestMapping(params="action=acceptOrder")
	public String acceptOrder (Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("[START] acceptOrder"); 
		logger.debug("[END] showCloseOutClaimPage"); 
		return "claims/claimDebriefView";		
	}	
	
}