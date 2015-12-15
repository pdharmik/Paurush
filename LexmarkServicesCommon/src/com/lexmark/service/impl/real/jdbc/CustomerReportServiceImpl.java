package com.lexmark.service.impl.real.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.ReportConstants;
import com.lexmark.contract.CategoryAdministrationDetailContract;
import com.lexmark.contract.CategoryAdministrationListContract;
import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.DeleteReportAdministrationContract;
import com.lexmark.contract.ObieeImpersonateContract;
import com.lexmark.contract.ObieeReportParameterContract;
import com.lexmark.contract.ObieeReportParameterListContract;
import com.lexmark.contract.ReportDefinitionDetailContract;
import com.lexmark.contract.ReportDefinitionDisplayContract;
import com.lexmark.contract.ReportHierarchyContract;
import com.lexmark.contract.ReportInstanceListContract;
import com.lexmark.contract.ReportListContract;
import com.lexmark.contract.SaveCategoryAdministrationDetailContract;
import com.lexmark.contract.SaveReportDefinitionDetailContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.contract.SwapCategoryOrderNumberContract;
import com.lexmark.domain.CompanyMdmAssociation;
import com.lexmark.domain.CompanyTreeBean;
import com.lexmark.domain.DefinitionLocale;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.ObieeReportParameter;
import com.lexmark.domain.Report;
import com.lexmark.domain.ReportCustomers;
import com.lexmark.domain.ReportDefinition;
import com.lexmark.domain.ReportDeleteStatus;
import com.lexmark.domain.ReportParameterListValue;
import com.lexmark.domain.ReportParameters;
import com.lexmark.domain.ReportSchedule;
import com.lexmark.domain.ReportScheduleParameter;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.result.CategoryAdministrationDetailResult;
import com.lexmark.result.CategoryAdministrationListResult;
import com.lexmark.result.CategoryDeleteResult;
import com.lexmark.result.DeleteReportAdministrationResult;
import com.lexmark.result.ObieeReportDefinitionResult;
import com.lexmark.result.ObieeReportParameterListResult;
import com.lexmark.result.ReportAdministrationListResult;
import com.lexmark.result.ReportDefinitionDetailResult;
import com.lexmark.result.ReportDefinitionDisplayResult;
import com.lexmark.result.ReportDefinitionNameListResult;
import com.lexmark.result.ReportDefinitionParameterResult;
import com.lexmark.result.ReportHierarchyResult;
import com.lexmark.result.ReportInstanceListResult;
import com.lexmark.result.ReportListResult;
import com.lexmark.result.ReportListRow;
import com.lexmark.result.ReportParamsResult;
import com.lexmark.result.SaveCategoryAdministrationDetailResult;
import com.lexmark.result.SaveReportDefinitionDetailResult;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.result.SwapCategoryOrderNumberResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.StringUtil;

public class CustomerReportServiceImpl implements CustomerReportService {
	private static int REPORT_FILE_TYPES_NUMBER = 2;
	private static int MAX_SCHEDULED_REPORT_NUMBER = 10;
	private static int MAX_MANUALLY_REPORT_NUMBER = 13;
	
	public String NULL = null ; 		// added by rituparna
//	added by nelson
	private static final String RUN_NOW_REPORT = "O";
	private static final String SCHEDULED_REPORT = "S";
	private static final String companyGlobalNo = "COMPANY_MDM_GLOBAL_ULT_NO";
	private static final String companyGlobalName = "COMPANY_MDM_GLOBAL_ULT_NAME";
	private static final String companyDomesticNo = "COMPANY_MDM_DOMESTIC_ULT_NO";
	private static final String companyDomesticName = "COMPANY_MDM_DOMESTIC_ULT_NAME";
	private static final String companyLegalNo = "COMPANY_MDM_ID";
	private static final String companyLegalName = "COMPANY_LEGAL_NAME";
	private static final String companyDetail_table_Name = "COMPANY_DETAIL";	
	private static final String companyDAId = "COMPANY_DA_ID";
	private static final String companyStatus = "COMPANY_STATUS";
	private static final String companyType = "COMPANY_TYPE";
	private static final String companyRole = "COMPANY_ROLE";
	
	

	
//	end of addition by nelson
	
	private static Logger logger = LogManager.getLogger(CustomerReportServiceImpl.class);
	private static final String SQL_SEARCH_ALL_REPORT_DEFINITION = 
		"select rd.report_definition_id, rd.name, c.name categoryName, rd.report_type, rd.is_schedulable " +
		"from report_definition rd, category c " +
		"where rd.category_id = c.category_id " +
		"and rd.definition_type = 'REPORT' " +
		"and rd.is_deleted = 'F' " +
		"and rd.report_type <> 'SD' " +
		"order by c.name";
	private static final String SQL_SEARCH_ALL_CATEGORY =
		"select ctg.category_id, ctg.name, ctg.order_num, r.name roleName from ( " +
		"select c.category_id, c.name, c.order_num, ra.role_id from category c,report_acl ra " +
		"where c.category_id = ra.category_id(+) " +
		"and c.is_deleted = 'F' " +
		"and c.type = :type) ctg, role r " +
		"where ctg.role_id = r.role_id(+) order by ctg.order_num";
	
	
	private static final String SQL_SEARCH_REPORT_DEF_BY_ROLE =	"select distinct c.category_id, nvl(cl.name, c.name) categoryName, rd.report_definition_id, nvl(dl.name, rd.name), c.order_num order_num from " +
		"supported_locale sl left join category_locale cl on (sl.supported_locale_id = cl.supported_locale_id and sl.supported_locale_code = :localeCode) right join " +
		"category c on (c.category_id = cl.category_id), " +
		"supported_locale sl2 left join definition_locale dl on (sl2.supported_locale_id = dl.supported_locale_id and sl2.supported_locale_code = :localeCode) right join " +
		"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
		"role r, report_acl ra " +
		"where rd.category_id = c.category_id and r.role_id = ra.role_id and ra.category_id = c.category_id " +
		"and UPPER(r.name) in ({0}) " +
		"and c.type= :categoryType " +
		"and rd.DEFINITION_TYPE = :definitionType " +
		"and rd.REPORT_TYPE in ({1}) " +
		"and rd.view_type in ({2}) " +
		"and c.is_deleted = :isDeleted and rd.is_deleted = :isDeleted " +
//		commented by nelson for CI5 multiple customers report
		/*"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
		"and (rd.mdm_id = :mdmId or rd.mdm_id is null) " +*/
		" and rd.report_definition_id in (select rc.report_definition_id FROM report_customers rc where (rc.mdm_level = :mdmLevel or rc.mdm_level is null) " +
		"and (rc.restrict_report = :restrict_report  or rc.restrict_report is null ) and (rc.mdm_id = :mdmId or rc.mdm_id is null) "+
		"or (rc.restrict_report = :restrict_reportExclude and (rc.mdm_id != :mdmId)) and rc.report_definition_id not in " +
		"(select distinct rc.report_definition_id  FROM report_customers rc where rc.restrict_report = :restrict_reportExclude and rc.mdm_id = :mdmId )) " +		 //Modified as part of BRD #14-07-03	
		"order by order_num";
	
	/* Added for BRD14 */
	private static final String SQL_SEARCH_REPORT_DEF_BY_ROLE_FOR_MU =	"select distinct c.category_id, nvl(cl.name, c.name) categoryName, rd.report_definition_id, nvl(dl.name, rd.name), c.order_num order_num from " +
			"supported_locale sl left join category_locale cl on (sl.supported_locale_id = cl.supported_locale_id and sl.supported_locale_code = :localeCode) right join " +
			"category c on (c.category_id = cl.category_id), " +
			"supported_locale sl2 left join definition_locale dl on (sl2.supported_locale_id = dl.supported_locale_id and sl2.supported_locale_code = :localeCode) right join " +
			"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
			"role r, report_acl ra " +
			"where rd.category_id = c.category_id and r.role_id = ra.role_id and ra.category_id = c.category_id " +
			"and UPPER(r.name) in ({0}) " +
			"and c.type= :categoryType " +
			"and rd.DEFINITION_TYPE = :definitionType " +
			"and rd.REPORT_TYPE in ({1}) " +
			"and rd.view_type in ({2}) " +
			"and c.is_deleted = :isDeleted and rd.is_deleted = :isDeleted " +
//			commented by nelson for CI5 multiple customers report
			/*"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
			"and (rd.mdm_id = :mdmId or rd.mdm_id is null) " +*/
			" and rd.report_definition_id in (select rc.report_definition_id FROM report_customers rc where (rc.mdm_level = :mdmLevel or rc.mdm_level is null) and " +
			"(rc.restrict_report = :restrict_report  or rc.restrict_report is null ) and (rc.mdm_id in( :mdmId ) or rc.mdm_id is null) " +
			"or (rc.restrict_report = :restrict_reportExclude and (rc.mdm_id != :mdmId)) and rc.report_definition_id not in " +
			"(select distinct rc.report_definition_id  FROM report_customers rc where rc.restrict_report = :restrict_reportExclude and rc.mdm_id = :mdmId )) " +			
			"order by order_num"; //Modified as part of BRD #14-07-03
	/* end */
	
	private static final String SQL_GET_REPORT_BY_ID_AND_ROLE = "select lpd.R_OBJECT_ID, lpd.OBJECT_NAME, lpd.A_WEBC_URL, lpd.FILE_CONTENT_DATE AS R_CREATION_DATE " +
		"from report_definition rd, report_acl ra, role r, LXK_PORTAL_DOC_VIEW lpd " +
		"where lpd.definition_id = rd.report_definition_id and rd.category_id = ra.category_id and " +
		"ra.role_id = r.role_id " +
		"and lpd.R_OBJECT_ID = :id " +
		"and UPPER(r.name) in ({0})";
	
	/*private static final String SQL_TOOLTIP = " ' | JobRunLogID: ' || jr.JOB_RUNLOG_ID || chr(13) || " + 
    	"' | mdmID: ' || jr.MDM_ID || chr(13) || " +
    	"' | mdmLevel: ' || jr.MDM_LEVEL || chr(13) || " +
        "' | Dcmnt DateTime: ' || to_char(jr.run_date_time,'MM/DD/YYYY HH:MI.SSpm') || ' GMT' ";
	*/	
	/*
	 *  Get Top10 are using LXK_PORTAL_DOC_VIEW.file_content_date as report_creation_date, which is GMT-5 timezone     
	 */
	/*private static final String SQL_GET_TOP10_REPORTS = 
		"select report_definition_name, report_definition_id, report_creation_date, report_id, report_type, file_data_link, file_path, category_name, definition_type, tooltip_text, job_runlog_id from( " +
				"select report_definition_name, report_definition_id, report_creation_date,  " +
				"report_id, report_type, file_data_link, file_path, category_name, definition_type, tooltip_text, job_runlog_id " +
				"from " +
				"( " +
				"select " + 
				"distinct nvl(dl.name, rd.name) report_definition_name, " +
				"rd.report_definition_id, " +
				"lpd.file_content_date report_creation_date, lpd.R_OBJECT_ID report_id, " +
				"lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url file_path, c.name as category_name, rd.report_type as definition_type, lpd.tooltip_text, '''' as job_runlog_id " +
				"from " +
				"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
				"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
				"role r, category c, report_acl ra, LXK_PORTAL_DOC_VIEW lpd " +
				"where " +
				"not exists (select r_object_id from report_delete_status rds where lpd.r_object_id = rds.r_object_id and rds.is_deleted = :isReportDeleted) " +
				"and rd.category_id = c.category_id and r.role_id = ra.role_id and ra.category_id = c.category_id " +
				"and UPPER(r.name) in ({0}) " +
				"and rd.view_type in ({1}) " +
				"and rd.report_type = :manuType " +
				"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
				"and (rd.mdm_id = :mdmId or rd.mdm_id is null) " +
				"and lpd.definition_id = rd.report_definition_id " +
				"and rd.is_deleted = :isDeleted " +
				"union " +
				"select " +
				"nvl(dl.name, rd.name) report_definition_name, " +
				"rd.report_definition_id, " +
				"lpd.file_content_date  report_creation_date, lpd.R_OBJECT_ID report_id, " +
				"lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url file_path, c.name as category_name,  rd.report_type as definition_type, nvl(lpd.tooltip_text, JOBRUN_TOOLTIP_TEXT(jr.job_runlog_id)) as tooltip_text, to_char(lpd.job_runlog_id) as job_runlog_id " +
				"from " +
				"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
				"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
				"category c, " +
				"LXK_PORTAL_DOC_VIEW lpd,  report_schedule rs, job_runlog jr " +
				"where " +
				"rd.view_type in ({1}) and " +
				"not exists (select r_object_id from report_delete_status rds where rds.r_object_id = to_char(lpd.job_runlog_id) and rds.is_deleted = :isReportDeleted) " +
				"and rd.report_type= :bussType " +
				"and lpd.r_object_id = jr.file_object_id and  rd.report_definition_id = rs.report_definition_id and rs.report_schedule_id = jr.report_schedule_id " +
				"and rd.category_id = c.category_id " + 
				"and rs.usernumber = :userNumber " +
				"and rd.is_deleted = :isDeleted " +
				") " +
				"order by report_creation_date desc" +
				") " +
				"where rownum > 0 and rownum < 21 ";*/			// commented by rituparna
	
	//	Query Changed for CI BRD 13-10-10--START

	private static final String SQL_GET_TOP10_REPORTS = "select report_definition_name, report_definition_id, report_creation_date, report_id, report_type, file_data_link, file_path, category_name, definition_type, tooltip_text, job_runlog_id ,status_code,file_name from( " +
			"select report_definition_name, report_definition_id, report_creation_date,  " +
			"report_id, report_type, file_data_link, file_path, category_name, definition_type, tooltip_text, job_runlog_id,status_code,file_name " +
			"from " +
			"( " +
			"select " + 
			"distinct nvl(dl.name, rd.name) report_definition_name, " +
			"rd.report_definition_id, " +
			"lpd.file_content_date report_creation_date, lpd.R_OBJECT_ID report_id, " +
			"lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url file_path, c.name as category_name, rd.report_type as definition_type, lpd.tooltip_text, '''' as job_runlog_id ,''PUBLISHOK'' as status_code,lpd.file_name  " +
			"from " +
			"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
			"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
			"role r, category c, report_acl ra, LXK_PORTAL_DOC_VIEW lpd " +
			"where " +
			"not exists (select r_object_id from report_delete_status rds where lpd.r_object_id = rds.r_object_id and rds.is_deleted = :isReportDeleted) " +
			"and rd.category_id = c.category_id and r.role_id = ra.role_id and ra.category_id = c.category_id " +
			"and UPPER(r.name) in ({0}) " +
			"and rd.view_type in ({1}) " +
			"and rd.report_type = :manuType " +
			" and rd.report_definition_id in (select rc.report_definition_id FROM report_customers rc where (rc.mdm_level = :mdmLevel or rc.mdm_level is null) and (rc.mdm_id = :mdmId or rc.mdm_id is null)) " +
			"and lpd.definition_id = rd.report_definition_id " +
			"and rd.is_deleted = :isDeleted " +
			"union " +
			"select " +
			"nvl(dl.name, rd.name) report_definition_name, " +
			"rd.report_definition_id, " +
			"lpd.file_content_date  report_creation_date, lpd.R_OBJECT_ID report_id, " +
			"lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url file_path, c.name as category_name,  rd.report_type as definition_type, nvl(lpd.tooltip_text, JOBRUN_TOOLTIP_TEXT(jr.job_runlog_id)) as tooltip_text, to_char(lpd.job_runlog_id) as job_runlog_id ,jr.status_code as status_code,lpd.file_name " +
			"from " +
			"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
			"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
			"category c, " +
			"LXK_PORTAL_DOC_VIEW lpd,  report_schedule rs, job_runlog jr " +
			"where " +
			"rd.view_type in ({1}) and " +
			"not exists (select r_object_id from report_delete_status rds where rds.r_object_id = to_char(lpd.job_runlog_id) and rds.is_deleted = :isReportDeleted) " +
			"and rd.report_type= :bussType " +
			"and lpd.r_object_id = jr.file_object_id and  rd.report_definition_id = rs.report_definition_id and rs.report_schedule_id = jr.report_schedule_id " +
			"and rd.category_id = c.category_id " + 
			"and rs.usernumber = :userNumber " +
			"and rd.is_deleted = :isDeleted " +
			") " +
			"order by report_creation_date desc" +
			") " +
			"where rownum > 0 and rownum < 21"+
			"UNION ALL "+
			 " (select  distinct nvl(dl.name, rd.name) report_definition_name,rd.report_definition_id,   rs.SCHEDULE_EFFECTIVE_DATE  report_creation_date,NULL as report_id,NULL as report_type,NULL as file_data_link,NULL as file_path,NULL as category_name,NULL as definition_type,NULL as tooltip_text ,NULL as job_runlog_id ,''SELECTED'' as status_code,lpd.file_name from report_schedule rs,"+
			    " (select report_schedule_id, job_type, max(created_dt) as GOAL_FINISHED_ON "+"  from job_runlog "+
				    "where job_type = ''SUBMITNOW''  group by report_schedule_id, job_type )  runlog,LXK_PORTAL_DOC_VIEW  lpd,"+
					 " supported_locale sl, definition_locale dl, report_definition rd" +  
					 " where rs.report_definition_id = rd.report_definition_id" +  
					 " and rs.usernumber = :userNumber" +  
					 " and dl.supported_locale_id = sl.supported_locale_id(+)" +
					 " and rd.report_definition_id = dl.report_definition_id(+)" + 
					 " and rs.report_schedule_id = runlog.report_schedule_id(+)" +
					 " and sl.supported_locale_code = :locationCode" +
					 " and rs.RUN_FREQUENCY = ''O''" +
					 " and rd.is_deleted = ''F''" + 
				   " and (runlog.GOAL_FINISHED_ON IS NULL OR runlog.GOAL_FINISHED_ON < rs.SCHEDULE_EFFECTIVE_DATE) )" +
				   " UNION ALL "+
				    "select  name  report_definition_name,report_definition_id,SCHEDULE_EFFECTIVE_DATE report_creation_date,NULL as report_id,NULL as report_type,NULL as file_data_link,NULL as file_path,NULL as category_name,NULL as definition_type,NULL as tooltip_text ,NULL as job_runlog_id, ''SELECTED'' as status_code,lpd.file_name from "+
				"LXK_PORTAL_DOC_VIEW  lpd,(select nvl(dl.name, rd.name) name , rs.* from report_schedule rs, " + "( select report_schedule_id, job_type, max(run_date_time) as GOAL_FINISHED_ON from job_runlog " + " where job_type = ''{2}'' and  ( status_code =''WAITFORPUB'' or status_code = ''PUBLISHOK'') group by report_schedule_id, job_type )  runlog, " + 
				 "report_definition rd,supported_locale sl ,definition_locale dl  "+  
				 "where rs.report_definition_id = rd.report_definition_id "+ 
				 "and rs.usernumber = :userNumber " +
				 " and dl.supported_locale_id = sl.supported_locale_id(+) "+
				 "and rd.report_definition_id = dl.report_definition_id(+) "+
				 "and rs.report_schedule_id = runlog.report_schedule_id(+) "+ 
				 "and sl.supported_locale_code = :locationCode"+
				 " and ((( rs.SCHEDULE_EFFECTIVE_DATE IS NULL OR  rs.SCHEDULE_EFFECTIVE_DATE <= sysdate ) "+
				 " and ( rs.SCHEDULE_EXPIRATION_DATE IS NULL OR  rs.SCHEDULE_EXPIRATION_DATE >= sysdate )) "+
				 " or rs.schedule_effective_date >= sysdate)" +
				 "and rs.RUN_FREQUENCY <> ''O'' and rd.is_deleted = ''F'') order by report_creation_date desc ";
	//	Query Changed for CI BRD 13-10-10--ENDS

//	end of addition by rituparna
	
//	added by nelsons --Edited for LEX:AIR00060472   
	private static final String SQL_GET_TOP10_REPORTS_EMPLOYEE = "select * from ((  select report_definition_name, report_definition_id, report_creation_date, report_id, report_type, file_data_link, file_path, category_name, definition_type, tooltip_text, job_runlog_id ,status_code from( " +
	"select report_definition_name, report_definition_id, report_creation_date,  " +
	"report_id, report_type, file_data_link, file_path, category_name, definition_type, tooltip_text, job_runlog_id,status_code " +
	"from " +
	"( " +
	"select " + 
	"distinct nvl(dl.name, rd.name) report_definition_name, " +
	"rd.report_definition_id, " +
	"lpd.file_content_date report_creation_date, lpd.R_OBJECT_ID report_id, " +
	"lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url file_path, c.name as category_name, rd.report_type as definition_type, lpd.tooltip_text, '''' as job_runlog_id ,''PUBLISHOK'' as status_code  " +
	"from " +
	"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
	"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
	"role r, category c, report_acl ra, LXK_PORTAL_DOC_VIEW lpd,report_schedule rs " +
	"where " +
	"not exists (select r_object_id from report_delete_status rds where lpd.r_object_id = rds.r_object_id and rds.is_deleted = :isReportDeleted) " +
	"and rd.category_id = c.category_id and r.role_id = ra.role_id and ra.category_id = c.category_id " +
	"and UPPER(r.name) in ({0}) " +
	"and rd.view_type in ({1}) " +
	"and rd.report_type = :manuType " +
//	commented by nelson for CI5 multiple customer reports
/*	"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
	"and (rd.mdm_id = :mdmId or rd.mdm_id is null) " +*/
	" and rd.report_definition_id in (select rc.report_definition_id FROM report_customers rc where (rc.mdm_level = :mdmLevel or rc.mdm_level is null) and (rc.mdm_id = :mdmId or rc.mdm_id is null)) " +		// added by nelson for CI5 multiple customers report
	//added for LEX:AIR00060472
	"and rs.mdm_id = :employeemdmId " +
	//************
	//added for LEX:AIR00060472
	"and rs.mdm_id = :employeemdmId " +
	//************
	"and lpd.definition_id = rd.report_definition_id " +
	"and rd.is_deleted = :isDeleted " +
	"union " +
	"select " +
	"nvl(dl.name, rd.name) report_definition_name, " +
	"rd.report_definition_id, " +
	"lpd.file_content_date  report_creation_date, lpd.R_OBJECT_ID report_id, " +
	"lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url file_path, c.name as category_name,  rd.report_type as definition_type, nvl(lpd.tooltip_text, JOBRUN_TOOLTIP_TEXT(jr.job_runlog_id)) as tooltip_text, to_char(lpd.job_runlog_id) as job_runlog_id ,jr.status_code as status_code " +
	"from " +
	"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
	"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
	"category c, " +
	"LXK_PORTAL_DOC_VIEW lpd,  report_schedule rs, job_runlog jr " +
	"where " +
	"rd.view_type in ({1}) and " +
	"not exists (select r_object_id from report_delete_status rds where rds.r_object_id = to_char(lpd.job_runlog_id) and rds.is_deleted = :isReportDeleted) " +
	"and rd.report_type= :bussType " +
	"and lpd.r_object_id = jr.file_object_id and  rd.report_definition_id = rs.report_definition_id and rs.report_schedule_id = jr.report_schedule_id " +
	"and rd.category_id = c.category_id " + 
	"and rs.usernumber = :userNumber " +
	//added for LEX:AIR00060472
	"and rs.mdm_id = :employeemdmId " +
	//************
	"and rd.is_deleted = :isDeleted " +
	") " +
	"order by report_creation_date desc" +
	") " +
	"where rownum > 0 and rownum < 21  ) "+
	"UNION ALL "+
	 " (select  distinct nvl(dl.name, rd.name) report_definition_name,rd.report_definition_id,   rs.SCHEDULE_EFFECTIVE_DATE  report_creation_date,NULL as report_id,NULL as report_type,NULL as file_data_link,NULL as file_path,NULL as category_name,NULL as definition_type,NULL as tooltip_text ,NULL as job_runlog_id ,''SELECTED'' as status_code from report_schedule rs,"+
	    " (select report_schedule_id, job_type, max(created_dt) as GOAL_FINISHED_ON "+"  from job_runlog "+
		    "where job_type = ''SUBMITNOW''  group by report_schedule_id, job_type )  runlog,"+
			 " supported_locale sl, definition_locale dl, report_definition rd" +  
			 " where rs.report_definition_id = rd.report_definition_id" +  
			 " and rs.usernumber = :userNumber" +  
			 " and rs.mdm_id = :employeemdmId" +
			 " and dl.supported_locale_id = sl.supported_locale_id(+)" +
			 " and rd.report_definition_id = dl.report_definition_id(+)" + 
			 " and rs.report_schedule_id = runlog.report_schedule_id(+)" +
			 " and sl.supported_locale_code = :locationCode" +
			 " and rs.RUN_FREQUENCY = ''O''" +
			 " and rd.is_deleted = ''F''" + 
		   " and (runlog.GOAL_FINISHED_ON IS NULL OR runlog.GOAL_FINISHED_ON < rs.SCHEDULE_EFFECTIVE_DATE) )" +
		   " UNION ALL "+
		    "select  name  report_definition_name,report_definition_id,SCHEDULE_EFFECTIVE_DATE report_creation_date,NULL as report_id,NULL as report_type,NULL as file_data_link,NULL as file_path,NULL as category_name,NULL as definition_type,NULL as tooltip_text ,NULL as job_runlog_id, ''SELECTED'' as status_code from "+
		"(select nvl(dl.name, rd.name) name , rs.* from report_schedule rs, " + "( select report_schedule_id, job_type, max(run_date_time) as GOAL_FINISHED_ON from job_runlog " + " where job_type = ''{2}'' and  ( status_code =''WAITFORPUB'' or status_code = ''PUBLISHOK'') group by report_schedule_id, job_type )  runlog, " + 
		 "report_definition rd,supported_locale sl ,definition_locale dl  "+  
		 "where rs.report_definition_id = rd.report_definition_id "+ 
		 "and rs.usernumber = :userNumber " +
		 " and rs.mdm_id = :employeemdmId" + 
		 " and dl.supported_locale_id = sl.supported_locale_id(+) "+
		 "and rd.report_definition_id = dl.report_definition_id(+) "+
		 "and rs.report_schedule_id = runlog.report_schedule_id(+) "+ 
		 "and sl.supported_locale_code = :locationCode"+
		 " and ((( rs.SCHEDULE_EFFECTIVE_DATE IS NULL OR  rs.SCHEDULE_EFFECTIVE_DATE <= sysdate ) "+
		 " and ( rs.SCHEDULE_EXPIRATION_DATE IS NULL OR  rs.SCHEDULE_EXPIRATION_DATE >= sysdate )) "+
		 " or rs.schedule_effective_date >= sysdate)" +
		 "and rs.RUN_FREQUENCY <> ''O'' and rd.is_deleted = ''F'')) order by report_creation_date desc ";
//	end of addition by nelsons
	
	private static final String SQL_GET_DEFINITION_TYPE_BY_ID = 
		"select rd.report_definition_id, rd.report_type, nvl(dl.name, rd.name), nvl(dl.description, rd.description) from " + 
		"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :localeCode) right join " +
		"report_definition rd on (rd.report_definition_id = dl.report_definition_id) " +
		"where rd.report_definition_id = :definitionId";
	/*
	 *  Get Manual are using LXK_PORTAL_DOC_VIEW.file_content_date as report_creation_date, which is GMT-5 timezone     
	 */
	
	private static final String SQL_GET_MANU_UPLOADED_REPORTS_BY_DEFINITION_ID = 
		"select report_definition_name, report_definition_id, report_creation_date, report_id, report_type, file_data_link, a_webc_url, category_name,tooltip_text " +
		"from ( " +
		"select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, lpd.file_content_date report_creation_date, lpd.R_OBJECT_ID report_id,lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url,c.name as category_name, lpd.tooltip_text " +
		"from " +
		"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
		"report_definition rd on (rd.report_definition_id = dl.report_definition_id), LXK_PORTAL_DOC_VIEW lpd, " +
		"category c " +
		"where " +
		"rd.category_id = c.category_id " +
		"and not exists (select r_object_id from report_delete_status rds where lpd.r_object_id = rds.r_object_id and rds.is_deleted = :isReportDeleted) " +
		"and lpd.definition_id = rd.report_definition_id " +
//		commented by nelson for CI5 multiple customers report
		/*"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
		"and (rd.mdm_id = :mdmId or rd.mdm_id is null) " +
		"and rd.report_definition_id = :definitionId " +*/
		" and rd.report_definition_id = (select rc.report_definition_id FROM report_customers rc where (rc.mdm_id = :mdmId or rc.mdm_id is null) and (rc.mdm_level = :mdmLevel or rc.mdm_level is null) and rc.report_definition_id = :definitionId) " + // added by nelson for multiple customers report
		"order by report_creation_date desc " +
		") where rownum > 0 and rownum <= " + MAX_MANUALLY_REPORT_NUMBER;
	/*
	 * This is added for CI 13-10-10
	 * 
	 * This query along with all data get the uploaded filr name from the view LXK_PORTAL_DOC_VIEW_MU
	 * This LXK_PORTAL_DOC_VIEW_MU also new added for CI 13-10-10 
	 */
	private static final String SQL_GET_MANU_UPLOADED_REPORTS_BY_DEFINITION_ID_MU = 
		"select report_definition_name, report_definition_id, report_creation_date, report_id, report_type, file_data_link, a_webc_url, category_name,tooltip_text, file_name " +
		"from ( " +
		"select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, lpd.file_content_date report_creation_date, lpd.R_OBJECT_ID report_id,lpd.i_full_format report_type, lpd.file_data_link, lpd.a_webc_url,c.name as category_name, lpd.tooltip_text, lpd.file_name " +
		"from " +
		"supported_locale sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join " +
		"report_definition rd on (rd.report_definition_id = dl.report_definition_id), LXK_PORTAL_DOC_VIEW_MU lpd, " +
		"category c " +
		"where " +
		"rd.category_id = c.category_id " +
		"and not exists (select r_object_id from report_delete_status rds where lpd.r_object_id = rds.r_object_id and rds.is_deleted = :isReportDeleted) " +
		"and lpd.definition_id = rd.report_definition_id " +
//		commented by nelson for CI5 multiple customers report
		/*"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
		"and (rd.mdm_id = :mdmId or rd.mdm_id is null) " +
		"and rd.report_definition_id = :definitionId " +*/
		" and rd.report_definition_id = (select rc.report_definition_id FROM report_customers rc where (rc.mdm_id = :mdmId or rc.mdm_id is null) and (rc.mdm_level = :mdmLevel or rc.mdm_level is null) and rc.report_definition_id = :definitionId) " + // added by nelson for multiple customers report
		"order by report_creation_date desc " +
		") where rownum > 0 and rownum <= " + MAX_MANUALLY_REPORT_NUMBER;
	
//	added by rituparna
	private static final String SQL_GET_SCHEDULED_REPORTS_BY_DEFINITION_ID =  " select * from(select nvl(dl.name, rd.name) report_definition_name,rd.report_definition_id,rs.SCHEDULE_EFFECTIVE_DATE report_creation_date,null as report_id,null as report_type,null as file_data_link,'SELECTED' as status_code,null as file_path,null as job_runlog_id,null as category_name,null as tooltip_text from report_schedule rs,"+
	 " (select report_schedule_id, job_type, max(created_dt) as GOAL_FINISHED_ON from job_runlog"+
	 " where job_type = 'SUBMITNOW' group by report_schedule_id, job_type )  runlog, "+
	 " supported_locale sl, definition_locale dl, report_definition rd" +  
	 " where rs.report_definition_id = rd.report_definition_id" +  
	 " and  rd.report_definition_id = :definitionId" +
	 " and rs.usernumber = :userNumber" +  
	 " and dl.supported_locale_id = sl.supported_locale_id(+)" +
	 " and rd.report_definition_id = dl.report_definition_id(+)" + 
	 " and rs.report_schedule_id = runlog.report_schedule_id(+)" +
	 " and sl.supported_locale_code = :locationCode" +
	 " and rs.RUN_FREQUENCY = 'O'" +
	 " and rd.is_deleted = 'F'" + 
	   " and (runlog.GOAL_FINISHED_ON IS NULL OR runlog.GOAL_FINISHED_ON < rs.SCHEDULE_EFFECTIVE_DATE ) "+
	 "UNION ALL "+
	 "select * from ( "+
	"select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, jr.created_dt report_creation_date, jr.file_object_id report_id, "+
	"jr.file_type report_type, to_char(jr.file_data_link) "+
	"as file_data_link, jr.status_code, lpd.a_webc_url file_path, to_char(jr.job_runlog_id) "+
	"as job_runlog_id,c.name as category_name,"+
	"nvl(lpd.tooltip_text, JOBRUN_TOOLTIP_TEXT(jr.job_runlog_id)) as tooltip_text  "+
	"from supported_locale sl left join definition_locale dl "+
	"on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode)"+ 
	" right join report_definition rd  "+
	"on (rd.report_definition_id = dl.report_definition_id), category c ,report_schedule rs, job_runlog jr "+ 
	"left join LXK_PORTAL_DOC_VIEW lpd  "+
	"on  jr.file_object_id = lpd.r_object_id   "+
	"where  "+
	"rd.category_id = c.category_id "+  
	"and rs.usernumber = :userNumber " +
	" and rd.report_definition_id = rs.report_definition_id and rs.report_schedule_id = jr.report_schedule_id and  rd.report_definition_id = :definitionId " +   
	" and not exists (select r_object_id from report_delete_status rds where rds.r_object_id = to_char(jr.job_runlog_id) and rds.is_deleted = :isReportDeleted) " +  
	" order by jr.created_dt desc "+
	")where rownum > 0 and rownum <=" + MAX_SCHEDULED_REPORT_NUMBER *2 + " UNION ALL "+
   "select  name  report_definition_name,report_definition_id,SCHEDULE_EFFECTIVE_DATE report_creation_date,null as report_id,null as report_type,null as file_data_link,'SELECTED' as status_code,null as file_path,null as job_runlog_id,null as category_name,null as tooltip_text from "+
   	"(select nvl(dl.name, rd.name) name , rs.* from report_schedule rs, " + "(select report_schedule_id, job_type, max(run_date_time) as GOAL_FINISHED_ON from job_runlog " + " where job_type = '{2}' and  ( status_code = 'WAITFORPUB' or status_code = 'PUBLISHOK') group by report_schedule_id, job_type )  runlog, " + 
" report_definition rd,supported_locale sl ,definition_locale dl"+
" where rs.report_definition_id = rd.report_definition_id  "+
" and  rd.report_definition_id = :definitionId " +
" and rs.usernumber = :userNumber " +
" and dl.supported_locale_id = sl.supported_locale_id(+) "+
" and rd.report_definition_id = dl.report_definition_id(+) "+
" and rs.report_schedule_id = runlog.report_schedule_id(+) "+ 
" and sl.supported_locale_code= :locationCode"+
" and ((( rs.SCHEDULE_EFFECTIVE_DATE IS NULL OR  rs.SCHEDULE_EFFECTIVE_DATE <= sysdate ) "+ 
" and ( rs.SCHEDULE_EXPIRATION_DATE IS NULL OR  rs.SCHEDULE_EXPIRATION_DATE >= sysdate )) "+
" or rs.schedule_effective_date >= sysdate) " +
" and rs.RUN_FREQUENCY <> 'O' and rd.is_deleted = 'F'))order by report_creation_date desc"  ;
//  end of addition by rituparna
	 
//	added by nelsons
	private static final String SQL_GET_SCHEDULED_REPORTS_BY_DEFINITION_ID_EMPLOYEE =   " select * from(select nvl(dl.name, rd.name) report_definition_name,rd.report_definition_id,rs.SCHEDULE_EFFECTIVE_DATE report_creation_date,null as report_id,null as report_type,null as file_data_link,'SELECTED' as status_code,null as file_path,null as job_runlog_id,null as category_name,null as tooltip_text from report_schedule rs,"+
	 " (select report_schedule_id, job_type, max(created_dt) as GOAL_FINISHED_ON from job_runlog"+
	 " where job_type = 'SUBMITNOW' group by report_schedule_id, job_type )  runlog, "+
	 " supported_locale sl, definition_locale dl, report_definition rd" +  
	 " where rs.report_definition_id = rd.report_definition_id" +  
	 " and  rd.report_definition_id = :definitionId" +
	 " and rs.usernumber = :userNumber" +  
	 " and rs.mdm_id = :employeemdmId" +//changed to employeemdmid from mdmid for LEX:AIR00060472
	 " and dl.supported_locale_id = sl.supported_locale_id(+)" +
	 " and rd.report_definition_id = dl.report_definition_id(+)" + 
	 " and rs.report_schedule_id = runlog.report_schedule_id(+)" +
	 " and sl.supported_locale_code = :locationCode" +
	 " and rs.RUN_FREQUENCY = 'O'" +
	 " and rd.is_deleted = 'F'" + 
	   " and (runlog.GOAL_FINISHED_ON IS NULL OR runlog.GOAL_FINISHED_ON < rs.SCHEDULE_EFFECTIVE_DATE ) "+
	 "UNION ALL "+
	 "select * from ( "+
	"select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, jr.created_dt report_creation_date, jr.file_object_id report_id, "+
	"jr.file_type report_type, to_char(jr.file_data_link) "+
	"as file_data_link, jr.status_code, lpd.a_webc_url file_path, to_char(jr.job_runlog_id) "+
	"as job_runlog_id,c.name as category_name,"+
	"nvl(lpd.tooltip_text, JOBRUN_TOOLTIP_TEXT(jr.job_runlog_id)) as tooltip_text  "+
	"from supported_locale sl left join definition_locale dl "+
	"on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode)"+ 
	" right join report_definition rd  "+
	"on (rd.report_definition_id = dl.report_definition_id), category c ,report_schedule rs, job_runlog jr "+ 
	"left join LXK_PORTAL_DOC_VIEW lpd  "+
	"on  jr.file_object_id = lpd.r_object_id   "+
	"where  "+
	"rd.category_id = c.category_id "+  
	"and rs.usernumber = :userNumber " +
	" and rs.mdm_id = :employeemdmId" +//changed to employeemdmid from mdmid added for LEX:AIR00060472
	" and rd.report_definition_id = rs.report_definition_id and rs.report_schedule_id = jr.report_schedule_id and  rd.report_definition_id = :definitionId " +   
	" and not exists (select r_object_id from report_delete_status rds where rds.r_object_id = to_char(jr.job_runlog_id) and rds.is_deleted = :isReportDeleted) " +  
	" order by jr.created_dt desc "+
	")where rownum > 0 and rownum <=" + MAX_SCHEDULED_REPORT_NUMBER *2 + 
			" UNION ALL "+
   "select  name  report_definition_name,report_definition_id,SCHEDULE_EFFECTIVE_DATE report_creation_date,null as report_id,null as report_type,null as file_data_link,'SELECTED' as status_code,null as file_path,null as job_runlog_id,null as category_name,null as tooltip_text from "+
   	"(select nvl(dl.name, rd.name) name , rs.* from report_schedule rs, " + "(select report_schedule_id, job_type, max(run_date_time) as GOAL_FINISHED_ON from job_runlog " + " where job_type = '{2}' and  ( status_code = 'WAITFORPUB' or status_code = 'PUBLISHOK') group by report_schedule_id, job_type )  runlog, " + 
" report_definition rd,supported_locale sl ,definition_locale dl"+
" where rs.report_definition_id = rd.report_definition_id  "+
" and  rd.report_definition_id = :definitionId " +
" and rs.usernumber = :userNumber " +
" and rs.mdm_id = :employeemdmId" + //changed to employeemdmid from mdmid for LEX:AIR00060472
" and dl.supported_locale_id = sl.supported_locale_id(+) "+
" and rd.report_definition_id = dl.report_definition_id(+) "+
" and rs.report_schedule_id = runlog.report_schedule_id(+) "+ 
" and sl.supported_locale_code= :locationCode"+
" and ((( rs.SCHEDULE_EFFECTIVE_DATE IS NULL OR  rs.SCHEDULE_EFFECTIVE_DATE <= sysdate )  "+ 
" and ( rs.SCHEDULE_EXPIRATION_DATE IS NULL OR  rs.SCHEDULE_EXPIRATION_DATE >= sysdate )) "+
" or rs.schedule_effective_date >= sysdate) " +
" and rs.RUN_FREQUENCY <> 'O' and rd.is_deleted = 'F'))order by report_creation_date desc"; 
//	end of addition by nelsons
	//Query Modified for CI-7 Defect #11656
	private static final String	SQL_GET_PARAMETERS_BY_DEFINITION_ID = 
		"select rp.report_parameter_id, rp.name, rp.display_name, " +
		"rp.value, rp.data_type,rp.default_values, rp.max_size, rp.is_required, rp.report_definition_id, " +
		"rs.report_schedule_id, rs.run_frequency, rs.run_interval, rs.day_of_week, " +
		"rs.day_of_month, rs.schedule_effective_date, rs.schedule_expiration_date, " +
		"rsp.name rspName, rsp.value rspValue, rsp.id rspId, rs.preferred_timezone, " +
		"rp.display_name_spanish,rp.DISPLAY_NAME_GERMAN,rp.DISPLAY_NAME_FRENCH,rp.DISPLAY_NAME_CHINA,rp.DISPLAY_NAME_CHINA_TW,"+
		"rp.DISPLAY_NAME_PT_PT,rp.DISPLAY_NAME_PT_BR,rp.DISPLAY_NAME_ITALY,rp.DISPLAY_NAME_KOREA,"+
		"rp.DISPLAY_NAME_RUSSIA,rp.DISPLAY_NAME_JAPAN,rp.DISPLAY_NAME_TURKEY "+
		"from report_parameter rp left join " +
		"( select *  from report_schedule where usernumber = :usernumber and mdm_id = :mdmId) rs " + 	// added by nelson for employee report        
		"on rp.report_definition_id = rs.report_definition_id " +
		"left join report_schedule_parameters rsp on (rs.report_schedule_id = rsp.report_schedule_id and rp.name = rsp.name) " +
		"where rp.report_definition_id = :definitionId " +
		" order by rp.order_number";
	
	/*	private static final String	SQL_GET_PARAMETERS_BY_DEFINITION_ID = 
		"select rp.report_parameter_id, rp.name, rp.display_name, " +
		"rp.value, rp.data_type,rp.default_values, rp.max_size, rp.is_required, rp.report_definition_id, " +
		"rs.report_schedule_id, rs.run_frequency, rs.run_interval, rs.day_of_week, " +
		"rs.day_of_month, rs.schedule_effective_date, rs.schedule_expiration_date, " +
		"rsp.name rspName, rsp.value rspValue, rsp.id rspId, rs.preferred_timezone, " +
		"rp.display_name_spanish,rp.DISPLAY_NAME_GERMAN,rp.DISPLAY_NAME_FRENCH,rp.DISPLAY_NAME_CHINA,rp.DISPLAY_NAME_CHINA_TW,"+
		"rp.DISPLAY_NAME_PT_PT,rp.DISPLAY_NAME_PT_BR,rp.DISPLAY_NAME_ITALY,rp.DISPLAY_NAME_KOREA,"+
		"rp.DISPLAY_NAME_RUSSIA,rp.DISPLAY_NAME_JAPAN,rp.DISPLAY_NAME_TURKEY "+
		"from report_parameter rp left join " +
		"( select *  from report_schedule where usernumber = :usernumber and mdm_id = :mdmId) rs " + 	// added by nelson for employee report        
		"on rp.report_definition_id = rs.report_definition_id " +
		"left join report_schedule_parameters rsp on (rs.report_schedule_id = rsp.report_schedule_id and rp.name = rsp.name) " +
		"where rp.report_definition_id = :definitionId " +
		" order by rp.order_number";*/
	
	private static final String SQL_GET_REPORT_SCHEDULE_BY_DEFINITION_ID = 
		"select rs.report_schedule_id, rs.run_frequency, rs.run_interval, rs.day_of_week, " + 
		"rs.day_of_month, rs.schedule_effective_date, rs.schedule_expiration_date " +
		"from report_schedule rs " +
		"where rs.report_definition_id = :definitionId " +
		" and rs.MDM_ID = :mdmId" + 			// added by nelson for employee report
        " and rs.usernumber = :usernumber "; 
	//Added for CI BRD 13-10-27
	private static final String	SQL_GET_PARAMETERS_ToUpdate_BY_DEFINITION_ID_ = 
			"select rp.report_parameter_id, rp.name, rp.display_name,rp.display_name_spanish,"+
	        "rp.DISPLAY_NAME_GERMAN,rp.DISPLAY_NAME_FRENCH,rp.DISPLAY_NAME_CHINA,rp.DISPLAY_NAME_CHINA_TW,"+
			"rp.DISPLAY_NAME_PT_PT,rp.DISPLAY_NAME_PT_BR,rp.DISPLAY_NAME_ITALY,rp.DISPLAY_NAME_KOREA,"+	
	        "rp.DISPLAY_NAME_RUSSIA,rp.DISPLAY_NAME_JAPAN,rp.DISPLAY_NAME_TURKEY,"+
	        "rp.value, rp.data_type," +
			"rp.default_values, rp.max_size, rp.is_required " +			
			"from report_parameter rp " +
			"where rp.report_definition_id = :definitionId ";
	
	// SQL to search report definitions' id associated to provided category_id
	private static final String SQL_SEARCH_REPORT_BY_CATEGORY_ID = 
		"select report_definition_id from report_definition where category_id = :categoryId and definition_type = :definitionType and is_deleted = 'F'";
	//Started addition for OBIEE report
	private static final String SQL_GET_OBIEE_REPORT_DEFINITION_BY_DEFINITION_ID = 
		"select rd.REPORT_SOURCE_ID, rd.IS_MDM from REPORT_DEFINITION rd where rd.report_definition_id = :definitionId";
	private static final String SQL_GET_OBIEE_REPORT_PARAMETERS_BY_DEFINITION_ID = 
		"select rp.data_type, rp.name,rp.display_name, rp.is_required, orp.param_value, orp.param_value2, rp.report_parameter_id, rp.default_values , rp.max_size " +
		"from report_parameter rp LEFT JOIN OBIEE_REPORT_PARAMETER orp ON " + 
		"(orp.report_parameter_id = rp.report_parameter_id and orp.contact_id = :contactId) " + 
		"where rp.report_definition_id = :definitionId order by rp.name";
	
	private static final String SQL_GET_REPORT_BY_DOCUMENTID_AND_ROLE="select rj.Document_ID, rj.File_name,rj.created_date "
		+" from report_definition rd, report_acl ra, role r, report_job rj "
		+" where rj.report_definition_id = rd.report_definition_id and rd.category_id = ra.category_id and" 
		+" ra.role_id = r.role_id "
		+" and rj.Document_ID = :id "
		+" and UPPER(r.name) in ({0})";
	private static final String SQL_SEARCH_REPORTID_FOR_MU = "select rc.report_definition_id FROM report_customers rc where rc.mdm_id = :mdmId and rc.mdm_level = :mdmLevel and rc.restrict_report = :restrict_reportExclude";
	//Completed addition for OBIEE report
		
	public ReportAdministrationListResult retrieveReportAdministrationList()
			throws Exception {
		ReportAdministrationListResult result = new ReportAdministrationListResult();
		List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
				
		try {
			Query query = HibernateUtil.getSession().createSQLQuery(SQL_SEARCH_ALL_REPORT_DEFINITION);
			List list = query.list();
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++){
					ReportDefinition reportDefinition = new ReportDefinition();
					Object[] row = (Object[]) list.get(i);
					reportDefinition.setId(Integer.valueOf(row[0].toString()));
					reportDefinition.setName((String)row[1]);
					reportDefinition.setReportType((String)row[3]);
					reportDefinition.setIsSchedulable(row[4].toString().equalsIgnoreCase("T"));
					RoleCategory category = new RoleCategory();
					category.setName((String)row[2]);
					reportDefinition.setRoleCategory(category);
					reportDefinitionList.add(reportDefinition);
				}
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		
		result.setReportDefinitionList(reportDefinitionList);
		return result;
	}
	
	private String getViewTypeStub(List<String> viewTypes) {
		if(viewTypes.size()==0) return "''";
		StringBuilder viewTypeString = new StringBuilder();
		for (String viewType : viewTypes){
			viewTypeString.append("'" + viewType + "'");
			viewTypeString.append(",");
		}
		if(viewTypeString.length() > 0)
			viewTypeString.delete(viewTypeString.length() - 1, viewTypeString.length());
		return viewTypeString.toString();
	}
	public ReportHierarchyResult retrieveReportHierarchy(
			ReportHierarchyContract contract) {
		
		ReportHierarchyResult result = new ReportHierarchyResult();
		
		StringBuilder rolesString = new StringBuilder();
		for (String roleName : contract.getUserRoleNames()){
			rolesString.append("'" + roleName.toUpperCase() + "'");
			rolesString.append(",");
		}
		if(rolesString.length() > 0)
			rolesString.delete(rolesString.length() - 1, rolesString.length());
		/* Changes done for (BRD 14-02-14) */
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		String supportedReportType = "'BO','OA'"; 
		String muReportType = "'MU'";
		MessageFormat mf = new MessageFormat(SQL_SEARCH_REPORT_DEF_BY_ROLE);
		MessageFormat mfMu = new MessageFormat(SQL_SEARCH_REPORT_DEF_BY_ROLE_FOR_MU);
		String sql = mf.format(new Object[]{rolesString.toString(), supportedReportType, 
				getViewTypeStub(contract.getViewTypes())});		
		String sql2 = mfMu.format(new Object[]{rolesString.toString(), muReportType, 
				getViewTypeStub(contract.getViewTypes())});	
		//added by sbag
		MessageFormat mfExcludeMO =  new MessageFormat(SQL_SEARCH_REPORTID_FOR_MU);
		String sql3 = mfExcludeMO.format(new Object[]{rolesString.toString(), muReportType, 
				getViewTypeStub(contract.getViewTypes())});;
		//ends here
		
		try {
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("localeCode", language);
			query.setParameter("categoryType", "R");
			query.setParameter("definitionType", "REPORT");
			query.setParameter("isDeleted", "F");
			query.setParameter("mdmLevel", contract.getMdmLevel());
			query.setParameter("mdmId", contract.getMdmID());
			query.setParameter("restrict_report", "T");
			query.setParameter("restrict_reportExclude", "F");
			
			List list1 = query.list();
			Query query2 = HibernateUtil.getSession().createSQLQuery(sql2);
			query2.setParameter("localeCode", language);
			query2.setParameter("categoryType", "R");
			query2.setParameter("definitionType", "REPORT");
			query2.setParameter("isDeleted", "F");
			query2.setParameter("mdmLevel", contract.getUserMdmLevel());
			query2.setParameter("mdmId", contract.getUserMdmId());
			query2.setParameter("restrict_report", "T");
			query2.setParameter("restrict_reportExclude", "F");
			
			List listMu = query2.list();
						
			List list = new ArrayList(list1);
			list.addAll(listMu);
			
			//Added for BRD #14-07-03 to exclude the Manual Reports
			Query query3 = HibernateUtil.getSession().createSQLQuery(sql3);
			query3.setParameter("mdmLevel", "Global");
			query3.setParameter("mdmId", contract.getMdmID());
			query3.setParameter("restrict_reportExclude", "F");
			List listMoExclude = query3.list();
			
			if(list != null && list.size() > 0){
			ListIterator myIt=list.listIterator(); 
			for(Iterator<Object[]> iter = list.iterator(); iter.hasNext();)
			{
				Object[] toBeDeleted = ((Object[])iter.next());
				for(int k=0; k<listMoExclude.size();k++)
				{
					if(listMoExclude.get(k).toString().equals(toBeDeleted[2].toString())){						
						iter.remove();
					}
				}
			}
			}
			//Ends here
			
			/* End BRD 14-02-14)*/
			if(list != null && list.size() > 0) {
				Map<Integer, RoleCategory> categories = new HashMap<Integer, RoleCategory>();

				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					Integer categoryId = new Integer(row[0].toString());
					if(categories.containsKey(categoryId)){
						RoleCategory roleCategory = categories.get(categoryId);
						
						DocumentDefinition docDef = new DocumentDefinition();
						docDef.setId(new Integer(row[2].toString()));
						docDef.setName((String)row[3]);
						roleCategory.getDocumentList().add(docDef);
						
					}
					else{
						RoleCategory category = new RoleCategory();
						category.setCategoryId(new Integer(row[0].toString()));
						category.setName((String)row[1]);
						//Added for sorting under order number MPS 2.1
						category.setOrderNumber(new Integer(row[4].toString()));
						
						DocumentDefinition docDef = new DocumentDefinition();
						docDef.setId(new Integer(row[2].toString()));
						docDef.setName((String)row[3]);
						
						category.getDocumentList().add(docDef);
						categories.put(category.getCategoryId(), category);
						result.getRoleCategories().add(category);
					}
				}
				//Added for sorting under order number MPS 2.1
				Collections.sort(result.getRoleCategories(), new Comparator<RoleCategory>(){

					@Override
					public int compare(RoleCategory arg0, RoleCategory arg1) {
						if(arg0.getOrderNumber()<arg1.getOrderNumber()){
							return -1;
						}else{
							return 1;
						}
						
					}
					
				});
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}

//	added by nelsons
	public ReportListResult retrieveTopTenReportListEmployee(ReportListContract contract)
	throws Exception {
		logger.debug("To retrieve the top 10 records for employee---------->>>>");
		ReportListResult result = new ReportListResult();
		StringBuilder rolesString = new StringBuilder();
		for (String roleName : contract.getUserRoleNames()){
			rolesString.append("'" + roleName.toUpperCase() + "'");
			rolesString.append(",");
		}
		if(rolesString.length() > 0)
			rolesString.delete(rolesString.length() - 1, rolesString.length());	

		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		logger.info("!!!!!!!!!!!!!!!!!!!! SQL_GET_TOP10_REPORTS_EMPLOYEE "+SQL_GET_TOP10_REPORTS_EMPLOYEE);
		MessageFormat mf = new MessageFormat(SQL_GET_TOP10_REPORTS_EMPLOYEE);
		String sql = mf.format(new Object[]{
		rolesString.toString(), 
		getViewTypeStub(contract.getViewTypes())});	
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);
	 logger.debug("Emp top ten "+SQL_GET_TOP10_REPORTS_EMPLOYEE);
//		report_definition_name, report_definition_id, report_creation_date, report_id, report_type, 
//		file_data_link, file_path, category_name, definition_type
			query.addScalar("report_definition_name");
			query.addScalar("report_definition_id");
			query.addScalar("report_creation_date", Hibernate.TIMESTAMP);
			query.addScalar("report_id");
			query.addScalar("report_type");
			query.addScalar("file_data_link");
			query.addScalar("file_path");
			query.addScalar("category_name");
			query.addScalar("definition_type");
			query.addScalar("tooltip_text");
			query.addScalar("job_runlog_id");
			query.addScalar("status_code");			// added by rituparna
	
			query.setParameter("locationCode", language);
			query.setParameter("manuType", "MU");
			query.setParameter("bussType", "BO");
			query.setParameter("mdmLevel", contract.getMdmLevel());
			query.setParameter("mdmId", contract.getMdmId());
			query.setParameter("employeemdmId", contract.getEmployeeMdmId());
			query.setParameter("userNumber", contract.getUserNumber());
			logger.debug("$$$ user#: " + contract.getUserNumber());
			query.setParameter("isDeleted", "F");
			query.setParameter("isReportDeleted", "T");
			
			logger.debug("### mdm id on fetching top10 reports for emp: " + contract.getMdmId());
			logger.debug("The query is for getting the tpo 10 reports ------------->>>"+query.getQueryString());
			List list = query.list();
			logger.debug("The list size is------------->>>>"+list.size());
			if(list != null && list.size() > 0) {
				HashMap<Object, ReportListRow> reportListRows = new LinkedHashMap<Object, ReportListRow>();
				logger.debug("########## list size is >0");
				for(int i = 0; i < list.size(); i++){
					logger.debug("######### Inside for loop");
					boolean isSelected =false ; // added
					Object[] row = (Object[]) list.get(i);
					Object fileDataLink = row[5];
					String tooltip = (String)row[8]; // added by arvind
					if(fileDataLink != null && reportListRows.containsKey(fileDataLink)){
						logger.debug("############## Filedatalink is not null ");
						ReportListRow reportRow = reportListRows.get(fileDataLink);
				//reportRow.setReportDefinitionName((String)row[0]);
				//reportRow.setLastUpdated((Date)row[2]);
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setStatusCode((String) row[11]);		// added by rituparna
						report.setFilePath((String)row[6]);
						reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						// added by arvind start
						if(tooltip !=null && tooltip.indexOf("ERROR")>-1){
							logger.debug("************* Inside If");
							report.setTooltipText("");
						}else{
							logger.debug("************* Inside else");
						report.setTooltipText((String)row[9]);
						}
						// added by arvind end
						report.setJobRunLogId((String)row[10]);
						reportRow.getReportList().add(report);
					}
					else{
						logger.debug("############## Filedatalink is not null ");
						if(fileDataLink == null) {
							fileDataLink = new Object();
						}
//						if(reportListRows.values().size() == 10){		//commented by rituparna
						if(reportListRows.values().size() == 11){		//changed by rituparna
							break;
						}
						ReportListRow reportRow = new ReportListRow();
						// Commented for CI 14.10 removed account name from defination anme
						//	reportRow.setReportDefinitionName((String)row[0] + " - " + contract.getAccountNm());	// changed by nelson for employee report
						reportRow.setReportDefinitionName((String)row[0]);
						reportRow.setLastUpdated((Date)row[2]);
//						reportRow.setStatus(RunLogStatusEnum.PUBLISHOK.getStatusCode());   // commented by rituparna
						reportRow.setStatus((String) row[11]);		// added by rituparna
						logger.debug("### status of this report is: " + reportRow.getStatus());
						reportRow.setReportCategoryName((String)row[7]);
						reportRow.setDefinitionType((String)row[8]);
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[6]);
						reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						// added by arvind start
						if(tooltip !=null && tooltip.indexOf("ERROR")>-1){
							logger.debug("************* Inside If");
							report.setTooltipText("");
						}else{
							logger.debug("************* Inside else");
						report.setTooltipText((String)row[9]);
						}
						// added by arvind end						
						report.setJobRunLogId((String)row[10]);
						reportRow.getReportList().add(report);
						reportListRows.put(fileDataLink, reportRow);
				

					}
				}

				for(Map.Entry<Object,ReportListRow> entry : reportListRows.entrySet()){
					result.getReportListRows().add(entry.getValue());
				}			
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
			HibernateUtil.closeSession();
		}
		for(ReportListRow r : result.getReportListRows()) {
			if(r.getDefinitionType()!=null && r.getDefinitionType().equalsIgnoreCase("BO") && r.getStatusVoter() < REPORT_FILE_TYPES_NUMBER) {
				r.setStatus(ReportConstants.REPORT_STATUS_BUILDOK);
			}
		}
		return result;		
	}
//	end of addition by nelsons
	
	public ReportListResult retrieveTopTenReportList(ReportListContract contract)
			throws Exception {
		
		ReportListResult result = new ReportListResult();
		StringBuilder rolesString = new StringBuilder();
		for (String roleName : contract.getUserRoleNames()){
			rolesString.append("'" + roleName.toUpperCase() + "'");
			rolesString.append(",");
		}
		if(rolesString.length() > 0)
			rolesString.delete(rolesString.length() - 1, rolesString.length());	
		
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		MessageFormat mf = new MessageFormat(SQL_GET_TOP10_REPORTS);
		String sql = mf.format(new Object[]{
				rolesString.toString(), 
				getViewTypeStub(contract.getViewTypes())});	
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);
			query.addScalar("report_definition_name");
			query.addScalar("report_definition_id");
			query.addScalar("report_creation_date", Hibernate.TIMESTAMP);
			query.addScalar("report_id");
			query.addScalar("report_type");
			query.addScalar("file_data_link");
			query.addScalar("file_path");
			query.addScalar("category_name");
			query.addScalar("definition_type");
			query.addScalar("tooltip_text");
			query.addScalar("job_runlog_id");
			query.addScalar("status_code");			// added by rituparna
			query.addScalar("file_name"); //Added for CI BRD 13-10-10
			
			query.setParameter("locationCode", language);
			query.setParameter("manuType", "MU");
			query.setParameter("bussType", "BO");
			query.setParameter("mdmLevel", contract.getMdmLevel());
			query.setParameter("mdmId", contract.getMdmId());
			query.setParameter("userNumber", contract.getUserNumber());
			query.setParameter("isDeleted", "F");
			query.setParameter("isReportDeleted", "T");
			List list = query.list();
			if(list != null && list.size() > 0) {
				HashMap<Object, ReportListRow> reportListRows = new LinkedHashMap<Object, ReportListRow>();
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					//Object fileDataLink = row[5];
					Object reportDefinitionId = row[1];
					String tooltip = (String)row[9]; // added by arvind					
					if(reportDefinitionId != null && reportListRows.containsKey(reportDefinitionId)){
						ReportListRow reportRow = reportListRows.get(reportDefinitionId);
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setStatusCode((String) row[11]);		// added by rituparna
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[6]);
						reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						// added by arvind start
						if(tooltip !=null && tooltip.indexOf("ERROR")>-1){
							report.setTooltipText("");
						}else{
						report.setTooltipText((String)row[9]);
						}
						// added by arvind end
						report.setJobRunLogId((String)row[10]);
						reportRow.getReportList().add(report);
						if(reportListRows.values().size() == 10){		// added by sbag
							break;
						}
					}
					else{
						if(reportDefinitionId == null) {
							reportDefinitionId = new Object();
						}
						if(reportListRows.values().size() == 10){		// changed by sbag
							break;
						}
						ReportListRow reportRow = new ReportListRow();
						reportRow.setReportDefinitionName((String)row[12]); //file-name is fetched instead of Report Name BRD 13-10-10
						reportRow.setLastUpdated((Date)row[2]);
						reportRow.setStatus((String) row[11]);		// added by rituparna
//						reportRow.setStatus(RunLogStatusEnum.PUBLISHOK.getStatusCode());			// commented by rituparna
						reportRow.setReportCategoryName((String)row[7]);
						reportRow.setDefinitionType((String)row[8]);
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[6]);
						reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						// added by arvind start
						if(tooltip !=null && tooltip.indexOf("ERROR")>-1){
							report.setTooltipText("");
						}else{
						report.setTooltipText((String)row[9]);
						}
						// added by arvind end
						report.setJobRunLogId((String)row[10]);
						reportRow.getReportList().add(report);
						reportListRows.put(reportDefinitionId, reportRow);
						

					}
				}
				for(Map.Entry<Object,ReportListRow> entry : reportListRows.entrySet()){
					result.getReportListRows().add(entry.getValue());
	
				}			
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		for(ReportListRow r : result.getReportListRows()) {
			if(r.getDefinitionType()!=null && r.getDefinitionType().equalsIgnoreCase("BO") && r.getStatusVoter() < REPORT_FILE_TYPES_NUMBER) {
				r.setStatus(ReportConstants.REPORT_STATUS_BUILDOK);
			}
		}
		return result;		
	}
	

	public ReportDefinitionDisplayResult getReportDefinitionDisplay(ReportDefinitionDisplayContract contract) {
		ReportDefinitionDisplayResult result = new ReportDefinitionDisplayResult();
		
		Query query = HibernateUtil.getSession().createSQLQuery(SQL_GET_DEFINITION_TYPE_BY_ID);
		String docDefinitionId = contract.getDefinitionId();
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		query.setParameter("definitionId", docDefinitionId);
		query.setParameter("localeCode", language);

		List list = query.list();
		Object[] row = (Object[])list.get(0);
		result.setDefinitionId(row[0].toString());
		result.setDefinitionType((String)row[1]);
		result.setDefinitionName((String)row[2]);
		result.setDefinitionDescription((String)row[3]);
		
		return result;
	}	
	
	
	public ReportListResult retrieveManuUploadedReportsList(ReportListContract contract) throws Exception{
		ReportListResult result = new ReportListResult();
		StringBuilder rolesString = new StringBuilder();
		for (String roleName : contract.getUserRoleNames()){
			rolesString.append("'" + roleName.toUpperCase() + "'");
			rolesString.append(",");
		}
		if(rolesString.length() > 0)
			rolesString.delete(rolesString.length() - 1, rolesString.length());	
		
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		//MessageFormat mf = new MessageFormat(SQL_GET_MANU_UPLOADED_REPORTS_BY_DEFINITION_ID); // Commented for CI 13-10-10
		MessageFormat mf = new MessageFormat(SQL_GET_MANU_UPLOADED_REPORTS_BY_DEFINITION_ID_MU); // new query used to fetch file name CI 13-10-10
		String sql = mf.format(new Object[]{rolesString.toString()});	
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);
			//		report_definition_name, report_definition_id, report_creation_date, report_id, 
			//		report_type, file_data_link, a_webc_url, category_name 
			query.addScalar("report_definition_name");
			query.addScalar("report_definition_id");
			query.addScalar("report_creation_date", Hibernate.TIMESTAMP);
			query.addScalar("report_id");
			query.addScalar("report_type");
			query.addScalar("file_data_link");
			query.addScalar("a_webc_url");
			query.addScalar("category_name");
			query.addScalar("tooltip_text");
			query.addScalar("file_name"); // added to fetch uploaded file name CI 13-10-10
			

			query.setParameter("locationCode", language);
			query.setParameter("mdmLevel", contract.getMdmLevel());
			query.setParameter("mdmId", contract.getMdmId());
			query.setParameter("definitionId", contract.getReportDefinitionId());
			query.setParameter("isReportDeleted", "T");
			
			
			List list = query.list();
			if(list != null && list.size() > 0) {
				HashMap<Object, ReportListRow> reportListRows = new LinkedHashMap<Object, ReportListRow>();
				
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					Object fileDataLink = row[5];
					logger.debug("The file name is:"+(String)row[9]);
					if(fileDataLink != null && reportListRows.containsKey(fileDataLink)){
						ReportListRow reportRow = reportListRows.get(fileDataLink);
						//reportRow.setReportDefinitionName((String)row[0]);
						//reportRow.setLastUpdated((Date)row[2]);
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[6]);						
						//report.setTooltipText((String)row[8]); //commented by arvind
						report.setTooltipText("");
						reportRow.getReportList().add(report);
						reportRow.setReportDefinitionName((String)row[9]); //added for CI 13-10-10
						logger.debug("The ReportDefinitionName is:"+reportRow.getReportDefinitionName()); // added for CI 13-10-10
					}
					else{
						if(fileDataLink == null) {
							fileDataLink = new Object();
						}
						if(reportListRows.values().size() == MAX_MANUALLY_REPORT_NUMBER){
							break;
						}
						ReportListRow reportRow = new ReportListRow();
						//reportRow.setReportDefinitionName((String)row[0]); //commented for CI 13-10-10
						reportRow.setReportDefinitionName((String)row[9]); // added for CI 13-10-10
						logger.debug("The ReportDefinitionName is in [ELSE]:"+reportRow.getReportDefinitionName());
						reportRow.setReportCategoryName((String)row[7]);
						reportRow.setLastUpdated((Date)row[2]);
						reportRow.setDefinitionType("MU");
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[6]);
						//report.setTooltipText((String)row[8]);  //commented by arvind
						report.setTooltipText("");
						reportRow.getReportList().add(report);
						reportListRows.put(fileDataLink, reportRow);
					}
				}
				for(Map.Entry<Object,ReportListRow> entry : reportListRows.entrySet()){
					result.getReportListRows().add(entry.getValue());
				}	
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;		
	}
	
//	added by nelsons
	public ReportListResult retrieveScheduledReportsListEmployee(ReportListContract contract){
		logger.debug("Inside the Query employee scheduled------------>>>>");
		ReportListResult result = new ReportListResult();
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(SQL_GET_SCHEDULED_REPORTS_BY_DEFINITION_ID_EMPLOYEE);
//			report_definition_name, rd.report_definition_id,  report_creation_date,report_id,  report_type,
//			file_data_link, status_code,  file_path,  job_runlog_id, category_name 
			query.addScalar("report_definition_name");
			query.addScalar("report_definition_id");
			query.addScalar("report_creation_date", Hibernate.TIMESTAMP);
			query.addScalar("report_id");
			query.addScalar("report_type");
		
			query.addScalar("file_data_link");
			query.addScalar("status_code");
			query.addScalar("file_path");
			query.addScalar("job_runlog_id");
			query.addScalar("category_name");
			query.addScalar("tooltip_text");
			
		
			query.setParameter("locationCode", language);
			query.setParameter("definitionId", contract.getReportDefinitionId());
			query.setParameter("userNumber", contract.getUserNumber());
			query.setParameter("isReportDeleted", "T");
			//query.setParameter("mdmId", contract.getMdmId());		// added by nelson
			query.setParameter("employeemdmId", contract.getEmployeeMdmId());//changed for LEX:AIR00060472 
			logger.debug("employee not top 10 query "+SQL_GET_SCHEDULED_REPORTS_BY_DEFINITION_ID_EMPLOYEE);
			
			List list = query.list();
			logger.debug("The query result set is----------->>>>>>"+list.size());
			if(list != null && list.size() > 0) {
				HashMap<Object, ReportListRow> reportListRows = new LinkedHashMap<Object, ReportListRow>();
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					Object fileDataLink = row[5];
					if(fileDataLink != null && reportListRows.containsKey(fileDataLink)){
						ReportListRow reportRow = reportListRows.get(fileDataLink);
						
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[7]);
						report.setJobRunLogId((String)row[8]);
						reportRow.setReportCategoryName((String)row[9]);
						report.setTooltipText((String)row[10]);
						reportRow.getReportList().add(report);							
						
						String reportStatus = (String) row[6];
						if(reportRow.getStatus() != null && reportRow.getStatus().equalsIgnoreCase(ReportConstants.REPORT_STATUS_PUBLISHOK)) {
							reportRow.setStatus(reportStatus);
							reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						}
					}
					else{
						if(fileDataLink == null) {
							fileDataLink = new Object();
						}
						if(reportListRows.values().size() == MAX_SCHEDULED_REPORT_NUMBER ){
							break;
						}
						ReportListRow reportRow = new ReportListRow();
						reportRow.setReportDefinitionName((String)row[0] + " - " + contract.getAccountNm());	// changed by nelson for employee report
						reportRow.setStatus((String)row[6]);
						reportRow.setLastUpdated((Date)row[2]);
						
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[7]);
						report.setJobRunLogId((String)row[8]);
						reportRow.setReportCategoryName((String)row[9]);
						report.setTooltipText((String)row[10]);
						reportRow.getReportList().add(report);							
						
						String reportStatus = (String) row[6];
						if(reportRow.getStatus() != null && reportRow.getStatus().equalsIgnoreCase(ReportConstants.REPORT_STATUS_PUBLISHOK)) {
							reportRow.setStatus(reportStatus);
							reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						}
						reportListRows.put(fileDataLink, reportRow);
					}
				}
				for(Map.Entry<Object,ReportListRow> entry : reportListRows.entrySet()){
					result.getReportListRows().add(entry.getValue());
				}			
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		
		// only both pdf and excel publish ok, then set the status as Complete
		for(ReportListRow r : result.getReportListRows()) {
			if(r.getStatusVoter() < REPORT_FILE_TYPES_NUMBER) {
				r.setStatus(ReportConstants.REPORT_STATUS_BUILDOK);
			}
		}
		return result;
	}	
//	end of addition by nelsons
	
	public ReportListResult retrieveScheduledReportsList(ReportListContract contract){
		ReportListResult result = new ReportListResult();
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		//language set to en for defect 17315
		language = "en";
		logger.debug("-----------------------LANGUAGE IS -----------------------"+language);
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(SQL_GET_SCHEDULED_REPORTS_BY_DEFINITION_ID);
//			report_definition_name, rd.report_definition_id,  report_creation_date,report_id,  report_type,
//			file_data_link, status_code,  file_path,  job_runlog_id, category_name 
			query.addScalar("report_definition_name");
			query.addScalar("report_definition_id");
			query.addScalar("report_creation_date", Hibernate.TIMESTAMP);
			query.addScalar("report_id");
			query.addScalar("report_type");
		
			query.addScalar("file_data_link");
			query.addScalar("status_code");
			query.addScalar("file_path");
			query.addScalar("job_runlog_id");
			query.addScalar("category_name");
			query.addScalar("tooltip_text");
			
		
			query.setParameter("locationCode", language);
			query.setParameter("definitionId", contract.getReportDefinitionId());
			query.setParameter("userNumber", contract.getUserNumber());
			query.setParameter("isReportDeleted", "T");
			logger.debug("Customer not top ten:"+SQL_GET_SCHEDULED_REPORTS_BY_DEFINITION_ID);
			List list = query.list();
			if(list != null && list.size() > 0) {
				HashMap<Object, ReportListRow> reportListRows = new LinkedHashMap<Object, ReportListRow>();
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					Object fileDataLink = row[5];
					if(fileDataLink != null && reportListRows.containsKey(fileDataLink)){
						ReportListRow reportRow = reportListRows.get(fileDataLink);
						
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[7]);
						report.setJobRunLogId((String)row[8]);
						reportRow.setReportCategoryName((String)row[9]);
						report.setTooltipText((String)row[10]);
						reportRow.getReportList().add(report);							
						
						String reportStatus = (String) row[6];
						if(reportRow.getStatus() != null && reportRow.getStatus().equalsIgnoreCase(ReportConstants.REPORT_STATUS_PUBLISHOK)) {
							reportRow.setStatus(reportStatus);
							reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						}
					}
					else{
						if(fileDataLink == null) {
							fileDataLink = new Object();
						}
						if(reportListRows.values().size() == MAX_SCHEDULED_REPORT_NUMBER ){
							break;
						}
						ReportListRow reportRow = new ReportListRow();
						reportRow.setReportDefinitionName((String)row[0]);
						reportRow.setStatus((String)row[6]);
						reportRow.setLastUpdated((Date)row[2]);
						
						Report report = new Report();
						report.setFileObjectId((String)row[3]);
						report.setFileType((String)row[4]);
						report.setFilePath((String)row[7]);
						report.setJobRunLogId((String)row[8]);
						reportRow.setReportCategoryName((String)row[9]);
						report.setTooltipText((String)row[10]);
						reportRow.getReportList().add(report);							
						
						String reportStatus = (String) row[6];
						if(reportRow.getStatus() != null && reportRow.getStatus().equalsIgnoreCase(ReportConstants.REPORT_STATUS_PUBLISHOK)) {
							reportRow.setStatus(reportStatus);
							reportRow.setStatusVoter(reportRow.getStatusVoter() + 1);
						}
						reportListRows.put(fileDataLink, reportRow);
					}
				}
				for(Map.Entry<Object,ReportListRow> entry : reportListRows.entrySet()){
					result.getReportListRows().add(entry.getValue());
				}			
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		
		// only both pdf and excel publish ok, then set the status as Complete
		for(ReportListRow r : result.getReportListRows()) {
			if(r.getStatusVoter() < REPORT_FILE_TYPES_NUMBER) {
				r.setStatus(ReportConstants.REPORT_STATUS_BUILDOK);
			}
		}
		return result;
	}	
	
	
//	public ReportDefinitionParameterResult retrieveParametersByDefinitionId(		// commented by nelsons
	public ReportParamsResult retrieveParametersByDefinitionId(						// added by nelsons
			String definitionId, String userNumber, String mdmId) throws Exception {// added mdmId by nelson for employee report
		
//		ReportDefinitionParameterResult result = new ReportDefinitionParameterResult();		// commented by nelson
		ReportDefinitionParameterResult resultRunNow = new ReportDefinitionParameterResult();	// added by nelson
		ReportDefinitionParameterResult resultSchedule = new ReportDefinitionParameterResult();		// added by nelson

		ReportParamsResult resultFinal = new ReportParamsResult();	// added by nelsons
		
//		result.setReportDefinitionId(new Integer(definitionId));					// commented by nelson
		resultRunNow.setReportDefinitionId(new Integer(definitionId));								// added by nelson
		resultSchedule.setReportDefinitionId(new Integer(definitionId));							// added by nelson
		resultFinal.setReportDefinitionId(new Integer(definitionId));		   						// added by nelson
		
		try {
			Query query = HibernateUtil.getSession().createSQLQuery(SQL_GET_PARAMETERS_BY_DEFINITION_ID);
			query.setParameter("definitionId", definitionId);
			query.setParameter("usernumber", userNumber);
			query.setParameter("mdmId", mdmId);		// added by nelson for employee report
			logger.debug("### query fired on clicking on schedule report button: " + query.getQueryString());
			List list = query.list();
			if(list != null && list.size() > 0) {
//				added by nelson
				boolean isScheduled = false;
				boolean onlyRunNow = false;
				boolean onlyScheduled = false;
//				end of addition by nelson
				
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					Integer parameterId = new Integer(row[0].toString());
					String parameterName = (String)row[1];
					String displayName = (String)row[2];
					//Paramters fetched for CI-7 Defect #11656--STARTS 
					String displayNameSpanish = (String)row[20];
					String displayNameGerman = (String)row[21];
					String displayNameFrench = (String)row[22];
					String displayNameChina = (String)row[23];
					String displayNameChinaTw = (String)row[24];
					String displayNamePt_pt = (String)row[25];
					String displayNamePt_br = (String)row[26];
					String displayNameItaly = (String)row[27];
					String displayNameKorea = (String)row[28];
					String displayNameRussia = (String)row[29];
					String displayNameJapan = (String)row[30];
					String displayNameTurkey = (String)row[31];
					//Paramters fetched for CI-7 Defect #11656--ENDS
					String parameterValue = (String)row[3];
					String dataType = (String)row[4];
					String listValues = (String)row[5];
					Integer maxSize = row[6] == null ? null : new Integer(row[6].toString());
					String isRequired = row[7] == null ? null : ((Character)row[7]).toString();
					//Integer definitionIdInteger = new Integer(row[8].toString());
					Integer reportScheduleId = row[9] == null ? null : new Integer(row[9].toString());
					String runFrequency = (String)row[10];
//					added by nelson
					if(null != runFrequency)
						if(!runFrequency.equalsIgnoreCase(RUN_NOW_REPORT)) {
							isScheduled = true;
						}
//					end of addition by nelson
					Integer runInterval = row[11] == null ? null : new Integer(row[11].toString()); 
					Integer dayOfWeek = row[12] == null ? null : new Integer(row[12].toString());
					Integer dayOfMonth = row[13] == null ? null : new Integer(row[13].toString());
					Date effectiveDate = (Date)row[14];
					Date expirationDate = (Date)row[15];
					String parameterPairName = (String)row[16];
					String parameterPairValue = (String)row[17];
					String preferredTimeZone = (String)row[19];
					ReportParameters parameter = new ReportParameters();
					parameter.setReportParameterId(parameterId);
					parameter.setName(parameterName);
					parameter.setDisplayName(displayName);
					//parameters set for CI-7 Defect #11656 --STARTS
					parameter.setDisplayNameSpanish(displayNameSpanish);
					parameter.setDisplayNameGerman(displayNameGerman);
					parameter.setDisplayNameFrench(displayNameFrench);
					parameter.setDisplayNameChina(displayNameChina);
					parameter.setDisplayNameChinaTw(displayNameChinaTw);
					parameter.setDisplayNamePortugal(displayNamePt_pt);
					parameter.setDisplayNamePortugalBrazil(displayNamePt_br);
					parameter.setDisplayNameItaly(displayNameItaly);
					parameter.setDisplayNameKorea(displayNameKorea);
					parameter.setDisplayNameRussia(displayNameRussia);
					parameter.setDisplayNameJapan(displayNameJapan);
					parameter.setDisplayNameTurkey(displayNameTurkey);
					//parameters set for CI-7 Defect #11656 --STARTS
					parameter.setValue(parameterValue);
					parameter.setDataType(dataType);
					parameter.setListValues(listValues);
					parameter.setMaxSize(maxSize);
					if(isRequired != null){
						if("t".equalsIgnoreCase(isRequired)){
							parameter.setIsRequired(true);
						}
						else{
							parameter.setIsRequired(false);
						}
					}
					
//					commented by nelson
					/*result.getParameters().add(parameter);
					result.setReportScheduleId(reportScheduleId);
					result.setScheduleFrequency(runFrequency);
					result.setRunInterval(runInterval);
					result.setSpecificDayInWeek(dayOfWeek);
					result.setSpecificDayInMonth(dayOfMonth);
					result.setEffectiveDate(effectiveDate);
					result.setExpirationDate(expirationDate);
					result.setPerferredTimeZone(preferredTimeZone);
					result.getParametersValue().put(parameterPairName, parameterPairValue);*/
					
					//parameter.setReportDefinitionId(new Integer(definitionId));
//					added by nelson
//					the query is fetching both run now and scheduling data, so need to seperate them
					if(isScheduled) {
//						containing only scheduling data
						onlyScheduled = true;
						resultSchedule.getParameters().add(parameter);
						resultSchedule.setReportScheduleId(reportScheduleId);
						resultSchedule.setScheduleFrequency(runFrequency);			
						resultSchedule.setRunInterval(runInterval);
						resultSchedule.setSpecificDayInWeek(dayOfWeek);
						resultSchedule.setSpecificDayInMonth(dayOfMonth);
						resultSchedule.setEffectiveDate(effectiveDate);
						resultSchedule.setExpirationDate(expirationDate);
						resultSchedule.setPerferredTimeZone(preferredTimeZone);
						resultSchedule.getParametersValue().put(parameterPairName, parameterPairValue);
					}
					else {
//						containing only run now data
						onlyRunNow = true;
						resultRunNow.getParameters().add(parameter);
						resultRunNow.setReportScheduleId(reportScheduleId);
						resultRunNow.setScheduleFrequency(runFrequency);			
						resultRunNow.setRunInterval(runInterval);
						resultRunNow.setSpecificDayInWeek(dayOfWeek);
						resultRunNow.setSpecificDayInMonth(dayOfMonth);
						resultRunNow.setEffectiveDate(effectiveDate);
						resultRunNow.setExpirationDate(expirationDate);
						resultRunNow.setPerferredTimeZone(preferredTimeZone);
						resultRunNow.getParametersValue().put(parameterPairName, parameterPairValue);
					}
//					end of addition by nelson
//					commented by nelson
					/*result.getParameters().add(parameter);
					result.setReportScheduleId(reportScheduleId);
					result.setScheduleFrequency(runFrequency);			
					result.setRunInterval(runInterval);
					result.setSpecificDayInWeek(dayOfWeek);
					result.setSpecificDayInMonth(dayOfMonth);
					result.setEffectiveDate(effectiveDate);
					result.setExpirationDate(expirationDate);
					result.setPerferredTimeZone(preferredTimeZone);
					result.getParametersValue().put(parameterPairName, parameterPairValue);*/
//					end of comment by nelson

					isScheduled = false; 				// added by nelson
				}
//				added by nelson

				if(onlyRunNow && onlyScheduled) {
//					its returning both ondemand and scheduled data from report_schedule 
//					need to create final result object
					resultFinal.setScheduleFrequency(resultSchedule.getScheduleFrequency());
					resultFinal.setRunInterval(resultSchedule.getRunInterval());
					resultFinal.setEffectiveDate(resultSchedule.getEffectiveDate());
					resultFinal.setExpirationDate(resultSchedule.getExpirationDate());
					resultFinal.setSpecificDayInMonth(resultSchedule.getSpecificDayInMonth());
					resultFinal.setSpecificDayInWeek(resultSchedule.getSpecificDayInWeek());
					resultFinal.setReportScheduleId(resultSchedule.getReportScheduleId());
					resultFinal.setPerferredTimeZone(resultSchedule.getPerferredTimeZone());
					
					resultFinal.setParameters(resultSchedule.getParameters());
					
					resultFinal.setScheduleParametersValue(resultSchedule.getParametersValue());
					resultFinal.setRunNowParametersValue(resultRunNow.getParametersValue());
				}
				if(onlyScheduled && !onlyRunNow) {
					resultFinal.setScheduleFrequency(resultSchedule.getScheduleFrequency());
					resultFinal.setRunInterval(resultSchedule.getRunInterval());
					resultFinal.setEffectiveDate(resultSchedule.getEffectiveDate());
					resultFinal.setExpirationDate(resultSchedule.getExpirationDate());
					resultFinal.setSpecificDayInMonth(resultSchedule.getSpecificDayInMonth());
					resultFinal.setSpecificDayInWeek(resultSchedule.getSpecificDayInWeek());
					resultFinal.setReportScheduleId(resultSchedule.getReportScheduleId());
					resultFinal.setPerferredTimeZone(resultSchedule.getPerferredTimeZone());
					
					resultFinal.setParameters(resultSchedule.getParameters());
					
					resultFinal.setScheduleParametersValue(resultSchedule.getParametersValue());
				}
				if(onlyRunNow && !onlyScheduled) {
					resultFinal.setScheduleFrequency(resultRunNow.getScheduleFrequency());
					resultFinal.setRunInterval(resultRunNow.getRunInterval());
					resultFinal.setEffectiveDate(resultRunNow.getEffectiveDate());
					resultFinal.setExpirationDate(resultRunNow.getExpirationDate());
					resultFinal.setSpecificDayInMonth(resultRunNow.getSpecificDayInMonth());
					resultFinal.setSpecificDayInWeek(resultRunNow.getSpecificDayInWeek());
					resultFinal.setReportScheduleId(resultRunNow.getReportScheduleId());
					resultFinal.setPerferredTimeZone(resultRunNow.getPerferredTimeZone());
					
					resultFinal.setParameters(resultRunNow.getParameters());
					
					resultFinal.setRunNowParametersValue(resultRunNow.getParametersValue());
				}
//				end of addition by nelson

			}
			else{
				//in case there is no predefined parameters, still need to try to get the report schedule record if any
				retrieveReportScheduleByDefinitionId(resultFinal, userNumber, mdmId);			// added by nelson, changed for employee report
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		//added -- start
		Set<ReportParameters> hs = new HashSet<ReportParameters>();
		List<ReportParameters> al = new ArrayList<ReportParameters>();
		hs.addAll(resultFinal.getParameters());
		al.addAll(hs);
		resultFinal.setParameters(al);
		return resultFinal;																// added by nelson
		
	}


	private void retrieveReportScheduleByDefinitionId(
//			ReportDefinitionParameterResult result, 		// commented by nelsons
			ReportParamsResult result,
			String userNumber,
			String mdmId) {		// added by nelson for employee report

		Query query = HibernateUtil.getSession().createSQLQuery(SQL_GET_REPORT_SCHEDULE_BY_DEFINITION_ID);
		query.setParameter("definitionId", result.getReportDefinitionId());
		query.setParameter("usernumber", userNumber);
		query.setParameter("mdmId", mdmId);			// added by nelson for employee report
		List list = query.list();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				Object[] row = (Object[]) list.get(i);
				Integer reportScheduleId = new Integer(row[0].toString());
				String runFrequency = (String)row[1];
				Integer runInterval = row[2] == null ? null : new Integer(row[2].toString()); 
				Integer dayOfWeek = row[3] == null ? null : new Integer(row[3].toString());
				Integer dayOfMonth = row[4] == null ? null : new Integer(row[4].toString());
				Date effectiveDate = (Date)row[5];
				Date expirationDate = (Date)row[6];
				
				result.setReportScheduleId(reportScheduleId);
				result.setScheduleFrequency(runFrequency);
				result.setRunInterval(runInterval);
				result.setSpecificDayInWeek(dayOfWeek);
				result.setSpecificDayInMonth(dayOfMonth);
				result.setEffectiveDate(effectiveDate);
				result.setExpirationDate(expirationDate);	
			}
		}
	}

//	commented by nelsons
	/*public ScheduleReportResult saveReportSchedule(
			ScheduleReportContract contract) throws Exception{
		
		ScheduleReportResult result = new ScheduleReportResult();
		try {
			HibernateUtil.beginTransaction();
			ReportSchedule schedule = contract.getReportSchedule();
			List<ReportScheduleParameter> parameterValues = contract.getParameterValues();
			List<ReportScheduleParameter> parameterValuesMatched = new ArrayList<ReportScheduleParameter>();
			List<ReportScheduleParameter> parameterValuesInDBMatched = new ArrayList<ReportScheduleParameter>();
			
			if(schedule.getId() != null){
				// this schedule is for update
				Query query = HibernateUtil.getSession().createQuery("from ReportSchedule where reportDefinitionId = :reportDefinitionId and userNumber = :userNumber");
				query.setParameter("reportDefinitionId", schedule.getReportDefinitionId());
				query.setParameter("userNumber", schedule.getUserNumber());
				List results = query.list();
				if(results.size() > 0){
					ReportSchedule reportScheduleToBeUpdated = (ReportSchedule)results.get(0);
					for(ReportScheduleParameter parameterValueDB : reportScheduleToBeUpdated.getReportScheduleParameters()){
						for(ReportScheduleParameter parameterValue : parameterValues){
							if(parameterValueDB.getName()!=null && parameterValueDB.getName().equals(parameterValue.getName())){
								parameterValueDB.setValue(parameterValue.getValue());
								parameterValuesMatched.add(parameterValue);
								parameterValuesInDBMatched.add(parameterValueDB);
								break;
							}
						}
					}
					
					// all unmatched parameter values needs to be removed from table
					List<ReportScheduleParameter> needToBeDeletedParameters = reportScheduleToBeUpdated.getReportScheduleParameters();
					needToBeDeletedParameters.removeAll(parameterValuesInDBMatched);
					for(ReportScheduleParameter toBeDeletedParam : needToBeDeletedParameters){
						HibernateUtil.getSession().delete(toBeDeletedParam);
					}
					reportScheduleToBeUpdated.setReportScheduleParameters(parameterValuesInDBMatched);
					
					
					// the rest parameter values needs to be added to table 
					parameterValues.removeAll(parameterValuesMatched);
					for(ReportScheduleParameter parameterValue : parameterValues){
						parameterValue.setSchedule(reportScheduleToBeUpdated);
						reportScheduleToBeUpdated.getReportScheduleParameters().add(parameterValue);
					}
					
					reportScheduleToBeUpdated.setDayOfMonth(schedule.getDayOfMonth());
					reportScheduleToBeUpdated.setDayOfWeek(schedule.getDayOfWeek());
					reportScheduleToBeUpdated.setEffectiveDate(schedule.getEffectiveDate());
					reportScheduleToBeUpdated.setExpirationDate(schedule.getExpirationDate());
					reportScheduleToBeUpdated.setRecepientEmail(schedule.getRecepientEmail());
					reportScheduleToBeUpdated.setRunFrequency(schedule.getRunFrequency());
					reportScheduleToBeUpdated.setRunInterval(schedule.getRunInterval());
					reportScheduleToBeUpdated.setUserNumber(schedule.getUserNumber());
					reportScheduleToBeUpdated.setCountry(schedule.getCountry());
					reportScheduleToBeUpdated.setCustomLeadMinutes(schedule.getCustomLeadMinutes());
					reportScheduleToBeUpdated.setEmailReminderFlag(schedule.getEmailReminderFlag());
					reportScheduleToBeUpdated.setPreferedTimezone(schedule.getPreferedTimezone());
					
					HibernateUtil.getSession().saveOrUpdate(reportScheduleToBeUpdated);
				}
			}
			else{
				// this schedule is new, need to be inserted with all parameter values
				schedule.getReportScheduleParameters().addAll(parameterValues);
				for(ReportScheduleParameter parameterValue: schedule.getReportScheduleParameters()){
					parameterValue.setSchedule(schedule);
				}
				HibernateUtil.getSession().saveOrUpdate(schedule);
			}
			HibernateUtil.commitTransaction();
			result.setSchedule(schedule);
		}
		catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}
		finally{
			HibernateUtil.closeSession();
		}
		return result;
	}*/
	
//	added by nelsons
	public ScheduleReportResult saveReportSchedule(
			ScheduleReportContract contract) throws Exception {
		logger.debug("---------------------------in saveReportSchedule API------------------------------ ");

		ScheduleReportResult result = new ScheduleReportResult();
		try {
			
			logger.debug("---------------------------in saveReportSchedule API in try------------------------------ ");
			HibernateUtil.beginTransaction();			
			ReportSchedule schedule = contract.getReportSchedule();
			logger.debug("params start here ");
			logger.debug("params start here "+schedule.getIsLBSAccount());
			List<ReportScheduleParameter> parameterValues = contract.getParameterValues();
			List<ReportScheduleParameter> parameterValuesMatched = new ArrayList<ReportScheduleParameter>();
			List<ReportScheduleParameter> parameterValuesInDBMatched = new ArrayList<ReportScheduleParameter>();

//			after displaying report on page
//			getting usernumber and report definition id for fetching report based on it from db
			
			String userNbr = contract.getReportSchedule().getUserNumber();
			Integer rptDefId = contract.getReportSchedule().getReportDefinitionId();
			String mdmId = contract.getReportSchedule().getMdmId();		// added by nelson for employee report
			String formRunFreq = contract.getReportSchedule().getRunFrequency();
			String reportSignForm = null;
			String reportSignDb = null;
			
			boolean scheduleUpdated = false;
			
			if(formRunFreq.equalsIgnoreCase(RUN_NOW_REPORT)) {
				reportSignForm = RUN_NOW_REPORT;	
			}
			else {
				reportSignForm = SCHEDULED_REPORT;
			}
			
			logger.debug("reportDefinitionId:"+rptDefId);
			logger.debug("userNumber:"+userNbr);
			logger.debug("mdmId:"+mdmId);
			
//			Query query = HibernateUtil.getSession().createQuery("from ReportSchedule where reportDefinitionId = :reportDefinitionId and userNumber = :userNumber"); 					// commented by nelson for employee report
			Query query = HibernateUtil.getSession().createQuery("from ReportSchedule where reportDefinitionId = :reportDefinitionId and userNumber = :userNumber and mdmId = :mdmId"); // added by nelson for employee report
			query.setParameter("reportDefinitionId", rptDefId);
			query.setParameter("userNumber", userNbr);
			query.setParameter("mdmId", mdmId);		// added by nelson for employee report
			
			logger.debug("### retrieving report param for saving- reportDefId: " + rptDefId
					+ " userNumber: " + userNbr
					+ " mdmID: " + mdmId);
			
			List results = query.list();
			
			
//				need to save the details if the report is run for the first time after creation
				schedule.setId(null);
				schedule.getReportScheduleParameters().addAll(parameterValues);
				for(ReportScheduleParameter parameterValue: schedule.getReportScheduleParameters()){
					parameterValue.setSchedule(schedule);
				}
				
				logger.debug("----------------- LBS flag value in IMPL is 3 ------------------- "+schedule.getIsLBSAccount());
				HibernateUtil.getSession().save(schedule);
			
			
			HibernateUtil.commitTransaction();
			result.setSchedule(schedule);
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		}
        catch(Exception e){
           e.printStackTrace();
}
		finally{
			HibernateUtil.closeSession();
		}
		return result;
	}
//	end of addition by nelsons
	
	public Report retrieveReportById(String reportId, List<String> userRoles) throws Exception{
		if(userRoles == null){
			Query query = HibernateUtil.getSession().createSQLQuery("select R_OBJECT_ID, OBJECT_NAME, A_WEBC_URL, file_content_date R_CREATION_DATE from LXK_PORTAL_DOC_VIEW where R_OBJECT_ID = :id ");
			query.setParameter("id", reportId);
			List result = query.list();
			if(result.size() > 0){
				Object[] row = (Object[])result.get(0);
				Report report = new Report();
				report.setFileObjectId((String)row[0]);
				report.setFileName((String)row[1]);
				report.setFilePath((String)row[2]);
				report.setLastUpdateTime((Date)row[3]);
				return report;
			}			
		}
		else{
			StringBuilder rolesString = new StringBuilder();
			for (String roleName : userRoles){
				rolesString.append("'" + roleName.toUpperCase() + "'");
				rolesString.append(",");
			}
			if(rolesString.length() > 0)
				rolesString.delete(rolesString.length() - 1, rolesString.length());	
 
			MessageFormat mf = new MessageFormat(SQL_GET_REPORT_BY_ID_AND_ROLE);
			String sql = mf.format(new Object[]{rolesString.toString()});
			
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("id", reportId);

			List result = query.list();
			if(result.size() > 0){
				Object[] row = (Object[])result.get(0);
				Report report = new Report();
				report.setFileObjectId((String)row[0]);
				report.setFileName((String)row[1]);
				report.setFilePath((String)row[2]);
				report.setLastUpdateTime((Date)row[3]);
				return report;
			}			
		}
		return null;
	}
	
	public Report retrieveReportByDocumentID(String reportId, List<String> userRoles) throws Exception{
		if(userRoles == null){
			Query query = HibernateUtil.getSession().createSQLQuery("select Document_ID, File_name,created_date from report_job where Document_ID = :id ");
			query.setParameter("id", reportId);
			List result = query.list();
			if(result.size() > 0){
				Object[] row = (Object[])result.get(0);
				Report report = new Report();
				report.setFileObjectId((String)row[0]);
				report.setFileName((String)row[1]);
				report.setLastUpdateTime((Date)row[2]);
				return report;
			}			
		}
		else{
			StringBuilder rolesString = new StringBuilder();
			for (String roleName : userRoles){
				rolesString.append("'" + roleName.toUpperCase() + "'");
				rolesString.append(",");
			}
			if(rolesString.length() > 0)
				rolesString.delete(rolesString.length() - 1, rolesString.length());	
	
			MessageFormat mf = new MessageFormat(SQL_GET_REPORT_BY_DOCUMENTID_AND_ROLE);
			String sql = mf.format(new Object[]{rolesString.toString()});
			
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("id", reportId);
 

			List result = query.list();
			if(result.size() > 0){
				Object[] row = (Object[])result.get(0);
				Report report = new Report();
				report.setFileObjectId((String)row[0]);
				report.setFileName((String)row[1]);
				report.setLastUpdateTime((Date)row[2]);
				return report;
			}			
		}
		return null;
	}
	
	public CategoryAdministrationListResult retrieveCategoryAdministrationList(
			CategoryAdministrationListContract contract) throws Exception {
		CategoryAdministrationListResult result = new CategoryAdministrationListResult();
		List<RoleCategory> catetoryList = new ArrayList<RoleCategory>();

		try {
			Query query = HibernateUtil.getSession().createSQLQuery(SQL_SEARCH_ALL_CATEGORY);
			query.setParameter("type", LexmarkConstants.CATEGORY_TYPE_REPORT);
			populateCategoryList(catetoryList, query.list());
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
		    HibernateUtil.closeSession();
		}
		result.setRoleCategoryList(catetoryList);
		return result;
	}

	public CategoryAdministrationDetailResult retrieveCategoryAdministrationDetail(
			CategoryAdministrationDetailContract contract) throws Exception {
		CategoryAdministrationDetailResult result = new CategoryAdministrationDetailResult();
		RoleCategory category = null;
		try {
			category = (RoleCategory)HibernateUtil.getSession().get(RoleCategory.class, contract.getCategoryId());
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
		    HibernateUtil.closeSession();
		}
		result.setRoleCategory(category);
		return result;
	}

	public SaveCategoryAdministrationDetailResult saveCategoryAdministrationDetail(
			SaveCategoryAdministrationDetailContract contract) throws Exception {
		SaveCategoryAdministrationDetailResult result = new SaveCategoryAdministrationDetailResult();
		RoleCategory category = contract.getCategory();
		
		// check whether the category name is unique
		try {
			boolean isNameUnique = true;
			Query queryCategory = HibernateUtil.getSession().createSQLQuery(
					"select category_id from category where name = :categoryName and is_deleted = 'F'");
			queryCategory.setParameter("categoryName", category.getName());
			List categoryIdList = queryCategory.list();
			if (categoryIdList != null && categoryIdList.size() > 0) {
				for (int i = 0; i < categoryIdList.size(); i ++) {
					if (category.getCategoryId() == null) {
						isNameUnique = false;
						break;
					} else if (((BigDecimal) categoryIdList.get(i)).intValue() != category.getCategoryId()){
						isNameUnique = false;
						break;
					}
				}
			}
			
			if (!isNameUnique) {
				HibernateUtil.closeSession();
				logger.debug("The category name:" + category.getName() + " is duplicated.");
				throw new Exception(LexmarkConstants.DUPLICATED_CATEGORY_NAME);
			}
		} catch (HibernateException ex) {
			logger.debug(ex.getMessage());
			HibernateUtil.closeSession();
			result.setResult(false);
			return result;
		}
//		Integer categoryId = category.getCategoryId();
		category.setType(LexmarkConstants.CATEGORY_TYPE_REPORT);
		List<RoleCategoryLocale> categoryLocaleList = new ArrayList<RoleCategoryLocale>();
		for (RoleCategoryLocale categoryLocale : category.getLocaleList()) {
			if (!StringUtil.isStringEmpty(categoryLocale.getName())) {
				categoryLocaleList.add(categoryLocale);
			}
		}
		category.setLocaleList(categoryLocaleList);
		try {
			HibernateUtil.beginTransaction();
			// generate order number for new category
			if (category.getCategoryId() == null) {
				int maxOrder = 0;
				Query queryMaxOrderNumber = HibernateUtil.getSession().createSQLQuery("select nvl(max(order_num),0) from category where type = 'R' and is_deleted = 'F'");
				List list = queryMaxOrderNumber.list();
				if (list != null && list.size() > 0) {
					maxOrder = ((BigDecimal) list.get(0)).intValue();
				}
				
				category.setOrderNumber(maxOrder + 1);
			} else { // Delete empty category locale
				Set<Integer> ToBeDeletedLocaleSet = new HashSet<Integer>();
				Query queryCategoryLocaleList = HibernateUtil.getSession().createSQLQuery(
						"select category_locale_id from category_locale where category_id = :categoryId");
				queryCategoryLocaleList.setParameter("categoryId", category.getCategoryId());
				List list = queryCategoryLocaleList.list();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i ++) {
 						ToBeDeletedLocaleSet.add(((BigDecimal) list.get(i)).intValue());
					}

					for (RoleCategoryLocale categoryLocale : categoryLocaleList) {
						if (categoryLocale.getCategoryLocaleId() == null) {
							continue;
						}
						Integer categoryLocaleId = Integer.valueOf(categoryLocale.getCategoryLocaleId());
						if (ToBeDeletedLocaleSet.contains(categoryLocaleId)) {
							ToBeDeletedLocaleSet.remove(categoryLocaleId);
						}
					}
					
					for (Integer categoryLocaleId : ToBeDeletedLocaleSet) {
						RoleCategoryLocale categoryLocale = (RoleCategoryLocale)HibernateUtil.getSession().get(RoleCategoryLocale.class, categoryLocaleId);
						HibernateUtil.getSession().delete(categoryLocale);
					}
				}
				
			}
			
			HibernateUtil.getSession().saveOrUpdate(category);
			HibernateUtil.commitTransaction();
			result.setResult(true);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			result.setResult(false);
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	/**
	 * Delete a Category by a given category_id
	 */
	public CategoryDeleteResult deleteCategory(
			CategoryDeleteContract contract) throws Exception {
		CategoryDeleteResult result = new CategoryDeleteResult();
		try {
			// check whether the category has associated valid report definitions
			Query queryReportIds = HibernateUtil.getSession().createSQLQuery(
					SQL_SEARCH_REPORT_BY_CATEGORY_ID);
			queryReportIds.setParameter("categoryId", contract.getCategoryId());
			queryReportIds.setParameter("definitionType", "REPORT");
			List reportIdList = queryReportIds.list();
			if (reportIdList.size() > 0) {
				HibernateUtil.closeSession();
				logger.debug("Category:" + contract.getCategoryId() + " can not be deleted, because valid report definitions are associated.");
				throw new Exception(LexmarkConstants.REPORT_DEFINITION_ASSOCIATED);
			}
			
			RoleCategory categoryToBeDeleted = (RoleCategory) HibernateUtil.
					getSession().get(RoleCategory.class, contract.getCategoryId());
			int orderNumber = categoryToBeDeleted.getOrderNumber();
    		HibernateUtil.beginTransaction();
    		categoryToBeDeleted.setIsDeleted(true);
    		HibernateUtil.getSession().saveOrUpdate(categoryToBeDeleted);
    		HibernateUtil.commitTransaction();
    		
    		// Update order number of other categories
    		Connection conn = null;
    		conn = HibernateUtil.getSession().connection();
    		PreparedStatement stmtUpdateOrderNumber = conn.prepareStatement(
    				"update category set order_num = order_num - 1 where type = ? and order_num > ?");
    		stmtUpdateOrderNumber.setString(1, LexmarkConstants.CATEGORY_TYPE_REPORT);
    		stmtUpdateOrderNumber.setInt(2, orderNumber);
    		stmtUpdateOrderNumber.executeUpdate();
    		stmtUpdateOrderNumber.close();
			conn.commit();
    		
    		result.setSucceed(true);
		} catch (HibernateException ex) {
			result.setSucceed(false);
			logger.error("Failed to delete Report Category:" + contract.getCategoryId(), ex);
			throw new InfrastructureException(ex);
		} finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}

	public SaveReportDefinitionDetailResult saveReportDefinition(
			SaveReportDefinitionDetailContract contract) throws Exception {
		SaveReportDefinitionDetailResult result = new SaveReportDefinitionDetailResult();
		ReportDefinition reportDefinition = contract.getReportDefinition();
		String reportDefinitionId="";
		String localeId= "";
		if(contract.getLocaleId()!=null)
		{
			localeId = contract.getLocaleId();
		}

		// check whether the category name is unique
		try {
			boolean isNameUnique = true;
			Query queryReportDefinition = HibernateUtil.getSession().createSQLQuery(
					"select report_definition_id from report_definition where name = :reportDefinitionName and is_deleted = 'F'");
			queryReportDefinition.setParameter("reportDefinitionName", reportDefinition.getName());
			List reportDefinitionIdList = queryReportDefinition.list();
			
			if (reportDefinitionIdList != null && reportDefinitionIdList.size() > 0) {
				for (int i = 0; i < reportDefinitionIdList.size(); i ++) {
					if (reportDefinition.getId() == null) {
						isNameUnique = false;
						break;
					} else if (((BigDecimal) reportDefinitionIdList.get(i)).intValue() != reportDefinition.getId()){
						isNameUnique = false;
						break;
					}
				}
			}
			
			if (!isNameUnique) {
				HibernateUtil.closeSession();
				logger.debug("The report definition name:" + reportDefinition.getName() + " is duplicated.");
				throw new Exception(LexmarkConstants.DUPLICATED_REPORT_NAME);
			}
		} catch (HibernateException ex) {
			logger.debug(ex.getMessage());
			HibernateUtil.closeSession();
			result.setResult(false);
			return result;
		}
		
		List<DefinitionLocale> localeList = new ArrayList<DefinitionLocale>();
		for (DefinitionLocale definitionLocale : reportDefinition.getLocaleList()) {
			if (!StringUtil.isStringEmpty(definitionLocale.getName())) {
				localeList.add(definitionLocale);
			}
		}
		reportDefinition.setLocaleList(localeList);
		reportDefinition.getRoleCategory().setType(LexmarkConstants.CATEGORY_TYPE_REPORT);
		try {
			HibernateUtil.beginTransaction();
			
			
			if (reportDefinition.getId() != null) { 
				// Delete deprecated definition locale
				Set<Integer> ToBeDeletedLocaleSet = new HashSet<Integer>();
				Query queryDefinitionLocaleList = HibernateUtil.getSession().createSQLQuery("" +
						"select definition_locale_id from definition_locale where report_definition_id = :definitionId");
				queryDefinitionLocaleList.setParameter("definitionId", reportDefinition.getId());
				
				List definitionLocaleList = queryDefinitionLocaleList.list();
				if (definitionLocaleList != null && definitionLocaleList.size() > 0) {
					for (int i = 0; i < definitionLocaleList.size(); i ++) {
						ToBeDeletedLocaleSet.add(((BigDecimal) definitionLocaleList.get(i)).intValue());
					}

					for (DefinitionLocale definitionLocale : reportDefinition.getLocaleList()) {
						if (definitionLocale.getDefinitionLocaleId() == null) {
							continue;
						}
						Integer definitionLocaleId = Integer.valueOf(definitionLocale.getDefinitionLocaleId());
						if (ToBeDeletedLocaleSet.contains(definitionLocaleId)) {
							ToBeDeletedLocaleSet.remove(definitionLocaleId);
						}
					}
					
					for (Integer definitionLocaleId : ToBeDeletedLocaleSet) {
						DefinitionLocale definitionLocale = (DefinitionLocale)HibernateUtil.
								getSession().get(DefinitionLocale.class, definitionLocaleId);
						HibernateUtil.getSession().delete(definitionLocale);
					}
				}
				
				// Delete deprecated report parameters
				Set<Integer> ToBeDeletedParameterSet = new HashSet<Integer>();
				Query queryReportParameterList = HibernateUtil.getSession().createSQLQuery("" +
						"select report_parameter_id from report_parameter where report_definition_id = :reportDefinitionId");
				
				queryReportParameterList.setParameter("reportDefinitionId", reportDefinition.getId());
				
				List list = queryReportParameterList.list();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i ++) {
						ToBeDeletedParameterSet.add(((BigDecimal) list.get(i)).intValue());	// all values from db nelson
					}

					for (ReportParameters reportParameter : reportDefinition.getParameterList()) {
						if (reportParameter.getReportParameterId() == null) {
							continue;
						}
						Integer reportParameterId = Integer.valueOf(reportParameter.getReportParameterId());
						if (ToBeDeletedParameterSet.contains(reportParameterId)) {
							ToBeDeletedParameterSet.remove(reportParameterId);
						}
					}
					
					for (Integer reportParameterId : ToBeDeletedParameterSet) {
						ReportParameters reportParameter = (ReportParameters)HibernateUtil.
								getSession().get(ReportParameters.class, reportParameterId);
						HibernateUtil.getSession().delete(reportParameter);
					}
				}
				
//				code for manipulating and inserting multiple customers
//				added by nelson for CI5 multiple report customers
				Set<Integer> toBeDeletedCustomerSet = new HashSet<Integer>();
				Query queryReportCustomerList = HibernateUtil.getSession().createSQLQuery("" +
						"select report_customer_id from report_customers where report_definition_id = :reportDefinitionId");
				queryReportCustomerList.setParameter("reportDefinitionId", reportDefinition.getId());
				List customerList = queryReportCustomerList.list();
				logger.info("#######rows in db: " + customerList.size());
				if(customerList != null & customerList.size() > 0) {
					for(int i = 0; i < customerList.size(); i ++) {
						toBeDeletedCustomerSet.add(((BigDecimal) customerList.get(i)).intValue());	// fetching all existing customers from db nelson
			}
			
					for(Integer reportCustomerId : toBeDeletedCustomerSet) {
						logger.info("#######cust id is: " + reportCustomerId);
						ReportCustomers reportCustomers = (ReportCustomers)HibernateUtil.getSession().get(ReportCustomers.class, reportCustomerId);
						logger.info("#####deleting customer");
						HibernateUtil.getSession().delete(reportCustomers);
					}
				}
//				end of addition for CI5 multiple customer report
			}
			logger.info("########size of cux list bfr saving: " + reportDefinition.getReportCustomersList().size());
			logger.info("#######saving report defn");
			/* Changes done for (BRD 14-02-14) start*/
//			HibernateUtil.getSession().saveOrUpdate(reportDefinition);
			for(int j=0;j<reportDefinition.getReportCustomersList().size();j++){
				HibernateUtil.getSession().saveOrUpdate(reportDefinition);
			}
			/* Changes done for (BRD 14-02-14) end*/
			HibernateUtil.commitTransaction();
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			result.setResult(false);
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public ReportDefinitionDetailResult retrieveReportDefinitionDetail(
			ReportDefinitionDetailContract contract) {
		ReportDefinitionDetailResult result = new ReportDefinitionDetailResult();
		logger.info("######getting report for id: " + contract.getReportDefinitionId());
		ReportDefinition reportDefinition = null;
		try {
			reportDefinition = (ReportDefinition)HibernateUtil.getSession().get(ReportDefinition.class, contract.getReportDefinitionId());
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
		    HibernateUtil.closeSession();
		}
		result.setReportDefinition(reportDefinition);
		return result;
	}

	/**
	 * Delete a report definition by a given report_definition_id
	 */
	public DeleteReportAdministrationResult deleteReportAdministration(
			DeleteReportAdministrationContract contract) throws Exception {
		DeleteReportAdministrationResult result = new DeleteReportAdministrationResult();
		if (contract.getReportDefinitionId() != null) {
			try {
				ReportDefinition reportToBeDeleted = (ReportDefinition) HibernateUtil.
						getSession().get(ReportDefinition.class, contract.getReportDefinitionId());
        		HibernateUtil.beginTransaction();
        		reportToBeDeleted.setIsDeleted(true);
        		HibernateUtil.getSession().saveOrUpdate(reportToBeDeleted);
        		HibernateUtil.commitTransaction();
        		result.setResult(true);
			} catch (HibernateException ex) {
				result.setResult(false);
				logger.error("Failed to delete Report Definition:" + contract.getReportDefinitionId(), ex);
				throw new InfrastructureException(ex);
			} finally {
			    HibernateUtil.closeSession();
			}
		} else {
			result.setResult(false);
			throw new Exception("The ID of Report Definition to be deleted is not provided.");
		}
		return result;
	}
	
	/**
	 * Swap order number of category, by order number and increment.
	 */
	public SwapCategoryOrderNumberResult swapCategoryOrderNumber(SwapCategoryOrderNumberContract contract) throws Exception {
		SwapCategoryOrderNumberResult result = new SwapCategoryOrderNumberResult();
		Connection conn = null;
		
		try {
			conn = HibernateUtil.getSession().connection();
			String ddlSwapOrderNumber = "update category set order_num = " +
					"case when order_num = ? then order_num + ? " +
					"when order_num = ? + ? then order_num - ? end " +
					"where order_num in (?,? + ?) and type = ? and is_deleted='F'";
			PreparedStatement stmtSwapOrderNumber = conn.prepareStatement(ddlSwapOrderNumber);
			stmtSwapOrderNumber.setInt(1, contract.getOrderNumber());
			stmtSwapOrderNumber.setInt(2, contract.getIncrement());
			stmtSwapOrderNumber.setInt(3, contract.getOrderNumber());
			stmtSwapOrderNumber.setInt(4, contract.getIncrement());
			stmtSwapOrderNumber.setInt(5, contract.getIncrement());
			stmtSwapOrderNumber.setInt(6, contract.getOrderNumber());
			stmtSwapOrderNumber.setInt(7, contract.getOrderNumber());
			stmtSwapOrderNumber.setInt(8, contract.getIncrement());
			stmtSwapOrderNumber.setString(9, contract.getCategoryType());
			
			stmtSwapOrderNumber.executeUpdate();
			stmtSwapOrderNumber.close();
			conn.commit();
			result.setSucceed(true);
		} catch (SQLException ex) {
			result.setSucceed(false);
			logger.error("Failed to update order number of categories.");
			throw new Exception("Failed to update order number of categories.");
		} finally {
			conn.close();
		}
		
		return result;
	}
	
	private void populateCategoryList(List<RoleCategory> categoryList, List resultList) {
		RoleCategory prevCategory = null;
		for(int i = 0; i < resultList.size(); i++) {
			Object[] row = (Object[]) resultList.get(i);
			Integer categoryId = Integer.valueOf(row[0].toString());
			Role role = new Role();
			String roleName = (String) row[3];
			role.setName(roleName);
			if (prevCategory == null || !categoryId.equals(prevCategory.getCategoryId())){
				RoleCategory category = new RoleCategory();
				String categoryName = (String) row[1];
				Integer orderNumber = Integer.valueOf(row[2].toString());
				List<Role> roleList = new ArrayList<Role>();
				roleList.add(role);
				category.setCategoryId(categoryId);
				category.setName(categoryName);
				category.setOrderNumber(orderNumber);
				category.setRoles(roleList);
				categoryList.add(category);
				prevCategory = category;
			} else {
				prevCategory.getRoles().add(role);
			}
		}
	}

	@Override
	public ReportDefinitionNameListResult retrieveReportDefinitionNameList()
			throws Exception {
		ReportDefinitionNameListResult result = new ReportDefinitionNameListResult();
		List<String> reportDefinitionList = new ArrayList<String>();
		//String sql = "select DISTINCT rd.NAME " +
		//		"FROM JOB_RUNLOG  j, REPORT_SCHEDULE  rs, REPORT_DEFINITION rd WHERE j.REPORT_SCHEDULE_ID = rs.REPORT_SCHEDULE_ID AND rs.REPORT_DEFINITION_ID = rd.REPORT_DEFINITION_ID";
		String sql = "select DISTINCT NAME FROM  REPORT_DEFINITION WHERE DEFINITION_TYPE='REPORT' AND IS_DELETED='F' AND NAME IS NOT NULL";
		Query query = HibernateUtil.getSession().createSQLQuery(sql);
		List list = query.list();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				reportDefinitionList.add(list.get(i).toString());
			}
		}
		result.setReportDefinitionNameList(reportDefinitionList);
 		return result;
	}

	@Override
	public ReportInstanceListResult retrieveReportInstanceList(
			ReportInstanceListContract contract) throws Exception {
			ReportInstanceListResult result = new ReportInstanceListResult();
			List<Report> reportInstanceList = new ArrayList<Report>();
			Map<String,String> rJMap = new HashMap<String,String> ();
			String sql = "select j.RUN_DATE_TIME, rs.RECEPIENT_EMAIL, rd.REPORT_DEFINITION_ID, j.STATUS_CODE, j.ERROR_LOG, j.JOB_RUNLOG_ID, rd.name as report_name " +
					"FROM JOB_RUNLOG  j, REPORT_DEFINITION rd,REPORT_SCHEDULE  rs WHERE j.REPORT_SCHEDULE_ID = rs.REPORT_SCHEDULE_ID AND rs.REPORT_DEFINITION_ID = rd.REPORT_DEFINITION_ID AND rd.DEFINITION_TYPE='REPORT'";

			sql = sql + " And j.RUN_DATE_TIME BETWEEN :startDate And :endDate ";

			SQLQuery runLogIDs = HibernateUtil.getSession().createSQLQuery(sql);
			runLogIDs.addScalar("RUN_DATE_TIME", Hibernate.TIMESTAMP);
			runLogIDs.addScalar("RECEPIENT_EMAIL");
			runLogIDs.addScalar("REPORT_DEFINITION_ID");
			runLogIDs.addScalar("STATUS_CODE");

			runLogIDs.addScalar("ERROR_LOG");
			runLogIDs.addScalar("JOB_RUNLOG_ID");
			runLogIDs.addScalar("report_name");
			
			runLogIDs.setParameter("startDate", contract.getStartDate());
			runLogIDs.setParameter("endDate", contract.getEndDate());
			List list = runLogIDs.list();
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[]) list.get(i);
					Report report = new Report();
					report.setRunTime((Date) row[0]);
					report.setUserEmail((String)row[1]);
					
					report.setStatusCode((String)row[3]);
					report.setErrorMessage((String)row[4]);
					report.setJobRunLogId(row[5].toString());
					DocumentDefinition documentDefinition = new DocumentDefinition();
					documentDefinition.setName((String)row[6]);
					documentDefinition.setId(Integer.valueOf(row[2].toString()));
					report.setDefinition(documentDefinition);
					
					if(!"".equals(contract.getReportDefinitionName())&&contract.getReportDefinitionName()!=null){
						if(contract.getReportDefinitionName().equals((String)row[6])){
							reportInstanceList.add(report);
						}
					}else{
						reportInstanceList.add(report);
					}
					rJMap.put(row[2].toString(), row[5].toString());
				}
			}
			result.setReportInstanceList(reportInstanceList);
			result.setRJMap(rJMap);
	 		return result;
		}

	@Override
	public Map<String,String> retrieveRunLogParameterList(
			int scheduleId) throws Exception {
		Map<String,String> logParameters = new HashMap<String,String> ();
		//String sql = "select rp.NAME, rp.VALUE " +
			//	"FROM REPORT_INSTANCE_PARAMETERS rp WHERE rp.JOB_RUNLOG_ID = "+runLogID;
		
		String sql = "select NAME, VALUE " +
			"FROM schedule_instance_parameters  WHERE report_schedule_id = "+scheduleId;
	
		Query query = HibernateUtil.getSession().createSQLQuery(sql);
		List list = query.list();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				Object[] row = (Object[]) list.get(i);
				logParameters.put((String)row[0], (String)row[1]);
			}
		}
		return logParameters;
	}

	@Override
	public boolean rerunReport(String definitionId) throws Exception {
		String sql = "update REPORT_SCHEDULE r SET r.RUN_FREQUENCY='O', r.SCHEDULE_EFFECTIVE_DATE = SYSDATE  WHERE r.REPORT_DEFINITION_ID="+definitionId;
		boolean result = false;
		try {
			HibernateUtil.getSession().beginTransaction();
			int updateCount = HibernateUtil.getSession().createSQLQuery(sql).executeUpdate();
			HibernateUtil.commitTransaction();
			if (updateCount>0) {
				result = true;
			} else {
				result = false;
			}
		} catch (HibernateException e) {
			result = false;
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	@Override
	public void deleteReports(String[] reportIds) throws Exception {
		try{
			HibernateUtil.beginTransaction();
			for(String reportId : reportIds){
				Query query = HibernateUtil.getSession().createQuery("from ReportDeleteStatus where reportId = :reportId");
				query.setParameter("reportId", reportId);
				List list = query.list(); 
				if(list != null && list.size() > 0) {
					ReportDeleteStatus deleteStatus = (ReportDeleteStatus)list.get(0);
					deleteStatus.setIsDeleted(Boolean.TRUE);
					HibernateUtil.getSession().saveOrUpdate(deleteStatus);
				}
				else{
					ReportDeleteStatus deleteStatus = new ReportDeleteStatus();
					deleteStatus.setReportId(reportId);
					deleteStatus.setIsDeleted(Boolean.TRUE);
					HibernateUtil.getSession().save(deleteStatus);
				}
			}
			HibernateUtil.commitTransaction();
		}
		catch(HibernateException e){
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			throw e;
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
	//Started addition for OBIEE report
	@Override
	public ObieeReportDefinitionResult getObieeReportDefinitionByDefinitionId(String definitionId) throws Exception {
		ObieeReportDefinitionResult result = new ObieeReportDefinitionResult();
		
		Query query;
		try {
			query = HibernateUtil.getSession().createSQLQuery(SQL_GET_OBIEE_REPORT_DEFINITION_BY_DEFINITION_ID);
			logger.debug("!!!!!!!!!definition id is in CustomerReportServiceImpl : "+definitionId);
			query.setParameter("definitionId", definitionId);
			List list = query.list();
			if(list != null && list.size() > 0) {
				Object[] row = (Object[])list.get(0);
				ReportDefinition reportDefinition = new ReportDefinition();
				reportDefinition.setReportSourceId((String)row[0]);
				reportDefinition.setIsSendMDMParameter("T".equalsIgnoreCase(row[1].toString()));
				logger.debug("!!!!!!!!! values are "+(String)row[0]+" "+row[1].toString());
				result.setReportDefinition(reportDefinition);
			} else {
				logger.debug("Definition not found");
				logger.info("Definition not found");
			}
		}catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}
	
	@Override
	public ObieeReportParameterListResult getObieeReportParameterList(ObieeReportParameterListContract contract) throws Exception {
		ObieeReportParameterListResult result = new ObieeReportParameterListResult();
		
		Query query;
		try {
			query = HibernateUtil.getSession().createSQLQuery(SQL_GET_OBIEE_REPORT_PARAMETERS_BY_DEFINITION_ID);
			logger.debug("!!!!!!!!!definition id is in CustomerReportServiceImpl : "+contract.getReportDefinitionId());
			logger.debug("!!!!!!!!!contact id is in CustomerReportServiceImpl : "+contract.getContactId());
			query.setParameter("contactId", contract.getContactId());
			query.setParameter("definitionId", contract.getReportDefinitionId());
			List list = query.list();
			List<ObieeReportParameter> obieeReportParameterList = new ArrayList<ObieeReportParameter>();
			if(list != null && list.size() > 0) {
				BigDecimal maxSize;
				for(int i = 0; i < list.size(); i++){
					Object[] row = (Object[])list.get(i);
					ObieeReportParameter obieeReportParameter = new ObieeReportParameter();
					obieeReportParameter.setParameterType((String)row[0]);
					obieeReportParameter.setParameterName((String)row[1]);
					obieeReportParameter.setDisplayName((String)row[2]);
					obieeReportParameter.setParameterRequired("T".equalsIgnoreCase(row[3].toString()));
					obieeReportParameter.setParameterValue((String)row[4]);
					obieeReportParameter.setParameterValue2((String)row[5]);
					obieeReportParameter.setReportParameterId(Integer.valueOf(row[6].toString()));
					obieeReportParameter.setListOptions(getListOptions((String)row[7]));
					maxSize = (BigDecimal)row[8];
					obieeReportParameter.setMaxSize((maxSize == null) ? -1 : maxSize.intValue());
					logger.debug("!!!!!!!!! values are "+(String)row[0]+" "+(String)row[1]+" "+(String)row[2]+" "+row[3].toString()+" "+(String)row[4]+" "+(String)row[5]);
					obieeReportParameterList.add(obieeReportParameter);
				}
				result.setObieeReportParameterList(obieeReportParameterList);
			} else {
				logger.debug("Definition not found");
				logger.info("Definition not found");
			}
		}catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}
	
	private List<ReportParameterListValue> getListOptions(String strOptions) {
		List<ReportParameterListValue> listOptions = new ArrayList<ReportParameterListValue>(0);
		
		if (StringUtil.isStringEmpty(strOptions)) {
			return listOptions;
		}
		String[] pairs = strOptions.split(",");
		for(String pair : pairs){
			String[] nameAndValue = pair.split("=");
			ReportParameterListValue listValue = new ReportParameterListValue();
			listValue.setValue(nameAndValue[0]);
			if(nameAndValue.length > 1)
				listValue.setLabel(nameAndValue[1]);
			else
				listValue.setLabel(nameAndValue[0]);
			listOptions.add(listValue);
		}
		
		return listOptions;
	}
	
	public void saveObieeReportParameter(ObieeReportParameterContract contract) throws Exception{
		try{
			HibernateUtil.beginTransaction();
			logger.debug("--------------------------starting saveObieeReportParameter---------------------------");
			logger.debug(" @@@@@@@"+contract.getObieeReportParameter().getParameterName()+" "+contract.getObieeReportParameter().getParameterValue()+" "+contract.getObieeReportParameter().getReportParameterId()+" "+contract.getObieeReportParameter().getContactId());
			HibernateUtil.getSession().saveOrUpdate(contract.getObieeReportParameter());
			logger.debug("--------------------------database transaction successful------------------------------");
			HibernateUtil.commitTransaction();
			logger.debug("-------------------------ending saveObieeReportParameter------------------------------");
		}catch (Exception e) {
			logger.debug("Got exception in the saveObieeReportParameter method");
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void saveObieeImpersonateData(ObieeImpersonateContract contract) throws Exception{
		try{
			HibernateUtil3.beginTransaction();
			logger.debug("--------------------------starting saveObieeImpersonateData---------------------------");
			HibernateUtil3.getSession().saveOrUpdate(contract.getObieeImpersonate());
			logger.debug("--------------------------database transaction successful------------------------------");
			HibernateUtil3.commitTransaction();
			logger.debug("-------------------------ending saveObieeImpersonateData------------------------------");
		}catch (Exception e) {
			logger.debug("Got exception in the saveObieeImpersonateData method");
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil3.closeSession();
		}
		
	}
	//Completed addition for OBIEE report
		
	/* Added for (BRD 14-02-14) */	
	
	public ArrayList getL4AccountForL3ReportsAdmin(String searchName){
		StringBuffer sb = new StringBuffer();
        StringBuffer sbMdmAcctId = new StringBuffer();
        ArrayList accountlist = new ArrayList();
        sb.append(" SELECT ");
        sb.append(companyDAId + ", " + companyLegalName + " , ");
        sb.append(companyType + ", " + companyRole + " , ");
        sb.append(companyLegalNo + ", " + companyGlobalNo + " , ");
        sb.append(companyDomesticNo);
        sb.append(" FROM ");
        sb.append(companyDetail_table_Name);
        sb.append(" WHERE ");
        sb.append(companyLegalNo + "='" + searchName + "'");
        sb.append(" AND ");
        sb.append(companyStatus + "='A'");
        
        try {
			Query query = HibernateUtil.getSession().createSQLQuery(sb.toString());
			List list = query.list();
			
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++){
					CompanyMdmAssociation cmbean = new CompanyMdmAssociation();
					Object[] L4Account = (Object[]) list.get(i);
					String companyDAId = (String)L4Account[0];
					String companyLegalName = (String)L4Account[1];
					String companyType = (String)L4Account[2];
					String companyRole = (String)L4Account[3];
					String companyLegalNo = (String)L4Account[4];
					String companyGlobalNo = (String)L4Account[5];
					String companyDomesticNo = (String)L4Account[6];
					cmbean.setCompanyDAId(companyDAId);
					cmbean.setCompanyLegalName(companyLegalName);
					cmbean.setCompanyType(companyType);
					cmbean.setCompanyRole(companyRole);
					cmbean.setCompanyLegalNo(companyLegalNo);
					cmbean.setCompanyGlobalNo(companyGlobalNo);
					cmbean.setCompanyDomesticNo(companyDomesticNo);
					logger.debug("Domestic :: "+cmbean.getCompanyDomesticNo());
					accountlist.add(cmbean);		
					
				}
			}			
        } catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
        return accountlist;
			
	}
	
	public ArrayList getMdmAccountForReportAdmin(String searchName){
		StringBuffer sb = new StringBuffer();
        StringBuffer sbMdmAcctId = new StringBuffer();
        ArrayList accountlist = new ArrayList();
        sb.append(" SELECT ");
        sb.append(companyDAId + ", " + companyLegalName + " , ");
        sb.append(companyType + ", " + companyRole + " , ");
        sb.append(companyLegalNo + ", " + companyGlobalNo + " , ");
        sb.append(companyDomesticNo);
        sb.append(" FROM ");
        sb.append(companyDetail_table_Name);
        sb.append(" WHERE ");
        sb.append(companyDAId + "='" + searchName + "'");
        sb.append(" AND ");
        sb.append(companyStatus + "='A'");
        try {
			Query query = HibernateUtil.getSession().createSQLQuery(sb.toString());
			List list = query.list();
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++){
					CompanyMdmAssociation cmbean = new CompanyMdmAssociation();
					Object[] L4Account = (Object[]) list.get(i);
					String companyDAId = (String)L4Account[0];
					String companyLegalName = (String)L4Account[1];
					String companyType = (String)L4Account[2];
					String companyRole = (String)L4Account[3];
					String companyLegalNo = (String)L4Account[4];
					String companyGlobalNo = (String)L4Account[5];
					String companyDomesticNo = (String)L4Account[6];
					cmbean.setCompanyDAId(companyDAId);
					cmbean.setCompanyLegalName(companyLegalName);
					cmbean.setCompanyType(companyType);
					cmbean.setCompanyRole(companyRole);
					cmbean.setCompanyLegalNo(companyLegalNo);
					cmbean.setCompanyGlobalNo(companyGlobalNo);
					cmbean.setCompanyDomesticNo(companyDomesticNo);
					logger.debug("Domestic :: "+cmbean.getCompanyDomesticNo());
					accountlist.add(cmbean);		
				}
			}
        } catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
        return accountlist;			
	}
	
	
	
	public ArrayList getTreeForReportAdmin(String searchedCompString){
		StringBuffer sb = new StringBuffer();
        ArrayList treelist = new ArrayList();

        String editUserMDMID = "";
        String editUserMDMLeveName = "";
        String editUserLevelName_MDMID = "";
                
        sb.append(" SELECT DISTINCT ");
        sb.append(companyGlobalNo + ", " + companyGlobalName + " , ");
        sb.append(companyDomesticNo + ", " + companyDomesticName + " , ");
        sb.append(companyLegalNo + "," + companyLegalName);
        sb.append(" FROM ");
        sb.append(companyDetail_table_Name);
        sb.append(" WHERE ");
        sb.append(" ROWNUM < 250 AND ( ");
        sb.append("( UPPER(COMPANY_MDM_GLOBAL_ULT_NAME) ");
        sb.append("LIKE UPPER('%" + searchedCompString + "%') ");
        sb.append("AND COMPANY_MDM_GLOBAL_ULT_NO IS NOT NULL AND COMPANY_MDM_DOMESTIC_ULT_NO IS NOT NULL AND COMPANY_MDM_ID IS NOT NULL) ");
        sb.append("OR (UPPER(COMPANY_LEGAL_NAME) ");
        sb.append("LIKE UPPER('%" + searchedCompString + "%') ");
        sb.append("AND COMPANY_MDM_GLOBAL_ULT_NO IS NULL AND COMPANY_MDM_DOMESTIC_ULT_NO IS NULL ");
        sb.append("AND COMPANY_MDM_GLOBAL_ULT_NAME IS NULL AND COMPANY_MDM_DOMESTIC_ULT_NAME IS NULL) ");
        sb.append(" ) ORDER BY COMPANY_MDM_GLOBAL_ULT_NO,COMPANY_MDM_DOMESTIC_ULT_NO,COMPANY_MDM_ID");
                
               
        try {
			Query query = HibernateUtil.getSession().createSQLQuery(sb.toString());
			List list = query.list();
			if(list != null && list.size() > 0) {
				for(int i = 0; i < list.size(); i++){
					CompanyTreeBean ctbean = new CompanyTreeBean();
					Object[] row = (Object[]) list.get(i);				
					String gbultno = (String)row[0];	                
	                String gbultCheckNo = gbultno;	                
	                String dmultno = (String)row[2];	                
	                String legalno = (String)row[4];	                
	                if (gbultno != null) {
	                    ctbean.setCompany_global_ult_no(gbultno);
	                    ctbean.setCompany_global_ult_name((String)row[1]);
	                    if (dmultno != null) {
	                        ctbean.setCompany_domestic_ult_no(dmultno);
	                        ctbean.setCompany_domestic_ult_name((String)row[3]);
	                    }
	                    ctbean.setlevelName("GLOBAL");
	                    ctbean.setCompany_legal_mdm_id(legalno);
	                    ctbean.setCompany_legal_mdm_name((String)row[5]);
	                    ctbean.setChildPresent(true);

	                } else {
	                    gbultno = (String)row[4];
	                    ctbean.setlevelName("LEGAL");
	                    ctbean.setCompany_global_ult_no(gbultno);
	                    ctbean.setCompany_global_ult_name((String)row[5]);

	                }
	                treelist.add(ctbean);
				}
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
        return treelist;
	}
	/* End */
	/*This function fetches all the report parameters list while updating or adding new
	parameters--Serves the purpose of persisting display names for different locales in the DB
	CI-7 BRD 13-10-27*/
	public List getReportParametersList
	       (ReportDefinition reportDefinition) throws Exception {
		List reportParamsList=null;
		try{
			Query query = 
		    HibernateUtil.getSession().createSQLQuery(SQL_GET_PARAMETERS_ToUpdate_BY_DEFINITION_ID_);
			query.setParameter("definitionId", reportDefinition.getId());
			reportParamsList = query.list();
			
			if(reportParamsList != null && reportParamsList.size() > 0) {
				logger.debug("Report Parameters are fetched from database------");
				logger.debug("Report Parameters Size-->>"+reportParamsList.size());
			}
		}
		catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return reportParamsList;
	}
	/*
	 * @author: CI-7 Relese 14.02 BRD 13-10-27
	 * (non-Javadoc)
	 */
	public List<ReportParameters> getReportParametersList_showDefinition
    (ReportDefinition reportDefinition) throws Exception {
		List<ReportParameters> paramsList = new ArrayList<ReportParameters>();
	
	try{
		Query query = 
	    HibernateUtil.getSession().createSQLQuery(SQL_GET_PARAMETERS_ToUpdate_BY_DEFINITION_ID_);
		query.setParameter("definitionId", reportDefinition.getId());
		List list = query.list();
		
		for(int i = 0; i < list.size(); i++){
			ReportParameters rp = new ReportParameters();
			Object[] row = (Object[]) list.get(i);
			rp.setDisplayName((String)row[2]);
			rp.setDisplayNameSpanish((String)row[3]);
			rp.setDisplayNameGerman((String)row[4]);
			rp.setDisplayNameFrench((String)row[5]);
			rp.setDisplayNameChina((String)row[6]);
			rp.setDisplayNameChinaTw((String)row[7]);
			rp.setDisplayNamePortugalBrazil((String)row[8]);
			rp.setDisplayNamePortugal((String)row[9]);
			rp.setDisplayNameItaly((String)row[10]);
			rp.setDisplayNameKorea((String)row[11]);
			rp.setDisplayNameRussia((String)row[12]);
			rp.setDisplayNameJapan((String)row[13]);
			rp.setDisplayNameTurkey((String)row[14]);
			paramsList.add(rp);
		}
	}
	catch (HibernateException ex) {
		throw new InfrastructureException(ex);
	}finally {
	    HibernateUtil.closeSession();
	}
	return paramsList;
}
	
}