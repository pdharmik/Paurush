package com.lexmark.services.portlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.services.constants.BeanFieldNames;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.domain.CartItem;
import com.lexmark.services.form.ShoppingCartForm;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.JsonUtil;
@Controller
@RequestMapping("VIEW")
public class ShoppingCartController {

	private static Logger LOGGER = LogManager
	.getLogger(ShoppingCartController.class);
	
	
	

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @param qty 
	 * @param prodId 
	 * @param partsOrdering 
	 * @param bundleId 
	 * @param cartType 
	 * @throws IOException 
	 */
	@ResourceMapping("addToShoppingCart")
	public void addToShoppingCart(ResourceRequest request,
			ResourceResponse response, Model model, PortletSession session,
			@RequestParam("quantity") String qty,@RequestParam("prodId") String prodId,
			@RequestParam(value="isOptnWarr", required=false) String partsOrdering,
			@RequestParam("bundleId") String bundleId,
			@RequestParam("cartType") String cartType,
			@RequestParam("unspscCode") String unspscCode)
			throws IOException {
		LOGGER.debug(" in addToShoppingCart");
		
		LOGGER.debug("prod id="+prodId +" qty = "+qty+" cart type is"+cartType+" unspscCode is "+unspscCode);
		
		Map<String, ShoppingCartForm> _shoppingFormList = (Map<String, ShoppingCartForm>) session.getAttribute(PunchoutConstants.CART_SESSION);		
		
		if(StringUtils.isNotBlank(partsOrdering) && "true".equalsIgnoreCase(partsOrdering)){
			LOGGER.debug("adding options and warranties ");
			ControllerUtil.addShoppingCartParts(session, prodId, qty, bundleId, cartType);
		}else{
			LOGGER.debug("adding bundles ... ");
			ControllerUtil.addToShoppingCart(session,prodId,qty,cartType,unspscCode);
		}		
	
		int cartSize = getCartSize(request, cartType);
		if(null != session.getAttribute("forGlobalSearch") && (Boolean)session.getAttribute("forGlobalSearch")){
			LOGGER.debug("for global search add to shopping cart");
			cartSize = getCartSize(request, "supplies")+getCartSize(request, "printers");
			cartType = "globalSearch";
		}
		
		LOGGER.debug("fROM SESSION ITEMS IN CART===========>" + cartSize);
		Map<String,String> params=new HashMap<String,String>();
		params.put(PunchoutConstants.SIZE,String.valueOf(cartSize));
		params.put(PunchoutConstants.CART_TYPE,cartType);
		LOGGER.debug("GENERATEDJSONCART"+JsonUtil.generateCartJSON(params));
		ControllerUtil.prepareResponse(response, JsonUtil.generateCartJSON(params));
		
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param session 
	 */
	@ResourceMapping("getCartSize")
	public void refreshCartSize(ResourceRequest request,
			ResourceResponse response,PortletSession session,
			@RequestParam("ct") String cartType) {
		LOGGER.debug("[in refresh Cart Size ]");
		Map<String,String> params=new HashMap<String,String>();
		params.put(PunchoutConstants.SIZE,String.valueOf(getCartSize(request, cartType)));
		params.put(PunchoutConstants.CART_TYPE,cartType);
		ControllerUtil.prepareResponse(response, JsonUtil.generateCartJSON(params));
		LOGGER.debug("[Out refresh Cart Size ]");
	}
	
	

	/**
	 * @param _request 
	 * @return int  
	 */
	private int getCartSize(PortletRequest _request, String cartType){
		LOGGER.debug("[in get Cart Size ]");
		PortletSession session=_request.getPortletSession();
		Map<String, ShoppingCartForm> _form=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
		
		if(_form.get(cartType).getCartItems()==null){
			LOGGER.debug("[Cart is null]");
			LOGGER.debug("[out get Cart Size ]");
			return 0;
		}else{
			LOGGER.debug("[out get Cart Size ]");
			return _form.get(cartType).getCartItems().size();
		}
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 */
	@ResourceMapping("loadShoppingCart")
	public String showShoppingCart(ResourceRequest request,
			ResourceResponse response, Model model, PortletSession session,
			@RequestParam("cartType") String cartType) {
		LOGGER.debug(("[ In  showShoppingCart ]"));
		LOGGER.debug("cart type is "+cartType);
		Map<String, ShoppingCartForm> shoppingCartForm = new HashMap<String, ShoppingCartForm>();
		List<CartItem> cartItems = null;
		BigDecimal total = new BigDecimal(0);
		if(!cartType.equalsIgnoreCase("globalSearch")){
			LOGGER.debug("cartType in if block is "+cartType);
			shoppingCartForm=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
		    cartItems=shoppingCartForm.get(cartType).getCartItems();
		    total=ControllerUtil.calculateTotal(cartItems);
		    if("printers".equalsIgnoreCase(cartType)){
		    	model.addAttribute("shoppingCartFormBundles", shoppingCartForm.get(cartType));
		    }else{
		    	model.addAttribute("shoppingCartFormSupplies", shoppingCartForm.get(cartType));
		    }
		    model.addAttribute("shoppingCartType", cartType);
		}else{
			LOGGER.debug("in shopping cart controller, else block");
		    shoppingCartForm=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
			cartItems=shoppingCartForm.get("printers").getCartItems();
			total=ControllerUtil.calculateTotal(cartItems);
			cartItems = shoppingCartForm.get("supplies").getCartItems();
    		total=total.add(ControllerUtil.calculateTotal(cartItems));
			 model.addAttribute("shoppingCartFormBundles", shoppingCartForm.get("printers"));
			 model.addAttribute("shoppingCartFormSupplies", shoppingCartForm.get("supplies"));
			 model.addAttribute("shoppingCartType", "globalSearch");
		}
		
		
        LOGGER.debug("total pr"+total);
        
		
		model.addAttribute(PunchoutConstants.TOTAL_PRICE, total);
		return "shoppingCart/shoppingCartPage";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @param productId 
	 */
	@ResourceMapping("removeFromCart")
	public void removefromShoppingCart(ResourceRequest request,
			ResourceResponse response, Model model, PortletSession session,
			@RequestParam("pr")String productId,
			@RequestParam("ct") String cartType) {
		LOGGER.debug(("[ In  removefromShoppingCart ]"));
		
		Map<String,String> params=new HashMap<String,String>();
		params.put(PunchoutConstants.ID, productId);		
		
		try{
			Map<String, ShoppingCartForm> shoppingCartForm=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);			
			
			params.put(PunchoutConstants.CART_TYPE,cartType);		
			ControllerUtil.removeFromList(shoppingCartForm.get(cartType).getCartItems(), productId);
			BigDecimal total=ControllerUtil.calculateTotal(shoppingCartForm.get(cartType).getCartItems());
        	LOGGER.debug("total pr"+total);
        	params.put(PunchoutConstants.TOTAL,total.toPlainString());
        	params.put(PunchoutConstants.STATUS, PunchoutConstants.SUCCESS);
        	params.put(PunchoutConstants.SIZE,String.valueOf(getCartSize(request, cartType)));
		}catch(Exception e){
			params.put(PunchoutConstants.STATUS, PunchoutConstants.FAILURE);
			params.put(PunchoutConstants.TOTAL, PunchoutConstants.ZERO);
			params.put(PunchoutConstants.SIZE, PunchoutConstants.ZERO);
			LOGGER.error("[ error occured ]"+e.getCause());
		}
		
        ControllerUtil.prepareResponse(response, JsonUtil.generateCartJSON(params));
		
	}
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @param productId 
	 * @param quantity 
	 */
	@ResourceMapping("updateQty")
	public void addQuantityShoppingCart(ResourceRequest request,
			ResourceResponse response, Model model, PortletSession session,
			@RequestParam("pr")String productId,
			@RequestParam("qt")String quantity,
			@RequestParam("ct")String cartType) {
		LOGGER.debug(("[ In  addQuantityShoppingCart ]"));
		
		Map<String,String> params=new HashMap<String,String>();
		params.put(PunchoutConstants.ID, productId);
		
		try{
			Map<String, ShoppingCartForm> shoppingCartForm=(Map<String, ShoppingCartForm>)session.getAttribute(PunchoutConstants.CART_SESSION);
			//ShoppingCartForm shoppingCartForm = (ShoppingCartForm) session.getAttribute(PunchoutConstants.CART_SESSION);
		CartItem _cartItem=ControllerUtil.findWithinCartItems(shoppingCartForm.get(cartType).getCartItems(), productId);
		_cartItem.setQuantity(quantity);
		params.put(PunchoutConstants.QUANTITY, quantity);
		params.put(PunchoutConstants.CART_TYPE, cartType);
		BigDecimal total=ControllerUtil.calculateTotal(shoppingCartForm.get(cartType).getCartItems());
        LOGGER.debug("total pr"+total);
        
        params.put(PunchoutConstants.TOTAL,total.toPlainString());
        params.put(PunchoutConstants.STATUS, PunchoutConstants.SUCCESS);
		}catch(Exception e){
			params.put(PunchoutConstants.STATUS, PunchoutConstants.FAILURE);
			params.put(PunchoutConstants.TOTAL, PunchoutConstants.ZERO);
			LOGGER.error("[ error occured ]"+e.getCause());
		}
		
        ControllerUtil.prepareResponse(response, JsonUtil.generateCartJSON(params));
		
	}
	
	/**
	 * @param session 
	 */
	@ResourceMapping("clearCartSession")
		public void clearCartFromSession(PortletSession session){
			LOGGER.debug("in clear session");
			session.setAttribute(PunchoutConstants.CART_SESSION, ControllerUtil.initShoppingCart());
			
		}
	
	

}
