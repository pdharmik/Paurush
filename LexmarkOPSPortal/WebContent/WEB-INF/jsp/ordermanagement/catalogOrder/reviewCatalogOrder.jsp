<%--MPS 2.1 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%--ends --%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%-- Added for CI 7 BRD 12 --%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%-- Ends changes CI 7 BRD 12 --%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<style>
  .positionUp2{position:relative;top:-70px;}/*Added on June19,2013*/
</style>	
<![endif]-->
<portlet:renderURL var="confirmCatalogOrderURL">
	<portlet:param name="action" value="confirmCatalogOrder"/>
</portlet:renderURL>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include>
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
	<portlet:param name="addressCleansedFlag" value="true"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="creditCardPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showCreditCardPage"></portlet:param>
</portlet:renderURL>
<script type="text/javascript">
/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('back disabled so not going to prev page');
	window.history.forward(); 
}
</script>


<div id="showCredit"></div>
<div id="overlayPopup" style="display:none"></div>
<div id="creditcardPageData" style="display: none"></div>

<div id="dialogAddress"></div>
<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">
<div class="main-wrap">
	<div class="content">
		<form:form id="catalogDetailPageForm" name="catalogDetailPageForm" method="post" modelAttribute="catalogDetailPageForm" commandName="catalogDetailPageForm" action = "${confirmCatalogOrderURL}">
		<div id="content-wrapper">
			<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div><!-- End journal-content-article -->	
   
   	  <!--  Error Div Starts Here -->
      <div id="validationErrors" class="error" style="display: none;"></div>
      <%--Changes for BRD12 CI 7 --%>
        <div>
        <h3><strong>
        <jsp:include page="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"></jsp:include>
        <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div> 
        <%--Ends Changes for BRD12 CI 7 --%>
      <!-- PROCESS STEPS START -->
      <div class="portletBlock">
				<div class="portletBlock center">
					<ul class="processSteps shadow">
						<li class="active"><a href="javascript:deleteCatalogDetails();" title="<spring:message code="requestInfo.tooltip.step1SelectItems"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.message.selectItems"/></a></li>
						<li class="active"><a href="javascript:goBack()" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
						<li class="selected"><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
						<li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
					</ul>
				</div><!-- End portletBlock center -->
	</div>
		<!-- PROCESS STEPS END -->		
	<div class="portletBodyWrap">			
					        <h3 class="pageTitle"><spring:message code="requestInfo.heading.reviewOrder"/></h3>
        					<p class="info banner"><spring:message code="requestInfo.heading.reviewOrderDesc"/>
	        					<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
	        						<br/><spring:message code="requestInfo.heading.reviewOrderDescLine2"/>
	        					</c:if>
   							</p>
	   

					<%@ include file="/WEB-INF/jsp/ordermanagement/catalogOrder/commonContactInfoReviewForCatalogOrder.jsp"%>
				


				<!-- Parts Detail Start -->
	<hr class="separator" />
        <p class="inlineTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></p>
        <div class="columnInner">
        <div class="wFull displayGrid columnInnerTable rounded shadow">
          <table>
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.heading.partNumber"/></th>
								<th><spring:message code="requestInfo.heading.description"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.partType"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.yield"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.model"/></th>								
								<th class="w100 right"><spring:message code="requestInfo.heading.orderQuantity"/></th>
								<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
									<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
										<th class="w100 right"><spring:message code="requestInfo.heading.price"/></th>
										<th class="w100 right"><spring:message code="requestInfo.heading.total"/></th>
									</c:if>
								</c:if>
							</tr>
						</thead>
						<tbody>	  

						<c:forEach items="${reviewCatalogOrderForm.catalogPartList}" var="catalogPartListDetail" varStatus="counter" begin="0">
						
							<tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>" id= "partRow${counter.index}">
								<td class="w100">${catalogPartListDetail.partNumber}</td>
								<td>${catalogPartListDetail.partDesc}</td>
								<td class="w100">${catalogPartListDetail.partType}</td>
								<td class="w100">${catalogPartListDetail.yield}</td>
								<td class="w100">${catalogPartListDetail.model}</td>								
								<td class="w100 right">${catalogPartListDetail.orderQuantity}</td>
								<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
									<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
									<c:choose>
										<c:when test="${empty catalogPartListDetail.unitPrice or catalogPartListDetail.unitPrice eq '0'}">
											<td class="w100 right" colspan="2"><span class='errorText'>Price not available</span></td>
										</c:when>
										<c:otherwise>
											<td class="w100 right">${catalogPartListDetail.unitPrice} (${catalogPartListDetail.currency})</td>
											<td class="w100 right">${catalogPartListDetail.total} (${catalogPartListDetail.currency})</td>	
										</c:otherwise>
									</c:choose>
											
									</c:if>
								</c:if>						
							</tr>
						</c:forEach>
						</tbody>
  	   					</table>
  	   					</div>
  	   					<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
	  	   					<c:if test="${sessionScope.catalogFinalFlags['finalTaxCalcFlag'] == 'true' && sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}"> 
	  	   					<c:if test="${not empty catalogDetailPageForm.subTotal}">
	  	   					<div class="lineClear"></div>
	  	   					<div class="floatR w250" style="height:90px;">
							    <table class="positionUp2">
							    <tr height="25">
							    	<td class="right w150 strong"><spring:message code="requestInfo.label.partsPriceSubTotal"/></td>
							    	<td class="right w110">${catalogDetailPageForm.subTotal}</td>
							    	<form:hidden path="subTotal"/>
							    </tr>
							    <tr height="25">
							    	<td class="right strong">
							    	<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.label.partsTaxesEstimated"/>'>&nbsp; <%-- Added on 11June --%>
							    	<spring:message code="requestInfo.label.partsTaxes"/></td>
							    	<c:choose>
								    	<c:when test="${catalogDetailPageForm.tax == 'Unavailable'}">
								    		<td class="right"><spring:message code="requestInfo.tax.unavailable"/></td>
								    	</c:when>
								    	<c:otherwise>
								    		<td class="right">${catalogDetailPageForm.tax}</td>
								    	</c:otherwise>
								    </c:choose>
							    	<form:hidden path="tax"/>
							    </tr>
							    <tr height="25">
							    	<td class="right strong"><spring:message code="requestInfo.label.partsTotalAmountPayable"/></td>
							    	<td class="right">${catalogDetailPageForm.totalAmt}</td>
							    	<form:hidden path="totalAmt"/>
							    </tr>
							    </table>
						    </div>
						    </c:if>
						    </c:if>
						   </c:if>
				</div><!-- portlet-body-table table-left -->
			<!-- Parts Detail End -->
				<!-- Shipping & Billing Information Start -->
				
						<p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
					<div class="portletBlock">
					   <div class="columnsTwo">
						<div class="infoBox columnInner rounded shadow">
							<h4>
								<spring:message code="requestInfo.heading.shipToAddress"/>
							</h4>
							<ul class="roDisplay">
								<li><div>${catalogDetailPageForm.shipToAddress.storeFrontName}</div>
								<util:addressOutput value="${catalogDetailPageForm.shipToAddress}"></util:addressOutput>
													                 <%-- <div>${catalogDetailPageForm.shipToAddress.addressLine1}</div>
													                  <div>${catalogDetailPageForm.shipToAddress.officeNumber}</div>
													                  <c:if test="${catalogDetailPageForm.shipToAddress.addressLine2 !='' && catalogDetailPageForm.shipToAddress.addressLine2!=null}">
													                  <div>${catalogDetailPageForm.shipToAddress.addressLine2}, </div>
													                  </c:if>
													                  <div>${catalogDetailPageForm.shipToAddress.addressLine3}</div>
													                  <span>${catalogDetailPageForm.shipToAddress.city},
													                  <c:if test="${catalogDetailPageForm.shipToAddress.county != '' && catalogDetailPageForm.shipToAddress.county!=null}">
													                  ${catalogDetailPageForm.shipToAddress.county},
													                  </c:if>
													                  <c:if test="${catalogDetailPageForm.shipToAddress.stateCode != '' && catalogDetailPageForm.shipToAddress.stateCode != null}">
													                  ${catalogDetailPageForm.shipToAddress.stateCode}
													                  </c:if>
													                  <c:if test="${(catalogDetailPageForm.shipToAddress.province !='' && catalogDetailPageForm.shipToAddress.province !=' ') && catalogDetailPageForm.shipToAddress.province != null}">
													                  ,${catalogDetailPageForm.shipToAddress.province}
													                  </c:if>
													                   region changed to district for MPS 2.1 
													                  <c:if test="${(catalogDetailPageForm.shipToAddress.district !='' && catalogDetailPageForm.shipToAddress.district !=' ') && catalogDetailPageForm.shipToAddress.district != null}">
													                  ,${catalogDetailPageForm.shipToAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${catalogDetailPageForm.shipToAddress.postalCode}</div> 
													                  <div>${catalogDetailPageForm.shipToAddress.country}</div>--%>
															</li>
								<li><span><spring:message code="requestInfo.addressInfo.label.building"/>&nbsp;${catalogDetailPageForm.shipToAddress.physicalLocation1}</span></li>
				                <li><span><spring:message code="requestInfo.addressInfo.label.floor"/>&nbsp;${catalogDetailPageForm.shipToAddress.physicalLocation2}</span></li>
				                <li><span><spring:message code="requestInfo.addressInfo.label.office"/>&nbsp;${catalogDetailPageForm.shipToAddress.physicalLocation3}</span></li>
							</ul>
						</div><!-- columnInner rounded infoBox -->
					</div><!-- columnsTwo -->
					<div class="columnsTwo">
						<div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
							<ul class="form">
								
								<li>
									<label for="desc2"><spring:message code="requestInfo.label.specialInstructions"/></label> 
									<span class="textScroll">${catalogDetailPageForm.specialInstruction}</span>
								</li>
								<%-- <li>
									<label><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
									<span class="multiLine">${catalogDetailPageForm.defaultSpecialInstruction}</span>
								</li> --%>
								<%--
								Changes for JAN release Request Expedite order --%>
								<c:if test="${catalogDetailPageForm.expediteOrderAllowed == 'true'}">
								<li>
									<label for="chck"><spring:message code="requestInfo.label.requestExpediteOrder"/></label> 
				                  <span>
				                  <c:choose>
				                  	<c:when test="${catalogDetailPageForm.requestExpediteOrder eq \"true\"}">
				                  	<spring:message code="requestInfo.option.yes"/>
				                  	</c:when>
				                  	<c:otherwise>
				                  	<spring:message code="requestInfo.option.no"/>
				                  	</c:otherwise>
				                  </c:choose>
				                  
				                  </span>
				                 </li>
				                 </c:if>
				                  <%--				                          
				                Changes for JAN release Request Expedite order ENDS--%>
								<li>
									<label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label> 
									${catalogDetailPageForm.requestedDeliveryDate}
								</li>
							</ul>
						</div><!-- columnInner rounded infoBox -->
					</div><!-- columnsTwo -->
				</div><!-- portletBlock -->
				<!-- Shipping & Billing Information End -->
				<!-- Payment Details Start -->
				
				 <div class="portletBlock">	   
		          <div class="columnsTwo">
		            <div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
						<form:hidden id="paymentMethod" path="paymentMethod"/>
							<c:choose>
							<c:when test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'false'}">
								<ul class="form">
									<li>
										<label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label>									
											${catalogDetailPageForm.poNumber}  
											<div style="display:none">
												<form:input  path="poNumber"/>
											</div>
									</li>
								</ul>
								</c:when>
								<c:otherwise>
									<c:if test="${sessionScope.catalogFinalFlags['finalCreditFlag'] == 'false'}">
										<ul class="form">
											<li>
												<label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label>									
													${catalogDetailPageForm.poNumber}  
													<div style="display:none">
														<form:input  path="poNumber"/>
													</div>
											</li>
										</ul>
									</c:if>
								</c:otherwise>
								</c:choose>
							 <c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
								<c:if test="${sessionScope.catalogFinalFlags['finalCreditFlag'] == 'true' && sessionScope.catalogFinalFlags['finalPOFlag'] == 'true'}"> 
								<ul class="form">
					                <li>
					                  <label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label>
					                  <span>
					                  	<form:input size="10" id="poNumber" path="poNumber" /> 
										<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.ponumber"/>" >
					                  </span></li>
					            </ul>
					            <c:if test="${not empty catalogDetailPageForm.subTotal}">				              
				                <ul class="form">
				                  <li>
					                  <label><a href="javascript:void(0);" onclick="updateCreditInfo();"><spring:message code="requestInfo.label.selectCard"/></a> <span class="req">*</span></label>
					                  <div id="creditCardNo"></div>
					                  <form:hidden id="creditCardEncryptedNo" path="creditCardEncryptedNo"/>
					                  <form:hidden id="creditCardToken" path="creditCardToken"/>
					                  <form:hidden id="creditCardExpirationDate" path="creditCardExpirationDate"/>
					                  <form:hidden id="creditCardType" path="creditCardType"/>
					                  <form:hidden id="cardHoldername" path="cardHoldername"/>
					                  <form:hidden id="creditFlag" path="creditFlag"/>
					                  <form:hidden id="transactionId" path="transactionId"/>
				                 </ul>	
				                 </c:if>		
								</c:if>
							</c:if>
						</div>
					</div><!-- columnInner rounded infoBox -->
					<div class="columnsTwo" style="display: none;" id="billToBlock">
		            <div class="infoBox columnInner rounded shadow">
		              <h4><spring:message code="requestInfo.heading.billToAddress"/></h4>
		              <ul class="roDisplay">
		              	<li>
			                  <div id="sameAsShipto" align="left">
			                    <input type="checkBox" id="sameAsShiptoCheckBox" onchange="confirmAddressCopy();"/>
								<spring:message code="requestInfo.label.sameAsShipToAddr"/><br/>
			                  </div></li>
						<li>
						
		            		<div id="pickupStoreFrontNameLblParent"></div>
		            		<div id="pickupAddressLine1LblParent"></div>
		            		<div id="pickupAddressofficeNumberLblParent"></div>
		                  	<div id="pickupAddressLine2LblParent"></div>			                  	
		                 	<div><div style="display:inline;" id="pickupAddressCityLblParent"></div>
		                 	<div style="display:inline;" id="pickupAddresscountyLblParent"></div>
		                 	<div style="display:inline;" id="pickupAddressStateLblParent"></div><div style="display:inline;" id="pickupAddressProvinceLblParent"></div>
		                 	<div style="display:inline;" id="pickupAddressRegionLBParent"></div></div>
		                 	<div><span id="pickupAddressPostCodeLblParent"></span></div>
		                 	<div><span id="pickupAddressCountryLblParent"></span></div>
		             		
		             	
						</li>
					</ul>
					<!-- Hidden fields to bind the address data with form -->
					<span style="display: none">
						<form:input id="pickupAddressId" path="billToAddress.addressId" />
						<form:input id="pickupStoreFrontName" path="billToAddress.storeFrontName" />
						<form:input id="pickupAddressLine1" path="billToAddress.addressLine1" /> 
						<form:input id="pickupAddressLine2" path="billToAddress.addressLine2" />

						<form:input id="pickupAddressCity" path="billToAddress.city" />
						<form:input id="pickupAddressState" path="billToAddress.state" />
						<form:input id="pickupAddressProvince" path="billToAddress.province" />
						<form:input id="pickupAddressPostCode" path="billToAddress.postalCode" />
						<form:input id="pickupAddressCountry" path="billToAddress.country" />
						
						<!-- Below Fields for Cleansing address Don't change the
						input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						 -->
						<form:input id="pickupAddresscounty" path="billToAddress.county" />
					    <form:input id="pickupAddressofficeNumber" path="billToAddress.officeNumber" />
					    <form:input id="pickupAddressstateCode" path="billToAddress.stateCode" />
					    <form:input id="pickupAddressstateFullName" path="billToAddress.stateFullName" />
					    <form:input id="pickupAddressdistrict" path="billToAddress.district" />
					    <form:input id="pickupAddressregion" path="billToAddress.region" />
					    <form:input id="pickupAddresslatitude" path="billToAddress.latitude" />
					    <form:input id="pickupAddresslongitude" path="billToAddress.longitude" />
					    <form:input id="pickupAddresscountryISOCode" path="billToAddress.countryISOCode" />
					    <form:input id="pickupAddresssavedErrorMessage" path="billToAddress.savedErrorMessage" />
					    <form:input id="pickupAddressisAddressCleansed" path="billToAddress.isAddressCleansed"/>
					    
					    
						<!-- Ends Cleansing address -->
						
					</span>
		            </div>
		          </div>
				</div><!-- portletBlock -->
				<!-- Payment Details End -->
				<!-- Attachment Description Start -->
				<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine noteWrap notesOverflow">${catalogDetailPageForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
            	<!-- Attachment Description End -->
				<!-- Attachment Start -->
				<c:if test="${not empty catalogDetailPageForm.attachmentList}">
				<div class="portletBlock" style="padding-left:4px">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
					
						<table>
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${catalogDetailPageForm.attachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td class="w70p"><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
				</div>
				</c:if>
				<!-- Attachment End -->
				
			</div><!-- portletBlock -->
		</div><!-- content-wrapper -->
		<div class="buttonContainer">
			<div style="display:none">				
				<form:input  path="attachmentDescription"/>
				<form:input  path="specialInstruction"/>
				<form:input  path="requestExpediteOrder"/>
				<form:input  path="requestedDeliveryDate"/>
				<form:input  path="shipToAddress.physicalLocation1"/>
				<form:input  path="shipToAddress.physicalLocation2"/>
				<form:input  path="shipToAddress.physicalLocation3"/>
				<form:input id="relatedServiceRequestedNumber" path="relatedServiceRequestedNumber" />
				<form:input id="relatedServiceRequestedRowId" path="relatedServiceRequestedRowId" />
				<form:hidden path="submitToken"/>
			</div>
			<button class="button_cancel" type="button" onclick="javascript:goBack()"><spring:message code="button.back"/></button>
			&nbsp;
			<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
			&nbsp;
			<button class="button" type="button" onclick="javascript:confirmCatalogOrder()"><spring:message code="button.submit"/></button>
		</div>
		</form:form>
		</div>
	</div><!-- content -->
</div><!-- main wrap -->
</div>
<script type="text/javascript">

<c:if test="${srCreationError != null}">
	jQuery('#validationErrors').show();	
	jQuery('#validationErrors').append('${srCreationError}');
</c:if>

<%-- Changes CI 7 BRD 12 --%>
isCatalogPage="true";
ajaxSuccessFunction=function updateRequest(){
	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') != -1)
	{	
		
		var partnerURLWithoutParams;
		var partnerIndexOfQuestionMark = currentURL.indexOf("?");
		if (partnerIndexOfQuestionMark > 0) {
			partnerURLWithoutParams = currentURL.substring(0, partnerIndexOfQuestionMark);
		} else {
			partnerURLWithoutParams = currentURL;
		}
		partnerBaseURL = partnerURLWithoutParams.substring(0, partnerURLWithoutParams.lastIndexOf('/partner-portal') + '/partner-portal'.length) + "/catalogorder";
		showOverlay();
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+partnerBaseURL;
		return;
	}
	else{
		showOverlay();
		window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+populateURL("catalogorder",[],[]);
	}			
}
<%-- Ends  Changes CI 7 BRD 12 --%>




/***********This is for the back button**************/
function goBack()
{
	//alert('inside goback');
	var url='<portlet:renderURL><portlet:param name="action" value="goToCatalogDetail"></portlet:param></portlet:renderURL>';
	//alert('url is '+url);
	jQuery("#catalogDetailPageForm").attr("action", url);
	jQuery("#catalogDetailPageForm").submit();
}


jQuery(document).ready(function(){
	var currentURL = window.location.href;
	addPickupAddress("${billTo.addressId}","${billTo.addressName}","${billTo.addressLine1}","${billTo.addressLine2}","${billTo.city}","${billTo.state}","${billTo.province}",
			"${billTo.country}","${billTo.postalCode}","${billTo.storeFrontName}","${billTo.userFavorite}","${billTo.officeNumber}","${billTo.district}","${billTo.county}","${billTo.countryISOCode}","${billTo.region}","${billTo.stateCode}");
//		Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	
});

function deleteCatalogDetails(){
	jConfirm("<spring:message code='requestInfo.message.sessionClearConfirmation'/>", "", function(confirmResult) {
		if(confirmResult){
			var relatedServiceRequestNumber = document.getElementById("relatedServiceRequestedNumber").value;
			var url = "<portlet:renderURL></portlet:renderURL>&relatedServiceRequestNumber=" + relatedServiceRequestNumber;
			//alert('url is '+url);
			window.location.href = url;
		}
	});
}
/******************end****************************/

function confirmCatalogOrder(){
	jQuery("#validationErrors").html("");
	var validation_error_flag = false;
	if(jQuery("#creditCardNo").length!=0){
		if(jQuery("#creditCardNo").val()==null || jQuery("#creditCardNo").val()==""){
			validation_error_flag = true;
			jQuery("#validationErrors").append("<li><strong><spring:message code="requestInfo.validation.credit"/></strong></li>");
		}		
	}
	if(validation_error_flag == true){
		jQuery("#validationErrors").show();
		jQuery(document).scrollTop(0);
		return false;
	}else{		
		if(jQuery("#poNumber").length!=0){
			if(jQuery("#creditCardToken").length!=0){
				if((jQuery("#poNumber").val().trim()==null || jQuery("#poNumber").val().trim()=="") && (jQuery("#creditCardToken").val().trim()!=null && jQuery("#creditCardToken").val().trim()!="")){
					jQuery("#paymentMethod").val('Credit Card');
				}else if((jQuery("#poNumber").val().trim()!=null && jQuery("#poNumber").val().trim()!="") && (jQuery("#creditCardToken").val().trim()!=null && jQuery("#creditCardToken").val().trim()!="")){
					jQuery("#paymentMethod").val('Credit Card');
				}else if((jQuery("#poNumber").val().trim()!=null && jQuery("#poNumber").val().trim()!="") && (jQuery("#creditCardToken").val().trim()==null || jQuery("#creditCardToken").val().trim()=="")){
					jQuery("#paymentMethod").val('Purchase Order');
				}
			}else{
				jQuery("#paymentMethod").val('Purchase Order');
			}
		}else{
			if(jQuery("#creditCardNo").length!=0){
				jQuery("#paymentMethod").val('Credit Card');
			}
		}
		
		if(jQuery("#creditCardNo").length!=0 && jQuery("#creditCardNo").val()!=""){
			jQuery("#creditCardEncryptedNo").val(jQuery("#creditCardNo").val());
			jQuery("#creditFlag").val("true");
		}		
		if(jQuery("#creditCardType").val()!=''){
			if(jQuery("#creditCardType").val() == '001'){
				jQuery("#creditCardType").val('Visa');
			}
			if(jQuery("#creditCardType").val() == '002'){
				jQuery("#creditCardType").val('MasterCard');
			}
			if(jQuery("#creditCardType").val() == '003'){
				jQuery("#creditCardType").val('American Express');
			}
			if(jQuery("#creditCardType").val() == '004'){
				jQuery("#creditCardType").val('Discover');
			}
		}
		jQuery("#catalogDetailPageForm").submit();
	}
	
};
function openAttachment(fileName){
		//alert("displayAttachment12:: "+ '${displayAttachment}');
		window.location="${displayAttachment}&fileName="+fileName;
		
	}
	
/*
 * Confirm Bill To address change
 */
function confirmAddressChange(hyperlinkId){
	jConfirm("<spring:message code='requestInfo.message.newCardAuthorize'/>", "", function(confirmResult) {
		if(confirmResult){
			jQuery('#creditCardNo').html('');
			jQuery('#creditCardEncryptedNo').val('');
			jQuery('#creditCardToken').val('');
			jQuery('#creditCardExpirationDate').val('');
			jQuery('#creditCardType').val('');
			popupAddress(hyperlinkId);
		}
	});
}

/*
 * Confirm copy of Ship To Address To Bill To Address
 */
function confirmAddressCopy(){
	jConfirm("<spring:message code='requestInfo.message.newCardAuthorize'/>", "", function(confirmResult) {
		
		if(confirmResult){
			jQuery('#creditCardNo').html('');
			jQuery('#creditCardEncryptedNo').val('');
			jQuery('#creditCardToken').val('');
			jQuery('#creditCardExpirationDate').val('');
			jQuery('#creditCardType').val('');
			copyShipAddressToBillTo();
		}else{
			jQuery('#sameAsShiptoCheckBox').attr('checked', false);
		}
	});
}

function popupAddress(hyperlinkId){	
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	var addressFlag = "${catalogDetailPageForm.addressFlag}";
	//alert('addressFlag '+addressFlag);
// 	var url=jQuery('#'+hyperlinkId).attr('href');
    var url = "${addressListPopUpUrl}";
	url+="&addressFlag="+addressFlag;
	jQuery('#dialogAddress').load(url,function(){
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			height: 460,
			width: 700,
			close: function(event,ui){
				dialog=null;
				jQuery('#content_addr').remove();
				jQuery('#totalContentCredit').show();
				jQuery('#grid_menu').remove();
				}
			});
		jQuery('#totalContentCredit').hide();
		dialog.dialog('open');
		initializeAddressGrid();
		
		});
	return false;
}

function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2,
		addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode)
	{
	<%--Changes for MPS 2.1 --%>
	if(addressId==null || addressId=="")
		jQuery('#pickupAddressisAddressCleansed').val("false");
	else
		jQuery('#pickupAddressisAddressCleansed').val("true");
	
	<%--ENDS Changes for MPS 2.1 --%>
			addressChangeReq=true;
			if(linkId=="diffAddressLink")
			{
				//alert("inside add service addr element ");
				//call to addConsumablesAddress in the commonCatalog.js
				addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
						addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
			}
			closeDialog();
			
	}
	
function addPickupAddress(addressId,storeFrontName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
		addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode)
{	
	if(addressProvince == null){
		addressProvince = '';
	}
	if(addressId==null){
		addressId = '-1';
	}	
	//alert('addressId is '+addressId);
	jQuery("#pickupAddressId").val(addressId);
	jQuery("#pickupStoreFrontName").val(storefrontName);
	jQuery("#pickupAddressLine1").val(addressLine1);		
	jQuery("#pickupAddressLine2").val(addressLine2);
			
	jQuery("#pickupAddressCity").val(addressCity);
	jQuery("#pickupAddressState").val(addressState);
	jQuery("#pickupAddressProvince").val(addressProvince);
	jQuery("#pickupAddressPostCode").val(addressPostCode);
	jQuery("#pickupAddressCountry").val(addressCountry);
	<%--Changes for Phase 2.1--%>
	jQuery("#pickupAddressofficeNumber").val(officeNumber);
	jQuery("#pickupAddressdistrict").val(district);
	jQuery("#pickupAddresscounty").val(county);
	jQuery("#pickupAddressregion").val(region);
	jQuery("#pickupAddresscountryISOCode").val(countryISO);
    jQuery("#pickupAddressstateCode").val(stateCode);
	<%--Ends--%>
			
	//This is for display purpose pickup address
	jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
	jQuery("#pickupStoreFrontNameLblParent").html(storefrontName);
	jQuery("#pickupAddressLine1Lbl").html(addressLine1);
	jQuery("#pickupAddressLine1LblParent").html(addressLine1);
	if(addressLine2!='' && addressLine2!=' ' && addressLine2!=null){
		jQuery("#pickupAddressLine2Lbl").html(addressLine2+',');
		jQuery("#pickupAddressLine2Lbl").show();
		jQuery("#pickupAddressLine2LblParent").html(addressLine2+',');
		jQuery("#pickupAddressLine2LblParent").show();
		}
	if(addressCity!='' && addressCity!=' ' && addressCity!=null){
		jQuery("#pickupAddressCityLbl").html(addressCity);
		jQuery("#pickupAddressCityLblParent").html(addressCity);
		}
	if(county!='' && county!=' ' && county!=null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+county);
		jQuery("#pickupAddresscountyLbl").show();
		jQuery("#pickupAddresscountyLblParent").html(',&nbsp;'+county);
		jQuery("#pickupAddresscountyLblParent").show();
		}
	if(addressProvince!='' && addressProvince!=' ' && addressProvince!=null){
		jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+addressProvince);
		jQuery("#pickupAddressProvinceLbl").show();
		jQuery("#pickupAddressProvinceLblParent").html(',&nbsp;'+addressProvince);
		jQuery("#pickupAddressProvinceLblParent").show();
		}
	// region changed to district for MPS 2.1
	if(district!='' && district!=' ' && district!=null){
		jQuery("#pickupAddressRegionLB").html(',&nbsp;'+district);
		jQuery("#pickupAddressRegionLB").show();
		jQuery("#pickupAddressRegionLBParent").html(',&nbsp;'+district);
		jQuery("#pickupAddressRegionLBParent").show();
		}
	if(stateCode!='' && stateCode!=' ' && stateCode!=null){
		jQuery("#pickupAddressStateLbl").html(',&nbsp;'+stateCode);
		jQuery("#pickupAddressStateLbl").show();
		jQuery("#pickupAddressStateLblParent").html(',&nbsp;'+stateCode);
		jQuery("#pickupAddressStateLblParent").show();
		}
	if(addressProvince!='' && addressProvince!=' ' && addressProvince!=null){
	jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+addressProvince);
	jQuery("#pickupAddressProvinceLbl").show();
	jQuery("#pickupAddressProvinceLblParent").html(',&nbsp;'+addressProvince);
	jQuery("#pickupAddressProvinceLblParent").show();
	}
	jQuery("#pickupAddressPostCodeLblParent").html(addressPostCode);		
	jQuery("#pickupAddressCountryLblParent").html(addressCountry);
	
	if(county=='' || county==' ' || county==null){
		jQuery("#pickupAddresscountyLbl").hide();
		jQuery("#pickupAddresscountyLblParent").hide();
		}
	if(addressProvince=='' || addressProvince==' ' || addressProvince==null){
		jQuery("#pickupAddressProvinceLbl").hide();
		jQuery("#pickupAddressProvinceLblParent").hide();
		}
	// region changed to district for MPS 2.1
	if(district=='' || district==' ' || district==null){
		jQuery("#pickupAddressRegionLB").hide();
		jQuery("#pickupAddressRegionLBParent").hide();
		}
	if(stateCode=='' || stateCode==' ' || stateCode==null){
		jQuery("#pickupAddressStateLbl").hide();
		jQuery("#pickupAddressStateLblParent").hide();
		}
	if(addressLine2=='' || addressLine2==' ' || addressLine2==null){
		jQuery("#pickupAddressLine2Lbl").hide();
		jQuery("#pickupAddressLine2LblParent").hide();
		
		}
	jQuery("#pickupAddressofficeNumberLbl").html(officeNumber);
	jQuery("#pickupAddressofficeNumberLblParent").html(officeNumber);
}
function closeDialog()
{
        dialog.dialog('close');                        
        dialog=null;
        jQuery('#totalContentCredit').show();
}

function updateCreditInfo(){
	popupCreditDialog("creditCardNo");
}



//Added for MPS 2.1 Extra fields for address cleansing
function addRestFieldsOfCleanseAddress(){
	jQuery('#pickupAddressisAddressCleansed').val("true");
	<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
		jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
	<%}%>
	jQuery("#pickupAddressofficeNumberLbl").html(jQuery("#pickupAddressofficeNumber").val());
	//Added for MPS 2.1
	if(jQuery('#pickupAddresscounty').val() !='' && jQuery('#pickupAddresscounty').val() !=' ' && jQuery('#pickupAddresscounty').val() !=null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+jQuery('#pickupAddresscounty').val());
		jQuery("#pickupAddresscountyLbl").show();
	}
	if(jQuery('#pickupAddresscounty').val() =='' && jQuery('#pickupAddresscounty').val() ==' ' && jQuery('#pickupAddresscounty').val() ==null){
		jQuery("#pickupAddresscountyLbl").hide();
	}
	//jQuery("#pickupAddressdistrictLbl").html(jQuery('#pickupAddressdistrict').val());
	//END
	}	
//Ends

function popupCreditDialog(creditId){
	var creditCurrency = "${creditCurrency}";
	var shipToCountryName = "${catalogDetailPageForm.shipToAddress.country}";
	var shipToCountryCode = "${catalogDetailPageForm.shipToAddress.countryISOCode}";
	var state="";
	var country="";
	if(jQuery('#pickupAddressregion').val()!=null && jQuery('#pickupAddressregion').val()!=''){
		state = jQuery('#pickupAddressregion').val();
	}else if(jQuery('#pickupAddressState').val()!=null && jQuery('#pickupAddressState').val()!=''){
		state = jQuery('#pickupAddressState').val();
	}else if(jQuery('#pickupAddressProvince').val()!=null && jQuery('#pickupAddressProvince').val()!=''){
		state = jQuery('#pickupAddressProvince').val();
	}
	
	if(jQuery('#pickupAddresscountryISOCode').val()!=null && jQuery('#pickupAddresscountryISOCode').val()!=''){
		country = jQuery('#pickupAddresscountryISOCode').val();
	}
	var dispCountry=jQuery('#pickupAddressCountry').val();
	var address_name = encodeURIComponent(jQuery('#pickupStoreFrontName').val());
	var address_line1 = encodeURIComponent(jQuery('#pickupAddressLine1').val());
	var address_line2 = encodeURIComponent(jQuery('#pickupAddressLine2').val());
	var address_office = encodeURIComponent(jQuery('#pickupAddressofficeNumber').val());
	var address_city = encodeURIComponent(jQuery('#pickupAddressCity').val());
	var address_state = encodeURIComponent(state);
	dispCountry = encodeURIComponent(dispCountry);
	var address_country = encodeURIComponent(country);

	var county=encodeURIComponent(jQuery('#pickupAddresscounty').val());
	var province=encodeURIComponent(jQuery('#pickupAddressProvince').val());
	var district=encodeURIComponent(jQuery('#pickupAddressdistrict').val());
	
	var address_postal_code = encodeURIComponent(jQuery('#pickupAddressPostCode').val());
	var url = "${creditCardPopUpUrl}";
	url=url+'&address_line1='+address_line1+'&address_city='+address_city+'&address_state='+address_state+'&address_country='+address_country+'&address_postal_code='+address_postal_code+'&currency='+creditCurrency+'&address_line2='+address_line2+'&address_office='+address_office+'&address_name='+address_name+'&dispCountry='+dispCountry+'&county='+county+'&province='+province+'&district='+district+'&shipToCountryName='+shipToCountryName+'&shipToCountryCode='+shipToCountryCode;
		showOverlay();
		/*alert('before ajax call');*/
		jQuery('#creditcardPageData').load(url,function(){
			/*alert('after ajax call');*/
			dialog=jQuery('#totalContentCredit').dialog({
				autoOpen: false,
				title: 'Credit Card Payment',
				modal: true,
				height: 460,
				width: 700,
				open: function(){
					showToolTipInPopup();
					jQuery('#totalContentCredit').show();					
				},
				position: ['center','top'],
				close: function(event,ui){
						/*alert('in close dialog of createnew');*/
						
					 hideOverlay();
					 //dialog.dialog('destroy');					 
					 dialog=null;
					 jQuery('#totalContentCredit').remove();
					 //jQuery('#creditCard').val('');
					 //jQuery('#creditcardPageData').empty();
					 
					}
				});
			//jQuery(document).scrollTop(0);
			dialog.dialog('open');
			});
		
		return false;
	}
function removeValidation(){
	jQuery('#poNumber').removeClass('errorColor');
	
	jQuery('#validationErrors').html("");
	jQuery('#validationErrors').hide();
 }
</script>