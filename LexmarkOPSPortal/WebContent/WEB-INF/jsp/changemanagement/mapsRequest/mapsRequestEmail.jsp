<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
 
<table width="100%">

<tr>
<td>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center" style="border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">

  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><span id="changeRequest"><spring:message code="requestInfo.cm.heading.changeRequest"/></span>&nbsp; &raquo; &nbsp;<spring:message code="requestInfo.cm.heading.mapsRequest"/></strong></td>
     </tr>
      </table></td>
      </tr>

      <tr>
        <td><div id="accNameAgreeName"></div></td>
        </tr>
 
  <tr>
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.requestConfirmation"/>
	</strong></td>
  </tr>
 
    <tr>
    <td valign="top" style="padding:10px;font-family:Arial, Helvetica, sans-serif; font-size:11px"><spring:message code="requestInfo.message.confirmMessage1"/>   
		<span id="reqNumber"> </span>		<spring:message code="requestInfo.message.confirmMessage2"/>
		</td>
  </tr>
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" > <span id="firstNameLabel"> </span>
               </td>
	                   
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="workPhoneLabel">
				
				</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddressLabel">
				
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
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="custReferenceId">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="costCenter">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><div id="specialInstruction"  class="noteWrap notesOverflow w300"></div>
				
				</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="reqDate">
				
				</td>
              </tr>
            </table></td>
        </tr>
        <tr id="secondaryContactRow" style="display: none;">
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span id="firstNameLabel2"> </span> 
             </td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="workPhoneLabel2">
				
				</td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddressLabel2">
				
				</td>
              </tr>
          </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      
      <!-- REQUEST INFORMATION GOES BELOW THIS -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
        <tr>
          <td width="50%" valign="top" >
          <table>
          
             <tr><td class="table-td-style16" id="notesOrCommentBlock"></td></tr>
        </table>
          <table width="100%" border="0" cellspacing="0" cellpadding="5" id="mapsContactRow" style="display:none;">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.ContactForMapsRequest"/></th>
              </tr>
            
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16" > <span id="firstNameLabel3"> </span>
               </td>
	                   
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" class="table-td-style16" id="workPhoneLabel3">
				
				</td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" class="table-td-style16" id="emailAddressLabel3">
				
				</td>
              </tr>
              <tr><td></br><td></tr>
          </table></td>
          <td rowspan="2" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5" class="float-right">
              <tr>
           <td class="table-td-style16" id="addressDiv"></td></tr>
      </table></td>
      
      <!-- REQUEST INFORMATION GOES BELOW THIS -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
       
        
        <tr id="hideAttachment">
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <thead>
                <tr>
                  <th align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachments"/></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="fileListDiv"> </td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </table></td>
  </tr>

</table>
	</td>
	</tr>
	<tr><td><br></td></tr>

	<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
				<img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code="requestInfo.tooltip.emailThisPage"/>" />
				<span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>	
</table>

<script type="text/javascript">
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
window.document.getElementById("changeRequest").innerHTML = window.opener.window.document.getElementById("changeRequest").innerHTML;


window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
window.document.getElementById("firstNameLabel").innerHTML = window.opener.window.document.getElementById("firstNameLabel").innerHTML;

window.document.getElementById("workPhoneLabel").innerHTML = window.opener.window.document.getElementById("workPhoneLabel").innerHTML;
window.document.getElementById("emailAddressLabel").innerHTML = window.opener.window.document.getElementById("emailAddressLabel").innerHTML;
window.document.getElementById("custReferenceId").innerHTML = window.opener.window.document.getElementById("custReferenceId").innerHTML;

window.document.getElementById("costCenter").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("specialInstruction").innerHTML = window.opener.window.document.getElementById("specialInstruction").innerHTML;
window.document.getElementById("reqDate").innerHTML = window.opener.window.document.getElementById("reqDate").innerHTML;


if(window.opener.window.document.getElementById("firstNameLabel2")!=null)
{
window.document.getElementById("firstNameLabel2").innerHTML = window.opener.window.document.getElementById("firstNameLabel2").innerHTML;
window.document.getElementById("workPhoneLabel2").innerHTML = window.opener.window.document.getElementById("workPhoneLabel2").innerHTML;
window.document.getElementById("emailAddressLabel2").innerHTML = window.opener.window.document.getElementById("emailAddressLabel2").innerHTML;
jQuery('#secondaryContactRow').show();
}else{
	jQuery('#secondaryContactRow').remove();
}

if(window.opener.window.document.getElementById("firstNameLabel3")!=null)
{
window.document.getElementById("firstNameLabel3").innerHTML = window.opener.window.document.getElementById("firstNameLabel3").innerHTML;
window.document.getElementById("workPhoneLabel3").innerHTML = window.opener.window.document.getElementById("workPhoneLabel3").innerHTML;
window.document.getElementById("emailAddressLabel3").innerHTML = window.opener.window.document.getElementById("emailAddressLabel3").innerHTML;
jQuery('#mapsContactRow').show();
}
else{
	jQuery('#mapsContactRow').remove();
}
if(window.opener.window.document.getElementById("notesOrCommentBlock")!=null){
	window.document.getElementById("notesOrCommentBlock").innerHTML = window.opener.window.document.getElementById("notesOrCommentBlock").innerHTML;
	}
	jQuery("#rqstDesc").addClass("w300");
if(window.opener.window.document.getElementById("addressDiv")!=null){
window.document.getElementById("addressDiv").innerHTML = window.opener.window.document.getElementById("addressDiv").innerHTML;
}
if(window.opener.window.document.getElementById("fileListDiv")!=null){
	jQuery('#hideAttachment').show();
window.document.getElementById("fileListDiv").innerHTML = window.opener.window.document.getElementById("fileListDiv").innerHTML;
}else{
	jQuery('#hideAttachment').hide();
}


		<%-- function doEmail(){
    		
    		window.document.getElementById("btmEmail").style.display='none';
    		
    		
    	};
    	
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
    		doEmail();
    		emailPopUpWindow.show();
    		emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
    		emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
    		emailPopUpWindow.io.start();
    		
    		} --%>
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



