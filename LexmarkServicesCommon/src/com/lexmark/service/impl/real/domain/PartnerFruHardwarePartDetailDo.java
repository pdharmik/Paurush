package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;


/**
 * mapping file: "do-partnerfruhardwarepartdetail-mapping.xml"
 */

public class PartnerFruHardwarePartDetailDo extends PartnerFruPartBaseDo {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<PartnerHardwarePartDetailLocations> partLocations;

	public ArrayList<PartnerHardwarePartDetailLocations> getPartLocations() {
		return partLocations;
	}

	public void setPartLocations(
			ArrayList<PartnerHardwarePartDetailLocations> partLocations) {
		this.partLocations = partLocations;
	}
	
}
