<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="updateContent">
	<h3><spring:message code="claim.label.updateNote"/></h3>
	<table width="80%">
		<tr>
			<td><label><b><spring:message code="claim.label.date"/></b></label></td>
		<td><label><b><spring:message code="claim.label.author"/></b></label></td>
	</tr>
	<tr>
		<td><span id="date"></span></td>
		<td><span id="author"></span></td>
	</tr>
	<tr>
		<td colspan="2">
			<label><b><spring:message code="claim.label.note"/></b></label><br>
			<textarea  rows="6" class="note-detail" id="noteDetail" ></textarea>
			</td>
		</tr>
	</table>
	<br>	 
	<input type="hidden" id="noteDate"/>
	<input type="hidden" id="noteAuthor"/>
	<input type="hidden" id="rowId"/>
	<div class="float-right">
		<a href="###" class="button" onclick="addRow(this);"><spring:message code="button.ok"/></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="###" class="button" onclick="dialog.dialog('close');"><spring:message code="button.cancel"/></a>
	</div>
	
</div>
