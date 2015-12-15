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
	
	public List<String> getLegalNameListExclude() {
		return legalNameListExclude;
	}

	public void setLegalNameListExclude(List<String> legalNameListExclude) {
		this.legalNameListExclude = legalNameListExclude;
	}

	public ReportDefinitionDetailForm() {
	}
	
	public void setReportDefinition(ReportDefinition reportDefinition) {
		this.reportDefinition = reportDefinition;
	}
	public ReportDefinition getReportDefinition() {
		return reportDefinition;
	}
	public void setCategoryList(List<RoleCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public List<RoleCategory> getCategoryList() {
		return categoryList;
	}

//	commented for CI5 multiple customer report
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalName() {
		return legalName;
	}
//	end of comment for CI5 multiple customer report
//	added for CI5 multiple customer report
	public List<String> getLegalNameList() {
		return legalNameList;
	}
	
	public void setLegalNameList(List<String> legalNameList) {
		this.legalNameList = legalNameList;
	}
//	end of addition for CI5 multiple customer report
	public void assemble(List<SupportedLocale> supportedLocales) {
		if (reportDefinition == null) {
			reportDefinition = new ReportDefinition();
		}
		
		List<DefinitionLocale> localeList = new ArrayList<DefinitionLocale>();
		boolean matched;
		
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
