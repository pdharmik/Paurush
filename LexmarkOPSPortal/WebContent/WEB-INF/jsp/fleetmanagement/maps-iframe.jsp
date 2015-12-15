<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<div id="maps-Div-Popup">

	 <div id="uam-mps-view-map">
        <iframe id="uam-mps-view-map-iframe" class="uam-test-iframe map-img floatR" name="uam-mps-view-map-iframe" scrolling="no" style="height:600px;width:100%; float:left; border: 1px solid #e8e8e8;"></iframe>
        <div class="clearBoth"></div>
    </div>
    <form id="uam-mps-map-form" target="uam-mps-view-map-iframe" method="post" action="" >
        <input id="uam-mps-map-input" type="hidden" name="formPayload"/>
    </form>
   
</div>


<%-- POC for Siebel  
<div id="uam-mps-view-map">
        <iframe src="https://dlexwsbla011.na.ds.lexmark.com:9001/callcenter_oui_enu/start.swe?SWECmd=ExecuteLogin&SWEUserName=SATLAS90&SWEPassword=Lexmark0001&SWEAC=SWECmd=GotoView&SWEView=LXK+MPS+Service+Request+List+View&IsPortlet=1&SWESM=Edit+List" id="siebel-iframe"  name="siebel-iframe" scrolling="no" style="height:600px;width:100%; float:left; border: 1px solid black;"></iframe>
    </div>
    
--%>
<script>
function getIframeHeight(){
	return Math.round($('#uam-mps-view-map-iframe').height());
}
function getIframeWidth(){
	return Math.round($('#uam-mps-view-map-iframe').width());
}
</script>
