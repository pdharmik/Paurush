<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ page import="com.lexmark.services.form.FileUploadForm"%>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<div id="content-wrapper">
    <div class="journal-content-article">
    <h1><span id="changeRequest"><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
    <h2 class="step"><spring:message code = "requestInfo.cm.label.acceptanceRequest"/></h2></div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div id="accNameAgreeName"><h3><strong>
    <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    </strong></h3></div>
    <!-- END -->
    
    <div class="main-wrap">
	<div class="content">
				
		<!-- <div class="right-column"> -->
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code = "requestInfo.heading.acceptanceRequest"/> - <spring:message code="requestInfo.message.confirmation"/></h3>
          <div class="utilities printPage floatR" id="topEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="button.clickToEmail"/>" title="<spring:message code="button.clickToEmail"/>"></a></li>
            <li><a href="javascript: print();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="button.clickToPrint"/>" title="<spring:message code="button.clickToPrint"/>"></a></li>
          </ul>
        </div>
          <div id="emailPrintWraper">
          <div class="columnInner" id="headerMesg1">          
          <c:if test="${error ne null && error != ''}">
		  <p class="info banner err"><span>${error}</span></p>
		  </c:if>
		  <c:if test="${assetAcceptForm.serviceRequest.serviceRequestNumber != null && assetAcceptForm.serviceRequest.serviceRequestNumber != ''}"> 
        <p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/><span id="reqNumber">${assetAcceptForm.serviceRequest.serviceRequestNumber}</span> <spring:message code="requestInfo.message.confirmMessage2"/></p>   
			 </c:if>        	  
		  </div>
		  <form:form id="assetAcceptFormId" commandName="assetAcceptForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
	          <div class="portletBlock">
	            <div class="columnsTwo">
	              <div class="infoBox columnInner rounded shadow">
	               
	                	<h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>				
	                
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="primaryCont_name"> ${assetAcceptForm.serviceRequest.primaryContact.firstName }   ${assetAcceptForm.serviceRequest.primaryContact.lastName } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="primaryCont_phone">${assetAcceptForm.serviceRequest.primaryContact.workPhone } </span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="primaryCont_email">${assetAcceptForm.serviceRequest.primaryContact.emailAddress }</span>  
	                  </li>
	                </ul>
	               
	                
	                
	              </div>
	              <c:if test="${assetAcceptForm.serviceRequest.secondaryContact.firstName != ''}">
				  <div class="infoBox columnInner rounded shadow" id="addiContact">
	                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>
	               			
	                <ul class="form wordBreak">
	                  <li>
	                    <label><spring:message code="requestInfo.label.name"/></label>
	                    <span id="secondaryContact_name">${assetAcceptForm.serviceRequest.secondaryContact.firstName }  ${assetAcceptForm.serviceRequest.secondaryContact.lastName }</span>
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                    <span id="secondaryContact_phone">${assetAcceptForm.serviceRequest.secondaryContact.workPhone }</span>  
	                  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                    <span id="secondaryContact_email">${assetAcceptForm.serviceRequest.secondaryContact.emailAddress }</span>
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
	                    <span id="custReferenceId">${assetAcceptForm.serviceRequest.customerReferenceId }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.costCentre"/></label>
	                    <span id="costCenter">${assetAcceptForm.serviceRequest.costCenter }</span>
					  </li>
	                  <li>
	                    <label><spring:message code="requestInfo.label.description"/></label>
	                    <span id="specialInstruction">${assetAcceptForm.serviceRequest.addtnlDescription }</span>
					  </li>
					  </ul>
				  </div>
	            </div>
				<div style="clear:both"></div>
	            <hr class="separator" />
			<div class="portletBlock infoBox">			
					
					<div class="columnsTwo">
					  <div class="columnInner">
					<ul class="form wordBreak">
					<li>
                  <label for="rqstSubType"><spring:message code="requestInfo.label.subArea"/></label>
                  <span id="subArea">${assetAcceptForm.cmAreaValue }</span> </li>
                <li>
                  <label for="notes"><spring:message code="requestInfo.label.notes"/></label>
                  <span id="notes">${assetAcceptForm.notes}</span> </li>
					  </ul>
	                </div>
				</div>
			  </div>
	          
	          </form:form>
           <c:if test="${fileMap != null}">
           <p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/> </p>
       
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
        
        	
		</div>
		 
	</div>
	<!-- MAIN CONTENT END -->
          <div class="filterLinks floatR" id="btmEmailPrintDiv">
          <ul>
            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" title="email" height="23" width="27"/><spring:message code="button.clickToEmail"/></a></li>
            <li><a href="javascript: print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" title="print" height="27" width="27"/><spring:message code="button.clickToPrint"/></a></li>
          </ul>
        </div>
          
          <script type="text/javascript">
          function print(){
        	  callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCECONFIRM%>','<%=LexmarkSPOmnitureConstants.CONFIRMASSETACCEPTANCEPRINT%>');
        		 
        	  	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printAssetAcceptance' /></portlet:renderURL>";         
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
        	  		
        	  		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCECONFIRM%>','<%=LexmarkSPOmnitureConstants.CONFIRMASSETACCEPTANCEEMAIL%>');
        	  			var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailAssetAcceptance' /></portlet:renderURL>" ;

        	  	  		
        	  		
        	  		    var iWidth=680;
        	  		    var iHeight=480;
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
        	  		jQuery(document).ready(function() {
        	  		var c=1;
        	  	  <c:if test="${fileMap != null}">
        	  		jQuery('#fileListTable').empty();
        	  		responseText = '<thead><tr>	<th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
        	  	  
        	  		responseText = responseText + '<tbody>';
        	  		<c:forEach items="${fileMap}" var="entry">
        	  		if(c%2==0){
        	  			responseText = responseText + '<tr class="altRow">';
        	  		}
        	  		else{
        	  			responseText = responseText + '<tr>';
        	  		}
        	  		c++;
        	  			responseText = responseText + '<td>'+ '<c:out value="${entry.value.displayFileName}" />' + '</td>';
        	  			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
        	  			
        	  			responseText = responseText + '</tr>';
        	  			
        	  		</c:forEach>
        	  		responseText = responseText + '</tbody>';
        	  		jQuery('#fileListTable').append(responseText);
        	  		

        	  	</c:if>

        	  	<c:if test="${fileMap == null}">
        	  	jQuery('#fileListTable').empty();
        	  	</c:if>


        	  	textScroll(document.getElementById('addtnlInfo_description'));
        	  	textScroll(document.getElementById('addtnlInfo_costCenter'));
        	  	textScroll(document.getElementById('notes'));
        	  		});
          
          </script>