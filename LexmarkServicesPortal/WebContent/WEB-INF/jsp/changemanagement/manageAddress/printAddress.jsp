<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<style type="text/css"><%@ include file="/WEB-INF/css/mps.css" %></style>

<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content-wrapper-inner">
	<div class="journal-content-article">
		<h1>
			<spring:message code="requestInfo.cm.heading.changeRequest" />
		</h1>
		<h2 class="step"><spring:message
				code="requestInfo.cm.manageAddress.heading.addresses" /></h2>
	</div>
	
	<!-- Added for CI7 BRD14-02-12 -->
	<div id="accNameAgreeName">
	</div>
	<!-- END -->
	
	<div class="main-wrap">
		<div class="content">
			<div>
				<a onClick="javascript:doPrint();" id="topPrintLink"> <img
					src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon cursor-pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>




			<div id="divPrintBody"></div>


			<div>
				<a id="btmPrint" onClick="javascript:doPrint();"> <img
					src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon cursor-pointer"
					title="<spring:message code='requestInfo.link.printThisPage'/>" />
					<span class="cursor-pointer"><spring:message
							code='requestInfo.link.printThisPage' /></span>
				</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	window.document.getElementById("divPrintBody").innerHTML = window.opener.window.document.getElementById("printAddress").innerHTML;
	/* Added for CI7 BRD14-02-12 */
	window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
	/* END */
	function doPrint() {
		window.opener.callOmnitureAction('Address', 'Address Summary');
		window.document.getElementById("topPrintLink").style.display = 'none';
		window.document.getElementById("btmPrint").style.display = 'none';
		window.print();
		window.close();
	};
</script>
