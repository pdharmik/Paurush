<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!--[if IE 8]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie8.css" %></style>
<![endif]--> 
<!-- Added for CI 13-10-22 STARTS-->
<portlet:resourceURL var="downloadAttachment" id="downloadAttachment">
</portlet:resourceURL>
<div id="contentDetailsChange" style="max-height: 500px; overflow-y: auto">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.installReqDetails"/></h1>
      <h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="HardwareRqstNumber"> ${requestFormPopup.srNumber}</span></h2></div>
     <br><br>
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
                  <span id="area_Popup">${requestFormPopup.serviceRequest.area.value}</span> 
                  <span id="coveredService" style="display: none;">- ${requestFormPopup.serviceRequest.otherRequestedService}</span>
                  </li>
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
        
                <table class="shipment wFull" >
                  <tr>
                  <td><div class="status">
<!--                         <div id="div_technicianProgressBar">PROGRESS BAR WILL COME HERE</div> -->
                        <div id="div_technicianProgressBar1" class="status-bar"></div><!-- status-bar -->
                       <c:if test="${requestFormPopup.reqProgress gt 0}">
                        <table border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><spring:message code="serviceRequest.label.inProcess"/></td>
                            
                            <td><spring:message code="reportStatus.PUBLISHOK"/></td>
                          </tr>
                        </table>
                        </c:if>
                        <c:if test="${requestFormPopup.reqProgress==0}">
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
              			<c:when test="${requestFormPopup.serviceRequest.otherRequestedService eq 'HW BAU Install Move'}">
	                     <li>
			                 <div>${requestFormPopup.serviceRequest.asset.moveFromAddress.storeFrontName}</div>	
			                  <util:addressOutput value="${requestFormPopup.serviceRequest.asset.moveFromAddress}"></util:addressOutput>
			                </li>
	                  </c:when>
	                  <c:otherwise>
	                   <li>	 
	                      <div>${requestFormPopup.serviceRequest.installAddress.storeFrontName}</div>	      
	                  <util:addressOutput value="${requestFormPopup.serviceRequest.installAddress}"></util:addressOutput>
	                  </li>
	                  </c:otherwise>
	                  </c:choose>
	                      </ul>
	                      </div>
                      	  </div>
                      	  
                      	  <div class="columnsTwo">
				          <div class="infoBox columnInner rounded shadow" id="moveInfoAddress" style="display:none">         
							 
							<h4><spring:message code="requestInfo.manageAsset.heading.moveToAddr"/></h4>
							 <ul class="roDisplay">
							 <c:if test="${requestFormPopup.serviceRequest.otherRequestedService eq 'HW BAU Install Move'}">    
			                 <li>
			                 <div>${requestFormPopup.serviceRequest.installAddress.storeFrontName}</div>	
			                  <util:addressOutput value="${requestFormPopup.serviceRequest.installAddress}"></util:addressOutput>
			                </li>
		                   <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
								<span>${requestFormPopup.serviceRequest.installAddress.physicalLocation1}</span>
		                      </li>
							  <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
		                       <span>${requestFormPopup.serviceRequest.installAddress.physicalLocation2}</span>
		                      </li>
							
							  <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
		                    <span>${requestFormPopup.serviceRequest.installAddress.physicalLocation3}</span>
		                      </li>
		                      <li><label><spring:message code="requestInfo.addressInfo.label.lbsIdentifier"/> </label> 
		                    <span>${requestFormPopup.serviceRequest.installAddress.LBSIdentifierFlag}</span>
		                      </li>
		                      </c:if>
		                      </ul>
		                 
                 		</div>
                 		</div>
                 	</div>
                  </td>
                 </tr>
                  
                </table>
        <!-- ACTIVITY BLOCK - START -->
        <%-- <c:if test="${requestFormPopup.serviceRequest.serviceActivityStatus!= null}"> --%>
        <c:if test="${SRTechnicianXMLFlagPopup}">
        <div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4 class="expand" id="sr_technician_h4"><spring:message code="Details.supplyRequestDetails.heading.activity"/></h4>
              <div class="collapse" id="sr_technician_div">
              <div class="icon"><img class="ui_icon_sprite widgets technician-large-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div>
              <div class="columnInner separatorV">
               
                
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
        </c:if>
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
      </div>
      <!-- MAIN CONTENT END --> 

	</div>
</div>

<script type="text/javascript">
	
function changeRqstDetails(ServiceRqstNumber,ServiceRqstType) {
	//alert('in changeRequestDetailsPopup ServiceRqstNumber='+ServiceRqstNumber+' ServiceRqstType='+ServiceRqstType);
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
var barImages = {50: '<html:imagesPath/>progressBar/progressbg_red.gif',   		
				100: '<html:imagesPath/>progressBar/progressbg_green.gif'};	
barImagesCancel = {0: '<html:imagesPath/>progressBar/progressbg_black.gif'};
		if('${requestFormPopup.reqProgress}' ==0){
			jQuery('#div_technicianProgressBar1').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImagesCancel} );
		}else{
				if('${requestFormPopup.reqProgress}'=='100'){
					jQuery('#div_technicianProgressBar1').progressBar(100, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
				}else{
					jQuery('#div_technicianProgressBar1').progressBar(50, { width:480,speed:25,increment:1,height:12,showText: false, boxImage: '<html:imagesPath/>progressBar/progressbar.gif', barImage:barImages} );
					}
			
		}
			


var SRArea='${requestFormPopup.serviceRequest.area.value}';
var SRSubArea='${requestFormPopup.serviceRequest.subArea.value}';
var coveredService='${requestFormPopup.serviceRequest.otherRequestedService}';
	if(coveredService =="HW BAU Install" || coveredService =="HW BAU Install Change"){
		jQuery('#installInfo').show();
		jQuery('#moveInfo').hide();
		jQuery('#pickUpInfo').hide();
		}
	else if(coveredService =="HW BAU Install Move"){
		jQuery('#installInfo').hide();
		jQuery('#moveInfo').show();
		jQuery('#pickUpInfo').hide();
		jQuery('#moveInfoAddress').show();
		
	}else if(coveredService =="HW BAU Install Decommission"){
		jQuery('#installInfo').hide();
		jQuery('#moveInfo').hide();
		jQuery('#pickUpInfo').show();
	}else{
		jQuery('#primaryAddress').hide();
		jQuery('#installInfo').show();
		jQuery('#moveInfo').hide();
		jQuery('#pickUpInfo').hide();
		jQuery('#moveInfoAddress').hide();
	}

jQuery(document).ready(function(){	
	<c:if test="${not empty requestFormPopup.serviceRequest.otherRequestedService}">
	jQuery('#coveredService').show();
	</c:if>
	jQuery('#sr_technician_div').show();
	
});
</script>

