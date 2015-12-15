<%@ include file="/WEB-INF/jsp/include.jsp"%>

	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style> 
<![endif]-->

<style type="text/css">
body{
	align: left;
	text-align:left;
}
@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%" height="100%">
    <tr id="topPrint"><td align="left">
    &nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();">
    <img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" title="<spring:message code='button.clickToPrint'/>" />
    <span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span></a>
    </td></tr>
    
    <!-- Added for CI 7 BRD14-02-12 -->
    <tr>
        <td><div id="accNameAgreeName"> </div></td>
        </tr>
        <!-- END -->
	
	<tr>
		<td><div id="divPrintBody"></div></td>
	</tr>
	<tr>
		<td align="left">
			<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
			<img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" 
			title="<spring:message code='button.clickToPrint'/>" />
			<span class="cursor-pointer"><spring:message code='button.clickToPrint'/></span>
			</a>
 		</td>
	</tr>
</table>
<script type="text/javascript">
	window.document.getElementById("divPrintBody").innerHTML = window.opener.window.document.getElementById("emailPrintWraper").innerHTML;
	/* Added for CI7 BRD14-02-12 */
	window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
	/* END */
	 function doPrint(){
	  	    window.document.getElementById("topPrint").style.display='none';
	 		window.document.getElementById("btmPrint").style.display='none';
	 		window.print();
	 		window.close();
	 	};
 
</script>
<script type="text/javascript">
//---- Ominture script 
   
</script>