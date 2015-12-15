package com.lexmark.service.api;

import java.util.List;
import java.util.Map;

import com.lexmark.contract.CategoryAdministrationDetailContract;
import com.lexmark.contract.CategoryAdministrationListContract;
import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.DeleteReportAdministrationContract;
import com.lexmark.contract.ObieeImpersonateContract;
import com.lexmark.contract.ObieeReportParameterListContract;
import com.lexmark.contract.ObieeReportParameterContract;
import com.lexmark.contract.ReportDefinitionDetailContract;
import com.lexmark.contract.ReportDefinitionDisplayContract;
import com.lexmark.contract.ReportHierarchyContract;
import com.lexmark.contract.ReportInstanceListContract;
import com.lexmark.contract.ReportListContract;
import com.lexmark.contract.SaveCategoryAdministrationDetailContract;
import com.lexmark.contract.SaveReportDefinitionDetailContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.contract.SwapCategoryOrderNumberContract;
import com.lexmark.domain.Report;
import com.lexmark.domain.ReportDefinition;
import com.lexmark.domain.ReportParameters;
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
import com.lexmark.result.ReportHierarchyResult;
import com.lexmark.result.ReportInstanceListResult;
import com.lexmark.result.ReportListResult;
import com.lexmark.result.ReportParamsResult;
import com.lexmark.result.SaveCategoryAdministrationDetailResult;
import com.lexmark.result.SaveReportDefinitionDetailResult;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.result.SwapCategoryOrderNumberResult;

public interface CustomerReportService {
	public ReportAdministrationListResult retrieveReportAdministrationList()
			throws Exception;
	
	public ReportHierarchyResult retrieveReportHierarchy(ReportHierarchyContract contract)
			throws Exception;
	
	public ReportListResult retrieveTopTenReportList(ReportListContract contract)
			throws Exception;
	
	public ReportListResult retrieveTopTenReportListEmployee(ReportListContract contract)  // added by nelson
			throws Exception;
	
	public ReportListResult retrieveManuUploadedReportsList(ReportListContract contract) 
			throws Exception;
	
	public CategoryAdministrationListResult retrieveCategoryAdministrationList(
			CategoryAdministrationListContract contract) throws Exception;
	
	public CategoryAdministrationDetailResult retrieveCategoryAdministrationDetail(
			CategoryAdministrationDetailContract contract) throws Exception;
	
	public SaveCategoryAdministrationDetailResult saveCategoryAdministrationDetail(
			SaveCategoryAdministrationDetailContract contract) throws Exception;

	public DeleteReportAdministrationResult deleteReportAdministration(
			DeleteReportAdministrationContract contract) throws Exception;

	public ReportDefinitionDisplayResult getReportDefinitionDisplay(ReportDefinitionDisplayContract contract);

	public ReportListResult retrieveScheduledReportsList(
			ReportListContract contract) throws Exception;
	
	public ReportListResult retrieveScheduledReportsListEmployee(ReportListContract contract) 		// added by nelsons
			throws Exception;

//	public ReportDefinitionParameterResult retrieveParametersByDefinitionId(		// commented by nelsons
	public ReportParamsResult retrieveParametersByDefinitionId(						// added by nelsons
			String definitionId, String userNumber, String mdmId) throws Exception; // added mdmId by nelson for employee report

	public ScheduleReportResult saveReportSchedule(
			ScheduleReportContract contract) throws Exception;

	public SaveReportDefinitionDetailResult saveReportDefinition(
			SaveReportDefinitionDetailContract contract) throws Exception;
	//Added for CI BRD 13-10-27 --STARTS
	public List getReportParametersList
    (ReportDefinition reportDefinition) throws Exception;
	
	public List<ReportParameters> getReportParametersList_showDefinition
    (ReportDefinition reportDefinition) throws Exception;
	//Added for CI BRD 13-10-27 --ENDS

	public ReportDefinitionDetailResult retrieveReportDefinitionDetail(
			ReportDefinitionDetailContract contract) throws Exception;

	public CategoryDeleteResult deleteCategory(CategoryDeleteContract contract) throws Exception;

	public SwapCategoryOrderNumberResult swapCategoryOrderNumber(
			SwapCategoryOrderNumberContract contract) throws Exception;

	public Report retrieveReportById(String reportId, List<String> userRoles) throws Exception;
	
	public ReportDefinitionNameListResult retrieveReportDefinitionNameList() throws Exception;
	
	public ReportInstanceListResult retrieveReportInstanceList(
			ReportInstanceListContract contract) throws Exception;

	public Map<String,String> retrieveRunLogParameterList(int runLogID) throws Exception;
	
	public Report retrieveReportByDocumentID(String reportId, List<String> userRoles) throws Exception;

	public boolean rerunReport(String definitionId)throws Exception;

	public void deleteReports(String[] reportIds)throws Exception;
	
	//Started addition for OBIEE report
	public ObieeReportDefinitionResult getObieeReportDefinitionByDefinitionId(String definitionId) throws Exception;
	
	public ObieeReportParameterListResult getObieeReportParameterList(ObieeReportParameterListContract contract) throws Exception;
	
	public void saveObieeReportParameter(ObieeReportParameterContract contract) throws Exception;
	
	public void saveObieeImpersonateData(ObieeImpersonateContract contract) throws Exception;
	
	//Completed addition for OBIEE report

	
}
