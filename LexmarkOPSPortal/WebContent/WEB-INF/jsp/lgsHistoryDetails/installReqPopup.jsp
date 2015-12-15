<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%> 
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>
<script type="text/javascript"
	src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js">
	
	</script>
<%-- <script src='<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js'></script>
<script src='<html:rootPath/>js/validation.js'></script>
<script src='<html:rootPath/>js/grid/ext/dhtmlxgrid_srnd.js'></script>
<script src='<html:rootPath/>js/i18n/jquery-ui-i18n.js'></script> --%>

<style>

</style>
<div id="contentDetailsInstall" style="max-height: 500px; overflow-y: auto">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.installReqDetails"/></h1>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="HardwareRqstNumber">${requestFormPopup.srNumber}</span></h2> </div>
     <br><br>
      <div class="utilities printPage floatR" id="topEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="button.clickToEmail"/>" title="<spring:message code="button.clickToEmail"/>"></a></li>
            <li><a href="javascript: print();"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="button.clickToPrint"/>" title="<spring:message code="button.clickToPrint"/>"></a></li>
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
                  <span id="area_Popup">${requestFormPopup.serviceRequest.area.value }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.subArea"/> </label>
                  <span id="subarea_Popup">${requestFormPopup.serviceRequest.subArea.value }</span></li>
                <c:if test="${userSegment == 'Employee'}">
	                <li>
	                <label> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></label>
	                <span id="orderSource_Popup">${requestFormPopup.serviceRequest.orderSource }</span></li>
                  </c:if>
                

                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.status"/> </label>
                  <span id="serviceRequestStatus_Popup">${requestFormPopup.serviceRequest.serviceRequestStatus }</span></li>
                  
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.dateAndTime"/> </label>
                  
                  <span id = "creationTime_Popup">${requestFormPopup.creationDate }</span></li>
                <li>
                  <label><spring:message code="Details.changeRequestDetails.label.requesterName"/> </label>
                  <span id = "requestorName_Popup">${requestFormPopup.serviceRequest.requestor.firstName } &nbsp; ${requestFormPopup.serviceRequest.requestor.lastName }</span></li>
               <li>
               		<label><spring:message code="hardwareDetails.label.accName"/>	</label>
               		<span id="accountName_Popup">${requestFormPopup.serviceRequest.accountName }</span>
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
                  <span id="primaryname_Popup">${requestFormPopup.serviceRequest.primaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.primaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="primaryphone_Popup">${requestFormPopup.serviceRequest.primaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="primaryemail_Popup">${requestFormPopup.serviceRequest.primaryContact.emailAddress }</span></li>
              </ul>
              </div>
              <c:if test="${requestFormPopup.serviceRequest.secondaryContact.firstName != ''}">
            <div class="infoBox columnInner rounded shadow" id="secondarySection">
              <h4><spring:message code="requestInfo.heading.secondaryServCont"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryname_Popup">${requestFormPopup.serviceRequest.secondaryContact.firstName }&nbsp; ${requestFormPopup.serviceRequest.secondaryContact.lastName }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  <span id="secondaryphone_Popup">${requestFormPopup.serviceRequest.secondaryContact.workPhone }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.emailAddress"/></label>
                  <span id="secondaryemail_Popup">${requestFormPopup.serviceRequest.secondaryContact.emailAddress }</span></li>
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
                  <span id="customerReferenceNumber_Popup">${requestFormPopup.serviceRequest.customerReferenceNumber }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.costCentre"/></label>
                  <span id="costCenter_Popup">${requestFormPopup.serviceRequest.costCenter }</span></li>
                <li>
                  <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="addtnlDescription_Popup">${requestFormPopup.serviceRequest.addtnlDescription}</span></li>
              </ul>
            </div>
          </div>
        </div>   
        <%-- ${requestFormPopup.serviceRequestTechnicianXML } --%>
        <hr class="separator" />
        <!-- ACTIVITY BLOCK - START -->
        <%-- <c:if test="${requestFormPopup.serviceRequest.serviceActivityStatus!= null}"> --%>
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" id="sr_technician_h4"><spring:message code="Details.supplyRequestDetails.heading.activity"/></h4>
              <div class="collapse" id="sr_technician_div">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                <table class="shipment wFull" >
                  <tr>
                    <td class="w70p"><div class="status" style="float: left; width: 400px ! important;">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_technicianProgressBar1_Popup" class="status-bar"></div><!-- status-bar -->
                        <table border="0" cellspacing="0" cellpadding="0" style="width: 360px">
                          <tr>
                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            
                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
                          </tr>
                        </table>
                      </div></td>
                      <td><b><spring:message code="requestInfo.heading.installAddress"/></b></td>
                      </tr>
                      <tr>
                      <td>
                             
					       
                  <util:addressOutput value="${requestFormPopup.serviceRequest.installAddress}"></util:addressOutput>
                  </td>
                 </tr>
                  
                  
                  
                      <!-- <td class="labelBold" width = "30%" align = "left">Install Address</td>
                      <tr><td><li>740 W. New Circle Road</li>
                      <li>Lexington, Kentucky, 40550</li>
                      <li>USA</li></td>
                      </tr> -->
                    
                  
                </table>
                
                <%-- <c:if test="${requestFormPopup.serviceRequestTechnicianXML!= null}"> --%>
                <div id="div_technicianPrintTable_Popup">
							<div id="tab_technicianGrid_Popup" style="display:block;">
							
								<div id="div_technicianContainer_Popup" style="background-color: white;width:100%;">
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
        <%-- </c:if> --%>
        <!-- ACTIVITY BLOCK - END -->
       
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notes"/> &amp; <spring:message code="requestInfo.heading.attachments"/></p>
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="orderSupplies.notes.notes"/></h4>
              <ul class="roDisplay wordBreak">
              <li><span id="notes_Popup">${requestFormPopup.serviceRequest.notes}</span></li>
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
              		<c:forEach items="${requestFormPopup.attachList}" var="entry" >
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
		<div class="buttonContainer" width="100%" align="right">
		<button id="btnClose" type="button" class="button" onclick="Liferay.Popup.close(this);"><spring:message code='button.close'/></button></div>
      </div>
      <!-- MAIN CONTENT END --> 

	</div>
</div>

<script type="text/javascript">
	


	
	function installRqstDetails(ServiceRqstNumber,ServiceRqstType) {
		
		jQuery('#HardwareRqstNumber').html(ServiceRqstNumber);
		
	}



/* if(${requestFormPopup.serviceRequest.serviceActivityStatus !=null && requestFormPopup.serviceRequestTechnicianXML != null }){ */
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

SRTechnicianGrid.loadXMLString('${requestFormPopup.serviceRequestTechnicianXML }');


//}	
barImages = { 0: '<html:imagesPath/>progressBar/progressbg_red.gif',
		45:	'<html:imagesPath/>progressBar/progressbg_half.gif',   		
		70: '<html:imagesPath/>progressBar/progressbg_green.gif'};
		jQuery('#div_technicianProgressBar1_Popup').progressBar(${requestFormPopup.serviceRequestTechnicianProgress}, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
 


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

</script>

