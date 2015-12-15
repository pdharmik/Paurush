<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--  Changed for CI Defect 11868 -->
<style> .ui-dialog-titlebar{background: none repeat scroll 0 0 #C3C3C3!important;color: #222222 !important;}
.close-btn{
       float:right !important;
       width:30px;
       height:30px;
       
}
.yui3-widget-positioned{
position: absolute!important;
left: 16% !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="partListDiv" class="part-list-div" >
	<div id="partListGridDiv">
		<div id="gridCCVPartsList" class="width-100per"></div>
		<div id="loadingPartsList" class='gridLoading'>
			<br><!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
		</div>
		<div id="pagingAreaPartList"></div>
	</div>
	<div id="partListLoadingDiv" style="display:none;">
		<img src ='<html:imagesPath/>loading-icon.gif'>&nbsp;&nbsp;<spring:message code='claim.message.waitingForRetrievePart'/>
	</div>
</div>
<script type="text/javascript">
partListPopUpWindow.titleNode.html("<spring:message code='claim.label.partsList'/><div style=\"float:right!important\" class=\"close-btn\"><a href=\"javascript:closePopup();\"><img class=\"ui-icon ui-icon-closethick\" src=\"<html:imagesPath/>transparent.png\" width=\"27\" height=\"27\"></a></div>");
	var partsListGrid = new dhtmlXGridObject('gridCCVPartsList');
	partsListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	partsListGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.partsList"/>',4));
	partsListGrid.setInitWidths("150,500,0,150");
	partsListGrid.attachHeader("#text_filter,#text_filter,&nbsp;,&nbsp;");
	partsListGrid.setColAlign("left,left,left,left");
	partsListGrid.setColTypes("ro,ro,ro,ro");
	partsListGrid.setColSorting("str,str,na,na");
	partsListGrid.enableAutoWidth(true);
	partsListGrid.enableAutoHeight(true);
	partsListGrid.init();
	partsListGrid.enablePaging(true, 10, 5, "pagingAreaPartList", true);
	partsListGrid.setPagingSkin("bricks");			 
	partsListGrid.setSkin("light");
	partsListGrid.enableResizing("false,false,false,false");
	partsListGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
	partsListGrid.loadXML("${showPartList}&modelNumber="+modelNumber);
	partsListGrid.attachEvent("onXLS", function() {
		document.getElementById('loadingPartsList').style.display = 'block';
	});
	partsListGrid.attachEvent("onXLE", function() {
		if(!document.getElementById('partListDiv')){
			return;
		}
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	    document.getElementById('loadingPartsList').style.display = 'none';
	});
	partsListGrid.attachEvent("onFilterStart", function(indexes,values){
		for(var i=0; i < values.length; i++){
			values[i] = values[i].trim();
		}
		return true;
    });

	function selectRow(id,ele){
		var part = new Array();
		part[0] = partsListGrid.cells(id,0).getValue();
		part[1] = partsListGrid.cells(id,1).getValue();
		part[2] = partsListGrid.cells(id,2).getValue();
		part[3] = null;
		part[4] = id;
		part[5] = document.getElementById("partReplaceNumber_"+id).value;
		window.parent.window.addRowByPartList(part);
		hideOverlayIE6();
	}
	function closePopup(){
		jQuery(window).scrollTop(0);
		partListPopUpWindow.destroy();
		partListFunction();
	}
</script>