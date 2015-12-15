<%-- Added for CI BRD13-10-02--STARTS --%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%-- Added for CI BRD13-10-02--ENDS --%>
<%@page import="com.lexmark.domain.FileObject"%>
<%@page import="java.util.Map"%>
<%@page import="com.lexmark.services.util.ChangeMgmtConstant"%>
	
	<style type="text/css">
			<%@ include file="/WEB-INF/css/mps_ie7.css" %>
	</style>  
<%--CI BRD 13-10-08 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
	<%@ include file="/WEB-INF/jsp/common/validationMPS.jsp"%>
    <%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %> 
<%--include file changed for CI BRD13-10-08 --%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
   
<%-- Below jsp added for CI BRD 13-10-02--%>
<jsp:include page="/WEB-INF/jsp/common/reqDetailsPopUp.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<%-- Added for CI BRD13-10-02 --%>
<script type="text/javascript" src="<html:rootPath/>js/expand.js"></script>

<script type="text/javascript"> 
    //Added for CI Defect #8217--STARTS
	var offsetMinute = new Date().getTimezoneOffset();
	var timezoneOffset = (offsetMinute/60);//It will make it positive value
	//Added for CI Defect #8217--ENDS
	function stopRKey(evt) { 
	  var evt = (evt) ? evt : ((event) ? event : null); 
	  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
	  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	
	document.onkeypress = stopRKey; 


 	jQuery(function() {
 	    jQuery("h4.expand").toggler();
 	});
</script>

<style type="text/css">
.service_add_link{/*color:#D9EEF7 !important;*/font-size:12px;float:right;padding:0 10px}
.ie7 .service_add_linkalign{margin-top:-30px !important;}
ul{margin-left:0px !important}
div.portlet-header h3{font-size: 14px !important;}
#typeIfServiceNeededId input{border:none !important;border:0 !important;margin:0px !important;padding:0px !important;}
#typeIfServiceNeededId input:focus{border:none !important;border:0 !important;margin:0px !important;padding:0px !important;}
.noBorder{
		border-color: white;
		border-width: 0px;
		border-style: solid;
		padding-left: 2px;
		padding-bottom: 2px;
		width: 500px;
	}
	
	.errorBorderColor{
		border-color: red;
		border-width: 2px;
		border-style: solid;
		padding-left: 2px;
		padding-bottom: 2px;
		width: 500px;
	}
	label.error {
		width: 250px;
		display: block;
		float: left;
		color: red;
		padding-left: 5px;
	}
	
	button.starredButton1 { 
		background: transparent url(<html:imagesPath/>favorite.png) no-repeat
			left center;
	}
	
	div.loadingNotification {
		width: 300px;
		font-size: 20pt;
		text-align: center;
		position: relative;
		top: 20px;
		left: 100px;
		height: 50px;
		background-color: silver;
		opacity: 0.3;
		-moz-opacity: 0.3;
		filter: alpha(opacity =  30);
	}
	
	img.action{
	 cursor:pointer
	}
	
   .contentHeadId{
       display: table;
       width: 100%;
   }
   .contentRowId{
        display: table-row;
   }
   
  .contentDataId_Right, .contentDataId_Left{
        display: table-cell;
        width: 47%;
        vertical-align: top;
        float:left;margin:5px 10px;
   }
   
   tr.tableHeader{
	   background-color: gray;
	   color: black;
	   font: bold;
   }
   tr.tableContentColor{
	   	color: blue;
	   	border: 1px;
   }
   th.width60, td.width60{
   		width: 300px;
   }
   th.width35, td.width35{
   		width: 145px;
   }
   th.width20, td.width20{
   		width: 50px;
   }
div.portlet-header {
	background: none repeat scroll 0 0 #e6e6f0 !important;
	height: 30px;
	margin: 0;
	padding: 0;}


div.portlet-header h3 {
	/*background: url(../images/custom/portlet-topright.png) no-repeat top right;*/
	background-image:none;
	height: 30px;
	width: 100%;
	margin: 0;
	padding: 0;
	font-size: 16px;
	line-height: 30px;
	text-indent: 15px;
	color: #1d1d25;
	font-weight: normal;
	}	
	
* html div.portlet-header h3 {	
	line-height: 30px;
}   
div.portlet-header h3{margin:0}
div.portlet-footer, div.portlet-footer-inner{background-image:none}
.ie7 ul.form li label{margin-left: -44% !important;}
/* UI Channge under CI BRD 13-10-08 */
#list1{padding-left:0px!important;}
#list2{padding-left:0px!important;}
#list3{padding-left:0px!important;}
#list4{padding-left:0px!important;}
#list5{padding-left:0px!important;}
#list6{padding-left:0px!important;}
#list7{padding-left:0px!important;}
#liofficeId{padding-left:0px!important;}
</style>

<!-- Below URL opens up the address list grid in popup--------- CI6 -->
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<%--Added for CI--%>
<%-- Below div opens up as popup with request detail--%>
	<div id="dialogChangeDetails" style="display: none;"></div>
	<div id="dialogSupplyDetails" style="display: none;"></div>

<!-- Below div opens up as popup, with contact and address contents respectively---- CI6 -->
<div id="dialogAddress" style="display: none;">&nbsp;</div> 
<div id="dialog_contact" style="display: none;">&nbsp;</div>

 <div class="journal-content-article">
      <h1><spring:message code='requestInfo.requestHistory.heading.serviceRequests'/></h1>
      <h2 class="step" id="historyTypeName"></h2>
 </div>
 
<div class="main-wrap">
	<div class="content">
		<portlet:actionURL var="actionUrl">
			<portlet:param name="action" value="submitServiceRequest" />
		</portlet:actionURL>
		<portlet:actionURL var="addAttachmentsCreate">
	         <portlet:param name="action" value="addAttachmentsCreateService"/>
        </portlet:actionURL>
        <portlet:actionURL var="removeAttachmentURL">
	         <portlet:param name="action" value="removeAttachment"/>
        </portlet:actionURL> 
		
		<div class="error" id="errorDiv" style="display: none;"></div>
 		<!-- JS Validation Errors displayed in the below html:statusBanner -->
           <div id="jsValidationErrors" class="error" style="display: none;"></div>
        <!-- Validation Errors displayed in the below div -->
        <spring:hasBindErrors name="fileUploadForm">
		<div class="error"  id="errors">
		<c:forEach var="error" items="${errors.allErrors}">
		<li><strong><spring:message code="${error.code}"/></strong></li>
		</c:forEach>
		</div>	
		</spring:hasBindErrors> 
		<spring:hasBindErrors name="serviceRequestConfirmationForm">
				<div class="error" id="serviceRequestConfirmationFormErrors">
					<c:forEach var="serviceRequestConfirmationFormError" items="${errors.allErrors}">
						<li><strong><spring:message code="${serviceRequestConfirmationFormError.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
					
		<div id="validationErrors" class="error" style="display: none;"></div><!--Div used for file upload validation errors  -->
		<!-- Exceptions displayed in the below div -->
          	 
       			<div class="error" id="dispalyExceptions" style="display: none;">
	       	       <c:if test="${exceptionList != null}">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>Exception - ${entry}</strong></li>		       		
			     	</c:forEach>
      		        </c:if>
      			</div>
          	

          	<div id="exceptionsDiv" class="error" style="display: none;"></div>	<!--Div used for file upload exception  -->
		<form:form id="serviceRequestConfirmationForm" commandName="serviceRequestConfirmationForm" modelAttribute="serviceRequestConfirmationForm" method="post" action="${actionUrl}">
			
			<form:hidden path="submitToken" />
			<form:hidden path="asset.notMyPrinter"/>
			<form:hidden path="draftFlag" id="isDraftFlag"/>	<!-- added for breakfix MPS save as dreaft flag -->
			<div style="width:100%">
			
<!-- *****************Device Information******************* -->
			<div class="contentHeadId">
				<div class="contentRowId">
				<div class="contentDataId_Left" style="padding-top: 5px;">
			<div class="portlet-wrap rounded">
			<div class="portlet-header">
			<h3><spring:message code="serviceRequest.label.DeviceInformation" /></h3>
				
			</div>
			<div class="portlet-wrap-inner">
			<table width="100%">
				<tr>
					<td valign="top" align="center"><b>${serviceRequestConfirmationForm.asset.descriptionLocalLang}</b><br />
					<img src="${serviceRequestConfirmationForm.asset.productImageURL}"
						width="100px" /><br />
					</td>
					<td width="125px"></td>
					<td valign="center" align="left">
						<spring:message code="deviceFinder.listHeader.serialNumber" />: <b>${serviceRequestConfirmationForm.asset.serialNumber}</b><br />
						<spring:message code="deviceFinder.listHeader.ipAddress" />: 
						<%--Start: Added for Defect 3924-Jan Release--%>
						<b><a href="javascript:gotoControlPanel('${serviceRequestConfirmationForm.asset.controlPanelURL}')">${serviceRequestConfirmationForm.asset.ipAddress}</a>
						</b><br />
						<%--End: Added for Defect 3924-Jan Release--%>
						<spring:message code="serviceRequest.label.MachineType" />: <b>${serviceRequestConfirmationForm.asset.modelNumber}</b><br />
						<spring:message code="serviceRequest.label.ProductPNTLI" />: <b>${serviceRequestConfirmationForm.asset.productTLI}</b><br />
						<a href="#" onClick="selectAnotherPrinter();">
							<spring:message code="serviceRequest.label.SelectAnotherPrinter" /></a><br />
					</td>
				</tr>
			</table>
			</div>
			<div class="portlet-footer">
			<div class="portlet-footer-inner"></div>
			</div>
			</div></div>
			<!-- *****************Device Information End****************** -->
			
			<div class="contentDataId_Right" style="padding-top: 5px;">
					<!-- *********************************Service Address*********************************** -->
					 <div class="portlet-wrap rounded"> 
					 <div class="portlet-header">               	
			         <h3><spring:message code="serviceRequest.label.ServiceAddress" /> <a href="${addressListPopUpUrl}" class="service_add_link service_add_linkalign" id="diffAddressLink" onclick="return popupAddress(id);">
											<spring:message code="serviceRequest.label.ChooseADifferentAddress" />
										</a></h3>
			           </div> 
			         <label for="serviceRequest.serviceAddress.addressId" class="error" style="display: none;"><spring:message code='serviceRequest.errorMsg.serviceAddress' /></label>
			         <div id="serviceAddressDiv">
			         <ul class="roDisplay">
	              <!-- Changes done for CI BRD 13-10-08 STARTS-->
									<li><div id="pickupAddressNameLbl"></div>  
									<div id="pickupStoreFrontNameLbl"></div> 
									<div id="pickupAddressLine1Lbl"></div>
									<div id="pickupAddressofficeNumberLbl"></div>
									<div id="pickupAddressLine2Lbl"></div>
									<div id="city_state_zip_popup_span">
									<div style="display:inline;" id="pickupAddressCityLbl"></div>
									<div  style="display:inline;" id="pickupAddresscountyLbl"></div>
									<div style="display:inline;" id="pickupAddressStateLbl"></div>
									<div style="display:inline;" id="pickupAddressProvinceLbl"></div>
									<div style="display:inline;" id="pickupAddressRegionLB"></div>
									</div>
									<div id="pickupAddressPostCodeLbl"></div>
									<div id="pickupAddressCountryLbl"></div></li>
				<!-- Changes done for CI BRD 13-10-08 ENDS-->
	              </ul>
	              <ul class="form division">
	              <!-- Path for Building,floor and office changed for CI BRD 13-10-08 ENDS-->
	                <li>
	                  <label for="bldg"><spring:message code="requestInfo.addressInfo.label.building"/> </label>
	                  <span><form:input id="building" path="serviceRequest.serviceAddress.physicalLocation1" class="w80" /></span>
	                </li>
	                <li>
	                  <label for="flr"><spring:message code="requestInfo.addressInfo.label.floor"/></label>
	                  <span><form:input id="floor" path="serviceRequest.serviceAddress.physicalLocation2" class="w80" /></span>
	                </li>
	                <li>
	                  <label for="ofc"><spring:message code="requestInfo.addressInfo.label.office"/></label>
	                  <span><form:input id="office" path="serviceRequest.serviceAddress.physicalLocation3" class="w80" /></span>
	                </li>
	              </ul>
<!-- 					 <table style="margin: 10px;"> -->
<!-- 					 <tr><td><b><label id="addressNameLabel"></label></b></td></tr> -->
<!-- 					 <tr><td><label id="addressLine1Label"></label></td></tr> -->
<!-- 					 <tr><td><label id="addressLine2Label"></label></td></tr> -->
<!-- 					 <tr> -->
<!-- 					 <td> -->
<!-- 					 <label id="cityLabel"></label>,  -->
<!-- 					 <label id="stateLabel"></label>  -->
<!-- 					 <label id="provinceLabel"></label>  -->
<!-- 					 <label id="stateProvinceLabel"></label> -->
<!-- 					 <label id="commaLevel">,</label>  -->
<!-- 					 <label id="countryLabel"></label>,  -->
<!-- 					 <label id="postalCodeLabel"></label> -->
<!-- 					 <br></br> -->
<!-- 					 </td> -->
<!-- 					 </tr> -->
<!-- 					 <tr> -->
<!-- 					 <td style="display: none"> -->
<%-- 									    <form:input path="serviceRequest.serviceAddress.addressId" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.addressName" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.addressLine1" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.addressLine2" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.city" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.state" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.province" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.stateProvince" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.country" />  --%>
<%-- 										<form:input path="serviceRequest.serviceAddress.postalCode" />  --%>
<!-- 									</td> -->
<!-- 								</tr> -->
							
<!-- 							 </table> -->
						 </div>
		            </div>
				    </div> 
			</div>
			</div>
			 <!-- Hidden fields to bind with form -->
		  <span style="display: none">
						         	<form:input id="pickupAddressId" path="serviceRequest.serviceAddress.addressId" />
						         	<form:input id="pickupAddressName" path="serviceRequest.serviceAddress.addressName" />
						         	<form:input id="pickupStoreFrontName" path="serviceRequest.serviceAddress.storeFrontName" />
							     	<form:input id="pickupAddressLine1" path="serviceRequest.serviceAddress.addressLine1" /> 
							        <form:input id="pickupAddressLine2" path="serviceRequest.serviceAddress.addressLine2" />
							        <form:input id="pickupAddressCity" path="serviceRequest.serviceAddress.city" />
							        <form:input id="pickupAddressState" path="serviceRequest.serviceAddress.state" />
							        <form:input id="pickupAddressProvince" path="serviceRequest.serviceAddress.province" />
							        <form:input id="pickupAddressPostCode" path="serviceRequest.serviceAddress.postalCode" />
							        <form:input id="pickupAddressCountry" path="serviceRequest.serviceAddress.country" />
 							        <%--<form:input path="listOfFileTypes" id="listOfFileTypes"/> --%>
									<!-- Below Fields for Cleansing address Don't change the
									input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
									 -->
									
									<form:input id="pickupAddresscounty" path="serviceRequest.serviceAddress.county" />
							        <form:input id="pickupAddressofficeNumber" path="serviceRequest.serviceAddress.officeNumber" />
							        <form:input id="pickupAddressstateCode" path="serviceRequest.serviceAddress.stateCode" />
							        <form:input id="pickupAddressstateFullName" path="serviceRequest.serviceAddress.stateFullName" />
							        <form:input id="pickupAddressdistrict" path="serviceRequest.serviceAddress.district" />
							        <form:input id="pickupAddressregion" path="serviceRequest.serviceAddress.region" />
							        <form:input id="pickupAddresslatitude" path="serviceRequest.serviceAddress.lbsLatitude" />
							        <form:input id="pickupAddresslongitude" path="serviceRequest.serviceAddress.lbsLongitude" />
							        <form:input id="pickupAddresscountryISOCode" path="serviceRequest.serviceAddress.countryISOCode" />
							        <form:input id="pickupAddresssavedErrorMessage" path="serviceRequest.serviceAddress.savedErrorMessage" />
							        <form:input id="pickupAddressisAddressCleansed" path="serviceRequest.serviceAddress.isAddressCleansed"/>
							        
									<!-- Ends Cleansing address -->
									</span>
			<!-- *****************Device Information End****************** -->
			<div class="contentHeadId">
				<div class="contentRowId">
					
	<!-- ***************************Primary Contact Information******************************* -->
		 		  	<jsp:include page="/WEB-INF/jsp/common/commonContactinfo_SR.jsp" />  
    <!--End of primaryContactData -->
					
					
					
			     </div> <!-- End of content row id -->
			   </div> <!-- End of contentHeadId -->
			
			<%--Request History for Asset (CI) STARTS--%>
		 <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" onclick="show_load();"> <!--show load called for asynshronous-CI7 -->
              <spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></h4>
              <div  class="collapse">
<!--                <div class="grid-controls">  -->
<!--                       Expand-min Start -->
                       <div class="expand-min">
                      
                       <a href="#grid" id='headerMenuButton' style="display: none;"><spring:message code="requestInfo.link.customizeColumns"/></a>  <!--DONT REMOVE THIS CI BRD13-10-02--%>
                       <!--Commented for CI BRD13-10-02--STARTS -->
<!--                         <ul> -->
<%--                           <li class="first"><img src="<html:imagesPath/>wrench.png"  style="cursor:pointer" alt="Customize Grid" title="Customize Grid" /> --%>
<%--                             <a href="#grid" id='headerMenuButton'><spring:message code="requestInfo.link.customizeColumns"/></a></li>  --%>
<%--                           <li><img class="ui_icon_sprite reset-icon" src="<html:imagesPath/>transparent.png" alt="Reset Grid" title="Reset Grid" /> --%>
<%--                           <a href="javascript:doReset();"><spring:message code="requestInfo.link.reset"/></a></li> --%>
<!--                         </ul> -->
						<!--Commented for CI BRD13-10-02--ENDS -->
                       </div>
<!--                       NO CONTENT HERE PLS  -->
<!--                     </div> -->
                <div id="gridContainerDiv_all_request_types"  class="gridbox gridbox_light" style="display: none;"></div>
                
				    		<div id="loadingNotification_request_type" class='gridLoading'>
	        					<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    					</div>
				    		<div class="pagination" id="paginationId">
				    		<span id="pagingArea_request_type"></span><span id="infoArea_req_status"></span> 
				    		</div>
					
              </div>
            </div>
          </div>
        </div>
        <%--Request History for Asset (CI) ENDS--%>
			
<!-- *****************************problemDescription******************************* -->

 			<div class="portlet-wrap rounded" style="margin-top:10px">
			<div class="portlet-header">
			<h3><spring:message code="serviceRequest.label.problemDescription" /></h3>
			</div>
			<div class="portlet-wrap-inner" style="margin-left:10px">
			<table width="500px">
				<tr>
					<td><b>*<spring:message
						code="serviceRequest.label.problemDescription" /></b><br />
					<form:textarea style="width:400px; height:100px" path="serviceRequest.problemDescription" maxlength="2000" id="problemDescription" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></td>
				</tr>
				
				<%-- Added For Printer Error Code for CI 13.10.17 --%>
				<%-- Commented as per CI Defect # 10318--%>
<!-- 				<tr>				 -->
<%-- 					<td><b><spring:message code="serviceRequest.label.printerErrorCode(Optional)" /></b></td> --%>
<!-- 				</tr> -->
<!-- 					<tr> -->
<%-- 					<td> <span> <form:select path="printerErrorCode" id="printerErrorCode" multiple="false"> --%>
<%-- 	                   		 <form:option value="0" >-<spring:message code="requestInfo.option.select"/>-</form:option> --%>
<%-- 	                    	 <c:forEach items="${requestTypeLOVMap}" var="entry"> --%>
<%-- 					       		 <form:option value="${entry.key}">${entry.value}</form:option> --%>
<%-- 					     	 </c:forEach> --%>
<%-- 	                    </form:select> </span></td> --%>
	            
<!-- 				</tr> -->
				
								
				<tr>
					<td valign="center" align="left"><b><spring:message
						code="serviceRequest.label.ReferenceNumber" />:</b> <br />
					<form:input style="width:400px; height:20px; margin-left:0px"
						path="serviceRequest.referenceNumber" maxlength="30"/></td>
				</tr>
				<tr>
					<td><br></br>
					<c:choose>
						<c:when test="${empty serviceRequestConfirmationForm.asset.entitlement.serviceDetails}">
							<br />
 							<b><spring:message 
								code="serviceRequest.description.assetNotEntitled" /></b>
						</c:when>
						<c:otherwise>
							<div><b>*<spring:message code="serviceRequest.label.TypeOfServiceNeeded" /></b></div>
							
							<div align="left" class="noBorder" id="typeIfServiceNeededId">
								<c:forEach items="${serviceRequestConfirmationForm.asset.entitlement.serviceDetails}" var="serviceDetail">
									<c:choose>
										<c:when test="${serviceDetail.siebelValue == 'Option Exchange'}">
										<input type="checkbox" class="selectedServiceDetails" name="selectedServiceDetails" value="${serviceDetail.serviceDetailId}" onclick="showHideDiv(this,'exchangeOfOption'); clearBorder()" /> ${serviceDetail.serviceDetailDescription}<br />
											<div id="exchangeOfOption" style="display: none; margin-left: 55px;">
												<form:textarea	id = "TextExchangeofOption" style="width:400px; height:50px ; color:#D3D3D3" path="serviceRequest.optionExchangeOtherDescription" onblur="javascript:doOnblur(this,'exchangeofOption');" onfocus="javascript:doOnFocus(this,'exchangeofOption');" maxlength="2000"/>
											</div>
										</c:when>	
										<c:otherwise>
											<form:checkbox class="selectedServiceDetails" path="selectedServiceDetails" value="${serviceDetail.serviceDetailId}" onclick="clearBorder()"/> ${serviceDetail.serviceDetailDescription}<br/>
										</c:otherwise>
								    </c:choose>
								</c:forEach> 
								

							<input type="checkbox" class="selectedServiceDetails" name="selectedServiceDetails" value="Other" onclick="callOmnitureAction('Service Request', 'Service Request Detail Toggle Other Service Requests Div');showHideDiv(this,'otherDiv');clearBorder()" /> 
							<spring:message code="serviceRequest.label.Other" /><br/>
							<div id="otherDiv" style="display: none; margin-left: 55px;" >
								<form:textarea id = "TextOther" style="width:400px; height:50px; color:#D3D3D3" path="serviceRequest.otherRequestedService" onblur="javascript:doOnblur(this,'other');" onfocus="javascript:doOnFocus(this,'other');" maxlength="2000" />
							</div>
							<div class="clear"></div>

							<label for="selectedServiceDetails" class="error"
								style="display: none;"><spring:message
								code='serviceRequest.errorMsg.selectedServiceDetails' /></label>
						</div>
						</c:otherwise>
						</c:choose>
					
					</td>
				</tr>		
			</table>
			  
			</div>
			</div>
			       
		<!--  Attachment Start -->
        
      <div class="portletBlock infoBox rounded shadow">
									<div class="columnsOne">
										<div class="columnInner">
											<h4 class="expand"><spring:message code="requestInfo.heading.addNotesAndAttachments"/></h4>
												<div class="collapse">
													<div class="form">
														<span style="float:left;margin-right:15px;font-weight: bold;">
															<label for="notes"><spring:message code="requestInfo.label.attachmentNotes"/>:</label>
														</span>	 
														 <span class="multiLine" style="float:left;margin-right:15px">
                   											<form:textarea id="attachmentDescription" rows="3" path="attachmentDescription" maxlength="2000"/>
                   										 </span>
													</div>
			    </form:form>
			    <p class="inlineTitle"><spring:message code="orderSupplies.notes.attachmentFiles"/></p>
			    <div class="infoBox">
				<form:form id="serviceRequestAttachmentForm" name="serviceRequestAttachmentForm" commandName="fileUploadForm" method="post" enctype="multipart/form-data">
						<script type="text/javascript">
				  	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
				  	window.parent.document.getElementById("fileCount").value = fileCount;
				     function uploadFile() {
			        	 var validFlag = 1;
						 var errorList = "";
			              var inputfileCount=fileCount;
			              var input, file;
			              input = document.getElementById('files');
			              file = input.files[0];
			              
					var filePath = document.getElementById('txtFilePath').value;
					var listOfFileTypes = ' <spring:message code="breakfix.attachment.fileType.list"/>';  
					
					var maxAttCount = ' <spring:message code="breakfix.attachment.maxcount"/>';  

					var inputFileCount = document.getElementById("fileCount").value;
					 
					 var fileExt = filePath.substring(filePath.lastIndexOf(".")+1, filePath.length).toLowerCase();
					 
					jQuery('#jsValidationErrors',window.parent.document).empty();
					document.getElementById('jsValidationErrors').innerHTML = "";
					document.getElementById("jsValidationErrors").style.display = 'none';
										
					  if(file.size>1000000){
								document.getElementById("validationErrors").style.display = 'none';
								document.getElementById("exceptionsDiv").style.display = 'none';
								errorList ="<li><strong><spring:message code='validation.fileUpload.maxSizeExceeds.errorMsg'/></strong></li>";
								jQuery('#jsValidationErrors',window.parent.document).empty();
								document.getElementById("jsValidationErrors").innerHTML = errorList;
								document.getElementById("jsValidationErrors").style.display = 'block';
								window.parent.scrollTo(0,0);
								validFlag = 0;
						  }
					if(filePath.trim() == ""){
							document.getElementById("validationErrors").style.display = 'none';
							document.getElementById("exceptionsDiv").style.display = 'none';
							errorList ="<li><strong><spring:message code='requestInfo.label.validation.attachmentEmpty'/></strong></li>";
							jQuery('#jsValidationErrors',window.parent.document).empty();
							document.getElementById("jsValidationErrors").innerHTML = errorList;
							document.getElementById("jsValidationErrors").style.display = 'block';
							window.parent.scrollTo(0,0);
							validFlag = 0;
					 }
					 else if(inputFileCount >= 2){
							document.getElementById("validationErrors").style.display = 'none';
							document.getElementById("exceptionsDiv").style.display = 'none';
							errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileCountExceeds.errorMsg'/></strong></li>";
							jQuery('#jsValidationErrors',window.parent.document).empty();
							document.getElementById("jsValidationErrors").innerHTML = errorList;
							document.getElementById("jsValidationErrors").style.display = 'block';
							window.parent.scrollTo(0,0);
							validFlag = 0;
					 }
					 else if(listOfFileTypes.indexOf(fileExt) == -1){
							document.getElementById("validationErrors").style.display = 'none';
							document.getElementById("exceptionsDiv").style.display = 'none';
							errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileTypeInvalid.errorMsg'/></strong></li>";
							jQuery('#jsValidationErrors',window.parent.document).empty();
							document.getElementById("jsValidationErrors").innerHTML = errorList;
							document.getElementById("jsValidationErrors").style.display = 'block';
							window.parent.scrollTo(0,0);
							validFlag = 0;
					 }
					 
					 else if(validteDuplicateFile(filePath, inputFileCount)){
							document.getElementById("validationErrors").style.display = 'none';
							document.getElementById("exceptionsDiv").style.display = 'none';
							errorList = errorList + "<li><strong><spring:message code='breakfix.attachment.duplicateFile.message'/></strong></li>";
							jQuery('#jsValidationErrors',window.parent.document).empty();
							document.getElementById("jsValidationErrors").innerHTML = errorList;
							document.getElementById("jsValidationErrors").style.display = 'block';
							window.parent.scrollTo(0,0);
							validFlag = 0;
					 }
				 	if(validFlag==1){
						document.getElementById('serviceRequestAttachmentForm').target = 'upload_target';				
						var link = "${addAttachmentsCreate}";			
						document.getElementById('serviceRequestAttachmentForm').action = link;
						eval(document.serviceRequestAttachmentForm).submit();
					}	
				 }
			     
			     function validteDuplicateFile(fileName, fileCount){
						var returnFlag = false;
						var filesInfo = [];
						for(var i=0; i< fileCount; i++){
							filesInfo[i] = document.getElementById("fileId_"+i).innerHTML;
						}
						
						for(var i=0; i< fileCount; i++){
						if(fileName == filesInfo[i]){
								returnFlag = true;
							}
						}
						
						return 	returnFlag;	
				}
						  	
				  	  </script>			
					  <input type="text" id="txtFilePath" readonly="true" style="width:320px; height:20px; margin-left:0px" />&nbsp;
					  <form:input type="file" size="1" id="files" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" />
					 
					  <button class="button" type="button" id="btnBrowse" style="cursor:pointer;"><spring:message code="changemanagement.common.button.browse"/></button>&nbsp;&nbsp;					
					  <button class="button" type="button" id="btnUpload" style="cursor:pointer;" onclick="uploadFile();"><spring:message code="changemanagement.common.button.attach"/></button>
					  <span style="display: none">
					  <form:input path="fileCount" />
						</span>
		        </form:form>
		        <p class="note">
			        <spring:message code="orderSupplies.notes.attachmentInstruction1"/>&nbsp;<spring:message code="orderSupplies.notes.attachmentInstruction2"/>
		        </p>
		        <div class="wHalf displayGrid columnInnerTable rounded shadow">
				<table id="fileListTable"></table>
				<iframe id="upload_target" name="upload_target" style="width:0px;height:0px;border:0px solid #fff;"></iframe>
				</div><!-- displayGrid rounded shadow wHalf -->
				</div>
			</div>
		</div>
		<!-- Attachment End -->
		</div><!-- portletBlock infoBox rounded shadow -->	
		<form:form id="dummyForm" name="dummyForm" method="post" style="margin-bottom:10px">
			<div align="right" style="margin-right:20px"><!-- Submit & Cancel -->
			<c:if test="${not empty serviceRequestConfirmationForm.asset.entitlement.serviceDetails}">
				<button class="button_cancel" type="button" onClick="onCancelClick()">
		        	<spring:message code="button.cancel" />
				</button>&nbsp;
				<button class="button" type="button" onClick="submitSRForm()">
					<spring:message code="button.submit" />
				</button>
			</c:if>		
			<br/>
			
			</div><!-- Submit & Cancel -->
			</form:form>
			<input type="hidden" id="fileCount" />
			</div>
		<!-- **************************ATTACHMENTS START******************** -->
			

		</div><!-- collapse -->
	    </div><!-- columnInner -->
		</div><!-- columnsOne -->
		</div><!-- portletBlock infoBox rounded shadow --> 
		<!-- **************************ATTACHMENTS END******************** -->
			   
			   
		
		
		<div class="portlet-footer">
		<div class="portlet-footer-inner"></div>
		</div><!--  portlet-footer-->
		</div><!-- Content -->
		</div><!-- main-wrap -->
		<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include> <%--Added for CI BRD13-10-02--%>
		
		<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${serviceRequestConfirmationForm.backToMap}"/>
</form>
 		<script type="text/javascript">
 		
		 var xx = document.getElementById("gridIframeId");
		var linkId;
		var favStatus = {};
		var isPageLoaded = false;
		var isServiceAddressGridLoaded = false;
		var isSecContactGridLoaded = false;
		var isPriContactGridLoaded = false;
		var addressChangeReq=false;
		var dialog;
		//Added for CI BRD13-10-02--START
		//var assetRowId1="${assetDetailPageForm.asset.assetId}";
		var assetRowId1="${serviceRequestConfirmationForm.asset.assetId}";
		var accountId='${serviceRequestConfirmationForm.asset.account.accountId}';
		var linkClicked=1;
		var timezoneOffsetServiceRequest="";
		//Added for CI BRD13-10-02--END
		
		var isError='${exceptn}';
		
			if(isError)
				{
				
				jQuery("#errorDiv").show();
				jQuery("#errorDiv").append('<li><strong><spring:message code="exception.unableToRetrieveDeviceDetail"/></strong></li>');
			}
		
		 //Script for Request History Grid CI (BRD13-10-02) STARTS 
		 	var counter=0;
			//Added for CI BRD13-10-02--START
			function show_load(){
					counter++;
					if(!(counter%2)==0)
						{
					initialiseGrid();
					jQuery("#gridContainerDiv_all_request_types").css('height','25px');
					jQuery("#gridContainerDiv_all_request_types").show();
						}
				}
			//Added for CI BRD13-10-02--END	
			// following variables are declared in dynamicGridInitialize
			// Changes for Defect 7490 MPS 2.1
			pagingArea="pagingArea_request_type";infoArea="infoArea_req_status";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_request_type";backFilterValues="";
			gridCreationId="gridContainerDiv_all_request_types";
			// ENDS Changes for Defect 7490 MPS 2.1
			pageSize=5,pagesIngrp=10;
			JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='suppliesmanagement.details.srhistorygrid'/>";
			JSON_Param["<%=gridConfigurationValues[1]%>"]=",,,,";
			JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left";
			JSON_Param["<%=gridConfigurationValues[3]%>"]="130,160,120,120,100";
			JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro";
			JSON_Param["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str";
			JSON_Param["<%=gridConfigurationValues[6]%>"]="0,1,2,3,4"; //Changed for CI Defect #8217
			JSON_Param["<%=gridConfigurationValues[7]%>"]="0,des";
			JSON_Param["<%=gridConfigurationValues[8]%>"]=""; //Changed for CI Defect #8217
			JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
			JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id='retrieveHistoryList'/>";
			JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
			JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
			JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
			JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";

			/*
			 *Added for MPS phase 2 
			 *
			 */
			
			reloadGrid=function(){
				clearMessage();
				refreshGridSettingOnPage(requestListGrid);
				xmlURLQueryParams.setLoadXMLUrl();
				requestListGrid.clearAndLoad(xmlURL);
				
			};


			initURLParams = function(){
				xmlURLQueryParams={
						urlParameters:["direction","orderBy","timezoneOffset","accountRowId","srHistoryType","assetRowId"],
						setParameters : function(){
												this["direction"]=requestListGrid.a_direction;
												this["orderBy"]=requestListGrid.getSortColByOffset();
												this["timezoneOffset"]=timezoneOffset;
												this["accountRowId"]=accountId;
												this["srHistoryType"]="ALL_REQUESTS";
												this["assetRowId"]=assetRowId1;
												
												},
						setLoadXMLUrl : function(){
														xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
														this.setParameters();
														for(i=0;i<this.urlParameters.length;i++){
															if(this[this.urlParameters[i]]!=null)
																xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
																
														}	
												}
						};
	
		
		
		
				};

				function onSRNmbrClick(serviceRequestNumber, requestType){
					var serviceRqstType=requestType;
					if(serviceRqstType=='Consumables Management')
						serviceRqstType='Consumables_Management';
				    if(serviceRqstType=='Fleet Management')
				    	serviceRqstType='Fleet_Management';
					
					goToDetailPage(serviceRequestNumber,serviceRqstType);
				}

	             //Script for Request History Grid CI (BRD13-10-02) ENDS 
		
				
		 function fetchDefaultContactData()
		{
			var _firstName="${serviceRequestConfirmationForm.asset.assetContact.firstName}";
			var _lastName="${serviceRequestConfirmationForm.asset.assetContact.lastName}";
			var _phoneNo="${serviceRequestConfirmationForm.asset.assetContact.workPhone}";
			var _emailId="${serviceRequestConfirmationForm.asset.assetContact.emailAddress}";
			
			document.getElementById("primaryLastNameLabel").innerHTML= _firstName+" "+_lastName;
			document.getElementById("primaryWorkPhoneLabel").innerHTML= _phoneNo;
			document.getElementById("primaryEmailAddressLabel").innerHTML= _emailId;
			
			
		}  
		 //Changes done for CI Defect #10318
		 function fetchDefaultAddressData()
		  {
			 	var _addressName		= "${serviceRequestConfirmationForm.asset.installAddress.addressName}";
				var _storeName		= "${serviceRequestConfirmationForm.asset.installAddress.storeFrontName}";
				var _addressLine1		= "${serviceRequestConfirmationForm.asset.installAddress.addressLine1}";
				var _addressLine2		= "${serviceRequestConfirmationForm.asset.installAddress.addressLine2}";
				var _officeNo		= "${serviceRequestConfirmationForm.asset.installAddress.officeNumber}";
				var _cityLabel			= "${serviceRequestConfirmationForm.asset.installAddress.city}";
				var _countyLabel			= "${serviceRequestConfirmationForm.asset.installAddress.county}";
				var _stateLabel			= "${serviceRequestConfirmationForm.asset.installAddress.state}";
				var _provinceLabel		= "${serviceRequestConfirmationForm.asset.installAddress.province}";
				var _region		= "${serviceRequestConfirmationForm.asset.installAddress.region}";
				var _countryLabel		= "${serviceRequestConfirmationForm.asset.installAddress.country}";
				var _postalCodeLabel	= "${serviceRequestConfirmationForm.asset.installAddress.postalCode}";
				document.getElementById("pickupStoreFrontNameLbl").innerHTML= _storeName;
				document.getElementById("pickupAddressNameLbl").innerHTML= _addressName;
				document.getElementById("pickupAddressLine1Lbl").innerHTML= _addressLine1;
				document.getElementById("pickupAddressofficeNumberLbl").innerHTML= _officeNo;
				document.getElementById("pickupAddressLine2Lbl").innerHTML= _addressLine2;
				document.getElementById("pickupAddressCityLbl").innerHTML= _cityLabel;
				document.getElementById("pickupAddresscountyLbl").innerHTML= _countyLabel;
				document.getElementById("pickupAddressStateLbl").innerHTML= _stateLabel;
				document.getElementById("pickupAddressProvinceLbl").innerHTML= _provinceLabel;
				document.getElementById("pickupAddressRegionLB").innerHTML= _region;
				document.getElementById("pickupAddressCountryLbl").innerHTML= _countryLabel;
				document.getElementById("pickupAddressPostCodeLbl").innerHTML= _postalCodeLabel;
		  }  
	   
	   fetchDefaultContactData();     
	   fetchDefaultAddressData(); 
	   
	   
	     /* ****************Functions used by Attachments START******************** */
   		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g,"");
		}
	     
		
	 	function deleteFile(fileName) {
	 		jQuery('#jsValidationErrors',window.parent.document).empty();
			document.getElementById('jsValidationErrors').innerHTML = "";
			document.getElementById("jsValidationErrors").style.display = 'none';
			
			var link = "${removeAttachmentURL}";
			link=link+ "&fileName=" + fileName;
			document.getElementById('serviceRequestAttachmentForm').target = 'upload_target';			
			document.getElementById('serviceRequestAttachmentForm').action = link;
			eval(document.serviceRequestAttachmentForm).submit();
			//jQuery('#serviceRequestAttachmentForm').submit();
	    }
		
		function setPathToTextBox(){
			var path = document.getElementById('files').value;
			if(path.match(/fakepath/)) {
				path = path.replace(/C:\\fakepath\\/i, '');
			}
			jQuery('#txtFilePath').val(path);
	    }
		
	    
		
 		function getFullPath(obj) 
		{ 
		    if(obj) 
		    { 
		        //ie 
		        if (window.navigator.userAgent.indexOf("MSIE")>=1) 
		        { 
		            obj.select(); 
		           var path = document.selection.createRange().text;
				   return path ; 
		        } 
		        else
		        {  
		            if(obj.files) 
		            { 
						return obj.files.item(0).fileName; 
		            } 
		            return obj.value;
		        } 
		    } 
		};
		function move(thisObject){
		    var a= document.getElementById('fileContent');
		    var offset = jQuery('#btnUpload').offset();
		    a.style.left = offset.left + 'px';
		    a.style.top = offset.top + 'px';
		};   
		function showHideDiv(box,id){
			var elm = document.getElementById(id);		
			elm.style.display = box.checked? "block":"none"	
			if(id == "exchangeOfOption"){
				document.getElementById("TextExchangeofOption").value = "<spring:message code='serviceRequest.helpMsg.exchangeOption'/>";
				document.getElementById("TextExchangeofOption").style.color='#D3D3D3';
			}
			if(id == "otherDiv"){
				document.getElementById("TextOther").value = "<spring:message code='serviceRequest.helpMsg.other'/>";
				document.getElementById("TextOther").style.color='#D3D3D3';
			}
	  	}
		
		
		<c:if test="${fileUploadForm.fileMap != null}">
		jQuery('#fileListTable',window.parent.document).empty();
			var responseText = "";
			var fileCountInJsp=0;
			responseText = '<thead><tr class="tableHeader"><th class="w15"></th><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35 right" ><spring:message code="serviceRequest.attachment.table.header.size"/></th></thead>';
			responseText = responseText + '<tbody>';			
			var index = 0;
				<c:forEach items="${fileUploadForm.fileMap}" var="entry" varStatus="rowCounter" begin="0">
				responseText = responseText + '<tr class="tableContentColor">';
				responseText = responseText + '<td class="w15"><img class="ui_icon_sprite trash-icon" id="img_help1"  src="<html:imagesPath/>transparent.png" style="cursor:pointer;" title="Delete" alt="Remove" onclick=\'deleteFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '<td class="width60" id="fileId_'+ index +'">';
	 			responseText = responseText + '<c:out value="${entry.value.displayFileName}" />' + '</td>';
	 			responseText = responseText + '<td class="width35  right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
				responseText = responseText + '</tr>';
				index = index+1;
				fileCountInJsp=fileCountInJsp+1;
				
				</c:forEach>
			responseText = responseText + '</tbody>'; 
			
			
			jQuery('#fileListTable',window.parent.document).append(responseText);
			jQuery('#txtFilePath',window.parent.document).val('');//Used to remove the filename when the upload is done
			jQuery('#jsValidationErrors',window.parent.document).empty();
			document.getElementById('jsValidationErrors').innerHTML = "";
			document.getElementById("jsValidationErrors").style.display = 'none';
		</c:if>
		
		<c:if test="${fileUploadForm.fileMap == null}">
		fileCountInJsp = 0;
		jQuery('#fileListTable',window.parent.document).empty();
	    </c:if>

		/*********************************************************************************
		******************************service address grid script*************************
		**********************************************************************************/

		serviceAddressGrid = new dhtmlXGridObject('serviceAddressGridbox'); 	 
	    serviceAddressGrid.setImagePath("<html:imagesPath/>gridImgs/");
	    serviceAddressGrid.setHeader("<spring:message code='serviceRequest.listHeader.serviceAddressList'/>");
	    serviceAddressGrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	    serviceAddressGrid.setInitWidths("130,120,120,130,130,100,60,60,70,100");
	    serviceAddressGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
	    serviceAddressGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	    serviceAddressGrid.setColSorting("na,str,str,str,str,str,str,str,str,na");
	    serviceAddressGrid.init();
	    serviceAddressGrid.prftInit();
	    serviceAddressGrid.enablePaging(true, 5, 6, "pagingArea5", true, "infoArea");
	    serviceAddressGrid.setPagingSkin("bricks");
	    serviceAddressGrid.enableAutoWidth(true);
	    serviceAddressGrid.enableAutoHeight(true);
	    serviceAddressGrid.enableMultiline(true);
	    serviceAddressGrid.a_direction = "ASCENDING";
	    serviceAddressGrid.setSizes();
	    serviceAddressGrid.setColumnHidden(0,true); // indicate column index and set the state to true to hire the column
	    serviceAddressGrid.setSkin("light");
	    serviceAddressGrid.attachEvent("onXLS", function() {
			document.getElementById('loadingNotification5').style.display = '';
		});
	    serviceAddressGrid.attachEvent("onMouseOver", function(id,ind){		
			return false;
		});
	    serviceAddressGrid.attachEvent("onXLE", function() {
	    	serviceAddressGrid.setSortImgState(true, 2, "asc");
	    	document.getElementById('loadingNotification5').style.display = 'none';
	    	setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
	    	setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
		});

	    //serviceAddressGrid2 for My Addresses
	    serviceAddressGrid2 = new dhtmlXGridObject('serviceAddressGridbox2');
	    serviceAddressGrid2.setImagePath("<html:imagesPath/>gridImgs/");
	    serviceAddressGrid2.setHeader("<spring:message code='serviceRequest.listHeader.myServiceAddressList'/>");
	    serviceAddressGrid2.attachHeader("&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,&nbsp;");
	    serviceAddressGrid2.setInitWidths("130,120,120,130,100,100,60,60,70,130");
	    serviceAddressGrid2.setColAlign("left,left,left,left,left,left,left,left,left,left");
	    serviceAddressGrid2.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	    serviceAddressGrid2.setColSorting("na,str,str,str,str,str,str,str,str,na");
	    serviceAddressGrid2.init();
	    serviceAddressGrid2.prftInit();
	    serviceAddressGrid2.enablePaging(true, 5, 6, "pagingArea6", true, "infoArea");
	    serviceAddressGrid2.setPagingSkin("bricks");
	    serviceAddressGrid2.enableAutoWidth(true);
	    serviceAddressGrid2.enableAutoHeight(true);
	    serviceAddressGrid2.enableMultiline(true);
	    serviceAddressGrid2.a_direction = "ASCENDING";
	    serviceAddressGrid2.setSizes();    
	    serviceAddressGrid2.setColumnHidden(0,true); // indicate column index and set the state to true to hire the column
	    serviceAddressGrid2.setSkin("light");
	    serviceAddressGrid2.attachEvent("onXLS", function() {
			document.getElementById('loadingNotification6').style.display = '';
		});
	    serviceAddressGrid2.attachEvent("onMouseOver", function(id,ind){		
			return false;
		});
	    serviceAddressGrid2.attachEvent("onXLE", function() {
	    	serviceAddressGrid2.setSortImgState(true, 2, "asc");
	    	document.getElementById('loadingNotification6').style.display = 'none';
	    	setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
	    	setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
		});

	    var searchCriteria="";
		 /*
		  *retrieve the favorite contact/address list associated with the contact requesting
		  *the service
		  */
			function loadMyFav(gridName) {		
				searchCriteria= "${serviceRequestConfirmationForm.loginAccountContact.contactId}";
				var url;				
				if(gridName=="primary"){
					url = "${serviceRequestConfirmationForm.contactListURL}";
				}
				else if(gridName=="secondary"){
					url = "${serviceRequestConfirmationForm.secContactListURL}";
				}else if(gridName=="serviceAddress"){
					url = "${serviceRequestConfirmationForm.serviceAddressListURL}";
				}
				if(searchCriteria != null && searchCriteria != "") {
					url = url + "&searchCriterias=" + searchCriteria;
					searchCriteria="";
				}			
				if(gridName=="primary"){						
			    	primaryContactGrid2.clearAndLoad(url);
				}else if(gridName=="secondary"){
			    	secondaryContactGrid2.clearAndLoad(url);
				}else if(gridName=="serviceAddress"){
					serviceAddressGrid2.clearAndLoad(url);
				}
			}
			
			 //**************************************************************// reload data
			function reloadGrid_SR(gridName) {			
		    	
		    	if(gridName != null)
		    	{
		   
				var url;				
				if(gridName=="primary"){
					url = customizedGridURL;
				}
				else if(gridName=="secondary"){
					url = "${serviceRequestConfirmationForm.secContactListURL}";
				}else if(gridName=="serviceAddress"){
					url = "${serviceRequestConfirmationForm.serviceAddressListURL}";
					//alert(url);
				}
				if(searchCriteria != null && searchCriteria != "") {
					url = url + "&searchCriterias=" + searchCriteria;
					searchCriteria="";
				}			
				if(gridName=="primary"){		
			    	primaryContactGrid.clearAndLoad(url);
				}else if(gridName=="secondary"){
			    	secondaryContactGrid.clearAndLoad(url);
				}else if(gridName=="serviceAddress"){
					serviceAddressGrid.clearAndLoad(url);
				}
			}
		    	else
		    		{
		    	     clearMessage();			
					primaryContactGrid.clearAndLoad(customizedGridURL);
		    		}
			} 
			
			/*

	                   Commented for AIR #LEX:AIR00059707
			function reloadGrid() {	 
				//alert("reloadGrid w/o parameter");
				clearMessage();			
				primaryContactGrid.clearAndLoad(customizedGridURL);		
				
			}*/
			
			function populateURLCriterias(){
				var url = "${serviceRequestConfirmationForm.contactListURL}";
				if(searchCriteria != null && searchCriteria != "") {
					url = url + "&searchCriterias=" + searchCriteria;
				}
				if(primaryContactGrid.filterValues != null && primaryContactGrid.filterValues != "") {
					url = url + "&filterCriterias=" + primaryContactGrid.filterValues;
				}
		        if(primaryContactGrid.a_direction != null && primaryContactGrid.a_direction != "") {
		            url = url + "&direction=" + primaryContactGrid.a_direction;
		        }
		        url = url + "&orderBy=" + primaryContactGrid.getSortColByOffset();
		        return url;
		    }	
			function doReset(){
				callOmnitureAction('primary Contact Request', 'primary Contact Request List Reset');
				resetGridSetting('primaryContactGrid', resetprimaryContactGridCallback);  
			};

		    function resetprimaryContactGridCallback() {
		        clearMessage();
		        colsOrder = "0,1,2,3,4,5,6";
		        colsWidth = "px:150,170,170,170,240,140";
		        colsSorting = "2,asc";
		        colsHidden = "";
		        primaryContactGrid.sortIndex = 2;
		        dBPersistentFlag = false;
		        gridToBeReset = true;
		        primaryContactGrid.loadOrder(colsOrder);
		        primaryContactGrid.loadPagingSorting(colsSorting,1);
		        primaryContactGrid.loadSize(colsWidth);
		        primaryContactGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
		        customizedGridURL = updateGridURL(customizedGridURL, 1, 'asc', primaryContactGrid.filterValues);
		        primaryContactGrid.clearAndLoad(customizedGridURL);
		    };
	    function addPrimaryContactElement(ci, ln, fn, wp, ea, currentFavStatus) {
			if(favStatus[ci]){
				currentFavStatus = favStatus[ci].isFavorite? "true":"false";
			}
	  		document.getElementById("serviceRequest.primaryContact.contactId").value=ci;
			document.getElementById("serviceRequest.primaryContact.lastName").value=ln;
			document.getElementById("serviceRequest.primaryContact.firstName").value=fn;
			document.getElementById("serviceRequest.primaryContact.workPhone").value=wp;
			document.getElementById("serviceRequest.primaryContact.emailAddress").value=ea;
	        //just for display purpose        
			document.getElementById("lastNameLabel").innerHTML=ln;
			document.getElementById("firstNameLabel").innerHTML=fn;
			document.getElementById("workPhoneLabel").innerHTML=wp;
			document.getElementById("emailAddressLabel").innerHTML=ea;		
			document.getElementById("primaryContactList").style.display="none";
			document.getElementById("primaryContactDiv").style.display="block";
			//we will mark that contact as favorite only if he or she is not a favriote contact for the requestor
			
			if(isPageLoaded == true && currentFavStatus == "false"){		
				setPrimaryContactFavourite(ci,true);
			}
			if(isPageLoaded == true){
				//manually triger the jquery validation framework.
				jQuery("#serviceRequestConfirmationForm").validate().element("#serviceRequest\\.primaryContact\\.contactId");
			}
	 	 }

	  	function addSecondaryContactElement(ci, ln, fn, wp, ea, currentFavStatus) {
	  		if(favStatus[ci]){
				currentFavStatus = favStatus[ci].isFavorite? "true":"false";
			}
		  	document.getElementById("serviceRequest.secondaryContact.contactId").value=ci;
			document.getElementById("serviceRequest.secondaryContact.lastName").value=ln;
			document.getElementById("serviceRequest.secondaryContact.firstName").value=fn;
			document.getElementById("serviceRequest.secondaryContact.workPhone").value=wp;
			document.getElementById("serviceRequest.secondaryContact.emailAddress").value=ea;
			//just for display purpose        
			
			document.getElementById("lastNameLabel2").innerHTML=ln;
			document.getElementById("firstNameLabel2").innerHTML=fn;
			document.getElementById("workPhoneLabel2").innerHTML=wp;
			document.getElementById("emailAddressLabel2").innerHTML=ea;	
			document.getElementById("secondaryContactList").style.display="none";
			document.getElementById("secondaryContactDiv").style.display="block";
			//we will mark that contact as favorite only if he or she is not a favriote contact for the requestor
			if(isPageLoaded == true && currentFavStatus == "false")
				setSecondaryContactFavourite(ci,true);
		  }
	  	function removePrimaryContactInformation() {
			var d = document.getElementById('primaryContactDiv');
			d.style.display = "none";		
	  	}

	  	function removeSecondaryContactInformation() {
			var d = document.getElementById('secondaryContactDiv');
			d.style.display = "none";
	  	}

	 	function removeServiceAddress() {
			var d = document.getElementById('serviceAddressDiv');
			d.style.display = "none";
	  	}

	    //note: favContactId will always refer to the contact who is being marked as favourite
	    // doSelectUnfavoriteFlag: if user selects an unfavorite contact, it will be true
	    function setPrimaryContactFavourite(favContactId, doSelectUnfavoriteFlag) {
	        var url = '<portlet:resourceURL id="setContactFavouriteFlag"/>';
	        url += "&contactId=${serviceRequestConfirmationForm.loginAccountContact.contactId}";
	        url += "&favoriteContactId=" + favContactId;
	        
	        // if user selects an unfavorite contact
	        if (doSelectUnfavoriteFlag) {
	            url += "&flagStatus=" + "false";
	            doAjax(url, null, null, "");
	            return;
	        }
	        // if the favorite flag is being updated
	        if (favStatus[favContactId]&&favStatus[favContactId].isLoading) {
	            return;
	        }
	        
	        var starImg = window.document.getElementById("starImg_primary_"+favContactId);
	        var isFavorite=(jQuery("#starImg_primary_"+favoriteAddressId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	        //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
	        favStatus[favContactId] = {isFavorite:isFavorite,isLoading:true};
	        starImg.src = "<html:imagesPath/>loading-icon.gif";
	        url += "&flagStatus=" + isFavorite;
	        
	        doAjax(url, callbackGetPrimaryContactResult, null, "");
	    }
	    //note: favContactId will always refer to the contact who is being marked as favourite
	    // doSelectUnfavoriteFlag: if user selects an unfavorite contact, it will be true
	    function setSecondaryContactFavourite(favContactId,doSelectUnfavoriteFlag) {         
	    	var url = '<portlet:resourceURL id="setContactFavouriteFlag"/>';
	        url += "&contactId=${serviceRequestConfirmationForm.loginAccountContact.contactId}";
	        url += "&favoriteContactId=" + favContactId;
	        
	        // if user selects an unfavorite contact
	        if (doSelectUnfavoriteFlag) {
	            url += "&flagStatus=" + "false";
	            doAjax(url, null, null, "");
	            return;
	        }
	        // if the favorite flag is being updated
	        if (favStatus[favContactId]&&favStatus[favContactId].isLoading) {
	            return;
	        }
	        
	        var starImg = window.document.getElementById("starImg_secondary_"+favContactId);
	        var isFavorite=(jQuery("#starImg_secondary_"+favoriteAddressId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	        //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
	        favStatus[favContactId] = {isFavorite:isFavorite,isLoading:true};
	        starImg.src = "<html:imagesPath/>loading-icon.gif";
	        url += "&flagStatus=" + isFavorite;
	        
	        doAjax(url, callbackGetSecondaryContactResult, callbackGetSecondaryContactFailure, "");           
	    }
	   
	    function setServiceAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag){        
	    	var url = '<portlet:resourceURL id="setServiceAddressFavouriteFlag"/>';
	        url += "&favoriteAddressId="+favoriteAddressId;
	        url += "&contactId=${serviceRequestConfirmationForm.loginAccountContact.contactId}";

	        // if user selects an unfavorite contact
	        if (doSelectUnfavoriteFlag) {
	            url += "&flagStatus=" + "false";
	            doAjax(url, null, null, "");
	            return;
	        }
	        // if the favorite flag is being updated
	        if (favStatus[favoriteAddressId]&&favStatus[favoriteAddressId].isLoading) {
	            return;
	        }
	        
	        var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
	        var isFavorite=(jQuery("#starImg_address_"+favoriteAddressId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	        //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
	        favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
	        starImg.src = "<html:imagesPath/>loading-icon.gif";
	        url += "&flagStatus=" + isFavorite;
	        
	        doAjax(url, callbackGetServiceAddressResult, callbackGetServiceAddressFailure, "");
	    }
	 	
	    // call back, when successfully update favorite status of primary contact
	    function callbackGetPrimaryContactResult(result) {
	        var contactId = result.data;
	        if (document.getElementById("primaryContactGridbox2").style.display!="none") {
	            var starImg = document.getElementById('starImg_primary_' + contactId);
	            //just change the star image
	            if (favStatus[contactId].isFavorite) {
	            	jQuery('#starImg_primary_'+contactId).removeClass('bookmark-star-icon');
	    			jQuery('#starImg_primary_'+contactId).addClass('removebookmark-icon');
	    			starImg.src = "<html:imagesPath/>transparent.png";
	    			//starImg.src = "<html:imagesPath/>unfavorite.png";
	            } else {
	            	jQuery('#starImg_primary_'+contactId).removeClass('removebookmark-icon');
	    			jQuery('#starImg_primary_'+contactId).addClass('bookmark-star-icon');
	    			starImg.src = "<html:imagesPath/>transparent.png";
	    			//starImg.src = "<html:imagesPath/>favorite.png";
	            }
	        }
	        if (favStatus[contactId].isFavorite) {
	            favStatus[contactId].isFavorite = false;
	        } else {
	            favStatus[contactId].isFavorite = true;
	        }
	        favStatus[contactId].isLoading = false;
	    }

	    // call back, when fail to update favorite status of primary contact
	    function callbackGetPrimaryContactFailure(result) {
	        var contactId = result.data;
	        if (document.getElementById("primaryContactGridbox2").style.display!="none") {
	            var starImg = document.getElementById('starImg_primary_' + contactId);
	            //just change the star image
	            if (favStatus[contactId].isFavorite) {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            } else {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            }
	        }
	        favStatus[contactId].isLoading = false;
	    }

		// call back, when successfully update favorite status of secondary contact
	    function callbackGetSecondaryContactResult(result) {
	        var contactId = result.data;
	        if (document.getElementById("secondaryContactGridbox2").style.display!="none") {
	        	var starImg = document.getElementById('starImg_secondary_' + contactId);
	        	//just change the star image
	            if (favStatus[contactId].isFavorite) {
	            	jQuery('#starImg_secondary_'+contactId).removeClass('bookmark-star-icon');
	    			jQuery('#starImg_secondary_'+contactId).addClass('removebookmark-icon');
	    			starImg.src = "<html:imagesPath/>transparent.png";
	    			//starImg.src = "<html:imagesPath/>unfavorite.png";
	            } else {
	            	jQuery('#starImg_secondary_'+contactId).removeClass('removebookmark-icon');
	    			jQuery('#starImg_secondary_'+contactId).addClass('bookmark-star-icon');
	    			starImg.src = "<html:imagesPath/>transparent.png";
	    			//starImg.src = "<html:imagesPath/>favorite.png";
	            }
	        }
	        if (favStatus[contactId].isFavorite) {
	            favStatus[contactId].isFavorite = false;
	        } else {
	            favStatus[contactId].isFavorite = true;
	        }
	        favStatus[contactId].isLoading = false;
	    }

	    // call back, when fail to update favorite status of secondary contact
	    function callbackGetSecondaryContactFailure(result) {
	        var contactId = result.data;
	        if (document.getElementById("secondaryContactGridbox2").style.display!="none") {
	            var starImg = document.getElementById('starImg_secondary_' + contactId);
	            //just change the star image
	            if (favStatus[contactId].isFavorite) {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            } else {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            }
	        }
	        favStatus[contactId].isLoading = false;
	    }

		// call back, when successfully update favorite status of service address
		function callbackGetServiceAddressResult(result) {
			var addressId = result.data;
			if (document.getElementById("serviceAddressGridbox2").style.display!="none") {		
				var starImg = document.getElementById('starImg_address_' + addressId);
				if (favStatus[addressId].isFavorite) {
					jQuery('#starImg_address_'+addressId).removeClass('bookmark-star-icon');
					jQuery('#starImg_address_'+addressId).addClass('removebookmark-icon');
					starImg.src = "<html:imagesPath/>transparent.png";
					//starImg.src = "<html:imagesPath/>unfavorite.png";
				} else {
					jQuery('#starImg_address_'+addressId).removeClass('removebookmark-icon');
					jQuery('#starImg_address_'+addressId).addClass('bookmark-star-icon');
					starImg.src = "<html:imagesPath/>transparent.png";
					//starImg.src = "<html:imagesPath/>favorite.png";
				}
			}
			if (favStatus[addressId].isFavorite) {
				favStatus[addressId].isFavorite = false;
			} else {
				favStatus[addressId].isFavorite = true;
			}
			favStatus[addressId].isLoading = false;
		}

	    // call back, when fail to update favorite status of service address
	    function callbackGetServiceAddressFailure(result) {
	        var addressId = result.data;
	        if (document.getElementById("serviceAddressGridbox2").style.display!="none") {      
	            var starImg = document.getElementById('starImg_address_' + addressId);
	            if (favStatus[addressId].isFavorite) {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            } else {
	                starImg.src = "<html:imagesPath/>transparent.png";
	            }
	        }
	        favStatus[addressId].isLoading = false;
	    }

	  	function showHideSecondaryDiv(img,id){ 	  	
			var elm = document.getElementById(id);		
			elm.style.display = elm.style.display=="none"? "block":"none";		
			if(elm.style.display=="block"){
				reloadGrid_SR('secondary');
				img.setAttribute("src", "<html:imagesPath/>collapsed.jpg"); 
				useDefaultSecondaryContactRecord();
				showSecContact();
			}else{
				img.setAttribute("src", "<html:imagesPath/>expand.jpg");		
			}	
	  	}
	  	/*****************************************************************************************************
		***************************************primary contact create/edit related javascript***************
		******************************************************************************************************/
		
		
		function useDefaultPrimaryContact(){ //use Asset Contact As Primary Contact				
			var ci = "${serviceRequestConfirmationForm.asset.assetContact.contactId}";
			var ln = "${serviceRequestConfirmationForm.asset.assetContact.lastName}";
			var fn = "${serviceRequestConfirmationForm.asset.assetContact.firstName}";
			var wp = "${serviceRequestConfirmationForm.asset.assetContact.workPhone}";
			var ea = "${serviceRequestConfirmationForm.asset.assetContact.emailAddress}";		
			addPrimaryContactElement(ci, ln, fn, wp, ea, "true");
			//disablePrimaryContactInputs();		
		}
		
		function useMyInfoAsPrimaryContact(){ //use login user As Primary Contact		
			var ci = "${serviceRequestConfirmationForm.loginAccountContact.contactId}";
			var ln = "${serviceRequestConfirmationForm.loginAccountContact.lastName}";
			var fn = "${serviceRequestConfirmationForm.loginAccountContact.firstName}";
			var wp = "${serviceRequestConfirmationForm.loginAccountContact.workPhone}";
			var ea = "${serviceRequestConfirmationForm.loginAccountContact.emailAddress}";
			addPrimaryContactElement(ci, ln, fn, wp, ea, "true");
			//disablePrimaryContactInputs();		
		}

		/*****************************************************************************************************
		***************************************secondary contact create/edit related javascript***************
		******************************************************************************************************/
		function useDefaultSecondaryContactRecord(){
			var ci = "${serviceRequestConfirmationForm.asset.assetContact.contactId}";
			var ln = "${serviceRequestConfirmationForm.asset.assetContact.lastName}";
			var fn = "${serviceRequestConfirmationForm.asset.assetContact.firstName}";
			var wp = "${serviceRequestConfirmationForm.asset.assetContact.workPhone}";
			var ea = "${serviceRequestConfirmationForm.asset.assetContact.emailAddress}";
			addSecondaryContactElement(ci, ln, fn, wp, ea, "true");		
		}
		
		function useMyInfoAsSecondaryContact(){ //use Login user As Secondary Contact
			var ci = "${serviceRequestConfirmationForm.loginAccountContact.contactId}";
			var ln = "${serviceRequestConfirmationForm.loginAccountContact.lastName}";
			var fn = "${serviceRequestConfirmationForm.loginAccountContact.firstName}";
			var wp = "${serviceRequestConfirmationForm.loginAccountContact.workPhone}";
			var ea = "${serviceRequestConfirmationForm.loginAccountContact.emailAddress}";
			addSecondaryContactElement(ci, ln, fn, wp, ea, "true");		
		}
		/*****************************************************************************************************
		***************************************service address create related javascript**********************
		******************************************************************************************************/
		//useAssetAddressAsDefaultServiceAddress	
		
		 function useDefaultServiceAddress(){ 	
			var ai = "${serviceRequestConfirmationForm.asset.installAddress.addressId}";
			var an = "${serviceRequestConfirmationForm.asset.installAddress.addressName}";
			var al1 = "${serviceRequestConfirmationForm.asset.installAddress.addressLine1}";
			var al2 = "${serviceRequestConfirmationForm.asset.installAddress.addressLine2}";
			var al3 = "${serviceRequestConfirmationForm.asset.installAddress.addressLine3}";
			var city = "${serviceRequestConfirmationForm.asset.installAddress.city}";		
			var state = "${serviceRequestConfirmationForm.asset.installAddress.state}";
			var province = "${serviceRequestConfirmationForm.asset.installAddress.province}";
			var stateProvince = "${serviceRequestConfirmationForm.asset.installAddress.stateProvince}";
			var country = "${serviceRequestConfirmationForm.asset.installAddress.country}";
			var zip = "${serviceRequestConfirmationForm.asset.installAddress.postalCode}";
			addServiceAddressElement(ai, an, al1, al2, al3, city, state, province, stateProvince, country, zip, "true");		
		   } 
		 function onCancelClick(){
			 var currentURL = window.location.href;
			 if(currentURL.indexOf('/fleet-management') != -1)
				{	
				 jConfirm('<spring:message code="common.country.alert"/>', "", function(confirmResult) {
						if(confirmResult){
							showOverlay();
							 //window.location.href="<portlet:renderURL><portlet:param name='action' value='default' /></portlet:renderURL>"
							
							 var lat=document.getElementById("pickupAddresslatitude").value;
							
							var lon=document.getElementById("pickupAddresslongitude").value;
					//var lat=49.474685633790784;
				//		var lon=1.112907825559887;
							
											var defaultArea={											
										                lat: lat,
										                lng: lon,							                		           
														

													
													};
							
							$('#backJson').val(JSON.stringify(defaultArea));
							$('#backFormMap').submit();
						}else{
							return false;
							}
					});
				
				}else{
					 window.location.href="<portlet:renderURL><portlet:param name='action' value='default' /></portlet:renderURL>"
				}
				
			}

			function getInternetExplorerVersion()
			// Returns the version of Windows Internet Explorer or a -1
			// (indicating the use of another browser).
			{
			   var rv = -1; // Return value assumes failure.
			   if (navigator.appName == 'Microsoft Internet Explorer')
			   {
			      var ua = navigator.userAgent;
			      var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
			      if (re.exec(ua) != null)
			         rv = parseFloat( RegExp.$1 );
			   }
			   return rv;
			}	
								    
			//jquery form validation approach along with some initiliazation work
			jQuery(document).ready(function() {	
				var JQueryfileCount = fileCountInJsp;
	             window.parent.document.getElementById("fileCount").value = JQueryfileCount;
	           //Added for CI BRD13-10-08 --STARTS
	         	jQuery("#pickupStoreFrontNameLbl").html(document.getElementById("pickupStoreFrontName").value);
	         	jQuery("#pickupAddressNameLbl").html(document.getElementById("pickupAddressName").value);
	         	document.getElementById("pickupAddressLine1Lbl").innerHTML=document.getElementById("pickupAddressLine1").value;
	         	jQuery("#pickupAddressofficeNumberLbl").html(document.getElementById("pickupAddressofficeNumber").value);
	         	if(document.getElementById("pickupAddressLine2").value !="" && document.getElementById("pickupAddressLine2").value !=null){
	         		jQuery("#pickupAddressLine2Lbl").html(document.getElementById("pickupAddressLine2").value + ',');
	         		jQuery("#pickupAddressLine2Lbl").show();
	         		}
	         	if(document.getElementById("pickupAddressCity").value!='' && document.getElementById("pickupAddressCity").value != null){
	         		jQuery("#pickupAddressCityLbl").html(document.getElementById("pickupAddressCity").value);
	         		}
	         	if(document.getElementById("pickupAddresscounty").value!='' && document.getElementById("pickupAddresscounty").value != null){
	         		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+document.getElementById("pickupAddresscounty").value);
	         		jQuery("#pickupAddresscountyLbl").show();
	         	}
	         	if(document.getElementById("pickupAddressstateCode").value!='' && document.getElementById("pickupAddressstateCode").value != null){
	         		jQuery("#pickupAddressStateLbl").html(',&nbsp;'+document.getElementById("pickupAddressstateCode").value);
	         	}
	         	if((document.getElementById("pickupAddressProvince").value!='' && document.getElementById("pickupAddressProvince").value!=' ') && document.getElementById("pickupAddressProvince").value != null){
	         		jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+document.getElementById("pickupAddressProvince").value);
	         		jQuery("#pickupAddressProvinceLbl").show();
	         	}
	         	if((document.getElementById("pickupAddressdistrict").value!=' ' && document.getElementById("pickupAddressdistrict").value!='') && document.getElementById("pickupAddressdistrict").value != null){
	         		jQuery("#pickupAddressRegionLB").html(',&nbsp;'+document.getElementById("pickupAddressdistrict").value);
	         		jQuery("#pickupAddressRegionLB").show();
	         	}
	         	jQuery("#pickupAddressPostCodeLbl").html(document.getElementById("pickupAddressPostCode").value);
	         	jQuery("#pickupAddressCountryLbl").html(document.getElementById("pickupAddressCountry").value);	
	         	if((document.getElementById("pickupAddressProvince").value=='' || document.getElementById("pickupAddressProvince").value==' ') || document.getElementById("pickupAddressProvince").value == null){
	         		jQuery("#pickupAddressProvinceLbl").hide();
	         	}
	         	if(document.getElementById("pickupAddresscounty").value=='' || document.getElementById("pickupAddresscounty").value == null){
	         		jQuery("#pickupAddresscountyLbl").hide();
	         	}
	         	if((document.getElementById("pickupAddressdistrict").value=='' || document.getElementById("pickupAddressdistrict").value==' ') || document.getElementById("pickupAddressdistrict").value == null){
	         		jQuery("#pickupAddressRegionLB").hide();
	         	}
	         	if(document.getElementById("pickupAddressLine2").value=='' || document.getElementById("pickupAddressLine2").value == null){
	         		jQuery("#pickupAddressLine2Lbl").hide();
	         	}
	         	
	         	jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
	         	//Added for CI BRD13-10-08 --ENDS
	          
			   jQuery.validator.addMethod("validateOtherRequestdService",function(value, element){
					if(document.getElementById("otherDiv").style.display != "none" ){
						if(document.getElementById("TextOther").value == "<spring:message code='serviceRequest.helpMsg.other'/>" || document.getElementById("TextOther").value == "")
							return false;
					}
					return true;
				},"<spring:message code='serviceRequest.errorMsg.other'/>");
				
				jQuery.validator.addMethod("validateOptionExchange",function(value, element){
					var optionObj = document.getElementById("exchangeOfOption");
					if(optionObj){
						if(optionObj.style.display != "none" ){
							if(document.getElementById("TextExchangeofOption").value == "<spring:message code='serviceRequest.helpMsg.exchangeOption'/>" || document.getElementById("TextExchangeofOption").value =="")
								return false;
						}
					}
					return true;
				},"<spring:message code='serviceRequest.errorMsg.exchangeOption'/>");

				jQuery("#serviceRequestConfirmationForm").validate({
					rules: {
						"serviceRequest.primaryContact.contactId":"required",
						"serviceRequest.serviceAddress.addressId":"required",
						"serviceRequest.problemDescription":"required",    	  
						"serviceRequest.otherRequestedService": {"validateOtherRequestdService":true},
						"serviceRequest.optionExchangeOtherDescription": {"validateOptionExchange":true},
						"selectedServiceDetails":	{required: "input[name='selectedServiceDetails']", minlength: 1}
					},       
					messages: {        	
						"serviceRequest.problemDescription":"<spring:message code='serviceRequest.errorMsg.problemDescription'/>"
					}
				});
		      if (document.getElementById('asset.notMyPrinter').value == 'false') {
		          useDefaultPrimaryContact(); //try to fill up the information, even there isn't any, <showPriContact>will handle the rest.
		          useDefaultServiceAddress();
		      }
		      showPriContact();
			  showAddress();
			  isPageLoaded = true;
		    });
		    
			 //change color for selected button
			  function changeMyColor(btnName){
				  btnEle = document.getElementById(btnName);
				  btnEle.style.backgroundColor="#6495ED";
				  if(btnEle.id=="myFavoriteContactsBtn")
					  document.getElementById("allContactsBtn").style.backgroundColor="#CFE4FE";
				  if(btnEle.id=="allContactsBtn")
					  document.getElementById("myFavoriteContactsBtn").style.backgroundColor="#CFE4FE";
				  if(btnEle.id=="secFavBtn")
					  document.getElementById("secAllBtn").style.backgroundColor="#CFE4FE";
				  if(btnEle.id=="secAllBtn")
					  document.getElementById("secFavBtn").style.backgroundColor="#CFE4FE";
				  if(btnEle.id=="myFavoriteAddressesBtn")
					  document.getElementById("accountAddressesBtn").style.backgroundColor="#CFE4FE";
				  if(btnEle.id=="accountAddressesBtn")
					  document.getElementById("myFavoriteAddressesBtn").style.backgroundColor="#CFE4FE";	  
			  }
              function setFocus(inputId){
				  document.getElementById(inputId).focus();
			  }

			  function selectAnotherPrinter(){
				  callOmnitureAction('Service Request', 'Service Request Detail Select Another Asset');
				  var answer = confirm ("<spring:message code="serviceRequest.alert.selectAnotherPrinter" />");
					if (!answer){		 
							return;
					}else{
						window.location.href="<portlet:renderURL><portlet:param name='action' value='displayAssetSelection' /></portlet:renderURL>"
					}
			  }
			 
			  //show up the primary contact if the asset has one,otherwise bring up a contact list where user can make a selection
			  // also bring up a contact list in not my printer scenario.
			  function showPriContact(){	 
				  var ci = "${serviceRequestConfirmationForm.asset.assetContact.contactId}";	
				if(ci==""||ci==null||document.getElementById('asset.notMyPrinter').value == 'true'){
					chooseADifferentOne('primary');
				}else{
					document.getElementById("primaryContactList").style.display="none";	
					document.getElementById("primaryContactDiv").style.display="block";	
				}
			  }

			  //show up the secondary contact if the asset has one,otherwise bring up a contact list where user can make a selection
			  function showSecContact(){
				  var ci = "${serviceRequestConfirmationForm.asset.assetContact.contactId}";	
				if(ci==""||ci==null){		
					chooseADifferentOne('secondary');		
				}else{		
					document.getElementById("secondaryContactList").style.display="none";	
					document.getElementById("secondaryContactDiv").style.display="block";	
				}
			  }

			  //show up the service address if the asset has one,otherwise bring up a address list where user can make a selection
			  // also bring up a address list in not my printer scenario.
			  function showAddress(){	 
				  var ci = "${serviceRequestConfirmationForm.asset.installAddress.addressId}"; 
				if(ci==""||ci==null||document.getElementById('asset.notMyPrinter').value == 'true'){		
					chooseADifferentOne('address');
				}else{
					document.getElementById("serviceAddressList").style.display="none";	
					document.getElementById("serviceAddressDiv").style.display="block";		
				}
			  }

			  function chooseADifferentOne(gridName){	  
				  if(gridName == "primary"){
					  //let's remove any value user previously chose. 
					  //why? because user may proceed without select anything. we would like to stop such attamp.
					  document.getElementById("serviceRequest.primaryContact.contactId").value = "";	 
					  document.getElementById("primaryContactList").style.display="block";
					  document.getElementById("primaryContactDiv").style.display="none";		 
					  switchToAllContacts('primary');		  
					  document.getElementById("sameAsRequestorCheckBox").checked = false;
						checkboxReset();
				  }
				  if(gridName == "secondary"){
					  document.getElementById("secondaryContactList").style.display="block";
					  document.getElementById("secondaryContactDiv").style.display="none";
					  switchToAllContacts('secondary');
				  }
				  if(gridName == "address"){
					  //let's remove any value user previously chose. 
					  //why? because user may proceed without select anything. we would like to stop such attamp.
					  document.getElementById("serviceRequest.serviceAddress.addressId").value = "";
					  document.getElementById("serviceAddressList").style.display="block";
					  document.getElementById("serviceAddressDiv").style.display="none";	
					  switchToAllAccountAddresses();	 
				  }
			  }  
			//create new contact
				function popupCreateNewContactPage(gridName){
					callOmnitureAction('Service Request', 'Service Request Detail Create New Contact');
					new Liferay.Popup({
						title: "<spring:message code='serviceRequest.title.createModifyContact'/>",
						position: [450,50], 
						modal: true,
						width: 450,
						height: 300,
						xy: [100, 100],
						onClose: function() {showSelect();},
						url:"<portlet:renderURL
							windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
							"<portlet:param name='action' value='showCreateNewPage' />" + 				
							"</portlet:renderURL>&gridName=" + gridName
						});
					};

				//edit a contact		
					function popupEditContactPage(gridName,ci,ln,fn,wp,ea){
						callOmnitureAction('Service Request', 'Service Request Detail Edit New Contact');
						new Liferay.Popup({
							title: "<spring:message code='serviceRequest.title.createModifyContact'/>",
							position: [450,50], 
							modal: true,
							width: 500, 
							height: 300,
							xy: [100, 100],
							onClose: function() {showSelect();},
							url:"<portlet:renderURL
								windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
								"<portlet:param name='action' value='showEditPage' />" + 				
								"</portlet:renderURL>&gridName=" + gridName + "&contactId="+ ci + "&lastName=" + ln +
								"&firstName=" + fn + "&workPhone=" + wp + "&emailAddress=" + ea
							});
						};
						//create a new address
						function popupCreateNewAddressPage(gridName){
							callOmnitureAction('Service Request', 'Service Request Detail Create New Address');
							new Liferay.Popup({
								title: "<spring:message code='serviceRequest.title.createNewAddress'/>",
								position: [450,50], 
								modal: true,
								width: 600, 
								height: 400,
								xy: [100, 100],
								onClose: function() {showSelect();},
								url:"<portlet:renderURL
									windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
									"<portlet:param name='action' value='showCreateNewPage' />" + 				
									"</portlet:renderURL>&gridName=" + gridName
								});
							};

										
							//popup wait page
							function popupWaitPage(){
								new Liferay.Popup({
									title: "",
									position: [450,50], 
									modal: true,
									width: 400, 
									height: 200,
									xy: [100, 100],
									onClose: function() {showSelect();},
									url:"<portlet:renderURL
										windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
										"<portlet:param name='action' value='showWaitPage' />" + 				
										"</portlet:renderURL>"
									});
								}

							function hideSelect(){   
								var selects = document.getElementsByTagName('select');
								for (var i=0; i < selects.length; i++){
									var select = selects[i];
									select.style.visibility = "hidden";
								}      
							}  

							function showSelect(){   
								var selects = document.getElementsByTagName('select');
								for (var i=0; i < selects.length; i++){
									var select = selects[i];
									select.style.visibility = "visible";
								} 
							}  

							

					function switchToAllContacts(gridName){		
						if(gridName=='primary'){
							  document.getElementById("primaryContactGridbox").style.display="block";
							  document.getElementById("primaryContactGridbox2").style.display="none";
							  //document.getElementById("loadingNotification1").style.display="block";
							  document.getElementById("loadingNotification2").style.display="none";
							  document.getElementById("pagingArea").style.display="block";
							  document.getElementById("pagingArea2").style.display="none";
							  changeMyColor('allContactsBtn');				
							  if(isPriContactGridLoaded==false){				
							  	reloadGrid_SR('primary');
							  	isPriContactGridLoaded=true;
							  }
						}
						if(gridName=='secondary'){
							  document.getElementById("secondaryContactGridbox").style.display="block";
							  document.getElementById("secondaryContactGridbox2").style.display="none";
							  document.getElementById("loadingNotification4").style.display="none";
							  document.getElementById("pagingArea3").style.display="block";
							  document.getElementById("pagingArea4").style.display="none";
							  changeMyColor('secAllBtn');
							  if(isSecContactGridLoaded==false){
								  reloadGrid_SR('secondary');
							  	  isSecContactGridLoaded=true;
							  }
						}
						  
					}
					function switchToMyContacts(gridName){
						if(gridName=='primary'){				
							  document.getElementById("primaryContactGridbox2").style.display="block";
							  document.getElementById("primaryContactGridbox").style.display="none";
							  document.getElementById("pagingArea2").style.display="block";
							  document.getElementById("pagingArea").style.display="none";				 
							  changeMyColor('myFavoriteContactsBtn');				
							  loadMyFav('primary');
						}
						if(gridName=='secondary'){
							  document.getElementById("secondaryContactGridbox2").style.display="block";
							  document.getElementById("secondaryContactGridbox").style.display="none";
							  document.getElementById("loadingNotification3").style.display="none";
							  document.getElementById("pagingArea4").style.display="block";
							  document.getElementById("pagingArea3").style.display="none";
							  changeMyColor('secFavBtn');
							  loadMyFav('secondary');
						}
					}

					function switchToAllAccountAddresses(){			
							  document.getElementById("serviceAddressGridbox").style.display="block";
							  document.getElementById("serviceAddressGridbox2").style.display="none";
							  document.getElementById("loadingNotification6").style.display="none";
							  document.getElementById("pagingArea5").style.display="block";
							  document.getElementById("pagingArea6").style.display="none";
							  changeMyColor('accountAddressesBtn');
							  if(isServiceAddressGridLoaded==false){
								 
							  	reloadGrid_SR('serviceAddress');
							  	isServiceAddressGridLoaded=true;
							  }	  
					}
					function switchToMyAddresses(){		
							  document.getElementById("serviceAddressGridbox2").style.display="block";
							  document.getElementById("serviceAddressGridbox").style.display="none";
							  document.getElementById("loadingNotification5").style.display="none";
							  document.getElementById("pagingArea6").style.display="block";
							  document.getElementById("pagingArea5").style.display="none";
							  changeMyColor('myFavoriteAddressesBtn');
							  loadMyFav('serviceAddress');			
					}

					function submitSRForm(){
						clearBorder();
						callOmnitureAction('Service Request', 'Service Request Detail Submit SR');
						document.getElementById('isDraftFlag').value = false;
						
						//--- DEFECT 1942
						var validationFlag = true;
						document.getElementById('jsValidationErrors').innerHTML = "";
						document.getElementById('jsValidationErrors').style.display = "none";
						
						if(jQuery('#problemDescription').val()==null || jQuery('#problemDescription').val()=='' ) {
							jQuery('#jsValidationErrors').append('<li><strong><spring:message code="serviceRequest.errorMsg.problemDescription"/></strong></li>');
							validationFlag = false;
							jQuery('#problemDescription').addClass('errorColor');
						}
						
						var temCheckbox = true;
							jQuery(".selectedServiceDetails:checked").each(function() {
								temCheckbox = false;
							}
						  );
						if(temCheckbox){
							jQuery('#jsValidationErrors').append('<li><strong><spring:message code="serviceRequest.errorMsg.selectedServiceDetails"/></strong></li>');
							validationFlag = false;
							jQuery('#typeIfServiceNeededId').addClass('errorBorderColor');
						}
						if(validationFlag){
							cleanUpHelpText();
							clearBorder();
							//jQuery("#serviceRequestConfirmationForm").submit();
							showOverlay();
							document.getElementById('serviceRequestConfirmationForm').submit();
							
						}
						else {
							document.getElementById('jsValidationErrors').style.display = "block";
						}
					}
					
					
				    function doOnblur(obj, type){
						if(obj.value==''){
							obj.value= getHelpMessage(type);
							obj.style.color='#D3D3D3';
						}
					}
					
				function doOnFocus(obj, type){
					if(obj.value==getHelpMessage(type)){
						obj.value='';
						obj.style.color='black';
					}
				}

				function getHelpMessage(type){
					var value = "";
					if(type == "exchangeofOption")
						value = "<spring:message code='serviceRequest.helpMsg.exchangeOption'/>";
					if(type == "other")
						value = "<spring:message code='serviceRequest.helpMsg.other'/>";
					if(type == "addressName")
						value = "<spring:message code='serviceRequest.helpMsg.addressName'/>";
					return value;		
				}		
				 function cleanUpHelpText(){
						if (document.getElementById("TextExchangeofOption") && jQuery.trim(document.getElementById("TextExchangeofOption").value)=="<spring:message code='serviceRequest.helpMsg.exchangeOption'/>"){
							document.getElementById("TextExchangeofOption").value = "";
						}
						if (document.getElementById("TextOther") && jQuery.trim(document.getElementById("TextOther").value)=="<spring:message code='serviceRequest.helpMsg.other'/>"){
							document.getElementById("TextOther").value = "";
						}
					}
					window.onload = function(){
						if (document.getElementById("TextExchangeofOption") && jQuery.trim(document.getElementById("TextExchangeofOption").value)==''){
							document.getElementById("TextExchangeofOption").value = "<spring:message code='serviceRequest.helpMsg.exchangeOption'/>";
						}
						if (document.getElementById("TextOther") && jQuery.trim(document.getElementById("TextOther").value)==''){
							document.getElementById("TextOther").value = "<spring:message code='serviceRequest.helpMsg.other'/>";
						}
					}
					function popupAddress(hyperlinkId){
						linkId=hyperlinkId;//This link id is for displaying inner htmls in address
						
						jQuery('#dialogAddress').load(jQuery('#'+hyperlinkId).attr('href'),function(){	
							dialog=jQuery('#content_addr').dialog({
								autoOpen: false,
								title: jQuery('#'+hyperlinkId).attr('title'),
								modal: true,
								draggable: false,
								resizable: false,
								height: 500,
								width: 950,
								close: function(event,ui){
									dialog=null;
									jQuery('#content_addr').remove();
									}
								});
							jQuery(document).scrollTop(0);
							dialog.dialog('open');
							initializeAddressGrid();
							
							});
						return false;
					}

					

					function draftSRForm(){
						callOmnitureAction('Service Request', 'Service Request Detail Draft SR');
						document.getElementById('isDraftFlag').value = true;
						cleanUpHelpText();
						document.getElementById('serviceRequestConfirmationForm').submit();
					};
					
					
				   function closeDialog()
					{
						dialog.dialog('close');			
						dialog=null;
					} 

					function clearBorder(){
						jQuery('#typeIfServiceNeededId').removeClass('errorBorderColor');
						jQuery('#typeIfServiceNeededId').addClass('noBorder');
					}
					
					/*Method gotoControlPanel added for Defect 3924- Jan Release*/
					function gotoControlPanel(url) {

						controlPanelPopUpWindow.show();
						controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
						controlPanelPopUpWindow.io.start();
						<%--
				    	new Liferay.Popup({
				            title: "",
				            position: [400,150], 
				            modal: true,
				            width: 400, 
				            height: 150,
				            xy: [100, 100],
				            onClose: function() {showSelect();},
				            url:"<portlet:renderURL
				                windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
				                "<portlet:param name='action' value='gotoControlPanel' />" + 
				                "</portlet:renderURL>&controlPanelURL=" + url
				            });  --%>
				    };
				    <%--Changes for CI BRD 13-10-08 STARTS--%>
				    function addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
				    		addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode)
				    {	
				    	if(addressProvince == null){
				    		addressProvince = '';
				    	}
				    	if(addressId==null){
				    		addressId = '-1';
				    	}
				    	jQuery("#pickupAddressId").val(addressId);
				    	jQuery("#pickupStoreFrontName").val(storefrontName);
				    	jQuery("#pickupAddressName").val(addressName);
				    	jQuery("#pickupAddressLine1").val(addressLine1);		
				    	jQuery("#pickupAddressLine2").val(addressLine2);
				    			
				    	jQuery("#pickupAddressCity").val(addressCity);
				    	jQuery("#pickupAddressState").val(addressState);
				    	jQuery("#pickupAddressProvince").val(addressProvince);
				    	jQuery("#pickupAddressPostCode").val(addressPostCode);
				    	jQuery("#pickupAddressCountry").val(addressCountry);
				    	<%--Changes for Phase 2.1--%>
				    	jQuery("#pickupAddressofficeNumber").val(officeNumber);
				    	jQuery("#pickupAddressdistrict").val(district);
				    	jQuery("#pickupAddresscounty").val(county);

				    	jQuery("#pickupAddressregion").val(region);
				    	jQuery("#pickupAddresscountryISOCode").val(countryISO);
				    	jQuery("#pickupAddressstateCode").val(stateCode);

				    	//This is for display purpose pickup address
				    	jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
				    	jQuery("#pickupAddressNameLbl").html(addressName);
				    	jQuery("#pickupAddressLine1Lbl").html(addressLine1);
				    	jQuery("#pickupAddressofficeNumberLbl").html(officeNumber);		
				    	if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
				    		jQuery("#pickupAddressLine2Lbl").html(addressLine2+",");
				    		jQuery("#pickupAddressLine2Lbl").show();
				    		}

				    	if(addressCity !='' && addressCity !=' ' && addressCity != null){
				    		jQuery("#pickupAddressCityLbl").html(addressCity);
				    		}
				    	
				    	if(county !='' && county !=' ' && county != null){
				    		jQuery("#pickupAddresscountyLbl").html(', '+county);
				    		jQuery("#pickupAddresscountyLbl").show();
				    		}
				    	if(addressState !='' && addressState !=' ' && addressState != null){
				    		jQuery("#pickupAddressStateLbl").html(", "+addressState);
				    		jQuery("#pickupAddressStateLbl").show();
				    		}
				    	if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
				    		jQuery("#pickupAddressProvinceLbl").html(', '+addressProvince);
				    		jQuery("#pickupAddressProvinceLbl").show();
				    		}
				    	// region changed to district fot MPS 2.1
				    	if(district !=' ' && district !=' ' && district != null){
				    		jQuery("#pickupAddressRegionLB").html(', '+district);
				    		jQuery("#pickupAddressRegionLB").show();
				    		}
				    	jQuery("#pickupAddressPostCodeLbl").html(addressPostCode);		
				    	jQuery("#pickupAddressCountryLbl").html(addressCountry);
				    	if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
				    		jQuery("#pickupAddressProvinceLbl").hide();
				    		}
				    	if(county =='' || county ==' ' || county == null){
				    		jQuery("#pickupAddresscountyLbl").hide();
				    		}
				    	// region changed to district for MPS 2.1
				    	if(district ==' '||district =='' || district == null){
				    		jQuery("#pickupAddressRegionLB").hide();
				    		}
				    	if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
				    		jQuery("#pickupAddressLine2Lbl").hide();
				    		}
				    	if(addressState =='' || addressState ==' ' || addressState == null){
				    		jQuery("#pickupAddressStateLbl").hide();
				    		}
				    	
				    }
				    	function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2, 
				    			addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode)
				    	
				    	{
				    		if(addressId==null || addressId=="")
				    			jQuery('#pickupAddressisAddressCleansed').val("false");
				    		else
				    			jQuery('#pickupAddressisAddressCleansed').val("true");
				    		
				    		
				    		addressChangeReq=true;
				    		if(linkId=="diffAddressLink")
				    		{
				    			<%--Changes for Phase 2.1--%>
				    			//call to addConsumablesAddress in the commonAsset.js
				    			addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
				    					addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
				    			<%--Ends--%>
				    		}
				    		closeDialog();
				    	}
				    	function addRestFieldsOfCleanseAddress(){
				    		jQuery('#pickupAddressisAddressCleansed').val("true");
				    		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
				    			jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
				    			<%}%>
				    		jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
				    		//Start Added for MPS 2.1
				    		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+jQuery('#pickupAddresscounty').val());
				    		jQuery("#pickupAddresscountyLbl").show();
				    		if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() == null){
				    			jQuery("#pickupAddresscountyLbl").hide();
				    			}
				    		//jQuery("#pickupAddressdistrictLbl").html(jQuery('#pickupAddressdistrict').val());
				    		//END
				    		}
				    	<%--Changes for CI BRD 13-10-08 ENDS--%>
			 
			      
		</script> 
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});


</script>