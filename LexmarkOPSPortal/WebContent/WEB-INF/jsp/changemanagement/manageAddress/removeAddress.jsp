
<%-- REMOVE ADDRESS JSP --%>

<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->


<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@include  file="/WEB-INF/jsp/common/subTab.jsp"%>
<jsp:include page="../../common/validationMPS.jsp" />

<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>

<portlet:renderURL var="removeAddressConfirmationURL">
	<portlet:param name="action" value="addressConfirm"></portlet:param>
</portlet:renderURL>


<portlet:renderURL var="setDeleteAddressSession">
	<portlet:param name="action" value="setOldAddress"/>
</portlet:renderURL>



<form:form id="addressForm" method="post" commandName="addressForm">
<%-- Below section is for the prev sr no and update flag binding--%>
<form:hidden path="prevSrNo" id="prevSrNo"/>


<div style="display:none">
<%-- setting the hidded form values --%>
	<form:input id="addressId" path="genericAddress.addressId"/>
	<form:input id="addressName" path="genericAddress.addressName"/>
	<form:input id="storeFrontName" path="genericAddress.storeFrontName"/>
	<form:input id="address1" path="genericAddress.addressLine1"/>
	<form:input id="address2" path="genericAddress.addressLine2"/>
<%--	<form:input id="address3" path="genericAddress.addressLine3"/>    --%>
	<form:input id="city" path="genericAddress.city"/>
	<form:input id="country" path="genericAddress.country"/>
	<form:input id="state" path="genericAddress.state"/>
	<form:input id="poCode" path="genericAddress.postalCode"/>
	
	
						<form:input  path="genericAddress.county" />
						<form:input path="genericAddress.officeNumber" />
						<form:input path="genericAddress.stateFullName" />
						<form:input path="genericAddress.district" />
						<form:input path="genericAddress.region" />
						<form:input path="genericAddress.stateCode" />
						<form:input path="genericAddress.latitude" />
						<form:input path="genericAddress.longitude" />
						<form:input path="genericAddress.savedErrorMessage" />
						<form:input path="genericAddress.countryISOCode" />
						<form:input  path="genericAddress.isAddressCleansed" />
</div>



  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1>
      	<spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
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
    
    <div class="error" id="errorMsg" style="display:none"></div>
    
    <div class="main-wrap">
      <div class="content">
        
        
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle">
          <spring:message code="requestInfo.cm.manageAddress.heading.removeAddress"/>
          </h3>
           <spring:hasBindErrors name="addressForm">
          	<div class="error">
				<c:forEach var="error" items="${errors.allErrors}">
			   		<li><strong><spring:message code="${error.code}"/></strong></li>
            	</c:forEach>
     		</div>	
	      </spring:hasBindErrors>
	      <!-- Added for CI 14-02-03 START-->
	      <div id="jsValidationErrors" class="error" style="display: none;"></div>	
	        <div id="validationErrors" class="error" style="display: none;"></div>
	        <%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
		  <!-- Added for CI 14-02-03 ENDS--> 
				 <jsp:include page="/WEB-INF/jsp/common/commonContactInfo.jsp"/>            
          <hr class="separator" />
		  
			<p class="info"><spring:message code="requestInfo.cm.manageAddress.heading.removeAddressReq"/></p>
		
		  
          
		  <div class="portletBlock infoBox rounded shadow">
		  	<div class="columnsOne">
            	<div class="columnInner">
		  	
				<h4>
				<a href="<portlet:renderURL/>" title="Select Different Address" id="lightboxAddress">
				<spring:message code="requestInfo.link.selectADifferentAddress"/>
				</a>
				<spring:message code="requestInfo.heading.selectedAddress"/>
				</h4>
				
                    <ul class="form">
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressName"/> </label>
                        <span id="addressNamelabel">${addressForm.genericAddress.addressName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.storeFrontName"/></label>
                       <span id="storeFrontNamelabel"> ${addressForm.genericAddress.storeFrontName}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/> </label>
                       <span id="addressLine1label"> ${addressForm.genericAddress.addressLine1}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/> </label>
                       <span id="addressLine2label"> ${addressForm.genericAddress.addressLine2}</span>
                      </li>
					  
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/></label>
                        <span id="citylabel">${addressForm.genericAddress.city}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/></label>
                       <span id="countrylabel"> ${addressForm.genericAddress.country}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/></label>
                        <span id="statelabel">${addressForm.genericAddress.state}
                         <c:set var="provinceValue" value="${addressForm.genericAddress.province}"/>
                        <c:if test='${provinceValue ne ""}'>
                        	/&nbsp; ${addressForm.genericAddress.province}
                        </c:if> 
                        </span>
                      </li>
					  
					  
					  
                       <c:if test="${addressForm.genericAddress.officeNumber != ''}">
                      <li>
                        <label for="officeNumber"><spring:message code="requestInfo.addressInfo.label.officeNumber"/></label>
                        <span>${addressForm.genericAddress.officeNumber}</span>
                      </li>
                       </c:if> 
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.county"/></label>
                        <span>${addressForm.genericAddress.county}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.district"/></label>
                        <span>${addressForm.genericAddress.district}</span>
                      </li>
					  
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.postalCode"/></label>
                       <span id="postalCodelabel"> ${addressForm.genericAddress.postalCode}</span>
                      </li>
					</ul>
			</div>
		  </div>
		  </div>
		  <form:hidden path="requestName" id="typeOfReq"/>
		  <input type="hidden" name="attachmentDescriptionID" id="attachmentDescriptionID" />  <!--//Added for CI 14-02-03 -->
</form:form>
		<!--Added for CI 14-02-03  STARTS -->
		<jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" />
		
        <input type="hidden" id="fileCount" />
        <!--Added for CI 14-02-03  ENDS-->
		  <div class="buttonContainer">
            <button class="button_cancel" type="button" onclick="redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button class="button" type="button" onclick="setValues();"><spring:message code="button.continue"/></button>
          </div>
		  
       
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>



<script type="text/javascript">
var portlet_name="<%=LexmarkSPOmnitureConstants.DECOMMISSIONADDRESS %>";
var action_primary="<%=LexmarkSPOmnitureConstants.DECOMMISSIONADDRESSCONTACT %>";
var action_secondary="<%=LexmarkSPOmnitureConstants.DECOMMISSIONADDRESSREMOVEADDITIONALCONTACT%>";
var pageType = "removeAddress"; //Added for page type for CI 14-02-03
jQuery(document).ready(function(){
	var currentURL = window.location.href;
	jQuery('#attachmentDescription').val('${addressForm.attachmentDescription}');
	<%-- jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>'); --%>
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

function setValues(){
	jQuery("#prevSrNo").val('${addressForm.prevSrNo}');	
	//alert("prev sr no "+jQuery('#prevSrNo').val());
	
	jQuery('#errorMsg').html('');
	var validationFlag=true;
	/* Check field length */
	if(!descValidate(jQuery('#addtnlDescription').val(),"errorMsg")) {
		validationFlag = false;
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	/* Added by jyoti effective date validation */
	if( jQuery('#effDtOfChange').val()!="" && (!validateFormat(jQuery('#effDtOfChange').val(),"effDtOfChange","errorMsg"))){
		
		validationFlag=false;
		jQuery('#effDtOfChange').addClass('errorColor');
	}
	if(validationFlag==false)
	{
		jQuery('#errorMsg').show();
		jQuery(document).scrollTop(0);
	}
	else
		{
			goForSubmit();
		}
}

function goForSubmit()
{	
	jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	var removeAddressConfirmUrl="${removeAddressConfirmationURL}&timeZoneOffset=" + timezoneOffset;
	jQuery("#addressForm").attr("action", removeAddressConfirmUrl);
	
	jQuery("#typeOfReq").val("remove");	
	jQuery('#addressForm').submit();
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);					
}
 /* END */
</script>
