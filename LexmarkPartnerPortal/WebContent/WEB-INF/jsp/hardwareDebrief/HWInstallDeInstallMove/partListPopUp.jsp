
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<%-- <script type="text/javascript"
	src="<html:rootPath/>js/jQuery/jquery.min.js">
</script> --%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/grid/dhtmlxgrid_pgn_bricks.css?version=<html:fileCacheVersion/>" />
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_pgn.js?version=<html:fileCacheVersion/>"></script>


<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="partListDiv" style="padding:20px 5px 10px 5px">
	<div id="partListGridDiv">
		<div id="gridCCVPartsList" style="width:100%"></div>
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
	var partsListGrid;
	var selectRowFunction;
	function initialisePartGrid(selectSuccessFunction){
		selectRowFunction=selectSuccessFunction;
		partsListGrid = new dhtmlXGridObject('gridCCVPartsList');
		partsListGrid.setImagePath("<html:imagesPath/>gridImgs/");
		partsListGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.partsList"/>',4));
		partsListGrid.setInitWidths("150,500,0,*");
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
		//alert('model no. 5016-001');
		//partsListGrid.loadXML("${showPartList}&modelNumber="+modelNumber);
		partsListGrid.loadXML('<portlet:resourceURL id="retrievePartList"/>'+ '&modelNumber='+parent.modelNumber);
	
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
	}
	function selectRow(id,ele){
		var rowData={
				partNumber:partsListGrid.cells(id,0).getValue(),
				description:partsListGrid.cells(id,1).getValue()
		};
		selectRowFunction(rowData);
		dialog.dialog('close');			
		dialog=null;
		//searchPart(partsListGrid.cells(id,0).getValue());
	}
</script>