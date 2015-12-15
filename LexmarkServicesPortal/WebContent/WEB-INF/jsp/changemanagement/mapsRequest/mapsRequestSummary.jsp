<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","fleet-management"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ page import="com.lexmark.services.form.FileUploadForm"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<script type="text/javascript">
jQuery(document).ready(function() {
	
	c=1;
	////var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	//alert(fileCount);
	//window.parent.document.getElementById("fileCount").value = fileCount;
	
	<c:if test="${fileMap != null}">
			jQuery('#fileListTable').empty();
			responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		    //alert("responseText" + responseText);
	 		responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileMap}" var="entry">
	 		if(c%2==0){
 				responseText = responseText + '<tr class="altRow">';
 			}
 			else{
 				responseText = responseText + '<tr>';
 			}
 			c++;
	 			responseText = responseText + '<td>'+ '<c:out value="${entry.value.displayFileName}" />' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			//responseText = responseText + '<td class="width5"><img id="img_help1"  src="<html:imagesPath/>tabbarImgs/default/close.png" style="cursor:help;" title="Delete" alt="Remove" onclick=\'removeFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '</tr>';
	 			//alert("file name " + '<c:out value="${entry.key}" />');
				//alert("file size " + '<c:out value="${entry.value.fileSize}" />');
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable').append(responseText);
	 		//alert("end :::"+ document.getElementById("test").innerHTML);

	</c:if>

	<c:if test="${fileMap == null}">
		jQuery('#fileListTable').empty();
	</c:if>
	
	textScroll(document.getElementById('specialInstruction'));
	textScroll(document.getElementById('costCenter'));

	
	
});


</script>

<div id="content-wrapper">
    <div class="journal-content-article" id="chlOthersHeader">
      <h1><span id="changeRequest"><spring:message code="requestInfo.cm.heading.changeRequest"/></span></h1>
      
      <h2 class="step"><spring:message code="requestInfo.cm.heading.mapsRequest"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div id="accNameAgreeName"><h3><strong>
    <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      </strong></h3></div>
      <!-- END -->
      
<div class="main-wrap">
	<div class="content">
				
		
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.heading.requestConfirmation"/></h3>
          <div class="utilities printPage floatR">
            <ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="requestInfo.link.emailThisPage"/>" title="<spring:message code="requestInfo.link.emailThisPage"/>" ></a></li>
              <li><a href="javascript:print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="requestInfo.link.printThisPage"/>" title="<spring:message code="requestInfo.link.printThisPage"/>" height="27" width="27"></a></li>
            </ul>
          </div>
          <div id="emailPrint">
          	<div class="columnInner" id="headerMesg1">          
          <c:if test="${error ne null && error != ''}">
		  <p class="info banner err"><span>${error}</span></p>
		  </c:if>
		  <c:if test="${mapsRequestForm.serviceRequest.serviceRequestNumber != null && mapsRequestForm.serviceRequest.serviceRequestNumber != ''}">
		  <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/>
		  <span id="reqNumber">${mapsRequestForm.serviceRequest.serviceRequestNumber}</span>	
		  <spring:message code="requestInfo.message.confirmMessage2"/></p>
		  </c:if>
		 
			   </div>   
			         	  
		  <form:form id="mapsRequestFormId" commandName="mapsRequestForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  
	          <div class="portletBlock">
	            <div class="columnsTwo">
	              <div class="infoBox columnInner rounded shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>				
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="firstNameLabel"> ${mapsRequestForm.serviceRequest.primaryContact.firstName }   ${mapsRequestForm.serviceRequest.primaryContact.lastName } </span>
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
					</span>
	                
	              </div>
				  <c:if test="${mapsRequestForm.serviceRequest.secondaryContact != null}">
				  <div class="infoBox columnInner rounded shadow" id="addiContact">
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
						 <%-- <span id="">${mapsRequestForm.serviceRequest.requestedEffectiveDate }</span> --%>
						<span id="reqDate"><%-- <fmt:formatDate value="${mapsRequestForm.serviceRequest.requestedEffectiveDate}" pattern="MM/dd/yyyy"/> --%>
						<util:dateFormat value="${mapsRequestForm.serviceRequest.requestedEffectiveDate}">
						</util:dateFormat>
						 </span>
					  </li>
	                </ul>
				  </div>
	            </div>
	          </div>
			  
			 <hr class="separator" />
			<div class="portletBlock infoBox">			
					
					<div class="columnsTwo">
					  <div class="columnInner" id="notesOrCommentBlock">
					<ul class="form wordBreak">
					
					
	                 <%-- <li>
	                    <label><spring:message code="requestInfo.label.area"/></label>
	                    <span id="cmArea">${mapsRequestForm.cmAreaValue }</span>
	                   
					  </li>
					  <c:if test="${mapsRequestForm.cmSubAreaValue != ''}">
					  <li>
	                    <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/></label>
	                    <span id="cmSubArea">${mapsRequestForm.cmSubAreaValue }</span>
	                    						
					  </li>
					  </c:if> --%>
	                  <li>
	                    <label for="rqstDesc"><spring:message code="requestInfo.label.notesMapReview"/></label>
	                    <span id="rqstDesc">${mapsRequestForm.notesOrComments }</span>
					  </li>
					    <li>
                           <label for="moveAsset"><spring:message code="requestInfo.label.moveAsset"/></label>
                           <span id="moveAsset">
                           <c:choose><c:when test="${mapsRequestForm.moveAsset==true}"><p>Yes</p></c:when>
                           <c:otherwise><p>No</p></c:otherwise></c:choose></span>
                           <script>
                    
                           </script>
                                         </li>
                                         <c:if test="${mapsRequestForm.moveAsset==true}">
                                         <li>
                           <label for="moveContactSelect"><spring:message code="requestInfo.label.moveContactSelect"/></label>
                           <span id="moveContactSelect">
                           <c:choose><c:when test="${mapsRequestForm.moveContactSelect==true}"><p>Yes</p></c:when>
                           <c:otherwise><p>No</p></c:otherwise></c:choose></span></li></c:if>
					  <span style="display: none">
						<form:input  path="moveAsset" /> 
						<form:input path="moveContactSelect" /> 
				</span>
	                </ul>
	                </div>
	                
	                <c:if test="${mapsRequestForm.serviceRequest.mapsRequestContact.firstName !=null}">
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
                </c:if>
				</div>
				<div class="columnsTwo">
					   <div class="columnInner">
						<div class="infoBox columnInner rounded shadow" id="addressDiv">
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
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation3}</span>
                      </li>
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
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${mapsRequestForm.serviceRequest.installAddress.physicalLocation3}</span>
                      </li>
                      
					 <li><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span>${mapsRequestForm.serviceRequest.installAddress.zoneName}</span>
                      </li>
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
                      </c:when>
                      </c:choose>
                      <%--</c:when>
                      </c:choose> --%>
                      
					 
					</ul>
						</div><!-- columnInner rounded infoBox -->
					</div><!-- columnsTwo -->
					
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
			  
	          </div>
	          
        </div>
        
        <!-- MAIN CONTENT END -->	
		</div>
		 
	</div>

		 <div class="filterLinks floatR">
	          <ul>
	            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" height="23" width="27"/><spring:message code="requestInfo.link.emailThisPage"/></a></li>
	            <li><a href="javascript:print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" height="27" width="27"/><spring:message code="requestInfo.link.printThisPage"/></a></li>
	          </ul>
          </div>
<script type="text/javascript"><!--
function print() {
	
	
		//callOmnitureAction('Others', 'Others Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='printMapsRequest' />" +
			"</portlet:renderURL>";
	
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};
	
function email() {
	
	
		//callOmnitureAction('Others', 'Others Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='emailMapsRequest' />" +
			"</portlet:renderURL>";
		
	
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'historyList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};

</script>