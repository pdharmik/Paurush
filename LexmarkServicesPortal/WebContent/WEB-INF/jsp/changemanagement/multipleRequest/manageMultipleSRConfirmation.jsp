<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>

<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>

	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style> 
<![endif]-->

<c:set var="pageId" value="srEmail"/>



  <div id="content-wrapper" id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>  <h2 class="step" id="flowType">${requestSubType} </h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div id="accNameAgreeName"><h3><strong>
    <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    </strong></h3></div>
    <!-- END -->
    
    <div class="main-wrap" id="main-wrap">
      <div class="content">
          <!-- MAIN CONTENT BEGIN -->
          
            <h3 class="pageTitle"><spring:message code="requestInfo.heading.requestConfirmation"/></h3>
           <div class="utilities printPage floatR">
		    <ul>
		      <li class="first"><a href="javascript:email();" ><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="requestInfo.link.emailThisPage"/>" title="<spring:message code='requestInfo.link.emailThisPage'/>"></a></li>
		      <li><a href="javascript: print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code='requestInfo.link.printThisPage'/>" title="<spring:message code='requestInfo.link.printThisPage'/>"></a></li>
		    </ul>
		  </div>
		  <div id="emailPrintWraper">
		  <c:if test="${requestNumber == null || requestNumber == ''}">
		  <p class="info banner err"><spring:message code="requestInfo.error.unableToAccept"/></p>
		  </c:if>
		  <c:if test="${requestNumber != null && requestNumber != ''}">
		  <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/><span id="reqNumber">${requestNumber}</span>	<spring:message code="requestInfo.message.confirmMessage2"/>.</p>
		  </c:if>
		  
		          
		<form:form id="multipleForm" name="templateRequestConfirmationForm" commandName="templateRequestForm"  method="post" action="">
		
          <div class="portletBlock">
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox">
			  <div class="borderBottomLightGray">
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
				</div>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="primaryCont_name">${templateRequestForm.serviceRequest.primaryContact.firstName } &nbsp; ${templateRequestForm.serviceRequest.primaryContact.lastName }</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="primaryCont_phone"> ${templateRequestForm.serviceRequest.primaryContact.workPhone }</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="primaryCont_email"> ${templateRequestForm.serviceRequest.primaryContact.emailAddress }</span> </li>
                </ul>
              </div>
			  <div class="columnInner rounded infoBox" id="addiContact">
			  <div class="borderBottomLightGray">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
				</div>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                     <span id="secondaryCont_name">${templateRequestForm.serviceRequest.secondaryContact.firstName } &nbsp; ${templateRequestForm.serviceRequest.secondaryContact.lastName }</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                     <span id="secondaryCont_phone">${templateRequestForm.serviceRequest.secondaryContact.workPhone }</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                     <span id="secondaryCont_email">${templateRequestForm.serviceRequest.secondaryContact.emailAddress }</span> </li>
                </ul>
              </div>
            </div>
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox addiInfo">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>
				<div class="borderBottomLightGray"></div>
                <ul class="form wordBreak">
                  <li>
                    <label for="refId"><spring:message code="requestInfo.label.customerRefereceId"/></label>
                     <span id="addtnlInfo_customerRefId">${templateRequestForm.serviceRequest.customerReferenceId}</span>
					
				  </li>
                  <li>
                    <label for="costCntr"><spring:message code="requestInfo.label.costCentre"/></label>
                   <span id="addtnlInfo_costCenter"> ${templateRequestForm.serviceRequest.costCenter} </span>
					
				  </li>
                  <li>
                    <label for="desc"><spring:message code="requestInfo.label.description"/></label>
                    <span id="addtnlInfo_description">${templateRequestForm.serviceRequest.addtnlDescription} </span>
					
				  </li>
				  <li><label for="date1"><spring:message code="requestInfo.label.dateOfChange"/></label>
					<span id="reqEffectiveDate"><util:dateFormat value="${templateRequestForm.serviceRequest.requestedEffectiveDate}">
					</util:dateFormat>
					</span>
					
					</li>
                </ul>
			  </div>
            </div>
          </div>
		
		 		
			<div class="lineClear"></div>
			
		  
			
						
			<div class="columnInner">
			  <div class="wHalf displayGrid columnInnerTable rounded shadow">
			  
				<div class="lineClear"></div>
				<table>
				<thead>
				<tr class="tableHeader">				
					<th class="width60"><spring:message code="requestInfo.heading.attachmentFileName"/></th>				
				</tr>
				</thead>
				
				<tbody>
				<tr class="tableContentColor">				
					<td class="width60"><span id="attachedFile">${templateRequestForm.fileName}</span></td>				
				</tr>	
				</tbody>
				</table>
			
				<div class="lineClear"></div>							
			  </div>
			  </div>
			
		
			
	
		  </form:form>
		   </div>
         <div class="filterLinks floatR">
		  <ul>
		    <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code='requestInfo.link.emailThisPage'/>" height="23" width="27"/><spring:message code="requestInfo.link.emailThisPage"/></a></li>
		    <li><a href="javascript: print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code='requestInfo.link.printThisPage'/>" height="27" width="27"/><spring:message code="requestInfo.link.printThisPage"/></a></li>
		  </ul>
		</div>
  
        <!-- MAIN CONTENT END --> 
       
      </div>
    </div>
  </div>


<script type="text/javascript">

jQuery(document).ready( function() {

		if ("${templateRequestForm.serviceRequest.secondaryContact.firstName }"=="" || "${templateRequestForm.serviceRequest.secondaryContact.firstName }"==null) 
		{
			document.getElementById('addiContact').style.display = "none";
		}

		textScroll(document.getElementById('addtnlInfo_description'));
		textScroll(document.getElementById('addtnlInfo_costCenter'));
		  
});

//This piece od code disables the back button/functionality of browser.
function noBack(){window.history.forward();setTimeout("noBack()", 500);}
noBack();
window.onload=noBack;
window.onpageshow=function(evt){if(evt.persisted)noBack();};
window.onunload=function(){void(0);};

function print(){
	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printConfirmationPage' /></portlet:renderURL>";         
    var iWidth=900;
    var iHeight=5600;
    var iTop = (window.screen.availHeight-30-iHeight)/2;        
    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
    window.open(url,
            'ConfirmAssetOrder',
            'height='+iHeight+
            ',innerHeight='+
            iHeight+',width='+
            iWidth+',innerWidth='+
            iWidth+',top='+iTop+
            ',left='+iLeft+
            ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};

	
	function email() {
		
			var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='multipleSRemailConfirmationPage' /></portlet:renderURL>" ;
			
		
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
		
</script>
