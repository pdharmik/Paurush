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
	
	/**
	 * 
	 * @return String 
	 */
	public String getDisplayMdmId() {
		return displayMdmId;
	}
/**
 * 
 * @param displayMdmId 
 */
	public void setDisplayMdmId(String displayMdmId) {
		this.displayMdmId = displayMdmId;
	}

/**
 * 
 * @return Integer 
 */
	public Integer getDefinitionId() {
		return definitionId;
	}

/**
 * 
 * @param definitionId 
 */
	public void setDefinitionId(Integer definitionId) {
		this.definitionId = definitionId;
	}
/**
 * 
 * @return Integer 
 */

	public Integer getCategoryId() {
		return categoryId;
	}

/**
 * 
 * @param categoryId 
 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

/**
 * 
 * @return String 
 */
	public String getDefinitionName() {
		return definitionName;
	}

/**
 * 
 * @param definitionName 
 */
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}

/**
 * 
 * @return Integer 
 */
	public Integer[] getLocaleIds() {
		return localeIds;
	}

/**
 * 
 * @param localeIds 
 */
	public void setLocaleIds(Integer[] localeIds) {
		this.localeIds = localeIds;
	}

/**
 * 
 * @return String 
 */
	public String[] getDisplayValues() {
		return displayValues;
	}

/**
 * 
 * @param displayValues 
 */
	public void setDisplayValues(String[] displayValues) {
		this.displayValues = displayValues;
	}

/**
 * 
 * @return String 
 */
	public String getLegalName() {
		return legalName;
	}
/**
 * 
 * @param legalName 
 */

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

/**
 * 
 * @return Boolean 
 */
	public boolean isLimitAcc() {
		return limitAcc;
	}
/**
 * 
 * @param limitAcc 
 */

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
