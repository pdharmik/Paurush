package com.lexmark.result;

import java.io.Serializable;
import com.lexmark.domain.Part;

public class FRUPartDetailResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3725894800991909581L;
	private Part part;

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}
}
