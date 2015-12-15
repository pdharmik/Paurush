package com.lexmark.result;

import java.io.Serializable;

public class SiebelServiceSessionResult  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3579469683078539440L;
	
	private String siebelServiceSessionID;
	
	public void setSiebelServiceSessionID(String siebelServiceSessionID){
		this.siebelServiceSessionID = siebelServiceSessionID;
	}
	
	public String getSiebelServiceSessionID(){
		return this.siebelServiceSessionID;
	}
}
