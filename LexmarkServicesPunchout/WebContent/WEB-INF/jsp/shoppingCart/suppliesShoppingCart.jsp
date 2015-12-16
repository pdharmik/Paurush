<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<div id="successMessage" class="info banner ok" style="display: none;"></div>
<div id="errorMsgPopupCart" class="error" style="display: none;"></div>
<table  border="0" cellspacing="0" cellpadding="0" class="discrptn-table">
                  	<tr>
			                <td bgcolor="#e6e6f0" class="table-title"></td>
			                <td bgcolor="#e6e6f0" class="table-title"><spring:message code="product.suppliesTitle.productList"/></td>
			                <td bgcolor="#e6e6f0" class="table-title"><spring:message code="product.suppliesTitle.partNumber"/></td>
			                <td bgcolor="#e6e6f0" class="table-title"><spring:message code="requestInfo.heading.unitPrice"/></td>
			                <td bgcolor="#e6e6f0" class="table-title"><spring:message code="requestInfo.heading.Qty"/></td>
			                <td bgcolor="#e6e6f0" class="table-title">&nbsp;</td>
                    		
              		</tr>

		<c:forEach var="supply" items="${shoppingCartFormSupplies.cartItems}" varStatus="status">
				   
				    
				    
             <tr id="cartItem_${supply.partNumber}" class="<c:if test="${status.index % 2 ne 0}">altRow</c:if>">
              <td style="border-right:1px solid #8c8c8c;" class="table-txt"><img src="<html:imagesPath/>product-list-1.jpg" width=\"40\" height=\"26\" /> </td>
                <td style="border-right:1px solid #8c8c8c;" class="table-txt">${supply.description} </td>
                <td class="table-txt" style="border-right:1px solid #8c8c8c;">${supply.partNumber}</td>
                <td class="table-txt" style="border-right:1px solid #8c8c8c;">${supply.price}</td>
                <td class="table-txt"><input type="text" id="quantity_cart${supply.partNumber}" value="${supply.orderQuantity}" style="width:25px; text-align:center; border:1px solid #d8d8d8;"/></td>
                
                 <td class="table-txt">
                 <img onClick="removeFromCart('${supply.partNumber}', 'supplies')" src="<html:imagesPath/>delete.jpg" style="cursor: pointer;padding:0px 25px 0px 0px" alt="Delete Button" width="16" height="20" border="0">
                 <input name="UpdateCart" title="" type="button" class="button" value="Update Cart" border="0" onClick="refreshQty('${supply.partNumber}', 'supplies')">
                 </td>
              </tr>			   
				    
   		 </c:forEach>
   		 
   		 </table>