<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="java.util.Date"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%-- Below URL opens up the Update Note in popup --%>
<portlet:renderURL var="showUpdateNotePopUP"
	windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showUpdateNotePopUP"></portlet:param>
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.header"/></h4>
              <div>
					<table class="displayGrid2 rounded shadow wFull" >
						<thead>
							<tr>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.date"/></th>
								<th><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.header"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.author"/></th>
								<th class="w100"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.action"/></th>
							</tr>
						</thead>
					</table>
					<div class="scrollTable wFull">
					<table class="displayGrid2 rounded shadow wFull" id="notesTable">
						<tbody>
						 <c:choose>
						<c:when test="${fn:length(hardwareDebriefForm.userEnteredActivity.activityNoteList)>0}">
						<c:forEach var="activityNote" items="${hardwareDebriefForm.userEnteredActivity.activityNoteList}" varStatus="status">
							
							<c:choose>
									<c:when test="${status.count%2==0}">
													<tr id="notesRow[${status.index}]" class="altRow">
												</c:when>
												<c:otherwise>
													<tr id="notesRow[${status.index}]" >
												</c:otherwise>
							</c:choose>
								<td class="w100"><util:dateFormat value="${activityNote.noteDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat></td>
								<td>${activityNote.noteDetails }</td>
								<td class="w100">${fn:trim(activityNote.noteAuthor.firstName)}&nbsp;${fn:trim(activityNote.noteAuthor.lastName)}</td>
								<%-- Checking to show EDIT button or not --%>
								
								<td class="w100" >
								
								<c:if test="${sessionScope.ldapUserData_PHASE2['CONTACTID'] eq activityNote.noteAuthor.contactId }">
									<button class="button" title="<spring:message code="button.edit"/>" type="button" onClick="editAndAddNote(${status.index})"><spring:message code="button.edit"/></button>
								</c:if>	
								<div style="display: none;">
									<input name="userEnteredActivity.activityNoteList[${status.index}].noteId" value="${activityNote.noteId }"/>
									<input name="userEnteredActivity.activityNoteList[${status.index}].noteDate" value="<util:dateFormat value="${activityNote.noteDate}" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>"/>
									<input name="userEnteredActivity.activityNoteList[${status.index}].noteDetails" value="${activityNote.noteDetails }"/>
									<input name="userEnteredActivity.activityNoteList[${status.index}].repairCompleteFlag" value="${activityNote.repairCompleteFlag }"/>
									<input name="userEnteredActivity.activityNoteList[${status.index}].activityUpdateFlag" value="${activityNote.activityUpdateFlag }"/>
									
									<input name="userEnteredActivity.activityNoteList[${status.index}].noteAuthor.contactId" value="${activityNote.noteAuthor.contactId }"/>
									<input name="userEnteredActivity.activityNoteList[${status.index}].noteAuthor.firstName" value="${activityNote.noteAuthor.firstName }"/>
									<input name="userEnteredActivity.activityNoteList[${status.index}].noteAuthor.lastName" value="${activityNote.noteAuthor.lastName }"/>
									
								</div>						
								</td>
							</tr>
						</c:forEach>
						</c:when>
						<c:otherwise>
						<tr><td colspan="4"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
						</c:otherwise>
						</c:choose>
						</tbody>
					</table>
					</div>
					<br/>
					<button class="button button21 position-static-important" type="button" id="addNotes" title="Update Note" onclick="editAndAddNote(null);"><spring:message code="button.addNote"/></button>
				</div>
            </div>
          </div>
        </div>
        
       
        	<div id="content_notePopUP" title="<spring:message code="claim.label.updateNote"/>" style="display: none;" class="margin-left-6px">
        
	<table width="80%">
	<tr>
		<td><label><b><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.date"/></b></label></td>
		<td><label><b><spring:message code="requestInfo.hardwareDebreief.common.closeOut.notes.author"/></b></label></td>
	</tr>
	<tr>
		<td><span id="notesDatePopup"><util:dateFormat value="<%=new Date()%>" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat></span></td>
		<td><span>${sessionScope.ldapUserData_PHASE2['FIRSTNAME']}&nbsp;${sessionScope.ldapUserData_PHASE2['LASTNAME']}</span></td>
	</tr>
	<tr>
		<td colspan="2">
			<label><b><spring:message code="claim.label.note"/></b></label><br>
			<textarea  class="wFull" id="notesTextAreaPopup"></textarea></textarea>
		</td>
	</tr>
		</table>
			 <div class="text-align-right">
			 <button class="button"  type="button" onclick="notesObj.checkAndSetvalues()"><spring:message code="button.ok"/></button>
	         <button class="button_cancel" onClick="closeDialog();"><spring:message code="button.cancel"/></button>
	        </div>
		</div>
 
	
	
	    
         <div style="display:none;" id="templateNotesPopupDiv">
         <%-- THIS DIV IS USED TO CREATE A NEW TABLE ROW FOR ADD NEW CONTACT --%>
         	<table id="defaultNotePopupTableTemplate">
         	<tbody>
         		<tr class="altRow" id="notesRow[-1]" >
         			<td class="w100"><util:dateFormat value="<%=new Date()%>" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat></td>
         			<td id="notesRowDetails[-1]"></td>
					<td class="w100">${sessionScope.ldapUserData_PHASE2['FIRSTNAME']}&nbsp;${sessionScope.ldapUserData_PHASE2['LASTNAME']}</td>
					<td class="w100" >
					<button class="button" title="<spring:message code="button.edit"/>" type="button" onClick="editAndAddNote(-1)"><spring:message code="button.edit"/></button>
					<div style="display: none;">
									
									<input name="userEnteredActivity.activityNoteList[-1].noteDate" value="<util:dateFormat value="<%=new Date()%>" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>"/>
									<input name="userEnteredActivity.activityNoteList[-1].noteDetails" />
									<input name="userEnteredActivity.activityNoteList[-1].noteAuthor.contactId" value="${sessionScope.ldapUserData_PHASE2['CONTACTID']}"/>
									<input name="userEnteredActivity.activityNoteList[-1].noteAuthor.firstName" value="${sessionScope.ldapUserData_PHASE2['FIRSTNAME']}"/>
									<input name="userEnteredActivity.activityNoteList[-1].noteAuthor.lastName" value="${sessionScope.ldapUserData_PHASE2['LASTNAME']}"/>
					</div>						
					</td>
         		</tr>
         		
         	</tbody>
         	</table>
         </div>
         		
	
	
        
<script type="text/javascript">


var notesListLength=-1;
	if(" ${fn:length(hardwareDebriefForm.userEnteredActivity.activityNoteList)}"!=0){
		notesListLength=parseInt(" ${fn:length(hardwareDebriefForm.userEnteredActivity.activityNoteList)}");
		notesListLength--;
 }
    
var notesObj={
				rowId:"",
				inputPopupIds:["notesDatePopup","notesTextAreaPopup","notesAuthorPopup"],
				checkAndSetvalues:function(){
								
								if(this.rowId==null){
									addAnotherNotesRow();
								}else{
									updateExistingRow(this.rowId);
									}
								closeDialog();
						}	
				
			};

	function editAndAddNote(rowId){
		
		<%-- rowId == null means add note clicked so data will be appended to the table --%>
		notesObj.rowId=rowId;
		if(rowId!=null){
			
				jQuery('#notesRow\\['+rowId+'\\] td').each(function(id){
						if(id==0){
							jQuery('#'+notesObj.inputPopupIds[id]).html(jQuery(this).html());
						}
						if(id==1){
							jQuery('#'+notesObj.inputPopupIds[id]).val(jQuery(this).html());
							}
						
				});
		}else{
			
				jQuery('#notesDatePopup').html('<util:dateFormat value="<%=new Date()%>" showTime="true" timezoneOffset="${tzOffset}"></util:dateFormat>');
				jQuery('#notesTextAreaPopup').val('');
			}
		dialog=jQuery('#content_notePopUP').dialog({
			autoOpen: false,
			title: '<spring:message code="claim.label.updateNote"/>',
			modal: true,
			draggable: false,
			resizable: false,
			height: 350,
			width: 610,
			position: 'top',
			open:function(){
				jQuery('#content_notePopUP').show();
			},
			close: function(event,ui){
					
					dialog.dialog('destroy');
					dialog=null;
					
				}
		  });
		  
		  dialog.dialog('open');
		
		  
	}
	function addAnotherNotesRow(){
		
         	//alert('default template='+jQuery('#defaultTableTemplate tbody').html());
		if(notesListLength==-1){
     		<%-- This is for removing the first row which state table is empty --%>
     		jQuery('#notesTable tbody tr:first').remove();
     	
     		
     	}
         	var trHtml=jQuery('#defaultNotePopupTableTemplate tbody').html();
         	notesListLength++;
         	
             	
         	trHtml=replaceAll(trHtml,'-1',notesListLength);
         	if(notesListLength%2==0){
         		trHtml=replaceAll(trHtml,'altRow','');	
         	}
         	jQuery(trHtml).appendTo(jQuery('#notesTable tbody'));
         	jQuery("[name='userEnteredActivity.activityNoteList["+notesListLength+"].noteDetails']").val(jQuery('#notesTextAreaPopup').val());
         	var notesTableRowId="notesRowDetails["+notesListLength+"]";
         	notesTableRowId=notesTableRowId.replace('[','\\[');
         	notesTableRowId=notesTableRowId.replace(']','\\]');
         	jQuery('#'+notesTableRowId).html(jQuery('#notesTextAreaPopup').val());
         	//jQuery('#defaultTableTemplate tr').clone().appendTo(jQuery('#contactTable'));
         		//jQuery('#contactTable')
         
         	
 
		
		}
	function updateExistingRow(rowId){
		jQuery('#notesRow\\['+rowId+'\\] td').each(function(id){
			
			if(id==1){
				jQuery(this).html(jQuery('#notesTextAreaPopup').val());
				var inputId="userEnteredActivity.activityNoteList["+rowId+"].noteDetails";
				jQuery("[name='"+inputId+"']").val(jQuery('#notesTextAreaPopup').val());
				
			}
			
			});
		}
</script>