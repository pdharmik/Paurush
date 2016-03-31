<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css">
.floatR { float:right!important }
#search,#globalSearch {
    margin: 3px;
}
#globalSearch {
    padding: 0 8px !important;
}
</style>
      <div class="left-nav-inner">
       <c:if test='${sessionScope.aribaParamMap["isKaiser"]=="true"}'>
      	<!-- Added the Home Link -->
      	<div class="left-nav-header">        	
        	<h3><a href="#" title="Home" id="homeLink"><spring:message code="myRequest.leftNavLinks.homeLink"/></a><input class="button floatR" type="button" id="globalSearch" value="search" onclick="globalSearch();"/><input class="floatR" type="text" id="search" style="width:80px;height:10px;"/></h3>
        </div><br/>
      </c:if>
        <div class="left-nav-header">       	
			<h3><spring:message code="serviceRequest.title.myServiceRequest"/></h3>
		</div>
        <ul class="leftNavLinks">
          <li><a href="#" title="<spring:message code='myRequest.leftNavLinks.historyLink'/>"  id="historyLink"><spring:message code="myRequest.leftNavLinks.historyLink"/></a></li>
          <c:if test='${sessionScope.aribaParamMap["isKaiser"]=="false"}'>
	      <li><a href="#" title="<spring:message code='requestInfo.header.requestprinters'/>"id="reqPrinterLink" ><spring:message code="requestInfo.header.requestprinters"/></a></li>
	      <li ><a href="#" title="<spring:message code='changemanagement.popup.label.orderSupplies'/>"id="reqSupplyLink"><spring:message code="changemanagement.popup.label.orderSupplies"/></a></li>
	      </c:if>
        </ul>
      </div>

<script>

jQuery('.leftNavLinks a').click(function(){
	raiseEvent(jQuery(this).attr('id'));
});

function raiseEvent(msg){
	//"loadOtherPortlet",[msg]
	jQuery.event.trigger("loadOtherPortlet",msg);
	
}

/* Functionality for the click of Home Link */
jQuery(".left-nav-header #homeLink").click(function(){
	showHome();
});

function globalSearch(){
	var searchNumber = document.getElementById("search").value;
	//showOverlay();
	//window.location.href='';
	//window.location.href='<portlet:renderURL><portlet:param name="action" value="globalSearch" /></portlet:renderURL>';
	raiseEvent("globalSearchList");
	getDataForGlobalSearch({
		  "searchNumber":searchNumber
	  });
	
}

</script>