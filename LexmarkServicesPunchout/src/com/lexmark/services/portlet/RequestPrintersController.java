package com.lexmark.services.portlet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.GlobalCatalogListContract;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.BundleListResult;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.GlobalCatalogListResult;
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
import com.lexmark.util.PropertiesMessageUtil;

import org.apache.commons.lang.StringEscapeUtils;


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
	public String showPrinterList(ResourceRequest request,
			ResourceResponse response, Model model,PortletSession session) throws InterruptedException, ExecutionException {
		LOGGER.debug("in show printers");
				
		LOGGER.debug(("[ In  showPrinterList ]"));
		request.setAttribute("callType", "showPrinterList");
		
		//changes for parallel call start
		//request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));
		
		LOGGER.debug("size for all account list is "+allAccountInformation.getAllAccountList().size());
		List<PunchoutAccount> punchoutAccountList = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(), request);
		LOGGER.debug("punchAcntList size in showPrinterList is === "+punchoutAccountList.size());
		ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
		List<FutureTask<CatalogListResult>> taskList = new ArrayList<FutureTask<CatalogListResult>>();
		int i =0;
		
		while(i<punchoutAccountList.size()){
			final PunchoutAccount punchoutAccount =  punchoutAccountList.get(i);
			LOGGER.debug(i+"th element = ============= = List aggreement id "+punchoutAccountList.get(i).getAgreementId()+" object aggrement id is "+punchoutAccount.getAgreementId()+" List contract no "+punchoutAccountList.get(i).getContractNumber()+" object contract no is "+punchoutAccount.getContractNumber()+" list sold to number is "+punchoutAccountList.get(i).getSoldTo()+" object sold to number is "+punchoutAccount.getSoldTo());
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
			LOGGER.debug("in for taskList lov size list is "+taskList.get(j).get().getLovList().size()+" for "+j+"th task");
			LOGGER.debug(j+"th task aggreement is "+taskList.get(j).get().getAgreementId()+" === ");			
			for(int k=0;k<taskList.get(j).get().getLovList().size();k++){
				List<String> contactNoAndAggrementId = new ArrayList<String>();
				LOGGER.debug("value is "+taskList.get(j).get().getLovList().get(k).getValue()+" and name is "+taskList.get(j).get().getLovList().get(k).getName());			
				if(null != printerMap.get(taskList.get(j).get().getLovList().get(k).getValue()) && !printerMap.get(taskList.get(j).get().getLovList().get(k).getValue()).equals("")){
					contactNoAndAggrementId.addAll(printerMap.get(taskList.get(j).get().getLovList().get(k).getValue()));
				}			
				contactNoAndAggrementId.add(punchoutAccountList.get(j).getAccountId()+","+punchoutAccountList.get(j).getAgreementId()+","+punchoutAccountList.get(j).getContractNumber()+","+punchoutAccountList.get(j).getSoldTo());
				printerMap.put(taskList.get(j).get().getLovList().get(k).getValue(),contactNoAndAggrementId);
			}
		}
		// end
		LOGGER.debug("Printer map is ==================== "+printerMap);	
		
		model.addAttribute(PunchoutConstants.BUNDLE_LIST, printerMap);
		session.setAttribute("printerMap", printerMap, PortletSession.APPLICATION_SCOPE);
		String fromAriba = session.getAttribute("fromAriba", PortletSession.APPLICATION_SCOPE).toString();
		LOGGER.debug("fromAriba from session in Request Printer Controller --------------- "+fromAriba);
		model.addAttribute("fromAriba", fromAriba);
		
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
	public String showPrinterProductsRender(RenderRequest request, RenderResponse response, Model model){
		LOGGER.debug("[in showPrinterProductsRender]");
		PortletSession session = request.getPortletSession();
		String fromAriba = session.getAttribute("fromAriba", PortletSession.APPLICATION_SCOPE).toString();
		session.setAttribute("forGlobalSearch",false);
		LOGGER.debug("from ariba in showPrinterProductsRender = "+fromAriba);
		model.addAttribute("fromAriba", fromAriba);
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
		String fromAriba = session.getAttribute("fromAriba", PortletSession.APPLICATION_SCOPE).toString();
		LOGGER.debug("fromAriba from session in Request Printer Controller --------------- "+fromAriba);
		model.addAttribute("fromAriba", fromAriba);
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
		
		if(null != session.getAttribute("supplyItems")){
			session.removeAttribute("supplyItems");
		}
		
		//CrmSessionHandle crmSessionHandle=null;
		
		//crmSessionHandle= globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		/* List<Bundle> draftBundleItems = new ArrayList<Bundle>();

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
        }*/
        // end for draft SR
		
		// starting here
	      LOGGER.debug("-------->>>in controller<<<---------------");
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
		    			  LOGGER.debug("inside innermost For");
		    			  String printerDetails[];
		    			  printerDetails = printerDetailsList.get(i).split(",");
		    			  LOGGER.debug(i+"th account id  is = "+printerDetails[0]+" and aggrement id is = "+printerDetails[1]+" and contract no is = "+printerDetails[2]+" sold to no is = "+printerDetails[3]);
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
	    	  punchoutAccountList = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(), request);
	      }
	      	LOGGER.debug("before Future Task punchoutAccountList size is "+punchoutAccountList.size());
			ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
			List<FutureTask<BundleListResult>> taskList = new ArrayList<FutureTask<BundleListResult>>();
			int i =0;
			List<Bundle> bundleItems = new ArrayList<Bundle>();
			int totalCount = 0;
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
			LOGGER.debug("tasklist size is "+taskList.size());
			for(int j=0; j<taskList.size();j++){
				LOGGER.debug(j+"th tasklist element bundleItems size is "+taskList.get(j).get().getBundleList().size());
				bundleItems.addAll(taskList.get(j).get().getBundleList());
				totalCount = totalCount + taskList.get(j).get().getTotalCount();
			}
		
		model.addAttribute(PunchoutConstants.BUNDLE_ITEMS, bundleItems);		
		model.addAttribute(PunchoutConstants.TOTAL_COUNT, totalCount);		
		//model.addAttribute(PunchoutConstants.START_POS, contract.getStartRecordNumber());		
		model.addAttribute(PunchoutConstants.START_POS, 0);		
		session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundleItems);
		String fromAriba = session.getAttribute("fromAriba", PortletSession.APPLICATION_SCOPE).toString();
		LOGGER.debug("fromAriba from session in Request Printer Controller before going to bundlegridxml.jsp--------------- "+fromAriba);
		model.addAttribute("fromAriba", fromAriba);
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
		String fromAriba = session.getAttribute("fromAriba", PortletSession.APPLICATION_SCOPE).toString();
		LOGGER.debug("fromAriba from session in Request Printer Controller before going to bundlegridxml.jsp--------------- "+fromAriba);
		model.addAttribute("fromAriba", fromAriba);
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
		List<String> partDetails= new ArrayList<String>(); // added to add description to the partnumber (displaying in learn ore pop up)
		String partDescription = "";
		for(Bundle bundle:productBundle){
			if(bundleId !=null && bundleId.equalsIgnoreCase(bundle.getBundleId())){
					for(Part part:bundle.getPartList()){
						LOGGER.debug("part number is "+part.getPartNumber()+" and Part description is "+part.getDescription());
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
		List<LearnMoreForm> learnMore = new ArrayList<LearnMoreForm>();
		/*for(String partNo:partNumber){
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
		model.addAttribute("learnMoreForm", learnMore);*/
		LOGGER.debug("[ out loadLearnMorePopup ]");
		return PATH + "learnMorePopUp";
	}
	
	public BundleListResult serviceCallForBundleList(ResourceRequest request,PunchoutAccount punchoutAccount){	
		
		
		CrmSessionHandle crmSessionHandle=null;
		
		crmSessionHandle= globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		List<Bundle> bundleItems=new ArrayList<Bundle>();	       
	        	HardwareCatalogContract contract = ContractFactory.retrieveBundleListContract(request,punchoutAccount);
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
	           
	            if(result!=null){
	            	LOGGER.debug("result is not null");
	            	bundleItems.addAll(result.getBundleList());
	            }
	            PortletSession session = request.getPortletSession();
	            session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundleItems);        
	            ControllerUtil.updateItemsWithCartData(session, request.getParameter("cType"), "");
	            
	            /*if("Draft".equalsIgnoreCase(request.getParameter("rTyp"))){
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
	            }*/
	            LOGGER.debug("result bundle list size is ========== "+result.getBundleList().size());
	            if((null != result && null != result.getBundleList()) && result.getBundleList().size() > 0){
	            	List<Bundle> bundlePriceList = new ArrayList<Bundle>();
					try {
						bundlePriceList = ControllerUtil.getBundlePrice(loadPriceInformation,request,punchoutAccount);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LOGGER.debug("in price call exception");
					}
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
	       }
	        
		return result;
	}


	@ResourceMapping("loadGlobalSearchList")
	public String globalSearch(ResourceRequest request, ResourceResponse response, Model model){
		LOGGER.debug("in global search method");
		LOGGER.debug("searchNumber is "+request.getParameter("searchNumber"));
		String searchNumber = request.getParameter("searchNumber");
		GlobalCatalogListContract globalCatalogListContract = new GlobalCatalogListContract();
		PortletSession session = request.getPortletSession();
		if(null != session.getAttribute("supplyItems",PortletSession.APPLICATION_SCOPE)){
			session.removeAttribute("supplyItems");
		}
		session.setAttribute(PunchoutConstants.CART_SESSION, ControllerUtil.initShoppingCart());
		PunchoutAccount punchoutAccount = ControllerUtil.getPunchoutAccount((List<PunchoutAccount>) session.getAttribute(PunchoutConstants.ACCOUNT_LIST, PortletSession.APPLICATION_SCOPE), request);
		LOGGER.debug("sold to no is "+punchoutAccount.getSoldTo());
		LOGGER.debug("aggreement id is "+punchoutAccount.getAgreementId());
		LOGGER.debug("contract no is "+punchoutAccount.getContractNumber());
		
		globalCatalogListContract.setAccSoldToNumber(punchoutAccount.getSoldTo());
		globalCatalogListContract.setAccAgreementId(punchoutAccount.getAgreementId());
		globalCatalogListContract.setAccContractNumber(punchoutAccount.getContractNumber());
		globalCatalogListContract.setAccEffectiveDate(new Date());
		globalCatalogListContract.setAccPartNumber(searchNumber);
		globalCatalogListContract.setBunAgreementId(punchoutAccount.getAgreementId());
		globalCatalogListContract.setBunSoldToNumber(punchoutAccount.getSoldTo());
		globalCatalogListContract.setBunContractNumber(punchoutAccount.getContractNumber());
		globalCatalogListContract.setBunEffectiveDate(new Date());
		globalCatalogListContract.setBunPartNumber(searchNumber);
		globalCatalogListContract.setSupSoldToNumber(punchoutAccount.getSoldTo());
		globalCatalogListContract.setSupAgreementId(punchoutAccount.getAgreementId());
		globalCatalogListContract.setSupContractNumber(punchoutAccount.getContractNumber());
		globalCatalogListContract.setSupEffectiveDate(new Date());
		globalCatalogListContract.setSupPartNumber(searchNumber);
		
		
		CrmSessionHandle crmSessionHandle=null;
		GlobalCatalogListResult result = null;
		LOGGER.debug("in global search method 1");
		List<OrderPart> suppliesList = new ArrayList<OrderPart>();
		List<Bundle> bundleList = new ArrayList<Bundle>();
		try {
			crmSessionHandle= globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			globalCatalogListContract.setSessionHandle(crmSessionHandle);
			
			LOGGER.debug("in global search method 2");
			ObjectDebugUtil.printObjectContent(globalCatalogListContract, LOGGER);
			result = orderSuppliesCatalogService.retrieveGlobalCatalogListB2B(globalCatalogListContract);
			LOGGER.debug("Accessories List size is = "+result.getAccessoriesList().size());
			LOGGER.debug("Bundle list size is = "+result.getBundleList().size());
			LOGGER.debug("Supplies list size is = "+result.getSuppliesList().size());
			//LOGGER.debug("LOV list size is = "+result.getLovList().size());
			LOGGER.debug("part list size is = "+result.getPartsList().size());
			
			LOGGER.debug("result bundle list size is ========== "+result.getBundleList().size());
            if((null != result && null != result.getBundleList()) && result.getBundleList().size() > 0){
            	List<Bundle> bundlePriceList = new ArrayList<Bundle>();
				try {
					bundlePriceList = ControllerUtil.getBundlePrice(loadPriceInformation,request,punchoutAccount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOGGER.debug("in price call exception");
				}
	           if(bundlePriceList != null){
		            for(Bundle bundlePrice:bundlePriceList){
		    			for(Bundle bundle:result.getBundleList()){
		    				if(bundlePrice.getBundleId().equalsIgnoreCase(bundle.getBundleId())){
		    					LOGGER.debug("PRICe-------"+bundlePrice.getPrice());
		    					bundle.setPrice(bundlePrice.getPrice());
		    					bundle.setCurrency(bundlePrice.getCurrency());					
		    				}
		    			}
		    		}
	           }
       }
			suppliesList = result.getPartsList();
			bundleList = result.getBundleList();
			for(int i=0; i<result.getPartsList().size();i++){
				LOGGER.debug(i+"th part description is "+result.getPartsList().get(i).getDescription());
				LOGGER.debug(i+"th part Number is "+result.getPartsList().get(i).getPartNumber());
				LOGGER.debug(i+"th order Quantity is "+result.getPartsList().get(i).getOrderQuantity());
				LOGGER.debug(i+"th price is "+result.getPartsList().get(i).getPrice());
			}
		} catch (Exception e) {
			LOGGER.debug("in Request PrintersController");
			result = new GlobalCatalogListResult();
			// TODO Auto-generated catch block
			e.getMessage();
			LOGGER.debug("==============================================================");
			e.printStackTrace();
		}
		finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		LOGGER.debug("exiting global search method");
		LOGGER.debug("exiting global search list");
		
		float timezoneOffset = 0;
		if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
			 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		}
		String fromAriba = session.getAttribute("fromAriba", PortletSession.APPLICATION_SCOPE).toString();
		LOGGER.debug("fromAriba from session in Request Printer Controller --------------- "+fromAriba);
		model.addAttribute(PunchoutConstants.SUPPLY_ITEMS, result.getPartsList());
		model.addAttribute(PunchoutConstants.TOTAL_COUNT, result.getPartsList().size());
		model.addAttribute(PunchoutConstants.START_POS, 0);
		String suppliesListXml = buildXMLForsuppliesList(suppliesList,result.getPartsList().size(),0,timezoneOffset);
		locale = request.getLocale();
		String bundleListXml = buildXMLForBundleList(bundleList,result.getBundleList().size(),0,timezoneOffset,fromAriba,locale);
		LOGGER.debug("suppliesListXml is "+suppliesListXml);
		LOGGER.debug("bundleListXml is "+bundleListXml);
		
		if(null != result.getPartsList() && result.getPartsList().size() > 0){
			ControllerUtil.updateItemsWithCartData(session, "supplies", "");
		}
		if(null != result.getBundleList() && result.getBundleList().size() > 0){
			ControllerUtil.updateItemsWithCartData(session, "printers", "");
		}
		
		
		List <Bundle> bundleItems = new ArrayList<Bundle>();
		List <OrderPart> supplyItems = new ArrayList<OrderPart>();
		
		bundleItems = result.getBundleList(); 
		supplyItems = result.getPartsList();
		session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, bundleItems);
		session.setAttribute("supplyItems", supplyItems);
		session.setAttribute("forGlobalSearch", true);
		
		model.addAttribute("fromAriba", fromAriba);
		model.addAttribute("forGlobalSearch", "true");
		model.addAttribute("suppliesListXml", suppliesListXml);
		model.addAttribute("bundleListXml", bundleListXml);
		
		return "requests/globalSearchList";
	}
	
	
	public String buildXMLForsuppliesList(List<OrderPart> suppliesList, int totalCount, int startRecordNumber, float timezoneOffset){
		LOGGER.debug("buildXMLForSuppliesList Enter ---------------------- "+suppliesList.size());
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">");
		for (int i=0; i<suppliesList.size(); i++) {
				LOGGER.debug("in "+i+"th loop");
				 OrderPart orderPart = suppliesList.get(i);
				 xml = xml.append(" <row id=\""+i+"\">");				 
				// xml.append("  <cell><![CDATA[<div id='supply"+(i+1)+"'> <div class='supplies-table-cntnr'><table  border='0' cellspacing='0' cellpadding='0' class='discrptn-table'><tr><td style='border-right:1px solid #8c8c8c;' class='w10 table-txt'><img src='<html:imagesPath/>product-list-1.jpg' width='40' height='26' /> </td><td style='border-right:1px solid #8c8c8c;' class='table-txt'>"+orderPart.getDescription()+"</td><td class='w10 table-txt'>"+orderPart.getPartNumber()+"</td><td class='w10 table-txt' style='border-right:1px solid #8c8c8c;border-left:1px solid #8c8c8c'>"+orderPart.getPrice()+"</td> <td class='w10 table-txt'><input type='text' id='quantity"+orderPart.getPartNumber()+"' value='' style='width:25px; text-align:center; border:1px solid #d8d8d8;'/></td><td class='w10 table-txt'><input type='button' id='cartButton"+orderPart.getPartNumber()+"' onClick='moveToCartSupplies(\'"+orderPart.getPartNumber()+"\',this.id)' class='button' value='Add To Cart' /></td></tr></table></div></div>]]></cell>");
				 xml.append("  <cell><![CDATA[<div id=\"supply"+(i+1)+"\"> <div class=\"supplies-table-cntnr\"><table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"discrptn-table\"><tr><td style=\"border-right:1px solid #8c8c8c;\" class=\"w10 table-txt\"><img src=\"/LexmarkServicesPunchout/images/product-list-1.jpg\" width=\"40\" height=\"26\" /> </td><td style=\"border-right:1px solid #8c8c8c;\" class=\"table-txt\">"+orderPart.getDescription()+"</td><td class=\"w10 table-txt\">"+orderPart.getPartNumber()+"</td><td class=\"w10 table-txt\" style=\"border-right:1px solid #8c8c8c;border-left:1px solid #8c8c8c\">"+orderPart.getPrice()+"</td> <td class=\"w10 table-txt\"><input type=\"text\" id=\"quantity"+orderPart.getPartNumber()+"\" value=\"\" style=\"width:25px; text-align:center; border:1px solid #d8d8d8;\"/></td><td class=\"w10 table-txt\"><input type=\"button\" id=\"cartButton"+orderPart.getPartNumber()+"\" onClick=\"moveToCartSupplies('"+orderPart.getPartNumber()+"',this.id)\" class=\"button\" value=\"Add To Cart\" /></td></tr></table></div></div>]]></cell>");
				 xml = xml.append(" </row>");		
			
		}
		xml = xml.append("</rows>");
		
		return StringEscapeUtils.escapeJavaScript(xml.toString());
	}
	
	public String buildXMLForBundleList(List<Bundle> bundleList, int totalCount, int startRecordNumber, float timezoneOffset,String fromAriba, Locale locale){
		LOGGER.debug("buildXMLForBundleList Enter ---------------------- "+bundleList.size());
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">");
		for (int i=0; i<bundleList.size(); i++) {
			xml.append("<row id=\""+bundleList.get(i).getBundleId()+"\">");
			xml.append("<cell><![CDATA[getOptionsAndWarranties&bundleId="+bundleList.get(i).getBundleId()+"&cartType=printers]]></cell>");
			//xml.append("<cell><![CDATA[222]]></cell>");
			xml.append("<cell><![CDATA[<div id=\"bundle"+i+"\"> <div class=\"printer-cntnr\"><div class=\"prdctpge-printerimg\"><img src=\"/LexmarkServicesPunchout/images/product-printer.jpg\" width=\"89\" height=\"72\" alt=\"Mono Laser Printers\"></div><div class=\"printer-details-cntnr\"><br/><span style=\"font-size:24px;font-weight:bold;\" id=\"printerId\">"+bundleList.get(i).getConfigId()+"</span><br/><span style=\"font-size:12px;\">"+bundleList.get(i).getMpsDescription()+"</span><br/><br/><span style=\"font-size:12px;\">"+bundleList.get(i).getBundleName()+"</span><br/>");
			xml.append("<p style=\"width:90%; padding:0; font-size:12px; line-height:17px;\">"+bundleList.get(i).getDescription()+"<a href = \"javascript:learnMorePopup('"+bundleList.get(i).getBundleId()+"');\">&nbsp;&nbsp;<B>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME, "shoppingCart.printers.link", locale)+"</B></a></p>");
			xml.append("<table width=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"discrptn-table-parts\"><tr><td style=\"background-color:#A9A9A9 !important\" class=\"table-title\" style=\"font-size: 15px;\"><b>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME, "orderSupplies.placeOrderHeader.partNumber", locale)+"</b></td><td style=\"background-color:#A9A9A9 !important\" class=\"table-title\" style=\"font-size: 15px;\"><b>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME, "orderSupplies.placeOrderHeader.description", locale)+"</b></td><td style=\"background-color:#A9A9A9 !important\" class=\"table-title\" style=\"font-size: 15px;\"><b>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME, "requestInfo.heading.Qty", locale)+"</b></td></tr>");
			if(null == bundleList.get(i).getPartList() || bundleList.get(i).getPartList().size()==0){
				xml.append("<tr><td bgcolor=\"#e6e6f0\" color=\"#c82424\">No Records Available</td><td bgcolor=\"#e6e6f0\"></td><td bgcolor=\"#e6e6f0\"></td></tr>");
			}
			else{
				LOGGER.debug("for "+i+"th bundle part list size is ======== "+bundleList.get(i).getPartList().size());
				for(int j=0; j<bundleList.get(i).getPartList().size(); j++){
					xml.append("<tr class=\"");
					if(j%2 != 0){
						xml.append("altRow\"><td style=\"border-right:1px solid #8c8c8c;\" class=\"table-txt\">"+bundleList.get(i).getPartList().get(j).getPartNumber()+"</td><td class=\"table-txt\" style=\"border-right:1px solid #8c8c8c;\">"+bundleList.get(i).getPartList().get(j).getDescription()+"</td> <td class=\"table-txt\">"+bundleList.get(i).getPartList().get(j).getOrderQuantity()+"</td>");
					}
					else{
						xml.append("\"><td style=\"border-right:1px solid #8c8c8c;\" class=\"table-txt\">"+bundleList.get(i).getPartList().get(j).getPartNumber()+"</td><td class=\"table-txt\" style=\"border-right:1px solid #8c8c8c;\">"+bundleList.get(i).getPartList().get(j).getDescription()+"</td> <td class=\"table-txt\">"+bundleList.get(i).getPartList().get(j).getOrderQuantity()+"</td>");
					}
					xml.append("</tr>");
				}
			}
			
			xml.append("</table></div>");
			// part list table ended
				if(fromAriba.equalsIgnoreCase("true")){
							if(null != bundleList.get(i).getPrice()){
								xml.append("<div class=\"quntity-cart-cntnr\"><div class=\"quntyty-cntnr\"><span>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME, "shoppingCart.printers.quantity", locale)+"</span>");
									if(null != bundleList.get(i).getBundleQty() && !bundleList.get(i).getBundleQty().equals("0")){
										xml.append("<input type=\"text\" name=\"Quantity\" id=\"quantity"+bundleList.get(i).getBundleId()+"\" value=\""+bundleList.get(i).getBundleQty()+"\"  style=\"width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;\">");
									}
									else{
										xml.append("<input type=\"text\" name=\"Quantity\" id=\"quantity"+bundleList.get(i).getBundleId()+"\"  style=\"width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;\">");
									
									}
								xml.append("</div>");
								xml.append("<div class=\"price-btn-cntnr\"><div class=\"price-btn-cntnr\">"+bundleList.get(i).getPrice()+"");
									if(null != bundleList.get(i).getPrice()){
										xml.append("("+bundleList.get(i).getCurrency()+")");
									}
								xml.append("</div>");
									if(null != bundleList.get(i).getBundleQty() && !bundleList.get(i).getBundleQty().equals("0")){
										xml.append("<input name=\"Update Cart\" title=\"\" id=\"cartButton"+bundleList.get(i).getBundleId()+"\" type=\"button\" class=\"button\" value=\"Update Cart\" border=\"0\" onclick=\"moveToCart('"+bundleList.get(i).getBundleId()+"',this.id)\">");
									}
									else{
										xml.append("<input name=\"Add to Cart\" title=\"\" id=\"cartButton"+bundleList.get(i).getBundleId()+"\" type=\"button\" class=\"button\" value=\"Add to Cart\" border=\"0\" onclick=\"moveToCart('"+bundleList.get(i).getBundleId()+"',this.id)\">");
									}
								xml.append("</div></div>");					
							}
							else{
								xml.append("<div class=\"quntity-cart-cntnr\"><div class=\"quntyty-cntnr\"><span>Quantity</span>");
									if(null != bundleList.get(i).getBundleQty() && !bundleList.get(i).getBundleQty().equals("0")){
										xml.append(" <input type=\"text\" name=\"Quantity\" id=\"quantity"+bundleList.get(i).getBundleId()+"\" value=\""+bundleList.get(i).getBundleQty()+"\"  style=\"width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;\">");
									}
									else{
										xml.append("<input type=\"text\" name=\"Quantity\"");
										if(bundleList.get(i).getBillingModel().equalsIgnoreCase("Ship and Bill")){
											xml.append("disabled=\"disabled\"");
										}
										xml.append("id=\"quantity"+bundleList.get(i).getBundleId()+"\"  style=\"width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;\">");
									}
								xml.append("</div><div class=\"quntity-cart-cntnr\"><div class=\"price-btn-cntnr\" style=\"width: 90%!important;\">Price Not Available</div> </div>");
								if(null != bundleList.get(i).getBundleQty() && !bundleList.get(i).getBundleQty().equals("0")){
									xml.append("<input name=\"Update Cart\" title=\"\" id=\"cartButton"+bundleList.get(i).getBundleId()+"\" type=\"button\" class=\"button\" value=\"Update Cart\" border=\"0\" onclick=\"moveToCart('"+bundleList.get(i).getBundleId()+"',this.id)\">");
								}
								else{
									xml.append("<input name=\"Add to Cart\" title=\"\" id=\"cartButton"+bundleList.get(i).getBundleId()+"\" type=\"button\" class=\"button\" value=\"Add to Cart\" border=\"0\" onclick=\"moveToCart('"+bundleList.get(i).getBundleId()+"',this.id)\">");
								}
								xml.append("</div></div>");
							}
				}
			
			xml.append("</div></div>]]></cell>");
		
			xml.append("</row>");
		}
		xml = xml.append("</rows>");
		return StringEscapeUtils.escapeJavaScript(xml.toString());
	}
	
}