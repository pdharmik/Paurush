package com.lexmark.services.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.w3c.dom.Document;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.hook.filter.PunchoutSetupResponse;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.service.api.RequestTypeServiceB2B;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.domain.CartItem;
import com.lexmark.services.domain.SessionInformation;
import com.lexmark.services.form.ShoppingCartForm;


public class ControllerUtil {
	private static Logger LOGGER = LogManager.getLogger(ControllerUtil.class);
	private static HashMap<String, String> printerPartTypeMap = null;
	private static HashMap<String, String> certProductMap = null;
	private static String CONFIG_FILE_NAME = "/ariba.properties";
	private static final String PRINTER_PART_KEY_PREFIX = "ariba.PrinterPartType.";
	private static final String CERT_PRODUCT_KEY_PREFIX = "ariba.CertProductType.";
	private static Properties aribaProperty;
	private static final String NCAL_ACNT ="NCAL HealthConnect Certified";
	private static final String SCAL_ACNT ="SCAL HealthConnect Certified";
	private static final String SQL_GET_SIEBEL_LOCALIZATION ="SELECT sl.display_Value as displayValue FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
			" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
			" and l.supported_locale_code = :local_code and s.siebel_Value = :siebel_value and s.option_type = :option_type";

	public static Properties getConfigProperties() {
		if (aribaProperty == null) {
			Properties props = new Properties();
			InputStream in;
			try {
				in = PunchoutSetupResponse.class
						.getResourceAsStream(CONFIG_FILE_NAME);
				props.load(in);
				in.close();
			} catch (IOException e) {
				new RuntimeException(
						"Fail to get ariba.properties Property configuration file.",
						e);
			}

			aribaProperty = props;
		}
		return aribaProperty;
	}

	public static String getPrinterPartType(String partType) {
		if (printerPartTypeMap == null) {
			initPrinterLinksMap();
		}
		String type = printerPartTypeMap.get(partType) != null ? printerPartTypeMap.get(partType) : "";
		LOGGER.debug("TYPE--->"+type);
		return type;
	}

	public static void initPrinterLinksMap() {
		LOGGER.debug("Inside initPrinterLinksMap");
		printerPartTypeMap = new HashMap<String, String>();
		Properties props = getConfigProperties();
		for (Object key : props.keySet()) {
			String keyString = (String) key;
			if (keyString.indexOf(PRINTER_PART_KEY_PREFIX) > -1) {
				String value = props.getProperty(keyString);
				String fileExtensionKey = keyString
						.substring(PRINTER_PART_KEY_PREFIX.length());
				printerPartTypeMap.put(fileExtensionKey, value);
			}
		}
	}

	/**
	 * @param fields
	 * @param destination
	 * @param source
	 */
	public static void copyBeanFields(String fields[], Object destination,
			Object source) {

		for (String field : fields) {

			Object valueRead = null;
			try {
				valueRead = PropertyUtils.getProperty(source, field);
				PropertyUtils.setProperty(destination, field, valueRead);
			} catch (IllegalAccessException e) {
				LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
			} catch (InvocationTargetException e) {
				LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
			} catch (NoSuchMethodException e) {
				LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
			}
			
			
		}
		
		
	}
	

	/**
	 * @param source 
	 * @param propertyName 
	 * @return Object 
	 */
	public static Object readProperty(Object source,String propertyName){
		if(source == null || propertyName == null){
			return null;
		}
		Object valueRead = null;
		try {
			valueRead = PropertyUtils.getProperty(source, propertyName);
		} catch (IllegalAccessException e) {
			LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
		}
		return valueRead;		
	}
	
	/**
	 * @param source 
	 * @param propertyName 
	 * @param valueToSet 
	 */
	public static void setProperty(Object source,String propertyName, Object valueToSet){
		if(source == null || propertyName == null ){
			return ;
		}		
		try {
			PropertyUtils.setProperty(source, propertyName, valueToSet);
		} catch (IllegalAccessException e) {
			LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			LOGGER.debug("Exception occured" + e.getMessage());
				//e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @param cartItems 
	 * @param productIDValue 
	 * @param comparisonField 
	 * @return Object 
	 */
	public static Object findInList(List<Object> cartItems,String productIDValue,String comparisonField){
		
		if(cartItems==null){
			return null;
		}
		
		for(Object cartItem:cartItems){
			if(cartItem==null){				
				continue;
			}
			
			if(ControllerUtil.compare(cartItem,comparisonField, productIDValue)){
					return cartItem;
			}
			
			
			
			
		}
		return null;
	}
	
	/**
	 * @param value 
	 * @param fieldName 
	 * @param compareToValue 
	 * @return boolean 
	 */
	public static boolean compare(Object value,String fieldName,String compareToValue){
		String readValue=(String)ControllerUtil.readProperty(value, fieldName);
		if(readValue!=null && readValue.equalsIgnoreCase(compareToValue)){
			return true;
		}
		return false;
	}
	
	/**
	 * @param oldSessionBundle 
	 * @return Bundle 
	 */
	public static Bundle createNewBundle(Bundle oldSessionBundle){
		String PART_DETAILS[]={"partNumber","description","orderQuantity"};
		String BUNDLE_DETAILS[]={"bundleId","bundleProductId","price","description","orderParts"};
		
		Bundle newCartBundle=new Bundle();
		List<Part> newPartList=new ArrayList<Part>();
		newCartBundle.setPartList(newPartList);
		ControllerUtil.copyBeanFields(BUNDLE_DETAILS, newCartBundle, oldSessionBundle);
		for(Part _part:oldSessionBundle.getPartList()){
			Part newCartPart=new Part();
			ControllerUtil.copyBeanFields(PART_DETAILS, newCartPart, _part);
			newPartList.add(newCartPart);
		}
		return newCartBundle;
		
	}
	
	/**
	 * @param response 
	 * @param content 
	 */
	public static void prepareResponse(ResourceResponse response,String content){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			LOGGER.error("[error while getting response writer]");
			response.setContentType("text/html");
			return;
		}
		response.setContentType("text/html");
		out.print(content);
		out.flush();
		out.close();
	}
	
	/**
	 * @param cartList 
	 * @param prodIdValue 
	 * @param compareField 
	 */
	public static void removeFromList(List<CartItem> cartList,String prodIdValue){
		if(cartList==null){
			return ;
		}
		int i=-1;
		boolean found=false;
		for(CartItem _cart:cartList){
			i++;			
			if(_cart.getItemId().equals(prodIdValue)){
				found=true;
				break;
			}
		}
		if(found){
			cartList.remove(i);
			LOGGER.debug("[removed Item from cart i="+i+" ] ");
			return;
		}
		LOGGER.debug("[item not found in cart] ");
	}
	
	/**
	 * @param cartBundle 
	 * @param priceField 
	 * @param qtyField 
	 * @return BigDecimal 
	 */
	public static BigDecimal calculateTotal(List<CartItem> cart){
		BigDecimal totalPrice=new BigDecimal("0");
			if(cart!=null){
			
	        for(CartItem _item:cart){
	        	BigDecimal price=_item.getPrice();
	        	String qty = _item.getQuantity();
	        	totalPrice=totalPrice.add(new BigDecimal(qty).multiply(price==null?new BigDecimal("0"):price));
	        }
		}
			return totalPrice;
	}
	
	
	/**
	 * @param request 
	 * @param service 
	 * @return CatalogListResult 
	 */
	public static CatalogListResult amindRetrievePrinterTypes(PortletRequest request,OrderSuppliesCatalogService service,PunchoutAccount punchoutAccount){
		CatalogListResult result=new CatalogListResult();
		CatalogListContract contract=ContractFactory.retirevePrinterTypes(request);
		contract.setAgreementId(punchoutAccount.getAgreementId());
		contract.setContractNumber(punchoutAccount.getContractNumber());
		contract.setSoldToNumber(punchoutAccount.getSoldTo());
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		try {
			result=service.retrievePrinterTypesB2B(contract);
		
		} catch (Exception e) {
			LOGGER.error("[Exception while getting printer types b2b ]"+e.getCause());
		}
		return result;
		
	}

	/**
	 * @param request 
	 * @param service 
	 * @param globalService 
	 * @return CatalogListResult 
	 */
	public static CatalogListResult amindRetrieveAccessoriesB2B(PortletRequest request,OrderSuppliesCatalogService service,GlobalService globalService){
		LOGGER.debug("IN amindRetrieveAccessoriesB2B");
		CatalogListResult result=new CatalogListResult();
		LOGGER.debug("call type set in request is ------->>>> "+request.getAttribute("callType"));
		CatalogListContract contract=ContractFactory.retirevePrinterTypes(request);
		CrmSessionHandle crmSessionHandle=null;
		crmSessionHandle= globalService
		.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		try {
			LOGGER.debug("b4 calling retrieveAccessoriesB2b");
			result = service.retrieveAccessoriesB2b(contract);
			LOGGER.debug("after calling retrieveAccessoriesB2b");
			
		} catch (Exception e) {
			LOGGER.error("[Exception while getting printer types b2b ]"+e.getCause());
		}
		LOGGER.debug("out amindRetrieveAccessoriesB2B");
		return result;
		
		
	}
	
	/**
	 * @param session 
	 * @param bundleId 
	 * @param accessories 
	 */
	public static void updateBundlesWithOptionswarranties(PortletSession session, String bundleId, 
			List<OrderPart> accessories, String cartType){
		List<Bundle> bundles = (List<Bundle>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		Bundle bundle=findWithinBundle(bundles, bundleId);
		bundle.setOrderParts(accessories);	
	}
	
	/**
	 * @param session 
	 * @param prodIdValue 
	 * @param qty 
	 * @param bundleId 
	 */
	public  static void addShoppingCartParts(PortletSession session, String prodIdValue,String qty,String bundleId,String cartType,String unspscCode,String marketingName){
		
		
		Map<String, ShoppingCartForm> _shoppingForm = (Map<String, ShoppingCartForm>) session.getAttribute(PunchoutConstants.CART_SESSION);		
		List<CartItem> cartItems=_shoppingForm.get(cartType).getCartItems();		
		
		//Check whether the item is in cart or not
		CartItem _cartItem=findWithinCartItems(cartItems, prodIdValue);
		if(_cartItem==null){
			LOGGER.debug(String.format(" [the product is not there in cart id = %s] ",prodIdValue));
			
			OrderPart orderPart=null;
			List<OrderPart> orderParts=null;
			if(StringUtils.isBlank(bundleId)){
				orderParts  = (List<OrderPart>) session.getAttribute(PunchoutConstants.PRODUCT_OPTIONSWARRANTIES);
				
			}else{
				List<Bundle> _productListSession  = (List<Bundle>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
				Bundle bundle=findWithinBundle(_productListSession, bundleId);
				orderParts=bundle.getOrderParts();							
			}	
			orderPart=findWithinOptions(orderParts, prodIdValue);
			_cartItem=createShoppingCartItem(orderPart);
			cartItems.add(_cartItem);
		}
		_cartItem.setQuantity(qty);
		_cartItem.setUnspscCode(unspscCode);
		_cartItem.setMarketingName(marketingName);
		
	}
	
	/**
	 * @param session 
	 * @param prodId 
	 * @param qty 
	 * 
	 * This method should only be used to add Bundles to the cart
	 * if required to add supplied item create separate method.
	 */
	public  static void addToShoppingCart(PortletSession session, String bundleId,String qty,String cartType,String unspscCode,String marketingName){
		
		List<Bundle> _productListSession = (List<Bundle>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		
		Map<String, ShoppingCartForm> _shoppingForm = (Map<String, ShoppingCartForm>) session.getAttribute(PunchoutConstants.CART_SESSION);
		List<CartItem> cartItems=_shoppingForm.get(cartType).getCartItems();
		
		//Check whether the item is in cart or not
		CartItem _cartItem=findWithinCartItems(cartItems, bundleId);
		
		if(_cartItem==null){
			LOGGER.debug(String.format(" [the product is not there in cart id = %s] ",bundleId));
			//This is new item to be added to cart
			//Prior to that need to get the data from the session list first
			Bundle bundle=findWithinBundle(_productListSession, bundleId);
			LOGGER.debug("Item Bundle "+bundle);
			bundle.setUnspscCode(unspscCode);
			LOGGER.debug("after unspsc" +cartItems );
			LOGGER.debug("length" +cartItems.size() );
			_cartItem=createShoppingCartItem(bundle);
			cartItems.add(_cartItem);
			
		}
		_cartItem.setQuantity(qty);
		_cartItem.setUnspscCode(unspscCode);
		_cartItem.setMarketingName(marketingName);
		
	}
	
	public static Bundle findWithinBundle(List<Bundle> bundles,String id){
		for(Bundle bundle:bundles){
			if(bundle.getBundleId().equalsIgnoreCase(id)){
				return bundle;
			}
		}
		return null;
	}
	
	public static CartItem findWithinCartItems(List<CartItem> cartItems,String id){
		for(CartItem cartItem:cartItems){
			if(cartItem.getItemId().equalsIgnoreCase(id)){
				return cartItem;
			}
		}
		return null;
	}
	
	public static OrderPart findWithinOptions(List<OrderPart> orderParts,String id){
		for(OrderPart part:orderParts){
			if(part.getPartNumber().equalsIgnoreCase(id)){
				return part;
			}
		}
		return null;
	}
	/*
	 * This method creates a bundle object and set
	 * its properties to be shown in the shopping cart details.
	 * 
	 * */
	public static CartItem createShoppingCartItem(Bundle bundle){
		CartItem cartItem= new CartItem();
		cartItem.setItemId(bundle.getBundleId());
		cartItem.setPrice(bundle.getPrice());
		cartItem.setItemName(bundle.getBundleName());
		cartItem.setItemDescription(bundle.getMpsDescription());
		cartItem.setConfigId(bundle.getConfigId());
		cartItem.setImgUrl(bundle.getImgUrl());
		cartItem.setItemType("bundle");
		cartItem.setShowOptions(true);
		cartItem.setProductId(bundle.getBundleProductId());
		cartItem.setContractNumber(bundle.getContractNumber());
		cartItem.setSapLineId(bundle.getSapLineID());
		return cartItem;		
	}
	/*
	 * This method creates a Part object and set
	 * its properties to be shown in the shopping cart details.
	 * 
	 * */
	public static CartItem createShoppingCartItem(OrderPart part){
		LOGGER.debug("in createShoppingCartItem, unspsc code is "+part.getUnspscCode()+" product is is "+part.getProductId()+" part number is "+part.getPartNumber());
		CartItem cartItem= new CartItem();
		cartItem.setItemId(part.getPartNumber());
		cartItem.setPrice(part.getPrice());
		cartItem.setProductId(part.getProductId());
		//cartItem.setItemName(part.get);
		cartItem.setItemDescription(part.getDescription());
		//cartItem.setConfigId(part.getConfigId());
		cartItem.setImgUrl(part.getPartImg());
		cartItem.setItemType("options");
		return cartItem;		
	}
	/**
	 * @param destination 
	 * @param field 
	 * @param addQty 
	 */
	public static void updateQty(Object destination,String field,String addQty){
		ControllerUtil.setProperty(destination, field, (Object)(StringUtils.isNotBlank(addQty)==true?addQty:"0"));
	}
	
	/**
	 * @param contract 
	 * @param requestTypeB2bService 
	 * @return RequestResult 
	 * @throws Exception 
	 */
	public static RequestResult requestHistoryDetails(RequestContract contract,RequestTypeServiceB2B requestTypeB2bService) throws Exception{
		RequestResult result=null;
		result=requestTypeB2bService.retrieveSupplyRequestDetailB2B(contract);
		return result;
		
	}
	
	/**
	 * @param accounts 
	 * @return PunchoutAccount 
	 */
	public static PunchoutAccount getPunchoutAccount(List<PunchoutAccount> accounts, PortletRequest request){
		LOGGER.debug("IN PUNCHOUT ACCOUNTS");
		if(accounts!=null && accounts.size()!=0){
			LOGGER.debug("IN PUNCHOUT ACCOUNTS INSIDE IF");
			
		String cNum = StringUtils.isNotBlank(request.getParameter("cNum"))==true?request.getParameter("cNum"):"";
		String cName = StringUtils.isNotBlank(request.getParameter("cName"))==true?request.getParameter("cName"):"";
		
		List<String> certifiedCNames = new ArrayList<String>();
		certifiedCNames.add(NCAL_ACNT);
		certifiedCNames.add(SCAL_ACNT);
		
		if(accounts.get(0)!=null){
			for(int i = 0; i < accounts.size(); i++)
			{
				PunchoutAccount ac = accounts.get(i);
				if(cName.length() > 0 && ac.getContractName().contains(cName))
					cNum = ac.getContractNumber();
								
				if((cNum.trim().length() > 0 && ac.getContractNumber().equalsIgnoreCase(cNum)) ||
					(cNum.trim().length() == 0 && ac.getContractName().equalsIgnoreCase("base")))
					return ac;
			}
			return accounts.get(0);
			}else{
			return new PunchoutAccount();
			}
		}
		return new PunchoutAccount();
	}
	
	
	public static PunchoutAccount getPunchoutAccountByContractNumber(List<PunchoutAccount> accounts, String contractnumber){
		if(accounts.get(0)!=null){
			for(PunchoutAccount acc:accounts){
				if(StringUtils.isNotBlank(contractnumber) && StringUtils.isNotBlank(acc.getContractNumber())){
					return acc;
				}
			}
		}else{
			return new PunchoutAccount();
		}
		return new PunchoutAccount();
	}

	/**
	 * @return ShoppingCartForm 
	 */
	public static Map<String, ShoppingCartForm> initShoppingCart(){
		Map<String, ShoppingCartForm> formList = new HashMap<String, ShoppingCartForm>();
		
		ShoppingCartForm printersForm=new ShoppingCartForm();
		//printersForm.setCartType("printers");
		printersForm.setCartItems(new ArrayList<CartItem>());
		formList.put("printers", printersForm);
		
		ShoppingCartForm suppliesForm=new ShoppingCartForm();
		//suppliesForm.setCartType("supplies");
		suppliesForm.setCartItems(new ArrayList<CartItem>());
		formList.put("supplies", suppliesForm);
				
		return formList;
	}
	
		

	/**
	 * @param session 
	 * @return SessionInformation 
	 */
	public static SessionInformation populateSessionInformation(PortletSession session){
		String sessionParams[]={"buyerCookie","supplierCookie",
				"identity","operation","supplierId",
				"networkUserId","sharedSecret",
				"userId","duns","formPostURL"};
		SessionInformation infor=new SessionInformation();
		HashMap<String, String> aribaParamMap=(HashMap<String, String>)session.getAttribute("aribaParamMap",PortletSession.APPLICATION_SCOPE);
		for(String sessionParam:sessionParams){
			String val=aribaParamMap.get(sessionParam);
			LOGGER.debug(String.format("[param name =%s param value=%s ]",sessionParam,val ));
			setProperty(infor, sessionParam, val==null?null:val);
		}
		infor.setShoppingCartForm((Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION));
		return infor;
	}
	

	/**
	 * @param document 
	 * @return String 
	 * @throws Exception 
	 */
	public static String generateCXMLResponse(Document document)throws Exception{
		 OutputFormat format = new OutputFormat(document);
	      format.setDoctype(null, "http://xml.cxml.org/schemas/cXML/1.2.009/cXML.dtd");
	      //format.setEncoding("UTF-8");
	      format.setEncoding("ISO-8859-1");
	      format.setVersion("1.0");
	      StringWriter stringOut = new StringWriter();
	      XMLSerializer serial = new XMLSerializer(stringOut, format);
	      try {
			serial.serialize(document);
		} catch (IOException e) {
			
			LOGGER.error("[error occured during serialize ]",e);
			throw e;
		}
	      
	      String encodedText=null;
	      String decodedText=null;
		try {
			encodedText = new String(Base64.encodeBase64(stringOut.toString().getBytes("UTF8")));
			decodedText = new String(Base64.decodeBase64(encodedText.getBytes("UTF8")));
			LOGGER.debug("[ DECODED cXML \n]"+decodedText);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("[error occured during encoding ]",e);
			throw e;
		}
	     
	    return encodedText; 
	     
		 
	}


	/**
	 * @param loadPriceInformation 
	 * @param request 
	 * @return List 
	 * @throws Exception 
	 */
	public static Set<String> getBundleContractLineItems(List<Bundle> bundleList)  {
		
		Set<String> contractLineItems=new HashSet<>();
		for(Bundle bundle:bundleList){
			String contractLineItem=bundle.getContractLineItemId();
			if(StringUtils.isNotBlank(contractLineItem)){
				contractLineItems.add(contractLineItem);
			}			
		}		
		return contractLineItems;
	}
	
	/**
	 * @param contractNumber
	 * @param partsList
	 * @param retrievePriceService
	 * @return
	 * @throws Exception 
	 */
	public static Set<String> getPartsContractLineItem(List<OrderPart> partsList) {
		Set<String> contractLineItems=new HashSet<>();
		for(OrderPart part:partsList){
			String contractLineItem=part.getContractLineItemId();
			if(StringUtils.isNotBlank(contractLineItem)){
				contractLineItems.add(contractLineItem);
			}			
		}	
		return contractLineItems;				
	}
	
	/**
	 * @param retrievePriceService
	 * @param contractLineItems
	 * @param contractNumber
	 * @return
	 * @throws Exception 
	 */
	private static Map<String,Price>  doPriceCall(RetrievePriceService retrievePriceService,
			Set<String> contractLineItems,String contractNumber) {
				PriceContract priceContract =ContractFactory.getPriceContract(contractLineItems, contractNumber);
				ObjectDebugUtil.printObjectContent(priceContract, LOGGER);
				PriceResult result=null;
				try{
					result=retrievePriceService.retrievePriceList(priceContract);
				}catch(Exception e ){
					LOGGER.debug(" Exception occued in retrieving price "+e.getMessage());
				}
				Map<String, Price> priceMap=new HashMap<>();
				if(result!=null){
					List<Price> priceList=result.getPriceOutputList();
					for(Price price:priceList){
						priceMap.put(price.getContractLineItemId(), price);
					}
				}
				
				return priceMap;
	}
	
	
	public static String getCertifiedProduct(String certProduct){
		LOGGER.debug("CERTIFIED PRODUCT is-->"+certProduct);
		
	
		
		if (certProductMap == null) {
			initcertProductsMap();
		}
		String type = certProductMap.get(certProduct) != null ? certProductMap.get(certProduct) : "";
		LOGGER.debug("TYPE--->"+type);
		return type;
	}

	public static void initcertProductsMap() {
		LOGGER.debug("Inside initcertProductsMap");
		certProductMap = new HashMap<String, String>();
		Properties props = getConfigProperties();
		for (Object key : props.keySet()) {
			String keyString = (String) key;
			if (keyString.indexOf(CERT_PRODUCT_KEY_PREFIX) > -1) {
				String value = props.getProperty(keyString);
				String fileExtensionKey = keyString
						.substring(CERT_PRODUCT_KEY_PREFIX.length());
				certProductMap.put(fileExtensionKey, value);
			}
		}
	}
	
	// added to return a list of PunchoutAccounts for parallel Call (multiple contract)
	public static List<PunchoutAccount> getPunchoutAccountList(List<PunchoutAccount> accounts, PortletRequest request){
		LOGGER.debug("IN PUNCHOUT ACCOUNTS");
		List<PunchoutAccount> punchoutAccountList = new ArrayList<PunchoutAccount>();
		if(accounts!=null && accounts.size()!=0){
			LOGGER.debug("IN PUNCHOUT ACCOUNTS INSIDE IF");
			
		String cNum = StringUtils.isNotBlank(request.getParameter("cNum"))==true?request.getParameter("cNum"):"";
		String cName = StringUtils.isNotBlank(request.getParameter("cName"))==true?request.getParameter("cName"):"";
		
		List<String> certifiedCNames = new ArrayList<String>();
		certifiedCNames.add(NCAL_ACNT);
		certifiedCNames.add(SCAL_ACNT);
		
		if(accounts.get(0)!=null){
			LOGGER.debug("accounts.get(0) is not null");
			for(int i = 0; i < accounts.size(); i++)
			{
				LOGGER.debug("In for "+i+"th loop");
				PunchoutAccount ac = accounts.get(i);
				if(cName.length() > 0 && ac.getContractName().contains(cName))
					cNum = ac.getContractNumber();
								
				if((cNum.trim().length() > 0 && ac.getContractNumber().equalsIgnoreCase(cNum)) ||
					(cNum.trim().length() == 0 && ac.getContractName().equalsIgnoreCase("base"))){
				}
				punchoutAccountList.add(ac);
			}
			LOGGER.debug("in return 2 list size is "+punchoutAccountList.size());
			return punchoutAccountList;
			}else{
				LOGGER.debug("in return 3 list size is "+punchoutAccountList.size());
			return punchoutAccountList;
			}
		}
		LOGGER.debug("in return 4 list size is "+punchoutAccountList.size());
		return punchoutAccountList;
	}
	
	/**
	 * @param contract
	 * @return
	 */
	public static LocalizedSiebelValueResult retrieveLocalizedSiebelValue(LocalizedSiebelValueContract contract) {
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		String localizedSiebelValue = null;
		String localeName = contract.getLocaleName();
		String lovValue = contract.getLovValue();
		String lovType = contract.getLovListName();
		ListOfValues lov = new ListOfValues();
		lov.setType(lovType);
		lov.setValue(lovValue);
		
		try {
			Query query = HibernateUtil.getSession().createSQLQuery(SQL_GET_SIEBEL_LOCALIZATION);
			query.setParameter("local_code", localeName);
			query.setParameter("siebel_value" , lovValue);
			query.setParameter("option_type", lovType);
			List list = query.list();
			if(list != null && list.size() > 0) {
				localizedSiebelValue = (String) list.get(0);
			} 
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		lov.setName(localizedSiebelValue);
		
		result.setLovValue(lov);
		return result;
	}
	/**
	 * @param LOV
	 * @param siebelValue
	 * @return
	 */
	public static String portalSiebelLocalization(String LOV, String siebelValue){
		LocalizedSiebelValueContract localizedSiebelValueContract = ContractFactory.createLocalizedSiebelValueContract(LOV,siebelValue,null);
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		String accname="";
		try {
			result = ControllerUtil.retrieveLocalizedSiebelValue(localizedSiebelValueContract);
		} catch (InfrastructureException ex) {
			
			throw new LGSRuntimeException("Exception while retrieving localized SR_TYPE LOV list from DB",ex);
		}
		ListOfValues localLovValue = result.getLovValue();
		if(localLovValue !=null){
			accname= localLovValue.getName();
		}
		return accname;
	}
	
	
	
	/**
	 * @param response
	 * @param val
	 */
	public static void writeResponse(ResourceResponse response,String val){
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setContentType("text/javascript");
			out.write(val);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
	

	}
	
	/**
	 * @param priceMap
	 * @param bundles
	 */
	public static void updateBundleWithPrice(Map<String,Price> priceMap,List<Bundle> bundles){
		for(Bundle bundle:bundles){
			Price price=priceMap.get(bundle.getContractLineItemId());
			if(price!=null){
				bundle.setPrice(new BigDecimal(price.getPrice()));
				bundle.setCurrency(price.getCurrency());
			}
		}
	}
	
	/**
	 * @param priceMap
	 * @param parts
	 */
	public static void updatePartsWithPrice(Map<String,Price> priceMap,List<OrderPart> parts){
		for(OrderPart part:parts){
			// Update with price 
			Price price=priceMap.get(part.getContractLineItemId());
			if(price!=null){
				part.setPrice(new BigDecimal(price.getPrice()));
				part.setCurrency(price.getCurrency());
			}
		}
	}
	
	
	/**
	 * @param session
	 * @return
	 */
	public static String getSupplierId(PortletSession session){
		Map<String,String> aribaParams=(Map<String,String>)session.getAttribute(PunchoutConstants.ARIBA_PARAMS,PortletSession.APPLICATION_SCOPE);
		String supplierId=aribaParams.get("supplierId");
		return supplierId==null?"":supplierId;
	}
	
	public static boolean getFromAriba(PortletSession session){
		Map<String,String> aribaParams=(Map<String,String>)session.getAttribute(PunchoutConstants.ARIBA_PARAMS,PortletSession.APPLICATION_SCOPE);
		return Boolean.valueOf(aribaParams.get("fromAriba"));
	}
}


