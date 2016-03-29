<%@ page contentType="text/html" isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/common/subTab.jsp"%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>

<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%>
</script>


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
<portlet:actionURL var="addMultiAssetConfirmUrl">
<portlet:param name="action" value="addMultiAssetConfirmation"></portlet:param>
</portlet:actionURL>

<%-- Below URL sends the request to main asset list page --%>
<portlet:renderURL var="redirectToAssetListPage"></portlet:renderURL>

<body onload=""/>
<%-- Below div opens up as popup, with contact, address and chl contents respectively --%>
<div id="dialogAddress"></div>
<div id="dialogChlTree" style="display: none;"></div>

<div id="content-wrapper">
	<div class="journal-content-article">
 		 <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
 		 <h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
    </div>
	
	<div><h3><strong>
		<%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
	</strong></h3></div>
	
	<div class="main-wrap">
		<div class="content">
		
	    	<%--Form Bean is manageAssetFormForChange instance of ManageAssetForm - --%>
			<form:form method="post" commandName="manageAssetForm" id="manageAssetForm">
			
				<%-- Below section is for the prev sr no and update flag binding--%>
				<form:hidden path="prevSrNo" id="prevSrNo"/>
				
	          	<!-- MAIN CONTENT BEGIN -->
	          	<%-- This section shows the front end validation errors --%>
				<div class="serviceError" id="errorDiv" style="display: none;">
				</div>
			    <%-- End of front end validations --%>
	
	            <%-- This section shows the server side validation errors --%>
	            <spring:hasBindErrors name="manageAssetForm">
		          	<div class="error" >
						<c:forEach var="error" items="${errors.allErrors}">
					   		<li><strong><spring:message code="${error.code}"/></strong></li>
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
				<h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.link.addMultipleAssets"/></h3>
		     
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
								 <label><%-- 
								 changed against defect# 1730
								 <spring:message code="requestInfo.tooltip.doYouRequireLexmarkToCollectThisAsset"/> --%>  
								 <spring:message code="requestInfo.cm.manageAsset.label.doYouRequireLexmarkToInstallThisDeviceMultiSelect"/>
								 <span class="req">*</span> 
								 </label> 
								 <span class="radio radio_confirm">
								 <form:radiobutton name="installAsset" path="installAssetFlag" value="yes" id="installAssetYes" onclick="clearValidatnErr()"/>
								 <label>
								 <spring:message code="requestInfo.option.yes"/> </label> 
								 <form:radiobutton name="installAsset" path="installAssetFlag" value="no" id="installAssetNo" onclick="clearValidatnErr()"/>
								 <label>
								 <spring:message code="requestInfo.option.no"/></label>
								 </span>
							</li>

						</ul>
					</div>
				 </div>
				<hr class="separator" />
				<%@ include file="/WEB-INF/jsp/changemanagement/manageMultiAsset/multiAssetAdditionInfo.jsp"%>
						
				<!-- Add Attachments BLOCK - Start -->
		        <span style="display: none;">
		        	<input type="text" name="attachmentDescriptionID" id="attachmentDescriptionID" />
		        </span>
		        <!-- Add Attachments BLOCK - End -->
			</form:form>
			<jsp:include page="multiAddForm.jsp"></jsp:include>
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
	<input type="hidden" name="backJson" id="backJson" value="${manageAssetForm.backToMap}"/>
</form>
<script type="text/javascript">

<%--Changed for LBS --%>
function onCancelClick() {	
	jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
		if(confirmResult){
			showOverlay();
			var lat="${manageAssetForm.assetDetail.installAddress.lbsLatitude}";
			var lon="${manageAssetForm.assetDetail.installAddress.lbsLongitude}";
		
			if("${manageAssetForm.backToMap}" == ""){
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

var addrName,addrLine1, addrLine2, addrLine3, addrCountry, addrPostCode, addrState, addrProvince, addrCity
,addrBuilding,addrFloor,addrOffice,physicalLoc1,physicalLoc2,physicalLoc3, addrStrFrntNm;
/************This is for front-end validation*********/

var xCo; 
var yCo;
var assetRowId1="${manageAssetForm.assetDetail.assetId}";
var accountId='${manageAssetForm.assetDetail.account.accountId}';
var linkClicked=1;
var timezoneOffsetServiceRequest="";

jQuery(document).ready(function() {
	
   hideShowDelete();
   
   <c:forEach items="${manageAssetForm.assetDetailsList}" var="assetInfo" varStatus="assetDetail">
		<c:if test="${assetInfo.serialNumber != null && assetInfo.serialNumber !=''}">
			getProductModel("backFlow",'${assetDetail.index}');
		</c:if>
   </c:forEach>	

	 
	var currentURL = window.location.href;

	//	Change Account Link Hide/Show for CI-7 Defect #12274
	var isError="${exceptn}";
	if(isError)
	{
		jQuery("#errorDiv").show();
		jQuery("#errorDiv").append('<li><strong><spring:message code="exception.unableToRetrieveDeviceDetail"/></strong></li>');
	}
	  
	jQuery("#prevSrNo").val('<%= request.getParameter("prevSrNo")%>');	
	
	var offsetMinuteServiceRequest = new Date().getTimezoneOffset();
	 timezoneOffsetServiceRequest = (offsetMinuteServiceRequest/60)*(-1);
	/***************This is for front-end validation*******************/

	var fleetManagegmtFlag = "${fleetManagementFlag}";
	var addAssetConfirmUrl="${addMultiAssetConfirmUrl}"+"&timeZoneOffset=" + timezoneOffset+"&pageFlow="+flowPage+"&fleetManagementFlag="+fleetManagegmtFlag;

	xCo= jQuery("#errorDiv").offset().left;
	YCo= jQuery("#errorDiv").offset().top;
	
	//validation for continue button
	jQuery(':input').bind('mousedown focus',function(){
		jQuery(this).removeClass('errorColor');
	});
	
	jQuery("#btnContinue").click(function(){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ADDANEWASSET%>','<%=LexmarkSPOmnitureConstants.ADDASSETCONTINUE%>');

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
		   
			var numberOfAsset=$('.asset').length;
			for(i=0;i<numberOfAsset;i++)
				{
				if(jQuery('#'+i+'_productType :selected').val()=="other")
				{
				
					jQuery('#'+i+'_productModel').val(jQuery('#'+i+'_enterProduct').val());
				}
				else
				{
	                
					jQuery('#'+i+'_productModel').val(jQuery('#'+i+'_productType :selected').val());
					
				}
			 
				 if(jQuery("#"+i+"_installDateInput").val()!=''&&jQuery("#"+i+"_installDateInput").val!=null)
			 	{
					
					jQuery('#'+i+'_installDate').val(jQuery('#'+i+'_installDateInput').val());	
			 	}	
			 
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
			else if(flowPage.toLowerCase() === "fleetmgmtchange" || flowPage.toLowerCase() === "decommission" 
				|| flowPage.toLowerCase() === "addmultiple")
			{	
				assetLbsAddressFlag = lbsaddressflagins;
			}
			var addressLevelOfDetails = "";
			if(flowPage.toLowerCase() === "fleetmgmtmove")
			{
				addressLevelOfDetails = $.trim($("#moveToAddressLevelOfDetails").val()).toLowerCase();
			}
			else if(flowPage.toLowerCase() === "fleetmgmtchange" || flowPage.toLowerCase() === "decommission"
				|| flowPage.toLowerCase() === "addmultiple")
			{	
				addressLevelOfDetails = $.trim($("#installLevelOfDetails").val()).toLowerCase();
			}
			
			var cntr = 1;
				$(".buildingDD").each(function(){

					var astInd = $(this).attr("astid");
					
					if(assetLbsAddressFlag=="true" && addressLevelOfDetails.match("floor level|grid level|mix - see floor")){

						jQuery('#'+astInd+'_physicalLocation1h').val(jQuery('#'+astInd+'_buildingDD').val());
						jQuery('#'+astInd+'_physicalLocation1').val(jQuery('#'+astInd+'_buildingDD option:selected').text());

						jQuery('#'+astInd+'_physicalLocation2h').val(jQuery('#'+astInd+'_floorDD').val());
						jQuery('#'+astInd+'_physicalLocation2').val(jQuery('#'+astInd+'_floorDD option:selected').text());

						jQuery('#'+astInd+'_zoneh').val(jQuery('#'+astInd+'_zoneDD').val());
						jQuery('#'+astInd+'_zoneName').val(jQuery('#'+astInd+'_zoneDD option:selected').text());
						
						if(jQuery('#'+astInd+'_physicalLocation1h').val() == ''|| jQuery('#'+astInd+'_physicalLocation1h').val() ==null ){
							validationflag=true;
							jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.multiselect.asset.building'/> "+cntr+"</strong></li>");
						}
						if(jQuery('#'+astInd+'_physicalLocation2h').val() == ''|| jQuery('#'+astInd+'_physicalLocation2h').val() ==null){
							validationflag=true;
							jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong><spring:message code='lbs.multiselect.asset.floor'/> "+cntr+"</strong></li>");
						}
					}
					else if(assetLbsAddressFlag=="true" && addressLevelOfDetails.match("street level")){
						jQuery('#'+astInd+'_physicalLocation1h').val("");
						jQuery('#'+astInd+'_physicalLocation1').val(jQuery('#'+astInd+'_buildingTT').val());

						jQuery('#'+astInd+'_physicalLocation2h').val("");
						jQuery('#'+astInd+'_physicalLocation2').val(jQuery('#'+astInd+'_floorTT').val());

						jQuery('#'+astInd+'_zoneh').val("");
						jQuery('#'+astInd+'_zoneName').val(jQuery('#'+astInd+'_zoneTT').val());
					}
					cntr++;
				
			});
    		
		var fleetMgmtFlag = "${fleetManagementFlag}";
		//var validationflag=false;
		if(!(jQuery('#installAssetYes').is(':checked')) && !(jQuery('#installAssetNo').is(':checked')))
		{	
			validationflag=true;
			jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"<spring:message code='validation.Asset.installAssetFlag.format.errorMsg'/>" 
			+ "</strong></li>");
		}
		var showErrorMsgForDeviceInfo = false;
		for(var assetIndex in deviceMapObj)
			{
		    if(deviceMapObj[assetIndex]==true)
		    	{
		    	if(jQuery('#'+assetIndex+'_serialNumber').val()=="" 
                    && jQuery('#'+assetIndex+'_ipAddress').val()==""
                    && jQuery('#'+assetIndex+'_hostName').val()==""
                    && jQuery('#'+assetIndex+'_deviceTag').val()==""
                    && jQuery('#chlNodeValueLabel_'+assetIndex).val()==""
                    && jQuery('#'+assetIndex+'_installDateInput').val()==""
                    )
					{	
						validationflag=true;
						showErrorMsgForDeviceInfo = true;

					}
		    	}
			}
		if(showErrorMsgForDeviceInfo==true){
			jQuery("#errorDiv").append("<li class=\"portlet-msg-error\"><strong>"+"Please enter at least one device information to proceed." 
		              + "</strong></li>");
		}
		if(validationflag==true){
			jQuery("#errorDiv").show();
			jQuery(document).scrollTop(0);
			return false;
		}
		else{
			jQuery("#manageAssetForm").attr("action", addAssetConfirmUrl);
			jQuery("#manageAssetForm").submit();
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
	
ajaxSuccessFunction=function updateRequest(){
	window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
}
</script>