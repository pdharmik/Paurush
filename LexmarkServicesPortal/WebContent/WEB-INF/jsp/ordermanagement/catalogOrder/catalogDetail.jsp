<%--MPS 2.1 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%--ends --%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%-- Added for CI 7 BRD 12 --%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%-- Ends changes CI 7 BRD 12 --%>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css"/></style>
<![endif]-->
<script type="text/javascript">	
	<%@ include file="../../../../js/expand.js"%>
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL var="submitCatalogOrderURL">
	<portlet:param name="action" value="submitCatalogOrder"/>
</portlet:renderURL>
 
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
	<c:if test="${sessionScope.catalogCurrentDetails['paymentType'] == 'Ship and Bill'}">
		<portlet:param name="addressCleansedFlag" value="true"></portlet:param>
	</c:if>
</portlet:renderURL>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include> 
<jsp:include page="../../common/validationMPS.jsp" />
<div id="dialogAddress" style="display: none;"></div>
<div class="main-wrap">
      <div class="content">

  <div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
      <h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
      </div>
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
	<!-- Validation Errors displayed in the below div -->
           <spring:hasBindErrors name="catalogDetailPageForm">
				<div class="error" id="assetDetailErrors">
					<c:forEach var="assetDetailError" items="${errors.allErrors}">
						<li><strong><spring:message code="${assetDetailError.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
			<spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
			<div id="jsValidationErrors" class="error" style="display: none;"></div>
			<div id="validationErrors" class="error" style="display: none;"></div>
			<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
     		<div id="taxValidationErrors" class="error" style="display: none;">${Error}</div>
     		
        <!-- PROCESS STEPS START -->
        <div class="portletBlock">
	        <div class="portletBlock center">
	          <ul class="processSteps shadow">
	            <li class="active"><a href="javascript:deleteCatalogDetails();" title="<spring:message code="requestInfo.tooltip.step1SelectItems"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.message.selectItems"/></a></li>
	            <li class="selected"><a href="#" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
	            <li><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
	            <li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
	          </ul>
	        </div>
        </div>
        <!-- PROCESS STEPS END -->
       
        <div class="portletBodyWrap">
        <h3 class="pageTitle"><spring:message code="requestInfo.heading.orderDetails"/></h3>
        <p><spring:message code="requestInfo.label.pageHeader"/></p>
			
			<form:form id="catalogDetailPageForm" method="post" commandName="catalogDetailPageForm" modelAttribute="catalogDetailPageForm" action="${submitCatalogOrderURL}">
			<form:hidden path="pageSubmitType" id="pageSubmitType"/>
			<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%> 
			
	<!-- Parts Detail Start -->
	<hr class="separator" />
        <p class="inlineTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></p>
        <div class="columnInner">
        <div class="wFull displayGrid columnInnerTable rounded shadow">
          <table id="catalogDetailPartTab">
				<thead>
					<tr>
						<th class="w15"><spring:message code="requestInfo.heading.delete"/></th>
						<th class="w100"><spring:message code="requestInfo.heading.partNumber"/></th>
						<th><spring:message code="requestInfo.heading.description"/></th>
						<th class="w100"><spring:message code="requestInfo.heading.partType"/></th>
						<th class="w100"><spring:message code="requestInfo.heading.yield"/></th>
						<th class="w100"><spring:message code="requestInfo.heading.model"/></th>
						<th class="w100 right"><spring:message code="requestInfo.heading.orderQuantity"/></th>
						<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
							<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
								<th class="w100 right"><spring:message code="requestInfo.heading.price"/></th>
							</c:if>
						</c:if>
					</tr>
				</thead>
				<tbody>	  
				<c:forEach items="${catalogDetailPageForm.catalogPartList}" var="catalogPartListDetail" varStatus="counter" begin="0">
					<tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>" id= "partRow${counter.index}">
						<td class="w15"><img id= "${counter.index}" height="15" width="15" onclick="deletePartFromCatalogDetail('${catalogPartListDetail.catalogId}', ${counter.index});"class="delete ui_icon_sprite ui-icon-closethick" title="Delete"	src="<html:imagesPath/>transparent.png"></td>
						<input type="hidden" id="catalogId[${counter.index}]" name="catalogId[${counter.index}]" value='${catalogPartListDetail.catalogId}'/>						
						<td id="partNumber[${counter.index}]" class="w100">${catalogPartListDetail.partNumber}</td>
						<td id="partDesc[${counter.index}]">${catalogPartListDetail.partDesc}</td>
						<td id="partType[${counter.index}]" class="w100">${catalogPartListDetail.partType}</td>
						<td id="yield[${counter.index}]" class="w100">${catalogPartListDetail.yield}</td>
						<td id="model[${counter.index}]" class="w100">${catalogPartListDetail.model}</td>
						<td class="w100 right"><input type="text" id="catalogPartList[${counter.index}].orderQuantity" class="w90 right" name="catalogPartList[${counter.index}].orderQuantity" value='${catalogPartListDetail.orderQuantity}' maxlength="4" onkeyup="checkParts(this.id,'${catalogPartListDetail.mpsQuantity}','${counter.index}')" onchange='removeErrorMessage()' ><div style='display: none; color: red;' id='MsgassetPartList[${counter.index}].orderQuantity'><B><spring:message code="maxOrderQtyValidation"/>&nbsp;${catalogPartListDetail.mpsQuantity}</B></div></td>
						<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
							<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
								<c:choose>
									<c:when test="${empty catalogPartListDetail.unitPrice or catalogPartListDetail.unitPrice eq '0'}">
										<td id="price[${counter.index}]" class="w100 right"><span class='errorText'>Price not available</span></td>
									</c:when>
									<c:otherwise>
										<td id="price[${counter.index}]" class="w100 right">${catalogPartListDetail.unitPrice}(${catalogPartListDetail.currency })</td><!-- Added for Currency -->
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
        </table>
        </div>
	</div><!-- columnInner -->
	<!-- Parts Detail End -->
	<!-- Shipping & Billing Information Start -->
	<p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
	<div class="portletBlock">
		<div class="columnsTwo">
			<div class="infoBox columnInner rounded shadow">
				<h4>
					<a href="${addressListPopUpUrl}" class="lightboxLarge" title="<spring:message code="requestInfo.link.selectAnAddress"/>" id="diffAddressLink" 
						onclick="return popupAddress(id);"><spring:message code="requestInfo.link.selectAnAddress"/></a>
					<spring:message code="requestInfo.heading.shipToAddress"/>
				</h4>
				<ul class="roDisplay">
					<li> <div id="pickupStoreFrontNameLbl"></div> 
									<div id="pickupAddressLine1Lbl"></div>
									<div id="pickupAddressofficeNumberLbl"></div>
									<div id="pickupAddressLine2Lbl"></div>
									<div id="city_state_zip_popup_span">
									<div style="display:inline;" id="pickupAddressCityLbl"></div>
									<div style="display:inline;" id="pickupAddresscountyLbl"></div>
									<div style="display:inline;" id="pickupAddressStateLbl"></div>
									<div style="display:inline;" id="pickupAddressProvinceLbl"></div>
									<div style="display:inline;" id="pickupAddressRegionLB"></div>
									</div>
									<div id="pickupAddressPostCodeLbl"></div>
									<div id="pickupAddressCountryLbl"></div></li>
				</ul>										 	 
				<ul class="form division">
					<li>
						<label for="building"><spring:message code="requestInfo.addressInfo.label.building"/></label> 
						<span><form:input id="physicalLocation1" class="w100" path="shipToAddress.physicalLocation1" maxlength="100"/></span>
					</li>
					<li>
						<label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/></label> 
						<span><form:input id="physicalLocation2" class="w100" path="shipToAddress.physicalLocation2" maxlength="100"/></span>
					</li>
					<li>
						<label for="office"><spring:message code="requestInfo.addressInfo.label.office"/></label> 
						<span><form:input id="physicalLocation3" class="w100" path="shipToAddress.physicalLocation3" maxlength="100"/></span>
					</li>
				</ul>
					<!-- Hidden fields to bind the address data with form -->
					<span style="display: none">
						<form:input id="pickupAddressId" path="shipToAddress.addressId" />
						<form:input id="pickupStoreFrontName" path="shipToAddress.storeFrontName" />
						<form:input id="pickupAddressLine1" path="shipToAddress.addressLine1" /> 
						<form:input id="pickupAddressLine2" path="shipToAddress.addressLine2" />

						<form:input id="pickupAddressCity" path="shipToAddress.city" />
						<form:input id="pickupAddressState" path="shipToAddress.state" />
						<form:input id="pickupAddressProvince" path="shipToAddress.province" />
						<form:input id="pickupAddressPostCode" path="shipToAddress.postalCode" />
						<form:input id="pickupAddressCountry" path="shipToAddress.country" />
						
						<!-- Below Fields for Cleansing address Don't change the
							input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						-->
						<form:input id="pickupAddresscounty" path="shipToAddress.county" />
						<form:input id="pickupAddressofficeNumber" path="shipToAddress.officeNumber" />
						<form:input id="pickupAddressstateCode" path="shipToAddress.stateCode" />
						<form:input id="pickupAddressstateFullName" path="shipToAddress.stateFullName" />
						<form:input id="pickupAddressdistrict" path="shipToAddress.district" />
						<form:input id="pickupAddressregion" path="shipToAddress.region" />
						<form:input id="pickupAddresslatitude" path="shipToAddress.latitude" />
						<form:input id="pickupAddresslongitude" path="shipToAddress.longitude" />
						<form:input id="pickupAddresscountryISOCode" path="shipToAddress.countryISOCode" />
						<form:input id="pickupAddresssavedErrorMessage" path="shipToAddress.savedErrorMessage" />
						<form:input id="pickupAddressisAddressCleansed" path="shipToAddress.isAddressCleansed"/>
						<!-- Ends Cleansing address -->
						
						
						
						
						
						
						
						<form:input id="relatedServiceRequestedNumber" path="relatedServiceRequestedNumber" />
						<form:input id="relatedServiceRequestedRowId" path="relatedServiceRequestedRowId" />
						<form:input id="listOfFileTypes" path="listOfFileTypes" />
						<form:input id="attMaxCount" path="attMaxCount"/>
					</span>
			</div><!-- infoBox columnInner rounded shadow -->
		</div><!-- columnsTwo -->
		<div class="columnsTwo">
			<div class="infoBox columnInner rounded shadow">
				<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
				<ul class="form">
					<li>
						<label for="desc2"><spring:message code="requestInfo.label.specialInstructions"/></label> 
 						<span class="multiLine">
 						<%--<form:textarea id="specialInstruction" rows="3" path="specialInstruction"/></span> --%>
 						<!-- Added for "Special Instruction" Textarea -- CI17_BRD_18 -- Start-->
													
						<form:textarea id="specialInstruction" rows="3" path="specialInstruction" maxlength="255"/>
													
						<!-- Added for "Special Instruction" Textarea -- CI17_BRD_18 -- End-->
						
						<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.specialInstruction"/>" >
						</span>
						<em class="note"><spring:message code="requestInfo.label.note1"/></em>
					</li>
					<%-- <li>
						<label><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
						<span class="multiLine">${catalogDetailPageForm.defaultSpecialInstruction}</span>
					</li> --%>
					<%--
					Commented for Code revert--%>
					<c:if test="${catalogDetailPageForm.expediteOrderAllowed == 'true'}">
					<li>
						<label for="chck"><spring:message code="requestInfo.label.requestExpediteOrder"/></label> 
						<span><form:checkbox id="requestExpediteOrder" path="requestExpediteOrder"/></span>
						<em class="note"><spring:message code="requestInfo.label.expediteShippingNote"/></em>
					</li>
					</c:if>
					<%--Ends --%>
					
					
					
					<li>
						<label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label> 
						<span><form:input id="requestedDeliveryDate" class="w100" path="requestedDeliveryDate" readonly="true" onchange="shwDate1();" onblur="shwDate1();" /> 
						<img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" onclick="showCal('requestedDeliveryDate', convertDateToString(new Date().addDays(null)), null); " />
						&nbsp;&nbsp;<img id="imgDate2" class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" height="17px" width="17px" onClick="removeDate1();"/>
						&nbsp;&nbsp;<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.requestedDeliveryDate"/>" ></span>
					</li>
				</ul>
			</div><!-- infoBox columnInner rounded shadow -->
		</div><!-- columnsTwo -->
	</div><!-- portletBlock -->
	<!-- Shipping & Billing Information End -->
	<!-- Payment Details Start -->	
	<c:choose>
	<c:when test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'false'}">
		<div class="portletBlock">
	    	<div class="columnsOne">
	        	<div class="infoBox columnInner rounded shadow">
				<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
					<ul class="form">
						<li><label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label> 
						<span><form:input size="10" id="poNumber" path="poNumber" maxlength="100"/> 
						<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.ponumber"/>" ></span></li>
					</ul>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<c:if test="${sessionScope.catalogFinalFlags['finalCreditFlag'] == 'false'}"> 
	<div class="portletBlock">
    	<div class="columnsOne">
        	<div class="infoBox columnInner rounded shadow">
			<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
				<ul class="form">
					<li><label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label> 
					<span><form:input size="10" id="poNumber" path="poNumber" maxlength="100"/> 
					<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.ponumber"/>" ></span></li>
				</ul>
			</div>
		</div>
	</div><!-- portletBlock -->	
	</c:if>
	</c:otherwise>
	</c:choose>
	<!-- Payment Details End -->
	<!-- Add Notes Start -->
					<span style="display: none;">
						<input type="text" name="attachmentDescriptionID" id="attachmentDescriptionID" />
					</span>
					</form:form>
					<p class="inlineTitle"><spring:message code="requestInfo.label.attachFiles"/></p>
		
       				 <jsp:include page="/WEB-INF/jsp/common/commonAttachment.jsp" /><!-- infoBox -->
				
	<!-- Add Notes End -->
	<form:form id="dummyForm" name="dummyForm" method="post">
	<div class="buttonContainer">
		<button type="button" class="button_cancel" onclick="onCancelClick();"><spring:message code="button.back"/></button>
		<button class="button_cancel" type="button" onclick="javascript:draftCatalogOrder();"><spring:message code="button.saveAsDraft"/></button>
		<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
        <button type="button" class="button" onclick="javascript:submitCatalogOrder();"><spring:message code="button.continue"/></button>			
	</div><!-- buttonContainer -->
	</form:form>
	<input type="hidden" id="fileCount" />
	<div id="modaldialogbox" class="modal-dialogbox2" >
	    <div class="columnInner" id="loadingDialog">
	    	<div class="columnsTwo w50"><img src="/LexmarkServicesPortal/images/green-check.png" class="green-check-img" ></img></div>
	    	<div class="columnsTwo w70p">
	      		<p><spring:message code="requestInfo.message.draftMessage1"/> ${srnumber}<br/>
	      	<spring:message code="requestInfo.message.draftMessage2"/></p>
	    	</div>
	   		<div class="buttonContainer popupBtnCont">
	    		<button id="draftPopupOk" class="button" onclick="dialog.dialog('close');"><spring:message code="button.ok"/></button>
	    	</div>
	     </div>
	</div>
	</div><!-- portletBodyWrap -->
</div><!-- content-wrapper -->
</div><!-- main-wrap -->
</div><!-- content -->
<script type="text/javascript">	   
var pageType = "catalogDetails";
var tabRowNum = document.getElementById("catalogDetailPartTab").rows.length;
var rowsNumFlag = [];
for(var i=0;i<tabRowNum-1;i++){
	rowsNumFlag[i] = true;
}
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





var portlet_name="<%=LexmarkSPOmnitureConstants. CATALOGORDERDETAILADDADDITIONALCONTACT %>";
var action_primary="<%=LexmarkSPOmnitureConstants.CATALOGORDERDETAILSELECTDIFFERENTCONTACT%>";
var action_secondary="<%=LexmarkSPOmnitureConstants. CATALOGORDERDETAILADDADDITIONALCONTACT%>";
//added by Sagar
<c:if test="${catalogDetailPageForm.serviceRequest.expediteOrder == true}">
	jQuery('#requestExpediteOrder').attr('checked','checked');
</c:if>

<c:if test="${Error != null}">
jQuery('#taxValidationErrors').show();	
</c:if>

var dialog;
var linkId;
var draftConfirmation = "${draftConfirmation}";
var dialog;
var orderQtyFlag=true
//alert('draftConfirmation has any value '+draftConfirmation);
if(draftConfirmation == "draftConfirmation"){
	//alert('Lets call here the popup');
	popupDialogDraft();
	
}else{
	//alert('From asset list page so hiding the secondary contact');
	//hideAddiContacts();
	document.getElementById("modaldialogbox").style.display = 'none';
}



function popupDialogDraft(){
	dialog = jQuery("#modaldialogbox").dialog({
		autoOpen: false,
		open: function() {jQuery(".ui-dialog-titlebar-close").hide();},
		modal: true,
		closeOnEscape: false,
		title: '<spring:message code="requestInfo.message.draftSaved"/>',
		resizable: false,
		position: "center",
		width: 450,
		height: 400
		
		});
	
	dialog.dialog("open");

	if (window.PIE) {
        document.getElementById("draftPopupOk").fireEvent("onmove");
    }
	
	return false;
}
jQuery(document).ready( function() {
	//jQuery("h4.expand").toggler();
	if(jQuery('#attachmentDescription').val() != '' && jQuery('#attachmentDescription').val() != null){
		jQuery('#showAttachment').show();
	}
	//Show the primary address
	//alert('check the error message is there');
	var currentURL = window.location.href;
	if(currentURL.indexOf('/partner-portal') != -1)
	{	
		jQuery('#topnavigation li a').each(function(){
			  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
			  jQuery(this).parent().addClass('selected');
			  });
	}
// 		Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		else
			{
			jQuery('#changeaccount').hide();
			}
	//Added for MPS 2.1
	jQuery("#pickupStoreFrontNameLbl").html(document.getElementById("pickupStoreFrontName").value);
	document.getElementById("pickupAddressLine1Lbl").innerHTML=document.getElementById("pickupAddressLine1").value;
	jQuery("#pickupAddressofficeNumberLbl").html(document.getElementById("pickupAddressofficeNumber").value);
	if(document.getElementById("pickupAddressLine2").value !="" && document.getElementById("pickupAddressLine2").value !=null){
		jQuery("#pickupAddressLine2Lbl").html(document.getElementById("pickupAddressLine2").value + ',');
		jQuery("#pickupAddressLine2Lbl").show();
		}
	if(document.getElementById("pickupAddressCity").value!='' && document.getElementById("pickupAddressCity").value != null){
		jQuery("#pickupAddressCityLbl").html(document.getElementById("pickupAddressCity").value);
		}
	if(document.getElementById("pickupAddresscounty").value!='' && document.getElementById("pickupAddresscounty").value != null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+document.getElementById("pickupAddresscounty").value);
		jQuery("#pickupAddresscountyLbl").show();
		}
	if(document.getElementById("pickupAddressState").value!='' && document.getElementById("pickupAddressState").value != null){
		jQuery("#pickupAddressStateLbl").html(',&nbsp;'+document.getElementById("pickupAddressState").value);
	}
	if((document.getElementById("pickupAddressProvince").value!='' && document.getElementById("pickupAddressProvince").value!=' ') && document.getElementById("pickupAddressProvince").value != null){
		jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+document.getElementById("pickupAddressProvince").value);
		jQuery("#pickupAddressProvinceLbl").show();
		}
	// region changed to district for MPS 2.1
	if((document.getElementById("pickupAddressdistrict").value!='' && document.getElementById("pickupAddressdistrict").value!=' ')&& document.getElementById("pickupAddressdistrict").value != null){
		jQuery("#pickupAddressRegionLB").html(',&nbsp;'+document.getElementById("pickupAddressdistrict").value);
		jQuery("#pickupAddressRegionLB").show();
		}
	jQuery("#pickupAddressPostCodeLbl").html(document.getElementById("pickupAddressPostCode").value);
	jQuery("#pickupAddressCountryLbl").html(document.getElementById("pickupAddressCountry").value);
	if((document.getElementById("pickupAddressProvince").value=='' || document.getElementById("pickupAddressProvince").value==' ') || document.getElementById("pickupAddressProvince").value == null){
		jQuery("#pickupAddressProvinceLbl").hide();
	}
	if(document.getElementById("pickupAddresscounty").value=='' || document.getElementById("pickupAddresscounty").value == null){
		jQuery("#pickupAddresscountyLbl").hide();
	}
	// region changed to district for MPS 2.1
	if((document.getElementById("pickupAddressdistrict").value==''|| document.getElementById("pickupAddressdistrict").value==' ') || document.getElementById("pickupAddressdistrict").value == null){
		jQuery("#pickupAddressRegionLB").hide();
	}
	if(document.getElementById("pickupAddressLine2").value=='' || document.getElementById("pickupAddressLine2").value == null){
		jQuery("#pickupAddressLine2Lbl").hide();
	}
	//Added for MPS 2.1 end
});		

function popupAddress(hyperlinkId){
	
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	//alert(linkId);
	var addressFlag = "${catalogDetailPageForm.addressFlag}";
	//alert('addressFlag '+addressFlag);
	var url=jQuery('#'+hyperlinkId).attr('href');
	url+="&addressFlag="+addressFlag;
	showOverlay();
	jQuery('#dialogAddress').load(url,function(){
		dialog=jQuery('#content_addr').dialog({
			autoOpen: false,
			title: jQuery('#'+hyperlinkId).attr('title'),
			modal: true,
			draggable: false,
			resizable: false,
			height: 500,
			width: 950,
			close: function(event,ui){
				hideOverlay();
				dialog=null;
				jQuery('#content_addr').remove();
				}
			});
		jQuery(document).scrollTop(0);
		dialog.dialog('open');
		initializeAddressGrid();
		
		});
	return false;
}

if(document.getElementById("errors")!= null){
	//alert('Check if there are error in the javascript method');
	var divText = document.getElementById("errors").innerHTML;
	//alert('div text is '+divText);
	jQuery('#validationErrors',window.parent.document).append(divText);
	window.parent.document.getElementById("validationErrors").style.display = 'block';
	window.parent.scrollTo(0,0);
}

function checkParts(inputboxId,maxOrderQty,id){
	tabRowNum = document.getElementById("catalogDetailPartTab").rows.length;	
	var ordQty = document.getElementById(inputboxId).value;	
	var newID = inputboxId;
	var maxQty = parseInt(maxOrderQty);
	var orderQuantity = parseInt(ordQty);
	if(maxQty != "" && maxQty != null){
		if(orderQuantity>maxQty){
			 rowsNumFlag[id] = false;			 
			 orderQtyFlag = false;
			 jQuery('#'+newID).addClass('errorColor');
			 document.getElementById("MsgassetPartList["+id+"].orderQuantity").style.display = 'block';
		 }
		 else{
			 rowsNumFlag[id] =true;
			 jQuery('#'+newID).removeClass('errorColor');
			 orderQtyFlag = true;
			 document.getElementById("MsgassetPartList["+id+"].orderQuantity").style.display = 'none';
		 }
	}
		 	 
 }

function removeErrorMessage(id){
	document.getElementById('errorMsgPopup_CatalogDtl').style.display = "none";
	document.getElementById('errorMsgPopup_CatalogDtl').innerHTML = "";
		jQuery('#partQuantity'+id).removeClass('errorColor'); 

}
function submitCatalogOrder(){
	// 	Added for CI7 BRD 13-10-18 START
	var text_specialInstruction;
	var length_specialInstruction;
	jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	text_specialInstruction=jQuery('#specialInstruction').val();
	length_specialInstruction=text_specialInstruction.length;
	var text_addtnlDescription;
	var length_addtnlDescription;
	text_addtnlDescription=jQuery('#addtnlDescription').val();
	length_addtnlDescription=text_addtnlDescription.length;
	//Added for CI7 BRD 13-10-18 END
	document.getElementById("jsValidationErrors").innerHTML = '';
	document.getElementById("jsValidationErrors").style.display = 'none';
	document.getElementById("validationErrors").innerHTML = '';
	document.getElementById("validationErrors").style.display = 'none';
	var tableCatalogDetail = document.getElementById('catalogDetailPartTab');
	var tableCatalogDetailNum = tableCatalogDetail.rows.length;
	var validationFlagPopup = true;
	<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
	document.getElementById('errorMsgPopup_CatalogDtl').style.display = "none";
	document.getElementById('errorMsgPopup_CatalogDtl').innerHTML = "";
	if(tableCatalogDetailNum==1){
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.catalogDetail.emptyTable"/></strong></li>');
		validationFlagPopup = false;
	}
	// added by jyoti for requested delivery date validation
	if(jQuery('#requestedDeliveryDate').val()!=""&&!dateCheck(jQuery('#requestedDeliveryDate').val()))
		{
			//alert(jQuery('#requestedDeliveryDate').val());
			<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
			jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.date.format.errorMsg"/></strong></li>');
			jQuery('#requestedDeliveryDate').addClass('errorColor');	
			validationFlagPopup = false;
		}
	//Added for CI BRD 13-10-18 Start
	if(length_specialInstruction>255){
		validationFlagPopup = false;
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.maxLength.errorMsg"/></strong></li>');
		jQuery('#specialInstruction').addClass('errorColor');
	}
	//Added for CI BRD 13-10-18 END
	<%-- Defect 10776 --%>
	if(jQuery('#poNumber').length!=0){
		if(jQuery('#poNumber').val().length > 100){
			jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="requestInfo.validation.poNumber"/></strong></li>');
			jQuery('#poNumber').addClass('errorColor');
			validationFlagPopup = false;
			}
	}
	if(jQuery('#physicalLocation1').val().length > 100){
		validationFlagPopup = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.buiding"/></strong></li>');
		jQuery('#physicalLocation1').addClass('errorColor');
		}

	if(jQuery('#physicalLocation2').val().length > 100){
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.floor"/></strong></li>');
		jQuery('#physicalLocation2').addClass('errorColor');
		validationFlagPopup = false;
		}

	if(jQuery('#physicalLocation3').val().length > 100){
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.office"/></strong></li>');
		jQuery('#physicalLocation3').addClass('errorColor');
		validationFlagPopup = false;
		}
	
	if(jQuery('#custReferenceId').val().length > 64){
		validationFlagPopup = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.custReference.length.errorMsg"/></strong></li>');
		jQuery('#custReferenceId').addClass('errorColor');
		}

	if(jQuery('#costCenter').val().length > 200){
		validationFlagPopup = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.costCenter.length.errorMsg"/></strong></li>');
		jQuery('#costCenter').addClass('errorColor');
		}
	/* if(!descValidate(jQuery('#partsToBeInstalled1').val(),"errorMsg")) {
		validationFlagPopup = false;
		jQuery('#partsToBeInstalled1').addClass('errorColor');
	} */
	if(jQuery('#attachmentDescription').val().length > 2000){
		validationFlagPopup = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="requestInfo.validation.attachmentNotes"/></strong></li>');
		jQuery('#attachmentDescription').addClass('errorColor');
	}
	if(length_addtnlDescription>255){
		validationFlagPopup = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.maxLength.errorMsg"/></strong></li>');
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	
	for(var i=0;i<tabRowNum-1;i++){
		if(rowsNumFlag[i]==false){
			orderQtyFlag = false;
		}
		
	}
	
	if(!orderQtyFlag) {
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
		validationFlagPopup = false;
	}
	<%-- Ends Defect 10776 --%>
	if (!validationFlagPopup)
	{	
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		document.getElementById('errorMsgPopup_CatalogDtl').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
		//alert("block error");
	}
	
	document.getElementById("pageSubmitType").value = 'confirmOrderRequest';
	jQuery("#catalogDetailPageForm").submit();
}

function draftCatalogOrder(){
// 	Added for CI7 DEFECT 7928 START
jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	var validationFlag_Popup = true;
	var text_specialInstruction;
	var length_specialInstruction;
	text_specialInstruction=jQuery('#specialInstruction').val();
	length_specialInstruction=text_specialInstruction.length;
	var text_addtnlDescription;
	var length_addtnlDescription;
	text_addtnlDescription=jQuery('#addtnlDescription').val();
	length_addtnlDescription=text_addtnlDescription.length;
	if(length_specialInstruction>255){
		validationFlag_Popup = false;
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.maxLength.errorMsg"/></strong></li>');
		jQuery('#specialInstruction').addClass('errorColor');
	}
	if(length_addtnlDescription>255){
		validationFlag_Popup = false;
		jQuery('#errorMsg').append('<li><strong><spring:message code="validation.maxLength.errorMsg"/></strong></li>');
		jQuery('#addtnlDescription').addClass('errorColor');
	}
	if(!orderQtyFlag) {
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
		validationFlag_Popup = false;
	}
	if (!validationFlag_Popup)
	{	
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		document.getElementById('errorMsgPopup_CatalogDtl').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
	}
//Added for CI7 DEFECT 7928 END
	document.getElementById("pageSubmitType").value = 'draftOrderRequest';
	jQuery("#catalogDetailPageForm").submit();
}


jQuery(document).ready(function() {
	//alert('ready function 1');
	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	window.parent.document.getElementById("fileCount").value = fileCount;
	jQuery("#fileInput").css({"cursor":"pointer"});
	//alert('ready function 2');
	jQuery("#processingHint",window.parent.document).css({
	           display:"none"
	   });
	//alert('ready function 3');
	   jQuery("#overlay",window.parent.document).css({
	           display:"none"
	   });
	});

  
	

	
	function getFullPath(obj) 
	{ 
	    if(obj) 
	    { 
	        //ie 
	        if (window.navigator.userAgent.indexOf("MSIE")>=1) 
	        { 
	            obj.select(); 
	           var path = document.selection.createRange().text;
			   return path ; 
	        } 
	        else
	        {  
	            if(obj.files) 
	            { 
					return obj.files.item(0).fileName; 
	            } 
	            return obj.value;
	        } 
	    } 
	};

	function move(thisObject){
	    var a= document.getElementById('fileContent');
	    var offset = jQuery('#btnUpload').offset();
	    a.style.left = offset.left + 'px';
	    a.style.top = offset.top + 'px';
	};
	<%--Changes for Phase 2.1--%>
	function addPickupAddress(addressId,storeFrontName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
			addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode)
	<%--Ends--%>
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
		//added 
		jQuery("#pickupAddressregion").val(region);
		jQuery("#pickupAddresscountryISOCode").val(countryISO);
		jQuery("#pickupAddressstateCode").val(stateCode);
		<%--Ends--%>		
		
		//changes for MPS 2.1
		//This is for display purpose pickup address
		jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
		jQuery("#pickupAddressLine1Lbl").html(addressLine1);
		jQuery("#pickupAddressofficeNumberLbl").html(officeNumber);
		if(addressLine2 !='' && addressLine2 !=' ' && addressLine2 != null){
			jQuery("#pickupAddressLine2Lbl").html(addressLine2+",");
			jQuery("#pickupAddressLine2Lbl").show();
			}

		if(addressCity !='' && addressCity !=' ' && addressCity != null){
			jQuery("#pickupAddressCityLbl").html(addressCity);
			}
		if(county !='' && county !=' ' && county != null){
			jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+county);
			jQuery("#pickupAddresscountyLbl").show();
			}
		if(addressState !='' && addressState !='' && addressState != null){
			jQuery("#pickupAddressStateLbl").html(',&nbsp;'+addressState);
			jQuery("#pickupAddressStateLbl").show();
			}
		if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
			jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+addressProvince);
			jQuery("#pickupAddressProvinceLbl").show();
			}
		// region changed to district for MPS 2.1
		if(district !='' && district !=' ' && district != null){
			jQuery("#pickupAddressRegionLB").html(',&nbsp;'+district);
			jQuery("#pickupAddressRegionLB").show();
			}
		jQuery("#pickupAddressPostCodeLbl").html(addressPostCode);		
		jQuery("#pickupAddressCountryLbl").html(addressCountry);
		if(addressProvince =='' || addressProvince ==' ' || addressProvince == null){
			jQuery("#pickupAddressProvinceLbl").hide();
			}
		if(county =='' || county ==' ' || county == null){
			jQuery("#pickupAddresscountyLbl").hide();
			}
		// region changed to district for MPS 2.1
		if(district =='' || district ==' ' || district == null){
			jQuery("#pickupAddressRegionLB").hide();
			}
		if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
			jQuery("#pickupAddressLine2Lbl").hide();
			}
		if(addressState =='' || addressState ==' ' || addressState == null){
			jQuery("#pickupAddressStateLbl").hide();
			}
		//changes for MPS 2.1 end
	}
	<%--Changes for Phase 2.1--%>
	function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2,
	addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode)
	<%--Ends--%>
	{

		<%--Changes for defect 7896 MPS 2.1 --%>
		jQuery('#assetDetailErrors').hide();
		jQuery('#diffAddressLink').html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
		<%--Ends Changes for defect 7896 MPS 2.1 --%>
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
				<%--Changes for Phase 2.1--%>
				addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
						addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
				<%--Ends--%>
			}
			closeDialog();
			if(document.getElementById("assetDetailErrors")!= null){
			document.getElementById('assetDetailErrors').style.display = "none";
			}
	}
	function deletePartFromCatalogDetail(catalogId, partNumberIndex) {
		//alert('entered deletePartFromCart catalog id '+catalogId);
		var tableCart = document.getElementById('catalogDetailPartTab');
		//alert('partNumberIndex is '+partNumberIndex);
		var tableCartRowNum = tableCart.rows.length;
		//alert('tableCartRowNum is '+tableCartRowNum);
		//Let make an call to remove the part from the cart
		var selectedItem = "&random="+Math.random()*99999+ "&catalogId=" + catalogId;
		var updateDetailViewURL = '<portlet:resourceURL id="deleteFromCatalogDetail"/>'+selectedItem;
		jQuery("#"+partNumberIndex).load(updateDetailViewURL,function(response){
		//alert('updateCartViewURL resourcemapping called');
	
		});


		var newCatalogId;
		for (var i = partNumberIndex; i < tableCartRowNum - 2; i ++) {
			//alert('old catalogid '+document.getElementById('catalogId[' + i +']').value);
			document.getElementById('catalogPartList[' + i +'].orderQuantity').value = document.getElementById('catalogPartList[' + (i + 1) +'].orderQuantity').value;
			document.getElementById('partNumber[' + i +']').innerHTML = document.getElementById('partNumber[' + (i + 1) +']').innerHTML;
			document.getElementById('partDesc[' + i +']').innerHTML = document.getElementById('partDesc[' + (i + 1) +']').innerHTML;
			document.getElementById('partType[' + i +']').innerHTML = document.getElementById('partType[' + (i + 1) +']').innerHTML;
			document.getElementById('yield[' + i +']').innerHTML = document.getElementById('yield[' + (i + 1) +']').innerHTML;
			document.getElementById('model[' + i +']').innerHTML = document.getElementById('model[' + (i + 1) +']').innerHTML;
			//alert('lets call catalog id');
			document.getElementById('catalogId[' + i +']').value = document.getElementById('catalogId[' + (i + 1) +']').value;
			//prepare innerhtml for quantity field
			//alert('catalog id assignement done');
			//prepare innerhtml for delete field
			newCatalogId = document.getElementById('catalogId[' + i +']').value;
			//alert('catalog id printing '+newCatalogId);
			partQuantity = document.getElementById('catalogPartList[' + i +'].orderQuantity').value;
			//alert('newCatalogId is '+newCatalogId);
			newCatalogId = "'"+newCatalogId+"'";
			//alert('formatted '+newCatalogId);
			tableCart.rows[i+1].cells[6].innerHTML = '<input type="text" id="catalogPartList['+ i +'].orderQuantity" class="w90 right" name="catalogPartList['+ i +'].orderQuantity" value='+partQuantity+' maxlength="4">';
			//alert('input type is done');
			//please change the above to 6 once the development done
			tableCart.rows[i+1].cells[0].innerHTML = '<img id= "'+(i)+'" onclick="deletePartFromCatalogDetail('+newCatalogId+', '+(i)+');" class="delete ui_icon_sprite ui-icon-closethick" title="Delete" src="<html:imagesPath/>transparent.png" width="15" height="15">';
			//alert('image is done');
		}
		//alert('changing done about to delete '+tableCartRowNum);
		tableCart.deleteRow(tableCartRowNum-1);

		
		//alert('after call');
		//tableCart.deleteRow(partNumberIndex + 1);
		//Let
	};

	function onCancelClick() {
		var cancelAction = 'cancelAction';
		var relatedServiceRequestNumber = document.getElementById("relatedServiceRequestedNumber").value;
		var url = "<portlet:renderURL></portlet:renderURL>&pageFrom="+cancelAction+ "&relatedServiceRequestNumber=" + relatedServiceRequestNumber;
		//alert('url is '+url);
		window.location.href = url;
	};

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
	
	//Added for MPS 2.1 Extra fields for address cleansing
	function addRestFieldsOfCleanseAddress(){
		jQuery('#pickupAddressisAddressCleansed').val("true");
		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
			jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
		<%}%>
		jQuery("#pickupAddressofficeNumberLbl").html(jQuery("#pickupAddressofficeNumber").val());
		//Added for MPS 2.1
		if(jQuery('#pickupAddresscounty').val()!='' && jQuery('#pickupAddresscounty').val()!=null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+jQuery('#pickupAddresscounty').val());
		jQuery("#pickupAddresscountyLbl").show();
		}
		if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() == null){
			jQuery("#pickupAddresscountyLbl").hide();
			}
		//jQuery("#pickupAddressdistrictLbl").html(jQuery('#pickupAddressdistrict').val());
		//END
		}
	//Ends
	
	//Added for MPS 2.1
	jQuery(document).ready (function (){
	
		jQuery('#imgDate2').hide();
			
	});
	
	function removeDate1(){
		 jQuery('#requestedDeliveryDate').val('');
		 jQuery('#imgDate2').hide();
	 }
	 
	 function shwDate1(){
		 if(jQuery('#requestedDeliveryDate').val() != ''){
			 jQuery('#imgDate2').show();
		 }
	 }
	
	 /* End */
	function removeValidation(){
		jQuery('#poNumber').removeClass('errorColor');
		
		jQuery('#errorMsgPopup_CatalogDtl').html("");
		jQuery('#errorMsgPopup_CatalogDtl').hide();
	 }

</script>