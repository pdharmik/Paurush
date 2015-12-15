package com.lexmark.services.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Document;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.PropertiesMessageUtil;

public class XmlOutputGeneratorForDocument {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(XmlOutputGeneratorForDocument.class);
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(XmlOutputGeneratorForDocument.class);

	private static final String INDENT = "    ";

	private static String TODAY = "";
	private Locale locale = Locale.US;
	private Calendar calToday = Calendar.getInstance();

	private String contextPath;

	private String imagePath;
/**
 * 
 * @param locale 
 */
	public XmlOutputGeneratorForDocument(Locale locale) {
		this.locale = locale;
		TODAY = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "today", locale);
	}
/**
 * 
 * @param request 
 */
	public XmlOutputGeneratorForDocument(PortletRequest request) {
		this(request == null ? Locale.US : request.getLocale());
		if (request != null) {
			contextPath = request.getContextPath();
			imagePath = contextPath + "/images/";
		}
	}
/**
 * 
 * @param hierarchy 
 * @return String 
 */
	@SuppressWarnings( { "unchecked" })
	public String generateDocumentFolderTree(HashMap hierarchy) {
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<tree id=\"").append(0).append("\">\n");
		appendDocumentHierarchyNodes(sbuff, 1, hierarchy);
		sbuff.append("</tree>\n");
		return sbuff.toString();
	}
/**
 * 
 * @param sbuff 
 * @param indent 
 * @param hierarchy 
 */
	@SuppressWarnings( { "unchecked" })
	private void appendDocumentHierarchyNodes(StringBuffer sbuff, int indent, HashMap hierarchy) {
		indent(sbuff, indent);
		for (Iterator iterator = hierarchy.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry enrty = (Map.Entry) iterator.next();
			Document doc = (Document) enrty.getKey();
			if (!doc.isDirectory()) {
				break;
			}
			indent(sbuff, indent).append("<item id=\"").append(doc.getFilePath().replace('/', '_')).append("\">\n");

			boolean isLeaf = !(enrty.getValue() instanceof HashMap);

			indent(sbuff, indent + 1).append("<itemtext><![CDATA[");
			if (isLeaf) {
				sbuff.append("<a href=\"javascript:viewDocuments('").append(doc.getFilePath()).append("');\">");
				sbuff.append(doc.getFileName()).append("</a>");
			} else {
				sbuff.append(doc.getFileName());
			}
			sbuff.append("]]></itemtext>\n");
			if (!isLeaf) {
				appendDocumentHierarchyNodes(sbuff, indent + 1, (HashMap) enrty.getValue());
			}
			indent(sbuff, indent).append("</item>\n");
		}

	}
/**
 * 
 * @param docs 
 * @return String 
 */
	public String generateDocumentListGrid(List<Document> docs) {
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<rows total_count=\"").append(docs == null ? 0 : docs.size()).append("\">\n");
		if (docs != null) {
			for (Document document : docs) {
				appendDocumentToGrid(sbuff, document);
			}
		}
		sbuff.append("</rows>\n");
		return sbuff.toString();
	}
/**
 * 
 * @param sbuff 
 * @param document 
 */
	private void appendDocumentToGrid(StringBuffer sbuff, Document document) {
		indent(sbuff, 1).append("<row>\n");
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			appendIco(sbuff, document);
			sbuff.append(document.getFileName());
			sbuff.append("]]></cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell>");
			Date lastUpdateTime = document.getLastUpdateTime();
			String strDate = formatDate(lastUpdateTime);
			sbuff.append(strDate);
			sbuff.append("</cell>\n");
		}
		indent(sbuff, 1).append("</row>\n");
	}
/**
 * 
 * @param lastUpdateTime 
 * @return String 
 */
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
	/**
	 * 
	 * @param sbuff 
	 * @param document 
	 */

	private void appendIco(StringBuffer sbuff, Document document) {
		String fileType = DocumentumWebServiceUtil.getDocumentTypeFromFilePath(document.getFilePath());
		String ico = DocumentumWebServiceUtil.getFileTypeIcon(fileType);
		sbuff.append("<img src=\"").append(imagePath).append("fileIcons/").append(ico).append("\">");
	}
/**
 * 
 * @param name 
 * @return String 
 */
	private static String getFileExt(String name) {
		int idx = name.lastIndexOf('.');
		if (idx >= 0) {
			return name.substring(idx + 1).trim();
		}
		return null;
	}
/**
 * 
 * @param hierarchy 
 * @return String 
 */
	public String generateDocumentCategoryTree(List<RoleCategory> hierarchy) {

		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<tree id=\"").append(0).append("\">\n");
		for (RoleCategory category : hierarchy) {

			indent(sbuff, 1).append("<item id=\"category").append(category.getCategoryId()).append("\">\n");
			indent(sbuff, 2).append("<itemtext>").append(category.getName()).append("</itemtext>\n");

			List<DocumentDefinition> docs = category.getDocumentList();
			for (DocumentDefinition document : docs) {
				indent(sbuff, 2).append("<item id=\"def").append(document.getId()).append("\">\n");
				indent(sbuff, 3).append("<itemtext><![CDATA[<a href=\"javascript:listDocument(");
				sbuff.append(document.getId()).append(");\">").append(document.getName()).append("</a>]]></itemtext>\n");
				indent(sbuff, 2).append("</item>\n");
			}
			indent(sbuff, 1).append("</item>\n");
		}
		sbuff.append("</tree>\n");
		return sbuff.toString();
	}
/**
 * 
 * @param docs 
 * @param canDeleteFiles 
 * @return String 
 */
	public String generateDocumentUserListGridXML(List<Document> docs, boolean canDeleteFiles) {
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<rows total_count=\"").append(docs == null ? 0 : docs.size()).append("\">\n");
		if (docs != null) {
			for (Document document : docs) {
				appendDocumentToUserGrid(sbuff, document, canDeleteFiles);
			}
		}
		sbuff.append("</rows>\n");
		return sbuff.toString();
	}
/**
 * 
 * @param sbuff 
 * @param document 
 * @param canDeleteFiles 
 */
	private void appendDocumentToUserGrid(StringBuffer sbuff, Document document, boolean canDeleteFiles) {
		indent(sbuff, 1).append("<row>\n");
		{
			
			indent(sbuff, 2).append("<cell><![CDATA[");
			sbuff.append("<A target=\"_blank\" onclick=\"javascript:downloadDocument('");
			sbuff.append(document.getFileName() + "');\" ");
			sbuff.append(" href=\"" + ReportPathUtil.getReportFullURL(document.getFilePath()) + "\">");
			sbuff.append(document.getFileName());
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");

		}
		{
			indent(sbuff, 2).append("<cell>");
			Date lastUpdateTime = document.getLastUpdateTime();
			String strDate = formatDate(lastUpdateTime);
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
			sbuff.append("<A href=\"javascript:deleteDocument('").append(document.getFileObjectId()).append("');\">");
			sbuff.append("<IMG class=\"ui_icon_sprite trash-icon\" src=\"").append(imagePath).append("transparent.png\">");
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");
		}else{
			indent(sbuff, 2).append("<cell></cell>\n");
		}
		indent(sbuff, 1).append("</row>\n");
	}
/**
 * 
 * @param sbuff 
 * @param indent 
 * @return StringBuffer 
 */
	private static StringBuffer indent(StringBuffer sbuff, int indent) {
		for (int i = 0; i < indent; i++) {
			sbuff.append(INDENT);
		}
		return sbuff;
	}
/**
 * 
 * @param cal1 
 * @param cal2 
 * @return boolean 
 */
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
 * 
 * @param docs 
 * @return String 
 */
	public String generateDocumentAdminListGrid(List<DocumentDefinition> docs) {
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<rows total_count=\"").append(docs == null ? 0 : docs.size()).append("\">\n");
		if (docs != null) {
			for (DocumentDefinition document : docs) {
				appendDocumentToAdminGrid(sbuff, document);
			}
		}
		sbuff.append("</rows>\n");
		return sbuff.toString();
	}
	
	//For CI BRD 14-06-01
	/**
	 * 
	 * @param docs 
	 * @param CategoryName 
	 * @return String 
	 */
	public String generateDocumentDisplayOrderGrid(List<DocumentDefinition> docs, String CategoryName) {
		logger.debug("docs sixe in xmlgenerator------->>"+docs.size());
		String category = CategoryName;
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<rows total_count=\"").append(docs == null ? 0 : docs.size()).append("\">\n");
		if (docs != null) {
			for (DocumentDefinition document : docs) {
				appendDisplayOrderToPopupGrid(sbuff, document, category);
			}
		}
		sbuff.append("</rows>\n");
		logger.debug("Buffer value ------------>>"+sbuff.toString());
		return sbuff.toString();
	}
/**
 * 
 * @param sbuff 
 * @param document 
 */
	private void appendDocumentToAdminGrid(StringBuffer sbuff, DocumentDefinition document) {
		indent(sbuff, 1).append("<row>\n");
		
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			//sbuff.append("<A href=\"javascript:goDocumentName(").append(document.getId()).append(");\">");		
			sbuff.append((document.getDisplay_order().equalsIgnoreCase("0") || document.getDisplay_order() == null)  ? " " : document.getDisplay_order());
			//sbuff.append("</A>");
			sbuff.append("]]></cell>\n");
		}
		
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			String customerAccount = "";
			if(null != document.getCustomerAccount()){
				customerAccount = document.getCustomerAccount();
				logger.debug("11111111 "+customerAccount);		
				
			}
			sbuff.append("<A href=\"javascript:goDocumentName(").append(document.getId()).append(",'").append(customerAccount).append("');\">");
			//sbuff.append("<A href=\"javascript:goDocumentName(").append(document.getId()).append(");\">");
			sbuff.append(document.getName());
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");
		}
		
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			if(Boolean.TRUE.equals(document.getLimitFlag())){				
				//sbuff.append("T");
				sbuff.append("<IMG class=\"ui_icon_sprite checkmark-icon\" src=\"").append(imagePath).append("transparent.png\"><div style=\"display:none\">True</div>");
			}
			else{
				sbuff.append("<div style=\"display:none\">False</div>");
			}			
			sbuff.append("]]></cell>\n");
		}
		
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			if(null != document.getCustomerAccount() && !"".equals(document.getCustomerAccount())){
				sbuff.append(document.getCustomerAccount());
			}
			else{
				sbuff.append("");
			}			
			sbuff.append("]]></cell>\n");
		}
		{
			String categoryName = "";
			RoleCategory roleCategory = document.getRoleCategory();
			if (roleCategory != null) {
				categoryName = roleCategory.getName();
			}
			//CI BRD 14-06-01 STARTS
			indent(sbuff, 2).append("<cell><![CDATA[");
			logger.debug("Category Name-->>"+categoryName);
			sbuff.append("<A href=\"javascript:displayCategoryPopup(\'").append(categoryName).append("\');\">");
			sbuff.append(categoryName);
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");
			logger.debug("----------cell values appended-----------");
			//CI BRD 14-06-01 ENDS
		}
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			sbuff.append("<A href=\"javascript:deleteDefinition(").append(document.getId()).append(");\">");
			sbuff.append("<IMG class=\"ui_icon_sprite trash-icon\" src=\"").append(imagePath).append("transparent.png\">");
			sbuff.append("</A>");
			sbuff.append("]]></cell>\n");
		}
		indent(sbuff, 1).append("</row>\n");
	}
	
	//Done for CI BRD 14-06-01
	/**
	 * 
	 * @param sbuff 
	 * @param document 
	 * @param categoryName 
	 */
	private void appendDisplayOrderToPopupGrid(StringBuffer sbuff, DocumentDefinition document, String categoryName) {
		LOGGER.enter(this.getClass().getSimpleName(), "appendDisplayOrderToPopupGrid");
		indent(sbuff, 1).append("<row>\n");
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			sbuff.append(document.getId());
			sbuff.append("]]></cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell><![CDATA[");
			sbuff.append(document.getName());
			sbuff.append("]]></cell>\n");
		}
		{
//			String categoryName = "";
//			RoleCategory roleCategory = document.getRoleCategory();
//			if (roleCategory != null) {
//				categoryName = roleCategory.getName();
//			}
			//CI BRD 14-06-01 STARTS
			indent(sbuff, 2).append("<cell><![CDATA[");
			logger.debug("Category Name-->>"+categoryName);
			sbuff.append(categoryName);
			sbuff.append("]]></cell>\n");
			logger.debug("----------cell values appended-----------");
			//CI BRD 14-06-01 ENDS
		}
		{
//			indent(sbuff, 2).append("<cell><![CDATA[");
			indent(sbuff, 2).append("<cell><![CDATA[<input type=\"text\"  maxlength=\"3\" style=\"width:50px;\" name=\"displayOrder\" value=\""+((document.getDisplay_order().equalsIgnoreCase("0") || document.getDisplay_order() == null) ? " " : document.getDisplay_order())+"\"");
			/*if((document.getDisplay_order()).equalsIgnoreCase("0")){
                sbuff.append("-");
        }
        else
        sbuff.append(document.getDisplay_order());
			//sbuff.append("]]></cell>\n");
*/			sbuff.append("</a>]]></cell>\n");
		}
		indent(sbuff, 1).append("</row>\n");
		LOGGER.exit(this.getClass().getSimpleName(), "appendDisplayOrderToPopupGrid");
	}
/**
 * 
 * @param categories 
 * @return String 
 */
	public String generateCategoryAdminListGrid(List<RoleCategory> categories) {
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sbuff.append("<rows total_count=\"").append(categories == null ? 0 : categories.size()).append("\">\n");
		if (categories != null) {
			int i = 0;
			for (RoleCategory category : categories) {
				appendCategoryToAdminGrid(sbuff, category, i, categories.size());
				i++;
			}
		}
		sbuff.append("</rows>\n");
		return sbuff.toString();
	}
/**
 * 
 * @param categories 
 * @return String 
 */
	public String generateCategoryAdminListJSON(List<RoleCategory> categories) {
		StringBuffer sbuff = new StringBuffer(300);
		sbuff.append("[");
		if (categories != null) {
			int i = 0;
			for (RoleCategory category : categories) {
				if (i != 0) {
					sbuff.append(", ");
				}
				appendCategoryJSON(sbuff, category, i, categories.size());
				i++;
			}
		}
		return sbuff.append("]").toString();
	}
/**
 * 
 * @param sbuff 
 * @param category 
 * @param idx 
 * @param total 
 */
	private void appendCategoryToAdminGrid(StringBuffer sbuff, RoleCategory category, int idx, int total) {
		indent(sbuff, 1).append("<row>\n");
		{
			indent(sbuff, 2).append("<cell>");
			sbuff.append(idx);
			sbuff.append("</cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell>");
			sbuff.append(idx);
			sbuff.append("</cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell>");
			sbuff.append(idx);
			sbuff.append("</cell>\n");
		}
		{
			indent(sbuff, 2).append("<cell>");
			sbuff.append(idx);
			sbuff.append("</cell>\n");
		}
		indent(sbuff, 1).append("</row>\n");
	}
/**
 * 
 * @param sbuff 
 * @param category 
 * @param idx 
 * @param total 
 */
	private void appendCategoryJSON(StringBuffer sbuff, RoleCategory category, int idx, int total) {
		sbuff.append("{");
		sbuff.append("id:").append(category.getCategoryId()).append(",");
		sbuff.append("name:\"");
		XMLEncodeUtil.appendJSON(sbuff,category.getName());
		sbuff.append("\",");
		sbuff.append("order:").append(category.getOrderNumber()).append(",");
		sbuff.append("locales:[");
		boolean first = true;
		for (RoleCategoryLocale locale : category.getLocaleList()) {
			if (!first) {
				sbuff.append(", ");
			}
			sbuff.append("{id:").append(locale.getSupportedLocale().getSupportedLocaleId());
			sbuff.append(", name:\"");
			XMLEncodeUtil.appendJSON(sbuff,locale.getSupportedLocale().getSupportedLocaleName());
			sbuff.append("\", display:\"");
			XMLEncodeUtil.appendJSON(sbuff,locale.getName());
			sbuff.append("\"}");
			first = false;

		}
		sbuff.append("],roles:{");
		first = true;
		for (Role role : category.getRoles()) {
			if (!first) {
				sbuff.append(", ");
			}
			sbuff.append(role.getId()).append(":\"");
			XMLEncodeUtil.appendJSON(sbuff,role.getName()); 
			sbuff.append('"');
			first = false;

		}
		sbuff.append("}}");
	}

	
}
