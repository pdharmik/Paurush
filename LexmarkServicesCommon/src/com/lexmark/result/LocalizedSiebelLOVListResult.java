package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ListOfValues;

public class LocalizedSiebelLOVListResult implements Serializable {

	private static final long serialVersionUID = -1512184786514694741L;
	private List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>(0);
	
	public void setLocalizedSiebelLOVList(List<ListOfValues> localizedSiebelLOVList) {
		this.localizedSiebelLOVList = localizedSiebelLOVList;
	}
	public List<ListOfValues> getLocalizedSiebelLOVList() {
		return localizedSiebelLOVList;
	}
}
