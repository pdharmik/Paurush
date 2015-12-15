package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.ListOfValues;

public class SiebelLOVListResult implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private List<ListOfValues> lovList;

	public List<ListOfValues> getLovList() {
		return lovList;
	}

	public void setLovList(List<ListOfValues> states) {
		this.lovList = states;
	}

}
