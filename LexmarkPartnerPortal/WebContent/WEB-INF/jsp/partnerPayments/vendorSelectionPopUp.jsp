
<% request.setAttribute("subTabSelected","accountsPayable"); %>
<%@ include file="subTab.jsp"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<!-- 
<div id="tabs" class="nestedTabs ui-tabs ui-widget ui-widget-content ui-corner-all">
<div id="totalContent">
	<jsp:include page="vendorSelection.jsp"/>
</div>        
   <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-al">
   <li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" id="tabLi1">
   	<a href="#tabs-1" onclick="openPopUp('AP');">Accounts Payable</a>
   </li>
   <li class="ui-state-default ui-corner-top" id="tabLi2" >
   		<a id="tab2anchor" onclick="openPopUp('AR');" href="#tabs-2">Accounts Recievable
   		</a>
   </li>
   </ul>
</div>

 -->
 <%-- <div style="display: none;">
  <div id="popup_VendorSelection" class="columnsThree hovfx">
    <div class="columnInner"><img src="/LexmarkServicesPortal/images/req_supplies.png" alt="<spring:message code="partnerPayments.alt.suppliesRequest"/>" class="width-100per height-100px" ></img>
      <p class="brief"></p>
      
      <ul>
        <li><a href="#" onclick="dialog_vendorSelection.dialog('close');openPopUp('AP');"><spring:message code="partnerPayments.link.accountsPayable"/></a></li>
        <li><a href="#" onclick="dialog_vendorSelection.dialog('close');openPopUp('AR');"><spring:message code="partnerPayments.link.accountsReceivable"/></a></li>
      </ul>
    </div>
  </div> 
</div> --%>
<div id="partnerError" style="display: none;">
	<div class="error">
		<ul><li><strong><spring:message code="partnerPayments.errorMsg.selectAnAccount"/></strong></li></ul>
	</div>
	<!-- <div class="portletBodyWrap rounded infoBox">
		<a href="#" onclick="openPopUp('AP');"><span>Select Account</span></a>
	</div> -->
</div>


<script>

var dialog_vendorSelection;
jQuery(document).ready(function(){
	/*
	jQuery("#tabs .ui-tabs-nav li").hover(function(){
		jQuery(this).addClass("ui-state-hover");
	},function() {
		jQuery(this).removeClass("ui-state-hover");
	});

	jQuery("#tabs .ui-tabs-nav li").focus(function(){
		jQuery(this).addClass("ui-state-focus");
	},function() {
		jQuery(this).removeClass("ui-state-focus");
	});

	jQuery("#tabs .ui-tabs-nav li").click(function(){
		jQuery("#tabs .ui-tabs-nav li").removeClass("ui-tabs-selected");
		jQuery("#tabs .ui-tabs-nav li").removeClass("ui-state-active");
		jQuery(this).addClass("ui-tabs-selected");
		jQuery(this).addClass("ui-state-active");

		jQuery("#tabs .tabContent .ui-tabs-panel").addClass("ui-tabs-hiden");
		jQuery("#tabs .tabContent .ui-tabs-panel[0]").removeClass("ui-tabs-hiden");
		var Objid = jQuery(this).children().attr("href");
		jQuery("#tabs .tabContent "+Objid).removeClass("ui-tabs-hiden");
		
	});*/
	 /* dialog_vendorSelection=jQuery('#popup_VendorSelection').dialog({
		autoOpen: false,
		modal: true,
		height: 460,
		width: 300,
		close: function(){
			dialog_vendorSelection.dialog('destroy');
			dialog_vendorSelection=null;
			},
		position: ['center','top']
		});
		dialog_vendorSelection.dialog('open'); */
		
		openPopUp('AP');
	
});



	
</script>