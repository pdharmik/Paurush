<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<div id="successMessage" class="info banner ok" style="display: none;"></div>
<div id="errorMsgPopupCart" class="error" style="display: none;"></div>
<c:forEach var="cartBundle" items="${shoppingCartFormBundles.cartItems}" varStatus="status">
	
	<c:set var="cartPrice" value="${cartBundle.price}"/>
	
	<c:if test="${not fn:containsIgnoreCase(cartBundle['class'], 'orderpart')}">
		<c:set var="cartId" value="${cartBundle.bundleId}"/>
		<c:set var="cartName" value="${cartBundle.bundleName}"/>
		<c:set var="cartQty" value="${cartBundle.bundleQty}"/>
		<c:set var="cartDesc" value="${cartBundle.mpsDescription}"/>
		<c:set var="configId" value="${cartBundle.configId}"/>
		
	</c:if>
	
	<c:if test="${fn:containsIgnoreCase(cartBundle['class'], 'orderpart')}">
		<c:set var="cartId" value="${cartBundle.partNumber}"/>
		<c:set var="cartName" value=""/>
		<c:set var="cartQty" value="${cartBundle.orderQuantity}"/>
		<c:set var="cartDesc" value="${cartBundle.description}"/>
		<c:set var="configId" value="${cartId}"/>
	</c:if>	
				
	<div id="cartItem_${cartId}" class="printer-cntnr">
	    <!--<div class="prdctpge-printerimg"><img src="<html:imagesPath/>product-printer.jpg" width="89" height="72" alt="Mono Laser Printers"></div>
		--><div class="printer-details-cntnr">
		
			<span style="font-size:12px;">${configId}</span><span style="font-size:12px;">${cartName}</span><br/>
            <p style="width:305px; padding:0; font-size:12px; line-height:17px; font-weight:bold;">${cartDesc} </p>
	    </div>
		<div class="quntity-cart-cntnr2">
		    <div class="delete"><img onClick="removeFromCart('${cartId}', 'printers')" src="<html:imagesPath/>delete.jpg" alt="Delete Button" width="16" height="20" border="0"></div>
			<div class="price">Price:<span style=" color:#c00000;">${cartPrice}</span></div>
			<div class="quntyty-refresh-cntnr">
			    <div class="quntyty-cntnr2">
				    <span>Quantity:</span><input type="text"   name="Quantity" id="quantity_cart${cartId}" value="${cartQty}" style="width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;"/>
				</div>
			</div>
 			<div class="update-cart" style="width: 50% !important;"> 
 				<input name="UpdateCart" title="" type="button" class="button" value="Update Cart" border="0" onClick="refreshQty('${cartId}', 'printers')">
 			</div>
	    </div>
    </div>
				    
</c:forEach>
   		 