<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%-- This subtab.jsp is for the Request History/Change Request tab at the top --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../common/subTab.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<portlet:resourceURL var="displayAttachment" id="displayAttachment">	
</portlet:resourceURL>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<script type="text/javascript">
jQuery(document).ready( function() {
	var offsetMinute = new Date().getTimezoneOffset();
	  var timezoneOffset = (offsetMinute/60);
	  jQuery('#timezoneOffset').val(timezoneOffset);
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
	if(currentURL.indexOf('/fleet-management') == -1){
		
		
		jQuery('#gridLiDecom').show();
		jQuery('#fleet_cancel').hide();
		jQuery('#cancel').show();
	}else{
		jQuery('#gridLiDecom').hide();
		jQuery('#fleet_cancel').show();
		jQuery('#cancel').hide();
	}
//Hide the additional contact, if not selected in previous page
	if ("${manageAssetFormForDecommission.serviceRequest.secondaryContact.firstName}"=="" || "${manageAssetFormForDecommission.serviceRequest.secondaryContact.firstName}"==null) 
	{
		//document.getElementById('addiContact').style.display = "none";
		jQuery("#addiContact").hide();	
	}

	textScroll(document.getElementById('description'));
	textScroll(document.getElementById('costCenter'));
});
</script>
<script type="text/javascript">
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
	        }); --%>
};
/* Added for CI7 BRD14-02-12 */
ajaxSuccessFunction=function updateRequest(){
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
}
/* END */
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.xhdr {
       position: static!important;
}
.objbox {
       position: static!important;
}
</style>
<%-- Below URL hits the controller to submit the service request --%>
<portlet:actionURL var="decommAssetActUrl">
<portlet:param name="action" value="decommissionAsset"></portlet:param>
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">

<div id="content-wrapper">
<div class="journal-content-article">
  <h1><spring:message code="requestInfo.cm.heading.changeRequest"/> </h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
</div>
<!-- Added for CI7 BRD14-02-12 -->
<div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	<!-- END -->
        	
<form:form action="<%=decommAssetActUrl %>" method="post" modelAttribute="manageAssetFormForDecommission" id="deleteAssetForm">
<form:hidden path="submitToken" />

<span style="display: none;">
<input type="text" name="timezoneOffset" id="timezoneOffset"/>
		<input type="text" name="pageCountValue" id="pageCountValue" />
        <input type="text" name="pageCountDateValid" id="pageCountDateValid" />
        </span>
<div class="main-wrap">
      <div class="content">
      
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.decommissionAnAsset"/>  &ndash; <spring:message code="requestInfo.heading.review"/></h3>
          
      <%--Error message to be shown in error div while trying to save SR --%>    
      <div class="error" style="display: none;" id="errorDiv"> 
		<strong><spring:message code="exception.sr.save"/></strong>
      </div>
      <%--End --%>
      
		
		  <p class="info banner"><spring:message code="requestInfo.message.reviewMessage"/></p>
		
		  
		  <div class="portletBlock">
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox">
              
              <%-- Below section shows the primary contact of the requestor --%>
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="NameLabel">${manageAssetFormForDecommission.serviceRequest.primaryContact.firstName}  ${manageAssetFormForDecommission.serviceRequest.primaryContact.lastName}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="workPhoneLabel">${manageAssetFormForDecommission.serviceRequest.primaryContact.workPhone}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="emailAddressLabel">${manageAssetFormForDecommission.serviceRequest.primaryContact.emailAddress}</span> </li>
                </ul>
                
                <%-- End --%>
				
              </div>
              
 <%--This is the secondary contact div--%>
 
        
			  <div class="infoBox columnInner rounded shadow" id="addiContact">
			  
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="NameLabel2">${manageAssetFormForDecommission.serviceRequest.secondaryContact.firstName }  ${manageAssetFormForDecommission.serviceRequest.secondaryContact.lastName }</span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                <span id="workPhoneLabel2">${manageAssetFormForDecommission.serviceRequest.secondaryContact.workPhone }</span>  
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                <span id="emailAddressLabel2">${manageAssetFormForDecommission.serviceRequest.secondaryContact.emailAddress }</span></li>
                </ul>
                
              </div>
         <%-- End --%>    
              
            </div>
		  
		  
	<%--This section is for the additional information for the decommission asset request --%>
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>				
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span>${manageAssetFormForDecommission.serviceRequest.customerReferenceId}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${manageAssetFormForDecommission.serviceRequest.costCenter}</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span class="span-description" id="description">${manageAssetFormForDecommission.serviceRequest.addtnlDescription}</span>                   
				  </li>
				  <li>
                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                    <span><util:dateFormat value="${manageAssetFormForDecommission.serviceRequest.requestedEffectiveDate}">
                    </util:dateFormat></span>
                    <%-- ${manageAssetFormForDecommission.serviceRequest.requestedEffectiveDate}
                    <util:dateFormat value="${manageAssetFormForDecommission.serviceRequest.requestedEffectiveDate}" timezoneOffset="${timezoneOffset}">
                    </util:dateFormat>--%>
				  </li>
                </ul>
			</div>
    	</div>
   </div>	  
	<%-- End --%>
	<hr class="separator" />
	<%--Selected asset information start --%>
	
		  <div class="portletBlock infoBox rounded shadow">
			 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail">
				<tr>
					<td class="pModel">
						<ul>
						  <li class="center"><img src='${manageAssetFormForDecommission.assetDetail.productImageURL}' 
						  width="100" height="100" id="MyPicture" onError="image_error();"></li>
						  <li class="pModelName"><%-- ${manageAssetFormForDecommission.assetDetail.productTLI} --%>
						  ${manageAssetFormForDecommission.assetDetail.descriptionLocalLang}
						  </li>
					</ul>
					</td>
					<td class="pDetail">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="h4"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails"/></td>
								<td class="h4">&nbsp;</td>
							</tr>
							<tr>
								<td class="infoDsiplay">
									<ul class="form wordBreak">
								  <li>
									<label ><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
									${manageAssetFormForDecommission.assetDetail.serialNumber}			
								  </li>
								  <li>
									<label><spring:message code="requestInfo.assetInfo.label.productName"/> </label>
								   <%-- ${manageAssetFormForDecommission.assetDetail.productLine} --%>
								   ${manageAssetFormForDecommission.assetDetail.productTLI}
								  </li>
								  <li>
									<label><spring:message code="requestInfo.assetInfo.label.installDate"/>  </label>								   
									<util:dateFormat value="${manageAssetFormForDecommission.assetDetail.installDate}">
                					</util:dateFormat>
								  <%-- ${manageAssetFormForDecommission.assetDetail.installDate} --%>
								  </li>
								  <li>
									<label><spring:message code="requestInfo.assetInfo.label.ipAddress"/> </label>
									<%--Start: Added for Defect 3924-Jan Release--%>
									<a href="javascript:gotoControlPanel('${manageAssetFormForDecommission.assetDetail.controlPanelURL}')">${manageAssetFormForDecommission.assetDetail.ipAddress}</a>
									<%--End: Added for Defect 3924-Jan Release--%>
								  </li>
								  <li>
									<label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
									${manageAssetFormForDecommission.assetDetail.hostName}
								  </li>
								  <li>
									<label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/> </label>
								   ${manageAssetFormForDecommission.assetDetail.deviceTag}
								  </li>
									</ul>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			 </table>
			
			 
						</div>
		  
	<%--Selected asset information end --%>
	 <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.manageAsset.heading.pageCount"/></h4>
              <div  class="collapse" id="statusExpand">
                <div id="div_pageCounts">
					<div id="tab_pageCounts" style="display:block;">
						<div id="pageCounts_container" class="pageCounts_container"></div>
	    				<div class="pagination"><span id="div_pageCountsPagingArea"></span>
	    				<span id="div_pageCountsinfoArea"></span></div>
					</div>
				</div>
              </div>
            </div>
          </div>
           <div class="portletBlock">
		  <div class="columnsOne">
		  <div class="infoBox columnInner rounded shadow">
			<ul class="form installCheck">
				<li>
                <label><spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset"/></label>
				<span class="radio_confirm">${manageAssetFormForDecommission.decommAssetFlag}</span>
                </li>		
			</ul>
		  </div>
		  </div>
		  </div>
	<%-- Pickup Information Start --%>	  
		  <div id="installAssetConfirmationDtl" style="display: none" >
			<p class="info"><spring:message code="requestInfo.cm.manageAsset.heading.pickupInformation"/></p>
		  
          
		  <div class="portletBlock">
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.cm.manageAsset.heading.pickupAddress"/></h4>	
                <ul class="roDisplay">
	                	<%--<c:choose>
				<c:when test="${fleetManageDropFlag ne 'true'}">
	                	<li>
						<div>${manageAssetFormForDecommission.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForDecommission.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      <c:when test="${fleetManageDropFlag eq 'true'}"> --%>
                       <c:choose>
                      <c:when test="${manageAssetFormForDecommission.assetDetail.installAddress.lbsAddressFlag eq 'null' }">
                      <li>
						<div>${manageAssetFormForDecommission.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForDecommission.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      <c:when test="${manageAssetFormForDecommission.assetDetail.installAddress.lbsAddressFlag eq 'true' }">
                     
                      <li>
						<div>${manageAssetFormForDecommission.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForDecommission.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					<li><label><spring:message code='lbs.label.zone'/>: </label> 
                    <span>${manageAssetFormForDecommission.assetDetail.installAddress.zoneName}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      
                      <c:if test="${manageAssetFormForDecommission.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'grid level' 
                                                  ||  (manageAssetFormForDecommission.assetDetail.installAddress.levelOfDetails.toLowerCase() eq 'mix - see floor' 
                                                     &&  manageAssetFormForDecommission.assetDetail.installAddress.floorLevelOfDetails.toLowerCase() eq 'grid level')}">
                      	<li id="gridLiDecom">
							<div id="installedXYLblDiv">
							<label id="installedXYLbl">Grid X/Y : </label><label id="installedCoords"></label>
							</div>
						</li>
						</c:if>
                      
					 
                      </c:when>
                      <c:when test="${manageAssetFormForDecommission.assetDetail.installAddress.lbsAddressFlag ne 'true' }">
                      <li>
						<div>${manageAssetFormForDecommission.assetDetail.installAddress.storeFrontName}</div>						
						<util:addressOutput value="${manageAssetFormForDecommission.assetDetail.installAddress}"></util:addressOutput>
						</li>					                    
                      
                      <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
						<span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation1}</span>
                      </li>
					  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                       <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation2}</span>
                      </li>
					
					  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                    <span>${manageAssetFormForDecommission.assetDetail.installAddress.physicalLocation3}</span>
                      </li>
                      </c:when>
                      </c:choose>
                     
                     <%--  </c:when>
                      </c:choose>--%>
					 
					</ul>
              </div>
            </div>
            
 <%-- Pickup Information end --%>

</div><%--This is closing of the decom addtn infor div --%>
</div>
		  
		 <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine attachmentDescription" >${attachmentForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
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
										<td class="w70p"><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
					</c:if>
				</div>
        <!-- Add Attachments BLOCK - End --> 

          <div class="buttonContainer">
		  <button class="button_cancel" type="button" onclick="goBack();"> <spring:message code="button.back"/></button>
		 <%-- <c:if test="${fleetManagementFlag == true }"> --%>
          <span id="fleet_cancel" style="display:none;">  <button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button></span>
           <%--</c:if>
            <c:if test="${fleetManagementFlag != true }"> --%> 
           <span id="cancel" style="display:none;"> <button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWDECOMMISSIONASSETCANCEL%>');"><spring:message code="button.cancel"/></button></span>
           <%-- </c:if> --%>
            <button class="button" type="submit" onclick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWDECOMMISSIONASSETSUBMIT%>');"><spring:message code="button.submit"/></button>
          </div>
       
        <!-- MAIN CONTENT END --> 
      
    </div>
	</div>
  <!-- <div id="footer">Footer</div> -->
  </form:form>
</div>
</body>
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForDecommission.backToMap}"/>
</form>
<script type="text/javascript">
function onCancelClick() {
	//showOverlay();
	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					//window.location.href = "<portlet:renderURL></portlet:renderURL>";
					
					var lat="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLatitude}";
					
					var lon="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLongitude}";
				//var lat=49.474685633790784;
				//var lon=1.112907825559887;
					
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
};
pageCountsGrid = new dhtmlXGridObject('pageCounts_container');
pageCountsGrid.setImagePath("<html:imagesPath/>gridImgs/");
pageCountsGrid.setHeader("<spring:message code='requestInfo.heading.commonPageCounts'/>");
pageCountsGrid.setColAlign("left,left,left");
pageCountsGrid.setColTypes("ro,ro,ro,ro");
pageCountsGrid.setInitWidths("160,200,180,0");
pageCountsGrid.enableAutoWidth(true);
pageCountsGrid.enableMultiline(true);
pageCountsGrid.enableAutoHeight(true);
pageCountsGrid.init(); 
pageCountsGrid.prftInit();
pageCountsGrid.enablePaging(true, 5, 10, "div_pageCountsPagingArea", true);
pageCountsGrid.setPagingSkin("bricks");
pageCountsGrid.setSkin("light");
//alert('${requestForm.srStausXML}');
pageCountsGrid.loadXMLString('${pageCountsString}'); 
/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('in noback');
	window.history.forward(); 
}

/********Implemented for the back button***********/
function goBack()
{
	callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETREVIEW%>','<%=LexmarkSPOmnitureConstants.REVIEWDECOMMISSIONASSETBACK%>');
	var url='<portlet:renderURL><portlet:param name="action" value="goToDecomAsset"></portlet:param></portlet:renderURL>';
	jQuery("#deleteAssetForm").attr("action", url);
	jQuery("#deleteAssetForm").submit();
}

function openAttachment(fileName){
	//alert("displayAttachment12:: "+ '${displayAttachment}');
	window.location="${displayAttachment}&fileName="+fileName;
	
}
/************End**********/

/*********This is for the error div to show***********/
jQuery(document).ready(function() {

var xCordInstall='${manageAssetFormForDecommission.assetDetail.installAddress.coordinatesXPreDebriefRFV}';
var yCordInstall='${manageAssetFormForDecommission.assetDetail.installAddress.coordinatesYPreDebriefRFV}';

coordinates(xCordInstall,yCordInstall,"installed");

	jQuery('#pageCountValue').val('${pageCountsOriginalval}');
	jQuery('#pageCountDateValid').val('${pageCountsOriginaldate}');
var isError="${errorOccurred}";
var decomFlagVal="${manageAssetFormForDecommission.decommAssetFlag}";
if(isError)
	{
	jQuery("#errorDiv").show();

	}
	
	
	if(decomFlagVal!="no")
	{
		jQuery("#installAssetConfirmationDtl").show();
	}
});
image_error= function () {
	
	  document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
	  
	};
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
function coordinates(xCo,yCo,flag){
	var xCoordinate="";
	var yCoordinate="";
	var seperator="/";
	if(!(xCo && yCo))
	{
			seperator="";
	}
	if(xCo){xCoordinate=xCo;}
	if(yCo){yCoordinate=yCo;}
	if(flag=="moveTo"){
		$('#moveToCoords').html(xCoordinate+seperator+yCoordinate);
		$('#moveToAddresscoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#moveToAddresscoordinatesYPreDebriefRFV').val(yCoordinate);
	}
	else if(flag=="installed"){
		$('#installedCoords').html(xCoordinate+seperator+yCoordinate);
		$('#installcoordinatesXPreDebriefRFV').val(xCoordinate);
		$('#installcoordinatesYPreDebriefRFV').val(yCoordinate);
	}	
}

</script>