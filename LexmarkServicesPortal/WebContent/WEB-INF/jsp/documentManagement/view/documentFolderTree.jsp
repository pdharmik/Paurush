<script type="text/javascript"><%@ include file="../../../../js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@ include file="../../../css/tree/dhtmlxtree.css" %></style>
<portlet:resourceURL id="folderTreeXML" var="folderTreeXMLURL"/>
<div class="left-nav" id="categoryTree">
	<div class="left-nav-inner" style="overflow:hidden;">
		<div class="left-nav-header">
			<h3><spring:message code="documentViewPage.lhs.title"/></h3>
		</div><!-- left-nav-header -->
		<br>
		<div id="hierarchy_tree_div" style="display: none"></div>
        <div id="notificationDiv" class='treeLoading'>
            <img src="<html:imagesPath/>loading-icon.gif"/>
        </div>
	</div>
	<div class="left-nav-footer"></div><!-- left-nav-footer -->
</div>

<script type="text/javascript">
	var locationTree = new dhtmlXTreeObject("hierarchy_tree_div","100%","100%",0);
	locationTree.setSkin('sky_blue');
	locationTree.setImagePath("<html:imagesPath/>treeImgs/");
	locationTree.enableTreeLines(true);
	if(jQuery.browser.ie) {
		locationTree.enableIEImageFix(true);
	}
	
    locationTree.attachEvent("onXLS", function() {
        document.getElementById('notificationDiv').style.display = 'block';
    });
	locationTree.attachEvent("onXLE", function() {
        document.getElementById('hierarchy_tree_div').style.display = 'block';
        document.getElementById('notificationDiv').style.display = 'none';
    });
	locationTree.loadXML("${folderTreeXMLURL}");
	
</script>
