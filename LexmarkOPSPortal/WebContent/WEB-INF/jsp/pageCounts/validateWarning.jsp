<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<div id="pageCountWarningPage" style="max-height: 500px; overflow-y: auto">
<table width="100%">
	<tr>
		<td align="center" width="100%" align="left" valign="top">
		<table width="100%">
			<tr>
				<td align="left" class="orangeSectionTitles" colspan="4">
					<div id="div_title"></div>
					<input id="input_warning" type="hidden" value="<spring:message code="meterRead.label.meterReadWarning"/>" />
					<input id="input_error" type="hidden" value="<spring:message code="meterRead.label.meterReadError" />" />
				</td>
			</tr>
			<tr><td width="18%" align="center"><h4 style="text-decoration: underline;"><spring:message code="meterRead.listHeader.serialNumber" /></h4></td>
				<td width="15%" align="center"><h4 style="text-decoration: underline;"><spring:message code="meterRead.label.readDate" /></h4></td>
				<td width="18%" align="center"><h4 style="text-decoration: underline;"><spring:message code="meterRead.label.newPageCount" /></h4></td>
				<td width="24%" align="center"><h4 style="text-decoration: underline;"><spring:message code="meterRead.label.newColorPageCount" /></h4></td>
			    <td width="25%" align="left"><h4 style="text-decoration: underline;"><spring:message code="meterRead.label.response" /></h4></td>
			</tr>
			<tr>	
				<td align="center" valign="top"><div id="serialNumber"></div></td>
				<td align="center" valign="top"><div id="readDate"></div></td>
				<td align="center" valign="top"><div id="pageCount"></div></td>
				<td align="center" valign="top"><div id="colorPageCount"></div></td>
				<td align="left">  <!-- Changes Done for LEX:AIR00068875 -->
				<table width="285px"> 			
			<tr><td align="left"><div id="warningMsgBW"></div></td></tr>
			<tr><td align="left"><div id="warningMsgcolor"></div></td></tr>
			<tr><td align="left"><div id="msgNotUpdate"></div></td></tr>
			<tr height="20"></tr>
			<tr><td align="left"><div id="ignoreMsg"></div></td></tr>
			<tr>	
				<td align="right">
					<div id="defferButtons" style="display: none;">
						<button class="button" onClick="closeWindow();dialog.dialog('close');"><spring:message code="button.no" /></button>
						&nbsp;
						<button class="button" onClick="doSave();dialog.dialog('close');"><spring:message code="button.yes" /></button>
					</div>
					<div id="rejectedButton" style="display: none;">
						<button class="button" onClick="closeWindow();dialog.dialog('close');"><spring:message code="button.ok" /></button>
					</div>
				</td>
			</tr>
			</table>
		</td>			
		</table>
		</td>
		<!-- <td align="center" width="40%" align="left">
		<table width="100%">
			<tr>
				<td align="left">&nbsp;</td>
			</tr>
			<tr>
				<td align="left"><h4 style="text-decoration: underline;"><spring:message code="meterRead.label.response" /></h4></td>
			</tr>
			<tr><td align="left"><div id="warningMsgBW"></div></td></tr>
			<tr><td align="left"><div id="warningMsgcolor"></div></td></tr>
			<tr><td align="left"><div id="msgNotUpdate"></div></td></tr>
			<tr height="20"></tr>
			<tr><td align="left"><div id="ignoreMsg"></div></td></tr>
			<tr>	
				<td align="right">
					<div id="defferButtons" style="display: none;">
						<button class="button" onClick="closeWindow();Liferay.Popup.close(this);"><spring:message code="button.no" /></button>
						&nbsp;
						<button class="button" onClick="doSave();Liferay.Popup.close(this);"><spring:message code="button.yes" /></button>
					</div>
					<div id="rejectedButton" style="display: none;">
						<button class="button" onClick="closeWindow();Liferay.Popup.close(this);"><spring:message code="button.ok" /></button>
					</div>
				</td>
			</tr>
		</table>
		</td>  -->
	</tr>
</table>
</div>
<script type="text/javascript">
	var parentgrid = window.parent.window.meterReadGrid;
	var parentStatus = window.parent.window.status;
	var msg = window.parent.window.msg;
	var msgColor = window.parent.window.msgColor;
	var msgNotUpdate = window.parent.window.msgNotUpdate;
	var selectedId = parentgrid.getSelectedRowId();
	var inputNewCount = window.parent.window.document.getElementById("input_count"+selectedId).value;
	var inputNewColorCount="";
	if(window.parent.window.isColorCapableFlag){
		inputNewColorCount = window.parent.window.document.getElementById("input_colorCount"+selectedId).value;
		document.getElementById("colorPageCount").innerHTML=inputNewColorCount;
	}else{
		document.getElementById("colorPageCount").innerHTML="---";
	}
	document.getElementById("serialNumber").innerHTML=window.parent.window.serialNumber;
	document.getElementById("readDate").innerHTML=window.parent.window.document.getElementById("localizedReadDate"+selectedId).value;
	document.getElementById("pageCount").innerHTML=inputNewCount;
	document.getElementById("warningMsgBW").innerHTML=msg;
	document.getElementById("warningMsgcolor").innerHTML=msgColor;
	document.getElementById("msgNotUpdate").innerHTML=msgNotUpdate;
	if(parentStatus=='DEFERRED'){
		document.getElementById("div_title").innerHTML = document.getElementById("input_warning").value;
		document.getElementById("ignoreMsg").innerHTML= "<spring:message code='meterRead.warning.ignore' />";
		document.getElementById("defferButtons").style.display='';
	}else{
		document.getElementById("div_title").innerHTML= document.getElementById("input_error").value;
		document.getElementById("rejectedButton").style.display='';
	}

	function doSave(){
		var oldLTPC1="";
		var oldColor="";
		callOmnitureAction('Page Counts', 'Page Count Warning Page Save');
		updateURL = '<portlet:resourceURL id="updateAssetMeterRead"/>';
		updateURL +="&assetId="+window.parent.window.tempAssetId;
		updateURL +="&selectedRowId="+selectedId;
		updateURL +="&newPageCount="+inputNewCount;
		updateURL +="&newColorPageCount="+inputNewColorCount;
		updateURL +="&newReadDate="+formatDateToDefault(window.parent.window.document.getElementById("localizedReadDate"+selectedId).value);
		updateURL +="&oldPageCount="+oldLTPC;
		updateURL +="&oldColorPageCount="+oldColor;
		window.parent.window.enableOrDisableButton(selectedId);
		doAjax(updateURL, function(a){window.parent.window.successCallbackUpdateAsset(a.data[0],a.data[1],a.data[2]);},function(b){window.parent.window.failCallbackUpdateAsset(b.data[0],b.data[1],b.data[2]);});
		window.parent.window.status="";
		window.parent.window.msg="";
		window.parent.window.msgColor="";
		window.parent.window.msgNotUpdate="";
	}

	function closeWindow(){
		callOmnitureAction('Page Counts', 'Page Count Warning Page Close');
		window.parent.window.document.getElementById("input_count"+selectedId).value="";
		if(window.parent.window.isColorCapableFlag){
			window.parent.window.document.getElementById("input_colorCount"+selectedId).value="";
		}
		window.parent.window.status="";
		window.parent.window.msg="";
		window.parent.window.msgColor="";
		window.parent.window.msgNotUpdate="";
	}
    addPortlet('Page Count Warning Page');
</script>