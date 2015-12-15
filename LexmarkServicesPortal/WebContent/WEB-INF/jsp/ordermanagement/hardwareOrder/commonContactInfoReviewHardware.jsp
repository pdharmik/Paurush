<script type="text/javascript">

jQuery(document).ready( function() {

if ("${hardwareDetailPageForm.serviceRequest.secondaryContact.firstName }"=="" || "${hardwareDetailPageForm.serviceRequest.secondaryContact.firstName }"==null) {
	document.getElementById('addiContact').style.display = "none";
}
});

</script>

<div class="portletBlock">
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox">
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>		
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="primaryCont_name">${hardwareDetailPageForm.serviceRequest.primaryContact.firstName}&nbsp;${hardwareDetailPageForm.serviceRequest.primaryContact.lastName}</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                   <span id="primaryCont_phone">${hardwareDetailPageForm.serviceRequest.primaryContact.workPhone}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                   <span id="primaryCont_email">${hardwareDetailPageForm.serviceRequest.primaryContact.emailAddress}</span> </li>
                </ul>
                
		
              </div>
			  <div class="columnInner rounded infoBox" id="addiContact">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryCont_name">${hardwareDetailPageForm.serviceRequest.secondaryContact.firstName}&nbsp;${hardwareDetailPageForm.serviceRequest.secondaryContact.lastName}</span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	               <span id="secondaryCont_phone"> ${hardwareDetailPageForm.serviceRequest.secondaryContact.workPhone }  </span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	               <span id="secondaryCont_email"> ${hardwareDetailPageForm.serviceRequest.secondaryContact.emailAddress }</span></li>
                </ul>

              </div>
            </div>
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox addiInfo">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>		
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                   <span id="addtnlInfo_customerRefId"> ${hardwareDetailPageForm.serviceRequest.customerReferenceId}</span>              
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                   <span id="addtnlInfo_costCenter"> ${hardwareDetailPageForm.serviceRequest.costCenter}  </span>                
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="addtnlInfo_description" class="multiLine">  ${hardwareDetailPageForm.serviceRequest.addtnlDescription}</span>
				  </li>
				</ul>
			  </div>
            </div>
</div>
