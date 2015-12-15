<%-- <%@ include file="../include.jsp"%> --%>
<%@ page contentType="text/html" isELIgnored="false" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<jsp:include page="/WEB-INF/jsp/include.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%-- includeGrid.jsp has been icnluded if the browser is Safari --%>

<c:set var="userAgent" value="${header['user-agent']}"/>
<c:set var="browserSafari" value="${fn:indexOf(userAgent, 'Safari')}"/>
<c:set var="browserChrome" value="${fn:indexOf(userAgent, 'Chrome')}"/>

<c:if test="${browserSafari!=-1 && browserChrome==-1}">
	<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
</c:if>



<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<script type="text/javascript"><%@ include file="../../../js/jQueryAlert/jquery.alerts.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/validation.js"%></script>

<!-- Added for customize column popup -->
<script type="text/javascript" src="<html:rootPath/>js/jQuery/jquery.qtip-1.0.0-rc3.min.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/mps.js"></script>

<style type="text/css"><%@ include file="/WEB-INF/css/lexmark.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/jQueryAlert/jquery.alerts.css" %></style> 
<%@page import="java.util.HashMap"%> 
<c:set var="subTabSelected" value="${requestScope.subTabSelected}"></c:set>
<c:set var="hMapForAccess" value="${sessionScope.userAccessMapForSr}"></c:set>

<c:choose>
<c:when test="${subTabSelected eq 'requestHistory'}">
<c:set var="requestHistory" scope="page" value="selected"></c:set>
</c:when>
<c:when test="${subTabSelected eq 'createNewRequest'}">
<c:set var="createNewRequest" scope="page" value="selected"></c:set>
</c:when>
</c:choose>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div id="subnavigation" style="display: none;">	
	<!-- Links for history or create new request -->
	<ul>
		<c:if test="${(hMapForAccess['SHOW SERVICE REQUEST'] eq true) or 
		(hMapForAccess['SHOW SUPPLIES REQUEST'] eq true)
		or (hMapForAccess['SHOW CHANGE MGMT REQUEST'] eq true)
		or (hMapForAccess['SHOW HARDWARE REQUEST'] eq true)
		or (hMapForAccess['SHOW_ALL_REQUEST'] eq true)}">
    	<li class="${requestHistory}">
      	<a href="service-requests" title='<spring:message code="button.requestHistory"/>'>
			<span><spring:message code="button.requestHistory"/></span>
		</a>
		</li>
		</c:if>
		<c:if test="${(hMapForAccess['CREATE SERVICE REQUEST'] eq true) or 
		(hMapForAccess['CREATE SUPPLIES REQUEST'] eq true)
		or (hMapForAccess['CREATE CHANGE MGMT REQUEST'] eq true) or
		(hMapForAccess['CREATE HARDWARE REQUEST'] eq true)}" >
		<li class="${createNewRequest}">
      	<a href='<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
      		<portlet:param name="action" value="createNewRequestPopUp"></portlet:param>
			</portlet:renderURL>' class='createNewRequestPopUp' title='<spring:message code="button.createNewRequest"/>' onclick="return popupDialog();">
			<span><spring:message code="button.createNewRequest"/></span>
		</a>
		</li>
		</c:if>
    </ul>
<div id="dialog_createnew" style="display: none;">
<div id="totalContent">
<div id="overlayPopup" style="display:none">
</div>
<div id="processingHintPopup" tabindex="-1" >
   &nbsp;&nbsp;<img class="margin-top-10px" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp; <spring:message code="processing"/><br>
</div>
<div id="createNewRequestPageData"></div>
<jsp:include page="/WEB-INF/jsp/common/accountSelection.jsp"/>

</div>
</div>
</div>	
<%-- ADded for Maps request for LBS to pass filter data in comments --%>
<form id="maps-requestForm" action="mapsrequest" method="post">
	<input type="hidden" id="maps_req_addressId" name="maps_req_addressId" />
	<input type="hidden" id="maps_req_addressName" name="maps_req_addressName"/>
	<input type="hidden" id="maps_req_addressLine1" name="maps_req_addressLine1"/>
	<input type="hidden" id="maps_req_addressLine2" name="maps_req_addressLine2"/>
	<input type="hidden" id="maps_req_addressCity" name="maps_req_addressCity"/>
	<input type="hidden" id="maps_req_addressState" name="maps_req_addressState"/>
	<input type="hidden" id="maps_req_addressProvince" name="maps_req_addressProvince"/>
	<input type="hidden" id="maps_req_addressCountry" name="maps_req_addressCountry"/>
	<input type="hidden" id="maps_req_addressPostCode" name="maps_req_addressPostCode"/>
	
	<input type="hidden" id="maps_req_addressSite" name="maps_req_addressSite"/>
	<input type="hidden" id="maps_req_addressBuilding"  name="maps_req_addressBuilding"/>
	<input type="hidden" id="maps_req_addressFloor" name="maps_req_addressFloor"/>
	<input type="hidden" id="maps_req_addressZone" name="maps_req_addressZone"/>	
</form>
<%-- END Maps request for LBS to pass filter data in comments --%>
<script type="text/javascript">
var language = "${language}";
var country = "${country}";

//alert("Language is " + language + " Country " + country);

	//Open the create new request popup
	var dialog;
function popupDialog(){
	
	isCatalogPage = "false";
	var url='<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">'+
      		'<portlet:param name="action" value="createNewRequestPopUp"></portlet:param>'+
			'</portlet:renderURL>';
			var fleetManagementFlag=(location.href.indexOf('fleet-management')!=-1)?true:false;
			
	<%-- Changes for MPS 2.1 --%>
	url+="&uniqueTime="+new Date().getTime()+"&fleetManagementFlag="+fleetManagementFlag+"&g="+(typeof dhtmlXGridObject=="function"?false:true);
	<%--ENDS  Changes for MPS 2.1 --%>
	showOverlay();
	/*alert('before ajax call');*/
	jQuery('#createNewRequestPageData').load(url,function(){
		/*alert('after ajax call');*/
		dialog=jQuery('#totalContent').dialog({
			autoOpen: false,
			title: jQuery('.createNewRequestPopUp').attr('title'),
			modal: true,
			height: 460,
			width: 700,
			open: function(){
				jQuery('#totalContent').show();
			},
			position: ['center','top'],
			close: function(event,ui){
					/*alert('in close dialog of createnew');*/
					
				 hideOverlay();
				 dialog.dialog('destroy');
				 
				 dialog=null;
				 jQuery('#createNewRequestPageData').empty();
				 jQuery('#accountInitialize').hide();
				 
				 if(ajaxAccountSelection=="success"){
					 	showOverlay();
					 	if(linkObj_accSelect.id=='mapsRequest')<%-- Checking for maps Request as needs to subit the form to pass comments/address data --%>
						{	$('#maps-requestForm').submit();
							
						}else{
							window.location.href=linkObj_accSelect.href;	
						}
						
						
					}
				 
				}
			});
		//jQuery(document).scrollTop(0);
		dialog.dialog('open');
		});
	
	return false;
}
	
	
	
	
	//For cancel Button----- requestType would be history ---goes to CommonController
	function redirectToHistory(pageName)
	{
		if(pageName=='ListPage'){
			showOverlay();
			window.location.href="service-requests";
		}else{

			jConfirm('<spring:message code="common.cancel.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					window.location.href="service-requests";
				}else{
					return false;
					}
			});
		}
		
	}

	function createServiceRequest() {
		window.location.href="<portlet:actionURL><portlet:param name='action' value='checkAccountSRPrivilege'/></portlet:actionURL>";
	};
	
	
	
	jQuery(document).ready(function() {
		//Added for lbs toggling view for device finder and fleet management tabs
		var currentURL = window.location.href;
		//alert("currentURL:: "+ currentURL);
		if(currentURL.indexOf('/fleet-management') > -1){	
			jQuery("#subnavigation").hide();
		}else if(currentURL.indexOf('/grid-view') > -1){
			jQuery("#subnavigation").hide();
		}
		else if(currentURL.indexOf('/service-requests') > -1){
			jQuery("#subnavigation").show();
		}else{
			jQuery("#subnavigation").show();
		}
	});
	
</script>	