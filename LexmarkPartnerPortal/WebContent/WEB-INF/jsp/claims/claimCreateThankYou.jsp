<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/mps.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.ie7 #gridCTYVRequestedPartsPagingArea>div { width:100%!important }
</style>
<portlet:renderURL  var="createNewClaim">
	<portlet:param name="action" value="showGlobalPartnerAssetSectionView" />
</portlet:renderURL>

<portlet:renderURL  var="requestsListViewUrl">
</portlet:renderURL>
<script type="text/javascript">
//trimm spaces and leading 0, s, S
function trim(strValue){
	var trimValue = strValue.replace(/\s/g,"");
	trimValue = trimValue.replace(/^[s0S]*/g,"");
	return trimValue;	
}
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table id="mainContent" width="100%">
	<tr>
		<td>
			<div class="main-wrap div-wrap-style" >
				<input type="hidden" id="repairCompleteFlag" value ="${claimRequestConfirmationForm.activity.repairCompleteFlag}"/>
			    <div class="content">
					<div class="grid-controls">
						<div class="utilities">
							<ul>
								<li>
									<img width="23px" height="23px" title="<spring:message code='image.title.print'/>"	class="cursor-pointer" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" onClick="return print()">
								</li>
							</ul>
						</div><!-- utilities -->
						<div class="expand-min">
							<A id="headerImageButton" href="${requestsListViewUrl}" class="button"><spring:message code="link.return.to.requests" /></A>
							&nbsp;&nbsp;
							<a href="javascript:createNewClaim();" class="button"><spring:message code="claim.button.createNewClaim" /></a>
						</div>
						<div class="clear-right"></div>
						<br>
					</div>
			
									
					<div class="portlet-wrap" id="claim_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.thankYou" /></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div class="width-120per"> <%--Changed by sankha for LEX:AIR00068879 --%>
								<div class="columns one" >
									<div class="first-column">
										<dl>
											<dd><h3 class="font-weight-normal"><spring:message code="claim.label.claimRequestSubmitted" /></h3></dd>
											<dd><h2><B><spring:message code="claim.label.claimNumber" /> ${claimRequestConfirmationForm.activity.serviceRequest.serviceRequestNumber}</B></h2></dd>
											<dd><spring:message code="claim.label.reviewDetails" /></dd>
										</dl>
									</div><!-- first-column -->
								</div>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
			
					<div class="portlet-wrap" id="device_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.deviceInformation" /></h3>
						</div>
						<div class="portlet-wrap-inner">
							<div class="width-100per">
								<div class="columns three" >
									<div class="first-column">
									<dl>
										<dd>
											<div class="device">
												<label>${claimRequestConfirmationForm.asset.productLine}</label>
												<img width="75" src="${claimRequestConfirmationForm.asset.productImageURL}" id="divImgs">
											</div><!-- device -->
										</dd>
									</dl>			
									</div><!-- first-column -->
									<div class="second-column">
										<table>
											<tr>
												<td ></td>
												<td ></td>
											</tr>
											<tr height="25px">
												<td  align="right" width="140px"><B><spring:message code="claim.label.openedDate" /></B></td>
												<td width="20px"/></td>
												<td >
													<util:dateFormat value="${claimRequestConfirmationForm.activity.serviceRequest.serviceRequestDate}" timezoneOffset="${claimRequestConfirmationForm.timezoneOffset}" />
												</td>													
											</tr>
											<tr height="25px">
												<td   align="right" width="140px"><B><spring:message code="claim.label.claimNumber" /></B></td>
												<td width="20px"/></td>
												<td >${claimRequestConfirmationForm.activity.serviceRequest.serviceRequestNumber}</td>
											</tr>
											<tr height="25px">
												<td  align="right" width="140px"><B><spring:message code="claim.label.serialNumber" /></B></td>
												<td width="20px"/></td>
												<td >${claimRequestConfirmationForm.asset.serialNumber}</td>
											</tr>
											<tr height="25px">
												<td  align="right" width="140px"><B><spring:message code="claim.label.machineTypeModel" /></B></td>
												<td width="20px"/></td>
												<td >${claimRequestConfirmationForm.asset.modelNumber}</td>		
											</tr>
											<tr height="25px">
												<td  align="right" width="140px"><B><spring:message code="claim.label.productPN/TLI" /></B></td>
												<td width="20px"/></td>
												<td >${claimRequestConfirmationForm.asset.productTLI}</td>
											</tr>
										</table>
									</div><!-- second-column -->
									<div class="third-column">
										<table width="90%" class="table-style1">
										<c:if test="${claimType != ''}">
											<tr>
												<td  align="right"><B>Claim Type:</B></td>
												<td width="20px"/></td>
												<td >${claimType}</td>
											</tr>
										</c:if>		
											<tr>
												<td  align="right"><B><spring:message code="claim.label.customerAccount" /></B></td>
												<td width="20px"/></td>
												<td >${claimRequestConfirmationForm.activity.customerAccount.accountName}</td>
											</tr>
											<tr>
												<td  align="right" valign="top"><B><spring:message code="claim.label.customerAddress" /></B></td>
												<td width="20px"/></td>
												<!-- changed for CI-5 2098 -->
												<c:choose>
													<c:when test="${claimRequestConfirmationForm.activity.newCustomerAccountFlag=='true'}">
														<td valign="bottom">
															<util:replaceRN value="${claimRequestConfirmationForm.activity.newCustomerAddressCombined}"/>
														</td>
													</c:when>
													
													<c:otherwise>
														<td valign="bottom">
															<util:addressOutput value="${claimRequestConfirmationForm.activity.serviceRequest.asset.installAddress}"/>
														</td>
													</c:otherwise>
												</c:choose>
												</tr>
										</table>
									</div><!-- third-column -->
								</div><!-- columns -->
							</div>
						</div>
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div>
					</div>
			
					<div class="portlet-wrap" id="customer_contact_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.customerContactinformation" /></h3>
						</div><!-- portlet-header -->
						
						<div class="portlet-wrap-inner" >
							<div class="width-100per">
								<div class="columns" >
									<table>
										<tr><td class="font-weight-bold"><spring:message code="claim.label.contact" />&nbsp;&nbsp;</td>
										<td>${claimRequestConfirmationForm.activity.serviceRequest.primaryContact.firstName} ${claimRequestConfirmationForm.activity.serviceRequest.primaryContact.lastName}</td></tr>
										<tr><td></td><td>${claimRequestConfirmationForm.activity.serviceRequest.primaryContact.workPhone}</td></tr>
										<tr><td></td><td>${claimRequestConfirmationForm.activity.serviceRequest.primaryContact.emailAddress}</td></tr>
									</table>
								</div>	
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
			
					<div class="portlet-wrap" id="problem_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.problemInformation" /></h3>
						</div><!-- portlet-header -->
						
						<div class="portlet-wrap-inner" >
							<div class="width-100per">
								<div class="columns two" >
									<div class="first-column">
										<dl>
											<dd><label><spring:message code="claim.label.problemCode" /></label></dd>
											<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.actualFailureCode.value}</dd>
											<br>
											<dd><label><spring:message code="claim.label.problemDetails" /></label></dd>
											<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.serviceRequest.problemDescription}</dd>
											<br> 
											<dd><label><spring:message code="claim.label.serviceProviderReferenceNumber" /></label></dd>
											<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.serviceProviderReferenceNumber}</dd>
											<br>
											<c:if test="${(claimRequestConfirmationForm.activity.reviewComments != '') && (claimRequestConfirmationForm.activity.reviewComments != null)}">
													<dd><label><spring:message code="claim.label.reviewComments" /></label></dd>
													<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.reviewComments}</dd>
												
											</c:if>
											<br>
										</dl>
									</div><!-- first-column -->
								</div>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
			
					<div class="portlet-wrap" id="technician_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.technicianInformation" /></h3>
						</div><!-- portlet-header -->
						
						<div class="portlet-wrap-inner" >
							<div class="width-100per">
								<div class="columns two" >
									<div class="first-column">
										<dl>
											<dd><label><spring:message code="claim.label.technician" />
												</label>${claimRequestConfirmationForm.activity.technician.lastName}, ${claimRequestConfirmationForm.activity.technician.firstName} </dd>
										</dl>
									</div><!-- first-column -->
								</div>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
						
					<div class="portlet-wrap" id="debrief_information">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.debriefInformation" /></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner" >
							<div class="width-100per">
								<div class="columns" >
									<dl>
										<dd><label><spring:message code="claim.label.repairDescription" /></label></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.debrief.repairDescription}</dd><br>
										<dd><label><spring:message code="claim.label.resolutionCode" /></label></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.resolutionCode}</dd><br>
										<!-- Added for CI BRD13-10-07 STARTS-->
										<dd><label><spring:message code="claim.label.dateServiceRequested" /></label></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;<util:dateFormat value="${claimRequestConfirmationForm.activity.debrief.serviceRequestedDate}" showTime="true" timezoneOffset="${claimRequestConfirmationForm.timezoneOffset}" /></dd><br>
										<!-- Added for CI BRD13-10-07 ENDS-->	
										<dd><label><spring:message code="claim.label.dateServiced(Start)" /></label></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;<util:dateFormat value="${claimRequestConfirmationForm.activity.debrief.serviceStartDate}" showTime="true" timezoneOffset="${claimRequestConfirmationForm.timezoneOffset}" /></dd><br>		
										<dd><label><spring:message code="claim.label.dateServiced(End)" /></label></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;<util:dateFormat value="${claimRequestConfirmationForm.activity.debrief.serviceEndDate}" showTime="true" timezoneOffset="${claimRequestConfirmationForm.timezoneOffset}" /></dd>		
										<dd><label><spring:message code="claim.label.printerWorkingCondition" /></label></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.debrief.deviceCondition.name}</dd>
										<dd><b><spring:message code="claim.label.pageCountAll" /></b></dd>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;${claimRequestConfirmationForm.activity.debrief.pageCountAll}</dd> 
									</dl>
								</div>
							</div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
					
					<script type="text/javascript">
						var repairCompleteFlag = document.getElementById("repairCompleteFlag").value;
						if(repairCompleteFlag=="true"){//No
							document.getElementById("debrief_information").style.display="block";
							
						}else{//Yes
							document.getElementById("debrief_information").style.display="none";
						}
					</script>
						
					<div class="portlet-wrap" id="requested_parts">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.requestedParts" /></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div id="gridCTYVRequestedParts" class="width-100per"></div>
							<div><span id="gridCTYVRequestedPartsPagingArea"></span>&nbsp;<span id="gridCDtlVSHRequestRecinfoArea"></span></div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
			
					<script type="text/javascript">
						var repairCompleteFlag = document.getElementById("repairCompleteFlag").value;
						if(repairCompleteFlag=="false"){
							//CI7 Defect # 14151 Grid header changed
							var gridCTYVRequestsPartsListGrid = new dhtmlXGridObject('gridCTYVRequestedParts');
							gridCTYVRequestsPartsListGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCTYVRequestsPartsListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='claim.headerList.partsAndTools.detail.claimThankYou'/>",6));
							gridCTYVRequestsPartsListGrid.setColTypes("ro,ro,ro,ro,ro,ro");
							gridCTYVRequestsPartsListGrid.setColSorting("str,str,str,str,str,str");
							gridCTYVRequestsPartsListGrid.setInitWidths("140,140,140,140,140,*");	
							gridCTYVRequestsPartsListGrid.setLockColVisibility("false,false,false,false,false,false");
							gridCTYVRequestsPartsListGrid.setColAlign("left,left,left,left,left,left");
							gridCTYVRequestsPartsListGrid.enableAutoWidth(true);
							gridCTYVRequestsPartsListGrid.enableAutoHeight(true);
							gridCTYVRequestsPartsListGrid.enableMultiline(true);
							gridCTYVRequestsPartsListGrid.enablePaging(true, 6, 10, "gridCTYVRequestedPartsPagingArea", true, "gridCTYVRequestedParts");
							gridCTYVRequestsPartsListGrid.init();
							gridCTYVRequestsPartsListGrid.setColumnHidden(4,true);
							gridCTYVRequestsPartsListGrid.setColumnHidden(5,true);
							gridCTYVRequestsPartsListGrid.setSkin("light");
							gridCTYVRequestsPartsListGrid.setPagingSkin("bricks");
							gridCTYVRequestsPartsListGrid.loadXMLString('${claimRequestConfirmationForm.requestedPartsListNOXML}');
							
							
						}else{
							var gridCTYVRequestsPartsListGrid = new dhtmlXGridObject('gridCTYVRequestedParts');
							gridCTYVRequestsPartsListGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCTYVRequestsPartsListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='claim.headerList.thankYouRequestedParts'/>",10));
							gridCTYVRequestsPartsListGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
							gridCTYVRequestsPartsListGrid.setColSorting("str,str,str,str,str,str,str,str,str,str");
							gridCTYVRequestsPartsListGrid.setInitWidths("100,60,80,80,80,160,160,100,120,*");	
							gridCTYVRequestsPartsListGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false,false");
							gridCTYVRequestsPartsListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
							gridCTYVRequestsPartsListGrid.enableAutoWidth(true);
							gridCTYVRequestsPartsListGrid.enableAutoHeight(true);
							gridCTYVRequestsPartsListGrid.enableMultiline(true);
							gridCTYVRequestsPartsListGrid.setColumnHidden(5,true);
							gridCTYVRequestsPartsListGrid.setColumnHidden(6,true);
							gridCTYVRequestsPartsListGrid.enablePaging(true, 5, 10, "gridCTYVRequestedPartsPagingArea", true, "gridCTYVRequestedParts");
							gridCTYVRequestsPartsListGrid.init(); 
							gridCTYVRequestsPartsListGrid.setSkin("light");
							gridCTYVRequestsPartsListGrid.setPagingSkin("bricks");
							gridCTYVRequestsPartsListGrid.loadXMLString('${claimRequestConfirmationForm.requestedPartsListXML}');
							
						}
					</script>
			
					<div class="portlet-wrap" id="additional_payment_requests">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.additionalPaymentRequests" /></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div id="gridCTYVAdditionPaymentRequest" class="width-100per"></div>
							<div><span id="gridCTYVAdditionPaymentRequestPagingArea"></span>&nbsp;<span id="gridCDtlVSHRequestRecinfoArea"></span></div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>	
					
					<script type="text/javascript">
						var repairCompleteFlag = document.getElementById("repairCompleteFlag").value;
						if(repairCompleteFlag=="false"){
							document.getElementById("additional_payment_requests").style.display="none";
						}else{
							gridCTYVAdditionPaymentRequestGrid = new dhtmlXGridObject('gridCTYVAdditionPaymentRequest');
							gridCTYVAdditionPaymentRequestGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCTYVAdditionPaymentRequestGrid.setHeader(autoAppendPlaceHolder("<spring:message code='claim.headerList.additionalPaymentRequests'/>",6));
							gridCTYVAdditionPaymentRequestGrid.setColAlign("left,left,left,left,left,left");
							gridCTYVAdditionPaymentRequestGrid.setColTypes("ro,ro,ro,ro,ro,ro");
							gridCTYVAdditionPaymentRequestGrid.setColSorting("str,str,str,str,str,str");
							gridCTYVAdditionPaymentRequestGrid.setInitWidths("140,140,140,140,140,*");
							gridCTYVAdditionPaymentRequestGrid.enableAutoWidth(true);
							gridCTYVAdditionPaymentRequestGrid.enableAutoHeight(true);
							gridCTYVAdditionPaymentRequestGrid.enableMultiline(true);
							gridCTYVAdditionPaymentRequestGrid.enablePaging(true, 5, 10, "gridCTYVAdditionPaymentRequestPagingArea", true, "gridCTYVAdditionPaymentRequest");
							gridCTYVAdditionPaymentRequestGrid.init();
							gridCTYVAdditionPaymentRequestGrid.setSkin("light");
							gridCTYVAdditionPaymentRequestGrid.setPagingSkin("bricks");
							gridCTYVAdditionPaymentRequestGrid.loadXMLString('${claimRequestConfirmationForm.additionalPaymentRequestListXML}');
						}
							
					</script>
						
					<div class="portlet-wrap" id="notes">
						<div class="portlet-header">
							<h3><spring:message code="claim.label.notes" /></h3>
						</div><!-- portlet-header -->
						<div class="portlet-wrap-inner">
							<div id="notes_grid" class="width-100per"></div>
							<div><span id="gridnotes_gridPagingArea"></span>&nbsp;<span id="gridCDtlVSHRequestRecinfoArea"></span></div>
						</div><!-- portlet-wrap-inner -->
						<div class="portlet-footer">
							<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
						</div><!-- portlet-footer -->
					</div>
			
					<script type="text/javascript">
						//jQuery(document).ready(function() {
							gridCTYVNoteListGrid = new dhtmlXGridObject('notes_grid');
							gridCTYVNoteListGrid.setImagePath("<html:imagesPath/>gridImgs/");
							gridCTYVNoteListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='claim.headerList.notes.detail'/>",4));
							gridCTYVNoteListGrid.setColAlign("left,left,left,left");
							gridCTYVNoteListGrid.setInitWidths("20,150,150,*");
							gridCTYVNoteListGrid.setColTypes("sub_row,ro,ro,ro");
							gridCTYVNoteListGrid.setColSorting("na,str,str,str");
							gridCTYVNoteListGrid.enableAutoWidth(true);
							gridCTYVNoteListGrid.enableAutoHeight(true);
							gridCTYVNoteListGrid.enableMultiline(true);
							gridCTYVNoteListGrid.enablePaging(true, 5, 10, "gridnotes_gridPagingArea", true, "notes_grid");
							gridCTYVNoteListGrid.init(); 
							gridCTYVNoteListGrid.setSkin("light");
							gridCTYVNoteListGrid.setPagingSkin("bricks");
							gridCTYVNoteListGrid.loadXMLString('${claimRequestConfirmationForm.activityNoteListXML}');
						//});
					</script>
					
				</div>	
			</div>
		</td>
	</tr>
</table>

<script type="text/javascript">
	function createNewClaim(){
		window.location.href="${createNewClaim}";
	}

     function print(){
    	 url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
 			"<portlet:param name='action' value='printThankYouPage' />"+
 			"</portlet:renderURL>";
 		    var iWidth=900;
 		    var iHeight=600;
 		    var iTop = (window.screen.availHeight-30-iHeight)/2;        
 		    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
 		    window.open(url,
 		    		'ThankYouPage',
 				    'height='+iHeight+
 				    ',innerHeight='+
 				    iHeight+',width='+
 				    iWidth+',innerWidth='+
 				    iWidth+',top='+iTop+
 				    ',left='+iLeft+
 				    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
         
     }

     // The following code is used to check to see if the page is original load or reloading
     // Sends data to Omniture to notify of elapsed time and do not want to submit multiple
     var refresh_prepare = 1;

      window.onload = function checkRefresh()
     {
     	// Get the time now and convert to UTC seconds
     	var today = new Date();
     	var now = today.getUTCSeconds();

     	// Get the cookie
     	var cookie = document.cookie;
     	var cookieArray = cookie.split('; ');

     	// Parse the cookies: get the stored time
     	for(var loop=0; loop < cookieArray.length; loop++)
     	{
     		var nameValue = cookieArray[loop].split('=');
     		// Get the cookie time stamp
     		if( nameValue[0].toString() == 'CONFIRM' )
     		{
     			var cookieTime = parseInt( nameValue[1] );
     		}
     		// Get the cookie page
     		else if( nameValue[0].toString() == 'CONFIRMP' )
     		{
     			var cookieName = nameValue[1];
     		}
     	}

     	if( cookieName &&
     		cookieTime &&
     		cookieName == escape(location.href) &&
     		Math.abs(now - cookieTime) < 5 )
     	{
     		// Refresh detected - Do nothing

     		//alert("Reloading Page"); 
     	}else{
	       	 var elapsedCreateTime= "${elapsedCreateTime}";
	       	 if (elapsedCreateTime != ""){
		       	 //Omniture code here
	            callOmnitureCompleteClaimAction(elapsedCreateTime);
	         }
	   	}	

      }

     window.onunload = function prepareForRefresh()
     {
     	if( refresh_prepare > 0 )
     	{
     		// Turn refresh detection on so that if this
     		// page gets quickly loaded, we know it's a refresh
     		var today = new Date();
     		var now = today.getUTCSeconds();
     		document.cookie = 'CONFIRM=' + now + ';';
     		document.cookie = 'CONFIRMP=' + escape(location.href) + ';';
     	}
     	else
     	{
     		// Refresh detection has been disabled
     		document.cookie = 'CONFIRM=;';
     		document.cookie = 'CONFIRMP=;';
     	}
     }

          
</script>

