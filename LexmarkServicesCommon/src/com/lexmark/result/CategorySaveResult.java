package com.lexmark.result;

public class CategorySaveResult extends ServiceResult{

	private static final long serialVersionUID = 6168575276638419059L;
	private Boolean result;
	
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult() {
		return result;
	}
}
