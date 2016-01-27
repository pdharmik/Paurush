<script type="text/javascript">

jQuery(document).ready( function() {

	if ("${assetDetailPageForm.serviceRequest.secondaryContact.firstName }"=="" || "${assetDetailPageForm.serviceRequest.secondaryContact.firstName }"==null) {
		document.getElementById('addiContact').style.display = "none";
	}

	
	textScroll(document.getElementById('costCenter'));
	
});

</script>

<div class="portletBlock">
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox">
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>	
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span>${assetDetailPageForm.serviceRequest.primaryContact.firstName} &nbsp; 
                    ${assetDetailPageForm.serviceRequest.primaryContact.lastName}</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span>${assetDetailPageForm.serviceRequest.primaryContact.workPhone}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                   <span> ${assetDetailPageForm.serviceRequest.primaryContact.emailAddress}</span></li>
                </ul>
                
		
              </div>
			  <div class="columnInner rounded infoBox" id="addiContact">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                   <span> ${assetDetailPageForm.serviceRequest.secondaryContact.firstName } &nbsp;
					${assetDetailPageForm.serviceRequest.secondaryContact.lastName }</span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	                <span>${assetDetailPageForm.serviceRequest.secondaryContact.workPhone }</span> 
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	                <span>${assetDetailPageForm.serviceRequest.secondaryContact.emailAddress }</span></li>
                </ul>

              </div>
            </div>
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox addiInfo">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>		
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span>${assetDetailPageForm.serviceRequest.customerReferenceId}</span>                  
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${assetDetailPageForm.serviceRequest.costCenter}</span>                  
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span id="description" class="multiLine wFull">${assetDetailPageForm.serviceRequest.addtnlDescription}</span>
				  </li>
				</ul>
			  </div>
            </div>
</div>
