<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%--MPS 2.1 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_INPUT"%>
<%@page import ="static com.lexmark.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%@page import ="static com.lexmark.util.ChangeMgmtConstant.DISPLAYHTML"%>

<%--ends --%>


<portlet:resourceURL var="newAddressValidateURL" id="newAddressValidate"></portlet:resourceURL>

<portlet:resourceURL var="countryListPopulateURL" id="countryDropDownPopulate"/>
<portlet:resourceURL var="stateListPopulateURL" id="stateDropDownPopulate"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content_addr">
  

		
		<div class="portletBlock">
			<div class="columnsOne">
				<div class="columnInner">
				<div class="error" style="display: none;" id="address_error_div_popup"></div>
					<div class="grid-controls">
  						<div class="filterLinks">
							<ul>
      						<li class="first" id="alladdress"><spring:message code="claim.button.allAccountAddresses"/></li>
      						<li><img src="<html:imagesPath/>favorite.png" width="15" height="15">
      						<span id="myBookmarkAddress"><a href="javascript:showBookmarkedAddress();"><spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/></a></span>
      						</li>
      						</ul>
      					</div>
      					<div class="expand-min">
      						<ul>
      						<li>
	             				<a href="#grid" id='headerMenuButton'>
	             				<img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" height="25" width="25"  />
	             				&nbsp;
								<spring:message	code="label.customize" />
								</a>
							</li>
							<li>
								<a href="javascript:doReset();" id="resetGridSetting">
								<img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon cursor-pointer" height="25" width="25" />
								<spring:message	code="label.reset" />
								</a>
							</li>
    						</ul>
						</div>
					</div>
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	<div id="serviceAddressGridbox" class="gridbox gridbox_light"></div>
							<div id="loadingNotification_addr" class='gridLoading'>
							<br><!--spring:message code="requestInfo.popup.loading"/-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
							</div>
						</div>
					</div>
						
				<div class="pagination" id="paginationId"><span id="pagingArea5"></span><span id="infoArea"></span></div>					
					<div class="buttonContainer" id="buttonContact_popup">
  						<button class="button_cancel" id="cancelAddressButton" onclick="dialog.dialog('close'); <%-- callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkPPOmnitureConstants.ADDRESSPOPUPCANCELADDRESSPOPUP%>'); --%>" type="button"><spring:message code="button.cancel"/></button>
  						<c:if test="${showCreateNew}">
  						 <button class="button" id="createNewAddressButton" onclick="javascript:showAddAddress();" type="button"><spring:message code="claim.label.createNewAddress"/></button>
  						 </c:if>
  					</div>
					<div class="error" id="errorMsg_popup" style="display:none"></div>
					<!-- CREATE NEW ADDRESS FORM START -->
					<div class="oneblock" id="update" style="display:none">
					<h3 class="pageTitle"><spring:message code="requestInfo.popup.button.createNewAddress"/> <span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span><spring:message code="requestInfo.label.areRequired"/></span>
					<%--ADded for MPS 2.1 --%>
					<span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">**</span><spring:message code="requestInfo.label.provideAtleastOne"/></span></h3>
					<%--Ends  --%>
       				 <p class="info"><spring:message code="requestInfo.cm.manageAddress.heading.addAddressesInfo"/></p>
					<div class="portletBlock rounded shadow split" id="newAdd" style="display: none;">
							<div class="columnsTwo">
          						<div class="columnInner">
														
								<ul class="form">
									<li>
										<label for="storeName"><spring:message code="requestInfo.addressInfo.label.storeFrontName"/> </label> 
										<span><input type="text" value="${accountName}" id="storeName" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span> 
										<img src="<html:imagesPath/>transparent.png" class="helpIconPopup ui-icon info-icon" title="<spring:message code="requestInfo.tooltip.storeFrontName"/>">
									</li>
									<li>
										<label for="addrLine1"><spring:message code="claim.label.addressLine1"/><span	class="req">*</span>
										</label>
										<span><input type="text" id="addrLine1" maxlength="70" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
									</li>
									<li>
										<label for="addrLine2"><spring:message code="claim.label.addressLine2"/></label> 
										<span><input type="text" id="addrLine2" maxlength="35" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
									</li>
									<%--<li>
										<label for="addrLine3"><spring:message code="requestInfo.addressInfo.label.addressLine3"/></label> 
										<span><input type="text" id="addrLine3" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');"/></span>
									</li> --%>
									<li>
										<label for="cityPopup"><spring:message code="claim.label.city"/><span class="req">*</span>
										</label> 
										<span><input type="text" id="cityPopup" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
									</li>
								</ul>
							</div>
						</div>
							<div class="columnsTwo">
							<div class="columnInner">
								<ul class="form">
									
									<li>
										<label for="country"><spring:message code="claim.label.country"/><span class="req">*</span>
										</label> 
										<span><select name="country" id="country_popup" onchange="loadStateList_popup();" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"></select>
										<img src="<html:imagesPath/>loading-icon.gif" id="loadingImage_country" style="display: none;"/>
										</span> 										
									</li>
									<li>
										<label for="state"><spring:message code="requestInfo.addressInfo.label.region"/><span class="req">**</span>
										</label> 
										<span><select name="state" id="state_popup" onfocus="jQuery(this).removeClass('errorColor');jQuery('#zipCode').removeClass('errorColor');" onmousedown="jQuery('#zipCode').removeClass('errorColor');jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"></select>
										<img src="<html:imagesPath/>loading-icon.gif" id="loadingImage_state" style="display: none;"/>
										<img src="<html:imagesPath/>transparent.png" class="helpIconPopup ui-icon info-icon" id="helpIconRegion" title="<spring:message code="requestInfo.addressInfo.label.regionDetails"/>" />
										</span>
										<div id="stateorzipmesg" class="note pageTitle" ><span class="margin-top--8px">(<spring:message code="requestInfo.addressInfo.label.regionDetails"/>)</span></div>
									</li>
									<li>
										<label for="zipCode" ><spring:message code="requestInfo.addressInfo.label.postalCode"/><span class="req">**</span>
										</label> 
										<span><input type="text" id="zipCode" maxlength="30" onfocus="jQuery(this).removeClass('errorColor'); jQuery('#state_popup').removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#state_popup').removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});" /></span>
										
									</li>
								</ul>
							</div>
						</div>
				</div>
					

						<div class="buttonContainer" id="button_popup" style="display:none;">
						<div id="ignoreSaveAddress" style="display: none;" align="left">
							<input type="checkbox" id="ignoreSaveAddrChk" onclick="ignoreAndSave();"/>
							<label><spring:message code="addressCleansing.error.ignore.warning">
							</spring:message></label>
						</div>
							<button class="button_cancel"
								onclick="javascript:hideAddAddress();"><spring:message code="button.cancel"/></button>
							&nbsp;
							<button class="button"
								onclick="javascript:showCleansedaddress();"><spring:message code="button.validateaddress"/>
								</button>
						</div>
					</div>
					<div id="cleansedAddress" style="display:none;">
					<hr class="separator" />
						<p class="info banner"><spring:message code="requestInfo.popup.cleansedAddresses"/></p>
						<div class="portletBlock infoBox">
						<div class="columnsOne">
						<div class="columnInner">
						<ul class="roDisplay">
						
						
									<li id="cleanseAddressOutputPopup"></li>
									

								</ul>
								<p class="check">
								<input type="checkbox" id="check_popup"	onclick="javascript:validate_popup();" /> 
								<label for="modAddress">
								<%-- <spring:message code="requestInfo.popup.cleansedAddresses"/> --%>
								<spring:message code="requestInfo.popup.usecleansedAddresses"/>
								</label>
								</p>
								
							</div>
						</div>
					</div>
						<div class="buttonContainer">
							<button class="button_cancel" onclick="hideadd();"><spring:message code="button.cancel"/></button>
							&nbsp;
							<button id="btnSelect_addr" class="button" onclick="saveAddressFromPopupToPage();"><spring:message code="button.save"/>&nbsp;<spring:message code="requestInfo.cm.manageAddress.heading.newAddresses"/>
								</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>

<script>

var cleanseAddrLine1, cleanseAddrLine2,cleanseaddrCity,cleanseaddrZipPostal,cleanseaddrStateProv,cleanseaddrCountry,
cleanseAddrStoreFrNm;

var cleaseAddressFields={};

var goForCleanseAddrFlg = false;
//following variables are declared in dynamicGridInitialize
pagingArea="pagingArea5";infoArea="infoArea";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_addr";backFilterValues="";
gridCreationId="serviceAddressGridbox";
pageSize=5,pagesIngrp=6;
JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='serviceRequest.listHeader.serviceAddressListPopUp'/>,,,,,,";
JSON_Param["<%=gridConfigurationValues[1]%>"]=",,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,";
JSON_Param["<%=gridConfigurationValues[2]%>"]="center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
JSON_Param["<%=gridConfigurationValues[3]%>"]="100,40,100,100,100,100,80,50,60,80,75,80,80,80,80,0,0,0,0,0"; <%-- changed for CI7 --%>
JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
JSON_Param["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,str,str,na,na,na,na,na";
JSON_Param["<%=gridConfigurationValues[6]%>"]="0,1,2";
JSON_Param["<%=gridConfigurationValues[7]%>"]="2,des";
JSON_Param["<%=gridConfigurationValues[8]%>"]="1,3,4,7,8,9,10";
JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrievePartnerAddressList"/>";
JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
JSON_Param["columnMove"]="false";


var functionToCallAfterSelect;
var linkspanId;
var inputIds;

/*
 *Added for MPS phase 2 
 *
 */

reloadGrid=function(){
	//clearMessage();
	//alert('in reload Grid');
	refreshGridSettingOnPage(requestListGrid);
	xmlURLQueryParams.setLoadXMLUrl();
	requestListGrid.clearAndLoad(xmlURL);
	
};


initURLParams = function(){
		xmlURLQueryParams={
				isFavorite:false,
				urlParameters:["direction","orderBy","isFavorite","timezoneOffset","filterCriterias","ispopup","accountId"],
				setParameters : function(){
										this["direction"]=requestListGrid.a_direction;
										this["orderBy"]=requestListGrid.getSortColByOffset()-1;
										this["timezoneOffset"]=timezoneOffset;
									
										this["ispopup"]=true;
										this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
										this["accountId"]=jQuery('#partnerAccountIdHidden').val();
										},
				setLoadXMLUrl : function(){
												xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
												this.setParameters();
												for(i=0;i<this.urlParameters.length;i++){
													xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														
												}	
										}
				};
		
			
			
			
		};

//Ends Phase 2


function initializeAddressGrid(hyperLinkId,spanContactId,selectSuccessFunction){
	functionToCallAfterSelect=selectSuccessFunction;
	
 	linkspanId=hyperLinkId;
 	inputIds=spanContactId;
	initialiseGrid();
}


function showAllAccountAddress(){
	jQuery('#alladdress').html('<spring:message code="claim.button.allAccountAddresses"/>');
	jQuery('#myBookmarkAddress').html('<a href="javascript:showBookmarkedAddress();"><spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/></a>');

	xmlURLQueryParams.isFavorite=false;
	reloadGrid();
	
}
function showBookmarkedAddress(){
	//disable my bookmarked addresses
	jQuery('#myBookmarkAddress').html('<spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/>');
	//change All Account Address to Link
	jQuery('#alladdress').html('<a href="javascript:showAllAccountAddress();"><spring:message code="claim.button.allAccountAddresses"/></a>');
	xmlURLQueryParams.isFavorite=true;
	reloadGrid();
	
}

var favStatus = {};
function setServiceAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag){ 
	 <%-- callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkPPOmnitureConstants.ADDRESSPOPUPBOOKMARKADDRESS%>'); --%>
	var url = '<portlet:resourceURL id="setServiceAddressFavouriteFlag"/>';
    url += "&favoriteAddressId="+favoriteAddressId;
    url += "&contactId=${contactid}";

      
    var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
    var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
    favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
    starImg.src = "<html:imagesPath/>loading-icon.gif";
    url += "&flagStatus=" + isFavorite;
    
    doAjax(url, callbackGetServiceAddressResult, callbackGetServiceAddressFailure, "");
}

// call back, when successfully update favorite status of service address
function callbackGetServiceAddressResult(result) {
	var addressId = result.data;
	if (document.getElementById("serviceAddressGridbox").style.display!="none") {		
		var starImg = document.getElementById('starImg_address_' + addressId);
		if (favStatus[addressId].isFavorite) {
			starImg.src = "<html:imagesPath/>unfavorite.png";
		} else {
			starImg.src = "<html:imagesPath/>favorite.png";
		}
	}
	if (favStatus[addressId].isFavorite) {
		favStatus[addressId].isFavorite = false;
	} else {
		favStatus[addressId].isFavorite = true;
	}
	favStatus[addressId].isLoading = false;
}

// call back, when fail to update favorite status of service address
function callbackGetServiceAddressFailure(result) {
    var addressId = result.data;
    if (document.getElementById("serviceAddressGridbox").style.display!="none") {      
        var starImg = document.getElementById('starImg_address_' + addressId);
        if (favStatus[addressId].isFavorite) {
            starImg.src = "<html:imagesPath/>favorite.png";
        } else {
            starImg.src = "<html:imagesPath/>unfavorite.png";
        }
    }
    favStatus[addressId].isLoading = false;
}

function hideAddAddress() {
	<%-- callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkPPOmnitureConstants.ADDRESSPOPUPCANCELADDRESSPOPUP%>'); --%>
	
	document.getElementById('buttonContact_popup').style.display = "block";
	document.getElementById('update').style.display = "none";
	jQuery('#errorMsg_popup').hide();
	//change dialog to original height
	jQuery('#content_addr').data('height.dialog','500px');
	}
		
	function showAddAddress() {
		 <%-- callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkPPOmnitureConstants.ADDRESSPOPUPCREATENEWADDRESS%>'); --%>
		//showToolTip('helpIconPopup');
		//alert('showAddAddress');
		jQuery('#buttonContact_popup').hide();
		jQuery('#button_popup').show();
		//document.getElementById('update').style.display = "block";
		jQuery('#newAdd').show();
		jQuery('#update').show();
		jQuery('#check_popup').attr('checked',false);
		enableInputs();
		//load state and country list
		loadCountryList_popup();
		//change the dialog height
		jQuery('#content_addr').data('height.dialog','700px');
		
	}
	function hideCleansedaddress() {
	document.getElementById('cleansedAddress').style.display = "none";
	}
	function validateFields_popup_Address(){
		var validationFlag=true;
		
		if(jQuery('#addrLine1').val().trim()=="")
		{
			validationFlag=false;
			jQuery('#address_error_div_popup').append('<li><strong><spring:message code="validation.address.addrline1.empty"/></strong></li>');
			jQuery('#addrLine1').addClass('errorColor');
		}else{
				
			}
		
		if(jQuery('#cityPopup').val().trim()=="")
		{
			validationFlag=false;
			jQuery('#address_error_div_popup').append('<li><strong><spring:message code="validation.address.city.empty"/></strong></li>');
			jQuery('#cityPopup').addClass('errorColor');
		}else{
			}

		
		if(jQuery('#country_popup option:selected').text()=="")
		{
			validationFlag=false;
			jQuery('#address_error_div_popup').append('<li><strong><spring:message code="validation.address.country.empty"/></strong></li>');
			jQuery('#country_popup').addClass('errorColor');
		}
			/* Validation Changes For address Cleansing - Phase 2*/
		if((jQuery('#zipCode').val().trim()=="")&&(jQuery('#state_popup option:selected').text()=="")){
			
			validationFlag=false;
			jQuery('#address_error_div_popup').append('<li><strong><spring:message code="validation.address.ziporstate.empty"/></strong></li>');
			jQuery('#state_popup').addClass('errorColor');
			jQuery('#zipCode').addClass('errorColor');
			}
	
		if(validationFlag==false)
			return false;
		else 
			return true;
		
	}
		
	function showCleansedaddress() {
		 <%-- callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkPPOmnitureConstants.ADDRESSPOPUPSAVENEWADDRESS%>'); --%>
		//clear the div error contents
		jQuery('#address_error_div_popup').html('');
		var statusValid=validateFields_popup_Address();
		//alert('city val'+jQuery('#cityPopup').val());
		if(statusValid==false)
		{
			jQuery(document).scrollTop(0);
			jQuery('#address_error_div_popup').show();
		}
		else{
		
		jQuery('#address_error_div_popup').hide();
		jQuery.ajax({
				url:'${newAddressValidateURL}',
				beforeSend: function()
				{
					showOverlayPopup();
				},
				type:'POST',
				dataType: 'JSON',
				data: {
						<%=ADDRESSCLEANSEFIELDS_INPUT[0]%>: jQuery('#storeName').val(),
						<%=ADDRESSCLEANSEFIELDS_INPUT[1]%>: jQuery('#addrLine1').val(),
						<%=ADDRESSCLEANSEFIELDS_INPUT[2]%>: jQuery('#addrLine2').val(),
						<%=ADDRESSCLEANSEFIELDS_INPUT[3]%>: jQuery('#cityPopup').val(),
						<%=ADDRESSCLEANSEFIELDS_INPUT[4]%>:	jQuery('#country_popup option:selected').val(),
						<%=ADDRESSCLEANSEFIELDS_INPUT[5]%>:	jQuery('#state_popup option:selected').val(), 	
						<%=ADDRESSCLEANSEFIELDS_INPUT[6]%>: jQuery('#zipCode').val()
						
						
					},
				success: function(results){
						//alert('success');
						hideOverlayPopup();
						var obj2 = results;
						
						if(obj2 !=null){						
							var error=obj2.error;
							//alert('obj2 is not null');
							//alert(error);
							if(error=="none"){
	
								
								jQuery.each(obj2, function(name, value) {
										cleaseAddressFields[name]=value;		
									});

								jQuery('#cleanseAddressOutputPopup').html(generateAddressDisplayHTML(obj2));
								<%--
								//set the values to span
								jQuery('#storefront_popup_span').html(cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[0]%>"]);
								jQuery('#addrLine1_popup_span').html(cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[1]%>"]);
								jQuery('#addrLine2_popup_span').html(cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[2]%>"]);
								jQuery('#city_state_zip_popup_span').html(cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[3]%>"] +','+cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[5]%>"]+','+cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[6]%>"]);
								jQuery('#country_popup_span').html(cleaseAddressFields["<%=ADDRESSCLEANSEFIELDS_INPUT[4]%>"]);
								--%>
								
								//display the cleansed address
								jQuery('#cleansedAddress').show();
								//if error div is already show hide it
								jQuery('#errorMsg_popup').hide();
								document.getElementById('button_popup').style.display = "none";
								jQuery('#content_addr').data('height.dialog','1000px');
								jQuery("#ignoreSaveAddress").hide();
								}
							else if(error=="cleanseError")
								{
								jQuery('#errorMsg_popup').html(obj2.cleansedError);
								jQuery('#errorMsg_popup').show();
								jQuery("#ignoreSaveAddress").show();
								}
							else{
									jQuery('#errorMsg_popup').html('');
									jQuery.each(obj2, function(name, value) {
										if(value!="yes")
										jQuery('#errorMsg_popup').append('<li><strong>'+value+'</strong></li>');
										
									});
									jQuery('#errorMsg_popup').show();
									jQuery('#content_addr').data('height.dialog','800px');
																	
								}
						}	else{
							//show the error div with error details
							
							//alert('in else');
							//alert(results);
							jQuery('#errorMsg_popup').html('');
							jQuery('#errorMsg_popup').append(results);
							jQuery('#errorMsg_popup').show();
							//to be changed according to the error div height
							jQuery('#content_addr').data('height.dialog','800px');
							}
					},
				failure: function(results){
							jQuery('#errorMsg_popup').html('<li><strong>Unable to cleanse address</strong></li>');
							jQuery('#errorMsg_popup').show();
						}
			});
		}
		
		
	}
	

	function hideadd() {
		 <%-- callOmnitureAction('<%=LexmarkPPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkPPOmnitureConstants.ADDRESSPOPUPCANCELADDRESSPOPUP%>'); --%>
		jQuery('#cleansedAddress').hide();
		jQuery('#buttonContact_popup').show();
		clearInputs();
		jQuery('#newAdd').hide();
		jQuery('#update').hide();
		jQuery('#errorMsg_popup').hide();
		jQuery('#content_addr').data('height.dialog','500px');
		
	/*showUnUsed();
	document.getElementById('check').checked=false;
	document.getElementById('cleansedAddress').style.display = "none";
	document.getElementById('button_popup').style.display = "block";
	hideAddAddress();*/
	}
	function showUsed() {
		document.getElementById('newAdd').style.display = "none";
		//document.getElementById('usedNewAdd').style.display = "block";
	}
	function showUnUsed() {
		//document.getElementById('usedNewAdd').style.display = "none";
		document.getElementById('newAdd').style.display = "block";
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
		jQuery('#storeName').removeAttr('disabled');
		jQuery('#addrLine1').removeAttr('disabled');
		jQuery('#addrLine2').removeAttr('disabled');
		/*jQuery('#addrLine3').removeAttr('disabled');*/
		jQuery('#cityPopup').removeAttr('disabled');
		jQuery('#country_popup').removeAttr('disabled');
		<%-- Changes for Phase 2.1 State should be disable by default--%>
		jQuery('#state_popup').attr('disabled','disabled');
		<%-- Ends --%>
		jQuery('#zipCode').removeAttr('disabled');
		
		}
	function disableInputs(){
		jQuery('#storeName').attr('disabled','disabled');
		jQuery('#addrLine1').attr('disabled','disabled');
		jQuery('#addrLine2').attr('disabled','disabled');
		//jQuery('#addrLine3').attr('disabled','disabled');
		jQuery('#cityPopup').attr('disabled','disabled');
		jQuery('#country_popup').attr('disabled','disabled');
		jQuery('#state_popup').attr('disabled','disabled'); 						
		jQuery('#zipCode').attr('disabled','disabled');
	}
	function clearInputs(){
		jQuery('#storeName').val("${accountName}");
		jQuery('#addrLine1').val('');
		jQuery('#addrLine2').val('');
		/*jQuery('#addrLine3').val('');*/
		jQuery('#cityPopup').val('');
		jQuery('#country_popup').val('');
		jQuery('#state_popup').val('');						
		jQuery('#zipCode').val('');
	}

	//Ajax call to load the country list
	function loadCountryList_popup(){
		jQuery.ajax({
			url:'${countryListPopulateURL}',
			beforeSend: function(){
						jQuery('#loadingImage_country').show();
					},
			success: function(countryListResult){
						jQuery('#loadingImage_country').hide();
						jQuery("#country_popup").append(countryListResult);			
				}
		});
	}
	//Ajax call to load the State List
	function loadStateList_popup(){
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
						}
							
								
				}
		});
		
	}
	function saveAddressFromPopupToPage(){

		var addressRowObj;
		if(jQuery('#check_popup').attr('checked')){
			addressRowObj=createObjFromUserInputs();
			
		}else{
			addressRowObj={
					clickFromId:"",
					inputIds:"",
					keys:["addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country","addressId","countryISOCode","stateCode","stateFullName","latitude","longitude","savedErrorMessage","isAddressCleansed"],
					setValues:function(){
						for(i=0;i<this.keys.length;i++){
								if(this.keys[i]=="isAddressCleansed"){
										this[this.keys[i]]=true;
								}else{
										
										if(this.keys[i] in cleaseAddressFields){
											this[this.keys[i]]=cleaseAddressFields[this.keys[i]];
										}else{
											//alert(this.keys[i]+'not in obj');
											this[this.keys[i]]="";
										}
									}
								}
							}		
					};
		}	
						
			
	 addressRowObj.clickFromId=linkspanId;
	 addressRowObj.inputIds=inputIds;

	 	
	 addressRowObj.setValues();
	 functionToCallAfterSelect(addressRowObj);
	
		<%-- 
		if(goForCleanseAddrFlg)
		{	
			goForCleanseAddrFlg = false;
			addServiceAddressElement(null,null,cleanseAddrLine1,cleanseAddrLine2,cleanseaddrCity,null,cleanseaddrCountry,cleanseaddrZipPostal,
			cleanseAddrStoreFrNm,null, null, null,null, null, null,null);
		
			addRestFieldsOfCleanseAddress();
		
		}
		else{
		goForCleanseAddrFlg = false;
		
		
		
		}--%>
	}
	
	function createObjFromUserInputs(){
		var addressRowObj={
				clickFromId:"",
				inputIds:"",
				keysIDInputs:["addrLine1","addrLine2","cityPopup","state_popup","country_popup","zipCode","storeName"],
				keys:["addressLine1","addressLine2","city","state","country","postalCode","storeFrontName","isAddressCleansed"],
				setValues:function(){
					for(i=0;i<this.keys.length;i++){
							if(this.keys[i]=="isAddressCleansed"){
								this[this.keys[i]]=false;
							}else{
									this[this.keys[i]]=jQuery('#'+this.keysIDInputs[i]).val();
									
								}
							}
						}		
				};
		return addressRowObj;
		
	}
	
	function ignoreAndSave(){
		var addressRowObj=createObjFromUserInputs();
		
		addressRowObj.clickFromId=linkspanId;
		addressRowObj.inputIds=inputIds;
		 addressRowObj.setValues();
		 functionToCallAfterSelect(addressRowObj);	
		<%-- 
		addServiceAddressElement(null,null,jQuery('#addrLine1').val(),jQuery('#addrLine2').val(),
		jQuery('#cityPopup').val(),jQuery('#state_popup').val(),null,jQuery('#country_popup').val(),jQuery('#zipCode').val(),
		jQuery('#storeName').val(),null, null, null,null,null,null,null);
		--%>	
	}
	


       

	
	
	
	function selectAddress(addressId,btnObj){
			 var addressRowObj={
						clickFromId:"",
						inputIds:"",
						keys:["addressName","storeFrontName","addressLine1","addressLine2","officeNumber","city","state","province","county","district","region","postalCode","country","addressId","countryISOCode","physicalLocation1","physicalLocation2","physicalLocation3"],
						setValues:function(rowId){
							for(i=0;i<this.keys.length;i++){
															
									this[this.keys[i]]=requestListGrid.cellById(addressId,i+2).getValue();
								}		
						}
						
								
					};
			 addressRowObj.setValues(addressId);
			 addressRowObj.clickFromId=linkspanId;
			 addressRowObj.inputIds=inputIds;
			 functionToCallAfterSelect(addressRowObj);
				
			

		
			
		}

	
	

</script>


