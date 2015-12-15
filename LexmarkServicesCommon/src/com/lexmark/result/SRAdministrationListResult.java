package com.lexmark.result;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.SiebelLocalization;

public class SRAdministrationListResult extends ServiceResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1306629636159422657L;
	private int totalCount;
	private List<SiebelLocalization> siebelLocalizations  = new ArrayList<SiebelLocalization>(0);
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<SiebelLocalization> getSiebelLocalizations() {
		return siebelLocalizations;
	}
	public void setSiebelLocalizations(List<SiebelLocalization> siebelLocalizations) {
		this.siebelLocalizations = siebelLocalizations;
	}
}
