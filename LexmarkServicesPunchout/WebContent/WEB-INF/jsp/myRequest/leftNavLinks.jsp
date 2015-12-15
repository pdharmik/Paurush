<%@ include file="/WEB-INF/jsp/include.jsp"%>

      <div class="left-nav-inner">
      <c:if test="${acntType eq 'KAISER'}">
      	<!-- Added the Home Link -->
      	<div class="left-nav-header">        	
        	<h3><a href="#" title="Home" id="homeLink"><spring:message code="myRequest.leftNavLinks.homeLink"/></a></h3>
        </div><br/>
      </c:if>
        <div class="left-nav-header">       	
			<h3><spring:message code="serviceRequest.title.myServiceRequest"/></h3>
		</div>
        <ul class="leftNavLinks">
          <li><a href="#" title="<spring:message code='myRequest.leftNavLinks.historyLink'/>"  id="historyLink"><spring:message code="myRequest.leftNavLinks.historyLink"/></a></li>
	      <li><a href="#" title="<spring:message code='requestInfo.header.requestprinters'/>"id="reqPrinterLink" ><spring:message code="requestInfo.header.requestprinters"/></a></li>
	      <li ><a href="#" title="<spring:message code='changemanagement.popup.label.orderSupplies'/>"id="reqSupplyLink"><spring:message code="changemanagement.popup.label.orderSupplies"/></a></li>
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

</script>