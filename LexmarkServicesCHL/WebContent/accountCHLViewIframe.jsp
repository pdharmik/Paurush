<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/dhtmlxcommon.js"></script>
<script src="js/dhtmlxtree.js"></script>
<style type="text/css"><%@ include file="WEB-INF/css/chl.css" %></style>
<style type="text/css"><%@ include file="WEB-INF/css/tree/dhtmlxtree.css" %></style>
<style type="text/css">
html { overflow:auto; overflow-x:hidden; }
</style>
</head>
<%
if (session.getAttribute("saveResult") != null && (Boolean)(session.getAttribute("saveResult")) == true) {
	session.removeAttribute("saveResult");
%>
<!-- Start to close the window, if authorization updated successfully -->
<script type="text/javascript">
    top.window.close();
</script>
<!-- End to close the window, if authorization updated successfully -->
<%} else {%>
<!-- Start to show the view of customer hierarchy tree, when admin comes from IDM application -->
<body>
<%
if (session.getAttribute("saveResult") != null && (Boolean)(session.getAttribute("saveResult")) == false) {
    session.removeAttribute("saveResult");
%>
    <!-- Start to indicate failing to update user authorization -->
    <div id="divSaveFailure">
        <span style="color: red"><spring:message code="failure.updateUserAuthorization"/></span>
    </div>
    <!-- End to indicate failing to update user authorization -->
<%} %>
    <div id="reportingTreeLoadingNotification" class='treeLoading'>
        <img src="${pageContext.request.contextPath}/images/loading-icon.gif"/>
    </div>
	<div id="mainContent" style="display: none">
	    <div id="divCurrentRestriction" style="display: none;">
	        <span id="msgCurrentRestriction" style="BACKGROUND: #F2EEAA; FONT-WEIGHT:bold;"></span>
	        <br/>
	    </div>
	    <div>
	        <spring:message code="rstrctDevicesToAcct"/>
	    </div>
	    
	    <div id="divRestrictionSelection" style="display: none">
            <input type="radio" id="donotHaveRestriction" name="restrictionSelection" onClick="removeRestriction()"/>&nbsp;<spring:message code="deviceRestriction.no"/><br/>
            <input type="radio" id="haveRestriction" name="restrictionSelection" checked="checked"/>&nbsp;<spring:message code="deviceRestriction.yes"/>
	    </div>
        <div style="position: relative; left: 25px">
            <span><spring:message code="chooseLevelGrant"/></span>
            <div id="divReportingHierarchy" style="border: 1px solid black; width: 400px"></div>
        </div>
<script type="text/javascript">
	function getArg(arg){
	    var sValue=location.search.match(new RegExp("[\?\&]" + arg + "=([^\&]*)(\&?)","i"));
	    if (sValue != null) {
	        return decodeURI(sValue?sValue[1]:sValue);
	    }
	    return null;
	};

	var nodeId = "";
	var reportingTree = new dhtmlXTreeObject("divReportingHierarchy","100%","100%",0);
	reportingTree.setImagePath("images/treeImgs/");
	reportingTree.enableTreeLines(true);
	
    reportingTree.attachEvent("onXLS", function(tree,id) {
    	if(id == null)
    		document.getElementById('reportingTreeLoadingNotification').style.display = 'block';
		else
			reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
    });
	reportingTree.attachEvent("onXLE", function(tree, id) {
		if (id == '0') {
			nodeId = tree.getSelectedItemId();
			document.getElementById('reportingTreeLoadingNotification').style.display = 'none';
	        document.getElementById('mainContent').style.display = 'block';
	        document.getElementById('msgCurrentRestriction').innerHTML = "<spring:message code='currentRestriction'/>: " + tree.getSelectedItemText();
	        if (nodeId != '') {
	            document.getElementById('divRestrictionSelection').style.display = "block";
	            document.getElementById('divCurrentRestriction').style.display = "block";
	        }
		} else {
			if (tree.hasChildren(id) == 0) {
				tree.setItemImage2(id, 'leaf.gif', 'folderOpen.gif', 'folderClosed.gif');
			}
		}
	});
	var href = window.location.href;
	var index = href.indexOf("accountCHLViewIframe.jsp");
	var customerHierarchyURL = href.substring(0, index) + 'customerHierarchyURL';
	
	treeURL = customerHierarchyURL + '?operid=' + getArg("operid") + '&type=' + getArg("type") + '&jsession_num=' + getArg("jsession_num");
	reportingTree.loadXML(treeURL);
	reportingTree.setXMLAutoLoading(customerHierarchyURL);
</script>
		<br/>
	    <div id="divForm" align="left">
	        <form id="formUserAuthorization" method="post" action="saveServicesUser" onSubmit="return(processBeforeSubmit())">
	            <input type="hidden" id="mdmId" name="mdmId">
	            <input type="hidden" id="mdmLevel" name="mdmLevel">
	            <input type="hidden" id="chlNodeId" name="chlNodeId">
	            <input type="hidden" id="nodeText" name="nodeText">
	            <input type="hidden" id="operid" name="operid" value=<%=request.getParameter("operid") %>>
	            <input type="hidden" id="type" name="type" value=<%=request.getParameter("type") %>>
	            <input type="hidden" id="jsession_num" name="jsession_num" value=<%=request.getParameter("jsession_num") %>>
	            <input class="submit" type="submit" value="<spring:message code='button.submit'/>">&nbsp;&nbsp;
	            <input type="button" id="cancel" name="cancel" value="<spring:message code='button.cancel'/>" onClick="closeWindow()">
	        </form>
	    </div>
    </div>
</body>
</html>
<script type="text/javascript">
function processBeforeSubmit() {
	// restriction removed
    if (document.getElementById('divRestrictionSelection') && document.getElementById('donotHaveRestriction').checked) {
    	document.getElementById('mdmId').value = '';
        document.getElementById('mdmLevel').value = '';
        document.getElementById('chlNodeId').value = '';
        return true;
    }

    var selectedNodeId = reportingTree.getSelectedItemId();

	// invalid selection: root node selected.
	if (selectedNodeId == "1") {
		alert("<spring:message code='chooseValidRestriction'/>");
		return false;
	}
	// no item selected, or no change
	if (selectedNodeId == "" || nodeId == selectedNodeId) {
		top.window.close();
		return false; 
	}
	
	var conjuctionPosition = selectedNodeId.indexOf('_',0);
	if (conjuctionPosition > -1) {
	    document.getElementById('mdmId').value = selectedNodeId.substring(0, conjuctionPosition);
	    document.getElementById('mdmLevel').value = selectedNodeId.substring(conjuctionPosition + 1);
	} else if (selectedNodeId.length > 1) {
		document.getElementById('mdmId').value = '';
        document.getElementById('mdmLevel').value = '';
        document.getElementById('chlNodeId').value = selectedNodeId;
	}
	document.getElementById("nodeText").value = reportingTree.getSelectedItemText();
	return true;
};

function hideOrShowTree() {
	var isChecked = document.getElementById("chkRestrictAccess").checked;
	if (isChecked) {
		document.getElementById("divUserAuthorization").style.display = "";
        document.getElementById("divUserAuthorization").style.visibility = "visible";
	} else {
        document.getElementById("divUserAuthorization").style.display = "none";
        document.getElementById("divUserAuthorization").style.visibility = "hidden";
	}
};

function closeWindow() {
	top.window.opener=null;
	top.window.open('','_self'); 
	top.window.close();
};

function removeRestriction() {
	reportingTree.clearSelection();
	reportingTree.closeAllItems();
};
</script>
<%} %>
<!-- End to show the view of customer hierarchy tree, when admin comes from IDM application -->

