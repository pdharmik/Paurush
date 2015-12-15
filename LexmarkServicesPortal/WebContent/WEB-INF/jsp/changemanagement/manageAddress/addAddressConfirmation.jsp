<!-- ADD NEW ADDRESS CONFIRMATION PAGE -->
<%@ page import="com.lexmark.services.util.ChangeMgmtConstant" %>

<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Added for CI 14-02-03 STARTS-->
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!-- Added for CI 14-02-03 ENDS-->
	<% request.setAttribute("subTabSelected","createNewRequest"); %>
	<%--@include file="/WEB-INF/jsp/common/subTab.jsp"--%>  
	<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
<c:set var="addAddrArea" value="<%=ChangeMgmtConstant.DATAMANAGEMENTAREA %>"></c:set>   
<c:set var="addAddrSubArea" value="<%=ChangeMgmtConstant.ADDADDRESSSUBAREA %>"></c:set>

<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">   
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1>
      <spring:message code="requestInfo.cm.heading.changeRequest"/>
      </h1>
       <h2 class="step"><spring:message code="requestInfo.cm.manageAddress.heading.addresses"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%--@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"--%>
      <jsp:include page="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"></jsp:include>
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
          <h3 class="pageTitle">
          	<spring:message code="requestInfo.cm.manageAddress.heading.addAddresses"/>
          	&ndash; 
          	<spring:message code="requestInfo.heading.review"/>
          </h3>
          <%--@ include file="/WEB-INF/jsp/changemanagement/manageAddress/commonAddressConfirmation.jsp" --%>
         <jsp:include page="/WEB-INF/jsp/changemanagement/manageAddress/commonAddressConfirmation.jsp"></jsp:include>
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>
  
</body>

<script type="text/javascript">
//Change Account Link Hide/Show for CI-7 Defect #12274
jQuery(document).ready(function(){
	var currentURL = window.location.href;
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

//This function is called when user clicks on BACK Button
//parameter is added so that we can classify what is the request type ADD,EDIT or Delete
//parameter is processed in the controller

function setToBack(){
	//alert('in back');
	var acturl="${AddressActionURL}";
	acturl+="&isback=true&isadd=true";
	jQuery('#addAddressForm').attr('action',acturl);
	//alert('form url='+jQuery('#addAddressForm').attr('action'));
	jQuery('#addAddressForm').submit();
}
//This function is called when user clicks on Submit Button
//parameter is added so that after any error occurs the user can get back to confirmation page
//parameter is processed in the controller
function submitForm(){
	//var acturl="${AddressActionURL}";	
	//jQuery("#areaAddAddr").val("Add Fleet data");'<portlet:actionURL><portlet:param name="action" value="changeAsset"></portlet:param></portlet:actionURL>
	var acturl='<portlet:actionURL><portlet:param name="action" value="submitAddress"></portlet:param></portlet:actionURL>';
	jQuery("#areaAddAddr").val('${addAddrArea}');
	jQuery("#subareaAddAddr").val('${addAddrSubArea}');
	//jQuery("#subareaAddAddr").val("Address Creation");
	acturl+="&addAddress=true";
	jQuery('#addAddressForm').attr('action',acturl);	
	//alert('form url='+jQuery('#addAddressForm').attr('action'));
	
	jQuery('#prevSrNo').val('${addressForm.prevSrNo}');
	
	jQuery('#addAddressForm').submit();
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);					
}
/* END */

</script>