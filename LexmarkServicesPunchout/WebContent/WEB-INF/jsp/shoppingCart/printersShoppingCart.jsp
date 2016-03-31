<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<div id="successMessage" class="info banner ok" style="display: none;"></div>
<div id="errorMsgPopupCart" class="error" style="display: none;"></div>
<c:forEach var="cartBundle" items="${shoppingCartFormBundles.cartItems}" varStatus="status">
	
	
				
	<div id="cartItem_${cartBundle.itemId}" class="printer-cntnr">
		<div class="prdctpge-printerimg">
		<img src="${cartBundle.imgUrl}"/>
		</div>
	    <div class="printer-details-cntnr-cart">		
			<span style="font-size:12px;">${cartBundle.itemId}</span><span style="font-size:12px;">${cartBundle.itemName}</span><br/>
            <p style="padding:0; font-size:12px; line-height:17px; font-weight:bold;">${cartBundle.itemDescription} </p>
            <c:if test="${cartBundle.showOptions}">
            	<div onclick="gotToOptions('${cartBundle.itemId}')" style="cursor: pointer;">Buy Options</div>
            </c:if>
            
	    </div>
		<div class="quntity-cart-cntnr2">
		    <div class="delete"><img onClick="removeFromCart('${cartBundle.itemId}', 'printers')" src="<html:imagesPath/>delete.jpg" alt="Delete Button" width="16" height="20" border="0"></div>
			<div class="price">Price:<span>${cartBundle.price}</span></div>
			<div class="quntyty-refresh-cntnr">
			    <div class="quntyty-cntnr2">
				    <span>Quantity:</span><input type="text"   name="Quantity" id="quantity_cart${cartBundle.itemId}" value="${cartBundle.quantity}"/>
				</div>
			</div>
 			<div class="update-cart"> 
 				<input name="UpdateCart" title="" type="button" class="button" value="Update Cart" border="0" onClick="refreshQty('${cartBundle.itemId}', 'printers')">
 			</div>
	    </div>
    </div>
				    
</c:forEach>
   		 