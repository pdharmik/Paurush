package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.InputStream;
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
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.CategoryHierarchyContract;
import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.contract.DocumentUserListContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.domain.Document;
import com.lexmark.domain.RoleCategory;
import com.lexmark.emc.client.servicesweb.DocumentumWebServiceFacade;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.result.CategoryHierarchyResult;
import com.lexmark.result.DocumentUserListResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.service.api.DocumentManagementService;
import com.lexmark.service.api.GeographyService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.services.reports.bean.DocDelDownloadInfo;
import com.lexmark.services.reports.bean.DocListInfo;
import com.lexmark.services.reports.bean.DocumentInformation;
import com.lexmark.services.reports.bean.DocumentBean;
import com.lexmark.services.reports.service.LexmarkReportService;
import com.lexmark.services.reports.service.LexmarkReportServiceImpl;
import com.lexmark.services.reports.util.LexmarkReportUtil;
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
	
	 @Autowired 
	private GeographyService geographyService; 
	
	@Resource
	private List<GeographyListResult> allCountryList;

	/**
	 * @param path 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 * @throws Exception 
	 */
	@RequestMapping("VIEW")
	public String defaultPage(@RequestParam(value = "path", required = false) String path, Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		logger.debug("in defaultPage fo document library");
		try{
			logger.debug("in defaultPage fo document library try");
			PortletSession portletSession = request.getPortletSession();
			String mdmId = PortalSessionUtil.getMdmId(portletSession);
			logger.debug("in defaultPage fo document library try mdm id is "+mdmId);
			String mdmLevel = PortalSessionUtil.getMdmLevel(portletSession);
			logger.debug("in defaultPage fo document library try mdmLevel is "+mdmLevel);
			List<String> roles = LexmarkUserUtil.getUserRoleNameList(request);
			logger.debug("role list size is "+roles.size());
			for(int i=0; i<roles.size();i++){
				logger.debug("role name is "+roles.get(i));
			}
			//Countries
		//	List<String> countriesList = LexmarkUserUtil.getCountriesList(request);
			//logger.debug("countriesList size is "+countriesList.size());
		/*	for(int i =0; i<countriesList.size();i++){
				logger.debug("countriesList "+countriesList.get(i));
			}*/
			//Partner Types
			List<String> partnerTypes = LexmarkUserUtil.getPartnerTypeList(request);
			logger.debug("partnerTypes size is "+partnerTypes.size());
			for(int i =0; i<partnerTypes.size();i++){
				logger.debug("partnerTypes "+partnerTypes.get(i));
			}
			
			logger.debug(mdmId + " --- " + mdmLevel + " --- " + roles);
			logger.debug(request.getLocale().getLanguage()+"|"+request.getLocale().getCountry()+"|"+request.getLocale().getDisplayName());
			boolean isPublishing = LexmarkUserUtil.isPublishing(request);
			logger.debug("isPublishing "+isPublishing);
			model.addAttribute("isPublishing",isPublishing);
			setRequestType(response.createRenderURL(), request);
			List<RoleCategory> hierarchy = retrieveCategoryHierarchy(request);
			logger.debug("hierarchy size is "+hierarchy.size());
			for(int i=0;i<hierarchy.size();i++){
				logger.debug("hierarchy is "+hierarchy.get(i));
			}
			//List<Integer> categoryId = retrieveCategoryId(request);
			
			model.addAttribute("hierarchy",hierarchy);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.debug("======================in catch block=====================");
			e.printStackTrace();
		}
		return "documentManagement/user/documentUser";
	}
	
	/**
	 * @param query  
	 * @param request 
	 */
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

	/**
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
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

	
	/**
	 * @param request  
	 * @return List<RoleCategory>  
	 * @throws Exception  
	 */
	private List<RoleCategory> retrieveCategoryHierarchy(PortletRequest request) throws Exception {
		logger.debug("in retrieveCategoryHierarchy");
		List<RoleCategory> hierarchy = new ArrayList<RoleCategory>();
		try{
		PortletSession portletSession = request.getPortletSession();
		List<String> roles = LexmarkUserUtil.getUserRoleNameList(request);
		logger.debug(" roles size in retrieveCategoryHierarchy "+roles.size());
		for(int i=0;i<roles.size();i++){
			logger.debug("roles is "+roles.get(i));
		}
		// added for BRD 14-07-04
		List<String> countriesList = LexmarkUserUtil.getCountriesList(request);
		logger.debug(" countriesList size in retrieveCategoryHierarchy "+countriesList.size());
		/*for(int i=0;i<countriesList.size();i++){
			logger.debug("countriesList is "+countriesList.get(i));
		}*/
		logger.debug("before countryList allCountryList size is "+allCountryList.size());
		//List<GeographyCountryContract> countryList= allCountryList.get(0).getCountryList();
		

		GeographyListResult countryListResult = geographyService.getCountryDetails();
		List<GeographyCountryContract> countryListcountries1 = countryListResult.getCountryList();
		logger.debug("checking my country list size "+countryListcountries1.size());
		for(int i=0;i<countryListcountries1.size();i++){
			logger.debug("country is "+countryListcountries1.get(i).getCountryName() +" country code is "+countryListcountries1.get(i).getCountryCode());
		}
		/*if(null != allCountryList && allCountryList.size()>0){
			countryList=allCountryList.get(0).getCountryList();
		}*/
		List<GeographyCountryContract> countryList= countryListcountries1;
		//countryList = countryListcountries1;
		logger.debug(" countryList size in retrieveCategoryHierarchy "+countryList.size());
		for(int i=0;i<countryList.size();i++){
			logger.debug("countryList is "+countryList.get(i));
		}
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
		
		logger.debug(" partnerTypes size in retrieveCategoryHierarchy "+partnerTypes.size());
		for(int i=0;i<partnerTypes.size();i++){
			logger.debug("partnerTypes is "+partnerTypes.get(i));
		}

		CategoryHierarchyContract contract = ContractFactory.getCategoryHierarchyContract(request, roles, partnerTypes , countriesCode , request
				.getLocale(), globalService);
		CategoryHierarchyResult result = documentManagementService.retrieveCategoryHierarchy(contract);

		hierarchy = result.getCategoryHierarchy();
		return hierarchy;
		}
		catch (Exception e) {
			logger.debug("in retrieveCategoryHierarchy catch block");
			e.printStackTrace();
			return hierarchy;
			// TODO: handle exception
		}
		
		}
	
	/**
	 * @param definitionId 
	 * @param content 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(params = "action=handleDocumentUpload")
	public void handleDocumentUpload(
			@RequestParam("definitionId") int definitionId,
			@RequestParam("fileContent") CommonsMultipartFile content,
			Model model, ActionRequest request, ActionResponse response) {

		try {
			logger.info("Document User uploading file to Secured Documents : "
					+ definitionId);
			String fileUniqueId = reportScheduleService.getReportUniqueId()
					.toString();
			String fileName = content.getOriginalFilename();
			
			Date fileContentDate = new Date();
			
			DocumentInformation docInfo = LexmarkReportUtil.getDocumentInfo(LexmarkReportUtil.DOCUMENT_TYPE_DOCUMENT,fileContentDate, request, String.valueOf(definitionId),globalService, fileName, fileUniqueId, content.getBytes());
			getLexmarkReportServiceImpl().createDocument(docInfo);
			model.addAttribute("uploadSuccess", true);
			logger.info("Successfully uploaded file in Secured Documents: "
					+ definitionId);

		} catch (Exception e) {
			logger.debug("Exception " + e.getMessage());
			model.addAttribute("uploadFail", true);
		}
		model.addAttribute("definitionId", definitionId);
	}

	/**
	 * @param definitionId 
	 * @param request  
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("documentUserListXML")
	public void documentUserListXML(@RequestParam("definitionId") int definitionId, ResourceRequest request, ResourceResponse response)
			throws IOException {

		logger.info("Start-------DocumentUserController-----documentUserListXML");
		try {
			DocumentUserListContract contract = ContractFactory.getDocumentUserListContract(definitionId);
			DocListInfo docListInfo = LexmarkReportUtil.getDocListInfo(contract.getDefinitionId());
			List<DocumentBean> documentList = getLexmarkReportServiceImpl().listDocumentByDefinitionId(docListInfo);
			boolean canDeleteFiles = getCanDeleteDocFlag(request, definitionId);
			String gridXML = getReportXmlOutputGeneratorForDocumentGrid(request).generateDocumentUserListGridXML(documentList , canDeleteFiles);
			response.setContentType("text/xml");			
			OutputStream portletOutputStream = response.getPortletOutputStream();
			portletOutputStream.write(gridXML.getBytes());
			logger.info("End-------DocumentUserController-----documentUserListXML");
		} catch (IOException e) {
			logger.debug("IO Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.listDocument", request.getLocale());
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.listDocument", request.getLocale());
		}
	}			
	
	/**
	 * @param id 
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("deleteDocument")
    public void deleteDocument(@RequestParam("id") String id, ResourceRequest request, ResourceResponse response)
                    throws IOException {
		
		logger.info("Start-------DocumentUserController-----deleteDocument");
        try {
           	DocumentDeleteContract contract = ContractFactory.getDocumentDeleteContract(id);
           	DocDelDownloadInfo docDelDownloadInfo = LexmarkReportUtil.getDocDelDownloadInfo(contract.getObjectId());                   
           	getLexmarkReportServiceImpl().deleteDocument(docDelDownloadInfo);
           	ServiceStatusUtil.responseResult(response, "message.documentManagement.deleteDocumentName", request.getLocale());
    		logger.info("deleted document id = "+id);
    		logger.info("End-------DocumentUserController-----deleteDocument");
        } catch (Exception e) {
            logger.debug("Exception "+e.getMessage());
            ServiceStatusUtil.responseResult(response, "exception.documentManagement.deleteDocument", request.getLocale());
        }
    }
	
	/**
	 * @param id 
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	@ResourceMapping("downloadDocument")
	@ResponseBody
	public void downloadDocument(@RequestParam("id") String id,
			ResourceRequest request, ResourceResponse response)
			throws IOException, JAXBException {

		logger.info("Start-------DocumentUserController-----downloadDocument");
		try {
			DocumentDeleteContract contract = ContractFactory
					.getDocumentDeleteContract(id);
			DocDelDownloadInfo docDelDownloadInfo = LexmarkReportUtil
					.getDocDelDownloadInfo(contract.getObjectId());
			HttpResponse resp = getLexmarkReportServiceImpl().downloadDocument(docDelDownloadInfo);
			
			InputStream respInpStream = resp.getEntity().getContent();

			byte[] documentBody = IOUtils.toByteArray(respInpStream);

			response.setContentType(MediaType.APPLICATION_OCTET_STREAM
					.toString());
			response.setContentLength(documentBody.length);
			String filename = null;
			for (int i = 0; i < resp.getAllHeaders().length; i++) {
				if (resp.getAllHeaders()[i].getName().equalsIgnoreCase(
						"content-disposition")) {
					filename = resp.getAllHeaders()[i].getValue();
					filename = filename.substring(
							filename.indexOf("filename = "), filename.length());
					filename = filename.replace("filename = ", "");
				}
			}
			response.setProperty("Content-Disposition", "attachment; filename=\"" + filename.trim() + "\"");
			response.getPortletOutputStream().write(documentBody);
			
			logger.info("End-------DocumentUserController-----downloadDocument");

		} catch (IOException e) {
			logger.debug("IO Exception " + e.getMessage());
			ServiceStatusUtil.responseResult(response,
					"exception.documentManagement.downloadDocument", request.getLocale());
		} catch (Exception e) {
			logger.debug("Exception " + e.getMessage());
			ServiceStatusUtil.responseResult(response,
					"exception.documentManagement.downloadDocument", request.getLocale());
		}
	}
		
	private LexmarkReportService reportService;
	/**
	 * @return LexmarkReportService
	 */
	LexmarkReportService getLexmarkReportServiceImpl(){
		
		if(reportService == null){
			reportService = new LexmarkReportServiceImpl();
		}
		
		return reportService;
	}
	
	/**
	 * @param request 
	 * @param definitionId 
	 * @return boolean  
	 * @throws Exception 
	 */
	private boolean getCanDeleteDocFlag(ResourceRequest request, int definitionId) throws Exception{
		boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		boolean canDeleteDoc = (definitionId==-1? false:true);
		if(!isPublishing) {
			canDeleteDoc = false;
		}
		return canDeleteDoc;
	}
}