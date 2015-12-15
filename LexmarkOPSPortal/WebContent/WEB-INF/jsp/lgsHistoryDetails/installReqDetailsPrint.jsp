<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
	@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<!-- CSS include -->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<!-- CSS include -->

<div id="content-wrapper-inner">
	<div class="journal-content-article">
		<h1>
			Install Request Details
		</h1>
		<h2 class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> <span id="reqNo1"><script type="text/javascript">document.writeln(window.opener.window.document.getElementById("HardwareRqstNumber").innerHTML);</script></span></h2>
	</div>
	<div class="main-wrap">
		<div class="content">
			<div>
				<a onClick="javascript:doPrint();" id="topPrintLink"> <img
					src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon"
					style="cursor: pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span style="cursor: pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>




			<div id="divPrintBody"></div>


			<%-- <div>
				<a id="btmPrint" onClick="javascript:doPrint();"> <img
					src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon"
					style="cursor: pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span style="cursor: pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div> --%>
		</div>
	</div>
</div>
<script type="text/javascript">
	
	window.document.getElementById("divPrintBody").innerHTML = window.opener.window.document.getElementById("emailPrintWraper").innerHTML;
	//window.document.getElementById("reqNo1").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
	function doPrint() {
		window.document.getElementById("topPrintLink").style.display = 'none';
		//window.document.getElementById("btmPrint").style.display = 'none';
		window.print();
		window.close();
	};
</script>
