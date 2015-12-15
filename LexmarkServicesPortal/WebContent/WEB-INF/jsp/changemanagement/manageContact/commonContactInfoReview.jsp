<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<script type="text/javascript">

jQuery(document).ready( function() {
//Hide the additional contact, if not selected in previous page
	if ("${manageContactForm.serviceRequest.secondaryContact.firstName }"=="" || "${manageContactForm.serviceRequest.secondaryContact.firstName }"==null) 
	{
		document.getElementById('addiContact').style.display = "none";
	}	
	
	//var desc = document.getElementById('description');
	textScroll(document.getElementById('description'));
	textScroll(document.getElementById('costCenter'));
	  
});
</script>



<div class="portletBlock">
            <div class="columnsTwo">
              <!-- Show primary contact -->
              <div class="columnInner rounded infoBox">
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>		
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="firstLastName1">${manageContactForm.serviceRequest.primaryContact.firstName}
                    ${manageContactForm.serviceRequest.primaryContact.lastName}</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="phoneNumber1">${manageContactForm.serviceRequest.primaryContact.workPhone}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="emailAddress1">${manageContactForm.serviceRequest.primaryContact.emailAddress}</span> </li>
                </ul>			
              </div>
              <!-- Show secondary contact -->
			  <div class="infoBox columnInner rounded shadow" id="addiContact">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="firstLastName2">${manageContactForm.serviceRequest.secondaryContact.firstName }
					${manageContactForm.serviceRequest.secondaryContact.lastName }</span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                <span id="phoneNumber2">${manageContactForm.serviceRequest.secondaryContact.workPhone } </span> 
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                <span id="emailAddress2">${manageContactForm.serviceRequest.secondaryContact.emailAddress }</span>
	              </li>
                </ul>
              </div>
            </div>
            
            <!-- Show additional information -->
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>		
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span id="customerReferenceid">${manageContactForm.serviceRequest.customerReferenceId} </span>                  
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${manageContactForm.serviceRequest.costCenter} </span>                 
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span id="description">${manageContactForm.serviceRequest.addtnlDescription} </span>
				  </li>
				  <li>
                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                    <span id="reqEffectiveDate">
                    <%--<span >${manageContactForm.serviceRequest.requestedEffectiveDate} </span>--%>
                    <%-- <fmt:setLocale value="${language}_${country}"/>				
					<fmt:formatDate value="${manageContactForm.serviceRequest.requestedEffectiveDate}"
					
					<util:dateFormat value="${manageContactForm.serviceRequest.requestedEffectiveDate}" timezoneOffset="${timezoneOffset}">
					</util:dateFormat> /> --%>
					<util:dateFormat value="${manageContactForm.serviceRequest.requestedEffectiveDate}">
					</util:dateFormat></span>
				  </li>
                </ul>
			  </div>
            </div>
</div>
