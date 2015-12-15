package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ListOfValues;

public class ListOfValuesResult implements Serializable {

	private static final long serialVersionUID = -223368392630216090L;

	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();

	public List<ListOfValues> getLovList() {
		return lovList;
	}

	public void setLovList(List<ListOfValues> states) {
		this.lovList = states;
	}
}
