<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="maps-Div-Popup">

	 <div id="uam-mps-view-map">
        <iframe id="uam-mps-view-map-iframe" class="uam-test-iframe map-img iframe-style2" name="uam-mps-view-map-iframe" scrolling="no" ></iframe>
    </div>
    <form id="uam-mps-map-form" target="uam-mps-view-map-iframe" method="post" action="" >
        <input id="uam-mps-map-input" type="hidden" name="formPayload"/>
    </form>
   
</div>


<%-- POC for Siebel  
<div id="uam-mps-view-map">
        <iframe src="https://dlexwsbla011.na.ds.lexmark.com:9001/callcenter_oui_enu/start.swe?SWECmd=ExecuteLogin&SWEUserName=SATLAS90&SWEPassword=Lexmark0001&SWEAC=SWECmd=GotoView&SWEView=LXK+MPS+Service+Request+List+View&IsPortlet=1&SWESM=Edit+List" id="siebel-iframe"  name="siebel-iframe" scrolling="no" class="iframe-style3"></iframe>
    </div>
    
--%>
<script>
/*var obj={
    "action": "changeSize",
    "item": "map",
    "info": [{
            height: 600,
            width: 800
    		}]
}

jQuery("#uam-mps-map-input").val(JSON.stringify(obj));
jQuery("#uam-mps-map-form").submit();*/
function getIframeHeight(){
	return Math.round($('#uam-mps-view-map-iframe').height());
}
function getIframeWidth(){
	return Math.round($('#uam-mps-view-map-iframe').width());
}
</script>
