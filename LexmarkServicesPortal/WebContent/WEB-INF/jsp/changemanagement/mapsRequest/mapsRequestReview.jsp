<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","fleet-management"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import="com.lexmark.services.form.FileUploadForm"%>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<script type="text/javascript"><%@ include file="../../../../js/showCoordinates.js"%></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<portlet:actionURL var="createRequestAction">
	<portlet:param name="action" value="createCMRequest" />
</portlet:actionURL>
<portlet:actionURL var='backToEditAction'>
	<portlet:param name='action' value='backToEdit' />
</portlet:actionURL>

<script type="text/javascript">

/*
 * From summary page back is disabled
 */
 
 
window.history.forward();
function backDisable() { 
	
	window.history.forward(); 
}

function submitRequest(){
	
	showOverlay();
	var link="<%=createRequestAction %>";
	
	document.getElementById('mapsRequestFormId').action = link;
	
	document.getElementById('mapsRequestFormId').submit();
}

function backToEdit(){
	showOverlay();
	var link="<%=backToEditAction %>";
	
	var currentURL = "${fleet}";
	//if(currentURL.indexOf('fleet=true') == -1){
		if(currentURL == "true"){
		var fleetvalue='true';
	}
	else{
		var fleetvalue='false';
	}
	
	document.getElementById('mapsRequestFormId').action = link+"&fleet="+fleetvalue;
	
	document.getElementById('mapsRequestFormId').submit();
}
function openTemplate(type,fileName){
	if(type=='CHLTemplate'){
		window.location="${attachmentURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${attachmentURL}&fileType=Attachment&fileName="+fileName;
	}
	
}

jQuery(document).ready(function() {
	
	 coordinates($('#addressCoordinatesXPreDebriefRFV').val(),$('#addressCoordinatesYPreDebriefRFV').val());	
	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	c=1;
	
	<c:if test="${fileMap != null}">
	jQuery('#fileListTable').empty();
	responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		    
	 		responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileMap}" var="entry">
	 		if(c%2==0){
 				responseText = responseText + '<tr class="altRow">';
 			}
 			else{
 				responseText = responseText + '<tr>';
 			}
 			c++;
	 			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openTemplate(\'Attachment\',\'${entry.key}\');"><c:out value="${entry.value.displayFileName}" /></a>' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			
	 			responseText = responseText + '</tr>';
	 			
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable').append(responseText);
	 		

	</c:if>

	<c:if test="${fileMap == null}">
	jQuery('#fileListTable').empty();
	</c:if>
	 
	<c:if test="${exceptionList != null}">
		window.scrollTo(0,0);
	</c:if>

	textScroll(document.getElementById('specialInstruction'));
	textScroll(document.getElementById('costCenter'));
	textScroll(document.getElementById('rqstDesc'));
	
});

function onCancelClick() {
	//showOverlay();
	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					window.location.href='fleet-management';
				}else{
					return false;
					}
			});
};
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showMapsRequestPage'/></portlet:actionURL>&friendlyURL="+populateURL("mapsrequest",[],[]);					
}

</script>

<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.heading.mapsRequest"/></h2> 
      </div>

       <c:if test="${changeAccountLink}">
      <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	</c:if>
        
<div class="main-wrap">
	<div class="content">
				
		
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.heading.mapsRequest"/> - <spring:message code="requestInfo.heading.review"/></h3>
          	
				<p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/>
			 
          	
          	<!-- Exceptions displayed in the below div -->
	          	 <c:if test="${exceptionList != null}">
	       			<div class="info banner err" id="dispalyExceptions">
		       			<c:forEach items="${exceptionList}" var="entry">
				       		<li><strong>Exception - ${entry}</strong></li>		       		
				     	</c:forEach>
	      			</div>
	          	</c:if>  
		  <form:form id="mapsRequestFormId" commandName="mapsRequestForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  <form:hidden path="submitToken" />
	          <div class="portletBlock">
	            <div class="columnsTwo">
	              <div class="columnInner rounded infoBox shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>				
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel"> ${mapsRequestForm.serviceRequest.primaryContact.firstName } ${mapsRequestForm.serviceRequest.primaryContact.lastName } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel">${mapsRequestForm.serviceRequest.primaryContact.workPhone } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel">${mapsRequestForm.serviceRequest.primaryContact.emailAddress }</span>  
	                  </li>
	                </ul>
	                <span style="display: none">
	                	<form:input path="serviceRequest.requestor.contactId" /> 
						<form:input path="serviceRequest.requestor.firstName" /> 
						<form:input path="serviceRequest.requestor.lastName" /> 
						<form:input path="serviceRequest.requestor.workPhone" /> 
						<form:input path="serviceRequest.requestor.emailAddress" />
						
						<form:input path="serviceRequest.primaryContact.contactId" /> 
						<form:input path="serviceRequest.primaryContact.firstName" /> 
						<form:input path="serviceRequest.primaryContact.lastName" /> 
						<form:input path="serviceRequest.primaryContact.workPhone" /> 
						<form:input path="serviceRequest.primaryContact.emailAddress" />
						<!-- This hidden field is for passing alternate phone -->
						<form:input path="serviceRequest.primaryContact.alternatePhone" />
					</span>
	                
	              </div>
				   <c:if test="${mapsRequestForm.serviceRequest.secondaryContact.firstName != ''}">
				  <div class="columnInner rounded infoBox shadow" id="addiContact">
	                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
	                				
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel2">${mapsRequestForm.serviceRequest.secondaryContact.firstName }  ${mapsRequestForm.serviceRequest.secondaryContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel2">${mapsRequestForm.serviceRequest.secondaryContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel2">${mapsRequestForm.serviceRequest.secondaryContact.emailAddress }</span>
	                  </li>
	                </ul>
	                <span style="display: none">
						<form:input path="serviceRequest.secondaryContact.contactId" /> 
						<form:input path="serviceRequest.secondaryContact.firstName" /> 
						<form:input path="serviceRequest.secondaryContact.lastName" /> 
						<form:input path="serviceRequest.secondaryContact.workPhone" /> 
						<form:input path="serviceRequest.secondaryContact.emailAddress" />
						<!-- This hidden field is for passing alternate phone -->
						<form:input path="serviceRequest.secondaryContact.alternatePhone" />
					</span>
	              </div>
				 </c:if> 
	            </div>
	            <div class="columnsTwo">
	              <div class="infoBox columnInner rounded shadow">
	                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
	                    <span id="custReferenceId">${mapsRequestForm.serviceRequest.customerReferenceId }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.costCentre"/></label>
	                    <span id="costCenter">${mapsRequestForm.serviceRequest.costCenter }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.description"/></label>
	                    <span id="specialInstruction">${mapsRequestForm.serviceRequest.addtnlDescription }</span>
					  </li>
					  <div class="lineClear"/>
					  <li><label for="date1"><spring:message code="requestInfo.label.dateOfChange"/></label>
						
						 <span id="">
						 <util:dateFormat value="${mapsRequestForm.serviceRequest.requestedEffectiveDate}">
						 </util:dateFormat>
						 </span>
						
					  </li>
	                </ul>
	                <span style="display: none">
	                	<form:input path="serviceRequest.customerReferenceId" /> 
						<form:input path="serviceRequest.costCenter" /> 
						<form:input path="serviceRequest.addtnlDescription" /> 
						
						<form:input path="serviceRequest.requestedEffectiveDate" />
						<form:input path="notesOrComments" />
						<form:input path="prevSRNumber" />
						<form:input path="listOfFileTypes" id="listOfFileTypes"/>
						<form:input path="attMaxCount" id="attMaxCount"/>
						<form:input path="chlTempMaxCount" id="chlTempMaxCount"/> 
						    
						<form:input path="requestedFrom" id="requestedFrom"/> 
					</span>
					
				  </div>
	            </div>
	          </div>
			    <hr class="separator" />
			 
			  <div class="portletBlock infoBox">			
					
					<div class="columnsTwo">
					<div class="columnInner">
					<ul class="form wordBreak">
	                
	                  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notesMapReview"/></label>
	                    <span id="rqstDesc"  class="notesOverflow wFull">${mapsRequestForm.notesOrComments }<br>${mapsRequestForm.notesForNewBuilding }</br></span>
					  </li>
					  <li>
                           <label for="moveAsset"><spring:message code="requestInfo.label.moveAsset"/></label>
                           <span id="moveAsset">
                           <c:choose><c:when test="${mapsRequestForm.moveAsset==true}"><spring:message code="customerReportsAdmin.label.yes"/></c:when>
                           <c:otherwise><spring:message code="customerReportsAdmin.label.no"/></c:otherwise></c:choose></span>
                           <script>
                         
                           </script>
                                         </li>
                                         <c:if test="${mapsRequestForm.moveAsset==true}">
                                         <li>
                           <label for="moveContactSelect"><spring:message code="requestInfo.label.moveContactSelect"/></label>
                           <span id="moveContactSelect">
                           <c:choose><c:when test="${mapsRequestForm.moveContactSelect==true}"><p><spring:message code="customerReportsAdmin.label.yes"/></p></c:when>
                           <c:otherwise><p><spring:message code="customerReportsAdmin.label.no"/></p></c:otherwise></c:choose></span></li></c:if>
                           
                           	  <span style="display: none">
						<form:input  path="moveAsset" /> 
						<form:input path="moveContactSelect" /> 
				</span>
	                </ul>
	               
	                </div>
	                <c:if test="${mapsRequestForm.serviceRequest.mapsRequestContact.firstName != ''}">
	                <div class="infoBox columnInner rounded shadow" id="mapsRequestContact">
	               <h4><spring:message code="requestInfo.heading.ContactForMapsRequest"/></h4>				
	                
             
             				
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel3">${mapsRequestForm.serviceRequest.mapsRequestContact.firstName }  ${mapsRequestForm.serviceRequest.mapsRequestContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="workPhoneLabel3">${mapsRequestForm.serviceRequest.mapsRequestContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="emailAddressLabel3">${mapsRequestForm.serviceRequest.mapsRequestContact.emailAddress }</span>
	                  </li>
	                </ul>
               </div>
               
				</c:if>
                <!-- Hidden fields to bind the secondary contact data with form -->
                <span style="display: none">
						<form:input  path="serviceRequest.mapsRequestContact.contactId" /> 
						<form:input  path="serviceRequest.mapsRequestContact.firstName" /> 
						<form:input  path="serviceRequest.mapsRequestContact.lastName" /> 
						<form:input  path="serviceRequest.mapsRequestContact.workPhone" /> 
						<form:input  path="serviceRequest.mapsRequestContact.alternatePhone" />
						<form:input  path="serviceRequest.mapsRequestContact.emailAddress" />
						
				</span>
             
				</div>
					   <div class="columnsTwo">
					   <div class="columnInner">
						<div class="infoBox columnInner rounded shadow">
							<h4>
								<spring:message code="requestInfo.heading.mapchangerequest"/>
							</h4>
							
							
							<ul class="roDisplay">
				<%--	<c:choose>
				<c:when test="${fleetManageDropFlag ne 'true'}">
	                	<li>
						<div>${manageAssetForm.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetForm.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetForm.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetForm.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetForm.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      <c:when test="${fleetManageDropFlag eq 'true'}"> --%>
                      <c:choose>
                     <c:when test="${mapsRequestForm.serviceRequest.installAddress.lbsAddressFlag eq null }">
                      <li>
						<div>${mapsRequestForm.serviceRequest.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${mapsRequestForm.serviceRequest.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation2}</span>
                      </li>
					
					 <%--  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation3}</span>
                      </li> --%>
                      </c:when>
                      <c:when test="${mapsRequestForm.serviceRequest.installAddress.lbsAddressFlag eq 'true' }">
                      
                      <li>
						<div>${mapsRequestForm.serviceRequest.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${mapsRequestForm.serviceRequest.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation2}</span>
                      </li>
                      
		
					  <%-- <c:if test="${mapsRequestForm.serviceRequest.installAddress.levelOfDetails eq 'Street Level' or mapsRequestForm.serviceRequest.installAddress.levelOfDetails eq ''}">
						  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
	                    	<span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation3}</span>
	                      </li>
                  	  </c:if> --%>
                     
                
                       <c:forEach var="isLbsAddress" items="${isLbsAddress}">
                                  <c:if test="${isLbsAddress.key=='Y'}">
                                  <li><label><spring:message code="fleetmanagement.headers.LBSAddress"/> : </label>
                                  <span>${isLbsAddress.value}</span>
                                  </li>
                                  </c:if>
                      </c:forEach>
                      
                       <c:forEach var="lbsAddressLOD" items="${lbsAddressLOD}">
                                  <c:if test="${lbsAddressLOD.key==mapsRequestForm.serviceRequest.installAddress.levelOfDetails}">
                                  <li><label><spring:message code="lbs.label.addresslod"/> : </label> 
                                  <span>${lbsAddressLOD.value}</span>
                                  </li>
                                  </c:if>
                      </c:forEach>
                      <c:forEach var="lbsFloorLOD" items="${lbsFloorLOD}">
                                  <c:if test="${lbsFloorLOD.key==mapsRequestForm.serviceRequest.installAddress.floorLevelOfDetails}">
                                  <li><label><spring:message code="lbs.label.floorlod"/> : </label> 
                                  <span>${lbsFloorLOD.value}</span>
                                  </li>
                                  </c:if>
                      </c:forEach>
                      
                      
                      <c:if test="${mapsRequestForm.serviceRequest.installAddress.floorLevelOfDetails=='Grid Level'}">
                                  <li><label id="addressXYLbl"><spring:message code="fleetmanagement.headers.grid"/> : </label> 
                                 	<label id="addressCoords"></label>
                                  </li>
                      </c:if>
                      
                      </c:when>
                      <c:when test="${mapsRequestForm.serviceRequest.installAddress.lbsAddressFlag ne 'true' }">
                      <li>
						<div>${mapsRequestForm.serviceRequest.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${mapsRequestForm.serviceRequest.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation3}</span>
                      </li>
                     
                      
                       <c:forEach var="isLbsAddress" items="${isLbsAddress}">
                                  <c:if test="${isLbsAddress.key=='N'}">
                                  <li><label><spring:message code="fleetmanagement.headers.LBSAddress"/> : </label> 
                                  <span>${isLbsAddress.value}</span>
                                  </li>
                                  </c:if>
                      </c:forEach>
                      </c:when>
                      </c:choose>
                      <%--</c:when>
                      </c:choose> --%>
                      
					 
					</ul>
							
						</div><!-- columnInner rounded infoBox -->
					</div><!-- columnsTwo -->
					<!-- Hidden fields to bind the address data with form -->
					<span style="display: none">
						<form:input  path="serviceRequest.installAddress.addressId" />
						<form:input  path="serviceRequest.installAddress.storeFrontName" />
						<form:input  path="serviceRequest.installAddress.addressLine1" /> 
						<form:input  path="serviceRequest.installAddress.addressLine2" />

						<form:input  path="serviceRequest.installAddress.city" />
						<form:input  path="serviceRequest.installAddress.state" />
						<form:input  path="serviceRequest.installAddress.province" />
						<form:input  path="serviceRequest.installAddress.postalCode" />
						<form:input  path="serviceRequest.installAddress.country" />
						<form:input  path="serviceRequest.installAddress.physicalLocation1" />
						<form:input  path="serviceRequest.installAddress.physicalLocation2" />
						<form:input  path="serviceRequest.installAddress.physicalLocation3" />
						<form:input  path="serviceRequest.installAddress.zoneName" />
								
						
						<form:input	id="campus1" path="serviceRequest.installAddress.campusName"   />
						
						<form:input	id="physicalLocation1h" path="serviceRequest.installAddress.buildingId"  />
						<form:input	id="physicalLocation2h" path="serviceRequest.installAddress.floorId"  />
						
						<form:input	id="zoneh" path="serviceRequest.installAddress.zoneId"   />
						<form:input	id="campush" path="serviceRequest.installAddress.campusId"   />
						<!-- Below Fields for Cleansing address Don't change the
						input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						 -->
						<form:input  path="serviceRequest.installAddress.county" />
					    <form:input  path="serviceRequest.installAddress.officeNumber" />
					    <form:input  path="serviceRequest.installAddress.stateCode" />
					    <form:input  path="serviceRequest.installAddress.stateFullName" />
					    <form:input  path="serviceRequest.installAddress.district" />
					    <form:input  path="serviceRequest.installAddress.region" />
					    <form:input  path="serviceRequest.installAddress.latitude" />
					    <form:input  path="serviceRequest.installAddress.longitude" />
					    <form:input  path="serviceRequest.installAddress.countryISOCode" />
					    <form:input  path="serviceRequest.installAddress.savedErrorMessage" />
					    <form:input  path="serviceRequest.installAddress.isAddressCleansed" />
					    <form:input	id="installLBSAddressFlag" path="serviceRequest.installAddress.lbsAddressFlag" />
					    
					    <form:input	id="isRequestForBuilding" path="serviceRequest.installAddress.isRequestForBuilding" />
						<form:input	id="isRequestForFloor" path="serviceRequest.installAddress.isRequestForFloor" />
						
						<form:input	id="notesForNewBuilding" path="notesForNewBuilding" />
						
						<form:input	id="lodAddressInput" path="serviceRequest.installAddress.levelOfDetails" />
						<form:input	id="lodFloorInput" path="serviceRequest.installAddress.floorLevelOfDetails" />
						
						
						<form:input
						id="addressCoordinatesXPreDebriefRFV" path="serviceRequest.installAddress.coordinatesXPreDebriefRFV" />
						<form:input
						id="addressCoordinatesYPreDebriefRFV" path="serviceRequest.installAddress.coordinatesYPreDebriefRFV" />
						
					
						
						
						<!-- Ends Cleansing address -->
						
					</span>
		            
					<!-- columnsTwo -->
				</div>
			  </div>
			  
	          </form:form>
	          
	          <c:if test="${fileMap != null}">
	        
			
				<p class="info"><spring:message code="requestInfo.heading.attachments"/></p>
				
			 	<div class="columnInner">
			  		
					  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
						
						<table id="fileListTable">
						</table>

						<div class="lineClear"></div>							
					  </div>
					  
				  </div>
				</c:if>
				
			  
			  <div class="buttonContainer clearBoth">
	            <button type="button" class="button_cancel" onclick="backToEdit();"><spring:message code="button.back"/></button>
	            <c:if test="${fleet != true }">
				<button type="button" class="button_cancel" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
				</c:if>
				<c:if test="${fleet == true }">
				<button type="button" class="button_cancel" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
				</c:if>
	            <button type="button" class="button" onclick="submitRequest();"><spring:message code="button.submit"/></button>
	          </div>
	          
	          
        </div>
        <!-- MAIN CONTENT END -->	
		</div>
	
</div>
</body>