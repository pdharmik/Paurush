<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<div id="accor1">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px; overflow:auto;">
            <tr>
                  <td class="supplies-title-small"><spring:message code="product.suppliesTitle.productList"/></td>
                  <td class="supplies-title-small"><spring:message code="product.suppliesTitle.partNumber"/></td>
                  <c:if test="${fromAriba =='true'}">
                  <td class="supplies-title-small"><spring:message code="requestInfo.heading.unitPrice"/></td>                 
                  <td class="supplies-title-small"><spring:message code="requestInfo.heading.Qty"/></td>
                   </c:if>
                  <td class="supplies-title-small">&nbsp;</td>
                </tr>
           
            <c:forEach items="${accessoriesList}" var="orderPart">
             <tr>
                  <td height="35" align="left" valign="middle"><table width="260" border="0" cellspacing="0" cellpadding="0" style="margin-left:10px;">
                      <tr>
                      <td width="45" align="left" valign="middle"><img src="${orderPart.partImg}" width="40" height="26" alt="Product List"/></td>
                      <td align="left" valign="middle" class="supplies-product-list">${orderPart.description}</td>
                    </tr>
                    </table></td>
                  <td style="padding-left:5px;">${orderPart.partNumber}</td>
                   <c:if test="${fromAriba =='true'}">
                  <c:choose>
                  <c:when test="${orderPart.price eq null ||  orderPart.price eq '' || orderPart.price eq '0'}">
                  <td style="padding-left:5px;" class="price-btn-cntnr2">Price Not Available</td>
                  <td align="center" valign="middle"><input type="text" name="Quantity" id="quantity_optn_warran${orderPart.partNumber}" disabled="disabled" style="width:25px; text-align:center; border:1px solid #d8d8d8;"></td>
                  <td align="center" valign="middle"></td>
                 
                 
                 
                  </c:when>
                  <c:otherwise>
                  <td style="padding-left:5px;">${orderPart.price}</td>
                  <td align="center" valign="middle"><input type="text" name="Quantity" id="quantity_optn_warran${orderPart.partNumber}" style="width:25px; text-align:center; border:1px solid #d8d8d8;"></td>
                  <td height="30" align="center" valign="middle"><button  class="button" id="addToCartOptnWarran_${orderPart.partNumber}_${bundleId}" onClick="addOptnsWarranties(this)">Add To Cart</button></td>
                  </c:otherwise>
                  </c:choose>
                  </c:if>
                </tr>
             </c:forEach>
              </table>
            </div>
    