<%-- Added for CI BRD13-10-02--STARTS --%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%-- Added for CI BRD13-10-02--ENDS --%>
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
<script type="text/javascript" src='<html:rootPath/>js/portletRedirection.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/expand.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/jquery.progressbar.js'></script>
<script type="text/javascript">
////Toggle Containers
	jQuery(function() {
	    jQuery("h4.expand").toggler();
	});
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.installReqDetails"/></h1>
      <h2 class="step">
      	<spring:message code="Details.supplyRequestDetails.label.rquestNumber"/>
      	<span id="HardwareRqstNumber"> ${requestForm.srNumber}</span>
      </h2>
     </div>
     <br><br>
      <div class="utilities printPage floatR" id="topEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="button.clickToEmail"/>" title="<spring:message code="button.clickToEmail"/>"></a></li>
            <li><a href="javascript: print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="button.clickToPrint"/>" title="<spring:message code="button.clickToPrint"/>"></a></li>
          </ul>
        </div>
    <div class="main-wrap">
      <div class="content" id="hardwareDetails">
     
      <div id="emailPrintWraper"> 
        <!-- MAIN CONTENT BEGIN -->
        <div class="portletBlock infoBox rounded shadow split">
         <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions_Popup">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong><spring:message code="requestInfo.error.exceptionExist"/> - ${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          </c:if>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.area"/> </label>
                  <span id="area_Popup">${requestForm.serviceRequest.area.value}</span> 
                  <span id="coveredService" style="display: none;">- ${requestForm.serviceRequest.otherRequestedService}</span>
                  </li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/> </label>
                  <span id="subarea_Popup">${requestForm.serviceRequest.subArea.value }</span></li>
                <c:if test="${userSegment == 'Employee'}">
	                <li>
	                <label> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span id="orderSource_Popup">${requestForm.serviceRequest.orderSource }</span></li>
                  </c:if>
                

                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/> </label>
                  <span id="serviceRequestStatus_Popup">${requestForm.serviceRequest.serviceRequestStatus }</span></li>
                  
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/> </label>
                  
                  <span id = "creationTime_Popup">${requestForm.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/> </label>
                  <span id = "requestorName_Popup">${requestForm.serviceRequest.requestor.firstName } &nbsp; ${requestForm.serviceRequest.requestor.lastName }</span></li>
               <li>
               		<label><spring:message code="hardwareDetails.label.accName"/>	</label>
               		<span id="accountName_Popup">${requestForm.serviceRequest.accountName }</span>
               </li>
                   
             
               
              
                  
              </ul>
            </div>
          </div>
        </div>
		
        

        <hr class="separator" />
        <div class="portletBlock">
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
            
              <h4><spring:message code="requestInfo.heading.primaryServCont"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryname_Popup">${requestForm.serviceRequest.primaryContact.firstName }&nbsp; ${requestForm.serviceRequest.primaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryphone_Popup">${requestForm.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryemail_Popup">${requestForm.serviceRequest.primaryContact.emailAddress }</span></li>
              </ul>
              </div>
              <c:if test="${requestForm.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="secondarySection">
              <h4><spring:message code="requestInfo.heading.secondaryServCont"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryname_Popup">${requestForm.serviceRequest.secondaryContact.firstName }&nbsp; ${requestForm.serviceRequest.secondaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryphone_Popup">${requestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryemail_Popup">${requestForm.serviceRequest.secondaryContact.emailAddress }</span></li>
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
                  <span id="customerReferenceNumber_Popup">${requestForm.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter_Popup">${requestForm.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="addtnlDescription_Popup">${requestForm.serviceRequest.addtnlDescription}</span></li>
              </ul>
            </div>
          </div>
        </div>   
        <%-- ${requestForm.serviceRequestTechnicianXML } --%>
        <hr class="separator" />
        <div id="addressBlock">
                <table class="shipment wFull">
                  <tr>
                  <td><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_technicianProgressBar1" class="status-bar"></div><!-- status-bar -->
                       <c:if test="${requestForm.reqProgress gt 0}">
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            
                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
                          </tr>
                        </table>
                        </c:if>
                        <c:if test="${requestForm.reqProgress==0}">
	                        <table border="0" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <td colspan="3"><spring:message code="serviceRequest.label.cancelled" /></td>
	                            
	                          </tr>
	                        </table>
                        </c:if>
                      </div></td>
                  
                      
                      </tr>
                      <tr>
                      <td>
                      <div class="portletBlock">
				          <div class="columnsTwo">
				          <div class="infoBox columnInner rounded shadow">
                      
	                      <h4 id="installInfo" style="display: none"><spring:message code="requestInfo.heading.installAddress"/></h4>
	              				<h4 id="moveInfo" style="display: none"><spring:message code="requestInfo.heading.currentInstallAdd"/></h4>
	              				<h4 id="pickUpInfo" style="display: none"><spring:message code="requestInfo.cm.manageAsset.heading.pickupAddress"/></h4>
	              			
	              			 <ul class="roDisplay" id="primaryAddress">
	              			  <c:choose>
              			<c:when test="${requestForm.serviceRequest.otherRequestedService eq 'HW BAU Install Move'}">
	                     <li>
			                 <div>${requestForm.serviceRequest.asset.moveFromAddress.storeFrontName}</div>	
			                  <util:addressOutput value="${requestForm.serviceRequest.asset.moveFromAddress}"></util:addressOutput>
			                </li>
	                  </c:when>
	                  <c:otherwise>
	                   <li>	 
	                      <div>${requestForm.serviceRequest.installAddress.storeFrontName}</div>	      
	                  <util:addressOutput value="${requestForm.serviceRequest.installAddress}"></util:addressOutput>
	                  </li>
	                  </c:otherwise>
	                  </c:choose>
	                      </ul>
	                      </div>
                      	  </div>
                      	  
                      	  <div class="columnsTwo">
				          <div class="infoBox columnInner rounded shadow" id="moveInfoAddress" style="display:none">         
							 
							<h4> <spring:message code="requestInfo.manageAsset.heading.moveToAddr"/></h4>
							 <ul class="roDisplay">
							 <c:if test="${requestForm.serviceRequest.otherRequestedService eq 'HW BAU Install Move'}">    
			                 <li>
			                 <div>${requestForm.serviceRequest.asset.installAddress.storeFrontName}</div>	
			                  <util:addressOutput value="${requestForm.serviceRequest.installAddress}"></util:addressOutput>
			                </li>
		                   <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
								<span>${requestForm.serviceRequest.installAddress.physicalLocation1}</span>
		                      </li>
							  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
		                       <span>${requestForm.serviceRequest.installAddress.physicalLocation2}</span>
		                      </li>
							
							  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
		                    <span>${requestForm.serviceRequest.installAddress.physicalLocation3}</span>
		                      </li>
		                      </c:if>
		                      </ul>
		                 
                 		</div>
                 		</div>
                 	</div>
                  </td>
                 </tr>
                  
                </table>
                </div>
        <!-- ACTIVITY BLOCK - START -->
        <%-- <c:if test="${requestForm.serviceRequest.serviceActivityStatus!= null}"> --%>
        <c:if test="${SRTechnicianXMLFlag}">
        <div class="portletBlock infoBox rounded shadow" id="activityBlockId">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" id="sr_technician_h4"><spring:message code="Details.supplyRequestDetails.heading.activity"/></h4>
              <div class="collapse" id="sr_technician_div">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <%-- <c:if test="${requestForm.serviceRequestTechnicianXML!= null}"> --%>
                <div id="div_technicianPrintTable_Popup">
							<div id="tab_technicianGrid_Popup" style="display:block;">
							
								<div id="div_technicianContainer_Popup" class="div-style48">
								</div>
    							<div id="div_technicianPagingArea_Popup" class="popup_grid"></div>
    						</div>
    				</div>
    			<%-- </c:if> --%>
              </div>
              </div>
            </div>
          </div>
        </div>
        </c:if>
        <%-- </c:if> --%>
        <!-- ACTIVITY BLOCK - END -->
       
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notes"/> &amp; <spring:message code="requestInfo.heading.attachments"/></p>
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="orderSupplies.notes.notes"/></h4>
              <ul class="roDisplay wordBreak">
              <li><span id="notes_Popup">${requestForm.serviceRequest.notes}</span></li>
            </ul>
            </div>
          </div>
        </div>
        
        <div class="portletBlock">
          <div class="columnInner">
          <div class="wHalf displayGrid columnInnerTable rounded shadow">
            <table>
              <thead>
                <tr>
                  <th><spring:message code="requestInfo.heading.attachments"/></th>
                </tr>
              </thead>
              <tr><td>
              <div id ="showAttachment_Popup">
              		<% int c = 1; %>
              		<c:forEach items="${requestForm.attachList}" var="entry" >
              			<% if(c%2==0){ %>
	              			<tr class="altRow" >
	              				<c:if test="${entry.extension != 'URL'}">
					           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td>${entry.attachmentName}</td>
					           	</c:if>
					        </tr>
              			<% }else{ %>
	              			<tr>
					           	<c:if test="${entry.extension != 'URL'}">
					           		<td>${entry.displayAttachmentName}.${entry.extension}</td>
					           	</c:if>
					           	<c:if test="${entry.extension == 'URL'}">
					           		<td>${entry.attachmentName}</td>
					           	</c:if>
					        </tr>
              			<% }
              			   c++;
              			%>
						
					</c:forEach>
              </div></td></tr>
            </table>
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
      </div>
      <!-- MAIN CONTENT END --> 

	</div>


<script type="text/javascript">
	



/* if(${requestForm.serviceRequest.serviceActivityStatus !=null && requestForm.serviceRequestTechnicianXML != null }){ */
	<c:if test="${SRTechnicianXMLFlag}">
	SRTechnicianGrid = new dhtmlXGridObject('div_technicianContainer_Popup');
	
	SRTechnicianGrid.setImagePath("<html:imagesPath/>gridImgs/");
	SRTechnicianGrid.setHeader("<spring:message code='requestInfo.listHeader.hwInstallFields'/>");
	SRTechnicianGrid.setColAlign("left,left,left,left,left,left,left,left");
	SRTechnicianGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro");
	
	SRTechnicianGrid.setInitWidths("30,100,100,100,100,100,100,*");
	SRTechnicianGrid.enableAutoWidth(true);
	SRTechnicianGrid.enableMultiline(true);
	SRTechnicianGrid.enableAutoHeight(true);
	SRTechnicianGrid.init(); 
	SRTechnicianGrid.prftInit();
	SRTechnicianGrid.enablePaging(true, 5, 10, "div_technicianPagingArea_Popup", true);
	SRTechnicianGrid.setPagingSkin("bricks");
	SRTechnicianGrid.setSkin("light");
	
	SRTechnicianGrid.loadXMLString('${requestForm.serviceRequestTechnicianXML }');
	</c:if>

//}	
var barImages = {50: '<html:imagesPath/>progressBar/progressbg_red.gif',   		
				100: '<html:imagesPath/>progressBar/progressbg_green.gif'};	
barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
		if('${requestForm.reqProgress}' ==0){
			jQuery('#div_technicianProgressBar1').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
		}else{
				if('${requestForm.reqProgress}'=='100.0'){
					jQuery('#div_technicianProgressBar1').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
				}else{
					jQuery('#div_technicianProgressBar1').progressBar(50, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
					}
			
		}
			

function print() {

	
	 
	jQuery('#sr_technician_div').show();
	
	
	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		"<portlet:param name='action' value='printHwInstallDetails' />" + 
		"</portlet:renderURL>";

    var iWidth=1000;
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

function email() {
	
	
	  
	jQuery('#sr_technician_div').show();
	

	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		"<portlet:param name='action' value='emailHwInstallDetails' />" + 
		"</portlet:renderURL>";

    var iWidth=1000;
    var iHeight=530;
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

var SRArea='${requestForm.serviceRequest.area.value}';
var SRSubArea='${requestForm.serviceRequest.subArea.value}';
var coveredService='${requestForm.serviceRequest.otherRequestedService}';
	if(coveredService =="HW BAU Install" || coveredService =="HW BAU Install Change"){
		jQuery('#installInfo').show();
		jQuery('#moveInfo').hide();
		jQuery('#pickUpInfo').hide();
		subArea="Install Request";
		}
	else if(coveredService =="HW BAU Install Move"){
		jQuery('#installInfo').hide();
		jQuery('#moveInfo').show();
		jQuery('#pickUpInfo').hide();
		jQuery('#moveInfoAddress').show();
		subArea="Move Request";
		
	}else if(coveredService =="HW BAU Install Decommission"){
		jQuery('#installInfo').hide();
		jQuery('#moveInfo').hide();
		jQuery('#pickUpInfo').show();
			subArea="Decommission Request";
	}else{
		jQuery('#primaryAddress').hide();
		jQuery('#installInfo').show();
		jQuery('#moveInfo').hide();
		jQuery('#pickUpInfo').hide();
		jQuery('#moveInfoAddress').hide();
			srArea='${requestForm.serviceRequest.area.value}';
			subArea='Update Request';
	  		pagetype="change";
	}


function cancelRequest(){
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTCANCELREQUESTBUTTON%>');
	showOverlay();
	//var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','change']);
	var forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea','pagetype'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'cancel','Cancel Request',subArea,'change']);
	window.location.href=forwardURL;
}

function updateRequest(){
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.CHANGEREQUESTDETAILS%>','<%=LexmarkSPOmnitureConstants.CHANGEREQUESTUPDATEREQUESTBUTTON%>');
	showOverlay();
	var area = '${updateTypeFlag}';
	var page ;
	var forward ;
	var forwardURL ;
	
		forwardURL = populateURL("managechlothers",['forward', 'serviceRequestNumber', 'typeOfFlow','area','subArea'],['Others', '${requestForm.serviceRequest.serviceRequestNumber }', 'update','Update Request',subArea]);

	window.location.href=forwardURL;
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
jQuery(document).ready(function(){	
	<c:if test="${not empty requestForm.serviceRequest.otherRequestedService}">
	jQuery('#coveredService').show();
	</c:if>
	jQuery('#sr_technician_div').show();
	
});
</script>

