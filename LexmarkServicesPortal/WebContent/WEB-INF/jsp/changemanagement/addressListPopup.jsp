<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%--MPS 2.1 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_INPUT"%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ISPOPUP"%>
<%--ends --%>
<c:if test='${includeGrid eq "true" || includeGrid eq null}'>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
</c:if>

<%--Changes for defect 7960 MPS 2.1--%>
<!--[if IE 8]>
       <style>.relativeR{position:relative!important;}
       </style>
<![endif]-->
<%-- Ends Changes for defect 7560 MPS 2.1--%>
<portlet:resourceURL var="newAddressValidateURL" id="newAddressValidate"></portlet:resourceURL>
<portlet:renderURL var="countryListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="countryDropDownPopulate"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="stateListPopulateURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="stateDropDownPopulate"></portlet:param>
</portlet:renderURL>
<script type="text/javascript">
var addressFlag = "<%=request.getParameter("addressFlag")%>";
//alert('addressFlag is '+addressFlag);
if(addressFlag!="null"){
	if(addressFlag=="false"){
		//alert('about to remove the button');
		document.getElementById('createNewAddressButton').style.display = 'none';
	}else{
		//alert('inside else block');
	}
}else{
	//alert('not sending any address flag');
}



</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content_addr">
  

		
		<div class="portletBlock">
			<div class="columnsOne">
				<div class="columnInner">
				<div class="error" style="display: none;" id="address_error_div_popup"></div>
				<%--Added for MPS 2.1 --%>
				<div><ul class="serviceSuccess" id="message_banner_Popup_address"></ul>
				<ul class="serviceError" id="error_banner_Popup_address"></ul>
				  
				</div>
				<%--end Added for MPS 2.1 --%>
					<div class="grid-controls">
  						<div class="filterLinks">
							<ul>
      						<li class="first" id="alladdress"><spring:message code="requestInfo.cm.manageAddress.link.allAccAddress"/></li>
      						<li><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite bookmark-star-icon">
      						<span id="myBookmarkAddress"><a href="javascript:showBookmarkedAddress();"><spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/></a></span>
      						</li>
      						</ul>
      					</div>
      					<div class="expand-min">
      						<ul>
      						<li>
	             				<a href="#grid" id='headerMenuButton'>
	             				<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon cursor-pointer" ; />
	             				<spring:message	code="serviceRequest.label.customize" />
								</a>
							</li>
							<li>
								<a href="javascript:doReset();" id="resetGridSetting">
								<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon cursor-pointer" ; />
								<spring:message	code="serviceRequest.label.reset" />
								</a>
							</li>
    						</ul>
						</div>
					</div>
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	<div id="serviceAddressGridbox" class="gridbox gridbox_light"></div>
							<div id="loadingNotification_addr" class='gridLoading'>
							<!--spring:message code="requestInfo.popup.label.loading"/>&nbsp;&nbsp; --><img src="<html:imagesPath/>gridloading.gif" />
							</div>
						</div>
					</div>
						
				<div class="pagination" id="paginationId"><span id="pagingArea5"></span><span id="infoArea"></span></div>					
					<div class="buttonContainer" id="buttonContact_popup">
  						<button class="button_cancel" id="cancelAddressButton" onclick="dialog.dialog('close'); callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkSPOmnitureConstants.ADDRESSPOPUPCANCELADDRESSPOPUP%>');" type="button"><spring:message code="button.cancel"/></button>
  						<button class="button" id="createNewAddressButton" onclick="javascript:showAddAddress();" type="button"><spring:message code="requestInfo.popup.button.createNewAddress"/></button>
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
 									<li id="list1"> <!--ID given for UI Change under CI BRD 13-10-08 -->
										<label for="storeName"><spring:message code="requestInfo.addressInfo.label.storeFrontName"/> </label> 
										<span><input type="text" value="${accountName}" id="storeName" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span> 
										<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.storeFrontName"/>">
									</li>
									<li id="list2"> <!--ID given for UI Change under CI BRD 13-10-08 -->
										<label for="addrLine1"><spring:message code="requestInfo.addressInfo.label.addressLine1"/><span	class="req">*</span>
										</label>
										<span><input type="text" id="addrLine1" maxlength="70" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
									</li>
									<li id="list3"> <!--ID given for UI Change under CI BRD 13-10-08 -->
										<label for="addrLine2"><spring:message code="requestInfo.addressInfo.label.addressLine2"/></label> 
										<span><input type="text" id="addrLine2" maxlength="35" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
									</li>
									<%-- Changes for MPS 2.1 House Number Added--%>
									<li id="liofficeId" style="display: none;"> <!--ID given for UI Change under CI BRD 13-10-08 -->
                        				<label for="officeNo"><spring:message code="requestInfo.addressInfo.label.officeNumber"/>  </label>
                       				    <span><input type="text" id="officeNo" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
                    			    </li>
									<%-- Ends Changes for MPS 2.1 House Number Added--%>
									<li id="list4"> <!--ID given for UI Change under CI BRD 13-10-08 -->
										<label for="cityPopup"><spring:message code="requestInfo.addressInfo.label.city"/><span class="req">*</span>
										</label> 
										<span><input type="text" id="cityPopup" maxlength="50" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"/></span>
									</li>
								</ul>
							</div>
						</div>
							<div class="columnsTwo">
							<div class="columnInner">
								<ul class="form">
									
									<li id="list5"> <!--ID given for UI Change under CI BRD 13-10-08 -->
										<label for="country"><spring:message code="requestInfo.addressInfo.label.country"/><span class="req">*</span>
										</label> 
										<span><select name="country" id="country_popup" onchange="loadStateList_popup();" onfocus="jQuery(this).removeClass('errorColor');" onmousedown="jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"></select>
										<img src="<html:imagesPath/>loading-icon.gif" id="loadingImage_country" style="display: none;"/>
										</span>
									</li>
									<li id="list6"> <!--ID given for UI Change under CI BRD 13-10-08 -->
										<label for="state"><spring:message code="requestInfo.addressInfo.label.region"/><span class="req">**</span>
										</label> 
										<span><select name="state" id="state_popup" onfocus="jQuery(this).removeClass('errorColor');jQuery('#zipCode').removeClass('errorColor');" onmousedown="jQuery('#zipCode').removeClass('errorColor');jQuery(this).removeClass('errorColor');jQuery('#cleansedAddress').hide(function(){jQuery('#button_popup').show();});"></select>
										<img src="<html:imagesPath/>loading-icon.gif" id="loadingImage_state" style="display: none;"/>
										<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" id="helpIconRegion" title="<spring:message code="requestInfo.addressInfo.label.regionDetails"/>" />
										</span>
										<!--Class Changed for UI Change under CI BRD 13-10-08 -->
										<div id="stateorzipmesg" class="note" ><span class="margin-top--8px">(<spring:message code="requestInfo.addressInfo.label.regionDetails"/>)</span></div>
									</li>
									<li id="list7"> <!--ID given for UI Change under CI BRD 13-10-08 -->
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
							<button class="button_cancel margin70" id="btn_cancel_validate"
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
						
						
									<li><div id="storefront_popup_span"></div>
									<div id="addrLine1_popup_span"></div>
									<div id="houseNumber_popup_span"></div>
									<div id="addrLine2_popup_span"></div>
									<%--<span id="addrLine3_popup_span"></span><br />  --%>
									<div id="city_state_zip_popup_span">
									<div class="dBlock dBlock5" id="addcityPopup"></div>
									<div class="dBlock dBlock5" id="addcountyPopup"></div>
									<div class="dBlock dBlock5" id="addstatePopup"></div>
									<div class="dBlock dBlock5" id="addprovincePopup"></div>
									<div class="dBlock dBlock5" id="addregionPopup"></div>
									</div>
									<div id="zip_popup_span"></div>
									<div id="country_popup_span"></div></li>
									

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
							<button class="button_cancel" id="btn_cancel_saveAddress" onclick="hideadd();"><spring:message code="button.cancel"/></button>
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
<script type="text/javascript">
	<%-- Changes for MPS 2.1 House Number Added--%>
var cleanseAddrLine1, cleanseAddrLine2,cleanseaddrCity,cleanseaddrZipPostal,cleanseaddrStateProv,cleanseaddrCountry,
cleanseAddrStoreFrNm,cleanseCountryISO,cleanseRegion,cleanseOffice,cleanseStateCode,cleanseDistrict;
	<%-- Ends Changes for MPS 2.1 House Number Added--%>
var cleaseAddressFields={};

var goForCleanseAddrFlg = false;
//following variables are declared in dynamicGridInitialize
pagingArea="pagingArea5";infoArea="infoArea";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification_addr";backFilterValues="";
gridCreationId="serviceAddressGridbox";
pageSize=5,pagesIngrp=6;
JSON_Param["<%=gridConfigurationValues[0]%>"]="<spring:message code='serviceRequest.listHeader.serviceAddressListPopUp'/>";
JSON_Param["<%=gridConfigurationValues[1]%>"]=",,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
JSON_Param["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,center";
JSON_Param["<%=gridConfigurationValues[3]%>"]="100,40,100,100,100,100,80,50,60,80,75,80,80,80,80";
JSON_Param["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
JSON_Param["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,str,str";
JSON_Param["<%=gridConfigurationValues[6]%>"]="0,1";
JSON_Param["<%=gridConfigurationValues[7]%>"]="2,des";
JSON_Param["<%=gridConfigurationValues[8]%>"]="9,10,11";
JSON_Param["<%=JSON_COMBO_FILTER%>1"]="";
JSON_Param["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="addressListPopulate"/>";
JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";

/*
 *Added for MPS phase 2 
 *
 */

reloadGrid=function(){
	refreshGridSettingOnPage(requestListGrid);
	xmlURLQueryParams.setLoadXMLUrl();
	requestListGrid.clearAndLoad(xmlURL);
	
};
initURLParams = function(){
		xmlURLQueryParams={
				isFavorite:false,
				<%--MPS 2.1 Changes Added for address cleansing --%>
				urlParameters:["direction","orderBy","isFavorite","timezoneOffset","addressCleansedFlag","filterCriterias","<%=ISPOPUP%>","lbsFlag"],
				<%--Ends MPS 2.1 Changes Added for address cleansing --%>
				setParameters : function(){
										this["direction"]=requestListGrid.a_direction;
										this["orderBy"]=requestListGrid.getSortColByOffset()-2;
										this["timezoneOffset"]=timezoneOffset;										
										this["<%=ISPOPUP%>"]=true;
										this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
										this["addressCleansedFlag"]='${addressCleansedFlag}';
										this["lbsFlag"]=(location.href.indexOf('fleet-management')!=-1)?true:false;
										if(this["lbsFlag"]==true){
											
											if($.trim($("#gridFilterValues").val()).length > 0)
											{
												var gridFilterValues = ",${accountName},"+$("#gridFilterValues").val();
												this["filterCriterias"]=gridFilterValues;
												requestListGrid.setFiltersValue(gridFilterValues);
												$("#gridFilterValues").val("");
												gridFilterValues = "";
											}
										}
										
										},
				setLoadXMLUrl : function(){
												xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
												this.setParameters();
												for(var i=0;i<this.urlParameters.length;i++){
													xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														
												}	
										}
				};
		
			
			
			
		};

//Ends Phase 2

afterXLEfunction=function(){
}
jQuery(document).ready(function(){
	
	initialiseGrid();

	
});
initializeAddressGrid=function(){};

function showAllAccountAddress(){
	jQuery('#alladdress').html('<spring:message code="requestInfo.cm.manageAddress.link.allAccAddress"/>');
	jQuery('#myBookmarkAddress').html('<a href="javascript:showBookmarkedAddress();"><spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/></a>');

	xmlURLQueryParams.isFavorite=false;
	reloadGrid();
	
}
function showBookmarkedAddress(){
	//disable my bookmarked addresses
	jQuery('#myBookmarkAddress').html('<spring:message code="requestInfo.cm.manageAddress.link.bookmarkedAddress"/>');
	//change All Account Address to Link
	jQuery('#alladdress').html('<a href="javascript:showAllAccountAddress();"><spring:message code="requestInfo.cm.manageAddress.link.allAccAddress"/></a>');
	xmlURLQueryParams.isFavorite=true;
	reloadGrid();
	
}

var favStatus = {};
<%--Added for MPS 2.1 --%>
function setServiceAddressFavourite(favoriteAddressId){
<%--Ends Added for MPS 2.1 --%>
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkSPOmnitureConstants.ADDRESSPOPUPBOOKMARKADDRESS%>');
	var url = '<portlet:resourceURL id="setServiceAddressFavouriteFlag"/>';
    url += "&favoriteAddressId="+favoriteAddressId;
    url += "&contactId=${contactid}";

    // if user selects an unfavorite contact
    
    // if the favorite flag is being updated
    if (favStatus[favoriteAddressId]&&favStatus[favoriteAddressId].isLoading) {
        return;
    }
    
    var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
    var isFavorite=(jQuery("#starImg_address_"+favoriteAddressId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
    //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
    favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
    starImg.src = "<html:imagesPath/>loading-icon.gif";
    url += "&flagStatus=" + isFavorite;
    <%--Added for MPS 2.1 --%>
    doAjaxPopup(url, callbackGetServiceAddressResult, callbackGetServiceAddressFailure, "Popup_address");
    <%--ends Added for MPS 2.1 --%>
}

// call back, when successfully update favorite status of service address
function callbackGetServiceAddressResult(result) {
	var addressId = result.data;
	<%--Added for MPS 2.1 --%>
	if (document.getElementById("serviceAddressGridbox")!=null && document.getElementById("serviceAddressGridbox").style.display!="none") {
	<%--ends Added for MPS 2.1 --%>		
		var starImg = document.getElementById('starImg_address_' + addressId);
		if (favStatus[addressId].isFavorite && starImg!=null) {
			jQuery('#starImg_address_'+addressId).removeClass('bookmark-star-icon');
			jQuery('#starImg_address_'+addressId).addClass('removebookmark-icon');
			starImg.src = "<html:imagesPath/>transparent.png";
			//starImg.src = "<html:imagesPath/>unfavorite.png";
			<%-- Changes for Defect 8028 --%>
			starImg.title="<spring:message code="requestInfo.tooltip.address.Bookmark"/>";
			<%-- End Changes for Defect 8028 --%>
		} else {
			jQuery('#starImg_address_'+addressId).removeClass('removebookmark-icon');
			jQuery('#starImg_address_'+addressId).addClass('bookmark-star-icon');
			starImg.src = "<html:imagesPath/>transparent.png";
			//starImg.src = "<html:imagesPath/>favorite.png";
			<%-- Changes for Defect 8028 --%>
			starImg.title="<spring:message code="requestInfo.tooltip.address.UnBookmark"/>";
			<%-- End Changes for Defect 8028 --%>
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
    <%--Added for MPS 2.1 --%>
    if (document.getElementById("serviceAddressGridbox")!=null && document.getElementById("serviceAddressGridbox").style.display!="none") {
    <%--ends Added for MPS 2.1 --%>      
        var starImg = document.getElementById('starImg_address_' + addressId);
        if (favStatus[addressId].isFavorite && starImg!=null) {
            starImg.src = "<html:imagesPath/>transparent.png";
            
        } else {
            starImg.src = "<html:imagesPath/>transparent.png";
            
        }
    }
    favStatus[addressId].isLoading = false;
}

function hideAddAddress() {
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkSPOmnitureConstants.ADDRESSPOPUPCANCELADDRESSPOPUP%>');
	
	document.getElementById('buttonContact_popup').style.display = "block";
	document.getElementById('update').style.display = "none";
	jQuery('#errorMsg_popup').hide();
	//change dialog to original height
	jQuery('#content_addr').data('height.dialog','500px');
	}
		
	function showAddAddress() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkSPOmnitureConstants.ADDRESSPOPUPCREATENEWADDRESS%>');
		showToolTip('helpIconPopup');
		jQuery('#buttonContact_popup').hide();
		jQuery('#button_popup').show();
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
		}
		
		if(jQuery('#cityPopup').val().trim()=="")
		{
			validationFlag=false;
			jQuery('#address_error_div_popup').append('<li><strong><spring:message code="validation.address.city.empty"/></strong></li>');
			jQuery('#cityPopup').addClass('errorColor');
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
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkSPOmnitureConstants.ADDRESSPOPUPSAVENEWADDRESS%>');
		//clear the div error contents
		jQuery('#address_error_div_popup').html('');
		var statusValid=validateFields_popup_Address();
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
						<%=ADDRESSCLEANSEFIELDS_INPUT[6]%>: jQuery('#zipCode').val(),
						<%-- Changes for MPS 2.1 House Number Added--%>
						<%=ADDRESSCLEANSEFIELDS_INPUT[7]%>: jQuery('#officeNo').val()
						<%-- Ends Changes for MPS 2.1 House Number Added--%>
						
					},
				success: function(results){
						hideOverlayPopup();
						var obj2;
						try{
							 //obj2=jQuery.parseJSON(results);
							obj2=results;
						}catch(e){
							}
						
						if(obj2 !=null){						
							var error=obj2.error;
							if(error=="none"){
								jQuery.each(obj2, function(name, value) {
										cleaseAddressFields[name]=value;		
									});
									
								//modified for MPS 2.1
								jQuery('#storefront_popup_span').html(obj2.storeFrontName);
								jQuery('#addrLine1_popup_span').html(obj2.addressLine1);
								jQuery('#houseNumber_popup_span').html(obj2.officeNumber);
								if(obj2.addressLine2 != '' && obj2.addressLine2 != ' ' && obj2.addressLine2 != null){
									jQuery('#addrLine2_popup_span').html(obj2.addressLine2 + ', ');
									jQuery('#addrLine2_popup_span').show();
									}
								if(obj2.city != '' && obj2.city != ' ' && obj2.city!= null ){
									jQuery('#addcityPopup').html(obj2.city);
								}
								if(obj2.county != '' && obj2.county != ' ' && obj2.county != null ){
									jQuery('#addcountyPopup').html(',&nbsp;'+obj2.county);
									jQuery('#addcountyPopup').show();
								}
								if(obj2.state != '' && obj2.state != ' ' && obj2.state!= null ){
									jQuery('#addstatePopup').html(',&nbsp;'+obj2.state);
									jQuery('#addstatePopup').show();
								} 
															 
								if(obj2.province != '' && obj2.province != ' ' && obj2.province!= null ){
									jQuery('#addprovincePopup').html(',&nbsp;'+obj2.province);
									jQuery('#addprovincePopup').show();

									// region changed to district for MPS 2.1
								}if(obj2.district != '' && obj2.district != ' ' && obj2.district != null ){
									jQuery('#addregionPopup').html(',&nbsp;'+obj2.district);
									jQuery('#addregionPopup').show();
								}
								jQuery('#zip_popup_span').html(obj2.postalCode);
								jQuery('#country_popup_span').html(obj2.country);
								if(obj2.province == '' || obj2.province == ' ' || obj2.province == null ){
									jQuery('#addprovincePopup').hide();
								}
								if(obj2.county == '' || obj2.county == ' ' || obj2.county == null ){
									jQuery('#addcountyPopup').hide();
								}
								// region changed to district for MPS 2.1
								if(obj2.district == '' || obj2.district == ' ' || obj2.district == null ){
									jQuery('#addregionPopup').hide();
								}
								if(obj2.addressLine2 == '' || obj2.addressLine2 == ' ' || obj2.addressLine2 == null ){
									jQuery('#addrLine2_popup_span').hide();
								}
								if(obj2.state == '' || obj2.state == ' ' || obj2.state == null ){
									jQuery('#addstatePopup').hide();
								}
								//modified for MPS 2.1 end

								jQuery('#cleansedAddress').show();
								//if error div is already show hide it
								jQuery('#errorMsg_popup').hide();
								document.getElementById('button_popup').style.display = "none";
								
								jQuery('#content_addr').data('height.dialog','1000px');
								//Changes for phase 2.1
								populateAddressValues(obj2.addressLine1, obj2.addressLine2,
								obj2.city, obj2.postalCode, obj2.state, obj2.country, obj2.storeFrontName,obj2.countryISO,obj2.region,obj2.officeNumber,obj2.stateCode,obj2.district,obj2.county);
								//ends
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
	function hideUsed() {
	//document.getElementById('usedNewAdd').style.display = "none";
	}

	function hideadd() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.SELECTANADDRESSPOPUP%>','<%=LexmarkSPOmnitureConstants.ADDRESSPOPUPCANCELADDRESSPOPUP%>');
		jQuery('#cleansedAddress').hide();
		jQuery('#buttonContact_popup').show();
		clearInputs();
		jQuery('#newAdd').hide();
		jQuery('#update').hide();
		jQuery('#errorMsg_popup').hide();
		jQuery('#content_addr').data('height.dialog','500px');
	}
	function showUsed() {
		document.getElementById('newAdd').style.display = "none";
	}
	function showUnUsed() {
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
		jQuery('#cityPopup').removeAttr('disabled');
		jQuery('#country_popup').removeAttr('disabled');
		<%-- Changes for Phase 2.1 State should be disable by default--%>
		jQuery('#state_popup').attr('disabled','disabled');
		<%-- Ends --%>
		jQuery('#zipCode').removeAttr('disabled');
		jQuery('#officeNo').removeAttr('disabled');
		
		}
	function disableInputs(){
		jQuery('#storeName').attr('disabled','disabled');
		jQuery('#addrLine1').attr('disabled','disabled');
		jQuery('#addrLine2').attr('disabled','disabled');
		jQuery('#cityPopup').attr('disabled','disabled');
		jQuery('#country_popup').attr('disabled','disabled');
		jQuery('#state_popup').attr('disabled','disabled'); 						
		jQuery('#zipCode').attr('disabled','disabled');
	}
	function clearInputs(){
		jQuery('#storeName').val("${accountName}");
		jQuery('#addrLine1').val('');
		jQuery('#addrLine2').val('');
	<%-- Changes for MPS 2.1 House Number Added--%>
		jQuery('#officeNo').val('');
	<%-- Ends Changes for MPS 2.1 House Number Added--%>
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
						}			
				}
		});
		
	}
	function saveAddressFromPopupToPage(){
		 
		if(goForCleanseAddrFlg)
		{	
			goForCleanseAddrFlg = false;
			addServiceAddressElement(null,null,cleanseAddrLine1,cleanseAddrLine2,cleanseaddrCity,
			cleanseaddrStateProv,null,cleanseaddrCountry,cleanseaddrZipPostal,
			cleanseAddrStoreFrNm,null, null, null,null,cleanseOffice, cleanseDistrict,null,cleanseCountryISO,cleanseRegion,cleanseStateCode);
			<%-- Changes for MPS phase 2--%>
			addRestFieldsOfCleanseAddress();
			<%-- ENDS --%>
		}
		else{
		goForCleanseAddrFlg = false;
	
		<%-- Changes for MPS 2.1--%>
		addServiceAddressElement(null,null,jQuery('#addrLine1').val(),jQuery('#addrLine2').val(),
		jQuery('#cityPopup').val(),jQuery('#state_popup').val(),null,jQuery('#country_popup').val(),jQuery('#zipCode').val(),
		jQuery('#storeName').val(),null, null, null,null,jQuery('#officeNo').val(),null,null,null,null,null);
		<%--Ends--%>
		}
	}
	
	function populateAddressValues(addrL1, addrL2, addrCity, addrZipPost, addrStateProv, addrCountry, addrStrFrNm,countryISO,region,office,statecode,district,county)
	{	
		cleanseAddrLine1 = addrL1;
		cleanseAddrLine2 = addrL2;
		cleanseaddrCity = addrCity;
		cleanseaddrZipPostal = addrZipPost;
		cleanseaddrStateProv = addrStateProv;
		cleanseaddrCountry = addrCountry;
		cleanseAddrStoreFrNm = addrStrFrNm;
		cleanseCountryISO = countryISO;
		cleanseRegion = region;
		cleanseOffice = office;
		cleanseStateCode = statecode;
		cleanseDistrict=district;
		
		cleanseCounty=county;
	}
	
	function ignoreAndSave(){		
		addServiceAddressElement(null,null,jQuery('#addrLine1').val(),jQuery('#addrLine2').val(),
		jQuery('#cityPopup').val(),jQuery('#state_popup').val(),null,jQuery('#country_popup').val(),jQuery('#zipCode').val(),
		jQuery('#storeName').val(),null, null, null,null,jQuery('#officeNo').val(),null,null,null,null,null);
	}
</script>