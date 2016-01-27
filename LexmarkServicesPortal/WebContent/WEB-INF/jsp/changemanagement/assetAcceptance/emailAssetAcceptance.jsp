<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page import = "com.lexmark.services.util.LexmarkSPOmnitureConstants" %>
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
 <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<table width="100%">

<tr>
<td>
<table width="800"  border="0" cellspacing="0" cellpadding="0" align="center" id="emailPrintWraper" class="emailPrintWraper">

  <tr>
    <td class="table-td-style10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td  class="table-td-style11"><strong><span id="changeRequest"><spring:message code="requestInfo.cm.heading.changeRequest"/></span>&nbsp; &raquo;&nbsp;
          <spring:message code = "requestInfo.cm.label.acceptanceRequest"/></strong></td>
     </tr>
      </table></td>
    </tr> 
    
    <!-- Added for CI7 BRD14-02-12 --> 
      <tr>
        <td><div id="accNameAgreeName"></div></td>
        </tr>
        <!-- END -->
  
  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.requestConfirmation"/>
	</strong></td>
  </tr>
 
    <tr>
    <td valign="top" class="table-td-style13"><spring:message code="requestInfo.message.confirmMessage1"/>   
		<span id="reqNumber"> </span>		<spring:message code="requestInfo.message.confirmMessage2"/>
		</td>
  </tr>
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16" > <span id="primaryCont_name"> </span>
               </td>
	                   
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" class="table-td-style16" id="primaryCont_phone">
				
				</td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" class="table-td-style16" id="primaryCont_email">
				
				</td>
              </tr>
          </table></td>
          <td rowspan="2" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5" class="float-right">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  class="table-td-style16" id ="addtnlInfo_customerRefId">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" id ="addtnlInfo_costCenter">
				
				</td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style16" ><div id ="addtnlInfo_description" class="smallNotesOverflow"></div>
				
				</td>
              </tr>
              
            </table></td>
        </tr>
        <tr id="secondaryContactRow" style="display: none;">
          <td  valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" class="table-td-style16"> <span id="secondaryCont_name"> </span> 
             </td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" class="table-td-style16" id="secondaryCont_phone">
				
				</td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" class="table-td-style16" id="secondaryCont_email">
				
				</td>
              </tr>
          </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr class="hr-style1" />
      
      <!-- REQUEST INFORMATION GOES BELOW THIS -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
        <tr>
          <td valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.cm.chlothers.heading.newChangedSelected"/> </th>
              </tr>
             
         		
               <tr>
                 <td valign="top" class="table-td-style16" width="30px"><strong><spring:message code="requestInfo.label.subArea"/></strong></td>
                <td valign="top" class="table-td-style16" id="subArea">
				</td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16" width="30px"><strong><spring:message code="requestInfo.label.notes"/></strong></td>
                <td valign="top" class="table-td-style16">
                <div id="notes" class="smallNotesOverflow w300"></div>
				
				</td>
              </tr>
              
             
              </table></td>
        </tr>
        
        <tr>
          <td valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
              <tbody>
                <tr>
                  <td class="table-td-style16" id="fileListDiv"> </td>
                </tr>
              </tbody>
            </td>
        </tr>
      </table></td>
  </tr>

</table>
	</td>
	</tr>
	<tr><td><br></td></tr>

	<br>
<table width="620" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<a id="btmEmail" onclick="showEmailPopup();">
				<img src="<html:imagesPath/>transparent.png"  class="ui_icon_sprite email-icon cursor-pointer" title="<spring:message code="requestInfo.tooltip.emailThisPage"/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;&nbsp;&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>	
</table>

<script type="text/javascript">
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
/* window.document.getElementById("changeRequest").innerHTML = window.opener.window.document.getElementById("changeRequest").innerHTML; */
/* Added for CI7 BRD14-02-12 */
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
/* END */
window.document.getElementById("primaryCont_name").innerHTML = window.opener.window.document.getElementById("primaryCont_name").innerHTML;
window.document.getElementById("primaryCont_phone").innerHTML = window.opener.window.document.getElementById("primaryCont_phone").innerHTML;
window.document.getElementById("primaryCont_email").innerHTML = window.opener.window.document.getElementById("primaryCont_email").innerHTML;
window.document.getElementById("addtnlInfo_customerRefId").innerHTML = window.opener.window.document.getElementById("custReferenceId").innerHTML;
window.document.getElementById("addtnlInfo_costCenter").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("addtnlInfo_description").innerHTML = window.opener.window.document.getElementById("specialInstruction").innerHTML;
window.document.getElementById("subArea").innerHTML = window.opener.window.document.getElementById("subArea").innerHTML;
window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;

if(window.opener.window.document.getElementById("secondaryContact_name")!=null){
	window.document.getElementById("secondaryCont_name").innerHTML = window.opener.window.document.getElementById("secondaryContact_name").innerHTML;
	window.document.getElementById("secondaryCont_phone").innerHTML = window.opener.window.document.getElementById("secondaryContact_phone").innerHTML;
	window.document.getElementById("secondaryCont_email").innerHTML = window.opener.window.document.getElementById("secondaryContact_email").innerHTML;
jQuery('#secondaryContactRow').show();
}else{
	jQuery('#secondaryContactRow').remove();
}
if(window.opener.window.document.getElementById("fileListDiv")!=null)
window.document.getElementById("fileListDiv").innerHTML = window.opener.window.document.getElementById("fileListDiv").innerHTML;


			function doEmail(){
    		
    		window.document.getElementById("btmEmail").style.display='none';
    		
    		
    	};
    	
    	<%--	function email(){
    		
    		doEmail();
    		window.opener.callOmnitureAction('<%=LexmarkSPOmnitureConstants.ASSETACCEPTANCEEMAIL%>','<%=LexmarkSPOmnitureConstants.CONFIRMASSETACCEPTANCEEMAIL%>');
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
    	
    		};--%>
</script>
<%-- Added for AUI Pop up --%>
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