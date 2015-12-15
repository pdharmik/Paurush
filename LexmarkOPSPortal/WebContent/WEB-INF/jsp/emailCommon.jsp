<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css"><%@ include file="../css/lexmark.css" %></style>
<c:set var="pageId" value="email"/>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
	<div id="divToAddress" width = "100%" height="250" border = "1" valign="top" >
		<table width="100%" height="100%" border="0" valign="top">
  		<tr>
    		<td height="20" width="100%" colspan="3" align="left" class="blackCopyTitle2"><spring:message code='email.label.pleaseSpecifyRecipients'/></td>
  		</tr>
  		<tr>
    		<td width="30%" height="100"><spring:message code='email.label.toAddress'/></td>
    		<td width="65%">
        		<textarea id="txtToAddress" name="txtToAddress" rows="4" style="width:400px;" wrap="virtual" onkeypress="getKeyCode(event);cleanStatus();" onkeyup="cleanStatus();" onpropertychange="initEmailFormat(this);" oninput="initEmailFormat(this);"  onblur="lost(this);"></textarea>
    		</td>
    		<td width="5%"></td>
  		</tr>
  		<tr>
    		<td colspan="3" width="100%" height="60" align="right" valign="top">
    			 <button class="button" id="btnSendEmail" onClick="doSending();"> Send </button> &nbsp; <button id="btnClose" class="button_cancel" onClick="Liferay.Popup.close(this);"> Close </button>
    		</td>
  		</tr>
		</table>
	</div>

<script type='text/javascript'>
	var gridContent = "";
	if("deviceDetail"== window.parent.window.getJSPPageName()){
		gridContent = wrapDeviceDetailContent(gridContent);
	}
	var url;
 	function doSending(){
    	var toEmailAddress = window.document.getElementById("txtToAddress").value;
    	if(validateEmailAddress(toEmailAddress)){
    		var emailSubject = window.parent.window.getEmailSubject();
    		var emailContent = window.parent.window.document.getElementById("divDetailBody").innerHTML;
    		if("deviceDetail"== window.parent.window.getJSPPageName()){
    			window.document.getElementById("linkRS").style.display="none";
        		window.document.getElementById("linkCP").style.display="none";
        		window.document.getElementById("linkOS").style.display="none";
        		window.document.getElementById("linkSD").style.display="none";
        		emailContent = window.parent.window.document.getElementById("divDetailBody").innerHTML;
        		}
    		emailContent += "<br>";
    		emailContent +=gridContent;
			var isHTML = new Boolean(true);
			url = '<portlet:resourceURL id="sendingEmail"/>';
			param = "content="+encodeURIComponent(emailContent);
			param +="&subject="+encodeURIComponent(emailSubject);
			param +="&toAddress="+encodeURIComponent(toEmailAddress);
			param +="&isHTML="+isHTML;
			doAjaxPost(url,param,null,null,"email");
    	}else{
    		showError("<spring:message code='email.label.invalidEmailAddress'/>","email");
        }
	}
	
	function cleanStatus(){
		clearMessage();
	}
	function wrapDeviceDetailContent(gridContent){
		var pageSize = 5;
		var parentgrid = window.parent.SRHistoryGrid;
		var start = (Number(parentgrid.currentPage)-1)*pageSize;
		var rowNumber = parentgrid.getRowsNum();
		var totalpages = Math.ceil(Number(parentgrid.getRowsNum())/pageSize);
		var printRecordNumber;
		if (rowNumber%pageSize > 0 && parentgrid.currentPage == totalpages) {
			printRecordNumber = rowNumber%pageSize;
		} else {
			printRecordNumber = pageSize;
		}
			gridContent =  window.parent.window.document.getElementById("srTitle").innerHTML;
			gridContent += "<br><table border='1px'  cellspacing='0px' width='800'><tr>";

			for(m=0;m<parentgrid.getColumnsNum();m++){
			
				gridContent = gridContent+"<th align='left'>"+parentgrid.getHeaderCol(m)+"</th>";
				}
				gridContent +="</tr><tr>";
			
			for(n=start;n<(start+printRecordNumber);n++){
			
				gridContent += "<tr>";
				for(i = 0;i<parentgrid.getColumnsNum();i++){
				
					gridContent += "<td>";
					gridContent += parentgrid.cellByIndex(n,i).cell.innerHTML;
					gridContent += "</td>";
				}
				gridContent += "</tr>";
			}
		gridContent = gridContent+"</table><br><div align='center'><spring:message code='page.currentPage'/>:&nbsp;"+parentgrid.currentPage+
		"&nbsp;<spring:message code='page.of'/>&nbsp;<spring:message code='page.totalPage'/>:&nbsp;"+totalpages+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		return gridContent;
	}
	
</script>
