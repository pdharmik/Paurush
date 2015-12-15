<%@ include file="/WEB-INF/jsp/include.jsp"%>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="div-style11" valign="top;">
	<table class="margin-top-10px" valign="top;">	
		<tr>
			<td colspan="2">
			<h5><spring:message code="serviceRequest.description.createNewContact"/></h5>
			</td>
		</tr>
		<tr>
			<td colspan="2" height="20px">
				<label id="requiredFieldsErrorMsg" class="color-red" style="display:none"><spring:message code="serviceRequest.errorMsg.pleaseFillInAllRequiredFields"/>&nbsp;</label>
				<label id="emailErrorMsg" class="color-red" style="display:none"><spring:message code="email.label.invalidEmailAddress"/>&nbsp;</label>
			</td>
		</tr>
		<tr align="left"> 
			<td>* <spring:message code="serviceRequest.label.lastName"/></td> 
			<td>* <spring:message code="serviceRequest.label.firstName"/></td>
		</tr>
		<tr align="left">
			<td><input id="lastName" name="lastName" size="30" type="text" value="${lastName}" size="20" maxlength="50" onFocus="removeErrMsg();"></td> 
			<td><input id="firstName" name="firstName"  size="30" type="text" value="${firstName}" size="20" maxlength="50" onFocus="removeErrMsg();"> 
		</tr>
		
		<tr align="left">
			<td>* <spring:message code="serviceRequest.label.workPhone"/></td>
			<td>* <spring:message code="serviceRequest.label.emailAddress"/></td>
		</tr>
		<tr align="left">
			<td><input id="workPhone" name="workPhone"  size="30" type="text" value="${workPhone}" size="20" maxlength="25" onFocus="removeErrMsg();" onkeypress="return onlyTelNumbers();"></td>
			<td><input id="emailAddress" type="text"  size="30" value="${emailAddress}" size="30" maxlength="100" onFocus="removeErrMsg();"></td>
		</tr>
		
		<tr>
			 <td width="60%" align="left">&nbsp;</td>
			 <td width="40%" align="left"></td>
		</tr>
	</table>
</div>
<br/>
<div align="right" class="margin-right-30px" >
	<button name="btnCancel" class="button_cancel" onClick="Liferay.Popup.close(this);">
		<spring:message code="button.cancel"/>
	</button> &nbsp;
	<button name="btnSubmit" class="button" onClick="createNew(this);">
		<spring:message code="button.save"/>
	</button>
</div>

<script type="text/javascript">  
    var validationFlag = true;
	function createNew(buttonElm){
		callOmnitureAction('Service Request', 'Service Request Create New Contact');
		validateForm();
		var gridName = "${gridName}";
		var ci = "${contactId}";
		var ln = document.getElementById("lastName").value;
		var fn = document.getElementById("firstName").value;
		var wp = document.getElementById("workPhone").value;
		var ea = document.getElementById("emailAddress").value;
				
		document.getElementById("primaryContactList").style.display="none";
		document.getElementById("primaryContactDiv").style.display="block";		
		if(validationFlag){
			if(gridName=="primary")
				if(ci!=null&&ci!=""){
					window.parent.window.addPrimaryContactElement(ci, ln, fn, wp, ea);
				}else{
					window.parent.window.addPrimaryContactElement('-1', ln, fn, wp, ea);
				}
			else if(gridName=="secondary")
				if(ci!=null&&ci!=""){
					window.parent.window.addSecondaryContactElement(ci, ln, fn, wp, ea);
				}else{
					window.parent.window.addSecondaryContactElement('-1', ln, fn, wp, ea);
				}
			Liferay.Popup.close(buttonElm);
		}
	}

	function validateForm(){
		var ln = document.getElementById("lastName").value;
		var fn = document.getElementById("firstName").value;
		var wp = document.getElementById("workPhone").value;
		var ea = document.getElementById("emailAddress").value;
		if((ln==null||ln=="")||(fn==null||fn=="")||(wp==null||wp=="")||(ea==null||ea=="")){
			document.getElementById("requiredFieldsErrorMsg").style.display="block";
			validationFlag = false;
		}		
		if(!validateEmailAddress(ea)){
			document.getElementById("emailErrorMsg").style.display="block";
			validationFlag = false;
		}	
	}

	function removeErrMsg(){
		document.getElementById("requiredFieldsErrorMsg").style.display="none";
		document.getElementById("emailErrorMsg").style.display="none";
		validationFlag = true;
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Create New Contact";
     addPortlet(portletName);
     pageTitle="Create new contact";
</script>