package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.CategoryHierarchyContract;
import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.contract.DocumentUserListContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.domain.Document;
import com.lexmark.domain.RoleCategory;
import com.lexmark.result.CategoryHierarchyResult;
import com.lexmark.result.DocumentUserListResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.service.api.DocumentManagementService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.DocumentumWebServiceUtil;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusUtil;

@Controller
@RequestMapping( { "VIEW" })
public class DocumentUserController extends BaseController{
	private static final Logger logger = LogManager.getLogger(DocumentUserController.class);
	
	
	@Autowired
	private DocumentManagementService documentManagementService;
	
	@Autowired
	private ReportScheduleService reportScheduleService;

	
	@Autowired
	private GlobalService globalService;
	
	@Resource
	private List<GeographyListResult> allCountryList;

	@RequestMapping("VIEW")
	public String defaultPage(@RequestParam(value = "path", required = false) String path, Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		PortletSession portletSession = request.getPortletSession();
		String mdmId = PortalSessionUtil.getMdmId(portletSession);
		String mdmLevel = PortalSessionUtil.getMdmLevel(portletSession);
		List<String> roles = LexmarkUserUtil.getUserRoleNameList(request);
		//Countries
		List<String> countriesList = LexmarkUserUtil.getCountriesList(request);
		for(int i =0; i<countriesList.size();i++){
			logger.debug("countriesList "+countriesList.get(i));
		}
		//Partner Types
		List<String> partnerTypes = LexmarkUserUtil.getPartnerTypeList(request);
		for(int i =0; i<partnerTypes.size();i++){
			logger.debug("partnerTypes "+partnerTypes.get(i));
		}
		
		logger.debug(mdmId + " --- " + mdmLevel + " --- " + roles);
		logger.debug(request.getLocale().getLanguage()+"|"+request.getLocale().getCountry()+"|"+request.getLocale().getDisplayName());
		boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		model.addAttribute("isPublishing",isPublishing);
		setRequestType(response.createRenderURL(), request);
		List<RoleCategory> hierarchy = retrieveCategoryHierarchy(request);
		//List<Integer> categoryId = retrieveCategoryId(request);
		
		model.addAttribute("hierarchy",hierarchy);
		return "documentManagement/user/documentUser";
	}
	
	private void setRequestType(PortletURL query,PortletRequest request){
		 logger.debug("[in set request type]");
		String queryString = query.toString();
		
		if(queryString.indexOf("/partner-portal")!=-1)
		{
			request.setAttribute("partnerPortal", true);
			logger.debug("setting partner portal true");
		}else if (queryString.indexOf("/global-services")!=-1){
			logger.debug("setting services portal true");
			request.setAttribute("servicesPortal", true);
		}
		logger.debug("[out set request type]");
	}

	@ResourceMapping("categoryTreeXML")
	public void categoryTreeXML(ResourceRequest request,
			ResourceResponse response) throws IOException {
		try {
			setRequestType(response.createRenderURL(), request);
			List<RoleCategory> hierarchy = retrieveCategoryHierarchy(request);
			
			
			String treeXML =  getXmlOutputGeneratorForDocument(request).generateDocumentCategoryTree(hierarchy);
			response.setContentType("text/xml");
			OutputStream portletOutputStream = response.getPortletOutputStream();
			
			portletOutputStream.write(treeXML.getBytes());
		} catch (IOException e) {
			
			throw e;
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.tree", request.getLocale());
		}
	}

	private List<RoleCategory> retrieveCategoryHierarchy(PortletRequest request) throws Exception {
		PortletSession portletSession = request.getPortletSession();
		List<String> roles = LexmarkUserUtil.getUserRoleNameList(request);
		
		// added for BRD 14-07-04
		List<String> countriesList = LexmarkUserUtil.getCountriesList(request);
		List<GeographyCountryContract> countryList=allCountryList.get(0).getCountryList();
		List<String> countriesCode=new LinkedList<String>();
		for(String country:countriesList){
			for(GeographyCountryContract countryContract:countryList){
				if(country.equalsIgnoreCase(countryContract.getCountryName())){
					countriesCode.add(countryContract.getCountryCode());
					break;
				}
			}
		}
		logger.debug(" country codes is "+countriesCode);
		
		
		List<String> partnerTypes = LexmarkUserUtil.getPartnerTypeList(request);

		CategoryHierarchyContract contract = ContractFactory.getCategoryHierarchyContract(request, roles, partnerTypes , countriesCode , request
				.getLocale(), globalService);
		CategoryHierarchyResult result = documentManagementService.retrieveCategoryHierarchy(contract);

		List<RoleCategory> hierarchy = result.getCategoryHierarchy();
		return hierarchy;
	}

	@ResourceMapping("documentUserListXML")
	public void documentUserListXML(@RequestParam("definitionId") int definitionId, ResourceRequest request, ResourceResponse response)
			throws IOException {

		try {
			DocumentUserListContract contract = ContractFactory.getDocumentUserListContract(definitionId);
			DocumentUserListResult result = documentManagementService.retrieveDocumentUserList(contract);
			List<Document> documentList = result.getDocumentList();
			boolean canDeleteFiles = getCanDeleteDocFlag(request, definitionId);
			String gridXML = getXmlOutputGeneratorForDocument(request).generateDocumentUserListGridXML(documentList , canDeleteFiles);
			logger.debug(gridXML);
			response.setContentType("text/xml");
			OutputStream portletOutputStream = response.getPortletOutputStream();
			portletOutputStream.write(gridXML.getBytes());
		} catch (IOException e) {
			logger.debug("IO Exception "+e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.tree", request.getLocale());
		}
	}
	@ResourceMapping("deleteDocument")
	public void deleteDocument(@RequestParam("id") String id, ResourceRequest request, ResourceResponse response)
			throws IOException {

		try {
			DocumentDeleteContract contract = ContractFactory.getDocumentDeleteContract(id);
			documentManagementService.handleDocumentDelete(contract );
			ServiceStatusUtil.responseResult(response, "message.documentManagement.deleteDocumentName", request.getLocale());
			logger.debug("deleted document id = "+id);
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.tree", request.getLocale());
		}
	}
	
	/*@RequestMapping(params = "action=handleDocumentUpload")
	public void handleDocumentUpload(@RequestParam("definitionId") int definitionId,
			@RequestParam("fileContent") CommonsMultipartFile content, Model model, ActionRequest request, ActionResponse response) {

		try {
			logger.debug("******DocumentUser upload file to report : " + definitionId);
			String fileUniqueId = reportScheduleService.getReportUniqueId().toString();
			String fileName = content.getOriginalFilename();
			Date fileContentDate = new Date();
			DocumentInfo docInfo = DocumentumWebServiceUtil.getDocumentInfo(request, fileName, "" + definitionId, fileUniqueId, DocumentumWebServiceUtil.DOCUMENT_TYPE_DOCUMENT,  globalService, fileContentDate);
			DocumentumWebServiceFacade facade = DocumentumWebServiceUtil.getDocumentumWebServiceFacade();
			try {
				facade.createDocument(docInfo, content.getBytes());
				model.addAttribute("uploadSuccess", true);
				logger.debug("******successfully upload file to report : " + definitionId);
			}catch(RuntimeException runtimeException) {
				if(!DocumentumWebServiceUtil.isFileFormatSupported(runtimeException)) {
					model.addAttribute("uploadFileNotSupported", true);
					logger.debug("******fail to upload file to report : " + definitionId);
					logger.debug(runtimeException.getCause().getMessage());
				} else {
					logger.debug("Exception "+runtimeException.getMessage());
					model.addAttribute("uploadFail", true);
				}
			}
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			model.addAttribute("uploadFail", true);
		}
		model.addAttribute("definitionId", definitionId);
	}*/

	
	private boolean getCanDeleteDocFlag(ResourceRequest request, int definitionId) throws Exception{
		boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		boolean canDeleteDoc = (definitionId==-1? false:true);
		if(!isPublishing) {
			canDeleteDoc = false;
		}
		return canDeleteDoc;
	}
}