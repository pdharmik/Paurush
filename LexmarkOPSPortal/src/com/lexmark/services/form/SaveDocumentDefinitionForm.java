package com.lexmark.services.form;


public class SaveDocumentDefinitionForm {
	private Integer definitionId;
	private Integer categoryId;
	private String definitionName;
	private String legalName;
	private boolean limitAcc;
	private Integer[] localeIds;
	private String[] displayValues;
	private String displayMdmId;
	
	
	public String getDisplayMdmId() {
		return displayMdmId;
	}

	public void setDisplayMdmId(String displayMdmId) {
		this.displayMdmId = displayMdmId;
	}


	public Integer getDefinitionId() {
		return definitionId;
	}


	public void setDefinitionId(Integer definitionId) {
		this.definitionId = definitionId;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getDefinitionName() {
		return definitionName;
	}


	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}


	public Integer[] getLocaleIds() {
		return localeIds;
	}


	public void setLocaleIds(Integer[] localeIds) {
		this.localeIds = localeIds;
	}


	public String[] getDisplayValues() {
		return displayValues;
	}


	public void setDisplayValues(String[] displayValues) {
		this.displayValues = displayValues;
	}


	public String getLegalName() {
		return legalName;
	}


	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}


	public boolean isLimitAcc() {
		return limitAcc;
	}


	public void setLimitAcc(boolean limitAcc) {
		this.limitAcc = limitAcc;
	}


	@Override
	public String toString() {
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("id: ").append(definitionId);
		sbuff.append("\nname: ").append(definitionName);
		sbuff.append("\nlegalName: ").append(legalName);
		sbuff.append("\nlocales: ");
		int localeLen = localeIds.length;
		for (int i = 0; i < localeLen; i++) {
				sbuff.append("\n\t").append(localeIds[i]).append(":").append(displayValues[i]);
		}
		sbuff.append("\nroles: ");
		return sbuff.toString();
	}
}
