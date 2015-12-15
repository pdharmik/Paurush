package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
	
/**
 * The mapping file: do-supplyrequestdetailactivity-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain.ServiceRequestActivity
 * @see do-servicerequestactivity-mapping.xml
 */
public class SupplyRequestDetailActivityDo extends ServiceRequestActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3362096375500189297L;
	
	private ArrayList<SupplyRequestRecommendedPartsDo> recommendedParts;

	public ArrayList<SupplyRequestRecommendedPartsDo> getRecommendedParts() {
		return recommendedParts;
	}

	public void setRecommendedParts(
			ArrayList<SupplyRequestRecommendedPartsDo> recommendedParts) {
		this.recommendedParts = recommendedParts;
	}
	
}
