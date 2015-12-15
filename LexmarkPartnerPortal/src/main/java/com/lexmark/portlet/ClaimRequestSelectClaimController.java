package com.lexmark.portlet;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.form.DeviceSelectionForm;
import com.lexmark.result.GlobalPartnerAssetListResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.result.OpenClaimListResult;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.XmlOutputGenerator;

/*
 * Selects device from device list.
 * Path1: if there're one or more claims associated to the selected device,
 *     open multiple claims view.
 * Path2: if there is no claim associated to the selected device,
 *     directly go to submit a warranty claim page.
 */
@Controller
@RequestMapping("VIEW")
public class ClaimRequestSelectClaimController {
	
	private static Logger logger = LogManager.getLogger(ClaimRequestSelectClaimController.class);
	
	@Autowired
	private PartnerRequestsService partnerRequestsService;
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	@ActionMapping(params = "action=selectDevice")
	public void selectDevice(ActionRequest request, ActionResponse response,
			@ModelAttribute("deviceSelectionForm") DeviceSelectionForm deviceSelectionForm,
			BindingResult bindingResult,
			Model model) throws Exception {
		logger.debug("[START] selectDevice"); 
		String assetId = deviceSelectionForm.getAsset().getAssetId();
		// user selects a device to create new claim
		if (!StringUtil.isEmpty(assetId)) {
			// retrieve open claim list
			OpenClaimListContract openClaimListContract = ContractFactory.
					crateOpenClaimListContract(assetId, request);
			OpenClaimListResult result = partnerRequestsService.retrieveOpenClaimList(openClaimListContract);
			
			if(result == null){
				throw new IllegalArgumentException("Failed to open Select Device page: <br/> OpenClaimListResult is null");
			}
			
			if (result.getTotalCount() > 0) {
				deviceSelectionForm.getAsset().setProductImageURL(
						ControllerUtil.retrieveProductImageUrl(
								productImageService, deviceSelectionForm.getAsset().getProductTLI()));
				
				// localize problem code of existing open claims.
				Map<String, String> problemCodes = ControllerUtil.retrieveLocalizedLOVMap(
						SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), null, serviceRequestLocaleService, request.getLocale());
				ControllerUtil.batchLocalizeActivityProblemCode(result.getClaimList(), problemCodes);
				
				// generate XML String of open claim list,
				// and transfer to showMultipleClaimView, to avoid duplicated retrieving the list.
				String openClaimListXML;
				openClaimListXML = new XmlOutputGenerator(request.getLocale()).
						convertOpenClaimListToXML(result.getClaimList(), Float.valueOf(deviceSelectionForm.getTimezoneOffset()).floatValue());
				// ******* ADDITION start *******
				response.setRenderParameter("mki", deviceSelectionForm.getAsset().getMki());
				response.setRenderParameter("serviceProvider", deviceSelectionForm.getAsset().getServiceProvider());
				logger.debug("mki for showMultipleClaimView is "+deviceSelectionForm.getAsset().getMki());
				logger.debug("sevice Provider for showMultipleClaimView is "+deviceSelectionForm.getAsset().getServiceProvider());
				// ******* ADDITION end *******
				response.setRenderParameter("openClaimListXML", openClaimListXML);
				response.setRenderParameter("action", "showMultipleClaimView");
			} else {
				// go to submit a warranty claim page.
				showCreateClaimRequestPage(deviceSelectionForm, response);
			}
		} else { // user clicks add button to crate new claim, in "not my printer" scenario
			showCreateClaimRequestPage(deviceSelectionForm, response);
		}
		logger.debug("[END] selectDevice");
	}
	
	private void showCreateClaimRequestPage(DeviceSelectionForm deviceSelectionForm,
			ActionResponse response) {
		response.setRenderParameter("assetId",  deviceSelectionForm.getAsset().getAssetId());
		response.setRenderParameter("notMyPrinterFlag", deviceSelectionForm.getNotMyPrinterFlag());
		response.setRenderParameter("serialNumber", deviceSelectionForm.getAsset().getSerialNumber());
		response.setRenderParameter("machineType", deviceSelectionForm.getAsset().getModelNumber());
		response.setRenderParameter("produceTLI", deviceSelectionForm.getAsset().getProductTLI());
		response.setRenderParameter("duplicateDeviceFlag", Boolean.toString(deviceSelectionForm.getAsset().getDuplicateDevice()));
		// ******* ADDITION start *******
		response.setRenderParameter("mki", deviceSelectionForm.getAsset().getMki());
		response.setRenderParameter("serviceProvider", deviceSelectionForm.getAsset().getServiceProvider());
		logger.debug("mki is "+deviceSelectionForm.getAsset().getMki());
		logger.debug("sevice Provider is "+deviceSelectionForm.getAsset().getServiceProvider());
		// ******* ADDITION end *******
		response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(deviceSelectionForm.getTimezoneOffset()));
		response.setRenderParameter("action", "showCreateClaimRequestPage");
	}
	
	@RequestMapping(params = ("action=showMultipleClaimView"))
	public String showMultipleClaimView(RenderRequest request, RenderResponse reponse,
			Model model) throws Exception {
		
		// ******* ADDITION start *******
		String mki = request.getParameter("mki");
		logger.debug("mki in showMultipleClaimView is "+mki);
		String serviceProvider = request.getParameter("serviceProvider");
		logger.debug("serviceProvider in showMultipleClaimView is "+serviceProvider);
		// *******ADDITION end *******
		model.addAttribute("mki", mki);
		model.addAttribute("serviceProvider", serviceProvider);
		model.addAttribute("openClaimListXML", request.getParameter("openClaimListXML"));
		return "claims/createClaimMultipleClaimView";
	}
	
	@RequestMapping(params="action=showGlobalPartnerAssetSectionView")
	public String showGlobalPartnerAssetSectionView(RenderRequest request, RenderResponse response, Model model){
		logger.debug("[START] showGlobalPartnerAssetSectionView");
		Locale locale = request.getLocale();
		String URLPart = "&locale=" + locale.getLanguage() + "&userlocale=" +
				locale.getLanguage() + "_" + locale.getCountry();
		String helpURL = PropertiesMessageUtil.getPropertyMessage(
				"com.lexmark.resources.hostConfig",
				"printerSelectionHelpHost", Locale.US) + URLPart;
		
		DeviceSelectionForm deviceSelectionForm = new DeviceSelectionForm();
		model.addAttribute("deviceSelectionForm", deviceSelectionForm);
		model.addAttribute("helpURL" , helpURL);
		
		logger.debug("[END] showGlobalPartnerAssetSectionView");
		return "claims/deviceSearch";
	}
	
	@ResourceMapping("getGlobalPartnerAssetList")
	public String getGlobalPartnerAssetListBySerialNumber(ResourceRequest request,ResourceResponse response, Model model,
			@RequestParam("serialNumber") String serialNumber){
		logger.debug("[START] getGlobalPartnerAssetListBySerialNumber");
		GlobalPartnerAssetListContract contract = ContractFactory.createGlobalPartnerAssetListContract(serialNumber);
		ObjectDebugUtil.printObjectContent(contract, logger);
		GlobalPartnerAssetListResult result = partnerRequestsService.retrieveGlobalPartnerAssetList(contract);
		List<Asset> assetList = result.getAssetList();
		if (assetList != null) {
			for (Asset device : assetList) {
				logger.debug("MKI is ================== "+device.getMki());
				logger.debug("Service Provider is ================== "+device.getServiceProvider());
				device.setDuplicateDevice(false);
				device.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, device.getProductTLI()));
			}
			
			for(int i=0;i<assetList.size();i++){
				if(assetList.get(i).getDuplicateDevice()==false){
					logger.debug("inside if checking deuplicate   i value is  "+i);
					for(int j=i+1;j<assetList.size();j++){
						if(assetList.get(i).getProductLine().equalsIgnoreCase(assetList.get(j).getProductLine()) && assetList.get(i).getModelNumber().equalsIgnoreCase(assetList.get(j).getModelNumber())
								&& assetList.get(i).getProductTLI().equalsIgnoreCase(assetList.get(j).getProductTLI())){
							logger.debug("Same values for ---- "+i+" And "+j);
							//assetList.get(i).setCustomerReportingName(customerReportingName)
							assetList.get(i).setDuplicateDevice(true);	
							assetList.get(j).setDuplicateDevice(true);	
						}
						/*else{
							assetList.get(i).setDuplicateDevice(false);
							assetList.get(j).setDuplicateDevice(false);
						}*/
					}
				}
				
			}
		}
		model.addAttribute("globalPartnerAssetListResult", result);
		logger.debug("[END] getGlobalPartnerAssetListBySerialNumber");
		
		response.setContentType("text/xml");
		return "claims/xml/deviceSearchListXML";
	}
	
	//validate manual asset
	@ResourceMapping("validateManualAsset")
	public void validateManualAsset(ResourceRequest request, ResourceResponse response) throws Exception {
		logger.debug("[START] validateManualAsset");
		String machineType = request.getParameter("machineType");
		String productTLI = request.getParameter("productTLI");
		String errorCode = "message.gerneral.success";
		boolean success = false;
		try {
			ManualAssetContract contract = new ManualAssetContract();
			logger.debug("the machine type is " + machineType);
			logger.debug("the product TLI is " + productTLI);
			if(machineType!=null)
				contract.setModelNumber(machineType);
			if(productTLI!=null)
				contract.setProductTLI(productTLI);
			ManualAssetResult mar = serviceRequestService.validateManualAsset(contract);
			logger.debug("-------------validateManualAsset end---------");
			success = mar.getResult();
		} catch (Exception e) {
			errorCode = "exception.servicerequest.validateManualAsset";
		}
		logger.debug("success: "+success);
		//note: following msg error is never used, but they are need for getting the 
		//response call working
		
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),String.valueOf(success));
		logger.debug("[END] validateManualAsset");
	}
}