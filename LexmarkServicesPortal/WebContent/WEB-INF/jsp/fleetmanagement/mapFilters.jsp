<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>


<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/multiselect/multiselect.css"/>
<portlet:resourceURL var="stateListPopulateURL" id="getStateList"/>
<portlet:resourceURL var="cityListPopulateURL" id="getCity"/>
<portlet:resourceURL var="siteBuildingListPopulateURL" id="getSiteBuildingZone"/>
<portlet:resourceURL var="buildingListPopulateURL" id="getBuilding"/>
<portlet:resourceURL var="floorURL" id="getFloor"/>
<portlet:resourceURL var="zoneURL" id="getZone"/>
<portlet:resourceURL var="getAllLocationURL" id="getAllLocation"/>


<portlet:resourceURL var="saveCustomzieSettingsPopupURL" id="saveCustomzieSettingsPopup"/>
<portlet:resourceURL var="opsSaveCustomzieSettingsPopupURL" id="saveCustomzieSettingsPopupOPS"/>	


<portlet:resourceURL var="saveFilterURL" id="saveFilter"></portlet:resourceURL>
<portlet:resourceURL var="retrieveFilterURL" id="retrieveFilter"></portlet:resourceURL>
<portlet:renderURL var="showCHLTreePopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>
<c:set var="prefixId" value="prefPop" scope="request"/>
<c:set var="opsUser" value="${fleetMgmtForm.opsUser}"></c:set>
<script type="text/javascript" src="<html:rootPath/>js/multiselect/multiselect.js?version=2"></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div class="filterLBS" id="parentFilter">
<div id="divFilter" onClick="showOrHideFilter()"><span><spring:message code='lbs.label.filters'/></span><img id="arrow" src="<html:imagesPath/>arrow-down-grayed.png"/></div>
			<br/>
			<div id="filterDivs" style="display: none;">
				<div id="Filters"><img src="<html:imagesPath/>asc.gif"><span><spring:message code='lbs.label.filters'/></span>
				</div><br/>
				<div id="FiltersMenu">
					<span class="popup_arrow">
						<span class="popup_arrow-inner"></span>
					</span>
					<div id="filtersSetPreference" onclick='checkOption("setPref")'><spring:message code='lbs.label.setpreference'/></div>
					<div id="filtersLoadPreference" onclick='checkOption("loadPref")'><spring:message code='lbs.label.loadpreference'/></div>
					<div id="filterBookmarkedDevices" onclick='checkOption("bookmark")'><spring:message code='lbs.label.bookmarkedDevices'/></div>
					<span id="savedFiltersPrefPop" class="popupHeader popupSubHeader"><b>Saved Filters</b></span>
					<%--Below section is for canned queries --%>
					<span class="popupHeader popupSubHeader clearBoth"><b>Canned Queries</b></span><br/>
					
					
				</div>
				<div id="FilterDetails">
			<div id="filterLocation">
					<div class="filterHeader">
						<h3><spring:message code='lbs.label.location'/></h3>
						<div id="customizeLocation" onclick="loadPopupCustomize(this)" >
							<a><spring:message code='lbs.label.customize'/></a>
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" alt="Customize Grid" title="Customize Grid" />
							
						</div>
					</div>
					<div id="filterCountryStateCity">
						<ul>
							<li>
								<select id="selectCountry">
									<option value=""><spring:message code='lbs.label.country'/></option>
									${fleetMgmtForm.countryString}
								</select>
							</li>
							<li>
								<select id="selectState" >
								<option value=""><spring:message code='lbs.label.state'/></option>
								</select>
							</li>
							<li>
								<select id="selectCity" >
								<option value=""><spring:message code='lbs.label.city'/></option>
								</select>
							</li>
						</ul>
						<div id="selectAddress">
						<ul class="roDisplay"><li id="address_display_li"></li></ul>
						<a id="addRessLink" onClick="openAddressPopup()" class="cursor-pointer"><spring:message code='lbs.label.selectaddress'/></a>
						</div>
					</div>
					
					<div id="selectLocalAddress">
						<ul>
							<li>
								<select id="site">
								<option value=""><spring:message code='lbs.label.site'/></option>
								</select>
							</li>
							<li>
								<select id="bldng">
								<option value=""><spring:message code='lbs.label.building'/></option>
								</select>
							</li>
							<li>
								<select id="flr">
								<option value=""><spring:message code='lbs.label.floor'/></option>
								</select>
							</li>
							<li>
								<select id="zone">
								<option value=""><spring:message code='lbs.label.zone'/></option>
								</select>
							</li>
							<li>
								<select id="bldngType">
								<option value=""><spring:message code='lbs.label.buildingType'/></option>
								${fleetMgmtForm.buildingTypes}
								</select>
							</li>
						</ul>
						
					</div>
				</div>
				<div id="customerHierarchy">
					<div class="filterHeader">
						<h3><spring:message code='lbs.label.customerhierarchy'/></h3>
						<div id="customizeCustomerHierarchy" onclick="loadPopupCustomize(this)" >
							<a><spring:message code='lbs.label.customize'/></a>
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" alt="Customize Grid" title="Customize Grid" />
							
						</div>
					</div>						
						<a id="customerHierarchyDir" onclick="popUpChlTree()" class="cursor-pointer"><spring:message code='lbs.label.customerhierarchy'/></a><br/>
						<span id="chlNodeValueLabel"></span>						
					</div>
				
				<div id="filterDevice">
				<div class="filterHeader">
					<h3><spring:message code='lbs.label.device'/></h3>
					<div id="customizeDevice" onclick="loadPopupCustomize(this)" >
						<a><spring:message code='lbs.label.customize'/></a>
						<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" alt="Customize Grid" title="Customize Grid" />
						
					</div>
				</div>
				<div id="DeviceProperties">
					<ul>
						<li>
							<input id="serialNumber" type="text" name="SerialNumber" value="<spring:message code='lbs.label.serialno'/>" />
						</li>	
						<li>
							<input id="ipAddress" type="text" name="IPAddress" value="<spring:message code='lbs.label.ipaddress'/>" />
						</li>
					
						<li>
						<%-- Label changes done from Asset Sheet --%>
							
							
								
									<input id="productModel" type="text" name="productModel" value="<spring:message code='serviceRequest.label.productModel'/>" />
							
							
						</li>
				
						<li >
						<input id="assetProduct" type="text" name="assetProduct" value="<spring:message code='lbs.label.product'/>" />
							
						</li>
						
						
						
						
						<li>
							<div id="productType"></div>
							<%-- 
							<input id="productType" type="text" name="productType" value="<spring:message code='lbs.label.producttype'/>" />
							--%>
						</li>
						<li>
							<div id="productSeries"></div>
						</li>
						<li>
						<input id="brand" type="text" name="brand" value="<spring:message code='lbs.label.brand'/>" />
							
						</li>
						
						<li>
							
							<input id="costCenterAsset" type="text" name="costCenterAsset" value="<spring:message code='lbs.label.costcenter'/>" />
						</li>
						<li>
							<input id="departmentAsset" type="text" name="departmentAsset" value="<spring:message code='lbs.label.department'/>" />
							
						</li>
						<li>
							
							<select id="meterReadTypeAsset">
								<option value=""><spring:message code='lbs.label.meterreadtype'/></option>
								<c:forEach items="${meterReads}" var="requestStatus" varStatus = "status" >
									<option value="${requestStatus.key}">${requestStatus.value}</option>
								</c:forEach>																
							</select>			

						</li>
						<li>
							<input id="assetTag" type="text" name="assetTag" value="<spring:message code='lbs.label.assettag'/>" />
							
						</li>
						<%-- 
						<li>
							<div id="thirdpartyDevicesDiv"><input type="checkbox" id="thirdPartyDevices"/><span><spring:message code='lbs.label.thirdpartydevices'/></span></div>
						</li>
						<li>
							<div id="colorMono"></div>
						</li>
						<li>
							<div id="singleMFP"></div>
						</li>--%>
						<li>
							<input type="text" id="agreement" name="Agreement" value="<spring:message code='lbs.label.agreement'/>" />
						</li>
						<li>
							<input type="text" id="deviceHostName" name="DeviceHostName" value="<spring:message code='lbs.label.devicehostname'/>" />
						</li>
						<li class="width-100per">
						
						<ul id="assetInstallDateDiv" style="display: inline;"><%-- Don't remove this DIV  --%>					
								<li><input id="installDateFrom" type="text"  name="startDateFrom" value="<spring:message code='lbs.fleetmgmt.installDate.From'/>" readonly="readonly" />
								<img id="imgLocalizedBeginDateAsset" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon cursor-pointer" />
								</li>
								<li><input id="installDateTo" type="text"  name="endDateTo" value="<spring:message code='lbs.fleetmgmt.installDate.To'/>"  readonly="readonly"/>
								<img id="imgLocalizedEndDateAsset" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon cursor-pointer" />
								</li>
							</ul>
						<li>
						
						
						</ul>
				</div>
				
			</div>
				<div id="filterRequestsDiv">
				<div class="filterHeader">
					<h3><spring:message code='lbs.label.requests'/></h3>
					<div id="customizeRequests" onclick="loadPopupCustomize(this)" >
						<a><spring:message code='lbs.label.customize'/></a>
						<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" alt="Customize Grid" title="Customize Grid" />
						
					</div>
				</div>
				<div id="RequestDetails">
					<ul>
						<li>
							<input id="requestNo" type="text" name="RequestNo" value="<spring:message code='lbs.label.requestnumber'/>"/>
						</li>
						<li>
							
							<div id="requestType"></div>
						</li>
						<li>
						
							<div id="requestStatus"></div>	
						</li>
						<li>
							<input id="customerRef" type="text" name="customerRef" value="<spring:message code='lbs.label.customerreference'/>" />
						</li>
						
						<li>
							
							<div id="areaDrop"></div>
						</li>
						<li>
							
							<div id="subAreaDrop"></div>
						</li>
						<li class="width-100per">
							<ul id="dateRangeFilterContainer"><%-- Don't remove this DIV  --%>					
								<li><input id="startDateFilter" type="text"  name="startDate" value="<spring:message code='requestInfo.requestHistory.label.from'/>" readonly="readonly"/>
								<img id="imgLocalizedBeginDate" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon cursor-pointer" />
								</li>
								<li><input id="endDateFilter" type="text"  name="endDate" value="<spring:message code='requestInfo.requestHistory.label.to'/>" readonly="readonly"/>
								<img id="imgLocalizedEndDate" src="<html:imagesPath/>transparent.png" class="ui_icon_sprite calendar-icon cursor-pointer" />
								</li>
							</ul>
						</li>
					</ul>		
				</div>
			
			</div>
			</div>
				<div id="preferenceAndFilter">
						<input id="applyFilter" class="buttonBlue" type="button" value="<spring:message code='lbs.label.applyFilter'/>" />
						<input id="clearFilter" class="buttonGrey" type="button" value="<spring:message code='lbs.label.clearFilter'/>" />						
				</div>
			</div>
			<div style="display: none;" id="popupCustomize">
				<span class="popup_arrow">
					<span class="popup_arrow-inner"></span>
				</span>
				<div class="popupHeader">
					<span><b><spring:message code='lbs.label.customize'/></b></span>
				</div>
				<div id="loadCustomizeSelectors">
				</div>
			</div>
			<div id="errorCustomize"></div>
		</div>


<%--CHL TREE Starts!! --%>
<div style="display: none;" id="dialogChlTree"></div>
<div id="dynamicScriptChl">
<script>
var originalFuncChl=window.setValToPage;
window.setValToPage=function (){
	jQuery("#${prefixId}chlNodeValueLabel").html(cValue);
	
	//Bind the id & value with the form element	
	jQuery("#${prefixId}chlNodeId").val(cId);
	jQuery("#${prefixId}chlNodeValue").val(cValue);
	closeDialog(); 
};
</script>
</div>
<input type="hidden" id="chlNodeId"/>
<input type="hidden" id="chlNodeValue"/>
			
<script>

<%-- following input ids will have default value in the text boxes once user clicks on it 
the text will be removed. otherwise it will be shown
This object will be used in JSON creation the default values will be ignored...
--%>
function allhtmlTextIds(){
	this.serialNumber="<spring:message code='lbs.label.serialno'/>";//replace with spring message code
	this.installDateFrom="<spring:message code='lbs.fleetmgmt.installDate.From'/>";
	this.installDateTo="<spring:message code='lbs.fleetmgmt.installDate.To'/>";
	this.ipAddress="<spring:message code='lbs.label.ipaddress'/>";
	this.assetProduct="<spring:message code='lbs.label.product'/>";
	<c:if test="${opsUser}">
	this.modelType="<spring:message code='lbs.label.modeltype_family'/>";
	</c:if>
	this.brand="<spring:message code='lbs.label.brand'/>";
	this.costCenterAsset="<spring:message code='lbs.label.costcenter'/>";
	this.departmentAsset="<spring:message code='lbs.label.department'/>";
	this.assetTag="<spring:message code='lbs.label.assettag'/>";
	this.productModel="<spring:message code='serviceRequest.label.productModel'/>";
	this.agreement="<spring:message code='lbs.label.agreement'/>";
	this.deviceHostName="<spring:message code='lbs.label.devicehostname'/>";
	this.requestNo="<spring:message code='lbs.label.requestnumber'/>";
	this.customerRef="<spring:message code='lbs.label.customerreference'/>";
	this.startDateFilter="<spring:message code='requestInfo.requestHistory.label.from'/>";//localizeDate(convertDateToString(new Date().addDays(-15)));
	this.endDateFilter="<spring:message code='requestInfo.requestHistory.label.to'/>";//localizeDate(todayStr);
};
var allhtmlTextIdsObj=new allhtmlTextIds();
<%-- This is for binding the onBlur and onFocus events to text box--%>
for(fields in allhtmlTextIdsObj){
	$('#'+fields).focus(function(){
		if($(this).val()==allhtmlTextIdsObj[$(this).attr('id')]){
			$(this).val('');
		}
	});
	$('#'+fields).blur(function(){
		if($(this).val()==''){
			$(this).val(allhtmlTextIdsObj[$(this).attr('id')]);
		}
	});
}
<%-- 
Below object is used for showing the customize popup for dEvice filter
the key is the HTML ID and value is spring message code!
--%>
function deviceIdsHTML(){
	this.agreement="<spring:message code='lbs.label.agreement'/>";
	this.assetTag="<spring:message code='lbs.label.assettag'/>";
	this.brand="<spring:message code='lbs.label.brand'/>";
	this.costCenterAsset="<spring:message code='lbs.label.costcenter'/>";
	this.departmentAsset="<spring:message code='lbs.label.department'/>";
	this.deviceHostName="<spring:message code='lbs.label.devicehostname'/>";
	this.assetInstallDateDiv= "<spring:message code='lbs.label.installdate'/>";	
	this.ipAddress="<spring:message code='lbs.label.ipaddress'/>";
	this.meterReadTypeAsset="<spring:message code='lbs.label.meterreadtype'/>";
	this.productModel="<spring:message code='serviceRequest.label.productModel'/>";
	
	this.assetProduct="<spring:message code='lbs.label.product'/>";
	this.productSeries="<spring:message code='lbs.label.productseries'/>";
	this.productType="<spring:message code='lbs.label.producttype'/>";	
	this.serialNumber="<spring:message code='lbs.label.serialno'/>";
	//this.thirdpartyDevicesDiv="<spring:message code='lbs.label.thirdpartydevices'/>";

	
	//this.colorMono="<spring:message code='lbs.label.color_mono'/>";	
	//this.singleMFP="<spring:message code='lbs.label.single_mfp'/>";
		
};



var deviceIdsHTMLObj=new deviceIdsHTML();
function urlParams(){
	this.htmlInputIds=["selectCountry","selectState","selectCity","site","bldng","zone","flr"];
	this.defaultMessages=["<spring:message code='lbs.label.country'/>","<spring:message code='lbs.label.state'/>","<spring:message code='lbs.label.city'/>","<spring:message code='lbs.label.site'/>","<spring:message code='lbs.label.building'/>","<spring:message code='lbs.label.zone'/>","<spring:message code='lbs.label.floor'/>"];
	this.paramNames=["ctry","state","cty","site","bldng","","flr","extra"];
	this.setDefaultHTMl=function(){
		for(var i=0;i<this.htmlInputIds;i++){
			$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
		};
	};
	this.setDefaultHTMlAt=function(id){
		var index=this.htmlInputIds.indexOf(id);
		$('#'+id).html('<option value="">'+this.defaultMessages[index]+'</option>');
	};
	this.clearHtmlAfter=function(htmlId){
		var index=this.htmlInputIds.indexOf(htmlId);
		if(index!=-1){
			for(var i=(index+1);i<this.htmlInputIds.length;i++){
				$('#'+this.htmlInputIds[i]).html('<option value="">'+this.defaultMessages[i]+'</option>');
			};
		};
	};
	this.setDropParamsToObj=function(){
		for(var i=0;i<this.paramNames.length;i++){
			if(this.paramNames[i]!=""){
				this[this.paramNames[i]]=$('#'+this.htmlInputIds[i]).val();				
			};
			
		};
	};
	this.disableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).attr('disabled','disabled');
		};
	};
	this.enableAll=function(){
		for(var i=0;i<this.htmlInputIds.length;i++){
			$('#'+this.htmlInputIds[i]).removeAttr('disabled');
		};
	};
	
	
}
var urlParamsObj=new urlParams();

$('#selectCountry').change(function(){
	breadCrumpObject.resetRestFields(1);//Defined in defaultview.jsp
	breadCrumpObject.updateCountry($('#selectCountry option:selected').text(),$(this).val());//Defined in defaultview.jsp
	breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
	urlParamsObj.clearHtmlAfter($(this).attr('id'));
	if($(this).val()==""){
		return;
	}
	urlParamsObj.setDropParamsToObj();
	$(this).attr('disabled','disabled');
	getState(null);
});

function getState(p,c){
	var url=appendURLPrams("${stateListPopulateURL}",urlParamsObj);	
	$.getJSON(url,function(response){
		$('#selectCountry').removeAttr('disabled','');
		$('#selectState').append(response.state);
		if(response.city!=""){$('#selectCity').append(response.city);}
		$('#selectState').removeAttr('disabled','');
		if(p!=null){
			$('#selectState').val(p);
			
		}if(c!=null){
			$('#selectCity').val(c);
			
		}
			
	});
}

$('#selectState').change(function(){
	breadCrumpObject.updateState($('#selectState option:selected').text(),$(this).val());//Defined in defaultview.jsp
	breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
	urlParamsObj.clearHtmlAfter($(this).attr('id'));	
	if($(this).val()==""){
		return;
	}
	urlParamsObj.setDropParamsToObj();
	$(this).attr('disabled','disabled');
	getCity(null);
	
});

function getCity(p){
	
	var url=appendURLPrams("${cityListPopulateURL}",urlParamsObj);//"${cityListPopulateURL}&state="+$('#selectState').val()+"&ctry="+$('#selectCountry').val();
	$.get(url,function(response){	
		$('#selectState').removeAttr('disabled','');
		if(!isResponseBlank(response)){$('#selectCity').append(response);}
		
		if(p!=null){
			$('#selectCity').val(p);
		}
	});
}

$('#selectCity').change(function(){
	breadCrumpObject.updateCity($('#selectCity option:selected').text(),$(this).val());//Defined in defaultview.jsp
	breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
	urlParamsObj.clearHtmlAfter($(this).attr('id'));	
	if($(this).val()==""){
		return;
	}
	urlParamsObj.setDropParamsToObj();
	
	$(this).attr('disabled','disabled');
	getSiteBuildingZone(null,null,null);
});

function getSiteBuildingZone(site,building,zone){
	var url=appendURLPrams("${siteBuildingListPopulateURL}&frCal=true",urlParamsObj);
	$.getJSON(url,function(response){
		$('#selectCity').removeAttr('disabled','');
		$('#bldng').removeAttr('disabled','');
		$('#site').removeAttr('disabled','');
		$('#zone').removeAttr('disabled','');
		if(!isResponseBlank(response.site)){
			$('#site').append(response.site);			
		}
		if(site!=null){$('#site').val(site);}
		if(!isResponseBlank(response.building)){
			$('#bldng').append(response.building);			
		}
		if(building!=null){$('#bldng').val(building);}
		if(!isResponseBlank(response.zone)){
			$('#zone').append(response.zone);			
		}
		if(zone!=null){$('#zone').val(zone);}
	});
}

function getSite(p){
	var url=appendURLPrams("${siteBuildingListPopulateURL}&frCal=true",urlParamsObj);
	$.getJSON(url,function(response){
		$('#selectCity').removeAttr('disabled','');
		if(!isResponseBlank(response.site)){$('#site').append(response.site);}
		
		if(p!=null){
			$('#site').val(p);
		}
	});
}
$('#site').change(function(){
	urlParamsObj.setDefaultHTMlAt('bldng');	
	if($(this).val()==""){
		return;
	}
	urlParamsObj.setDropParamsToObj();
	
	$(this).attr('disabled','disabled');
	getBuilding();
});

function getBuilding(p){
	var url=appendURLPrams("${buildingListPopulateURL}",urlParamsObj);
	$.get(url,function(response){		
		$('#site').removeAttr('disabled','');
		if(!isResponseBlank(response)){$('#bldng').append(response);}
		
		if(p!=null){
			$('#bldng').val(p);
		}
	});
}

$('#bldng').change(function(){
	urlParamsObj.setDefaultHTMlAt('flr');
	if($(this).val()==""){
		return;
	}
	urlParamsObj.setDropParamsToObj();	
	
	$(this).attr('disabled','disabled');
	getFloor(null);
});
function getFloor(p){
	urlParamsObj["extra"]="blank";
	var url=appendURLPrams("${floorURL}",urlParamsObj);
	$.get(url,function(response){
		$('#bldng').removeAttr('disabled','');
		$('#flr').removeAttr('disabled','');
		if(!isResponseBlank(response)){$('#flr').append(response);}
		
		if(p!=null){
			$('#flr').val(p);
		}
	});	
}

function getZone(p){
	var url=appendURLPrams("${zoneURL}",urlParamsObj);
	$.get(url,function(response){	
		if(!isResponseBlank(response)){$('#zone').append(response);}
		
		
		$('#flr').removeAttr('disabled','');	
		$('#zone').removeAttr('disabled','');	
		if(p!=null){
			
			$('#zone').val(p);
		}
	});	
}
/*
$('#flr').change(function(){
		
	var url=appendURLPrams("${zoneURL}",urlParamsObj);//"${zoneURL}&flr="+$('#flr').val()+"&cty="+$('#selectCity').val()+"&ctry="+$('#selectCountry').val()+"&state="+$('#selectState').val();
	$.get(url,function(response){		
		$('#zone').html("<option value=''><spring:message code='lbs.label.zone'/></option>"+response);
		
		
	});	
});
*/
function appendURLPrams(url,paramsObj){
	var urlT=url;
	for(var i=0;i<paramsObj.paramNames.length;i++){
		if(paramsObj[paramsObj.paramNames[i]]!=null && paramsObj[paramsObj.paramNames[i]]!="")
			urlT+="&"+paramsObj.paramNames[i]+"="+encodeURI(paramsObj[paramsObj.paramNames[i]]);
	}
	
	return urlT+"&t="+new Date().getTime();
}


function loadCoutrySateCitySiteBuilding(addressId){
	urlParamsObj.clearHtmlAfter('selectCountry');
	urlParamsObj.setDefaultHTMlAt('selectCountry');
	//$('#selectCountry,#selectState,#selectCity,#site,#bldng,#flr,#zone').val('');
	urlParamsObj=new urlParams();
	//addressId="1-JTKIR30";
	//urlParamsObj["aid"]=addressId;
	
	var url=appendURLPrams("${getAllLocationURL}&frCal=true&aid="+addressId,urlParamsObj);//"${getAllLocationURL}&aid="+addressId+"&frCal=true";
	urlParamsObj.disableAll();
	
	
	$.getJSON(url,function(response){
		urlParamsObj.enableAll();
		
		if(!isResponseBlank(response.country)){$('#selectCountry').append(response.country);}
		if(!isResponseBlank(response.state)){$('#selectState').append(response.state);}
		if(!isResponseBlank(response.city)){$('#selectCity').append(response.city);}
		if(!isResponseBlank(response.site)){$('#site').append(response.site);}
		if(!isResponseBlank(response.building)){$('#bldng').append(response.building);}
		if(!isResponseBlank(response.zone)){$('#zone').append(response.zone);}
		
		//if(!isResponseBlank(response.floor)){$('#flr').append(response.floor);}
		
		//This condition will be checked if filter values are from DB and when selected from address Popup
		if("ctry" in addressPopup){
			$('#selectCountry').val(addressPopup["ctry"]);
		}else{
			$('#selectCountry').val('');
		}
		if("state" in addressPopup){
			if(addressPopup["state"].indexOf("^")!=-1){
				$('#selectState').val(addressPopup["state"]);
			}else{
				var tokens=["s","p","c","d"];
				for(var i=0;i<tokens.length;i++){
					if($('#selectState').val()==""){
						$('#selectState').val(addressPopup["state"]+"^"+tokens[i]);		
					}else{
						break;
					}
				}
			}		
			
			
		}if("cty" in addressPopup){
			$('#selectCity').val(addressPopup["cty"]);
		}if("sNm" in addressPopup){
			$('#site').val(addressPopup["sNm"]);
		}
		if("bNm" in addressPopup){
			$('#bldng').val(addressPopup["bNm"]);			
		}
		
		if("zn" in addressPopup){
			$('#zone').val(addressPopup["zn"]);			
		}
		
		if("fNm" in addressPopup){
			// This is for get floor since its a separate call 
			urlParamsObj.setDropParamsToObj();
			getFloor(("fNm" in addressPopup)==true?addressPopup["fNm"]:null);	
			//Ends floor call here 
			
		}
		
		
		
		breadCrumpObject.updateCountry(addressPopup["ctry"],addressPopup["ctry"]);
		breadCrumpObject.updateState(addressPopup["state"],addressPopup["state"]);
		breadCrumpObject.updateCity(addressPopup["cty"],addressPopup["cty"]);//Defined in defaultview.jsp
		breadCrumpObject.updateBreadCrump();//Defined in defaultview.jsp
	});
	
}

function loadAllDropDowns(addressObj){
	
	urlParamsObj.clearHtmlAfter('selectCountry');
	urlParamsObj.setDropParamsToObj();
	urlParamsObj.disableAll();
	
	if("ctry" in addressObj){
		urlParamsObj["ctry"]=addressObj["ctry"];
		$('#selectCountry').val(addressObj["ctry"]);		
	}
	$('#selectCountry').removeAttr('disabled','');
	if("ctry" in addressObj){		
		getState(("state" in addressObj)==true?addressObj["state"]:null,("cty" in addressObj)==true?addressObj["cty"]:null);		
	}else{
		$('#selectState').removeAttr('disabled');
	}
	if("state" in addressObj){
		urlParamsObj["state"]=addressObj["state"];
		getCity(("cty" in addressObj)==true?addressObj["cty"]:null);
	}else{
		$('#selectCity').removeAttr('disabled');
	}
	if("cty" in addressObj){
		urlParamsObj["cty"]=addressObj["cty"];
		getSiteBuildingZone(("sNm" in addressObj)==true?addressObj["sNm"]:null,("bNm" in addressObj)==true?addressObj["bNm"]:null,("zn" in addressObj)==true?addressObj["zn"]:null);
	}else{
		$('#selectCity').removeAttr('disabled','');
		$('#site').removeAttr('disabled','');
		$('#bldng').removeAttr('disabled','');
		$('#zone').removeAttr('disabled','');
	}
	if("bNm" in addressObj){
		urlParamsObj["bldng"]=addressObj["bNm"];
		getFloor(("fNm" in addressObj)==true?addressObj["fNm"]:null);		
	}else{
		$('#flr').removeAttr('disabled');
	}
	
	
}





var productTypeArray = [];
productTypeArray.push({"value":"","displayValue":"<spring:message code='lbs.label.producttype'/>"});

<c:forEach items="${productType}" var="requestStatus" varStatus = "status" >
productTypeArray.push({"value":"${requestStatus.key}","displayValue":"${requestStatus.value}"});
</c:forEach>

var productSeriesArray = [];
productSeriesArray.push({"value":"","displayValue":"<spring:message code='lbs.label.productseries'/>"});
<c:forEach items="${productSeries}" var="requestStatus" varStatus = "status" >
productSeriesArray.push({"value":"${requestStatus.key}","displayValue":"${requestStatus.value}"});
</c:forEach>



var requestTypeArray = [];
requestTypeArray.push({"value":"","displayValue":"<spring:message code='lbs.label.requesttype'/>"});
<c:forEach items="${srType}" var="requestStatus" varStatus = "status" >
requestTypeArray.push({"value":"${requestStatus.key}","displayValue":"${requestStatus.value}"});
</c:forEach>

var requestStatusArray = [];
requestStatusArray.push({"value":"","displayValue":"<spring:message code='lbs.label.requeststatus'/>"});
<c:forEach items="${srStatus}" var="requestStatus" varStatus = "status" >
requestStatusArray.push({"value":"${requestStatus.key}","displayValue":"${requestStatus.value}"});
</c:forEach>

var areaArray = [];
areaArray.push({"value":"","displayValue":"<spring:message code='lbs.label.area'/>"});
<c:forEach items="${srArea}" var="requestStatus" varStatus = "status" >
areaArray.push({"value":"${requestStatus.key}","displayValue":"${requestStatus.value}"});
</c:forEach>

var subAreaArray = [];
subAreaArray.push({"value":"","displayValue":"<spring:message code='lbs.label.subarea'/>"});
<c:forEach items="${srSubArea}" var="requestStatus" varStatus = "status" >
subAreaArray.push({"value":"${requestStatus.key}","displayValue":"${requestStatus.value}"});
</c:forEach>






var width="90%";
	<%-- Below objects are for Device Filters !!  --%>
	//var modelObj=new MultiSelect('productModel',width,modelArray);
	//var assetProductObj=new MultiSelect('assetProduct',width,assetProductArray);
	//var modelTypeObj=new MultiSelect('modelType',width,modelTypeArray);
	var productTypeObj=new MultiSelect('productType',width,productTypeArray);
	var productSeriesObj=new MultiSelect('productSeries',width,productSeriesArray);
	
	//var brandObj=new MultiSelect('brand',width,brandArray);
	
	//var costCenterAssetObj=new MultiSelect('costCenterAsset',width,costCenterAssetArray);
	//var departmentAssetObj=new MultiSelect('departmentAsset',width,departmentAssetArray);
	//var meterReadTypeAssetObj=new MultiSelect('meterReadTypeAsset',width,meterReadTypeAssetArray);
	
	//var assetTagObj=new MultiSelect('assetTag',width,assetTagArray);
	
	<%-- Below objects are for Employee only !! --%>
	<%--<c:if test="${fleetMgmtForm.employee}">
		var LXKAssetTagObj=new MultiSelect('LXKAssetTag',width,LXKAssetTagArray);
		var assetLifeCycleObj=new MultiSelect('assetLifeCycle',width,assetLifeCycleArray);
		var devicePhaseObj=new MultiSelect('devicePhase',width,devicePhaseArray);
		var hardwareStatusObj=new MultiSelect('hardwareStatus',width,hardwareStatusArray);
	</c:if>--%>
	<%-- Below objects are for Request Filters !!  --%>
	var reqTypeObj=new MultiSelect('requestType',width,requestTypeArray);
	
	var reqStatusObj=new MultiSelect('requestStatus',width,requestStatusArray);
	
	var areaDropObj=new MultiSelect('areaDrop',width,areaArray);
	
	var subAreadDropObj=new MultiSelect('subAreaDrop',width,subAreaArray);
		
	
	


jQuery(document).ready(function(){
	
	//Show the fields that are there in customize retireived from DB
	loadCustomizeSetting("${fleetMgmtForm.defaultFieldsView}");//method declared in LbsService.js
	
	$('#ActionsMenu,#popupCustomize,#FiltersMenu').css('display','none');
	//#filterDivs,#customerHierarchy,#assetEntitlement,#requestStatus,#dateRangeFilterContainer,#customerRef,#subAreaDrop,#areaDrop
	$( "#Filters" ).click( "mouseenter", function() {$('.filterLBS #FiltersMenu').css('display','block');});
	$( "#Filters" ).live( "mouseleave", function() {FiltersTimer=setTimeout(function(){$('.filterLBS #FiltersMenu').css('display','none');},3000)});
	$(".filterLBS #FiltersMenu").live("mouseenter",function(){if(FiltersTimer!=null){clearTimeout(FiltersTimer);}$('.filterLBS #FiltersMenu').css('display','block');});
	$(".filterLBS #FiltersMenu").live("mouseleave",function(){$('.filterLBS #FiltersMenu').css('display','none');});
	$("#actionsDiv").click(function(){$('#ActionsMenu').css('display','block');});
	$("#actionsDiv").mouseleave(function(){actionsTimer=setTimeout(function(){$('#ActionsMenu').css('display','none');},3000)});
	$("#ActionsMenu").mouseenter(function(){if(actionsTimer!=null){clearTimeout(actionsTimer);}$('#ActionsMenu').css('display','block');});
	$("#ActionsMenu").mouseleave(function(){$('#ActionsMenu').css('display','none');});
	
	$('#FiltersMenu').append(cannedString);
	
});
function showOrHideFilter()
{
	if($('#filterDivs').css('display')=="none")
	{		
		$('.filterLBS').css('background','#FFFFFF');
		$(".filterLBS #filterDivs").css('display','block');
		$(".filterLBS #arrow").attr('src','<html:imagesPath/>arrow-up-grayed.png');
		
		/* Resolution for DateRange input and InstallDate input */ 
		var deviceProperties_li_Width=$('#DeviceProperties li').width();
		var installDateInputWidth=deviceProperties_li_Width*.85-$('#imgLocalizedBeginDateAsset').width()-30;
		$('#installDateFrom,#installDateTo').css({width:installDateInputWidth});
		
		var requestDetails_li_Width=$('#RequestDetails li').width();
		var dateRangeFilterInputWidth=requestDetails_li_Width*.86-$('#imgLocalizedBeginDate').width()-30;
		$('#startDateFilter,#endDateFilter').css({width:dateRangeFilterInputWidth});
			
	}
	else{
		$(".filterLBS #filterDivs").css('display','none');
		$('.filterLBS').css('background','#E8E8E8');
		$(".filterLBS #arrow").attr('src','<html:imagesPath/>arrow-down-grayed.png');
	}
	
	
}
var filterTimer=null;
$('#popupCustomize').mouseover(function(){
	if(filterTimer!=null){
		clearTimeout(filterTimer);	
	}	
});
$('#popupCustomize').mouseout(function(){
	closePopupCustomize();
});
function closePopupCustomize()
{
	filterTimer=setTimeout(function(){
		$("#popupCustomize").hide();
	 	},500);

}
function closeFleetCHL()
{
$("#fleetCHL").hide();
}
var checked="checked=\"checked\"";
function loadPopupCustomize(x)
{
	//closeFleetCHL();
	//closePopupCustomize();
	
	
	$('#popupCustomize').css("display","block");
	var widthfilterDivs=$('#filterDivs').width();
	var widthfilterLocation=$('#filterLocation').width();
	var widthcustomerHierarchy=$('#customerHierarchy').width();
	var widthfilterDevice=$('#filterDevice').width();
	var widthfilterRequestsDiv=$('#filterRequestsDiv').width();
	var widthpopupCustomize=0;
	var resultLeftMargin=0;
	
	if($(x).attr('id')=="customizeLocation" || $(x).attr('id')=="customizeCustomerHierarchy")
	{
		var strLocationChecked="";
		var strChlChecked="";
		if($('#filterLocation').css('display')!="none")
		{
			strLocationChecked=checked;
		}if($('#customerHierarchy').css('display')!="none")
		{
			strChlChecked=checked;
		}
		$("#loadCustomizeSelectors").html("<input id=\"chl\" type=\"radio\" name=\"chl\" value=\"chl\" "+strChlChecked+" onclick=\"showCHLFilter()\"><spring:message code='lbs.label.chl'/><br>"+
		"<input id=\"location\" type=\"radio\" name=\"chl\" value=\"location\" "+strLocationChecked+" onclick=\"showLocationFilter()\"><spring:message code='lbs.label.location'/>");
		
		widthpopupCustomize=$('#popupCustomize').width();
		resultLeftMargin=widthfilterDivs-widthpopupCustomize-widthfilterDevice-widthfilterRequestsDiv-12;
		$('#popupCustomize').css({left:resultLeftMargin});
		/*$('#popupCustomize').removeClass('customizeDeviceLeft');
		$('#popupCustomize').removeClass('customizeRequestLeft');
		$('#popupCustomize').addClass('customizeLocationLeft');*/
	}
	
	if($(x).attr('id')=="customizeDevice")
	{
				
		
		var defaultHTml="<td><input class=\"customize\" type=\"checkbox\" value=\"-1\" -2 onclick=\"OpenCustomizePopup(this)\">-3</td>";
		var finalHtml="<table><tr>";
		var countt=0;
		for(field in deviceIdsHTMLObj){
			var t=defaultHTml.replace('-1',$('#'+field).attr('id'));
			t=t.replace('-3',deviceIdsHTMLObj[field]);
			if($('#'+field).css('display')!="none"){
				t=t.replace('-2','checked');	
			}
			if(countt%2==0){
				finalHtml+="</tr></tr>";
			}
			finalHtml+=t;
			countt=countt+1;
		}
		finalHtml+="</tr></table>";
		
		
		$("#loadCustomizeSelectors").html(finalHtml);
		
		widthpopupCustomize=$('#popupCustomize').width();
		resultLeftMargin=widthfilterDivs-widthpopupCustomize-widthfilterRequestsDiv-12;
		$('#popupCustomize').css({left:resultLeftMargin});
		/*$('#popupCustomize').addClass('customizeDeviceLeft');
		$('#popupCustomize').removeClass('customizeRequestLeft');
		$('#popupCustomize').removeClass('customizeLocationLeft');*/
	}
	
	if($(x).attr('id')=="customizeRequests")
	{
		var strRequestNoChecked="";
		var strRequestTypeChecked="";
		var strRequestStatusChecked="";
		var strDateRangeChecked="";
		var strCustomerReference="";
		var strArea="";
		var strSubArea="";
		
		
		if($('#requestNo').css('display')!="none"){	strRequestNoChecked=checked;}
		if($('#requestType').css('display')!="none"){strRequestTypeChecked=checked;}
		if($('#requestStatus').css('display')!="none"){strRequestStatusChecked=checked;}
		if($('#dateRangeFilterContainer').css('display')!="none"){strDateRangeChecked=checked;}
		if($('#customerRef').css('display')!="none"){strCustomerReference=checked;}
		if($('#areaDrop').css('display')!="none"){strArea=checked;}
		if($('#subAreaDrop').css('display')!="none"){strSubArea=checked;}
		//alert('inside customizeRequests');
		$("#loadCustomizeSelectors").html("<input class=\"customize\" type=\"checkbox\" value=\"areaDrop\" "+strArea+"onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.area'/><br>"+
											"<input class=\"customize\" type=\"checkbox\" value=\"customerRef\" "+strCustomerReference+"onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.customerreference'/><br>"+
											"<input class=\"customize\" type=\"checkbox\" disabled=\"disabled\" value=\"dateRangeFilterContainer\" "+strDateRangeChecked+"onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.daterange'/><br>"+
											"<input class=\"customize\" type=\"checkbox\" value=\"requestNo\" "+strRequestNoChecked+" onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.requestnumber'/><br>"+
											"<input class=\"customize\" type=\"checkbox\" value=\"requestStatus\" "+strRequestStatusChecked+"onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.requeststatus'/><br>"+
											"<input class=\"customize\" type=\"checkbox\" value=\"requestType\" "+strRequestTypeChecked+"onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.requesttype'/><br>"+
											"<input class=\"customize\" type=\"checkbox\" value=\"subAreaDrop\" "+strSubArea+" onclick=\"OpenCustomizePopup(this)\"><spring:message code='lbs.label.subarea'/><br>");
		widthpopupCustomize=$('#popupCustomize').width();
		resultLeftMargin=widthfilterDivs-widthpopupCustomize-12;
		$('#popupCustomize').css({left:resultLeftMargin});
		/*$('#popupCustomize').removeClass('customizeDeviceLeft');
		$('#popupCustomize').addClass('customizeRequestLeft');
		$('#popupCustomize').removeClass('customizeLocationLeft');*/
}
}
function OpenCustomizePopup(x) {

		var n=$(x).val();
		
		
		var limitSelect=0;
		var errorMessage="";
		var parentID=$('#'+n).parent().parent().parent().attr('id');
		if(parentID=="DeviceProperties")
		{
			limitSelect=6;
			errorMessage="<spring:message code='lbs.errorMessage.maximumSelected.device'/>";
			
		}
		else if(parentID=="RequestDetails")
		{
			limitSelect=5;
			errorMessage="<spring:message code='lbs.errorMessage.maximumSelected.request'/>";
		}
		var a=$("#loadCustomizeSelectors").find('input[type="checkbox"]:checked').length;
		
		if(a>=limitSelect+1)
		{
			//alert(errorMessage);
			$("#errorCustomize").html(errorMessage);
			customizeErrorDialog.dialog('open');
			
			$(x).attr('checked',false);
		}
		else
		{
			if($(x).is(':checked')){$("#"+n).css("display","inline");}
			else{$("#"+n).css("display","none");}	
			saveCustomizeSetting(n,$(x).is(':checked'),"${saveCustomzieSettingsPopupURL}");
		}
	
}

var customizeErrorDialog=$('#errorCustomize').dialog({
	autoOpen:false,
	resizable:false,
	title:"<spring:message code='lbs.title.popup.warning'/>",
	modal: true
	
});
function showCHLFilter()
{

	$(".filterLBS #filterLocation").css('display','none');
		$(".filterLBS #customerHierarchy").css('display','block');
		closePopupCustomize();
		//Below code is for saving the customer hierarchy display as customize popup
		customizeIndexSetting['filterLocation'][1]=false;//This is locatted in LBSService.js
		saveCustomizeSetting('customerHierarchy',true,"${saveCustomzieSettingsPopupURL}");
}
function showLocationFilter()
{
	$(".filterLBS #customerHierarchy").css('display','none');
		$(".filterLBS #filterLocation").css('display','block');
		closePopupCustomize();
		//Below code is for saving the filter location display as customize popup
		customizeIndexSetting['customerHierarchy'][1]=false;//This is locatted in LBSService.js
		saveCustomizeSetting('filterLocation',true,"${saveCustomzieSettingsPopupURL}");
		
}


function checkOption(option){
	if(option == "setPref"){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.FLEETMANAGER%>','<%=LexmarkSPOmnitureConstants.SETFILTERPREFERENCE%>');
		<%--showPopupMessages("Are you sure you want to Set Preference? All old saved filters will be lost and current filters will be saved",function(){
			preferenceObject.setIspreferenc();
			setValuesToObjects(false);setPreferenceToDB("${saveFilterURL}","Y");//defined in LbsDbFilters.js	
		});--%>
		showSetFilterAlert();
	}else if(option == "loadPref"){
			callOmnitureAction('<%=LexmarkSPOmnitureConstants.FLEETMANAGER%>','<%=LexmarkSPOmnitureConstants.LOADFILTERPREFERENCES%>');
		var jsonObj=preferenceObject.getPreferenceObject();
		if(jsonObj==null){
			 jAlert('There are no Default Preferences set', "");
		}else{
			parseSettings(jsonObj);
			setValuesToObjects(true);checkAndSetCancelForDate();showMapBtnClicked();hideOverlay();
				
		}
		
		
		<%-- 
		showPopupMessages("Are you sure you want to load Preference? All your current filters will be lost and saved preference will be loaded.",function(){
			getFilterSettings("${retrieveFilterURL}");//defined in LbsDbFilters.js	
		});--%>
		
	}else if(option == "bookmark"){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.FLEETMANAGER%>','<%=LexmarkSPOmnitureConstants.FILTERSBOOKMARK%>');
		if($('#left-nav-Div').css('display')!='none'){
    		hideNav();<%-- Hide left navigation --%>	
    	}	 		
		//Below methods are in default-view.jsp
		loadBookmarkedAssetsObj();showMapBtnClicked();hideOverlay();
	}
}
var chlDialog;
function popUpChlTree()
{
	
	jQuery('#dialogChlTree').load("${showCHLTreePopUp}&t="+new Date().getTime(),function(){
		var heightDoc=jQuery(window).height()<630?630:jQuery(window).height();
		var divht=heightDoc-150;
		jQuery('#chl_node_tree_container').css('width','100%');
		jQuery('#chl_node_tree_container').css('height',divht+'px');	
		chlDialog=jQuery('#content_chlTree').dialog({
		autoOpen: false,
		title: jQuery('#chlTreeLink').attr('title'),
		modal: true,
		draggable: false,
		resizable: false,
		width: 400,
		height: heightDoc-80,
		position: 'top',
		open:function(){
			
			if(c.getParentContext()=="${prefixId}"){
				jQuery('#content_chlTree').append('<div id="dynaSc">'+jQuery('#dynamicScriptChl').html()+'</div>');
			}
		},
		close: function(event,ui){
				chlDialog=null;
				jQuery('#content_chlTree').remove();
				if(c.getParentContext()=="${prefixId}"){
					window.setValToPage=originalFuncChl;
				}
				
				//This section is to hide the select hierarchy level link				
				/*if(jQuery("#chlNodeValue").val()!=""){
					showHiererchy();
				}*/
			}
	});
	
	chlDialog.dialog('open');
	
	
	initialiseCHLTree();		
});
return false;
};
function closeDialog(){
	chlDialog.dialog('close');
}
function setCHLValue(v){
	$('#chlNodeValueLabel').html(v);
}

$("#dateRangeFilterContainer").find("input").mouseup(function(){
	var beginDate=null,endDate=null;
	if(this.id=="startDateFilter"){
		beginDate=convertDateToString(new Date().addDays(-90));
		if($("#endDateFilter").val()==allhtmlTextIdsObj["endDateFilter"]){
			endDate=todayStr;
		}else{
			endDate=formatDateToDefault($("#endDateFilter").val());
		}
	}else if(this.id=="endDateFilter"){
		endDate=todayStr;
		if($("#startDateFilter").val()==allhtmlTextIdsObj["startDateFilter"]){
			beginDate=convertDateToString(new Date().addDays(-90));
		}else{
			beginDate=formatDateToDefault($("#startDateFilter").val());
		}
	}

	show_cal(this.id, beginDate, endDate);bindCloseCal();
});
jQuery('#startDateFilter').val(allhtmlTextIdsObj["startDateFilter"]);//localizeDate(convertDateToString(new Date().addDays(-15))));
jQuery('#endDateFilter').val(allhtmlTextIdsObj["endDateFilter"]);//localizeDate(todayStr));

$("#dateRangeFilterContainer").find("input ~ img").mouseup(function(){
	jQuery(this).prev().mouseup();
});

function bindCloseCal(){
	if(popupCal){
		popupCal.user_onchange_handler=function(cal,ob_code){
			cal_on_change(cal,ob_code);
			appednCancelIcon(cal.get_text_field_id());
		};
		
	};
}

function appednCancelIcon(id){
	var cancelIsprsnt=false;
	$('#'+id).parent().children().each(function(){
		var cl=$(this).attr('class');
		
		if(cl!=null && cl.indexOf("cancelIconCalendar")!=-1){
			cancelIsprsnt=true;
				return false;
		}
	});
	
	if(cancelIsprsnt || $('#'+id).val()==allhtmlTextIdsObj[id]){return;}
	$('#'+id).parent().append("<img src=\"<html:imagesPath/>transparent.png\"  class=\"ui_icon_sprite ui-icon-closethick cancelIconCalendar\"  style=\"cursor:pointer;\" />");
	$('.cancelIconCalendar').click(function(){
		$(this).parent().find("input").val(allhtmlTextIdsObj[id]);
		$(this).remove();
	});
}
function checkAndSetCancelForDate(){
	var ids=["installDateFrom","installDateTo","startDateFilter","endDateFilter"];
	for(var i=0;i<ids.length;i++){
		if($('#'+ids[i]).val()!='' && $('#'+ids[i]).val()!=allhtmlTextIdsObj[ids[i]]){
			appednCancelIcon(ids[i]);
		};	
	};
	
}

$('#imgLocalizedBeginDateAsset,#installDateFrom').click(function(){
	show_cal('installDateFrom', null, $('#installDateTo').val()==""?null:formatDateToDefault($("#installDateTo").val()));bindCloseCal();
});
$('#imgLocalizedEndDateAsset,#installDateTo').click(function(){
	show_cal('installDateTo', $('#installDateFrom').val()==""?null:formatDateToDefault($("#installDateFrom").val()), todayStr);bindCloseCal();
});
function setMultiValue(){
	$('.options').each(function()
			{
				var arraySelect=$(this).val();
				if(arraySelect==null)
				{
					arraySelect=[""];
				}
				var arraySlText=[];
				$(this).find("option").each(function(){
					if($.inArray($(this).val(),arraySelect)!=-1){
						arraySlText.push($(this).text());	
					}
				});
				
				if(arraySelect.length<3){
					$(this).siblings('.multiSelectBody').children('.contentZone').children('.placeholderContent').html(arraySlText.join(","));
				}
				else{
					$(this).siblings('.multiSelectBody').children('.contentZone').children('.placeholderContent').html(arraySlText.length+str1);
				}
			});
}

function isResponseBlank(resp){
	if($.trim(resp)=='<option value=\"\"></option>'){
		return true;
	}return false;
}

var cannedMsgs=["<spring:message code='lbs.filter.canned0'/>","<spring:message code='lbs.filter.canned1'/>","<spring:message code='lbs.filter.canned2'/>","<spring:message code='lbs.filter.canned3'/>"];
var cannedObj=[];
var cannedString="";
<c:forEach items="${fleetMgmtForm.cannedQueries}" var="cannedQuery">
cannedObj[${cannedQuery.id}]={"id":"${cannedQuery.id}","name":"${cannedQuery.name}","json":${cannedQuery.jsonString}};
cannedString+="<div class='clearBoth' onClick='loadCanned(${cannedQuery.id})'>"+cannedMsgs[${cannedQuery.id}]+"</div>";
</c:forEach>
function loadCanned(id){
	//alert(cannedObj[id].json);
callOmnitureAction('<%=LexmarkSPOmnitureConstants.FLEETMANAGER%>','<%=LexmarkSPOmnitureConstants.CANNEDFILTERS%>');
	showPopupMessages("<spring:message code='lbs.filter.cannedPopUp'/>",function(){
				resetFilters(false);
				if($('#left-nav-Div').css('display')!='none'){
	    			hideNav();<%-- Hide left navigation --%>	
	    		}	
				loadCannedObj(cannedObj[id].json);
				showMapBtnClicked();hideOverlay();		
		});
		
	
};

function loadCannedObj(cannedObj){
	filtersObj=new Filters();
	filtersObj.setCannedValues(cannedObj);
	siebelJSON=new SiebelJSON();
	siebelJSON.setMdmIDMdmLevel(accountInfoSiebel);
	siebelJSON.setPostMessageURL(location.href);
	siebelJSON.setFilters(filtersObj);
	siebelJSON.setQueryId(emailAddress);
	//siebelJSON.setLanguage(languageID);
	//siebelJSON.setMview();
};

</script>
<%@include file="commonFiltersPopup.jsp"%>