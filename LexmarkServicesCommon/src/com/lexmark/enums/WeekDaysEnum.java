package com.lexmark.enums;

public enum WeekDaysEnum {
	
	Monday(2, "Monday"),
	Tuesday(3, "Tuesday"),
	Wednesday(4,"Wednesday"),
	Thursday(5, "Thursday"),
	Friday(6, "Friday"),
	Saturday(7, "Saturday"),
	Sunday(1, "Sunday");
	
	private Integer dayNumber;
	private String dayName;
	public Integer getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	
	WeekDaysEnum(Integer dayNumber, String dayName){
		this.dayNumber = dayNumber;
		this.dayName = dayName;
	}
}
