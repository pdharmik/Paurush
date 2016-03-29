<%-- SUMMARY PAge for ADD DELETE EDIT --%>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->


<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1>
      	<spring:message code="requestInfo.cm.heading.changeRequest"/>
      </h1>
      <h2 class="step">
      	<spring:message code="requestInfo.cm.manageAddress.heading.addresses"/>
      </h2>
    </div>
    
    <!-- Added for CI7 BRD14-02-12 -->
    <div id="accNameAgreeName"><h3><strong>
    <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    </strong></h3></div>
    <!-- END -->
    
    <div class="main-wrap">
      <div class="content">
        
       
          <!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle">
          	<spring:message code="requestInfo.heading.requestSummary"/>
          </h3>
		  <div class="utilities printPage floatR">
		  	<ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png"  class="ui_icon_sprite email-icon" alt="Email this page" ></a></li>
              <li><a href="javascript:print();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon" alt="Print this page" ></a></li>
            </ul>
          </div>
          <div id="printAddress">         
          <c:if test="${error ne null && error != ''}">
		  <p class="info banner err"><span>${error}</span></p>
		  </c:if>
		  <c:if test="${srNo != null && srNo != ''}">
		  <p class="info banner ok">
		  <%-- <spring:message code="requestInfo.message.requestSummary"/> --%> 
		  <spring:message code="requestInfo.message.confirmMessage1"/>
		  <span id="reqNumber">${srNo}</span>	
		  <spring:message code="requestInfo.message.confirmMessage2"/>.</p>
		  </c:if>
          
		  <%-- <p class="info banner ok">
		  <spring:message code="requestInfo.message.requestSummary"/>  
		  <spring:message code="requestInfo.message.confirmMessage1"/> <span id="reqNumber"> ${srNo} </span>
		  <spring:message code="requestInfo.message.confirmMessage2"/>
		  </p> --%>
		   
		  <div class="portletBlock">
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4>
                	<spring:message code="requestInfo.heading.primaryContactForThisRequest"/>
                </h4>
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="firstLastName1">
                    	${addressForm.serviceRequest.primaryContact.firstName}&nbsp;&nbsp;${addressForm.serviceRequest.primaryContact.lastName}
                    </span> 
                    </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="phoneNo1">
                    	${addressForm.serviceRequest.primaryContact.workPhone}
                    </span> 
                  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="emailAddress1">
                   		${addressForm.serviceRequest.primaryContact.emailAddress}
                   	</span>
                  </li>
                </ul>
              </div>
              <c:if test='${addressForm.serviceRequest.secondaryContact.firstName ne "" || addressForm.serviceRequest.secondaryContact.lastName ne "" }'>
			  <div class="infoBox columnInner rounded shadow">
                <h4>
                	<spring:message code="requestInfo.heading.secondaryContactForThisRequest"/>
                </h4>	
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.name"/></label>
                    <span id="firstLastName2">
                    	${addressForm.serviceRequest.secondaryContact.firstName }&nbsp; ${addressForm.serviceRequest.secondaryContact.lastName}
                    </span> 
                    </li>
                  <li>
                    <label><spring:message code="requestInfo.label.phoneNumber"/></label>
                    <span id="phoneNo2">
                    	${addressForm.serviceRequest.secondaryContact.workPhone}
                    </span> 
                    </li>
                    	
                  <li>
                    <label><spring:message code="requestInfo.label.emailAddress"/></label>
                    <span id="emailAddress2">
                    	${addressForm.serviceRequest.secondaryContact.emailAddress}
                    </span> 
                  </li>
                </ul>
              </div>
              </c:if>
            </div>
            <div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">
                <h4>
                	<spring:message code="requestInfo.heading.additionalInformationForThisRequest"/>
               	</h4>	
				
                <ul class="form wordBreak">
                  <li>
                    <label><spring:message code="requestInfo.label.customerRefereceId"/></label>
                    <span id="customerRefId">
                    	${addressForm.serviceRequest.customerReferenceId}
                    </span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.costCentre"/></label>
                    <span id="costCenter">${addressForm.serviceRequest.costCenter}
                    </span>
				  </li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span id="description">${addressForm.serviceRequest.addtnlDescription}
                    </span>
				  </li>
				  <li>
                    <label for="eddate"><spring:message code="requestInfo.label.dateOfChange"/></label>
                   <span id="reqEffectiveDate"><%-- ${addressForm.serviceRequest.requestedEffectiveDate} --%>
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
             <h4  id="headingFlowType">
              <c:set var="addAddressValue" value="<%=request.getParameter(\"addAddress\")%>"></c:set>
              <c:set var="changeAddressValue" value="<%=request.getParameter(\"isChangeAddress\")%>"></c:set>
                <c:choose>
						<c:when test='${addAddressValue eq "true"}'>
							<spring:message code="requestInfo.cm.manageAddress.heading.newAddresses"/>
						</c:when>
						<c:when test='${changeAddressValue eq "true"}'>
							<spring:message code="requestInfo.cm.manageAddress.heading.changedSelected"/>
						</c:when>
						<c:otherwise>
							<spring:message code="requestInfo.cm.manageAddress.heading.removeSelected"/>
						</c:otherwise>
				</c:choose>
             	</h4>
			      <ul class="form">
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressName"/></label>
                        <span id="addressName">${addressForm.genericAddress.addressName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.storeFrontName"/></label>
                       <span id="storeFrontName">${addressForm.genericAddress.storeFrontName}</span>
                      </li>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine1"/> </label>
                       <span id="addressLine1"> ${addressForm.genericAddress.addressLine1}</span>
                      </li>
                      
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine2"/></label>
                       <span id="addressLine2"> ${addressForm.genericAddress.addressLine2}</span>
                      </li>
					<%--   <li>
                        <label><spring:message code="requestInfo.addressInfo.label.addressLine3"/></label>
                       <span id="addressLine3"> ${addressForm.genericAddress.addressLine3}</span>
                      </li>--%>
					  <li>
                        <label><spring:message code="requestInfo.addressInfo.label.city"/></label>
                       <span id="city"> ${addressForm.genericAddress.city}</span>
                      </li>  
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.country"/></label>
                       <span id="country">${addressForm.genericAddress.country}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.stateProvince"/></label>
                       <span id="stateProvince"> ${addressForm.genericAddress.state}&nbsp;${addressForm.genericAddress.province}
                       </span>
                      </li>
                      
                       <c:if test="${addressForm.genericAddress.officeNumber != ''}">
                      <li>
                        <label for="officeNumber"><spring:message code="requestInfo.addressInfo.label.officeNumber"/></label>
                        <span id="officeNumber">${addressForm.genericAddress.officeNumber}</span>
                      </li>
                       </c:if> 
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.county"/>:</label>
                        <span id="county">${addressForm.genericAddress.county}</span>
                      </li>
                      <li>
                        <label><spring:message code="requestInfo.changemgmt.address.district"/>:</label>
                        <span id="district">${addressForm.genericAddress.district}</span>
                      </li>
                      
                      
                      
                      <li>
                        <label><spring:message code="requestInfo.addressInfo.label.postalCode"/></label>
                       <span id="postalCode"> ${addressForm.genericAddress.postalCode}
                       </span>
                      </li>
					</ul>
			</div>
		  </div>
		  </div>
		  
		  <div id="attachment" class="columnInner" style="display:none" class="wHalf displayGrid columnInnerTable rounded shadow">
			  <table>
	            <thead>
	              <tr>
	                <th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
	                <th class="w80 right"><spring:message code="requestInfo.heading.attachmentFileSizeBytes"/></th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	                <td>DocumentName1</td>
	                <td class="right">678</td>
	              </tr>
	            </tbody>
	          </table>
		</div>
		
<!-- 		 Added for CI 14-02-03 START-->
		 <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine noteWrap notesOverflow" ><span id="attachmentDescription">${addressForm.attachmentDescription}</span></p>
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
										<td>${attachmentListDetail.displayAttachmentName}</td>
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
		<div class="filterLinks floatR">
          <ul>
            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" height="23" width="27"/><spring:message code="requestInfo.link.emailThisPage"/></a></li>
            <li><a href="javascript:print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" height="27" width="27"/><spring:message code="requestInfo.link.printThisPage"/></a></li>
          </ul>
        </div>
         <!-- MAIN CONTENT END --> 
       
			</div>	
		</div>
	</div>  

<script type="text/javascript">

jQuery(document).ready(function(){
	textScroll(document.getElementById('description'));
	textScroll(document.getElementById('costCenter'));

	if("${attachmentException}"=="attachfailed"){
		jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
		 jQuery("#attachmentFailed").show();					
		}
	
});

function print() {
	
	
	
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='printAddress' />" + 
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
	
		

		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='emailAddress' />" +
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
 