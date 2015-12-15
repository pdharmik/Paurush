package com.lexmark.result;

import java.io.Serializable;

/**
 * The class contains notification Detail list
 * get notificationDetials.
 * @author Lyn.chen
 *
 */
public class UserNotificationResult implements Serializable {
	private static final long serialVersionUID = -3496664520983804445L;
	
	private Boolean result;

	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	
}
