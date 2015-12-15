package com.lexmark.services.portlet.fleetmanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.FleetManagementForm;
import com.lexmark.services.portlet.cm.ManageAssetsController;
import com.lexmark.services.portlet.cm.ManageMultiAssetsController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.util.PropertiesMessageUtil;

@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={ChangeMgmtConstant.ADDASSETFORM, ChangeMgmtConstant.CHANGEASSETFORM, ChangeMgmtConstant.DECOMMASSETFORM})
public class AddDemmissionChangeAssetController {
	
	@Autowired
	ManageAssetsController manageAssetsController;
	
	@Autowired
	ManageMultiAssetsController manageMultiAssetsController;
	
	private static Logger LOGGER = LogManager.getLogger(AddDemmissionChangeAssetController.class);
	
	
	/**
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @param fleetMgmtForm 
	 * @param map 
	 * @return String 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=decommissionAssetFromMap")
	public void redirectToDecommissionAsset(ActionRequest req, ActionResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map) throws Exception {
		String toJsp=null;
		String exError=null;
		try{
		
			//for attachments
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("attachmentForm", new AttachmentForm());
			LOGGER.debug("Entering redirectToDecommissionAsset method");
			String assetId = fleetMgmtForm.getAssetId(); //"1-BBBV-2098";
			
			String multiAssetList = fleetMgmtForm.getMultiAssetList();
			req.setAttribute("fleetManagementFlag", "true");
			req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
			LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
			req.setAttribute("fltForm", fleetMgmtForm);
			LOGGER.debug("inside decommissionAssetFromMap");
					
			if(multiAssetList != null && multiAssetList.length() > 0)
			{			
				req.setAttribute("toJsp",toJsp);
				resp.setRenderParameter("action","multipleDecomission");			
			}
			else{
				req.setAttribute("assetId",assetId);
				resp.setRenderParameter("action","singleDecomission");			
			}
		
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
		}		
	}
	
	@RenderMapping(params = "action=singleDecomission")
	public String singleDecomission(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		String assetId=(String)req.getAttribute("assetId");
		String toJsp="";
		try {
			 toJsp = manageAssetsController.redirectToChangeDecom(req, resp, model, assetId, ChangeMgmtConstant.DECOMMASSETREQTYPE, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}
	
	@RenderMapping(params = "action=multipleDecomission")
	public String multipleDecomission(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		String toJsp="";
		GenericAddress installAddress = (GenericAddress)fleetMgmtForm.getInstallAddress();	
		PortletSession portletSession =  req.getPortletSession();		
		portletSession.removeAttribute("installAddress", PortletSession.APPLICATION_SCOPE);
		portletSession.setAttribute("installAddress", installAddress, PortletSession.APPLICATION_SCOPE);
		try {
			toJsp = manageMultiAssetsController.redirectToChangeMultiAsset(req, resp, model, ChangeMgmtConstant.DECOMMMULTIASSETREQTYPE, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}
	
	/**
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @param fleetMgmtForm 
	 * @param map 
	 * @return String 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=changeAssetFromMap")
	public void redirectToChangeAsset(ActionRequest req, ActionResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map) throws Exception {
		String toJsp=null;
		String exError=null;
		try{
			LOGGER.debug("Entering redirectToChangeAsset method");
			
			//for attachments
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("attachmentForm", new AttachmentForm());
			String assetId = fleetMgmtForm.getAssetId();//"1-BBBV-2098";
			String multiAssetList = fleetMgmtForm.getMultiAssetList();
			req.setAttribute("fleetManagementFlag", "true");
			req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
			LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
			req.setAttribute("fltForm", fleetMgmtForm);
			LOGGER.debug("inside redirectToChangeAsset");
			
			if(multiAssetList != null && multiAssetList.length() > 0)
			{
				req.setAttribute("toJsp",toJsp);
				resp.setRenderParameter("action","multipleChange");
			}
			else
			{
				req.setAttribute("assetId",assetId);
				resp.setRenderParameter("action","singleChange");
			}
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
		}
	}
	
	@RenderMapping(params = "action=singleChange")
	public String singleChange(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		String assetId=(String)req.getAttribute("assetId");
		String toJsp="";
		try {
			 toJsp = manageAssetsController.redirectToChangeDecom(req, resp, model, assetId, ChangeMgmtConstant.CHANGEASSETREQTYPE, map);//"fleetmanagement/defaultView";//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}
	
	@RenderMapping(params = "action=multipleChange")
	public String multipleChange(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		String toJsp="";
		GenericAddress installAddress = (GenericAddress)fleetMgmtForm.getInstallAddress();		
		
		PortletSession portletSession =  req.getPortletSession();		
		portletSession.removeAttribute("installAddress", PortletSession.APPLICATION_SCOPE);
		portletSession.setAttribute("installAddress", installAddress, PortletSession.APPLICATION_SCOPE);
		try {
			toJsp = manageMultiAssetsController.redirectToChangeMultiAsset(req, resp, model, ChangeMgmtConstant.CHANGEMULTIASSETREQTYPE, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}

	
	/**
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @param fleetMgmtForm 
	 * @param map 
	 * @return String 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=moveAssetFromMap")
	public void handleMoveAsset(ActionRequest req, ActionResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map) throws Exception {
		String toJsp=null;
		String exError=null;
		try{
			LOGGER.debug("Entering handleMoveAsset method");		
				
			//for attachments
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("attachmentForm", new AttachmentForm());
			String assetId = fleetMgmtForm.getAssetId();
			String multiAssetList = fleetMgmtForm.getMultiAssetList();
			req.setAttribute("fleetManagementFlag", "true");
			req.setAttribute("moveAssetFlag", "true");
			req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
			LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
			GenericAddress moveAddress = (GenericAddress)fleetMgmtForm.getMoveToAddress();
			LOGGER.debug("moveAddress::"+moveAddress);
			PortletSession portletSession =  req.getPortletSession();		
			portletSession.removeAttribute("moveAddress", PortletSession.APPLICATION_SCOPE);
			portletSession.setAttribute("moveAddress", moveAddress,PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("moveAddress1::"+moveAddress);
			GenericAddress installAddress = (GenericAddress)fleetMgmtForm.getInstallAddress();		
			LOGGER.debug("installAddress::"+installAddress);
			portletSession.removeAttribute("installAddress", PortletSession.APPLICATION_SCOPE);
			portletSession.setAttribute("installAddress", installAddress, PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("installAddress1::"+installAddress);
			req.setAttribute("fltForm", fleetMgmtForm);
			
			if(multiAssetList != null && multiAssetList.length() > 0)
			{
				LOGGER.debug("Inside manageMultiAssetsController.redirectToChangeMultiAsset");
				req.setAttribute("toJsp",toJsp);
				resp.setRenderParameter("action","multipleMove");
			}
			else
			{
				req.setAttribute("assetId",assetId);
				resp.setRenderParameter("action","singleMove");
			}
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
		}
	}
	
	@RenderMapping(params = "action=singleMove")
	public String singleMove(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		String assetId=(String)req.getAttribute("assetId");
		String toJsp="";
		try {
			 toJsp = manageAssetsController.redirectToChangeDecom(req, resp, model, assetId, ChangeMgmtConstant.CHANGEASSETREQTYPE, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}
	
	@RenderMapping(params = "action=multipleMove")
	public String multipleMove(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		String toJsp="";
		
		try {
			toJsp = manageMultiAssetsController.redirectToChangeMultiAsset(req, resp, model, ChangeMgmtConstant.CHANGEMULTIASSETREQTYPE, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}
	
	/**
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @param fleetMgmtForm 
	 * @param map 
	 * @return String 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=addAssetFromMap")
	public void handleAddAsset(ActionRequest req, ActionResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map) throws Exception {
		String toJsp=null;
		String exError=null;
		try{
			LOGGER.debug("Entering handleAddAsset method");		
				
			//for attachments
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("attachmentForm", new AttachmentForm());
			String assetId = fleetMgmtForm.getAssetId();
			req.setAttribute("fleetManagementFlag", "true");
			String selectedVal="addone";
			req.setAttribute("assetId", assetId);
			//req.setAttribute("placementId", fleetMgmtForm.getPlacementId());
			req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
			LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
			GenericAddress installedAtAddress = (GenericAddress)fleetMgmtForm.getMoveToAddress();
			req.setAttribute("installAddress", installedAtAddress);
			
			req.setAttribute("assetId",assetId);
			resp.setRenderParameter("action","singleAdd");
			//toJsp = manageAssetsController.redirectToAddAsset(model, req, resp, selectedVal,  map);
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
		}
	}
	
	@RenderMapping(params = "action=singleAdd")
	public String singleAdd(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map){
		
		String toJsp="";
		String selectedVal="addone";
		
		try {
			toJsp = manageAssetsController.redirectToAddAsset(model, req, resp, selectedVal,  map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toJsp;
	}
	
	/**
	 * @param req
	 * @param fileName
	 * @param resp
	 */
	@ResourceMapping("displayMultipleAttachment")
	public void downloadAttachment(ResourceRequest req,@RequestParam("fileName") String fileName,
			ResourceResponse resp){
		LOGGER.debug("In download attachment");
		AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
		String downloadPath = fileProperties.getFileUploadDestination() +fileName;
		LOGGER.debug("download path"+downloadPath);
		LOGGER.debug("download file name is"+fileName);
		
		resp.setProperty("Content-disposition", "attachment; filename=\"" + fileName +"\"");
		resp.setContentType("application/vnd.ms-excel");
		InputStream inputStream=null;OutputStream out=null;
		try {
			inputStream = new FileInputStream(downloadPath);
			out = resp.getPortletOutputStream();
			 byte buf[]=new byte[1024];
			 int inputStreamBufferlen=inputStream.read(buf);
			 while(inputStreamBufferlen>0){
				 out.write(buf,0,inputStreamBufferlen);
				 inputStreamBufferlen=inputStream.read(buf);
			 }
			 
		} catch (FileNotFoundException e) {
			LOGGER.debug("[ File not Found Exception ]");
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.debug("[ IO Exception ]");
			e.printStackTrace();
		}finally{
			try {
				if(out!=null && inputStream != null){
					out.close();
					inputStream.close();
				}				
			} catch (IOException e) {
				LOGGER.debug("[ IO Exception ]");
			}
		}
		 
	}
}
