package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * 
 * @author vshynkarenko
 * mapping-file: "do-requesttypefavoriteasset-mapping.xml"
 *
 */
public class RequestTypeFavoriteAssetDo extends RequestTypeDo{

	private static final long serialVersionUID = 1L;
	private ArrayList<RequestTypeAssetPartDo>assetParts;
	public ArrayList<RequestTypeAssetPartDo> getAssetParts() {
		return assetParts;
	}
	public void setAssetParts(ArrayList<RequestTypeAssetPartDo> assetParts) {
		this.assetParts = assetParts;
	}
	
	
	
}
