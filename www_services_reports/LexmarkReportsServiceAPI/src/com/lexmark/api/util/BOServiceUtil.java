package com.lexmark.api.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.crystaldecisions.sdk.exception.SDKException;
import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.occa.infostore.CeScheduleType;
import com.crystaldecisions.sdk.occa.infostore.ICalendarDay;
import com.crystaldecisions.sdk.occa.infostore.ICalendarRunDays;
import com.crystaldecisions.sdk.occa.infostore.IDestination;
import com.crystaldecisions.sdk.occa.infostore.IDestinationPlugin;
import com.crystaldecisions.sdk.occa.infostore.IDestinations;
import com.crystaldecisions.sdk.occa.infostore.IInfoObject;
import com.crystaldecisions.sdk.occa.infostore.IInfoObjects;
import com.crystaldecisions.sdk.occa.infostore.IInfoStore;
import com.crystaldecisions.sdk.occa.infostore.ISchedulingInfo;
import com.crystaldecisions.sdk.plugin.desktop.common.IReportFormatOptions;
import com.crystaldecisions.sdk.plugin.desktop.common.IReportParameter;
import com.crystaldecisions.sdk.plugin.desktop.report.IReport;
import com.crystaldecisions.sdk.plugin.destination.diskunmanaged.IDiskUnmanagedOptions;
import com.lexmark.api.bean.ScheduleReportInfoBean;
import com.lexmark.api.enums.FormatTypeEnum;
import com.lexmark.api.enums.ScheduleEnum;

public class BOServiceUtil {
	private static final Logger logger = Logger.getLogger(BOServiceUtil.class);

	public static ScheduleReportInfoBean runNowReportOnBO(ScheduleReportInfoBean scheduleReportInfoBean) {
		ScheduleReportInfoBean output = new ScheduleReportInfoBean();
		logger.info("in runNowReportOnBO");
		IEnterpriseSession enterpriseSession = null;
		String format = null;
		try {
			enterpriseSession = (IEnterpriseSession) CrystalEnterprise.getSessionMgr().logon(scheduleReportInfoBean.getBoUser(), scheduleReportInfoBean.getBoPassword(),
					scheduleReportInfoBean.getBoCmsName(), scheduleReportInfoBean.getBoAuthType());
			IInfoStore infoStore = (IInfoStore) enterpriseSession.getService("", "InfoStore");
			String infoStore_query = "SELECT TOP 1 * FROM CI_INFOOBJECTS WHERE SI_PROGID = 'CrystalEnterprise.Report' AND SI_INSTANCE=0 AND SI_ID='" + scheduleReportInfoBean.getReportSourceId() + "'";
			IInfoObjects boInfoObjects = (IInfoObjects) infoStore.query(infoStore_query);
			if (boInfoObjects.size() > 0) {
				IInfoObject obj = (IInfoObject) boInfoObjects.get(0);
				IReport oReport = (IReport) obj;

				// Setting the report title.
				logger.info("--> Assigning Title: " + scheduleReportInfoBean.getTitle());
				oReport.setTitle(scheduleReportInfoBean.getTitle());
				IReportFormatOptions reportFormat = oReport.getReportFormatOptions();

				// Setting report format
				logger.info("--> Assigning Type" + scheduleReportInfoBean.getFormatType());
				if (scheduleReportInfoBean.getFormatType().equals(FormatTypeEnum.PDF.getValue())) {
					reportFormat.setFormat(IReportFormatOptions.CeReportFormat.PDF);
					format = "pdf";
				} else if (scheduleReportInfoBean.getFormatType().equals(FormatTypeEnum.EXCEL.getValue())) {
					reportFormat.setFormat(IReportFormatOptions.CeReportFormat.EXCEL_DATA_ONLY);
					format = "Excel";
				}

				// Passing the values to report parameters.
				List paramList = oReport.getReportParameters();
				// IReportParameterSingleValue currentValue = null;

				for (int i = 0; i < paramList.size(); i++) {
					Set<Entry<String, String>> parameterEntrySet = (Set<Entry<String, String>>) scheduleReportInfoBean.getParameterMap().entrySet();
					for (Entry<String, String> entry : parameterEntrySet) {
						if (entry.getKey().equalsIgnoreCase(((IReportParameter) paramList.get(i)).getParameterName())) {
							if (((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("StartDate")
									|| ((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("EndDate")) {
								((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue("Date(" + entry.getValue() + ")");
							} else if (((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("LifeCycle")) {
								((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue(entry.getValue());								
							} else if (((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("LBS Account")) {
								if (entry.getValue().equalsIgnoreCase("F")) {
									((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue("false");
								} else {
									((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue("true");
								}
							} else {
								((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue(entry.getValue());
							}
							break;
						}
					}
				}
				logger.info("Value assigned successfully");

				ISchedulingInfo schedInfo = oReport.getSchedulingInfo();
				logger.info("--> Setting Run now flag");

				// If Run now
				schedInfo.setRightNow(true);
				schedInfo.setType(CeScheduleType.ONCE);

				IDestination destination = schedInfo.getDestination();
				IDestinationPlugin destinationPlugin = (IDestinationPlugin) getDestinationPlugin(scheduleReportInfoBean, infoStore, reportFormat.getFormat());
				destination.setFromPlugin(destinationPlugin);

				logger.info("Checking");
				infoStore.schedule(boInfoObjects);
				infoStore.commit(boInfoObjects);
				logger.info("Recurring schedule created now and getting its details below:");

//				String _query = "SELECT SI_ID FROM CI_INFOOBJECTS WHERE SI_SCHEDULEINFO.SI_SUBMITTER= '" + scheduleReportInfoBean.getBoUser() + "' AND SI_PARENTID="
//						+ scheduleReportInfoBean.getReportSourceId() + " AND SI_RECURRING!=1 ORDER BY SI_CREATIONTIME ASC";
				String _query = "SELECT * FROM CI_INFOOBJECTS WHERE SI_SCHEDULEINFO.SI_SUBMITTER= '"+scheduleReportInfoBean.getBoUser()+"' AND SI_PARENTID="+scheduleReportInfoBean.getReportSourceId()+" AND SI_NAME = '"+scheduleReportInfoBean.getScheduleId()+"' AND SI_SCHEDULEINFO.SI_SCHEDULE_TYPE=0 ORDER BY SI_CREATION_TIME ASC";
				logger.info(_query);
				boInfoObjects = (IInfoObjects) infoStore.query(_query);
				int lastcreated = 0;
				if (boInfoObjects.size() == 0) {
					logger.info("No data found");
					output.setWsStatusCode(400);
					output.setWsStatusMessage("Report Dosed not Exists in BO server");
				} else {
					for (Iterator boCount = boInfoObjects.iterator(); boCount.hasNext();) {
						IInfoObject boObject = (IInfoObject) boCount.next();
						if (scheduleReportInfoBean.getTitle().equals(boObject.getTitle()) && format.equalsIgnoreCase(boObject.getKind().toString())) {
							logger.info("ID of recurrenceSiId " + boObject.getID() + " with title " + boObject.getTitle() + " format " + boObject.getKind().toString());
							lastcreated = boObject.getID();
							scheduleReportInfoBean.setRecurrenceSiId(String.valueOf(lastcreated));
						}else if(scheduleReportInfoBean.equals(boObject.getTitle()) && format == null){
							logger.info("ID of recurrenceSiId " + boObject.getID() + " with title " + boObject.getTitle() + " format " + boObject.getKind().toString());
							lastcreated = boObject.getID();
						}
					}
					output.setRecurrenceSiId(String.valueOf(lastcreated));
					output.setWsStatusCode(200);
					output.setWsStatusMessage("Report Scheduled Successfully");

					output.setNfsfileLocation(scheduleReportInfoBean.getDestination() + "/" + "Services Reports^" + scheduleReportInfoBean.getScheduleId() + "^"
							+ scheduleReportInfoBean.getRecurrenceSiId() + "^NOVALUE^SR_Reports." + scheduleReportInfoBean.getFormatType());
				}
			} else {
				output.setWsStatusCode(400);
				output.setWsStatusMessage("Report Dosed not Exists in BO server");
				logger.info("Report Dosed not Exists in BO server");
			}
		} catch (SDKException e) {
			output.setWsStatusCode(400);
			output.setWsStatusMessage("Exception Occured in BO : " + e.getMessage());
		} finally {
			enterpriseSession.logoff();
		}
		return output;
	}

	private static IDestinationPlugin getDestinationPlugin(ScheduleReportInfoBean scheduleReportInfoBean, IInfoStore infoStore, int formatType) throws SDKException {

		String fileExt = getFileExtensionByType(formatType);
		IDestinationPlugin destinationPlugin = null;
		destinationPlugin = (IDestinationPlugin) infoStore.query("SELECT TOP 1 * " + "FROM CI_SYSTEMOBJECTS " + "WHERE SI_NAME='CrystalEnterprise.DiskUnmanaged'").get(0);

		IDiskUnmanagedOptions diskUnmanagedOptions = (IDiskUnmanagedOptions) destinationPlugin.getScheduleOptions();
		List listDestination = diskUnmanagedOptions.getDestinationFiles();
		String username = "";
		String password = "";
		diskUnmanagedOptions.setUserName(username);
		diskUnmanagedOptions.setPassword(password);
		String location = null;
		if (scheduleReportInfoBean.getRecurrenceSiId() == null) {
			location = scheduleReportInfoBean.getDestination() + "/Services Reports^" + scheduleReportInfoBean.getScheduleId() + "^" + "%SI_ID%" + "^NOVALUE^SR_Reports"+".%EXT%";// + fileExt;
		} else {
			location = scheduleReportInfoBean.getDestination() + "/Services Reports^" + scheduleReportInfoBean.getScheduleId() + "^" + scheduleReportInfoBean.getRecurrenceSiId() + "^" + "%SI_ID%"
					+ "^SR_Reports"+".%EXT%";// + fileExt;
		}
		listDestination.add(location);
		return destinationPlugin;
	}

	private static String getFileExtensionByType(int reportType) {

		String fileExt = "";

		switch (reportType) {
		case IReportFormatOptions.CeReportFormat.CRYSTAL_REPORT:
			fileExt = ".rpt";
			break;
		case IReportFormatOptions.CeReportFormat.EXCEL:
			fileExt = ".xls";
			break;
		case IReportFormatOptions.CeReportFormat.EXCEL_DATA_ONLY:
			fileExt = ".xls";
			break;
		case IReportFormatOptions.CeReportFormat.PDF:
			fileExt = ".pdf";
			break;
		case IReportFormatOptions.CeReportFormat.RTF:
			fileExt = ".rtf";
			break;
		case IReportFormatOptions.CeReportFormat.RTF_EDITABLE:
			fileExt = ".rtf";
			break;
		case IReportFormatOptions.CeReportFormat.TEXT_CHARACTER_SEPARATED:
			fileExt = ".txt";
			break;
		case IReportFormatOptions.CeReportFormat.TEXT_PAGINATED:
			fileExt = ".txt";
			break;
		case IReportFormatOptions.CeReportFormat.TEXT_PLAIN:
			fileExt = ".txt";
			break;
		case IReportFormatOptions.CeReportFormat.TEXT_TAB_SEPARATED:
			fileExt = ".txt";
			break;
		case IReportFormatOptions.CeReportFormat.TEXT_TAB_SEPARATED_TEXT:
			fileExt = ".txt";
			break;
		case IReportFormatOptions.CeReportFormat.USER_DEFINED:
			fileExt = "";
			break;
		case IReportFormatOptions.CeReportFormat.WORD:
			fileExt = ".doc";
			break;
		default:
			fileExt = ".rpt";
			break;
		}
		return fileExt;

	}

	public static ScheduleReportInfoBean scheduleReportOnBO(ScheduleReportInfoBean scheduleReportInfoBean) {
		logger.info("in scheduleReportOnBO");
		// ScheduleReportInfoBean output = new ScheduleReportInfoBean();
		IEnterpriseSession enterpriseSession = null;
		String title = scheduleReportInfoBean.getScheduleId();
		String format = null;
		try {
			enterpriseSession = (IEnterpriseSession) CrystalEnterprise.getSessionMgr().logon(scheduleReportInfoBean.getBoUser(), scheduleReportInfoBean.getBoPassword(),
					scheduleReportInfoBean.getBoCmsName(), scheduleReportInfoBean.getBoAuthType());
			IInfoStore infoStore = (IInfoStore) enterpriseSession.getService("", "InfoStore");
			String infoStore_query = "SELECT TOP 1 * FROM CI_INFOOBJECTS WHERE SI_PROGID = 'CrystalEnterprise.Report' AND SI_INSTANCE=0 AND SI_ID='" + scheduleReportInfoBean.getReportSourceId() + "'";
			IInfoObjects boInfoObjects = (IInfoObjects) infoStore.query(infoStore_query);
			if (boInfoObjects.size() > 0) {
				IInfoObject obj = (IInfoObject) boInfoObjects.get(0);
				IReport oReport = (IReport) obj;

				// Setting the report title.
				logger.info("--> Assigning Title: " + scheduleReportInfoBean.getTitle());
				oReport.setTitle(scheduleReportInfoBean.getTitle());
				IReportFormatOptions reportFormat = oReport.getReportFormatOptions();

				// Setting report format
				logger.info("--> Assigning Type" + scheduleReportInfoBean.getFormatType());
				if (scheduleReportInfoBean.getFormatType().equals(FormatTypeEnum.PDF.getValue())) {
					reportFormat.setFormat(IReportFormatOptions.CeReportFormat.PDF);
					format = "pdf";
				} else if (scheduleReportInfoBean.getFormatType().equals(FormatTypeEnum.EXCEL.getValue())) {
					reportFormat.setFormat(IReportFormatOptions.CeReportFormat.EXCEL_DATA_ONLY);
					format = "Excel";
				}

				// Passing the values to report parameters.
				List paramList = oReport.getReportParameters();
				// IReportParameterSingleValue currentValue = null;

				for (int i = 0; i < paramList.size(); i++) {

					Set<Entry<String, String>> parameterEntrySet = (Set<Entry<String, String>>) scheduleReportInfoBean.getParameterMap().entrySet();
					for (Entry<String, String> entry : parameterEntrySet) {
						if (entry.getKey().equalsIgnoreCase(((IReportParameter) paramList.get(i)).getParameterName())) {
							if (((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("StartDate")
									|| ((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("EndDate")) {
								((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue("Date(" + entry.getValue() + ")");
							} else if (((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("LifeCycle")) {
								((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue(entry.getValue());
							} else if (((IReportParameter) paramList.get(i)).getParameterName().equalsIgnoreCase("LBS Account")) {
								if (entry.getValue().equalsIgnoreCase("F")) {
									((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue("false");
								} else {
									((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue("true");
								}
							} else {
								((IReportParameter) paramList.get(i)).getCurrentValues().addSingleValue().setValue(entry.getValue());
							}
							break;
						}
					}
				}
				logger.info("Value assigned successfully");

				ISchedulingInfo schedInfo = oReport.getSchedulingInfo();

				schedInfo.setRightNow(false);

				if (scheduleReportInfoBean.getIntervalType().equalsIgnoreCase(ScheduleEnum.DAILY.getValue())) {
					schedInfo.setIntervalDays(Integer.parseInt(scheduleReportInfoBean.getIntervalInDays()));
					schedInfo.setType(CeScheduleType.DAILY);
				} else if (scheduleReportInfoBean.getIntervalType().equalsIgnoreCase(ScheduleEnum.WEEKLY.getValue())) {
					schedInfo.setType(CeScheduleType.CALENDAR);
					ICalendarRunDays runDays = schedInfo.getCalendarRunDays();
					int dayOfWeek = Integer.parseInt(scheduleReportInfoBean.getDayOfWeek());
					runDays.add(ICalendarDay.ALL, ICalendarDay.ALL, ICalendarDay.ALL, ICalendarDay.ALL, ICalendarDay.ALL, ICalendarDay.ALL, dayOfWeek, ICalendarDay.ALL);
				} else {
					schedInfo.setIntervalNthDay(Integer.parseInt(scheduleReportInfoBean.getDayOfMonth()));
					schedInfo.setType(CeScheduleType.NTH_DAY);
				}

				if (scheduleReportInfoBean.getStartDate() != null) {
					schedInfo.setBeginDate(scheduleReportInfoBean.getStartDate());
				}
				if (scheduleReportInfoBean.getEndDate() != null) {
					schedInfo.setEndDate(scheduleReportInfoBean.getEndDate());
				}
				infoStore.schedule(boInfoObjects);
				infoStore.commit(boInfoObjects);

				int lastcreated = 0;
				String _query = "SELECT * FROM CI_INFOOBJECTS WHERE SI_SCHEDULEINFO.SI_SUBMITTER= '"+scheduleReportInfoBean.getBoUser()+"' AND SI_PARENTID="+scheduleReportInfoBean.getReportSourceId()+" AND SI_NAME = '"+scheduleReportInfoBean.getScheduleId()+"' AND SI_SCHEDULEINFO.SI_SCHEDULE_TYPE!=0 ORDER BY SI_CREATION_TIME ASC";
				boInfoObjects = (IInfoObjects) infoStore.query(_query);
				logger.info(_query);
				if (boInfoObjects.size() == 0) {
					logger.debug("No data found");
				}
				lastcreated = 0;
				for (Iterator boCount = boInfoObjects.iterator(); boCount.hasNext();) {
					IInfoObject boObject = (IInfoObject) boCount.next();
					if (title.equals(boObject.getTitle()) && format.equalsIgnoreCase(boObject.getKind().toString())) {
						logger.info("ID of recurrenceSiId " + boObject.getID() + " with title " + boObject.getTitle() + " format " + boObject.getKind().toString());
						lastcreated = boObject.getID();
					}else if(title.equals(boObject.getTitle()) && format == null){
						logger.info("ID of recurrenceSiId " + boObject.getID() + " with title " + boObject.getTitle() + " format " + boObject.getKind().toString());
						lastcreated = boObject.getID();
					}
				}
				scheduleReportInfoBean.setRecurrenceSiId(String.valueOf(lastcreated));

				boInfoObjects = (IInfoObjects) infoStore.query("SELECT * FROM CI_INFOOBJECTS WHERE SI_ID=" + lastcreated);
				IInfoObject iInfoObject = (IInfoObject) boInfoObjects.get(0);
				ISchedulingInfo schedInformation = iInfoObject.getSchedulingInfo();

				IDestinationPlugin destinationPlugin = getDestinationPlugin(scheduleReportInfoBean, infoStore, reportFormat.getFormat());
				IDestinations boDestinations = schedInformation.getDestinations();
				IDestination boDestination = boDestinations.add("CrystalEnterprise.DiskUnmanaged");
				boDestination.setFromPlugin(destinationPlugin);
				iInfoObject.properties().setProperty("SI_DESTINATION", destinationPlugin);
				infoStore.commit(boInfoObjects);
				scheduleReportInfoBean.setWsStatusCode(200);
				scheduleReportInfoBean.setWsStatusMessage("Report scheduled");
			}
		} catch (Exception e) {
			scheduleReportInfoBean.setWsStatusCode(400);
			scheduleReportInfoBean.setWsStatusMessage("Exception Occured in BO : " + e.getMessage());
			e.printStackTrace();
		} finally {
			enterpriseSession.logoff();
		}
		return scheduleReportInfoBean;
	}

	public static int getStatus(IInfoObject bo) throws Exception {
		ISchedulingInfo schedinfo = bo.getSchedulingInfo();
		int x = schedinfo.getFlags();
		return x;
	}

	// IExcel Format = (IExcel) boObject;
	//
	// int
	// x=((IReportProcessingInfo)Format).getReportFormatOptions().getFormat();
}
