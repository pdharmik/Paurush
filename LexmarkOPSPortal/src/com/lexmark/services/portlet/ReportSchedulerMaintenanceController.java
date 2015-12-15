package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ReportInstanceListContract;
import com.lexmark.domain.Report;
import com.lexmark.result.ReportDefinitionNameListResult;
import com.lexmark.result.ReportInstanceListResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.liferay.portal.kernel.util.JavaConstants;

@Controller
@RequestMapping("VIEW")
public class ReportSchedulerMaintenanceController extends BaseController{
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	private CustomerReportService customerReportService;
	
	@RequestMapping
	public String getReportList(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		ReportDefinitionNameListResult result = customerReportService.retrieveReportDefinitionNameList();
		List<String> nameList = result.getReportDefinitionNameList();
		model.addAttribute("nameList",nameList);
		
		PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletSession portletSession = portletRequest.getPortletSession();
        if (portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE) != null) {
        	model.addAttribute("definitionName", String.valueOf(portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE)));
		}
		return "customerReportsAdmin/schedulerMaintenance";
	}
	
	@RequestMapping(params = "action=runLogParametersLightBox")
	public String showRunLogParametersLightBox(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		int runLogID = Integer.parseInt(request.getParameter("runLogID"));
		Map<String,String> parameters = customerReportService.retrieveRunLogParameterList(runLogID);
		model.addAttribute("parameters",parameters);
		return "customerReportsAdmin/parameterDetails";
	}
	
	@ResourceMapping
	public void retrieveReportList(ResourceRequest request, ResourceResponse response) throws Exception{
		String strBeginDate = request.getParameter("beginDate");
		String strEndDate = request.getParameter("endDate");
		String strTimezone = request.getParameter("timezone");
		int timezone;;
		if (StringUtil.isStringEmpty(strTimezone)) {
			timezone = 0;
		} else {
			timezone = Integer.valueOf(strTimezone);
		}
		if (timezone < -12 || timezone > 12) {
			timezone = 0;
		}
		String definitionName = request.getParameter("definitionName");
		ReportInstanceListContract contract = new ReportInstanceListContract();
		if (strBeginDate!=null&&strEndDate!=null) {
			Date beginDate;
			Date endDate;
			beginDate = DATE_FORMAT.parse(strBeginDate);
			TimezoneUtil.adjustDate(beginDate, TimezoneUtil.getServerTimeZone() - timezone);
			// HH:mm:ss =  00:00
			contract.setStartDate(beginDate);
			
			endDate = DATE_FORMAT.parse(strEndDate);
			// HH:mm:ss =  23:59:59
			endDate.setTime(endDate.getTime() + LexmarkConstants.MILLISECONDS_IN_HOUR * 24 - 1000);
			TimezoneUtil.adjustDate(endDate, TimezoneUtil.getServerTimeZone() - timezone);
			contract.setEndDate(endDate);
		} else {
			contract.setStartDate(getDefaultBeginDate());
			Date end = getDefaultBeginDate();
			end.setTime(end.getTime() + LexmarkConstants.MILLISECONDS_IN_HOUR * 24 - 1000);
			contract.setEndDate(end);
		}
		PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletSession portletSession = portletRequest.getPortletSession();
		if (portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE) != null) {
			definitionName = String.valueOf(portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE));
			portletSession.removeAttribute("definitionName", PortletSession.APPLICATION_SCOPE);
		}
		if (!StringUtil.isStringEmpty(definitionName)){
			contract.setReportDefinitionName(definitionName);
		}
		ReportInstanceListResult result  = customerReportService.retrieveReportInstanceList(contract);
		List<Report>  reportList = result.getReportInstanceList();
		adjustReportsRunDate(reportList, timezone);
		int totalCount = reportList.size();
		String xmlContent = getXmlOutputGenerator(request.getLocale()).convertReportsToXML(result, totalCount, 0);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xmlContent);
		out.flush();
		out.close();
	}
	
	private Date getDefaultBeginDate() {
		Calendar calendarDateTime = Calendar.getInstance();
		calendarDateTime.set(Calendar.HOUR_OF_DAY, 0);
		calendarDateTime.set(Calendar.MINUTE, 0);
		calendarDateTime.set(Calendar.SECOND, 0);
		
		return calendarDateTime.getTime();
	}
	
	@ResourceMapping("rerunScheduleMaintenance")
	public void rerunScheduleMaintenance(@RequestParam("definitionId") String definitionId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean	success = customerReportService.rerunReport(definitionId);
		String errorCode = success?"message.reportAdmin.rerunScheduleMaintenance"
				:"exception.reportAdmin.rerunScheduleMaintenance";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
		}
	
	/**
	 * Adjust run date of report based on client time zone.
	 * @param reportList
	 * @param timezone
	 */
	private void adjustReportsRunDate(List<Report> reportList, int timezone) {
		for (Report report : reportList) {
			Date runTime = report.getRunTime();
			TimezoneUtil.adjustDate(runTime, timezone - TimezoneUtil.getServerTimeZone());
			report.setRunTime(runTime);
		}
	}
}
