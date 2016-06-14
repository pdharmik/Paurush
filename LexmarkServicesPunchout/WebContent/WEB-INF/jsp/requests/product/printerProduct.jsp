<%@ include file="/WEB-INF/jsp/include2.jsp"%>



<div id="layout-grid">
   
    <div id="portlet-wrap">
    	 <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
          <div id="breadcrum-cart-cntnr">
       		 <div class="breadcrum-cntnr"><a href="#" onclick="calledFromLEftNav('reqPrinterLink')"><spring:message code="requestInfo.header.requestprinters"/></a> > <a href="#" class="active" id="printerType"></a></div>
       			 
     	 </div>
     	</c:if>
     		<div class="clearBoth"></div>
          <div class="pageTitle" id="pageTitle"></div>
          <div class="error noDisplay" id="errorMsgPopup"></div>
          <div id="bundle">
          <jsp:include page="bundle.jsp"/>
          </div>
            <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
            <c:if test="${forGlobalSearch != 'true'}">
	          <div class="price-btn-cntnr">
	              <input name="Continue" title="" type="button" class="button floatR" value="Shopping Cart" border="0" onclick="showShoppingCart('printers')">
	              <div class="clearBoth"></div>
	          </div>
            </c:if>
           </c:if>
        </div>
    
  </div>
   

