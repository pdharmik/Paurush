<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div class="portlet-section-header"></div>
<div class="portlet-section-body">
		<script type="text/javascript" src="<html:rootPath/>js/swfobject.js"></script>
		<script type="text/javascript">
			var flashvars = {xmlpath: "/LexmarkServicesPortal/xml/media_rotator.xml"};
			swfobject.embedSWF("/LexmarkServicesPortal/images/flash/lexmark2.swf", "lex", "970", "380", "9.0.0", false, flashvars, false, false);
		</script>
		<div id="lex">
			<h1>Alternative content</h1>
			<p><a href="http://www.adobe.com/go/getflashplayer">
			<img src="<html:imagesPath/>flash/get_flash_player.gif" alt="Get Adobe Flash player" />
			</a></p>
		</div>
</div>