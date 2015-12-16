package com.lexmark.services.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.portlet.PortletSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.lexmark.services.constants.BeanFieldNames;
import com.lexmark.services.domain.SessionInformation;
import com.lexmark.services.form.ShoppingCartForm;
import com.lexmark.services.util.ControllerUtil;

public class PunchoutItemDAO {
	private static Logger LOGGER = LogManager
	.getLogger(PunchoutItemDAO.class);

	/**.
	 * 
	 * This method creates CXML. 
	 * 
	 * @param information 
	 * @return Document 
	 * @throws Exception Exception 
	 */
	
	public Document createCXML(SessionInformation information, String cartType,PortletSession session)throws Exception {
		Document doc = new DocumentImpl();
		LOGGER.debug("cart type in createCXML is "+cartType);
		try {
			LOGGER.debug("[in cXML creation ]");
			Element root = doc.createElement("cXML");
			doc.appendChild(root);
			root.setAttribute("payloadID", information.getBuyerCookie()+ "@lexmark.com");
			root.setAttribute("timestamp", getTimestamp());
			root.setAttribute("xml:lang", "en-US");
			buildHeader(doc, root,information);
			
			if(null != session.getAttribute("forGlobalSearch") && (Boolean)session.getAttribute("forGlobalSearch")){
				LOGGER.debug("in if block of createCXML");
				buildBody(doc, root, information, "printers");
				buildBody(doc, root, information, "supplies");
			}
			else{
				LOGGER.debug("in else block of createCXML");
				buildBody(doc, root, information, cartType);
			}

		} catch (Exception ex) {
			LOGGER.error("[exception occured in generating cxml]",ex);
			throw ex;
		}
		return doc;
	}

	/**.
	 * 
	 * This method builds the header 
	 * 
	 * @param doc 
	 * @param root 
	 * @param information 
	  
	 */
	
	private void buildHeader(Document doc, Node root, SessionInformation information) {
		LOGGER.debug("[in Build Header]");
		Element header = doc.createElement("Header");
		root.appendChild(header);

		Element from = doc.createElement("From");
		header.appendChild(from);
		buildCredential(doc, from, "NetworkID", information.getSupplierId());
		//buildCredential(doc, from, "NetworkID", information.getIdentity());

		Element to = doc.createElement("To");
		header.appendChild(to);
		buildCredential(doc, to, "DUNS", information.getDuns());

		Element sender = doc.createElement("Sender");
		header.appendChild(sender);
		buildCredential(doc, sender, "AribaNetworkUserId", information.getNetworkUserId(),
				information.getSharedSecret());

		Element userAgent = doc.createElement("UserAgent");
		sender.appendChild(userAgent);
		userAgent.appendChild(doc.createTextNode("Lexmark"));
		LOGGER.debug("[OUT Build Header]");
	}
	/**.
	 * 
	 * This method builds the credentials
	 * 
	 * @param doc 
	 * @param theElement 
	 * @param theDomain 
	 * @param theText 
	 * @param theSecretText 
	*/
	private void buildCredential(Document doc, Node theElement,
			String theDomain, String theText, String theSecretText) {
		Element credential = doc.createElement("Credential");
		credential.setAttribute("domain", theDomain);
		theElement.appendChild(credential);

		Element identity = doc.createElement("Identity");
		credential.appendChild(identity);
		identity.appendChild(doc.createTextNode(theText));

		if (theSecretText != null) {
			Element sharedSecret = doc.createElement("SharedSecret");
			credential.appendChild(sharedSecret);
			sharedSecret.appendChild(doc.createTextNode(theSecretText));
		}
	}
	/**.
	 * 
	 * This method builds the credentials 
	 * 
	 * @param doc 
	 * @param theElement 
	 * @param theDomain 
	 * @param theText 
	 */
	private void buildCredential(Document doc, Node theElement,
			String theDomain, String theText) {
		String theSecretText = null;
		buildCredential(doc, theElement, theDomain, theText, theSecretText);
	}
	/**.
	 * 
	 * This method builds the body  
	 * 
	 * @param doc 
	 * @param root 
	 * @param sInformation 
	 */
	private void buildBody(Document doc, Node root, SessionInformation sInformation, String cType) {
		/*List cartItems = ShoppingCartItemBO.getShoppingCartItemsKaiser(
				userSessionId, customerNumber, country, language);*/
		LOGGER.debug("[in Build Body]");
		
		Map<String, ShoppingCartForm> _sformMap=sInformation.getShoppingCartForm();
		ShoppingCartForm _sform = _sformMap.get(cType);
		String cartType=cType;		
		
		LOGGER.debug(String.format("[cart type = %s]",cartType));
		Element elmMessage = doc.createElement("Message");
		root.appendChild(elmMessage);
		
		Element elmOrderMessage = doc.createElement("PunchOutOrderMessage");
		elmMessage.appendChild(elmOrderMessage);
		
		Element elmBuyerCookie = doc.createElement("BuyerCookie");
		elmBuyerCookie.appendChild(doc.createTextNode(sInformation.getBuyerCookie()));
		elmOrderMessage.appendChild(elmBuyerCookie);
		
		Element elmOrderMessageHeader = doc
				.createElement("PunchOutOrderMessageHeader");
		elmOrderMessageHeader.setAttribute("operationAllowed", sInformation.getOperation());
		elmOrderMessage.appendChild(elmOrderMessageHeader);
		
		Element elmTotal = doc.createElement("Total");
		elmOrderMessageHeader.appendChild(elmTotal);
		
		Element money = doc.createElement("Money");
		money.setAttribute("currency", "USD");		
		money.appendChild(doc.createTextNode("" + ControllerUtil.calculateTotal(_sform.getCartItems()
				, BeanFieldNames.PRICE.getValue(cartType), BeanFieldNames.QUANTITY.getValue(cartType)).doubleValue()));
		elmTotal.appendChild(money);
		
		BigDecimal unitPrice = null;
		String supplierPartId = null;
		String description = null;
		String manufactureId = null;
		String prdQty = null;
		String contractNo = "";
		String sapLineId = "";
		String unspscCode = "";
		for(Object _cartItem:_sform.getCartItems()){
						
			description=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.DESCRIPTION.getValue(cartType));
			unitPrice = (BigDecimal)ControllerUtil.readProperty(_cartItem, BeanFieldNames.PRICE.getValue(cartType));
			//supplierPartId=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.ID.getValue(cartType));
			manufactureId=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.PRODUCTID.getValue(cartType));
			prdQty=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.QUANTITY.getValue(cartType));
			unspscCode=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.UNSPSCCODE.getValue(cartType));
			LOGGER.debug("unspsc code in punchoutItemDAO is "+unspscCode);
			
			contractNo=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.CONTRACTNO.getValue("printers"));
			sapLineId=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.SAPLINEID.getValue("printers"));
			LOGGER.debug("contractNo Number ------------------------>>>>" +contractNo);
			LOGGER.debug("sapLineId Number ------------------------>>>>" +sapLineId);
			if (contractNo != null && sapLineId !=null){
				supplierPartId=contractNo+sapLineId;
			}else{
				supplierPartId=null;
			}
			LOGGER.debug("CPN Number ------------------------>>>>" +supplierPartId);
			if(cartType.equalsIgnoreCase("printers"))
			{
				if(supplierPartId == null)
					supplierPartId=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.ID.getValue("supplies"));
				
				if(manufactureId == null)
					manufactureId=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.PRODUCTID.getValue("supplies"));
				
				if(prdQty == null)
					prdQty=(String)ControllerUtil.readProperty(_cartItem, BeanFieldNames.QUANTITY.getValue("supplies"));
			}
			Element itemIn = doc.createElement("ItemIn");
			itemIn.setAttribute("quantity", "" + prdQty);
			
			elmOrderMessage.appendChild(itemIn);
			Element elmItemId = doc.createElement("ItemID");
			itemIn.appendChild(elmItemId);
			
			Element elmSupplierPartId = doc.createElement("SupplierPartID");		
			elmSupplierPartId.appendChild(doc.createTextNode(supplierPartId==null?"":supplierPartId));
			elmItemId.appendChild(elmSupplierPartId);
			
			Element elmSupplierPartAuxiliaryID = doc.createElement("SupplierPartAuxiliaryID");
			elmSupplierPartAuxiliaryID.appendChild(doc.createTextNode(sInformation.getSupplierCookie()));
			elmItemId.appendChild(elmSupplierPartAuxiliaryID);
			
			Element elmItemDetail = doc.createElement("ItemDetail");
			itemIn.appendChild(elmItemDetail);
			
			Element elmUnitPrice = doc.createElement("UnitPrice");
			elmItemDetail.appendChild(elmUnitPrice);
			
			Element elmPrice = doc.createElement("Money");
			elmUnitPrice.appendChild(elmPrice);
			elmPrice.setAttribute("currency", "USD");
			elmPrice.appendChild(doc.createTextNode(unitPrice==null?"0":String.valueOf(unitPrice.doubleValue())));
			LOGGER.debug("unit price "+unitPrice);
			Element desc = doc.createElement("Description");
			desc.setAttribute("xml:lang", "en-us");
			desc.appendChild(doc.createTextNode(description==null?"":description));
			elmItemDetail.appendChild(desc);
			LOGGER.debug("after unit of measure");
			Element elmUnitOfMeasure = doc.createElement("UnitOfMeasure");
			elmItemDetail.appendChild(elmUnitOfMeasure);
			elmUnitOfMeasure.appendChild(doc.createTextNode("EA"));
			
			Element elmClassification = doc.createElement("Classification");
			elmItemDetail.appendChild(elmClassification);
			elmClassification.setAttribute("domain", "UNSPSC");
			elmClassification.appendChild(doc.createTextNode(unspscCode));
			LOGGER.debug("after domain" +unspscCode);
			//log.debug("unspsc=" + cartItem.getUnspsc());
			//Hardcoded for the timebeing
			
			elmClassification.appendChild(doc.createTextNode(""));//cartItem.getUnspsc()
			Element manuPartId = doc.createElement("ManufacturerPartID");
			manuPartId.appendChild(doc.createTextNode(manufactureId==null?"":manufactureId));
			elmItemDetail.appendChild(manuPartId);
			
			Element elmManufacturer = doc.createElement("ManufacturerName");
			elmManufacturer.appendChild(doc.createTextNode("Lexamrk"));
			elmItemDetail.appendChild(elmManufacturer);
		}
		LOGGER.debug("[out Build body]");
	}
	/**.
	 * 
	 * This method gives the Timestamp 
	 * 
	 * @return String 
	 */
	private String getTimestamp() {
		String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
		final SimpleDateFormat ISO8601 = new SimpleDateFormat(
				ISO_8601_DATE_FORMAT);
		return ISO8601.format(new Date(System.currentTimeMillis()));
	}

}
