<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<portlet:resourceURL var="showShoppingCart" id="showShoppingCart"></portlet:resourceURL>

<portlet:resourceURL var="refreshCart" id="getCartSize"></portlet:resourceURL>
<script type="text/javascript" src="<html:rootPath/>/js/cart/cart.js?v=1"></script>
<div class="orderCart" id="cart-cntnr" style="cursor: pointer;"><spring:message code="requestInfo.label.order"/><c:if test="${forGlobalSearch != 'true'}"><img id="cartLoading" src="<html:imagesPath/>loading-icon.gif"/></c:if><span id="totItems" style="display:none;">0</span> <spring:message code="requestInfo.label.items"/></div>

<div id="shoppingCart" style="display:none"></div>

<portlet:resourceURL var="removeCart" id="removeFromCart"></portlet:resourceURL>
<portlet:resourceURL var="updateCart" id="updateQty"></portlet:resourceURL>
<portlet:resourceURL var="addToShoppingCartvar" id='addToShoppingCart'></portlet:resourceURL>
<script>
var addToCartObj={
		url:"${addToShoppingCartvar}"
}
var cartObj={
		url:"${refreshCart}",
		loadingId:"cartLoading",
		cartSizeId:"totItems",
		cartType:cartCheckObj.cartType		
};
var removeCartObj={
		url:"${removeCart}",
		bundleId:"",
		cartType:""
}
var addQtyCartObj={
		url:"${updateCart}",
		bundleId:"",
		qty:"",
		cartType:""
}
var validationmsg={
		qtyInvalid:"<spring:message code="validation.quantity.format.errorMsg"/>"
}
jQuery(document).ready(function(){
	
	  	loadCartSize(cartObj);
	
});

function removeFromCart(bId, cartType){
	removeCartObj.bundleId=bId;
	removeCartObj.cartType = cartType;
	removeFromCART(removeCartObj);
	
}

function refreshQty(bId, cartType){
	addQtyCartObj.bundleId=bId;
	addQtyCartObj.qty=jQuery('#quantity_cart'+bId).val();
	addQtyCartObj.cartType=cartType;
	
	addCartQty(addQtyCartObj);
}

var dialogCart;
jQuery('.orderCart').click(function(){
	var shoppingCartObject={cart:"",id:"shoppingCart"};
    global_click_msgs.clickedFrom="orderCart";//defined in rightNavHome.jsp
	calledFromLEftNav(shoppingCartObject); 
});
</script>
<div class="clearBoth"></div>