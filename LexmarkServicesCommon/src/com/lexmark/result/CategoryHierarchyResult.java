package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.RoleCategory;

public class CategoryHierarchyResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private List<RoleCategory> categoryHierarchy;
	private List<Integer> partnerTypeCategoryId;
	private List<Integer> countryCategoryId;

	public List<RoleCategory> getCategoryHierarchy() {
		return categoryHierarchy;
	}

	public void setCategoryHierarchy(List<RoleCategory> categoryHierarchy) {
		this.categoryHierarchy = categoryHierarchy;
	}
	
	public List<Integer> getPartnerTypeCategoryId() {
		return partnerTypeCategoryId;
	}

	public void setPartnerTypeCategoryId(List<Integer> partnerTypeCategoryId) {
		this.partnerTypeCategoryId = partnerTypeCategoryId;
	}

	public List<Integer> getCountryCategoryId() {
		return countryCategoryId;
	}

	public void setCountryCategoryId(List<Integer> countryCategoryId) {
		this.countryCategoryId = countryCategoryId;
	}
	
}
