package com.lexmark.reportScheduler.server;

import java.util.Date;


import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.ReportSelectionFlagEnum;
import com.lexmark.reportScheduler.util.DateUtil;
import com.lexmark.service.impl.real.jdbc.ReportScheduleServiceImp;

public class SchedulerParameters {
	private static Logger logger = LogManager.getLogger(SchedulerParameters.class);
	
	private static final String REPORT_RUN_KEY = "ReportRunKey";
	private static final String SELECTION_FLAG = "SelectionFlag";
	private static final String RUN_TIME_LIMIT = "RunTimeLimit";
	private static final String REPORT_ENGINE_THREADS = "ReportEngineThreads";
	private static final String INTERVAL_END_TIME = "IntervalEndTime";
	private static final String INTERVAL_END_DATE = "IntervalEndDate";
	private static final String INTERVAL_START_TIME = "IntervalStartTime";
	private static final String INTERVAL_START_DATE = "IntervalStartDate";
	
	
	/*intervalStartDate=07/23/2014
			intervalStartTime=0000
			intervalEndDate=07/23/2014
			intervalEndTime=2359
			reportEngineThreads=2
			runTimeLimit=5
			selectionFlag=SUBMITNOW
			reportRunKey=null*/
	
	private static final int DEFAULT_THREADDS_NUMBER = 1;
	private static final int DEFAULT_RUMTIME_LIMIT = 5;

	private Date intervalStartDate;
	private Date intervalStartTime;
	private Date intervalEndDate;
	private Date intervalEndTime;
	private int reportEngineThreads = DEFAULT_THREADDS_NUMBER;
	private int runTimeLimit = DEFAULT_RUMTIME_LIMIT;
	private String selectionFlag;
	private String reportRunKey;
	
	public SchedulerParameters(String[] arguments) {
		parse(arguments);
	}
	
	private void parse(String[] arguments) {
	            Options opt = new Options();

	            opt.addOption(INTERVAL_START_DATE, true, "optional, will default to current day (GMT) if not provided");
	            opt.addOption(INTERVAL_START_TIME, true, "optional, will be defaulted to 00:00AM (GMT)of the current day if not provided");
	            opt.addOption(INTERVAL_END_DATE, true, "optional, will default to current day (GMT) if not provided");
	            logger.debug("inside the argument initialization");
	            Option endTimeOption = new Option(INTERVAL_END_TIME, true, "required format example 2300) in GM");
	            endTimeOption.setRequired(true);  //should be false in local
	            opt.addOption(endTimeOption);
	            
	            /*opt.addOption("07/23/2014", true, "optional, will default to current day (GMT) if not provided");
	            opt.addOption("0000", true, "optional, will be defaulted to 00:00AM (GMT)of the current day if not provided");
	            opt.addOption("07/23/2014", true, "optional, will default to current day (GMT) if not provided");
	            Option endTimeOption = new Option("2359", true, "required format example 2300) in GM");
	            endTimeOption.setRequired(true);
	            opt.addOption(endTimeOption);*/

	            opt.addOption(REPORT_ENGINE_THREADS, true, "1 to 99999 - degree of parallelization of ReportEngine, default is 1");
	            opt.addOption(RUN_TIME_LIMIT, true, "in minutes, Engines wraps ups processing after exceeding this limit, default is 5");
	            String selectFlagDescription = 
	            	"UNRUN - only jobs that have not yet been initiated this cycle\n" +                                                  
	            	"    FAILED (used to just attempt rerun of jobs that failed in the selected interval, or are in an incomplete state)\n" +
	            	"    NORMAL (includes both Unrun and Failed - most typical, so it catches and reruns any odd failures that may sporadically occur)\n" +                  
	            	"    FORCE (unusual - used to force a rerun of all jobs, say because downstream system disk failed and backups were not available)\n" +                                                                                                                               
	            	"    SUBMITNOW - separate dedicate stream to initiate immediate ad-hoc reports, including special alerting.  Ignores Interval parameters, if passed.\n";
	            opt.addOption(SELECTION_FLAG, true, selectFlagDescription);
	            opt.addOption(REPORT_RUN_KEY, true, "optional - used to just rerun a particular PortalReport, regardless of values passed in any other parameters");

	   	     try {
	            
	            BasicParser parser = new BasicParser();
	            CommandLine cl = parser.parse(opt, arguments);

	            String startDate = cl.getOptionValue(INTERVAL_START_DATE);
	            //String startDate = "08/06/2014";
	            logger.debug("The start time is-->>>"+startDate);
                intervalStartDate = DateUtil.parseGMTDate(startDate);
                String startTime =	cl.getOptionValue(INTERVAL_START_TIME); 
               // String startTime =	"0000";
                intervalStartTime = DateUtil.parseGMTTime(startTime);
                String endDate = 	cl.getOptionValue(INTERVAL_END_DATE);
              //  String endDate = "07/06/2014";
                intervalEndDate = DateUtil.parseGMTDate(endDate);
                String endTime = 	cl.getOptionValue(INTERVAL_END_TIME);
               // String endTime = "2359";
                intervalEndTime = DateUtil.parseGMTTime(endTime);

                String strThreadNumber = cl.getOptionValue(REPORT_ENGINE_THREADS);
              //  String strThreadNumber = "2";
                try {
                	reportEngineThreads = Integer.parseInt(strThreadNumber);
                }catch(Exception e) {
                	
                }
                String strRunLimit = cl.getOptionValue(RUN_TIME_LIMIT);
               // String strRunLimit = "5";
                try {
                	runTimeLimit = Integer.parseInt(strRunLimit);
                }catch(Exception e) {
                	
                }
                selectionFlag	=	cl.getOptionValue(SELECTION_FLAG);
                logger.debug("The selectionFlag--->>"+selectionFlag);
                if(selectionFlag == null) {
                	selectionFlag = ReportSelectionFlagEnum.NORMAL.getType();
                } else {
                	try {
                		 ReportSelectionFlagEnum.valueOf(selectionFlag);
                	} catch(Exception e) {
                    	selectionFlag = ReportSelectionFlagEnum.NORMAL.getType();
                	}
                }
                //selectionFlag = "SUBMITNOW"; //sbag: hardcoded
                reportRunKey = cl.getOptionValue(REPORT_RUN_KEY);
	        }
	        catch (ParseException e) {
	        	 HelpFormatter f = new HelpFormatter();
	                f.printHelp("OptionsTip", opt);
	             throw new RuntimeException(e);
	        }
	        
	        if(logger.isDebugEnabled()) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("intervalStartDate=" + DateUtil.formatGMTDate(intervalStartDate) + "\n");
	        	sb.append("intervalStartTime=" + DateUtil.formatGMTTime(intervalStartTime) + "\n");
	        	sb.append("intervalEndDate=" + DateUtil.formatGMTDate(intervalEndDate) + "\n");
	        	sb.append("intervalEndTime=" + DateUtil.formatGMTTime(intervalEndTime) + "\n");
	        	sb.append("reportEngineThreads=" + reportEngineThreads + "\n");
	        	sb.append("runTimeLimit=" + runTimeLimit + "\n");
	        	sb.append("selectionFlag=" + selectionFlag + "\n");
	        	sb.append("reportRunKey=" + reportRunKey + "\n");
	        	logger.debug("SchedulerParameters : \n" + sb.toString());
	        }
	}

	public Date getIntervalStartDate() {
		return intervalStartDate;
	}

	public void setIntervalStartDate(Date intervalStartDate) {
		this.intervalStartDate = intervalStartDate;
	}

	public Date getIntervalStartTime() {
		return intervalStartTime;
	}

	public void setIntervalStartTime(Date intervalStartTime) {
		this.intervalStartTime = intervalStartTime;
	}

	public Date getIntervalEndDate() {
		return intervalEndDate;
	}

	public void setIntervalEndDate(Date intervalEndDate) {
		this.intervalEndDate = intervalEndDate;
	}

	public Date getIntervalEndTime() {
		return intervalEndTime;
	}

	public void setIntervalEndTime(Date intervalEndTime) {
		this.intervalEndTime = intervalEndTime;
	}

	public int getReportEngineThreads() {
		return reportEngineThreads;
	}

	public void setReportEngineThreads(int reportEngineThreads) {
		this.reportEngineThreads = reportEngineThreads;
	}

	public int getRunTimeLimit() {
		return runTimeLimit;
	}

	public void setRunTimeLimit(int runTimeLimit) {
		this.runTimeLimit = runTimeLimit;
	}

	public String getSelectionFlag() {
		return selectionFlag;
	}

	public void setSelectionFlag(String selectionFlag) {
		this.selectionFlag = selectionFlag;
	}

	public String getReportRunKey() {
		return reportRunKey;
	}

	public void setReportRunKey(String reportRunKey) {
		this.reportRunKey = reportRunKey;
	}
	
	
}
