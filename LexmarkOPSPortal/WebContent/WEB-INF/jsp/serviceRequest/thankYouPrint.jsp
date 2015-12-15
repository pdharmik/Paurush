<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
<table width="100%" height="100%">
    <tr id="topPrint"><td align="left">
    &nbsp;&nbsp;<a href="javascript:void(0)" onClick="doPrint();"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" /><span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span></a>
    </td></tr>
	<tr>
		<td><div id="divPrintBody"></div></td>
	</tr>
	<tr>
		<td align="left">
			<a id="btmPrint" href="javascript:void(0)" onClick="doPrint();">
			<img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" style="cursor:pointer" title="<spring:message code='button.clickToPrint'/>" />
			<span style="cursor:pointer"><spring:message code='button.clickToPrint'/></span>
			</a>
 		</td>
	</tr>
</table>
<script type="text/javascript">
	window.document.getElementById("divPrintBody").innerHTML = window.opener.window.document.getElementById("thankYouWrapper").innerHTML;

	 function doPrint(){
	  	    window.opener.callOmnitureAction('Service Request', 'Service Request Thank you Page Print');
	 		window.document.getElementById("topPrint").style.display='none';
	 		window.document.getElementById("btmPrint").style.display='none';
	 		window.print();
	 		window.close();
	 	};
 	function pagesetup_null(){                
        var     hkey_root,hkey_path,hkey_key;
        hkey_root="HKEY_CURRENT_USER";
        hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
        try{
                    var RegWsh = new ActiveXObject("WScript.Shell");
                    hkey_key="header";
                    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
                    hkey_key="footer";
                    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
            }catch(e){}
    }
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Thank you Print";
     addPortlet(portletName);
     pageTitle="Service request thank you print";
</script>