<%@ page import="com.lexmark.services.util.ChangeMgmtConstant" %>

<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<c:set var="removeAddrArea" value="<%=ChangeMgmtConstant.DATAMANAGEMENTAREA %>"></c:set>   
<c:set var="removeAddrSubArea" value="<%=ChangeMgmtConstant.REMOVEADDRESSSUBAREA %>"></c:set>

<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">  
  
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.manageAddress.heading.addresses"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content">
       <div class="info banner err" style="display: none;" id="errorDiv"> 
			<strong><spring:message code="exception.sr.save"/></strong>
      	</div>
        
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAddress.heading.removeAddress"/>&ndash; <spring:message code="requestInfo.heading.review"/></h3>
		  <%@ include file="/WEB-INF/jsp/changemanagement/manageAddress/commonAddressConfirmation.jsp" %>
       
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>

</body>
<script type="text/javascript">
jQuery(document).ready(function(){
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	
});

/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

//this function is called when user click on BACK
function setToBack(){
	//alert('in back');
	var acturl="${AddressActionURL}";
	acturl+="&isback=true&isremove=true";
	jQuery('#addAddressForm').attr('action',acturl);
	//alert('form url='+jQuery('#addAddressForm').attr('action'));
	jQuery('#addAddressForm').submit();
}
//This is called to submit the form
//We need it as it will be called from CommonAddressConfirmation jsp
function submitForm(){
	
	/* jQuery("#areaAddAddr").val("Decommission Data");removeAddrArea removeAddrSubArea
	jQuery("#subareaAddAddr").val("Address Decommission"); */
	jQuery("#areaAddAddr").val("${removeAddrArea}");
	jQuery("#subareaAddAddr").val("${removeAddrSubArea}");
	jQuery('#prevSrNo').val('${addressForm.prevSrNo}');
	jQuery('#addAddressForm').submit();
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);					
}
/* END */
</script>