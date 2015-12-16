<?xml version="1.0" encoding="UTF-8"?>
<%@ include file="/WEB-INF/jsp/include2.jsp"%>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="supply" items="${supplyItems}" varStatus="status">
		<row id="${status.index}">
		    <cell><![CDATA[<div id="supply${status.index+1}">
          
      
        <div class="supplies-table-cntnr"> 
		<table  border="0" cellspacing="0" cellpadding="0" class="discrptn-table">
		
                  
             <tr>
              <td style="border-right:1px solid #8c8c8c;" class="w10 table-txt"><img src="<html:imagesPath/>product-list-1.jpg" width=\"40\" height=\"26\" /> </td>
                <td style="border-right:1px solid #8c8c8c;" class="table-txt">${supply.description} </td>
                <td class="w10 table-txt">${supply.partNumber}</td>
                <c:if test="${fromAriba == 'true'}">
                <td class="w10 table-txt" style="border-right:1px solid #8c8c8c;border-left:1px solid #8c8c8c">${supply.price}</td>
                </c:if>
                
                <c:set var="isExit" value="0" />
                <c:choose>
                <c:when test="${supply.price eq null ||  supply.price eq '' || supply.price eq '0'}">                	
	                <c:set var="suppliesCatalogListItems" value="${supply.suppliesCatalogList}"/>
	                <c:forEach var="supplyCatalog" items="${suppliesCatalogListItems}">
	                	<c:if test="${isExit == '0' && supplyCatalog.billingModel ne null && supplyCatalog.billingModel == 'Ship and Bill'}">	                		
	                		<c:set var="isExit" value="1" />
	                	</c:if>
	                </c:forEach>	                
                </c:when>                
                </c:choose>
                 <c:if test="${fromAriba == 'true'}">
                <td class="w10 table-txt"><input type="text" id="quantity${supply.partNumber}" value="${supply.orderQuantity}" <c:if test="${isExit == '1'}">disabled="disabled"</c:if> style="width:25px; text-align:center; border:1px solid #d8d8d8;"/></td>
                
                <c:set var="cartText" value="Add To Cart"/>
                <c:if test="${supply.orderQuantity ne null && supply.orderQuantity ne 0}">
                  	<c:set var="cartText" value="Update Cart"/>
                </c:if>
                 <td class="w10 table-txt"><input type="button" id="cartButton${supply.partNumber}" onClick="moveToCartSupplies('${supply.partNumber}',this.id)" class="button" value="${cartText}" /></td>
              	</c:if>
              </tr>
               
                </table>
                </div>
                </div>
               
            ]]></cell>
           
        </row>
</c:forEach>
</rows>