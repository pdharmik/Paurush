<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<portlet:resourceURL var="retrieveSupplyGrid" id='retrieveSupplyGrid'></portlet:resourceURL>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<style type="text/css">
#supplyGrid_container table{
	width:100%!important;
}
</style>
   <div id="portlet-wrap" style="width:100%!important">
   	 <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
   	<c:if test="${forGlobalSearch != 'true'}">
      	 <div id="breadcrum-cart-cntnr">
       	 	<div class="breadcrum-cntnr"><a style="cursor: pointer;" onclick="calledFromLEftNav('reqSupplyLink')"><spring:message code="changemanagement.popup.label.orderSupplies"/></a> > <a class="active"><spring:message code="product.suppliesTitle.productList"/></a></div>
       	 	<jsp:include page="/WEB-INF/jsp/shoppingCart/totalItems.jsp"/>
     	 </div>
     </c:if>
      </c:if>
      <div class="pageTitle"><spring:message code="suppliesProduct.pageTitle.searchResult"/></div>
      <div class="error" id="errorMsgPopup" style="display:none"></div>
      <div class="mid-cntnr">
        <div id="div_supplyGrid">
							<div id="tab_supplyGrid"  style="display:block;">
								<div id="supplyGrid_container" style="width:100%;"></div>
								<div id="loadingNotification_supplyGrid" class='gridLoading'>
	        	<br/><img src="/lexmark-punchout-theme/images/custom/loading_big.gif"/><br/>
	    	  </div>
	  							
	  							<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
	  						</div>
	  						  <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
		  						 <c:if test="${forGlobalSearch != 'true'}">
		  						 	<input name="Continue" title="" type="button" class="button floatR" value="Shopping Cart" border="0" onclick="showShoppingCart('supplies')">
		  						 </c:if>
	  						 </c:if>
	    				</div>
      </div>
    </div>
 
<script>
var bundleSupplyGrid;
if("${forGlobalSearch}" != "true"){
var url= "${retrieveSupplyGrid}"+suppliesLinkObject.getURL();
url+="&cType="+cartCheckObj.cartType;
}
var headerString = "<div class='supplies-table-cntnr'><table  border='0' cellspacing='0' cellpadding='0' class='discrptn-table-header w100'><tr><td bgcolor='#e6e6f0' class='w10 table-title' ></td><td bgcolor='#e6e6f0' class='table-title'><spring:message code='product.suppliesTitle.productList'/></td><td bgcolor='#e6e6f0' class='w10 table-title'><spring:message code='product.suppliesTitle.partNumber'/></td><td bgcolor='#e6e6f0' class='w10 table-title'><spring:message code='requestInfo.heading.unitPrice'/></td><td bgcolor='#e6e6f0' class='w10 table-title'><spring:message code='requestInfo.heading.Qty'/></td><td bgcolor='#e6e6f0' class='w10 table-title'>&nbsp;</td></tr></table></div>";

if("${sessionScope.aribaParamMap["fromAriba"]}" != "true"){
	headerString = "<div class='supplies-table-cntnr'><table  border='0' cellspacing='0' cellpadding='0' class='discrptn-table-header w100'><tr><td bgcolor='#e6e6f0' class='w10 table-title' ></td><td bgcolor='#e6e6f0' class='table-title'><spring:message code='product.suppliesTitle.productList'/></td><td bgcolor='#e6e6f0' class='w10 table-title'><spring:message code='product.suppliesTitle.partNumber'/></td></tr></table></div>";
}

bundleSupplyGrid = new dhtmlXGridObject('supplyGrid_container');
bundleSupplyGrid.setImagePath("<html:imagesPath/>gridImgs/");
//bundleSupplyGrid.setHeader("<div><table  border='0' cellspacing='0' cellpadding='0' class='discrptn-table'><tr><td bgcolor='#e6e6f0' class='table-title'></td><td bgcolor='#e6e6f0' class='table-title'><spring:message code='product.suppliesTitle.productList'/></td><td bgcolor='#e6e6f0' class='table-title'><spring:message code='product.suppliesTitle.partNumber'/></td><td bgcolor='#e6e6f0' class='table-title'><spring:message code='requestInfo.heading.unitPrice'/></td><td bgcolor='#e6e6f0' class='table-title'><spring:message code='requestInfo.heading.Qty'/></td><td bgcolor='#e6e6f0' class='table-title'>&nbsp;</td></tr></table></div>");
bundleSupplyGrid.setHeader(headerString);
bundleSupplyGrid.setInitWidths("800px");

bundleSupplyGrid.setColAlign("left");
bundleSupplyGrid.setSkin("light");
bundleSupplyGrid.setColSorting("na");
bundleSupplyGrid.setColTypes("ro");
bundleSupplyGrid.enableAutoHeight(true);
bundleSupplyGrid.enableMultiline(true);
bundleSupplyGrid.enableColumnMove(true);
bundleSupplyGrid.enableAlterCss("even_row","odd_row");
bundleSupplyGrid.init();
if("${forGlobalSearch}" == "true"){
	bundleSupplyGrid.enablePaging(true,2, 2, "pagingArea", true, "infoArea");	
}
else{
	bundleSupplyGrid.enablePaging(true,10, 2, "pagingArea", true, "infoArea");
}
bundleSupplyGrid.setPagingSkin("bricks");
bundleSupplyGrid.attachEvent("onXLE", function() {
	jQuery('#loadingNotification_supplyGrid').hide();
	if("${sessionScope.aribaParamMap["fromAriba"]}"=="true" && "${forGlobalSearch}" != "true"){
			loadCartSize(cartObj);
	}
});
bundleSupplyGrid.attachEvent("onXLS", function() {
	jQuery('#loadingNotification_supplyGrid').show();
});
bundleSupplyGrid.attachEvent("onMouseOver", function(id,ind){
	if(ind==0){				
		return false;
	}else{
		return true;
		}
	});

if("${forGlobalSearch}" == "true"){
	bundleSupplyGrid.parse("${suppliesListXml}");
}
else{
	bundleSupplyGrid.loadXML(url);
}
	
</script>
