<%@ page pageEncoding="UTF-8" %>
<% String path=request.getContextPath(); %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ include file="/WEB-INF/jsp/noBackToThisPage.jsp" %>
<%--ChangeMgmtConstant imported for CI BRD 13-10-26  --%>
<%@page import ="static com.lexmark.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<%--MPS.CSS Added for CI BRD13-10-26 --%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css">
.close-btn{
       float:right;
       width:30px;
       height:30px;
       
}
.displayGrid{line-height:20px;}
div.portlet-wrap-inner .columns table tr{}

.ie7 .dhx_combo_box {
	width: 207px !important;
}
.ie7 .dhx_combo_list {
	width: 200px !important;
} 
.ie7 .dhx_combo_input {
	width: 200px !important;
} 
.pagination span#gridRDVOrderedPartsTablePagingArea>div { width:95%!important }
.pagination span#gridRDVbeReturnedPartsPagingArea>div { width:100%!important }
.pagination span#pagingAreaRecommend>div { width:100%!important }

.ie7 #pagingAreaNotes>div { width:100%!important }

#popup_content table {
	width:100%;
}

</style>
<script type="text/javascript">
showSRPopupFunction();
var showSRPopup;
function showSRPopupFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	showSRPopup=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	position: [-80,20],
	resizable: false,
	width: 800,
	height: 2000,
	xy: [-80, 20],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}



window.dhx_globalImgPath = "<%=path%>/js/codebase/imgs/";
</script>
<!-- <link rel="STYLESHEET" type="text/css" href="<%=path%>/js/codebase/dhtmlxcombo.css"> --> 
<script src='<html:rootPath/>js/trackingNumber.js'></script>
<script language="JavaScript" type="text/javascript" src="<html:rootPath/>js/jquery.validate.js"></script>
<!--<script language="JavaScript" type="text/javascript" src="<%=path%>/js/codebase/dhtmlxcommon.js"></script>-->
 

<portlet:actionURL var="updateRequestDetailURL">
	<portlet:param name="action" value="updateRequestDetailURL"/>
	<portlet:param name="serviceRequestId" value="${serviceRequestDetailForm.activity.serviceRequest.id}" />
	<portlet:param name="activityId" value="${serviceRequestDetailForm.activity.activityId}" />
</portlet:actionURL>
<portlet:renderURL  var="updateNoteUrl" windowState='<%= LiferayWindowState.POP_UP.toString() %>'>
    <portlet:param name="action" value="showUpdateNotePopup" />
</portlet:renderURL>

<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>

<portlet:actionURL  var="cancelRequestUpdateUrl">
    <portlet:param name="action" value="cancelRequestUpdate" />
</portlet:actionURL>

<portlet:actionURL var="addAttachmentsRequestUpdate">
	<portlet:param name="action" value="addAttachmentsRequestUpdate"/>
</portlet:actionURL>

<portlet:actionURL var="removeAttachmentURL">
	<portlet:param name="action" value="removeAttachmentActionRequestUpdate"/>
</portlet:actionURL>

<%--Added for CI BRD 13-10-26 STARTS --%>
<portlet:renderURL  var="showAddressPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showSelectAddressPage" />
</portlet:renderURL>
<%--Added for CI BRD 13-10-26 ENDS --%>

<c:set var="assetId" value="${serviceRequestDetailForm.activity.serviceRequest.asset.assetId}"/>
<c:set var="serviceRequestId" value="${serviceRequestDetailForm.activity.serviceRequest.id}"/>

<%--Added for CI BRD13-10-26 STARTS--%>
<%-- Below div opens up as popup with address--%>
<div id="dialogAddress"></div>
<%--Added for CI BRD13-10-26 ENDS--%>

<script type="text/javascript">

var shipToaddrFlag = [];

</script>
<!-- Style added for Production Defect #12891 -->
<style>
.ie7 .service_information_width, .ie8 .service_information_width{width:95%}
.service_information_width td input{float:left}
.ie7 .service_information_width tr {line-height:30px}
.service_information_width{width:80%}
.service_information_width td img{float:left !important}
.service_information_width {border-collapse:separate;border-spacing:1em;}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
	<tr>
		<td>
			<form:form id="requestDetailUpdateForm" name="requestDetailUpdateForm" commandName="serviceRequestDetailForm" method="post" action="${updateRequestDetailURL}">
			<input type="hidden" id="partnerAccountIdHidden" value=""></input>
			<div class="main-wrap">
			    <div class="content">
			        <div class="grid-controls">
			            <div class="utilities">
			            </div><!-- utilities -->
			            
			            <div class="expand-min">
			                <ul>
			                    <li class="first">
			                        <a id="headerImageButton" class="button" href="${requestsListViewUrl}&back=true"> &lt;&lt;<spring:message code="link.return.to.requests" /></a><!-- Changed By sabya for AIR00072632 -->
			                    </li>
			                </ul>
			            </div><!-- expand-min -->  
			            <div class="clear-right"></div><!-- clear -->
			        </div>
			        <%--Added for CI BRD 13-10-26 STARTS --%>
		           					 
						         <span style="display: none">
						         	<form:input id="pickupAddressId" path="activity.shipToAddress.addressId" />
						         	<form:input id="pickupStoreFrontName" path="activity.shipToAddress.storeFrontName" />
							     	<form:input id="pickupAddressLine1" path="activity.shipToAddress.addressLine1" /> 
							        <form:input id="pickupAddressLine2" path="activity.shipToAddress.addressLine2" />
							        <form:input id="pickupAddressCity" path="activity.shipToAddress.city" />
							        <form:input id="pickupAddressState" path="activity.shipToAddress.state" />
							        <form:input id="pickupAddressProvince" path="activity.shipToAddress.province" />
							        <form:input id="pickupAddressPostCode" path="activity.shipToAddress.postalCode" />
							        <form:input id="pickupAddressCountry" path="activity.shipToAddress.country" />

									<!-- Below Fields for Cleansing address Don't change the-->
 									<!--input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT  -->
									
									<form:input id="pickupAddresscounty" path="activity.shipToAddress.county" />
							        <form:input id="pickupAddressofficeNumber" path="activity.shipToAddress.officeNumber" />
							        <form:input id="pickupAddressstateCode" path="activity.shipToAddress.stateCode" />
							        <form:input id="pickupAddressstateFullName" path="activity.shipToAddress.stateFullName" />
							        <form:input id="pickupAddressdistrict" path="activity.shipToAddress.district" />
							        <form:input id="pickupAddressregion" path="activity.shipToAddress.region" />
							        <form:input id="pickupAddresslatitude" path="activity.shipToAddress.latitude" />
							        <form:input id="pickupAddresslongitude" path="activity.shipToAddress.longitude" />
							        <form:input id="pickupAddresscountryISOCode" path="activity.shipToAddress.countryISOCode" />
							        <form:input id="pickupAddresssavedErrorMessage" path="activity.shipToAddress.savedErrorMessage" />
							       <form:input id="pickupAddressisAddressCleansed" path="activity.shipToAddress.isAddressCleansed"/>
							        
									
									</span>
					<%--Added for CI BRD 13-10-26 ENDS --%>
			        <!-- Request Information -->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			            <table class="displayGrid">
						  <thead>
						     <tr><th><spring:message code="claim.label.requestInformation" /></th>
						      </tr>
						  </thead>
						              
						</table>
			               
			            </div>
			            <div class="portlet-wrap-inner">
			            	<div class="width-100per">
				                <div class="columns two">
				                    <div class="first-column">
				                    	<input type="hidden" id = "serviceRequestIdHidden" name="activity.serviceRequest.id" value="${serviceRequestDetailForm.activity.serviceRequest.id}"></input>
										<input type="hidden" name="activity.serviceRequest.serviceRequestNumber" value="${serviceRequestDetailForm.activity.serviceRequest.serviceRequestNumber}"/>
				                        <dl>
				                            <dd>
				                                <table>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.reuqestNumber" /></label></td>
				                                        <td>
				                                           ${serviceRequestDetailForm.activity.serviceRequest.serviceRequestNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.openedDateTime" /></label></td>
				                                        <td>
				                                           <util:dateFormat value="${serviceRequestDetailForm.activity.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${serviceRequestDetailForm.timezoneOffset}" />
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.requestStatus" /></label></td>
				                                        <td>
				                                           ${serviceRequestDetailForm.activity.activityStatus.name}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.statusDetail" /></label></td>
				                                        <td>
				                                           ${serviceRequestDetailForm.activity.activitySubStatus.name}
				                                        </td>
				                                    </tr>
				                                    
				                                </table>
				                            </dd>
				                            
				                        </dl>
				                    </div>
				                    
				                </div>
			                </div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        <!-- Device Information -->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			            <table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.deviceInformation" /></th>
						      </tr>
						  </thead>
						</table>
			               
			            </div>
			            <div class="portlet-wrap-inner">
			            	<div class="width-100per">
				                <div class="columns two">
				                    <div class="first-column">
				                    	<table width="80%" border="0">
												<tr>
													<td  class="labelTD" align="center"><h3>${serviceRequestDetailForm.activity.serviceRequest.asset.productLine}</h3>
												    </td>
												</tr>
												<tr>
												  	<td align="center"><img width="75" src="${serviceRequestDetailForm.activity.serviceRequest.asset.productImageURL}"/></td>
												</tr>
										</table>
				                    </div>
				                    <div class="second-column">
				                        <dl>
				                            <dd>
				                                &nbsp;
				                            </dd>
				                            <dd>
				                                &nbsp;
				                            </dd>
				                            <dd>
				                                <table>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.serialNumber" /></label></td>
				                                        <td>
				                                           ${serviceRequestDetailForm.activity.serviceRequest.asset.serialNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.machineTypeModel" /></label></td>
				                                        <td>
				                                           ${serviceRequestDetailForm.activity.serviceRequest.asset.modelNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.productPN/TLI" /></label></td>
				                                        <td>
				                                           ${serviceRequestDetailForm.activity.serviceRequest.asset.productTLI}
				                                        </td>
				                                    </tr>
				                                </table>
				                            </dd>
				                            
				                        </dl>
				                    </div>
				                </div>
				             </div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        
			         <!-- Service History begin-->
			      	 <%@ include file="/WEB-INF/jsp/request/serviceRequestHistoryList.jsp"%>
			        <!-- Service History end-->
			        
			        <!-- Customer Information begin-->
			        <div id="customerInformation" class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.customerInformation" /></th>
						      </tr>
						  </thead>
						</table>
							
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns three">
									<div class="first-column">
										<table width="100%" class="table-style1">			
											<tr>
												<td></td>
												<td width="20px"/></td>
												<td><u><B><spring:message code="claim.label.customerAccount" /></B></u></td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.name" /></B></td>
												<td width="20px"/></td>
												<td>${serviceRequestDetailForm.activity.customerAccount.accountName}</td>
											</tr>
											<tr>
												<td  align="right" valign="top"><B><spring:message code="claim.label.address" /></B></td>
												<td width="20px"/></td>
												<td valign="bottom">
													<util:addressOutput value="${serviceRequestDetailForm.activity.customerAccount.address}"/>
												</td>
											</tr>
											<tr>
												<td  align="right" valign="top"><B><spring:message code="claim.label.helpDeskReference" /></B></td>
												<td width="20px"/></td>
												<td valign="bottom">${serviceRequestDetailForm.activity.serviceRequest.customerReferenceNumber}</td>
											</tr>
										</table>
									</div>
									<div class="second-column">
										 <table width="90%" class="table-style2">			
											<tr>
												<td></td>
												<td width="20px"/></td>
												<td><u><B><spring:message code="claim.label.primaryContact" /></B></u></td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.name" /></B></td>
												<td width="20px"/></td>
												<td>${serviceRequestDetailForm.activity.serviceRequest.primaryContact.firstName} ${serviceRequestDetailForm.activity.serviceRequest.primaryContact.lastName}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.phone" /></B></td>
												<td width="20px"/></td>
												<td>${serviceRequestDetailForm.activity.serviceRequest.primaryContact.workPhone}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${serviceRequestDetailForm.activity.serviceRequest.primaryContact.alternatePhone}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.email" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${serviceRequestDetailForm.activity.serviceRequest.primaryContact.emailAddress}</td>
											</tr>
										</table>
									</div>
									<div class="third-column">
										<table width="90%" class="table-td-style8">			
											<tr>
												<td></td>
												<td width="20px"/></td>
												<td><u><B><spring:message code="claim.label.secondaryContact" /></B></u></td>
											</tr>
											<tr>
												<td align="right"><B><spring:message code="claim.label.name" /></B></td>
												<td width="20px"/></td>
												<td>${serviceRequestDetailForm.activity.serviceRequest.secondaryContact.firstName} ${serviceRequestDetailForm.activity.serviceRequest.secondaryContact.lastName}</td>
											</tr>
											<tr>
												<td  align="right" class="top-0px"><B><spring:message code="claim.label.phone" /></B></td>
												<td width="20px"/></td>
												<td>${serviceRequestDetailForm.activity.serviceRequest.secondaryContact.workPhone}</td>
											</tr>
											<tr>
												<td  align="right" class="top-0px"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${serviceRequestDetailForm.activity.serviceRequest.secondaryContact.alternatePhone}</td>
											</tr>
											<tr>
												<td  align="right" class="top-0px"><B><spring:message code="claim.label.email" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${serviceRequestDetailForm.activity.serviceRequest.secondaryContact.emailAddress}</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div>
						</div>
					</div>
			        <!-- Customer Information end-->
			        
			        <!-- Problem Information begin-->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			            <table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.problemInformation" /></th>
						      </tr>
						  </thead>
						</table>
			               
			            </div>
			            <div class="portlet-wrap-inner">
			            	<div class="width-100per">
				                <div class="columns two">
				                    <div class="first-column">
				                        <dl>
				                            <dd>
				                                <label><spring:message code="claim.label.problemCode" /></label>
				                                &nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestDetailForm.activity.actualFailureCode.name}
				                            </dd>
				                            <dd>
				                                <label><spring:message code="claim.label.problemDetails" /></label>
				                                &nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestDetailForm.activity.serviceRequest.problemDescription}
				                            </dd>				                            
				                            <c:if test="${(serviceRequestDetailForm.activity.reviewComments != null) && (serviceRequestDetailForm.activity.reviewComments != '')}">
				                            	<dd>
													<label><spring:message code="claim.label.reviewComments" /></label>
				                                	&nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestDetailForm.activity.reviewComments}
			                                	</dd>
											</c:if>
				                        </dl>
				                    </div>
				                </div>
			                </div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        <!-- Problem Information end-->
			        
			        <!-- Service Provider Information begin-->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			            <table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.serviceProviderInformation" /></th>
						      </tr>
						  </thead>
						</table>
			              
			            </div>
			            <div class="portlet-wrap-inner">
			            	<div class="width-100per">
				                <div class="columns">
				                    <div class="first-column width-55per">
				                        <dl>
				                            <dd>
				                                <table class="service_information_width">
				                                    <tr>
				                                        <td nowrap align="right">
				                                            <label><spring:message code="claim.label.customerRequestedResponse" /></label>
				                                        </td>
				                                        <td>
				                                            <input type="text" readonly="readonly" onmouseup="show_cal('customerRequestedResponse', null, null, true);" size="16" id="customerRequestedResponse" name="customerRequestedResponseStr" class="" value="<util:dateFormat value="${serviceRequestDetailForm.activity.customerRequestedResponseDate}" showTime="true" showSecond="false" timezoneOffset="${serviceRequestDetailForm.timezoneOffset}" />">
				                                            <img onclick="show_cal('customerRequestedResponse', null, null, true)"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon image-style1" id="imgCustomerRequestedResponse">
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td nowrap align="right">
				                                            <label><spring:message code="claim.label.estimatedTimeofArrival" /></label>
				                                        </td>
				                                        <td>
				                                           <input type="text" readonly="readonly" onmouseup="show_cal('estimatedTimeofArrival', null, null, true);" size="16" id="estimatedTimeofArrival" name="estimatedTimeofArrivalStr" class="" value="<util:dateFormat value="${serviceRequestDetailForm.activity.estimatedArrivalTime}" showTime="true" showSecond="false" timezoneOffset="${serviceRequestDetailForm.timezoneOffset}" />">
				                                            <img onclick="show_cal('estimatedTimeofArrival', null, null, true)"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon image-style1" id="imgEstimatedTimeofArrival">
				                                        </td>
				                                    </tr>
				                                    
				                                    <tr>
				                                        <td nowrap align="right">
				                                        <label><b><span class="required">* </span><spring:message code="claim.label.status" /><b></label>
				                                         </td>
				                                        <td>
				                                           
				                                           <div id="serviceProvStatusDiv">
				                                           		<select name="activity.serviceProviderStatus" id="serviceproviderstatus">
																
																<option value=""><spring:message code="requestsDetailUpdate.label.SELECTED"/></option>
																<c:forEach items="${serviceRequestDetailForm.activityStatusList}" var="activityStatusEntry" varStatus="loop">
																	<option value="${activityStatusEntry.key}">${activityStatusEntry.value}</option>
																</c:forEach>
															</select>
															</div>
															<script>
															
															var z = dhtmlXComboFromSelect("serviceproviderstatus");
															//z.enableFilteringMode(true);
															z.readonly(true);  //Added for LEX:AIR00065373
															z.setComboText("");	
															//validateStatus function added by sankha for LEX:AIR00068469
															function validateStatus(){		
																var result = true;														
																var substatus = z.getSelectedValue();
																//Added for CI 9790 START
																var text_specialInstruction;
																var length_specialInstruction;
																text_specialInstruction=document.getElementById('activity.servicerComments').value;
																length_specialInstruction=text_specialInstruction.length;
																if(length_specialInstruction>1500){
													    			result = false;
													    			showError("<spring:message code='claim.errorMsg.maxlengthComment'/>",null, true);
													    			}
																//Added for CI 9790 START
																	
																if(!substatus || substatus == ""){
																	result = false;
																	showError("<spring:message code='claim.errorMsg.statusNotNull'/>",null, true);
																	jQuery('#serviceProvStatusDiv').addClass('errorColor_status');
																}
																return result;
															}
															</script>
				                                           			                                            
				                                        </td>
				                                    </tr>
				                                    
				                                    <tr>
				                                        <td nowrap align="right">
				                                            <label><spring:message code="claim.label.statusAsOf" /></label>
				                                        </td>
														<c:choose>
															<c:when test="${serviceRequestDetailForm.activity.statusUpdateDate == null || serviceRequestDetailForm.activity.statusUpdateDate == ''}" >
																<td>
																	<input type="text" readonly="readonly" onmouseup="show_cal('statusAsOf', null, todayStr, true);" size="16" id="statusAsOf" name="statusAsOfStr" class="">
																	<img onclick="show_cal('statusAsOf', null, todayStr, true)"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon image-style1" id="imgStatusAsOf">
																</td>
															</c:when>
															<c:otherwise>
																<td>
																	<input type="text" readonly="readonly" onmouseup="show_cal('statusAsOf1', null, todayStr, true);" size="16" id="statusAsOf1" name="statusAsOfStr" class="" value="<util:dateFormat value="${serviceRequestDetailForm.activity.statusUpdateDate}" showTime="true" showSecond="false" timezoneOffset="${serviceRequestDetailForm.timezoneOffset}" />">
																	<img onclick="show_cal('statusAsOf1', null, todayStr, true);"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon image-style1" id="imgStatusAsOf">
																</td>
															</c:otherwise>
														</c:choose>
				                                    </tr>
													<tr>
														<td nowrap align="right">
															<label><spring:message code="claim.label.serviceProviderReferenceNumber" /></label><br>
														</td>
														<td>
														<input type="text" name="serviceProviderReferenceNumber" value="${serviceRequestDetailForm.activity.serviceProviderReferenceNumber}" onchange="validateLength(0,50,this)">
														</td>
													</tr>
				                                </table>
				                            </dd>
				                        </dl>
				                    </div>
				                    <div class="second-column width-auto">
				                        <table>
				                        	<tr>
				                        		<td align="top">
													<b><label><spring:message code="claim.label.comments" />:</label></b>
												</td>
				                        	</tr>
				                            <tr>
												<td>
													<textarea id="activity.servicerComments" cols="50" rows="6" maxlength="1500"  name="activity.servicerComments" class="activity-servicerComments" >${serviceRequestDetailForm.activity.servicerComments}</textarea>
												</td>
											</tr>
				                        </table>
				                    </div>
				                </div>
				            </div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        
			      <!-- Service Provider Information end-->
					<script type="text/javascript">
						var strNow = localizeFormatDate(new Date(), true);
						var defaultStatusAsOf = strNow.substring(0, strNow.lastIndexOf(':'));
						jQuery("#statusAsOf").val(defaultStatusAsOf);
						
					</script>
					
			        <!-- Technician Information begin-->
			        <div class="portlet-wrap" id="technician_information" >
						<div class="portlet-header">
						<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.technicianInformation"/></th>
						      </tr>
						  </thead>
						</table>
							
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<table class="table-style7">
											<tr>
												<td align="right">
													<label><b><span class="required">* </span><spring:message code="claim.label.technician"/><b></label> 
												</td>
												<td width="10px" />
												<td align="left">
													<select id="technicianChoose" onchange="showTechnicianInputTexts()" name="technicianFullName">
													</select>
												</td>
												<td>
													<span id="technicianInfoLoading" style="display: none;">
														&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
													</span>											
												</td>
											</tr>
											<tr id="technicianFirstNameDD" style="display:none">
												<td align="right">
													<label><b><span class="required">* </span><spring:message code="claim.label.firstName"/></b></label>
												</td>
												<td width="10px" />
												<td align="left">
													<input type="text" id="technicianFirstNameInput" name="activity.technician.firstName" onchange="validateLength(0,50,this)"/>
												</td>
												<td/>
											</tr>
											<tr id="technicianLastNameDD" style="display:none">
												<td align="right">
													<label><b><span class="required">* </span><spring:message code="claim.label.lastName"/></b></label>
												</td>
												<td width="10px" />
												<td align="left">
													<input type="text" id="technicianLastNameInput" name="activity.technician.lastName" onchange="validateLength(0,50,this)"/>
												</td>
												<td/>
											</tr>
										</table>
								</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
					<script type="text/javascript">
						
						var accountId = '${serviceRequestDetailForm.activity.partnerAccount.accountId}';	
						if(accountId){
							//to ajax technician information use partnerAccount Id
							ajaxTechnicianInformation(accountId);
						}else{
							ajaxTechnicianInformation("");
						}
						
						function showTechnicianInputTexts(){
							var value = document.getElementById("technicianChoose").value;
							if(value == "other"){
								document.getElementById("technicianLastNameDD").style.display = "";
								document.getElementById("technicianFirstNameDD").style.display = "";
							}
							else{
								document.getElementById("technicianLastNameDD").style.display = "none";
								document.getElementById("technicianFirstNameDD").style.display = "none";
								document.getElementById("technicianLastNameInput").value = "";
								document.getElementById("technicianFirstNameInput").value = "";
							}
						}
						
						function ajaxTechnicianInformation(accountId){
							//var result = // result from Ajax;
							var url = '<portlet:resourceURL id="setTechnicianInformation"/>';
							url +="&accountId=" + accountId;
							document.getElementById("technicianInfoLoading").style.display = "block";
							doAjax(url, callbackGetTechInfoAccountList, null, null);
						}
			
						function callbackGetTechInfoAccountList(result){
							var mapList = result.data;
							//Alex0, zhou/1-P00910}Alex1, zhou/1-P3YKE1}Alex2, zhou/1-P00912}Alex3, zhou/1-P00913}
							//put the result value into parent page technician choose.
							var valueKey = mapList.split("}");
							var selectObject = document.getElementById('technicianChoose');
							selectObject.options.length=0;
							//clearn all then added
							for (i=0;i<valueKey.length;i++){
								var textValue = valueKey[i].split("/");
								var optionObject = new Option(textValue[0],valueKey[i]);
								var name = textValue[0].split(",");
								var firstName = '${serviceRequestDetailForm.activity.technician.firstName}';
								var lastName = '${serviceRequestDetailForm.activity.technician.lastName}';
								if(textValue[0] == '${serviceRequestDetailForm.activity.technician.lastName}, ${serviceRequestDetailForm.activity.technician.firstName}'){
									optionObject.selected = true;
								}
								selectObject.options.add(optionObject);
							}
							selectObject.remove(selectObject.length-1);
							
							selectObject.options.add(new Option('<spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/>','other'));
							document.getElementById("technicianInfoLoading").style.display = "none";
							return true;
						}
						
			
						function cleanTechnicians(){
							document.getElementById("technicianChoose").value = '';
							document.getElementById("technicianLastNameDD").style.display = "none";
							document.getElementById("technicianFirstNameDD").style.display = "none";
							document.getElementById("technicianLastNameInput").value = "";
							document.getElementById("technicianFirstNameInput").value = "";
						}
			
						function validateTechnicianInfo(){
							var result = true;
							var technician = document.getElementById('technicianChoose').value;
							if(!technician){
								result = false;
								showError("<spring:message code='claim.errorMsg.technicianNotNull'/>",null, true);
								jQuery('#technicianChoose').addClass('errorColor');
							}
							if(document.getElementById("technicianFirstNameDD").style.display == ""){
								if(!(document.getElementById("technicianFirstNameInput").value).trim()){
									result = false;
									showError("<spring:message code='claim.errorMsg.technicianFirstNameNotNull'/>",null, true);
									jQuery('#technicianFirstNameInput').addClass('errorColor');
								}
							}
							if(document.getElementById("technicianLastNameDD").style.display == ""){
								if(!(document.getElementById("technicianLastNameInput").value).trim()){
									result = false;
									showError("<spring:message code='claim.errorMsg.technicianLastNameNotNull'/>",null, true);
									jQuery('#technicianLastNameInput').addClass('errorColor');
								}
							}
							return result;
						}
						
						
						
						
					</script>
			
				
			        <!-- Technician Information end-->
					
			        <!-- Technician Instructions begin-->
			        <c:if test="${serviceRequestDetailForm.technicianInformationListXML != null || not empty serviceRequestDetailForm.activity.activityServiceInstructions}">
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			            <table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.technicianInstructions" /></th>
						      </tr>
						  </thead>
						</table>
			           
			            </div>
			            <div class="portlet-wrap-inner scroll">
			            	<c:if test="${not empty serviceRequestDetailForm.activity.activityServiceInstructions}">
			            		<div><label><H4>${serviceRequestDetailForm.activity.activityServiceInstructions}</H4></label></div>
			            	</c:if>
			            	<c:if test="${serviceRequestDetailForm.technicianInformationListXML != null}">
				            	<div id="divTechnicianInstructions">
				                	<div id="gridRDUTechnicianInstruction" class="width-100per"></div>
				                	<div id="technicianLoadingNotification" class="gridLoading" style="display: none;">
				                    	<br><!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
				                	</div>
				                </div>
			                </c:if>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			        </c:if>
			        <c:if test="${serviceRequestDetailForm.technicianInformationListXML != null}">
			        <script type="text/javascript">
			            //technician grid
		            	technicianGrid = new dhtmlXGridObject('gridRDUTechnicianInstruction');
		                technicianGrid.setImagePath("<html:imagesPath/>gridImgs/");
		                technicianGrid.setHeader(autoAppendPlaceHolder('<spring:message code="request.headerList.technicianInstructions"/>',3));
		                technicianGrid.setColTypes("ro,ro,ro");
		                technicianGrid.setColSorting("str,str,str");
		                technicianGrid.setInitWidths("140,140,*");
		    			
		                technicianGrid.setColAlign("left,left,left");
		                technicianGrid.enableAutoWidth(true);
		                technicianGrid.enableAutoHeight(true);
		                technicianGrid.enableMultiline(true);
		                technicianGrid.init();
		                technicianGrid.setSkin("light");
		                technicianGrid.setPagingSkin("bricks");
		                technicianGrid.loadXMLString('${serviceRequestDetailForm.technicianInformationListXML}');
			        </script>
			        </c:if>
			        <!-- Technician Instructions end-->
			        
			        <!-- Ordered Parts begin-->
			        <c:if test="${serviceRequestDetailForm.orderPartsXML != null || serviceRequestDetailForm.orderPartsXML ==''}">			
						<div class="portlet-wrap">
							<div class="portlet-header">
							<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.ordered.parts" /></th>
						      </tr>
						  </thead>
						</table>
				            
				            </div>
				         	<div class="portlet-wrap-inner scroll">
								<div id="gridRDVOrderedPartsTable" class="gridbox_light width-95per" ></div>
								<div class="pagination" id="paginationId"><span id="gridRDVOrderedPartsTablePagingArea"></span>&nbsp;<span id="gridRDVOrderedPartsTableInfoArea"></span></div><!-- mygrid_container -->
							</div>
							<div class="portlet-footer">
			                	<div class="portlet-footer-inner">
			                	</div><!-- portlet-footer-inner -->
			            	</div>
						</div>
						<script type="text/javascript">
							//jQuery(document).ready(function(){
								var gridRDVOrderedPartsTable = new dhtmlXGridObject("gridRDVOrderedPartsTable");
								gridRDVOrderedPartsTable.setImagePath("<html:imagesPath/>gridImgs/");
								gridRDVOrderedPartsTable.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.orderedParts.detail"/>',12));
								gridRDVOrderedPartsTable.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left");
								gridRDVOrderedPartsTable.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
								gridRDVOrderedPartsTable.setColSorting("na,str,str,str,str,str,str,str,str,str,str,str,str");
								gridRDVOrderedPartsTable.setLockColVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false");
								//Changes for CI7 BRD 14-06-07
								gridRDVOrderedPartsTable.setInitWidths("20,80,50,80,120,150,100,120,80,100,100,120,0");
								//gridRDVOrderedPartsTable.enableAutoWidth(true);
								gridRDVOrderedPartsTable.enableAutoHeight(true);
								gridRDVOrderedPartsTable.enableMultiline(true);
								gridRDVOrderedPartsTable.enablePaging(true, 5, 10, "gridRDVOrderedPartsTablePagingArea", true, "gridRDVOrderedPartsTableInfoArea");
								gridRDVOrderedPartsTable.init();
								gridRDVOrderedPartsTable.setSkin("light");
								// move enabled for CI 14.10 CRM-DAmster201408041344
								gridRDVOrderedPartsTable.enableColumnMove(true);	
								gridRDVOrderedPartsTable.setPagingSkin("bricks");
								gridRDVOrderedPartsTable.setColumnHidden(12,true);	
								gridRDVOrderedPartsTable.loadXMLString("${serviceRequestDetailForm.orderPartsXML}");
								//gridRDVOrderedPartsTable.setColumnHidden(1,true);
								
								// changes for db saving and retrieving	start
								//gridRDVOrderedPartsTable.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
								<c:if test="${gridSettingVar.gridId == 'gridRDVOrderedPartsTable'}">					
								var colsOrder = "${gridSettingVar.colsOrder}";
								gridRDVOrderedPartsTable.loadOrder(colsOrder);
							
								</c:if>
								</c:forEach>
								gridRDVOrderedPartsTable.attachEvent("onAfterCMove",function(a,b){								
								gridRDVOrderedPartsTable.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								//});	
								});	
								
								// changes for db saving and retrieving	end
													
								var totalRows=gridRDVOrderedPartsTable.getRowsNum();
								 
								for(var j=0;j<totalRows;j++){
									var cellObj = gridRDVOrderedPartsTable.cellById(i,12);
									//alert("in for "+totalRows+" j value is "+j);
										var cellvalue=cellObj.getValue();
										shipToaddrFlag[j]=cellvalue;
										//alert("cellvalue "+cellvalue);
										//if(shipToaddrFlag[j]=="true"){
											//expand();
								        // }								
								}
								
								//function expand() {
									
									//var index_sub_row=0;
									//gridRDVOrderedPartsTable.forEachRow(function(id){
									//	if(true)
									//		gridRDVOrderedPartsTable.cellById(0,index_sub_row).open();
									//});
									
								//};
								
							//});
						</script>
					</c:if>
			        <!-- Ordered Parts end-->
			        
			        <!-- Parts to Be Returned begin-->
			        <c:if test="${serviceRequestDetailForm.returnPartsXML != null}">
				        <div class="portlet-wrap">
				        	<div class="portlet-header">
				        	<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.parts.to.be.returned" /></th>
						      </tr>
						  </thead>
						</table>
				                
				            </div>
								<div class="portlet-wrap-inner">
									<div class="width-100per">
										<div id="gridRDVbeReturnedParts"  class="width-100per"></div>
										<div class="pagination" id="paginationReturnedPartsId"><span id="gridRDVbeReturnedPartsPagingArea"></span>&nbsp;<span id="gridRDVbeReturnedPartsRecinfoArea"></span></div><!-- mygrid_container -->
									</div>
								</div>
								<div class="portlet-footer">
				                	<div class="portlet-footer-inner">
				                	</div><!-- portlet-footer-inner -->
				            	</div>
							</div>
							
						<script type="text/javascript">
							//jQuery(document).ready(function() {
								var gridRDVbeReturnedPartsGrid = new dhtmlXGridObject('gridRDVbeReturnedParts');
								gridRDVbeReturnedPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
								gridRDVbeReturnedPartsGrid.setHeader(autoAppendPlaceHolder(' ,<spring:message code="claim.headerList.partsToBeReturned"/>',9));
								gridRDVbeReturnedPartsGrid.setColAlign("left,left,left,left,left,left,left,left,left");
								gridRDVbeReturnedPartsGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro");
								gridRDVbeReturnedPartsGrid.setColSorting("na,str,str,str,str,str,str,str,str");
								gridRDVbeReturnedPartsGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false");
								gridRDVbeReturnedPartsGrid.setInitWidths("20,50,120,120,120,120,120,120,120");
								gridRDVbeReturnedPartsGrid.enableAutoWidth(true);
								gridRDVbeReturnedPartsGrid.enableAutoHeight(true);
								gridRDVbeReturnedPartsGrid.enableMultiline(true);
								gridRDVbeReturnedPartsGrid.enablePaging(true, 5, 10, "gridRDVbeReturnedPartsPagingArea", true, "gridRDVbeReturnedPartsRecinfoArea");
								gridRDVbeReturnedPartsGrid.init();
								gridRDVbeReturnedPartsGrid.setSkin("light");
								// move enabled for CI 14.10 CRM-DAmster201408041344
								gridRDVbeReturnedPartsGrid.enableColumnMove(true);
								gridRDVbeReturnedPartsGrid.setPagingSkin("bricks");
								gridRDVbeReturnedPartsGrid.loadXMLString("${serviceRequestDetailForm.returnPartsXML}");

								// changes for db saving and retrieving	start
								//gridRDVbeReturnedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								gridRDVbeReturnedPartsGrid.attachEvent("onAfterCMove",function(a,b){								
								gridRDVbeReturnedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
								//});	
								});	
								<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
								<c:if test="${gridSettingVar.gridId == 'gridRDVbeReturnedParts'}">					
								var colsOrder = "${gridSettingVar.colsOrder}";
								gridRDVbeReturnedPartsGrid.loadOrder(colsOrder);
							
								</c:if>
								</c:forEach>
								// changes for db saving and retrieving	end

							//});
						</script>
					</c:if>
			        <!-- Parts to Be Returned end-->
			        
			       <!-- Part and Address Start -->
					
					<div class="portlet-wrap">
						<div class="portlet-header">
						<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.recommendedParts" /></th>
						      </tr>
						  </thead>
						</table>
							
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div id="qtyValidate" class="qty-validate"></div>
							<div class="columns scroll">
								<div id="qtyValidate" class="qty-validate"></div>
								<div id="gridRDURecommendPart" class="width-100per"></div>
								<div class="pagination" id="pagingAreaRecommendPart"><span id="pagingAreaRecommend"></span>&nbsp;<span id="pagingInfoAreaRecommend"></span></div>
							</div>
							<%-- Added for LEX:AIR00066994 start--%>
							
							<%-- Added for LEX:AIR00066994 end --%>
							<c:if test="${serviceRequestDetailForm.activity.partnerAccount.orderPartsFlag}">
								<div id="addPartSection">
									<table>
										<tr>
											<td>
												<b><label><spring:message code="claim.label.addParttoOrder"/>:</label></b>
											</td>
										</tr>
										<tr>
											<td>
												<input class="text" type="text" id="partNumber"/>&nbsp;&nbsp;
												<a href="javascript:searchPart(document.getElementById('partNumber').value);" class="button" id="partSearchButton"><span><spring:message code="claim.button.addPart"/></span></a>&nbsp;&nbsp;
												<span id="showPartsListSpan"><a id="showPartsListLink" href="javascript:void(0);" onclick="showPartList(); return false;"><spring:message code="claim.label.partsList"/></a></span>
											</td>
											<td>
												<label id="partSearchLoading" style="display: none;">
												&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
												</label>
											</td>
										</tr>
									</table>
								</div>
							</c:if>
							<br>
							<label for="activity.shipToAddress.addressId" class="error"
							style="display: none;"><spring:message code="claim.errorMsg.partnerAddress"/></label>
							<div id="partnerAddressDiv">
								<table>
									<tr>
										<td valign="top" align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;<b><spring:message code="claim.label.shipTo"/></b>
										</td>

							<td>
							<!--Added for CI Defect #9183 STARTS-->
 							
 							<table id="addressInfo" name="addressInfo" >
								<tr><td><label id="addressNameLabel" style="display:none;"></label></td></tr>
								<tr><td><label id="pickupStoreFrontNameLbl" style="display:none;"></label></td></tr>
								<tr><td><label id="addressLine1Label" style="display:none;"></label></td></tr>
								<tr><td><label id="pickupAddressofficeNumberLbl" style="display:none;"></label></td></tr>
								<tr><td><label id="addressLine2Label" style="display:none;"></label></td></tr>
								<tr>
									<td>
										<label id="cityLabel" style="display:none;"></label>
										<label id="pickupAddresscountyLbl" style="display:none;"></label>
										<label id="stateLabel" style="display:none;"></label> 
										<label id="provinceLabel" style="display:none;"></label>
										<label id="pickupAddressRegionLB" style="display:none;"></label>
										
									</td>
								</tr>
								<tr><td><label id="postalCodeLabel" style="display:none;"></label><br></td></tr>
								<tr><td><label id="countryLabel" style="display:none;"></label><br></td></tr>	
										
									
								
								<tr align="left">
									<td style="display: none">
  										<form:input path="activity.shipToAddress.addressId" />
										<form:input path="activity.shipToAddress.addressName" />
										<form:input path="activity.shipToAddress.storeFrontName" /> 
										<form:input path="activity.shipToAddress.addressLine1" /> 
										<form:input path="activity.shipToAddress.officeNumber" /> 
										<form:input path="activity.shipToAddress.addressLine2" /> 
										<form:input path="activity.shipToAddress.city" /> 
										<form:input path="activity.shipToAddress.county" />
										<form:input path="activity.shipToAddress.state" /> 
										<form:input path="activity.shipToAddress.province" /> 
										<form:input path="activity.shipToAddress.region" />
										<form:input path="activity.shipToAddress.country" /> 
										<form:input path="activity.shipToAddress.postalCode" />
										<form:input path="activity.shipToAddress.newAddressFlag" />
										
									
									</td>
								</tr>
							</table>
 							<!--Added for CI Defect #9183 ENDS-->
						</td>

									</tr>
									<tr id="selectOtherAddress">
										<%--Done for CI BRD13-10-26 STARTS --%>
										<td colspan="2"><a href="javascript:void(0);" id="installedAddressLink" onClick="return popupAddress(id);">
											<spring:message code="claim.label.selectAddress"/></a>
										</td>
										<%--Done for CI BRD13-10-26 ENDS --%>
									</tr>
								</table>
								</div>
<!-- 							 address list start		 -->
<!-- 							<div id="partnerAddressList" class="scroll" style="display:none;"> -->
<!-- 							<table width="100%"> -->
<!-- 							<tr> -->
<!-- 								<td class="contactsTD"> -->
<!-- 								<a href="javascript:switchToAllAddresses();" class="button" id="accountAddressesBtn"> -->
<%-- 									<span><spring:message code="claim.button.allAccountAddresses"/></span> --%>
<!-- 								</a>&nbsp; -->
<!-- 								<a href="javascript:switchToMyAddresses();" id="myFavoriteAddressesBtn" class="selected_fav_button_anchor" style="background-Color:#6495ED;"> -->
<%-- 									<span><spring:message code="claim.button.myAddresses"/></span> --%>
<!-- 								</a> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 							<tr><td>&nbsp;</td></tr> -->
<!-- 							<tr> -->
<!-- 								<td> -->
<!-- 								<div id="gridCCVAllPartnerAddress" -->
<!-- 									style="width:100%; height: 150px; background-color: white;display:none;"></div> -->
<%-- 								<div id="loadingNotificationAll" class="gridLoading" style=" display:none;"><!--spring:message code="label.loadingNotification"/-->&nbsp;<img --%>
<%-- 									src="<html:imagesPath/>gridloading.gif" /><br> --%>
<!-- 								</div> -->
<!-- 								<div id="pagingAreaAll"></div> -->
<!-- 								<div id="gridCCVMyPartnerAddress" style="width:100%; height: 150px; background-color: white;"></div> -->
<!-- 								<div id="loadingNotificationMy" class="gridLoading"> -->
<%-- 									<!--spring:message code="label.loadingNotification"/-->&nbsp; --%>
<%-- 									<img src="<html:imagesPath/>gridloading.gif" /><br> --%>
<!-- 								</div> -->
<!-- 								<div id="pagingAreaMy"></div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<%-- 							<c:if test="${serviceRequestDetailForm.activity.partnerAccount.createShipToAddressFlag}"> --%>
<!-- 								<tr> -->
<!-- 									<td align="left"> -->
<%-- 										<a href="javascript:popupCreateNewAddressPage();" class="button" id="createSecondaryBtn" ><span><spring:message code="claim.button.createNew"/></span></a> --%>
<!-- 									</td> -->
<!-- 								</tr> -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${serviceRequestDetailForm.activity.partnerAccount.createShipToAddressFlag!=true}"> --%>
<!-- 								<tr> -->
<!-- 									<td align="left"> -->
<%-- 										<label><spring:message code="claim.label.canNotCreateShipToAddress"/></label> --%>
<!-- 									</td> -->
<!-- 								</tr> -->
<%-- 							</c:if> --%>
<!-- 						</table> -->
<!-- 						</div> address list end -->
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
						
					</div>
					<script type="text/javascript">
					showNotesFunction();
					var showNotes;
					function showNotesFunction(){
					AUI().use('aui-base',
					'aui-io-plugin-deprecated',
					'liferay-util-window',
					function(A) {

						showNotes=Liferay.Util.Window.getWindow(
						{
						dialog: {
						centered: true,
						constrain2view: true,
						//cssClass: 'yourCSSclassName',
						modal: true,
						position: [-80,20],
						resizable: false,
						width: 650,
						height: 400,
						xy: [-80, 20],
						destroyOnClose: true,
						visible: false
						}
						}
						).plug(
						A.Plugin.IO,
						{
						autoLoad: false
						})



					});
					}
					partListFunction();
					var partListPopUpWindow;
					function partListFunction(){
					AUI().use('aui-base',
					'aui-io-plugin-deprecated',
					'liferay-util-window',
					function(A) {

						partListPopUpWindow=Liferay.Util.Window.getWindow(
						{
						dialog: {
						centered: true,
						constrain2view: true,
						//cssClass: 'yourCSSclassName',
						modal: true,
						position: [-80,20],
						resizable: false,
						width: 950,
						height: 550,
						xy: [-80, 20],
						destroyOnClose: true,
						visible: false
						}
						}
						).plug(
						A.Plugin.IO,
						{
						autoLoad: false
						})



					});
					}
					url="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
		         	"<portlet:param name='isMaintenance' value='false' />"+
		         	"<portlet:param name='action' value='showPartAndToolPage' /></portlet:renderURL>";
		         	function partListPopup(){
									jQuery(window).scrollTop(0);
									partListPopUpWindow.show();
									jQuery(".aui button.close, .aui button.btn.close").hide();
									partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/>");
									partListPopUpWindow.io.set('uri',url);
									partListPopUpWindow.io.start();
									
									}
						var isPartnerAddressGridLoaded = false;
						var isPageLoaded = false;
						var isChooseAdifferentAddress = true;
						var preOption = 0;
						var modelNumber="${serviceRequestDetailForm.activity.serviceRequest.asset.modelNumber}";
						
						var favStatus = {};
						var qtyList = new Array();
						for(b = 1;b<=5;b++){
							qtyList[b] = b;
						}
						var recommendGrid = new dhtmlXGridObject('gridRDURecommendPart');
						recommendGrid.setImagePath("<html:imagesPath/>gridImgs/");
						recommendGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.requestUpdate.recommendedPart"/>',7));
						recommendGrid.setColAlign("left,left,left,left,left,left,left");
						recommendGrid.setInitWidths("200,200,200,200,0,20,0");
						recommendGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
						recommendGrid.setColSorting("na,str,str,str,na,na,na");
						recommendGrid.enableAutoWidth(true);
						recommendGrid.enableAutoHeight(true);
						recommendGrid.enableMultiline(true);
						recommendGrid.init();
						//added by ragesree
						recommendGrid.prftInit();
						recommendGrid.setSizes();
						recommendGrid.setColumnHidden(4,true);
						recommendGrid.setColumnHidden(6,true);
						//*********************//
						if("${serviceRequestDetailForm.activity.addressStatus}"=="${serviceRequestDetailForm.addressStatusList[0]}"){
							recommendGrid.setColumnHidden(0,true);
							recommendGrid.setColumnHidden(4,true);
							<c:if test="${serviceRequestDetailForm.activity.partnerAccount.orderPartsFlag}">
							document.getElementById("addPartSection").style.display = "none";
							</c:if>
						}
						recommendGrid.setSkin("light");
						recommendGrid.enablePaging(true, 9999, 6, "pagingAreaRecommend", true,"pagingInfoAreaRecommend");
						recommendGrid.setPagingSkin("bricks");
						recommendGrid.loadXMLString('${serviceRequestDetailForm.recommendedPartsXML}');

						// changes for db saving and retrieving	start
						//recommendGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
						<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
						<c:if test="${gridSettingVar.gridId == 'gridRDURecommendPart'}">					
						var colsOrder = "${gridSettingVar.colsOrder}";
						recommendGrid.loadOrder(colsOrder);
					
						</c:if>
						</c:forEach>
						recommendGrid.attachEvent("onAfterCMove",function(a,b){						
						recommendGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
						//});	
						});	
						
						// changes for db saving and retrieving	end
						
						recommendGrid.attachEvent("onRowAdded", function(rId){
							document.getElementById("partNumber").value = "";
						}); 
						recommendGrid.attachEvent("onAfterRowDeleted", function(id,pid){
							document.getElementById("partNumber").value = "";
			     		});
								 
						/*********************************************************************************
						******************************Partner address grid script*************************
						**********************************************************************************/
						
						
						
						function searchPart(partNumber){
							
							var partNum = partNumber.replace(/(^\s*)|(\s*$)/g, "");
							if(!document.getElementById("partListDiv")){
								document.getElementById("partNumber").value = partNum;
							}
							if(partNum=="" || partNum==null){
								jAlert('<spring:message code="claim.message.pleaseEnterAPartNumber"/>', "");
								return;
								}
							document.getElementById("partSearchButton").disabled = true;
							document.getElementById("partSearchLoading").style.display = "block";
							
							var url = '<portlet:resourceURL id="retrievePart"/>';
							url+="&partNumber="+partNumber+"&modelNumber="+modelNumber+"&organizationId=${serviceRequestDetailForm.activity.partnerAccount.organizationID}"+"&isMaintenance=false";
							doAjax(url,callbackGetResult,callbackGetFailureResult,null);
						}
						function callbackGetResult(result) {
							document.getElementById("partSearchButton").disabled = false;
							document.getElementById("partSearchLoading").style.display = "none";
							var data = result.data?result.data:"";
							if(document.getElementById("partListDiv")){
								jQuery(window).scrollTop(0);
								partListPopUpWindow.destroy();
								partListFunction();
							}
							if(!result.data){
								jAlert('<spring:message code="claim.message.noPartFound"/>', "");
								return true;
							}
							var strs = data.split("///");
							addRowByPart(strs);
							return true;
						}
						function callbackGetFailureResult(result){
							document.getElementById("partSearchButton").disabled = false;
							document.getElementById("partSearchLoading").style.display = "none";
							if(document.getElementById("partListDiv")){
								jQuery(window).scrollTop(0);
								partListPopUpWindow.destroy();
								partListFunction();
							}
							if(!result.data){
								jAlert('<spring:message code="claim.message.noPartFoundModified"/>', "");
								return true;
							}
							return false;
						}
						
						function addRowByPartList(part){
							document.getElementById("partListGridDiv").style.display = "none";
							document.getElementById("partListLoadingDiv").style.display = "";
							
							searchPart(part[0]);	
						}
						
						function deletePartRow(ind){
							jConfirm("<spring:message code='claim.message.removePart'/>", "", function(confirmResult) {
								if(!confirmResult){
									return;
								}else{
									recommendGrid.deleteRow(ind);
									showMessageForQty("",true);
								}
							});
						}
						function saveOption(ele){
							preOption = ele.value;
						}
						function selectQty(ele){
							if(parseInt(ele.value)>1){
								jAlert("<spring:message code='claim.message.qtyIsGreaterThan1'/>", "");
								showMessageForQty("<spring:message code='claim.message.qtyIsGreaterThan5'/>",false);
							}else{
								showMessageForQty("<spring:message code='claim.message.qtyIsGreaterThan5'/>",false);
							}
						}
						function showMessageForQty(msg,isDelete){
							var isMoreThan5 = checkTotalQTY();
							if(!isDelete){
								if(isMoreThan5)
									document.getElementById("qtyValidate").innerHTML = msg;
								else 
									document.getElementById("qtyValidate").innerHTML = "";
							}else{
								if(!isMoreThan5)
									document.getElementById("qtyValidate").innerHTML = "";
							}
						}
						function checkTotalQTY(){
							var grid = recommendGrid;
							var rowNumber = grid.getRowsNum();
							var totalQty=0;
							if(rowNumber!=0){
								for(i = 0;i< rowNumber ;i++){
									var ind = grid.getRowId(i);
									var qtyValue = document.getElementById("qty_"+ind).value;
									totalQty=parseInt(totalQty)+parseInt(qtyValue);	
								}
								if(parseInt(totalQty)>5)
									return true;
							}
							return false;
						}
						function countQTYSum(){
							var grid = recommendGrid;
							var rowNumber = grid.getRowsNum();
							var totalQty=0;
							if(rowNumber!=0){
								for(i = 0;i< rowNumber ;i++){
									var ind = grid.getRowId(i);
									var qtyValue = document.getElementById("qty_"+ind).value;
									totalQty=parseInt(totalQty)+parseInt(qtyValue);	
								}
							}
							
							return totalQty;
						}
						
						function showPartList(){
							showOverlayIE6();
							partListPopup();
							
							bindingClosePopupIE6();
						}
						function addRowByPart(part){
							
								var rowNumber = recommendGrid.getRowsNum();
								if(rowNumber!=0){
									for(i = 0;i< rowNumber ;i++){
										var parNum = recommendGrid.cellByIndex(i,2).cell.innerHTML;
										if(parNum==part[0]){
											if(part[3]!="null" && part[3]!=null && part[3]!= "" ){
												var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0]+'. <spring:message code="claim.message.partIsExist"/>';
												jAlert(replaceMessage, "");
												return;
											}
											jAlert('<spring:message code="claim.message.partIsExist"/>', "");
											return;
										}
									}
								}
								if(part[3]!="null" && part[3]!=null && part[3]!= ""){
									var replaceMessage = '<spring:message code="claim.message.partNumber"/>&nbsp;' +part[3]+'&nbsp;<spring:message code="claim.message.rePlacePartNumber"/>&nbsp;' +part[0];
									jAlert(replaceMessage, "");
								}
								var rowNum = recommendGrid.getRowsNum();
								var id = part[4];
								var str =['<table><tr><td><select id="qty_'+id+'" onchange=\"selectQty(this);\" onclick=\"saveOption(this);\">'+generateOption(qtyList)+'</select></td></tr></table>','',part[0],part[1],(part[2]=="true"?'<spring:message code="claim.lebel.returnRequired.yes"/>':'<spring:message code="claim.lebel.returnRequired.no"/>'),'<a href="javascript:deletePartRow(\''+id+'\');"><img class="ui-icon trash-icon" src="<html:imagesPath/>transparent.png"></a><input id="newPartLineItemFlag_'+id+'" type="hidden" value="true"/>',''];
								recommendGrid.addRow(id,str,0);
							showMessageForQty("<spring:message code='claim.message.addRowQtyIsGreaterThan5'/>",false);
							
						}
						function generateOption(arr){
							var optionStr="";
							for(var i = 0;i<arr.length;i++){
								if(arr[i]==null)
									continue;
								optionStr +='<option value="'+i+'">'+arr[i]+'</option>';
							}
							return optionStr;
						}
						function switchToAllAddresses(){			
							  document.getElementById("gridCCVAllPartnerAddress").style.display="block";
							  document.getElementById("gridCCVMyPartnerAddress").style.display="none";
							  document.getElementById("loadingNotificationMy").style.display="none";
							  document.getElementById("pagingAreaAll").style.display="block";
							  document.getElementById("pagingAreaMy").style.display="none";
							  changeMyColor('accountAddressesBtn');
							  if(isPartnerAddressGridLoaded==false){
								  allPartnerAddressGrid.clearAndLoad("${serviceRequestDetailForm.partnerAddressListURL}&accountId=${serviceRequestDetailForm.activity.partnerAccount.accountId}&favoriteFlag=false");		
							  	isPartnerAddressGridLoaded=true;
							  }	  
						}
						
						
						function chooseADifferentAddress(){
							//callOmnitureAction('Requests Detail Update View', 'Choose A Different Address');
							document.getElementById("activity.shipToAddress.addressId").value="";
							if(!isChooseAdifferentAddress){
								document.getElementById('partnerAddressList').style.display = 'block';
								document.getElementById("partnerAddressDiv").style.display="none";
								switchToMyAddresses();
								return;
							}
							//myPartnerAddressGrid.clearAndLoad("${serviceRequestDetailForm.partnerAddressListURL}&accountId=${serviceRequestDetailForm.activity.partnerAccount.accountId}&favoriteFlag=true");
						}
						
						function clearAddress(){
							document.getElementById("activity.shipToAddress.addressId").value="";
							document.getElementById("addressNameLabel").innerHTML="";
							document.getElementById("addressInfo").style.display="none";
						}
						//change color for selected button
						function changeMyColor(btnName){
							  btnEle = document.getElementById(btnName);
							  if(btnEle.id=="myFavoriteAddressesBtn"){
								  addClass(btnEle, "selected_fav_button_anchor");
								  removeClass(btnEle, "not_selected_fav_button_anchor");
								  //addClass(document.getElementById("accountAddressesBtn"), "not_selected_button_anchor");
								  //removeClass(document.getElementById("accountAddressesBtn"), "selected_button_anchor");
							  }
							  if(btnEle.id=="accountAddressesBtn"){
								  //addClass(btnEle, "selected_button_anchor");
								  //removeClass(btnEle, "not_selected_button_anchor");
								  addClass(document.getElementById("myFavoriteAddressesBtn"), "not_selected_fav_button_anchor");
								  removeClass(document.getElementById("myFavoriteAddressesBtn"), "selected_fav_button_anchor");
							  }	  
						}
						
						//the next three functions are used to add/remove classes on elements
						function hasClass(ele,cls) {
							return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
						}
						function addClass(ele,cls) {
							if (!this.hasClass(ele,cls)) ele.className += " "+cls;
						}
						function removeClass(ele,cls) {
							if (hasClass(ele,cls)) {
								var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
								ele.className=ele.className.replace(reg,' ');
							}
						}
			
						//create a new address
						
	var addressPopUpWindow;
	AUI().use('aui-base',
	'aui-io-plugin-deprecated',
	'liferay-util-window',
	function(A) {

		addressPopUpWindow=Liferay.Util.Window.getWindow(
		{
		dialog: {
			centered: true,
			constrain2view: true,
			//cssClass: 'yourCSSclassName',
			modal: true,
			position: [-80,20],
			resizable: false,
			width: 650,
			height: 350,
			xy: [-80, 20],
			destroyOnClose: true,
			visible: false
		}
		}
		).plug(
		A.Plugin.IO,
		{
		autoLoad: false
		})



	});
						
						function popupCreateNewAddressPage(){
							//callOmnitureAction('Requests Detail Update View', 'Create New Address PopUp');
							showOverlayIE6();
							addressPopUpWindow.show();
							jQuery(".aui button.close, .aui button.btn.close").hide();
							addressPopUpWindow.titleNode.html("<spring:message code='claim.label.createNewAddress'/>");
							addressPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='showCreateNewAddressPage' /></portlet:renderURL>");
							addressPopUpWindow.io.start();
							
							
							<%--new Liferay.Popup({
								title: "<spring:message code='claim.label.createNewAddress'/>",
								position: [450,50], 
								modal: true,
								width: 600, 
								height: 'auto',
								xy: [100, 100],
								onClose: function() {showSelect();},
								url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
									"<portlet:param name='action' value='showCreateNewAddressPage' /></portlet:renderURL>"
								});--%>
							bindingClosePopupIE6();
						};
						
						//Added for CI7 Address Pop Up Issue
						function addPartnerAddressElement(ai,storeName,currentFavStatus, 
								  an, al1, offcNo, al2,city,county, state, province,region,country, zip,
								  physicalLoc1,physicalLoc2,physicalLoc3,district,countryISO,stateCode){
							//callOmnitureAction('Create Claim Device Selection', 'Add A Partner Address Element');
							if(favStatus[ai]){  		
								currentFavStatus = favStatus[ai].isFavorite? "true":"false";
							}
							if(ai==null || ai=="")
								jQuery('#pickupAddressisAddressCleansed').val("false");
							else
								jQuery('#pickupAddressisAddressCleansed').val("true");
							//we will not mark that address as favorite if it is already a favorite one.
							if(isPageLoaded == true && currentFavStatus == "false")
								setPartnerAddressFavourite(ai,true); 
							if(isPageLoaded == true){
								//manually triger the jquery validation framework.
								//jQuery("#createClaimRequestForm").validate().element("#activity\\.shipToAddress\\.addressId");
							}
							isChooseAdifferentAddress = true;
							document.getElementById("partnerAddressDiv").style.display="block";
							document.getElementById("addressInfo").style.display="block";
				 			addPickupAddress(ai,storeName,currentFavStatus, 
									  an, al1, offcNo, al2,city,county, state, province,region,country, zip,
									  physicalLoc1,physicalLoc2,physicalLoc3,district,countryISO,stateCode);
				 			closeDialog();
				 			
						}
						
						// Added by Arunava for LEX:AIR00074206 Started
						function addPartnerAddressElementPopup(addressId, addressName, addressLine1, addressLine2, addressLine3, addressCity, addressState, addressProvince, nummValue , addressCountry, addressPostCode, fav){
							addPartnerAddressElement(addressId, addressName, addressLine1, addressLine2, "", addressCity, addressState, addressProvince, null , addressCountry, addressPostCode, fav);
						}
						// Added by Arunava for LEX:AIR00074206 Ended
						
						function setPartnerAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag) {
							var url = '<portlet:resourceURL id="setPartnerAddressFavouriteFlag"/>';
						    url += "&favoriteAddressId="+favoriteAddressId;
						    url += "&accountId=${serviceRequestDetailForm.activity.partnerAccount.accountId}";
						
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
						    var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
						    favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
						    starImg.src = "<html:imagesPath/>loading-icon.gif";
						    url += "&flagStatus=" + isFavorite;
						    
						    doAjax(url, callbackGetPartnerAddressResult, callbackGetPartnerAddressFailure, "");
						
						}
						//call back, when successfully update favorite status of service address
						function callbackGetPartnerAddressResult(result) {
							var addressId = result.data;
							if (document.getElementById("gridCCVMyPartnerAddress").style.display!="none") {		
								var starImg = document.getElementById('starImg_address_' + addressId);
								if (favStatus[addressId].isFavorite) {
									starImg.src = "<html:imagesPath/>unfavorite.png";
								} else {
									starImg.src = "<html:imagesPath/>favorite.png";
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
						function callbackGetPartnerAddressFailure(result) {
						    var addressId = result.data;
						    if (document.getElementById("gridCCVMyPartnerAddress").style.display!="none") {      
						        var starImg = document.getElementById('starImg_address_' + addressId);
						        if (favStatus[addressId].isFavorite) {
						            starImg.src = "<html:imagesPath/>favorite.png";
						        } else {
						            starImg.src = "<html:imagesPath/>unfavorite.png";
						        }
						    }
						    favStatus[addressId].isLoading = false;
						}
						
						
			
						function generatePartAndTool(){
							var date = new Date();
							var rows = recommendGrid.getRowsNum();
							for(var i = 0 ;i < rows ;i++){
								var ind = recommendGrid.getRowId(i);
								var qtyValue = document.getElementById("qty_"+ind).value;
								var reRequire = recommendGrid.cellByIndex(i,4).cell.innerHTML;
								var partNumber = recommendGrid.cellByIndex(i,2).cell.innerHTML;
								var partName = recommendGrid.cellByIndex(i,3).cell.innerHTML;
								var reQuantity = recommendGrid.cellByIndex(i,1).cell.innerHTML;
			                   
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partId"; 
								input.name = "activity.recommendedPartList["+i+"].partId"; 
								input.value = ind;
								document.getElementById("contentPart").appendChild(input);
                              
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partLineItemId"; 
								input.name = "activity.recommendedPartList["+i+"].partLineItemId"; 
								input.value = recommendGrid.cellByIndex(i,6).cell.innerHTML=="&nbsp;"?"":recommendGrid.cellByIndex(i,6).cell.innerHTML;
								document.getElementById("contentPart").appendChild(input);
								  
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partOrderedDate"; 
								input.name = "activity.recommendedPartList["+i+"].partOrderedDate"; 
								input.value = date;
								document.getElementById("contentPart").appendChild(input);
								 
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].quantity"; 
								input.name = "activity.recommendedPartList["+i+"].quantity"; 
								input.value = parseInt(qtyValue);
								document.getElementById("contentPart").appendChild(input);
								 
								if(reQuantity != "&nbsp;"){
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].recommendedQuantity"; 
									input.name = "activity.recommendedPartList["+i+"].recommendedQuantity"; 
									input.value = reQuantity;
									document.getElementById("contentPart").appendChild(input);
								}
								  
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partNumber"; 
								input.name = "activity.recommendedPartList["+i+"].partNumber"; 
								input.value = partNumber;
								document.getElementById("contentPart").appendChild(input);
								 
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partName"; 
								input.name = "activity.recommendedPartList["+i+"].partName"; 
								input.value = partName;
								document.getElementById("contentPart").appendChild(input);
								 
								if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.name = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.value = true;
									document.getElementById("contentPart").appendChild(input);
								}else{
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.name = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.value = false;
									document.getElementById("contentPart").appendChild(input);
								}

								if(!document.getElementById("newPartLineItemFlag_"+ind) && qtyValue!=0){
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].partLineItemUpdateFlag"; 
									input.name = "activity.recommendedPartList["+i+"].partLineItemUpdateFlag"; 
									input.value = true;
									document.getElementById("contentPart").appendChild(input);
								}
							}
							 
													
						}
						
						/*********Added for CI BRD 13-10-26 STARTS***********/
						/*******************Method for opening the popup for all kinds of address***************/
						function popupAddress(hyperlinkId){
							jQuery('#partnerAccountIdHidden').val(accountId);
									linkId=hyperlinkId;//This link id is for displaying inner htmls in address
									showOverlay();
									var url="${showAddressPopup}";
									url += "&accountId="+accountId;
									
									jQuery('#dialogAddress').load(url,function(){
										
										dialog=jQuery('#content_addr').dialog({
											autoOpen: false,
											title: jQuery('#'+hyperlinkId).attr('title'),
											modal: true,
											draggable: false,
											resizable: false,
											height: 500,
											width: 950,
											close: function(event,ui){
												hideOverlay();
												dialog=null;
												jQuery('#content_addr').remove();
												}
											});
										jQuery(document).scrollTop(0);
										dialog.dialog('open');
										initializeAddressGrid();
										if (window.PIE) {
								            document.getElementById("createNewAddressButton").fireEvent("onmove");
								            document.getElementById("cancelAddressButton").fireEvent("onmove");
								        }
										});
									return false;
								}
						

						//Changes done for CI Defect #9183--STARTS
						function addPickupAddress(ai,storeName,currentFavStatus, 
								  an, al1, offcNo, al2,city,county, state, province,region,country, zip,
								  physicalLoc1,physicalLoc2,physicalLoc3,district,countryISO,stateCode)
					
						{	
							if(province == null){
								province = '';
							}
							if(ai==null){
								ai = '-1';
							}
							jQuery("#pickupAddressId").val(ai);
							jQuery("#pickupStoreFrontName").val(storeName);
							jQuery("#pickupAddressLine1").val(al1);
							jQuery("#pickupAddresscounty").val(county);
							jQuery("#pickupAddressofficeNumber").val(offcNo);
							jQuery("#pickupAddressLine2").val(al2);	
							jQuery("#pickupAddressCity").val(city);
							jQuery("#pickupAddressState").val(state);
							jQuery("#pickupAddressProvince").val(province);
							jQuery("#pickupAddressPostCode").val(zip);
							jQuery("#pickupAddressCountry").val(country);
							jQuery("#pickupAddressdistrict").val(district);
							jQuery("#pickupAddressregion").val(region);
							jQuery("#pickupAddresscountryISOCode").val(countryISO);
							//Changes done for Address comma issues-CI STARTS
							if(storeName!="null"){
								jQuery("#pickupStoreFrontNameLbl").html(storeName);
								jQuery("#pickupStoreFrontNameLbl").show();
								}
							if(storeName!=null ||storeName!=' '){
								jQuery("#pickupStoreFrontNameLbl").html(storeName);
								jQuery("#pickupStoreFrontNameLbl").show();
								}
							if(al1!=' ' || al1!=''){
								jQuery("#addressLine1Label").html(al1);
								jQuery("#addressLine1Label").show();
							}
							if(al1!=null){
								jQuery("#addressLine1Label").html(al1);
								jQuery("#addressLine1Label").show();
							}
							if(offcNo!=' '||offcNo!=''){
								jQuery("#pickupAddressofficeNumberLbl").html(offcNo);
								jQuery("#pickupAddressofficeNumberLbl").show();
							}
							if(offcNo!=null){
								jQuery("#pickupAddressofficeNumberLbl").html(offcNo);
								jQuery("#pickupAddressofficeNumberLbl").show();
							}
							if(al2!=' '||al2!=''||al2!=null){
								jQuery("#addressLine2Label").html(al2);
								jQuery("#addressLine2Label").show();
							}
							//Changes done for CI Defect #9183--Starts 
							if(city !='' || city !=' ' || city != null){
							jQuery("#cityLabel").html(city);
							jQuery("#cityLabel").show();
							}
							if(county !=' '||county !=''||county !=null){
							jQuery("#pickupAddresscountyLbl").html(', '+county);
							jQuery("#pickupAddresscountyLbl").show();
							}
							if(state!=' '||state!=''||state!=null)
								{
								jQuery("#stateLabel").html(', '+state);
								jQuery("#stateLabel").show();
								}
							if(province !="null"){
								jQuery("#provinceLabel").html(', '+province);
								jQuery("#provinceLabel").show();
							}
							if(province !=' '||province!=''||province!=null){
								jQuery("#provinceLabel").html(', '+province);
								jQuery("#provinceLabel").show();
							}
							if(district !=' ' || district !='' || district !=null){
								jQuery("#pickupAddressRegionLB").html(', '+district);
								jQuery("#pickupAddressRegionLB").show();
								}
							if(zip !=' ' || zip !=''||zip!=null){
								jQuery("#postalCodeLabel").html(zip);
								jQuery("#postalCodeLabel").show();
								}	
							if(country !=' ' || country !=''||country !=null){
								jQuery("#countryLabel").html(country);
								jQuery("#countryLabel").show();
								}	
							//This is to hide if values are blank or null
							if(storeName==null ||storeName==' '){
								jQuery("#pickupStoreFrontNameLbl").hide();
								}
							if(storeName=="null"){
								jQuery("#pickupStoreFrontNameLbl").hide();
								}
							if(al1==null){
								jQuery("#addressLine1Label").hide();
							}
							if(al1==' ' || al1==''){
								jQuery("#addressLine1Label").hide();
							}
							if(offcNo==' '||offcNo==''){
								jQuery("#pickupAddressofficeNumberLbl").hide();
							}
							if(offcNo==null){
								jQuery("#pickupAddressofficeNumberLbl").hide();
							}
							if(al2==' '||al2==''||al2=="null"){
								jQuery("#addressLine2Label").hide();
							}
							if(city =='' || city ==' ' || city == null){
								jQuery("#cityLabel").hide();
								}
							if(county ==' '||county ==''||county ==null){
								jQuery("#pickupAddresscountyLbl").hide();
								}
							if(state==' '||state==''||state==null)
							{
							jQuery("#stateLabel").hide();
							}
							if(province =="null"){
								jQuery("#provinceLabel").hide();
							}
							if(province ==' '||province==''||province==null){
								jQuery("#provinceLabel").hide();
							}
							if(district ==' ' || district =='' || district ==null){
								jQuery("#pickupAddressRegionLB").hide();
								}
							if(district =="undefined"){
								jQuery("#pickupAddressRegionLB").hide();
								}
							if(zip ==' ' || zip ==''||zip==null){
								jQuery("#postalCodeLabel").hide();
								}	
							if(country ==' ' || country ==''||country ==null){
								jQuery("#countryLabel").hide();
								}	
							//Changes done for CI Defect #9183 ENDS
							//Changes done for Address comma issues-CI ENDS
								
						}
						
						
						function addRestFieldsOfCleanseAddress(){
								jQuery('#pickupAddressisAddressCleansed').val("true");
								<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
									jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
								<%}%>
								jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
								//Start Added for MPS 2.1
								jQuery("#pickupAddresscountyLbl").html(jQuery('#pickupAddresscounty').val());
								jQuery("#pickupAddresscountyLbl").show();
								if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() == null){
									jQuery("#pickupAddresscountyLbl").hide();
									}
								jQuery("#pickupAddressRegionLB").html(jQuery('#pickupAddressdistrict').val());
								//END
								}
						//Changes done for CI Defect #9183--ENDS
					
					function closeDialog()
					{
							dialog.dialog('close');
							dialog=null;
							jQuery('#dialog').html("");
					}
					/********************End*******************/
						/*********Added for CI BRD 13-10-26 ENDS***********/
					</script>
					<!-- Part and Address End -->
			       
			        <!-- Notes begin-->
			        
			        <div class="portlet-wrap" id="notes">
						<div class="portlet-header">
						<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th>
						     		<spring:message code="claim.label.notes"/>
						     	</th>
						      </tr>
						  </thead>
						</table>
							

						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div class="scroll">
								<div id="gridCCSNotes" class="width-98per"></div>
								<div id="pagingAreaNotes"></div>
								<a href="javascript:addNotePopUp();" class="button"><span><spring:message code="claim.button.addNotes"/></span></a>
								<div class="overlayAddnotePopup"></div>
								<br>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
						</div>
						<div id="noteContent" style="display:none;"></div>
						<script type="text/javascript">
						 var notesGrid = new dhtmlXGridObject('gridCCSNotes');
						 notesGrid.setImagePath("<html:imagesPath/>gridImgs/");
						 notesGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.requestUpdate.note"/>',11));
						 notesGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
						 notesGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left");
						 notesGrid.setColSorting("na,str,str,str,na,na,na,na,na,na,na");
						 notesGrid.setInitWidths("20,200,200,*,100,0,0,0,0,0,0");
						 notesGrid.enableAutoWidth(true);
						 notesGrid.enableAutoHeight(true);
						 notesGrid.init(); 
						 
						 notesGrid.setSkin("light");
						 notesGrid.enablePaging(true, 5, 6, "pagingAreaNotes", true);	
						 notesGrid.setPagingSkin("bricks");
						 notesGrid.loadXMLString('${serviceRequestDetailForm.notesXML}');
						 
						 function showNotesPopup(){
								jQuery(window).scrollTop(0);
								showNotes.show();
								jQuery(".aui button.close, .aui button.btn.close").hide();
								showNotes.titleNode.html("<spring:message code='claim.label.updateNote'/>");
								showNotes.io.set('uri',"${updateNoteUrl}&handleGridFlag=add");
								showNotes.io.start();
								
								}
						 function addNotePopUp(){
							//callOmnitureAction('Requests Detail Update View', 'Add Note PopUp');
							showOverlayIE6();
							showNotesPopup();
							bindingClosePopupIE6();
						}
						function addRowInNotesGrid(note){
								var subDetail = escapeStringForNoteSubRow(note[0]);
								var noteNum = notesGrid.getRowsNum();
								var id = notesGrid.getUID();
								var str =['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>',note[1],note[2],note[3],'<button class="button" type="button" onclick="editRow('+id+')"><spring:message code="button.edit"/></button>','','<input id="'+id+'_noteDetail" type="hidden" value="'+note[0]+'">'
								          ,'${serviceRequestDetailForm.contactId}','${serviceRequestDetailForm.firstName}','${serviceRequestDetailForm.lastName}','false'];
								notesGrid.addRow(id,str,0);
								notesGrid.changePage(1);
						}
						
						function updateRowInNotesGrid(note,rowId){
							var subDetail = escapeStringForNoteSubRow(note[0]);
							notesGrid.cellById(rowId,0).setContent(['<strong><spring:message code="claim.label.note"/></strong><br><div style="word-wrap: break-word;width:80%">'+subDetail+'</div>']);
							notesGrid.cells(rowId,3).setValue(note[3]);
							notesGrid.cells(rowId,6).setValue('<input id="'+rowId+'_noteDetail" type="hidden" value="'+note[0]+'">');
							if(notesGrid.cells(rowId,5).cell.innerHTML!="&nbsp;" && notesGrid.cells(rowId,5).cell.innerHTML !=""){
								notesGrid.cells(rowId,10).setValue('true');
							}
						}
						function updateNotesPopup(noteDate,noteAuthor,noteDetailId,id){
							jQuery(window).scrollTop(0);
							showNotes.show();
							jQuery(".aui button.close, .aui button.btn.close").hide();
							showNotes.titleNode.html("<spring:message code='claim.label.updateNote'/>");
							showNotes.io.set('uri',"${updateNoteUrl}&noteDate="+noteDate+"&noteAuthor="+noteAuthor+"&noteDetailId="+noteDetailId+"&rowId="+id+"&handleGridFlag=update");
							showNotes.io.start();
							
							}
						function editRow(id){
							//callOmnitureAction('Requests Detail Update View', 'Edit Note');
							var noteDate = notesGrid.cells(id,1).getValue();
							var noteAuthor =  notesGrid.cells(id,2).getValue();
							var noteDetailId = id+"_noteDetail";
							showOverlayIE6();
							updateNotesPopup(noteDate,noteAuthor,noteDetailId,id);
							bindingClosePopupIE6();
						}
						function generateNotesFormData(){
							document.getElementById("noteContent").innerHTML = "";
							var rows = notesGrid.getRowsNum();
							var noteCurrentPage = notesGrid.currentPage;
							for(var i = 0 ;i < rows ;i++){
								if(i == 0){
									notesGrid.changePage(1);
								}else if(i%global_paging_size ==0){
									notesGrid.changePageRelative(1);
								}
								var rowId = notesGrid.getRowId(i);
								//noteId
								var inputNoteId = document.createElement("input");
								inputNoteId.id = "activity.activityNoteList["+i+"].noteId"; 
								inputNoteId.name = "activity.activityNoteList["+i+"].noteId";
								inputNoteId.value = notesGrid.cellByIndex(i,5).cell.innerHTML=="&nbsp;"?"":notesGrid.cellByIndex(i,5).cell.innerHTML;
								document.getElementById("noteContent").appendChild(inputNoteId);
								//noteDate
								var inputDate = document.createElement("input");
								inputDate.id = "activity.activityNoteList["+i+"].noteDate"; 
								inputDate.name = "activity.activityNoteList["+i+"].noteDate";
								inputDate.value = formatDateToDefault(notesGrid.cellByIndex(i,1).cell.innerHTML);
								document.getElementById("noteContent").appendChild(inputDate);
								//noteAuthor
								var inputAuthor = document.createElement("input");
								inputAuthor.id = "activity.activityNoteList["+i+"].noteAuthor.contactId"; 
								inputAuthor.name = "activity.activityNoteList["+i+"].noteAuthor.contactId";
								inputAuthor.value = notesGrid.cellByIndex(i,7).cell.innerHTML;
								document.getElementById("noteContent").appendChild(inputAuthor);
			
								var inputAuthor = document.createElement("input");
								inputAuthor.id = "activity.activityNoteList["+i+"].noteAuthor.firstName"; 
								inputAuthor.name = "activity.activityNoteList["+i+"].noteAuthor.firstName";
								inputAuthor.value = notesGrid.cellByIndex(i,8).cell.innerHTML;
								document.getElementById("noteContent").appendChild(inputAuthor);
			
								var inputAuthor = document.createElement("input");
								inputAuthor.id = "activity.activityNoteList["+i+"].noteAuthor.lastName"; 
								inputAuthor.name = "activity.activityNoteList["+i+"].noteAuthor.lastName";
								inputAuthor.value = notesGrid.cellByIndex(i,9).cell.innerHTML;
								document.getElementById("noteContent").appendChild(inputAuthor);
								//noteDetail
								var inputDetail = document.createElement("input");
								inputDetail.id = "activity.activityNoteList["+i+"].noteDetails"; 
								inputDetail.name = "activity.activityNoteList["+i+"].noteDetails";
								//var detail = escapeStringForNote(document.getElementById(rowId+"_noteDetail").value);
								var detail = document.getElementById(rowId+"_noteDetail").value;
								inputDetail.value = detail;
								document.getElementById("noteContent").appendChild(inputDetail);
								//activityUpdateFlag
								var inputUpdateFlag = document.createElement("input");
								inputUpdateFlag.id = "activity.activityNoteList["+i+"].activityUpdateFlag"; 
								inputUpdateFlag.name = "activity.activityNoteList["+i+"].activityUpdateFlag";
								inputUpdateFlag.value = notesGrid.cellByIndex(i,10).cell.innerHTML;
								document.getElementById("noteContent").appendChild(inputUpdateFlag);
							}
							notesGrid.changePage(noteCurrentPage);
						} 
			
						function cleanNotes(){
							notesGrid.clearAll();
						}
					</script>
					
			        <!-- Notes end-->
			    </div><!-- content -->
			</div><!-- main-wrap -->
			
			
			<div id="contentPart" style="display:none;"></div>
			</form:form>
			<!-- File Attachment start -->
				      
				  <table width="98%" class="margin-left-10px">
				    <tr>
				      <td>
				      <form:form id="attachmentForm" name="attachmentForm" commandName="attachmentFormDisplay" method="post" enctype="multipart/form-data">
				        <div class="portlet-wrap" id="attachmentBorder">
				        	<div class="portlet-header">
				        	<table class="displayGrid">
						  <thead>
						     <tr>
						     	<th>
						     		<spring:message code="claim.label.attachments" />
						     	</th>
						      </tr>
						  </thead>
						</table>
								
							</div>
							
							
							<div id="browseAttachmentFileId" class="portlet-wrap-inner">
								<table class="importTable" width="100%" border="0">
									<tr class="import-table-tr1">
										<td>
											<b><LABEL class="labelBold" for="fileInput"><spring:message code="fileAttachment.label.fileToUpload" /></LABEL>:&nbsp;</b>
										</td>
										<td>
											<nobr>
												<input type="text" id="txtFilePath" size="50" readonly="true" />&nbsp;
												<a href="#" class="button"><span><spring:message code="button.browse"/></span></a>&nbsp;
												<form:input type="file" size="1" id="fileInput" class="requestsUploader" path="fileData" onchange="document.getElementById('txtFilePath').value = getFileName();" style="z-index: 2; position: relative; filter: alpha(opacity = 0); opacity: 0; width: 80px; left: 0px !important; top: 0px !important; margin-left: -75px !important;" /> &nbsp;
												<a href="javascript:uploadFile();" class="button"><span><spring:message code="button.uploadFile"/></span></a>
											</nobr>		   
										</td>
									</tr>
									<tr class="import-table-tr1"><td></td><td><nobr><b><spring:message code="attachment.messgae.FileRestriction"/></b></nobr></td></tr>
									
									<tr class="import-table-tr1"><td></td><td><nobr><b><table id="errorMessageTable"></table></b></nobr></td></tr>
									<script type="text/javascript">
							        var errorMessageText;
				                    var attachmentErrorMsg="${attachmentFormDisplay.errorMessage}";
				                    jQuery('#errorMessageTable',window.parent.document).empty();
				                    errorMessageText ='<thead><tr><th><font color="RED">';
				                    if(attachmentErrorMsg=='attachment.maxFileSize.message'){
				                    	errorMessageText=errorMessageText+' <spring:message code="attachment.maxFileSize.message"/>';
				                    }
				                    if(attachmentErrorMsg=='attachment.duplicateFile.message'){
				                    	errorMessageText=errorMessageText+'<spring:message code="attachment.duplicateFile.message"/>';
				                    }
				                    if(attachmentErrorMsg=='attachment.maxNumberOfFiles.message'){
				                    	errorMessageText=errorMessageText+' <spring:message code="attachment.maxNumberOfFiles.message"/>';
				                    }
				                    if(attachmentErrorMsg=='attachment.FileType.message'){
				                    	errorMessageText=errorMessageText+' <spring:message code="attachment.FileType.message"/>';
				                    }
				                    errorMessageText=errorMessageText+'</font></th></tr></thead>';
									
									jQuery('#errorMessageTable',window.parent.document).append(errorMessageText);
							        </script>
							        
							        
									
								</table>
							</div>
											
							
							<div id='uploadedFileDIV' class="portlet-wrap-inner">
							<!-- 
								<div id='uploadedFileGrid' style="width:100%;"></div>
								<div id="loadingAttachmentNote" class="gridLoading" style=" display:none;"><spring:message code="label.loadingNotification"/>&nbsp;
									<img src="<html:imagesPath/>gridloading.gif" /><br>
								</div>
								-->
								<table id="fileListTable"></table>
							</div>
							<div class="portlet-footer">
								<div class="portlet-footer-inner"></div>
							</div>
				        </div>
				     	<iframe id="upload_target" name="upload_target" class="iframe-upload-target"></iframe>
				        
				        <script type="text/javascript">
				        
				     <c:if test="${attachmentFormDisplay.attachmentList != null}">
						jQuery('#fileListTable',window.parent.document).empty();
						var responseText = '<thead><tr class="tableHeader"><b><th class="width60"><spring:message code="attachment.message.FileName"/></th><th class="width35"><spring:message code="attachment.message.FileSize"/></th></b></tr></thead>';
						   responseText = responseText + '<tbody>';
							<c:forEach items="${attachmentFormDisplay.attachmentList}" var="entry">
					 			responseText = responseText + '<tr class="tableContentColor">';
					 			responseText = responseText + '<td class="width60">'+ '<a href="#" onClick=\'openFile("${entry.attachmentName}");\'><c:out value="${entry.attachmentName}" /></a>';
					 			responseText = responseText + '<img id="img_help1" class="ui-icon trash-icon"  src="<html:imagesPath/>transparent.png" style="cursor:pointer;" title="Delete" alt="Remove" onclick=\'deleteFile("${entry.attachmentName}");\' /></td>';
					 			responseText = responseText + '<td align="center" class="width35">'+ '<c:out value="${entry.sizeForDisplay}" />' + '</td>';
					 			responseText = responseText + '</tr>';					 			
							</c:forEach>
						responseText = responseText + '</tbody>';
						jQuery('#fileListTable',window.parent.document).append(responseText);
						jQuery('#txtFilePath',window.parent.document).val('');//Used to remove the filename when the upload is done
					</c:if>
					
					<c:if test="${attachmentFormDisplay.attachmentList == null}">

						jQuery('#fileListTable',window.parent.document).empty();
					</c:if>


					jQuery(document).ready(function() {
						jQuery("#fileInput").css({"cursor":"pointer"});
					jQuery("#processingHint",window.parent.document).css({
					               display:"none"
					       });
					       jQuery("#overlay",window.parent.document).css({
					               display:"none"
					       });
					});
				       function getFullPath(obj) {
							if (obj) {
							//ie 
								if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
									obj.select();
									var path = document.selection.createRange().text;
									return path;
								} else {
									if (obj.files) {
										return obj.files.item(0).fileName;
									}
										return obj.value;
								}
							}	
						};

						function uploadFile() {
							var filePath = document.getElementById('txtFilePath').value;
							
							if(filePath != '') {
								document.getElementById('attachmentForm').target = 'upload_target';
								var link = "${addAttachmentsRequestUpdate}";
								document.getElementById('attachmentForm').action = link;
								eval(document.attachmentForm).submit();
							}
						};

						function deleteFile(fileName) {
							// call action in controller to delete file
							var link = '${removeAttachmentURL}';
							document.getElementById('attachmentForm').target = 'upload_target';
							document.getElementById('attachmentForm').action = link + "&fileName=" + fileName;
							eval(document.attachmentForm).submit();
							};

						function openFile(fileNm) {
							// call action in controller to open file
							
						};
						function getFileName() {
							var path = document.getElementById('fileInput').value;
								if(path.match(/fakepath/)) {
									path = path.replace(/C:\\fakepath\\/i, '');
							    }
							return path;
						};
						
						</script>
						</form:form>
					  </td>
				  </tr>
			  </table>
			  <table width="98%" class="margin-left-10px">
			  	<tr>
			  		<td>
			  			<form:form id="dummyForm" name="dummyForm" method="post">
			  				<table class="width-95per">
								<tr>
									<td>
										<div align="left">
										<a href="javascript:submitFormRequestUpdate();" class="button"><span><spring:message code="button.update" /></span></a>&nbsp;
										<a href="javascript:cancelUpateRequest();" class="button_cancel"><span><spring:message code="button.cancel" /></span></a>										</div>
									</td>
								</tr>
							</table>
			  			</form:form>
			  		</td>	
			  	</tr>
			  </table>	        
				        <!-- File Attachment end -->
		</td>
	</tr>
</table>			        
			        
			        

			        <br><br>
					
					<script type="text/javascript">
						var showshipToDefaultFlag=false;
						var count = 1;
						var rgrid = recommendGrid;
						var partsAddedCount = rgrid.getRowsNum();
						function validateBeforeSubmit(){
							var result = validateTechnicianInfo();
							result &= validateStatus();							
							return result;
						}
			
						function validatePartAndAddress(){
							//alert("showshipToDefaultFlag"+showshipToDefaultFlag);
							if(showshipToDefaultFlag){
								return;
							}
							else{
							if("${serviceRequestDetailForm.activity.addressStatus}"!="${serviceRequestDetailForm.addressStatusList[0]}"){
							    var postalCode = document.getElementById('postalCodeLabel').innerHTML;
							    var city;
							    var addr1;
							    var country;
							    if(document.getElementById('cityLabel')){
							     city = document.getElementById('cityLabel').innerHTML;
							    }else{
							    	city="";
							    }
							    if(document.getElementById('addressLine1Label')){
							    	addr1 = document.getElementById('addressLine1Label').innerHTML;
								    }else{
								    	addr1="";
								    }
							    if(document.getElementById('countryLabel')){
							    	country = document.getElementById('countryLabel').innerHTML;
								    }else{
								    	country="";
								    }
								if((city.trim() == "" || addr1.trim() == "" || country.trim() == "")&&(countQTYSum()!=0)){
									var itext ='<br><spring:message code="claim.label.noAddressSelected" /> <spring:message code="claim.label.noPartWillBeOrder" /> <br><br><spring:message code="claim.label.toSelectAddress" /><br><spring:message code="claim.label.toContinueSubmit" /><br><br>';
									jiConfirm(itext, '','<spring:message code="button.submitUpdate" />','<spring:message code="button.goBack" />','', function(iconfirmResult) {
										if(iconfirmResult)
											confirmSubmitUpdateRequest();
									});
								}else if((partsAddedCount > 0)&&(countQTYSum()==0)){
									var itext ='<br><spring:message code="claim.label.partQuantityZero" /> <spring:message code="claim.label.noPartWillBeOrder" /> <br><br><spring:message code="claim.label.toAdjustPart" /><br><spring:message code="claim.label.toContinueSubmit" /><br><br>';
									jiConfirm(itext, '','<spring:message code="button.submitUpdate" />','<spring:message code="button.goBack" />', '',function(iconfirmResult) {
										if(iconfirmResult)
											confirmSubmitUpdateRequest();
									});
								}else if(showshipToDefaultFlag){
										return;
									}
								else{
									confirmSubmitUpdateRequest();
								}
							}
							else{
								confirmSubmitUpdateRequest();
							}
							}
						}
						
				    	function submitFormRequestUpdate(){
				    		//callOmnitureAction('Requests Detail Update View', 'Submit');
							clearMessage();
							removeErrorClass();
							generateNotesFormData();													
							if(validateBeforeSubmit() != 0)	{							
								count++;
								checkShipToDefault();
								validatePartAndAddress();									
							}																			
							else{
								window.scroll(0,0);
							}	
						}
				    					    	
						
						function confirmSubmitUpdateRequest(){
							jConfirm("<spring:message code='claim.message.confirmSubmitRequestUpdate'/>", "", function(confirmResult) {
								if(confirmResult){
									generatePartAndTool();	
									
									jQuery("#requestDetailUpdateForm").submit();
								}else{
									return;
								}
							});
						}
						
						function cancelUpateRequest(){
							//callOmnitureAction('Requests Detail Update View', 'Cancel');
							jConfirm("<spring:message code='claim.message.cancelRequestUpdate'/>", "", function(confirmResult) {
								if(!confirmResult){
									return;
								}else{
									window.location.href = "${cancelRequestUpdateUrl}&timezoneOffset="+timezoneOffset;
								}
							});
						}
						
						function removeErrorClass(){
							jQuery('#technicianChoose').removeClass('errorColor');
							jQuery('#technicianLastNameInput').removeClass('errorColor');
							jQuery('#technicianFirstNameInput').removeClass('errorColor');
							jQuery('#serviceProvStatusDiv').removeClass('errorColor_status');
						}

						//added for BRD 14-07-02
						var ShipToDefault = "${ShipToDefault}";
						//alert("ShipToDefault--->>>"+ShipToDefault);
						function checkShipToDefault(){							
							showshipToDefaultFlag= false;						
							if(jQuery.inArray("true", shipToaddrFlag).toString()=="0"){	
								if(ShipToDefault=="true" && ((document.getElementById('cityLabel').innerHTML=="")&&(document.getElementById('addressLine1Label').innerHTML==""))){
									showshipToDefaultPopUp();
									showshipToDefaultFlag= true;
								}
							}							
						}
						 function showshipToDefaultPopUp(){
							jConfirm("<spring:message code='servicerequestUpdate.selectShipToAddress'/>", "", function(confirmResult) {
								if(confirmResult){									
									return;
								}else{
									return;
								}
							});
							
							}; 
							
							/* function showshipToDefaultPopUp(){
								//dialog=jQuery('#shipToDefaultPopUp').dialog({
									autoOpen: false,
									title: "Select Ship To Address",
									modal: true,
									height: 130,
									width: 300,
									position: ['center','center'],
									xy: [100, 100],
									resizable: false,
									open:function(){
											//jQuery(this).scrollTop(0);
											jQuery('#shipToDefaultPopUp').show();
										},
									close: function(event,ui){
										 //hideOverlay();
										 dialog.dialog('destroy');									 
										 //confirmRfresh();
										}
									});
								jQuery(document).scrollTop(0);
								dialog.dialog('open');
								
								}; */
							
							
					</script>
					<div id="shipToDefaultPopUp" style="display: none;">
						<div><spring:message code='requestsDetailUpdate.label.selectAddress'/>.
						</div>
					</div>
