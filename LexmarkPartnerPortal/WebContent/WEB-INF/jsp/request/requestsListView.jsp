<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>


<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRTYPE"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRNUMBER"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.viewOrCloseout"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.ACTIVITYID"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.BACKURL"%>
<%@page import="javax.portlet.PortletSession"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />

<!-- The below line positioned the debrief pop up in the center -->
<style>
.ie7 .button22, .ie7 .button_cancel22 {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<style type="text/css">
.buttonContainer{
	margin-top: 0px;
}
.ui-dialog 
{
position:fixed;
}
.step{display:block!important;font-size: 18px!important;}
.doLine1{
	border-top:1px dashed #cccccc;
	margin:2px 0px 2px 0px;
	height: 1px;
	overflow:hidden
}

.radioCss1{
	padding-left:10px;
	padding-top:5px;
	padding-bottom:5px;
}
/* Added for defect 10464 */
.filterByType_label {
 	width:90%;
 	float:left;
}
.ie7 .filterByType_label {
 	width:86%;
 	float:left;
 	padding-left:6px;
}
</style>
<!--[if IE 7]>
<style>
.scrollTable{	
	max-height:240px!important;
	
}
</style>
<![endif]-->
<!--[if IE 8]>
<style>
.scrollTable{
	max-height: 240px!important;
}
</style>
<![endif]-->
<!-- Below Render URL Added for CI BRD13-10-01 -->
<portlet:renderURL var="saveAsDraftDetailURL">
	<portlet:param name='action' value='retrieveSaveAsDraftDetail' />
</portlet:renderURL>
<portlet:renderURL var="claimRequestDetailURL">
	<portlet:param name='action' value='retrieveClaimDetail' />
</portlet:renderURL>
<portlet:renderURL var="serviceRequestDetailURL">
	<portlet:param name='action' value='showRequestDetailPage' />
</portlet:renderURL>
<portlet:renderURL  var="updateRequestUrl">
	<portlet:param name="action" value="showUpdateRequestPage" />
	<portlet:param name="requestFromPage" value="requestsListView" />
</portlet:renderURL>
<portlet:renderURL  var="createChildSR">
	<portlet:param name="action" value="createChildSRPage" />
	<portlet:param name="requestFromPage" value="requestsListView" />
</portlet:renderURL>
<portlet:renderURL  var="updateClaimUrl">
	<portlet:param name="action" value="showClaimUpdatePage" />
	<portlet:param name="fromPage" value="requestList" />
</portlet:renderURL>
<portlet:renderURL  var="showCloseOutClaimPage">
	<portlet:param name="action" value="showCloseOutClaimPage" />
	<portlet:param name="fromPage" value="requestListView" />
</portlet:renderURL>
<portlet:renderURL  var="showRequestsDebriefPage">
	<portlet:param name="action" value="showRequestsDebriefPage" />
	<portlet:param name="fromPage" value="requestListView" />
</portlet:renderURL>
<style type="text/css">
	/* Landscaping Printer overrides */
	@media print {
		*, body {
			background-color:white !important;
			background:none !important;
			}
		table {
			page-break-after:left;
			}
		html, body, .gridbox_light {
			width:auto;
			overflow:visible !important;
			}	
		#wrapper {
			width:auto;
			overflow:visible;
			}	
		#top-level-navigation,div .dhx_combo_box ,input, .navbar ,.grid-controls, #topnavigation, .left-nav, #banner, #pagingArea, .portlet-footer, .portlet-chat, .utilities, img {
 			
			display:none !important;
			}
		#top-header-navigation li{
			display:none !important;
		}
		.objbox	{
			overflow:hidden !important;
			}
		.journal-content-article h2 {
			color: black !important;
			}
		.right-column {
			width:auto !important;
			margin-left:0px !important;
			}	
		.portlet-wrap-inner, .gridbox .gridbox_light {
			width:auto !important;
			height:auto;
			overflow:visible !important;
			page-break-after:left;
			}		
}

.buttonWrap {
	width:84% !important;
	word-wrap: break-word !important;
}

</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="main-wrap">

<!-- <div class="journal-content-article">
      <h2>Requests</h2>

</div> -->
<div class="error" id="errorDiv" style="display: none;"> <p> <spring:message code="exception.notAuthorized.message"/> </p></div>
	<div id="modaldialogbox" style="display: none;">
	<div class="" id="loadingDialog"><div class="columnsTwo w45"><img src="/LexmarkPartnerPortal/images/attention.png" class="green-check-img" ></img></div>	
	<div class="floatL">
	  <p><spring:message code="requestInfo.message.pendingDebriefMessage1"/><br/>
      <spring:message code="requestInfo.message.pendingDebriefMessage2"/></p>
	</div>
	</div>
	 <div class="buttonContainer popupBtnCont">
	  <button id="draftPopupOk" class="button" onclick="pendingDebriefClaims();"><spring:message code="requestInfo.message.reviewDebrief"/></button>
      <button id="draftPopupOk" class="button" onclick="contiunueWtAllClaims();"><spring:message code="requestInfo.message.continueWithoutDebrief"/></button>
     </div>   								
	</div>

	<div class="content">
	
		<div class="left-nav">
			<div id="filterContainer" class="left-nav-inner overflow-hidden" >
			
				
			<!-- table class="displayGrid">
              <thead>
                <tr><th>&nbsp;</th>
                </tr></thead>
              
            </table -->
				<!-- left-nav-header -->
				<div>
					<c:if test="${requestListForm.createClaimFlag}">
					<div class="div-style25">
						<a href="javascript:showGlobalPartnerAssetSectionView();" class="button buttonWrap" ><spring:message code="button.createClaim"/></a>
					</div>
					</c:if>
					
					<c:if test="${requestListForm.uploadClaimFlag}">
					<div class="div-style26">
						<a href="#" onclick="javascript:downloadClaimsRequestsView('downloadClaim');return false;" onmouseover="title='<spring:message code="button.downloadClaims"/>'"  class="button buttonWrap"><spring:message code="button.shortname.downloadClaim"/></a>
					</div>
					</c:if>
					
					<c:if test="${requestListForm.uploadRequestFlag}">
					<div class="div-style26">
						<a href="#" onclick="javascript:downloadClaimsRequestsView('downloadRequest');return false;" onmouseover="title='<spring:message code="button.downloadRequests"/>'" class="button buttonWrap"><spring:message code="button.shortname.downloadRequest"/></a>
					</div>
					</c:if>					
					
					<div class="left-nav-header"><h3><spring:message code="request.filters.filterByPref" /></h3></div>
					<div class="radioCss">
						<input id="filter1AllRequests" type="radio" name="queryType" value="All" checked="checked">
						<label for="filter1AllRequests"><spring:message code="request.filter.allRequests" /></label>
						<br>
						<input id="filter1MyRequests" type="radio" name="queryType" value="My">
						<label for="filter1MyRequests"><spring:message code="request.filter.myRequests" /></label>
						<br>
						 <!-- added for BRD 13-10-09 -->
						
						<input id="filter1PendingDebriefs" type="radio" name="queryType" value="Pending">
						<label for="filter1PendingDebriefs"><spring:message code="request.filter.myPendingDebriefs" /></label>
						<br>
						
						<!-- ends here -->				
						<c:if test="${requestListForm.displayUnassignedReqeustsFlag}">
						<input id="filter1UnassignedRequests" type="radio" name="queryType" value="Unassigned">
						<label for="filter1UnassignedRequests"><spring:message code="request.filter.unassignedRequests" /></label>
						<br>
						</c:if>
					</div>
				</div>
				
				<div class="doLine"></div>
				<div>
				<c:if test="${requestListForm.displayRequestTypeFilterFlag or requestListForm.hardwareDebriefFlag}">
				<div class="left-nav-header"><h3><spring:message code="request.filters.filterByType" /></h3></div>
				</c:if>
					<div class="radioCss">
				<%--Changes MPS 2.1 Hardware debrief
				--%>
				<c:if test="${requestListForm.displayRequestTypeFilterFlag}">
						<input id="filter2AllRequests" type="radio" name="requestType" value="All" checked="checked"/>
						<label for="filter2AllRequests"><spring:message code="request.filter.all" /></label>
						<br/>
						<input id="filter2ClaimRequests" type="radio" name="requestType" value="Claim Request"/>
						<label for="filter2ClaimRequests"><spring:message code="request.filter.claimRequests" /></label>
						<br/>
						<input id="filter2ServiceRequests" type="radio" name="requestType" value="Service Request"/>
						<label for="filter2ServiceRequests"><spring:message code="request.filter.serviceRequests" /></label>
						<br/>
				</c:if> 
				<%--Changes MPS 2.1 Hardware debrief
				--%>
				<c:if test="${requestListForm.hardwareDebriefFlag}">
						<%--Added for MPS 2 Wave 4  --%>
						<div class="float-left">
						<c:choose>
						<%--This section is only for checking if user has only hardware debrief access. --%>
						<c:when test="${requestListForm.hardwareDebriefFlag and not requestListForm.displayRequestTypeFilterFlag}">
						<input id="filter2MADCRequests" type="radio" name="requestType" value="MADCRequests" checked="checked">
						</c:when>
						<c:otherwise>
						<input id="filter2MADCRequests" type="radio" name="requestType" value="MADCRequests" >
						</c:otherwise>
						</c:choose>
						</div>
						<div class="filterByType_label"><label for="filter2MADCRequests"><spring:message code="request.filter.MADCRequests" />
						<img src="<html:imagesPath/>transparent.png" class="ui-icon info-icon" alt="<spring:message code="request.filter.MADCRequests" />" title="<spring:message code="request.filter.toolTip.MADCRequests" />"/>
						</label></div><div class="clear-both"></div>
						<br>
						<%--ENDS MPS 2 Wave 4  --%>
				</c:if>
					</div>
				</div>
				
				<div class="doLine1"></div>
				<div>
				<div class="left-nav-header"><h3><spring:message code="request.filters.filterByStatus" /></h3></div>
					<div class="radioCss1">
						<input id="filter3All" type="radio" name="status" value="All">
						<label for="filter3All"><spring:message code="request.filter.all" /></label>
						<br>
						<input id="filter3Open" type="radio" name="status" value="Open"  checked="checked">
						<label for="filter3Open"><spring:message code="request.filter.open" /></label>
						<br>
						<input id="filter3Closed" type="radio" name="status" value="Closed">
						<label for="filter3Closed"><spring:message code="request.filter.Closed" /></label>
						<br>
					</div>
				</div>
				<div class="doLine"></div>
				<div>
					<div class="left-nav-header"><h3><spring:message code="request.filter.byDateRangeCreated"/></h3></div>
					<table id="dateRangeFilterContainer">
						<tr>
							<!-- td style="padding-top: 1px">
								<img src="<html:imagesPath/>treeImgs/minus5.gif" style="cursor:pointer;" />
							</td-->
							<%-- ID added for MPS CR 13991  --%>
							<!-- td id="dateFilterLabel"><spring:message code="request.filter.byDateRangeCreated"/></td-->
						</tr>
						<tr>
							<td class="padding-top-3px"></td>
							<td>
								<div class="text-align-left">
									<div class="div-style19"> <!-- Changed by Arunava for LEX:AIR00071181 -->
										<span class="div-style20">
										<spring:message code="request.filter.from"/></span>
										<input class="localized-begin-date" type="text" name="localizedBeginDate" id="localizedBeginDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedBeginDate" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedBeginDate"/>
									</div>
									<div class="div-style19">
										<span class="div-style20"><spring:message code="request.filter.to"/></span>
										<input class="localized-end-date" type="text" name="localizedEndDate" id="localizedEndDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedEndDate" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedEndDate"/>
									</div>
									<div>
										<div class="buttonContainerIn">
<%-- 							             <a href="javascript:void(0)" class="button"><span><spring:message code="request.button.go"/></span></a> --%>
										 <button class="button"><span><spring:message code="request.button.go"/></span></button>
							            </div>
										<!-- </a> -->
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div><!-- left-nav-inner -->
			<div class="left-nav-footer"></div><!-- left-nav-footer -->
		</div><!-- left-nav -->
		
		<div class="right-column">
			<div class="grid-controls">
				<div id="utilDivs" class="utilities">
					<ul>
						<li class="first">
							<img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer" height="23px" width="23px" onclick="download('pdf')" title="<spring:message code="image.title.exportPDF"/>" />
						</li>
						<li>
							<img src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon cursor-pointer" height="23px" width="23px" onclick="download('csv')" title="<spring:message code="image.title.exportExcel"/>" />
						</li>
						<li>
							<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" onclick="javascript:window.print();"; title="<spring:message code="image.title.print"/>" />
						</li>
					</ul>
				</div><!-- utilities -->
				<div id="customLinksDiv" class="expand-min">
					<ul>
						<li class="first">
							<img src="<html:imagesPath/>transparent.png" class="ui-icon minimize-icon cursor-pointer" height="25px" width="25px" onclick="minimizeAll()" title="<spring:message code="image.title.minimizeAll"/>"/>
							&nbsp;<a href="javascript:void(0)" onclick="minimizeAll()" title="<spring:message code="image.title.minimizeAll"/>"><spring:message code="label.minimizeAll"/></a>
						</li>
						<li>
							<img src="<html:imagesPath/>transparent.png" class="ui-icon expand-icon cursor-pointer" height="25px" width="25px" onclick="expandAll()" title="<spring:message code="image.title.expandAll"/>"/>
							&nbsp;<a href="javascript:void(0)" onclick="expandAll()" title="<spring:message code="image.title.expandAll"/>"><spring:message code="label.expandAll"/></a>
						</li>
						<!-- Changes for defect no.10844 -->
						<li>
							<%-- <a href="javascript:void(0);" id='headerImageButton' title="<spring:message code="image.title.customize"/>">
								<img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" />
							</a><a href="javascript:void(0);" id='headerMenuButton' title="<spring:message code="image.title.customize"/>"><spring:message code="label.customize"/></a> --%>
							<a href="javascript:void(0);" id='headerMenuButton' title="<spring:message code="image.title.customize"/>">
							<img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" height="25" width="25"  />
							<spring:message	code="label.customize" />
						</a>
						</li>
						<!-- END -->
						<li>
							<img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon cursor-pointer" height="25px" width="25px" onclick="doReset()" title="<spring:message code="image.title.reset"/>"/>
							<a href="javascript:doReset();" id="resetGridSetting" title="<spring:message code="image.title.reset"/>"><spring:message code="label.reset"/></a>
						</li>
					</ul>
				</div><!-- expand-min -->
				<div class="clear-right"></div><!-- clear -->
			</div><!-- grid-controls -->
		
			<div class="portlet-wrap rounded">
				<div class="portlet-wrap-inner">
					<%--
					Commented in MPS 2 Wave 4
					it will be added through script.
			
		 <div id="gridRLVRequestList" class="gridbox gridbox_light" ></div>
					 
					 --%>
					<div id="loadingNotification" class='gridLoading'>
						<img src="<html:imagesPath/>gridloading.gif" /><br>
					</div>
					<div id="pagingSection">
						<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
					</div>
				</div>
				<br class="clear-both" /> 
				<!-- portlet-wrap-inner -->
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div><!-- portlet-wrap -->
		</div><!-- right-column -->
	</div><!-- content -->
	<form action="hardwareinstallation" id="hwInstallForm" method="post">
		<input type="hidden" name="<%=SRTYPE%>" id="<%=SRTYPE%>"/>
		<input type="hidden" name="<%=SRNUMBER%>" id="<%=SRNUMBER%>"/>
		<input type="hidden" name="<%=viewOrCloseout%>" id="<%=viewOrCloseout%>"/>
		<input type="hidden" name="<%=ACTIVITYID%>" id="<%=ACTIVITYID%>"/>
		<input type="hidden" name="<%=TIMEZONE_OFFSET%>" id="<%=TIMEZONE_OFFSET%>"/>
		<input type="hidden" name="<%=BACKURL%>" id="<%=BACKURL%>"/>
	</form>
	<%--Changes for Jun release MPS 2.1 10913 --%>
	<div style="display:none;">
		<div id="commentsDiv">
		<div id="commentErrorDiv" class="error" style="display:none;">
			<p><spring:message code="requestInfo.hardwareDebreief.common.reject"/></p>
		</div>
		<ul>
			<li class="first" class="list-style">
			<div class="list-div1"><spring:message code="claim.label.comments"/>: </div>
			 <div class="list-div2"> <textarea id="reject_textArea" class="width-175px"></textarea></div>
			 <div class="clear-both"></div>
			 </li>
			<li class="buttonContainer" class="list-style">
			<button class="button button22"  id="ok_butn" onclick="commentConfirm()"><spring:message code="button.ok"/></button><button class="button_cancel button_cancel22" onClick="closeDialog()"><spring:message code="button.cancel"/></button>
			</li>
		
		</ul>
		
		</div>
	</div>
	<%--Ends Changes for Jun release MPS 2.1 10913 --%>
</div><!-- main-wrap -->

<script type="text/javascript">

var backFromDetail="${back}";

function contiunueWtAllClaims()
{
	 var url = requestsQueryOperator.getQueryURL();
	
	 requestListGrid.clearAndLoad(url);

	 jQuery("#modaldialogbox").dialog("close");
	 jQuery(".content").show();
	 jQuery("label[for=filter2ServiceRequests]").show();
	 if($('label[for=filter2ServiceRequests]').next('br').length==0){
	 $('label[for=filter2ServiceRequests]').after('<br/>');
	 }
		$("#filter2ServiceRequests").live().next('span').show();
	 showHiddenLeftNavSection();
	
}

function hideALeftNavSection()
{
		jQuery(".doLine1").hide();
		jQuery(".radioCss1").hide();
		jQuery("label[for=filter2ServiceRequests]").hide();
		$("#filter2ServiceRequests").live().next('span').hide();
		$('label[for=filter2ServiceRequests]').next('br').remove();
}



//ended here

function pendingDebrief(){
	jQuery(".content").hide();

	dialog = jQuery("#modaldialogbox").dialog({
		autoOpen: false,
		open: function() {jQuery(".ui-dialog-titlebar-close").hide();},
		modal: true,
		closeOnEscape: false,
		title: '<spring:message code="request.filter.myPendingDebriefs"/>',
		resizable: false,
		draggable: false,
		position: "center",
		width: 450,
		height: 400				
		});
	
	jQuery("#modaldialogbox").dialog("open");
	jQuery("#modaldialogbox").show();
}
	

 
function pendingDebriefClaims()
    {
		//alert("Hello");
		jQuery("#filter1PendingDebriefs").attr('checked', 'checked');
		var url = requestsQueryOperator.getQueryURL();	
	    requestListGrid.clearAndLoad(url);
	   jQuery("#modaldialogbox").dialog("close");
	    jQuery(".content").show();
		//dialog.dialog("close");
		//jQuery(".content").show();
		jQuery(".doLine1").hide();
		jQuery(".radioCss1").hide();
		//jQuery("#filter1PendingDebriefs").attr('checked', 'checked');
		jQuery("label[for=filter2ServiceRequests]").hide();
		$('label[for=filter2ServiceRequests]').next('br').remove();
		$("#filter2ServiceRequests").live().next('span').hide();
		dialog.dialog("close");
	}
<%-- Changes MPS 2.1 wAve 4 --%>
var confirmationMessages={
		ConfirmMsg1:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessage" javaScriptEscape="true"/>",
		ConfirmTitle:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageTitle" javaScriptEscape="true"/>",
		alertSuccess:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageSuccess" javaScriptEscape="true"/>",
		alertFailure:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageFailure" javaScriptEscape="true"/>",
		ConfirmMsg2:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageToClose" javaScriptEscape="true"/>",
		commentTitle:"<spring:message code="claim.label.comments" javaScriptEscape="true"/>"
}
var requestTypeList = [];
var requestStatusList = [];
var claimRequestStatusList = [];
var serviceRequestStatusList = [];
var serviceTypeList = [];

var requestStatusDetailList = [];
var claimRequestStatusDetailList = [];
var serviceRequestStatusDetailList = [];
var childSRMap = [];

/*Added for Debrief Status map MPS 2.1*/
var debriefRequestStatusList = [];

<c:forEach items="${requestListForm.requestTypeMap}" var="requestType" varStatus = "status" >
	requestTypeList[${status.index}] = ["${requestType.key}","${requestType.value}"];
</c:forEach>

<c:forEach items="${requestListForm.requestStatusMap}" var="requestStatus" varStatus = "status" >
	requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
</c:forEach>
<c:if test="${requestListForm.claimRequestStatusMap != null}">
	<c:forEach items="${requestListForm.claimRequestStatusMap}" var="claimRequestStatus" varStatus = "status" >
		claimRequestStatusList[${status.index}] = ["${claimRequestStatus.key}","${claimRequestStatus.value}"];
	</c:forEach>	
</c:if>
<c:if test="${requestListForm.serviceRequestStatusMap != null}">
	<c:forEach items="${requestListForm.serviceRequestStatusMap}" var="serviceRequestStatus" varStatus = "status" >
		serviceRequestStatusList[${status.index}] = ["${serviceRequestStatus.key}","${serviceRequestStatus.value}"];
	</c:forEach>	
</c:if>

<c:forEach items="${requestListForm.serviceTypeMap}" var="serviceType" varStatus = "status" >
	serviceTypeList[${status.index}] = ["${serviceType.key}","${serviceType.value}"];
</c:forEach>

<c:forEach items="${requestListForm.requestStatusDetailMap}" var="requestStatusDetail" varStatus = "status" >
	requestStatusDetailList[${status.index}] = ["${requestStatusDetail.key}","${requestStatusDetail.value}"];
</c:forEach>
<c:if test="${requestListForm.claimRequestStatusDetailMap != null}">
	<c:forEach items="${requestListForm.claimRequestStatusDetailMap}" var="claimRequestStatusDetail" varStatus = "status" >
		claimRequestStatusDetailList[${status.index}] = ["${claimRequestStatusDetail.key}","${claimRequestStatusDetail.value}"];
	</c:forEach>
</c:if>
<c:if test="${requestListForm.serviceRequestStatusDetailMap != null}">
	<c:forEach items="${requestListForm.serviceRequestStatusDetailMap}" var="serviceRequestStatusDetail" varStatus = "status" >
		serviceRequestStatusDetailList[${status.index}] = ["${serviceRequestStatusDetail.key}","${serviceRequestStatusDetail.value}"];
	</c:forEach>
</c:if>

<c:if test="${requestListForm.allowChildSR != null}">
<c:forEach items="${requestListForm.allowChildSR}" var="allowChildSRDetail" varStatus = "status" >
	childSRMap[${status.index}] = ["${allowChildSRDetail.key}","${allowChildSRDetail.value}"];
</c:forEach>
</c:if>

/*Added for Debrief Status map MPS 2.1*/
<c:if test="${requestListForm.debriefRequestStatusMap != null}">
	<c:forEach items="${requestListForm.debriefRequestStatusMap}" var="debriefRequestStatus" varStatus = "status" >
		debriefRequestStatusList[${status.index}] = ["${debriefRequestStatus.key}","${debriefRequestStatus.value}"];
	</c:forEach>
</c:if>
var i=0;
var requestList = [];
<c:if test="${requestListLOV != null}">
<c:forEach items="${requestListLOV}" var="requestList" varStatus = "status" >
	requestList[i] = ["${requestList.key}","${requestList.value}"];
	i++;
</c:forEach>
</c:if>
var gridInitParamsAllClaimServiceRequests={};
var gridInitParamsMADCRequests={};
var gridQueryParams={};

		//House Number, County and District added for CI BRD 13-10-08--STARTS
		// Changes for CI7 14-07-05 START
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[0]%>"]=",<spring:message code='request.headers.new'/>";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[1]%>"]=",&nbsp;,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,,,";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[3]%>"]="20,130,130,100,110,100,100,100,130,110,170,130,100,100,100,100,100,100,100,140,140,200,200,100,80,80,90,90,180,180,100,100,100,100,100,100,100,100";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[5]%>"]="na,str,na,na,str,na,str,str,str,na,str,str,na,str,na,str,str,na,str,na,na,na,na,na,na,na,na,na,na,na,str,str,str,str,str,str,str,str";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[6]%>"]="2";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[7]%>"]="1,des";
		gridInitParamsAllClaimServiceRequests["<%=gridConfigurationValues[8]%>"]="7,10,11,12,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37";
		gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>1"]=requestTypeList;
		gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>2"]=requestStatusList;
		gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>3"]=requestStatusDetailList;
		gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>4"]=serviceTypeList;
		gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>5"]=childSRMap
		
		gridInitParamsAllClaimServiceRequests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveRequestList"></portlet:resourceURL>";
		gridInitParamsAllClaimServiceRequests["headingMessage"]="<spring:message code="requestInfo.heading.hardwareOrder.hardwareOrders"/>";
		gridInitParamsAllClaimServiceRequests["creationId"]="gridRLVRequestList";	
		gridInitParamsAllClaimServiceRequests["downloadURL"]="<portlet:resourceURL id="downloadRequestsURL"/>";
		//House Number, County and District added for CI BRD 13-10-08--ENDS
		// Changes for CI7 14-07-05 END
		
		gridInitParamsMADCRequests["<%=gridConfigurationValues[0]%>"]=",,<spring:message code='requestInfo.requests.listHeader.MADCRequests'/>,<spring:message code="hardware.massUploadTemplate.expectedStartDate"/>";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[1]%>"]=",,#text_filter,,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[3]%>"]="30,20,130,130,110,100,100,100,100,130,110,100,130,100,100,100,100,140,140,200,200,100,80,80,80,80,80,80,80,80,80,80,80,80,80";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[4]%>"]="ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[6]%>"]="6";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[7]%>"]="2,des";
		gridInitParamsMADCRequests["<%=gridConfigurationValues[8]%>"]="4,6,8,9,10,12,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34";
		gridInitParamsMADCRequests["<%=JSON_COMBO_FILTER%>1"]=requestList;
		gridInitParamsMADCRequests["<%=JSON_COMBO_FILTER%>2"]=requestStatusList;
		gridInitParamsMADCRequests["<%=JSON_COMBO_FILTER%>3"]=debriefRequestStatusList;
		gridInitParamsMADCRequests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveOrderHardwareList"></portlet:resourceURL>&isRequests=true";
		gridInitParamsMADCRequests["headingMessage"]="<spring:message code="request.filter.MADCRequests"/>";
		gridInitParamsMADCRequests["creationId"]="hardwareRequestList";	
		gridInitParamsMADCRequests["downloadURL"]="<portlet:resourceURL id="downloadHardwareRequestsURL"/>";
<%--Ends Changes MPS 2.1 wAve 4 --%>
var requestListGrid;
<%-- INIT Grid starts--%>
	function initGrid(){
				dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
				requestListGrid = new dhtmlXGridObject(gridQueryParams["creationId"].toString());
				requestListGrid.setImagePath("<html:imagesPath/>gridImgs/");
				requestListGrid.setHeader(gridQueryParams["<%=gridConfigurationValues[0]%>"].toString());
				<%--var attachHeader=",&nbsp;,#combo_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,";--%>
				requestListGrid.attachHeader(gridQueryParams["<%=gridConfigurationValues[1]%>"].toString());
				requestListGrid.setColAlign(gridQueryParams["<%=gridConfigurationValues[2]%>"].toString());
				requestListGrid.setInitWidths(gridQueryParams["<%=gridConfigurationValues[3]%>"].toString());
				requestListGrid.setColTypes(gridQueryParams["<%=gridConfigurationValues[4]%>"].toString());
				requestListGrid.setColSorting(gridQueryParams["<%=gridConfigurationValues[5]%>"].toString());
				requestListGrid.i18n.paging.notfound = "<spring:message code='page.listNotfound'/>";
				lockColVisibility=gridQueryParams["<%=gridConfigurationValues[6]%>"].toString();
				defaultColSorting=gridQueryParams["<%=gridConfigurationValues[7]%>"].toString();
				defaultColsHidden=gridQueryParams["<%=gridConfigurationValues[8]%>"].toString();
				colsOrder=gridQueryParams["<%=gridSavingParams[0]%>"].toString();
				colsWidth=gridQueryParams["<%=gridSavingParams[1]%>"].toString();
				colsSorting=gridQueryParams["<%=gridSavingParams[2]%>"].toString();
				colsHidden= gridQueryParams["<%=gridSavingParams[3]%>"].toString();
				requestListGrid.enableAutoHeight(true);
			  	requestListGrid.enableMultiline(true);
			  	requestListGrid.setSkin("light");
				requestListGrid.enableColumnMove(true);
				requestListGrid.init();
				requestListGrid.prftInit();
				requestListGrid.setLockColVisibility(setLockColVisibilityArray());
				requestListGrid.enablePaging(true, 20, 10, "pagingArea", true);
				requestListGrid.setPagingSkin("bricks");
				requestListGrid.sortIndex = null;
				if(backFromDetail!=null && backFromDetail!=""){
					if("filterCriterias" in requestsQueryOperator){
						requestListGrid.filterValues = requestsQueryOperator["filterCriterias"];
						requestListGrid.setFiltersValue(requestsQueryOperator["filterCriterias"]);
					}
				}  
				//Dynamic combo filters
				var comboFilterIndex=calculateComboFilter();
				var comboFilterIndexSplit=comboFilterIndex.split(",");
				var countComboFilter=0;
				for(i=0;i<comboFilterIndexSplit.length;i++){
					if(comboFilterIndexSplit[i]!=""){
						countComboFilter++;
						requestList=gridQueryParams["<%=JSON_COMBO_FILTER%>"+countComboFilter];
						requestListGrid.setCustomizeCombo(requestList,comboFilterIndexSplit[i]);
						
					}
				}
				//Ends dynamic combo filters
				if(colsSorting.length==0){
					requestListGrid.a_direction = requestListGrid.a_direction||"des";	
					colsSorting = gridQueryParams["<%=gridConfigurationValues[7]%>"];		
					requestListGrid.setSortImgState(true,1,requestListGrid.a_direction);
				}else{
					requestListGrid.setSortImgState(true,colsSorting.split(",")[0],colsSorting.split(",")[1]);
				}
				requestListGrid.columnChanged = true;
				requestListGrid.enableHeaderMenuForButton('headerMenuButton');
				dBPersistentFlag = false; 
				requestListGrid.loadOrder(colsOrder);
				requestListGrid.loadPagingSorting(colsSorting,1);
				requestListGrid.loadSize(colsWidth);
				if(!requestListGrid.loadHiddenColumns(colsHidden) && colsWidth=="") {
					setDefaultHiddenColumns();
				}
				dBPersistentFlag = true;
				requestListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
				
				requestListGrid.attachEvent("onXLS", function() {
					document.getElementById('pagingArea').style.visibility = 'hidden';
					document.getElementById('loadingNotification').style.display = 'block';
					if(requestListGrid.a_direction=='asc'){
						requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,defaultColSorting.split(",")[0]), 'asc');
			        }else{
			        	requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,defaultColSorting.split(",")[0]), 'des');
			        }
				});
				
				requestListGrid.attachEvent("onXLE", function() {
					setTimeout(function(){
			    		rebrandPagination();
			    	
			    	},100);
				    document.getElementById('loadingNotification').style.display = 'none';
				    document.getElementById('pagingArea').style.visibility = 'visible';
				    setTimeout(function(){
			    		rebrandPagination();
			    	
			    	},100);
				});
			
			
				requestListGrid.attachEvent("onBeforeSorting", function(index){
					callOmnitureAction('Request', 'Request List Sort');
					var a_state = requestListGrid.getSortingState();
					if(a_state[0] == (index)){
						requestListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
					}else {
						requestListGrid.a_direction = "asc" ;
						requestListGrid.columnChanged = true;
					}
					requestListGrid.sortIndex = index;
					if(requestListGrid.a_direction=='asc'){
						requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,1), 'asc');
			        }else{
			        	requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,1), 'des');
			        }	
					reloadGrid();	
					
					return true;
				});
			
				requestListGrid.attachEvent("onFilterStart", function(indexes,values){
					var manySearchString = 0;
			    	var finalSearchString = "";
			    	for(i=0;i<values.length;i++)
			    	{			    		
			    		if(values[i] != "")
			    			{		    			
			    				var str = values[i];
			    				finalSearchString = finalSearchString+str.replace("&","encodedAnd");
								finalSearchString = finalSearchString+",";
			    			}
			    		else
			    			{
			    			finalSearchString = finalSearchString+",";
			    			}
			    	}
			    	requestListGrid.filterValues =finalSearchString;
					setGridFilerTimer(reloadGrid);	
			    });
			    
				requestListGrid.attachEvent("onPaging", function(count){
					saveGridQueryParams(gridQueryParams["creationId"].toString(),requestsQueryOperator.getNeedSavedKeyValues(),function(){
						refreshGridSettingOnPage(requestListGrid);
					});		
				});
			
				requestListGrid.attachEvent("onMouseOver", function(id,ind){
					if(ind!=0){
						var style = requestListGrid.cells(id,ind).cell.style;
					    style.cssText += ";cursor: pointer;";
						return true;
					}
			    });
			
				requestListGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
					var cellValue = requestListGrid.cells(rowId,cellIndex).getValue();
						var cell=requestListGrid.cellById(rowId,getIndexOfSub_row());
						var	row = requestListGrid.getRowById(rowId);
						if(!row._expanded){
							cell.open();
						}else{
							cell.close();
						}
				});

				requestListGrid.loadXML(requestsQueryOperator.getQueryURL(),checkDebrief);

	}
	
<%-- INIT Grid Ends--%>
	function getIndexOfSub_row(){
		var col_type=gridQueryParams["<%=gridConfigurationValues[4]%>"].toString();
		var splitCol_type=col_type.split(',');
		var indexOfSub_row=0;
		for(i=0;i<splitCol_type.length;i++){
			if(splitCol_type[i]=='sub_row'){
				indexOfSub_row=i;
				break;
			}
		}
		return indexOfSub_row;
	}
	function calculateComboFilter(){
		var filterTypes=gridQueryParams["<%=gridConfigurationValues[1]%>"];
		var filterTypesSplit=filterTypes.split(",");
		var comboFilterIndexes="";
		for(i=0;i<filterTypesSplit.length;i++)
			if(filterTypesSplit[i]=='#combo_filter')
				comboFilterIndexes=comboFilterIndexes+i+",";
		return comboFilterIndexes;
		
	}

	function setLockColVisibilityArray(){
		//alert(requestListGrid.getColumnsNum());
		var lockColVisibilityArray=new Array(requestListGrid.getColumnsNum());
		
		var split_lockColVisibility=lockColVisibility.split(",");
		 for(i=0;i<lockColVisibilityArray.length;i++)
			 lockColVisibilityArray[i]="false";
		
		 for(i=0;i<split_lockColVisibility.length;i++)
			 lockColVisibilityArray[split_lockColVisibility[i]]="true";
		
		 return lockColVisibilityArray.toString();
		 
		 
	}
	
	var requestsQueryOperator = {
			queryParams:["timezoneOffset","queryType","requestType","status","beginDate","endDate","filterCriterias","direction","orderBy"],
			needSavedParams:["queryType","requestType","status","beginDate2","endDate2","filterCriterias","page"],
			page:0,
			initDates:function(){
						//initialize date input with default value
						jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
						jQuery("#localizedEndDate").val(localizeDate(todayStr));
			},
			initializePage:function(savedKeyValues){
				if(!savedKeyValues){
					this.initDates();
					return;
				}

				// initialize left filters
				jQuery("#filterContainer").find("input[type='radio']").each(function(index,element){
					if(savedKeyValues[element.name] == element.value){
						jQuery(element).attr("checked","checked");
						$('input[type="radio"]').each(function(){radioRebrandFunction(this)});
					}
				});
				
				// initialize date range filter
				if(savedKeyValues["beginDate2"] && savedKeyValues["beginDate2"].length > 0)
					jQuery("#localizedBeginDate").val(savedKeyValues["beginDate2"]);
				if(savedKeyValues["endDate2"] && savedKeyValues["endDate2"].length > 0)
					jQuery("#localizedEndDate").val(savedKeyValues["endDate2"]);

				//revert pageNum
				var first=true;
				if(savedKeyValues["page"]){
					/*requestListGrid.attachEvent("onXLE", function() {
						if(first){
							requestListGrid.changePage(savedKeyValues["page"]);
							first = false;
						}
					});*/
				}
				//if(requestListGrid!=null){
					// initialize Grid Header filter
					if(savedKeyValues["filterCriterias"] && savedKeyValues["filterCriterias"].length > 0)
						this["filterCriterias"]=savedKeyValues["filterCriterias"];
					else
						this["filterCriterias"]="";
						//requestListGrid.setFiltersValue(savedKeyValues["filterCriterias"]);
				//}
			},

			_clear:function(){
				for(var k=0;k<this.needSavedParams.length;k++){
					this[this.needSavedParams[k]] = null;
				}
				for(var k=0;k<this.queryParams.length;k++){
					this[this.queryParams[k]] = null;
				}
			},
			
			_refresh:function(){
				this._clear();
				// sets the queryType, requestType, status
				jQuery("#filterContainer").find("input[type='radio']:checked").each(function(index,element){
					requestsQueryOperator[element.name] = element.value;
				});
								
				// sets the beginDate and endDate, This two date values for query 
				this["beginDate"] = formatDateToDefault(jQuery("#localizedBeginDate").val());
				this["endDate"] = formatDateToDefault(jQuery("#localizedEndDate").val());
				// This two date values for store and restore
				this["beginDate2"] = jQuery("#localizedBeginDate").val();
				this["endDate2"] = jQuery("#localizedEndDate").val();
				
				// sets properties that from Grid
				this["filterCriterias"] = requestListGrid.filterValues;
				this["direction"] = requestListGrid.a_direction;
				requestListGrid.columnChanged = true;
				this["orderBy"] = gridQueryParams["creationId"]=='hardwareRequestList'?requestListGrid.getSortColByOffset()-1:requestListGrid.getSortColByOffset();
				this["page"] = requestListGrid.currentPage;

				this["timezoneOffset"] = timezoneOffset;				
			},
			
			//return a string of JSON that contains several key-value pairs which need to persistent
			getNeedSavedKeyValues:function(){
				this._refresh();
				
				var keyValus =	'{' ;
				for(var k=0;k<this.needSavedParams.length;k++){
					if(this[this.needSavedParams[k]]){
						keyValus += "\""+this.needSavedParams[k]+"\"" +':"'+this[this.needSavedParams[k]]+'"';
						if(k != this.needSavedParams.length-1)
							keyValus += ',';
					}
				}
				keyValus += '}';
				return keyValus;
			},
			
			getQueryURL:function(){
				this._refresh();
				
				var queryURL = gridQueryParams["<%=JSON_RESOURCE_URL%>"].toString();
				for(var k=0;k<this.queryParams.length;k++){
					if(this[this.queryParams[k]] || this.queryParams[k] == "timezoneOffset"){
						queryURL += "&" + this.queryParams[k] + "=" + this[this.queryParams[k]];
					}
				}
			    return queryURL;
			}
		};
	
	
	
	
	
	

jQuery(document).ready(function(){

	//THis is for showing the headings
	jQuery('.journal-content-article').append('<span class="step"></span>');

	var back=false;
	
	if(backFromDetail=="true"){
		
		gridQueryParams=gridInitParamsAllClaimServiceRequests;
		back=true;
	
	}else if(backFromDetail=="hw"){
		gridQueryParams=gridInitParamsMADCRequests;
		back=true;		
	}else{
		<c:choose>
		<c:when test="${requestListForm.displayRequestTypeFilterFlag}">
		
			gridQueryParams=gridInitParamsAllClaimServiceRequests;
			setMessageToBreadCrump('<spring:message code="request.filter.allRequests" />');
		</c:when>
		<c:when test="${requestListForm.hardwareDebriefFlag}">
		
			gridQueryParams=gridInitParamsMADCRequests;
			setMessageToBreadCrump('<spring:message code="request.filter.MADCRequests" />');
		</c:when>
		<c:otherwise>
			jQuery(':input').attr('disabled','disabled');
			jQuery('#errorDiv').show();
			jQuery('#customLinksDiv,#utilDivs').hide();
			jQuery('#loadingNotification,#pagingSection').hide();
			return;
		</c:otherwise>
		</c:choose>
		}

	gridQueryParams["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
	gridQueryParams["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
	gridQueryParams["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
	gridQueryParams["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
	if(back){
		var retrieveQueryParamsUrl = '<portlet:resourceURL id="retrieveQueryParams"></portlet:resourceURL>';
		retrieveQueryParamsUrl += "&gridId="+gridQueryParams["creationId"].toString()+"&timestamp=" + (new Date()).valueOf();
		jQuery.getJSON(retrieveQueryParamsUrl,function(result){
			requestsQueryOperator.initializePage(result.data);
			checkAndSetMessageToBreadcrump(result.data.requestType);
			jQuery('#loadingNotification').before('<div id="'+gridQueryParams["creationId"]+'" class="gridbox gridbox_light"></div>');
			
			initGrid();		
		});
	}else{
		jQuery('#loadingNotification').before('<div id="'+gridQueryParams["creationId"]+'" class="gridbox gridbox_light"></div>');
		requestsQueryOperator.initDates();
		initGrid();
		}
});



	
	
	function checkDebrief()
	{	
		if(requestsQueryOperator.requestType!='MADCRequests'){
			if(requestListGrid.getAllRowIds()!=""){
				var isDebrieftrue =requestListGrid.cellById(0,37).getValue();
				if (isDebrieftrue =="true")
					{
						 pendingDebrief();
					}
				}
			}
		jQuery("label[for=filter2ServiceRequests]").show();
		$("#filter2ServiceRequests").live().next('span').show();
		if($('label[for=filter2ServiceRequests]').next('br').length==0){
			$('label[for=filter2ServiceRequests]').after('<br/>');
		}
			
		if(document.getElementById("filter1PendingDebriefs").checked)
		{
		jQuery("label[for=filter2ServiceRequests]").hide();
		$('label[for=filter2ServiceRequests]').next('br').remove();
		$("#filter2ServiceRequests").live().next('span').hide();
		hideALeftNavSection();
		}			
	}

	function showHiddenLeftNavSection()
{
		jQuery(".doLine1").show();
		jQuery(".radioCss1").show();
		jQuery("label[for=filter2ServiceRequests]").show();
		if($('label[for=filter2ServiceRequests]').next('br').length==0){
		$('label[for=filter2ServiceRequests]').after('<br/>');
		}
		$("#filter2ServiceRequests").live().next('span').show();
}
	
	
	function reloadGrid(){
		clearMessage();
		refreshGridSettingOnPage(requestListGrid);
		saveGridQueryParams(gridQueryParams["creationId"].toString(),requestsQueryOperator.getNeedSavedKeyValues(),function(){
			if(gridQueryParams["creationId"].toString()!='hardwareRequestList'){
				if(requestsQueryOperator.getQueryURL().indexOf("queryType=Pending")>-1)
				{				 
				 hideALeftNavSection();
				}
				else
				{
				showHiddenLeftNavSection();
				}
			}	
			
			requestListGrid.clearAndLoad(requestsQueryOperator.getQueryURL());
		});
	}
	
	function minimizeAll() {
		callOmnitureAction('Claim Request', 'Claim Request List Minimize All');
		var pageSize = requestListGrid.rowsBufferOutSize;
		var begin = (requestListGrid.currentPage-1) * pageSize;
		var end =  requestListGrid.currentPage * pageSize - 1;

		requestListGrid.forEachRow(function(id){
			if(id >= begin && id <= end)
				requestListGrid.cellById(id,getIndexOfSub_row()).close();
		});
	}
	
	function expandAll() {
		callOmnitureAction('Claim Request', 'Claims Request List Expand All');
		var pageSize = requestListGrid.rowsBufferOutSize;
		var begin = (requestListGrid.currentPage-1) * pageSize;
		var end =  requestListGrid.currentPage * pageSize - 1;

		requestListGrid.forEachRow(function(id){
			if(id >= begin && id <= end)
				requestListGrid.cellById(id,getIndexOfSub_row()).open();
		});
	}

	function setDefaultHiddenColumns(){
		var split_defaultColsHidden=defaultColsHidden.split(",");
		for(i=0;i<split_defaultColsHidden.length;i++){
			requestListGrid.setColumnHidden(split_defaultColsHidden[i],true);
			}

	}
    function doReset(){
		callOmnitureAction('Claims Request', 'Claims Request List Reset');
		resetGridSetting(gridQueryParams["creationId"].toString(), resetCallback);  
	}

	function resetCallback() {
	    dBPersistentFlag = false;
	    clearMessage();
	    colsOrder = setDefaultColsOrder();
	    colsWidth = "px:"+gridQueryParams["<%=gridConfigurationValues[3]%>"].toString();
	    
	    colsSorting = defaultColSorting;
	    colsHidden = defaultColsHidden;
	    requestListGrid.sortIndex = defaultColSorting.split(",")[0];
	    gridToBeReset = true;
	    requestListGrid.loadOrder(colsOrder);
	   
	    requestListGrid.loadPagingSorting(colsSorting,defaultColSorting.split(",")[0]);
	    
	    requestListGrid.setSortImgState(true,defaultColSorting.split(",")[0],requestListGrid.a_direction);

	    requestListGrid.loadSize(colsWidth);
	    requestListGrid.setColumnsVisibility(setColumnVisibilityString());
		setDefaultHiddenColumns();
		requestListGrid.clearFiltersValue();
		dBPersistentFlag = true;
		var hasCombo=false;
		for (var i=0;i < requestListGrid.filters.length;i++){
			if(requestListGrid.filters[i][0].combo){
				hasCombo=true;
				break;
			}
		}
		if(!hasCombo)
			reloadGrid();	
	}
	function setDefaultColsOrder(){
		var defaultColsOrder=new Array(requestListGrid.getColumnsNum());
		for(i=0;i<defaultColsOrder.length;i++)
			defaultColsOrder[i]=i;
		return defaultColsOrder.toString();

		
		}

	function setColumnVisibilityString(){


		   var colVisibilityArray=new Array(requestListGrid.getColumnsNum());
		   var split_defaultColsHidden=defaultColsHidden.split(",");
		   for(i=0;i<colVisibilityArray.length;i++)
			   colVisibilityArray[i]="false";
		   for(i=0;i<split_defaultColsHidden.length;i++)
			   colVisibilityArray[split_defaultColsHidden[i]]="true";
		   return colVisibilityArray.toString();
			   
		}

    function download(type){
		callOmnitureAction('Claim Request', 'Request List Download - ' + type);
		if(requestListGrid.getRowId(0)==null){
			jAlert("<spring:message code='download.noDataFound'/>");
			return false;
		}
		var downloadURL=gridQueryParams["downloadURL"]+ "&timezoneOffset="+timezoneOffset;
		window.location=downloadURL+"&downloadType="+type;
	}

	function assignToMe(activityId){
		callOmnitureAction('Claim Request', 'Assign To Me');
		var assignToMeURL = "<portlet:resourceURL id='assignToMe'></portlet:resourceURL>" + "&activityId=" + activityId;
		doAjax(assignToMeURL, function(loader){
				jQuery("#trAssignToMe_" + loader.data).remove();
		}, function(loader){
			var status = parseLoader(loader);	
			showSystemError(status.message);
			return true;
		});
	}

	function showGlobalPartnerAssetSectionView(){
		callOmnitureAction('Claim Request', 'Create Claim');
		url = "<portlet:renderURL><portlet:param name='action' value='showGlobalPartnerAssetSectionView' /></portlet:renderURL>";
		window.location.href = url;
	}

	function downloadClaimsRequestsView(reportType){

		callOmnitureAction('Claim Request', 'downloadClaimsRequestsView '+ reportType);
		
		var strReportType;

		
		var msg1='<spring:message code="alert.message.downloadRequests"/>';
		var msg2='<spring:message code="alert.message.downloadClaims"/>';
		
		if(document.getElementById('filter2ServiceRequests')!=null){
		if(document.getElementById('filter2ServiceRequests').checked){
             if(reportType=='downloadClaim' ){
                 alert(msg1);
                  return false;
                 }
			}
		}
		if(document.getElementById('filter2ClaimRequests')!=null){
		if(document.getElementById('filter2ClaimRequests').checked){
            if(reportType=='downloadRequest' ){
                alert(msg2);
                 return false;
                }
			}
		}
		var downloadURL='<portlet:resourceURL id="downloadClaimsRequestsView"/>';
		window.location=downloadURL + "&downloadType=csv&reportType=" + reportType+"&timezoneOffset="+timezoneOffset;
	}	
	
	//blinding click event to all radio element for filter the result of the Grid
    jQuery("#filterContainer").find("input[type=radio]").each(function(index,radioElement){
    	//alert("element"+radioElement);
		if(radioElement.name == "requestType"){			
			jQuery(radioElement).click(function(){
				var requestType = jQuery(this).val();

				
				if(requestType == "Claim Request"){
					
					<%--Changes MPS Wave 4--%>
					setMessageToBreadCrump('<spring:message code="request.filter.claimRequests" />');
					
					if(gridQueryParams["creationId"]!='hardwareRequestList'){
						requestListGrid.setCustomizeCombo(claimRequestStatusDetailList,12);
						requestListGrid.setCustomizeCombo(claimRequestStatusList,6);
					}else{
						jQuery('#'+gridQueryParams["creationId"].toString()).remove();
						gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>2"]=claimRequestStatusList;
						gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>3"]=claimRequestStatusDetailList;
						gridQueryParams=gridInitParamsAllClaimServiceRequests;
						getGridSettingsWhenBack();
						return;
						}
					
				}else if(requestType == "Service Request"){
					
					setMessageToBreadCrump('<spring:message code="request.filter.serviceRequests" />');
					if(gridQueryParams["creationId"]!='hardwareRequestList'){
						requestListGrid.setCustomizeCombo(serviceRequestStatusDetailList,12);
						requestListGrid.setCustomizeCombo(serviceRequestStatusList,6);
					}else{
						jQuery('#'+gridQueryParams["creationId"].toString()).remove();
						gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>2"]=serviceRequestStatusList;
						gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>3"]=serviceRequestStatusDetailList;
						gridQueryParams=gridInitParamsAllClaimServiceRequests;
						getGridSettingsWhenBack();
						return;
						}
				<%-- Changes for MPS 2 Wave 4--%>
				}else if(requestType == "MADCRequests"){
					
					setMessageToBreadCrump('<spring:message code="request.filter.MADCRequests" />');
					jQuery('#'+gridQueryParams["creationId"].toString()).remove();
					gridQueryParams=gridInitParamsMADCRequests;
					getGridSettingsWhenBack();
					return;
					
						
				<%--Ends changes MPS 2 wvar 4--%>		
				}else{
					
					setMessageToBreadCrump('<spring:message code="request.filter.allRequests" />');
					if(gridQueryParams["creationId"]!='hardwareRequestList'){
						requestListGrid.setCustomizeCombo(requestStatusDetailList,12);
						requestListGrid.setCustomizeCombo(requestStatusList,6);			
					}else{
						jQuery('#'+gridQueryParams["creationId"].toString()).remove();
						<%-- 
						gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>2"]=claimRequestStatusList;
						gridInitParamsAllClaimServiceRequests["<%=JSON_COMBO_FILTER%>3"]=claimRequestStatusDetailList;
						--%>
						gridQueryParams=gridInitParamsAllClaimServiceRequests;
						getGridSettingsWhenBack();
						return;
						}
										
				}
				reloadGrid();
			});
		}else{
			jQuery(radioElement).click(reloadGrid);
		}
	});
	
	function getGridSettingsWhenBack(){
		
		showOverlay();
		var retrieveQueryParamsUrl = '<portlet:resourceURL id="retrieveQueryParams"></portlet:resourceURL>';
		retrieveQueryParamsUrl += "&gridId="+gridQueryParams["creationId"].toString()+"&timestamp=" + (new Date()).valueOf();
		jQuery.getJSON(retrieveQueryParamsUrl,function(result){
			requestsQueryOperator.initializePage(result.data);
		});
		var url="<portlet:resourceURL id="getGridSettings"/>&gridId="+gridQueryParams["creationId"].toString()+"&t="+new Date().getTime();
		jQuery.getJSON(url,function(gridSettings){
			hideOverlay();
			if(gridSettings.status=='available'){
			      
				jQuery.each(gridSettings,function(key,value){
					gridQueryParams[key]=value;								
				});
				
				jQuery('#grid_menu').remove();
				jQuery('#pagingArea,#infoArea').empty();						
				if(requestListGrid!=null){
						requestListGrid.destructor();
						requestListGrid=null;
				}
				
				jQuery('#loadingNotification').before('<div id="'+gridQueryParams["creationId"]+'" class="gridbox gridbox_light"></div>');
				initGrid();
											
			}
			
		});
		
		
	}
	
	//blinding click event
	jQuery("#dateRangeFilterContainer").find("tr > td:first").click(function(){
		var dateSelectBox=jQuery("#dateRangeFilterContainer").find("tr:last");
		if(dateSelectBox.is(":hidden")){
			jQuery(this).find("img").attr("src","<html:imagesPath/>treeImgs/minus5.gif");
			dateSelectBox.fadeIn();
		}else{
			jQuery(this).find("img").attr("src","<html:imagesPath/>treeImgs/plus5.gif");
			dateSelectBox.fadeOut();
		}
	});
	
	jQuery("#dateRangeFilterContainer").find(".button").click(reloadGrid);

	jQuery("#dateRangeFilterContainer").find("input").mouseup(function(){
		var beginDate=this.id=="localizedBeginDate" ? convertDateToString(new Date().addDays(-180)) : formatDateToDefault(jQuery("#localizedBeginDate").val());
		var endDate=this.id=="localizedEndDate" ? todayStr : formatDateToDefault(jQuery("#localizedEndDate").val());
		show_cal(this.id, beginDate, endDate);
	});

	jQuery("#dateRangeFilterContainer").find("input ~ img").mouseup(function(){
		jQuery(this).prev().mouseup();
	});

	//Added for CI BRD13-10-01 --STARTS
	function showClaimRequestDetail(srId,acId,srType,srStatus,requestTyepeis,assetID){
		srType=srType.replace('&','%26');		
		callOmnitureAction('Claim Request', 'Claim Request Number');
		if(requestTyepeis == "Draft"){
			window.location.href="${saveAsDraftDetailURL}&serviceRequestId=" + srId + "&activityId=" + acId + "&serviceType="+srType+"&requestTyepeis="+requestTyepeis+"&assetID=" + assetID+"&timezoneOffset=" + timezoneOffset;
		}else{
			window.location.href="${claimRequestDetailURL}&serviceRequestId=" + srId + "&activityId=" + acId + "&serviceType="+srType+"&requestTyepeis="+requestTyepeis+"&timezoneOffset=" + timezoneOffset;
		}
	}
	//Added for CI BRD13-10-01 --ENDS
	
	function showServiceRequestDetail(srId,acId){
		callOmnitureAction('Claim Request', 'Claim Request Number');
		window.location.href="${serviceRequestDetailURL}&serviceRequestId=" + srId + "&activityId=" + acId +"&timezoneOffset=" + timezoneOffset;
	}
	// modified by ragesree -2098
	function showCloseOutClaimPage(srId,acId,srType){
		srType=srType.replace('&','%26');
		callOmnitureAction('Claim Request', 'Claim Close Out');
		window.location.href="${showCloseOutClaimPage}&serviceRequestId=" + srId + "&activityId=" + acId + "&serviceType="+srType+"&timezoneOffset=" + timezoneOffset;
	}
	
	function showRequestsDebriefPage(srId,acId){
		callOmnitureAction('Claim Request', 'Requests Close out');
		window.location.href="${showRequestsDebriefPage}&serviceRequestId=" + srId + "&activityId=" + acId + "&timezoneOffset=" + timezoneOffset;
	}
	

	function showUpdateClaimPage(srId,acId,srType){
		srType=srType.replace('&','%26');
		callOmnitureAction('Claim Request', 'Update Claim');
		window.location.href="${updateClaimUrl}&serviceRequestId=" + srId + "&activityId=" + acId + "&serviceType="+srType+"&timezoneOffset=" + timezoneOffset;
	}
	
	function showUpdateRequestPage(srId,acId){
		callOmnitureAction('Claim Request', 'Update Request');
		window.location.href="${updateRequestUrl}&serviceRequestId=" + srId + "&activityId=" + acId + "&timezoneOffset=" + timezoneOffset;
	}
	
	function createChildSR(srId,acId,partnerId,orgId){
		//callOmnitureAction('Claim Request', 'Update Request');
		/* alert("Inside Child SR>>>" +srId);
		alert("partnerId-->>>"+partnerId); */
		window.location.href="${createChildSR}&serviceRequestId=" + srId + "&activityId=" + acId + "&partnerId=" +partnerId+ "&organizationID=" +orgId+ "&timezoneOffset=" + timezoneOffset;
	}

	<%-- Added for MPS 2.1 Hardware debreif--%>
	function redirectToDebriefViewAndCloseOut(srNumber,srType,viewOrCloseOut,activityId){
		
		jQuery('#<%=SRNUMBER%>').val(srNumber);
		jQuery('#<%=SRTYPE%>').val(srType);
		jQuery('#<%=viewOrCloseout%>').val(viewOrCloseOut);
		jQuery('#<%=ACTIVITYID%>').val(activityId);
		jQuery('#<%=TIMEZONE_OFFSET%>').val(timezoneOffset);
		if(gridQueryParams["creationId"]!='hardwareRequestList'){
			jQuery('#<%=BACKURL%>').val("/group/partner-portal/services-requests");	
		}else{
			jQuery('#<%=BACKURL%>').val("/group/partner-portal/services-requests?back=hw");	
		}
		jQuery('#hwInstallForm').submit();
		
	}

	
	<%-- ENDS Added for MPS 2.1 Hardware debreif--%>
	function showSession(){
		var DIRECT_PARTNET_FLAG = <%=session.getAttribute("flag.directPartnerFlag")%>;
		var INDIRECT_PARTNET_FLAG = <%=session.getAttribute("flag.indirectPartnerFlag")%>;
		var LOGISTICS_PARTNET_FLAG = <%=session.getAttribute("flag.logiticsPartnerFlag")%>;
		var LEXMARK_EMPLOYEE_FLAG = <%=session.getAttribute("flag.lexmarkEmployeeFlag")%>;
		var LEXMARK_FSE_FLAG = <%=session.getAttribute("flag.lexmarkFSEFlag")%>;
		var CREATE_CLAIM_FLAG = <%=session.getAttribute("flag.createClaimFlag")%>;
		var UPLOAD_CLAIM_FLAG = <%=session.getAttribute("flag.uploadClaimFlag")%>;
		var UPLOAD_REQUEST_FLAG = <%=session.getAttribute("flag.uploadRequestFlag")%>;
		var ALLOW_ADDITIONAL_PAYMENT_REQUEST_FLAG = <%=session.getAttribute("flag.allowAdditionalPaymentRequestFlag")%>;
		<%@ page import="java.util.List" %>
		<%
	 
			List<String> r = (List<String>)session.getAttribute("USERROLES_PHASE2");
			String s = "";
			if(r!= null && r.size() > 0){
				for(String t:r){
					s = s + ","+t;
				}
			}
		
		%>
		var roles = "<%=s%>";
		var status = "Only for debug, will be removed before go production.\n\n"
		+"DIRECT_PARTNET_FLAG=["+DIRECT_PARTNET_FLAG +"]\n"
		+"INDIRECT_PARTNET_FLAG=["+INDIRECT_PARTNET_FLAG+"]\n"
		+"LOGISTICS_PARTNET_FLAG=["+LOGISTICS_PARTNET_FLAG+"]\n"
		+"LEXMARK_EMPLOYEE_FLAG=["+LEXMARK_EMPLOYEE_FLAG+"]\n"
		+"LEXMARK_FSE_FLAG=["+LEXMARK_FSE_FLAG+"]\n"
		+"CREATE_CLAIM_FLAG=["+CREATE_CLAIM_FLAG+"]\n"
		+"UPLOAD_CLAIM_FLAG=["+UPLOAD_CLAIM_FLAG+"]\n"
		+"UPLOAD_REQUEST_FLAG=["+CREATE_CLAIM_FLAG+"]\n"
		+"ALLOW_ADDITIONAL_PAYMENT_REQUEST_FLAG=["+ALLOW_ADDITIONAL_PAYMENT_REQUEST_FLAG+"]\n"
		+"ROLES=["+roles+"]\n";
		jAlert(status);
	}
	<%--Changes MPS 2.1 --%>
	var popupURLS={
			acceptURL:"<portlet:resourceURL id='acceptRejectRequest'></portlet:resourceURL>"
			
	};
	function setMessageToBreadCrump(message){
		jQuery('.journal-content-article span').html(message);
		}
	function checkAndSetMessageToBreadcrump(elementName){
		
			if(elementName=='All'){
				setMessageToBreadCrump('<spring:message code="request.filter.allRequests" />');
			}else if(elementName=='Service Request'){
				setMessageToBreadCrump('<spring:message code="request.filter.serviceRequests" />');
			}else if(elementName=='Claim Requests'){
				setMessageToBreadCrump('<spring:message code="request.filter.claimRequests" />');
			}else if(elementName=='MADCRequests'){
				setMessageToBreadCrump('<spring:message code="request.filter.MADCRequests" />');
			}
		}
	<%--ENDS Changes MPS 2.1 --%>
	var backURL={backURLocation:""};
	var dialog;
</script>
<%--Changes MPS 2.1 --%>

<script type="text/javascript" src="<html:rootPath/>js/hardwareDebreif.js?version=2"></script>
<%--ENDS Changes MPS 2.1 --%>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Claim Request Page";
     addPortlet(portletName);
</script>