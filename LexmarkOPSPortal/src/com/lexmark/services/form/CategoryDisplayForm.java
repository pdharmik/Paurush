package com.lexmark.services.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.domain.SupportedLocale;

public class CategoryDisplayForm implements Serializable {

	private static final long serialVersionUID = 1556263777109378377L;
	private Long submitToken;
	private String categoryListURL;
	private RoleCategory category;
	private List<Role> customerInternalRoleList = new ArrayList<Role>();
	private List<Role> customerExternalRoleList = new ArrayList<Role>();
	private List<Role> partnerInternalRoleList = new ArrayList<Role>();
	private List<Role> partnerExternalRoleList = new ArrayList<Role>();
	
	public void setCategoryListURL(String categoryListURL) {
		this.categoryListURL = categoryListURL;
	}
	public String getCategoryListURL() {
		return categoryListURL;
	}
	public void setCategory(RoleCategory category) {
		this.category = category;
	}
	public RoleCategory getCategory() {
		return category;
	}
	
	public void assemble(List<SupportedLocale> supportedLocales, List<Role> allRoleList) {
		if (category == null) {
			category = new RoleCategory();
		}

		List<Role> customerInternalRoleList = new ArrayList<Role>(0);
		List<Role> customerExternalRoleList = new ArrayList<Role>(0);
		List<Role> partnerInternalRoleList = new ArrayList<Role>(0);
		List<Role> partnerExternalRoleList = new ArrayList<Role>(0);
		
		for (Role role : allRoleList) {
			if (LexmarkConstants.PORTAL_CUSTOMER.equals(role.getTargetPortal()) &&
					LexmarkConstants.ROLE_TYPE_INTERNAL.equals(role.getRoleType())) {
				customerInternalRoleList.add(role);
			} else if (LexmarkConstants.PORTAL_CUSTOMER.equals(role.getTargetPortal()) &&
					LexmarkConstants.ROLE_TYPE_EXTERNAL.equals(role.getRoleType())) {
				customerExternalRoleList.add(role);
			} else if (LexmarkConstants.PORTAL_PARTNER.equals(role.getTargetPortal()) &&
					LexmarkConstants.ROLE_TYPE_INTERNAL.equals(role.getRoleType())) {
				partnerInternalRoleList.add(role);
			} else if (LexmarkConstants.PORTAL_PARTNER.equals(role.getTargetPortal()) &&
					LexmarkConstants.ROLE_TYPE_EXTERNAL.equals(role.getRoleType())) {
				partnerExternalRoleList.add(role);
			}
		}
		
		this.setCustomerExternalRoleList(customerExternalRoleList);
		this.setCustomerInternalRoleList(customerInternalRoleList);
		this.setPartnerExternalRoleList(partnerExternalRoleList);
		this.setPartnerInternalRoleList(partnerInternalRoleList);
		
		boolean matched;
		List<RoleCategoryLocale> categoryLocaleList = new ArrayList<RoleCategoryLocale>(); 
		for (SupportedLocale supportedLocale : supportedLocales) {
			matched = false;
			for (RoleCategoryLocale categoryLocale : category.getLocaleList()) {
				if (supportedLocale.getSupportedLocaleId().equals(categoryLocale.getSupportedLocale().getSupportedLocaleId())) {
					categoryLocaleList.add(categoryLocale);
					matched = true;
					break;
				}
			}
			if (!matched) {
				RoleCategoryLocale emptyRoleCategoryLocale = new RoleCategoryLocale();
				emptyRoleCategoryLocale.setSupportedLocale(supportedLocale);
				categoryLocaleList.add(emptyRoleCategoryLocale);
			}
		}
		category.setLocaleList(categoryLocaleList);
		
//		for (Role role : allRoleList) {
//			for (Role role2 : category.getRoles()) {
//				if (role.getId().equals(role2.getId())) {
//					role
//				}
//			}
//		}
	}
	public void setSubmitToken(Long submitToken) {
		this.submitToken = submitToken;
	}
	public Long getSubmitToken() {
		return submitToken;
	}
	public List<Role> getCustomerInternalRoleList() {
		return customerInternalRoleList;
	}
	public void setCustomerInternalRoleList(List<Role> customerInternalRoleList) {
		this.customerInternalRoleList = customerInternalRoleList;
	}
	public List<Role> getCustomerExternalRoleList() {
		return customerExternalRoleList;
	}
	public void setCustomerExternalRoleList(List<Role> customerExternalRoleList) {
		this.customerExternalRoleList = customerExternalRoleList;
	}
	public List<Role> getPartnerInternalRoleList() {
		return partnerInternalRoleList;
	}
	public void setPartnerInternalRoleList(List<Role> partnerInternalRoleList) {
		this.partnerInternalRoleList = partnerInternalRoleList;
	}
	public List<Role> getPartnerExternalRoleList() {
		return partnerExternalRoleList;
	}
	public void setPartnerExternalRoleList(List<Role> partnerExternalRoleList) {
		this.partnerExternalRoleList = partnerExternalRoleList;
	}
}
