<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_drag.js"></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
 

<table width="953px" border="0" cellspacing="0" cellpadding="0" align="center" style=" margin-top: -4.5%;border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">
  <tr>
    	<td style="background-color:#dadada"><img src="<html:imagesPath/>/logoLexEmail.png" width="100%" height="65" /></td>
  	</tr>
  
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td  style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><span id="changeRequest"><spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/></span>&nbsp; &raquo;&nbsp;
          <spring:message code = "Details.supplyRequestDetails.label.rquestNumber"/>&nbsp;<span id = "reqNumber"></span></strong></td>
     </tr>
      </table></td>
  </tr>
  <tr>
  <td>&nbsp;</td>
  </tr>
    
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.area"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="area"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.subArea"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="subarea"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="Details.changeRequestDetails.label.status"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="status"></td>
              </tr>
            </table>
            </td>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="3">
              
             <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="creationTime"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="Details.changeRequestDetails.label.requesterName"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="requestorName"></td>
              </tr>
            </table>
            </td>
        </tr>
      
      </table>
        <br>
     
        
            </tr>
            <br>
            <tr>
            <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <tr><td width="200">
             <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div></td>
             <td class="status">
              
               <table><tr><td style="border-left: 0px solid #000000!important; border-right: 0px solid #000000!important">
                <div id="div_progressBar" style="border-left: 0px solid #000000!important; border-right: 0px solid #000000!important"><!-- PROGRESS BAR WILL COME HERE --></div>
                </td></tr></table>
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><spring:message code="Details.changeRequestDetails.process.submitted"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.inProgress"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.Complete"/></td>
                  </tr>
                </table>
                
              </td></tr>
              </table></td>
            </tr>
            <tr>
            <td>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="Details.supplyRequestDetails.heading.requestStatusHistory"/></th>
              </tr>
          <tr><td>
        <div style=" width : 75%" id="tab_statusGrid"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
      </td>
      </tr>
      <br>
        <tr> 
          <td>  
          <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span id="primaryCont_name"></span> 
             </td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="primaryCont_phone">
				
				</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="primaryCont_email">
				
				</td>
              </tr>
          </table></td>
        
      	
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5" style="float: right;">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id ="addtnlInfo_customerRefId">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id ="costCenter">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id ="description">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id ="effDateChange">
				
				</td>
              </tr>
              
            </table></td>
        </tr>
        
        <tr id="secondarySection" style="display: none;">
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span id="secondaryCont_name"></span> 
             </td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="secondaryCont_phone">
				
				</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="secondaryCont_email">
				
				</td>
              </tr>
          </table></td>
        </tr>
      </table>
      <br>
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      
      <!-- REQUEST INFORMATION GOES BELOW THIS -->
      <tr>
      <td>
      <table width="50%" border="0" cellspacing="5" cellpadding="0" >
       
        <tr id="notesView" style="display: none;">
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="50%" border="0" cellspacing="0" cellpadding="5">
         
          <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; width="30px"><strong><spring:message code="requestInfo.label.notes"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="notes">
				</td>
              </tr>
             
              </table></td>
        </tr>
        <br>
        <tr id="attachmentView" style="display: none;">
        <td>
     	<p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
     	<div id="attachmentDIV1"></div>
        </td>
        </tr>
        <tr id="attachmentView1" style="display: none;">
        <td>
     	<p class="info inlineTitle"><spring:message code="requestInfo.heading.attachments"/></p>
     	<div id="attachmentDIV"></div>
        </td>
        </tr>
        
        <tr id="attachmentView2" style="display: none;">
        <td>
         <spring:message code="Details.changeRequestDetails.message.toViewTheInformation"/>
          <div id="shwAttachment"></div>
     </td>
 	 </tr>
  <br>
  <tr>
  <td>
   <p class="inlineTitle"><spring:message code="Details.changeRequestDetails.heading.notifications"/></p>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
     
	
		<tr>
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              
          <tr><td>
        <div style=" width : 75%" id="notificationsGrid"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
      </td>
      </tr>
      <tr>
      <td>

</td>
</tr>
      
      
        </table>
        </td>
        </tr>
      	
</table>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
				<img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.tooltip.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;&nbsp;&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>

<script type="text/javascript">


window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
//start new
window.document.getElementById("area").innerHTML = window.opener.window.document.getElementById("area").innerHTML;
window.document.getElementById("subarea").innerHTML = window.opener.window.document.getElementById("subArea").innerHTML;
window.document.getElementById("status").innerHTML = window.opener.window.document.getElementById("status").innerHTML;
window.document.getElementById("requestorName").innerHTML = window.opener.window.document.getElementById("requesterName").innerHTML;
window.document.getElementById("primaryCont_name").innerHTML = window.opener.window.document.getElementById("primaryCont_name").innerHTML;
window.document.getElementById("primaryCont_phone").innerHTML = window.opener.window.document.getElementById("primaryCont_phone").innerHTML;
window.document.getElementById("primaryCont_email").innerHTML = window.opener.window.document.getElementById("primaryCont_email").innerHTML;
window.document.getElementById("addtnlInfo_customerRefId").innerHTML = window.opener.window.document.getElementById("addtnlInfo_customerRefId").innerHTML;
window.document.getElementById("costCenter").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("description").innerHTML = window.opener.window.document.getElementById("description").innerHTML;
window.document.getElementById("effDateChange").innerHTML = window.opener.window.document.getElementById("effDateChange").innerHTML;
	if(window.opener.window.document.getElementById("secondarySection") !=null){
		jQuery('#secondarySection').show();
		window.document.getElementById("secondaryCont_name").innerHTML = window.opener.window.document.getElementById("secondaryCont_name").innerHTML;
		window.document.getElementById("secondaryCont_phone").innerHTML = window.opener.window.document.getElementById("secondaryCont_phone").innerHTML;
		window.document.getElementById("secondaryCont_email").innerHTML = window.opener.window.document.getElementById("secondaryCont_email").innerHTML;
	}else{
		jQuery('#secondarySection').remove();
	}

//end new



window.document.getElementById("creationTime").innerHTML = window.opener.window.document.getElementById("date_time").innerHTML;
window.document.getElementById("div_progressBar").innerHTML = window.opener.window.document.getElementById("div_progressBar").innerHTML;
window.document.getElementById("tab_statusGrid").innerHTML = window.opener.window.document.getElementById("tab_statusGrid").innerHTML;
if(window.opener.window.document.getElementById("notes") != null){
	jQuery('#notesView').show();
	window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;
}else{
	jQuery('#notesView').remove();
}
</script>

<script type="text/javascript">
if(window.opener.window.document.getElementById("shwAttachment") != null){
	jQuery('#attachmentView2').show();
	window.document.getElementById("shwAttachment").innerHTML = window.opener.window.document.getElementById("shwAttachment").innerHTML;
}else{
	jQuery('#attachmentView2').remove();
}
if(window.opener.window.document.getElementById("attachmentDIV") != null){
	jQuery('#attachmentView1').show();
	window.document.getElementById("attachmentDIV").innerHTML = window.opener.window.document.getElementById("attachmentDIV").innerHTML;
}else{
	jQuery('#attachmentView1').remove();
}
if(window.opener.window.document.getElementById("attachmentView") != null){
	jQuery('#attachmentView').show();
	window.document.getElementById("attachmentDIV1").innerHTML = window.opener.window.document.getElementById("attachmentView").innerHTML;
}else{
	jQuery('#attachmentView').remove();
}
</script>
<script type="text/javascript">
window.document.getElementById("notificationsGrid").innerHTML = window.opener.window.document.getElementById("notificationsGrid").innerHTML;

// commented for adding AUI Popup
    	
    //	function email(){
    		<%-- window.opener.callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEEMAIL%>','<%=LexmarkSPOmnitureConstants.CONFIRMASSETACCEPTANCEEMAIL%>'); --%>
    	<%--	new Liferay.Popup({
    		title: "<spring:message code='requestInfo.title.emailConfirm'/>",
    	  	position: ['center','top'],
    	  	modal: true,
    	  	width: 550, 
    	  	height: 'auto',
    	  	xy: [100, 100],
    	  	onClose: function() {showSelect();},
    	  	url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>"
    	  	});
    	
    		}; --%>
    		
    		
    		
    		
    		
</script>
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

</script>
 --%>
 
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