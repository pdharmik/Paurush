<%@ include file="/WEB-INF/jsp/include2.jsp"%>
 
<div id="wrapper-popup">
<div id="shoppingCartDetails" style="max-height: 500px; overflow-y: auto">  
   
    <div class="mid-cntnr">
    <c:choose>
    	<c:when test="${fn:length(shoppingCartForm.cartItems) >0 }">
    		<c:if test="${shoppingCartType eq 'printers' }">
    		<jsp:include page="/WEB-INF/jsp/shoppingCart/printersShoppingCart.jsp"/>
    		</c:if>
    		<c:if test="${shoppingCartType eq 'supplies' }">
    		<jsp:include page="/WEB-INF/jsp/shoppingCart/suppliesShoppingCart.jsp"/>
    		</c:if>
    		
    		
    		
   	<table>	 
   	<tr>
   	<td>
   	<c:if test="${shoppingCartType eq 'printers' }">
   	<div class="cart-btn"><input name="Back" id="backBtn" onClick="goBackToProducts()" type="button" class="button" value="Back" border="0"></div>
   	</c:if>
   	<c:if test="${shoppingCartType eq 'supplies' }"> 
   	<div class="cart-btn"><input name="Back" id="backBtn" onClick="goBackToSupplies()" type="button" class="button" value="Back" border="0"></div>
   </c:if>   	
    <div class="cart-btn"><input name="Continue Shopping" onClick="showHome(true);closeShoppingCartPopup();" type="button" class="button" value="Continue Shopping" border="0"></div>
    <div class="cart-btn"><input name="Exit" onClick="exitCart()" type="button" class="button" value="Cancel" border="0"></div>
    <div class="cart-btn"><input name="Submit Order" id="submit_order" onClick="submitOrder()" type="button" class="button" value="Submit Order" border="0"></div>
    <div class="verisign"><img src="<html:imagesPath/>verisign.jpg" width="50" height="25" alt="Verisign"></div>
    <div class="total" style="margin-right: -35%">Total*:<span id="cart_totalPrice" style="font-weight:bold; color:#c00000;">$${totalPrice}</span></div>
   </td></tr>
    </table>	
    	</c:when>
    	<c:otherwise>
    			<div style="font-weight:bold; color:#c00000;"><spring:message code="validation.quantity.cartIsEmpty"/></div>
    			<div class="cart-btn"><input name="Continue Shopping" onClick="showHome(true);closeShoppingCartPopup();" type="button" class="button" value="Continue Shopping" border="0"></div>
    			
    	</c:otherwise>
    </c:choose>
   
    
   </div>
</div>
</div> 
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
 
    
    

