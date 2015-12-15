package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ListOfValues;

public class LocalizedSiebelValueResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4753851008057148259L;
	private ListOfValues lovValue;
	public ListOfValues getLovValue() {
		return lovValue;
	}
	public void setLovValue(ListOfValues lovValue) {
		this.lovValue = lovValue;
	}
}
