package com.lexmark.contract;

import com.lexmark.domain.OPSUserFilterPopupSetting;
import com.lexmark.domain.UserFieldsViewSetting;
import com.lexmark.domain.UserFieldsViewSettingOPS;
import com.lexmark.domain.UserFilterSetting;

public class UserFilterSettingContract {
	
	private UserFieldsViewSetting fieldsViewSetting;
	private UserFilterSetting filterSetting;
	private OPSUserFilterPopupSetting opsFilterSetting;
	private UserFieldsViewSettingOPS opsfieldsViewSetting;
	/**
	 * @param fieldsViewSetting the fieldsViewSetting to set
	 */
	public void setFieldsViewSetting(UserFieldsViewSetting fieldsViewSetting) {
		this.fieldsViewSetting = fieldsViewSetting;
	}
	/**
	 * @return the fieldsViewSetting
	 */
	public UserFieldsViewSetting getFieldsViewSetting() {
		return fieldsViewSetting;
	}
	/**
	 * @param filterSetting the filterSetting to set
	 */
	public void setFilterSetting(UserFilterSetting filterSetting) {
		this.filterSetting = filterSetting;
	}
	/**
	 * @return the filterSetting
	 */
	public UserFilterSetting getFilterSetting() {
		return filterSetting;
	}
	/**
	 * @param opsFilterSetting the opsFilterSetting to set
	 */
	public void setOpsFilterSetting(OPSUserFilterPopupSetting opsFilterSetting) {
		this.opsFilterSetting = opsFilterSetting;
	}
	/**
	 * @return the opsFilterSetting
	 */
	public OPSUserFilterPopupSetting getOpsFilterSetting() {
		return opsFilterSetting;
	}
	/**
	 * @param opsfieldsViewSetting the opsfieldsViewSetting to set
	 */
	public void setOpsfieldsViewSetting(UserFieldsViewSettingOPS opsfieldsViewSetting) {
		this.opsfieldsViewSetting = opsfieldsViewSetting;
	}
	/**
	 * @return the opsfieldsViewSetting
	 */
	public UserFieldsViewSettingOPS getOpsfieldsViewSetting() {
		return opsfieldsViewSetting;
	}
	
	
}
