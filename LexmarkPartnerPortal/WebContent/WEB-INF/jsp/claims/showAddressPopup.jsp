<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ taglib prefix="portlet" uri="/WEB-INF/tld/liferay-portlet.tld" %>



<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>

<portlet:resourceURL var="newAddressValidateURL" id="newAddressValidate"></portlet:resourceURL>

<script>
	var goForCleanseAddrFlg = false;
	var canCreateFlag = "${createNewFlag}";
	var accountId = "${accountId}";
	var partnerAddressListURL = "${partnerAddressListURL}";
	
	var isPartnerAddressGridLoaded = false;
	
	function setCreateNewAddressButton(value)
	{
		if(value == "true" || value == true){
				document.getElementById("createNewAddressSection").style.display="block";
				document.getElementById("canNotCreateNewAddressSection").style.display="none";
		}else{
				document.getElementById("createNewAddressSection").style.display="none";
				document.getElementById("canNotCreateNewAddressSection").style.display="block";
		}
	}
</script>

<div class="div-style11">

<!--  address list start -->
<style>
	.selected_button_anchor, .selected_button_anchor span {
		background-color:white !important;
	}
</style>
<%-- Commented by sankha for LEX:AIR00080651 and LEX:AIR00080691		
<div id="partnerAddressList" class="scroll" style="display:block; margin-left: 5px; width:100%;">
--%>
<%-- Added by sankha for LEX:AIR00080651 and LEX:AIR00080691 --%>
<div id="partnerAddressList" class="scroll" style="display:block;">
<table width="100%">
<tr>
	<td class="contactsTD">
	<a href="javascript:switchToAllAddresses();" class="not_selected_button_anchor bg-color-white" id="accountAddressesBtn" >
		<span><spring:message code="claim.button.allAccountAddresses"/></span>
	</a>&nbsp;
	<a href="javascript:switchToMyAddresses();" id="myFavoriteAddressesBtn" class="selected_fav_button_anchor bg-color1" >
		<span><spring:message code="claim.button.myAddresses"/></span>
	</a>
	</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td>
		<div id="gridCCVAllPartnerAddress" class="gridCCVAllPartnerAddress" style="display:none;"></div>
		<div id="loadingNotificationAll" class="gridLoading width-90per" style="display:none;">
			<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
		</div>
		<div id="pagingAreaAll"></div><div id="infoAreaAll"></div>
		<div id="gridCCVMyPartnerAddress" class="gridCCVMyPartnerAddress" ></div>
		<div id="loadingNotificationMy" class="gridLoading width-90per" >
			<!--spring:message code="label.loadingNotification"/-->&nbsp; <img src="<html:imagesPath/>gridloading.gif" /><br>
		</div>
		<div id="pagingAreaMy"></div><div id="infoAreaMy"></div>
	</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr id="createNewAddressSection">
	<td align="left">
		<a href="javascript:expandNewAddress();" class="button" id="createSecondaryBtn"><spring:message code="claim.button.createNew"/></a>
	</td>
</tr>
<tr id="canNotCreateNewAddressSection" style="display:none;">
	<td align="left">
		<label><spring:message code="claim.label.canNotCreateShipToAddress"/></label>
	</td>
</tr>
</table>

<!--  address list end -->

<!-- Below section used for create new address -->
	<div id="partnerNewAddressId" class="partner-newAddressId" >
	
		<table class="table-style5" width='100%'>
			<tr>
				<td colspan='2'>
					<h5><spring:message code="claim.description.createNewAddress"/>:</h5><br>
					<label id="requiredFieldsErrorMsg2" class="color-red" style="display:none"><spring:message code="claim.errorMsg.pleaseFillInAllRequiredFields"/></label>
					<label id="requiredFieldsErrorMsg3" class="color-red" style="display:none"><strong>Unable to cleanse address</strong></label>
				</td>
			</tr>
			<tr >
				<td width="50%"><span class="required">* </span><spring:message code="claim.label.addressLine1"/></td>
				<td width="50%">&nbsp;<spring:message code="claim.label.addressLine2"/></td>
			</tr>
			<tr >
				<td width="50%"><input  size= "77" id="addressLine1" name="addressLine1"  type="text" value="${addressLine1}" onFocus="removeErrMsg();" onblur="validateLength(0,200,this);"></td>
				<td width="50%"><input  size= "77" id="addressLine2" name="addressLine2"  type="text" value="${addressLine2}" onFocus="removeErrMsg();" onblur="validateLength(0,100,this);"></td>
			</tr>
			<tr >
				<td width="50%"><span class="required">* </span><spring:message code="claim.label.country"/> </td>
				<td width="50%"><span class="required">* </span><spring:message code="claim.label.city"/></td>
			</tr>
			<tr >
				<td width="50%">
					<select id="country" class="width-180px" name="countryDropDownList" onChange="getState();">
						<option  value="" selected="selected"><spring:message code="claim.label.pleaseSelect"/></option>
						<c:forEach items="${createNewAddressForm.countries}" var="country">
								<option value="${country.countryCode}">${country.countryName}</option>
						</c:forEach>
					</select>	
				</td>
				<td width="50%"><input id="city" size= "20" type="text" value="${city}" onFocus="removeErrMsg();" onblur="validateLength(0,50,this);">	</td>	
			</tr>
			<tr >
				<td width="50%">
					<div id="showStateProvince"><span class="required">* </span><spring:message code="claim.label.stateOrProvince"/></div>
					<label id="stateSearchLoading" style="display: none;">
						<img  src = "<html:imagesPath/>loading-icon.gif"; />&nbsp;<spring:message code="label.searchingNotification" />
					</label>
				</td>
				<td width="50%"><span class="required">* </span><spring:message code="claim.label.zip"/></td>
			</tr>
			<tr >
				<td width="50%"><div id="retrieveStateInfo">${stateData}</div>
					 	
				</td>	
				<td width="50%"><input id="zip" type="text" value="${zip}" onFocus="removeErrMsg();" onblur="validateLength(0,30,this);"></td>
			</tr>
			
			<tr>
			     <td width="50%" align="left">&nbsp;</td>
			     <td width="50%" align="left"></td>
			</tr>
		</table>
		
		<div id="buttonContact_popup" align="right"  class="buttonContact_popup">
			<a href="###" class="button_cancel" onclick="clearInputs(); collapseNewAddress();">
				<spring:message code="button.cancel"/>
			</a>
			&nbsp;
			<a href="###" onclick="createNew(this); hideOverlayIE6();" class="button" name="btnSubmit">
				<spring:message code="button.save"/>
			</a>
		</div>
	</div>
	
	<div>
	
	</div>
	
	<div id="cleansedAddress" style="display:none;">
	<hr class="separator" />
		<div class="portletBlock infoBox" id="checkBoxWithInfoContainer">
		<p class="info banner"><spring:message code="requestInfo.popup.cleansedAddresses"/></p>
			<div class="columnsOne">
				<div class="columnInner">
					<ul class="roDisplay">
							<li>
								<div id="addrLine1_popup_span"></div>
								<div id="addrLine2_popup_span"></div>
								<div id="city_state_zip_popup_span"></div>
								<div id="country_popup_span"></div>
							</li>
					</ul>
					<p class="check">
						<input type="checkbox" id="check_popup"	onclick="javascript:validate_popup();" /> 
						<label for="modAddress">
							<spring:message code="requestInfo.popup.usecleansedAddresses"/>
						</label>
					</p>
				</div>
				
			</div>
		</div>
		
		<div class="buttonContainer">
			<button class="button_cancel" onclick="hideadd();"><spring:message code="button.cancel"/></button>
			&nbsp;
			<button id="btnSelect_addr" class="button" onclick="saveAddressFromPopupToPage(this); hideOverlayIE6();")><spring:message code="button.save"/>&nbsp;
				<spring:message code="requestInfo.cm.manageAddress.heading.newAddresses"/>
			</button>
		</div>
	</div>
	
	
  </div>
</div>
<script>
	function expandNewAddress(){
		document.getElementById("partnerNewAddressId").style.display="block";
	}
	function collapseNewAddress(){
		document.getElementById("partnerNewAddressId").style.display="none";
	}
	
	var isChooseAdifferentAddress = true;
	
	var allPartnerAddressGridLoading = false;
	allPartnerAddressGrid = new dhtmlXGridObject('gridCCVAllPartnerAddress'); 	 
	allPartnerAddressGrid.setImagePath("<html:imagesPath/>gridImgs/");
	allPartnerAddressGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.address"/>',10));
	allPartnerAddressGrid.attachHeader("#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	allPartnerAddressGrid.setInitWidths("75,150,120,120,120,75,75,75,75,100");
	allPartnerAddressGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
	allPartnerAddressGrid.setColTypes("ro,rotxt,rotxt,rotxt,rotxt,rotxt,ro,ro,ro,ro");
	allPartnerAddressGrid.setColSorting("na,str,str,str,str,str,str,str,str,na");
	allPartnerAddressGrid.init();
	allPartnerAddressGrid.prftInit();
	allPartnerAddressGrid.enablePaging(true, 5, 6, "pagingAreaAll", true, "infoAreaAll");
	allPartnerAddressGrid.setPagingSkin("bricks");
	allPartnerAddressGrid.enableAutoWidth(true);
	allPartnerAddressGrid.enableAutoHeight(true);
	allPartnerAddressGrid.a_direction = "ASCENDING";
	allPartnerAddressGrid.setSizes();
	allPartnerAddressGrid.setColumnHidden(0,true); // indicate column index and set the state to true to hire the column
	allPartnerAddressGrid.setColumnHidden(4,true);
	allPartnerAddressGrid.setSkin("light");
	allPartnerAddressGrid.attachEvent("onXLS", function() {
		allPartnerAddressGridLoading = false;
		document.getElementById('loadingNotificationAll').style.display = '';
	});
	allPartnerAddressGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
	allPartnerAddressGrid.attachEvent("onXLE", function() {
		allPartnerAddressGridLoading = true;
		allPartnerAddressGrid.filterByAll();
		allPartnerAddressGrid.setSortImgState(true, 2, "asc");
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
		document.getElementById('loadingNotificationAll').style.display = 'none';
	});
	allPartnerAddressGrid.attachEvent("onFilterStart", function(indexes,values){
		if(!allPartnerAddressGridLoading)
			return false;
		for(var i=0; i < values.length; i++){
			values[i] = values[i].trim();
		}
		return true;
	});

	
	//myPartnerAddressGrid for My Addresses
	var myPartnerAddressGrid = new dhtmlXGridObject('gridCCVMyPartnerAddress');
	myPartnerAddressGrid.setImagePath("<html:imagesPath/>gridImgs/");
	myPartnerAddressGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.address"/>',10));
	myPartnerAddressGrid.attachHeader("&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,&nbsp;");
	myPartnerAddressGrid.setInitWidths("75,150,120,120,120,75,75,75,75,100");
	myPartnerAddressGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
	myPartnerAddressGrid.setColTypes("ro,rotxt,rotxt,rotxt,rotxt,rotxt,ro,ro,ro,ro");
	myPartnerAddressGrid.setColSorting("na,str,str,str,str,str,str,str,str,na");
	myPartnerAddressGrid.init();
	myPartnerAddressGrid.prftInit();
	myPartnerAddressGrid.enablePaging(true, 5, 6, "pagingAreaMy", true, "infoAreaMy");
	myPartnerAddressGrid.setPagingSkin("bricks");
	myPartnerAddressGrid.enableAutoWidth(true);
	myPartnerAddressGrid.enableAutoHeight(true);
	myPartnerAddressGrid.a_direction = "ASCENDING";
	myPartnerAddressGrid.setSizes();    
	myPartnerAddressGrid.setColumnHidden(0,true); // indicate column index and set the state to true to hire the column
	myPartnerAddressGrid.setColumnHidden(4,true);
	myPartnerAddressGrid.setSkin("light");
	myPartnerAddressGrid.attachEvent("onXLS", function() {
		if(!isChooseAdifferentAddress){
			document.getElementById('loadingNotificationMy').style.display = 'block';
		}
	});
	myPartnerAddressGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
	myPartnerAddressGrid.attachEvent("onXLE", function() {
		myPartnerAddressGrid.filterByAll();
		myPartnerAddressGrid.setSortImgState(true, 2, "asc");
		if(isChooseAdifferentAddress){
			isChooseAdifferentAddress = false;
			document.getElementById('partnerAddressList').style.display = 'block';
			if(myPartnerAddressGrid.getRowsNum()==0){
				switchToAllAddresses();
				}
			else{
				document.getElementById("gridCCVMyPartnerAddress").style.display="block";
				document.getElementById("gridCCVAllPartnerAddress").style.display="none";
				document.getElementById("loadingNotificationAll").style.display="none";
				document.getElementById("pagingAreaMy").style.display="block";
				document.getElementById("pagingAreaAll").style.display="none";
				changeMyColor('myFavoriteAddressesBtn');
				myPartnerAddressGrid.loadXMLString("<rows></rows>");
			}
			
		}
		document.getElementById('loadingNotificationMy').style.display = 'none';
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
	myPartnerAddressGrid.attachEvent("onFilterStart", function(indexes,values){
		for(var i=0; i < values.length; i++){
			values[i] = values[i].trim();
		}
		return true;
    });
	
	function chooseADifferentAddress(){
		//document.getElementById("activity.shipToAddress.addressId").value="";
		document.getElementById('partnerAddressList').style.display = 'none';
		if(!isChooseAdifferentAddress){
			document.getElementById('partnerAddressList').style.display = 'block';
			switchToMyAddresses();
			return;
		}
		myPartnerAddressGrid.clearAndLoad(partnerAddressListURL+"&accountId="+accountId+"&favoriteFlag=true");
	}
	
	function switchToMyAddresses(){		
		  document.getElementById("gridCCVMyPartnerAddress").style.display="block";
		  document.getElementById("gridCCVAllPartnerAddress").style.display="none";
		  document.getElementById("loadingNotificationAll").style.display="none";
		  document.getElementById("pagingAreaMy").style.display="block";
		  document.getElementById("pagingAreaAll").style.display="none";
		  changeMyColor('myFavoriteAddressesBtn');
		  myPartnerAddressGrid.clearAndLoad(partnerAddressListURL+"&accountId="+accountId+"&favoriteFlag=true");
	}
	
	function switchToAllAddresses(){			
		  document.getElementById("gridCCVAllPartnerAddress").style.display="block";
		  document.getElementById("gridCCVMyPartnerAddress").style.display="none";
		  document.getElementById("loadingNotificationMy").style.display="none";
		  document.getElementById("pagingAreaAll").style.display="block";
		  document.getElementById("pagingAreaMy").style.display="none";
		  changeMyColor('accountAddressesBtn');
		  if(isPartnerAddressGridLoaded==false){
			  allPartnerAddressGrid.clearAndLoad(partnerAddressListURL+"&accountId="+accountId+"&favoriteFlag=false");		
		  	isPartnerAddressGridLoaded=true;
		  }	  
	}
	
	//change color for selected button
	function changeMyColor(btnName){
		  btnEle = document.getElementById(btnName);
		  if(btnEle.id=="myFavoriteAddressesBtn"){
			  addClass(btnEle, "selected_fav_button_anchor");
			  removeClass(btnEle, "not_selected_fav_button_anchor");
			  addClass(document.getElementById("accountAddressesBtn"), "not_selected_button_anchor");
			  removeClass(document.getElementById("accountAddressesBtn"), "selected_button_anchor");
		  }
		  if(btnEle.id=="accountAddressesBtn"){
			  addClass(btnEle, "selected_button_anchor");
			  removeClass(btnEle, "not_selected_button_anchor");
			  addClass(document.getElementById("myFavoriteAddressesBtn"), "not_selected_fav_button_anchor");
			  removeClass(document.getElementById("myFavoriteAddressesBtn"), "selected_fav_button_anchor");
		  }	  
	}
	
	
	//the next three functions are used to add/remove classes on elements
	function hasClass(ele,cls) {
		return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
	}
	function addClass(ele,cls) {
		if (!this.hasClass(ele,cls)) ele.className += " "+cls;
	}
	function removeClass(ele,cls) {
		if (hasClass(ele,cls)) {
			var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
			ele.className=ele.className.replace(reg,' ');
		}
	}
	
	//Used for visibility of CreateNew Button
	setCreateNewAddressButton(canCreateFlag);
	//grid loading
	chooseADifferentAddress();
</script>

<!--  Used for create new address -->
<script>
var cleansedAddressInfo;
var validationFlag = true;	
function createNew(buttonElm)
{
	validateForm();
	var an = '';
	var city = document.getElementById("city").value;
	var al1  = document.getElementById("addressLine1").value;
	var al2  = document.getElementById("addressLine2").value;
	var al3  = "";
	var country = document.getElementById("country").value;
	var state = document.getElementById("state").value;
	var zip = document.getElementById("zip").value;
	var countryName = document.getElementById("country").options[document.getElementById("country").selectedIndex].text;
	var stateName = document.getElementById("state").options[document.getElementById("state").selectedIndex].text;		
	
	if(validationFlag)
	{
		//Temporarily Commented for cleansed Functionality
		//hideOverlayIE6();
		//Liferay.Popup.close(buttonElm);
		//window.parent.window.addPartnerAddressElement('-1', an, al1, al2, al3, city, null, null, state, country, zip);
		document.getElementById("requiredFieldsErrorMsg2").style.display="none";
		document.getElementById("requiredFieldsErrorMsg3").style.display="none";
		document.getElementById("cleansedAddress").style.display="none";
		
		//alert('${newAddressValidateURL}');
		jQuery.ajax({
			url:'${newAddressValidateURL}',
			type:'POST',
			dataType: 'JSON',
			data: {
					addressLine1: al1,
					addressLine2: al2,
					city: 		  city,
					country:	  country,
					state:		  state, 	
					zipPostal:    zip					
			},
			success: function(results){
				var obj2;
				try{
					 obj2=jQuery.parseJSON(results);
				}catch(e){
					//alert('exception occured');
				}
				if(obj2 !=null){
					var error=obj2.error;
					//alert(error);
					if(error=="none"){
						cleansedAddressInfo = obj2;
						//jQuery('#storefront_popup_span').html(obj2.storeFrontName);
						jQuery('#addrLine1_popup_span').html(obj2.addressLine1);
						jQuery('#addrLine2_popup_span').html(obj2.addressLine2);
						jQuery('#city_state_zip_popup_span').html(obj2.city +','+obj2.state+','+obj2.zipPostal);
						jQuery('#country_popup_span').html(obj2.country);
						//display the cleansed address
						jQuery('#cleansedAddress').show();
						//if error div is already show hide it
						jQuery('#requiredFieldsErrorMsg3').hide();
						jQuery('#buttonContact_popup').hide();
						
					}else{
						jQuery('#requiredFieldsErrorMsg3').html(obj2.cleansedError);
						jQuery('#requiredFieldsErrorMsg3').show();
						jQuery('#cleansedAddress').hide();
					}
					
				}
			},
			failure: function(results){
				document.getElementById("requiredFieldsErrorMsg3").style.display="block";
			}
		});
	}
}

function saveAddressFromPopupToPage(buttonElm){
	if(goForCleanseAddrFlg == true){
		//alert("Cleansed Address");
		//alert(cleansedAddressInfo);
		if(cleansedAddressInfo != null){
			hideOverlayIE6();
			var _addressId = cleansedAddressInfo.addressId;
			if(_addressId == 'undefined'){
				_addressId = '-1';
			}
			Liferay.Popup.close(buttonElm);
			window.parent.window.addPartnerAddressElement(_addressId, cleansedAddressInfo.addressName, cleansedAddressInfo.addressLine1, cleansedAddressInfo.addressLine2, '', cleansedAddressInfo.city, null, null, cleansedAddressInfo.state, cleansedAddressInfo.country, cleansedAddressInfo.zipPostal);
		}
	}else{
		//alert("Filledup Address");
		var _an = '';
		var _city = document.getElementById("city").value;
		var _al1  = document.getElementById("addressLine1").value;
		var _al2  = document.getElementById("addressLine2").value;
		var _al3  = "";
		var _country = document.getElementById("country").value;
		var _state = document.getElementById("state").value;
		var _zip = document.getElementById("zip").value;
		var _countryName = document.getElementById("country").options[document.getElementById("country").selectedIndex].text;
		var _stateName = document.getElementById("state").options[document.getElementById("state").selectedIndex].text;		
		hideOverlayIE6();
		Liferay.Popup.close(buttonElm);
		window.parent.window.addPartnerAddressElement('-1', _an, _al1, _al2, _al3, _city, null, null, _state, _country, _zip);
	}
}

function hideadd() {
	jQuery('#cleansedAddress').hide();
	jQuery('#buttonContact_popup').show();
	enableInputs();
	clearInputs();
	jQuery('#requiredFieldsErrorMsg3').hide();
}

function validate_popup() {
	if(document.getElementById('check_popup').checked){
		goForCleanseAddrFlg = true;
		disableInputs();
	}
	else{
		goForCleanseAddrFlg = false;
		enableInputs();
	}
}

function enableInputs(){
	//jQuery('#storeName').removeAttr('disabled');
	jQuery('#addressLine1').removeAttr('disabled');
	jQuery('#addressLine2').removeAttr('disabled');
	jQuery('#city').removeAttr('disabled');
	jQuery('#country').removeAttr('disabled');
	jQuery('#state').removeAttr('disabled'); 						
	jQuery('#zip').removeAttr('disabled');
	
	}
function disableInputs(){
	//jQuery('#storeName').attr('disabled','disabled');
	jQuery('#addressLine1').attr('disabled','disabled');
	jQuery('#addressLine2').attr('disabled','disabled');
	jQuery('#city').attr('disabled','disabled');
	jQuery('#country').attr('disabled','disabled');
	jQuery('#state').attr('disabled','disabled'); 						
	jQuery('#zip').attr('disabled','disabled');
}
function clearInputs(){
	//jQuery('#storeName').val("${accountName}");
	jQuery('#addressLine1').val('');
	jQuery('#addressLine2').val('');
	jQuery('#city').val('');
	jQuery('#country').val('');
	jQuery('#state').val('');						
	jQuery('#zip').val('');
}

function validateForm(){
	var city = document.getElementById("city").value;
	var al1 = document.getElementById("addressLine1").value;
	var countryCode = document.getElementById("country").value;
	var state = document.getElementById("state").value;
	var zip = document.getElementById("zip").value;
	if((city==null||city=="")||(al1==null||al1=="")||(zip==null||zip=="")||((countryCode==null||countryCode=="")||(state==null||state==""))){			
		document.getElementById("requiredFieldsErrorMsg2").style.display="block";
		validationFlag = false;
	}
}

function removeErrMsg(){		
	document.getElementById("requiredFieldsErrorMsg2").style.display="none";	
	document.getElementById("requiredFieldsErrorMsg3").style.display="none";
	document.getElementById("cleansedAddress").style.display="none";
	
	validationFlag = true;
}

function getState(){
	removeErrMsg();
	document.getElementById('showStateProvince').style.display = 'none';
	document.getElementById('retrieveStateInfo').style.display = 'none';
	document.getElementById("stateSearchLoading").style.display = "block";
	var countryCode = document.getElementById("country").value;
	var countryName = document.getElementById("country").options[document.getElementById("country").selectedIndex].text;
	var location="<portlet:resourceURL id="getState"/>&countryCode="+countryCode;
	jQuery("#retrieveStateInfo").load(location,function(response){
		document.getElementById("stateSearchLoading").style.display = "none";
		if(response=="<select id=\"state\"><option value=\"nostate\">No State Available</option></select>"){
			document.getElementById('showStateProvince').style.display = 'none';
			document.getElementById('retrieveStateInfo').style.display = 'none';
			return false;
		}
	document.getElementById('showStateProvince').style.display = '';
	document.getElementById('retrieveStateInfo').style.display = '';
	});
	
}

function addPartnerAddressElementPopup(addressId, addressName, addressLine1, addressLine2, addressLine3, addressCity, addressState, addressProvince, nummValue , addressCountry, addressPostCode, fav){
	window.parent.window.addPartnerAddressElement(addressId, addressName, addressLine1, addressLine2, "", addressCity, addressState, addressProvince, null , addressCountry, addressPostCode, fav);
}

</script>