<%@ page contentType="text/html" isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>

<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<%--Changed for CI Defect #7853 --%>
<style>
	.move_type_confirmation{
	    color: #000000;
	    float: left;
	    font-weight: bold;
	    line-height: 1.8em;
	    vertical-align: top;
	    width:auto;    
	}
	.move_type1{padding-left:0px!important;}<%--Changed for CI Defect #7853 --%>
	button.button[disabled] {
	    cursor: wait !important;
	}
</style>

<jsp:include page="../../common/validationMPS.jsp"></jsp:include>
<script type="text/javascript">
	<%@ include file="/js/commonAsset.js"%>
</script> 
<jsp:include page="../../common/mapViewPopup.jsp"></jsp:include>

<%-- Below URL opens up the address list grid in popup --%>
<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<%-- Below URL sends the request to controller to show the review page --%>
<portlet:actionURL var="decommissionAssetConfirmationUrl">
<portlet:param name="action" value="decommissionMultiAssetConfirmation"></portlet:param>
</portlet:actionURL>

<%-- Below URL sends the request to main asset list page --%>
<portlet:renderURL var="redirectToAssetListPage"></portlet:renderURL>

<body onload=""/>
<%-- Below div opens up as popup, with contact, address and chl contents respectively --%>
<div id="dialogAddress"></div>
<div id="dialogChlTree" style="display: none;"></div>

<div id="content-wrapper">
	<div class="journal-content-article">
 		 <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1><h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
    </div>
	
	<div><h3><strong>
		<%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
	</strong></h3></div>
	
	<div class="main-wrap">
		<div class="content">
		
	    	<%--Form Bean is manageAssetFormForChange instance of ManageAssetForm - --%>
			<form:form method="post" commandName="manageAssetFormForDecommission" id="decommAssetForm">
			
				<%-- Below section is for the prev sr no and update flag binding--%>
				<form:hidden path="prevSrNo" id="prevSrNo"/>
				
	          	<!-- MAIN CONTENT BEGIN -->
	          	<%-- This section shows the front end validation errors --%>
				<div class="serviceError" id="errorDiv" style="display: none;">
				</div>
			    <%-- End of front end validations --%>
	
	            <%-- This section shows the server side validation errors --%>
	            <spring:hasBindErrors name="manageAssetFormForDecommission">
		          	<div class="serviceError" >
						<c:forEach var="error" items="${errors.allErrors}">
					   		<li class="portlet-msg-error"><strong><spring:message code="${error.code}"/></strong></li>
		            	</c:forEach>
		     		</div>     		
		     	</spring:hasBindErrors>
		     
		     	<div id="jsValidationErrors" class="error" style="display: none;"></div>	
		        <div id="validationErrors" class="error" style="display: none;"></div>
		        <%-- This div has been renamed to errorMsgPopup_CatalogDtl because contact popup validations div id was conflicting --%>
	     		<div class="error" id="errorMsgPopup_CatalogDtl" style="display:none"></div>
		     	<%--End of server side validation errors --%>	
		     
		     	<%-- <c:choose>
					<c:when test="${moveAssetFlag eq 'true'}">
						<h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.moveAssetInformation"/></h3>
					</c:when>
				</c:choose>	 by preeti--%>
				<h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.decommissionAnAsset"/></h3>
		     
		     	<jsp:include page="/WEB-INF/jsp/changemanagement/manageMultiAsset/assetInstallInfo.jsp" />
		     	
		     	<%--Selected asset information current identifiers start --%>
				<hr class="separator" />
		     	
		    	<%--This jsp would display the contact of the requestor retrieved from Portal & Session,  
				Additional Information of the Change Mgmt Request End--%>
				<c:set var="showDiffContact" value="1"/>
				<%@ include file="/WEB-INF/jsp/common/commonContactInfo.jsp"%>
				
				<div class="infoBox columnInner rounded shadow">
		 <div class="columnsTwo">
		 <ul class="form installCheck">
							<li>
								 <label>
								 <spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAssetMultiSelect"/>
								 <span class="req">*</span> 
								 </label> 
								 <span class="radio radio_confirm">
								 <form:radiobutton name="moveAsset" path="decommAssetFlag" value="yes" id="decomAssetYes" onclick="clearValidatnErr()"/>
								 <label>
								 <spring:message code="requestInfo.option.yes"/> </label> 
								 <form:radiobutton name="moveAsset" path="decommAssetFlag" value="no" id="decomAssetNo" onclick="clearValidatnErr()"/>
								 <label>
								 <spring:message code="requestInfo.option.no"/></label>
								 </span>
							</li>

						</ul>
					</div>
				 </div>
				
				<hr class="separator" />
				<%@ include file="/WEB-INF/jsp/changemanagement/manageMultiAsset/multiAssetInfo.jsp"%>
						
				<!-- Add Attachments BLOCK - Start -->
		        <span style="display: none;">
		        	<input type="text" name="attachmentDescriptionID" id="attachmentDescriptionID" />
		        </span>
		        <!-- Add Attachments BLOCK - End -->
			</form:form>
		</div>
		
		<%--Changed for LBS --%>
		<div class="buttonContainer">
			<c:if test="${fleetManagementFlag == true }">
				<button class="button_cancel" onclick="onCancelClick();" type="button"><spring:message code="button.back"/></button>
				<button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
			</c:if>
				
			<button class="button" type="button" id="btnContinue"><spring:message code="button.continue"/></button>
		</div>
	</div>
</div>

<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetFormForDecommission.backToMap}"/>
</form>
<script type="text/javascript">

<%--Changed for LBS --%>
function onCancelClick() {	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
		if(confirmResult){
			showOverlay();
			var lat="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLatitude}";
			var lon="${manageAssetFormForDecommission.assetDetail.installAddress.lbsLongitude}";
		
			if("${manageAssetFormForChange.backToMap}" == ""){
				var defaultArea={											
	                lat: lat,
	                lng: lon					                		           
				};
									
				$('#backJson').val(JSON.stringify(defaultArea));
			}
			$('#backFormMap').submit();
		}
		else{
			return false;
		}
	});
};

var flowPage="${pageFlow}";
var dialog;
var multiAssetList = "${multiAssetList}";

var assetArr = multiAssetList.split(",");

var addrName,addrLine1, addrLine2, addrLine3, addrCountry, addrPostCode, addrState, addrProvince, addrCity
,addrBuilding,addrFloor,addrOffice,physicalLoc1,physicalLoc2,physicalLoc3, addrStrFrntNm;
/************This is for front-end validation*********/

var xCo; 
var yCo;
var assetRowId1="${manageAssetFormForDecommission.assetDetail.assetId}";
var accountId='${manageAssetFormForDecommission.assetDetail.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";

jQuery(document).ready(function() {
	
	var deviceNameList= "";
	 if(assetArr.length > 0)
		{
			for(var i=0; i <assetArr.length; i++)
			{
				if(i==0)
					{
						deviceNameList=deviceNameList+$("#"+assetArr[i]+"_deviceName").val();
					}
					
				else
					{
						deviceNameList=deviceNameList+","+$("#"+assetArr[i]+"_deviceName").val();
					}
			}
		}
	 getImageFromServerForDevice(deviceNameList);
	 
	var currentURL = window.location.href;

	//	Change Account Link Hide/Show for CI-7 Defect #12274
	var isError="${exceptn}";
	if(isError)
	{
		jQuery("#errorDiv").show();
		jQuery("#errorDiv").append('<li class="portlet-msg-error"><strong><spring:message code="exception.unableToRetrieveDeviceDetail"/></strong></li>');
	}
	  
	jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>');	
	
	var offsetMinuteServiceRequest = new Date().getTimezoneOffset();
	 timezoneOffsetServiceRequest = (offsetMinuteServiceRequest/60)*(-1);
	/***************This is for front-end validation*******************/

	var fleetManagegmtFlag = "${fleetManagementFlag}";
	var decommAssetConfirmUrl="${decommissionAssetConfirmationUrl}"+"&timeZoneOffset=" + timezoneOffset+"&pageFlow="+flowPage+"&fleetManagementFlag="+fleetManagegmtFlag+"&multiAssetList="+multiAssetList;

	xCo= jQuery("#errorDiv").offset().left;
	YCo= jQuery("#errorDiv").offset().top;
	
	//validation for continue button
	jQuery(':input').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
	});
	
	jQuery("#btnContinue").click(function(){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSET%>','<%=LexmarkSPOmnitureConstants.DECOMMISSIONASSETCONTINUE%>');
		
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
		//alert("move"+jQuery("#moveTophysicalLocation1").val());
		
		if(jQuery("#moveTophysicalLocation1").val()!=''&&jQuery("#moveTophysicalLocation1").val()!=null){
			jQuery("#bldngm1").val(jQuery("#moveTophysicalLocation1").val());	
		}
		if(jQuery("#moveTophysicalLocation2").val()!=''&&jQuery("#moveTophysicalLocation2").val()!=null){
			jQuery("#flrm1").val(jQuery("#moveTophysicalLocation2").val());	
		}
		if(jQuery("#moveTophysicalLocation3").val()!=''&&jQuery("#moveTophysicalLocation3").val()!=null){
			jQuery("#moveTophysicalLocation3h").val(jQuery("#moveTophysicalLocation3").val());	
		}
		if(jQuery("#zonemove").val()!=''&&jQuery("#zonemove").val()!=null){
			jQuery("#zonem1").val(jQuery("#zonemove").val());	
		}
		if(jQuery("#moveTophysicalLocation3m").val()!=''&&jQuery("#moveTophysicalLocation3m").val()!=null){
			jQuery("#moveTophysicalLocation3h").val(jQuery("#moveTophysicalLocation3m").val());	
		}
		
		var moveTypeSelection=jQuery('#selectMoveType option:selected').val(); //Added for Move Type Validation--CI BRD 13-10-03
		jQuery("#errorDiv").html('');
		
		 var elementIdsToValidate=["addtnlDescription","effDtOfChange"];
		  
		  	  	validationflag=false;
				for(i=0;i<elementIdsToValidate.length;i++){
					if((jQuery('#'+elementIdsToValidate[i]).val()).length>0){
					
						var patRes=validate(elementIdsToValidate[i],jQuery('#'+elementIdsToValidate[i]).val());
						
						if (patRes!=true)
						{
							validationflag=true;
							jQuery('#'+elementIdsToValidate[i]).addClass('errorColor');
							jQuery("#errorDiv").append('<li class="portlet-msg-error"><strong>'+patRes+'</strong></li>');	
						}
					}
				}
			
			
			var lbsaddressflagins=jQuery("#installLBSAddressFlag").val();
			var installAddressId = $("#installAddressId").val();
			if(installAddressId != null && installAddressId != undefined)
				lbsaddressflagins = "true";
			
			
			var lbsaddressflagmov=jQuery("#moveToAddressLBSAddressFlag").val();
			var moveToAddressId = $("#moveToAddressId").val();
			if(moveToAddressId != null && moveToAddressId != undefined)
				lbsaddressflagmov = "true";
			var levelOfDetails = $.trim(jQuery("#moveToAddressLevelOfDetails").val()).toLowerCase();
			
			//To validate the individual asset building and flooor 
			var assetLbsAddressFlag = "";
			if(flowPage.toLowerCase() === "fleetmgmtmove")
			{
				assetLbsAddressFlag = lbsaddressflagmov;				
			}
			else if(flowPage.toLowerCase() === "fleetmgmtchange" || flowPage.toLowerCase() === "decommission")
			{	
				assetLbsAddressFlag = lbsaddressflagins;
			}
			var addressLevelOfDetails = "";
			if(flowPage.toLowerCase() === "fleetmgmtmove")
			{
				addressLevelOfDetails = $.trim($("#moveToAddressLevelOfDetails").val()).toLowerCase();
			}
			else if(flowPage.toLowerCase() === "fleetmgmtchange" || flowPage.toLowerCase() === "decommission")
			{	
				addressLevelOfDetails = $.trim($("#installLevelOfDetails").val()).toLowerCase();
			}
			
			if(assetArr.length > 0)
    		{
				for(var ind=0; ind <assetArr.length; ind++)
				{
					if(assetLbsAddressFlag=="true" && addressLevelOfDetails.match("floor level|grid level|mix - see floor")){

						jQuery('#'+assetArr[ind]+'_physicalLocation1h').val(jQuery('#'+assetArr[ind]+'_buildingDD').val());

						if(jQuery('#'+assetArr[ind]+'_buildingDD').val() != null && jQuery('#'+assetArr[ind]+'_buildingDD').val().length > 0)
							jQuery('#'+assetArr[ind]+'_physicalLocation1').val(jQuery('#'+assetArr[ind]+'_buildingDD option:selected').text());
						else
							jQuery('#'+assetArr[ind]+'_physicalLocation1').val("");

						jQuery('#'+assetArr[ind]+'_physicalLocation2h').val(jQuery('#'+assetArr[ind]+'_floorDD').val());

						if(jQuery('#'+assetArr[ind]+'_floorDD').val() != null && jQuery('#'+assetArr[ind]+'_floorDD').val().length > 0)
							jQuery('#'+assetArr[ind]+'_physicalLocation2').val(jQuery('#'+assetArr[ind]+'_floorDD option:selected').text());
						else
							jQuery('#'+assetArr[ind]+'_physicalLocation2').val("");

						jQuery('#'+assetArr[ind]+'_zoneh').val(jQuery('#'+assetArr[ind]+'_zoneDD').val());

						if(jQuery('#'+assetArr[ind]+'_zoneDD').val() != null && jQuery('#'+assetArr[ind]+'_zoneDD').val().length > 0)
							jQuery('#'+assetArr[ind]+'_zoneName').val(jQuery('#'+assetArr[ind]+'_zoneDD option:selected').text());
						else
							jQuery('#'+assetArr[ind]+'_zoneName').val("");
						
						/*if(jQuery('#'+assetArr[ind]+'_physicalLocation1h').val() == ''|| jQuery('#'+assetArr[ind]+'_physicalLocation1h').val() ==null ){
							validationflag=true;
							jQuery("#errorDiv").append("<li><strong><spring:message code='lbs.label.move.building'/> - for Asset "+$("#"+assetArr[ind]+"_serialNumber").val()+"</strong></li>");
						}
						if(jQuery('#'+assetArr[ind]+'_physicalLocation2h').val() == ''|| jQuery('#'+assetArr[ind]+'_physicalLocation2h').val() ==null){
							validationflag=true;
							jQuery("#errorDiv").append("<li><strong><spring:message code='lbs.label.move.floor'/> - for Asset "+$("#"+assetArr[ind]+"_serialNumber").val()+"</strong></li>");
						}*/
					}
					else if(assetLbsAddressFlag=="true" && addressLevelOfDetails.match("street level")){
						jQuery('#'+assetArr[ind]+'_physicalLocation1h').val("");
						jQuery('#'+assetArr[ind]+'_physicalLocation1').val(jQuery('#'+assetArr[ind]+'_buildingTT').val());

						jQuery('#'+assetArr[ind]+'_physicalLocation2h').val("");
						jQuery('#'+assetArr[ind]+'_physicalLocation2').val(jQuery('#'+assetArr[ind]+'_floorTT').val());

						jQuery('#'+assetArr[ind]+'_zoneh').val("");
						jQuery('#'+assetArr[ind]+'_zoneName').val(jQuery('#'+assetArr[ind]+'_zoneTT').val());
					}
				}
    		}
			// Validate the individual asset building and flooor - ends
			if(!(jQuery('#decomAssetYes').is(':checked')) && !(jQuery('#decomAssetNo').is(':checked')))
				{	
					validationflag=true;
					jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.decomissionFlag.format.errorMsg'/>" 
							+ "</strong></li>");
				}
			var fleetMgmtFlag = "${fleetManagementFlag}";
			//alert("fleetMgmtFlag:: "+fleetMgmtFlag);
				
				if(validationflag==true){
					jQuery("#errorDiv").show();
					jQuery(document).scrollTop(0);
						return false;
				}
				else{
				
					jQuery('#attachmentDescriptionID').val(jQuery('#attachmentDescription').val());
					jQuery("#decommAssetForm").attr("action", decommAssetConfirmUrl);

					$("#moveToFloorLevelOfDetails").val($("#flrm option:selected").attr("lod"));
					$("#installFloorLevelOfDetails").val($("#flr option:selected").attr("lod"));
					
					jQuery("#decommAssetForm").submit();
				}

				
	});
});

/********************Method for closing all kinds of popups*******************/	
function closeDialog()
{
	dialog.dialog('close');
	dialog=null;
	jQuery('#dialog').html("");
}
	       
image_error= function () {
	document.getElementById("MyPicture").src = '<html:imagesPath/>dummy_noimg.jpg'; 
};
	
function clearValidatnErr()
{
	if(jQuery("#errorDiv").html()!=""){
	
		jQuery("#errorDiv").html('');
		jQuery("#errorDiv").hide();
	}
}
function openPopUpPg(assetRowId, serialNumber, ipAddress, productLine, assetTag){
	multiSelectAsset.addAsset([{
		"id":assetRowId,
		"serialNumber":serialNumber,
		"ipAddress":ipAddress,
		"customerDeviceTag":assetTag,
		"name":productLine
		
	}]);
	showPgCntMSlct();
}

var pgDialog;
function showPgCntMSlct(){
	<%-- Update the html content firmst--%>
	assetPgCntsObj.createAssetHTML(multiSelectAsset.getAssetList());
	$(document).scrollTop(0);
	if(pgDialog){
		pgDialog.dialog('open');
		return;
	}
	pgDialog=$('#assetPageCounts-Multiple').dialog({
		autoOpen: false,
		title: "Update Page Counts",
		modal: true,
		draggable: false,
		resizable: false,
		position: ['top'],
		height: 500,
		width: 950,
		open:function(){
			multiSelectAsset.openAsset();
		},
		close:function(){
			
			multiSelectAsset.resetObj();
		}
		});
	pgDialog.dialog('open');		
	
}

	
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
}
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/lbs.css"/>
<script type="text/javascript" src="<html:rootPath/>js/toggler.js?version=0.1"></script>
<jsp:include page="/WEB-INF/jsp/fleetmanagement/updatePageCounts-mulitple.jsp"/>