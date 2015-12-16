<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="popup-Map-html" style="display: none;">
<div id="maps-Div-Popup">

	 <div id="uam-mps-view-map">
        <iframe id="uam-mps-view-map-iframe" class="uam-test-iframe map-img iframe-style2" name="uam-mps-view-map-iframe" scrolling="no" ></iframe>
    </div>
    <form id="uam-mps-map-form" target="uam-mps-view-map-iframe" method="post" action="" >
        <input id="uam-mps-map-input" type="hidden" name="formPayload"/>
    </form>
   
</div>

</div>
<script type="text/javascript" src="<html:rootPath/>js/LbsService.js?version=3.83"></script>
<script>
function getIframeHeight(){
	return Math.round($('#uam-mps-view-map-iframe').height());
}
function getIframeWidth(){
	return Math.round($('#uam-mps-view-map-iframe').width());
}


</script>
<script type="text/javascript" src="<html:rootPath/>js/mapPopup.js?version=3.84"></script>