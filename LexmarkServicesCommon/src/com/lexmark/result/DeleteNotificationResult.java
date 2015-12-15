package com.lexmark.result;

import java.io.Serializable;

/**
 * The result object after administrator tries to delete a notification.
 * @author Roger.Lin
 *
 */
public class DeleteNotificationResult implements Serializable {

	private static final long serialVersionUID = 6465881275714048984L;
	private Boolean result;
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult() {
		return result;
	}

}
