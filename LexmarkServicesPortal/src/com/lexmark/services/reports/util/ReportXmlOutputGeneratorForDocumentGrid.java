package com.lexmark.services.reports.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.reports.bean.DocumentBean;
import com.lexmark.services.reports.bean.ReportListRecord;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.PropertiesMessageUtil;

public class ReportXmlOutputGeneratorForDocumentGrid {
	private static Logger logger = LogManager.getLogger(ReportXmlOutputGeneratorForDocumentGrid.class);
	private static final String INDENT = "    ";

	private static String TODAY = "";
	private Locale locale = Locale.US;
	private Calendar calToday = Calendar.getInstance();
	
	private static final String IMG_GRAYED_OPEN = "<img style=\"width:14px; height:14px;\" src=\"";
	
	private static final String TRANSPARENT_FILE_NAME = "transparent.png";
	
	private static final String IMAGE_PATH = "/images/";
	
	private static final String FILE_ICON_PATH = "/images/fileIcons/";
	
	private String contextPath;

	private String imagePath;

	public ReportXmlOutputGeneratorForDocumentGrid(Locale locale) {
		this.locale = locale;
		TODAY = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "today", locale);
	}

	public ReportXmlOutputGeneratorForDocumentGrid(PortletRequest request) {
		this(request == null ? Locale.US : request.getLocale());
		if (request != null) {
			contextPath = request.getContextPath();
			imagePath = contextPath + "/images/";
		}
	}	

	private String formatDate(Date lastUpdateTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastUpdateTime);
		String strDate = null;
		if (sameDay(cal, calToday)) {
			strDate = TODAY;
		} else {
			strDate = DateLocalizationUtil.localizeDateTime(lastUpdateTime, false, locale);
		}
		return strDate;
	}

	private void appendIco(StringBuffer sbuff, DocumentBean document) {
		String fileType = document.getAcontenttype();
		String ico = LexmarkReportUtil.getFileTypeIcon(fileType);
		sbuff.append("<img src=\"").append(imagePath).append("fileIcons/").append(ico).append("\">");
	}
	
	public String generateDocumentUserListGridXML(List<DocumentBean> docs, boolean canDeleteFiles) throws ParseException {
		logger.debug("Start-------ReportXmlOutputGeneratorForDocumentGrid-----generateDocumentUserListGridXML");
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<rows total_count=\"").append(docs == null ? 0 : docs.size()).append("\">\n");
		if (docs != null) {
			for (DocumentBean document : docs) {
				appendDocumentToUserGrid(sbuff, document, canDeleteFiles);
			}
		}
		sbuff.append("</rows>\n");
		logger.debug("End-------ReportXmlOutputGeneratorForDocumentGrid-----generateDocumentUserListGridXML");
		return sbuff.toString();
	}

	private void appendDocumentToUserGrid(StringBuffer sbuff, DocumentBean document, boolean canDeleteFiles) throws ParseException {
		
		logger.debug("Start-------ReportXmlOutputGeneratorForDocumentGrid-----appendDocumentToUserGrid");
		indent(sbuff, 1).append("<row>\n");
		{
			
			indent(sbuff, 2).append("<cell><![CDATA[");
			sbuff.append("<A onclick=\"javascript:downloadDocument('");
			sbuff.append(document.getDocumentId() + "');\" ");
			sbuff.append(" href=\"#\">");
			sbuff.append(document.getObjectname());
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");

		}
		{
			indent(sbuff, 2).append("<cell>");
			String strDate = document.getF5();
			
			if(strDate != null && !strDate.isEmpty())
			{
				DateTime resultDtTime = LexmarkReportUtil.ISO_FORMAT.parseDateTime(strDate);
				Date resultDt = resultDtTime.toDate();
				strDate = formatDate(resultDt);
			}
			else
			{
				strDate = "";
			}
			sbuff.append(strDate);
			sbuff.append("</cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell>");
			sbuff.append(document.getFileSize()/1024);
			sbuff.append("</cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			appendIco(sbuff, document);
			sbuff.append("]]></cell>\n");
		}
		if(canDeleteFiles)
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			sbuff.append("<A href=\"javascript:deleteDocument('").append(document.getDocumentId()).append("');\">");
			sbuff.append("<IMG src=\"").append(imagePath).append("delete.png\">");
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");
		}else{
			indent(sbuff, 2).append("<cell></cell>\n");
		}
		indent(sbuff, 1).append("</row>\n");
		
		logger.debug("End-------ReportXmlOutputGeneratorForDocumentGrid-----appendDocumentToUserGrid");
	}

	private static StringBuffer indent(StringBuffer sbuff, int indent) {
		for (int i = 0; i < indent; i++) {
			sbuff.append(INDENT);
		}
		return sbuff;
	}

	private boolean sameDay(Calendar cal1, Calendar cal2) {
		if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR))
			return false;
		if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH))
			return false;
		if (cal1.get(Calendar.DATE) != cal2.get(Calendar.DATE))
			return false;
		return true;
	}	
	
	
	/**
	 * @param reportListRows 
	 * @param contextPath 
	 * @param response 
	 * @param canDeleteReport 
	 * @return String 
	 */
	public String convertCustomerReportsListWithoutStatusToXML(List<ReportListRecord> reportListRows,String contextPath, ResourceResponse response, boolean canDeleteReport){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + reportListRows.size() + "\" pos=\"0\">\n");
		int i = 0;
		for (ReportListRecord row : reportListRows) {
			xml.append(" <row id=\"" + i + "\">\n");
			xml.append("  <cell><![CDATA[" + row.getFilename() + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + DateLocalizationUtil.localizeDateTime(row.getCreatedDate(), false, locale) + "]]></cell>\n");
//			xml.append("  <cell><![CDATA[" + row.getStatus() + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + getReportActionsString(row, contextPath, canDeleteReport) + "]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		
		return xml.toString();
	}
	private String getReportActionsString(ReportListRecord row,String contextPath,boolean canDeleteReport) {
		String html = "<table><tr><td width=''30''>{0}</td><td width=''30''>{1}</td><td width=''30''>{2}</td><td width=''30''>{3}</td></tr></table>";
		String fileLinkPattern = "<a target=''_blank'' title=''{0}'' onclick=\"javascript:downloadReport(''" + row.getDocumentId() + "'');\"" + ">" + IMG_GRAYED_OPEN + contextPath + FILE_ICON_PATH
				+ "{1}\"/>" + "</a>";
		String fileLinkPattern2 = "<a target=''_blank'' title=''{0}'' onclick=\"javascript:downloadReport(''" + row.getDocumentId2() + "'');\"" + ">" + IMG_GRAYED_OPEN + contextPath + FILE_ICON_PATH
				+ "{1}\"/>" + "</a>";
		String mailLinkPartA = "<a href='#' onclick=\"hideSelect();openSendEmailPage('";
		String mailLinkPartB = "');\">" + IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\"  class=\"ui_icon_sprite email-icon\"/></a>";
		String delLinkPartAPattern = "<a href='#' title=''{0}''onclick=\"deleteReportOrDocument(''" + row.getReportJobId() + "'',''" + row.getCategoryId() + "'',''"
				+ String.valueOf(row.getReportDefinitionId()).trim();
//		String delLinkPartAPattern = "<a href='#' title=''{0}''onclick=\"deleteReport(''" + row.getDocumentId() + "'',''" + row.getCategoryId() + "'',''"
//				+ String.valueOf(row.getReportDefinitionId()).trim();
		String delLinkPartB = "');\">" + IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\" class=\"ui_icon_sprite trash-icon\" /></a>";
		String[] links = new String[] { " ", " ", " ", " " };
		String toolTipText = "";
		String toolTipText2 = "";
		String delToolTip = "";

		boolean manualReport = (row.getReportType() != null && "MU".equalsIgnoreCase(row.getReportType()));
		if (manualReport) {
			links[0] = "";
			if (row.getDocumentId() != null) {
				row.setStatus("Complete");
				String reportFileType = row.getFileType();
				MessageFormat mf = new MessageFormat(fileLinkPattern);
				toolTipText = toolTipText + "Complete * ";
				String[] params = new String[] { toolTipText, LexmarkReportUtil.getFileTypeIcon(reportFileType) };
				links[1] = mf.format(params);				
			}
			delToolTip = row.getStatus();
		} else {
			links[0] = "";
			links[1] = "";
			if (row.getDocumentId() != null) {
				String reportFileType = "excel";
				MessageFormat mf = new MessageFormat(fileLinkPattern);
				toolTipText = "Complete * "+ row.getParameterList();
				String[] params = new String[] { toolTipText, LexmarkReportUtil.getFileTypeIcon(reportFileType) };
				links[0] = mf.format(params);
			} else {
				links[0] = " ";
			}
			if (row.getDocumentId2() != null) {
				String reportFileType = "pdf";
				MessageFormat mf = new MessageFormat(fileLinkPattern2);
				toolTipText2 = "Complete * "+ row.getParameterList();
				String[] params2 = new String[] { toolTipText2, LexmarkReportUtil.getFileTypeIcon(reportFileType) };
				links[1] = mf.format(params2);
			} else {
				links[1] = " ";
			}
			delToolTip = row.getStatus()+" * "+row.getParameterList();
		}

		if (row.getStatus() == "Complete") {
			links[2] = mailLinkPartA + row.getFilename() + "','" + row.getDocumentId() + mailLinkPartB;
		}
				
		String[] params = new String[] { delToolTip };
		MessageFormat mfTemp = new MessageFormat(delLinkPartAPattern);
		links[3] = mfTemp.format(params) + delLinkPartB;
		// if don't have publishing role, can not delete manually upload report
		if(!canDeleteReport && manualReport) {
			links[3] = " ";
		}
		
		MessageFormat mf = new MessageFormat(html);
		return mf.format(links);
		
	}

	public String convertCustomerReportsListWithStatusToXML(List<ReportListRecord> reportListRows, String contextPath, ResourceResponse response, boolean canDeleteReport) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + reportListRows.size() + "\" pos=\"0\">\n");
		int i = 0;
		for (ReportListRecord row : reportListRows) {
			xml.append(" <row id=\"" + i + "\">\n");
			xml.append("  <cell><![CDATA[" + row.getFilename() + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + DateLocalizationUtil.localizeDateTime(row.getScheduleCreateDate(), false, locale) + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + row.getStatus() + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + getReportActionsString(row, contextPath, canDeleteReport) + "]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
}
