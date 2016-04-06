package com.lexmark.services.portlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.portlet.PortletRequest;
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
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.GlobalCatalogListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderHardwareService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.cache.PriceCacheController;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.servlet.LoadAccountInformation;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.JsonUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;


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
	
	@Autowired
	private PriceCacheController priceCacheController;
	
	@Autowired 
	private RetrievePriceService retrievePriceService;
	private static Logger LOGGER = LogManager
			.getLogger(RequestPrintersController.class);

	private static final String PATH = "requests/product/";	
	private static String BUNDLENAME = "com.lexmark.services.resources.messages";
	private Locale locale;
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@ResourceMapping("loadPrinterList")
	public void showPrinterList(ResourceRequest request,
			ResourceResponse response, Model model,PortletSession session) throws InterruptedException, ExecutionException {
     	LOGGER.debug(("[ In  showPrinterList ]"));
		request.setAttribute("callType", "showPrinterList");
		
		//changes for parallel call start
		
		String supplierId = ControllerUtil.getSupplierId(session);
		List<PunchoutAccount> punchoutAccountList = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(supplierId), request);
		LOGGER.debug("punchAcntList size in showPrinterList is === "+punchoutAccountList.size());
		ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
		List<FutureTask<CatalogListResult>> taskList = new ArrayList<FutureTask<CatalogListResult>>();
		int i =0;
		
		while(i<punchoutAccountList.size()){
			final PunchoutAccount punchoutAccount =  punchoutAccountList.get(i);
			request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchoutAccountList.get(i));
			final ResourceRequest newRequest = request;
			FutureTask<CatalogListResult> futureTask = new FutureTask<CatalogListResult>(new Callable<CatalogListResult>() {
	            public CatalogListResult call() {
	                return ControllerUtil.amindRetrievePrinterTypes(newRequest,orderSuppliesCatalogService,punchoutAccount);
	            }
	        });
	        taskList.add(futureTask);
	        executor.execute(futureTask);	
	        i++;
			}
		
		Map<String,List<String>> printerMap = new HashMap<String, List<String>>();
		for(int j=0; j<taskList.size();j++){
			for(int k=0;k<taskList.get(j).get().getLovList().size();k++){
				List<String> contactNoAndAggrementId = new ArrayList<String>();
				if(null != printerMap.get(taskList.get(j).get().getLovList().get(k).getValue()) && !printerMap.get(taskList.get(j).get().getLovList().get(k).getValue()).equals("")){
					contactNoAndAggrementId.addAll(printerMap.get(taskList.get(j).get().getLovList().get(k).getValue()));
				}			
				contactNoAndAggrementId.add(punchoutAccountList.get(j).getAccountId()+","+punchoutAccountList.get(j).getAgreementId()+","+punchoutAccountList.get(j).getContractNumber()+","+punchoutAccountList.get(j).getSoldTo());
				printerMap.put(taskList.get(j).get().getLovList().get(k).getValue(),contactNoAndAggrementId);
			}
		}

		LOGGER.debug("Printer map is ==================== "+printerMap);	
		
		model.addAttribute(PunchoutConstants.BUNDLE_LIST, printerMap);
		session.setAttribute("printerMap", printerMap, PortletSession.APPLICATION_SCOPE);
		
		
		
		ControllerUtil.prepareResponse(response, JsonUtil.convertPrinterListJSON(printerMap));
		
		
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
	public void retrieveBundleGrid(ResourceRequest request,
			ResourceResponse response, Model model,
			PortletSession session) throws Exception{
		LOGGER.debug("IN RETRIEVE BUNDLE-------------");
		
		PunchoutAccount punchAcnt = ControllerUtil.getPunchoutAccount((List<PunchoutAccount>) session.getAttribute(PunchoutConstants.ACCOUNT_LIST, PortletSession.APPLICATION_SCOPE), request);
		String supplierId = ControllerUtil.getSupplierId(session);
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchAcnt);
		session.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchAcnt, PortletSession.APPLICATION_SCOPE);
		
		if(null != session.getAttribute("supplyItems")){
			session.removeAttribute("supplyItems");
		}
		
	
		  Map<String,List<String>> printerMap = new HashMap<String, List<String>>();
	      if(null != session.getAttribute("printerMap",PortletSession.APPLICATION_SCOPE)){
	    	 printerMap = (Map<String,List<String>>)session.getAttribute("printerMap",PortletSession.APPLICATION_SCOPE); 
	      }
	      
	      LOGGER.debug("printerMap from session in Request printer controller for bundle call is = "+printerMap);
	      List<String> printerDetailsList = new ArrayList<String>();
	      List<PunchoutAccount> punchoutAccountList = new ArrayList<PunchoutAccount>();
	      
	      String fromPrinterList = request.getParameter("fromPrinterList");
	      LOGGER.debug("fromPrinterList is = "+fromPrinterList);
	      
	      if((null != fromPrinterList) && "true".equalsIgnoreCase(fromPrinterList)){      
		      if(null != printerMap && !printerMap.isEmpty()){
		    	  LOGGER.debug("printer map is not null and not empty in bundle call");
		    	  printerDetailsList = printerMap.get(request.getParameter("pTyp"));
		    	
		    	  if(null != printerDetailsList && !printerDetailsList.isEmpty()){
		    		  LOGGER.debug("printerDetailsList is not null and not empty");	    		  
		    		  for(int i=0; i<printerDetailsList.size();i++){
		    			  PunchoutAccount punchoutAccount = new PunchoutAccount();
		    			  String printerDetails[];
		    			  printerDetails = printerDetailsList.get(i).split(",");
		    			  punchoutAccount.setAccountId(printerDetails[0]);
		    			  punchoutAccount.setAgreementId(printerDetails[1]);
		    			  punchoutAccount.setContractNumber(printerDetails[2]);
		    			  punchoutAccount.setSoldTo(printerDetails[3]);	    			  
		    			  punchoutAccountList.add(punchoutAccount);
		    		  }		    		 
		    		  
		    	  }
		    	   
		      }
	      }
	      else{
	    	  punchoutAccountList = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(supplierId), request);
	      }
	      	LOGGER.debug("before Future Task punchoutAccountList size is "+punchoutAccountList.size());
			ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
			List<FutureTask<BundleListResult>> taskList = new ArrayList<FutureTask<BundleListResult>>();
			int i =0;
			List<Bundle> bundleItems = new ArrayList<Bundle>();
			while(i<punchoutAccountList.size()){				
				final ResourceRequest newRequest = request;
				final PunchoutAccount punchoutAccount = punchoutAccountList.get(i);				
				FutureTask<BundleListResult> futureTask = new FutureTask<BundleListResult>(new Callable<BundleListResult>() {
		            public BundleListResult call() {
		                return serviceCallForBundleList(newRequest,punchoutAccount);
		            }
		        });
		        taskList.add(futureTask);
		        executor.execute(futureTask);	
		        i++;
				}
			for(int j=0; j<taskList.size();j++){
					bundleItems.addAll(taskList.get(j).get().getBundleList());
			}
	
			
		session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundleItems);
						
		LOGGER.debug(("[ Out  prepareBundle ]"));
		ControllerUtil.writeResponse(response, JsonUtil.convertBundleToJson(bundleItems));
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
	public void retrieveOptionsWarranties(ResourceRequest request,
			@RequestParam(value="bundleId", required=false) String bundleId,
			@RequestParam(value="cartType", required=false) String cartType,
			@RequestParam(value="cNum",required=false) String contractnumber,
			ResourceResponse response, Model model,
			PortletSession session){
		LOGGER.debug("[ in generate options warranties ]");
		request.setAttribute("callType", "retrieveBundleGrid");
		String supplierId = ControllerUtil.getSupplierId(session);
		PunchoutAccount punchAcnt=ControllerUtil.getPunchoutAccountByContractNumber(allAccountInformation.getAllAccountList(supplierId), contractnumber);
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchAcnt);
		//Set the list to bundle which is present in session
		List<OrderPart> accessories=(ControllerUtil.amindRetrieveAccessoriesB2B(request,orderSuppliesCatalogService,globalService)).getAccessoriesList();
		
		ControllerUtil.updateBundlesWithOptionswarranties(session, bundleId, accessories, cartType);
		
		String json=null;
		Map<String, Price> priceMap=null;
		
		doPrice(punchAcnt, request, null, accessories);
		json =JsonUtil.updateAndGenerateAccessories(accessories,bundleId) ;
		ControllerUtil.writeResponse(response, json);
		
		
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
		List<String> partDetails= new ArrayList<String>(); // added to add description to the partnumber (displaying in learn ore pop up)
		String partDescription = "";
		for(Bundle bundle:productBundle){
			if(bundleId !=null && bundleId.equalsIgnoreCase(bundle.getBundleId())){
					for(Part part:bundle.getPartList()){
						partDescription = part.getDescription();
						if(null != partDescription){
							partDetails.add(part.getPartNumber()+" - "+partDescription);
							partNumber.add(part.getPartNumber());
						}
						else{
							partDetails.add(part.getPartNumber());
							partNumber.add(part.getPartNumber());
						}
					}				
			}
		}
		model.addAttribute("partNumber",partDetails);
			
		LOGGER.debug("[ out loadLearnMorePopup ]");
		return PATH + "learnMorePopUp";
	}
	
	private BundleListResult serviceCallForBundleList(ResourceRequest request,PunchoutAccount punchoutAccount){	
		
		
		CrmSessionHandle crmSessionHandle=null;
		
		crmSessionHandle= globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		List<Bundle> bundleItems=new ArrayList<Bundle>();	       
	        	HardwareCatalogContract contract = ContractFactory.retrieveBundleListContract(request,punchoutAccount);
	    		contract.setSessionHandle(crmSessionHandle);
	            
	            LOGGER.debug("PRINTING LOGGER");
	            ObjectDebugUtil.printObjectContent(contract,LOGGER);
	            
	            BundleListResult result = null;
	            try{
	            	result = orderHardwareService.retrievePrinterBundleListB2B(contract);
	            }catch(Exception e){
	            	LOGGER.debug("Exception occured" + e.getMessage());
	            	//e.printStackTrace();
	            }finally{
	            	globalService.releaseSessionHandle(crmSessionHandle);
	            } 
	            List<Bundle> bundles=result.getBundleList();
	            LOGGER.debug("result bundle list size is ========== "+bundles.size());
	            if(null != bundles ){
	            	bundleItems.addAll(bundles);
	            }
	           doPrice(punchoutAccount, request, bundleItems,null);
	        
	            
	            
		return result;
	}


	@ResourceMapping("loadGlobalSearchList")
	public void globalSearch(ResourceRequest request, ResourceResponse response, Model model){
		LOGGER.debug("In global search method");
		
		
		PortletSession session = request.getPortletSession();
		
		session.setAttribute(PunchoutConstants.CART_SESSION, ControllerUtil.initShoppingCart());
		PunchoutAccount punchoutAccount  = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(ControllerUtil.getSupplierId(session)), request).get(0);
		ObjectDebugUtil.printObjectContent(punchoutAccount, LOGGER);	
		
		
		CrmSessionHandle crmSessionHandle=null;
		GlobalCatalogListResult result = null;
		LOGGER.debug("in global search method 1");
		List<OrderPart> suppliesList = null,
				accessories=null;
		GlobalCatalogListContract globalCatalogListContract =ContractFactory.createGlobalSearchListContract(punchoutAccount, request);
		try {
			crmSessionHandle= globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			globalCatalogListContract.setSessionHandle(crmSessionHandle);
			
			ObjectDebugUtil.printObjectContent(globalCatalogListContract, LOGGER);
			result = orderSuppliesCatalogService.retrieveGlobalCatalogListB2B(globalCatalogListContract);
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Accessories List size is = "+result.getAccessoriesList().size()
						+"Bundle list size is = "+result.getBundleList().size()
						+"Supplies list size is = "+result.getSuppliesList().size()
						+"part list size is = "+result.getPartsList().size());
			}
			
			
		} catch (Exception e) {
			LOGGER.debug("Exception occured in Global Search .. "+e.getMessage());
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		List<Bundle> bundles=result.getBundleList();
		suppliesList = result.getPartsList();
		accessories=result.getAccessoriesList();
		
        if(null != bundles && bundles.size() > 0){
        	doPrice(punchoutAccount, request, bundles,null);           
        }
        if(null!=accessories && accessories.size() > 0){
        	doPrice(punchoutAccount, request, null,accessories); 
        }
        
     	
		session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundles);
		session.setAttribute(PunchoutConstants.PRODUCT_OPTIONSWARRANTIES, accessories);
		
		session.setAttribute("supplyItems", suppliesList);
		session.setAttribute("forGlobalSearch", true);
		
	
		String bundleJSON=JsonUtil.convertBundleToJson(bundles);
		String accessoriesJSON=JsonUtil.updateAndGenerateAccessories(accessories, "");
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"bundle\":")
		.append(bundleJSON)
		.append(",\"optionsWarranties\":")
		.append(accessoriesJSON)
		.append("}");
		ControllerUtil.writeResponse(response, sb.toString());
				
	}
	
	/**
	 * @param punchoutAccount
	 * @param request
	 * @param bundleItems
	 */
	private void doPrice(PunchoutAccount punchoutAccount,PortletRequest request,
			List<Bundle> bundleItems,List<OrderPart> accessories){
		
		 if(ControllerUtil.getFromAriba(request.getPortletSession())){
			 Set<String> contractLineItems=null;
			 if(bundleItems!=null){
				 contractLineItems=ControllerUtil.getBundleContractLineItems(bundleItems);
			 }else{
				 contractLineItems=ControllerUtil.getPartsContractLineItem(accessories);
			 }
         	
     		PriceContract priceContract =ContractFactory.getPriceContract(contractLineItems, punchoutAccount.getContractNumber());
        	Map<String,Price> priceMap= priceCacheController.getPrice(priceContract);
        	if(bundleItems!=null){
        		ControllerUtil.updateBundleWithPrice(priceMap,bundleItems);
        	}else{
        		ControllerUtil.updatePartsWithPrice(priceMap,accessories);
        	}
         }
	}

	
}