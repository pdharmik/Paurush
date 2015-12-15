<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
</head>
<body>
<div id="contentControlPanel" class="div-style28">
<div class="text-align-left">
    <span><spring:message code="deviceFinder.confirmToControlPanel"/></span>
    <br/>
    <div class="text-align-right">
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