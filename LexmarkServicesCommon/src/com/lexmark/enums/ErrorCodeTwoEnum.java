package com.lexmark.enums;

/**
 * Enum for errorCode2.
 */
public enum ErrorCodeTwoEnum {
	
	CONTINUOUS_FEED_ONE("PaperFeed1 mock1"),
	CONTINUOUS_FEED_TWO("PaperFeed1 mock2"),
	CONTINUOUS_FEED_THREE("PaperFeed1 mock3"),
	CONTINUOUS_FEED_FOUR("PaperFeed2 mock1"),
	CONTINUOUS_FEED_FIVE("PaperFeed2 mock2"),
	CONTINUOUS_FEED_SIX("PaperFeed2 mock3"),
	CONTINUOUS_FEED_SEVEN("PaperFeed3 mock1"),
	CONTINUOUS_FEED_EIGHT("PaperFeed3 mock2"),
	CONTINUOUS_FEED_NINE("PaperFeed3 mock3");
	
	private String value;
	
	ErrorCodeTwoEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
