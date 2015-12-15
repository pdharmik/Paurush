<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<portlet:resourceURL var="displayAttachment" id="displayMultipleAttachment">	
</portlet:resourceURL>

<portlet:actionURL var="changeMultiAssetUrl">
	<portlet:param name="action" value="changeMultiAsset"></portlet:param>
</portlet:actionURL>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>

<script type="text/javascript">
	jQuery(document).ready( function() {

		var currentURL = window.location.href;
		//	Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		
		else
			{
			jQuery('#changeaccount').hide();
			}

	//Hide the additional contact, if not selected in previous page
		if ("${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetFormForChange.serviceRequest.secondaryContact.firstName}"==null) 
		{
			//document.getElementById('addiContact').style.display = "none";
			  jQuery("#addiContact").hide();	
		}
			

	var cha="${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag}";
	
	if("${manageAssetFormForChange.assetDetail.moveToAddress.lbsAddressFlag}" =="true"){
		//alert("zone show");
		jQuery('#zone_move').show();
	}
	else{
		
		if(currentURL.indexOf('/fleet-management') == -1){
			//alert("zone hide");
			jQuery('#zone_move').hide();
			}
		else{
			//alert("zone show");
			jQuery('#zone_move').show();
		}
	}
	
	if(currentURL.indexOf('/fleet-management') == -1){
		jQuery('#fleet_cancel').hide();
		jQuery('#cancel').show();
	}else{
		jQuery('#fleet_cancel').show();
		jQuery('#cancel').hide();
	}

	textScroll(document.getElementById('description'));
	textScroll(document.getElementById('costCenter'));
	textScroll(document.getElementById('assetCostCenterId'));
});
</script>

<%--Added for CI Defect #7853 --%>
<style>.move_type_confirmation{
    color: #000000;
    float: left;
    font-weight: bold;
    line-height: 1.8em;
    vertical-align: top;
    width:auto;
    
}
.move_type1{padding-left:0px!important;}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style>
.xhdr {
       position: static!important;
}
.objbox {
       position: static!important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
	<div id="content-wrapper">
		<div class="journal-content-article">
  			<h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
		</div>
		<div>
			<h3><strong>
      			<%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
        	</strong></h3>
        </div>
        	
		<form:form action="<%=changeMultiAssetUrl %>" method="post" modelAttribute="manageAssetFormForChange" id="changeMultiAssetForm">
			<form:hidden path="submitToken" />
			<span style="display: none;">
				<input type="text" name="timezoneOffset" id="timezoneOffset"/>
			</span>	
			<div id="dialog"></div>
			<div class="main-wrap">
      			<div class="content">
        
	          		<!-- MAIN CONTENT BEGIN -->
	          		<h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.changeAssetInformation"/>
	          		&ndash; 
	          		<spring:message code="requestInfo.heading.review"/></h3>
	          		<p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
			  		
			  		<%--Error message to be shown in error div while trying to save SR --%>    
				    <div class="info banner err" style="display: none;" id="errorDiv"> 
						<strong><spring:message code="exception.sr.save"/></strong>
				    </div>
				    <%--End --%>
	          
	          		<div class="portletBlock">            
						<div class="columnsTwo">
							<div class="infoBox columnInner rounded shadow">
								<h4><spring:message code="requestInfo.heading.currentInstallAdd"/></h4>				
			                    <ul class="roDisplay">
						                	<li>
												<div id="installStoreFrontNameLbl">${manageAssetFormForChange.assetDetail.installAddress.storeFrontName}</div>						
												<util:addressOutput value="${manageAssetFormForChange.assetDetail.installAddress}"></util:addressOutput>
											</li>					                    
			                      <%--
					                      	<li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
												<span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation1}</span>
					                      	</li>
								 			<li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
			                       				<span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation2}</span>
			                      			</li>
								
								  			<li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
			                    				<span>${manageAssetFormForChange.assetDetail.installAddress.physicalLocation3}</span>
			                      			</li>--%>
			                      		</ul>
							</div>
						</div>
	
						<!-- Move to Address -->
						<div class="columnsTwo" id="hideMoveToAddress">
							<div class="infoBox columnInner rounded shadow">
								<h4><spring:message code="requestInfo.manageAsset.heading.moveToAddr"/></h4>
								<ul class="roDisplay">
											<li>
												<div id="moveToStoreFrontNameLblf">${manageAssetFormForChange.assetDetail.moveToAddress.storeFrontName}</div>
												<util:addressOutput value="${manageAssetFormForChange.assetDetail.moveToAddress}"></util:addressOutput>
											</li>
											<%--
											<li>
												<label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
												<span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation1}</span>
						                    </li>
											<li>
												<label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
						                    	<span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation2}</span>
						                    </li>
											<li>
												<span id="zone_move"><label><spring:message code='lbs.label.zone'/>: </label>
						                        <span >${manageAssetFormForChange.assetDetail.moveToAddress.zoneName}</span></span>
						                    </li>
											<li>
												<label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
						                    	<span>${manageAssetFormForChange.assetDetail.moveToAddress.physicalLocation3}</span>
						                    </li>--%>
										</ul>
									</div>		
								</div>
							</div>
	          		<hr class="separator" />
	          		
			     	<div class="portletBlock">
			     		<%-- Section for showing the Primary Contact and Additional Cotact for the SR --%>  
						<div class="columnsTwo">
	              			<div class="columnInner rounded infoBox">
								<%-- Below section shows the primary contact of the requestor --%>
	                			<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
								<div class="borderBottomLightGray"></div>
				   				<ul class="form wordBreak">
	                  				<li>
		                    			<label><spring:message code="requestInfo.label.name"/></label>                    
		                    			<span id="NameLabel">${manageAssetFormForChange.serviceRequest.primaryContact.firstName}  ${manageAssetFormForChange.serviceRequest.primaryContact.lastName}</span>
	                  				</li>
				                    <li>
				                    	<label><spring:message code="requestInfo.label.phoneNumber"/></label>
				                    	<span id="workPhoneLabel">${manageAssetFormForChange.serviceRequest.primaryContact.workPhone}</span>
				                    </li>
	   			                    <li>
				                    	<label><spring:message code="requestInfo.label.emailAddress"/></label>
				                    	<span id="emailAddressLabel">${manageAssetFormForChange.serviceRequest.primaryContact.emailAddress}</span> 
				                    </li>			                    
	                			</ul>               
	                		<%-- End --%>
	              			</div>
				  			<div class="infoBox columnInner rounded shadow" id="addiContact">
					  			<%--This is the secondary contact div--%>
		                		<h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>		
		                		<ul class="form wordBreak">
		                  			<li>
		                    			<label><spring:message code="requestInfo.label.name"/></label>
										<span id="NameLabel2">${manageAssetFormForChange.serviceRequest.secondaryContact.firstName }  ${manageAssetFormForChange.serviceRequest.secondaryContact.lastName }</span>
			              			</li>
			              			<li>
			                			<label><spring:message code="requestInfo.label.phoneNumber"/></label>
			                			<span id="workPhoneLabel2">${manageAssetFormForChange.serviceRequest.secondaryContact.workPhone }</span>
			              			</li>
			              			<li>
			                			<label><spring:message code="requestInfo.label.emailAddress"/></label>
			                			<span id="emailAddressLabel2">${manageAssetFormForChange.serviceRequest.secondaryContact.emailAddress }</span>
			                		</li>
		                		</ul>
			                </div>
	              			<%-- End --%>
	            		</div>
	            
	            		<%--This section is for the additional information for the change multi asset request --%>
			            <div class="columnsTwo">
			            	<div class="columnInner rounded infoBox addiInfo">
			                	<h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>	
			                	<ul class="form wordBreak">
			                  		<li>
					                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
					                	<span>${manageAssetFormForChange.serviceRequest.customerReferenceId}</span>
									</li>
					                <li>
					                    <label><spring:message code="requestInfo.label.costCentre"/></label>
					                    <span id="costCenter">${manageAssetFormForChange.serviceRequest.costCenter}</span>
									</li>
					                <li>
					                    <label><spring:message code="requestInfo.label.description"/></label>
					                    <span id="description" class="span-description">${manageAssetFormForChange.serviceRequest.addtnlDescription}</span>                   
									</li>
									<li>
					                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
										<util:dateFormat value="${manageAssetFormForChange.serviceRequest.requestedEffectiveDate}">
										</util:dateFormat>
									</li>
					            </ul>
							</div>
			            </div>
			        </div>
			        <div class="portletBlock" style="margin-left:5px;">
					  <div class="columnsOne">
						  <div class="infoBox columnInner rounded shadow">
							<ul class="form installCheck" style="list-style-type:none;" style="width: 90%">
						  		<li class="" style="list-style-type:none;">
									<span for="installAsset" style="font-weight:bold;"><spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToPhysicallyMoveThisDeviceMultiSelect"/></span>
									<span class="radio_confirm">${manageAssetFormForChange.installAssetFlag}</span>
							    </li>
		                   </ul>  
       				 	  </div> 
       				 	</div> 
       				 </div>
			        <%-- End --%>
				 	<hr class="separator" />
					
					
			
					<div class="portletBlock">
						<div class="portletBlock infoBox">
		         			<c:if test="${not empty attachmentForm.attachmentList}">
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
												<td class="w70p"><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.actualFileName}');">${attachmentListDetail.displayAttachmentName}</a></td>
												<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
											</tr>
										</c:forEach>
									 </tbody>
								</table>
							</div>
							</c:if>
						</div>
				        <div class="buttonContainer">
				        	<button class="button_cancel" type="button" onclick="javascript:goBack();"><spring:message code="button.back"/></button>
				         	<span id="fleet_cancel" style="display:none;"><button class="button_cancel ie7_fix_margin" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button></span>
				          	<span id="cancel" style="display:none;"><button class="button_cancel ie7_fix_margin" type="button" onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETCANCEL%>');"><spring:message code="button.cancel"/></button></span>
				          	<button class="button" type="submit" id="btnContinue" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETBACK%>');"><spring:message code="button.submit"/></button>
				        </div>
			          	<input type="hidden" id="fleetManagementFlag" name="fleetManagementFlag" value="${fleetManagementFlag}" />  
			          	<input type="hidden" id="pageFlow" name="pageFlow" value="${pageFlow}" /> 
			        <!-- MAIN CONTENT END --> 
					</div>
				</div>
			</div>
   		</form:form>
	</div>
</body>
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForChange.backToMap}"/>
</form>

<script type="text/javascript">
function onCancelClick() {	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
		if(confirmResult){
			showOverlay();
			var lat="${manageAssetFormForChange.assetDetail.installAddress.lbsLatitude}";
			var lon="${manageAssetFormForChange.assetDetail.installAddress.lbsLongitude}";

			if("${manageAssetFormForChange.backToMap}" == ""){
				var defaultArea={											
					lat: lat,
					lng: lon							                		           
				};
				$('#backJson').val(JSON.stringify(defaultArea));
			}
			$('#backFormMap').submit();
		}else{
			return false;
		}
	});
};

/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

/***********This is for the back button**************/
function goBack()
{
	var flowPage="${pageFlow}";
	var fleetManagegmtFlag = "${fleetManagementFlag}";
	var multiAssetList = "${multiAssetList}";
	
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWCHANGEASSETBACK%>');
	var url='<portlet:renderURL><portlet:param name="action" value="goToChangeMultiAsset"></portlet:param></portlet:renderURL>&fleetManagementFlag='+fleetManagegmtFlag+'&pageFlow='+flowPage+"&multiAssetList="+multiAssetList+"&isBackFromConfirm=true";
	jQuery("#changeMultiAssetForm").attr("action", url);
	
	jQuery("#changeMultiAssetForm").submit();
}
/******************end****************************/

jQuery(document).ready(function() {
	var offsetMinute = new Date().getTimezoneOffset();
	var timezoneOffset = (offsetMinute/60);
	jQuery('#timezoneOffset').val(timezoneOffset);
	var installFlag ="${manageAssetFormForChange.installAssetFlag}"; //Added for CI Defect #7853
	
	
    if(installFlag.toLowerCase()=='no'||installFlag==''||installFlag==null)
	{
        var flowPage="${pageFlow}";
	
	    if(flowPage=="fleetMgmtMove"){
	    
			jQuery('#hideMoveToAddress').show();
		}
		else{
			
			jQuery('#hideMoveToAddress').hide();
		}
	}
	else if(installFlag.toLowerCase()=='yes')
	{
		 var flowPage="${pageFlow}";
		
		    if(flowPage=="fleetMgmtMove"){
		    	
				jQuery('#hideMoveToAddress').show();
			}
			else{
				
				jQuery('#hideMoveToAddress').hide();
			}
   	  //  jQuery('#hideMoveToAddress').show();
	}
	
	/* if(jQuery('#moveToStoreFrontNameLblf').val() == '' || jQuery('#moveToStoreFrontNameLblf').val() == null || "${manageAssetFormForChange.assetDetail.moveToAddress}" == "" || "${manageAssetFormForChange.assetDetail.moveToAddress}" == null){
		jQuery('#hideMoveToAddress').hide();
	}
	else{
		jQuery('#hideMoveToAddress').show();
	} */
	
	var isError="${errorOccurred}";
	var validationflag=false;
	if(isError)
	{
	    jQuery("#errorDiv").show();
		validationflag=true;
	}
	jQuery("#btnContinue").click(function(){
	    if(validationflag==true){
		   jQuery("#errorDiv").show();
		   jQuery(document).scrollTop(0);
		   return false;
		}
	});
});

image_error= function () {	
	document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
};
/*Method gotoControlPanel added for Defect 3924- Jan Release*/
function gotoControlPanel(url) {
	controlPanelPopUpWindow.show();
	controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
	controlPanelPopUpWindow.io.start();
};
	
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
}

function openAttachment(fileName){
	//alert("displayAttachment12:: "+ '${displayAttachment}');
	window.location="${displayAttachment}&fileName="+fileName;
	
}
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
		}
	)
});
</script>