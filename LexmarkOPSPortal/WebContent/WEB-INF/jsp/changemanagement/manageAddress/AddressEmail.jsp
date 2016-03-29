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
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><spring:message code="requestInfo.cm.heading.changeRequest"/></strong> &raquo; <strong><spring:message code="requestInfo.cm.manageAddress.heading.addresses"/></strong></td>
        </tr>
      </table></td>
  </tr>
  
  <!-- Added for CI7 BRD14-02-12 -->
  <tr>
        <td><div id="accNameAgreeName"></div></td>
        </tr>
        <!-- END -->
        
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
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="firstLastName1"> </td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="phoneNo1"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddress1"></td>
              </tr>
            </table></td>
          <td rowspan="2" width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="customerRefId"></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="costCenter"></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><div id ="description" class="smallNotesOverflow"></div></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="reqEffectiveDate"></td>
              </tr>
            </table></td>
        </tr>
        <tr id="secondaryContactRow" style="display: none;">
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/><strong></strong></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="firstLastName2"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="phoneNo2"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="emailAddress2"></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      
      <!-- REQUEST INFORMATION GOES BELOW THIS -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;" id="headingFlowType"></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.addressName"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="addressName"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.storeFrontName"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="storeFrontName"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.addressLine1"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="addressLine1"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.addressLine2"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="addressLine2"></td>
              </tr>
              
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.city"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="city"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.country"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="country"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.stateProvince"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="stateProvince"></td>
              </tr>
              <%-- district added --%>
			  <tr style="display:none" id="hideOfficeNo">
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.officeNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="officeNumber"></td>
              </tr>
			   <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.changemgmt.address.county"/>:</strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="county"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.changemgmt.address.district"/>:</strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="district"></td>
              </tr>
              
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.postalCode"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="postalCode"></td>
              </tr>
            </table></td>
        </tr>
   
      </table>
      
      <p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.notes" /></th>
              </tr>
              <tr>
             <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> &nbsp;</td>
             </tr>
              <tr>
                <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><div id="attachmentDescription" class="smallNotesOverflow w300"></div></td>
                
              </tr>
              </table>
               <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachments" /></th>
              </tr>
              </table>
    <div id="div_attachmentListPrint">
    </div>
    
     </td>
  </tr>
  
</table>
      </td>
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
				<img src="<html:imagesPath/>/transparent.png"  class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
				<span style="cursor:pointer"><spring:message code="requestInfo.link.emailThisPage"/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>	
</table>

<script type="text/javascript">

window.document.getElementById("headingFlowType").innerHTML = window.opener.window.document.getElementById("headingFlowType").innerHTML;
/* Added for CI7 BRD14-02-12 */
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
/* END */
window.document.getElementById("firstLastName1").innerHTML = window.opener.window.document.getElementById("firstLastName1").innerHTML;
window.document.getElementById("phoneNo1").innerHTML = window.opener.window.document.getElementById("phoneNo1").innerHTML;
window.document.getElementById("emailAddress1").innerHTML = window.opener.window.document.getElementById("emailAddress1").innerHTML;
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
window.document.getElementById("customerRefId").innerHTML = window.opener.window.document.getElementById("customerRefId").innerHTML;
window.document.getElementById("costCenter").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("description").innerHTML = window.opener.window.document.getElementById("description").innerHTML;
window.document.getElementById("reqEffectiveDate").innerHTML = window.opener.window.document.getElementById("reqEffectiveDate").innerHTML;

if(window.opener.window.document.getElementById("firstLastName2")!=null){
	window.document.getElementById("firstLastName2").innerHTML = window.opener.window.document.getElementById("firstLastName2").innerHTML;
	window.document.getElementById("phoneNo2").innerHTML = window.opener.window.document.getElementById("phoneNo2").innerHTML;
	window.document.getElementById("emailAddress2").innerHTML = window.opener.window.document.getElementById("emailAddress2").innerHTML;
	jQuery('#secondaryContactRow').show();
}else{
	jQuery('#secondaryContactRow').remove();
}
</script>
<script type="text/javascript">
window.document.getElementById("addressName").innerHTML = window.opener.window.document.getElementById("addressName").innerHTML;
window.document.getElementById("storeFrontName").innerHTML = window.opener.window.document.getElementById("storeFrontName").innerHTML;
window.document.getElementById("addressLine1").innerHTML = window.opener.window.document.getElementById("addressLine1").innerHTML;
window.document.getElementById("addressLine2").innerHTML = window.opener.window.document.getElementById("addressLine2").innerHTML;
/*window.document.getElementById("addressLine3").innerHTML = window.opener.window.document.getElementById("addressLine3").innerHTML;*/
window.document.getElementById("city").innerHTML = window.opener.window.document.getElementById("city").innerHTML;
window.document.getElementById("country").innerHTML = window.opener.window.document.getElementById("country").innerHTML;
window.document.getElementById("stateProvince").innerHTML = window.opener.window.document.getElementById("stateProvince").innerHTML;
if(window.opener.window.document.getElementById("officeNumber") !=null){
jQuery("#hideOfficeNo").show();
window.document.getElementById("officeNumber").innerHTML = window.opener.window.document.getElementById("officeNumber").innerHTML;
}
window.document.getElementById("county").innerHTML = window.opener.window.document.getElementById("county").innerHTML;
window.document.getElementById("district").innerHTML = window.opener.window.document.getElementById("district").innerHTML;
window.document.getElementById("postalCode").innerHTML = window.opener.window.document.getElementById("postalCode").innerHTML;
window.document.getElementById("attachmentDescription").innerHTML = window.opener.window.document.getElementById("attachmentDescription").innerHTML;
window.document.getElementById("div_attachmentListPrint").innerHTML = window.opener.window.document.getElementById("div_attachmentListPrint").innerHTML;


		function doEmail(){
    		//window.document.getElementById("topEmail").style.display='none';
    		window.document.getElementById("btmEmail").style.display='none';
    		
    		
    	};
	
    	<%-- Commented to add AUI Popup
    	 	function email(){
    		
    		//doEmail();
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
<%-- Added for AUI PopUp --%>
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
		doEmail();
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
