package com.lexmark.services.constants;

public enum BeanFieldNames {
	//Refer Bundle.java , OrderPart.java

/** Constant for BeanFieldNames */
	PRICE("price","price"),
	/** Constant for BeanFieldNames */
	QUANTITY("bundleQty","orderQuantity"),
	/** Constant for BeanFieldNames */
	ID("bundleId","partNumber"),
	/** Constant for BeanFieldNames */
	PRODUCTID("bundleProductId","productId"),
	/** Constant for BeanFieldNames */
	DESCRIPTION("description","description");
	/**.
	 * 
	 * This method gives the Bean field names
	 * 
	 * @param bundleFieldName 
	 * @param suppliesFieldName 
	 */
	private BeanFieldNames(String bundleFieldName,String suppliesFieldName) {
		this.suppliesField=suppliesFieldName;
		this.bundleField=bundleFieldName;
	}
	/**.
	 * 
	 * This method renders the change management history page.
	 * 
	 * @param cartType 
	 * @return String 
	 */
	public String getValue(String cartType){
		if("printers".equalsIgnoreCase(cartType)){
			return this.bundleField;
		}
		return this.suppliesField;
	}
	
	
	private String suppliesField;
	private String bundleField;

	

	
}
