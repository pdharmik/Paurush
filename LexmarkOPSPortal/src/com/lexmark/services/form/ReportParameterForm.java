package com.lexmark.services.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexmark.domain.ReportParameterListValue;
import com.lexmark.domain.ReportParameters;
import com.lexmark.enums.ReportParameterTypeEnum;

public class ReportParameterForm {
	
	private String name;
	private String value;
	private String listValues;
	private String displayName;
	//Parameters Added for CI Defect #11656 --STARTS
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
	//Parameters Added for CI Defect #11656 --ENDS
	private String dataType;
	private Integer orderNumber;
	private Integer maxSize;
	private Boolean isRequired;
	private Integer reportDefinitionId;
	private Integer reportParameterId;
	private List<ReportParameterListValue> listValuesList = new ArrayList<ReportParameterListValue>();
	
	public List<ReportParameterListValue> getListValuesList() {
		return listValuesList;
	}
	
	public void setListValues(String listValues){
		this.listValues = listValues;
		if(listValues != null && listValues.trim().length() > 0){
			String[] pairs = listValues.split(",");
			for(String pair : pairs){
				String[] nameAndValue = pair.split("=");
				ReportParameterListValue listValue = new ReportParameterListValue();
				listValue.setValue(nameAndValue[0]);
				if(nameAndValue.length > 1)
					listValue.setLabel(nameAndValue[1]);
				else
					listValue.setLabel(nameAndValue[0]);
				listValuesList.add(listValue);
			}
		}		
	}

	public void setListValuesList(List<ReportParameterListValue> listValuesList) {
		this.listValuesList = listValuesList;
	}

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public String getDisplayNamePortugal() {
		return displayNamePortugal;
	}

	public void setDisplayNamePortugal(String displayNamePortugal) {
		this.displayNamePortugal = displayNamePortugal;
	}

	public String getDisplayNameItaly() {
		return displayNameItaly;
	}

	public void setDisplayNameItaly(String displayNameItaly) {
		this.displayNameItaly = displayNameItaly;
	}

	public String getDisplayNameKorea() {
		return displayNameKorea;
	}

	public void setDisplayNameKorea(String displayNameKorea) {
		this.displayNameKorea = displayNameKorea;
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

	public String getListValues() {
		return listValues;
	}

	public void assemble(ReportParameters parameter, String parameterValue) {
		setDataType(parameter.getDataType());
		setDisplayName(parameter.getDisplayName());
		//Parameters Added for CI Defect #11656 --STARTS
		setDisplayNameSpanish(parameter.getDisplayNameSpanish());
		setDisplayNameGerman(parameter.getDisplayNameGerman());
		setDisplayNameFrench(parameter.getDisplayNameFrench());
		setDisplayNameChina(parameter.getDisplayNameChina());
		setDisplayNameChinaTw(parameter.getDisplayNameChinaTw());
		setDisplayNamePortugal(parameter.getDisplayNamePortugal());
		setDisplayNamePortugalBrazil(parameter.getDisplayNamePortugalBrazil());
		setDisplayNameItaly(parameter.getDisplayNameItaly());
		setDisplayNameKorea(parameter.getDisplayNameKorea());
		setDisplayNameRussia(parameter.getDisplayNameRussia());
		setDisplayNameJapan(parameter.getDisplayNameJapan());
		setDisplayNameTurkey(parameter.getDisplayNameTurkey());
		//Parameters Added for CI Defect #11656 --ENDS
		setIsRequired(parameter.getIsRequired());
		setListValues(parameter.getListValues());
		setMaxSize(parameter.getMaxSize());
		setName(parameter.getName());
		setOrderNumber(parameter.getOrderNumber());
		setReportDefinitionId(parameter.getReportDefinitionId());
		setReportParameterId(parameter.getReportParameterId());
		if(!ReportParameterTypeEnum.DATE.getType().equals(getDataType()))
			setValue(parameterValue);
		else
			setValue(changeDateFormat(parameterValue));
	}
	
	
	private static String changeDateFormat(String dateValue){
		// change the value form format yyyy,MM,dd to MM/dd/yyyy
		if(dateValue != null && dateValue.trim().length() > 0){
			try{
				Date date =  new SimpleDateFormat("yyyy,MM,dd").parse(dateValue);
				return 
					new SimpleDateFormat("MM/dd/yyyy").format(date);			
			}
			catch(ParseException e){
				return null;
			}			
		}
		else
			return null;
	}
}
