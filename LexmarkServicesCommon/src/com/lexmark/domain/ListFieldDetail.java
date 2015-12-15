package com.lexmark.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class ListFieldDetail {
	
	
	private String beanName;
	
	private List<Field> field;
	
	@XmlAttribute
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanName() {
		return beanName;
	}
	@XmlElement
	public void setField(List<Field> field) {
		this.field = field;
	}
	public List<Field> getField() {
		return field;
	}
}
