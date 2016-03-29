<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
 <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
 <%--
<table width="100%">
	<tr id="topEmail">
		<td align="left">
		&nbsp;&nbsp;<a id="btmEmail" href="javascript:email();">
				<img class="ui_icon_sprite email-icon cursor-pointer" src="<html:imagesPath/>transparent.png" height="27px" width="27px" title="<spring:message code='requestInfo.link.emailThisPage'/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr>
</table> --%>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center" class="emailPrintWraper" id="emailPrintWraper">

  <tr>
    <td class="table-td-style10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" class="table-td-style11"><strong>
          <span id="changeRequest"><spring:message code="ordermanagement.returns.heading.suppliesRequest"/></span>&nbsp; &raquo; &nbsp;<span id="flowType"> <spring:message code="ordermanagement.returns.heading.returnSupplies"/></span></strong></td>
     </tr>
      </table></td>
      </tr>
      
      <!-- Added for CI 7 BRD14-02-12 -->
      <tr>
        <td><span id="accNameAgreeName"> </span></td>
        </tr>
  <!-- END -->
  
  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.requestConfirmation"/>
	</strong></td>
  </tr>
  <tr>
    <td valign="top" class="table-td-style13" id="headerMesg1">
    <spring:message code="requestInfo.message.confirmMessage1"/>  
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
          <td rowspan="2" width="50%" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5" >
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  class="table-td-style16" >
				<span id ="addtnlInfo_customerRefId"></span>
				</td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" >
				<span id ="addtnlInfo_costCenter"></span>
				</td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style16" >
				<div id ="addtnlInfo_description" class="smallNotesOverflow"></div>
				</td>
              </tr>
              
            </table></td>
        </tr>
        <tr id="secondaryContactTR">
        
        
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
                <td valign="top" class="table-td-style16" >
				<span id="secondaryCont_phone"> </span>
				</td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" class="table-td-style16" >
				<span id="secondaryCont_email"> </span>
				</td>
              </tr>
          </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr class="hr-style1" />
     
     
     
     <table width="100%" cellspacing="5" cellpadding="0" border="0">

		<tr>
          <td width="50%" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">

        <tbody><tr>
          <td valign="top" class="table-td-style14"><table width="100%" cellspacing="0" cellpadding="5" border="0">
              
             <%-- <tr>
                <td width="100" valign="top" class="table-td-style16"><strong>Area:</strong></td>
                <td valign="top" class="table-td-style16">Area</td>
              </tr>  --%>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.subArea"/></strong></td>
                <td valign="top" class="table-td-style16"><span id="subArea"></span></td>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16"><strong><spring:message code="requestInfo.label.notes"/></strong></td>
                <td valign="top" class="table-td-style16"><div id="notes" class="smallNotesOverflow"></div></td>
              </tr>
             </table>			  
			  </td>

			  <td width="50%" valign="top" class="table-td-style14">
			  <table width="100%" cellspacing="0" cellpadding="5" border="0">
              <tbody><tr>
                <th align="left" class="table-td-style15" colspan="2"><spring:message code="requestInfo.heading.returnAddress"/></th>
              </tr>
              <tr>
                <td valign="top" class="table-td-style16" colspan="2">
                 <span id="returnAddressSpan"></span>
               
              </tr>
             
            </tbody></table>
			  </td>
        </tr>
       
      </tbody></table>
     
     <%--
     <table width="100%" cellspacing="5" cellpadding="0" border="0">
        <tbody><tr>
          <td width="50%" valign="top" class="table-td-style14">
          <table width="100%" cellspacing="0" cellpadding="5" border="0">
              <thead>
                <tr>
                  <th align="left" class="table-td-style15">Attachments</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td valign="top" class="table-td-style16">DocumentName1</td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </tbody></table>
       --%>
      <p class="inlineTitle"><spring:message code="orderSupplies.notes.attachments"/> </p>
      <div id="attachmentDiv">
      
      </div>
     
</td>
  </tr>
</table>
</td></tr></table>
<%-- 
<table>		
	<tr><td><br></td></tr>
	<tr>
		<td align="left">
		<a id="btmEmail" href="javascript:email();">
				<img src="<html:imagesPath/>transparent.png"  height="27px" width="27px" class="cursor-pointer" title="<spring:message code='requestInfo.link.emailThisPage'/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>
		</td>
	</tr>
</table>
--%>
<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right" >
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
				<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon cursor-pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
				<span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
			</a>&nbsp;
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code='button.cancel'/></button>
		</td>
	</tr>
</table>

<script type="text/javascript">


window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;

<%--  Changes BRD 12 CI 7--%>
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
<%--Ends  Changes BRD 12 CI 7--%>
window.document.getElementById("primaryCont_name").innerHTML = window.opener.window.document.getElementById("primaryCont_name").innerHTML;
window.document.getElementById("primaryCont_phone").innerHTML = window.opener.window.document.getElementById("primaryCont_phone").innerHTML;
window.document.getElementById("primaryCont_email").innerHTML = window.opener.window.document.getElementById("primaryCont_email").innerHTML;



window.document.getElementById("addtnlInfo_customerRefId").innerHTML = window.opener.window.document.getElementById("addtnlInfo_customerRefId").innerHTML;
window.document.getElementById("addtnlInfo_costCenter").innerHTML = window.opener.window.document.getElementById("addtnlInfo_costCenter").innerHTML;
window.document.getElementById("addtnlInfo_description").innerHTML = window.opener.window.document.getElementById("addtnlInfo_description").innerHTML;


window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;
window.document.getElementById("subArea").innerHTML = window.opener.window.document.getElementById("subArea").innerHTML;
window.document.getElementById("returnAddressSpan").innerHTML = window.opener.window.document.getElementById("returnAddressSpan").innerHTML;


if(window.opener.window.document.getElementById("secondaryContact_name")!=null){
window.document.getElementById("secondaryCont_name").innerHTML = window.opener.window.document.getElementById("secondaryContact_name").innerHTML;
window.document.getElementById("secondaryCont_phone").innerHTML = window.opener.window.document.getElementById("secondaryContact_phone").innerHTML;
window.document.getElementById("secondaryCont_email").innerHTML = window.opener.window.document.getElementById("secondaryContact_email").innerHTML;
}else{
	jQuery('#secondaryContactTR').remove();
}

if(window.opener.window.document.getElementById("fileListDiv")!=null)
	window.document.getElementById("attachmentDiv").innerHTML = window.opener.window.document.getElementById("fileListDiv").innerHTML;




<%--  Commented for Adding AUI popup

    function email(){
    		//alert('typeofflow='+'${typeOfFlow}'');
    		
    		new Liferay.Popup({
    		title: "Email Confirmation Page",
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