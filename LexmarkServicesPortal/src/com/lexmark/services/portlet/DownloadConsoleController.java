package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.InputStream;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.domain.Report;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.services.reports.bean.DocDelDownloadInfo;
import com.lexmark.services.reports.service.LexmarkReportService;
import com.lexmark.services.reports.service.LexmarkReportServiceImpl;
import com.lexmark.services.reports.util.LexmarkReportUtil;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.ReportPathUtil;
import com.lexmark.services.util.ServiceStatusUtil;
@Controller
@RequestMapping("VIEW")
public class DownloadConsoleController {
	private static Logger logger = LogManager.getLogger(DownloadConsoleController.class);

	@Autowired
	private CustomerReportService customerReportService;
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showDefault(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		
		return "downloadConsole/downloadConsolePage";
	}
	
	private LexmarkReportService reportService;
	LexmarkReportService getLexmarkReportServiceImpl(){
		
		if(reportService == null){
			reportService = new LexmarkReportServiceImpl();
		}
		
		return reportService;
	}
	
	
	/*@RequestMapping(params = "action=showReportEmaiLinkelPage")
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
		
	}*/
	@RequestMapping(params = "action=showReportFromEmail")
	public String getReport(RenderRequest request, 
			RenderResponse response,Model model) throws Exception {
		String reportId = request.getParameter("reportId");
		String hasPermission="false";
		logger.debug("show Download Console page begins for report " + reportId);
		Report report = customerReportService.retrieveReportByDocumentID(reportId, LexmarkUserUtil.getUserRoleNameList(request));
		
		if(report.getFileObjectId() == null ){
			 hasPermission="false";
			model.addAttribute("hasPermission", hasPermission);
		}
		else{
			 hasPermission="true";
			model.addAttribute("hasPermission", hasPermission);
			model.addAttribute("reportId",reportId);
		}
		return "downloadConsole/downloadConsoleEmail";
	}
	
	@ResourceMapping("showReportEmaiLinkelPage")
	@ResponseBody
	public void showReportEmaiLinkelPage(@RequestParam("id") String id, ResourceRequest request, ResourceResponse response,Model model) throws Exception{
	
					
			logger.info("Start-------CustomerReportsController-----downloadReport");
			try {
				DocumentDeleteContract contract = ContractFactory
						.getDocumentDeleteContract(id);
				DocDelDownloadInfo docDelDownloadInfo = LexmarkReportUtil
						.getDocDelDownloadInfo(contract.getObjectId());
				HttpResponse resp = getLexmarkReportServiceImpl().downloadDocument(docDelDownloadInfo);
				
				InputStream respInpStream = resp.getEntity().getContent();

				byte[] documentBody = IOUtils.toByteArray(respInpStream);

				response.setContentType(MediaType.APPLICATION_OCTET_STREAM
						.toString());
				response.setContentLength(documentBody.length);
				String filename = null;
				for (int i = 0; i < resp.getAllHeaders().length; i++) {
					if (resp.getAllHeaders()[i].getName().equalsIgnoreCase(
							"content-disposition")) {
						filename = resp.getAllHeaders()[i].getValue();
						filename = filename.substring(
								filename.indexOf("filename = "), filename.length());
						filename = filename.replace("filename = ", "");
					}
				}
				response.setProperty("Content-Disposition", "attachment; filename="
						+ filename.trim());
				response.getPortletOutputStream().write(documentBody);
				
				logger.info("End-------CustomerReportsController-----downloadReport");

			} catch (IOException e) {
				logger.debug("IO Exception " + e.getMessage());
				ServiceStatusUtil.responseResult(response,
						"exception.customerReports.downloadReport", request.getLocale());
			} catch (Exception e) {
				logger.debug("Exception " + e.getMessage());
				ServiceStatusUtil.responseResult(response,
						"exception.customerReports.downloadReport", request.getLocale());
			}
		
			
			
		
		logger.debug("show Download Console page ends");
		
	}
}
