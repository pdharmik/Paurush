<%@ include file="/WEB-INF/jsp/include2.jsp"%>



<script>
var printerType;
$(document).ready(function(){
  printerType=printerObject.printerType;
	jQuery('#printerType').html(printerType+" Printers");
	jQuery('#pageTitle').html(printerType+" Printers");
})
var printerType=printerObject.printerType; //printerObject resides in printerlist jsp

</script>

      <div id="layout-grid">
   
    <div id="portlet-wrap" style="width:100%!important">
          <div id="breadcrum-cart-cntnr">
        <div class="breadcrum-cntnr"><a href="#" onclick="calledFromLEftNav('reqPrinterLink')"><spring:message code="requestInfo.header.requestprinters"/></a> > <a href="#" class="active" id="printerType"></a></div>
        <jsp:include page="/WEB-INF/jsp/shoppingCart/totalItems.jsp"/>
      </div>
          <div class="pageTitle" id="pageTitle"></div>
          <div class="error" id="errorMsgPopup" style="display:none"></div>
          <div id="bundle">
          <jsp:include page="bundle.jsp"/>
          </div>
          <div class="price-btn-cntnr">
              <input name="Continue" title="" type="button" class="button" value="Continue" border="0" onclick="showShoppingCart()">
              </div>
        </div>
    
  </div>
   

