<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>


<div id="content-wrapper-inner">
	<div class="main-wrap">
		<div class="content">
			<td align="right" width="25%">
			<a id="btmEmail" href="javascript:email();">
                <img "ui-icon email-icon" src="<html:imagesPath/>transparent.png"  height="23" width="23" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
                <span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
            </a>
		</td>

       <div id="emailPrintWraper">
	   			<div id="emailHeader"><script>document.writeln(window.opener.window.document.getElementById("header").innerHTML);</script></div>
			    <div id="emailPrint"><script>document.writeln(window.opener.window.document.getElementById("hwDebriefView").innerHTML);</script></div>
	</div>
			      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
          <tr><td>
        <table width="800" border="0" cellspacing="5" cellpadding="0" align="center">
	<tr id="topEmail">
		<td width="55%"></td>
		<td align="right" width="25%">
			<a id="btmEmail" href="javascript:email();">
                <img class="ui-icon email-icon" src="<html:imagesPath/>transparent.png"  height="23" width="23" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
                <span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
            </a>
		</td>
		<td align="right">
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code="button.cancel"/></button>
		</td>
	</tr>
</table>
        </tr>
            </table></td>
        </tr>

      </table> 
			
		</div>
	</div>
</div>
<script type="text/javascript">
	jQuery('#acceptRejectButton').remove();
<%--function email(){
	new Liferay.Popup({
	title: "Email Confirm",
  	position: ['center','top'],
  	modal: true,
  	width: 550, 
  	height: 'auto',
  	xy: [100, 100],
  	onClose: function() {showSelect();},
  	url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>"
  	});

	};
  --%>
  
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
  function email(){
  	jQuery(window).scrollTop(0);
  	emailPopUpWindow.show();
  	jQuery(".aui button.close, .aui button.btn.close").hide();
  	emailPopUpWindow.titleNode.html("Email Confirm");
  	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
  	emailPopUpWindow.io.start();
  	
  	}
  
  </script>
