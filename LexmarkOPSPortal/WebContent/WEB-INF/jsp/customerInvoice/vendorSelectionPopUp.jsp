
<% request.setAttribute("subTabSelected","accountsReceivable"); %>
<%@ include file="customerInvoiceSubTab.jsp"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<style type="text/css">
       .ie7 .button, .ie7 .button_cancel {
               behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;                
        }
        .buttonContainer{position:relative!important;}
        .button_subrow1{margin-left:81%!important;padding: 0.27em 0.5em 0.34em!important;}
</style>
<![endif]-->

<div id="partnerError" style="display: none;">
	<div class="error">
	<ul><li><strong><spring:message code="partnerPayments.errorMsg.selectAnAccount"></spring:message></strong></li></ul>
	</div>
	<!-- <div class="portletBodyWrap rounded infoBox">
		<a href="#" onclick="openPopUp('AP');"><span>Select Account</span></a>
	</div> -->
</div>


<script type="text/javascript">

var dialog_vendorSelection;
jQuery(document).ready(function(){
	
		openPopUp('AR');
	
});



	
</script>