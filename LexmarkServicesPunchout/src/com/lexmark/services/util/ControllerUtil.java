package com.lexmark.services.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.HardwareCatalog;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.service.api.RequestTypeServiceB2B;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.constants.BeanFieldNames;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.domain.SessionInformation;
import com.lexmark.services.form.Bullet;
import com.lexmark.services.form.LearnMoreForm;
import com.lexmark.services.form.Marketing;
import com.lexmark.services.form.ShoppingCartForm;
import com.lexmark.services.form.TechSpec;
import com.lexmark.services.mock.GenerateMockData;
import com.lexmark.services.servlet.LoadPriceInformation;
import com.lexmark.servlet.PunchOutSetupResponse;
import com.liferay.portal.util.PortalUtil;


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

	public static Properties getConfigProperties() {
		if (aribaProperty == null) {
			Properties props = new Properties();
			InputStream in;
			try {
				in = PunchOutSetupResponse.class
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
	public static void removeFromList(List<Object> cartList,String prodIdValue,String compareField){
		if(cartList==null){
			return ;
		}
		int i=-1;
		boolean found=false;
		for(Object _cart:cartList){
			i++;
			
			if(_cart!=null && compare(_cart,compareField,prodIdValue)){
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
	public static BigDecimal calculateTotal(List<Object> cartBundle,String priceField,String qtyField){
		BigDecimal totalPrice=new BigDecimal("0");
			if(cartBundle!=null){
			
	        for(Object b:cartBundle){
	        	BigDecimal price=(BigDecimal)ControllerUtil.readProperty(b, priceField);
	        	String qty = (String)ControllerUtil.readProperty(b, qtyField);
	        	if(qtyField.equalsIgnoreCase("bundleqty") && qty == null)
	        	{
	        		qty = (String)ControllerUtil.readProperty(b, "orderQuantity");
	        	}
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
	public static CatalogListResult amindRetrievePrinterTypes(PortletRequest request,OrderSuppliesCatalogService service){
		CatalogListResult result=new CatalogListResult();
		CatalogListContract contract=ContractFactory.retirevePrinterTypes(request);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		try {
			result=service.retrievePrinterTypesB2B(contract);
			//LOGGER.debug("[ THIS IS MOCK CALL !!]");
		//	result.setLovList(new GenerateMockData().generatePrintersList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			result.setLovList(service.retrieveAccessoriesB2b(contract).getLovList());
			result.setLovList(service.retrievePrinterTypesB2B(contract).getLovList());
			//.debug("[ THIS IS MOCK CALL !!]");
			////result.setLovList(new GenerateMockData().generatePrintersList());
			//result.setAccessoriesList(new GenerateMockData().generateWarrantiesList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		List<Object> bundles = (List<Object>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		//List<Bundle> _cart_bundles = (List<Bundle>) session.getAttribute(PunchoutConstants.CART_SESSION);
		Map<String, ShoppingCartForm> shoppingCartForm=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
		List<Object> _cart_bundles = (List<Object>) shoppingCartForm.get(cartType).getCartItems();
		List<Object> finalList=new ArrayList<Object>();
		if(bundles != null){
		finalList.addAll(bundles);
		}if(_cart_bundles != null){
		finalList.addAll(_cart_bundles);
		}
		
		LOGGER.debug(" size ="+finalList==null?0:finalList.size());
		
		for(Object bundle:finalList){
			if(bundle instanceof Bundle && bundleId.equalsIgnoreCase(((Bundle) bundle).getBundleId())){
				LOGGER.debug(String.format("[ bundle id = %s found in the list ]",bundleId));
				((Bundle) bundle).setOrderParts(accessories);
			}
		}
		
	}
	
	/**
	 * @param session 
	 * @param prodIdValue 
	 * @param qty 
	 * @param bundleId 
	 */
	public  static void addShoppingCartParts(PortletSession session, String prodIdValue,String qty,String bundleId,String cartType){
		
		List<Object> _productListSession  = (List<Object>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		List<OrderPart> bundleOrderParts = new ArrayList<OrderPart>();
		List<Object> bundleParts = new ArrayList<Object>();
		
		for(Object bundle:_productListSession){
			if(bundleId.equalsIgnoreCase(((Bundle) bundle).getBundleId())){
				bundleOrderParts = (List<OrderPart>) ((Bundle) bundle).getOrderParts();
				session.setAttribute("bundleAcc", bundleOrderParts);
				bundleParts = (List<Object>) session.getAttribute("bundleAcc");
			}
		}		
		
		LOGGER.debug(" size ="+_productListSession ==null?0:_productListSession .size());			
		Map<String, ShoppingCartForm> _shoppingForm = (Map<String, ShoppingCartForm>) session.getAttribute(PunchoutConstants.CART_SESSION);		
		List<Object> cartItems=_shoppingForm.get(cartType).getCartItems();		
		
		//Check whether in the cart or not
		Object _cartItem=ControllerUtil.findInList(_shoppingForm.get(cartType).getCartItems(), prodIdValue,BeanFieldNames.ID.getValue("supplies"));
		if(_cartItem==null){
			LOGGER.debug(String.format(" [the product is not there in cart id = %s] ",bundleId));
			//This is new item to be added to cart
			//Prior to that need to get the data from the session list first
			_cartItem=ControllerUtil.findInList(bundleParts, prodIdValue,BeanFieldNames.ID.getValue("supplies"));
			//copy bundle properties
			//copy part properties
			cartItems.add(_cartItem);
		}
		
		/*//Lets search for the productId in the orderpartlist
		Object partItem=ControllerUtil.findInList((List<Object>)ControllerUtil.readProperty(_cartItem, "orderParts") ,prodIdValue,compareField);*/
		ControllerUtil.updateQty(_cartItem, BeanFieldNames.QUANTITY.getValue("supplies"), qty);
	}
	
	/**
	 * @param session 
	 * @param prodId 
	 * @param qty 
	 */
	public  static void addToShoppingCart(PortletSession session, String prodId,String qty,String cartType){
		
		List<Object> _productListSession = (List<Object>) session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		LOGGER.debug(" size ="+_productListSession ==null?0:_productListSession .size());
			
		Map<String, ShoppingCartForm> _shoppingForm = (Map<String, ShoppingCartForm>) session.getAttribute(PunchoutConstants.CART_SESSION);
		List<Object> cartItems=_shoppingForm.get(cartType).getCartItems();
		
		
		//Check whether in the cart or not
		Object _cartItem=ControllerUtil.findInList(_shoppingForm.get(cartType).getCartItems(), prodId,BeanFieldNames.ID.getValue(cartType));
		
		LOGGER.debug(_shoppingForm.get(cartType).getCartItems());
		if(_cartItem==null){
			LOGGER.debug(String.format(" [the product is not there in cart id = %s] ",prodId));
			//This is new item to be added to cart
			//Prior to that need to get the data from the session list first
			_cartItem=ControllerUtil.findInList(_productListSession , prodId,BeanFieldNames.ID.getValue(cartType));
			//copy bundle properties
			//copy part properties	
			cartItems.add(_cartItem);
		}
		
		ControllerUtil.updateQty(_cartItem, BeanFieldNames.QUANTITY.getValue(cartType), qty);	
		
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
			return accounts.get(5);
			}else{
			return new PunchoutAccount();
			}
		}
		return new PunchoutAccount();
	}

	/**
	 * @param bulletList 
	 * @param marketingList 
	 * @param techSpecList 
	 * @return LearnMoreForm 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static LearnMoreForm getLearnMoreDetails(List bulletList,
			List marketingList, List techSpecList) throws SQLException, IOException {

		LearnMoreForm learnMoreForm= new LearnMoreForm();
		//bullet list
		List<Bullet> list= new ArrayList<Bullet>();
		if(bulletList !=null){
		for(int i = 0; i < bulletList.size(); i++){
			Bullet bullet = new Bullet();
			Object[] row = (Object[]) bulletList.get(i);
			bullet.setValue((String)row[1]);
			LOGGER.debug("bullet value"+bullet.getValue());
			list.add(bullet);
		}
		learnMoreForm.setBulletList(list);
		}
		//Marketing List
		if(marketingList !=null){
		for(int i = 0; i < marketingList.size(); i++){
			Marketing marketing= new Marketing();
			Object[] row = (Object[]) marketingList.get(i);
			marketing.setPartId((String)row[0]);
			marketing.setPpmInfo((String)row[1]);
			marketing.setProductImage((String)row[2]);
			marketing.setFamilyImage((String)row[3]);
			marketing.setPartName((String)row[4]);
			marketing.setCatagory((String)row[7]);
			marketing.setName((String)row[8]);
			marketing.setUrl((String)row[9]);
			marketing.setPrice((BigDecimal)row[10]);
			StringBuffer str = new StringBuffer();
		       String strng=null;
		       if((java.sql.Clob) row[11] != null){
		       BufferedReader bufferRead = new BufferedReader(((java.sql.Clob) row[11]).getCharacterStream());
		       while ((strng=bufferRead .readLine())!=null)
		       {
		    	   str.append(strng);
		       }
		       }
		       if(str !=null){
		    	   marketing.setDescription(str.toString());
		       }else{
		    	   marketing.setDescription("");
		       }
		       
		       marketing.setPartNumber((String)row[12]);
		       marketing.setMkt_desc((String)row[13]);
		       
		       LOGGER.debug("marketing.setPartId"+marketing.getPartId());
		       LOGGER.debug("marketing.setPpmInfo"+marketing.getPpmInfo());
		       LOGGER.debug("marketing.setPartName"+marketing.getPartName());
		       LOGGER.debug("marketing.setDescription"+marketing.getDescription());
		       LOGGER.debug("marketing.setCatagory"+marketing.getCatagory());
		       
		       learnMoreForm.setMarketing(marketing);
		}
		}
		//Tech Spec List
		if(techSpecList !=null){
		for(int i = 0; i < techSpecList.size(); i++){
			TechSpec techSpec = new TechSpec();
			Object[] row = (Object[]) techSpecList.get(i);
			techSpec.setAttribute((String)row[0]);
			techSpec.setValue((String)row[1]);
			learnMoreForm.setTechSpec(techSpec);
		}
		}
		return learnMoreForm;
	}
	/**
	 * @return ShoppingCartForm 
	 */
	public static Map<String, ShoppingCartForm> initShoppingCart(){
		Map<String, ShoppingCartForm> formList = new HashMap<String, ShoppingCartForm>();
		
		ShoppingCartForm printersForm=new ShoppingCartForm();
		//printersForm.setCartType("printers");
		printersForm.setCartItems(new ArrayList<Object>());
		formList.put("printers", printersForm);
		
		ShoppingCartForm suppliesForm=new ShoppingCartForm();
		//suppliesForm.setCartType("supplies");
		suppliesForm.setCartItems(new ArrayList<Object>());
		formList.put("supplies", suppliesForm);
				
		return formList;
	}
	
	/**
	 * @param session 
	 */
	public static void updateItemsWithCartData(PortletSession session, String cartType, String bundleId){
		LOGGER.debug("inside updateItemsWithCartData");
		Map<String, ShoppingCartForm> _form=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
		List<Object> amindList=(List<Object>)session.getAttribute(PunchoutConstants.PRODUCT_BUNDLE);
		if(amindList==null){
			return;
		}
		List<Object> _cartItems=_form.get(cartType).getCartItems();
		List<OrderPart> orderParts = new ArrayList<OrderPart>();
		
		for(Object cartItem:_cartItems){
			if(cartType.equalsIgnoreCase("printers") && cartItem instanceof OrderPart && bundleId.length() > 0)
			{
				Object itemBundle=findInList(amindList, bundleId, BeanFieldNames.ID.getValue(cartType));
				orderParts = ((Bundle) itemBundle).getOrderParts();
				
				for(Object orderPart:orderParts){
					if(((OrderPart) orderPart).getPartNumber().equalsIgnoreCase((String)readProperty(cartItem, BeanFieldNames.ID.getValue("supplies"))))
					{
						updateQty(orderPart, BeanFieldNames.QUANTITY.getValue("supplies"), 
							(String)readProperty(cartItem, BeanFieldNames.QUANTITY.getValue("supplies")));
					}
				}
			}
			else
			{
				Object item=findInList(amindList,(String)readProperty(cartItem, BeanFieldNames.ID.getValue(cartType))
						, BeanFieldNames.ID.getValue(cartType));
				updateQty(item, BeanFieldNames.QUANTITY.getValue(cartType), 
					(String)readProperty(cartItem, BeanFieldNames.QUANTITY.getValue(cartType)));
			}
		}
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
	public static List<Bundle> getBundlePrice(LoadPriceInformation loadPriceInformation, ResourceRequest request) throws Exception {
		List<Bundle> bundleList = null;
		PunchoutAccount account=(PunchoutAccount)request.getAttribute("punchoutAccount");
		if(!LoadPriceInformation.allBundlePriceMap.isEmpty()){
			LOGGER.debug("Retrieving data from cache bundle price map" + account.getAccountId() +"----------------->>"+account.getContractNumber());	
		
			Map<String, Map<String,List<HardwareCatalog>>> allBundlePriceMap = LoadPriceInformation.allBundlePriceMap;
			Map<String, List<HardwareCatalog>> catalogMap = allBundlePriceMap.get(account.getAccountId());
			List<HardwareCatalog> hardwareCatalogList= catalogMap.get(account.getContractNumber());
			if(hardwareCatalogList != null){
			for(HardwareCatalog hardwareCatalog:hardwareCatalogList){
				bundleList = hardwareCatalog.getBundleList();
			}
			}
			return bundleList;
		}else{
			LOGGER.debug("price cache is empty, loading from normal map"  + account.getAccountId() +"----------------->>"+account.getContractNumber());
			Map<String, Map<String,List<HardwareCatalog>>> allBundlePriceMap = loadPriceInformation.getAllBundlePriceMap();
			Map<String, List<HardwareCatalog>> catalogMap = allBundlePriceMap.get(account.getAccountId());
			List<HardwareCatalog> hardwareCatalogList= catalogMap.get(account.getContractNumber());
			if(hardwareCatalogList != null){
			for(HardwareCatalog hardwareCatalog:hardwareCatalogList){
				bundleList = hardwareCatalog.getBundleList();
			}
			}
			return bundleList;
	}
		
	}
	
	public static PriceResult getPriceForParts(String contractNumber,List<OrderPart> partsList,RetrievePriceService retrievePriceService){
		 PriceResult bundlePriceResult = null;
		 PriceContract priceContract =new PriceContract();
			List<Price> priceList=new ArrayList<Price>();
			for(OrderPart parts:partsList){
				LOGGER.debug("contract line item id"+parts.getContractLineItemId());
				Price price =new Price();
				price.setPartNumber(parts.getPartNumber());
				price.setContractLineItemId(parts.getContractLineItemId());
				priceList.add(price);
			}
			priceContract.setPriceList(priceList);
			if(contractNumber !=null){
				LOGGER.debug("contract number --- > "+ contractNumber);
				priceContract.setContractNumber(contractNumber);
			}			
			try {
				bundlePriceResult = retrievePriceService.retrievePriceList(priceContract);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		return bundlePriceResult;
	}
	
	/**
	 * @return ShoppingCartForm 
	 */
	public static void setAribaParam(PortletRequest request, PortletSession session){
		if((HashMap<String, String>)session.getAttribute("aribaParamMap", PortletSession.APPLICATION_SCOPE) !=null){
			session.setAttribute("aribaParamMap", null, PortletSession.APPLICATION_SCOPE);
		}
		if(session.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE) !=null){
			session.setAttribute("supplierId", null, PortletSession.APPLICATION_SCOPE);
		}
		LOGGER.debug(("[ After HashMap ]"));
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
				
		String aribaSetupParams = null;
		Cookie[] cookies = httpReq.getCookies();
		    if (cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		        if (cookies[i].getName().equalsIgnoreCase("aribasetup")) {
		          aribaSetupParams = cookies[i].getValue();
		          LOGGER.debug("aribaSetupParams:::"+aribaSetupParams);
		          break;
		        }
		      }
		    }
		    
		byte[] valueDecoded= Base64.decodeBase64(aribaSetupParams != null ? aribaSetupParams : "");
		String URLParams = new String(valueDecoded);
		String urlParamsArray[]= URLParams.split(";");
		LOGGER.debug(("[ After SPlit ]"+URLParams));
		HashMap<String, String> aribaParamMap = new HashMap<String, String>();
		for(int i=0;i<urlParamsArray.length;i++){
			int j = urlParamsArray[i].indexOf("=");
			String paramKey = urlParamsArray[i].substring(0,j);
			//LOGGER.debug("paramKey:::"+paramKey);
			String paramValue = urlParamsArray[i].substring(j+1);
			//LOGGER.debug("paramValue:::"+paramValue);
			aribaParamMap.put(paramKey, paramValue);
			
			if(paramKey.equalsIgnoreCase("supplierId"))
				session.setAttribute("supplierId", paramValue, PortletSession.APPLICATION_SCOPE);
		}
		session.setAttribute("aribaParamMap", aribaParamMap, PortletSession.APPLICATION_SCOPE);
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

}


