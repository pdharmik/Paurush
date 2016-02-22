<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>


<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/lbs.css?version=1.111"/>




<!--[if IE 7]>
	
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css"/>
<style type="text/css">
       .ie7 .button, .ie7 .button_cancel {
               behavior: url(/LexmarkOPSPortal/WEB-INF/css/PIE.htc) !important;                
        }
        .buttonContainer{position:relative!important;}
        .button_subrow1{margin-left:81%!important;padding: 0.27em 0.5em 0.34em!important;}
</style>
<![endif]-->
<style>
 #tabs ul li {float : left;
 text-decoration: none;}
 #tabs ul{
 list-style-type: none;
}


</style>



<script type="text/javascript" src="<html:rootPath/>js/toggler.js?version=0.2"></script>
<script type="text/javascript" src="<html:rootPath/>js/LbsService.js?version=3.35"></script>
<script type="text/javascript" src="<html:rootPath/>js/LBSDbFilters.js?version=1.11"></script>
<script type="text/javascript" src="<html:rootPath/>js/addressPopup.js?version=1.1"></script>
<script type="text/javascript" src="<html:rootPath/>js/placement.js?version=1.43"></script>

<c:set var="hMapForAccess" value="${sessionScope.userAccessMapForSr}"></c:set>
<c:set var="showCreateNew" value="false" scope="request"></c:set>
		<c:if test="${(hMapForAccess['CREATE SERVICE REQUEST'] eq true) or 
		(hMapForAccess['CREATE SUPPLIES REQUEST'] eq true)
		or (hMapForAccess['CREATE CHANGE MGMT REQUEST'] eq true) or
		(hMapForAccess['CREATE HARDWARE REQUEST'] eq true)}" >
		<c:set var="showCreateNew" value="true" scope="request"></c:set>
</c:if>	


<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>
<portlet:resourceURL var="loadComboVar" id="loadCombo"></portlet:resourceURL>

<portlet:actionURL var="fleetManagementFormURL">
	<portlet:param name="action" value="createSuppliesRequest"/>
</portlet:actionURL>

<portlet:actionURL var="serviceRequestURL">
	<portlet:param name="action" value="createServiceRequest"/>
</portlet:actionURL>

<portlet:actionURL var="decommissionAssetURL">
	<portlet:param name="action" value="decommissionAssetFromMap"/>
</portlet:actionURL>

<portlet:actionURL var="changeAssetURL">
	<portlet:param name="action" value="changeAssetFromMap"/>
</portlet:actionURL>

<portlet:actionURL var="moveAssetURL">
	<portlet:param name="action" value="moveAssetFromMap"/>
</portlet:actionURL>

<portlet:resourceURL var="removePlacementURL" id="removePlacement"></portlet:resourceURL>

<portlet:actionURL var="addAssetURL">
	<portlet:param name="action" value="addAssetFromMap"/>
</portlet:actionURL>

<portlet:actionURL var="addMultiAssetURL">
	<portlet:param name="action" value="addMultiAssetFromMap"/>
</portlet:actionURL>


<portlet:resourceURL var="saveFilterURLNonPreference" id="saveFilter"></portlet:resourceURL>
<portlet:renderURL var="viewDeviceHistoryPopup" windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="viewDeviceHistoryPopup"/>
</portlet:renderURL>
<portlet:resourceURL var="retrieveDeviceURLVar" id="retrieveDeviceURL"></portlet:resourceURL>
<portlet:resourceURL var="encryptJSONVar" id="encryptJSON"></portlet:resourceURL>
<portlet:resourceURL var="retrieveDeviceBookmarkVar" id="retrieveDeviceBookmarked"></portlet:resourceURL>
<portlet:resourceURL var="setZoomLevelToSession" id="setZoomLevelToSession"></portlet:resourceURL>

<portlet:renderURL var="countryListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="countryDropDownPopulate"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="stateListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="stateDropDownPopulate"></portlet:param>
</portlet:renderURL>
<c:set var="showCapture" value="true"/>
<c:if test="${fn:indexOf(header['user-agent'], 'MSIE 8.0') != -1 || fn:indexOf(header['user-agent'], 'MSIE 9.0') != -1 || fn:indexOf(header['user-agent'], 'MSIE 10.0') != -1}">
	<c:set var="showCapture" value="false"/>
</c:if>

<c:if test="${fn:indexOf(header['user-agent'], 'Firefox') != -1}">
	<c:set var="showCapture" value="true"/>
</c:if>

<c:if test="${fn:indexOf(header['user-agent'], 'Chrome') != -1}">
	<c:set var="showCapture" value="true"/>
</c:if>
<div style="display: none;">
${header['user-agent']} showCapture=${showCapture}
</div>
  <div id="content-wrapper">
  <div class="error" id="errorDiv" style="display: none;"></div>
   <div class="success" id="successDiv" style="display: none;">
   	<ul id="message_banner_" class="serviceSuccess"><li class="portlet-msg-success">No other accounts available for this login.</li></ul>
   </div>
  	<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
 	<jsp:include page="/WEB-INF/jsp/fleetmanagement/toggleView.jsp"></jsp:include>
 	
    <div class="journal-content-article">
      <h1 id="mapInfo"><spring:message code='lbs.label.mapview'/></h1>
      
	  <div id="actionsDiv"><img src="<html:imagesPath/>asc.gif"><span><spring:message code='lbs.label.actions'/></span>
	  </div>
    </div>
    <div id="ActionsMenu" style="display:none;">
		<span class="popup_arrow">
			<span class="popup_arrow-inner"></span>
		</span>
		<c:if test="${fleetMgmtForm.mdmLevel!='Siebel'}">
			<div id="actionChangeAccount"><spring:message code='lbs.label.changeaccount'/></div>
		</c:if>
		<c:if test="${showCreateNew=='true'}">
			<div id="actionCreateRequest"><spring:message code='lbs.label.createrequest'/></div>
		</c:if>
		 <c:if test="${showCapture}">
		<div class="padding5" onClick="postCapture()">Capture Map</div>
		</c:if>
		<div class="padding5" id="viewGridActions" onClick="viewGridAct()" style="display: none;">Show Grid</div>		
	</div>

  <div class="main-wrap-fleet">
      
	<jsp:include page="mapFilters.jsp"></jsp:include>
	  
	  
	<div class="clearBoth"></div>
	 <div id="left-nav-Div" style="display: none;">
		<jsp:include page="assetDetailsView.jsp"></jsp:include>
	</div>
	<div class="map">
	<%-- LBS 1.5 Changes start--%>
	<div id="deviceStatusContent">
		<jsp:include page="/WEB-INF/jsp/fleetmanagement/deviceStatus/deviceStatus-home.jsp"/>
	</div>
	<%-- LBS 1.5 Changes ENDS --%>
			<jsp:include page="maps-iframe.jsp"></jsp:include>
	</div>
	<%-- this button will only be shown only when doing MOVE request--%> 
	<button class="buttonGrey" id="cancelMoveRequest" style="display: none;"><spring:message code='lbs.label.cancelmove'/></button>
	<%-- <button class="buttonGrey" id="cfrmMoveHidden"><spring:message code='lbs.confirm.move'/></button>--%>
	
    </div>
    
   
</div>

<form:form id="fleetMgmtForm" method="post" commandName="fleetMgmtForm" modelAttribute="fleetMgmtForm" action="${fleetManagementFormURL}" >

               <form:hidden  path="assetId" id="assetId" />
               <%-- Below section is for setting the move to address --%>
               	
				<form:hidden  path="moveToAddress.addressLine1" id="lbs_address" />
				<form:hidden  path="moveToAddress.latitude" id="lbs_lat" />
				<form:hidden  path="moveToAddress.longitude" id="lbs_lng" />
				<form:hidden  path="moveToAddress.floorNumber" id="lbs_floorNumber" />
				<%-- <form:hidden  path="moveToAddress.physicalLocation1" id="lbs_buildingId" />
				<form:hidden  path="moveToAddress.buildingName" id="lbs_buildingName" />--%>
				
				<form:hidden  path="moveToAddress.buildingId" id="lbs_buildingId" />
				<form:hidden  path="moveToAddress.physicalLocation1" id="lbs_buildingName" />
				<form:hidden  path="moveToAddress.zoneId" id="lbs_regionId" />
				<form:hidden  path="moveToAddress.zoneName" id="lbs_regionName" />
				<form:hidden  path="moveToAddress.city" id="lbs_city" />
				<form:hidden  path="moveToAddress.state" id="lbs_state" />
				<form:hidden  path="moveToAddress.country" id="lbs_country" />
				<form:hidden  path="moveToAddress.addressId" id="lbs_extAddressId" />
				<form:hidden  path="moveToAddress.campusId" id="lbs_campusId" />
				<form:hidden  path="moveToAddress.campusName" id="lbs_campusName" />
				<%--<form:hidden  path="moveToAddress.physicalLocation2" id="lbs_floorId" />
				<form:hidden  path="moveToAddress.floorName" id="lbs_floorName" /> --%>
				
				<form:hidden  path="moveToAddress.floorId" id="lbs_floorId" />
				<form:hidden  path="moveToAddress.physicalLocation2" id="lbs_floorName" />
				<form:hidden  path="moveToAddress.postalCode" id="lbs_zipCode" />
				
				<%-- Below section is for cleansed address --%>
				<form:hidden  path="moveToAddress.addressLine2" id="lbs_address2" />
				<form:hidden  path="moveToAddress.stateProvince" id="lbs_stateProvince" />
				<form:hidden  path="moveToAddress.storeFrontName" id="lbs_storeFrontName" />
				<form:hidden  path="moveToAddress.physicalLocation3" id="lbs_physicalLocation3" />
				<form:hidden  path="moveToAddress.district" id="lbs_district" />
				<form:hidden  path="moveToAddress.countryISOCode" id="lbs_countryISOCode" />
				<form:hidden  path="moveToAddress.region" id="lbs_region" />
				<form:hidden  path="moveToAddress.stateCode" id="lbs_stateCode" />
				<form:hidden  path="moveToAddress.county" id="lbs_county" />
				
				
				<form:hidden  path="moveToAddress.addressName" id="lbs_addressName" />
				
				<form:hidden  path="backInfo" id="backInfo" />
				<%--Changes LBS 1.5 asset list --%>
				<form:hidden  path="multiAssetList" id="multiAssetList" />
				<form:hidden  path="multiAssetRequestType" id="multiAssetRequestType" />
				
				<form:hidden  path="moveToAddress.lbsAddressFlag" id="lbs_AddressFlag" />
                <form:hidden  path="moveToAddress.levelOfDetails" id="lbs_addressLOD" />
                <form:hidden  path="moveToAddress.floorLevelOfDetails" id="lbs_floorLOD" />
                
               	<form:hidden  path="placementId" id="lbs_placementId" />
				<%--Changes LBS 1.5 asset list Ends--%>
				
				<%-- Below section is for setting the multiselect-install address --%>               	
				<form:hidden  path="installAddress.addressLine1" id="lbs_address_install" />				
				<form:hidden  path="installAddress.latitude" id="lbs_lat_install" />
				<form:hidden  path="installAddress.longitude" id="lbs_lng_install" />
				<form:hidden  path="installAddress.floorNumber" id="lbs_floorNumber_install" />
				<%-- <form:hidden  path="installAddress.physicalLocation1" id="lbs_buildingId_install" />
				<form:hidden  path="installAddress.buildingName" id="lbs_buildingName_install" />--%>
				
				<form:hidden  path="installAddress.buildingId" id="lbs_buildingId_install" />
				<form:hidden  path="installAddress.physicalLocation1" id="lbs_buildingName_install" />
				<form:hidden  path="installAddress.zoneId" id="lbs_regionId_install" />
				<form:hidden  path="installAddress.zoneName" id="lbs_regionName_install" />
				<form:hidden  path="installAddress.city" id="lbs_city_install" />
				<form:hidden  path="installAddress.state" id="lbs_state_install" />
				<form:hidden  path="installAddress.country" id="lbs_country_install" />
				<form:hidden  path="installAddress.addressId" id="lbs_extAddressId_install" />
				<form:hidden  path="installAddress.campusId" id="lbs_campusId_install" />
				<form:hidden  path="installAddress.campusName" id="lbs_campusName_install" />
				<%--<form:hidden  path="installAddress.physicalLocation2" id="lbs_floorId_install" />
				<form:hidden  path="installAddress.floorName" id="lbs_floorName_install" /> --%>
				
				<form:hidden  path="installAddress.floorId" id="lbs_floorId_install" />
				<form:hidden  path="installAddress.physicalLocation2" id="lbs_floorName_install" />
				<form:hidden  path="installAddress.postalCode" id="lbs_zipCode_install" />
				
				<%-- Below section is for cleansed address --%>
				<form:hidden  path="installAddress.addressLine2" id="lbs_address2_install" />
				<form:hidden  path="installAddress.stateProvince" id="lbs_stateProvince_install" />
				<form:hidden  path="installAddress.storeFrontName" id="lbs_storeFrontName_install" />
				<form:hidden  path="installAddress.physicalLocation3" id="lbs_physicalLocation3_install" />
				<form:hidden  path="installAddress.district" id="lbs_district_install" />
				<form:hidden  path="installAddress.countryISOCode" id="lbs_countryISOCode_install" />
				<form:hidden  path="installAddress.region" id="lbs_region_install" />
				<form:hidden  path="installAddress.stateCode" id="lbs_stateCode_install" />
				<form:hidden  path="installAddress.county" id="lbs_county_install" />				
				
				<form:hidden  path="installAddress.addressName" id="lbs_addressName_install" />
				
				<form:hidden  path="installAddress.lbsAddressFlag" id="lbs_AddressFlag_install" />
                <form:hidden  path="installAddress.levelOfDetails" id="lbs_addressLOD_install" />
                <form:hidden  path="installAddress.floorLevelOfDetails" id="lbs_floorLOD_install" />
				<%-- multiselect-install address ends --%>
				
				<%-- changes for LBS 1.5 Placement HW Order --%>
                <input type="hidden"  name="account.accountId" id="lbs_accountId" />
                <input type="hidden"  name="account.accountName" id="lbs_accountName" />
                <input type="hidden"  name="soldtoNumber" id="lbs_soldtoNumber" /> 
                 <input type="hidden"  name="contractNumber" id="lbs_contractNumber" />
                 <input type="hidden"  name="agreementName" id="lbs_agreementName" /> 
                 <input type="hidden"  name="agreementId" id="lbs_agreementid" /> 
                 <input type="hidden"  name="showPrice" id="lbs_showPrice" /> 
                 <input type="hidden"  name="creditCardFlag" id="lbs_creditCardFlag" /> 
                 <input type="hidden"  name="poFlag" id="lbs_poFlag" />                          
                <input type="hidden"  name="source" id="lbs_source" />
                 
                
				<%--Ends changes for LBS 1.5 Placement HW Order --%>
               <input type="hidden" name="backJson" id="backJsonParam"/>
			   
			   <%--Changes for multi select start--%>
				<div id="multiSelectContent">

				</div>
				<%--Changes for multi select ends--%>
				<jsp:include page="multiSelectForm.jsp"></jsp:include>
</form:form>
<input type="hidden" id="gridFilterValues" name="gridFilterValues"/>
<div style="display: none;">
<div id="dialogAddress"></div>
</div>

<div id="dialog_meterReads" style="display: none">
<jsp:include page="/WEB-INF/jsp/common/pageCountPopUp.jsp"/>
</div>

<%-- 
<div id="totalContent">
<jsp:include page="/WEB-INF/jsp/common/accountSelection.jsp"/>
<script>
function setAccount(accountId,vendorAccountId,isCreateClaimFlag,isViewOrderFlag,rowId,isRequestExpedite,soldToList,showPrice,poFlag,creditCardFlag,splitterFlag,salesOrg,contractNumber,contractLine){
	setAccountInformation(accountId,accountGrid.cellById(rowId,3).getValue()); 
}
</script>
</div>
--%>
<div id="dynamicScript">
<script>
<%-- Added for LBS account Selection --%>
var originalFunc=window.setAccount;
window.setAccount=function (accountId,vendorAccountId,isCreateClaimFlag,isViewOrderFlag,rowId,isRequestExpedite,soldToList,showPrice,poFlag,creditCardFlag,splitterFlag,salesOrg,contractNumber,contractLine){
	setAccountInformation(accountId,accountGrid.cellById(rowId,3).getValue(),soldToList,contractNumber,accountGrid.cellById(rowId,5).getValue(),accountGrid.cellById(rowId,4).getValue(),showPrice,creditCardFlag,poFlag); 
};
</script>
</div>
<script>

//QA URL 
encryptionObj.setLBSFormPostURL("${fleetMgmtForm.endPointURL}?lang=<%=request.getLocale()%>");
//Dev URL
//encryptionObj.setLBSFormPostURL("https://uamdevmpsproxy-env.elasticbeanstalk.com/mps/assetMap");

//encryptionObj.setLBSFormPostURL("https://uamapp02.ad.rds.lexmark.com:8443/uam-data-processor/mps/assetMap");
//QA URL
encryptionObj.setEncryptLBSFormPostURL("${fleetMgmtForm.endPointURL}?accountChange=true&lang=<%=request.getLocale()%>");
//DEV URL
//encryptionObj.setEncryptLBSFormPostURL("https://uamdevmpsproxy-env.elasticbeanstalk.com/mps/assetMap?accountChange=true");

function breadCrump(){
	this.paramNames=["home","map","account","country","state","city","bookmarkedAssets","device"];
	this.paramValues=["Home","Map View","${fleetMgmtForm.mdmId}","","","","",""];
	this.paramDisplayValues=["<spring:message code='lbs.label.Home'/>","<spring:message code='lbs.label.mapview'/>","${fleetMgmtForm.companyName}","","","","",""];
}
breadCrump.prototype={
		updateBreadCrump:function(){
			var html="";		
			for(var i=0;i<this.paramNames.length;i++){
				if(this.paramValues[i]!=null && this.paramValues[i]!=""){
					if(i==0 || i==1){
						html+=(this.paramDisplayValues[i]+" > ");
					}else{
						html+=("<a onClick=\"updateJSON("+i+")\">"+this.paramDisplayValues[i]+"</a>"+" > ");	
					}
					
				}
			}
			html=html.substring(0,html.length-2);
			$('#breadCrumpMap').html(html);
		},
		updateCountry:function(c,d){
			var index=this.paramNames.indexOf("country");
			this.paramDisplayValues[index]=c;
			this.paramValues[index]=d;
			this.resetRestFields(index);
		},
		updateState:function(c,d){
			var index=this.paramNames.indexOf("state");
			this.paramDisplayValues[index]=checkAndSetState(c);
			this.paramValues[index]=d;
			this.resetRestFields(index);
		},
		updateCity:function(c,d){
			var index=this.paramNames.indexOf("city");
			this.paramDisplayValues[index]=c;
			this.paramValues[index]=d;
			this.resetRestFields(index);
		},
		
		getParamVal:function(index){
			return this.paramValues[index];
		},
		getCountry:function(){
			return this.paramValues[this.paramNames.indexOf("country")];
		},
		getState:function(){
			return this.paramValues[this.paramNames.indexOf("state")];
		},
		getAccount:function(){
			var i=this.paramNames.indexOf("account");
			return {id:this.paramValues[i],name:this.paramDisplayValues[i]};
		},		
		updateBookmarkedAssets:function(c){
			var index=this.paramNames.indexOf("bookmarkedAssets");
			this.paramDisplayValues[index]=c;
			this.resetRestFields(index);
		},
		updateDevice:function(c){
			var index=this.paramNames.indexOf("device");
			this.paramDisplayValues[index]=c;
			this.resetRestFields(index);
		},
		updateAccount:function(acid,name){
			var index=this.paramNames.indexOf("account");
			this.paramDisplayValues[index]=name;
			this.paramValues[index]=acid;
			this.resetRestFields(index);
		},
		resetRestFields:function(index){
			for(var i=(index+1);i<this.paramNames.length;i++){
				this.paramValues[i]="";
				this.paramDisplayValues[i]="";
			}
		}
		
};


var breadCrumpObject=new breadCrump();
var navigation="";
function updateJSON(index){
	var country=null,state=null,city=null,account=null;
	
	breadCrumpObject.resetRestFields(index);
	breadCrumpObject.updateBreadCrump();
	
	if(index==3){
		<%-- country breadCrump Clicked --%>
		country=breadCrumpObject.getParamVal(index);
		$('#selectCountry').val(country);
		$('#selectState,#selectCity').val('');
	}else if(index==4){
		<%-- state breadCrump Clicked --%>
		country=breadCrumpObject.getCountry();
		state=breadCrumpObject.getParamVal(index);
		$('#selectCountry').val(country);
		$('#selectState').val(state);
		$('#selectCity').val('');
	}else if(index==5){
		<%-- City breadCrump Clicked --%>
		country=breadCrumpObject.getCountry();
		state=breadCrumpObject.getState();
		city=breadCrumpObject.getParamVal(index);
		$('#selectCountry').val(country);
		$('#selectState').val(state);
		$('#selectCity').val(city);
	}else if(index==2){
		account=breadCrumpObject.getAccount();
		initAccountInfor(account.id,account.name);
	}
	
	filtersObj.setBreadCrumpCountry(country);
	filtersObj.setBreadCrumpState(state);
	filtersObj.setBreadCrumpCity(city);
	
	
	//alert(JSON.stringify(siebelJSON,checkValue));
	showMapBtnClicked();//Submit the form for LBS
	hideOverlay();
}

function setbreadCrumpFromDB(parsedObject){
	if(parsedObject==null)return;
	breadCrumpObject.updateCountry(("ctry" in parsedObject["address"])==true?parsedObject["address"]["ctry"]:"",("ctry" in parsedObject["address"])==true?parsedObject["address"]["ctry"]:"");
	breadCrumpObject.updateState(("state" in parsedObject["address"])==true?parsedObject["address"]["state"]:"",("state" in parsedObject["address"])==true?parsedObject["address"]["state"]:"");
	breadCrumpObject.updateCity(("cty" in parsedObject["address"])==true?parsedObject["address"]["cty"]:"",("cty" in parsedObject["address"])==true?parsedObject["address"]["cty"]:"");
}

var bookMarkedAssets=[];
function loadBookmarkedAssets(){
	$.getJSON("${retrieveDeviceBookmarkVar}",function(response){
		bookMarkedAssets=response;		
	});
}


function showPopupMessages(message,callback){
	jConfirm(message, "", function(confirmResult) {
		if(confirmResult)
		callback(confirmResult);
	});
}
function showPopupMessagesOPS(message,callback){
	jConfirm(message, "", function(confirmResult) {
		callback(confirmResult);
	});
}

	
	
	preferenceObject.setPreferenceObject("${fleetMgmtForm.filterPreferences}");
	
	jQuery(document).ready(function() {
			
		bindHashChange();
		
		
		parseSettings(preferenceObject.getPreferenceObject());//This is for loading the filters set by user
		setbreadCrumpFromDB(preferenceObject.getPreferenceObject());
		encryptionObj.setEncryptionFlag(true);
		
		breadCrumpObject.updateBreadCrump();<%--ADded for breadcrump --%>
		checkAndSetCancelForDate();//This is for showing cancel icon for date range filters
		setTimeout(function(){
			loadBookmarkedAssets();<%--Load all device list --%>
		}, 2000);
		
		setValuesToObjects(true);showMapBtnClicked();hideOverlay();//Added temporarily
		
		c.appendHtmlPref(preferenceObject.generatePrefHtml());
		jQuery('#actionChangeAccount').click(function(){
					showAccountPopup();//method declared in this page.. below...			
		});
		jQuery('#actionCreateRequest').click(function(){
			popupDialog();//method declared in subTab.jsp			
		});
		
	    jQuery("#applyFilter").click(function(){
	    	if($('#left-nav-Div').css('display')!='none'){
	    		hideNav();<%-- Hide left navigation --%>	
	    	}	    	
	    	if(!validateInputs()){
	    		return;
	    	}
	    	setValuesToObjects(false);
	    	<%-- This call is for saving the user selected options this is NOT!! preference --%>
	    	<%--Commented for defect 17287 Don't save preference  
	    	if(preferenceObject.getIspreferenc()=="N" || preferenceObject.getIspreferenc()==""){
	    		setPreferenceToDB("${saveFilterURLNonPreference}","N");
	    	}--%>				
	    	showMapBtnClicked();
	       	hideOverlay();
	    	if(window.deviceStatus)
	       		deviceStatus.clearApplyFilter();
	       	placementObj.onChangeHandler();
	       	
	       
	    });	  
	    
	    jQuery('.createRequestMenu,.popupContactInfo,.additionalInfoContent').hide();
	   
	   
	    $(".additionalInfoContentLeft").mouseenter(function(){$(this).siblings('.popupContactInfo').css('display','inline-block');});
		$(".additionalInfoContentLeft").mouseleave(function(){$(this).siblings('.popupContactInfo').css('display','none');});
		
		
		
		
		
	});
	
	
	
	
	var visualizationObj=new Visualization();
	var htmlEleObj=new HtmlElement();
	htmlEleObj.setImageURL("${retrieveDeviceURLVar}");
	htmlEleObj.setEnableDragButtonId("but_enableDrag");
	
	var respProcessorObj=new ResponseProcessor();
	respProcessorObj.setHTMLElement(htmlEleObj);
	respProcessorObj.setVisualization(visualizationObj);
	
	var lbs=new LBS();
	lbs.setResponseProcessor(respProcessorObj);
	lbs.attachListner();
	
	
	
	
	
	jQuery('#but_enableDrag').click(function(){
		lbs.postMessage(visualizationObj);
	});
	var dialog;
	function openAddressPopup(){
		var url="${addressListPopUpUrl}&g="+(typeof dhtmlXGridObject=="function"?false:true);
		showOverlay();
		jQuery('#dialogAddress').load(url,function(){
			//alert(jQuery('#'+hyperlinkId).attr('href'));	
				dialog=jQuery('#content_addr').dialog({
					autoOpen: false,
					title: "<spring:message code='lbs.label.selectaddress'/>",
					modal: true,
					draggable: false,
					resizable: false,
					height: 500,
					width: 950,
					close: function(event,ui){
						hideOverlay();
						dialog=null;
						jQuery('#content_addr').remove();
						moveAssetFlag = 0;
						},
					open: function(event,ui){
						
						if(typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 1)
						{
							populateAddressListPopupVals();
							enableInputs();
							moveAssetLoadCountryList_popup();							
						}
						}
					});
				dialog.dialog('open');
				initializeAddressGrid();
				
				if((typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 0)
					|| typeof moveAssetFlag === 'undefined')
					jQuery("#createNewAddressButton").hide();
				});
	}
	
	function populateAddressListPopupVals()
	{		
		$("#content_addr #addrLine1").val($("#lbs_address").val());
		$("#content_addr #addrLine2").val("");
		$("#content_addr #officeNo").val("");
		$("#content_addr #cityPopup").val($("#lbs_city").val());
		$("#content_addr #zipCode").val($("#lbs_zipCode").val());
		
		$("#createNewAddressButton").prop("onclick", null);
		$("#createNewAddressButton").unbind("click");
		$("#createNewAddressButton").bind("click", moveAssetShowAddAddress);
		
		$("#btnSelect_addr").prop("onclick", null);
		$("#btnSelect_addr").unbind("click");
		$("#btnSelect_addr").bind("click", moveAssetSaveAddressFromPopupToPage);
		
		$("#cancelAddressButton").prop("onclick", null);
		$("#cancelAddressButton").unbind("click");
		$("#cancelAddressButton").bind("click", moveAssetCancelAddressPopup);
		
		$("#btn_cancel_validate").prop("onclick", null);
		$("#btn_cancel_validate").unbind("click");
		$("#btn_cancel_validate").bind("click", moveAssetCancelAddressPopup);
		
		$("#btn_cancel_saveAddress").prop("onclick", null);
		$("#btn_cancel_saveAddress").unbind("click");
		$("#btn_cancel_saveAddress").bind("click", moveAssetCancelAddressPopup);
	}
	
	function moveAssetLoadCountryList_popup()
	{		
		//Ajax call to load the country list
		jQuery.ajax({
			url:'${countryListPopulateURL}',
			beforeSend: function(){
						jQuery('#loadingImage_country').show();
					},
			success: function(countryListResult){
						jQuery('#loadingImage_country').hide();
						jQuery("#country_popup").append(countryListResult);	
						
						if(typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 1)
						{
							var lbsCntry;							
							
							if($("#lbs_country").val() === "USA" ||
								$("#lbs_country").val() === "US")
							{
								//lbsCntry = "UNITED STATES";
								lbsCntry = "US";
							}
							else
								lbsCntry = $('#lbs_country').val().toUpperCase();
							
							$('#content_addr #country_popup option').each(function()
							{	
								//if($(this).html().toUpperCase() === lbsCntry.toUpperCase())
								if($(this).val() === lbsCntry.toUpperCase())
								{
									$(this).prop('selected', true)
									moveAssetLoadStateList_popup();
									return;
								}								
							});							
						}
				}
		});
	}
	
	function moveAssetLoadStateList_popup()
	{
		<%-- Changes for MPS 2.1 House Number Added--%>
		if(jQuery('#country_popup').val()=="BR"){
			jQuery('#officeNo').val('');
			jQuery('#liofficeId').show();
		}
		else{
			jQuery('#liofficeId').hide();
		}
	<%-- Ends Changes for MPS 2.1 House Number Added--%>
		jQuery('#state_popup').empty();
		jQuery.ajax({
			url:'${stateListPopulateURL}',
			type:'POST',
			data: {
					countryCode:jQuery('#country_popup').val()
			      }, 
			beforeSend: function(){
					jQuery('#loadingImage_state').show();
				},
			success: function(stateListResult){
						var countryCode=jQuery('#country_popup').val();	
						
						if(stateListResult!=(countryCode+"<option></option>")){
							<%--Commented for MPS 2.1 as it will be disable by default.
							jQuery('#state_popup').show();
							 --%>
							jQuery('#state_popup').removeAttr('disabled');
							jQuery('#stateorzipmesg').show();
							jQuery('#helpIconRegion').show();
						}else{
							<%--Commented for MPS 2.1 as it will be disable by default.
							jQuery('#state_popup').hide();
							--%>
							jQuery('#state_popup').attr('disabled','disabled');
							
							<%--Commented for MPS 2.1 as it will be disable by default.
							jQuery('#stateorzipmesg').hide();
							jQuery('#helpIconRegion').hide();
							--%>
							<%--ends --%>
							}
						jQuery('#loadingImage_state').hide();
						if(stateListResult.substring(0,2)==countryCode){
							jQuery('#state_popup').empty();
							jQuery("#state_popup").append(stateListResult.substring(2));
							
							if(typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 1)
							{								
								$("#content_addr #state_popup option[value=" + $("#lbs_state").val() + "]").prop('selected', true);
							}
						}
						
						if(typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 1)
						{
							moveAssetFlag = 2;
						}
				}
		});
	}
	
	var moveAssetShowAddAddress = function() {
		showToolTip('helpIconPopup');
		jQuery('#buttonContact_popup').hide();
		jQuery('#button_popup').show();
		jQuery('#newAdd').show();
		jQuery('#update').show();
		jQuery('#check_popup').attr('checked',false);
		enableInputs();
		//load state and country list

		/*if(typeof moveAssetFlag === 'undefined'
				|| (typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 0))
		{
			
		}*/
		if($('#country_popup > option').length > 0
				&& typeof moveAssetFlag !== 'undefined'
				&& moveAssetFlag === 2)
			showCleansedaddress();
		else
			loadCountryList_popup();
			
		//change the dialog height
		jQuery('#content_addr').data('height.dialog','700px');
	};
	
	var moveAssetSaveAddressFromPopupToPage = function() {
		if(goForCleanseAddrFlg)
		{	
			goForCleanseAddrFlg = false;
			
			if(typeof moveAssetFlag !== 'undefined' && (moveAssetFlag === 1
					|| moveAssetFlag === 2))
			{
				submitCleansedAddressToChangePage(cleanseAddrLine1,cleanseAddrLine2,cleanseaddrCity,
						cleanseaddrStateProv,cleanseaddrCountry,cleanseaddrZipPostal,cleanseAddrStoreFrNm,
						cleanseOffice,cleanseDistrict,cleanseCountryISO,cleanseRegion,cleanseCounty,cleanseStateCode);
			}
			else
			{			
				addServiceAddressElement(null,null,cleanseAddrLine1,cleanseAddrLine2,cleanseaddrCity,
				cleanseaddrStateProv,null,cleanseaddrCountry,cleanseaddrZipPostal,
				cleanseAddrStoreFrNm,null, null, null,null,cleanseOffice, cleanseDistrict,null,cleanseCountryISO,cleanseRegion,cleanseStateCode);
				<%-- Changes for MPS phase 2--%>
				addRestFieldsOfCleanseAddress();
				<%-- ENDS --%>
			}
		}
		else{
		goForCleanseAddrFlg = false;
	
		<%-- Changes for MPS 2.1--%>
		addServiceAddressElement(null,null,jQuery('#addrLine1').val(),jQuery('#addrLine2').val(),
		jQuery('#cityPopup').val(),jQuery('#state_popup').val(),null,jQuery('#country_popup').val(),jQuery('#zipCode').val(),
		jQuery('#storeName').val(),null, null, null,null,jQuery('#officeNo').val(),null,null,null,null,null);
		<%--Ends--%>
		}
	};
	
	var moveAssetCancelAddressPopup = function() {
		if(typeof moveAssetFlag !== 'undefined' && 
				(moveAssetFlag === 1 || moveAssetFlag === 2))
			$("#cancelMoveRequest").click();
		dialog.dialog('close');
		siebelJSON.setDefaultArea($('#backInfo').val());
		showMapBtnClicked();
		hideOverlay();
	};
	
	function submitCleansedAddressToChangePage(cleanseAddrLine1,cleanseAddrLine2,cleanseaddrCity,
			cleanseaddrStateProv,cleanseaddrCountry,cleanseaddrZipPostal,cleanseAddrStoreFrNm,
			cleanseOffice,cleanseDistrict,cleanseCountryISO,cleanseRegion,cleanseCounty,
			cleanseStateCode,physicalLoca1,physicalLoca2,physicalLoca3,lbsAddressFlag,addressId,levelOfDetails)
	{
		//dialog.dialog('close');
		showOverlayPopup();
		$("#fleetMgmtForm #lbs_address").val(cleanseAddrLine1);
		$("#fleetMgmtForm #lbs_address2").val(cleanseAddrLine2);
		$("#fleetMgmtForm #lbs_city").val(cleanseaddrCity);
		$("#fleetMgmtForm #lbs_state").val(cleanseaddrStateProv);
		$("#fleetMgmtForm #lbs_country").val(cleanseaddrCountry);
		$("#fleetMgmtForm #lbs_zipCode").val(cleanseaddrZipPostal);
		$("#fleetMgmtForm #lbs_storeFrontName").val(cleanseAddrStoreFrNm);
		$("#fleetMgmtForm #lbs_physicalLocation3").val(cleanseOffice);
		$("#fleetMgmtForm #lbs_district").val(cleanseDistrict);
		$("#fleetMgmtForm #lbs_countryISOCode").val(cleanseCountryISO);
		$("#fleetMgmtForm #lbs_region").val(cleanseRegion);
		$("#fleetMgmtForm #lbs_stateCode").val(cleanseStateCode);
		$("#fleetMgmtForm #lbs_county").val(cleanseCounty);
		
		$("#fleetMgmtForm #lbs_AddressFlag").val(lbsAddressFlag);
		$("#fleetMgmtForm #lbs_extAddressId").val(addressId);
		$("#fleetMgmtForm #lbs_levelOfDetails").val(levelOfDetails);

		$("#fleetMgmtForm #lbs_buildingName").val(physicalLoca1);
		$("#fleetMgmtForm #lbs_floorName").val(physicalLoca2);
		$("#fleetMgmtForm #lbs_physicalLocation3").val(physicalLoca3);
		if (addAssetRedirect.assetLifeCycle!=null && addAssetRedirect.assetLifeCycle=="Shipped"){
			addAssetRequest(addAssetRedirect.assetId);
			return;
		}
		jQuery("#fleetMgmtForm").submit();
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	var mdmId="${fleetMgmtForm.mdmId}";//315000554
	var mdmLevel="${fleetMgmtForm.mdmLevel}";//Global
	var emailAddress="${fleetMgmtForm.emailAddress}";
	var backToMap="${fleetMgmtForm.backInfo}"!=""?true:false;
	//alert("${fleetMgmtForm.backInfo}");
    //var languageID="<%=request.getLocale().getCountry()%>";
	var	accountInfoSiebel,accountInfoDB,deviceInfoSiebel,deviceInfoDB,chlSiebel,chlDB,srInfoSiebel,srInfoDB,addressSiebel,addressDB,filtersObj,siebelJSON;
	var closePendingObj={
			"action": "hidePending",
			"item": "asset",
			"info": {
				setId:function(i){
					this["id"]=i;
				}
			}
			
			};
	var pendingObj={
			"action": "showPending",
			"item": "asset",
			"info": {
				setId:function(i){
					this["id"]=i;
				}
			}
			
			};
	var locHistory={
			"action": "showHistory",
			"item": "asset",
			"info": {
				setId:function(i){
					this["id"]=i;
				}
			},
			setCloseAction:function(){
				this["action"]="hideHistory";
			},
			setShowAction:function(){
				this["action"]="showHistory";
			}
	};
	
	
	var zoomProcessor={
			saveSession:false,
			refreshMap:false,
			zoomInfo:"",
			processResponse:function(){
				if(this.saveSession){
					$.ajax({			
						url:'${setZoomLevelToSession}',			
						type:'POST',
						data: {
							zoomLevelInfo:JSON.stringify(this.zoomInfo)	
						},
						success:function(){
							window.location.href=window.location.href.split("fleet-management")[0]+"grid-view";
							
						}
					});	
				}
				if(this.refreshMap){
					$('#backJsonParam').val(JSON.stringify(this.zoomInfo));
					jQuery("#fleetMgmtForm").attr("action", "fleet-management");
					$('#fleetMgmtForm').submit();
				}
				
			},
			postZoom:function(){
				var zoomAndCenterObj={
						"action": "sendZoomAndCenter",
						"item": "map",
						"info": {}
				};
				lbs.postMessage(zoomAndCenterObj);
			}
	};
	
	var prefernceFiltr=new PreferenceFilter();
	//This is for setting MDM ID and MDM Level for Account Information 
	accountInfoSiebel=new AccountInformation();
	accountInfoSiebel.setSiebelFieldValues(mdmId,mdmLevel);
	accountInfoDB=new AccountInformation();
	accountInfoDB.setDBFieldValues(mdmId,mdmLevel);
	
	//Ends This is for setting MDM ID and MDM Level for Account Information 
	var addressPopup,addressPrefPop;
	function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2,
			addressCity,addressState,addressProvince,addressCountry,addressPostCode,storeFrontName,physicalLoca1,physicalLoca2,physicalLoca3,isFavorite,
			officeNumber,district,county,countryIso,regionP,state,lbsAddressFlag,levelOfDetails){
		//alert("c.getParentContext()= "+c.getParentContext());
		if(c.getParentContext()=="${prefixId}"){
			addressPrefPop=new Address();//prefernceFiltr.getAddress();
			addressPrefPop.setAddressId(addressId);
			addressPrefPop.setAddressLine1(addressLine1);
			addressPrefPop.setAddressLine2(addressLine2);
			addressPrefPop.setCity(addressCity);
			var region="";if(addressState!=""){region=addressState;}else if(addressProvince!=""){region=addressProvince;}else if(district!=""){region=district;}else if(county!=""){region=county;}
			addressPrefPop.setState(region);
			addressPrefPop.setProvice(addressProvince);
			addressPrefPop.setCountry(addressCountry);
			addressPrefPop.setPostalCode(addressPostCode);
			dialog.dialog('close');
			c.__showAddress(addressPrefPop);
			c.__loadCoutrySateCitySiteBuilding(addressId);//Defined in mapFilters.jsp
			
		}else{
			if(typeof moveAssetFlag === 'undefined'
				|| (typeof moveAssetFlag !== 'undefined' && moveAssetFlag === 0))
			{
				addressPopup=new Address();//prefernceFiltr.getAddress();
				addressPopup.setAddressId(addressId);
				//addressPopup.setAddressName(addressName);
				addressPopup.setAddressLine1(addressLine1);
				addressPopup.setAddressLine2(addressLine2);
				addressPopup.setCity(addressCity);
				var region="";if(addressState!=""){region=addressState;}else if(addressProvince!=""){region=addressProvince;}else if(district!=""){region=district;}else if(county!=""){region=county;}
				addressPopup.setState(region);
				addressPopup.setProvice(addressProvince);
				addressPopup.setCountry(addressCountry);
				addressPopup.setPostalCode(addressPostCode);
				
				<%-- Below Code is for setting the form fields for maps request to send the address as comments. --%>
				setAddressFieldToMapRequest(addressPopup);
				<%-- ENDS for setting the form fields for maps request to send the address as comments. --%>
				dialog.dialog('close');
				showAddress(addressPopup);
				loadCoutrySateCitySiteBuilding(addressId);//Defined in mapFilters.jsp
			}
			else
			{
				if(lbsAddressFlag !== undefined && lbsAddressFlag.toLowerCase() != "true")
					lbsAddressFlag = "false";
				submitCleansedAddressToChangePage(addressLine1,addressLine2,addressCity,addressState,
						addressCountry,addressPostCode,storeFrontName,officeNumber,
						district,countryIso,regionP,county,state,physicalLoca1,physicalLoca2,physicalLoca3,lbsAddressFlag,addressId,levelOfDetails);
			}				
		}		
		
	}
	function showAddress(obj){
		$('#address_display_li').html(generateAddressDisplayLBSHTML(obj));
		if("aId" in obj){$('#addRessLink').text("<spring:message code='lbs.label.address.changeAddress'/>");}
		
	}
	
	
	
	
	function setValuesToObjects(isCalledFrmDB){
		
		/* isCalledFrmDB flag is used to check whether the settings are from DB or from the Front end
		 * isCalledFrmDB = False means apply filter button clicked and read from dropdown
		 * isCalledFrmDB = True means read from addressPopupValues i.e DB  
		 * */
		
		//To be called from Apply Filter button to populate the values
		
		//Populate the Device Information
		 deviceInfoSiebel=new DeviceInformation();
		 deviceInfoDB = new DeviceInformation();
		
		deviceInfoSiebel.setSiebelFieldValues();
		deviceInfoDB.setDBFieldValues();
		
		/*alert(JSON.stringify(deviceInfoSiebel));
		alert(JSON.stringify(deviceInfoDB));*/
		
		//Populate the SR information
		 srInfoSiebel=new SrRequestInformation();
		 srInfoDB=new SrRequestInformation();
		
		srInfoSiebel.setSiebelFieldValues();
		srInfoDB.setDBFieldValues();
		
		/*alert(JSON.stringify(srInfoSiebel));
		alert(JSON.stringify(srInfoDB));
		*/
		var isCHlOrAddress=$('#filterLocation').css('display')=='block'?"address":"chl";
		//Populate ADdress Information
		addressSiebel=new AddressInformation();
		addressDB=new AddressInformation();
		
		if(isCHlOrAddress=="address"){
			addressSiebel.setSiebelFieldValues(addressPopup,isCalledFrmDB);
			addressDB.setDBFieldValues(addressPopup,isCalledFrmDB);	
		}
		
		
		/*alert(JSON.stringify(addressSiebel));
		alert(JSON.stringify(addressDB));*/
		
		//Populate CHL information
		chlSiebel=new chl();
		chlDB=new chl();
		if(isCHlOrAddress=="chl"){
			encryptionObj.setEncryptionFlag(true);//Set this as newly encryption requireds
			chlSiebel.setSiebelFieldValues();
			chlDB.setDBFieldValues();
		}
		
		<%-- Below section is for MDM LEVEL Siebel L5!!! --%>
		if("${fleetMgmtForm.l5HeirarchyParentChain}" != ""){
			var l5HierarchyList="${fleetMgmtForm.l5HeirarchyParentChain}";
			chlSiebel.setL5UserInformation(l5HierarchyList);
			chlDB.setDBL5UserInformation(l5HierarchyList);
		}
		<%--Ends Below section is for MDM LEVEL Siebel L5!!! --%>
		
		filtersObj=new Filters();
		//filtersObj.setSR(srInfoSiebel);
		filtersObj.setRestFields(deviceInfoSiebel,addressSiebel,chlSiebel,srInfoSiebel,requestTypeObj);
		filtersObj.setPlacement(placementObj.getValue());
		<%-- This check will work for MDM ID field under device that will populate the accountinfoSiebel
		and not the DeviceInformation or filtersObj--%>
		
		accountInfoSiebel=new AccountInformation();
		accountInfoDB=new AccountInformation();
		if(filtersObj["mdmID"]!=null){
			//alert(filtersObj["mdmID"]);
			accountInfoSiebel.setSiebelFieldValues(filtersObj["mdmID"],'Siebel');
			accountInfoDB.setDBFieldValues(filtersObj["mdmID"],'Siebel');
			encryptionObj.setEncryptionFlag(true);
		}else{
			accountInfoSiebel.setSiebelFieldValues(mdmId,mdmLevel);
			accountInfoDB.setDBFieldValues(mdmId,mdmLevel);			
		}
		<%--ENDS OPS changes --%>
		
		
		siebelJSON=new SiebelJSON();
		siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
		siebelJSON.setPostMessageURL(location.href);
		siebelJSON.setFilters(filtersObj);
		siebelJSON.setMview();
		siebelJSON.setQueryId(emailAddress);
		//siebelJSON.setLanguage(languageID);
		
		<%-- Below section is for back to map --%>
		if(backToMap){
			siebelJSON.setDefaultArea("${fleetMgmtForm.backInfo}");
			backToMap=false;
		}
		
		
		
		//alert(JSON.stringify(siebelJSON,checkValue));
		//Setting the preference filter to save to DB information
		prefernceFiltr.setAddress(addressDB);
		prefernceFiltr.setDevice(deviceInfoDB);
		prefernceFiltr.setRequest(srInfoDB);
		prefernceFiltr.setAccountInfo(accountInfoDB);
		prefernceFiltr.setChl(chlDB);
		
		prefernceFiltr.setCHLOrAddress(isCHlOrAddress);
		//End Setting the preference filter to save to DB information
		
		//alert("final JSON "+JSON.stringify(siebelJSON));
		
	}
	
	function loadBookmarkedAssetsObj(){
		filtersObj=new Filters();
		filtersObj.setContactId("${fleetMgmtForm.contactId}");
		
		siebelJSON=new SiebelJSON();
		siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
		siebelJSON.setPostMessageURL(location.href);
		siebelJSON.setFilters(filtersObj);
		siebelJSON.setMview();
		siebelJSON.setQueryId(emailAddress);
		//siebelJSON.setLanguage(languageID);
	}
	
	function showMapBtnClicked(){
		
	  encryptionObj.setSiebelJSONString(JSON.stringify(siebelJSON,checkValue));
	  if(encryptionObj.getEncryptionFlag()==true){
		  $('#uam-mps-map-form').attr('action',encryptionObj.getEncryptLBSFormPostURL());
		  $.ajax({
				url:"${encryptJSONVar}&t="+new Date().getTime(),
				type:'POST',
				data:{"jsonString":encryptionObj.getSiebelJSONString()},
				success: function(response){
					$("#uam-mps-map-input").val(response);
					$("#uam-mps-map-form").submit();
					hideOverlay();
					},
			  failure: function(results){}
			});
		  encryptionObj.setEncryptionFlag(false);
	  }else{
		  //alert(JSON.stringify(siebelJSON,checkValue));
		  $('#uam-mps-map-form').attr('action',encryptionObj.getLBSFormPostURL());
		  $("#uam-mps-map-input").val(JSON.stringify(siebelJSON,checkValue));
		  $("#uam-mps-map-form").submit();
         

	  }
	 
	    
	    
	};
	
	
	function showAccountPopup(){
				showOverlay();
			if(accountGrid==null){
					initializeAccountGrid(showAccGridinPopup);
			}else{

					if(accountGrid.getAllRowIds()=="")
						reloadAccountPopupGrid();
					else{
						
			    		if(accountGrid.getAllRowIds() != "" ){
							if(accountGrid.getAllRowIds().split(',').length == 1){
			    	        	jQuery('#button'+accountGrid.getAllRowIds()).click();
			    		 	}else{
			    		 		showAccGridinPopup();
				    		 	}
						}else{
							showAccGridinPopup();
							}
						
						
						 
					}
						
				}
	}
	 
	var accountDialog;
	function showAccGridinPopup(){
		

		accountDialog=jQuery('#totalContent').dialog({
					autoOpen: false,
					modal: true,
					height: 460,
					width: 700,
					position: ['center','top'],
					open: function(){
							/*alert('in open');*/
							jQuery('#totalContent').show();
							$('#accountSelection_cancel_btn').click(function(){
								accountDialog.dialog('close');
							});
							//alert(jQuery('#dynamicScript').html());
							jQuery('#totalContent').append('<div id="dynaSc">'+jQuery('#dynamicScript').html()+'</div>');
							jQuery('#accountInitialize').show();
							jQuery('span.ui-dialog-title').text("<spring:message code="requestInfo.heading.accountSelection"/>");
			  			},
					close: function(event,ui){
	   				  	
	   					  hideOverlay();
	   					window.setAccount=originalFunc;
	   					jQuery('#dynaSc').remove();
	   					accountDialog=null;
	   					
					  	jQuery('#accountInitialize').hide();
					  	
					  	if(ajaxAccountSelection=="success")
					    {
					  		ajaxSuccessFunction();
					  	}
						}
				});
			
			jQuery(document).scrollTop('0');
			accountDialog.dialog('open');
			
		
	}
	ajaxSuccessFunction=function saveAccountDetails(){
		//alert('success func');
		};
	
		 

	
	
	
	function setAccountInformation(accId,accName,soldtoList,contractNumber,agreementName,agreementid,showPrice,creditCardFlag,poFlag){
		<%-- Added for LBS 1.5--%>
		
		if(isHardwarePage=="true"){
			isHardwarePage="false";
			placementObj.createHWorder(accId,accName,soldtoList,contractNumber,agreementName,agreementid,showPrice,creditCardFlag,poFlag);
			return;
		}
		<%-- ENDS for LBS 1.5--%>
		//This is for setting MDM ID and MDM Level for Account Information 
				initAccountInfor(accId,accName);
		//Ends This is for setting MDM ID and MDM Level for Account Information
		encryptionObj.setEncryptionFlag(true);//Set this as newly encryption requireds
		if(accountDialog!=null){
			//Close the dialog
			accountDialog.dialog('close');
			
			
		}else{
			// it means single account .. 
			var d=$('#successDiv').dialog({
				autoOpen: false,
				modal: true,
				height: 100,
				width: 400,
				resizable:false,
				position: ['center']
				
			});
			d.dialog('open');
		}
		hideOverlay();
		
		
		breadCrumpObject.updateAccount(accId,accName);
		breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
		
		filtersObj=new Filters();
				
		siebelJSON=new SiebelJSON();
		siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
		siebelJSON.setPostMessageURL(location.href);
		siebelJSON.setFilters(filtersObj);
		siebelJSON.setMview();
		siebelJSON.setQueryId(emailAddress);
		//siebelJSON.setLanguage(languageID);
		showMapBtnClicked();hideOverlay();	
		
		
	}
	
	function initAccountInfor(accId,accName){
		accountInfoSiebel=new AccountInformation();
		accountInfoSiebel.setSiebelAccountId(accId);
		accountInfoSiebel.setSiebelAccountName(accName);
		accountInfoDB=new AccountInformation();
		accountInfoDB.setDBAccountId(accId);
		accountInfoDB.setDBAccountName(accName);
	}
	
	
	
	
	
	var countryHTML=$('#selectCountry').html();
	function clearValuesFilter(){	
		if($('#left-nav-Div').css('display')!='none'){
    		hideNav();<%-- Hide left navigation --%>	
    	}	
		if(window.deviceStatus)
       		deviceStatus.clearApplyFilter();
		resetFilters(true);		
		setValuesToObjects(false);encryptionObj.setEncryptionFlag(true);showMapBtnClicked();hideOverlay();
	}
	function resetFilters(resetAccount){
		//Hide the error div
		$('#errorDiv').hide();		
		
		clearValuesInput(deviceFields.deviceIdInputIds);
		clearValuesInput(srFields.srInputIds);
		$('.placeholderContent').each(function(){
			var txt="";
			$(this).parent().parent().siblings('.options').each(function(){
				$(this).val([]);
			});
			$(this).parent().parent().siblings('.options').find('option').each(function(i){
				if(i==0){
					txt=$(this).text();
					return false;
				}
				
			});
			$(this).html(txt);
			
			//html('');//this call is for clearing the html of span in multiselect.
		});
		$('.listValue').each(function(){
			$(this).prop('checked', false);//this is for unchecking the the checkbox of multiselect.
		});
		
		
		
		clearValuesInput(addressFields.htmlInputIds);
		$('#address_display_li').html('');
		
		clearValuesInput(chlInformation.htmlInputIds);
		$('#chlNodeValueLabel').html('');
		
		<%--LBS 1.5 Placements --%>
		clearValuesInput([placementObj.htmlId]);
		<%--Ends LBS 1.5 Placements --%>
		//Set default values to input fields
		for(fields in allhtmlTextIdsObj){
			$('#'+fields).val(allhtmlTextIdsObj[fields]);
		}
		
		//Clear AddressLink reset to Select Address
		$('#addRessLink').text("<spring:message code='lbs.label.selectaddress'/>");
		
		//Clear address popup values
		addressPopup=null;
		
		//Clear Bread Crump
		breadCrumpObject.resetRestFields(2);
		breadCrumpObject.updateBreadCrump();
		
		//Reset country list
		$('#selectCountry').html(countryHTML);
		if(resetAccount){
			<%-- This is required since canned queries and saved filters won't have account information
			so keeping the last account information. --%>
		//Reset Account Info
		accountInfoSiebel=new AccountInformation();
		accountInfoSiebel.setSiebelFieldValues(mdmId,mdmLevel);
		accountInfoDB=new AccountInformation();
		accountInfoDB.setDBFieldValues(mdmId,mdmLevel);
		}
		//Remove the cancel icons of calendar
		$('.cancelIconCalendar').remove();
	}
	$('#clearFilter').click(function(){
		
		showPopupMessages("<spring:message code='lbs.loadMessage.filter'/>",clearValuesFilter);
		
	});
	//create Supplies Request
	function createSuppliesRequest(deviceID){

	
		
		jQuery('#assetId').val(deviceID);
		jQuery("#fleetMgmtForm").submit();
	}
	
	//create Service Request
	function createServiceRequest(deviceID){

		
		jQuery('#assetId').val(deviceID);
		jQuery("#fleetMgmtForm").attr("action", "${serviceRequestURL}");
		jQuery("#fleetMgmtForm").submit();
	}
	
	//Decommission Asset Request
	function decommissionAssetRequest(deviceID){

		
		jQuery('#assetId').val(deviceID);
		jQuery("#fleetMgmtForm").attr("action", "${decommissionAssetURL}");
		jQuery("#fleetMgmtForm").submit();
	}
	
	//Change Asset Request
	function changeAssetRequest(deviceID){

		
		jQuery('#assetId').val(deviceID);
		jQuery("#fleetMgmtForm").attr("action", "${changeAssetURL}");
		jQuery("#fleetMgmtForm").submit();
	}
	function addAssetRequest(deviceID){
		
		jQuery('#assetId').val(deviceID);
		jQuery("#fleetMgmtForm").attr("action", "${addAssetURL}");
		jQuery("#fleetMgmtForm").submit();
	}
	
	
	//Placements
	function addPlacementRequest(placementId){

	}
	function changePlacementRequest(placementId){

	}
	function decommissionPlacementRequest(placementId){
		showPopupMessagesOPS("Are you sure you want to decomission this placement?",function(b){
			var details={
					"placementId":$('#lbs_placementId').val()
					
			};
			if (b){
				$.ajax({
					url:"${removePlacementURL}&t="+new Date().getTime(),
					type:'POST',
					dataType:"json",
					data:details,
					success: function(response){
						if(response.message != "success"){
							jAlert("Falure in Placement Decomission ", "");
						}else{
							jAlert("Placement Decomissioned Successfully", "");
							$( "#popup_ok" ).click(function() {
								 zoomProcessor.refreshMap=true;
								 zoomProcessor.postZoom();
								});
							
						}							
						$('#cancelButton').click();
						hideOverlay();
						},
				  failure: function(results){
					  
				  }
				});
			}
			
		});
	}
	var moveAssetFlag = 0;
	function moveAssetRequest(){		
		
		//alert($.trim($("#fleetMgmtForm #lbs_extAddressId").val()));
		if($("#fleetMgmtForm #lbs_extAddressId").val() != undefined
		   && $.trim($("#fleetMgmtForm #lbs_extAddressId").val()).length > 0)
		{
			jQuery("#fleetMgmtForm").attr("action", "${moveAssetURL}");
			jQuery("#fleetMgmtForm").submit();
		}
		else
		{
			initOpenAddressPopup();
		}		
	}
	function initOpenAddressPopup(){
		jQuery("#fleetMgmtForm").attr("action", "${moveAssetURL}");
		
		$('#gridFilterValues').val(
				$("#lbs_address").val()+",,,"+				
				$("#lbs_city").val()+","+
				$("#lbs_state").val()+",,,,,"+
				$("#lbs_zipCode").val()+","+
				$("#lbs_country").val());		
		moveAssetFlag = 1;
		openAddressPopup();
	}
	
	
	
	//<a id="meterReadsCountLink" onclick="return openPopUp();" href="###">Update Page Counts</a>
	window.clearContentsOfpopup1=function(){
		pageCountPopup.dialog('close');
	};
	
	function validateInputs(){
		$('#errorDiv').hide();
		var html="<ul>";
		var valid=true;
		if($('#ipAddress').val()!=allhtmlTextIdsObj["ipAddress"] && !isIPAddress($('#ipAddress').val())){
			html+="<li>"+"<spring:message code='lbs.label.invalidipaddress'/>"+"</li>";
			valid=false;
		}
		<%-- commented for 20547
		if($('#serialNumber').val()!=allhtmlTextIdsObj["serialNumber"] &&!onlyAlphaNumeric($('#serialNumber').val())){
			html+="<li>"+"<spring:message code='lbs.label.invalidserialnumber'/>"+"</li>";
			valid=false;
		}
		if($('#deviceHostName').val()!=allhtmlTextIdsObj["deviceHostName"] && !onlyAlphaNumeric($('#deviceHostName').val())){
			html+="<li>"+"<spring:message code='lbs.label.invalidhostname'/>"+"</li>";
			valid=false;
		}
		if($('#agreement').val()!=allhtmlTextIdsObj["agreement"] && !onlyAlphaNumeric($('#agreement').val())){
			html+="<li>"+"<spring:message code='lbs.label.invalidagreement'/>"+"</li>";
			valid=false;
		}--%>
		if($('#requestNo').val()!=allhtmlTextIdsObj["requestNo"] && !isTel($('#requestNo').val())){
			html+="<li>"+"<spring:message code='lbs.label.invalidrequestnumber'/>"+"</li>";
			valid=false;
		}
		html+="</ul>";
		if(valid==false){
			$('#errorDiv').html(html).show();
			
		}
		return valid;
	}
	var isTwoYearHistoryFlag="false";
	function openHistoryPopup(type){
		if(type == -2)
			isTwoYearHistoryFlag="true";
			
		 var url='${viewDeviceHistoryPopup}&requestTypeStr='+requestHistoryObj.getGridType()+'&isTwoYearHistoryFlag='+isTwoYearHistoryFlag+"&inc="+(typeof dhtmlXGridObject=="function"?false:true);
		 
			showOverlay();
			jQuery('#deviceHistoryPopup').load(url,function(){
				dialog=jQuery('#content_history').dialog({
					autoOpen: false,
					//title: jQuery('#'+hyperlinkId).attr('title'),
					modal: true,
					draggable: false,
					resizable: false,
					height: 500,
					width: 950,
					close: function(event,ui){
						hideOverlay();
						dialog=null;
						showSelect();
						jQuery('#content_history').remove();
						}
					});
				jQuery(document).scrollTop(0);
				dialog.dialog('open');
				//initializeAddressGrid();
				
				});
			return false;
		}
		
	
	
	function bindHashChange(){
		
		$(window).bind('hashchange', function() {
			//alert(window.location.hash);
			
			if(window.location.hash=="" && navigation=="viewPendingMove"){
				//check for old hash to close
				//alert(JSON.stringify(closePendingObj));
				lbs.postMessage(closePendingObj);
				hidePendingMove();
				showNav();<%--Show the left nav with old data --%>
				navigation="";
			}
			if(window.location.hash=="" && navigation=="viewLocationHistory"){
				//check for old hash to close
				//alert(JSON.stringify(closePendingObj));
				locHistory.setCloseAction();
				lbs.postMessage(locHistory);
				hideLocationHistory();
				showNav();<%--Show the left nav with old data --%>
				navigation="";
			}
			 
			
			
			
		});
	}
	
	function openPopUp(assetRowId, serialNumber, ipAddress, productLine, assetTag){
		zoomProcessor.postZoom();
		multiSelectAsset.addAsset([{
			"id":assetRowId,
			"serialNumber":serialNumber,
			"ipAddress":ipAddress,
			"customerDeviceTag":assetTag,
			"name":productLine
			
		}]);
		showPgCntMSlct();
	}
	<%--  Added for LBS 1.5 Page counts popup--%>
	var pgDialog;
	function showPgCntMSlct(){
		<%-- Update the html content firmst--%>
		assetPgCntsObj.createAssetHTML(multiSelectAsset.getAssetList());
		$(document).scrollTop(0);
		if(pgDialog){
			pgDialog.dialog('open');
			return;
		}
		$('#assetPageCounts-Multiple').append("<button onClick=\"pgDialog.dialog('close');clearConditions();\" class=\"button_cancel\">Back To Map</button>");
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
				handleMultiSelectCancel();
				multiSelectAsset.resetObj();
				clearConditions();
			}
			});
		pgDialog.dialog('open');		
		
	}
	<%--  ENDS for LBS 1.5 Page counts popup--%>
	<%-- Changes LBS 1.5 multi asset submit--%>
	function submitMultiAsset(){
		var multiAssetRequestType=$('#multiAssetRequestType').val();
		
		if(multiAssetRequestType=='move')
			{
			$("#fleetMgmtForm").attr("action", "${moveAssetURL}");
			}
		else if(multiAssetRequestType=='change')
			{
			$("#fleetMgmtForm").attr("action", "${changeAssetURL}");
			}
		else if(multiAssetRequestType=='decom')
			{
			$("#fleetMgmtForm").attr("action", "${decommissionAssetURL}");
			}
		
		console.log('multiAssetRequestType='+ $('#multiAssetRequestType').val());
		console.log('multiAssetList='+ $('#multiAssetList').val());
		$("#fleetMgmtForm").submit();
	}
	<%-- Changes LBS 1.5 multi asset submit ENDS--%>
	
	<%-- Changes LBS 1.5 View Grid at Building level --%>
	
	var viewGridMsgs={viewGrid:"Show Grid",mapView:"Hide Grid",status:true};
	function viewGridAct(){
		
		
		filtersObj=new Filters();
		siebelJSON=new SiebelJSON();
		siebelJSON.defaultArea={};
		var bInfo=deviceInformations.getBuildingInfo();
		if(viewGridMsgs.status){
			viewGridMsgs.status=false;
			zoomProcessor.saveSession=false;
			zoomProcessor.postZoom();
			$('#ActionsMenu #viewGridActions').html(viewGridMsgs.mapView);
			//Added for View grid
			siebelJSON.optionalParameters.showGrid=true;
			siebelJSON.optionalParameters.suppressAssets=false;
			siebelJSON.optionalParameters.gridClick=true;
			siebelJSON.defaultArea.buildingId=bInfo.bId;//;
			if(bInfo.fId){
				siebelJSON.defaultArea.floorId=bInfo.fId;	
			}
		}else{
			viewGridMsgs.status=true;
			//accountInfoSiebel.setSiebelFieldValues("623331717","Global");
			$('#ActionsMenu #viewGridActions').html(viewGridMsgs.viewGrid);
			siebelJSON.defaultArea=	zoomProcessor.zoomInfo;
		}
			
		
		
		
		//Ends added for view Grid
		
		
		siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
		siebelJSON.setPostMessageURL(location.href);
		siebelJSON.setFilters(filtersObj);
		siebelJSON.setMview();
		siebelJSON.setQueryId(emailAddress);

		
		
	
		encryptionObj.setEncryptionFlag(true);showMapBtnClicked();hideOverlay();
	}
	var highlightAssetOnMap={
			   "action": "select",
			   "item": "asset",
			   "info": {
					setId:function(i){
						this["id"]=i;
					}
				}
			};
	function highlightAsset(assetId){	
		$('.deviceDetailsDiv').removeClass('greenBackground');
		$('#deviceDetails'+assetId).addClass('greenBackground');
		highlightAssetOnMap.info.setId(assetId);
		lbs.postMessage(highlightAssetOnMap);
	}
	
	function checkForwardAddAssetJSP(id){
		var srInfo=deviceInformations.getPlacementfromList(id).srArray;
		for(var i=0;i<srInfo.length;i++){
			var t=requestTypeObj.getRequestType(srInfo[i].type);
			if((t==7 || t==4)&& srInfo[i].count > 0){
				jAlert("There is already a Request in process", "");
				return;
			}
		}
		forwardToAddAssetJSP("addone",id);
	}
	
	function checkShowAccount(id){
		var srInfo=deviceInformations.getPlacementfromList(id).srArray;
		for(var i=0;i<srInfo.length;i++){
			var t=requestTypeObj.getRequestType(srInfo[i].type);
			
			if(t==6 && srInfo[i].count > 0){
				jAlert("There is already a Request in process", "");
				return;
			}
		}
		placementObj.showAccountPopup(id);
	}
	
	function forwardToAddAssetJSP(flow,value)
	{
		showOverlay();
		
		var address = deviceInformations.getAddress();
	
		$("#fleetMgmtForm #lbs_address").val(address.address);
		$("#fleetMgmtForm #lbs_city").val(address.city);
		$("#fleetMgmtForm #lbs_state").val(address.state);
		$("#fleetMgmtForm #lbs_country").val(address.country);
		$("#fleetMgmtForm #lbs_zipCode").val(address.zipcode);
		$("#fleetMgmtForm #lbs_buildingId").val(address.buildingId);
		$("#fleetMgmtForm #lbs_lbs_floorId").val(address.floorId);
		//$("#fleetMgmtForm #lbs_physicalLocation3").val("");	
		//$("#fleetMgmtForm #lbs_storeFrontName").val(cleanseAddrStoreFrNm);
		$("#fleetMgmtForm #lbs_AddressFlag").val(address.lbsAddressFlag);
		$("#fleetMgmtForm #lbs_extAddressId").val(address.extAddressId);
		$("#fleetMgmtForm #lbs_buildingName").val(address.buildingName);
		$("#fleetMgmtForm #lbs_floorName").val(address.floorName);
		$("#fleetMgmtForm #lbs_placementId").val(value);
		$("#fleetMgmtForm #lbs_accountId").val(address.accountId);	
		if(flow=='addone')
		{
			$("#fleetMgmtForm #lbs_addressLOD").val(address.addressLOD);
			jQuery("#fleetMgmtForm").attr("action", "${addAssetURL}");
		}
		else if(flow='addMultiple')
		{
			$("#fleetMgmtForm #lbs_addressLOD").val(address.addressLOD);
			//$("#fleetMgmtForm #lbs_AddressFlag").val(lbsAddressFlag);
			$("#fleetMgmtForm #lbs_extAddressId").val(address.extAddressId);
			jQuery("#fleetMgmtForm").attr("action", "${addMultiAssetURL}");
		}
		jQuery("#fleetMgmtForm").submit();
	}

	function setBackInfo(backDetails) 
	{
		//alert(JSON.stringify(backDetails));
		$('#backInfo').val(JSON.stringify(backDetails));
	}
	
function setMoveToAndInstallAddress(address)
{	//population for moveToAddress start
	
	$("#lbs_addressLOD").val(address.addressLOD);
	$("#lbs_extAddressId").val(address.extAddressId);
	$("#lbs_buildingName").val(address.buildingName);	
	$("#lbs_buildingId").val(address.buildingId);	
	$("#lbs_floorName").val(address.floorName);	
	$("#lbs_floorId").val(address.floorId);	
	$("#lbs_floorLOD").val(address.floorLOD);	
	$("#lbs_address").val(address.address);
	$("#lbs_city").val(address.city);	
	$("#lbs_state").val(address.state);	
	$("#lbs_zipCode").val(address.zipCode);	
	$("#lbs_country").val(address.country);	
	//$("#lbs_physicalLocation3").val("");	
	
	if(address.attributes != undefined){
		$("#lbs_addressName").val(address.attributes.addressName);	
		$("#lbs_storeFrontName").val(address.attributes.storeFrontName);
	}
	//population for moveToAddress End
	
	//population for installAddress start
	$("#lbs_addressLOD_install").val(address.addressLOD);
	$("#lbs_extAddressId_install").val(address.extAddressId);
	$("#lbs_buildingName_install").val(address.buildingName);	
	$("#lbs_buildingId_install").val(address.buildingId);	
	$("#lbs_floorName_install").val(address.floorName);	
	$("#lbs_floorId_install").val(address.floorId);	
	$("#lbs_floorLOD_install").val(address.floorLOD);	
	$("#lbs_address_install").val(address.address);
	$("#lbs_city_install").val(address.city);	
	$("#lbs_state_install").val(address.state);	
	$("#lbs_zipCode_install").val(address.zipCode);	
	$("#lbs_country_install").val(address.country);
	//$("#lbs_physicalLocation3_install").val("");
	
	

	if(address.attributes != undefined){
		$("#lbs_addressName_install").val(address.attributes.addressName);	
		$("#lbs_storeFrontName_install").val(address.attributes.storeFrontName);
	}	
	
	//population for installAddress End

	//population of account information starts
	$("#lbs_accountId").val(address.accountId);	
	
	if(address.attributes != undefined)
		$("#lbs_accountName").val(address.attributes.storeFrontName);
	//population of account information ends
}

function clearConditions(){
	deviceStatus.clearApplyFilter(false);
}

</script>
<div style="display: none;">
<div id="deviceHistoryPopup"></div> 
</div>
<jsp:include page="updatePageCounts-mulitple.jsp"/>