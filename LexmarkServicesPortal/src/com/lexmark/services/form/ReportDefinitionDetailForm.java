package com.lexmark.services.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.DefinitionLocale;
import com.lexmark.domain.ReportDefinition;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.SupportedLocale;

public class ReportDefinitionDetailForm implements Serializable {

	private static final long serialVersionUID = -8857110752906370496L;
	private ReportDefinition reportDefinition;
	private List<RoleCategory> categoryList;
	private String legalName;		// commented for CI5 multiple customer report
	private List<String> legalNameList;		// added for CI5 multiple customer report
	private List<String> legalNameListExclude;
	/**
	 * 
	 * @return List 
	 */
	public List<String> getLegalNameListExclude() {
		return legalNameListExclude;
	}
/**
 * 
 * @param legalNameListExclude 
 */
	public void setLegalNameListExclude(List<String> legalNameListExclude) {
		this.legalNameListExclude = legalNameListExclude;
	}
/**
 * 
 */
	public ReportDefinitionDetailForm() {
	}
	/**
	 * 
	 * @param reportDefinition 
	 */
	public void setReportDefinition(ReportDefinition reportDefinition) {
		this.reportDefinition = reportDefinition;
	}
	/**
	 * 
	 * @return reportDefinition 
	 */
	public ReportDefinition getReportDefinition() {
		return reportDefinition;
	}
	/**
	 * 
	 * @param categoryList 
	 */
	public void setCategoryList(List<RoleCategory> categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<RoleCategory> getCategoryList() {
		return categoryList;
	}

//	commented for CI5 multiple customer report
	/**
	 * 
	 * @param legalName 
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
/**
 * 
 * @return String 
 */
	public String getLegalName() {
		return legalName;
	}
//	end of comment for CI5 multiple customer report
//	added for CI5 multiple customer report
	/**
	 * 
	 * @return List 
	 */
	public List<String> getLegalNameList() {
		return legalNameList;
	}
	/**
	 * 
	 * @param legalNameList 
	 */
	public void setLegalNameList(List<String> legalNameList) {
		this.legalNameList = legalNameList;
	}
//	end of addition for CI5 multiple customer report
	/**
	 * 
	 * @param supportedLocales 
	 */
	public void assemble(List<SupportedLocale> supportedLocales) {
		if (reportDefinition == null) {
			reportDefinition = new ReportDefinition();
		}
		
		List<DefinitionLocale> localeList = new ArrayList<DefinitionLocale>();
		boolean matched=false;
		for (SupportedLocale locale : supportedLocales) {
			matched = false;
			for (DefinitionLocale definitionLocale : reportDefinition.getLocaleList()) {
				if (definitionLocale.getSupportedLocale().getSupportedLocaleCode().equals(locale.getSupportedLocaleCode())) {
					localeList.add(definitionLocale);
					matched = true;
					break;
				}
			}

			if(!matched) {
				DefinitionLocale emptyDefinitionLocale = new DefinitionLocale();

				emptyDefinitionLocale.setSupportedLocale(locale);
				localeList.add(emptyDefinitionLocale);
			}
		}
		reportDefinition.setLocaleList(localeList);
	}
}
