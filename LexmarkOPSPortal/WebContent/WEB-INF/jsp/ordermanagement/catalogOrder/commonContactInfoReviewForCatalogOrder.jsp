<script type="text/javascript">

jQuery(document).ready( function() {

if ("${catalogDetailPageForm.serviceRequest.secondaryContact.firstName }"=="" || "${catalogDetailPageForm.serviceRequest.secondaryContact.firstName }"==null) {
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
                  <span id="primaryCont_name">${catalogDetailPageForm.serviceRequest.primaryContact.firstName}&nbsp;${catalogDetailPageForm.serviceRequest.primaryContact.lastName}</span> </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                   <span id="primaryCont_phone"> ${catalogDetailPageForm.serviceRequest.primaryContact.workPhone}</span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                   <span id="primaryCont_email"> ${catalogDetailPageForm.serviceRequest.primaryContact.emailAddress}</span> </li>
                </ul>
                
		
              </div>
			  <div class="columnInner rounded infoBox" id="addiContact">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                  <span id="secondaryCont_name">${catalogDetailPageForm.serviceRequest.secondaryContact.firstName }&nbsp;${catalogDetailPageForm.serviceRequest.secondaryContact.lastName }</span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.phoneNumber"/></label>
	               <span id="secondaryCont_phone"> ${catalogDetailPageForm.serviceRequest.secondaryContact.workPhone }  </span>
	              </li>
	              <li>
	                <label><spring:message code="requestInfo.label.emailAddress"/></label>
	               <span id="secondaryCont_email"> ${catalogDetailPageForm.serviceRequest.secondaryContact.emailAddress }</span></li>
                </ul>

              </div>
            </div>
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox addiInfo">
                <h4><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></h4>		
                <ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                   <span id="addtnlInfo_customerRefId"> ${catalogDetailPageForm.serviceRequest.customerReferenceId}</span>              
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                   <span id="addtnlInfo_costCenter"> ${catalogDetailPageForm.serviceRequest.costCenter}  </span>                
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                  <span id="addtnlInfo_description" class="multiLine">  ${catalogDetailPageForm.serviceRequest.addtnlDescription}</span>
				  </li>
				</ul>
			  </div>
            </div>
</div>
