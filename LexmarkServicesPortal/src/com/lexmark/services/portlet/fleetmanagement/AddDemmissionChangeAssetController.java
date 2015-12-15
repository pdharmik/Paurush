package com.lexmark.services.portlet.fleetmanagement;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.lexmark.domain.GenericAddress;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.FleetManagementForm;
import com.lexmark.services.portlet.cm.ManageAssetsController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.util.PropertiesMessageUtil;

@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={ChangeMgmtConstant.ADDASSETFORM, ChangeMgmtConstant.CHANGEASSETFORM, ChangeMgmtConstant.DECOMMASSETFORM})
public class AddDemmissionChangeAssetController {
	
	@Autowired
	ManageAssetsController manageAssetsController;
	
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
	@RenderMapping(params="action=decommissionAssetFromMap")
	public String redirectToDecommissionAsset(RenderRequest req, RenderResponse resp, Model model, 
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
		
		/*PortletSession portletSession =  req.getPortletSession();		
		portletSession.removeAttribute("addressFromMap", PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("City from Map :: " + fleetMgmtForm.getAddressFromMap().getCity());
		portletSession.setAttribute("pickUpAddress", fleetMgmtForm.getAddressFromMap(),PortletSession.APPLICATION_SCOPE);*/
		
		req.setAttribute("fleetManagementFlag", "true");
		req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
		LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
		toJsp = manageAssetsController.redirectToChangeDecom(req, resp, model, assetId, ChangeMgmtConstant.DECOMMASSETREQTYPE, map);//"fleetmanagement/defaultView";//
		
		
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
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
	@RenderMapping(params="action=changeAssetFromMap")
	public String redirectToChangeAsset(RenderRequest req, RenderResponse resp, Model model, 
			@ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, 
			ModelMap map) throws Exception {
		String toJsp=null;
		String exError=null;
		try{
		LOGGER.debug("Entering redirectToChangeAsset method");
		
		/*PortletSession portletSession =  req.getPortletSession();		
		portletSession.removeAttribute("addressFromMap", PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("City from Map :: " + fleetMgmtForm.getAddressFromMap().getCity());
		portletSession.setAttribute("currentInstalledAddess", fleetMgmtForm.getAddressFromMap(),PortletSession.APPLICATION_SCOPE);*/
			
		//for attachments
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("attachmentForm", new AttachmentForm());
		String assetId = fleetMgmtForm.getAssetId();//"1-BBBV-2098";
		req.setAttribute("fleetManagementFlag", "true");
		req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
		LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
		toJsp = manageAssetsController.redirectToChangeDecom(req, resp, model, assetId, ChangeMgmtConstant.CHANGEASSETREQTYPE, map);//"fleetmanagement/defaultView";//
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
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
	@RenderMapping(params="action=moveAssetFromMap")
	public String handleMoveAsset(RenderRequest req, RenderResponse resp, Model model, 
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
		req.setAttribute("fleetManagementFlag", "true");
		req.setAttribute("moveAssetFlag", "true");
		req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
		LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
		GenericAddress moveAddress = (GenericAddress)fleetMgmtForm.getMoveToAddress();
		
		
		PortletSession portletSession =  req.getPortletSession();		
		portletSession.removeAttribute("moveAddress", PortletSession.APPLICATION_SCOPE);
		portletSession.setAttribute("moveAddress", moveAddress,PortletSession.APPLICATION_SCOPE);
		
		toJsp = manageAssetsController.redirectToChangeDecom(req, resp, model, assetId, ChangeMgmtConstant.CHANGEASSETREQTYPE, map);
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
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
	@RenderMapping(params="action=addAssetFromMap")
	public String handleAddAsset(RenderRequest req, RenderResponse resp, Model model, 
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
		req.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
		LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
		GenericAddress installedAtAddress = (GenericAddress)fleetMgmtForm.getMoveToAddress();
		req.setAttribute("installAddress", installedAtAddress);
		toJsp = manageAssetsController.redirectToAddAsset(model, req, resp, selectedVal,  map);
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"tax.validation.error", req.getLocale());
			model.addAttribute("errors", exError);
		}
		
		return toJsp;
	}
	
}
