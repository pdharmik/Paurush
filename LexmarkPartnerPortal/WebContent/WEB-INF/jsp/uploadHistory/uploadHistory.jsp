<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test='${accountSelectionDone eq "true"}'>
<link rel="stylesheet" type="text/css"	href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->
	<div id="content-wrapper" class="div-style29">
	<div>
	<p class="inlineTitle"><spring:message	code="requestInfo.heading.uploadHistory" /></p>
	<a class="floatR anchor-style4"	id="refresh">
		<spring:message	code="requestInfo.uploadHistory.link.refresh" />
	</a>
	</div>
	<div id="uploadHistoryGridContainer" class="gridbox gridbox_light"></div>
	<div id="loadingNotification_uploadHistory" class="gridLoading">
		<spring:message code="label.loadingNotification" />&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
	</div>
	
	<div class="pagination"><span id="pagingArea2"></span><span	id="infoArea2"></span></div>
	<div class="division"></div>
	</div>

	<script>
	var uploadHistoryTypeList=[];
	<c:forEach items="${uploadHistoryType}" var="historyReqType" varStatus = "status" >
		uploadHistoryTypeList[${status.index}] = ["${historyReqType.key}","${historyReqType.value}"];
	</c:forEach>
	<c:set var="bothFlags" value="false"/>
	<c:if test="${sessionScope.accountCurrentDetails['massUploadInstallDebriefFlag'] eq true and sessionScope.accountCurrentDetails['massUploadConsumablesFlag'] eq true}">
	<c:set var="bothFlags" value="true"/> 
    </c:if>
   
var uploadHistoryGrid;

var gridLoadParams;


jQuery(document).ready(function(){

	jQuery('#refresh').click(function(){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.UPLOAD_HISTORY_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.UPLOAD_HISTORY_ACTION_REFRESH%>');
		uploadHistoryGrid.clearAndLoad(gridLoadParams.getLoadXMLUrl());
		});

	uploadHistoryGrid = new dhtmlXGridObject("uploadHistoryGridContainer");
	uploadHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
	uploadHistoryGrid.setHeader("<spring:message code='requestInfo.uploadHistory.griHeader'/>");
	<c:choose>
		<c:when test="${bothFlags}">
		uploadHistoryGrid.attachHeader("#text_filter,,#combo_filter,#text_filter,,,#text_filter");
		</c:when>
		<c:otherwise>
		uploadHistoryGrid.attachHeader("#text_filter,,,#text_filter,,,#text_filter");
		</c:otherwise>
	</c:choose>
	
	uploadHistoryGrid.setInitWidths("150,90,120,120,110,120,250");
	uploadHistoryGrid.setColAlign("left,right,left,center,center,center,left");
	uploadHistoryGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
	uploadHistoryGrid.setColSorting("str,str,str,str,str,str,str");    



	 
	uploadHistoryGrid.enableAutoHeight(true);
	uploadHistoryGrid.enableMultiline(true);
	uploadHistoryGrid.setSkin("light");
	uploadHistoryGrid.init();
	uploadHistoryGrid.prftInit();
	uploadHistoryGrid.enablePaging(true, 20, 10, "pagingArea2", true, "infoArea2");
	uploadHistoryGrid.setPagingSkin("bricks");
	uploadHistoryGrid.a_direction = "des";
	uploadHistoryGrid.sortIndex = 0;
	uploadHistoryGrid.setSortImgState(true, 0, "des");
	<c:choose>
	<c:when test="${bothFlags}">
	uploadHistoryGrid.setCustomizeCombo(uploadHistoryTypeList,2);
	</c:when>
	</c:choose>
	
	setGridURL();
	uploadHistoryGrid.attachEvent("onXLS", function() {
				
		jQuery('#pagingArea2').hide();
		jQuery('#loadingNotification_uploadHistory').show();
		if(uploadHistoryGrid.a_direction=='asc'){
			uploadHistoryGrid.setSortImgState(true, uploadHistoryGrid.getDefaltSortImagIndex(uploadHistoryGrid.sortIndex,0), 'asc');
	    }else{
	    	uploadHistoryGrid.setSortImgState(true, uploadHistoryGrid.getDefaltSortImagIndex(uploadHistoryGrid.sortIndex,0), 'des');
	    }
	});
	
	uploadHistoryGrid.attachEvent("onXLE", function() {
		jQuery('#pagingArea2').show();
		jQuery('#loadingNotification_uploadHistory').hide();
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	
	});
	
	
	uploadHistoryGrid.attachEvent("onFilterStart", function(indexes,values){
			setGridFilerTimer(function(){
				uploadHistoryGrid.filterValues = values;
				uploadHistoryGrid.clearAndLoad(gridLoadParams.getLoadXMLUrl())
				});
		});
	uploadHistoryGrid.attachEvent("onBeforeSorting", function(index){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.UPLOAD_HISTORY_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.UPLOAD_HISTORY_ACTION_SORT%>');
			var a_state = uploadHistoryGrid.getSortingState();
			
			if(a_state[0] == (index)){
				uploadHistoryGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
			}else {
				uploadHistoryGrid.a_direction = "asc" ;
				uploadHistoryGrid.columnChanged = true;
			}
			uploadHistoryGrid.sortIndex = index;
		
			if(uploadHistoryGrid.a_direction=='asc'){
				uploadHistoryGrid.setSortImgState(true, uploadHistoryGrid.getDefaltSortImagIndex(uploadHistoryGrid.sortIndex,0), 'asc');
		    }else{
		    	uploadHistoryGrid.setSortImgState(true, uploadHistoryGrid.getDefaltSortImagIndex(uploadHistoryGrid.sortIndex,0), 'des');
		    }		
			uploadHistoryGrid.clearAndLoad(gridLoadParams.getLoadXMLUrl());
		});

	uploadHistoryGrid.loadXML(gridLoadParams.getLoadXMLUrl());
	
	
});

function setGridURL(){
	
	
	gridLoadParams={
			urlParameters:["direction","orderBy","timezoneOffset","filterCriterias"],
			setParameters : function(){
										this["direction"]=uploadHistoryGrid.a_direction;
										this["orderBy"]=uploadHistoryGrid.getSortColByOffset();
										this["timezoneOffset"]=timezoneOffset;
										this["filterCriterias"]=uploadHistoryGrid.filterValues==null?"":uploadHistoryGrid.filterValues;
										
		
									},
			getLoadXMLUrl : function(){
											//alert(uploadHistoryGrid.getSortColByOffset());
											var uploadHistoryURL="<portlet:resourceURL id="uploadHistoryList"/>";
											this.setParameters();
											for(i=0;i<this.urlParameters.length;i++){
												//alert(this[this.urlParameters[i]]);
												uploadHistoryURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
												}	
											return uploadHistoryURL;
								}
					};
}

function downloadErrorFile(requestNumber,fileName){
	callOmnitureAction('<%=LexmarkPPOmnitureConstants.UPLOAD_HISTORY_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.UPLOAD_HISTORY_ACTION_DOWNLOAD_ERROR%>');
	var errorFileDownloadURL="<portlet:resourceURL id="downloadErrorFile"/>&rNo="+requestNumber+"&fName="+fileName;
	window.location.href=errorFileDownloadURL;
}
/*
 * Thsi method is called
 * when OK is clicked while creating service request 
 */
function confirmRfresh(){
	uploadHistoryGrid.clearAndLoad(gridLoadParams.getLoadXMLUrl())
}

</script>
</c:if>
