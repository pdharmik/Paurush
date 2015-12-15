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
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="divToAddress" border = "1" valign="top" width="100%">
		<table width="100%"  border="0" valign="top">
		<tr>
		    <td height="20" width="100%" colspan="3" align="left" class="orangeSectionTitles"><spring:message code='email.label.emailServiceRequestDetail'/> </td>
		</tr>
		<tr><td><br></td></tr>
		<tr><td id="beforeDiv"> </td></tr>
		<tr>
			<td align="left">
				<label class="labelBold"><spring:message code="serviceRequest.listHeader.RequestNumber"/>:</label>&nbsp; <%=request.getParameter("requestNumber") %>
			</td>
		</tr>
		<tr><td><br></td></tr>
  		<tr>
		  	<td colspan="3" align="left" class="labelBold">
		  		<spring:message code="email.emailAddress.description"/>
		  	</td>
		</tr>
  		<tr>
    		<td width="65%">
        		<textarea id="txtToAddress" name="txtToAddress"  rows="2" class="width-500px" wrap="virtual" onkeypress="getKeyCode(event);cleanStatus();" onkeyup="cleanStatus();" onpropertychange="initEmailFormat(this);" oninput="initEmailFormat(this);"  onblur="lost(this);"></textarea>
    		</td>
  		</tr>
  		<tr><td><br></td></tr>
  		<tr>
    		<td colspan="3" width="100%" height="60" align="right" valign="top">
    			 <button id="btnClose" class="button_cancel width-65px" onClick="closeWindow(this);dialog.dialog('close');"><spring:message code='button.cancel'/></button> &nbsp; <button class="button width-65px cursor-pointer" id="btnSendEmail" onClick="doSending();"><spring:message code='button.send'/></button>
    		</td>
  		</tr>
		</table>
</div>


	
<script type="text/javascript">
		function getImage(str){
			var index = str.lastIndexOf("/");
			return str.substring(index+1 , str.length);
		};
	    function convertGridToTable(grid){
	    	var pageSize = 5;
	    	var rowNumber = grid.getRowsNum();
	    	var totalPages = Math.ceil(Number(rowNumber)/pageSize);
	    	var printRecordNumber;
	    	var start = 0;
	  	
	    	if(rowNumber!=0 && rowNumber%pageSize > 0 && grid.currentPage==totalPages){
		    	printRecordNumber = rowNumber%pageSize;
		    	}else{
		    	printRecordNumber = pageSize;
		    }	
	    	var tab_Content = "<table border='1px' cellpadding='0' cellspacing='0' rules='all' width='100%' style='text-align:left;'><tr class='tr-style-head'>";
	    	for(m=0;m<grid.getColumnsNum();m++){
	    		if(!grid.isColumnHidden(m))
	    			tab_Content = tab_Content + "<td>"+grid.getHeaderCol(m) + "</td>" ;
	    		}
	    	tab_Content = tab_Content + "</tr>";
	    	if(rowNumber!=0){
		    	var start = (Number(grid.currentPage)-1)*pageSize;
		    	if(grid!=window.parent.window.SRNotificationsGrid){
		    		for(i=start;i<(start+printRecordNumber);i++){
		    			str = "<tr  class='tr-style-"+(i%2)+"'>";
		    			for(j = 0;j<grid.getColumnsNum();j++){
		    				if(!grid.isColumnHidden(j)){
		    					str = str + "<td>"+grid.cellByIndex(i,j).cell.innerHTML+"</td>";
		    				}
		    			}
		    			str = str + "</tr>";
		    			tab_Content = tab_Content + str;
		    		}
			    }else{
			    	for(i=start;i<(start+printRecordNumber);i++){
		    			str = "<tr  class='tr-style-"+(i%2)+"'>";
		    			for(j = 0;j<(grid.getColumnsNum()-1);j++){
		    				if(j!= 2){
		    					str = str + "<td noWrap>"+grid.cellByIndex(i,j).cell.innerHTML+"</td>";
		    				}
		    				if(j== 2){
		    					str = str + "<td>"+grid.cellByIndex(i,j+1).cell.innerHTML+"</td>";
		    				}
		    			}
		    			str = str + "</tr>";
		    			tab_Content = tab_Content + str;
		    		}
				   }
	    		
	    	}
	    	tab_Content = tab_Content + "</table>";
	    	if(rowNumber ==0){
	    		tab_Content += "<spring:message code='deviceDetail.description.norecordsfound'/>";
		    }
	    	tab_Content += "<br>";
	    	return tab_Content;
	    };
	function createEmailContent(){
		
		var cssStyle= "<style>.titleBackColor{background-color: #26709f;} .myh3 {height: 30px;width: 100%;font-size: 16px;line-height: 37px;color: #fff;font-weight: normal;} .myh2 {height: 35px;width: 100%;font-size: 18px;line-height: 37px;color: #000;font-weight: bold;}.tr-style-1{background-color:#f1f1f1;} .tr-style-0{} .tr-style-head{background-color:#e3e3e3}";
		cssStyle += "dt {font-weight: bold;margin-bottom: 5px;font-size: 13px;}dd label {font-weight: bold;display: inline-block;padding-right: 10px;}</style>";
		var emailContent = cssStyle+"<table border='1' rules='none' width='800'>";
		emailContent += "<tr><td align='left'><label class='myh2'><spring:message code='message.servicerequest.detail' />-&nbsp;"+srNum+"</label></td></tr>";
		//Problem Information
		emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td colSpan='3' align='left'><label class='myh3'><spring:message code='message.servicerequest.problemInformation'/></label></td></tr>";
		emailContent += "<tr><td valign='top' width='33%'>"+window.parent.window.document.getElementById('problemDescription').innerHTML+"</td>";
		emailContent += "<td valign='top' width='33%'>"+window.parent.window.document.getElementById('typeOfServiceNeeded').innerHTML+"</td><td></td></tr>";
		emailContent += "<tr><td  colSpan='3'><spring:message code='serviceRequest.label.associatedServiceTickets'/></td></tr>"
		emailContent += "<tr><td  colSpan='3'>"+convertGridToTable(window.parent.window.SRAssociatedGrid)+"</td></tr></table></td></tr>";
		//deviceInformation
		emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td colSpan='3' align='left'><label class='myh3'><spring:message code='serviceRequest.label.deviceInformation'/></label></td></tr>";
		emailContent += "<tr><td width='33%'><img src='"+deviceImageURL+"'/></td>";
		emailContent += "<td valign='top' width='33%'>"+window.parent.window.document.getElementById('deviceSecondColumn').innerHTML+"</td>";
		emailContent += "<td valign='top' width='33%'>"+window.parent.window.document.getElementById('devicethirdColumn').innerHTML+"</td></tr>";
		//message key changed for CI Defect #8217
		emailContent += "<tr><td colSpan='3'><spring:message code='Details.manageAsset.heading.requestHistoryOnThisAsset'/></td></tr>";
		emailContent += "<tr><td colSpan='3'>"+convertGridToTable(window.parent.window.requestListGrid)+"</td></tr></table></td></tr>";
		//status 
		emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left'><label class='myh3'><spring:message code='serviceRequest.label.status'/></label></td></tr>";
		emailContent += "<tr><td style='font-weight: bold;'>"+window.parent.window.document.getElementById('serviceRequestStatus').innerHTML+"</td></tr>";
		emailContent += "<tr><td>"+convertGridToTable(window.parent.window.SRStatusGrid)+"</td></tr></table></td></tr>";
		//Technician 
		 if(document.getElementById("tab_technicianGrid")){
         	 var backgroundImageURL = window.parent.window.document.getElementById('div_technicianProgressBar_pbImage').style.backgroundImage.slice(4, -1);
        	 var imageURL = hostURL+getImage(backgroundImageURL);
        	 
    		 emailContent += "<tr><td><table width='100%'><tr><td style='font-weight: bold;' colSpan='2'><spring:message code='serviceRequest.label.technician'/></td></tr>";
    		 emailContent += "<tr><td width='30%'>"+window.parent.window.document.getElementById('manImage').innerHTML+"</td><td width='70%' ><img src='"+imageURL+"'/></td></tr>";
    		 emailContent += "<tr><td></td><td><table width='480'><tr heght='30'><td width='33%' align='center'><spring:message code='serviceRequest.label.inProcess' /></td><td width='33%' align='center'><spring:message code='serviceRequest.label.assigned'/></td><td width='33%' align='center'><spring:message code='serviceRequest.label.complete' /></td></tr></table></td></tr>";
			 emailContent += "<tr><td colSpan='2'>"+convertGridToTable(window.parent.window.SRTechnicianGrid)+"</td></tr></table></td></tr>";
		 }
		//inprocess
		if(document.getElementById("tab_inprocessGrid")){
			var backgroundImageURL = window.parent.window.document.getElementById('div_inProgressBar_pbImage').style.backgroundImage.slice(4, -1);
		  	var imageURL = hostURL+getImage(backgroundImageURL);
			emailContent += "<tr><td><table width='100%'><tr><td style='font-weight: bold;' colSpan='2'><spring:message code='serviceRequest.label.inProcessOrders'/></td></tr>";
			emailContent += "<tr><td width='30%'>"+window.parent.window.document.getElementById('inProcessImage').innerHTML+"</td><td width='70%' ><img src='"+imageURL+"' /></td></tr>";
			emailContent += "<tr><td></td><td><table width='480'><tr heght='30'><td width='33%' align='center'><spring:message code='serviceRequest.label.inProcess' /></td><td width='33%' align='center'><spring:message code='serviceRequest.label.assigned'/></td><td width='33%' align='center'><spring:message code='serviceRequest.label.complete' /></td></tr></table></td></tr>";
			emailContent += "<tr><td colSpan='2'>"+convertGridToTable(window.parent.window.SRInProgressGrid)+"</td></tr></table></td></tr>";
		}
		//Shipment
		 for(index=1;index<shipmentLength;index++){
 			eval("parent_SRShipmentGrid = window.parent.window.SRShipmentGrid"+index.toString());
         	var backgroundImageURL = window.parent.window.document.getElementById('div_shipmentProgressBar'+index+'_pbImage').style.backgroundImage.slice(4, -1);
         	var imageURL = hostURL+getImage(backgroundImageURL);
       	 	
 			emailContent += "<tr><td><table width='100%'><tr><td style='font-weight: bold;' colSpan='2'><spring:message code='serviceRequest.label.shipment'/>:&nbsp;"+trackingNumbers[index-1]+"</td></tr>";
 			emailContent += "<tr><td width='30%'>"+window.parent.window.document.getElementById('truckLeftImage'+(index-1)).innerHTML+"</td><td width='70%' ><img src='"+imageURL+"'/></td></tr>";
   		 	emailContent += "<tr><td></td><td><table width='480'><tr heght='30'><td width='33%' align='center'><spring:message code='serviceRequest.label.inProcess' /></td><td width='33%' align='center'><spring:message code='serviceRequest.label.assigned'/></td><td width='33%' align='center'><spring:message code='serviceRequest.label.complete' /></td></tr></table></td></tr>";
			emailContent += "<tr><td colSpan='2'>"+convertGridToTable(parent_SRShipmentGrid)+"</td></tr></table></td></tr>";
 		}
		//In progress return without tracking number
		if(document.getElementById("tab_inProgressReturnNoTrackNumGrid")){
			var backgroundImageURL = window.parent.window.document.getElementById('div_inProgressReturnNoTrackNumProgressBar_pbImage').style.backgroundImage.slice(4, -1);
		  	var imageURL = hostURL+getImage(backgroundImageURL);

			emailContent += "<tr><td><table width='100%'><tr><td style='font-weight: bold;' colSpan='2'><spring:message code='serviceRequest.label.inProcessReturns'/></td></tr>";
			emailContent += "<tr><td width='30%'>"+window.parent.window.document.getElementById('returnInProcessImage').innerHTML+"</td><td width='70%' ><img src='"+imageURL+"' /></td></tr>";
			emailContent += "<tr><td></td><td><table width='480'><tr heght='30'><td width='33%' align='center'><spring:message code='serviceRequest.label.inProcess' /></td><td width='33%' align='center'></td><td width='33%' align='center'><spring:message code='serviceRequest.label.complete' /></td></tr></table></td></tr>";
			emailContent += "<tr><td colSpan='2'>"+convertGridToTable(window.parent.window.inProgressReturnNoTrackNumGrid)+"</td></tr></table></td></tr>";
		}
 		//return shipment
 		for(n=1;n<returnShipmentLength;n++){
 			eval("parent_SRShipmentGrid = window.parent.window.SRReturnShipmentGrid"+n.toString());
         	var backgroundImageURL = window.parent.window.document.getElementById('div_returnProgressBar'+n+'_pbImage').style.backgroundImage.slice(4, -1);
       	 	var imageURL = hostURL+getImage(backgroundImageURL);
       	 	
         	emailContent += "<tr><td><table width='100%'><tr><td style='font-weight: bold;' colSpan='2'><spring:message code='serviceRequest.label.return'/>:&nbsp;"+returnTrackingNumbers[n-1]+"</td></tr>";
 			emailContent += "<tr><td width='30%'>"+window.parent.window.document.getElementById('truckRightImage'+(n-1)).innerHTML+"</td><td width='70%' ><img src='"+imageURL+"'/></td></tr>";
   		 	emailContent += "<tr><td></td><td><table width='480'><tr heght='30'><td width='33%' align='center'><spring:message code='serviceRequest.label.inProcess' /></td><td width='33%' align='center'></td><td width='33%' align='center'><spring:message code='serviceRequest.label.complete' /></td></tr></table></td></tr>";
			emailContent += "<tr><td colSpan='2'>"+convertGridToTable(parent_SRShipmentGrid)+"</td></tr></table></td></tr>"; 
			}
		//Delivered return without tracking number
		if(document.getElementById("tab_deliveredReturnNoTrackNumGrid")){
			var backgroundImageURL = window.parent.window.document.getElementById('div_deliveredReturnNoTrackNumProgressBar_pbImage').style.backgroundImage.slice(4, -1);
		  	var imageURL = hostURL+getImage(backgroundImageURL);

			emailContent += "<tr><td><table width='100%'><tr><td style='font-weight: bold;' colSpan='2'><spring:message code='serviceRequest.label.returnsTrackNumNotAvailable'/></td></tr>";
			emailContent += "<tr><td width='30%'>"+window.parent.window.document.getElementById('noTrackingNumtruckLeftImage').innerHTML+"</td><td width='70%' ><img src='"+imageURL+"' /></td></tr>";
			emailContent += "<tr><td></td><td><table width='480'><tr heght='30'><td width='33%' align='center'><spring:message code='serviceRequest.label.inProcess' /></td><td width='33%' align='center'></td><td width='33%' align='center'><spring:message code='serviceRequest.label.complete' /></td></tr></table></td></tr>";
			emailContent += "<tr><td colSpan='2'>"+convertGridToTable(window.parent.window.deliveredReturnNoTrackNumGrid)+"</td></tr></table></td></tr>";
		}
 		//contact
 		emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left' colSpan='3'><label class='myh3'><spring:message code='serviceRequest.label.contactInfo'/></label></td></tr>";
 		emailContent += "<tr><td width='33%'>"+window.parent.window.document.getElementById('contactInfoFirstColum').innerHTML+"</td>";
		emailContent += "<td width='33%'>"+window.parent.window.document.getElementById('contactInfoSecondColum').innerHTML+"</td>";
		emailContent += "<td>";
		if(window.parent.window.document.getElementById('contactInfoThirdColum')){
			emailContent += window.parent.window.document.getElementById('contactInfoThirdColum').innerHTML;
    	}
		emailContent += "</td></tr></table></td></tr>";
		//notification
 		if(document.getElementById("notificationsGrid")){
 			emailContent += "<tr><td><table width='100%'><tr style='background-color: #26709f;'><td align='left'><label class='myh3'><spring:message code='serviceRequest.label.notifications'/></label></td></tr>";
     		emailContent += "<tr><td>"+convertGridToTable(window.parent.window.SRNotificationsGrid)+"</td></tr></table></td></tr>";
 		}
    	emailContent += "</table>"; 
    	return emailContent;
	};
	function doSending(){
		
 	    callOmnitureAction('Service Request', 'Service Request DrillDown Send Email');
		var toEmailAddress = window.document.getElementById("txtToAddress").value;
		
    	if(validateEmailAddress(toEmailAddress)){
    		var emailSubject = window.parent.window.getEmailSubject();
    		var emailContent = createEmailContent();
			var isHTML = new Boolean(true);
			url = '<portlet:resourceURL id="sendingEmail"/>';
			param = "content="+encodeURIComponent(emailContent);
			param +="&subject="+encodeURIComponent(emailSubject);
			param +="&toAddress="+encodeURIComponent(toEmailAddress);
			param +="&isHTML="+isHTML;
			
			doAjaxPost(url,param,null,null,"srEmail");
			
			$('#message_banner_srEmail').appendTo('#beforeDiv');
			
    	}else{
    		$('#error_banner_srEmail').appendTo('#beforeDiv');
    		
    		showError("<spring:message code='email.label.invalidEmailAddress'/>","srEmail");
        }
	}
	function closeWindow(element){
 	    callOmnitureAction('Service Request', 'Service Request DrillDown Close Window');
		//Liferay.Popup.close(element);
	}
	function cleanStatus(){
		clearMessage();
	}
	function reSetBtn(){
		document.getElementById('btnSendEmail').style.color='';
		isSend = true;	
	}
</script>
<script type="text/javascript">
//---- Ominture script 

     portletName = "Service request Drilldown Email";
     addPortlet(portletName);
     pageTitle="Service request Drilldown Email";
    
</script>