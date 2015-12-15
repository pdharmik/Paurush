<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style type="text/css">
.w100mod{width:100px!important;}
	.w200mod{width:200px!important;}
	.w250mod{width:250px!important;}
 #hwShipToAddr{
       height: 230px;
}
@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content-wrapper-inner">
	<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.hardwareRequest"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.requestHardware"/></h2>
			</div>
	<div class="main-wrap">
		<div class="content">
			<div>
				<a onClick="javascript:doPrint();" id="topPrintLink"> <img
					src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor: pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>



				<%--Changes BRD 12 CI 7--%>
               <div id="accNameAgreeName"></div>
               <%--Changes BRD 12 CI 7--%>
			<div id="divPrintBody"></div>


			<div>
				<a id="btmPrint" onClick="javascript:doPrint();"> <img
					src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	window.document.getElementById("divPrintBody").innerHTML = window.opener.window.document.getElementById("confirmOrderPrintWrapper").innerHTML;
	 window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
	function doPrint() {
		window.document.getElementById("topPrintLink").style.display = 'none';
		window.document.getElementById("btmPrint").style.display = 'none';
		window.print();
		window.close();
	};
</script>
