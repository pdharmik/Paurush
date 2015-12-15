<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script>

var cartPopUpWindow=null;

	cartinitialize();


function cartinitialize(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {
	cartPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	id: 'dialogId',
	constrain2view: true,
	position: [-80,20],
	autoReposition: true,
	modal: true,
	resizable: false,
	width: 900, 
    height: 'auto',
    xy: [-80, 20],
    destroyOnClose: true,
	visible: false,
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}
var htmlContent='<div><h4 class="cartClass"><a style="cursor:pointer"><img class="helpIcon ui_icon_sprite ui-icon-closethick" cursor="pointer" onclick="closePopup();" width="18" height="18" src="/LexmarkServicesPortal/images/transparent.png" title=""></a><spring:message code="requestInfo.title.cartView"/></h4></div>';
function cartView(){
	jQuery(window).scrollTop(0);
	cartPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	cartPopUpWindow.titleNode.html(htmlContent);
	cartPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'>" + 
            "<portlet:param name='action' value='showCartViewPage' /></portlet:renderURL>");
	cartPopUpWindow.io.start();
	};
	
jQuery( ".yui3-widget " ).click(function() {
		  alert( "Handler for .click() called." );
		});




</script>