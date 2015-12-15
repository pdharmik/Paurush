package com.lexmark.services.portlet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

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
	 */
	@ResourceMapping("loadSuppliesList")
	public String showSuppliesList(ResourceRequest request,
			ResourceResponse response,Model model,PortletSession session) {
		LOGGER.debug(("[ In  loadSuppliesList ]"));
		

		//need to call product type list for drop down
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));//This is for temporary purpose
	CatalogListContract contract=ContractFactory.getProductTypeContract(request);
ObjectDebugUtil.printObjectContent(contract, LOGGER);

		try{
		CatalogListResult result=catalogService.retrievePrinterTypesB2B(contract);
			List<ListOfValues> productType=result.getLovList();
			//List<ListOfValues> productType=new GenerateMockData().generatePrintersList();
			model.addAttribute("productType",productType);
			
		}catch(Exception e){
			LOGGER.error("[Error occured ]"+e.getCause());
		}
		
		return PATH+"suppliesList";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@ResourceMapping("loadSuppliesProduct")
	public String showSuppliesProduct(ResourceRequest request,
			ResourceResponse response) {
		LOGGER.debug(("[ In  loadSuppliesProduct ]"));

		return PATH+"suppliesProduct";
	}
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 */
	@ResourceMapping("retrieveSupplyGrid")
	public String retrieveSupplyGrid(ResourceRequest request,
			ResourceResponse response,Model model,PortletSession session) {
		LOGGER.debug(("[ In  retrieveSupplyGrid ]"));
		
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));//This is for temporary purpose
		CatalogListContract contract=ContractFactory.getPrinterAccessoriesContract(request);
		
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
		}
		model.addAttribute(PunchoutConstants.SUPPLY_ITEMS, partsList);
		model.addAttribute(PunchoutConstants.TOTAL_COUNT, result.getTotalCount());
		model.addAttribute(PunchoutConstants.START_POS, contract.getStartRecordNumber());
		response.setContentType("text/xml");
		return PATH+"supplyGridXML";
		
		
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("retrieveProductModel")
	public void getProductModels(ResourceRequest request,ResourceResponse response){
		LOGGER.debug("Enter get product model");
		//need to call product type list for drop down
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(), request));//This is for temporary purpose
		CatalogListContract contract=ContractFactory.getProductModelContract(request);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		List<ListOfValues> productModel=null;
		try{
			CatalogListResult result=catalogService.retrievePrinterTypesB2B(contract);
			productModel=result.getLovList();			
			LOGGER.debug(String.format("[Lov list size is %s]",productModel==null?"0":productModel.size()));
		}catch(Exception e){
			LOGGER.error("[Error occured ]"+e.getCause());
		}finally{
			ControllerUtil.prepareResponse(response, JsonUtil.generateProductModelJSON(productModel));
			LOGGER.debug("Exit get product model");	
		}
	}
	
}
