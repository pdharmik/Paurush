<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<jsp:include page="/WEB-INF/jsp/common/validationMPS.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<style>
.inlineTitle{height:auto!important;}
.dataGrid td.label{
	width: 50%;
	text-align: right;
}
input[type="text"] {
	width: 180px;
}

.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
.ie8 .button, .ie8 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}

#customerProfilePopup .expand-min1{height:400px!important;overflow:auto!important;}
.expand-min1 ul li{list-style-type: none;};
</style>
<%-- 
<script type="text/javascript"
	src="<html:rootPath/>js/jQuery/jquery.min.js">
</script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> 
--%>

<%-- Constants to differentiate --%>
<c:set var="requestType" value="${fn:toUpperCase(srType)}" scope="request"/>
<c:set var="isHwInstallDecommission" value="false" scope="request"/>
<c:set var="hwInstall" value="${isHwInstall}" scope="request"/>
<c:choose>
			<c:when test='${fn:endsWith(requestType, "INSTALL/DECOMMISSION")}'>
				<c:set var="isHwInstallDecommission" value="true" scope="request"/>
			</c:when>
		</c:choose>
<%-- 
<c:set var="requestType" value="${srType}" scope="request"/>
--%>
<c:set var="tzOffset" value="${hardwareDebriefForm.timezoneOffset}" scope="request"/>
<%--ENDS Constants to differentiate --%>


<%-- Below URL opens up the Address in popup --%>
<portlet:renderURL  var="showAddressPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showAddressList" />
</portlet:renderURL>

<portlet:actionURL var="submitInstallDebriefRequestURL">
	<portlet:param name="action" value="submitDebriefRequest"/>
</portlet:actionURL>


<%-- Below URL opens up the Contact popup --%>
<portlet:renderURL  var="showContactPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showContactListPopUp" />
</portlet:renderURL>

<%-- Below URL opens up the chl tree in popup --%>
<portlet:renderURL var="showCHLTreePopUp"
	windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>

<%-- Below URL opens up the Contact popup --%>
<portlet:renderURL  var="showPartListPopup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="action" value="showPartListPopUp" />
</portlet:renderURL>
<portlet:resourceURL var="acceptRequest" id='acceptRequest' ></portlet:resourceURL>
  <div id="content-wrapper">
    <!-- div class="journal-content-article">
      <h2><spring:message code="requestInfo.hardwareDebreief.debriefInstall.heading"/></h2>
    </div -->
    <div class="main-wrap">
    
    	<c:if test="${exceptionOccured}">
    		<div class="error"><spring:message code="requestInfo.hardwareDebreief.debriefInstall.errorMsg"/></div>
    	</c:if>
    	<c:if test="${exceptionOccured_Detail}">
    		<div class="error"><spring:message code="requestInfo.hardwareDebreief.view.errorMsg"/></div>
    	</c:if>
    	<c:if test="${not empty savedSuccess and savedSuccess eq 'true'}">
    		<p id="successDiv" class="info banner ok"><spring:message code="requestInfo.hardwareDebreief.moveCloseOut.msg"/></p>
    	</c:if>
    
   <%--This div is for front end validations --%>
    <div style="display: none;" id="errorDiv" class="error"></div>
    
      <div class="content">
      <div class="grid-controls"> 
            <h3 class="pageTitle" style="float:left;margin-left: 15px !important;">
            <c:choose>
			<c:when test="${isHwInstallDecommission}">
				<spring:message code="requestInfo.hardwareDebreief.debriefInstallDecommission.heading"/></h3>
			</c:when>
			<c:otherwise>
			 <spring:message code="requestInfo.hardwareDebreief.debriefInstall.heading"/></h3>
			
			</c:otherwise>
		</c:choose>
            
           
            
            <div class="expand-min">
              <ul>
                <li class="first"><img class="ui-icon bck-arrow-icon" src="<html:imagesPath/>transparent.png" alt="Back to Service Orders" width="24" height="24" title="Back to Service Orders" /><a href="#" id="returnRequests"><spring:message code="button.return.to.requests"/></a> </li>
              </ul>
            </div>
          </div>
        <!-- MAIN CONTENT GOES HERE -->       
        <form:form id="hardwareDebriefForm" method="post" commandName="hardwareDebriefForm" modelAttribute="hardwareDebriefForm" action="${submitInstallDebriefRequestURL}">
        <form:input type="hidden" path="<%=TIMEZONE_OFFSET%>" id="<%=TIMEZONE_OFFSET%>" />
        <form:input type="hidden" path="submitToken"/>
        <form:input type="hidden" path="backURL"/>
        <form:input type="hidden" path="saveCloseoutFlag"/>
         <form:input type="hidden" path="srType" value="${hardwareDebriefForm.srType}"/>
          <form:input type="hidden" id="assetRemovalDateForm" path="userEnteredActivity.serviceRequest.asset.deinstRemovalDate"/>
        <c:forEach items="${hardwareDebriefForm.userEnteredActivity.cpFields}" var="cpField" varStatus="counter">
                <input type="hidden" name="userEnteredActivity.cpFields[${counter.index}].fieldName" value="${cpField.fieldName}"/>
       	</c:forEach>
        
        
        
        <!-- Request Information BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">          
          <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/requestInformationCloseOut.jsp"/>		  
        </div>
        <!-- Request Information BLOCK - End -->
        <!-- Customer Requested Items BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonDeviceDetailsAndCustomerRequestedItems.jsp"/>
        
        <!-- Customer Requested Items BLOCK - End --> 
        
        <!-- Service Information BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonServiceInformation.jsp"/>
        <!-- Service Information BLOCK - End -->
         
        <!-- Parts And Tools Used BLOCK - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partsAndTools"/></h4>
				<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.recommendedParts"/></p>
				<div class="w70p">
				<table class="displayGrid2 rounded shadow wFull">
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partNo"/></th>
								<th><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.description"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.quantity"/></th>
								<th class="w150"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.status"/></th>
								<%-- Coomented for 10408 
								<th class="w150"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.srNo"/></th>--%>
							</tr>
						</thead></table></div>
						<div class="scrollTable w70p">
						<table class="displayGrid2 rounded shadow wFull">
						<tbody>
						<c:choose>
							<c:when test="${not empty hardwareDebriefForm.userEnteredActivity.serviceRequest.parts}">
							<c:forEach items="${hardwareDebriefForm.userEnteredActivity.serviceRequest.parts}" var="partItem" varStatus="counter">
														<c:choose>
																<c:when test="${counter.index%2==0}">
																<tr class="altRow">
																</c:when>
																<c:otherwise>
																<tr>
																</c:otherwise>
												 		</c:choose>
												 		
												 		 
												 		<td class="w100">
															${partItem.partNumber}
															<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].partNumber" value="${partItem.partNumber}"/>
															
														</td>
														<td>
															${partItem.description}
															<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].description" value="${partItem.description}"/>
															
														</td>
														<td class="w100">
															<%--${partItem.orderQuantity} 
																${counter.count}--%>
															${partItem.orderQuantity}
															<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].orderQuantity" value="${partItem.orderQuantity}"/>
															
														</td>
														<c:if test="${partItem.typePrinter}">
														<td class="w150">
															<select class="w130" id="assetPartList[${counter.index}].status" name="userEnteredActivity.serviceRequest.parts[${counter.index}].status">
															<option value=""></option>
															<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForPrinterType}" var="recomendedPartlistTypeMap">
																<c:choose>
																	<c:when test="${partItem.status eq recomendedPartlistTypeMap.key }">
																	<option value="${recomendedPartlistTypeMap.key}"  selected="selected">${recomendedPartlistTypeMap.value}</option>
																	</c:when>
																	<c:otherwise>
																	<option value="${recomendedPartlistTypeMap.key}">${recomendedPartlistTypeMap.value}</option>
																	</c:otherwise>
																</c:choose>														
															</c:forEach>
															</select>
															<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].orderNumber" value="${partItem.orderNumber}"/>
															
														</td>
														</c:if>
														<c:if test="${not partItem.typePrinter}">
														<td class="w150">
															<select class="w130" id="assetPartList[${counter.index}].status" name="userEnteredActivity.serviceRequest.parts[${counter.index}].status">
															<option value=""></option>
															<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForNonPrinterType}" var="recomendedPartlistTypeMap">
																<c:choose>
																	<c:when test="${partItem.status eq recomendedPartlistTypeMap.key }">
																	<option value="${recomendedPartlistTypeMap.key}"  selected="selected">${recomendedPartlistTypeMap.value}</option>
																	</c:when>
																	<c:otherwise>
																	<option value="${recomendedPartlistTypeMap.key}">${recomendedPartlistTypeMap.value}</option>
																	</c:otherwise>
																</c:choose>														
															</c:forEach>
															</select>
															<input type="hidden" name="userEnteredActivity.serviceRequest.parts[${counter.index}].orderNumber" value="${partItem.orderNumber}"/>
															
														</td>
														</c:if>
														</tr>
														
														
											</c:forEach>
							
							</c:when>
							<c:otherwise>
							<tr><td colspan="4"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
							</c:otherwise>
						</c:choose>
						</tbody>
						
					</table>
					</div>
					<div class="lineClear"></div>
					<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.additionalParts"/></p>
					 <div class="infoBox">
                  <input type="text" size="70" name="partNumber" id="partNumber">
                  <button type="button" class="button button21" onclick="searchPart(document.getElementById('partNumber').value);" id="partSearchButton" ><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.addPart"/></button>
                  <a title="<spring:message  code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partList"/>" style="cursor:pointer" class="partListLink" onclick="popUpPartList(id)"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partList"/></a>
                  </div>
				  <span id="additionalPartsLabel" style="display: none;"><p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.additionalPartsUsed"/></p></span>
				  <c:choose>
				  	<c:when test="${fn:length(hardwareDebriefForm.userEnteredActivity.additionalPartList)>0}">
				  	<c:set var="display" value="display:block;"/>
 				  	</c:when>
 				  	<c:otherwise>
 				  	<c:set var="display" value="display:none;"/>
 				  	</c:otherwise>
				  </c:choose>
				  <table class="displayGrid2 rounded shadow wHalf" id="addPartListTable" style="${display}">
				  	<thead>
				  		<tr>
				  			<th class="w100"><spring:message code="requestInfo.label.serviceOrder.partNumber"/></th>
				  			<th><spring:message code="requestInfo.label.serviceOrder.description"/></th>
				  			<th class="w100"><spring:message code="requestInfo.label.serviceOrder.quantity"/></th>
				  			<th class="w15"><spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.status"/></th>
				  		</tr>
				  	</thead>
				  	<tbody>
				  		<c:if test="${fn:length(hardwareDebriefForm.userEnteredActivity.additionalPartList)>0}">
				  		<c:forEach var="addPartListInfo" items="${hardwareDebriefForm.userEnteredActivity.additionalPartList}" varStatus="status">
							
										<c:choose>
												<c:when test="${status.index%2==0}">
													<tr>
												</c:when>
												<c:otherwise>
													<tr class="altRow">
												</c:otherwise>
										</c:choose>
										<td>
										${addPartListInfo.partNumber}
										<input type="hidden" name="userEnteredActivity.additionalPartList[${status.index}].partNumber" value="${addPartListInfo.partNumber}"/>
										</td><td>										
										${addPartListInfo.description}
										<input type="hidden" name="userEnteredActivity.additionalPartList[${status.index}].description" value="${addPartListInfo.description}"/>
										</td><td class="w100">
										${addPartListInfo.orderQuantity}
										<input type="hidden" name="userEnteredActivity.additionalPartList[${status.index}].orderQuantity" value="${addPartListInfo.orderQuantity}"/>
										</td>
										<c:if test="${addPartListInfo.typePrinter }">
										<td>
											<select class="w130" name="userEnteredActivity.additionalPartList[${status.index}].status">
																<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForPrinterType}" var="recomendedPartlistTypeMap">
																<c:choose>
																	<c:when test="${addPartListInfo.status eq recomendedPartlistTypeMap.key }">
																	<option value="${recomendedPartlistTypeMap.key}"  selected="selected">${recomendedPartlistTypeMap.value}</option>
																	</c:when>
																	<c:otherwise>
																	<option value="${recomendedPartlistTypeMap.key}">${recomendedPartlistTypeMap.value}</option>
																	</c:otherwise>
																</c:choose>														
															</c:forEach>
															</select>									
										</td></c:if>
										<c:if test="${not addPartListInfo.typePrinter }">
										<td>
											<select class="w130" name="userEnteredActivity.additionalPartList[${status.index}].status">
																<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForNonPrinterType}" var="recomendedPartlistTypeMap">
																<c:choose>
																	<c:when test="${addPartListInfo.status eq recomendedPartlistTypeMap.key }">
																	<option value="${recomendedPartlistTypeMap.key}"  selected="selected">${recomendedPartlistTypeMap.value}</option>
																	</c:when>
																	<c:otherwise>
																	<option value="${recomendedPartlistTypeMap.key}">${recomendedPartlistTypeMap.value}</option>
																	</c:otherwise>
																</c:choose>														
															</c:forEach>
															</select>									
										</td></c:if>
										</tr>
						</c:forEach>
				  		</c:if>
				  	</tbody>
				  	
				  </table>
				   <div style="display:none;" id="templateAdditionalPartsDiv">
				  <table id="defaultAddPartTableTemplate">
         			<tbody>
         			<tr class="altRow">
         								<td>
										<span id="display[-1]partNumber"></span>
										<input type="hidden" name="userEnteredActivity.additionalPartList[-1].partNumber" value=""/>
										</td><td>										
										<span id="display[-1]description"></span>
										<input type="hidden" name="userEnteredActivity.additionalPartList[-1].description" value=""/>
										</td><td>
										<input type="text" class="w100" name="userEnteredActivity.additionalPartList[-1].orderQuantity" value="0"/>
										</td>
										<td>
											<select class="w130" name="userEnteredActivity.additionalPartList[-1].status">
															
															<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForPrinterType}" var="printerPartStatusMap">
																<c:choose>
																<c:when test="${printerPartStatusMap.key == 'Used' }">
																	<option value="${printerPartStatusMap.key}"  selected="selected">${printerPartStatusMap.value}</option>
																</c:when>	
																<c:otherwise>
																	<option value="${printerPartStatusMap.key}" >${printerPartStatusMap.value}</option>
																</c:otherwise>																
																</c:choose>														
															</c:forEach>
															</select>									
										</td>
										
         				
         			</tr>
         			</tbody>
         		</table>
         		<table id="defaultAddPartTableTemplate11">
         			<tbody>
         			<tr class="altRow">
         								<td>
										<span id="display[-1]partNumber"></span>
										<input type="hidden" name="userEnteredActivity.additionalPartList[-1].partNumber" value=""/>
										</td><td>										
										<span id="display[-1]description"></span>
										<input type="hidden" name="userEnteredActivity.additionalPartList[-1].description" value=""/>
										</td><td>
										<input type="text" class="w100" name="userEnteredActivity.additionalPartList[-1].orderQuantity" value="0"/>
										</td>
										<td>
											<select class="w130" name="userEnteredActivity.additionalPartList[-1].status">
															
															<c:forEach items="${hardwareDebriefForm.recommendedPartlistStatusForNonPrinterType}" var="nonPrinterPartStatusMap">
																<c:choose>
																<c:when test="${nonPrinterPartStatusMap.key == 'Used' }">
																	<option value="${nonPrinterPartStatusMap.key}"  selected="selected">${nonPrinterPartStatusMap.value}</option>
																</c:when>	
																<c:otherwise>
																	<option value="${nonPrinterPartStatusMap.key}" >${nonPrinterPartStatusMap.value}</option>
																</c:otherwise>																
																</c:choose>														
															</c:forEach>
															</select>									
										</td>
         				
         			</tr>
         			</tbody>
         		</table>
         		</div>
				  
				  <label id="partSearchLoading" style="display: none;">
									&nbsp;&nbsp;&nbsp;<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
								</label>
            </div>
          </div>
        </div>
        <!-- Parts And Tools Used BLOCK - End --> 
        
		<%-- Close Out Install Activity BLOCK - Start --%>
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/closeOutInstallActivity.jsp" />
        <%-- Close Out Install Activity BLOCK - End --%>
        
		<%-- Page Count BLOCK - Start --%>
		<jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/pageCountInstallCloseOut.jsp"/>        
        <%-- Page Count BLOCK - End --%>
         
		<%-- Service Information for this Device BLOCK - Start --%>
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/serviceRequestForDeviceCloseOut.jsp"/>
    
        <%-- Service Information for this Device BLOCK - End --%>
        
        <%-- 
		<!-- Supplies Information for this Device BLOCK - Start -->
		<jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/suppliesInfoForDeviceCloseOut.jsp"/>        
        <!-- Supplies Information for this Device BLOCK - End -->
        --%> 		
        <!-- Contact Information for this Device BLOCK - Start -->
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/commonContactInformationCloseOut.jsp"/>
        <!-- Contact Information for this Device BLOCK - End -->
        
       
     <c:if test="${isHwInstallDecommission}">
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/commonInstall-DeInstallCloseOut.jsp"/>
       
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/pageCountInstall-DeInstallCloseOut.jsp"/>
       </c:if>
     
       
        <%-- NOTES BLOCK - Start --%>
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/notesCloseOut.jsp"/> 
        <%-- NOTES BLOCK - End --%> 
        
		<%-- ATTACHMENTS BLOCK - Start --%>
         <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwView/commonAttachmentsView.jsp"/>
        <%-- ATTACHMENTS BLOCK - End --%> 
               
        </form:form>
        <%-- Add Attachments BLOCK - Start --%>
        <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/addAttachmentCloseOut.jsp"/> 
        <%-- Add Attachments BLOCK - End --%> 
        <c:if test="${not exceptionOccured_Detail}">
		<div class="buttonContainer">
			<a id="customerProfileInformation" onclick="popCustomerProfileInformation()" style="cursor:pointer;background: none !important;color:#336699!important;"><spring:message code="requestInfo.hardwareDebrief.customerProfile.heading.title"/></a>
		 <c:if test="${hardwareDebriefForm.activity.serviceRequest.serviceRequestStatus != 'completed'}">
          <a class="button" href="#" style="position:static!important;" onclick="saveInstall()"><spring:message code="button.save_as_draft" /></a>
         
          <button type="button" style="position:static!important;" class="button button21" onclick="confirmInstallCloseOut()"><spring:message code="button.closeout"/></button>
          </c:if>
          <button id="returnRequests_but" style="position:static!important;" class="button_cancel button_cancel21"><spring:message code="button.cancel"/></button>
        </div>
        </c:if>
        </div>
        
        
     </div>
      </div>


   	<div style="display: none;" id="partListPopUp" title="Part List">
   	  <%-- <jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/partListPopUp.jsp"/> --%> 
	</div>
	
	<div style="display: none;" id="addressListPopUp" title="Address List">
   	  <%--  <jsp:include page="/WEB-INF/jsp/claims/addressListPopup.jsp" /> --%>
	</div>
	
	<div style="display: none;" id="contactPopUp" title="Contact PopUp">
   	   <%--  <jsp:include page="/WEB-INF/jsp/common/selectContact.jsp" /> --%>
	</div>
	
	<div style="display: none;" id="chlPopUp" title="CHL PopUp">
   	  <%--  <jsp:include page="/WEB-INF/jsp/common/displayCHLTree.jsp" /> --%>
	</div>
	<div style="display:none;"  >
			<div  id="customerProfilePopup" title="<spring:message code="requestInfo.hardwareDebrief.customerProfile.heading.title"/>" >
			
			<p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebrief.customerProfile.heading"/></p>
			
				<div class="expand-min1">				
				</div>
            </div>
	</div>
 
  <%-- This div is used to hide elements from form submission --%>
  <div style="display: none;" id="tempContactDiv"></div>
  <div style="display: none;" id="tempPageCountDiv"></div>
  <div style="display: none;" id="tempPageCountDiv2"></div><%--fOR dEINSTALL aSSET PAGECOUNT --%>
  <div style="display: none;" id="tempNotesPopupDiv"></div>
  <div style="display: none;" id="tempAdditionalPartsDiv"></div>
  <spring:message code="requestInfo.hardwareDebreief.debriefInstallCloseOut.partList" var="partListTitle"/>
  <spring:message code="requestInfo.hardwareDebreief.common.closeOut.pageCount.header" var="pageCountTitle"/>
  <%-- Added for LBS 1.5 View Grid--%>
  
  <jsp:include page="/WEB-INF/jsp/common/mapPopup.jsp" />
  <%-- ENDS for LBS 1.5 View Grid--%>
<script>
<%-- Below value is for BACK URL--%>
var backURL={
		backURLocation:"${hardwareDebriefForm.backURL}"	 
};
</script>
	<script type="text/javascript" src="<html:rootPath/>js/validation.js?version=4"></script>
 <script type="text/javascript" src="<html:rootPath/>js/hardwareDebreif.js?version=9"></script>
<script>
	
 	var partnerAccountOrganizationIdHidden=null;
	var modelNumber = null;
	jQuery(document).ready(function() {
		modelNumber = '${hardwareDebriefForm.activity.serviceRequest.asset.productModelNumber}';
		//alert(modelNumber);
		//modelNumber = "5016-001";
		ajaxRetrieveOrgId();//Populate partnerAccountOrganizationIdHidden
		showToolTipList('helpIconList');
	});
	
	var popupURLS={
			addressPopupURL:"${showAddressPopup}",
			contactPopupURL:"${showContactPopup}",
			chlPopupURL:"${showCHLTreePopUp}",
			acceptURL:"${acceptRequest}"
		};
	var contact_AddressMessages={
			
				diffContactMsg:"<spring:message code="contact.selectDiffContact" />",
				diffAddressMsg:"<spring:message code="address.selectDiffAddress" />",
				contactMsg:"<spring:message code="contact.selectContact" />",
				addressMsg:"<spring:message code="address.selectAddress" />"
			};
	var confirmationMessages={
			ConfirmMsg1:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessage" javaScriptEscape="true"/>",
			ConfirmTitle:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageTitle" javaScriptEscape="true"/>",
			alertSuccess:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageSuccess" javaScriptEscape="true"/>",
			alertFailure:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.alertMessageFailure" javaScriptEscape="true"/>",
			ConfirmMsg2:"<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageToClose" javaScriptEscape="true"/>"
	};

    
	
    

function popUpPartList(hyperlinkId)
{
	if(modelNumber=="" || modelNumber==null){
		jAlert('<spring:message code="requestInfo.hardwareDebrief.modelNumberBlank"/>', "");
		return;
	}
	var isConsContPop=hyperlinkId;
	showOverlay();
	var linkId=hyperlinkId;
	var url="${showPartListPopup}";
	
		jQuery('#partListPopUp').load(url,function(){
		dialog=jQuery('#partListDiv').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			height: 550,
			width: 790,
			open:function(){
				initialisePartGrid(addAnotherPartlistRow);
			},
			close: function(event,ui){
				hideOverlay();
				dialog=null;
				jQuery('#partListDiv').remove();
				
				}
			});
		
		dialog.dialog('open');
		
		});
	return false;
}

function searchPart(partNumber){
	
	var partNum = partNumber.replace(/(^\s*)|(\s*$)/g, "");
	
	if(partNum=="" || partNum==null){
		jAlert('<spring:message code="claim.message.pleaseEnterAPartNumber"/>', "");
		return;
	}
	
	if(modelNumber=="" || modelNumber==null){
		jAlert('<spring:message code="requestInfo.hardwareDebrief.modelNumberBlank"/>', "");
		return;
	}
	document.getElementById("partSearchButton").disabled = true;
	document.getElementById("partSearchLoading").style.display = "block";
	var url = '<portlet:resourceURL id="retrieveSinglePart"/>';
	//modelNumber="7462-2A6";
	url+="&partNumber="+partNum+"&modelNumber="+modelNumber+"&organizationId="+partnerAccountOrganizationIdHidden;
	doAjax(url,callbackGetResult,callbackGetFailureResult,null);
}

function ajaxRetrieveOrgId(){
	var accountId='${hardwareDebriefForm.activity.partnerAccount.accountId}';
	//accountId='1-NMCFPJ';
	if(accountId=='' || accountId==null){
		//jAlert('<spring:message code="requestInfo.hardwareDebrief.partnerAccountBlank"/>', "");
		return;
	}
	var url = '<portlet:resourceURL id="retrieveOrganizationIdForHW"/>';
    url += "&partnerAccountID="+accountId;
    jQuery.getJSON(url,function(orgJSon){
		if(orgJSon.orgId!=""){
			partnerAccountOrganizationIdHidden=orgJSon.orgId;
			//alert(orgJSon.orgId);
		}
		
	});
	
    
}

function callbackGetResult(result) {
	//alert("In callbackGetResult");
	document.getElementById("partSearchButton").disabled = false;
	document.getElementById("partSearchLoading").style.display = "none";
	var data = result.data?result.data:"";
	
	if(!result.data){
		jAlert('<spring:message code="claim.message.noPartFound"/>', "");
		return true;
	}
	var strs = data.split("///");
	var rowData={
			partNumber:strs[0],
			description:strs[1],
			typePrinter:strs[4]
	};
	addAnotherPartlistRow(rowData);
	
	return true;
}

function callbackGetFailureResult(result){
	//alert("In callbackGetFailureResult");
	document.getElementById("partSearchButton").disabled = false;
	document.getElementById("partSearchLoading").style.display = "none";
	
	return false;
}





function ajaxTechnicianInformation(accountId){
	var url = '<portlet:resourceURL id="setTechnicianInformation"/>';
	url +="&accountId=" + accountId;
	
	document.getElementById("technicianLoading").style.display = "block";
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
		selectObject.options.add(new Option(textValue[0],valueKey[i]));
	}
	selectObject.remove(selectObject.length-1);
	selectObject.options.add(new Option('<spring:message code="claim.label.dropdownlist.optionvalue.technician.other"/>','other'));
	
	document.getElementById("technicianLoading").style.display = "none";
	return true;
}

var additionalPartlistLen=-1;
if("${fn:length(hardwareDebriefForm.userEnteredActivity.additionalPartList)}">0){
	additionalPartlistLen=parseInt("${fn:length(hardwareDebriefForm.userEnteredActivity.additionalPartList)}");
	additionalPartlistLen--;
}
function addAnotherPartlistRow(rowData){
	//alert("addAnotherPartlistRow");
	 //var result = userEnteredActivity.additionalPartList;
	 //alert("result = " + result);
	 //alert("rowData.partNumber = " + rowData.partNumber);
	 //alert("rowData.description = " + rowData.description);
	 //alert("rowData.typePrinter = " + rowData.typePrinter);
	if(rowData.typePrinter == "true"){
 		var trHtml=jQuery('#defaultAddPartTableTemplate tbody').html();
 		//alert("1 = " + trHtml);
	}
	else if(rowData.typePrinter == "false"){
		var trHtml=jQuery('#defaultAddPartTableTemplate11 tbody').html();
		//alert("2 = " + trHtml);
	}
 	
 	additionalPartlistLen++;
 	formatValidation[8][3]=additionalPartlistLen;
 	if(additionalPartlistLen==0){
 		jQuery('#addPartListTable').show();
 	}
     	
 	trHtml=replaceAll(trHtml,'[-1]','['+additionalPartlistLen+']');
 	if(additionalPartlistLen%2==0){
 		trHtml=replaceAll(trHtml,'altRow','');
 	}
 	jQuery(trHtml).appendTo(jQuery('#addPartListTable tbody'));
 	for(key in rowData){
 		var spanHTMLId="display["+additionalPartlistLen+"]"+key;
 		spanHTMLId=spanHTMLId.replace('[','\\[');
		spanHTMLId=spanHTMLId.replace(']','\\]');
		var inputHTMLId="userEnteredActivity.additionalPartList["+additionalPartlistLen+"]."+key;
		jQuery('#'+spanHTMLId).html(rowData[key]);
		jQuery("[name='"+inputHTMLId+"']").val(rowData[key]);
 	}
 	bindClickEvent();
 	
	
}



</script>


<script type="text/javascript" src="<html:rootPath/>js/commonAddress.js"></script>	
<jsp:include page="/WEB-INF/jsp/hardwareDebrief/HWInstallDeInstallMove/commonHwCloseOut/commonHardwareValidation.jsp"></jsp:include>  
<script>


addRequiredFieldToInput();
var mandatoryFieldObj={
		1:["actualStartDate","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.actualStartDate" javaScriptEscape="true"/>"],
		2:["actualEndDate","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.actualEndDate" javaScriptEscape="true"/>"],
		3:["assetInstallDate","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.assetInstallDate" javaScriptEscape="true"/>"],
		4:["serviceRequestDate",""],
		5:["serviceRequestStatusDropDown","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.statusEmpty" javaScriptEscape="true"/>"],
		6:["technicianNameArea","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.technicianName" javaScriptEscape="true"/>"],
		7:["assetSerialNumber","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.serialNumber" javaScriptEscape="true"/>"],
		8:["userEnteredActivity.serviceRequest.asset.installAddress.addressLine1","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.deviceInstallAddress" javaScriptEscape="true"/>"],
		<c:if test="${isLbsAddress==true && islod== true}">
		12:["userEnteredActivity.serviceRequest.asset.installAddress.buildingId","<spring:message code="requestInfo.hardwareDebrief.validation.building" javaScriptEscape="true"/>"],
		13:["userEnteredActivity.serviceRequest.asset.installAddress.floorId","<spring:message code="requestInfo.hardwareDebrief.validation.floor" javaScriptEscape="true"/>"],
		</c:if>
		10:["desc","<spring:message code="requestInfo.hardwareDebreief.common.reject" javaScriptEscape="true"/>"],
		11:["expectedEndDate",""],
		quantityInvalid:"<spring:message code="claim.errorMsg.qtyMustBeNumThan0" javaScriptEscape="true"/>",
		save:false,
		closeOut:false,
		requestFrom:"install",
		formId:"hardwareDebriefForm"		
};

var formatValidation={
		1:["userEnteredActivity.serviceRequest.asset.ipAddress","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.IpAddress" javaScriptEscape="true"/>",isIPv4V6],
		2:["userEnteredActivity.serviceRequest.asset.ipV6","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.IpAddress" javaScriptEscape="true"/>",isIPV6],
		3:["userEnteredActivity.serviceRequest.asset.macAddress","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.macAddress" javaScriptEscape="true"/>",isMACAddress],
		4:["userEnteredActivity.serviceRequest.asset.portNumber","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.portNumber" javaScriptEscape="true"/>",isDigit],
		5:["userEnteredActivity.serviceRequest.asset.faxPortNumber","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.faxPortNumber" javaScriptEscape="true"/>",isDigit],
		6:["userEnteredActivity.serviceRequest.asset.subnet","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.subnet" javaScriptEscape="true"/>",isIPAddress],
		7:["userEnteredActivity.serviceRequest.asset.gateway","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.gateway" javaScriptEscape="true"/>",isIPAddress],
 		8:["userEnteredActivity.additionalPartList[].orderQuantity","<spring:message code="claim.errorMsg.qtyMustBeNumThan0" javaScriptEscape="true" arguments="${partListTitle}" />",checkQuantity,additionalPartlistLen],
 		12:["userEnteredActivity.serviceRequest.asset.pageCounts[].type","<spring:message code="requestInfo.errorMsg.pageCountType" javaScriptEscape="true" />",checkBlankValue,pageCountslength],
		9:["userEnteredActivity.serviceRequest.asset.pageCounts[].count","<spring:message code="claim.errorMsg.qtyMustBeNumThan0" javaScriptEscape="true" arguments="${pageCountTitle}" />",checkQuantity,pageCountslength],
		14:["userEnteredActivity.serviceRequest.asset.deInstAssetPageCounts[].type","<spring:message code="requestInfo.errorMsg.pageCountType" javaScriptEscape="true" />",checkBlankValue,pageCountslength],
		15:["userEnteredActivity.serviceRequest.asset.deInstAssetPageCounts[].count","<spring:message code="claim.errorMsg.qtyMustBeNumThan0" javaScriptEscape="true" arguments="${pageCountTitle}" />",checkQuantity,pageCountslength],
		10:["userEnteredActivity.serviceRequest.contactInfoForDevice[].deviceContactType","<spring:message code="requestInfo.errorMsg.selectContactType" javaScriptEscape="true" />",checkBlankValue,contactListLength],
		11:["userEnteredActivity.serviceRequest.contactInfoForDevice[].firstName","<spring:message code="requestInfo.errorMsg.selectContact" javaScriptEscape="true" />",checkBlankValue,contactListLength],
		13:["","<spring:message code="requestInfo.hardwareDebreief.consumableContact.validation" javaScriptEscape="true" />",checkBlankValue,contactListLength],		
		save:false,
		closeOut:false
};

var mandatoryFieldForSAVE={
		1:["desc","<spring:message code="requestInfo.hardwareDebreief.common.reject" javaScriptEscape="true"/>"],
		5:["serviceRequestStatusDropDown","<spring:message code="requestInfo.hardwareDebreief.common.closeOut.validation.statusEmpty" javaScriptEscape="true"/>"]		
};

function confirmInstallCloseOut(){
	jQuery('#errorDiv').hide();
	
	jQuery('#assetRemovalDateForm').val(jQuery('#assetRemovalDate').val());
	
	formatValidation.save=false;
	formatValidation.closeOut=true;
	if(checkFormats(formatValidation)){
		jQuery('#errorDiv').show();
		jQuery(document).scrollTop(0);
		return;
	}
	
			    jConfirm(confirmationMessages.ConfirmMsg2, confirmationMessages.ConfirmTitle, function(r)
					{
					if(r==true){
						jQuery('#errorDiv').hide();
						
						mandatoryFieldObj["save"]=false;
						mandatoryFieldObj["closeOut"]=true;
						jQuery('#successDiv').hide();
						if(!checkMandatoryFields(mandatoryFieldObj) && !doCustomerProfileValidation(customerProfileFields,inputNamesMapToSiebel,'install')){
							
							submitForm(mandatoryFieldObj);
							//window.location.href="services-requests";
						}else{
							
								jQuery('#errorDiv').show();
								jQuery(document).scrollTop(0);
							}
						
						
					}
				});					
			}
	
function saveInstall(){
	
	jQuery('#errorDiv').hide();
	formatValidation.save=true;
	formatValidation.closeOut=false;
	
	mandatoryFieldForSAVE["save"]=true;
	mandatoryFieldForSAVE["closeOut"]=false;
	if(checkMandatoryFields(mandatoryFieldForSAVE)){
		jQuery('#errorDiv').show();
		jQuery(document).scrollTop(0);
		return;
	}
	if(checkFormats(formatValidation)){
		jQuery('#errorDiv').show();
		jQuery(document).scrollTop(0);
		return;
	}
	
	mandatoryFieldObj["save"]=true;
	mandatoryFieldObj["closeOut"]=false;
	
	submitForm(mandatoryFieldObj);
}

</script>
