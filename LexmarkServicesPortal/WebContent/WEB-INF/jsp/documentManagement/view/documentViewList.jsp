<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<style>
div.gridbox_light .xhdr { 
	background-image:none !important;
	background:#e6e6f0 !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:resourceURL id="documentViewListXML" var="listURL">
</portlet:resourceURL>
<table width="100%" border="0" class="sectionWrapper" cellspacing="5px">
	<tr>
		<td class='orangeSectionTitles'><spring:message code="documentViewPage.rhs.title" /></td>
	</tr>
	<tr>
		<td>
			<div id="hintDiv"><spring:message code="documentViewPage.rhs.hint" /></div>
			<div id="divDocList" class="div-style46"></div>
			<div style ="display:none" id="loadingListNotification" class="div-style47">
        		<spring:message code='loadingNotification'/>
   			</div>
			<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
		</td>
	</tr>
</table>
<script type="text/javascript">
	var today = '<spring:message code="today"/>';
	function dateSort(a,b,order){
		if(a==today)return (order=="asc"?1:-1);
		if(b==today)return -(order=="asc"?1:-1);
		return (a>b?1:-1)*(order=="asc"?1:-1);
	}
</script>
<script type="text/javascript">
var notInit = true;
function initGrid(){
	document.getElementById("hintDiv").style.display="none";
	srgrid = new dhtmlXGridObject('divDocList');
	srgrid.setImagePath("<html:imagesPath/>gridImgs/");
	srgrid.setHeader("<spring:message code='documentViewPage.rhs.listHeader'/>");
	srgrid.setColAlign("left,left");
	srgrid.setColTypes("ro,ro");
	srgrid.setColSorting("strSortCI,dateSort");
	srgrid.setInitWidthsP("70,30");
	srgrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	srgrid.setPagingSkin("bricks");
	
	srgrid.enableAutoWidth(true);
	srgrid.enableAutoHeight(true);
	srgrid.enableMultiline(true);
	srgrid.setSizes();
	srgrid.attachEvent("onMouseOver", function(id,ind){
		srgrid.setRowTextStyle(id, "cursor: pointer;");
	});

	srgrid.attachEvent("onXLE", function() {
        document.getElementById('loadingListNotification').style.display = 'none';
    });
	srgrid.attachEvent("onXLS", function() {
        document.getElementById('loadingListNotification').style.display = 'block';
    });
	srgrid.init();
	srgrid.setSkin("light");
}
</script>

<script type="text/javascript">
	var url = "${listURL}";
	function viewDocuments(path){
		if(notInit){
			initGrid();
		}
		srgrid.clearAndLoad(url+"&path="+path);
		notInit = true;
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Document View";
     addPortlet(portletName);
     pageTitle="Document view list";
</script>