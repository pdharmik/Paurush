<?xml version="1.0" encoding="UTF-8"?>
<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<portlet:resourceURL var="getOptionsAndWarrantiesVar" id="getOptionsAndWarranties"></portlet:resourceURL>

<rows total_count="${totalcount}" pos="${startPos}">
	<c:forEach var="bundle" items="${bundleItems}" varStatus="status">
		<row id="${bundle.bundleId}">
		
		<cell><![CDATA[${getOptionsAndWarrantiesVar}&bundleId=${bundle.bundleId}&cNum=${bundle.contractNumber}&cartType=printers<%-- Options warranties data goes here--%>]]>
		</cell>
		    <cell><![CDATA[<div id="bundle${status.index+1}">
        <div class="printer-cntnr">
              <div class="prdctpge-printerimg"><img src="<html:imagesPath/>product-printer.jpg" width="89" height="72" alt="Mono Laser Printers"></div>
              <div class="printer-details-cntnr">
            <br/><span style="font-size:24px;font-weight:bold;" id="printerId">${bundle.configId}</span><br/>
            <span style="font-size:12px;">${bundle.mpsDescription}</span><br/><br/>
            <span style="font-size:12px;">${bundle.bundleName}</span><br/>
            <p style="width:90%; padding:0; font-size:12px; line-height:17px;">${bundle.description} <a href = "javascript:learnMorePopup('${bundle.bundleId}');">&nbsp;&nbsp;<B><spring:message code="shoppingCart.printers.link"/></B></a>
            </p>
               
            
            <table width="300" border="0" cellspacing="0" cellpadding="0" class="discrptn-table-parts">
                  <tr>
                <td style="background-color:#A9A9A9 !important" class="table-title" style="font-size: 15px;"><b><spring:message code="orderSupplies.placeOrderHeader.partNumber"/></b></td>
                <td style="background-color:#A9A9A9 !important" class="table-title" style="font-size: 15px;"><b><spring:message code="orderSupplies.placeOrderHeader.description"/></b></td>
                <td style="background-color:#A9A9A9 !important" class="table-title" style="font-size: 15px;"><b><spring:message code="requestInfo.heading.Qty"/></b></td>
              </tr>
       <c:choose>
               <c:when test="${fn:length(bundle.partList) eq 0}">
              
                   <td bgcolor="#e6e6f0" color="#c82424">No Records Available</td>
                   <td bgcolor="#e6e6f0"></td>
                   <td bgcolor="#e6e6f0"></td>
               </c:when>
             <c:otherwise>
           <c:set var="partType" value=""/>
                <c:forEach var="partList" items="${bundle.partList}" varStatus="counter" begin="0">
                <c:if test="${partList.partType == 'Printers'}">
           			<c:set var="partType" value="${partList.partNumber}"/>     	
                </c:if> 
                   <tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>">
                     <td style="border-right:1px solid #8c8c8c;" class="table-txt">${partList.partNumber} </td>
                     <td class="table-txt" style="border-right:1px solid #8c8c8c;">${partList.description}</td>
                     <td class="table-txt">${partList.orderQuantity}</td>
                   </tr>
                </c:forEach>
            </c:otherwise>
      </c:choose>
              
             <input type="hidden" id="${bundle.bundleId}_partType" value="${partType}" />
                
                </table>
          </div>
          <c:if test="${fromAriba == 'true'}">
          <c:choose>
            	<c:when test="${not empty bundle.price}">
              <div class="quntity-cart-cntnr">
            <div class="quntyty-cntnr"> <span><spring:message code="shoppingCart.printers.quantity"/></span>
            <c:choose>
            	<c:when test="${bundle.bundleQty ne null && bundle.bundleQty ne 0}">
                  <input type="text" name="Quantity" id="quantity${bundle.bundleId}" value="${bundle.bundleQty}"  style="width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;">
                </c:when>
                <c:otherwise>
                <input type="text" name="Quantity" id="quantity${bundle.bundleId}"  style="width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;">
                </c:otherwise>
                </c:choose>
                </div>
                   
            <div class="price-btn-cntnr"><div>${bundle.price}<c:if test="${not empty bundle.price}">(${bundle.currency})</c:if></div>
             <c:choose>
            	<c:when test="${bundle.bundleQty ne null && bundle.bundleQty ne 0}">
              <input name="Update Cart" title="" id="cartButton${bundle.bundleId}" type="button" class="button" value="Update Cart" border="0" onclick="moveToCart('${bundle.bundleId}',this.id)">
              
               </c:when>
                <c:otherwise>
                 <input name="Add to Cart" title="" id="cartButton${bundle.bundleId}" type="button" class="button" value="Add to Cart" border="0" onclick="moveToCart('${bundle.bundleId}',this.id)">
                 </c:otherwise>
                </c:choose>
              </div>
              
          </div>
          </c:when>
                 <c:otherwise>
                <div class="quntity-cart-cntnr">
            <div class="quntyty-cntnr"> <span><spring:message code="shoppingCart.printers.quantity"/></span>
            <c:choose>
            	<c:when test="${bundle.bundleQty ne null && bundle.bundleQty ne 0}">
                  <input type="text" name="Quantity" id="quantity${bundle.bundleId}" value="${bundle.bundleQty}"  style="width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;">
                </c:when>
                <c:otherwise>
                <input type="text" name="Quantity" <c:if test="${bundle.billingModel == 'Ship and Bill'}">disabled="disabled"</c:if> id="quantity${bundle.bundleId}"  style="width:25px; margin:0 0 0 5px; text-align:center; border:1px solid #d8d8d8;">
                </c:otherwise>
                </c:choose>
                </div>
                   
           <div class="floatR"> 
               <div class="price-btn-cntnr" style="width: 100%!important;"><spring:message code="requestInfo.error.priceUnavailable"/></div>
                
                </div>
             <c:choose>
            	<c:when test="${bundle.bundleQty ne null && bundle.bundleQty ne 0}">
              <div><input name="Update Cart" title="" id="cartButton${bundle.bundleId}" type="button" class="button floatR" value="Update Cart" border="0" onclick="moveToCart('${bundle.bundleId}',this.id)">
            </div>
               </c:when>
                <c:otherwise>
                 <input name="Add to Cart" title="" id="cartButton${bundle.bundleId}" type="button" class="button floatR" value="Add to Cart" border="0" onclick="moveToCart('${bundle.bundleId}',this.id)">
                 </c:otherwise>
                </c:choose>
              </div>
              
          </div>
                  </c:otherwise>
                </c:choose>
                </c:if>
          <div id="accObj" style="width: 100%;float:left;"></div>
          <div class="gridSubrowHeader">
          <div style="width:80%;float: left;"><spring:message code="product.bundleGridXml.optionsAndWarranties"/></div>
          <div style="width:19%;display: inline-block;"><img id="optn_warrnt_img_${bundle.bundleId}" src="<html:imagesPath/>plus.png" onClick="openSubRow('${bundle.bundleId}',this)"/>
          <img  style="display:none" id="optn_warrnt_loading_${bundle.bundleId}" src="<html:imagesPath/>loading-icon.gif"/></div>

			</div>
            </div>]]></cell>
		</row>
</c:forEach>
</rows>


