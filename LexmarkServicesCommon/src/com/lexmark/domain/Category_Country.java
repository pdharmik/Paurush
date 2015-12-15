package com.lexmark.domain;

public class Category_Country {

	private int category_Id;
	private String country;
	private String category_id_original;
	public String getCategory_id_original() {
		return category_id_original;
	}
	public void setCategory_id_original(String category_id_original) {
		this.category_id_original = category_id_original;
	}
	public int getCategory_Id() {
		return category_Id;
	}
	public void setCategory_Id(int categoryId) {
		category_Id = categoryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
