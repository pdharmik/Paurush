package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-partnerfrupartdetail-mapping.xml"
 */
public class PartnerFruPartDetailDo extends PartnerFruPartBaseDo {
	
	private ArrayList<PartnerPartDetailLocations> partLocations;

	public void setPartLocations(ArrayList<PartnerPartDetailLocations> partLocations) {
		this.partLocations = partLocations;
	}

	public ArrayList<PartnerPartDetailLocations> getPartLocations() {
		return partLocations;
	}
}
