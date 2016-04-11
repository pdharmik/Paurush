<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<div id="successMessage" class="info banner ok noDisplay cart-Message"></div>
<div id="errorMsgPopupCart" class="error noDisplay cart-Message"></div>
<div class="cart-table-header floatL">
	<div class="prdctpge-printerimg">ITEMS:</div>
	<div class="printer-details-cntnr-cart">&nbsp;</div>
	<div class="quntity-cart-cntnr2">QUANTITY:</div>
	<div class="cart_sub_total">TOTAL:</div>
</div>
<c:forEach var="cartBundle" items="${shoppingCartFormBundles.cartItems}" varStatus="status">
	
	
				
	<div id="cartItem_${cartBundle.itemId}" class="printer-cntnr">
		<div class="prdctpge-printerimg">
		<img src="${cartBundle.imgUrl}"/>
		</div>
	    <div class="printer-details-cntnr-cart">		
			<span>${cartBundle.itemId}</span><span>${cartBundle.itemName}</span><br/>
            <div class="item_Description">${cartBundle.itemDescription} </div>            
			<div>Price:<span id="price_${cartBundle.itemId}">${cartBundle.price}</span></div>
            <c:if test="${cartBundle.showOptions}">
            	<a href="javascript:gotToOptions('${cartBundle.itemId}')">Buy Options</a>
            </c:if>
            
	    </div>
		<div class="quntity-cart-cntnr2">
			<div class="quntyty-refresh-cntnr floatL">
			    <div class="quntyty-cntnr2">
					<input type="text" name="Quantity" id="quantity_cart${cartBundle.itemId}" value="${cartBundle.quantity}"/>
				</div>
			</div>
 			<div class="update-cart  floatL"> 
 				<img name="UpdateCart" onClick="refreshQty('${cartBundle.itemId}', 'printers')" src="<html:imagesPath/>update.gif" class="pointer img-text-top" alt="Update Button"/>
 			</div>
 			<div class="delete  floatL">
 				<img onClick="removeFromCart('${cartBundle.itemId}', 'printers')" src="<html:imagesPath/>delete.gif" class="pointer img-text-top" alt="Delete Button"/>
 			</div>
	    </div>
		<div class="cart_sub_total">
			<span align="right" id="subTotal_${cartBundle.itemId}">${cartBundle.price*cartBundle.quantity}</span>
		</div>
    </div>
				    
</c:forEach>
   		 