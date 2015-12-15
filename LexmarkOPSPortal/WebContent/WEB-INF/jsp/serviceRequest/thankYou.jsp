<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<script type="text/javascript" src='<html:rootPath/>js/trackingNumber.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript">
	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',	
   		40: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.thirty {width:100% !important}

td{
 padding-left: 1em
}

/* added for MPS breakfix	*/
.portlet-wrap-inner {
	background: none!important;
	padding: 0!important;
}

.tableHeader { background: url("/LexmarkOPSPortal/images/gridImgs/lex/header-bg.png") repeat-x scroll left top transparent; }

.tableHeader th {
	background: url("/LexmarkOPSPortal/images/gridImgs/lex/hdr_divider.gif") no-repeat scroll left center transparent;
 background: url("/LexmarkOPSPortal/images/gridImgs/lex/header-bg.png") repeat-x scroll left top transparent !ie;
	height: 20px;
	padding: 5px;
	font-weight: bold;
}

.tableContentColor td { background-color: #f1f1f1; }


.width70 { width:70%; }
.width60 { width:60%; }

.width35 { width:35%; }

.width5 { width:5%; }

.width20 { width:20%; }

.width15 { width:15%; }

.width17 { width:17%; }
.width10 { width:10%; }

div.portletBodyWrap {
	width:auto;
	overflow:hidden;
	margin: 0px 5px 10px 5px;
	padding: 5px;
}

#subnavigation {
	border-top: 0;
	padding:5px;
	overflow:hidden;
	clear:both;
	margin-top: 0px;
}

#subnavigation ul {
	margin-left: 0px;
	margin-top: -2px;	
}

#subnavigation ul li {
	display:inline-block;
	float:left;
	margin-right:3px;
}

#subnavigation ul li a {
	display:inline-block;
	padding:3px 15px;
	color:#000;
	font-size:12px;
}

#subnavigation ul li a:hover {
	text-decoration:none;
	background-color:#fff;
	border: #cdcdcd 1px solid;
	border-radius: 3px;
	-moz-border-radius: 3px;
	color:#000;
	padding:2px 14px;
	font-weight:normal;
}

#subnavigation ul li.selected a {
	text-decoration:none;
	background:#f0f0f0;
	border: #cdcdcd 1px solid;
	border-radius: 3px;
	-moz-border-radius: 3px;
	color:#000;
	padding:2px 14px;
	font-weight:bold
}
#subnavigation ul li.selected a:active { font-weight: bold!important }
/* end of addition for MPS breakfix	*/

</style>
<!--added for MPS breakfix-->
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%--include file changed for CI BRD13-10-08 --%>
<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
<!--end of addition-->

<div class="journal-content-article">
      <h1><spring:message code='requestInfo.requestHistory.heading.serviceRequests'/></h1>
      <h2 class="step" id="historyTypeName"></h2>
</div>
<div style="">
 <div style="float:left;width:98.3%;border-bottom: 1px dotted #AAAAAA;margin:5px 25px 5px 5px">
   		<div style="float:left;width:40%">
    		<img  src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" onClick="return showEmailPopup(); hideSelect();"; style="cursor:pointer";/>&nbsp; 
       		<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" onClick="return print()"; style="cursor:pointer" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
         <%-- <c:if test='${fleetManagementFlag ne "true"}'>--%>
					<div class="cloumnsTwo" id="backtomap" style="display: none; float: left; ">
						<button class="button_cancel" onclick="javascript:backToMapView();"	type="button">Back to Map</button>
					</div>
		<%--</c:if>--%>

    	</div>
    
    	<div style="float:right;width:55%;padding:0 0 8px 8px;text-align:right">
   		<c:if test="${serviceRequestType=='normalServiceRequest'}">
   		
				<button class="button" onClick="goServiceRequestHome();">&lt;&lt;<spring:message code="serviceRequest.link.goToServiceRequest"/></button>
  				
	    		<button class="button" onclick="return popupDialog();"><spring:message code="serviceRequest.description.submitServiceRquest"/></button>
	  		
	  			
  		</c:if>
  		<c:if test="${serviceRequestType=='deviceFinderServiceRequest'}">
			<button class="button" onClick="goHome();">&lt;&lt;<spring:message code="deviceFinder.link.goToServiceRequest"/></button>
		</c:if>
 	</div>
</div>
<div style="float:left;width: 98.3%">

<div id="thankYouWrapper" >
        <div id="div_thankYou">
        <div class="portlet-wrap">
			<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.thankYou" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
<!--			added for attachment exception display MPS breakfix-->
			<c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			    		<li><strong>Exception - ${entry}</strong></li>		       		
			    	</c:forEach>
      			</div>
          	</c:if>
          	<div id="exceptionsDiv" class="error" style="display: none;"></div>
<!--          	end of addition-->
		         <p class="info banner ok">
			         <spring:message code="serviceRequest.label.yourServiceRequestHasBeenSubmitted" />.&nbsp;
					<span><spring:message code="serviceRequest.listHeader.RequestNumber" />:${serviceRequestConfirmationForm.serviceRequest.serviceRequestNumber}</span>.&nbsp;
					<spring:message code="serviceRequest.label.reviewYourServiceRequestDetail" />
				
				</p>	 
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner">
				</div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div><!-- portlet-wrap -->      
        </div>
		
		<div id="div_problemInfoPrint">
		<div id="tab_problemInfo" class="portlet-wrap">
			<div class="portlet-header">
				<h3><spring:message code="message.servicerequest.problemInformation" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="columns two">
					<div id="problemInfo_firstColumn" class="first-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.problemDescription" /></dt>
							<dd>${serviceRequestConfirmationForm.serviceRequest.problemDescription}</dd>
							
							<!-- Printer Error Code Added for CI 13.10.17 -->
							<dt><spring:message code="serviceRequest.label.printerErrorCode" /></dt>
							<dd>${errorcode}</dd>
							
						</dl>
					</div><!-- first-column -->
					<div id="problemInfo_secondColumn" class="second-column">
						
						<dl>					
													
							<dt><spring:message code="serviceRequest.label.typeOfServiceNeeded" /></dt>
							<c:forEach items="${serviceRequestConfirmationForm.serviceRequest.selectedServiceDetails}" var="selectedServiceDetial" varStatus="status">				
								<c:choose>
									<c:when test="${selectedServiceDetial.serviceDetailDescription == 'Option Exchange'}">
										<c:if test="${serviceRequestConfirmationForm.serviceRequest.optionExchangeOtherDescription != null && fn:trim(serviceRequestConfirmationForm.serviceRequest.optionExchangeOtherDescription) != ''}">
											<dd><spring:message	code='serviceRequest.label.ExchangeOfOption' /></dd><br>
											<dd>&nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestConfirmationForm.serviceRequest.optionExchangeOtherDescription}</dd><br>
										</c:if>	
									</c:when>
									<c:otherwise> 
										<dd>${selectedServiceDetial.serviceDetailDescription}</dd><br>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${serviceRequestConfirmationForm.serviceRequest.otherRequestedService != null && fn:trim(serviceRequestConfirmationForm.serviceRequest.otherRequestedService) != ''}">
								<dd><spring:message	code='serviceRequest.label.Other' /></dd><br>
								<dd>&nbsp;&nbsp;&nbsp;&nbsp;${serviceRequestConfirmationForm.serviceRequest.otherRequestedService}</dd><br>
							</c:if>
						</dl>
					</div><!-- second-column -->
					<div class="third-column">
						<dl><dt>&nbsp;</dt><dd>&nbsp;</dd><dd>&nbsp;</dd><dd>&nbsp;</dd></dl>
					</div>
				</div>			
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner">
				</div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
			</div>
	</div>		
	
	<div id="div_deviceInfoPrint">
    <div id="tab_deviceInfo" class="portlet-wrap">
    	<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.deviceInformation" /></h3>
		</div>
		<div class="portlet-wrap-inner">
			<div class="columns two">
					<div class="first-column">
						<div id="divDeviceImgs" class="device">
								<img id="divImgs" src="${serviceRequestConfirmationForm.serviceRequest.asset.productImageURL}" width="150px"/>
						</div><!-- device -->
					</div><!-- first-column -->
					<div id="device_secondColumn" class="second-column">
						<dl>
							<dd><label><spring:message code="serviceRequest.label.productModel" />:</label>${serviceRequestConfirmationForm.serviceRequest.asset.productLine}</dd>
							<dd><label><spring:message code="serviceRequest.label.serialNumber" />:</label>${serviceRequestConfirmationForm.serviceRequest.asset.serialNumber}</dd>
						<c:choose>
   						<c:when test="${serviceRequestConfirmationForm.serviceRequest.asset.ipAddress==null ||serviceRequestConfirmationForm.serviceRequest.asset.hostName==null||serviceRequestConfirmationForm.serviceRequest.asset.assetTag==null|| serviceRequestConfirmationForm.serviceRequest.asset.deviceTag==null}">
   							<dd><label><spring:message code="serviceRequest.label.assetTag" />:</label><spring:message code="serviceRequest.label.n/a"/></dd>
							<dd><label><spring:message code="serviceRequest.label.ipAddress" />:</label><spring:message code="serviceRequest.label.n/a"/></dd>
   						</c:when>
  						<c:otherwise> 
							<dd><label><spring:message code="serviceRequest.label.assetTag" />:</label>${serviceRequestConfirmationForm.serviceRequest.asset.assetTag}</dd>
							<dd><label><spring:message code="serviceRequest.label.ipAddress" />:</label>
							<%--Start: Added for Defect 3924-Jan Release--%>
							<a href="javascript:gotoControlPanel('${serviceRequestConfirmationForm.serviceRequest.asset.controlPanelURL}')">
							${serviceRequestConfirmationForm.serviceRequest.asset.ipAddress}
							</a>
							<%--End: Added for Defect 3924-Jan Release--%>
							</dd>
   						</c:otherwise>
						</c:choose>
						</dl>
					</div><!-- second-column -->
					<div id="device_thirdColumn" class="third-column">
							<dl>
							<dd><label><spring:message code="serviceRequest.label.serviceAddress" />:</label><br /></dd>
							<dd>
							<div>${serviceRequestConfirmationForm.serviceRequest.serviceAddress.storeFrontName}</div>
							<util:addressOutput value="${serviceRequestConfirmationForm.serviceRequest.serviceAddress}"></util:addressOutput>
							</dd>

							<dd><label><spring:message code="requestInfo.addressInfo.label.building"/></label>
							${serviceRequestConfirmationForm.serviceRequest.serviceAddress.physicalLocation1}</dd>
							<dd><label><spring:message code="requestInfo.addressInfo.label.floor"/></label>
							${serviceRequestConfirmationForm.serviceRequest.serviceAddress.physicalLocation2}</dd>
							<dd><label><spring:message code="requestInfo.addressInfo.label.office"/></label>
							${serviceRequestConfirmationForm.serviceRequest.serviceAddress.physicalLocation3}</dd>
							</dl>
					</div><!-- third-column --> 
				</div><!-- columns -->				
				</div>
				<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div>
			</div>
	</div>
	
	<div id="div_statusPrint">
	<div id="tab_status" class="portlet-wrap">
		<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.status" /></h3>
		</div>
		<div class="portlet-wrap-inner">
	    <div style="margin-left:1em">
				<br></br>
				<b><spring:message code="serviceRequest.label.checkBackLater" /></b>
				<br></br>
		</div>
		</div><!-- portlet-wrap-inner -->	
		  <div class="portlet-footer">
		<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
    </div><!-- portlet-footer -->
	</div>
	</div>
	
   <div id="div_contactInfoPrint">
   <div id="tab_contactInfo" class="portlet-wrap">
			<div class="portlet-header">
				<h3><spring:message code="serviceRequest.label.contactInfo" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
				<div class="columns two">
					<div id="contactInfoFirstColum" class="first-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.requestorInfo" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label> ${serviceRequestConfirmationForm.serviceRequest.requestor.firstName}&nbsp;${serviceRequestConfirmationForm.serviceRequest.requestor.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label> ${serviceRequestConfirmationForm.serviceRequest.requestor.workPhone}</dd>
							<dd style="word-break:break-all;"><label><spring:message code="serviceRequest.label.email" />:</label> ${serviceRequestConfirmationForm.serviceRequest.requestor.emailAddress}</dd>
						</dl>
					</div><!-- first-column -->
					<div id="contactInfoSecondColum"  class="second-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.primaryContactInformation" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label> ${serviceRequestConfirmationForm.serviceRequest.primaryContact.firstName}&nbsp;${serviceRequestConfirmationForm.serviceRequest.primaryContact.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label> ${serviceRequestConfirmationForm.serviceRequest.primaryContact.workPhone}</dd>
							<dd style="word-break:break-all;"><label><spring:message code="serviceRequest.label.email" />:</label>${serviceRequestConfirmationForm.serviceRequest.primaryContact.emailAddress}</dd>
						</dl>
					</div><!-- second-column -->
					<c:if test="${serviceRequestConfirmationForm.serviceRequest.secondaryContact!= null}">
					<c:if test="${serviceRequestConfirmationForm.serviceRequest.secondaryContact.firstName != ''||
								  serviceRequestConfirmationForm.serviceRequest.secondaryContact.lastName != ''||
								  serviceRequestConfirmationForm.serviceRequest.secondaryContact.workPhone!=''||
								  serviceRequestConfirmationForm.serviceRequest.secondaryContact.emailAddress!=''}">
					<div id="contactInfoThirdColum" class="third-column">
						<dl>
							<dt><spring:message code="serviceRequest.label.secondaryContactinformation" /></dt>
							<dd><label><spring:message code="serviceRequest.label.name" />:</label>${serviceRequestConfirmationForm.serviceRequest.secondaryContact.firstName}&nbsp;${serviceRequestConfirmationForm.serviceRequest.secondaryContact.lastName}</dd>
							<dd><label><spring:message code="serviceRequest.label.telephone" />:</label>${serviceRequestConfirmationForm.serviceRequest.secondaryContact.workPhone}</dd>
					        <dd style="word-break:break-all;"><label><spring:message code="serviceRequest.label.email" />:</label>${serviceRequestConfirmationForm.serviceRequest.secondaryContact.emailAddress}</dd>
						</dl>
					</div><!-- third-column --> 
					</c:if>
					</c:if>
				</div><!-- columns -->
			</div><!-- portlet-wrap-inner -->	
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div><!-- portlet-wrap -->
	</div>
	
	<div id="div_notificationsPrint">
	<div id="tab_notifications"  class="portlet-wrap">
    <div class="portlet-wrap">
		<div class="portlet-header">
			<h3><spring:message code="serviceRequest.label.notifications" /></h3>
			</div><!-- portlet-header -->
			<div class="portlet-wrap-inner">
    			<table id="tab_notifications" width="100%" >
					<tr height="10">
						<td>
							<br></br>
							<b><spring:message code="serviceRequest.label.noNotification" /></b>
							<br></br>
						</td>
					</tr>		
				</table>
			</div>
		</div><!-- portlet-wrap-inner -->	
		<div class="portlet-footer">
				<div class="portlet-footer-inner">
				</div><!-- portlet-footer-inner -->
			</div><!-- portlet-footer -->
		</div><!-- portlet-wrap -->	
	</div>
<!--	added for MPS breakfix-->
	<div class="portlet-wrap" id="attachmentDIV">
		<div class="portlet-header">
			<h3><spring:message code="serviceRequest.attachment.header"/></h3>
		</div>
		<br/>
		<div id='uploadedFileDIV' class="portletBodyWrap">
			<table id="fileListTable"></table>
		</div>
		<div class="portlet-footer">
			<div class="portlet-footer-inner"></div>
		</div>
	</div>
<!--end of addition for MPS breakfix-->
</div>
</div>
<table width="100%" style="float:left;">
	<tr>
 	<td style="text-align:right;padding-right:25px">

<c:if test="${serviceRequestType=='deviceFinderServiceRequest'}">
	<button class="button" onClick="goHome();">&lt;&lt;<spring:message code="deviceFinder.link.goToServiceRequest"/></button>
</c:if>
<c:if test="${serviceRequestType=='normalServiceRequest'}">
	<button class="button" onClick="goServiceRequestHome();">&lt;&lt;<spring:message code="serviceRequest.link.goToServiceRequest"/></button>
</c:if>
</td></tr>
</table>
</div>

<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${serviceRequestConfirmationForm.backToMap}"/>
</form>
<script type="text/javascript">
	var deviceImageURL = "${serviceRequestConfirmationForm.serviceRequest.asset.productImageURL}";
	var srNum = "${serviceRequestConfirmationForm.serviceRequest.serviceRequestNumber}";
	<%--	Commented for Adding AUI popup
	function email(){
  	   callOmnitureAction('Service Request', 'Service Request Thank you Page Email');
 	   new Liferay.Popup({
    	    title: "<spring:message code='serviceRequest.label.detail'/>",
        	position: [450,50],
        	modal: true,
        	width: 550, 
        	height: 'auto',
        	xy: [100, 100],
        	onClose: function() {showSelect();},
        	url:"<portlet:renderURL
            	windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
            	"<portlet:param name='action' value='thankYouEmail' />" + 
            	"</portlet:renderURL>"
        	});
	};  --%>
	function print(){
  	    callOmnitureAction('Service Request', 'Service Request Thank you Page Print');
        var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='thankYouPrint' /></portlet:renderURL>";         
        var iWidth=900;
        var iHeight=5600;
        var iTop = (window.screen.availHeight-30-iHeight)/2;        
        var iLeft = (window.screen.availWidth-10-iWidth)/2;           
        window.open(url,
                'ServiceRequestDrilldown',
                'height='+iHeight+
                ',innerHeight='+
                iHeight+',width='+
                iWidth+',innerWidth='+
                iWidth+',top='+iTop+
                ',left='+iLeft+
                ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
    };
    function getEmailSubject(){
        var serviceRequestNumber = '${serviceRequestConfirmationForm.serviceRequest.serviceRequestNumber}';
        var emailSubject = "<spring:message code='serviceRequest.label.detail'/>"+"("+ serviceRequestNumber +")";
        return emailSubject;
    };
        
    function getEmailNotification(){
        return "<spring:message code='serviceRequest.label.detail'/>";
    };
    
    function getJSPPageName(){
        return "serviceRequestDetail";
    };
    function expandCollapesGrid(gridId){
        var isDisplay = document.getElementById(gridId).style.display;
        var showImg = document.getElementById("img_"+gridId);
    	if(isDisplay=='none'){
      	    callOmnitureAction('Service Request', 'Service Request Thank you Page Expand');
    		document.getElementById(gridId).style.display = '';
    		$('#img_'+gridId).removeClass('expand-icon');
    		$('#img_'+gridId).addClass('minimize-icon'); 
        }else{
      	    callOmnitureAction('Service Request', 'Service Request Thank you Page Collapse');
        	document.getElementById(gridId).style.display = 'none';
    		$('#img_'+gridId).addClass('expand-icon');
    		$('#img_'+gridId).removeClass('minimize-icon'); 
           }
    }
    function onSRNmbrClick(serviceRequestNumber){
  	    callOmnitureAction('Service Request', 'Service Request Thank you Page View Detail');
        var iLeft = (window.screen.availWidth-820)/2; 
        new Liferay.Popup({
             title: "<spring:message code="message.servicerequest.detail"/>",
             position: [iLeft,50], 
             modal: true,
             width: 820, 
             height: 2600,
             xy: [100, 100],
             onClose: function() {showSelect();},
             url: "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='serviceRequestDrillDownLightBox' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber
             });
    };
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
    function goBack(){
  	    callOmnitureAction('Service Request', 'Service Request Thank you Page Go back');
        	  window.history.back();
          };

    function goHome(){
  	    callOmnitureAction('Service Request', 'Service Request Thank you Page Go Home');
       		window.location.href="<portlet:renderURL></portlet:renderURL>";
     };
     
     function goServiceRequestHome(){
   	    callOmnitureAction('Service Request', 'Service Request Thank you Page Go Home');
	  	//var backUrl ="service-requests?action='backToHistory'&requestTypeStr='SERVICE_REQUESTS'"
	  	var backUrl ="<portlet:renderURL><portlet:param name='action' value='backToHistory'/><portlet:param name='requestTypeStr' value='SERVICE_REQUESTS'/></portlet:renderURL>";
	  	//alert(backUrl);
	   	window.location=backUrl;
      };
     
    function doSubmitSR(){
  	    callOmnitureAction('Service Request', 'Service Request Thank you Page Create SR');
         		window.location.href="<portlet:actionURL><portlet:param name='action' value='checkAccountSRPrivilege'/></portlet:actionURL>";
       };
    
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Thank You page";
     addPortlet(portletName);
     pageTitle="Service Request - Thank You page";
</script>

<script type="text/javascript">
jQuery(document).ready(function() {
	 var currentURL = window.location.href;
	<c:if test="${serviceRequestConfirmationForm.attachmentFileMap != null}">
	jQuery('#fileListTable',window.document).empty();
		responseText = '<thead><tr class="tableHeader">	<th class="width60">File Name</th>	<th class="width35">Size (bytes)</th></tr></thead>';
    	responseText = responseText + '<tbody>';
		<c:forEach items="${serviceRequestConfirmationForm.attachmentFileMap}" var="entry">
			responseText = responseText + '<tr class="tableContentColor">';
			responseText = responseText + '<td class="width60">'+ '<c:out value="${entry.key}" />' + '</td>';
			responseText = responseText + '<td class="width35">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
			responseText = responseText + '</tr>';
		</c:forEach>
	responseText = responseText + '</tbody>';
	jQuery('#fileListTable',window.parent.document).append(responseText);
</c:if>

<c:if test="${serviceRequestConfirmationForm.attachmentFileMap == null}">
	jQuery('#fileListTable',window.document).empty();
	document.getElementById('attachmentDIV').style.display = 'none';
</c:if>

<c:if test="${fn:length(serviceRequestConfirmationForm.attachmentFileMap) <= 0}">
	document.getElementById('attachmentDIV').style.display = 'none';
</c:if>

// this is for attachment error
jQuery('#exceptionsDiv',window.parent.document).empty();
window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
if(document.getElementById("dispalyExceptions")!= null){
	var divText = document.getElementById("dispalyExceptions").innerHTML;
	jQuery('#exceptionsDiv',window.parent.document).append(divText);
	window.parent.document.getElementById("exceptionsDiv").style.display = 'block';
	//jQuery('#validationErrors').show();
}

if(!(currentURL.indexOf('/fleet-management') == -1)){
	jQuery('#backtomap').show();
	}
});

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
</script>
<!--  AUI popup Added -->
<%-- <script>
var emailPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 650,
	height: 350,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function showEmailPopup(){
	emailPopUpWindow.show();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script> --%>
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

<script type="text/javascript">
emailFunction();
var emailPopUpWindow;
function emailFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
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
}
function showEmailPopup(){
	jQuery(window).scrollTop(0);
	emailPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}
function backToMapView(){
	showOverlay();
	//window.location.href='fleet-management';
	
	 var lat="${serviceRequestConfirmationForm.serviceRequest.serviceAddress.latitude}";
	 
		var lon="${serviceRequestConfirmationForm.serviceRequest.serviceAddress.longitude}";
//var lat=49.474685633790784;
//		var lon=1.112907825559887;
		
						var defaultArea={											
					                lat: lat,
					                lng: lon,							                		           
									

								
								};
		
		$('#backJson').val(JSON.stringify(defaultArea));
		$('#backFormMap').submit();
	
}
</script>