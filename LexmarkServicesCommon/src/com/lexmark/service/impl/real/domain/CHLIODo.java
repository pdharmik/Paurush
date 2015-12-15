package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-chliodo-mapping.xml"
 */
public class CHLIODo extends AccountBasedDo {

	private static final long serialVersionUID = -5476577155452748374L;

	private String parentChain;
	private String location;
//	Added by sankha for LEX:AIR00074246 start
	private boolean activeFlag;
	
	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
//  Added by sankha for LEX:AIR00074246 end
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getParentChain() {
		return parentChain;
	}

	public void setParentChain(String parentChain) {
		this.parentChain = parentChain;
	}
}
