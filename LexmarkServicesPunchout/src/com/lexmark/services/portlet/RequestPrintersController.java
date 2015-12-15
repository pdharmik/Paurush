package com.lexmark.services.portlet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderHardwareService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.services.api.LPMDService;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.constants.BeanFieldNames;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.form.LearnMoreForm;
import com.lexmark.services.form.ShoppingCartForm;
import com.lexmark.services.mock.GenerateMockData;
import com.lexmark.services.servlet.LoadAccountInformation;
import com.lexmark.services.servlet.LoadPriceInformation;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.URLImageUtil;

/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: RequestHistoryController.java
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
/**
 * This controller is suppose to provide 
 * the default page for Right Navigation
 * */
/**
 * @author wipro
 * @version 1.0
 */
@Controller
@RequestMapping("VIEW")
public class RequestPrintersController {
	
	@Autowired
	private OrderHardwareService orderHardwareService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private LoadAccountInformation allAccountInformation;
	@Autowired
	private OrderSuppliesCatalogService orderSuppliesCatalogService;
	/*@Autowired
	private RequestTypeServiceB2B requestTypeB2bService;*/
	@Autowired
	private LPMDService LPMDService;
	@Autowired
	private LoadPriceInformation loadPriceInformation;
	@Autowired 
	private RetrievePriceService retrievePriceService;
	private static Logger LOGGER = LogManager
			.getLogger(RequestPrintersController.class);

	private static final String PATH = "requests/product/";	

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 */
	@ResourceMapping("loadPrinterList")
	public String showPrinterList(ResourceRequest request,
			ResourceResponse response, Model model,PortletSession session) {
		LOGGER.debug("in show printers");
				
		LOGGER.debug(("[ In  showPrinterList ]"));
		request.setAttribute("callType", "showPrinterList");
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));
		model.addAttribute(PunchoutConstants.BUNDLE_LIST, ControllerUtil.amindRetrievePrinterTypes(request,orderSuppliesCatalogService).getLovList());
		
		//MockCall
		//model.addAttribute(PunchoutConstants.BUNDLE_LIST, new GenerateMockData().generatePrintersList());
		return PATH + "printersList";
	}	
	
	/** 
	 * @param request 
	 * @param response 
	 * @param model 
	 */
	@ActionMapping(params="action=loadPrinterProducts")
	public void showPrinterProducts(ActionRequest request,
			ActionResponse response, Model model)  {
		LOGGER.debug(("[ In  loadPrinterProducts ]"));
		
		response.setRenderParameter("action", "loadPrinterProductsRender");		
	}
	
	/**
	 * @param model 
	 * @return String 
	 */
	@RequestMapping(params="action=loadPrinterProductsRender")
	public String showPrinterProductsRender(Model model){
		LOGGER.debug("[in showPrinterProductsRender]");
		return PATH + "printerProduct";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 */
	@ResourceMapping("prepareBundle")
	public String prepareBundle(ResourceRequest request,
			ResourceResponse response, Model model, PortletSession session) {
		LOGGER.debug(("[ In  prepareBundle ]"));
		//List<Bundle> bundleItems=new GenerateMockData().generateBundle();
		//model.addAttribute("bundleItems", bundleItems);
		//session.setAttribute("bundle", bundleItems,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug(("[ Out  prepareBundle ]"));
		return PATH + "bundle";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveBundleGrid")
	public String retrieveBundleGrid(ResourceRequest request,
			ResourceResponse response, Model model,
			PortletSession session) throws Exception{
		LOGGER.debug("IN RETRIEVE BUNDLE-------------");
		
		PunchoutAccount punchAcnt = ControllerUtil.getPunchoutAccount((List<PunchoutAccount>) session.getAttribute(PunchoutConstants.ACCOUNT_LIST, PortletSession.APPLICATION_SCOPE), request);
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchAcnt);
		session.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchAcnt, PortletSession.APPLICATION_SCOPE);
		
		CrmSessionHandle crmSessionHandle=null;
		
		crmSessionHandle= globalService
		.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		 List<Bundle> draftBundleItems = new ArrayList<Bundle>();

      if (request.getParameter("fromCart")!=null && "true".equalsIgnoreCase(request.getParameter("fromCart"))){
           
    		try{
    			LOGGER.debug("IN FROM CART");
    		
    		//Map<String, ShoppingCartForm> shoppingCarts = (Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
    		//List<Bundle> cartBundle = (List<Bundle>) shoppingCarts.get("printers");
    		//draftBundleItems=cartBundle;
    		LOGGER.debug("draftBundleItems size "+draftBundleItems.size());
    		}
    		catch (Exception e) {
    			LOGGER.debug("Exception occured" + e.getMessage());
    			//e.printStackTrace();
    		}
        }
        // end for draft SR
		
        
		HardwareCatalogContract contract = ContractFactory.retrieveBundleListContract(request);
		contract.setSessionHandle(crmSessionHandle);
        
        LOGGER.debug("PRINTING LOGGER");
        ObjectDebugUtil.printObjectContent(contract,LOGGER);
        
        BundleListResult result=new BundleListResult();
        try{
       result = orderHardwareService.retrievePrinterBundleListB2B(contract);
        }catch(Exception e){
        	LOGGER.debug("Exception occured" + e.getMessage());
        	//e.printStackTrace();
        }finally{
        	globalService.releaseSessionHandle(crmSessionHandle);
        } 
        List<Bundle> bundleItems=new ArrayList<Bundle>();
        if(result!=null){
        bundleItems=result.getBundleList();
        }
        
        session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundleItems);        
        ControllerUtil.updateItemsWithCartData(session, request.getParameter("cType"), "");
        
        if("Draft".equalsIgnoreCase(request.getParameter("rTyp"))){
        LOGGER.debug("bundle id ------------ "+result.getBundleList().get(0).getBundleId());		
		for(Bundle bundleHistory:draftBundleItems){
			for(Bundle bundle:bundleItems){
				if(bundleHistory.getBundleId().equalsIgnoreCase(bundle.getBundleId())){
					bundle.setBundleQty(bundleHistory.getBundleQty());
					//ControllerUtil.shoppingCart(session,bundle.getBundleId(),String.valueOf(bundleHistory.getBundleQty()));
				}
			}
		}		
       }
       if("true".equalsIgnoreCase(request.getParameter("fromCart"))){
           // LOGGER.debug("bundle id ------------ "+result.getBundleList().get(0).getBundleId());		
    		for(Bundle bundleHistory:draftBundleItems){
    			for(Bundle bundle:bundleItems){
    				if(bundleHistory.getBundleId().equalsIgnoreCase(bundle.getBundleId())){
    					bundle.setBundleQty(bundleHistory.getBundleQty());
    					LOGGER.debug("bundle qty ------------ "+bundleHistory.getBundleQty());    					
    				}
    			}
    		}	
        }	
        else if(session.getAttribute(PunchoutConstants.CART_SESSION)!=null)
        {
        	//ShoppingCartForm _form = (ShoppingCartForm) session.getAttribute(PunchoutConstants.CART_SESSION);
    		//draftBundleItems=_form.getCartItems();
    		for(Bundle bundleHistory:draftBundleItems){
    			for(Bundle bundle:bundleItems){
    				if(bundleHistory.getBundleId().equalsIgnoreCase(bundle.getBundleId())){
    					bundle.setBundleQty(bundleHistory.getBundleQty());
    					LOGGER.debug("bundle qty ------------ "+bundleHistory.getBundleQty());	
    					
    				}
    			}
    		}        	
        }
       List<Bundle> bundlePriceList=ControllerUtil.getBundlePrice(loadPriceInformation,request);
       if(bundlePriceList != null){
        for(Bundle bundlePrice:bundlePriceList){
			for(Bundle bundle:bundleItems){
				if(bundlePrice.getBundleId().equalsIgnoreCase(bundle.getBundleId())){
					LOGGER.debug("PRICe-------"+bundlePrice.getPrice());
					bundle.setPrice(bundlePrice.getPrice());
					bundle.setCurrency(bundlePrice.getCurrency());					
				}
			}
		}
        }
		
       for(Bundle bundle:bundleItems){    	 
    	  for(Part parts:bundle.getPartList()){    		  
    		  LOGGER.debug("PART NUMBER----->"+parts.getPartNumber());
    	  }
			/*if(bundle.getAssetId()!=null){
				LOGGER.debug("Asset ID-------->"+bundle.getAssetId());
				bundle.setImageURL(URLImageUtil.getPartImageFromLocal(bundle.getAssetId()));
				LOGGER.debug("IMAGE URL"+bundle.getImageURL());				
			}*/
		}
		model.addAttribute(PunchoutConstants.BUNDLE_ITEMS, bundleItems);		
		model.addAttribute(PunchoutConstants.TOTAL_COUNT, result.getTotalCount());		
		model.addAttribute(PunchoutConstants.START_POS, contract.getStartRecordNumber());		
		model.addAttribute(PunchoutConstants.START_POS, 0);		
		session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundleItems);		
		response.setContentType("text/xml");
		
		LOGGER.debug(("[ Out  prepareBundle ]"));
		return PATH + "bundleGridXML";
	}	
	
	/**
	 * @param request 
	 * @param bundleId 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping("getOptionsAndWarranties")
	public String retrieveOptionsWarranties(ResourceRequest request,
			@RequestParam(value="bundleId", required=false) String bundleId,
			@RequestParam(value="cartType", required=false) String cartType,
			ResourceResponse response, Model model,
			PortletSession session) throws Exception{
		LOGGER.debug("[ in generate options warranties ]");
		request.setAttribute("callType", "retrieveBundleGrid");
		
		PunchoutAccount punchAcnt = (PunchoutAccount) session.getAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, PortletSession.APPLICATION_SCOPE);
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchAcnt);
		//Set the list to bundle which is present in session
		List<OrderPart> accessories=(ControllerUtil.amindRetrieveAccessoriesB2B(request,orderSuppliesCatalogService,globalService)).getAccessoriesList();
		
		//model.addAttribute("accessoriesList",accessories);		
		ControllerUtil.updateBundlesWithOptionswarranties(session, bundleId, accessories, cartType);
		ControllerUtil.updateItemsWithCartData(session, cartType, bundleId);
		
		List<Object> amindList=(List<Object>)session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		Object itemBundle=ControllerUtil.findInList(amindList, bundleId, BeanFieldNames.ID.getValue(cartType));
		accessories = ((Bundle) itemBundle).getOrderParts();
		
		LOGGER.debug("accessories:::"+accessories);
		 PriceResult bundlePriceResult = null;
		 bundlePriceResult=ControllerUtil.getPriceForParts(punchAcnt.getContractNumber(),accessories,retrievePriceService);
		 if(bundlePriceResult!=null){
		 for(Price price:bundlePriceResult.getPriceOutputList())
		 {
			 for(OrderPart parts:accessories)
			 {
				 if(parts.getContractLineItemId()!=null && parts.getContractLineItemId().equalsIgnoreCase(price.getContractLineItemId())){
					 parts.setPrice((new BigDecimal(price.getPrice())));
				 }
			 }
		 }
		 }
		 for(OrderPart parts:accessories)
		 {
			 if(parts.getPartNumber()!=null){
				 LOGGER.debug("PART NUMBER--------->"+parts.getPartNumber());
				 parts.setPartImg(URLImageUtil.getPartImageFromLocal(parts.getPartNumber()));
				 LOGGER.debug("IMAGE URL------------"+parts.getPartImg());
				
			 }
		 }
		model.addAttribute("accessoriesList",accessories);
		model.addAttribute("bundleId",bundleId);
		LOGGER.debug("[ out generate options warranties ]");
		return PATH + "optionsAndWarranties";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping("loadLearnMorePopup")
	public String loadLearnMorePopup(ResourceRequest request,
			ResourceResponse response, Model model,
			PortletSession session) throws Exception{
		
		
		List<Bundle> productBundle = (List<Bundle>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		String bundleId=request.getParameter("bundleId");
		List<String> partNumber= new ArrayList<String>();
		for(Bundle bundle:productBundle){
			if(bundleId !=null && bundleId.equalsIgnoreCase(bundle.getBundleId())){
					for(Part part:bundle.getPartList()){
						partNumber.add(part.getPartNumber());
					}
				
			}
		}
		model.addAttribute("partNumber",partNumber);
		List<LearnMoreForm> learnMore = new ArrayList<LearnMoreForm>();
		for(String partNo:partNumber){
			List bulletList = new ArrayList();
			List marketingList = new ArrayList();
			List techSpecList = new ArrayList();
			try{
				bulletList=LPMDService.getBulletList(request,partNo);
				marketingList=LPMDService.getMarketingList(request,partNo);
				techSpecList=LPMDService.getTechSpecList(request,partNo);
				
			}catch(Exception e){
				throw new LGSRuntimeException("Error is LPMD Call" + e.getMessage());
			}
			
			LearnMoreForm learnMoreForm= ControllerUtil.getLearnMoreDetails(bulletList,marketingList,techSpecList);
			learnMore.add(learnMoreForm);
		}
		model.addAttribute("learnMoreForm", learnMore);
		LOGGER.debug("[ out loadLearnMorePopup ]");
		return PATH + "learnMorePopUp";
	}
}