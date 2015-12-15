<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@page import="java.util.Map"%>
<jsp:include page="/WEB-INF/jsp/common/validationMPS.jsp" />
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
      <h2 class="step"><spring:message code="ordermanagement.returns.heading.returnSupplies"/></h2> </div>
      
      <!-- Added for CI 7 BRD14-02-12 -->
      <div id="accNameAgreeName"><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      </strong></h3></div>
      <!-- END -->
      
    <div class="main-wrap" >
      <div class="content"> 
        <!-- MAIN CONTENT BEGIN -->
        <h3 class="pageTitle"><spring:message code="ordermanagement.returns.heading.returnSupplies"/> &ndash; <spring:message code="requestInfo.message.confirmation"/></h3>
        <div class="utilities printPage floatR" id="topEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="button.clickToEmail"/>" title="<spring:message code="button.clickToEmail"/>"></a></li>
            <li><a href="javascript: print();"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="button.clickToPrint"/>" title="<spring:message code="button.clickToPrint"/>"></a></li>
          </ul>
        </div>
        <div id="emailPrintWraper">
        <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/><span id="reqNumber">${manageReturnsForm.serviceRequest.serviceRequestNumber}</span> <spring:message code="requestInfo.message.confirmMessage2"/></p>
         <div class="portletBlock">
          <div class="columnsTwo">
             <div class="infoBox columnInner rounded shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>				
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="primaryCont_name"> ${manageReturnsForm.serviceRequest.primaryContact.firstName }   ${manageReturnsForm.serviceRequest.primaryContact.lastName } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="primaryCont_phone">${manageReturnsForm.serviceRequest.primaryContact.workPhone } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="primaryCont_email">${manageReturnsForm.serviceRequest.primaryContact.emailAddress }</span>  
	                  </li>
	                </ul>
	               
	              </div>
				   <c:if test="${manageReturnsForm.serviceRequest.secondaryContact.firstName != ''}">
				  <div class="infoBox columnInner rounded shadow" id="addiContact">
	                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
	               			
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="secondaryContact_name">${manageReturnsForm.serviceRequest.secondaryContact.firstName }  ${manageReturnsForm.serviceRequest.secondaryContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="secondaryContact_phone">${manageReturnsForm.serviceRequest.secondaryContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="secondaryContact_email">${manageReturnsForm.serviceRequest.secondaryContact.emailAddress }</span>
	                  </li>
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
	                    <span id="addtnlInfo_customerRefId">${manageReturnsForm.serviceRequest.customerReferenceId }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.costCentre"/></label>
	                    <span id="addtnlInfo_costCenter">${manageReturnsForm.serviceRequest.costCenter }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.description"/></label>
	                    <span id="addtnlInfo_description">${manageReturnsForm.serviceRequest.addtnlDescription }</span>
					  </li>
					
			  </ul>
            </div>
          </div>
        </div>
       
        
        <hr class="separator" />
        <div class="portletBlock infoBox">
          <div class="columnsTwo">
          <!-- Changed for CI Defect # 11926 -->
            <div class="infoBox columnInner rounded shadow">
            <h4><spring:message code="requestInfo.heading.details"/></h4>
              <ul class="form wordBreak">
                <li>
                  <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/></label>
                  <span id="subArea">${manageReturnsForm.returnsSubAreaValue }</span> </li>
                <li>
                  <label for="notes"><spring:message code="requestInfo.label.notes"/></label>
                  <span id="notes">${manageReturnsForm.notes}</span> </li>
              <%--  <li>
                  <label for="partQty">Parts &amp; Quantity:</label>
                  <span class="multiLine">${manageReturnsForm.partsQnty}</span> </li> --%>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="infoBox columnInner rounded shadow">
              <h4> <spring:message code="requestInfo.heading.returnAddress"/></h4>
              <div id="returnAddressSpan">
              <ul class="roDisplay">
               <%--Changes for CI Changes BRD13-10-08 STARTS--%>
              <li><div>${manageReturnsForm.returnAddress.storeFrontName}</div>
              <util:addressOutput value="${manageReturnsForm.returnAddress}"></util:addressOutput>
              </li>
                <li><label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
                <span id="building">${manageReturnsForm.returnAddress.physicalLocation1}</span></li>
                <li><label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                <span id="floor">${manageReturnsForm.returnAddress.physicalLocation2}</span></li>
                <li><label><spring:message code="requestInfo.addressInfo.label.office"/> </label> 
                <span id="office">${manageReturnsForm.returnAddress.physicalLocation3}</span></li>
                 <%--Changes for CI Changes BRD13-10-08 ENDS--%>
              </ul>
              </div>
            </div>
          </div>
        </div>
        
        
        <p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/> </p>
        <div class="columnInner" id="attachmentDiv">
           <c:if test="${fileMap != null}">
			 <div class="columnInner">			  	
					  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">						
						<table id="fileListTable">
						</table>
						<div class="lineClear"></div>							
					  </div>
						  
				  </div>
			  </c:if>
        </div>
        </div>
         </div>
        <div class="filterLinks floatR" id="btmEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" title="email" height="23" width="27"/><spring:message code="button.clickToEmail"/></a></li>
            <li><a href="javascript: print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" title="print" height="27" width="27"/><spring:message code="button.clickToPrint"/></a></li>
          </ul>
        </div>
      </div>
      <!-- MAIN CONTENT END --> 
    </div>
  
  
  
  <script type="text/javascript">
var c=1;
  <c:if test="${fileMap != null}">
	jQuery('#fileListTable').empty();
	responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
  //alert("responseText" + responseText);
	responseText = responseText + '<tbody>';
	<c:forEach items="${fileMap}" var="entry">
	if(c%2==0){
		responseText = responseText + '<tr class="altRow">';
	}
	else{
		responseText = responseText + '<tr>';
	}
	c++;
		responseText = responseText + '<td>'+ '<c:out value="${entry.key}" />' + '</td>';
		responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
		//responseText = responseText + '<td class="width5"><img id="img_help1"  src="<html:imagesPath/>tabbarImgs/default/close.png" style="cursor:help;" title="Delete" alt="Remove" onclick=\'removeFile("${entry.key}");\' /></td>';
		responseText = responseText + '</tr>';
		//alert("file name " + '<c:out value="${entry.key}" />');
		//alert("file size " + '<c:out value="${entry.value.fileSize}" />');
	</c:forEach>
	responseText = responseText + '</tbody>';
	jQuery('#fileListTable').append(responseText);
	//alert("end :::"+ document.getElementById("test").innerHTML);

</c:if>

<c:if test="${fileMap == null}">
jQuery('#fileListTable').empty();
</c:if>


textScroll(document.getElementById('addtnlInfo_description'));
textScroll(document.getElementById('addtnlInfo_costCenter'));
textScroll(document.getElementById('notes'));


  function print(){
	  callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESCONFIRMATION%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESCONFIRMATIONPRINTTHISPAGE%>');
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
  		callOmnitureAction('<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESCONFIRMATION%>','<%=LexmarkSPOmnitureConstants.RETURNSUPPLIESCONFIRMATIONEMAILTHISPAGE%>');
  			var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='returnSupplyemailConfirmationPage' /></portlet:renderURL>" ;

  	  		//alert("email--->"+url);
  		
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
  
 
