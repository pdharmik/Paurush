<%@ include file="/WEB-INF/jsp/include2.jsp"%>

<div id="printerList">
<div id="portlet-wrap" style="width:100%!important">
      <div id="breadcrum-cart-cntnr">
        <jsp:include page="/WEB-INF/jsp/shoppingCart/totalItems.jsp"/>
      </div>
      <div class="pageTitle"><spring:message code="meterRead.label.Printer"/></div>
      <div class="mid-cntnr">
      <c:if test="${fn:length(bundleList) == 0 }">
      
     <div><spring:message code="requestInfo.error.noRecordFound"/></div>
      </c:if>
         <c:forEach var="pList" items="${bundleList}" varStatus="status">
        <div class="printer-block">
          <div class="printer-title"><a href="#" id="${pList.value}">${pList.value}</a></div>
          <div class="printer-img"><img src="<html:imagesPath/>color-laser.jpg" width="110" height="81" alt="${pList.name }"></div>
        </div>
     </c:forEach>
      </div>
    </div>
    </div>
    
<script>
var printerObject={printerType:"",id:"printerProduct"};
    jQuery('.printer-title a').click(function(){
    	printerObject.printerType=jQuery(this).attr('id');
    	global_click_msgs.clickedFrom="printerList";//defined in rightNavHome.jsp
    	calledFromLEftNav(printerObject);
});
 </script>
   
   
