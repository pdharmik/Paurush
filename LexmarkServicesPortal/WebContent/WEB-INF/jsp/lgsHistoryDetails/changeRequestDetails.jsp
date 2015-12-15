<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>

<!-- CSS include -->

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!--[if IE 8]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie8.css" %></style>
<![endif]--> 

<% request.setAttribute("subTabSelected","requestHistory");%>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<style>
.status table td {
	width:25% !important;
}
</style>

<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/portletRedirection.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>
	
	<div class="utilities printPage floatR" id="topEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="button.clickToEmail"/>" title="<spring:message code="button.clickToEmail"/>"></a></li>
            <li><a href="javascript: print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="button.clickToPrint"/>" title="<spring:message code="button.clickToPrint"/>"></a></li>
          </ul>
        </div>
   <div id="emailPrintWraper">     	
  <div id="content-wrapper">
    <div class="journal-content-article">
    <c:choose>
    <c:when test="${requestForm.serviceRequest.area.value =='Upload' && ((requestForm.serviceRequest.subArea.value=='Upload Supplies Request'
    ||requestForm.serviceRequest.subArea.value=='Upload Consumable SRs Request' 
    ||requestForm.serviceRequest.subArea.value=='Uploaded Consumable Request'))}">
      <h1><spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/></h1>
      </c:when>
       <c:otherwise>      
      <h1><spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/></h1>
     </c:otherwise>
      </c:choose>
      <h2 class="step">
      	<spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> 
      	<span id="reqNumber">${requestForm.serviceRequest.serviceRequestNumber }</span>
      </h2>
     </div>
      
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
        <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong><spring:message code="requestInfo.error.exceptionExist"/> - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
        <div class="portletBlock infoBox rounded shadow split">
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.area"/></label>
                  <span id  = "area">${requestForm.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/></label>
                  <span id  = "subArea">${requestForm.serviceRequest.subArea.value }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span id = "status">${requestForm.serviceRequest.serviceRequestStatus }</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></label>
<%--                   <span><fmt:formatDate value="${requestForm.serviceRequest.serviceRequestDate }" pattern="dd MMM yyyy"/></span></li> --%>
<%-- 					<util:dateFormat value="${requestForm.serviceRequest.serviceRequestDate}" timezoneOffset="${timeZoneOffset}"></util:dateFormat> --%>
 					<span id = "date_time">${requestForm.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/></label>
                  <span id = "requesterName">${fn:trim(requestForm.serviceRequest.requestor.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.requestor.lastName)}</span></li>
              </ul>
            </div>
          </div>
        </div>
        <!-- Status Bar Block - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="status">
                <div id="div_progressBar"><!-- PROGRESS BAR WILL COME HERE --></div>
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><spring:message code="Details.changeRequestDetails.process.submitted"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.inProgress"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.shipped"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.Complete"/></td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- Status Bar Block - End --> 
        <!-- Request Status History - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand"><spring:message code="Details.supplyRequestDetails.heading.requestStatusHistory"/></h4>
              <div  class="collapse" id="statusExpand">
              
              	<div id="div_statusPrintTable">
					<div id="tab_statusGrid" style="display:block;">
						<div id="div_status_container" class="div-style48"></div>
	    				<div id="div_statusPagingArea"></div>
					</div>
				</div>
				
<!--                 <table class="displayGrid rounded wFull"> -->
<!--                   <thead> -->
<!--                     <tr> -->
<!--                       <th class="w100">Date</th> -->
<!--                       <th class="w150">Status</th> -->
<!--                       <th>Description</th> -->
<!--                     </tr> -->
<!--                   </thead> -->
<!--                   <tbody> -->
<!--                     <tr> -->
<!--                       <td>MM/DD/YY</td> -->
<!--                       <td>Status</td> -->
<!--                       <td>Description of the status goes here</td> -->
<!--                     </tr> -->
<!--                     <tr class="altRow"> -->
<!--                       <td>MM/DD/YY</td> -->
<!--                       <td>Status</td> -->
<!--                       <td>Description of the status goes here</td> -->
<!--                     </tr> -->
<!--                   </tbody> -->
<!--                 </table> -->
              </div>
            </div>
          </div>
        </div>
        <!-- Request Status History - End -->
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryCont_name">${fn:trim(requestForm.serviceRequest.primaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.primaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryCont_phone">${requestForm.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryCont_email">${requestForm.serviceRequest.primaryContact.emailAddress } </span></li>
              </ul>
            </div>
            <c:if test="${requestForm.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="secondarySection">
              <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryCont_name">${fn:trim(requestForm.serviceRequest.secondaryContact.firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.secondaryContact.lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryCont_phone">${requestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryCont_email">${requestForm.serviceRequest.secondaryContact.emailAddress }</span></li>
              </ul>
            </div>
            </c:if>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                  <span id="addtnlInfo_customerRefId">${requestForm.serviceRequest.customerReferenceNumber}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter">${requestForm.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span class="multiLine" id="description">${requestForm.serviceRequest.addtnlDescription}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                  <span id = "effDateChange">
<!--					<util:dateFormat value="${requestForm.serviceRequest.requestedEffectiveDate }" timezoneOffset="${timeZoneOffset}"></util:dateFormat>                  -->
					<util:dateFormat value="${requestForm.serviceRequest.requestedEffectiveDate }"></util:dateFormat> 
				  </span></li>
				<c:if test="${requestForm.serviceRequest.area.value=='Change Account Data' && requestForm.serviceRequest.subArea.value=='LBS Map Updates'}">  
             <li>
              <label><spring:message code="requestInfo.label.moveAsset"/></label>
              <span id="moveAsset"><c:choose><c:when test="${requestForm.serviceRequest.lbsDeviceinfo=='Y'}">
              <p><spring:message code="requestInfo.option.yes"/></p></c:when>
              <c:otherwise>
              <p><spring:message code="requestInfo.option.no"/></p></c:otherwise></c:choose></span>
              </li>
             <c:if test="${requestForm.serviceRequest.lbsDeviceinfo=='Y'}">
              <li>
            <label><spring:message code="requestInfo.label.moveContactSelect"/></label>
            <span id="moveContactSelect">
            <c:choose><c:when test="${requestForm.serviceRequest.lbsLexmarkInstall=='Y'}">
            <p><spring:message code="requestInfo.option.yes"/></p></c:when>
                           <c:otherwise>
                           <p><spring:message code="requestInfo.option.no"/></p></c:otherwise></c:choose></span></li></c:if>
                           </c:if>
              </ul>
            </div>
          </div>
        </div>
        <c:if test='${requestForm.serviceRequest.asset.installAddress.addressLine1 ne ""}'>
         <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.mapchangerequest"/></h4>
              <ul class="roDisplay">
                <li id="shipToAddress"><div>${requestForm.serviceRequest.asset.installAddress.storeFrontName}</div>
                  <util:addressOutput value="${requestForm.serviceRequest.asset.installAddress}"></util:addressOutput>
                  </li>
                <li><label for="building"><spring:message code="requestInfo.addressInfo.label.building"/> </label><span id="building"> ${requestForm.serviceRequest.asset.installAddress.physicalLocation1}</span></li>
                <li><label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label><span id="floor"> ${requestForm.serviceRequest.asset.installAddress.physicalLocation2}</span></li>
                <li><label for="office"><spring:message code="requestInfo.addressInfo.label.office"/>  </label><span id="office"> ${requestForm.serviceRequest.asset.installAddress.physicalLocation3}</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
          <div>
            <c:if test="${(!requestForm.serviceRequest.asset.deviceContact.isEmpty()) && requestForm.serviceRequest.asset.deviceContact[0].firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="mapsRequestContactSection">
              <h4><spring:message code="requestInfo.heading.ContactForMapsRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="mapsRequestCont_name">${fn:trim(requestForm.serviceRequest.asset.deviceContact[0].firstName)}&nbsp;${fn:trim(requestForm.serviceRequest.asset.deviceContact[0].lastName)}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="mapsRequestCont_phone">${requestForm.serviceRequest.asset.deviceContact[0].workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="mapsRequestCont_email">${requestForm.serviceRequest.asset.deviceContact[0].emailAddress }</span></li>
              </ul>
            </div>
          </c:if> 
             </div>
            
          </div>
        </div>
        </c:if>
        <hr class="separator" />
<!--         Added for CI 14-02-03 STARTS-->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notes"/> &amp; <spring:message code="requestInfo.heading.attachments"/></p>
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.notes"/></h4>
              <ul class="roDisplay wordBreak">
              <li><span id="notes">${requestForm.serviceRequest.notes}</span></li>
            </ul>
            </div>
          </div>
        </div>
<!--         Added for CI 14-02-03 ENDS-->
        <!-- Below section available only for CHL and Others -->
		<c:if test="${pageView =='CHLOthers'}">
			<div class="portletBlock infoBox">
	          <div class="columnsTwo">
	            <div class="columnInner">
	              <ul class="form wordBreak">
	                <li>
	                  <label for="notes"><spring:message code="requestInfo.label.notes"/></label>
	                  <span class="multiLine" id="notes">${requestForm.serviceRequest.notes }</span> </li>
	              </ul>
	            </div>
	          </div>
	        </div>
	        <p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
	        <div class="columnInner" >
	        <div class="wHalf displayGrid columnInnerTable rounded shadow" id="attachmentView">
	          <table>
	          <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	              </tr>
	            </thead>
	            <tbody>
	            	<c:forEach items="${requestForm.attachList}" var="entry">
						<tr class="altRow">
			           		<c:if test="${entry.extension != 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.displayAttachmentName}.${entry.extension}</a></td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}',,'${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.attachmentName}</a></td>
			           	</c:if>
			            </tr>
					</c:forEach>
	              
	            </tbody>
	          </table>
	          </div>
	        </div>
        </c:if>
        <!-- Below section available only for Multiple -->
		<c:if test="${requestForm.serviceRequest.area.name =='Multiple'}">
			<p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
	        <div class="columnInner">
	        <div class="wHalf displayGrid columnInnerTable rounded shadow" id="attachmentDIV">
	          <table >
	          <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	              </tr>
	            </thead>
	            <tbody>
	            	<c:forEach items="${requestForm.attachList}" var="entry">
						<tr class="altRow">
						<c:if test="${entry.extension != 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.displayAttachmentName}.${entry.extension}</a></td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.attachmentName}</a></td>
			           	</c:if>
			            </tr>
					</c:forEach>
	              
	            </tbody>
	          </table>
	          </div>
	        </div>
		</c:if>
		<!-- Below section available for Asset/Contact/Address -->
		<c:if test="${pageView =='Normal'}">
		<c:if test="${not empty requestForm.attachList}">
			<p class="info"><spring:message code="Details.changeRequestDetails.message.toViewTheInformation"/></p>
			<p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
	        <div class="columnInner" id="shwAttachment" >
	        <div class="wHalf displayGrid columnInnerTable rounded shadow">
	          <table>
	          <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	              </tr>
	            </thead>
	            <tbody>
	              <c:forEach items="${requestForm.attachList}" var="entry">
	              <tr class="altRow">
	                	<c:if test="${entry.extension != 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.displayAttachmentName}.${entry.extension}</a></td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.attachmentName}</a></td>
			           	</c:if>
	              </tr>
	              </c:forEach>
<!-- 					<tr class="altRow"> -->
<%-- 	                	<td><img src="<html:imagesPath/>download.png" width="24" height="24" class="download" /><a href="javascript:void(0);" onclick="downloadAttachment(this.name);" >Document1</a></td> --%>
<!-- 	              	</tr> -->
	            </tbody>
	          </table>
	          </div>
	        </div>
	        </c:if>			
		</c:if>
		
        
        
        <p class="inlineTitle"><spring:message code="Details.changeRequestDetails.heading.notifications"/></p>
        <div class="portletBlock">
        <div class="columnInner">
<!--        			<div id="div_notificationsPrintTable"> -->
					<div id="notificationsGrid" class="div-style51">
						<div id="div_notifications_container" class="width-650px-important"></div>
    					<div id="div_notificationsPagingArea"></div>
    				</div>
<!--     			</div> -->
<!--           <table class="displayGrid rounded shadow wFull"> -->
<!--           <thead> -->
<!--               <tr> -->
<!--                 <th class="w150">Date</th> -->
<!--                 <th class="w150">Recepient</th> -->
<!--                 <th>Description</th> -->
<!--               </tr> -->
<!--             </thead> -->
<!--             <tbody> -->
<!--               <tr > -->
<!--                 <td>MM/DD/YY hh:mm:ss AM</td> -->
<!--                 <td>Recepient Name</td> -->
<!--                 <td>Description goes here</td> -->
<!--               </tr> -->
<!--               <tr class="altRow"> -->
<!--                 <td>MM/DD/YY hh:mm:ss AM</td> -->
<!--                 <td>Recepient Name</td> -->
<!--                 <td>Description goes here</td> -->
<!--               </tr> -->
<!--             </tbody> -->
<!--           </table> -->
        </div>
        </div>
        </div>
        </div>
  </div>
  </div>
        
          <div class="buttonContainer">
              <button id="backButton" class="button_cancel" type="button" onclick="backToHistory();"><spring:message code="button.back"/></button>
         <c:if test="${requestForm.serviceRequest.serviceRequestStatus !=null && requestForm.serviceRequest.serviceRequestStatus != 'Completed' }">
	          <button id="updateButton" class="button" type="button" onclick="updateRequest();"><spring:message code="button.updateRequest"/></button>
	        
	          <c:if test="${sessionScope.isStandardUser eq false}">
	          <button id="CancelButton" class="button_cancel" type="button" onclick="cancelRequest();"><spring:message code="button.cancelRequest"/></button>
	          </c:if>
	      </c:if>
          </div>
          
        
        <!-- MAIN CONTENT END --> 
      
  
<script type="text/javascript">
if (window.PIE) {
	
    document.getElementById("backButton").fireEvent("onmove");
    document.getElementById("updateButton").fireEvent("onmove");
    document.getElementById("CancelButton").fireEvent("onmove");
   
}
	var buttonName=""; 
	jQuery(document).ready(function() {
		barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
				26:	'<html:imagesPath/>progressBar/progressbg_orange.gif',
		   		51: '<html:imagesPath/>progressBar/progressbg_yellow.gif',
				76: '<html:imagesPath/>progressBar/progressbg_green.gif'};
			barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
		jQuery('#div_progressBar').progressBar('${requestForm.reqProgress}', { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
		textScroll(document.getElementById('description'));
		textScroll(document.getElementById('notes'));
		textScroll(document.getElementById('costCenter'));
	});
	
	////Toggle Containers
	jQuery(function() {
	    jQuery("h4.expand").toggler();
	});


	
	/** 
	*  Notifications Grid config begin
	*/
	SRNotificationsGrid = new dhtmlXGridObject('div_notifications_container');
 	SRNotificationsGrid.setImagePath("<html:imagesPath/>gridImgs/");
 	SRNotificationsGrid.setHeader("<spring:message code='serviceRequest.listHeader.notifications'/>");
 	/* Added extra column for defect 4515*/
 	SRNotificationsGrid.setColAlign("left,left,left,left,left");
 	SRNotificationsGrid.setColTypes("ro,ro,ro,ro,ro");
 	SRNotificationsGrid.setColSorting("str,str,str,str,str");
 	SRNotificationsGrid.setInitWidths("250,250,450,0,0");
 	/* End of defect 4515 */
 	SRNotificationsGrid.enableAutoWidth(true);
 	SRNotificationsGrid.enableAutoHeight(true);
 	SRNotificationsGrid.enableMultiline(true);
 	SRNotificationsGrid.init(); 
 	SRNotificationsGrid.setSkin("light");
 	SRNotificationsGrid.prftInit();
 	SRNotificationsGrid.enablePaging(true, 5, 10, "div_notificationsPagingArea", true);
 	SRNotificationsGrid.setPagingSkin("bricks");
	/*alert('<?xml version="1.0" ?><rows><row id="1"><cell><![CDATA[hii]]></cell><cell><![CDATA[heloo]]></cell><cell><![CDATA[<a href="###"> fooo </a>]]></cell><cell><![CDATA[booo]]></cell></row></rows>');*/
 	SRNotificationsGrid.loadXMLString('${requestForm.srNotifyXML}'); 
 	SRNotificationsGrid.setSortImgState(true, 0, 'asc');
 	SRNotificationsGrid.setColumnHidden(3,true);
 	SRNotificationsGrid.setColumnHidden(4,true);//Fix for defect 6328
 	SRNotificationsGrid.sortRows(0,"str","asc");
 	
 	/** 
 	 *  status_container config begin
 	 */
 	SRStatusGrid = new dhtmlXGridObject('div_status_container');
  	SRStatusGrid.setImagePath("<html:imagesPath/>gridImgs/");
  	SRStatusGrid.setHeader("<spring:message code='serviceRequest.listHeader.srstatus'/>");
  	SRStatusGrid.setColAlign("left,left,left");
  	SRStatusGrid.setColTypes("ro,ro,ro");
  	//SRStatusGrid.setColSorting("str,str,str");
  	SRStatusGrid.setInitWidths("200,250,450");
  	SRStatusGrid.enableAutoWidth(true);
  	SRStatusGrid.enableMultiline(true);
  	SRStatusGrid.enableAutoHeight(true);
  	SRStatusGrid.init(); 
  	SRStatusGrid.prftInit();
  	SRStatusGrid.enablePaging(true, 5, 10, "div_statusPagingArea", true);
  	SRStatusGrid.setPagingSkin("bricks");
  	SRStatusGrid.setSkin("light");
	SRStatusGrid.loadXMLString('${requestForm.srStausXML}'); 
  	//SRStatusGrid.setSortImgState(true, 0, 'asc');
  	var area="${requestForm.serviceRequest.area.value}";
  	var subarea="${requestForm.serviceRequest.subArea.value}";
		var srArea;
  		var srSubArea;
  		var pagetype;
  		if(area=="Upload" && ((subarea=="Upload Supplies Request")
  				||(subarea=="Upload Consumable SRs Request")
  				||(subarea=="Uploaded Consumable Request"))){
  			srArea="Update Request";
  	  		srSubArea="Consumables SR";
  	  		pagetype="supplies";
  		}else{
  			srArea="${requestForm.serviceRequest.area.value}";
  	  		srSubArea="${requestForm.serviceRequest.subArea.value}";
  	  		pagetype="change";
  		}
  	function cancelRequest(){
  		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTCANCELREQUESTBUTTON%>');
  		showOverlay();
  		//var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','change']);
  		var forwardURL;
  		if(srSubArea =='LBS Map Updates'){
	  			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Cancel Request',srSubArea,pagetype]);
	  		}else{
	  			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Cancel Request',srSubArea,pagetype]);
	  		}
  		
  		window.location.href=forwardURL;
  	}
	
  	function updateRequest(){
  		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTUPDATEREQUESTBUTTON%>');
  		showOverlay();
  		var area = '${updateTypeFlag}';
  		var page ;
  		var forward ;
  		var forwardURL ;
  		
  		if(area == 'Asset'){
  			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'Address'){
  			forwardURL = populateURL("manageaddresses",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'Contact'){
  			forwardURL = populateURL("managecontacts",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'CHL'){
  			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber'],['CHL', '${requestForm.serviceRequest.serviceRequestNumber }']);
  		}
  		
  		//added defect #7853
  		else if(area == 'Move Physical'){
  			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'Move Install Locations'){
  			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'Move to Storage'){
  			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'Install from Storage'){
  			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		else if(area == 'Move Storage Locations'){
  			forwardURL = populateURL("manageassets",['serviceRequestNumber', 'updateFlag'],['${requestForm.serviceRequest.serviceRequestNumber }', 'true']);
  		}
  		//ends here
  		
  		else{
  			if(srSubArea =='LBS Map Updates'){
  	  			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update','Update Request',srSubArea]);
  	  		}else{
  	  		forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update',srArea,srSubArea]);
  	  		}
  			
  		}
 		window.location.href=forwardURL;
  	}
  	
  	function downloadAttachment(attachmentName, dispAttachmentName, fileExtension, identifier){
  		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDOWNLOADATTACHMENT%>');
  		
  		//alert("attachmentName ::" + attachmentName);
	  	//alert("fileExtension ::" + fileExtension);
	  	//alert("identifier ::" + identifier);
	  	if(fileExtension == "URL" || fileExtension == "url"){
	  		var url = "http://"+attachmentName;
	  		var iWidth=1000;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;
		    window.open(url,
		    		'_blank',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',innerWidth='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=yes,toolbar=yes,menubar=yes,location=yes,resizable=yes,scrollbars=yes,titlebar=no');
	  		
	  	}else{
	  		window.location = "${downloadAttachment}&attachmentName=" + attachmentName + "&fileExtension=" + fileExtension + "&identifier=" + identifier + "&dispAttachmentName=" + dispAttachmentName;
	  	}
	}
  	
  	function backToHistory(){
  		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTBACKBUTTON%>');
  		showOverlay();
  		//alert("Inside backToHistory::" + '${requestForm.sourcePage}');
  		var backUrl =  "<portlet:renderURL><portlet:param name='action' value='backToHistory' />	</portlet:renderURL>" ;
  		backUrl = backUrl+'&requestTypeStr=' + '${requestForm.sourcePage}';
  		var sourcepage="${requestForm.sourcePage}";
  		if(sourcepage=="DM"){
	  			window.location.href="device-finder?assetRowId=${assetRowId}&isDeviceBookmarked=${isDeviceBookmarked}&backHistory=true&lclick=${linkClicked}";
	  	}else{
	  		window.location=backUrl;
	  	}
  		
  	}
  	
  	function popupNotificationDecDetail(selectedRowId){
		var date = SRNotificationsGrid.cellByIndex(selectedRowId,0).cell.innerHTML;
	 	var recipient = SRNotificationsGrid.cellByIndex(selectedRowId,1).cell.innerHTML;
		var description = SRNotificationsGrid.cells(selectedRowId,3).cell.innerHTML;
		var comment = SRNotificationsGrid.cells(selectedRowId,4).cell.innerHTML; //Added for defect 4515
		new Liferay.Popup({
	 	    title: "<spring:message code='serviceRequest.label.notificationsDetail'/>",
	     	position: ['center','top'],
	     	modal: true,
	     	resizable: false,
	     	width: 550, 
	     	height: 'auto',
	     	xy: [100, 100],
	     	onClose: function() {showSelect();},
	     	url:"<portlet:renderURL 
	         	windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
	         	"<portlet:param name='action' value='showNotificationDescDetail' />" + 
	         	"</portlet:renderURL>&date="+date+"&recipient="+recipient+"&description="+description+"&selectedRowId="+selectedRowId
	     	});
	  	}
  	
  	function hideSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "hidden";
		}      
	}
	
	//Added for MPS Phase 2.1
  	function print(){
  		jQuery('#statusExpand').show(); 
  	  callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTPRINT%>');
  		 
  	  	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printChangeRequest' /></portlet:renderURL>";         
  	      var iWidth=900;
  	      var iHeight=5600;
  	      var iTop = (window.screen.availHeight-30-iHeight)/2;        
  	      var iLeft = (window.screen.availWidth-10-iWidth)/2;           
  	      window.open(url,
  	              'ChangeRequestDetails',
  	              'height='+iHeight+
  	              ',innerHeight='+
  	              iHeight+',width='+
  	              iWidth+',innerWidth='+
  	              iWidth+',top='+iTop+
  	              ',left='+iLeft+
  	              ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
  	  };


  	  	
  	  	function email() {
  	  	jQuery('#statusExpand').show(); 
  	  		<%-- callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CONFIRMASSETACCEPTANCEEMAIL%>'); --%>
  	  			var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailChangeRequest' /></portlet:renderURL>" ;

  	  	  		
  	  		
  	  		    var iWidth=1150;
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

