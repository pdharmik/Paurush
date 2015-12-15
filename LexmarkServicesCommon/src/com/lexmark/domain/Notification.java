package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the business object of Notification.
 * @author Roger.Lin
 *
 */
public class Notification implements Serializable {
	private static final long serialVersionUID = -6758935975830956610L;
	private Integer notificationId;
	private String adminName;
	private String displayURL;
	private Integer displayOrder;
	private Date displayDate;
	private Date removeDate;
	
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Date getDisplayDate() {
		return displayDate;
	}
	public void setDisplayDate(Date displayDate) {
		this.displayDate = displayDate;
	}
	public Date getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}
	public String getDisplayURL() {
		return displayURL;
	}
	public void setDisplayURL(String displayURL) {
		this.displayURL = displayURL;
	}
}
