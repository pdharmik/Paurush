<%@page import="com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum"%>
<%@page import="com.lexmark.services.LexmarkSPConstants"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript">
var ENTITLEMENT_SERVICE_DETAILS = "<%=SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue()%>";
var SERVICE_STATUS = "<%=SiebelLocalizationOptionEnum.SERVICE_STATUS.getValue()%>";
var PARTNER_SERVICE_REQUEST_STATUS = "<%=SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue()%>";
var PARTNER_SERVICE_REQUEST_SUBSTATUS = "<%=SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue()%>";
function showHideEntitlementFlag(){
	var select = document.getElementById("optionTypeSelect");
	var value = select.options[select.selectedIndex].value;

	var showStatusOrder = SERVICE_STATUS==value;
	var objStatusOrder = document.getElementById("tdStatusOrder");
	objStatusOrder.style.display = showStatusOrder?"block":"none";
	
	var toShow = ENTITLEMENT_SERVICE_DETAILS == value;
	var objEntitlementFlag = document.getElementById("tdEntitlementFlag");
	objEntitlementFlag.style.display = toShow ? "block":"none";

	var showPartnerType = (PARTNER_SERVICE_REQUEST_STATUS == value || PARTNER_SERVICE_REQUEST_SUBSTATUS == value);
	document.getElementById("tdPartnerType").style.display = showPartnerType ? "block":"none";
}
function back(){
	var backURL = "<portlet:renderURL></portlet:renderURL>";
	location.href = backURL;
}
function doValidate(){
	var input = document.getElementById("siebelValue");
	var valid = true;
	clearMessage();
	if(input.value.trim()==""){
		showError('<spring:message code="serviceRequestLocaleListPage.details.validateError.siebelValueNull" />',null, true);
		valid = false;
	}
	var select = document.getElementById("optionTypeSelect");
	if(select.value.trim()==""){
		showError('<spring:message code="serviceRequestLocaleListPage.details.validateError.optionTypeNull" />',null, true);
		valid = false;
	}
	valid = validateOrder();
	return valid;
}
function validateOrder(){
	clearMessage();
	var orderValue= document.getElementById("statusOrder").value;
	if(null!= orderValue && ""!=orderValue){
		var patrn = /^[1-9]*$/;
		if(!patrn.exec(orderValue)){
			showError('<spring:message code="serviceRequestLocaleListPage.details.validateError.mustBeNumber" />',null, true);
			document.getElementById("statusOrder").value = "";
			return false;
		}
	}
	return true;
}
</script>
<style>
div.portlet-topper {
	background: url(/LexmarkOPSPortal/images/bg_off_customize.png) repeat-x;
	height: 30px;
	margin: 0;
	padding: 0 !important;
	line-height: normal !important;
}

div.portlet-topper-inner {
	background-image:none;
	height: 30px;
	margin: 0;
	padding: 0;
}

span.portlet-title {
	font-size: 16px !important;
	display: inline;
	line-height: 32px !important;
	width: auto !important;
	margin: 0;
	padding: 0 0 0 15px;
	color: #fff;
	font-weight: normal;
}
.firefox .portlet-title img, .safari .portlet-title img{margin-top: 7px;}
</style>
<portlet:actionURL var="saveURL">
	<portlet:param name="action" value="save"/>
</portlet:actionURL>
<div class="main-wrap">
	<div class="content">
		<form name="SRLocaleDetailsForm" action="${saveURL}" method="post" onsubmit="return doValidate();">
		<input type="hidden" name="action" value="save">
		<table width="800px">
			<tr><td><table class="cellTable">
				<tr>
					<td class='orangeSectionTitles'><spring:message code="serviceRequestLocaleListPage.details.title" /></td>
				</tr>
				<tr>
					<td class='description'><spring:message code="serviceRequestLocaleListPage.details.description" /></td>
				</tr>
			</table></td></tr>
			<tr class="paddingTR"><td>&nbsp;</td></tr>
			<tr><td><table class="cellTable">
				<tr>
					<td width="35%" valign="bottom" class="labelTD">
						<input type="hidden" name="siebelLocalizationId" value="${form.siebelLocalizationId}"> 
						*<spring:message code="serviceRequestLocaleListPage.details.label.selectOption" />:<br>
						<select name="optionType" onchange="showHideEntitlementFlag()" id="optionTypeSelect">
							<option value=""></option>
							<c:forEach var="type" items="${form.optionTypes}">
								<option value="${type.key}" <c:if test="${type.key eq form.optionType}">selected</c:if> >${type.value}</option>
							</c:forEach>
						</select>

					</td>
					<td id="tdEntitlementFlag" valign="bottom" class="labelTD">
							<input type="checkbox" name="showEntitlementFlag" <c:if test="${form.showEntitlementFlag}">checked="checked"</c:if> id="showEntitlementFlagCheckBox"/>
							<spring:message code="serviceRequestLocaleListPage.details.label.showEntitlement" />
					</td>
					<td id="tdStatusOrder" valign="bottom" class="labelTD">
						<span>
							&nbsp;<spring:message code="serviceRequestLocaleListPage.details.label.statusOrder" /><br>
							<div class="inputMedWrapper">
							<input type=text name="statusOrder"  id="statusOrder" onkeyup="validateOrder();" value="${form.statusOrder}" class="inputMed" maxlength="9"/>
							</div>
						</span>	
					</td>
					<td id="tdPartnerType" valign="bottom" class="labelTD">
							<input type="checkbox" name="directPartnerFlag" <c:if test="${form.directPartnerFlag}">checked="checked"</c:if> id="directPartnerCheckBox"/>
							<spring:message code="serviceRequestLocaleListPage.details.label.directPartner" /><br>
							<input type="checkbox" name="indirectPartnerFlag" <c:if test="${form.indirectPartnerFlag}">checked="checked"</c:if> id="indirectPartnerCheckBox"/>
							<spring:message code="serviceRequestLocaleListPage.details.label.indirectPartner" /><br>
					</td>
				</tr>
			</table>
			<table class="cellTable">
				<tr>
					<td colspan="2" class="labelTD">
						*<spring:message code="serviceRequestLocaleListPage.details.label.siebelValue" />:<br>
						<div class="inputLgWrapper">
						<input type="text" name="siebelValue" id="siebelValue" value="${form.siebelValue}" class="inputLg" maxlength="250" >
						</div>
					</td>
				</tr>
				<tr> 
					<td class="labelTD"><spring:message code="serviceRequestLocaleListPage.details.label.supportedLanguage" /></td>
					<td class="labelTD"><spring:message code="serviceRequestLocaleListPage.details.label.displayValue" /></td>
				</tr>
				<c:forEach items="${form.localeIds}" varStatus="status">
					<tr> 
						<td>
							<input type="hidden" name="localeIds" value="${form.localeIds[status.index]}"> 
							<input type="hidden" name="localizationLocaleIds" value="${form.localizationLocaleIds[status.index]}"> 	
							<input type="hidden" name="localeNames" value="${form.localeNames[status.index]}"> 
							<input type="hidden" name="localeCodes" value="${form.localeCodes[status.index]}"> 
							${form.localeNames[status.index]}
						</td>
						<td><div class="inputLgWrapper"><input type="text" name="displayValues" class="inputLg" value="${form.displayValues[status.index]}" maxlength="250"></div></td>
					</tr>
				</c:forEach>
				<tr> 
					<td colspan="2">
					<div style="float:right">
						<a href="javascript:back();" class="button_cancel" style="text-decoration:none;color:#606060;"><spring:message code="button.cancel"/></a>&nbsp;
						<a href="javascript:document.SRLocaleDetailsForm.submit();" class="button" style="text-decoration:none;color:#FFF;"><spring:message code="button.submit"/></a>
					</div>
					</td>
				</tr>
			</table></td></tr>
		</table>
		</form>
	</div>
</div>	
<script type="text/javascript">showHideEntitlementFlag();</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Locale";
     addPortlet(portletName);
     pageTitle="Locale details";
</script>