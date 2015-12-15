<% request.setAttribute("subTabSelected","fleet-management"); %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<jsp:include page="../../common/validationMPS.jsp" />
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<jsp:include page="../../common/mapViewPopup.jsp"></jsp:include>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js"></script>
<script type="text/javascript"><%@ include file="../../../../js/showCoordinates.js"%></script>
<style type="text/css"><%@ include file="/WEB-INF/css/combo/dhtmlxcombo.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style type="text/css">
       .ie7 .buttonOK {
               behavior: url(/LexmarkOPSPortal/WEB-INF/css/PIE.htc) !important;                
        }
        .buttonOK{position:static!important;}
       
</style>
<![endif]-->
<!--[if IE 8]>
	
<style type="text/css">
       .ie8 .buttonOK {
               behavior: url(/LexmarkOPSPortal/WEB-INF/css/PIE.htc) !important;                
        }
         .buttonOK{position:static!important;}
       
</style>
<![endif]-->

<style type="text/css">
ul.form img.dhx_combo_img {
	margin-right: -1px !important;
} /* Added for combo select image */
</style>

<portlet:actionURL var='attachDocumentAction'>
	<portlet:param name='action' value='attachDocument' />
</portlet:actionURL>
<portlet:actionURL var='removeDocumentAction'>
	<portlet:param name='action' value='removeDocument' />
</portlet:actionURL>

<portlet:actionURL var='confirmRequestAction'>
	<portlet:param name='action' value='confirmRequest' />
</portlet:actionURL>
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
	<c:if test="${sessionScope.hardwareCurrentDetails['paymentType'] == 'Ship and Bill'}">
		<portlet:param name="addressCleansedFlag" value="true"></portlet:param>
	</c:if>
</portlet:renderURL>
<portlet:resourceURL var="stateListPopulateURL" id="getStateList"/>
<portlet:resourceURL var="cityListPopulateURL" id="getCity"/>
<portlet:resourceURL var="siteBuildingListPopulateURL" id="getSiteBuildingZone"/>
<portlet:resourceURL var="buildingListPopulateURL" id="getBuilding"/>
<portlet:resourceURL var="floorURL" id="getFloor"/>
<portlet:resourceURL var="siteURL" id="getSite"/>
<portlet:resourceURL var="zoneURL" id="getZone"/>
<portlet:resourceURL var="getAllLocationURL" id="getAllLocation"/>


<div id="dialogAddress" style="display: none;"></div>    
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
      <h2 class="step"><spring:message code="requestInfo.cm.heading.mapsRequest"/></h2> </div>
      
     
      <c:if test="${changeAccountLink}">
      <div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
    	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
        	</strong>
        	</h3>
        	</div>
        	</c:if>
        	
        	
       	   <!-- JS Validation Errors displayed in the below html:statusBanner -->
           <div id="jsValidationErrors" class="error" style="display: none;"></div>
            <!-- Validation Errors displayed in the below div -->
            <spring:hasBindErrors name="fileUploadForm">
				<div class="error" id="errors">
					<c:forEach var="error" items="${errors.allErrors}">
						<li><strong><spring:message code="${error.code}"/></strong></li>
					</c:forEach>
				</div>	
			</spring:hasBindErrors>
          	<div id="validationErrors" class="error" style="display: none;"></div><!--Div used for file upload validation errors  -->
          	
          	<!-- Exceptions displayed in the below div -->
          	 <c:if test="${exceptionList != null}">
       			<div class="error" id="dispalyExceptions">
	       			<c:forEach items="${exceptionList}" var="entry">
			       		<li><strong>${entry}</strong></li>		       		
			     	</c:forEach>
      			</div>
          	</c:if>
          	<div id="exceptionsDiv" class="error" style="display: none;"></div>	<!--Div used for file upload exception  -->
<div class="main-wrap">
	<div class="content">
			
		<!-- MAIN CONTENT BEGIN -->
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.heading.mapsRequest"/><span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span></h3>
 		  <form:form id="mapsRequestFormId" commandName="mapsRequestForm" method="post" action="${createRequestAction}" enctype="multipart/form-data" >
		  <%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
			Additional Information of the Change Mgmt Request End--%>
			<div><p><spring:message code="requestInfo.label.contactMessageMaps"/> 
			</p></div>
			<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
	        
			  
			 <hr class="separator" />
			 <span style="display: none">
				<form:input path="prevSRNumber" /> 
			 </span> 
			  		
					
					<div class="columnsTwo">
					<div class="columnInner">
						<ul class="form">

							<li><label for="rqstDesc"> <span class="req">*</span>
								<spring:message code="requestInfo.label.notesMaps" /></label> <span>
									<form:textarea path="notesOrComments" id="notesOrComments"
										rows="3" maxlength="2000" />
							</span></li>
						</ul>
						<div>

							<span><form:checkbox path="moveAsset" id="moveAsset"
									onclick="wantMove();" /> </span> <label><spring:message
									code="requestInfo.label.moveAsset" /></label>
						</div>
						<div id="contactDiv" style="display: none;">
							<span><form:checkbox path="moveContactSelect"
									id="moveContactSelect" onclick="mapsMoveContactSelect();" /> </span> <label><spring:message
									code="requestInfo.label.moveContactSelect" /></label>
							</li>
						</div>


					</div>
					<div class="infoBox columnInner rounded shadow"
						id="mapsRequestContact" style="display: none;">
						<h4 id="mapContactHeading"
							title='<spring:message code="requestInfo.link.selectContactForMove"/>'>
							<spring:message code="requestInfo.heading.ContactForMapsRequest" />
						</h4>

						<ul class="form wordBreak">
							<li><label><spring:message
										code="requestInfo.label.name" /></label> <span
								id="mapsRequestLastNameLabel"></span></li>
							<li><label><spring:message
										code="requestInfo.label.phoneNumber" /></label> <span
								id="mapsRequestWorkPhoneLabel"></span></li>
							<li><label><spring:message
										code="requestInfo.label.emailAddress" /></label> <span
								id="mapsRequestEmailAddressLabel"></span></li>
						</ul>
					</div>
					<!-- Hidden fields to bind the maps Request contact data with form -->
					<span style="display: none"> <form:input
							id="mapsRequestContactId"
							path="serviceRequest.mapsRequestContact.contactId" /> <form:input
							id="mapsRequestFirstName"
							path="serviceRequest.mapsRequestContact.firstName" /> <form:input
							id="mapsRequestLastName"
							path="serviceRequest.mapsRequestContact.lastName" /> <form:input
							id="mapsRequestWorkPhone"
							path="serviceRequest.mapsRequestContact.workPhone" /> <form:input
							id="mapsRequestAltPhone"
							path="serviceRequest.mapsRequestContact.alternatePhone" /> <form:input
							id="mapsRequestEmailAddr"
							path="serviceRequest.mapsRequestContact.emailAddress" /> <form:input
							id="mapsRequestUpdateContactFlag"
							path="serviceRequest.mapsRequestContact.updateContactFlag" /> <form:input
							id="mapsRequestNewContactFlag"
							path="serviceRequest.mapsRequestContact.newContactFlag" />
					</span>

				</div>
				<div class="columnsTwo">
					<div class="infoBox columnInner rounded shadow">
						<h4>
							<span class="req">*</span><a href="${addressListPopUpUrl}"
								class="lightboxLarge"
								title="<spring:message code="requestInfo.link.selectAnAddress"/>"
								id="diffAddressLink" onclick="return popupAddress(id);"> <spring:message
									code="requestInfo.link.selectAnAddress" />
							</a>

							<spring:message code="requestInfo.heading.mapchangerequest" />
						</h4>
						<ul class="roDisplay">
							<li>
								<div id="pickupStoreFrontNameLbl"></div>
								<div id="pickupAddressLine1Lbl"></div>
								<div id="pickupAddressofficeNumberLbl"></div>
								<div id="pickupAddressLine2Lbl"></div>
								<div id="city_state_zip_popup_span">
									<div style="display: inline;" id="pickupAddressCityLbl"></div>
									<div style="display: inline;" id="pickupAddresscountyLbl"></div>
									<div style="display: inline;" id="pickupAddressStateLbl"></div>
									<div style="display: inline;" id="pickupAddressProvinceLbl"></div>
									<div style="display: inline;" id="pickupAddressRegionLB"></div>
								</div>
								<div id="pickupAddressPostCodeLbl"></div>
								<div id="pickupAddressCountryLbl"></div>
							</li>
						</ul>
						<br/>
						<h4 id="changeHeader" style="display: none;">Change Request</h4>
						<ul class="form division1">
							<li id="lbsAD" style="display: none;">
								<label>
									<span class="req">*</span>LBS Address :
								</label> 
								<select	id="lbsAddressFlagSelect" class="smallSelect">
										<option value="">Select</option>
									<c:forEach var="isLbsAddress" items="${isLbsAddress}">
                                    	<option value="${isLbsAddress.key}">${isLbsAddress.value}</option>
                                    </c:forEach>
							    </select>
							</li>
							
							<li id="lod" style="display: none;">
								<label><spring:message code="lbs.label.addresslod"/> :</label>
									 <select id="lodAddress" class="smallSelect">
										<option value="">Select</option>
											<c:forEach var="lbsAddressLOD" items="${lbsAddressLOD}">
                                  				 <option value="${lbsAddressLOD.key}">${lbsAddressLOD.value}</option>
                                    		</c:forEach>
									</select>
							</li>
							<li class="borderBelow" style="display: none;"></li>
						</ul>
						<div id="selectLocalAddress1" style="display: none;">
							<ul class="form division1">
								<li><label for="building"><spring:message
											code="requestInfo.addressInfo.label.building" /> </label> <span><input
										id="physicalLocation1" class="w100" maxlength="100" /></span></li>
								<li><label for="floor"><spring:message
											code="requestInfo.addressInfo.label.floor" /> </label> <span><input
										id="physicalLocation2" class="w100" maxlength="100" /></span></li>
										
								<%-- <li><label for="office"><spring:message
											code="requestInfo.addressInfo.label.office" /> </label> <span><input
										id="physicalLocation3" class="w100" maxlength="100" /></span></li> --%>
							</ul>
						</div>
						<div id="selectLocalAddress" style="display: none;">
							<ul class="form division1">
								<li><label for="building"><span class="req">*</span>Building:</label>
									<select id="bldng" class="bigInputs"></select><input type="text" id="building1" class="bigInputs"
									style="display: none;" /></li>
								<li><label for="floor"><span class="req">*</span>Floor:</label>
									<select id="flr" class="bigInputs"></select><input type="text" id="floor1" class="bigInputs"
									style="display: none;" /></li>
									
								<%--<li style="display:none;"><label for="office">Office:</label> <select id="campus"
									class="bigInputs"></select><br/></li> --%>
									
							<li class="borderBelow"></li>
							</ul>
						</div>

						<ul class="form division1">
							<li id="lod1" style="display: none;"><label><spring:message code="lbs.label.floorlod"/> :</label>
								<select id="lodFloor"
								class="smallSelect">
									<option value="">Select</option>
									<c:forEach var="lbsFloorLOD" items="${lbsFloorLOD}">
                                   <option value="${lbsFloorLOD.key}">${lbsFloorLOD.value}</option>
                                    </c:forEach>
							</select></li>
							<li id="viewGrid" style="display: none;">
								<a href="#" id="addressGridLink">View Grid</a>
								<div id="addressXYLblDiv">
									<label id="addressXYLbl">Grid X/Y : </label><label id="addressCoords"></label>
								</div>
							</li>
						</ul>

						<span style="display: none;"> <form:input
								path="serviceRequest.installAddress.physicalLocation1"
								id="bldng1" /> <form:input
								path="serviceRequest.installAddress.physicalLocation2" id="flr1" />
							<form:input path="serviceRequest.installAddress.zoneName"
								id="zone1" /> <form:input id="campus1"
								path="serviceRequest.installAddress.campusName" />
						</span>
					</div>
					<!-- Hidden fields to bind the address data with form -->
					<span style="display: none">
						<form:input id="pickupAddressId" path="serviceRequest.installAddress.addressId" />
						         	<form:input id="pickupStoreFrontName" path="serviceRequest.installAddress.storeFrontName" />
							     	<form:input id="pickupAddressName" path="serviceRequest.installAddress.addressName" />
							     	<form:input id="pickupAddressLine1" path="serviceRequest.installAddress.addressLine1" /> 
							        <form:input id="pickupAddressLine2" path="serviceRequest.installAddress.addressLine2" />
							        <form:input id="pickupAddressCity" path="serviceRequest.installAddress.city" />
							        <form:input id="pickupAddressState" path="serviceRequest.installAddress.state" />
							        <form:input id="pickupAddressProvince" path="serviceRequest.installAddress.province" />
							        <form:input id="pickupAddressPostCode" path="serviceRequest.installAddress.postalCode" />
							        <form:input id="pickupAddressCountry" path="serviceRequest.installAddress.country" />
							        <form:input path="listOfFileTypes" id="listOfFileTypes"/>
							        
							        <form:input	id="physicalLocation1h" path="serviceRequest.installAddress.buildingId"  />
						<form:input	id="physicalLocation2h" path="serviceRequest.installAddress.floorId"  />
						<form:input	id="physicalLocation3h" path="serviceRequest.installAddress.physicalLocation3"  />
						<form:input	id="zoneh" path="serviceRequest.installAddress.zoneId"   />
						<form:input	id="campush" path="serviceRequest.installAddress.campusId"   />
							        
									<!-- Below Fields for Cleansing address Don't change the
									input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
									 -->
									
									<form:input id="pickupAddresscounty" path="serviceRequest.installAddress.county" />
							        <form:input id="pickupAddressofficeNumber" path="serviceRequest.installAddress.officeNumber" />
							        <form:input id="pickupAddressstateCode" path="serviceRequest.installAddress.stateCode" />
							        <form:input id="pickupAddressstateFullName" path="serviceRequest.installAddress.stateFullName" />
							        <form:input id="pickupAddressdistrict" path="serviceRequest.installAddress.district" />
							        <form:input id="pickupAddressregion" path="serviceRequest.installAddress.region" />
							        <form:input id="pickupAddresslatitude" path="serviceRequest.installAddress.latitude" />
							        <form:input id="pickupAddresslongitude" path="serviceRequest.installAddress.longitude" />
							        <form:input id="pickupAddresscountryISOCode" path="serviceRequest.installAddress.countryISOCode" />
							        <form:input id="pickupAddresssavedErrorMessage" path="serviceRequest.installAddress.savedErrorMessage" />
							        <form:input id="pickupAddressisAddressCleansed" path="serviceRequest.installAddress.isAddressCleansed" />
						<form:input	id="installLBSAddressFlag" path="serviceRequest.installAddress.lbsAddressFlag" />
						
						
						<form:input	id="isRequestForBuilding" path="serviceRequest.installAddress.isRequestForBuilding" />
						<form:input	id="isRequestForFloor" path="serviceRequest.installAddress.isRequestForFloor" />
						
						<form:input	id="notesForNewBuilding" path="notesForNewBuilding" />
						
						<form:input	id="lodAddressInput" path="serviceRequest.installAddress.levelOfDetails" />
						<form:input	id="lodFloorInput" path="serviceRequest.installAddress.floorLevelOfDetails" />
						
						<form:input
						id="addressCoordinatesXPreDebriefRFV" path="serviceRequest.installAddress.coordinatesXPreDebriefRFV" />
						<form:input
						id="addressCoordinatesYPreDebriefRFV" path="serviceRequest.installAddress.coordinatesYPreDebriefRFV" />
						
					
						
						
					</span>
			</div><!-- infoBox columnInner rounded shadow -->
		</div>
				
				
		</div>
		
			  
			  <span style="display: none">
					<form:input path="listOfFileTypes" id="listOfFileTypes"/>
					<form:input path="attMaxCount" id="attMaxCount"/> 
					<form:input path="chlTempMaxCount" id="chlTempMaxCount"/>
					<form:input path="flowType" id="flowType"/> 
					<form:input path="requestedFrom" id="requestedFrom"/> 
			  </span>
	          </form:form>
			  
				<p class="inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
				<span id="masstemplatespan" style="display: none;"><p><spring:message code="requestInfo.label.download"/> <a href="javascript:void(0);" onclick="openTemplate('massUploadTemplate',null);"> <spring:message code="requestInfo.label.massUploadTemplate"/></a> <spring:message code="requestInfo.label.fillDetails"/></p></span>
				<span id="hwMasstemplatespan" style="display: none;"><p><spring:message code="requestInfo.label.download"/> <a href="javascript:void(0);" onclick="openTemplate('hwMassUploadTemplate',null);"> <spring:message code="requestInfo.label.massUploadTemplate"/></a> <spring:message code="requestInfo.label.fillDetails"/></p></span>
				<div class="columnInner">
			  
			  
			  <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" action="${attachDocumentAction}" enctype="multipart/form-data" >
			  
			    	<span style="display: none">
					<form:input path="pageType" id="pageType"/> 
				</span>	  	
			  		
			 	<nobr>
					<input type="text" id="txtFilePath" readonly="true" style="width:320px; height:20px; margin-left:0px" />&nbsp;
					<form:input type="file" size="1" id="file" class="requestsUploader" path="fileData" onchange="setPathToTextBox();" onclick="clearAttachmentForm();"/>
					<button class="button_cancel" type="button" id="btnBrowse" style="cursor:pointer;"><spring:message code="button.browse"/></button>&nbsp;&nbsp;					
					<button class="button_cancel" type="button" id="btnUpload" style="cursor:pointer;" onclick="uploadFile();"><spring:message code="button.attach"/></button>
				</nobr>
						 	
					<p class="note"><spring:message code="requestInfo.label.mapsAttachmentInstruction1"/>
					<spring:message code="requestInfo.label.mapsAttachmentInstruction2"/></p>
									
					<iframe id="upload_target" name="upload_target" style="width:0px;height:0px;border:0px solid #fff;">
					
					</iframe>
					
						
						
						
						  <div id="fileListDiv" class="wHalf displayGrid columnInnerTable rounded shadow">
							
							<table id="fileListTable">
							</table>
							</div>
							<div id="test"></div>
							 <div class="lineClear"></div>							
						 
							  
				
				
					
			  </div>
			  
			  </form:form>
 			  <%String currURL=PortalUtil.getCurrentCompleteURL(request);
 			  boolean isFleetRequest=currURL.indexOf("fleet=true")!=-1; %>
 						
			  <div class="buttonContainer">
			 <%--  <c:if test="${fleetManagementFlag != true }">--%>
			 <%if(isFleetRequest){ %>
	            <button type="button" class="button_cancel buttonOK" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
	           <%--  </c:if>
	              <c:if test="${fleetManagementFlag == true }">--%>
	              <%}else{ %>
	              <button type="button" class="button_cancel buttonOK" onclick="javascript:redirectToHistory('others');"><spring:message code="button.cancel"/></button>
	            
	           <%-- </c:if>--%>
	           <%} %>
	            <button type="button" id="btnContinue" class="button buttonOK" onclick="submitRequest();" ><spring:message code="button.continue"/></button>
	          </div>
		  <input type="hidden" id="fileCount" />
        </div>
        <!-- MAIN CONTENT END -->	
		</div>
</div>

<script type="text/javascript">
//var fileNameVar = null;

function urlParams(){
	this.htmlInputIds=["bldng","zone","flr","campus"];
	this.defaultMessages=["<spring:message code='lbs.label.building'/>","<spring:message code='lbs.label.zone'/>","<spring:message code='lbs.label.floor'/>",""];//replace with spring messages
	this.paramNames=["ctry","state","cty","bldng","","flr","extra"];
	this.paramNames1=["bldng","","flr"];
	this.setDefaultHTMl=function(){
		for(var i=0;i<this.htmlInputIds;i++){
			$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
		}
	};
	this.setDefaultHTMlAt=function(id){
		
		var index=$.inArray(id,this.htmlInputIds);
		$('#'+id).html('<option value="">'+this.defaultMessages[index]+'</option>');
	};
	this.clearHtmlAfter=function(htmlId){
		
		var index=$.inArray(htmlId,this.htmlInputIds);
		if(index!=-1){
			for(var i=(index+1);i<this.htmlInputIds.length;i++){
				$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
			}
		}
	};
	this.setDropParamsToObj=function(){
		for(var i=0;i<this.paramNames1.length;i++){
			if(this.paramNames1[i]!=""){
				this[this.paramNames1[i]]=$('#'+this.htmlInputIds[i]).val();				
			}
			
		}
	};
	this.disableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).attr('disabled','disabled');
		}
	};
	this.enableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).removeAttr('disabled');
		}
	};
}
var urlParamsObj=new urlParams();

$('#lbsAddressFlagSelect').change(function(){

	 setDefaultValues(null);

	  var lbsAddressFlagSelect = $("#lbsAddressFlagSelect").val();     
	  if(lbsAddressFlagSelect=="Y")
			{
				$("#installLBSAddressFlag").val("true");
			}
      else
			{
				$("#installLBSAddressFlag").val("false");	
			}
	
	   if(lbsAddressFlagSelect=="Y")
			{
        		lodCheck();
			}
	   else
			{
				loadBuildingFloorText();
				jQuery("#lbsAD,.borderBelow").show();
				jQuery("#viewGrid").hide();
				clearGridValues();
			}
	
});


$('#lodAddress').change(function(){
	   
	  setDefaultValues("lodchange");
	  lodCheck();		
});

$('#lodFloor').change(function(){

	if($('#lodFloor').val().toLowerCase()=== "grid level")
		{
			
			$("#viewGrid").show();
		}
	else
		{
			$("#viewGrid").hide();
			clearGridValues();
			
		}
		
});


function lodCheck()
{

	jQuery("#bldng").val("");
	jQuery("#physicalLocation1h").val("");
	jQuery("#physicalLocation2h").val("");
	jQuery("#physicalLocation3h").val("");
	jQuery("#physicalLocation1").val("");
	jQuery("#physicalLocation2").val("");
	jQuery("#physicalLocation3").val("");
	jQuery("#notesForNewBuilding").val("");
	jQuery("#office").val("");
	jQuery("#building1").hide();
	jQuery("#floor1").hide();
	jQuery("#viewGrid").hide();
	jQuery("#lodFloor").val("");
	
	
	var lodAddress= $("#lodAddress").val();
	
	    if(lodAddress.toLowerCase()=="")
	     {
	      	loadBuildingFloorText();
	      	jQuery("#lbsAD,.borderBelow").show();
	      	jQuery("#lod").show();
	     }
	    else if(lodAddress.toLowerCase() === "street level")
		{
			loadBuildingFloorText();
			jQuery("#lbsAD,.borderBelow").show();
			jQuery("#lod").show();
		}
	    else if(lodAddress.toLowerCase() === "floor level" || lodAddress.toLowerCase() === "grid level")
		{
			loadBuildingFloorDropDown();
			var addressId=document.getElementById("pickupAddressId").value;
        	loadCoutrySateCitySiteBuilding(addressId);
        	jQuery("#lodFloor").val(lodAddress);
        	jQuery('#lodFloor').attr('disabled', true);
        
        if(lodAddress.toLowerCase()=== "grid level")
        	{
        		jQuery("#viewGrid").show();
        	}
		}
		else if(lodAddress.toLowerCase() === "mix - see floor")
		{
			
			loadBuildingFloorDropDown();
			var addressId=document.getElementById("pickupAddressId").value;
    		loadCoutrySateCitySiteBuilding(addressId);
    		var lodFloor=$("#lodFloorInput").val();
    		jQuery('#lodFloor').attr('disabled', true);
			jQuery("#lodFloor").val(lodFloor);
	    		if(lodFloor !=undefined && lodFloor!="" && lodFloor.toLowerCase() === "grid level")
	   			 {
					jQuery("#viewGrid").show();
	   			 }
 
	}
else
	{
	loadBuildingFloorText();
	jQuery("#lbsAD,.borderBelow").show();
	}
	    
}

$('#bldng').change(function(){
	
	
	 $("#building1").hide();
	 $("#floor1").hide();
	 $("#building1").val("");
	 $("#floor1").val(""); 
     var selectedBuilding=$("#bldng").val();
       
       urlParamsObj.setDefaultHTMlAt('flr');
        $('#flr').append("<option value='requestforfloor'>Request For Floor</option>");
       urlParamsObj.setDefaultHTMlAt('campus');
	
		
	 if(selectedBuilding.toLowerCase()=="requestforbuilding")
	 {
		$("#building1").show();
		jQuery("#flr option[value='requestforfloor']").attr("selected", "selected");
		jQuery("#flr").attr('disabled',"true");
		$("#floor1").show();
		
	 }
	 else
	{ 
    	jQuery('#bldng1').val(jQuery("#bldng option:selected").text());
		jQuery('#physicalLocation1h').val(jQuery("#bldng option:selected").val());
	}

 	var addressLevelOfDetails = $.trim($("#lodAddress").val());
	if(addressLevelOfDetails.toLowerCase() === "mix - see floor")
	{
		$("#viewGrid").hide();
		clearGridValues();
		jQuery("#lodFloor").attr('disabled',true);
		jQuery('#lodFloor').val("");
		$("#lodFloorInput").val("");

		if(selectedBuilding.toLowerCase()=="requestforbuilding")
			jQuery("#lodFloor").attr('disabled',false);
	}
	
	if($(this).val()==""){
		$('#flr').attr('disabled',false);
		return;
	}
	urlParamsObj.setDropParamsToObj();	
	$('#flr').attr('disabled',true);
	$('#btnContinue').attr('disabled',true);
	getSite("buildingchange");
	getFloor("buildingchange");
	
 
});
function getFloor(p){
	
	var ctry=$('#pickupAddressCountryLbl').text();
	
	urlParamsObj["ctry"]=ctry;
	
	var state=$('#pickupAddressStateLbl').text().slice(2);
	
	var county=$('#pickupAddresscountyLbl').text().slice(2);
	
	var province=$('#pickupAddressProvinceLbl').text().slice(2);
	
	var district=$('#pickupAddressRegionLB').text().slice(2);
	
	if(state!=""){
	urlParamsObj["state"]=state+"^s";
	}
	else if(county!=""){
		urlParamsObj["state"]=county+"^c";	
	}
else if(province!=""){
urlParamsObj["state"]=province+"^p";	
	}
else if(district!=""){
urlParamsObj["state"]=district+"^d";
}
	
	var cty=$('#pickupAddressCityLbl').text();
	
	urlParamsObj["cty"]=cty;
	
	var bId=jQuery("#bldng option:selected").val();
	urlParamsObj["bldng"]=bId;
	urlParamsObj["extra"]="blank"
	
	var url=appendURLPrams("${floorURL}",urlParamsObj);
	
	$.get(url,function(response){
		if($("#bldng").val().toLowerCase()!=="requestforbuilding")
			$('#flr').attr('disabled',false);
		$('#btnContinue').attr('disabled',false);
		$('#flr').append(response);
		
		lbsFloor(p);
		
	});	
	
}

function getSite(p){
	
	var ctry=$('#pickupAddressCountryLbl').text();
	urlParamsObj["ctry"]=ctry;
	
	var state=$('#pickupAddressStateLbl').text().slice(2);
	
	var county=$('#pickupAddresscountyLbl').text().slice(2);
	
	var province=$('#pickupAddressProvinceLbl').text().slice(2);
	
	var district=$('#pickupAddressRegionLB').text().slice(2);
	
	if(state!=""){
	urlParamsObj["state"]=state+"^s";
	}
	else if(county!=""){
		urlParamsObj["state"]=county+"^c";	
	}
else if(province!=""){
urlParamsObj["state"]=province+"^p";	
	}
else if(district!=""){
urlParamsObj["state"]=district+"^d";
}
	
	
	
	var cty=$('#pickupAddressCityLbl').text();
	urlParamsObj["cty"]=cty;
	
	var bId=jQuery("#bldng option:selected").val();
	urlParamsObj["bldng"]=bId;
	urlParamsObj["extra"]="site";
	
	var url=appendURLPrams("${siteURL}",urlParamsObj);

	$.get(url,function(response){
		
		
		$('#campus').append(response);
		lbsSite(p);
		
	});	

}

$('#flr').change(function(){
	
	    $("#floor1").hide();
		$("#floor1").val("");
		
		var selectedFloor=$("#flr").val();
		if(selectedFloor.toLowerCase()=="requestforfloor")
		{
			jQuery('#floor1').show();
		}
		else
		{
			jQuery('#flr1').val(jQuery("#flr option:selected").text());
			jQuery('#physicalLocation2h').val(jQuery("#flr option:selected").val());
		}
		$("#campus option").each(function() {
           
       		if( $(this).prop('text') != "" ) { 
        		$(this).attr('selected','selected');
        	}
   		});
		jQuery('#campus1').val(jQuery("#campus option:selected").text());
		jQuery('#campush').val(jQuery("#campus option:selected").val());

		if($("#lodAddress").val().toLowerCase() === "mix - see floor")
		{ 
			var lodFloorAttr=$("#flr option:selected").attr("lod");
			jQuery('#lodFloor').val(lodFloorAttr);
			$("#viewGrid").hide();
			clearGridValues();
			
			if($("#flr option:selected").attr("lod") != undefined &&
				$.trim($("#flr option:selected").attr("lod").toLowerCase()) === "grid level")
			{
				
				$("#viewGrid").show();
			}

			if( $(this).val())
				$("#lodFloor").attr("disabled", false);
			else
				$("#lodFloor").attr("disabled", true);
		}
	
});
function getZone(p){
	var url=appendURLPrams("${zoneURL}",urlParamsObj);
	$.get(url,function(response){		
		$('#zone').append(response);
		
		
		if(p!=null){
			
			$('#zone').val(p);
		}
	});	
}
$('#zone').change(function(){
	
		jQuery('#zone1').val(jQuery("#zone option:selected").text());
		jQuery('#zoneh').val(jQuery("#zone option:selected").val());
		
});
function appendURLPrams(url,paramsObj){
	var urlT=url;
	for(var i=0;i<paramsObj.paramNames.length;i++){
		if(paramsObj[paramsObj.paramNames[i]]!=null && paramsObj[paramsObj.paramNames[i]]!="")
			urlT+="&"+paramsObj.paramNames[i]+"="+encodeURI(paramsObj[paramsObj.paramNames[i]]);
	}
	
	return urlT;
}


function loadCoutrySateCitySiteBuilding(addressId){
	
	 jQuery("#lbsAddressFlagSelect option[value='Y']").attr("selected", "selected");
	 urlParamsObj.clearHtmlAfter('bldng');
	 urlParamsObj.setDefaultHTMlAt('bldng');
	
	 $("#bldng").append("<option value='requestforbuilding'>Request For Building</option>");
	 $('#flr').append("<option value='requestforfloor'>Request For Floor</option>");
	 
	urlParamsObj=new urlParams();
	var url=appendURLPrams("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj);
	urlParamsObj.disableAll();
	$.getJSON(url,function(response){
		urlParamsObj.enableAll();	
		if(response.building =="")
			{
				$("#bldng").val("requestforbuilding");
				$("#bldng").attr("disabled",true);
				$("#building1").show();
				$("#building1").val($("#bldng1").val());
				$('#flr').val("requestforfloor");
				$("#flr").attr("disabled",true);
				$("#floor1").show();
				$("#floor1").val($("#flr1").val());
				
					if($("#lodAddress").val().toLowerCase() === "mix - see floor")
						$("#lodFloor").attr("disabled", false);
			}
		
		$('#bldng').append(response.building);
		$('#zone').append(response.zone);
		lbsInstall();
		lbsZone();
	});
}

function loadBuildingFloorText(){
	
	jQuery("#selectLocalAddress1").show();
	jQuery("#selectLocalAddress").hide();
	//jQuery("#changeHeader").hide();
	jQuery("#lod").hide();
	jQuery("#lod1").hide();
	lbsInstall();
	lbsFloor();
	lbsZone();
	lbsSite();
	
}
function loadBuildingFloorDropDown(){

	jQuery("#selectLocalAddress1").hide();
	jQuery("#selectLocalAddress").show();
	jQuery("#changeHeader").show();
	jQuery("#lbsAD,.borderBelow").show();
	jQuery("#lod").show();
	jQuery("#lod1").show();
		
}
//For LBS dropdowns


 coordinates($('#addressCoordinatesXPreDebriefRFV').val(),$('#addressCoordinatesYPreDebriefRFV').val());
if(document.getElementById("installLBSAddressFlag").value !="" && document.getElementById("installLBSAddressFlag").value !=null && document.getElementById("installLBSAddressFlag").value =="true"){
	jQuery("#lbsAddressFlagSelect").val("Y");
	if($("#lodAddressInput").val()!=null && $("#lodAddressInput").val().length >0)
		{
			jQuery("#lodAddress").val($('#lodAddressInput').val());
		}
	if($("#lodFloorInput").val()!=null && $("#lodFloorInput").val().length >0)
	{
		jQuery("#lodFloor").val($('#lodFloorInput').val());
	}
	 lodCheck();
	
	}

if(document.getElementById("installLBSAddressFlag").value =='' || document.getElementById("installLBSAddressFlag").value ==null || document.getElementById("installLBSAddressFlag").value =="false"){
	document.getElementById("installLBSAddressFlag").value="false";
	
	
	jQuery("#lbsAddressFlagSelect").val("N");
	loadBuildingFloorText();
	if(jQuery("#pickupAddressLine1").val()!="")
		{
			jQuery("#lbsAD,.borderBelow").show();
		}	
	
	}


	jQuery("#pickupStoreFrontNameLbl").html(document.getElementById("pickupStoreFrontName").value);
	document.getElementById("pickupAddressLine1Lbl").innerHTML=document.getElementById("pickupAddressLine1").value;
	
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
	if((document.getElementById("pickupAddressdistrict").value!=' ' && document.getElementById("pickupAddressdistrict").value!='') && document.getElementById("pickupAddressdistrict").value != null){
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
	//Added for MPS 2.1
	jQuery('#pickupAddressofficeNumberLbl').html(jQuery('#pickupAddressofficeNumber').val());
	//Ends
	
	
	
	//For LBS dropdowns
	function lbsInstall(){
		if(document.getElementById("bldng1").value !="" && document.getElementById("bldng1").value !=null)
		{
			
			if(jQuery("#installLBSAddressFlag").val()=="true" &&  jQuery("#lodAddress").val().toLowerCase() !="street level" && jQuery("#lodAddress").val() !="")
			{
				if(jQuery('#isRequestForBuilding').val()=="true")
				{
					jQuery("#bldng").val('requestforbuilding');
					jQuery("#flr").val('requestforfloor');
					$('#bldng').attr('disabled', true);
					$('#flr').attr('disabled', true);
					jQuery("#building1").val(jQuery("#bldng1").val())
					jQuery("#floor1").val(jQuery("#flr1").val())
					jQuery("#building1").show();
					jQuery("#floor1").show();
					jQuery('#bldng1').val(jQuery("#building1").val());
	                //jQuery('#physicalLocation1h').val(jQuery("#building1").val());
				}
				else
					{
						var bldDrop=jQuery("#bldng1").val();
						var flrDrop=jQuery("#flr1").val();
		
						var insflg=false;
				
	                    $(function() 
	                    		{
	                           		$("#bldng option").each(function()
	                           				{
	                              
	                              			 if( $(this).prop('text') == bldDrop )
	                              			 	{ 
	                            	  				$(this).attr('selected','selected');
	                            	  				insflg=true; 
	                            	  		  	}
	                           				});
	                           
	                        	   if(insflg)
	                        	   	{
				                         $('#bldng').attr('disabled', true);
				                    }
	                           			jQuery('#bldng1').val(jQuery("#bldng option:selected").text());
	                           			jQuery('#physicalLocation1h').val(jQuery("#bldng option:selected").val());
	                           			$('#flr').attr('disabled',true);
	                           			$('#btnContinue').attr('disabled',true);
	                           			getSite(null);
	                           			getFloor(null);
	                                                                      
	                        	});
	                    
	                     installAddressFlag=true;
	                  }
			}
			else
			{	
				jQuery("#physicalLocation1").val(document.getElementById("bldng1").value);
				installAddressFlag=true;
			}
		}
	}
	
	function lbsFloor(p)
	{
		
		if(document.getElementById("flr1").value !="" && document.getElementById("flr1").value !=null)
		{
			 
			if(jQuery("#installLBSAddressFlag").val()=="true" &&  jQuery("#lodAddress").val().toLowerCase() !="street level" && jQuery("#lodAddress").val() !="")
			{
				if(jQuery('#isRequestForFloor').val()=="true")
				{
					jQuery("#flr").val('requestforfloor');
			        jQuery("#floor1").val(jQuery("#flr1").val())
			        jQuery("#floor1").show();
					 $('#flr').attr('disabled', true);
					 jQuery('#flr1').val(jQuery("#floor1").val());
	                 jQuery('#physicalLocation2h').val(jQuery("#floor1").val());
				}
					
				else
				{
					var flrflg=false;
					$(function()
							{
					 			if(p!="buildingchange")
					 			{
									 $("#flr option").each(function()
											 {
												if($(this).prop('text') == jQuery("#flr1").val() ) 
												{ 
													$(this).attr('selected','selected');
													flrflg=true; 
												}
	                                         });
					
					            }   	
	                            if(flrflg)
	                            {
				                 	$('#flr').attr('disabled', true);
				                }
					 
	                            jQuery('#flr1').val(jQuery("#flr option:selected").text());
	                            jQuery('#physicalLocation2h').val(jQuery("#flr option:selected").val());
	               
	                        });
				
								installAddressFlag=true;
				
				}
			}
			else
			   {
				jQuery("#physicalLocation2").val(document.getElementById("flr1").value);
				installAddressFlag=true;
			   }
			
		}
	}
	
	
	function lbsSite(p){
		
		if(document.getElementById("campus1").value !="" && document.getElementById("campus1").value !=null){
			if(jQuery("#installLBSAddressFlag").val()=="true" &&  jQuery("#lodAddress").val().toLowerCase() !="street level" && jQuery("#lodAddress").val() !=""){
				
				var siteflg=false;
				 $(function() {
					 if(p!="buildingchange"){
					 $("#campus option").each(function() {
	                 
	                 if( $(this).prop('text') == jQuery("#campus1").val() ) { $(this).attr('selected','selected'); }
	             });
	                		 }
	                   jQuery('#campus1').val(jQuery("#campus option:selected").text());
		         	 jQuery('#campush').val(jQuery("#campus option:selected").val());
	                
	             });
				
				installAddressFlag=true;
			}
			}
		}
	
	
	if(document.getElementById("physicalLocation3h").value !="" && document.getElementById("physicalLocation3h").value !=null){
		if(jQuery("#installLBSAddressFlag").val()=="true"){
			jQuery("#office").val(document.getElementById("physicalLocation3h").value);
			installAddressFlag=true;
		}else{
			jQuery("#physicalLocation3").val(document.getElementById("physicalLocation3h").value);
			
			installAddressFlag=true;
		}
		
		}
		function lbsZone(){
		if(document.getElementById("zone1").value !="" && document.getElementById("zone1").value !=null){
			var zoneDrop=jQuery("#zone1").val();
			var zonflg=false;
                       $(function() {
                           $("#zone option").each(function() {
                              
                               if( $(this).prop('text') == zoneDrop ) { $(this).attr('selected','selected');zonflg=true; }
                           });
                      
                     
                    	  if(zonflg){
			                        		 $('#zone').attr('disabled', true);
			                        	 }
                    	  
                       jQuery('#zone1').val(jQuery("#zone option:selected").text());
                       jQuery('#zoneh').val(jQuery("#zone option:selected").val());
                     
                       });
				installAddressFlag=true;
				
			}
	}

	
	
var contactListType="";
var combo = "";



function uploadFile() {
	
	var fileCount = document.getElementById("fileCount").value;
	var validFlag = 1;
	var errorList = "";

	var listOfFileTypes = document.getElementById("listOfFileTypes").value;
	var maxAttCount = document.getElementById("attMaxCount").value;
	var fileNameLocal = document.getElementById("txtFilePath").value;
	var fileExt = fileNameLocal.substring(fileNameLocal.lastIndexOf(".")+1, fileNameLocal.length).toLowerCase();
	
	if(fileNameLocal == ""){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		
		errorList ="<li><strong><spring:message code='requestInfo.label.validation.attachmentEmpty'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	else if(fileCount >= maxAttCount){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileCountExceeds.errorMsg'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	else if(listOfFileTypes.indexOf(fileExt) == -1){
		document.getElementById("validationErrors").style.display = 'none';
		document.getElementById("exceptionsDiv").style.display = 'none';
		errorList = errorList + "<li><strong><spring:message code='validation.fileUpload.fileTypeInvalid.errorMsg'/></strong></li>";
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		validFlag = 0;
	}
	
	if(validFlag==1){
		
		var link="<%=attachDocumentAction %>";
		document.getElementById('fileUploadFormId').target = 'upload_target';
		document.getElementById('fileUploadFormId').action = link;
		document.getElementById('pageType').value = "mapRequest";
		jQuery('#fileUploadFormId').submit();

	}	
}

function removeFile(fileName) {
	var link="<%=removeDocumentAction %>";
	document.getElementById("fileCount").value = document.getElementById("fileCount").value - 1;
	document.getElementById('fileUploadFormId').target = 'upload_target';
	document.getElementById('fileUploadFormId').action = link + "&fileName=" + fileName;
	
	jQuery('#fileUploadFormId').submit();
}

function submitRequest(){
	
	var lbsAddressFlagSelect = $("#lbsAddressFlagSelect").val();  
	if(lbsAddressFlagSelect=="Y")
			{
				$("#installLBSAddressFlag").val("true");
			}
    else
			{
				$("#installLBSAddressFlag").val("false");	
			}
       
                $("#lodAddressInput").val($("#lodAddress").val());
                $("#lodFloorInput").val($("#lodFloor").val());
        
       var buildingSelected=jQuery("#bldng option:selected").val();
       var floorSelected=jQuery("#flr option:selected").val();
       
       if(buildingSelected ==='requestforbuilding')
    	{
    	   	jQuery('#isRequestForBuilding').val("true");
    	}
       if(floorSelected ==='requestforfloor')
	   {
	   		jQuery('#isRequestForFloor').val("true");
	   } 
       
	if($("#building1").val()!="")
	{
		jQuery('#isRequestForBuilding').val("true");
		jQuery('#bldng1').val(jQuery("#building1").val());
		jQuery('#physicalLocation1h').val("");
	}
	if($("#floor1").val()!="")
	{   
		jQuery('#isRequestForFloor').val("true");
        jQuery('#flr1').val(jQuery("#floor1").val());
		jQuery('#physicalLocation2h').val("");
	}
	
	
	var isRequestForBuilding = $("#isRequestForBuilding").val();  
	var isRequestForFloor = $("#isRequestForFloor").val();  
	
	if(isRequestForBuilding && isRequestForFloor )
		{
	
	    if($("#notesForNewBuilding").val().indexOf("New building and floor is being requested") <0)
		$("#notesForNewBuilding").val("New building and floor is being requested");
		}
	
	else if(isRequestForFloor && !isRequestForBuilding)
	{
		if($("#notesForNewBuilding").val().indexOf("New floor is being requested") <0)
		$("#notesForNewBuilding").val("New floor is being requested");
	}
	

	
	window.parent.document.getElementById("validationErrors").style.display = 'none';
	window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
	var errorList = "";
	
	
	if(jQuery('#effDtOfChange').val()!=""&&!dateCheck(jQuery('#effDtOfChange').val()))
		{
			
			errorList=errorList + "<li><strong><spring:message code='validation.date.format.errorMsg'/></strong></li>";
			jQuery('#effDtOfChange').addClass('errorColor');		
		}
	
	if(document.getElementById('notesOrComments').value == ''){
		
		errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.notesMandatory'/></strong></li>";
		
		jQuery('#notesOrComments').addClass('errorColor');
		
	}
	

var selectAddressFlag=false;
for(var i=0;i<3;i++){
        var selectAddressVal=$('ul.roDisplay li div:eq('+i+')').text();
        //alert(selectAddressVal);
        if (selectAddressVal!=""){
                selectAddressFlag=true;
                break;
        }
}
if(!selectAddressFlag)
	{

	errorList = errorList + "<li><strong><spring:message code='requestInfo.label.validation.addressMandatory'/></strong></li>";
	}
	if(document.getElementById('addtnlDescription').value.length > 2000){
		
		errorList = errorList + "<li><strong><spring:message code='validation.description.length.errorMsg'/></strong></li>";
	}
	if(document.getElementById('notesOrComments').value.length > 2000){
		
		errorList = errorList + "<li><strong><spring:message code='validation.notes.length.errorMsg'/></strong></li>";
		
		jQuery('#notesOrComments').addClass('errorColor');
		
	}
	<%--Changed for LBS --%>
	var lbsaddressflagins=jQuery("#installLBSAddressFlag").val();
	var lodAddressLevel=jQuery("#lodAddress").val();
	if(lbsaddressflagins=="true" && lodAddressLevel!="" && lodAddressLevel.toLowerCase()!="street level"){
		
		 if(isRequestForBuilding =="true")
			 {
				if(jQuery('#building1').val() ==''||jQuery('#building1').val() == null)
					{
					errorList = errorList +"<li><strong><spring:message code='lbs.label.install.building'/></strong></li>";
					jQuery('#building1').addClass('errorColor');
					jQuery(document).scrollTop(0);
					}
			 }
		 else
			 { 
			 if(jQuery('#physicalLocation1h').val() ==''||jQuery('#physicalLocation1h').val() == null )
			 	{
				 
					errorList = errorList +"<li><strong><spring:message code='lbs.label.install.building'/></strong></li>";
					jQuery('#bldng').addClass('errorColor');
					jQuery(document).scrollTop(0);
				}
			 }
		 if(isRequestForFloor =="true")
		 {
			if(jQuery('#floor1').val() ==''||jQuery('#floor1').val() == null)
				{
				errorList = errorList +"<li><strong><spring:message code='lbs.label.install.floor'/></strong></li>";
				jQuery('#floor1').addClass('errorColor');
				jQuery(document).scrollTop(0);
				}
		 }
		 else
		 { 
		 if(jQuery('#physicalLocation2h').val() ==''||jQuery('#physicalLocation2h').val() == null )
		 	{
			 
			   	errorList = errorList +"<li><strong><spring:message code='lbs.label.install.floor'/></strong></li>";
				jQuery('#flr').addClass('errorColor');
				jQuery(document).scrollTop(0);
			}
		 }
		
	}
	
	if(errorList != ""){
		jQuery('#jsValidationErrors',window.parent.document).empty();
		document.getElementById("jsValidationErrors").innerHTML = errorList;
		window.parent.document.getElementById("jsValidationErrors").style.display = 'block';
		window.parent.scrollTo(0,0);
		
	}else{
		var link="<%=confirmRequestAction %>";
			
		<%--Changed for LBS --%>
		if(jQuery("#physicalLocation1").val()!=''&&jQuery("#physicalLocation1").val()!=null){
			jQuery("#bldng1").val(jQuery("#physicalLocation1").val());	
		}
		if(jQuery("#physicalLocation2").val()!=''&&jQuery("#physicalLocation2").val()!=null){
			jQuery("#flr1").val(jQuery("#physicalLocation2").val());	
		}
		if(jQuery("#physicalLocation3").val()!=''&&jQuery("#physicalLocation3").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#physicalLocation3").val());	
		}
		
		if(jQuery("#office").val()!=''&&jQuery("#office").val()!=null){
			jQuery("#physicalLocation3h").val(jQuery("#office").val());	
		}
		
		var currentURL = window.location.href;
		
			if(!(currentURL.indexOf('fleet=true') == -1)){
			var fleetvalue='true';
		}
		else{
			var fleetvalue='false';
		}
		document.getElementById('mapsRequestFormId').action = link +"&timeZoneOffset=" + timezoneOffset+"&fleet="+fleetvalue;
		
		document.getElementById('mapsRequestFormId').submit();
		showOverlay();
	}
	
}


function setPathToTextBox()
{
	filename=jQuery('#file').val();
	if(filename.match(/fakepath/)) {
		filename = filename.replace(/C:\\fakepath\\/i, '');
	}
	jQuery('#txtFilePath').val(filename);
	
}

function clearAttachmentForm(){
    document.getElementById("fileUploadFormId").reset();
}

function openTemplate(type,fileName){
	if(type=='CHLTemplate'){
		window.location="${chlTemplateURL}&fileType=CHLTemplate";
	}else if(type=='Attachment'){
		window.location="${chlTemplateURL}&fileType=Attachment&fileName="+fileName;
	}else if(type=='massUploadTemplate'){
		window.location="${chlTemplateURL}&fileType=massUploadTemplate&fileName="+fileName;
	}else if(type=='hwMassUploadTemplate'){
		window.location="${chlTemplateURL}&fileType=hwMassUploadTemplate&fileName="+fileName;
	}
	
}
function wantMove() {
	 if($("#moveAsset").is(":checked"))
	   {
	           $("#moveAsset").val(true);
	           $('#contactDiv').show();
	   } else {
	       $('#contactDiv').hide();
	       $("#moveAsset").val(false);
	   }
}

jQuery(document).ready(function() {
	
	/* if($("#lodAddressInput").val()!="")
		{
		var levelOfDetails=$("#lodAddressInput").val();
		jQuery("#lodAddress option[value="+levelOfDetails+"]").attr("selected", "selected");
		} */
	 if($("#moveAsset").is(":checked"))
	   {
	           $("#moveAsset").val(true);
	           $('#contactDiv').show();
	           if($("#moveContactSelect").is(":checked"))
	        	   {
	        	   $('#mapsRequestContact').show();
	        	   }
	   } else {
	       $('#contactDiv').hide();
	       $("#moveAsset").val(false);
	   }
	var currentURL = window.location.href;

	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
	
		jQuery('#notesOrComments').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
	
		
		jQuery('#bldng').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
			
		jQuery('#flr').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
		
		jQuery('#building1').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
				
		jQuery('#floor1').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
		
		jQuery('#physicalLocation1').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
		
		jQuery('#physicalLocation2').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');});
		
		jQuery('#physicalLocation3').bind('mousedown focus',function(){
			jQuery(this).removeClass('errorColor');});
		
	c=1;
	var responseText = "";
	var fileCount = '<c:out value="${fileUploadForm.fileCount}" />';
	window.parent.document.getElementById("fileCount").value = fileCount;
	<c:if test="${fileUploadForm.fileMap != null}">
		jQuery('#fileListTable',window.parent.document).empty();
		responseText = '<thead><tr><th class="w15" id="fileData"></th><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>';
		    responseText = responseText + '<tbody>';
	 		<c:forEach items="${fileUploadForm.fileMap}" var="entry">
	 			if(c%2==0){
	 				responseText = responseText + '<tr class="altRow">';
	 			}
	 			else{
	 				responseText = responseText + '<tr>';
	 			}
	 			c++;
	 			responseText = responseText + '<td class="w15"><img id="img_help1"  src="<html:imagesPath/>tabbarImgs/default/close.png" alt="Remove" height="15" width="15" onclick=\'removeFile("${entry.key}");\' /></td>';
	 			responseText = responseText + '<td>'+ ' <a href="javascript:void(0);" onclick="openTemplate(\'Attachment\',\'${entry.key}\');"><c:out value="${entry.value.displayFileName}" /></a>' + '</td>';
	 			responseText = responseText + '<td class="right">'+ '<c:out value="${entry.value.fileSize}" />' + '</td>';
	 			
	 			responseText = responseText + '</tr>';
			</c:forEach>
			responseText = responseText + '</tbody>';
			jQuery('#fileListTable',window.parent.document).append(responseText);
		
	</c:if>
	<c:if test="${fileUploadForm.fileMap == null}">
		jQuery('#fileListTable',window.parent.document).empty();
	</c:if>
	
	
	
	jQuery('#txtFilePath').val('');
	
	<c:if test="${source != null}"> 
		
		jQuery('#txtFilePath',window.parent.document).val('');
		
		jQuery('#validationErrors',window.parent.document).empty();
		window.parent.document.getElementById("validationErrors").style.display = 'none';
		window.parent.document.getElementById("jsValidationErrors").style.display = 'none';
		if(document.getElementById("errors")!= null){
			
			
			var divText = document.getElementById("errors").innerHTML;
			jQuery('#validationErrors',window.parent.document).append(divText);
			window.parent.document.getElementById("validationErrors").style.display = 'block';
			window.parent.scrollTo(0,0);
		}
		
		jQuery('#exceptionsDiv',window.parent.document).empty();
		window.parent.document.getElementById("exceptionsDiv").style.display = 'none';
		if(document.getElementById("dispalyExceptions")!= null){
			
			
			var divText = document.getElementById("dispalyExceptions").innerHTML;
			jQuery('#exceptionsDiv',window.parent.document).append(divText);
			window.parent.document.getElementById("exceptionsDiv").style.display = 'block';
			window.parent.scrollTo(0,0);
		}
		
		jQuery("#processingHint",window.parent.document).css({
			display:"none"
		});
		jQuery("#overlay",window.parent.document).css({
			display:"none" 
		});
	
	
	</c:if>
	if('${errorFileMsg}'=='error'){
		 error="<spring:message code='requestInfo.label.validation.fileSizeMaps'/>";
		jQuery('#jsValidationErrors',window.parent.document).show();
		jQuery('#jsValidationErrors',window.parent.document).append(error);
		window.parent.scrollTo(0,0);
		
	}else{
		jQuery('#jsValidationErrors',window.parent.document).hide();
		jQuery('#jsValidationErrors',window.parent.document).empty();
	}
	
	

});



function onCancelClick() {
	
	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					
					showOverlay();
					window.location.href='fleet-management';
				}else{
					return false;
					}
			});
};

ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showMapsRequestPage'/></portlet:actionURL>&friendlyURL="+populateURL("mapsrequest",[],[]);					
}
function popupAddress(hyperlinkId){
	
	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
	
	var url=jQuery('#'+hyperlinkId).attr('href');
	
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
function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2, 
		addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag,levelOfDetails)
<%--Ends--%>
{
	<%--Changes for defect 7896 MPS 2.1 --%>
	jQuery('#jsValidationErrors').hide();
	jQuery(':input').removeClass('errorColor');	
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
				addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag);
		<%--Ends--%>
		
		//added for LBS
		if(lbsAddressFlag=="true" || lbsAddressFlag=="Y"){	
			//setting values to ""
			jQuery("#bldng").val("");
			jQuery("#bldng1").val("");
			jQuery("#flr1").val("");
			jQuery("#zone1").val("");
			jQuery('#campus1').val("");
			jQuery('#campush').val("");
			jQuery("#physicalLocation1h").val("");
			jQuery("#physicalLocation2h").val("");
			jQuery("#physicalLocation3h").val("");
			jQuery("#zoneh").val("");
			jQuery("#physicalLocation1").val("");
			jQuery("#physicalLocation2").val("");
			jQuery("#physicalLocation3").val("");
			jQuery("#office").val("");
			jQuery("#lodAddressInput").val("");
			jQuery("#lodFloorInput").val("");
			jQuery("#notesForNewBuilding").val("");
			jQuery("#lbsAddressFlagSelect").val("Y");
			jQuery("#lodAddress").val(levelOfDetails);
			jQuery("#flr").val("");
			jQuery("#building1").val("");
			jQuery("#floor1").val("");
			jQuery("#isRequestForBuilding").val("");
			jQuery("#isRequestForFloor").val("");
			jQuery("#viewGrid").hide();
			clearGridValues();
			lodCheck();
			//loadCoutrySateCitySiteBuilding(addressId);
		
		}
		else{
			//setting values to ""
			jQuery("#bldng1").val("");
			jQuery("#flr1").val("");
			jQuery("#zone1").val("");
			jQuery('#campus1').val("");
			jQuery('#campush').val("");
			jQuery("#physicalLocation1h").val("");
			jQuery("#physicalLocation2h").val("");
			jQuery("#physicalLocation3h").val("");
			jQuery("#zoneh").val("");
			jQuery("#physicalLocation1").val("");
			jQuery("#physicalLocation2").val("");
			jQuery("#physicalLocation3").val("");
			jQuery("#office").val("");
			jQuery("#bldng").val("");
			jQuery("#flr").val("");
			jQuery("#building1").val("");
			jQuery("#floor1").val("");
			jQuery("#isRequestForBuilding").val("");
			jQuery("#isRequestForFloor").val("");
			jQuery("#notesForNewBuilding").val("");
			jQuery("#lodAddressInput").val("");
			jQuery("#lodFloorInput").val("");
			jQuery("#lbsAddressFlagSelect").val("N");
			jQuery("#selectLocalAddress").hide();
			jQuery("#selectLocalAddress1").show();
			jQuery("#changeHeader").hide();
			jQuery("#lbsAD,.borderBelow").show();
			jQuery("#lod").hide();
			jQuery("#lod1").hide();
			jQuery("#viewGrid").hide();
			clearGridValues();
			
		}
		
	}
	
	closeDialog();
}
function addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
		addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode,lbsAddressFlag)
<%--Ends--%>
{
	if(addressProvince == null){
		addressProvince = '';
	}
	
	if(addressId==null){
		//addressId = -1;
		addressId = '';
	}
		
	
	jQuery("#pickupAddressId").val(addressId);
	jQuery("#pickupStoreFrontName").val(storefrontName);
	jQuery("#pickupAddressName").val(addressName);
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
	if(lbsAddressFlag=='null'||lbsAddressFlag==''){
		lbsAddressFlag="false";
		
	}
	jQuery("#installLBSAddressFlag").val(lbsAddressFlag);
	
	
	
	<%--Ends--%>
	
			
	//changes for MPS 2.1
	//This is for display purpose pickup address
	jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
	jQuery("#pickupAddressNameLbl").html(addressName);
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
		jQuery("#pickupAddressProvinceLbl").text("");
		}
	if(county =='' || county ==' ' || county == null){
		jQuery("#pickupAddresscountyLbl").hide();
		jQuery("#pickupAddresscountyLbl").text("");
		}
	// region changed to district for MPS 2.1
	if(district =='' || district ==' ' || district == null){
		jQuery("#pickupAddressRegionLB").hide();
		jQuery("#pickupAddressRegionLB").text("");
		}
	if(addressLine2 =='' || addressLine2 ==' ' || addressLine2 == null){
		jQuery("#pickupAddressLine2Lbl").hide();
		jQuery("#pickupAddressLine2Lbl").text("");
		}
	if(addressState =='' || addressState ==' ' || addressState == null){
		jQuery("#pickupAddressStateLbl").hide();
		jQuery("#pickupAddressStateLbl").text("");
		}
	if(addressCity =='' || addressCity ==' ' || addressCity == null){
		jQuery("#pickupAddressCityLbl").hide();
		jQuery("#pickupAddressCityLbl").text("");
		}
	//changes for MPS 2.1 end
	
}
function mapsMoveContactSelect()
{
	if($("#moveContactSelect").is(":checked"))
		{
		return popupContact('mapsRequest');
		}
	else
		{
		hideMapsContacts();
		//$("#mapsRequestContact").hide();
		}

}
if (document.getElementById("mapsRequestFirstName").value=="" && document.getElementById("mapsRequestLastName").value=="") {
	hideMapsContacts();
}		
//Show the Maps Request contact if selected
else {
	document.getElementById("mapsRequestLastNameLabel").innerHTML=document.getElementById("mapsRequestFirstName").value+" "+document.getElementById("mapsRequestLastName").value;
	document.getElementById("mapsRequestWorkPhoneLabel").innerHTML=document.getElementById("mapsRequestWorkPhone").value;
	document.getElementById("mapsRequestEmailAddressLabel").innerHTML=document.getElementById("mapsRequestEmailAddr").value;
	showMapsContacts();
}

$('#addressGridLink').click(function(){openPopupMap("moveTo",'${mdmId}','${mdmLevel}','${formPost}','${emailId}',$("#physicalLocation1h").val(),$("physicalLocation2h").val());});



function setDefaultValues(action)
{
	jQuery("#bldng").val("");
	jQuery("#bldng1").val("");
	jQuery("#flr1").val("");
	jQuery("#flr").val("");
	jQuery("#building1").val("");
	jQuery("#floor1").val("");
	jQuery("#isRequestForBuilding").val("");
	jQuery("#isRequestForFloor").val("");
	jQuery("#notesForNewBuilding").val("");
	jQuery("#zone1").val("");
	jQuery('#campus1').val("");
	jQuery('#campush').val("");
	jQuery("#physicalLocation1h").val("");
	jQuery("#physicalLocation2h").val("");
	jQuery("#physicalLocation3h").val("");
	jQuery("#zoneh").val("");
	jQuery("#physicalLocation1").val("");
	jQuery("#physicalLocation2").val("");
	jQuery("#physicalLocation3").val("");
	jQuery("#office").val("");
	jQuery("#lodAddressInput").val("");
	jQuery("#lodFloor").val("");
	jQuery("#lodFloorInput").val("");

	if(action == undefined)
		jQuery("#lodAddress").val("");
}

function clearGridValues()
{
	$('#addressXYLbl').html("Grid X/Y : ");
	$('#addressCoordinatesXPreDebriefRFV').val("");
	$('#addressCoordinatesYPreDebriefRFV').val("");
}
</script>