<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
	@media print{
.portletBlock , .infoBox, .columnInner, .rounded, .shadow{background:none!important;background-color:white!important;}
}

</style>


<div id="content-wrapper-inner">
<div><script>document.writeln(window.opener.window.document.getElementById("header").innerHTML);</script></div>
	<div class="main-wrap">
		<div class="content">
		<table>
		<tr>
			<td width="25%">
			<a onClick="javascript:doPrint();" id="topPrintLink"> <img
					src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" height="23px" width="23px"
					style="cursor: pointer"
					title="Print this page" />
					<span style="cursor: pointer">Print this page</span>
				</a>
		</td>
		</tr>
		</table>




			<div id="emailPrintWraper"><script>document.writeln(window.opener.window.document.getElementById("hwDebriefView").innerHTML);</script></div>


			
			      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
          <tr><td>
        <table width="800" border="0" cellspacing="5" cellpadding="0" align="center" style="margin:0!important">
	<tr id="topPrint">
		
		<td  width="25%">
			<a id="btmPrint" onClick="javascript:doPrint();"> <img
					src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" height="23px" width="23px"
					style="cursor: pointer"
					title="print this page" />
					<span style="cursor: pointer">Print this page</span>
				</a>
		</td>
		<td align="right">
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code="button.cancel"/></button>
		</td>
	</tr>
</table>
        </tr>
            </table></td>
        </tr>

      </table> 
			
		</div>
	</div>
</div>
<script type="text/javascript">
jQuery('#acceptRejectButton').remove();
	
	function doPrint() {
		window.document.getElementById("topPrintLink").style.display = 'none';
		window.document.getElementById("btmPrint").style.display = 'none';
		window.print();
		window.close();
	};
</script>