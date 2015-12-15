package com.lexmark.services.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.lexmark.domain.ReportParameters;
import com.lexmark.enums.WeekDaysEnum;
import com.lexmark.result.ReportDefinitionParameterResult;
import com.lexmark.result.ReportParamsResult;
import com.lexmark.util.DateLocalizationUtil;

public class ScheduleReportForm {
	/**
	 * 
	 */
	public static final String RUN_NOW = "runNow";
	/**
	 * 
	 */
	public static final String SCHEDULE = "schedule";
	/**
	 * 
	 */
	public static final String RUN_NOW_FREQUENCY = "O";
	/**
	 * 
	 */
	public static final String DAILY_FREQUENCY = "D";
	/**
	 * 
	 */
	public static final String MONTHLY_FREQUENCY = "M";
	/**
	 * 
	 */
	public static final String WEEKLY_FREQUENCY = "W";

//	private List<ReportParameterForm> parameters = new ArrayList<ReportParameterForm>();	// commented
	
//	added 
	private List<ReportParameterForm> runNowParameters = new ArrayList<ReportParameterForm>();
	private List<ReportParameterForm> scheduleParameters = new ArrayList<ReportParameterForm>();
//	end of addition
	
	private String runType = RUN_NOW;
	private String scheduleFrequency;
	private Integer runIntervalDaily;
	private Integer runIntervalWeekly;
	private Integer runIntervalMonthly;
	private String effectiveDateDaily;
	private String expirationDateDaily;
	private String effectiveDateWeekly;
	private String expirationDateWeekly;
	private String effectiveDateMonthly;
	private String expirationDateMonthly;
	private Integer specificDayInMonth;
	private Integer specificDayInWeek;
	private Integer reportScheduleId;
	private Integer reportDefinitionId;
	private WeekDaysEnum[] weekDays = WeekDaysEnum.values();
	private Integer timezone;

	/*public List<ReportParameterForm> getParameters() {		// commented
		return parameters;
	}

	public void setParameters(List<ReportParameterForm> parameters) {
		this.parameters = parameters;
	}*/
/**
 * 
 * @return String 
 */
	public String getRunType() {
		return runType;
	}
/**
 * 
 * @param runType 
 */
	public void setRunType(String runType) {
		this.runType = runType;
	}
/**
 * 
 * @return String 
 */
	public String getScheduleFrequency() {
		return scheduleFrequency;
	}
/**
 * 
 * @param scheduleFrequency 
 */
	public void setScheduleFrequency(String scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getRunIntervalDaily() {
		return runIntervalDaily;
	}
/**
 * 
 * @param runIntervalDaily 
 */
	public void setRunIntervalDaily(Integer runIntervalDaily) {
		this.runIntervalDaily = runIntervalDaily;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getRunIntervalWeekly() {
		return runIntervalWeekly;
	}
/**
 * 
 * @param runIntervalWeekly 
 */
	public void setRunIntervalWeekly(Integer runIntervalWeekly) {
		this.runIntervalWeekly = runIntervalWeekly;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getRunIntervalMonthly() {
		return runIntervalMonthly;
	}
/**
 * 
 * @param runIntervalMonthly 
 */
	public void setRunIntervalMonthly(Integer runIntervalMonthly) {
		this.runIntervalMonthly = runIntervalMonthly;
	}
/**
 * 
 * @return String 
 */
	public String getEffectiveDateDaily() {
		return effectiveDateDaily;
	}
/**
 * 
 * @param effectiveDateDaily 
 */
	public void setEffectiveDateDaily(String effectiveDateDaily) {
		this.effectiveDateDaily = effectiveDateDaily;
	}
/**
 * 
 * @return String 
 */
	public String getExpirationDateDaily() {
		return expirationDateDaily;
	}
/**
 * 
 * @param expirationDateDaily 
 */
	public void setExpirationDateDaily(String expirationDateDaily) {
		this.expirationDateDaily = expirationDateDaily;
	}
/**
 * 
 * @return String 
 */
	public String getEffectiveDateWeekly() {
		return effectiveDateWeekly;
	}
/**
 * 
 * @param effectiveDateWeekly 
 */
	public void setEffectiveDateWeekly(String effectiveDateWeekly) {
		this.effectiveDateWeekly = effectiveDateWeekly;
	}
/**
 * 
 * @return String 
 */
	public String getExpirationDateWeekly() {
		return expirationDateWeekly;
	}
/**
 * 
 * @param expirationDateWeekly 
 */
	public void setExpirationDateWeekly(String expirationDateWeekly) {
		this.expirationDateWeekly = expirationDateWeekly;
	}
/**
 * 
 * @return String 
 */
	public String getEffectiveDateMonthly() {
		return effectiveDateMonthly;
	}
/**
 * 
 * @param effectiveDateMonthly 
 */
	public void setEffectiveDateMonthly(String effectiveDateMonthly) {
		this.effectiveDateMonthly = effectiveDateMonthly;
	}
/**
 * 
 * @return String 
 */
	public String getExpirationDateMonthly() {
		return expirationDateMonthly;
	}
/**
 * 
 * @param expirationDateMonthly 
 */
	public void setExpirationDateMonthly(String expirationDateMonthly) {
		this.expirationDateMonthly = expirationDateMonthly;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getSpecificDayInMonth() {
		return specificDayInMonth;
	}
/**
 * 
 * @param specificDayInMonth 
 */
	public void setSpecificDayInMonth(Integer specificDayInMonth) {
		this.specificDayInMonth = specificDayInMonth;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getSpecificDayInWeek() {
		return specificDayInWeek;
	}
/**
 * 
 * @param specificDayInWeek 
 */
	public void setSpecificDayInWeek(Integer specificDayInWeek) {
		this.specificDayInWeek = specificDayInWeek;
	}
/**
 * 
 * @return weekDays 
 */
	public WeekDaysEnum[] getWeekDays() {
		return weekDays;
	}
/**
 * 
 * @param weekDays 
 */
	public void setWeekDays(WeekDaysEnum[] weekDays) {
		this.weekDays = weekDays;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getReportScheduleId() {
		return reportScheduleId;
	}
/**
 * 
 * @param reportScheduleId 
 */
	public void setReportScheduleId(Integer reportScheduleId) {
		this.reportScheduleId = reportScheduleId;
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
 * @param timezone 
 */
	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}
/**
 * 
 * @return Integer 
 */
	public Integer getTimezone() {
		return timezone;
	}
	/**
	 * 
	 * @param runNowParameters 
	 */
	public void setRunNowParameters(List<ReportParameterForm> runNowParameters) {
		this.runNowParameters = runNowParameters;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<ReportParameterForm> getRunNowParameters() {
		return runNowParameters;
	}
/**
 * 	
 * @param scheduleParameters 
 */
	public void setScheduleParameters(
			List<ReportParameterForm> scheduleParameters) {
		this.scheduleParameters = scheduleParameters;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<ReportParameterForm> getScheduleParameters() {
		return scheduleParameters;
	}

//	public void assemble(ReportDefinitionParameterResult result, Locale locale) {	// commented
	/**
	 * 
	 * @param result 
	 * @param locale 
	 */
	public void assemble(ReportParamsResult result, Locale locale) {
		/*for(ReportParameters parameter : result.getParameters()) {		// commented
			ReportParameterForm form = new ReportParameterForm();
			String parameterValue = result.getParametersValue().get(parameter.getName());
			form.assemble(parameter, parameterValue);
			getParameters().add(form);
		}*/
		
//		added
		for(ReportParameters parameter : result.getParameters()) {

			ReportParameterForm runNowForm = new ReportParameterForm();
		    ReportParameterForm scheduleForm = new ReportParameterForm();

			
			String scheduleParameterValue = null;
			String runNowParameterValue = null;   
			
			scheduleParameterValue = result.getScheduleParametersValue().get(parameter.getName());
			scheduleForm.assemble(parameter, scheduleParameterValue);
			getScheduleParameters().add(scheduleForm);
			
			runNowParameterValue = result.getRunNowParametersValue().get(parameter.getName());
			runNowForm.assemble(parameter, runNowParameterValue);
			getRunNowParameters().add(runNowForm);
		
		}
//		end of addition
		
		if(RUN_NOW_FREQUENCY.equals(result.getScheduleFrequency())){
			setRunType(RUN_NOW);
			setReportDefinitionId(result.getReportDefinitionId());
			setReportScheduleId(result.getReportScheduleId());
		}
		else
		//means the frequency is D, W or M
			if(result.getScheduleFrequency() != null && result.getScheduleFrequency().trim().length() > 0){
				setRunType(SCHEDULE);
				setScheduleFrequency(result.getScheduleFrequency());
				if(DAILY_FREQUENCY.equals(getScheduleFrequency())){
					setEffectiveDateDaily(result.getEffectiveDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(populateClientDate(result.getEffectiveDate(), result.getPerferredTimeZone())) : null);
					setExpirationDateDaily(result.getExpirationDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(populateClientDate(result.getExpirationDate(), result.getPerferredTimeZone())) : null);	
					setRunIntervalDaily(result.getRunInterval());
				}
				else
					if(WEEKLY_FREQUENCY.equals(getScheduleFrequency())){
						setEffectiveDateWeekly(result.getEffectiveDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(populateClientDate(result.getEffectiveDate(), result.getPerferredTimeZone())) : null);
						setExpirationDateWeekly(result.getExpirationDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(populateClientDate(result.getExpirationDate(), result.getPerferredTimeZone())) : null);	
						setRunIntervalWeekly(result.getRunInterval());
					}
					else
						if(MONTHLY_FREQUENCY.equals(getScheduleFrequency())){
							setEffectiveDateMonthly(result.getEffectiveDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(populateClientDate(result.getEffectiveDate(), result.getPerferredTimeZone())) : null);
							setExpirationDateMonthly(result.getExpirationDate() != null ? new SimpleDateFormat("MM/dd/yyyy").format(populateClientDate(result.getExpirationDate(), result.getPerferredTimeZone())) : null);	
							setRunIntervalMonthly(result.getRunInterval());
						}
				setReportDefinitionId(result.getReportDefinitionId());
				setReportScheduleId(result.getReportScheduleId());
				setSpecificDayInMonth(result.getSpecificDayInMonth());
				setSpecificDayInWeek(result.getSpecificDayInWeek());
				setTimezone(result.getPerferredTimeZone() != null ? new Integer(result.getPerferredTimeZone()) : null);
			}
		// this report definition has not been scheduled yet
		else{
			setReportDefinitionId(result.getReportDefinitionId());
		}
	}
/**
 * 
 * @param date 
 * @param perferredTimeZone 
 * @return date
 */
	private Date populateClientDate(Date date, String perferredTimeZone) {
		if(perferredTimeZone != null){
			Integer timezone = new Integer(perferredTimeZone);
			
			int millisecondsInHour = 60*60*1000;
			Calendar calendar = Calendar.getInstance();
			int offSet = timezone - calendar.get(Calendar.ZONE_OFFSET) / millisecondsInHour;
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, offSet);
			date.setTime(calendar.getTimeInMillis());
			return date;			
		}
		return date;
	}

}
