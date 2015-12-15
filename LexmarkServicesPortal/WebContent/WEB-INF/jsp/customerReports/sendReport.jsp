<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageId" value="email"/>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<style type="text/css">
body{
	align: left;
	text-align:left;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
	<div id="divToAddress" width = "100%" height="250" border = "1" valign="top" >
		<table>
		<tr>
		<td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		<td>
				<table width="100%" height="100%" border="0" valign="top">
		  		<tr>
		    		<td height="20" width="100%" colspan="3" align="left" class="orangeSectionTitles"><spring:message code='customerReports.emailNotificatin.title'/></td>
		  		</tr>
		  		<tr>
		  			<td colspan="3" align="left"><spring:message code="customerReports.emailNotificatin.description"/></td>
		  		</tr>
		  		<tr>
		  			<td colspan="3" align="left">&nbsp;</td>
		  		</tr>
		  		<tr>
		  			<td colspan="3" align="left">
		  				<table width="100%">
		  					<tr>
		  						<td width="100%" align="left">
		  							<label class="labelBold"><spring:message code="customerReports.emailNotificatin.report"/></label>&nbsp; ${reportName}
		  						</td>
		  					</tr>
		  					<tr>
		  						<td width="100%" align="left">
		  							<label class="labelBold"><spring:message code="customerReports.emailNotificatin.date"/></label>&nbsp; ${reportDate}
		  						</td>
		  					</tr>
		  				</table>
		  			</td>
		  		</tr>
		  		<tr><td>&nbsp;</td></tr>
		  		<tr>
		  			<td colspan="3" align="left" class="labelBold">
		  				<spring:message code="email.emailAddress.description"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="3" align="left">
		  				<table>
		  					<tr>
					    		<td width="65%">
					        		<textarea id="txtToAddress" name="txtToAddress" rows="2" class="width-500px" wrap="virtual" onkeypress="getKeyCode(event);cleanStatus();" onkeyup="cleanStatus();" onpropertychange="initEmailFormat(this);" oninput="initEmailFormat(this);"  onblur="lost(this);"></textarea>
					    		</td>		  					
		  					</tr>
		  				</table>
		  			</td>
		  		</tr>
		  		<tr><td><br></td></tr>
		  		<tr>
		    		<td colspan="3" width="100%" height="60" align="right" valign="top">
						<a href="###" class="button" id="btnSendEmail" onClick="doSending();"> <span><spring:message code='button.send'/></span></a>&nbsp;
						<a href="###" class="button_cancel" onclick="closePopup();"><span><spring:message code="button.cancel"/></span></a>
					</td>
		  		</tr>
			</table>
		</td>
		</tr>
		</table>
	</div>

<script type='text/javascript'>
	var url;
 	function doSending(){
    	var toEmailAddress = window.document.getElementById("txtToAddress").value;
    	if(validateEmailAddress(toEmailAddress)){
    		var emailSubject = "<spring:message code='customerReports.sentReport.description.subject'/>: ${reportName}";
    		var emailContent = "<table><tr><td style='padding:10px 10px 10px 10px;'> <spring:message code='customerReports.sentReport.description.body1'/> <label style='font-weight: bold;'>${reportName}</label> <spring:message code='customerReports.sentReport.description.body2'/></td></tr>";
    			emailContent +="<tr><td style='padding:0 10px 10px 10px;'><a href='${reportURL}'>${reportURL}</a></td></tr></table>";
			var isHTML = new Boolean(true);
			callOmnitureAction('Customer Reports', 'Send report email-' + emailSubject);
			url = '<portlet:resourceURL id="sendingEmail"/>';
			param = "content="+encodeURIComponent(emailContent);
			param +="&subject="+encodeURIComponent(emailSubject);
			param +="&toAddress="+encodeURIComponent(toEmailAddress);
			param +="&isHTML="+isHTML;
			doAjaxPost(url,param,null,null,"email");
			closePopup();
    	}else{
    		showError("<spring:message code='email.label.invalidEmailAddress'/>","email");
        }
    	
	}
	function closeWindow(){
		document.getElementById('btnClose').click(); 
	}
	function cleanStatus(){
		clearMessage();
	}

	
	function wrapDeviceDetailContent(gridContent){
		var pageSize = 5;
		var parentgrid = window.opener.SRHistoryGrid;
		var start = (Number(parentgrid.currentPage)-1)*pageSize;
		var rowNumber = parentgrid.getRowsNum();
		var totalpages = Math.ceil(Number(parentgrid.getRowsNum())/pageSize);
		var printRecordNumber;
		if (rowNumber%pageSize > 0 && parentgrid.currentPage == totalpages) {
			printRecordNumber = rowNumber%pageSize;
		} else {
			printRecordNumber = pageSize;
		}
			gridContent =  window.opener.window.document.getElementById("srTitle").innerHTML;
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
	
	function closePopup(){
		dialog.dialog('destroy');					 
		dialog=null;
		showSelect();
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Send report light box";
     addPortlet(portletName);
     pageTitle="Send report";
</script>
