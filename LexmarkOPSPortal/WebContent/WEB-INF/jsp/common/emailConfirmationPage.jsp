<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageId" value="srEmail"/>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page import="com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<script type="text/javascript" src="<html:rootPath/>js/grid/dhtmlxcommon.js"></script>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
	<div id="divToAddress" border = "1" valign="top" width="100%">
		<table width="100%"  border="0" valign="top">
		<%--<tr>
		    <td height="20" width="100%" colspan="3" align="left" class="orangeSectionTitles"><spring:message code='serviceRequest.label.yourServiceRequestHasBeenSubmitted'/>&nbsp;<span id="req"> </span></td>
		</tr> --%>
		<tr><td><br></td></tr>
  		<tr>
		  	<td colspan="3" align="left" class="labelBold">
		  		<spring:message code="email.emailAddress.description"/>
		  	</td>
		</tr>
		<tr>
    		<td width="65%">
        		<textarea id="txtToAddress" name="txtToAddress"  rows="2" style="width:500px;" wrap="virtual" onkeypress="getKeyCode(event);cleanStatus();" onkeyup="cleanStatus();" onpropertychange="initEmailFormat(this);" oninput="initEmailFormat(this);"  onblur="lost(this);"></textarea>
    		</td>
    		<td width="5%"></td>
  		</tr>
  		<tr><td><br></td></tr>
  		<tr>
    		<td colspan="3" width="100%" height="60" align="right" valign="top">
    			 <button id="btnClose" class="button_cancel"  style="width:65px" onClick="closeEmailPopup(this);"><spring:message code='button.cancel'/></button> &nbsp; <button class="button"  style="width:65px" id="btnSendEmail" onClick="doSending();"><spring:message code='button.send'/></button>
    		</td>
  		</tr>
		</table>
</div>

<div id="div_emailDetailBody"  style="display:none;">	
</div>
<script type="text/javascript">

function createEmailContent(){
	jQuery('img').remove();
	var emailContent = "<table border='1' rules='none' width='800'>";
	emailContent += "<tr><td valign='top' width='33%'>"+window.document.getElementById('emailPrintWraper').innerHTML+"</td></tr>";
	emailContent += "</table>";	
	
	return emailContent;
};
function doSending(){	
	 
	var reqno= window.document.getElementById("reqNumber").innerHTML;
	
	var toEmailAddress = window.document.getElementById("txtToAddress").value;
	
	if(validateEmailAddress(toEmailAddress)){
		
		var emailSubject = "Service request Created"+reqno;		
		var emailContent = createEmailContent();		
		var isHTML = new Boolean(true);		
		var url = '<portlet:resourceURL id="sendingEmail"/>';		
		var param = "content="+encodeURIComponent(emailContent);
		param +="&subject="+encodeURIComponent(emailSubject);
		param +="&toAddress="+encodeURIComponent(toEmailAddress);
		param +="&isHTML="+isHTML;		
		
		doAjaxPost(url,param,null,null,"srEmail");

	}else{
		showError("<spring:message code='email.label.invalidEmailAddress'/>","srEmail");
    }
}

function cleanStatus(){
	clearMessage();
}

function closeWindow(elm){	
	closePopup(elm);
}

function closeEmailPopup(id){
	jQuery(window).scrollTop(0);
	emailPopUpWindow.destroy();
	emailFunction();
	}
</script>
