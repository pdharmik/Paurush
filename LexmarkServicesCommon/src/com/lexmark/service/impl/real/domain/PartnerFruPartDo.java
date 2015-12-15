package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-partnerfrupart-mapping.xml"
 */
public class PartnerFruPartDo extends PartnerFruPartBaseDo {
	
	private ArrayList<PartnerPartLocations> partLocations;

	public void setPartLocations(ArrayList<PartnerPartLocations> partLocations) {
		this.partLocations = partLocations;
	}

	public ArrayList<PartnerPartLocations> getPartLocations() {
		return partLocations;
	}

}
