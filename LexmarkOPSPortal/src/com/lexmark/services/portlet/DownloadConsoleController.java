package com.lexmark.services.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lexmark.domain.Report;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.ReportPathUtil;
@Controller
@RequestMapping("VIEW")
public class DownloadConsoleController {
	private static Logger logger = LogManager.getLogger(DownloadConsoleController.class);

	@Autowired
	private CustomerReportService customerReportService;
	
	@RequestMapping
	public String showDefault(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		return "downloadConsole/downloadConsolePage";
	}
	
	@RequestMapping(params = "action=showReportEmaiLinkelPage")
	public String showReportEmaiLinkelPage(RenderRequest request, 
			RenderResponse response,
									 Model model) throws Exception{
		String reportId = request.getParameter("reportId");
		logger.debug("show Download Console page begins for report " + reportId);
		Report report = customerReportService.retrieveReportById(reportId, LexmarkUserUtil.getUserRoleNameList(request));
		if(report == null ){
			model.addAttribute("hasPermission", false);
		}
		else{
			model.addAttribute("hasPermission", true);
			String url = ReportPathUtil.getReportFullURL(report.getFilePath());
			model.addAttribute("emailLink", url);
		}
		logger.debug("show Download Console page ends");
		return "downloadConsole/downloadConsolePage";
	}
}
