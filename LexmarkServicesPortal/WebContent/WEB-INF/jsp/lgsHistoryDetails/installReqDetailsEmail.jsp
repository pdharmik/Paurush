<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_drag.js"></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
 <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">
<!-- 	<tr id="topEmail">
		<td align="left">
		&nbsp;&nbsp;<a id="btmEmail" href="javascript:email();">
				<img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" height="23px" width="23px" style="cursor:pointer" title="<spring:message code="requestInfo.tooltip.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr> -->
<tr>
<td>
<table width="953"  border="0" cellspacing="0" cellpadding="0" align="center" class="emailPrintWraper" id="emailPrintWraper">

  <tr>
    <td class="table-td-style10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="table-td-style11"><strong>Install Request Details</strong>&nbsp; &raquo;&nbsp;<strong><span class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> <span id="reqNumber"></span></span></strong></td>
        </tr>
      </table></td>
  </tr>
  
  
    
  
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.area"/> </strong></td>
                <td class="table-td-style16" id="area"></td>
                 <td class="table-td-style16" id="coveredService"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.subArea"/></strong></td>
                <td class="table-td-style16" id="subarea"></td>
              </tr>
              <tr id="orderSource1">
                <td class="table-td-style16"><strong> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></strong></td>
                <td class="table-td-style16" id="orderSource"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.status"/> </strong></td>
                <td class="table-td-style16" id="serviceRequestStatus"></td>
              </tr>
               
            </table>
            </td>
          <td width="50%" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="3">
              
             <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></strong></td>
                <td class="table-td-style16" id="creationTime"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.requesterName"/></strong></td>
                <td class="table-td-style16" id="requestorName"></td>
              </tr>
             <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.accountName"/> </strong></td>
                <td class="table-td-style16" id="accountName"></td>
              </tr>
              
             
            </table>
            </td>
        </tr>
      
      </table>
      
       
      
      
	  	 <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong>Primary Service Contact</strong></th>
              </tr>
              <tr>
                <td width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td class="table-td-style16" id="primaryname"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td class="table-td-style16" id="primaryphone"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td class="table-td-style16" id="primaryemail"></td>
              </tr>
            </table></td>
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  class="table-td-style16" id="customerReferenceNumber"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" id="costCenter"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style16" id="addtnlDescription"></td>
              </tr>
            
            </table></td>
        </tr>
        <tr id="secondarySection" style="display: none;">
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong>Secondary Service Contact</strong></th>
              </tr>
              <tr>
                <td width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td class="table-td-style16" id="secondaryname"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td class="table-td-style16" id="secondaryphone"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td class="table-td-style16" id="secondaryemail"></td>
              </tr>
            </table></td>
        </tr>
      </table>
       </td></tr> 
       <tr>      
        <table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr style="display: none;" id="addressBlock_div">
   <td valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
          <tr><td>
        <div id="addressBlock"></div></td>
        </tr>
            </table></td>
        </tr>
		</table>
		</tr>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr style="display: none;" id="sr_technician_h4">
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="Details.supplyRequestDetails.heading.activity"/></th>
              </tr>
          <tr><td>
        <div id="sr_technician_div"></div></td>
        </tr>
            </table></td>
        </tr>
		</table>
	
        <br>
	
	  
     
	   <tr>
  	<td>
	    		<table width="100%" border="0" cellspacing="5" cellpadding="0">
					  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.notesAndAttachments"/></strong></td>
  </tr>
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.notes"/></th>
              </tr>
              <tr> <td valign="top" class="table-td-style16" id="notes"></td>
              </tr>
            </table></td>
        </tr>
      </table>
	 </td>
	 </tr>
	 <tr>
  	<td>
<br>
	 		<table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.attachments"/></th>
              </tr>
              <tr>
              <td>
              <div id="showAttachment_Popup">
              
              </div>
              </td>
              </tr>
              
              
            </table></td>
        </tr>
      </table>
      
      </td>
  </tr>

      <table width="950" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
				<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon cursor-pointer" title="<spring:message code="requestInfo.tooltip.emailThisPage"/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;&nbsp;&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table> 
</table>



<script type="text/javascript">
//start new 
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("HardwareRqstNumber").innerHTML;
if(window.opener.window.document.getElementById("coveredService") != null){
	window.document.getElementById("coveredService").innerHTML = window.opener.window.document.getElementById("coveredService").innerHTML;	
}
window.document.getElementById("area").innerHTML = window.opener.window.document.getElementById("area_Popup").innerHTML;
window.document.getElementById("subarea").innerHTML = window.opener.window.document.getElementById("subarea_Popup").innerHTML;
if(window.opener.window.document.getElementById("orderSource_Popup") != null){
	jQuery('#orderSource1').show();
	window.document.getElementById("orderSource").innerHTML = window.opener.window.document.getElementById("orderSource_Popup").innerHTML;
}else{
	jQuery('#orderSource1').remove();
}
window.document.getElementById("serviceRequestStatus").innerHTML = window.opener.window.document.getElementById("serviceRequestStatus_Popup").innerHTML;
window.document.getElementById("creationTime").innerHTML = window.opener.window.document.getElementById("creationTime_Popup").innerHTML;
window.document.getElementById("accountName").innerHTML = window.opener.window.document.getElementById("accountName_Popup").innerHTML;
window.document.getElementById("requestorName").innerHTML = window.opener.window.document.getElementById("requestorName_Popup").innerHTML;


window.document.getElementById("primaryname").innerHTML = window.opener.window.document.getElementById("primaryname_Popup").innerHTML;
window.document.getElementById("primaryphone").innerHTML = window.opener.window.document.getElementById("primaryphone_Popup").innerHTML;
window.document.getElementById("primaryemail").innerHTML = window.opener.window.document.getElementById("primaryemail_Popup").innerHTML;
window.document.getElementById("customerReferenceNumber").innerHTML = window.opener.window.document.getElementById("customerReferenceNumber_Popup").innerHTML;
window.document.getElementById("costCenter").innerHTML = window.opener.window.document.getElementById("costCenter_Popup").innerHTML;
window.document.getElementById("addtnlDescription").innerHTML = window.opener.window.document.getElementById("addtnlDescription_Popup").innerHTML;
if(window.opener.window.document.getElementById("secondarySection") !=null){
	jQuery('#secondarySection').show();
	window.document.getElementById("secondaryname").innerHTML = window.opener.window.document.getElementById("secondaryname_Popup").innerHTML;
	window.document.getElementById("secondaryphone").innerHTML = window.opener.window.document.getElementById("secondaryphone_Popup").innerHTML;
	window.document.getElementById("secondaryemail").innerHTML = window.opener.window.document.getElementById("secondaryemail_Popup").innerHTML;
}else{
	jQuery('#secondarySection').remove();
}

if(window.opener.window.document.getElementById("addressBlock") != null){
	 jQuery('#addressBlock_div').show();
	window.document.getElementById("addressBlock").innerHTML = window.opener.window.document.getElementById("addressBlock").innerHTML;	
}else{
	 jQuery('#addressBlock_div').remove();
}
if(window.opener.window.document.getElementById("sr_technician_h4") != null){
	 jQuery('#sr_technician_h4').show();
	window.document.getElementById("sr_technician_div").innerHTML = window.opener.window.document.getElementById("sr_technician_div").innerHTML;	
}
else{
	 jQuery('#sr_technician_h4').remove();
}
</script>
<script type="text/javascript">

window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes_Popup").innerHTML;
window.document.getElementById("showAttachment_Popup").innerHTML = window.opener.window.document.getElementById("showAttachment_Popup").innerHTML;
//end new

<%--
Commented for adding AUI Popup
function email(){
	new Liferay.Popup({
	title: "<spring:message code='requestInfo.title.emailConfirm'/>",
  	position: ['center','top'],
  	modal: true,
  	width: 550, 
  	height: 'auto',
  	xy: [100, 100],
  	onClose: function() {showSelect();},
  	url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>"
  	});

	};  --%>


</script>
<!--  Aui Popup Added -->
<%-- <script>
var emailPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 650,
	height: 350,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function showEmailPopup(){
	emailPopUpWindow.show();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script> --%>

<script type="text/javascript">
emailFunction();
var emailPopUpWindow;
function emailFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	position: [-80,20],
	resizable: false,
	width: 650,
	height: 350,
	xy: [-80, 20],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}
function showEmailPopup(){
	jQuery(window).scrollTop(0);
	emailPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script>
