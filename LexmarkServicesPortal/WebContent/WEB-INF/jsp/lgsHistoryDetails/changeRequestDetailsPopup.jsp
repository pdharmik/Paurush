<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>

<style>
.status table td {
	width:25% !important;
}
</style>
<!-- Added for CI 13-10-22 STARTS-->
<portlet:resourceURL var="downloadAttachment" id="downloadAttachment">
</portlet:resourceURL>
<!-- Added for CI 13-10-22 ENDS-->

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<c:choose>
<c:when test="${requestFormPopup.serviceRequest.area.value =='Upload' && ((requestForm.serviceRequest.subArea.value=='Upload Supplies Request'
    ||requestForm.serviceRequest.subArea.value=='Upload Consumable SRs Request' 
    ||requestForm.serviceRequest.subArea.value=='Uploaded Consumable Request'))}">
<div id="contentDetailsSupply" class="div-style28">
</c:when>
<c:otherwise> 
<div id="contentDetailsChange" class="div-style28">
</c:otherwise>
      </c:choose>
      
    <div class="journal-content-article">
       <c:choose>
    <c:when test="${requestFormPopup.serviceRequest.area.value =='Upload' && ((requestForm.serviceRequest.subArea.value=='Upload Supplies Request'
    ||requestForm.serviceRequest.subArea.value=='Upload Consumable SRs Request' 
    ||requestForm.serviceRequest.subArea.value=='Uploaded Consumable Request'))}">
      <h1><spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/></h1>
      </c:when>
       <c:otherwise>      
      <h1><spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/></h1>
     </c:otherwise>
      </c:choose>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="ChangeRqstNumber"></span></h2> </div>
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
        <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions_chngPopup">
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
                  <span>${requestFormPopup.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/></label>
                  <span>${requestFormPopup.serviceRequest.subArea.value }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span>${requestFormPopup.serviceRequest.serviceRequestStatus }</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></label>
<%--                   <span><fmt:formatDate value="${requestFormPopup.serviceRequest.serviceRequestDate }" pattern="dd MMM yyyy"/></span></li> --%>
 					<%-- <span><util:dateFormat value="${requestFormPopup.serviceRequest.serviceRequestDate}" timezoneOffset="${timeZoneOffsetPopup}"></util:dateFormat></span></li>--%>
 					<span>${requestFormPopup.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/></label>
                  <span>${requestFormPopup.serviceRequest.requestor.firstName } &nbsp; ${requestFormPopup.serviceRequest.requestor.lastName }</span></li>
              </ul>
            </div>
          </div>
        </div>
        <!-- Status Bar Block - Start -->
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="status statusPopup">
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

        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span>${requestFormPopup.serviceRequest.primaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.primaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span>${requestFormPopup.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span>${requestFormPopup.serviceRequest.primaryContact.emailAddress } </span></li>
              </ul>
            </div>
            <c:if test="${requestFormPopup.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="Details.changeRequestDetails.heading.secondaryContact"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span>${requestFormPopup.serviceRequest.secondaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.secondaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span>${requestFormPopup.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span>${requestFormPopup.serviceRequest.secondaryContact.emailAddress }</span></li>
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
                  <span>${requestFormPopup.serviceRequest.customerReferenceId }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter_chngPopup">${requestFormPopup.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="description_chngPopup">${requestFormPopup.serviceRequest.addtnlDescription}</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                  <span>
<!--					<util:dateFormat value="${requestFormPopup.serviceRequest.requestedEffectiveDate }" timezoneOffset="${timeZoneOffsetPopup}"></util:dateFormat>                  -->
						<util:dateFormat value="${requestFormPopup.serviceRequest.requestedEffectiveDate }"></util:dateFormat>
				  </span></li>
              </ul>
            </div>
          </div>
        </div>
         <c:if test='${requestFormPopup.serviceRequest.asset.installAddress.addressLine1 ne ""}'>
         <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.mapchangerequest"/></h4>
              <ul class="roDisplay">
                <li id="shipToAddress"><div>${requestFormPopup.serviceRequest.asset.installAddress.storeFrontName}</div>
                  <util:addressOutput value="${requestFormPopup.serviceRequest.asset.installAddress}"></util:addressOutput>
                  </li>
                <li><label for="building"><spring:message code="requestInfo.addressInfo.label.building"/> </label><span id="building"> ${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation1}</span></li>
                <li><label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label><span id="floor"> ${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation2}</span></li>
                <li><label for="office"><spring:message code="requestInfo.addressInfo.label.office"/>  </label><span id="office"> ${requestFormPopup.serviceRequest.asset.installAddress.physicalLocation3}</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            
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
              <li><span id="notes">${requestFormPopup.serviceRequest.notes}</span></li>
            </ul>
            </div>
          </div>
        </div>
		<!--  Added for CI 14-02-03 ENDS-->

		<!-- Below section available only for CHL and Others -->
		<c:if test="${pageView =='CHLOthers'}">
			<div class="portletBlock infoBox">
	          <div class="columnsTwo">
	            <div class="columnInner">
	              <ul class="form wordBreak">
	                <li>
	                  <label for="notes_chngPopup"><spring:message code="requestInfo.label.notes"/></label>
	                  <span class="multiLine" id="notes_chngPopup">${requestFormPopup.serviceRequest.notes }</span> </li>
	              </ul>
	            </div>
	          </div>
	        </div>
	        <p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
	        <div class="columnInner">
	        <div class="wHalf displayGrid columnInnerTable rounded shadow">
	          <table>
	          <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	              </tr>
	            </thead>
			<!--Commented and changes made for BRD 13-10-22 Start-->
	           <%--   <tbody>
	            	<c:forEach items="${requestFormPopup.attachList}" var="entry">
	            		<tr class="altRow">
			           		<c:if test="${entry.extension != 'URL'}">
			           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td>${entry.attachmentName}</td>
			           	</c:if>
			            </tr>
					</c:forEach>
	              
	            </tbody>--%>
	            <tbody>
	            	<c:forEach items="${requestFormPopup.attachList}" var="entry">
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
	            <!--Commented and changes made for BRD 13-10-22 End-->
	          </table>
	          </div>
	        </div>
        </c:if>
        <!-- Below section available only for Multiple -->
		<c:if test="${requestFormPopup.serviceRequest.area.value =='Multiple'}">
			<p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
	        <div class="columnInner">
	        <div class="wHalf displayGrid columnInnerTable rounded shadow">
	          <table>
	          <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	              </tr>
	            </thead>
	        	<!--Commented and changes made for BRD 13-10-22 Start-->
	            <%-- <tbody>
	            	<c:forEach items="${requestFormPopup.attachList}" var="entry">
	            		<tr class="altRow">
						<c:if test="${entry.extension != 'URL'}">
			           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td>${entry.attachmentName}</td>
			           	</c:if>
			            </tr>
					</c:forEach>
	              
	            </tbody> --%>
	            <tbody>
	            	<c:forEach items="${requestFormPopup.attachList}" var="entry">
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
	            <!--Commented and changes made for BRD 13-10-22 End-->
	          </table>
	        </div>
	        </div>
		</c:if>
		<!-- Below section available for Asset/Contact/Address -->
		<c:if test="${pageView =='Normal'}">
		<c:if test="${not empty requestFormPopup.attachList}">
	        <div class="columnInner">
	        <div class="wHalf displayGrid columnInnerTable rounded shadow">
	          <table>
	            <tbody>
			<!--Commented and changes made for BRD 13-10-22 Start-->
	              <%-- <c:forEach items="${requestFormPopup.attachList}" var="entry">
	              <tr class="altRow">
	                	<c:if test="${entry.extension != 'URL'}">
			           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td>${entry.attachmentName}</td>
			           	</c:if>
	              </tr>
	              </c:forEach> --%>
	              <c:forEach items="${requestFormPopup.attachList}" var="entry">
	              <tr class="altRow">
	                	<c:if test="${entry.extension != 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.displayAttachmentName}.${entry.extension}</a></td>
			           	</c:if>
			           	<c:if test="${entry.extension == 'URL'}">
			           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" name="${entry.attachmentName}" >${entry.attachmentName}</a></td>
			           	</c:if>
	              </tr>
	              </c:forEach>
	              <!--Commented and changes made for BRD 13-10-22 End-->
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
					<div id="notificationsGrid_chngPopup" style="display:block;">
						<div id="notifications_container_chngPopup" class="width-100per"></div>
    					<div id="notificationsPagingArea_chngPopup" class="popup_grid"></div>
    				</div>
        </div>
        </div>		
         
        </div>
        <!-- MAIN CONTENT END --> 
      </div>
</div>

<script type="text/javascript">
	//alert('popup');
	var buttonName=""; 
	
	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
			26:	'<html:imagesPath/>progressBar/progressbg_orange.gif',
	   		51: '<html:imagesPath/>progressBar/progressbg_yellow.gif',
			76: '<html:imagesPath/>progressBar/progressbg_green.gif'};
		barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};

	/** 
	*  Notifications Grid config begin
	*/
	SRNotificationsGrid = new dhtmlXGridObject('notifications_container_chngPopup');
 	SRNotificationsGrid.setImagePath("/LexmarkServicesPortal/images/gridImgs/");
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
 	SRNotificationsGrid.enablePaging(true, 5, 10, "notificationsPagingArea_chngPopup", true);
 	SRNotificationsGrid.setPagingSkin("bricks");
	/*alert('<?xml version="1.0" ?><rows><row id="1"><cell><![CDATA[hii]]></cell><cell><![CDATA[heloo]]></cell><cell><![CDATA[<a href="###"> fooo </a>]]></cell><cell><![CDATA[booo]]></cell></row></rows>');*/
 	SRNotificationsGrid.loadXMLString('${requestFormPopup.srNotifyXML}'); 
 	SRNotificationsGrid.setSortImgState(true, 0, 'asc');
 	SRNotificationsGrid.setColumnHidden(3,true);
 	SRNotificationsGrid.setColumnHidden(4,true);//Fix for defect 6328
 	SRNotificationsGrid.sortRows(0,"str","asc");

	function changeRqstDetails(ServiceRqstNumber,ServiceRqstType) {
		//alert('in changeRequestDetailsPopup ServiceRqstNumber='+ServiceRqstNumber+' ServiceRqstType='+ServiceRqstType);
		jQuery('#ChangeRqstNumber').html(ServiceRqstNumber);
		textScroll(document.getElementById('description_chngPopup'));
		textScroll(document.getElementById('costCenter_chngPopup'));
		textScroll(document.getElementById('notes_chngPopup'));
		jQuery('#div_progressBar').progressBar('${requestFormPopup.reqProgress}', { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '/LexmarkServicesPortal/images/progressBar/progressbar.gif', barImage:barImages} );
		//alert('before ajax');			
	}
	
	
	
</script>
<script type="text/javascript">
function downloadAttachment(attachmentName, dispAttachmentName, fileExtension, identifier){
		 <%--callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDOWNLOADATTACHMENT%>');--%>
		
		
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
</script>
