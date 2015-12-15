package com.lexmark.enums;

/**
 * Enum for errorCode1.
 */
public enum ErrorCodeOneEnum {

	PAPER_FEED_TYPE_ONE("PaperFeed1"),
	PAPER_FEED_TYPE_TWO("PaperFeed2"),
	PAPER_FEED_TYPE_THREE("PaperFeed3");
	
	private String value;
	
	ErrorCodeOneEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
