<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%-- Common JSP for delete and add ADDRESS --%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<portlet:actionURL var="AddressActionURL">
<portlet:param name="action" value="submitAddress"></portlet:param>
</portlet:actionURL>
<!-- Added for CI 14-02-03 -->
<portlet:resourceURL var="displayAttachmentURL" id="displayAttachment">
	
</portlet:resourceURL>

			<p class="info banner">
				<spring:message code="requestInfo.message.reviewMessage"/>
			</p>
			
		
          <div class="portletBlock">
            <div class="columnsTwo">
              <div class="columnInner rounded infoBox">
                <h4><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></h4>
				<div class="borderBottomLightGray"></div>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span>${addressForm.serviceRequest.primaryContact.firstName}&nbsp;${addressForm.serviceRequest.primaryContact.lastName}
                    </span>
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                  	 <span>${addressForm.serviceRequest.primaryContact.workPhone}</span>
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span>${addressForm.serviceRequest.primaryContact.emailAddress}</span>
                  </li>
                </ul>
              </div>
              <c:if test='${addressForm.serviceRequest.secondaryContact.firstName ne "" || addressForm.serviceRequest.secondaryContact.lastName ne "" }'>
			  <div class="infoBox columnInner rounded shadow">
                <h4><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></h4>	
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span>
                    ${addressForm.serviceRequest.secondaryContact.firstName }&nbsp; ${addressForm.serviceRequest.secondaryContact.lastName}
                    </span> 
                 </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span>${addressForm.serviceRequest.secondaryContact.workPhone}
                    </span>
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span>${addressForm.serviceRequest.secondaryContact.emailAddress}
                    </span> 
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
                    <span>
                    	${addressForm.serviceRequest.customerReferenceId}
                    </span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                   	<span id="costCenter">
                   		${addressForm.serviceRequest.costCenter}
                   	</span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span id="description">
                    	${addressForm.serviceRequest.addtnlDescription}
                    </span>
				  </li>
				  <li>
                    <label><spring:message code="requestInfo.label.dateOfChange"/></label>
                    
                    <span>
                    	<util:dateFormat value="${addressForm.serviceRequest.requestedEffectiveDate}">
                    	</util:dateFormat>
                    </span>
				  </li>
                </ul>
			  </div>
            </div>
          </div>
           <hr class="separator" />
		  <div class="portletBlock infoBox rounded shadow">
		  	<div class="columnsOne">
		  	<div class="columnInner">
                <h4>
                <c:set var="addAddressValue" value="<%=request.getParameter(\"addAddress\")%>"></c:set>
                <c:choose>
						<c:when test='${addAddressValue eq "true"}'>
							<spring:message code="requestInfo.cm.manageAddress.heading.newAddressToAdd"/>
						</c:when>
						<c:otherwise>
							<spring:message code="requestInfo.heading.selectedAddress"/>
						</c:otherwise>
				</c:choose>
                
                
                
                </h4>	
			     <ul class="form">
                     <li>
                        <label for="storeName"><spring:message code="requestInfo.addressInfo.label.addressName"/></label>
                        <span>
                        	${addressForm.genericAddress.addressName}
                        </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.storeFrontName"/></label>
                        <span>
                        	${addressForm.genericAddress.storeFrontName}
                        </span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/> </label>
                        <span>
                        	${addressForm.genericAddress.addressLine1}
                        </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/> </label>
                        <span>
                        	${addressForm.genericAddress.addressLine2} 
                        </span>
                      </li>
					  
					  
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/></label>
                        <span>
                        	${addressForm.genericAddress.country}
                        </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/></label>
                        <span>
                         	${addressForm.genericAddress.state}&nbsp; ${addressForm.genericAddress.province}
                        </span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/></label>
                        <span>
                        	${addressForm.genericAddress.city}
                        </span>
                      </li>
                      
                      <c:if test="${addressForm.genericAddress.officeNumber != ''}">
                      <li>
                        <label for="officeNumber"><spring:message code="requestInfo.addressInfo.label.officeNumber"/></label>
                        <span>${addressForm.genericAddress.officeNumber}</span>
                      </li>
                       </c:if> 
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.county"/></label>
                        <span>${addressForm.genericAddress.county}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.district"/></label>
                        <span>${addressForm.genericAddress.district}</span>
                      </li>
                      
                      
                      
                      
                      
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.postalCode"/></label>
                        <span>
                        	${addressForm.genericAddress.postalCode}
                        </span>
                      </li>
					</ul>
			</div>
		  </div>
		 </div>
		  <form:form id="addAddressForm"  method="post" action="${AddressActionURL}" commandName="addressForm">
		  
		  <form:hidden path="submitToken" />
		  
 				<div style="display:none">
	                	<form:input id="primaryContactId" path="serviceRequest.primaryContact.contactId" /> 
						<form:input id="primaryFirstName" path="serviceRequest.primaryContact.firstName" /> 
						<form:input id="primaryLastName" path="serviceRequest.primaryContact.lastName" /> 
						<form:input id="primaryWorkPhone" path="serviceRequest.primaryContact.workPhone" /> 
						<form:input id="primaryEmailAddress" path="serviceRequest.primaryContact.emailAddress" />
						<form:input id="primaryEmailAddress" path="serviceRequest.primaryContact.alternatePhone" />
			
						<form:input id="secondaryContactId" path="serviceRequest.secondaryContact.contactId" /> 
						<form:input id="secondaryFirstName" path="serviceRequest.secondaryContact.firstName" /> 
						<form:input id="secondaryLastName" path="serviceRequest.secondaryContact.lastName" /> 
						<form:input id="secondaryWorkPhone" path="serviceRequest.secondaryContact.workPhone" /> 
						<form:input id="secondaryEmailAddr" path="serviceRequest.secondaryContact.emailAddress" />
						<form:input id="secondaryEmailAddr" path="serviceRequest.secondaryContact.alternatePhone" />
						
						<form:input  path="serviceRequest.customerReferenceId" /> 
						<form:input  path="serviceRequest.costCenter" /> 
						<form:input  path="serviceRequest.addtnlDescription" /> 
						<form:input  path="serviceRequest.requestedEffectiveDate" /> 
						
						
						
						<form:input id="addressName" path="genericAddress.addressName" />
						<form:input id="storeFrontName" path="genericAddress.storeFrontName" />
						<form:input id="address1" path="genericAddress.addressLine1" />
						<form:input id="address2" path="genericAddress.addressLine2" />
				
						<form:input id="country" path="genericAddress.country" />
						<form:input id="state" path="genericAddress.state" />
						<form:input id="state" path="genericAddress.province" />
						<form:input id="city" path="genericAddress.city" />
						<form:input id="poCode" path="genericAddress.postalCode" />
						
						<form:input  path="genericAddress.county" />
						<form:input path="genericAddress.officeNumber" />
						<form:input path="genericAddress.stateFullName" />
						<form:input path="genericAddress.district" />
						<form:input path="genericAddress.region" />
						<form:input path="genericAddress.stateCode" />
						<form:input path="genericAddress.latitude" />
						<form:input path="genericAddress.longitude" />
						<form:input path="genericAddress.savedErrorMessage" />
						<form:input path="genericAddress.countryISOCode" />
						<form:input  path="genericAddress.isAddressCleansed" />
						
						
						
						
						 <form:hidden path="area" id="areaAddAddr"/>
						<form:hidden path="subArea" id="subareaAddAddr"/>
						<form:hidden path="prevSrNo" id="prevSrNo"/>
						<form:hidden path="attachmentDescription" id="attachmentDescription"/> <%-- //Added for CI 14-02-03 --%>
				</div>
	<!-- Add Attachments BLOCK - Start -->
        <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine noteWrap">${addressForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
            	<c:if test="${not empty attachmentForm.attachmentList}">
        <div class="portletBlock infoBox">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
						<table>
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
										<td><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
				</div>
				</c:if>
        <!-- Add Attachments BLOCK - End -->
        <div class="buttonContainer">
			<button class="button_cancel" type="button" onclick="setToBack();"><spring:message code="button.back"/></button>
            <button class="button_cancel ie7_fix_margin" type="button" onclick="redirectToHistory('others');"><spring:message code="button.cancel"/></button>
            <button  class="button" type="button" onclick="submitForm();"><spring:message code="button.submit"/></button>
        </div>
			</form:form>
       
        
     <script type="text/javascript">
     jQuery(document).ready(function(){
    		var error=<%=request.getParameter("errorOccured")%>
    		if (error == true)
    			jQuery('#errorDiv').show();

    		textScroll(document.getElementById('description'));
    		textScroll(document.getElementById('costCenter'));
    	});
     function openAttachment(fileName){
    		//alert(fileName);
    		window.location="${displayAttachmentURL}&fileName="+fileName;
    		
    	}
     </script>