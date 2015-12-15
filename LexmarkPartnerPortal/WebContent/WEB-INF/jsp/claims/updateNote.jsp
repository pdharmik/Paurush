<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.yui3-widget-positioned{
position: absolute!important;
left: 25% !important;
}
.modal a {
	
	display:inline-block !important;
	
}
.modal ul {
    border-top: 0px !important;
}
#noteDetail{
width:100%;
}
</style>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="80%">
	<tr>
		<td><label><b><spring:message code="claim.label.date"/></b></label></td>
		<td><label><b><spring:message code="claim.label.author"/></b></label></td>
	</tr>
	<tr>
		<td>${noteDate}</td>
		<td>${noteAuthor}</td>
	</tr>
	<tr>
		<td colspan="2">
			<label><b><spring:message code="claim.label.note"/></b></label><br>
			<textarea  rows="6" class="note-detail" id="noteDetail" ></textarea>
		</td>
	</tr>
</table>
<br>
<div class="float-right">
<button class="button" onclick="addRow(this);" id ="addNoteButton" type="button"><spring:message code="button.ok"/></button>
<button class="button_cancel" onclick="hideOverlayIE6();closeNotes();" id ="cancelNoteButton" type="button"><spring:message code="button.cancel"/></button>
	
</div>
<!-- hidden fields-->
<input type="hidden" id="noteDate"  value="${noteDate}" />
<input type="hidden" id="noteAuthor" value="${noteAuthor}" />
<script type="text/javascript">


	function closeNotes(){
		jQuery(window).scrollTop(0);
		showNotes.destroy();
		showNotesFunction();
		$('.overlayAddnotePopup').hide();
	}
	function addRow(ele){
		var noteDetailElement = document.getElementById("noteDetail");
		if(validateLengthNote(1,4095,noteDetailElement) && validateNoteDetails()){
			var note = new Array();
			var noteDetail = document.getElementById("noteDetail").value;
			note[0] = escapeStringForNotePopup(noteDetail);
			note[1] = "${noteDate}";
			note[2] = "${noteAuthor}";
			if(noteDetail.length > 49){
				note[3] = escapeStringForNotePopup(noteDetail.substr(0,49)+"...");
			}
			else{
				note[3] = escapeStringForNotePopup(noteDetail);
			}
			if("${handleGridFlag}" == "add"){
				window.parent.window.addRowInNotesGrid(note);
			}else if("${handleGridFlag}" == "update"){
				var rowId = "${rowId}";
				window.parent.window.updateRowInNotesGrid(note,rowId);
			}
			hideOverlayIE6();
			jQuery(window).scrollTop(0);
			showNotes.destroy();
			showNotesFunction();
			$('.overlayAddnotePopup').hide();
			
		}
			
	}	
	function validateNoteDetails(){
		var noteDetail = document.getElementById("noteDetail").value;
		if(noteDetail.trim().length < 1){
			jAlert('<spring:message code="claim.errorMsg.noteDetailsNotNull"/>', '');
			return false;
		}
		return true;
	}
	function  validateLengthNote(mimlen,maxlen, evt){
	    var str=evt.value.trim();
	    var myLen = 0;
	    for(i=0;(i<str.length)&&(myLen<=maxlen);i++){
	        if(str.charCodeAt(i)>0&&str.charCodeAt(i)<128)
	            myLen++;
	        else
	            myLen+=2;
	    }
	    if(myLen>maxlen){
	    	jAlert('<spring:message code="warning.maximumLengthExceed" arguments="'+maxlen+'"/>', "");
	        evt.value=str.substring(0,i-1);	 
	        return false;
	    }
	    return true;
	}
	
	function escapeStringForGrid(str){
		str = str.replace(/&lt;/g, "<");   
		str = str.replace(/&gt;/g, ">");
		str = str.replace(/&amp;/g, "&");
		str = str.replace(/&quot;/g, "\"");
		str = str.replace(/&apos;/g, "\'");
		return str;
	}
	jQuery(document).ready(function() {
		$('.overlayAddnotePopup').show();
		var patt=/^pickFromGrid/g;
		var noteStr = "${noteDetailId}";
		var result=patt.exec(noteStr);
		
		if("${noteDetailId}" != "" && result == null){
			document.getElementById("noteDetail").value = escapeStringForGrid(document.getElementById("${noteDetailId}").value);
		}else if(result !=null){
			var str = noteStr.substr(("pickFromGrid".length+1),noteStr.length);
			eval("noteGrid = window.parent.window."+str.toString());
			document.getElementById("noteDetail").value = escapeStringForGrid(noteGrid.cells("${rowId}",6).getValue());
		}
	});

	   
	    

</script>