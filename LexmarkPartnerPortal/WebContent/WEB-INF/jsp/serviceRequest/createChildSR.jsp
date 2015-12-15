<%@ page pageEncoding="UTF-8" %>
<% String path=request.getContextPath(); %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
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
.pagination span#gridRDVOrderedPartsTablePagingArea>div { width:100%!important }
.pagination span#gridRDVbeReturnedPartsPagingArea>div { width:100%!important }
.pagination span#pagingAreaRecommend>div { width:100%!important }

</style>
<script type="text/javascript">
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

<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>

<portlet:actionURL  var="cancelRequestUpdateUrl">
    <portlet:param name="action" value="cancelRequestUpdate" />
</portlet:actionURL>

<portlet:actionURL var="updateORCancelChildSRDetailUpdateURL">
	<portlet:param name="action" value="updateORCancelChildSRDetailUpdateURL"/>
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
//trimm spaces and leading 0, s, S
function trim(strValue){
	var trimValue = strValue.replace(/\s/g,"");
	trimValue = trimValue.replace(/^[s0S]*/g,"");
	return trimValue;	
}

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
			<form:form id="createChildSRDetailForm" name="createChildSRDetailForm" commandName="createChildSRDetailForm" method="post" action="${updateORCancelChildSRDetailUpdateURL}">
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
							       <form:input id = "childSRStatus" name ="childSRStatus" path="childSRStatus" value=""/>
							       <form:input id = "serviceRequestIdHidden" name="serviceRequest.id" path="serviceRequest.id" />
									<form:input id = "serviceRequestNumber" name="serviceRequest.serviceRequestNumber" path="serviceRequest.serviceRequestNumber" />
									<form:input id ="otherRequestedService" name = "serviceRequest.otherRequestedService" path ="serviceRequest.otherRequestedService" value = "${serviceRequest.otherRequestedService}" />		
							        
									
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
				                        <dl>
				                            <dd>
				                                <table>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.reuqestNumber" /></label></td>
				                                        <td>
				                                           ${createChildSRDetailForm.serviceRequest.serviceRequestNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.openedDateTime" /></label></td>
				                                        <td>
				                                           <util:dateFormat value="${createChildSRDetailForm.serviceRequest.serviceRequestDate}" showTime="true" timezoneOffset="${serviceRequestDetailForm.timezoneOffset}" />
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
													<td  class="labelTD" align="center"><h3>${createChildSRDetailForm.serviceRequest.asset.productLine}</h3>
												    </td>
												</tr>
												<tr>
												  	<td align="center"><img width="75" src="${createChildSRDetailForm.serviceRequest.asset.productImageURL}"/></td>
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
				                                           ${createChildSRDetailForm.serviceRequest.asset.serialNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.machineTypeModel" /></label></td>
				                                        <td>
				                                           ${createChildSRDetailForm.serviceRequest.asset.modelNumber}
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <td align="right"><label><spring:message code="claim.label.productPN/TLI" /></label></td>
				                                        <td>
				                                           ${createChildSRDetailForm.serviceRequest.asset.productTLI}
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
												<td>${createChildSRDetailForm.serviceRequest.accountName}</td>
											</tr>
											<tr>
												<td  align="right" valign="top"><B><spring:message code="claim.label.address" /></B></td>
												<td width="20px"/></td>
												<td valign="bottom">
													<util:addressOutput value="${createChildSRDetailForm.serviceRequest.installAddress}"/>
												</td>
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
												<td>${createChildSRDetailForm.serviceRequest.primaryContact.firstName} ${createChildSRDetailForm.serviceRequest.primaryContact.lastName}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.phone" /></B></td>
												<td width="20px"/></td>
												<td>${createChildSRDetailForm.serviceRequest.primaryContact.workPhone}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="requestInfo.contactInfo.label.alternatePhone" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${createChildSRDetailForm.serviceRequest.primaryContact.alternatePhone}</td>
											</tr>
											<tr>
												<td  align="right"><B><spring:message code="claim.label.email" /></B></td>
												<td width="20px"/></td>
												<td class="word-wrap-break-word">${createChildSRDetailForm.serviceRequest.primaryContact.emailAddress}</td>
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
			        
			         <!-- Problem Description begin-->
			        <div class="portlet-wrap">
			            <div class="portlet-header">
			            <table class="displayGrid">
						  <thead>
						     <tr>
						     	<th><spring:message code="claim.label.problemDescription" /></th>
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
				                                <label>*<spring:message code="claim.label.FCC" /></label>
				                                </br>
				                               <c:if test="${newChildSR == true }">
				                                <form:select id="fccCodeListLOV" path = "fccCodeListLOV">
								                  	<form:option value=""></form:option>
								                  	<c:forEach items="${fccCodeListLOV}" var="entry">
								                  		<c:choose>
								                  			<c:when test="${fccCodeListLOV.value == entry.key}">
								                  				<form:option value="${entry.key}" selected="selected">${entry.value}</form:option>
								                  			</c:when>
								                  			<c:otherwise>
								                  				<option value="${entry.key}" >${entry.value}</option>
								                  			</c:otherwise>
								                  		</c:choose>								                    
													</c:forEach>
													</form:select>
													</c:if>
													<c:if test="${newChildSR == false }">
													<dd><input type="text" readonly="readonly" value="${createChildSRDetailForm.serviceRequest.fccCode }"></input></dd>								                  
													</br>
													</c:if>
				                            </dd>
				                            <dd><label>*<spring:message code="claim.label.problemDescription" /></label></dd>
				                            <c:if test="${newChildSR == true }">			                                
				                            <dd><form:textarea id="problemDescriptionInput" name="problemDescriptionInput" path = "serviceRequest.problemDescription" class="problemDescriptionInput" onchange="validateLength(0,150,this)"></form:textarea></dd>
				                           
				                            </c:if>
				                            <c:if test="${newChildSR == false }">
				                            <dd><textarea id="problemDescriptionInput" readonly="readonly">${createChildSRDetailForm.serviceRequest.problemDescription }</textarea></dd>				                            
				                            </c:if>
				                        </dl>
				                    </div>
									</div>
				                </div>
			                </div>
			            </div>
			            <div class="portlet-footer">
			                <div class="portlet-footer-inner">
			                </div><!-- portlet-footer-inner -->
			            </div>
			        </div>
			         <!-- Problem Description End-->
			        		        
					<script type="text/javascript">
						var strNow = localizeFormatDate(new Date(), true);
						var defaultStatusAsOf = strNow.substring(0, strNow.lastIndexOf(':'));
						jQuery("#statusAsOf").val(defaultStatusAsOf);
						
					</script>
			        
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
								<c:if test="${newChildSR ==true }">					
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
						</td>

									</tr>
									<tr id="selectOtherAddress">
										<td colspan="2"><a href="javascript:void(0);" id="installedAddressLink" onClick="return popupAddress(id);">
											<spring:message code="claim.label.selectAddress"/></a>
										</td>
									</tr>
								</table>
								</div>

						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
						<div id="contentParts" style="display:none;"></div>
					</div>
					<script type="text/javascript">
					addressFunction();
					var addressPopUpWindow;
					function addressFunction(){
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
					urladdress="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
					"<portlet:param name='action' value='showCreateNewAddressPage' /></portlet:renderURL>";
					function addressPopup(){
						jQuery(window).scrollTop(0);
						addressPopUpWindow.show();
						jQuery(".aui button.close, .aui button.btn.close").hide();
						addressPopUpWindow.titleNode.html("<spring:message code='claim.label.createNewAddress'/>");
						addressPopUpWindow.io.set('uri',urladdress);
						addressPopUpWindow.io.start();
						
						}
					</script>
					<script type="text/javascript">
					
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
		         	"<portlet:param name='action' value='showPartAndToolPage' /></portlet:renderURL>"
					function partListPopup(){
						jQuery(window).scrollTop(0);
						partListPopUpWindow.show();
						jQuery(".aui button.close, .aui button.btn.close").hide();
						partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/>");
						partListPopUpWindow.io.set('uri',url);
						partListPopUpWindow.io.start();
						
						}
					jQuery(document).ready(function() {
						document.getElementById('selectOtherAddress').style.display = 'none';
						document.getElementById('partnerAddressDiv').style.display = 'none';
						//var newChildSR = ${newChildSR};
						//if(!newChildSR){
							//jQuery("#buttons").hide();
							//jQuery('#fccCodeListLOV').attr('disabled', 'disabled');	
							//jQuery('#problemDescriptionInput').attr('disabled', 'disabled');
							//jQuery("#addPartSection").hide();
						//}
					});
					
					var accountId = '${accountId}';
						var isPartnerAddressGridLoaded = false;
						var isPageLoaded = false;
						var isChooseAdifferentAddress = true;
						var preOption = 0;
						var modelNumber="${createChildSRDetailForm.serviceRequest.asset.modelNumber}";
						//alert("model #"+modelNumber);
						var favStatus = {};
						var qtyList = new Array();
						for(b = 1;b<=5;b++){
							qtyList[b] = b;
						}
						var recommendGrid = new dhtmlXGridObject('gridRDURecommendPart');
						recommendGrid.setImagePath("<html:imagesPath/>gridImgs/");
						recommendGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.requestUpdate.recommendedPart"/>',7));
						recommendGrid.setColAlign("left,left,left,left,left,left,left");
						recommendGrid.setInitWidths("200,200,200,200,0,50,0");
						recommendGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
						recommendGrid.setColSorting("na,str,str,str,na,str,na");
						recommendGrid.enableAutoWidth(true);
						recommendGrid.enableAutoHeight(true);
						recommendGrid.enableMultiline(true);
						recommendGrid.init();
						recommendGrid.prftInit();
						recommendGrid.setSizes();
						recommendGrid.setColumnHidden(4,true);
						//recommendGrid.setColumnHidden(5,true);
						recommendGrid.setColumnHidden(6,true);
						recommendGrid.setSkin("light");
						recommendGrid.enablePaging(true, 9999, 6, "pagingAreaRecommend", true,"pagingInfoAreaRecommend");
						recommendGrid.setPagingSkin("bricks");
						//recommendGrid.loadXMLString('${serviceRequestDetailForm.recommendedPartsXML}');
						<c:if test="${newChildSR == false}">
						recommendGrid.parse('${recommendedPartXML}');
						</c:if>
						
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
							url+="&partNumber="+partNumber+"&modelNumber="+modelNumber+"&organizationId=${organizationID}"+"&isMaintenance=false";
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
								jAlert('<spring:message code="claim.message.noPartFoundModified"/>', "");
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
								//alert("Hello");
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
							//alert("Inside add");
							//alert("FSCDoNotShowAddress"+'${FSCDoNotShowAddress}');
							var FSCDoNotShowAddress = '${FSCDoNotShowAddress}';
							if(FSCDoNotShowAddress == "false"){
							document.getElementById('selectOtherAddress').style.display = 'block';
							document.getElementById('partnerAddressDiv').style.display = 'block';
							}							
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
									checkCountAddressLinkVisibility();
									showMessageForQty("",true);
								}
							});
						}
						
						function checkCountAddressLinkVisibility()
						{							
							var grid = recommendGrid;
							var rowNumber = grid.getRowsNum();
							if(rowNumber == 0)
								{
								document.getElementById('selectOtherAddress').style.display = 'none';
								document.getElementById('partnerAddressDiv').style.display = 'none';								
								}
							return;
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
							//alert("pp");
							var FSCDoNotShowAddress = '${FSCDoNotShowAddress}';
							//alert("FSCDoNotShowAddress"+FSCDoNotShowAddress);
							if(FSCDoNotShowAddress == "false"){
								//alert("Hi-->>");
							document.getElementById('selectOtherAddress').style.display = 'block';
							document.getElementById('partnerAddressDiv').style.display = 'block';
							}							
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
						/* function switchToAllAddresses(){
							alert("Hello--->>>");
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
						} */
						
						
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
						function popupCreateNewAddressPage(){
							//callOmnitureAction('Requests Detail Update View', 'Create New Address PopUp');
							showOverlayIE6();
							addressPopup();
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
						
						function addPartnerAddressElementPopup(addressId, addressName, addressLine1, addressLine2, addressLine3, addressCity, addressState, addressProvince, nummValue , addressCountry, addressPostCode, fav){
							addPartnerAddressElement(addressId, addressName, addressLine1, addressLine2, "", addressCity, addressState, addressProvince, null , addressCountry, addressPostCode, fav);
						}
						
						
						function setPartnerAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag) {
							var url = '<portlet:resourceURL id="setPartnerAddressFavouriteFlag"/>';
						    url += "&favoriteAddressId="+favoriteAddressId;
						    url += "&accountId=${accountId}";
						
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
							//alert("Generate");
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
								document.getElementById("contentParts").appendChild(input);

								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partLineItemId"; 
								input.name = "activity.recommendedPartList["+i+"].partLineItemId"; 
								input.value = recommendGrid.cellByIndex(i,6).cell.innerHTML=="&nbsp;"?"":recommendGrid.cellByIndex(i,6).cell.innerHTML;
								document.getElementById("contentParts").appendChild(input);
								
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partOrderedDate"; 
								input.name = "activity.recommendedPartList["+i+"].partOrderedDate"; 
								input.value = date;
								document.getElementById("contentParts").appendChild(input);
									
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].quantity"; 
								input.name = "activity.recommendedPartList["+i+"].quantity"; 
								input.value = parseInt(qtyValue);
								document.getElementById("contentParts").appendChild(input);

								if(reQuantity != "&nbsp;"){
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].recommendedQuantity"; 
									input.name = "activity.recommendedPartList["+i+"].recommendedQuantity"; 
									input.value = reQuantity;
									document.getElementById("contentParts").appendChild(input);
								}
									
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partNumber"; 
								input.name = "activity.recommendedPartList["+i+"].partNumber"; 
								input.value = partNumber;
								document.getElementById("contentParts").appendChild(input);
									
								var input = document.createElement("input");
								input.id = "activity.recommendedPartList["+i+"].partName"; 
								input.name = "activity.recommendedPartList["+i+"].partName"; 
								input.value = partName;
								document.getElementById("contentParts").appendChild(input);
									
								if('<spring:message code="claim.lebel.returnRequired.yes"/>'==reRequire){
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.name = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.value = true;
									document.getElementById("contentParts").appendChild(input);
								}else{
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.name = "activity.recommendedPartList["+i+"].returnRequiredFlag"; 
									input.value = false;
									document.getElementById("contentParts").appendChild(input);
								}

								if(!document.getElementById("newPartLineItemFlag_"+ind) && qtyValue!=0){
									var input = document.createElement("input");
									input.id = "activity.recommendedPartList["+i+"].partLineItemUpdateFlag"; 
									input.name = "activity.recommendedPartList["+i+"].partLineItemUpdateFlag"; 
									input.value = true;
									document.getElementById("contentParts").appendChild(input);
								}
							}
													
						}
						
						
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
			    </div><!-- content -->
			</div><!-- main-wrap -->
			</form:form>
		</td>
	</tr>
</table>
<c:if test="${newChildSR == true }">
 <table width="98%" class="margin-left-10px">
			  	<tr>
			  		<td>
			  			<form:form id="dummyForm" name="dummyForm" method="post">
			  				<table class="width-95per">
								<tr>
									<td>
									
										<div align="left" id="buttons">
										<a href="javascript:submitFormRequestUpdate();" class="button"><span><spring:message code="button.update" /></span></a>&nbsp;
										<a href="javascript:cancelUpateRequest();" class="button_cancel"><span><spring:message code="button.cancel" /></span></a>										
										</div>
									</td>
								</tr>
							</table>
			  			</form:form>
			  		</td>	
			  	</tr>
			  </table>			        
			       </c:if> 
			        <br><br>
					
					<script type="text/javascript">
						
						var count = 1;
						var rgrid = recommendGrid;
						var partsAddedCount = rgrid.getRowsNum();
						
				    	function submitFormRequestUpdate(){
				    		clearMessage();
							removeErrorClass();
				    		if(validateBeforeSubmit())
				    		{
				    		jConfirm("<spring:message code='claim.message.confirmSubmitChildRequestUpdate'/>", "", function(confirmResult) {
								if(confirmResult){
									generatePartAndTool();
									//jQuery("#childSRStatus").val("update");
									document.getElementById('childSRStatus').value="Ready for Service";
									jQuery("#createChildSRDetailForm").submit();
								}else{
									return;
								}
							});
				    		
						}
				    		else
				    			{
				    			window.scroll(0,0);
				    			}
				    	}
				    	
				    	function validateBeforeSubmit(){
							var result = validateFLCC();
							result &= validateProblemDescription();
							return result;
						}
				    	
				    	function validateFLCC()
				    	{
				    		var result = true;
				    		var selectedFLCC = document.getElementById('fccCodeListLOV').value;
				    		if(selectedFLCC =='')
				    			{
				    			showError("<spring:message code='claim.errorMsg.FLCC'/>",null, true);
								jQuery('#fccCodeListLOV').addClass('errorColor_status');
				    			result=false;
				    			}
				    		return result;
				    	}
				    	function validateProblemDescription()
				    	{
				    		var result = true;
				    		var probDesc = document.getElementById('problemDescriptionInput').value;
				    		if(probDesc =='')
				    			{
				    			showError("<spring:message code='claim.errorMsg.probDesc'/>",null, true);
								jQuery('#problemDescriptionInput').addClass('errorColor_status');
				    			result=false;
				    			}
				    		return result;
				    		
				    	}
						
						
						function cancelUpateRequest(){
							//callOmnitureAction('Requests Detail Update View', 'Cancel');
							jConfirm("<spring:message code='claim.message.cancelRequestUpdate'/>", "", function(confirmResult) {
								if(!confirmResult){
									return;
								}else{
									jQuery("#childSRStatus").val("Cancelled Service Action");
									//window.location.href = "${cancelRequestUpdateUrl}&timezoneOffset="+timezoneOffset;
									jQuery("#createChildSRDetailForm").submit();
								}
							});
						}
						
						function removeErrorClass(){							
							jQuery('#problemDescriptionInput').removeClass('errorColor_status');
							jQuery('#fccCodeListLOV').removeClass('errorColor_status');														
						}
					</script>
