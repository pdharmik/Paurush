package com.lexmark.domain;

import java.io.Serializable;

public class ReportScheduleParameter implements Serializable{
	private static final long serialVersionUID = 5945768296927354825L;
	private Integer id;
	private String name;
	private String value;
	private ReportSchedule schedule;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ReportSchedule getSchedule() {
		return schedule;
	}
	public void setSchedule(ReportSchedule schedule) {
		this.schedule = schedule;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
