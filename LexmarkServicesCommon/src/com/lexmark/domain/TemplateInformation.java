package com.lexmark.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class TemplateInformation {
	private List<Field> field;
	private List<ListFieldDetail> listFieldDetail;
	private String fileName;
	private String firstLineCSV;
	@XmlElement
	public void setField(List<Field> field) {
		this.field = field;
	}
	public List<Field> getField() {
		return field;
	}
	
	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	
	
	@XmlElement
	public void setFirstLineCSV(String firstLineCSV) {
		this.firstLineCSV = firstLineCSV;
	}
	public String getFirstLineCSV() {
		return firstLineCSV;
	}
	
	public List<Field> getAllFields(){
		List<Field> fields=new ArrayList<Field>();
		fields.addAll(getField());
		for(ListFieldDetail listfieldDetail:getListFieldDetail()){
			fields.addAll(listfieldDetail.getField());	
		}		
		Collections.sort(fields, new Comparator<Field>(){

			@Override
			public int compare(Field arg0, Field arg1) {
				if(arg0.getColumnNumber()<arg1.getColumnNumber()){
					return -1;
				}else{
					return 1;
				}
					
			}
			
		});
		return fields;
	}
	@XmlElement
	public void setListFieldDetail(List<ListFieldDetail> listFieldDetail) {
		this.listFieldDetail = listFieldDetail;
	}
	public List<ListFieldDetail> getListFieldDetail() {
		return listFieldDetail;
	}
	
}
