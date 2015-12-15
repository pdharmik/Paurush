<%-- common subtab jsp to select history or create new request --%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<style type="text/css"><%@ include file="/WEB-INF/css/jquery/jquery-ui.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<script type="text/javascript">
jQuery(document).ready( function() {
	var summaryType = '${manageContactForm.pageName}';
	//alert('summaryType='+summaryType);
	if (summaryType=='addContact') {
		jQuery('#newContact').show();
	}
	else if (summaryType=='changeContact') {
		
		jQuery('#changeContact').show();

		if ('${manageContactForm.accountContact.workPhone}'=='' || '${manageContactForm.accountContact.workPhone}'==null)
			jQuery('#workPhone').html("${oldContactChange['workPhone']}");
		else 
			jQuery('#workPhone').html('${manageContactForm.accountContact.workPhone}');

		if ('${manageContactForm.accountContact.alternatePhone}'=='' || '${manageContactForm.accountContact.alternatePhone}'==null)
			jQuery('#alternatePhone').html("${oldContactChange['alternatePhone']}");
		else 
			jQuery('#alternatePhone').html('${manageContactForm.accountContact.alternatePhone}');

		if ('${manageContactForm.accountContact.emailAddress}'=='' || '${manageContactForm.accountContact.emailAddress}'==null)
			jQuery('#emailAddress').html("${oldContactChange['emailId']}");
		else 
			jQuery('#emailAddress').html('${manageContactForm.accountContact.emailAddress}');

		if('${manageContactForm.accountContact.address.addressLine1}'=="" || '${manageContactForm.accountContact.address.addressLine1}'==null) {
			jQuery('#changeContactAddr').hide();
		}
		else {
			jQuery('#changeContactAddr').show();
		}
		
	}
	else if (summaryType=='removeContact') {
		jQuery('#removeContact').show();
	}

	if("${attachmentException}"=="attachfailed"){
		jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
		 jQuery("#attachmentFailed").show();					
		}
	
});
</script>
<div id="content-wrapper-inner">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1> 
      <h2 class="step"><spring:message code="requestInfo.cm.manageContact.heading.contacts"/></h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div id="accNameAgreeName"><h3><strong>
    <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    </strong></h3></div>
    <!-- END -->
    
    <div class="main-wrap">
      <div class="content">
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.message.requestSummary"/></h3>
          
          <form:form commandName="manageContactForm" method="post">  
          
          <div class="utilities printPage floatR">
            <ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="requestInfo.link.emailThisPage"/>" title="<spring:message code="requestInfo.link.emailThisPage"/>" ></a></li>
              <li><a href="javascript:print();"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="requestInfo.link.printThisPage"/>" title="<spring:message code="requestInfo.link.printThisPage"/>" height="27" width="27"></a></li>
            </ul>
          </div>
		   <div id="printContact">
		   
		  <c:if test="${error ne null && error != ''}">
		  <p class="info banner err"><span>${error}</span></p>
		  </c:if>
		  <c:if test="${srNumber != null && srNumber != ''}">
		  <p class="info banner ok" id='srInfoContact'>
		  <spring:message code="requestInfo.message.confirmMessage1"/>
		  <span id="reqNumber">${srNumber}</span>	
		  <spring:message code="requestInfo.message.confirmMessage2"/>.</p>
		  </c:if>
		   
		  <%-- <p class="info banner ok" id='srInfoContact'>
		  <spring:message code="requestInfo.message.confirmMessage1"/> 
		  <span id="reqNumber">${srNumber} </span>
		  <spring:message code="requestInfo.message.confirmMessage2"/> 
		 </p> --%>                  

		  <!-- Include the common contact info jsp -->
		  <%@ include file="/WEB-INF/jsp/changemanagement/manageContact/commonContactInfoReview.jsp"%>                   
          
          <!-- Show the contact details -->
          <hr class="separator" />
		  <div class="portletBlock infoBox rounded shadow">
		  	<div class="columnsOne">
		  		<div class="columnInner">		  
				<h4 id="newContact" style="display: none"><spring:message code="requestInfo.heading.newContact"/></h4>
				<h4 id="changeContact" style="display: none"><spring:message code="requestInfo.cm.manageContact.heading.changedContact"/></h4>
				<h4 id="removeContact" style="display: none"><spring:message code="requestInfo.cm.manageContact.heading.selectedContact"/></h4>
                    <ul class="form">
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.firstName"/> </label>
                        <span id="firstName">${manageContactForm.accountContact.firstName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.middleName"/> </label>
                        <span id="middleName">${manageContactForm.accountContact.middleName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.contactInfo.label.lastName"/> </label>
                        <span id="lastName">${manageContactForm.accountContact.lastName}</span>
                      </li>
                      
                      <c:choose>
		  				<c:when test="${manageContactForm.pageName eq 'changeContact'}">
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
	                        <span id="workPhone"></span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone"/> </label>
	                         <span id="alternatePhone"></span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
	                        <span id="emailAddress"></span>
	                      </li>
	                    </c:when>                      
                      	<c:otherwise >
                      	<%-- 
	                      <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.workPhone"/> </label>
	                        <span id="workPhone">${manageContactForm.accountContact.workPhone}</span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.alternatePhone"/> </label>
	                         <span id="alternatePhone">${manageContactForm.accountContact.alternatePhone}</span>
	                      </li>
						  <li>
	                        <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/> </label>
	                        <span id="emailAddress">${manageContactForm.accountContact.emailAddress}</span>
	                      </li>
	                      --%>
                      	</c:otherwise >
                      </c:choose>
                      
					</ul>		
		  		</div>
		  	</div>
		  </div>
		  
		  <c:choose>
		  <c:when test="${manageContactForm.pageName eq 'changeContact'}">			  
			  <div class="portletBlock infoBox rounded shadow">
				<div class="columnsOne">
					<div class="columnInner">
						<h4><spring:message code="requestInfo.heading.contactAddress"/></h4>
						<ul class="roDisplay">
							  <li id="changeContactAddr">
							   <util:addressOutput value="${manageContactForm.accountContact.address}"></util:addressOutput>
		                        <%-- <div>${manageContactForm.accountContact.address.addressLine1}</div>
		                        <div>${manageContactForm.accountContact.address.addressLine2}</div>
		                        
		                        <span>${manageContactForm.accountContact.address.city}</span>, 
		                        <span>${manageContactForm.accountContact.address.state}</span> 
		                        <span>${manageContactForm.accountContact.address.province}</span> 
		                        <span>${manageContactForm.accountContact.address.postalCode}</span>
		                        
		                        <div>${manageContactForm.accountContact.address.country}</div>--%>
		                      </li>
			                  <li>
		                        <label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
		                        <span>${manageContactForm.accountContact.address.physicalLocation1}</span>
		                      </li>
							  <li>
		                        <label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
		                        <span>${manageContactForm.accountContact.address.physicalLocation2}</span>
		                      </li>
							  <li>
		                        <label><spring:message code="requestInfo.addressInfo.label.office"/> </label>
		                        <span>${manageContactForm.accountContact.address.physicalLocation3}</span>
		                      </li>		  
		                </ul>                     
					</div>
				</div>			
			  </div>
		  </c:when>
		  
		  <c:when test="${manageContactForm.pageName eq 'addContact'}">			  
			  <div class="portletBlock infoBox rounded shadow">
				<div class="columnsOne">
					<div class="columnInner">
						<h4><spring:message code="requestInfo.cm.manageContact.heading.newContactAddress"/></h4>
						<ul class="form">
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/> </label>
                        <span>${manageContactForm.accountContact.address.addressLine1}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/> </label>
                        <span>${manageContactForm.accountContact.address.addressLine2}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/> </label>
                        <span>${manageContactForm.accountContact.address.city}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/> </label>
                        <span>${manageContactForm.accountContact.address.country}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/> </label>
                        <span>${manageContactForm.accountContact.address.state}</span>
                      </li>
                      
                       <c:if test="${manageContactForm.accountContact.address.officeNumber != ''}">
                      <li>
                        <label for="officeNumber"><spring:message code="requestInfo.addressInfo.label.officeNumber"/></label>
                        <span>${manageContactForm.accountContact.address.officeNumber}</span>
                      </li>
                       </c:if> 
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.county"/></label>
                        <span>${manageContactForm.accountContact.address.county}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.district"/></label>
                        <span>${manageContactForm.accountContact.address.district}</span>
                      </li>
					  
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.postalCode"/> </label>
                        <span>${manageContactForm.accountContact.address.postalCode}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.building"/> </label>
                        <span>${manageContactForm.accountContact.address.physicalLocation1}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.floor"/> </label>
                        <span>${manageContactForm.accountContact.address.physicalLocation2}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.office"/> </label>
                        <span>${manageContactForm.accountContact.address.physicalLocation3}</span>
                      </li>
					</ul>                     
					</div>
				</div>			
			  </div>
		  </c:when>
		  </c:choose>
		  
		  <!-- 		 Added for CI 14-02-03 START-->
		 <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine" ><span id="attachmentDescription">${manageContactForm.attachmentDescription}</span></p>
            			</div>
            		</div>
            	</div>
	<div id="div_attachmentListPrint">
	<c:if test="${not empty attachmentForm.attachmentList}">
				<div class="portletBlock infoBox">
					<div class="wHalf displayGrid columnInnerTable rounded shadow" id="attachmentDetailsTable">
						<table id="attachmentDiv">
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${attachmentForm.attachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
<%-- 										<td>${attachmentForm.attachmentList}</td> --%>
										<td class="w70p">${attachmentListDetail.displayAttachmentName}</td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
				</div>
				</c:if>
				<div id="attachmentFailed" class="error" style="display: none; width: 50%">
				</div>
				</div>
		</div>	
<!-- 		 Added for CI 14-02-03 START-->
		  <!-- This block is for all the buttons at end of page -->         
          <div class="filterLinks floatR">
	          <ul>
	            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" height="23" width="27"/><spring:message code="requestInfo.link.emailThisPage" /></a></li>
	            <li><a href="javascript:print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" height="27" width="27"/><spring:message code="requestInfo.link.printThisPage"/></a></li>
	          </ul>
          </div>	  
					  		           
          </form:form>
		  
        <!-- MAIN CONTENT END --> 
      </div>
    </div>
</div>

  <div id="dialog"></div>
  
  <script type="text/javascript">
function print() {
	
		
		callOmnitureAction('Contact', 'Contact Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='printContact' />" +
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
	
		
		callOmnitureAction('Contact', 'Contact Summary');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" +
			"<portlet:param name='action' value='emailContact' />" +
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

</script>
