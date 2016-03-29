<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>

<!-- CSS include -->

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<% request.setAttribute("subTabSelected","requestHistory");%>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<jsp:include page="../includeGrid.jsp"></jsp:include>
<%-- <%@ include file="../includeGrid.jsp"%> --%> 
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/portletRedirection.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>

  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="Details.returnRequestDetails.heading.returnReqDeails"/></h1>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> ${requestForm.serviceRequest.serviceRequestNumber }</h2> </div>
    <div class="main-wrap">
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
        <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>Exception exists - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
        <div class="portletBlock infoBox rounded shadow split">
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
              <%--  <li>
                  <label><spring:message code="Details.changeRequestDetails.label.area"/></label>
                  <span>${requestForm.serviceRequest.area.value }</span></li>--%>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/></label>
                  <span>${requestForm.serviceRequest.subArea.value }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/></label>
                  <span>${requestForm.serviceRequest.serviceRequestStatus }</span></li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></label>
 					<span><util:dateFormat value="${requestForm.serviceRequest.serviceRequestDate}" timezoneOffset="${timeZoneOffset}"></util:dateFormat></span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/></label>
                  <span>${requestForm.serviceRequest.requestor.firstName } &nbsp; ${requestForm.serviceRequest.requestor.lastName }</span></li>
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
              <div  class="collapse">
              
              	<div id="div_statusPrintTable">
					<div id="tab_statusGrid" style="display:block;">
						<div id="div_status_container" style="background-color: white;width:100%;"></div>
	    				<div id="div_statusPagingArea"></div>
					</div>
				</div>
				
              </div>
            </div>
          </div>
        </div>
        <!-- Request Status History - End -->
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span>${requestForm.serviceRequest.primaryContact.firstName }&nbsp; ${requestForm.serviceRequest.primaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span>${requestForm.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span>${requestForm.serviceRequest.primaryContact.emailAddress } </span></li>
              </ul>
           
            </div>    
            <c:if test="${requestForm.serviceRequest.secondaryContact != null && requestForm.serviceRequest.secondaryContact.firstName != ''}" >        
             <div class="infoBox columnInner rounded shadow">
             
              
              <h4><spring:message code="Details.changeRequestDetails.heading.secondaryContact"/></h4>
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span>${requestForm.serviceRequest.secondaryContact.firstName }&nbsp; ${requestForm.serviceRequest.secondaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span>${requestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span>${requestForm.serviceRequest.secondaryContact.emailAddress }</span></li>
              </ul>
             
            </div>
             </c:if>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                  <span>${requestForm.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span>${requestForm.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span class="multiLine noteWrap">${requestForm.serviceRequest.addtnlDescription}</span></li>
               
              </ul>
            </div>
          </div>
        </div>
        <hr class="separator" />
        
        <!-- Below section available only for CHL and Others -->
		
			<div class="portletBlock infoBox">
	          <div class="columnsTwo">
	             <div class="infoBox columnInner rounded shadow"  style="min-height:180px;">
	              <h4> <spring:message code="requestInfo.label.notes"/></h4>
	              <!-- <ul class="form">
	                <li>
	                  <%-- <label for="notes"><spring:message code="requestInfo.label.notes"/></label> --%>
	                  <span class="multiLine">${requestForm.serviceRequest.notes }</span>
	                </li>
	              </ul> -->
	              <div class="noteWrap notesOverflow">${requestForm.serviceRequest.notes }</div>
	            </div>
	          </div>
	          
	           <div class="columnsTwo">
	            <div class="infoBox columnInner rounded shadow">
	              <h4> <spring:message code="requestInfo.heading.returnAddress"/></h4>
	              <ul class="roDisplay">
	                <li><div>${requestForm.serviceRequest.serviceAddress.addressLine1}</div>
	                 	<div>${requestForm.serviceRequest.serviceAddress.addressLine2}</div>	                  
	                    <span>${requestForm.serviceRequest.serviceAddress.city} <c:if test="${not empty requestForm.serviceRequest.serviceAddress.state }">,</c:if> ${requestForm.serviceRequest.serviceAddress.state} <c:if test="${not empty requestForm.serviceRequest.serviceAddress.province}">/</c:if>${requestForm.serviceRequest.serviceAddress.province} &nbsp; ${requestForm.serviceRequest.serviceAddress.postalCode}</span>
	                   	<div>${requestForm.serviceRequest.serviceAddress.country}</div>
	                </li>
	                <li><span> <spring:message code="requestInfo.addressInfo.label.building"/>   ${requestForm.serviceRequest.serviceAddress.physicalLocation1}</span></li>
	                <li><span><spring:message code="requestInfo.addressInfo.label.floor"/>    ${requestForm.serviceRequest.serviceAddress.physicalLocation2}</span></li>
	                <li><span><spring:message code="requestInfo.addressInfo.label.office"/>    ${requestForm.serviceRequest.serviceAddress.physicalLocation3}</span></li>
	              </ul>
	            </div>
	          </div>	
	        </div>
	        
	        
	         <p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
	        <div class="portletBlock">
	        <div class="columnInner">
	        <div class="wHalf displayGrid columnInnerTable rounded shadow">
	          <table>
	          <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	              </tr>
	            </thead>
	            <tbody>
	            	<% int c = 1; %>
              		<c:forEach items="${requestForm.attachList}" var="entry" >
              			<% if(c%2==0){ %>
	              			<tr class="altRow" >
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" >${entry.displayAttachmentName}.${entry.extension}</a></td>
					        </tr>
              			<% }else{ %>
	              			<tr>
					           		<td><a href="javascript:void(0);" onclick="downloadAttachment('${entry.attachmentName}','${entry.displayAttachmentName}','${entry.extension}', '${entry.activityId}');" >${entry.displayAttachmentName}.${entry.extension}</a></td>
					        </tr>
              			<% }
              			   c++;
              			%>
						
					</c:forEach>
	              
	            </tbody>
	          </table>
	        </div>
	        </div>
	        </div>
        

		
        
        
        <p class="inlineTitle"><spring:message code="Details.changeRequestDetails.heading.notifications"/></p>
        <div class="portletBlock">
        <div class="columnInner">
<!--        			<div id="div_notificationsPrintTable"> -->
					<div id="notificationsGrid" style="display:block;">
						<div id="div_notifications_container" style="width:100%;"></div>
    					<div id="div_notificationsPagingArea"></div>
    				</div>
        </div>
        </div>
          <div class="buttonContainer">
	          <button class="button_cancel" type="button" onclick="backToHistory();"><spring:message code="Details.changeRequestDetails.button.back"/></button>
	         <c:if test="${requestForm.serviceRequestShipments == null || requestForm.serviceRequest.serviceRequestStatus != 'Completed'}">
	          <button class="button_cancel" id="cancelButton" type="button" onclick="showAccountPopup('cancel');"><spring:message code="Details.changeRequestDetails.button.cancelReq"/></button>
	          </c:if>
	         
          </div>
          
        </div>
        <!-- MAIN CONTENT END --> 
      </div>
  </div>
<script type="text/javascript">
	
	var buttonName=""; 
	jQuery(document).ready(function() {
		var currentURL = window.location.href;
		if(currentURL.indexOf('/partner-portal') != -1)
		{	
			jQuery('#topnavigation li a').each(function(){
				  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
				  jQuery(this).parent().addClass('selected');
				  });
			
			jQuery('#cancelButton').hide();
		}
		jQuery('#div_progressBar').progressBar('${requestForm.reqProgress}', { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );

	});
	
	////Toggle Containers
	jQuery(function() {
	    jQuery("h4.expand").toggler();
	});

	barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',
   		55: '<html:imagesPath/>progressBar/progressbg_orange.gif',
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
	barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
	
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
	
  	function cancelRequest(){
  		showOverlay();
  		var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel']);
  		window.location.href=forwardURL;
  	}
	
  	function showAccountPopup(buttName){
  		
  		var mdmLevel="${mdmLevelFromDetails}";
  		showOverlay();
  		if(mdmLevel != "Account")
		{
  			
				
				dialog=jQuery('#totalContent').dialog({
					autoOpen: false,
					modal: true,
					height: 460,
					width: 700,
					minHeight: 300,
					position: ['center','top'],
					open: function(){
							jQuery('#totalContent').show();
							jQuery('#accountInitialize').show();
		  					jQuery('span.ui-dialog-title').text("<spring:message code='requestInfo.heading.accountSelection'/>");
				  			if(accountGrid==null){
				  					jQuery('#accountListGrid').addClass('gridbox gridbox_light');
				  					initializeAccountGrid();
				  			}else{
				  						if(accountGrid.getAllRowIds()=="")
											reloadAccountPopupGrid();
							}
							
		  					
							},
					close: function(event,ui){
		   				  //jQuery('.ui-dialog').empty().remove();
		   				  hideOverlay();
		   				  dialog.dialog('destroy');

		   				 // jQuery('#accountInitialize').appendTo(jQuery('#totalContent'));
						  dialog=null;
						  //alert('in change Request close');
						  jQuery('#accountInitialize').hide();
						  //alert(buttName);
						  if(ajaxAccountSelection=="success")
						  {
							  if(buttName=="update")
						  		updateRequest();
							  else
								cancelRequest();  
						  }
						}
					});
				
				jQuery(document).scrollTop('0');
				dialog.dialog('open');
				
		}
  	  }
  	  	
  	function updateRequest(){
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
  		else{
  			forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update']);
  		}
 		window.location.href=forwardURL;
  	}
  	
  	function downloadAttachment(attachmentName, dispAttachmentName, fileExtension, identifier){
		
  		window.location = "${downloadAttachment}&attachmentName=" + attachmentName + "&fileExtension=" + fileExtension+ "&identifier=" + identifier + "&dispAttachmentName=" + dispAttachmentName;
  	}
  	
  	function backToHistory(){
  		showOverlay();
  		//alert("Inside backToHistory::" + '${requestForm.sourcePage}');
  		var backUrl =  "<portlet:renderURL><portlet:param name='action' value='backToHistory' />	</portlet:renderURL>" ;
  		backUrl = backUrl+'&requestTypeStr=' + '${requestForm.sourcePage}';
  		//alert("backUrl:: " + backUrl);
  		window.location=backUrl;
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
	
</script>

