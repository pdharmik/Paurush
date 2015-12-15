package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
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
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.DocumentConfigurePathContract;
import com.lexmark.contract.DocumentGetConfigurePathContract;
import com.lexmark.contract.DocumentViewContract;
import com.lexmark.domain.Document;
import com.lexmark.result.DocumentGetConfigurePathResult;
import com.lexmark.result.DocumentViewResult;
import com.lexmark.service.api.DocumentManagementService;
import com.lexmark.service.impl.real.jdbc.DocumentManagementServiceImpl;
import com.lexmark.services.util.ServiceStatusUtil;
@Controller
@RequestMapping({ "VIEW", "EDIT" })
public class DocumentViewController extends BaseController {
	
	static Logger logger = LogManager.getLogger(DocumentViewController.class);
	@Autowired
	private DocumentManagementService documentManagementService;

	/**
	 * @param path 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping("VIEW")
	public String defaultPage(@RequestParam(value = "path", required = false) String path, Model model,
			RenderRequest request, RenderResponse response) {
		return "documentManagement/view/documentView";
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping({"EDIT"})
	public String configurePage(Model model, RenderRequest request, RenderResponse response) {
		logger.debug("Edit > Render phase.");
		if(model.asMap().get("path")==null){
			DocumentGetConfigurePathContract contract = new DocumentGetConfigurePathContract();
			DocumentGetConfigurePathResult result = documentManagementService.getConfiguredPath(contract);
			String path = result.getPath();
			model.addAttribute("path",path);
			logger.debug("path is: "+path);
		}
		return "documentManagement/view/documentConfigure";
	}
	
	/**
	 * @param path 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(value="EDIT",params="action=saveCofiguration")
	public void saveConfigure(@RequestParam("path") String path, Model model, ActionRequest request, ActionResponse response) {
		logger.debug("Edit > Action phase.");
		if(!path.startsWith("/")){
			ServiceStatusUtil.checkServiceStatus(model, "exception.documentManagement.configurePath.notStartWithSlash", request.getLocale(), true);
			return;
		}
		try{
			DocumentConfigurePathContract contract = new DocumentConfigurePathContract();
			contract.setPath(path);
			documentManagementService.configurePath(contract );
			ServiceStatusUtil.checkServiceStatus(model, "message.documentManagement.configurePath", request.getLocale(), true);
			logger.debug("file path saved to: "+path );
			model.addAttribute("path",path);
			response.setPortletMode(PortletMode.VIEW);
		}catch (Exception e) {
			ServiceStatusUtil.checkServiceStatus(model, "exception.documentManagement.configurePath", request.getLocale(), true);
			logger.debug(e.getMessage());
		}
	}
	/**
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("folderTreeXML")
	public void folderTreeXML(ResourceRequest request,
			ResourceResponse response) throws IOException {
		logger.debug("Tree > Resource phase.");
		DocumentGetConfigurePathContract pathcontract = new DocumentGetConfigurePathContract();
		DocumentGetConfigurePathResult pathresult = documentManagementService.getConfiguredPath(pathcontract);
		String path = pathresult.getPath();
		logger.debug("folder tree : "+path);
		String treeXML = "";
		try {
			DocumentViewContract contract = new DocumentViewContract();
			contract.setPath(path);
			DocumentViewResult result = documentManagementService.retrieveDocumentHierarchy(contract);
			HashMap hierarchy = result.getDocumentHierarchy();
			treeXML = getXmlOutputGeneratorForDocument(request).generateDocumentFolderTree(hierarchy);
			response.setContentType("text/xml");
			OutputStream portletOutputStream = response.getPortletOutputStream();
			portletOutputStream.write(treeXML.getBytes());
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			logger.debug(e);
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.tree", request.getLocale());
		}
	}
	
	
	/**
	 * @param path    
	 * @param request    
	 * @param response    
	 * @throws IOException    
	 */
	@ResourceMapping("documentViewListXML")
	public void documentViewListXML(@RequestParam("path") String path, ResourceRequest request,
			ResourceResponse response) throws IOException {

		if ((!path.startsWith("/")) ) {
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.invalidPath", request.getLocale());
			return;
		}
		try {
			logger.debug(path);
			DocumentViewContract contract = new DocumentViewContract();
			contract.setPath(path);
			DocumentViewResult result = documentManagementService.retrieveDocumentHierarchy(contract);
			HashMap hierarchy = result.getDocumentHierarchy();
			List<Document> docs = converToList(hierarchy);
			String treeXML = getXmlOutputGeneratorForDocument(request).generateDocumentListGrid(docs);
			response.setContentType("text/xml");
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			OutputStream portletOutputStream = response.getPortletOutputStream();
			portletOutputStream.write(treeXML.getBytes());
		} catch (IOException e) {
			logger.debug("IO Exception "+e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.tree", request.getLocale());
		}
	}
	
	/**
	 * @param path    
	 * @param request     
	 * @param response    
	 * @throws IOException    
	 */
	@ResourceMapping("documentUserListXML")
	public void documentUserListXML(@RequestParam("path") String path, ResourceRequest request,
			ResourceResponse response) throws IOException {

		if ((!path.startsWith("/")) || !path.endsWith("/")) {
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.invalidPath", request.getLocale());
			return;
		}
		try {
			logger.debug(path);
			DocumentViewContract contract = new DocumentViewContract();
			contract.setPath(path);
			DocumentViewResult result = documentManagementService.retrieveDocumentHierarchy(contract);
			HashMap hierarchy = result.getDocumentHierarchy();
			List<Document> docs = converToList(hierarchy);
			String treeXML = getXmlOutputGeneratorForDocument(request).generateDocumentUserListGridXML(docs,true);
			response.setContentType("text/xml");
			OutputStream portletOutputStream = response.getPortletOutputStream();
			portletOutputStream.write(treeXML.getBytes());
		} catch (IOException e) {
			logger.debug("IO Exception "+e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.tree", request.getLocale());
		}
	}
	/**
	 * @param hierarchy 
	 * @return List<Document>  
	 */
	@SuppressWarnings("unchecked")
	private List<Document> converToList(HashMap hierarchy) {
		List<Document> docs = null;
		Iterator iterator = hierarchy.values().iterator();
		if(iterator!=null&&iterator.hasNext()){
			Object next = iterator.next();
			if(next instanceof List){
				docs = (List<Document>) next;
			}
		}
		return docs;
	}
	
	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		DocumentManagementServiceImpl service = new DocumentManagementServiceImpl();
		for (int i = 0; i < 100; i++) {
			DocumentConfigurePathContract contract = new DocumentConfigurePathContract();
			contract.setPath("PATH: "+i);
			service.configurePath(contract );
			String path = service.getConfiguredPath(null).getPath();
			logger.info(path);
		}
	}
}