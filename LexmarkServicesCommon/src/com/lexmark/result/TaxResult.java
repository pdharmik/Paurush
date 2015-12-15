package com.lexmark.result;

import java.util.List;
import java.util.Map;

import com.lexmark.domain.Price;

public class TaxResult {
	
	private List<Price> lineInformationList;

	public List<Price> getLineInformationList() {
		return lineInformationList;
	}

	public void setLineInformationList(List<Price> lineInformationList) {
		this.lineInformationList = lineInformationList;
	}	
}
