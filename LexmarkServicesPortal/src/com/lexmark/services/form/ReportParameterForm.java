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
	/**
	 * 
	 * @return List 
	 */
	public List<ReportParameterListValue> getListValuesList() {
		return listValuesList;
	}
	/**
	 * 
	 * @param listValues 
	 */
	public void setListValues(String listValues){
		this.listValues = listValues;
		if(listValues != null && listValues.trim().length() > 0){
			String[] pairs = listValues.split(",");
			for(String pair : pairs){
				String[] nameAndValue = pair.split("=");
				ReportParameterListValue listValue = new ReportParameterListValue();

				listValue.setValue(nameAndValue[0]);
				if(nameAndValue.length > 1){
					listValue.setLabel(nameAndValue[1]);
				}
				else{
					listValue.setLabel(nameAndValue[0]);
				}
				listValuesList.add(listValue);
				
			}
		}		
	}
/**
 * 
 * @param listValuesList 
 */
	public void setListValuesList(List<ReportParameterListValue> listValuesList) {
		this.listValuesList = listValuesList;
	}
/**
 * 
 * @return String 
 */
	public String getName() {
		return name;
	}
/**
 * 
 * @param name 
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * 
 * @return string 
 */
	public String getValue() {
		return value;
	}
/**
 * 
 * @param value 
 */
	public void setValue(String value) {
		this.value = value;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayName() {
		return displayName;
	}
/**
 * 
 * @param displayName 
 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getDisplayNameSpanish() {
		return displayNameSpanish;
	}
/**
 * 
 * @param displayNameSpanish 
 */
	public void setDisplayNameSpanish(String displayNameSpanish) {
		this.displayNameSpanish = displayNameSpanish;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameGerman() {
		return displayNameGerman;
	}
/**
 * 
 * @param displayNameGerman 
 */
	public void setDisplayNameGerman(String displayNameGerman) {
		this.displayNameGerman = displayNameGerman;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameFrench() {
		return displayNameFrench;
	}
/**
 * 
 * @param displayNameFrench 
 */
	public void setDisplayNameFrench(String displayNameFrench) {
		this.displayNameFrench = displayNameFrench;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameChina() {
		return displayNameChina;
	}
/**
 * 
 * @param displayNameChina 
 */
	public void setDisplayNameChina(String displayNameChina) {
		this.displayNameChina = displayNameChina;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameChinaTw() {
		return displayNameChinaTw;
	}
/**
 * 
 * @param displayNameChinaTw 
 */
	public void setDisplayNameChinaTw(String displayNameChinaTw) {
		this.displayNameChinaTw = displayNameChinaTw;
	}
/**
 * 
 * @return String 
 */ 
	public String getDisplayNamePortugalBrazil() {
		return displayNamePortugalBrazil;
	}
/**
 * 
 * @param displayNamePortugalBrazil 
 */
	public void setDisplayNamePortugalBrazil(String displayNamePortugalBrazil) {
		this.displayNamePortugalBrazil = displayNamePortugalBrazil;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNamePortugal() {
		return displayNamePortugal;
	}
/**
 * 
 * @param displayNamePortugal 
 */
	public void setDisplayNamePortugal(String displayNamePortugal) {
		this.displayNamePortugal = displayNamePortugal;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameItaly() {
		return displayNameItaly;
	}
/**
 * 
 * @param displayNameItaly 
 */
	public void setDisplayNameItaly(String displayNameItaly) {
		this.displayNameItaly = displayNameItaly;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameKorea() {
		return displayNameKorea;
	}
/**
 * 
 * @param displayNameKorea 
 */
	public void setDisplayNameKorea(String displayNameKorea) {
		this.displayNameKorea = displayNameKorea;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameRussia() {
		return displayNameRussia;
	}
/**
 * 
 * @param displayNameRussia 
 */
	public void setDisplayNameRussia(String displayNameRussia) {
		this.displayNameRussia = displayNameRussia;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameJapan() {
		return displayNameJapan;
	}
/**
 * 
 * @param displayNameJapan 
 */
	public void setDisplayNameJapan(String displayNameJapan) {
		this.displayNameJapan = displayNameJapan;
	}
/**
 * 
 * @return String 
 */
	public String getDisplayNameTurkey() {
		return displayNameTurkey;
	}
/**
 * 
 * @param displayNameTurkey 
 */
	public void setDisplayNameTurkey(String displayNameTurkey) {
		this.displayNameTurkey = displayNameTurkey;
	}
/**
 * 
 * @return String 
 */
	public String getDataType() {
		return dataType;
	}
/**
 * 
 * @param dataType 
 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
/**
 * 
 * @param orderNumber 
 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getMaxSize() {
		return maxSize;
	}
/**
 * 
 * @param maxSize 
 */
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
/**
 * 
 * @return Boolean 
 */
	public Boolean getIsRequired() {
		return isRequired;
	}
/**
 * 
 * @param isRequired 
 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
/**
 * 
 * @param reportDefinitionId 
 */
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getReportParameterId() {
		return reportParameterId;
	}
/**
 * 
 * @param reportParameterId 
 */
	public void setReportParameterId(Integer reportParameterId) {
		this.reportParameterId = reportParameterId;
	}
/**
 * 
 * @return String 
 */
	public String getListValues() {
		return listValues;
	}
/**
 * 
 * @param parameter 
 * @param parameterValue 
 */
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
		if(!ReportParameterTypeEnum.DATE.getType().equals(getDataType())){
			setValue(parameterValue);
			}
		else{
			setValue(changeDateFormat(parameterValue));
		}
	}
	
	/**
	 * 
	 * @param dateValue 
	 * @return String 
	 */
	private static String changeDateFormat(String dateValue){
		// change the value form format yyyy,MM,dd to MM/dd/yyyy
		if(dateValue != null && dateValue.trim().length() > 0){
			try{
				Date date =  new SimpleDateFormat("yyyy,MM,dd").parse(dateValue);
				return 
					new SimpleDateFormat("MM/dd/yyyy").format(date);			
			}
			catch(ParseException e){
				e.getMessage();
				return null;
				
			}			
		}
		else{
			return null;
		}
	}
}
