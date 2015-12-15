package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportParameters implements Serializable {

	private static final long serialVersionUID = 3929406703986176928L;
	private String name;
	private String value;
	private String listValues;
	private String displayName;
	private String dataType;
	private Integer orderNumber;
	private Integer maxSize;
	private Boolean isRequired;
	private Integer reportDefinitionId;
	private Integer reportParameterId;
	private String displayNameSpanish;
	private String displayNameGerman;
	private String displayNameFrench;
	private String displayNameChina;
	private String displayNameChinaTw;
	private String displayNamePortugalBrazil;
	private String displayNamePortugal;
	private String displayNameItaly;
	private String displayNameKorea;
	private String displayNameRussia;
	private String displayNameJapan;
	private String displayNameTurkey;
	
	
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

	public String getListValues() {
		return listValues;
	}

	public void setListValues(String listValues) {
		this.listValues = listValues;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}

	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}

	public Integer getReportParameterId() {
		return reportParameterId;
	}

	public void setReportParameterId(Integer reportParameterId) {
		this.reportParameterId = reportParameterId;
	}

	public String getDisplayNameSpanish() {
		return displayNameSpanish;
	}

	public void setDisplayNameSpanish(String displayNameSpanish) {
		this.displayNameSpanish = displayNameSpanish;
	}

	public String getDisplayNameGerman() {
		return displayNameGerman;
	}

	public void setDisplayNameGerman(String displayNameGerman) {
		this.displayNameGerman = displayNameGerman;
	}

	public String getDisplayNameFrench() {
		return displayNameFrench;
	}

	public void setDisplayNameFrench(String displayNameFrench) {
		this.displayNameFrench = displayNameFrench;
	}

	public String getDisplayNameChina() {
		return displayNameChina;
	}

	public void setDisplayNameChina(String displayNameChina) {
		this.displayNameChina = displayNameChina;
	}

	public String getDisplayNamePortugal() {
		return displayNamePortugal;
	}

	public void setDisplayNamePortugal(String displayNamePortugal) {
		this.displayNamePortugal = displayNamePortugal;
	}

	public String getDisplayNameChinaTw() {
		return displayNameChinaTw;
	}

	public void setDisplayNameChinaTw(String displayNameChinaTw) {
		this.displayNameChinaTw = displayNameChinaTw;
	}

	public String getDisplayNamePortugalBrazil() {
		return displayNamePortugalBrazil;
	}

	public void setDisplayNamePortugalBrazil(String displayNamePortugalBrazil) {
		this.displayNamePortugalBrazil = displayNamePortugalBrazil;
	}

	public String getDisplayNameKorea() {
		return displayNameKorea;
	}

	public void setDisplayNameKorea(String displayNameKorea) {
		this.displayNameKorea = displayNameKorea;
	}

	public String getDisplayNameItaly() {
		return displayNameItaly;
	}

	public void setDisplayNameItaly(String displayNameItaly) {
		this.displayNameItaly = displayNameItaly;
	}

	public String getDisplayNameRussia() {
		return displayNameRussia;
	}

	public void setDisplayNameRussia(String displayNameRussia) {
		this.displayNameRussia = displayNameRussia;
	}

	public String getDisplayNameJapan() {
		return displayNameJapan;
	}

	public void setDisplayNameJapan(String displayNameJapan) {
		this.displayNameJapan = displayNameJapan;
	}

	public String getDisplayNameTurkey() {
		return displayNameTurkey;
	}

	public void setDisplayNameTurkey(String displayNameTurkey) {
		this.displayNameTurkey = displayNameTurkey;
	}
	
	@Override
	public boolean equals(Object obj) {
		ReportParameters reportParameters = (ReportParameters) obj;
		if(this.reportParameterId.compareTo(reportParameters.reportParameterId) == 0){
			 return true;
		}else{
			return false;
		}
	}


	@Override
	public int hashCode() {
		return 1;
	}
}
