<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%-- Added for CI 7 BRD 12 --%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"></jsp:include>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%-- Ends changes CI 7 BRD 12 --%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 8]>
<style type="text/css">
.relativeP{position:relative!important;}
</style>
<![endif]-->
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style type="text/css">
.relativeP{position:relative!important;}
</style>
<![endif]-->
<style>
.fontQty{font-size:14px;font-weight:bold;}
#bundlesTab tr td {
	padding:5px;	
	vertical-align:top
}
.noBorder tr td { border:0 none; }
.altRow{ background-color:#f0f0f0 }
#bundlesTab tr:hover{background-color:#ffffcc;};
</style>
<style>
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
.ie8 .button, .ie8 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<script type="text/javascript">	
	<%@ include file="../../../../js/expand.js"%>
</script>

<portlet:resourceURL var="downloadXLSFile" id="downloadXLSFile"></portlet:resourceURL>
<portlet:renderURL var="submitHardwareOrderURL">
	<portlet:param name="action" value="submitHardwareOrder"/>
</portlet:renderURL>
 
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
	<c:if test="${sessionScope.hardwareCurrentDetails['paymentType'] == 'Ship and Bill' || sessionScope.hardwareCurrentDetails['paymentType'] == 'Indirect Billing'}">
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
      <h1><spring:message code="requestInfo.heading.hardwareRequest"/></h1>
		<h2 class="step"><spring:message code="requestInfo.heading.requestHardware"/></h2>
      </div>
      <%--Changes for BRD12 CI 7 --%>
      <div><h3><strong>
        <jsp:include page="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"></jsp:include>
        <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        <%--Ends Changes for BRD12 CI 7 --%>
	<!-- Validation Errors displayed in the below div -->
           <spring:hasBindErrors name="hardwareDetailPageForm">
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
	            <li class="active"><a href="javascript:deleteHardwareDetails();" title="<spring:message code="requestInfo.tooltip.step1SelectItems"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.message.selectItems"/></a></li>
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
			
			<form:form id="hardwareDetailPageForm" method="post" commandName="hardwareDetailPageForm" modelAttribute="hardwareDetailPageForm" action="${submitHardwareOrderURL}">
			<form:hidden path="pageSubmitType" id="pageSubmitType"/>
			<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%> 
			
	<!-- Parts Detail Start -->
	<hr class="separator" />
	
	<!-- Bundle List -->
	<p class="inlineTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></p>
	<c:if test="${not empty hardwareDetailPageForm.bundleList}">
	<div class="portletBlock infoBox rounded shadow">
        <div class="columnsOne">
	        <div class="columnInner">
	          <h4 class="expand"><spring:message code="requestInfo.heading.preConfhardware"/></h4>
	          <div class="collapse staticPos" id="preConf">
	          <div class="columnInner">       
          <table id="bundlesTab" class="noBorder rounded shadow wFull">
          <c:forEach items="${hardwareDetailPageForm.bundleList}" var="hardwareBundleListDetail" varStatus="counter" begin="0">
          	<input type="hidden" id="bundleId[${counter.index}]" name="bundleId[${counter.index}]" value='${hardwareBundleListDetail.catalogId}'/>
            <tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>" id= "bundleRow${counter.index}">
              <td id="bundleDesc[${counter.index}]" class="w300">
				${hardwareBundleListDetail.partDesc}
			  </td>
              <td>
				<div>
					<table class="displayGrid rounded shadow wFull" id="">
						<thead>
							<tr>
								<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.partNumber"/></th>
								<th><spring:message code="orderSupplies.placeOrderHeader.description"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.Qty"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${hardwareBundleListDetail.bundlePartList}" var="bundlePart" varStatus="partCounter" begin="0">
							<tr class="<c:if test="${partCounter.index % 2 ne 0}">altRow</c:if>" id= "bundlePartRow${counter.index}">
								<td id="bundlePartNumber[${partCounter.index}]" class="w100 borderTableLeft">${bundlePart.partNumber}</td>
								<td id="bundlePartDesc[${partCounter.index}]" class="borderTableLeft">${bundlePart.description}</td>
								<td id="bundlePartQty[${partCounter.index}]" class="w100 borderTableLeft">${bundlePart.qty}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			  </td>
              <td class="w200">
              <c:choose>
	            <c:when test="${draftSrNumber==''}">
	              	<c:if test="${sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true'}">
	              		 <c:choose>
	              		 <c:when test="${hardwareBundleListDetail.unitPrice ne '' && hardwareBundleListDetail.unitPrice ne null && hardwareBundleListDetail.unitPrice ne '0' && hardwareBundleListDetail.unitPrice ne '0.0' && hardwareBundleListDetail.unitPrice ne '0.00'}">
	              		 <c:set var="price" value="${fn:split(hardwareBundleListDetail.unitPrice, '.')}" />
	              		 <div id="bundlePrice[${counter.index}]"><span class="f200">${price[0]}</span><span class="f150">${price[1]}</span> <span class="f200">(${hardwareBundleListDetail.currency})</span></div>
	              		 </c:when>
	              		 </c:choose>
						</c:if>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${hardwareBundleListDetail.unitPrice ne '' && hardwareBundleListDetail.unitPrice ne null && hardwareBundleListDetail.unitPrice ne '0' && hardwareBundleListDetail.unitPrice ne '0.0' && hardwareBundleListDetail.unitPrice ne '0.00'}">
					<c:set var="price" value="${fn:split(hardwareBundleListDetail.unitPrice, '.')}" />
						<div id="bundlePrice[${counter.index}]" ><span class="f200">${price[0]}</span><span class="f150">${price[1]}</span> <span class="f200">(${hardwareBundleListDetail.currency})</span></div>
					</c:when>
					</c:choose>
				</c:otherwise>
				</c:choose>
				
				<div class="lineClear">
				<span class="fontQty"><spring:message code="requestInfo.heading.Qty"/></span><span class="spaceClear"/>
				<input type="text" id="bundleList[${counter.index}].orderQuantity" class="w50 right" name="bundleList[${counter.index}].orderQuantity" value='${hardwareBundleListDetail.orderQuantity}' maxlength="4">
			  </td>
             
            </tr>
            </c:forEach>
          </table>
        </div>
	        </div>
	        </div>
	    </div>
	    </div>
	</c:if>
	<!-- Accessories List -->
	<c:if test="${not empty hardwareDetailPageForm.accessoriesList}">
	<div class="portletBlock infoBox rounded shadow">
	        <div class="columnInner">
	          <h4 class="expand"><spring:message code="requestInfo.heading.accessories"/></h4>
	          <div id="accessories" class="collapse staticPos">
	         <div class="columnInner">       
          <table id="accessoriesTab" class="displayGrid noBorder rounded shadow wFull">
          <c:forEach items="${hardwareDetailPageForm.accessoriesList}" var="hardwareAccessories" varStatus="counter" begin="0">
          	<input type="hidden" id="accessoriesCatalogId[${counter.index}]" name="accessoriesCatalogId[${counter.index}]" value='${hardwareAccessories.catalogId}'/>
            	<tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>">
	              <td class="w100">
	               <c:if test="${hardwareAccessories.partImg ne ''}">
	 				<img src="${hardwareAccessories.partImg}" alt="Change" width="100" height="100"> 
	 			   </c:if>
				  </td>
	              <td class="w400">
					<ul class="form">
						<li>
							<label for="refId"><spring:message code="requestInfo.heading.hardwarePartNo"/></label>
							<span class="textScroll">${hardwareAccessories.partNumber}</span></li><li>
							<label for="refId"><spring:message code="requestInfo.label.description"/></label>
							<span class="textScroll">${hardwareAccessories.partDesc}</span></li>
					</ul>
				  </td>
				  <td class="w300">
					<ul class="form">
						<li><label for="refId"><spring:message code="requestInfo.heading.deviceType"/></label><span class="textScroll" id="partType[${counter.index}]">${hardwareAccessories.partType}</span></li>
						<li><label for="refId"><spring:message code="requestInfo.label.model"/></label><span class="textScroll" id="model[${counter.index}]">${hardwareAccessories.model}</span></li></ul>
				  </td>
	              <td class="w200">
	              
	              
	              <c:choose>
	              <c:when test="${draftSrNumber==''}">
	              	<c:if test="${sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true'}">
	              	<c:choose>
	              		 <c:when test="${hardwareAccessories.unitPrice ne '' && hardwareAccessories.unitPrice ne null}">
		              		 <c:set var="priceAcc" value="${fn:split(hardwareAccessories.unitPrice, '.')}" />
		              		 <div><span class="f200">${priceAcc[0]}</span></span><span class="f150">${priceAcc[1]}</span> <span class="f200">(${hardwareAccessories.currency})</span></div>
	              		 </c:when>
	              		 </c:choose>
					</c:if>
					</c:when>
					<c:otherwise>
					<c:if test="${hardwareAccessories.unitPrice ne '' && hardwareAccessories.unitPrice ne null}">
						<c:set var="priceAcc" value="${fn:split(hardwareAccessories.unitPrice, '.')}" />
						<c:set var="priceAcc" value="${fn:split(hardwareAccessories.unitPrice, '.')}" />
		              		 <div><span class="f200">${priceAcc[0]}</span></span><span class="f150">${priceAcc[1]}</span> <span class="f200">(${hardwareAccessories.currency})</span></div>
					</c:if>
					</c:otherwise>
					</c:choose>
					
					
					<div class="lineClear"><span class="fontQty"><spring:message code="requestInfo.heading.Qty"/></span><span class="spaceClear"/>
						<input type="text" id="accessoriesList[${counter.index}].orderQuantity" class="w50 right" name="accessoriesList[${counter.index}].orderQuantity" value='${hardwareAccessories.orderQuantity}' maxlength="4">					
				  	</td>
             
            	</tr>
	            </c:forEach>
	          </table>
	        </div>	          
	        </div>
	        </div>
	    </div>
	    </c:if>
	    
	    
       <!-- Parts Detail End -->
	<!-- Shipping & Billing Information Start -->
	<p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
	<div class="portletBlock">
		<div class="columnsTwo">
			<div class="infoBox columnInner rounded shadow">
				<h4>
					<a href="${addressListPopUpUrl}" class="lightboxLarge" title="<spring:message code="requestInfo.link.selectAnAddress"/>" id="diffAddressLink" 
						onclick="return popupAddress(id);">
						
						<spring:message code="requestInfo.link.selectAnAddress"/>						
						</a>
						
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
							        <form:input path="listOfFileTypes" id="listOfFileTypes"/>
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
							        <form:input id="pickupAddressisAddressCleansed" path="shipToAddress.isAddressCleansed" />
						
						<form:input id="relatedServiceRequestedNumber" path="relatedServiceRequestedNumber" />
						<form:input id="relatedServiceRequestedRowId" path="relatedServiceRequestedRowId" />
						<form:input id="listOfFileTypes" path="listOfFileTypes" />
						<form:input id="attMaxCount" path="attMaxCount"/>
						<form:input id="templateFileLOV" path="templateFileLOV" />
						<form:input id="templateFileCount" path="templateFileCount" />
						<form:input id="templateFileName" path="templateFileName" />
					</span>
			</div><!-- infoBox columnInner rounded shadow -->
		</div><!-- columnsTwo -->
		<div class="columnsTwo">
			<div class="infoBox columnInner rounded shadow">
				<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
				<ul class="form">
					<li>
						<label for="desc2"><spring:message code="requestInfo.label.specialInstructions"/></label> 
						<span class="multiLine"><form:textarea id="specialInstruction" rows="3" path="specialInstruction" maxlength="255"/></span>
						<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.specialInstruction"/>" >
						<em class="note"><spring:message code="requestInfo.label.note"/></em>
					</li>
					<!--<li>
						<label><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
						<span class="multiLine">${hardwareDetailPageForm.defaultSpecialInstruction}</span>
					</li> -->
				</ul>
			</div><!-- infoBox columnInner rounded shadow -->
		</div><!-- columnsTwo -->
	</div><!-- portletBlock -->
	<!-- Shipping & Billing Information End -->
	<!-- Payment Details Start -->	
	
	<c:if test="${sessionScope.hardwareFinalFlags['finalCreditFlag'] == 'false'}"> 
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
	<!-- Payment Details End -->
	<!-- COVERED SERVICES BLOCK STARTS -->
        
        <div class="portletBlock">
          <div class="columnsOne">
            <div class="infoBox columnInner rounded shadow">
              <h4><spring:message code="requestInfo.heading.coveredService"/></h4>
              <ul class="form">
                <li>
                  <label for="coveredCheck"><spring:message code="requestInfo.label.coveredService"/></label>
                  <span class="width-100per">
                  <form:checkbox id="coveredCheck" path="installationOnlyFlag" onclick="showTemplate();"/>
                  <%-- Changes for MPS 2.1 7895 --%>
                  <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="hardwareDetail.installFlag.tooltip"/>" ></span>
                  
                 	
                 </li>
                 </ul>
                 <div id="downloadTemp" style="display:none;">
                 		<div class="div-style64">
                 		<button type="button" class="button positionShift iePositionShift" onClick="downloadHWInstallTemplate()"><spring:message code="requestInfo.hardwareInstall.downloadTemplate"/></button>
                 		</div>
                 		<div class="div-style65"><em class="note"><spring:message code="requestInfo.hardwareInstall.downloadTemplate.note"/></em>
                 		</div>
                 		<div class="clear-both"></div>
                 </div> 
                  <%-- Ends Changes for MPS 2.1 7895 --%>
              
            </div>
          </div>
        </div>
       
        <!-- COVERED SERVICES BLOCK ENDS -->
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
		<button class="button_cancel" type="button" onclick="javascript:draftHardwareOrder();"><spring:message code="button.saveAsDraft"/></button>
		<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
        <button type="button" class="button" onclick="javascript:submitHardwareOrder();"><spring:message code="button.continue"/></button>			
	</div><!-- buttonContainer -->
	</form:form>
	<input type="hidden" id="fileCount" />
	<input type="hidden" id="templateFileCheck" />
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
var attachTemplateCheck;
var pageType="hardwareDetails";
jQuery("#templateFileCheck").val("false");
<%-- Changes CI 7 BRD 12 --%>
isHardwarePage="true";
ajaxSuccessFunction=function updateRequest(){
	showOverlay();
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+populateURL("hardwareorder",[],[]);					
}

<%-- Ends Changes CI 7 BRD 12 --%>
var dialog;
var linkId;
var draftConfirmation = "${draftConfirmation}";
var dialog;
//alert('draftConfirmation has any value '+draftConfirmation);
if(draftConfirmation == "draftConfirmation"){
	//alert('Lets call here the popup');
	popupDialogDraft();
	
}else{
	//alert('From asset list page so hiding the secondary contact');
	//hideAddiContacts();
	document.getElementById("modaldialogbox").style.display = 'none';
}

jQuery(function() {
   // jQuery("h4.expand").toggler();
});


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
	var currentURL = window.location.href;
//	Change Account Link Hide/Show for CI-7 Defect #12274
if(currentURL.indexOf('/partner-portal') == -1)
  {	
	  jQuery('#changeaccount').show();
  }
else
	{
	jQuery('#changeaccount').hide();
	}
	if(jQuery('#attachmentDescription').val() != '' && jQuery('#attachmentDescription').val() != null){
		jQuery('#showAttachment').show();
	}
	
	if(jQuery('#coveredCheck').attr('checked') == true){
		jQuery("#downloadTemp").show();
	}else{
		jQuery("#downloadTemp").hide();
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
	if((document.getElementById("pickupAddressdistrict").value!='' && document.getElementById("pickupAddressdistrict").value!=' ') && document.getElementById("pickupAddressdistrict").value != null){
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
	if((document.getElementById("pickupAddressdistrict").value=='' || document.getElementById("pickupAddressdistrict").value==' ') || document.getElementById("pickupAddressdistrict").value == null){
		jQuery("#pickupAddressRegionLB").hide();
	}
	if(document.getElementById("pickupAddressLine2").value=='' || document.getElementById("pickupAddressLine2").value == null){
		jQuery("#pickupAddressLine2Lbl").hide();
	}
	//Added for MPS 2.1 end
	jQuery('#preConf').show();
	jQuery('#accessories').show();
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



<c:if test="${Error != null}">
jQuery('#taxValidationErrors').show();	
</c:if>

if(document.getElementById("errors")!= null){
	//alert('Check if there are error in the javascript method');
	var divText = document.getElementById("errors").innerHTML;
	//alert('div text is '+divText);
	jQuery('#validationErrors',window.parent.document).append(divText);
	window.parent.document.getElementById("validationErrors").style.display = 'block';
	window.parent.scrollTo(0,0);
}
function submitHardwareOrder(){
	jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
	document.getElementById("jsValidationErrors").innerHTML = '';
	document.getElementById("jsValidationErrors").style.display = 'none';
	document.getElementById("validationErrors").innerHTML = '';
	document.getElementById("validationErrors").style.display = 'none';
	var text_addtnlDescription;
	var length_addtnlDescription;
	text_addtnlDescription=jQuery('#addtnlDescription').val();
	length_addtnlDescription=text_addtnlDescription.length;
	var tableBundlesDetailNum=0;
	<c:if test="${not empty hardwareDetailPageForm.bundleList}">
	var tableBundlesDetail = document.getElementById('bundlesTab');
	tableBundlesDetailNum = tableBundlesDetail.rows.length;
	</c:if>
	var tableAccessoriesDetailNum=0;
	<c:if test="${not empty hardwareDetailPageForm.accessoriesList}">
	var tableAccessoriesDetail = document.getElementById('accessoriesTab');
	tableAccessoriesDetailNum = tableAccessoriesDetail.rows.length;
	</c:if>
	
	var validationFlagPopup = true;
	<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
	document.getElementById('errorMsgPopup_CatalogDtl').style.display = "none";
	document.getElementById('errorMsgPopup_CatalogDtl').innerHTML = "";
	if(jQuery('#pickupAddressLine1').val()==null || jQuery('#pickupAddressLine1').val()=='' ){
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="requestInfo.hardwareDetail.shipToAddress.mandatory"/></strong></li>');
		validationFlagPopup = false;
	}
	
	if(tableBundlesDetailNum==0 && tableAccessoriesDetailNum==0){
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.catalogDetail.emptyTable"/></strong></li>');
		validationFlagPopup = false;
	}
	

	if(jQuery('#specialInstruction').val().length > 255) {
		//alert('descData.length > 2000');
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.spclInstru.length.errorMsg"/>&nbsp;'+jQuery('#specialInstruction').val().length+'</strong></li>');
		//jQuery('#errorMsgPopup_CatalogDtl').append('&nbsp;'+jQuery('#specialInstruction').val().length+'</strong></li>');
		validationFlagPopup = false;
	}
	<%--  Defect 10776 --%>
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
	/* if(!descValidate(jQuery('#addtnlDescription').val(),"errorMsg")) {
		validationFlagPopup = false;
		jQuery('#addtnlDescription').addClass('errorColor');
	} */
	<%-- Ends Defect 10776 --%>
	if(jQuery('#coveredCheck').is(':checked')){
		var check =jQuery('#templateFileCheck').val();
		if(check=="false"){
		jQuery('#errorMsgPopup_CatalogDtl').append('<li><strong><spring:message code="validation.installCoveredAttachment.empty"/></strong></li>');
		validationFlagPopup = false;
			}
		}
	
	if (!validationFlagPopup)
	{	
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		document.getElementById('errorMsgPopup_CatalogDtl').style.display = "block";
		jQuery(document).scrollTop(0);
		//alert("block error");
		return false;
		
	}
	document.getElementById("pageSubmitType").value = 'confirmOrderRequest';
	jQuery("#hardwareDetailPageForm").submit();
}

function draftHardwareOrder(){
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
	if (!validationFlag_Popup)
	{	
		<%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
		document.getElementById('errorMsgPopup_CatalogDtl').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
	}
	document.getElementById("pageSubmitType").value = 'draftOrderRequest';
	jQuery("#hardwareDetailPageForm").submit();
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
		//alert('addressId is '+addressId);
		//alert('addressProvince '+addressProvince);
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
		jQuery("#pickupAddressofficeNumberLbl").html(officeNumber)		
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
		if(addressState !='' && addressState !=' ' && addressState != null){
			jQuery("#pickupAddressStateLbl").html(',&nbsp;'+addressState);
			jQuery("#pickupAddressStateLbl").show();
			}
		if(addressProvince !='' && addressProvince !=' ' && addressProvince != null){
			jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+addressProvince);
			jQuery("#pickupAddressProvinceLbl").show();
			}
		// region changed to district for MPS 2.1
		if(district !=' ' && district !=''  && district != null){
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
		jQuery('#errorMsgPopup_CatalogDtl').hide();
		jQuery('#diffAddressLink').html("<spring:message code="requestInfo.link.selectADifferentAddress"/>").attr('title',"<spring:message code="requestInfo.link.selectADifferentAddress"/>");
		
		<%--ENDS Changes for defect 7896 MPS 2.1 --%>
		<%--Changes for MPS 2.1 --%>
		if(addressId==null || addressId=="")
			jQuery('#pickupAddressisAddressCleansed').val("false");
		else
			jQuery('#pickupAddressisAddressCleansed').val("true");
		
		<%--ENDS Changes for MPS 2.1 --%>
		
		addressChangeReq=true;
		if(linkId=="diffAddressLink")
		{
			<%--Changes for Phase 2.1--%>
			//call to addConsumablesAddress in the commonAsset.js
			addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
					addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
			<%--Ends--%>
		}
		closeDialog();
	}
	


	function onCancelClick() {
		var cancelAction = 'cancelAction';
		var relatedServiceRequestNumber = document.getElementById("relatedServiceRequestedNumber").value;
		var url = "<portlet:renderURL></portlet:renderURL>&pageFrom="+cancelAction+ "&relatedServiceRequestNumber=" + relatedServiceRequestNumber;
		//alert('url is '+url);
		window.location.href = url;
	};

	function deleteHardwareDetails(){
		jConfirm("<spring:message code='requestInfo.message.sessionClearConfirmation'/>", "", function(confirmResult) {
			if(confirmResult){
				var relatedServiceRequestNumber = document.getElementById("relatedServiceRequestedNumber").value;
				var url = "<portlet:renderURL></portlet:renderURL>&relatedServiceRequestNumber=" + relatedServiceRequestNumber;
				//alert('url is '+url);
				window.location.href = url;
			}
		});
	}
	
	function showTemplate(){
		if(jQuery('#coveredCheck').is(':checked')){
			jQuery("#downloadTemp").show();
			jQuery('#showAttachment').show();
		}else{
			jQuery("#downloadTemp").hide();
		}
	};
	
	function downloadHWInstallTemplate(){
		window.location="${downloadXLSFile}";
	};
	//Added for MPS 2.1 Extra fields for address cleansing
	function addRestFieldsOfCleanseAddress(){
		jQuery('#pickupAddressisAddressCleansed').val("true");
		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
			jQuery("#pickupAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
		<%}%>
		jQuery("#pickupAddressofficeNumberLbl").html(jQuery("#pickupAddressofficeNumber").val());
		//Added for MPS 2.1
		if(jQuery('#pickupAddresscounty').val()!='' && jQuery('#pickupAddresscounty').val()!=' ' && jQuery('#pickupAddresscounty').val()!=null){
		jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+jQuery('#pickupAddresscounty').val());
		jQuery("#pickupAddresscountyLbl").show();
		}
		if(jQuery('#pickupAddresscounty').val() =='' || jQuery('#pickupAddresscounty').val() ==' ' || jQuery('#pickupAddresscounty').val() == null){
			jQuery("#pickupAddresscountyLbl").hide();
			}
		//jQuery("#pickupAddressdistrictLbl").html(jQuery('#pickupAddressdistrict').val());
		//END
		}	
	//Ends
	function removeValidation(){
		
		jQuery('#poNumber').removeClass('errorColor');
		jQuery('#errorMsgPopup_CatalogDtl').html("");
		jQuery('#errorMsgPopup_CatalogDtl').hide();
	 }
</script>