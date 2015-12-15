<%-- CHANGE ADDRESS CONFIRMATION --%>
<%@ page import="com.lexmark.services.util.ChangeMgmtConstant" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- ----------------------- --%>
<portlet:actionURL var="submitAddress">
	<portlet:param name="action" value="submitAddress"></portlet:param>
</portlet:actionURL>
<!-- Added for CI 14-02-03 -->
<portlet:resourceURL var="displayAttachmentURL" id="displayAttachment">
	
</portlet:resourceURL>

<c:set var="changeAddrArea" value="<%=ChangeMgmtConstant.DATAMANAGEMENTAREA %>"></c:set>   
<c:set var="changeAddrSubArea" value="<%=ChangeMgmtConstant.CHANGEADDRESSSUBAREA %>"></c:set>

<%-- ----------------------- --%>
<script type="text/javascript">

/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

//Function is called when user clicks on BaCk
function setToBack(){
	//alert('in back');
	var acturl="${submitAddress}";
	acturl+="&isback=true&ischange=true";
	jQuery('#changeAddressFormNew').attr('action',acturl);
	//alert('form url='+jQuery('#changeAddressFormNew').attr('action'));
	jQuery('#changeAddressFormNew').submit();
}

jQuery(document).ready(function(){
	var currentURL = window.location.href;
	jQuery('#addressID').val("${addressDetailsOld['addressId']}");
	var error=<%=request.getParameter("errorOccured")%>
		if (error == true)
			jQuery('#errorDiv').show();
		
		
		//jQuery("#areaChangeAddr").val("Change Fleet Data");
		jQuery("#areaChangeAddr").val("${changeAddrArea}");
		jQuery("#subareaChangeAddr").val("${changeAddrSubArea}");
		//jQuery("#subareaChangeAddr").val("Address Data Management");
		jQuery('#prevSrNo').val('${addressForm.prevSrNo}');
		
		textScroll(document.getElementById('description'));
		textScroll(document.getElementById('costCenter'));
//		Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		else
			{
			jQuery('#changeaccount').hide();
			}
});
//Added for CI 14-02-03
function openAttachment(fileName){
	//alert(fileName);
	window.location="${displayAttachmentURL}&fileName="+fileName;
	
}


/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAddressPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageaddresses",[],[]);					
}
/* END */
</script>

<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
<form:form id="changeAddressFormNew" action="${submitAddress}&isChangeAddress=true" method="post" commandName="addressForm">
<form:hidden path="submitToken" />
<div style="display:none">														
						<form:input id="addressID" path="genericAddress.addressId" />
						<form:input id="addressName" path="genericAddress.addressName" />
						<form:input id="storeFrontName" path="genericAddress.storeFrontName" />
						<form:input id="address1" path="genericAddress.addressLine1" />
						<form:input id="address2" path="genericAddress.addressLine2" />
			
						<form:input id="country" path="genericAddress.country" />
						<form:input id="state" path="genericAddress.state" />
						<form:input id="state" path="genericAddress.province" />
						<form:input id="city" path="genericAddress.city" />
						<form:input id="poCode" path="genericAddress.postalCode" />
						<form:hidden path="prevSrNo" id="prevSrNo"/>
						
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
						<form:hidden path="attachmentDescription" id="attachmentDescription"/> <!--Added for CI 14-02-03-->
						<form:hidden path="area" id="areaChangeAddr"/>
						<form:hidden path="subArea" id="subareaChangeAddr"/>							
		          		<form:input id="custReferenceId" path="serviceRequest.customerReferenceId"/>
		             	<form:input id="costCenter" path="serviceRequest.costCenter" />
		           		<form:textarea id="addtnlDescription" rows="3" path="serviceRequest.addtnlDescription"/>
		             	<form:input id="effDtOfChange" size="10" path="serviceRequest.requestedEffectiveDate"/>				
</div>
<span style="display: none">
	                	<form:input id="primaryContactId" path="serviceRequest.primaryContact.contactId" /> 
						<form:input id="primaryFirstName" path="serviceRequest.primaryContact.firstName" /> 
						<form:input id="primaryLastName" path="serviceRequest.primaryContact.lastName" /> 
						<form:input id="primaryWorkPhone" path="serviceRequest.primaryContact.workPhone" /> 
						<form:input id="primaryEmailAddress" path="serviceRequest.primaryContact.emailAddress" />
						<form:input id="primaryEmailAddress" path="serviceRequest.primaryContact.alternatePhone" />
				</span>
				<span style="display: none">
						<form:input id="secondaryContactId" path="serviceRequest.secondaryContact.contactId" /> 
						<form:input id="secondaryFirstName" path="serviceRequest.secondaryContact.firstName" /> 
						<form:input id="secondaryLastName" path="serviceRequest.secondaryContact.lastName" /> 
						<form:input id="secondaryWorkPhone" path="serviceRequest.secondaryContact.workPhone" /> 
						<form:input id="secondaryEmailAddr" path="serviceRequest.secondaryContact.emailAddress" />
						<form:input id="secondaryEmailAddr" path="serviceRequest.secondaryContact.alternatePhone" />
					</span>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
  
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"></spring:message></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.manageAddress.heading.addresses"></spring:message></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong></h3></div>
        	<!-- END -->
        	
     <div class="info banner err" style="display: none;" id="errorDiv"> 
			<strong><spring:message code="exception.sr.save"/></strong>
      	</div>
    <div class="main-wrap">
      <div class="content">
	  <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAddress.heading.addressChange"></spring:message>
           &ndash;<spring:message code="requestInfo.heading.review"></spring:message></h3>
		  
			<p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
		  
          <div class="portletBlock">
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
			
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span>${addressForm.serviceRequest.primaryContact.firstName}
                    &nbsp;${addressForm.serviceRequest.primaryContact.lastName}
                    </span>
                    </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <!--<span id="primaryWorkPhoneLabel"></span> -->
                    <span>
                    ${addressForm.serviceRequest.primaryContact.workPhone}
                    </span>
                    </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <!--<span id="primaryEmailAddressLabel"></span>-->
                    <span>
                    ${addressForm.serviceRequest.primaryContact.emailAddress}
                    </span>
                    </li>
                </ul>
                  
              </div>
              <c:if test='${addressForm.serviceRequest.secondaryContact.firstName ne "" || addressForm.serviceRequest.secondaryContact.lastName ne "" }'>
			  <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <!--<span id="secondaryfirstNameLabel"></span> &nbsp;
                    <span id="secondarylastNameLabel"></span> -->
                    <span>${addressForm.serviceRequest.secondaryContact.firstName}&nbsp; ${addressForm.serviceRequest.secondaryContact.lastName}
                    </span>
                    </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                   <!--<span id="secondaryworkPhoneLabel"></span> -->
                   <span>
                   ${addressForm.serviceRequest.secondaryContact.workPhone}
                   </span>
                   </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                   <!--<span id="secondaryemailAddressLabel"></span> -->
                   <span>
                   ${addressForm.serviceRequest.secondaryContact.emailAddress}
                   </span>
                   </li>
                </ul>
                
              </div>
              </c:if>
              
            </div>
             
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span>${addressForm.serviceRequest.customerReferenceId}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                   <span id="costCenter">${addressForm.serviceRequest.costCenter}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                   <span id="description"> ${addressForm.serviceRequest.addtnlDescription}</span>
				  </li>
				  <li>
                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                  <span><%--  ${addressForm.serviceRequest.requestedEffectiveDate} --%>
                  <util:dateFormat value="${addressForm.serviceRequest.requestedEffectiveDate}">
                  </util:dateFormat>
                  </span>
				  </li>
                </ul>
			  </div>
            </div>
          </div>
          <hr class="separator" />
		  <div class="portletBlock infoBox rounded shadow">
			<div class="columnsTwo">
				<div class="columnInner">					
					<h4><spring:message code="requestInfo.heading.selectedAddress"/></h4>
						
                    <ul class="form wordBreak">
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressName"/></label>
                      <span> ${addressDetailsOld['addressName']}
                      </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.storeFrontName"/> </label>
                        <span> ${addressDetailsOld['storeFrontName']}
                         </span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/></label>
                      <span> ${addressDetailsOld['addressLine1']}
                       </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/></label>
                      <span>   ${addressDetailsOld['addressLine2']}
                       </span>
                      </li>
					 
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/></label>
                       <span> ${addressDetailsOld['country']}
                        </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/></label>
                       <span>  ${addressDetailsOld['stateGrid']}
                       <c:set var="provinceValue" value="${addressDetailsOld['province']}"/>
                        <c:if test='${provinceValue ne ""}'>
                        	/&nbsp; ${addressDetailsOld['province']}
                        </c:if>
                        </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/></label>
                      <span> ${addressDetailsOld['city']}
                       </span>
                      </li>
                      
                      
                      <c:if test="${addressDetailsOld['houseNumber'] != ''}">
                      <li>
                        <label for="officeNumber"><spring:message code="requestInfo.addressInfo.label.officeNumber"/></label>
                        <span>${addressDetailsOld['houseNumber']}</span>
                      </li>
                       </c:if> 
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.county"/></label>
                        <span>${addressDetailsOld['county']}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.district"/></label>
                        <span>${addressDetailsOld['district']}</span>
                      </li>
                      
                      
                      
                      
                      
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.postalCode"/></label>
                       <span> ${addressDetailsOld['zipPostal']}
                        </span>
                      </li>
                      
                      
                      
                      
					</ul>
				</div>
			</div>
	
			<div class="columnsTwo splitter">
				<div class="columnInner">			
				
					<h4><spring:message code="requestInfo.cm.manageAddress.heading.updateReq"></spring:message></h4>				
                    
					<ul class="form wordBreak">
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressName"/></label>
                        <span>${addressForm.genericAddress.addressName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.storeFrontName"/></label>
                        <span>${addressForm.genericAddress.storeFrontName}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/></label>
                         <span>  ${addressForm.genericAddress.addressLine1}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/></label>
                         <span>  ${addressForm.genericAddress.addressLine2}</span>
                      </li>
					  
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/></label>
                         <span>  ${addressForm.genericAddress.country}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/></label>
                       <span> ${addressForm.genericAddress.state}&nbsp;${addressForm.genericAddress.province}
                       </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/></label>
                       <span> ${addressForm.genericAddress.city}</span>
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
                      <span> ${addressForm.genericAddress.postalCode}</span>
                      </li>
					</ul>
				</div>
			</div>
		  </div>
		  <!-- Add Attachments BLOCK - Start -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine">${addressForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
            	<c:if test="${not empty attachmentForm.attachmentList}">
        <div class="portletBlock infoBox">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
						<table>
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${attachmentForm.attachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
				</div>
				</c:if>
        <!-- Add Attachments BLOCK - End -->
		  <div class="buttonContainer">
            <button class="button_cancel" type="button" onclick="setToBack();"><spring:message code="button.back"/></button>
			<button class="button_cancel ie7_fix_margin" type="button" onclick="redirectToHistory('others');" ><spring:message code="button.cancel"/></button>
            <button class="button" type="button" onclick="jQuery('#changeAddressFormNew').submit();"><spring:message code="button.submit"/></button>
          </div>
        
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
  </div>

 

</form:form>
</body>