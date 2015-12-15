<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageId" value="srEmail"/>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
	<div id="divToAddress" border = "1" valign="top" width="100%">
		<table width="100%"  border="0" valign="top">
		<tr>
		    <td height="20" width="100%" colspan="3" align="left" class="orangeSectionTitles"><spring:message code='serviceRequest.label.yourServiceRequestHasBeenSubmitted'/></td>
		</tr>
		<tr><td><br></td></tr>
  		<tr>
		  	<td colspan="3" align="left" class="labelBold">
		  		<spring:message code="email.emailAddress.description"/>
		  	</td>
		</tr>
    		<td width="65%">
        		<textarea id="txtToAddress" name="txtToAddress"  rows="2" style="width:500px;" wrap="virtual" onkeypress="getKeyCode(event);cleanStatus();" onkeyup="cleanStatus();" onpropertychange="initEmailFormat(this);" oninput="initEmailFormat(this);"  onblur="lost(this);"></textarea>
    		</td>
    		<td width="5%"></td>
  		</tr>
  		<tr><td><br></td></tr>
  		<tr>
    		<td colspan="3" width="100%" height="60" align="right" valign="top">
    			 <button id="btnClose" class="button_cancel"  style="width:65px" onClick="closeWindow(this);"><spring:message code='button.cancel'/></button> &nbsp; <button class="button"  style="width:65px" id="btnSendEmail" onClick="doSending();"><spring:message code='button.send'/></button>
    		</td>
  		</tr>
		</table>
</div>

<div id="div_emailDetailBody"  style="display:none;">	
</div>
<script type="text/javascript">
function createEmailContent(){
	var cssStyle= "<style>.titleBackColor{background-color: #26709f;} .myh3 {height: 30px;width: 100%;font-size: 16px;line-height: 37px;color: #fff;font-weight: normal;} .myh2 {height: 35px;width: 100%;font-size: 18px;line-height: 37px;color: #000;font-weight: bold;}";
	cssStyle += "dt {font-weight: bold;margin-bottom: 5px;}dd label {font-weight: bold;display: inline-block;padding-right: 10px;}</style>";
	var emailContent = cssStyle+"<table border='1' rules='none' width='800'>";
	emailContent += "<tr><td align='left'><label class='myh2'><spring:message code='message.servicerequest.detail' /></label></td></tr>";
	//Thank you
	emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left'><label class='myh3'><spring:message code='serviceRequest.label.thankYou'/></label></td></tr>";
	emailContent += "<tr><td><label style='font-weight: bold;'><spring:message code='serviceRequest.label.yourServiceRequestHasBeenSubmitted'/></label></td></tr>";
	emailContent += "<tr><td><label style='font-weight: bold;'><spring:message code='serviceRequest.listHeader.RequestNumber'/>:&nbsp;"+srNum+"</label></td></tr>";
	emailContent += "<tr><td><spring:message code='serviceRequest.label.reviewYourServiceRequestDetail'/></td></tr></table></td></tr>";
	//Problem Information
	emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td colSpan='3' align='left'><label class='myh3'><spring:message code='message.servicerequest.problemInformation'/></label></td></tr>";
	emailContent += "<tr><td valign='top' width='33%'>"+window.parent.window.document.getElementById('problemInfo_firstColumn').innerHTML+"</td>";
	emailContent += "<td valign='top' width='33%'>"+window.parent.window.document.getElementById('problemInfo_secondColumn').innerHTML+"</td>";
	emailContent += "<td valign='top' width='33%'></td></tr></table></td></tr>";
	//deviceInformation
	emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td colSpan='3' align='left'><label class='myh3'><spring:message code='serviceRequest.label.deviceInformation'/></label></td></tr>";
	emailContent += "<tr><td width='33%'><img src='"+deviceImageURL+"' width='150px'/></td>";
	emailContent += "<td valign='top' width='33%'>"+window.parent.window.document.getElementById('device_secondColumn').innerHTML+"</td>";
	emailContent += "<td valign='top' width='33%'>"+window.parent.window.document.getElementById('device_thirdColumn').innerHTML+"</td></tr></table></td></tr>";
	//status 
	emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left'><label class='myh3'><spring:message code='serviceRequest.label.status'/></label></td></tr>";
	emailContent += "<tr><td style='font-weight: bold;padding-left:1em'><spring:message code='serviceRequest.label.checkBackLater'/></td></tr></table></td></tr>";
	//contact info
	emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left' colSpan='3'><label class='myh3'><spring:message code='serviceRequest.label.contactInfo'/></label></td></tr>";
	emailContent += "<tr><td width='33%'>"+window.parent.window.document.getElementById('contactInfoFirstColum').innerHTML+"</td>";
	emailContent += "<td width='33%'>"+window.parent.window.document.getElementById('contactInfoSecondColum').innerHTML+"</td>";
	emailContent += "<td>";
	if(window.parent.window.document.getElementById('contactInfoThirdColum')){
		emailContent += window.parent.window.document.getElementById('contactInfoThirdColum').innerHTML;
	}
	emailContent += "</td></tr></table></td></tr>";
	//notification
	emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left'><label class='myh3'><spring:message code='serviceRequest.label.notifications'/></label></td></tr>";
 	emailContent += "<tr><td style='font-weight: bold;padding-left:1em'><spring:message code='serviceRequest.label.noNotification'/></td></tr></table></td></tr>";
 	
	emailContent += "</table>"; 
	return emailContent;
};
function doSending(){	
	    callOmnitureAction('Service Request', 'Service Request Thank you Page Send Email');
	var toEmailAddress = window.document.getElementById("txtToAddress").value;
	if(validateEmailAddress(toEmailAddress)){
		var emailSubject = window.parent.window.getEmailSubject();
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
	callOmnitureAction('Service Request', 'Service Request Thank you Page Close Window');
	Liferay.Popup.close(elm);
}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Service Request Thank you email";
     addPortlet(portletName);
     pageTitle="Service request - Thank you email";
</script>
