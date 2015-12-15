package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.service.impl.real.domain.LBSCHLMapping;

public class LBSCHLListResult implements Serializable{

	/**
	 * @author David Tsamalashvili
	 */
	private static final long serialVersionUID = 6689722415261650266L;
	
	private List<LBSCHLMapping> chlAccount  = new ArrayList<LBSCHLMapping>();

	public List<LBSCHLMapping> getChlAccount() {
		return chlAccount;
	}

	public void setChlAccount(List<LBSCHLMapping> chlAccount) {
		this.chlAccount = chlAccount;
	}
	
}
