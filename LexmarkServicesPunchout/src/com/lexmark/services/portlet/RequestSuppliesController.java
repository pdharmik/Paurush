package com.lexmark.services.portlet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.PriceResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.form.ShoppingCartForm;
import com.lexmark.services.mock.GenerateMockData;
import com.lexmark.services.servlet.LoadAccountInformation;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.JsonUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.URLImageUtil;



/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: RequestSuppliesController.java
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
public class RequestSuppliesController {
	private static Logger LOGGER = LogManager
			.getLogger(RequestSuppliesController.class);
	private static final String PATH="requests/supplies/";
	
	@Autowired
	private LoadAccountInformation allAccountInformation;
	
	@Autowired
	private	OrderSuppliesCatalogService catalogService;
	
	@Autowired
	private GlobalService globalService;
	@Autowired 
	private RetrievePriceService retrievePriceService;
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@ResourceMapping("loadSuppliesList")
	public String showSuppliesList(ResourceRequest request,
			ResourceResponse response,Model model,PortletSession session) throws InterruptedException, ExecutionException {
		LOGGER.debug(("[ In  loadSuppliesList ]"));
		
		session.setAttribute("forGlobalSearch",false);
		//need to call product type list for drop down
		
		//starting here
		String supplierId = ControllerUtil.getSupplierId(session);
		List<PunchoutAccount> punchoutAccountList = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(supplierId), request);
		LOGGER.debug("punchAcntList size in showPrinterList is === "+punchoutAccountList.size());
		ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
		List<FutureTask<CatalogListResult>> taskList = new ArrayList<FutureTask<CatalogListResult>>();
		int i =0;
		List<ListOfValues> productType = new ArrayList<ListOfValues>();
		while(i<punchoutAccountList.size()){
			final PunchoutAccount punchoutAccount =  punchoutAccountList.get(i);
			LOGGER.debug(i+"th element = ============= = List aggreement id "+punchoutAccountList.get(i).getAgreementId()+" object aggrement id is "+punchoutAccount.getAgreementId()+" List contract no "+punchoutAccountList.get(i).getContractNumber()+" object contract no is "+punchoutAccount.getContractNumber()+" list sold to number is "+punchoutAccountList.get(i).getSoldTo()+" object sold to number is "+punchoutAccount.getSoldTo());
			request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchoutAccountList.get(i));
			final CatalogListContract contract=ContractFactory.getProductTypeContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			FutureTask<CatalogListResult> futureTask = new FutureTask<CatalogListResult>(new Callable<CatalogListResult>() {
	            public CatalogListResult call() {
	                try {
						return catalogService.retrievePrinterTypesB2B(contract);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						return new CatalogListResult();
					}
	            }
	        });
	        taskList.add(futureTask);
	        executor.execute(futureTask);	
	        i++;
			}
		LOGGER.debug("task list size is "+taskList.size());
		Map<String,List<String>> printerTypeMap = new HashMap<String, List<String>>();
		
		for(int j=0; j<taskList.size();j++){
			LOGGER.debug("in for taskList lov size list is "+taskList.get(j).get().getLovList().size()+" for "+j+"th task");
			LOGGER.debug(j+"th task aggreement is "+taskList.get(j).get().getAgreementId()+" === ");
			productType.addAll(taskList.get(j).get().getLovList());
			for(int k=0;k<taskList.get(j).get().getLovList().size();k++){
				List<String> contactNoAndAggrementIdList = new ArrayList<String>();
				LOGGER.debug("value is "+taskList.get(j).get().getLovList().get(k).getValue()+" and name is "+taskList.get(j).get().getLovList().get(k).getName());			
				if(null != printerTypeMap.get(taskList.get(j).get().getLovList().get(k).getValue()) && !printerTypeMap.get(taskList.get(j).get().getLovList().get(k).getValue()).equals("")){
					contactNoAndAggrementIdList.addAll(printerTypeMap.get(taskList.get(j).get().getLovList().get(k).getValue()));
				}
				contactNoAndAggrementIdList.add(punchoutAccountList.get(j).getAccountId()+","+punchoutAccountList.get(j).getAgreementId()+","+punchoutAccountList.get(j).getContractNumber()+","+punchoutAccountList.get(j).getSoldTo());
				printerTypeMap.put(taskList.get(j).get().getLovList().get(k).getValue(),contactNoAndAggrementIdList);
			}
			
		}
		
		model.addAttribute("productType",printerTypeMap);
		session.setAttribute("printerTypeMap", printerTypeMap, PortletSession.APPLICATION_SCOPE);
		
		
		/*request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));//This is for temporary purpose
		CatalogListContract contract=ContractFactory.getProductTypeContract(request);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);

		try{
		CatalogListResult result=catalogService.retrievePrinterTypesB2B(contract);
			List<ListOfValues> productType=result.getLovList();
			//List<ListOfValues> productType=new GenerateMockData().generatePrintersList();
			model.addAttribute("productType",productType);
			
		}catch(Exception e){
			LOGGER.error("[Error occured ]"+e.getCause());
		}*/
		
		return PATH+"suppliesList";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@ResourceMapping("loadSuppliesProduct")
	public String showSuppliesProduct(ResourceRequest request,
			ResourceResponse response, Model model, PortletSession session) {
		LOGGER.debug(("[ In  loadSuppliesProduct ]"));
			
		return PATH+"suppliesProduct";
	}
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@ResourceMapping("retrieveSupplyGrid")
	public String retrieveSupplyGrid(ResourceRequest request,
			ResourceResponse response,Model model,PortletSession session) throws InterruptedException, ExecutionException {
		LOGGER.debug(("[ In  retrieveSupplyGrid ]"));
		String supplierId = ControllerUtil.getSupplierId(session);
		
		
		if(null != session.getAttribute("supplyItems")){
			session.removeAttribute("supplyItems");
		}
		
		//started here
		LOGGER.debug("starting of parallel call");
		Map<String,List<String>> productModelMap = new HashMap<String, List<String>>();
		if(null != session.getAttribute("productModelMap",PortletSession.APPLICATION_SCOPE)){
			productModelMap = (Map<String, List<String>>) session.getAttribute("productModelMap",PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("productModelMap is not null "+productModelMap);
		}
		List<OrderPart> partsList=new ArrayList<OrderPart>();
		CatalogListResult result =null;
		int totalCount = 0;
		 if(StringUtils.isNotBlank(request.getParameter("partNum"))){
			 LOGGER.debug("For searching part number flow");
			 List<PunchoutAccount> punchoutAccountList = new ArrayList<PunchoutAccount>();
			 punchoutAccountList = ControllerUtil.getPunchoutAccountList(allAccountInformation.getAllAccountList(supplierId), request);			
			 ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
			 List<FutureTask<CatalogListResult>> taskList = new ArrayList<FutureTask<CatalogListResult>>();
			 int i =0;			 
			 while(i<punchoutAccountList.size()){
				 	LOGGER.debug("aggrement id for "+i+"th account is "+punchoutAccountList.get(i).getAgreementId()+" and contract no is "+punchoutAccountList.get(i).getContractNumber());
				    PunchoutAccount punchoutAccount = punchoutAccountList.get(i);
				 	final CatalogListContract contract=ContractFactory.getPrinterAccessoriesContract(request,punchoutAccount);
					final ResourceRequest newRequest = request;									
					FutureTask<CatalogListResult> futureTask = new FutureTask<CatalogListResult>(new Callable<CatalogListResult>() {
			            public CatalogListResult call() {
			                return retrieveAccessoriesB2bCall(newRequest,contract);
			            }
			        });
			        taskList.add(futureTask);
			        executor.execute(futureTask);	
			        i++;
					}
			 
			 LOGGER.debug("tasklist size is "+taskList.size());
			 for(int j=0; j<taskList.size();j++){
					LOGGER.debug(j+"th tasklist element partlist size is "+taskList.get(j).get().getPartsList().size());
					partsList.addAll(taskList.get(j).get().getPartsList());
					totalCount = totalCount + taskList.get(j).get().getTotalCount();
			 }
			 
		 }
		 else{
			 LOGGER.debug("after selecting product model");
			 PunchoutAccount punchoutAccount = new PunchoutAccount();
			 List<String>productModelDetailsList = productModelMap.get(request.getParameter("prodMod"));
			 Map<String,List<String>> printerTypeMap = new HashMap<String, List<String>>();
			 printerTypeMap = (Map<String, List<String>>) session.getAttribute("printerTypeMap",PortletSession.APPLICATION_SCOPE);
			 LOGGER.debug("printerTypeMap is "+printerTypeMap);
			 LOGGER.debug("productModelMap is "+productModelMap);
			 List<String> printerTypeDetailsLst = new ArrayList<String>();
			 LOGGER.debug("product type selected is "+request.getParameter("prodTyp"));
			 LOGGER.debug("product model selected is "+request.getParameter("prodMod"));
			 printerTypeDetailsLst = printerTypeMap.get(request.getParameter("prodTyp"));
			 for(int i =0;i<productModelDetailsList.size();i++){
				 for(int j=0;j<printerTypeDetailsLst.size();j++){
					 if(productModelDetailsList.get(i).equalsIgnoreCase(printerTypeDetailsLst.get(j))){
						 String [] accountDetails = productModelDetailsList.get(i).split(",");
						 punchoutAccount.setAccountId(accountDetails[0]);
						 punchoutAccount.setAgreementId(accountDetails[1]);
						 punchoutAccount.setContractNumber(accountDetails[2]);
						 punchoutAccount.setSoldTo(accountDetails[3]);
						 request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, punchoutAccount);
					 }
				 }
			 }
			 CatalogListContract contract=ContractFactory.getPrinterAccessoriesContract(request,punchoutAccount);
			 result = retrieveAccessoriesB2bCall(request,contract);
			 if(null != result){
				 partsList = result.getPartsList();
				 totalCount = result.getTotalCount();
			 }
		 }
		
		/*CatalogListContract contract=ContractFactory.getPrinterAccessoriesContract(request);
		
		List<OrderPart> partsList=null;
		CatalogListResult result =null;
		CrmSessionHandle crmSessionHandle=null;
		try{
			crmSessionHandle= globalService
			.initCrmSessionHandle(PortalSessionUtil
					.getSiebelCrmSessionHandle(request));
			contract.setSessionHandle(crmSessionHandle);
			
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			
			 result=catalogService.retrieveAccessoriesB2b(contract);
			 LOGGER.debug("here");
			 partsList=result.getPartsList();
			// partsList=new GenerateMockData().generateSuppliesProductList();
			 for(OrderPart parts : partsList){
				 LOGGER.debug("PARTS PARTNUMBER--"+parts.getPartNumber()+"--CATALOG ID--"+parts.getCatalogId()+"--CONTRACTLINHEITEMID--"+parts.getContractLineItemId());
				 
			 }
			 PriceResult bundlePriceResult = null;
			 bundlePriceResult=ControllerUtil.getPriceForParts(contract.getContractNumber(),partsList,retrievePriceService);
			 
			 for(Price price:bundlePriceResult.getPriceOutputList()){
					for(OrderPart parts:partsList)
					{
						if(parts.getContractLineItemId()!=null && parts.getContractLineItemId().equalsIgnoreCase(price.getContractLineItemId())){
							parts.setPrice((new BigDecimal(price.getPrice())));
						}
					}
				}
			 for(OrderPart parts:partsList)
				{
				 if(parts.getPartNumber()!=null){
					 LOGGER.debug("PART NUMBER--------->"+parts.getPartNumber());
					 parts.setPartImg(URLImageUtil.getPartImageFromLocal(parts.getPartNumber()));
					 LOGGER.debug("IMAGE URL------------"+parts.getPartImg());
					
				 }
				}
			 session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, partsList);
			 ControllerUtil.updateItemsWithCartData(session, request.getParameter("cType"), "");
		}catch(Exception e){
			LOGGER.error("[Error occured ]"+e.getCause());
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
			LOGGER.debug(String.format("[Parts list size is %s]",partsList==null?"0":partsList.size()));		
		}*/
		
		session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, partsList);
		
		LOGGER.debug("partlist size is "+partsList.size());
		model.addAttribute(PunchoutConstants.SUPPLY_ITEMS, partsList);
		model.addAttribute(PunchoutConstants.TOTAL_COUNT, totalCount);
		model.addAttribute(PunchoutConstants.START_POS, 0);
		response.setContentType("text/xml");
		return PATH+"supplyGridXML";
		
		
	}
	/**
	 * @param request 
	 * @param response 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@ResourceMapping("retrieveProductModel")
	public void getProductModels(ResourceRequest request,ResourceResponse response) throws InterruptedException, ExecutionException{
		LOGGER.debug("Enter get product model");
		//need to call product type list for drop down
		//request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));//This is for temporary purpose
		
		  // start here
		  Map<String,List<String>> printerTypeMap = new HashMap<String, List<String>>();
		  PortletSession session = request.getPortletSession();
		  printerTypeMap = (Map<String, List<String>>) session.getAttribute("printerTypeMap", PortletSession.APPLICATION_SCOPE);
	      
	      LOGGER.debug("printerMap from session in Request printer controller for bundle call is from session in Request printer controller for bundle call is = "+printerTypeMap);
	      List<String> printerTypeDetailsList = new ArrayList<String>();
	      List<PunchoutAccount> punchoutAccountList = new ArrayList<PunchoutAccount>();
	      if(null != printerTypeMap && !printerTypeMap.isEmpty()){
	    	  LOGGER.debug("printer map is not null and not empty in bundle call");
	    	  printerTypeDetailsList = printerTypeMap.get(request.getParameter("prodTyp"));
	    	  LOGGER.debug("printerTypeDetails List size is = "+printerTypeDetailsList.size());
	    	  if(null != printerTypeDetailsList && !printerTypeDetailsList.isEmpty()){
	    		  LOGGER.debug("printerDetailsList is not null and not empty");	    		  
	    		  for(int i=0; i<printerTypeDetailsList.size();i++){
	    			  PunchoutAccount punchoutAccount = new PunchoutAccount();
	    			  LOGGER.debug("inside innermost For");
	    			  String printerDetails[];
	    			  printerDetails = printerTypeDetailsList.get(i).split(",");
	    			  LOGGER.debug(i+"th account id  is = "+printerDetails[0]+" and aggrement id is = "+printerDetails[1]+" and contract no is = "+printerDetails[2]+" sold to no is = "+printerDetails[3]);
	    			  punchoutAccount.setAccountId(printerDetails[0]);
	    			  punchoutAccount.setAgreementId(printerDetails[1]);
	    			  punchoutAccount.setContractNumber(printerDetails[2]);
	    			  punchoutAccount.setSoldTo(printerDetails[3]);	    			  
	    			  punchoutAccountList.add(punchoutAccount);
	    		  }		    		 
	    		  
	    	  }
	    	   
	      }
	      Map<String,List<String>> productModelMap = new HashMap<String, List<String>>();
	      LOGGER.debug("before Future Task punchoutAccountList size is "+punchoutAccountList.size());
			ExecutorService executor = Executors.newFixedThreadPool(punchoutAccountList.size());
			List<FutureTask<CatalogListResult>> taskList = new ArrayList<FutureTask<CatalogListResult>>();
			int i =0;
			List<ListOfValues> productModel= new ArrayList<ListOfValues>();
			int totalCount = 0;
			while(i<punchoutAccountList.size()){
				request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT,punchoutAccountList.get(i));
				final CatalogListContract contract=ContractFactory.getProductModelContract(request);
				ObjectDebugUtil.printObjectContent(contract, LOGGER);
				FutureTask<CatalogListResult> futureTask = new FutureTask<CatalogListResult>(new Callable<CatalogListResult>() {
		            public CatalogListResult call() {
		                try {
							return catalogService.retrievePrinterTypesB2B(contract);
						} catch (Exception e) {
							LOGGER.debug("in catch retrievePrinterTypesB2B");
							return new CatalogListResult();
						}
		            }
		        });
		        taskList.add(futureTask);
		        executor.execute(futureTask);	
		        i++;
				}
			LOGGER.debug("tasklist size is "+taskList.size());
			for(int j=0; j<taskList.size();j++){
				LOGGER.debug(j+"th tasklist element productModel size is "+taskList.get(j).get().getLovList().size());
				productModel.addAll(taskList.get(j).get().getLovList());
				List<String> contactNoAndAggrementId = new ArrayList<String>();
				for(int k=0;k<taskList.get(j).get().getLovList().size();k++){
					if(null != productModelMap.get(taskList.get(j).get().getLovList().get(k).getValue()) && !productModelMap.get(taskList.get(j).get().getLovList().get(k).getValue()).equals("")){
						contactNoAndAggrementId.addAll(productModelMap.get(taskList.get(j).get().getLovList().get(k).getValue()));
					}
					contactNoAndAggrementId.add(punchoutAccountList.get(j).getAccountId()+","+punchoutAccountList.get(j).getAgreementId()+","+punchoutAccountList.get(j).getContractNumber()+","+punchoutAccountList.get(j).getSoldTo());
					productModelMap.put(taskList.get(j).get().getLovList().get(k).getValue(),contactNoAndAggrementId);
				}
				
				totalCount = totalCount + taskList.get(j).get().getTotalCount();
			}
	      
			ControllerUtil.prepareResponse(response, JsonUtil.generateProductModelJSON(productModel));
			session.setAttribute("productModelMap", productModelMap, PortletSession.APPLICATION_SCOPE);
	
	}
	
	public CatalogListResult retrieveAccessoriesB2bCall(ResourceRequest request,CatalogListContract contract){
		
		
		PortletSession session = request.getPortletSession();
		
		List<OrderPart> partsList=null;
		CatalogListResult result =null;
		CrmSessionHandle crmSessionHandle=null;
		try{
			crmSessionHandle= globalService
			.initCrmSessionHandle(PortalSessionUtil
					.getSiebelCrmSessionHandle(request));
			contract.setSessionHandle(crmSessionHandle);
			
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			
			 result=catalogService.retrieveAccessoriesB2b(contract);
			 LOGGER.debug("here");
			 partsList=result.getPartsList();
			
			 for(OrderPart parts : partsList){
				 LOGGER.debug("PARTS PARTNUMBER--"+parts.getPartNumber()+"--CATALOG ID--"+parts.getCatalogId()+"--CONTRACTLINHEITEMID--"+parts.getContractLineItemId());
				 
			 }
			 
			 if(null != partsList && partsList.size()>0){
				 
			
			 PriceResult bundlePriceResult = new PriceResult();
			 //bundlePriceResult=ControllerUtil.getPriceForParts(contract.getContractNumber(),partsList,retrievePriceService);
			 
			 for(Price price:bundlePriceResult.getPriceOutputList()){
					for(OrderPart parts:partsList)
					{
						if(parts.getContractLineItemId()!=null && parts.getContractLineItemId().equalsIgnoreCase(price.getContractLineItemId())){
							parts.setPrice((new BigDecimal(price.getPrice())));
						}
					}
				}
			 for(OrderPart parts:partsList)
				{
				 if(parts.getPartNumber()!=null){
					 LOGGER.debug("PART NUMBER--------->"+parts.getPartNumber());
					 parts.setPartImg(URLImageUtil.getPartImageFromLocal(parts.getPartNumber()));
					 LOGGER.debug("IMAGE URL------------"+parts.getPartImg());
					
				 }
				}
			 }
			 //session.setAttribute(PunchoutConstants.PRODUCT_BUNDLE, partsList);
		}catch(Exception e){
			LOGGER.error("[Error occured ]"+e.getCause());
			result = new CatalogListResult();
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
			LOGGER.debug(String.format("[Parts list size is %s]",partsList==null?"0":partsList.size()));	
		}
		return result;
		
	}
	
}
