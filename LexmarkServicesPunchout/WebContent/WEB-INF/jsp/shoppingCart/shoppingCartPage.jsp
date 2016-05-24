<%@ include file="/WEB-INF/jsp/include2.jsp"%>
 
<div id="wrapper-popup">
<br/>
<br/>
<div id="shoppingCartDetails" >  
   
    <div class="mid-cntnr">
    <c:choose>
    	<c:when test="${fn:length(shoppingCartFormBundles.cartItems) >0 || fn:length(shoppingCartFormSupplies.cartItems) >0}">
    		<div class="floatR">
    			<div class="verisign"><img src="<html:imagesPath/>verisign.jpg" width="50" height="25" alt="Verisign"></div>  	
			    <div class="cart-btn"><input name="Continue Shopping" onClick="showHome(true);closeShoppingCartPopup();" type="button" class="altWhitebutton button" value="Continue Shopping" border="0"/></div>
			    <div class="cart-btn"><input name="Submit Order" id="submit_order" onClick="submitOrder()" type="button" class="altGreenbutton button" value="Submit Order" border="0"/></div>
			    <div class="clearBoth"></div>
   			</div>
    		<c:if test="${shoppingCartType eq 'printers' }">
    			<jsp:include page="/WEB-INF/jsp/shoppingCart/printersShoppingCart.jsp"/>
    		</c:if>
    		<c:if test="${shoppingCartType eq 'supplies' }">
    			<jsp:include page="/WEB-INF/jsp/shoppingCart/suppliesShoppingCart.jsp"/>
    		</c:if>
    		<c:if test="${shoppingCartType eq 'globalSearch' }">
    			<jsp:include page="/WEB-INF/jsp/shoppingCart/printersShoppingCart.jsp"/>
    			<jsp:include page="/WEB-INF/jsp/shoppingCart/suppliesShoppingCart.jsp"/>
    		</c:if>
		    <div class="total">Total*:<span class="floatR" id="cart_totalPrice"><strong>$${totalPrice}</strong></span></div>
		    <div class="clearBoth"></div>
   			<div class="floatR">
			    <div class="verisign"><img src="<html:imagesPath/>verisign.jpg" width="50" height="25" alt="Verisign"></div> 	
			    <div class="cart-btn"><input name="Continue Shopping" onClick="showHome(true);closeShoppingCartPopup();" type="button" class="altWhitebutton button" value="Continue Shopping" border="0"/></div>
			    <div class="cart-btn"><input name="Submit Order" id="submit_order" onClick="submitOrder()" type="button" class="altGreenbutton button" value="Submit Order" border="0"/></div>
   			</div>
    	</c:when>
    	<c:otherwise>
    			<div class="redColor"><strong><spring:message code="validation.quantity.cartIsEmpty"/></strong></div>
    			<div class="cart-btn"><input name="Continue Shopping" onClick="showHome(true);closeShoppingCartPopup();" type="button" class="button" value="Continue Shopping" border="0"></div>
    	</c:otherwise>
    </c:choose>
   </div>
</div>
</div> 
<div class="clearBoth"></div>
   <script>
   var shoppingCartObj={fromCart:""};
   function goBackToProducts(){
	   global_click_msgs.clickedFrom="shoppingCartPage";
	   shoppingCartObj.fromCart="true";	   
       calledFromLEftNav("printerProduct");
   }
   function goBackToSupplies(){	   
	   calledFromLEftNav("suppliesProduct");
   }
   </script>
 
    
    

