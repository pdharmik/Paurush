<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title></title>
</head>
<body>
<div id="contentControlPanel" style="max-height: 500px; overflow-y: auto">
<div style="text-align: left">
    <span><spring:message code="deviceFinder.confirmToControlPanel"/></span>
    <br/>
    <div style="text-align: right">
	    <button class="button_cancel" onclick="dialog.dialog('close');">
	        <spring:message code="button.cancel" />
	    </button>
	    &nbsp;&nbsp;
	    <button class="button" onclick="dialog.dialog('close'); openControlPanel('${controlPanelURL}', '${pageName}')">
	        <spring:message code="button.ok"/>
	    </button>
    </div>
</div>
</div>
</body>
<script type="text/javascript">
    function openControlPanel(url, pageName) {
    	callOmnitureAction(pageName, pageName + ' open control panel'); //Change done for Defect 3924-Jan 13 Release
        window.open(url, 'controlPanel');
    };
    
	/* function closeCntlPanelPopup(id){
		controlPanelPopUpWindow.hide();
} */
</script>
</html>