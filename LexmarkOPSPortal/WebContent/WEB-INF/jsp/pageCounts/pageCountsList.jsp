<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@ include file="/WEB-INF/jsp/favoriteAsset.js.jsp"%>
<%@ page import="com.lexmark.util.DateLocalizationUtil" language="java"%>
<%@ page import="java.util.Date" language="java"%>
<%@ page import="java.util.Locale" language="java"%>
<%@page import="jsx3.gui.Alerts"%>
<script type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/validation.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/grid/ext/dhtmlxgrid_srnd.js'></script>
<script type="text/javascript" src='<html:rootPath/>js/i18n/jquery-ui-i18n.js'></script>
<!--[if IE 8]>
	
	<style>.relativeI{position:relative!important;}</style>
<![endif]-->
<!--Added by Supriyo - start CI 13.10.16-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--Added by Supriyo - end-->

<style>
.grid-inner-body tr td{vertical-align:top!important;}
.grid-inner-body tr td ul{margin:0px!important;padding:0px!important;}
.dhx_sub_row{height:180px!important;}
div.gridbox_light table.obj td img{margin-left:0px;}
.ie7 div.gridbox_light table.obj td img{position:absolute;margin-left:0px;}
div.gridbox .objbox table.obj td input{width:63px !important } 

.ie .gridbox .button, .ie .gridbox .button:focus {
	background: url(/LexmarkOPSPortal/images/buttonBg.jpg) #0076a3 repeat-x 0px 0px;
	border-radius: .4em;
	behavior: url(/LexmarkOPSPortal/WEB-INF/css/PIE.htc);
}
.cal_date {
	margin-left: 20px!important;
}
.rc_calendar{
	z-index:9999999999!important;
	top:200px!important;
	
}
#dialog_meterReads{position:relative!important;}
</style>

<!-- Added for defect no. 8321 -->
<div id="content-wrapper">
    <div class="journal-content-article" >
      <h1><spring:message code="meterRead.title.pageCounts"/></h1>
      <h2 class="step"><spring:message code="meterRead.label.onlineUpdates"/></h2>
     </div>
     <div>
				<h3 style="margin-left:15px"><strong><spring:message code="meterRead.label.note"/></strong></h3>
				</div>
				
     <!-- END -->
<div class="main-wrap">
	<div class="content">
	<!--Added by Supriyo - start CI 13.10.16-->
		<jsp:include page="/WEB-INF/jsp/pageCounts/pageCountsLeft.jsp" />
		<!--Added by Supriyo - end-->
		<div id="errorDiv1" class="error" style="display:none;"></div>
    	<div class="right-column">
					<div class="grid-controls">
						<div class="utilities">
							<ul>
        						<li class="first"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite spreadsheet-icon" onClick="return download('csv')"; style="cursor:pointer"; title="<spring:message code='image.title.downloadCSV'/>";/></li>
								<li><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite pdf-icon" onClick="return download('pdf')"; style="cursor:pointer"; title="<spring:message code='image.title.downloadPDF'/>";/></li>
								<li><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" onClick="return print()"; style="cursor:pointer"; title="<spring:message code='image.title.print'/>";/></li>
        			</div><!-- utilities -->
						<div class="expand-min">
							<ul>
								<li class="first">
									<img class="ui_icon_sprite minimize-icon" src="<html:imagesPath/>/transparent.png" onClick="minimizeAll()"; style="cursor:pointer;" />
	       		 				&nbsp;<a href="javascript:void(0)" onClick="minimizeAll()"; ><spring:message code="deviceFinder.label.minimizeAll"/></a>
								</li>
								<li>
									<img class="ui_icon_sprite expand-icon" src="<html:imagesPath/>/transparent.png" onClick="expandAll()"; style="cursor:pointer;" />
           			 			&nbsp;<a href="javascript:void(0)" onClick="expandAll()"; ><spring:message code="deviceFinder.label.expandAll"/></a>
								</li>
								

								
								<li>
									<img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite customize-icon"  style="cursor:pointer"; id='headerImageButton'/>
									&nbsp;
									<a href="#grid" id='headerMenuButton'><spring:message code="serviceRequest.label.customize"/></a>
								</li>
								
								<li>
									<img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite reset-icon" alt="<spring:message code="serviceRequest.label.reset"/>" onclick="javascript:doReset();" style="cursor:pointer"  /> 
									<a href="javascript:doReset();" id="resetGridSetting" ><spring:message code="serviceRequest.label.reset"/></a>
								</li>
							</ul>
						</div><!-- expand-min -->  
				<div style="clear: right"></div><!-- clear -->
					</div><!-- grid-controls -->
			<div class="portlet-wrap">
    			<div class="portlet-wrap-inner">
      			<div id="divMeterReadContainer" class="gridbox gridbox_light"></div>
	        	<div id="loadingNotification" class='gridLoading'>
	    			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	   			</div>
				<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
        		</div><!-- portlet-wrap-inner -->	
				<div class="portlet-footer">
					<div class="portlet-footer-inner">
					</div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div><!-- portlet-wrap -->
		</div><!-- right-column -->
		<div class="clear"></div><!-- clear -->
	</div><!-- content -->
</div><!-- main-wrap -->
</div>


<!-- brmandal added the below for Page count changes - MPS phase 2.1 -->
<div id="dialog_meterReads" style="display: none">
<jsp:include page="/WEB-INF/jsp/pageCounts/pageCountPopUp.jsp"/>
</div>
<!-- brmandal ended the below for Page count changes - MPS phase 2.1 -->
<div id="pageCountWarning" style="display: none;"></div>

  
<script type="text/javascript">
	//brmandal modified some of the parameters for the Page count changes -- MPS phase 2.1
    var serverTodayStr = "${serverTodayStr}";
    
	meterReadGrid = new dhtmlXGridObject('divMeterReadContainer');
	meterReadGrid.setImagePath("<html:imagesPath/>gridImgs/");
	meterReadGrid.setHeader(autoAppendPlaceHolder("<spring:message code='meterRead.listHeader.deviceFields'/>",28));
	meterReadGrid.attachHeader(",#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	meterReadGrid.setColAlign("left,left,left,left,left,left,left,left,center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	meterReadGrid.setInitWidths("30,90,90,80,80,90,100,160,80,80,80,80,90,60,60,60,60,60,90,90,90,90,90,90,90,90,90,90");
	meterReadGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	meterReadGrid.setColSorting("na,str,str,str,str,str,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,na,str");
    meterReadGrid.enableAutoHeight(true);
    meterReadGrid.enableMultiline(true);
	meterReadGrid.setSkin("light");
	meterReadGrid.setLockColVisibility("true,false,false,false,false,false,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
	meterReadGrid.enableColumnMove(true,"false,true,true,true,true,true,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true");
	meterReadGrid.init();
	meterReadGrid.prftInit();
	meterReadGrid.enableHeaderMenuForButton('headerMenuButton');
	meterReadGrid.enableHeaderMenuForButton('headerImageButton');
	
	var isColorCapableFlag = false;
    var pageName =  "BookMarked";
    var pageSize = 20;
    var isUpdating = false;
    var inputId; 
    var satrPosition;
    meterReadGrid.sortIndex=null;
    meterReadGrid.columnChanged = true;

    meterReadGrid.setColumnId(6,"LastReadDate");
    meterReadGrid.setColumnId(7,"ReadDate");
    meterReadGrid.setColumnId(8,"LastPage");
    meterReadGrid.setColumnId(9,"NewPage");
    meterReadGrid.setColumnId(10,"LastColor");
    meterReadGrid.setColumnId(11,"NewColor");
    meterReadGrid.setColumnId(12,"ButonSave");
    
    meterReadGrid.attachEvent("onBeforeCMove", function(cInd,posInd){
    	  var toColumnId = meterReadGrid.getColumnId(posInd);
    	  if(toColumnId=='LastReadDate'||toColumnId=='ReadDate'||toColumnId=='LastPage'||toColumnId=='NewPage'
    		  ||toColumnId=='LastColor'||toColumnId=='NewColor'||toColumnId=='ButonSave'){
        	  return false;
          }
		  return true;
    });

    meterReadGrid.attachEvent("onXLS", function() {
    	dBPersistentFlag = false;
        document.getElementById('loadingNotification').style.display = 'block';
        if(!meterReadGrid.loadHiddenColumns(colsHidden) && colsWidth =="" || gridToBeReset) {
			setDefaultHiddenColumns();
			gridToBeReset = false;
        }
        if(meterReadGrid.a_direction=='asc'){
            meterReadGrid.setSortImgState(true, meterReadGrid.getDefaltSortImagIndex(meterReadGrid.sortIndex,1), 'asc');
        }else{
            meterReadGrid.setSortImgState(true, meterReadGrid.getDefaltSortImagIndex(meterReadGrid.sortIndex,1), 'des');
        }
        dBPersistentFlag = true;
    });

    var onPageChangedEventId = null;
    meterReadGrid.attachEvent("onXLE", function() {
        document.getElementById('loadingNotification').style.display = 'none';
        meterReadGrid.forEachRow(function(id){
            //document.getElementById("localizedReadDate" + id).value = localizeDate(document.getElementById("readDate" + id).value);
            if( document.getElementById("localizedReadDate" + id)!=null){
                document.getElementById("localizedReadDate" + id).value = localizeDate(serverTodayStr);
                reloadDateAction('localizedReadDate' + id, document.getElementById("readDate" + id).value, 'PREV');
                reloadDateAction('localizedReadDate' + id, serverTodayStr, 'NEXT');
            }
	        isColorCapable(id);
	        if(!isColorCapableFlag){ 
	            meterReadGrid.setCellExcellType(id,10,"ro"); 
	        }
        }); // foreach over 
       	localizeSaveButton();
       	setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });

    // attach event onPageChanged
    meterReadGrid.attachEvent("onPageChanged", function(ind,fInd,lInd){
        var currentPage = meterReadGrid.currentPage;
        var recordNum = meterReadGrid.getRowsNum();
        for(i=0;i<pageSize && (currentPage-1)*pageSize+i < recordNum;i++){
            id = ((currentPage-1)*pageSize+i).toString();
            if( document.getElementById("localizedReadDate" + id)!=null){
            	document.getElementById("localizedReadDate" + id).value = localizeDate(serverTodayStr);
            if (document.getElementById("readDate" + id).value != "") {
                reloadDateAction('localizedReadDate' + id, document.getElementById("readDate" + id).value, 'PREV');
            }
            reloadDateAction('localizedReadDate' + id, serverTodayStr, 'NEXT');
         }
        }
    });
    
	//************************ Grid filer************************//
	meterReadGrid.attachEvent("onFilterStart", function(indexes,values){
		/*var outPutvalue=",";
		var temp = values.toString().split(",");
		for(i=0;i<temp.length;i++){
			outPutvalue = outPutvalue+(temp[i]+",");
			if(i==4){
				outPutvalue =outPutvalue+",,,,,,";
			}
		}*/	
		meterReadGrid.filterValues = values; 
			//outPutvalue.substring(0,(outPutvalue.length-1));
		customizedGridURL = updateGridURL(customizedGridURL, meterReadGrid.getSortColByOffset(), meterReadGrid.a_direction, meterReadGrid.filterValues);
    	setGridFilerTimer(reloadGrid);
    });
	meterReadGrid.attachEvent("onMouseOver", function(rowId, cellIndex) {});
	meterReadGrid.filterValues =",,,,,,,,,,,,,,,,,,,"; 
		//",,,,,,,,,,,,,,,,,,,,,,,";
    meterReadGrid.loadOrder(colsOrder);
    meterReadGrid.loadPagingSorting(colsSorting,1);
    meterReadGrid.loadSize(colsWidth);
	customizedGridURL = populateURLCriterias();
	meterReadGrid.loadXML(customizedGridURL);
	meterReadGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");	
	meterReadGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
		if (window.navigator.userAgent.indexOf("MSIE")>=1&&inputId!=null)
		     moveFocus(inputId);
	});		
	meterReadGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	meterReadGrid.setPagingSkin("bricks");
	//*********************** Overwrite Large data soring *********// 
	
	// KH:if the grid is dealing with a large set of data, than, sorting should be rewrited.
	meterReadGrid.attachEvent("onBeforeSorting", customColumnSort);
	meterReadGrid.sortRows = function(col, type, order) {}

	meterReadGrid.attachEvent("onPaging", function(count){
		refreshGridSettingOnPage(meterReadGrid);
	});
	meterReadGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
		closeCustomizedWindow();
		
	});
	function customColumnSort(ind) {
		callOmnitureAction('Page Counts', 'Page Count List Sort');
		var a_state = meterReadGrid.getSortingState();
		if(a_state[0] == (ind)){
			meterReadGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
		}else {
			meterReadGrid.a_direction = "asc";
			meterReadGrid.columnChanged = true;
		}
		meterReadGrid.sortIndex = ind;
		customizedGridURL = updateGridURL(customizedGridURL, meterReadGrid.getSortColByOffset(), meterReadGrid.a_direction, meterReadGrid.filterValues);
	    reloadGrid();
	    return true;
	}
	//**************************************************************//
	// colum Search 
	var searchCriteria="";
	function customColumnSearch(columnName,value) {
		callOmnitureAction('Page Counts', 'Page Count List Search');
		if(validateSerchValues(columnName,value)){
		searchCriteria=columnName+"^"+value;
		reloadGrid();
		return true;
		}
	};

   function validateSerchValues(columnName, value){
		if(columnName == "serialNumber")
			if(value.length != 7 && value.length != 11 && value.length != 13){
				showError("<spring:message code='validation.error.invalidSerialNumber'/>");
				return false;
			}
		return true;
   }

   function populateURLCriterias(){
	    var url = "<portlet:resourceURL id="retrievePageCountsList"></portlet:resourceURL>";
		if(searchCriteria != null && searchCriteria != "") {
			url = url + "&searchCriterias=" + searchCriteria;
		}
		if(meterReadGrid.filterIndexes != null && meterReadGrid.filterIndexes != "") {
			url = url + "&filterIndexes=" + meterReadGrid.filterIndexes;
			}
		if(meterReadGrid.filterValues != null && meterReadGrid.filterValues != "") {
			url = url + "&filterCriterias=" + meterReadGrid.filterValues;
		}
       if(meterReadGrid.a_direction != null && meterReadGrid.a_direction != "") {
           url = url + "&direction=" + meterReadGrid.a_direction;
       }
       url = url + "&pageName=" + pageName;
       url = url + "&orderBy=" + meterReadGrid.getSortColByOffset();
	   return url;
	   }

   function setDefaultHiddenColumns(){
	    meterReadGrid.setColumnHidden(12,true);
		meterReadGrid.setColumnHidden(13,true);
		meterReadGrid.setColumnHidden(14,true);
		meterReadGrid.setColumnHidden(15,true);
		meterReadGrid.setColumnHidden(16,true);
		meterReadGrid.setColumnHidden(17,true);
		meterReadGrid.setColumnHidden(18,true);
		meterReadGrid.setColumnHidden(19,true);
		meterReadGrid.setColumnHidden(20,true);
		meterReadGrid.setColumnHidden(21,true);
		meterReadGrid.setColumnHidden(22,true);
		meterReadGrid.setColumnHidden(23,true);
   }
	function reloadGrid() {
		clearMessage();
		refreshGridSettingOnPage(meterReadGrid);
		meterReadGrid.clearAndLoad(customizedGridURL);
	};
	
	function popupAdvancedSearchPage(){
		callOmnitureAction('Page Counts', 'Page Count List Advanced Search');
		closeCustomizedWindow();
			new Liferay.Popup({
		title: "<spring:message code='meterRead.title.advancedFilterOption'/>",
		position: [400,50], 
		modal: true,
		width: 500, 
		height: 600,
		xy: [100, 100],
		onClose: function() {showSelect();},
		url:"<portlet:renderURL
			windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='showMRListAdvancedSearchPage' />" + 
			"</portlet:renderURL>"
		});
	};
	function doReset(){
		callOmnitureAction('Page Counts', 'Page Count List Reset');
		resetGridSetting('divMeterReadContainer', resetCallback); 
	}

    function resetCallback() {
        clearMessage();
        colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25";
        colsWidth = "30,90,90,80,80,90,100,160,80,80,80,80,90,60,60,60,60,60,90,90,90,90,90,90,90,90";
        colsSorting = "1,asc";
        colsHidden = "";
        meterReadGrid.sortIndex = 1;
        dBPersistentFlag = false;
        gridToBeReset = true;
        meterReadGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
        meterReadGrid.loadOrder(colsOrder);
        meterReadGrid.loadPagingSorting(colsSorting,1);
        meterReadGrid.loadSize(colsWidth);
        customizedGridURL = updateGridURL(customizedGridURL, 1, 'asc', meterReadGrid.filterValues);
        meterReadGrid.clearAndLoad(customizedGridURL);
    };
	
	function minimizeAll() {
		callOmnitureAction('Page Counts', 'Page Count List Minimize All');
		meterReadGrid.forEachRow(function(id){
	   		var cell=meterReadGrid.cellById(id,0);
	   		cell.close();
	   	});
	};
	function expandAll() {
		callOmnitureAction('Page Counts', 'Page Count List Expand All');
		var pageSize = meterReadGrid.rowsBufferOutSize;
		var begin = (meterReadGrid.currentPage-1) * pageSize;
		var end =  meterReadGrid.currentPage * pageSize - 1;
			
		meterReadGrid.forEachRow(function(id){
			if(id >= begin && id <= end){
	   			var cell=meterReadGrid.cellById(id,0);
	   			cell.open();
			}
	   	});
	};
	
	function download(type){
		callOmnitureAction('Page Counts', 'Page Count List Download');
		if(meterReadGrid.getRowId(0)==null){
			alert("<spring:message code='meterRead.download.noDataFound'/>");
			return false;
		}
		window.location="${pageCountsListForm.downloadMeterReadURL}&downloadType=" + type;
	};
	
	function isColorCapable(rowId){
			var flag = meterReadGrid.cellByIndex(rowId,10).getValue();
			if(flag=='---'){ isColorCapableFlag=false; }
			else{isColorCapableFlag=true;}
	};
	function focusOnInput(id){
		if (window.navigator.userAgent.indexOf("MSIE")>=1){
		inputId = id;
	    inputBox = document.getElementById(id);
	    inputBox.focus();
        var  range =document.selection.createRange(); 
        range.setEndPoint("StartToStart",inputBox.createTextRange());
        satrPosition = range.text.length;
		}
	}
	function  moveFocus(id){
		inputBox2 = document.getElementById(id);
		inputBox2.focus();
		endPosition = (inputBox2.value.length-satrPosition);
        var s = inputBox2.createTextRange(); 
        s.moveStart("character",satrPosition);
		s.moveEnd("character",-endPosition);
		s.collapse(true);
		s.select();
	}
	function print() {
		callOmnitureAction('Page Counts', 'Page Count List Print');
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='printMeterReadDeviceList' />" + 
			"</portlet:renderURL>";
	    var iWidth=900;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'MeterReadList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};
	
	function validateInput(newPageCount,newColorCount){
		if(isColorCapableFlag&&/^[0-9]+$/.test(newPageCount)&&/^[0-9]+$/.test(newColorCount)){
			    clearMessage();
				return true;
		}else if(!isColorCapableFlag&&/^[0-9]+$/.test(newPageCount)){
			    clearMessage();
				return true;
		}else{
				showError("<spring:message code='validation.error.notNumber'/>");
				return false;
			}
	};

	 function localizeSaveButton(){
		 var buttons = jQuery("input[id^='btn_save']");
			for (i=0; i<buttons.length;i++){
				buttons[i].value="<spring:message code='button.save' />";
			}
	}
	 function enableOrDisableButton(rowId){
		 if(isUpdating){
				document.getElementById("btn_save"+rowId).style.display='';
				document.getElementById("img_update"+rowId).style.display='none';
			}
			else{
				document.getElementById("img_update"+rowId).style.display='';
				document.getElementById("btn_save"+rowId).style.display='none';
		}
		 var buttons = jQuery("input[id^='btn_save']");
			for (i=0; i<buttons.length;i++){
				if(isUpdating){
					buttons[i].disabled="";
				}
				else{
					buttons[i].disabled="disabled";
				}
			}
			isUpdating = !isUpdating;
	}

//************** Meter Read submit validation **************//
	var no_of_day_diff = 30;
	var updateURL = "";
	var tempAssetId="";
	var debugInfo = "";
	var status = "";
	var msg = "";
	var msgColor = "";
	var msgNotUpdate = "";
	var serialNumber = "";

	
	var oldLTPC = "";
	var oldColor = "";
	function updateMeterReadAsset(serialNum,assetId,rowId){	
		if(document.getElementById("#label_ltpcCount"+rowId)){
			oldLTPC = jQuery("#label_ltpcCount"+rowId).text();
		}
		if(document.getElementById("#label_lccCount"+rowId)){
			oldColor = jQuery("#label_lccCount"+rowId).text();
		}
		
		callOmnitureAction('Page Counts', 'Page Count List update asset-' + serialNum);
		clearMessage();
		tempAssetId = assetId; // for warining page use 
		serialNumber = serialNum;
		var selectedId = rowId;
        if(document.getElementById("localizedReadDate"+rowId).value==""){
            alert("<spring:message code='richCalendar.chooseDate'/>");
            return false;
        }
        var newReadDate = formatDateToDefault(document.getElementById("localizedReadDate"+rowId).value);
		isColorCapable(selectedId);
		var LTPCCount = document.getElementById("label_ltpcCount"+rowId).innerHTML;
		var newPageCount = document.getElementById("input_count"+rowId).value;
		if(isColorCapableFlag){
			var lastColorCount = document.getElementById("label_lccCount"+rowId).innerHTML;
			var newColorCount = document.getElementById("input_colorCount"+rowId).value;
		}
		debugInfo =selectedId +" -  "+LTPCCount+" -  "+newPageCount+" -  "+lastColorCount+" -  "+newColorCount;
		if(validateInput(newPageCount,newColorCount)){
			validationChecksForLTPC(LTPCCount,newPageCount, lastColorCount, newColorCount, isColorCapableFlag);
			if(status=='ACCEPTED'){
				updateURL = '<portlet:resourceURL id="updateAssetMeterRead"/>';
				updateURL +="&assetId="+assetId;
				updateURL +="&selectedRowId="+rowId;
				updateURL +="&newPageCount="+newPageCount;
				updateURL +="&newColorPageCount="+newColorCount;
				updateURL +="&newReadDate="+newReadDate;
				updateURL +="&oldPageCount="+oldLTPC;
				updateURL +="&oldColorPageCount="+oldColor;
				//document.getElementById("btn_save"+rowId).disabled="disabled";
				enableOrDisableButton(rowId);
				doAjax(updateURL, function(a){successCallbackUpdateAsset(a.data[0],a.data[1],a.data[2]);},function(b){failCallbackUpdateAsset(b.data[0],b.data[1],b.data[2]);});
			}else{
				validateWarning();
				hideSelect();
			}
		}
		/*
		if (window.PIE) {
			alert('window PIE inside if');
            document.getElementsByClassName("button").fireEvent("onmove");
           // jQuery(".button").trigger("onclick");
        }*/
		
			 
	};

	function failCallbackUpdateAsset(selectedId,isColorCapableFlag,success){
			enableOrDisableButton(selectedId);
			isUpdating = false;
			document.getElementById("input_count"+selectedId).value="";
			if(isColorCapableFlag)
			document.getElementById("input_colorCount"+selectedId).value="";
	};
	
	function successCallbackUpdateAsset(selectedId,isColorCapableFlag,success){
		meterReadGrid.setRowColor(selectedId,"#c8c8c8");
		enableOrDisableButton(selectedId);
		isUpdating = false;
		document.getElementById("label_ltpcCount"+selectedId).innerHTML = document.getElementById("input_count"+selectedId).value;
		document.getElementById("input_count"+selectedId).value="";
		var dateInputId = "localizedReadDate"+selectedId;
		var readDateStr = formatDateToDefault(document.getElementById(dateInputId).value);
		document.getElementById("readDate"+selectedId).value = readDateStr;
		meterReadGrid.cellByIndex(selectedId,6).cell.innerHTML = readDateStr;
		document.getElementById(dateInputId).onmouseup = function() {show_cal(dateInputId, document.getElementById("readDate"+selectedId).value, serverTodayStr);};
		document.getElementById(dateInputId).onfocus = function() {reloadDateAction(dateInputId, readDateStr, 'PREV'); reloadDateAction(dateInputId, serverTodayStr, 'NEXT');};
		document.getElementById("imgLocalizedReadDate"+selectedId).onclick = function() {show_cal(dateInputId, document.getElementById("readDate"+selectedId).value, serverTodayStr);};
		invalidateDateActionLink("localizedReadDate"+selectedId, 'PREV');
		if(isColorCapableFlag){
			document.getElementById("label_lccCount"+selectedId).innerHTML = document.getElementById("input_colorCount"+selectedId).value;
        	document.getElementById("input_colorCount"+selectedId).value="";
	   	 }
	};
	
	function validateWarning(){
		if(status=='DEFERRED'){
			titleStr = "<spring:message code='meterRead.label.meterReadWarning'/>";
		 }else{
			titleStr = "<spring:message code='meterRead.label.meterReadError'/>";
		}		
		
		var url="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='showValidateWarning' /></portlet:renderURL>";
		showOverlay();	
			jQuery('#pageCountWarning').load(url,function(){
				dialog=jQuery('#pageCountWarningPage').dialog({
				autoOpen: false,
				//title: titleStr,
				modal: true,
				draggable: false,
				resizable: false,
				position: 'center',
				height: 'auto',
				width: 940,
				open: function(){	
					jQuery('#pageCountWarningPage').show();
				},
				close: function(event,ui){
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					jQuery('#pageCountWarningPage').remove();
					},
				error: function (jqXHR, textStatus, errorThrown) {
					hideOverlay();
					dialog.dialog('destroy');					 
					dialog=null;
					jQuery('#pageCountWarningPage').remove();
					}
				});
			dialog.dialog('open');
			});	
		
		
		//var iWidth=800;
	   // var iHeight=260;
	   // var iTop = (window.screen.availHeight-240-iHeight)/2;
	  //  var iLeft = (window.screen.availWidth-iWidth)/2;
		//new Liferay.Popup({
		//	title: titleStr,
		//	position: [iLeft,iTop], 
			//modal: true,
			//width: iWidth, 
			//height: iHeight,
			//xy: [iTop, iHeight],
			//onClose: function() {showSelect();},
		//	url:"<portlet:renderURL windowState=''>" + 
			//"<portlet:param name='action' value='showValidateWarning' />" + 
			//"</portlet:renderURL>"
		//	}); 
	};
	
	function validationChecksForLTPC(LTPCCount,newPageCount, lastColorCount, newColorCount, colorFlag){
		if(!colorFlag){
			CheckForLTPCCountDifference(LTPCCount,newPageCount);
		}else{
			CheckForLTPCCountDifference(LTPCCount,newPageCount);
			checkForColorCountDifference(LTPCCount, newPageCount, lastColorCount, newColorCount);
		}
	}

	function CheckForLTPCCountDifference(LTPCCount, newPageCount){
		if(newPageCount==''){
			 msg = "<spring:message code='meterRead.msg.unreasonableLTPCLow'/>";
			 msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			 status = "REJECTED";
		}else if((newPageCount - LTPCCount) < 0){
			msg = "<spring:message code='meterRead.msg.LTPCValueLess'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdated'/>";
			status = "REJECTED";
		}else if((newPageCount - LTPCCount) > 50000){
			msg = "<spring:message code='meterRead.msg.unreasonableLTPCHigh'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			status = "DEFERRED";
		}else if((newPageCount - LTPCCount) < 10){
			msg = "<spring:message code='meterRead.msg.unreasonableLTPCLow'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			status = "DEFERRED";
		}else if((newPageCount - LTPCCount) > (no_of_day_diff * 2000)){
			msg = "<spring:message code='meterRead.msg.unreasonableLTPCHigh'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			status = "DEFERRED";
		}else{
			msg = "";
			status = "ACCEPTED";
		}
	}

	function checkForColorCountDifference(LTPCCount, newPageCount, lastColorCount, newColorCount){
		if(newPageCount == '' || newColorCount == ''){
			msgColor = "<spring:message code='meterRead.msg.colorReadRequired'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdated'/>";
			status = "REJECTED";
		}else if(status == "REJECTED"){
			return;
		}else if((newColorCount - lastColorCount) < 0){
			msgColor = "<spring:message code='meterRead.msg.colorReadLess'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdated'/>";
			status = "REJECTED";
		}else if((newColorCount - lastColorCount) > (newPageCount - LTPCCount)){
			msgColor = "<spring:message code='meterRead.msg.colorReadDifference'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdated'/>";
			status = "REJECTED";
		}else if((newColorCount - lastColorCount) > 50000){
			msgColor = "<spring:message code='meterRead.msg.unreasonableColorReadHigh'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			status = "DEFERRED";
		}else if((newColorCount - lastColorCount) < 10){
			msgColor = "<spring:message code='meterRead.msg.unreasonableColorReadLow'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			status = "DEFERRED";
		}else if((newColorCount - lastColorCount) > (no_of_day_diff * 2000)){
			msgColor = "<spring:message code='meterRead.msg.unreasonableColorReadHigh'/>";
			msgNotUpdate = "<spring:message code='meterRead.msg.notUpdatedDeferred'/>";
			status = "DEFERRED";
		}else{
			msg = "";
			status = "ACCEPTED";
		}
	}

    function gotoControlPanel(url) {
        callOmnitureAction('Page Counts', 'Page Counts List Goto Control Panel');
        closeCustomizedWindow();
        controlPanelPopUpWindow.show();
        controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
        controlPanelPopUpWindow.io.start();
<%--
        new Liferay.Popup({
            title: "",
            position: [400,150], 
            modal: true,
            width: 400, 
            height: 150,
            xy: [100, 100],
            onClose: function() {showSelect();},
            url:"<portlet:renderURL
                windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
                "<portlet:param name='action' value='gotoControlPanel' />" + 
                "</portlet:renderURL>&controlPanelURL=" + url
            });  --%>
    };
	//brmandal added for Page count changes - MPS phase 2.1
	function openPopUp(assetRowId, serialNumber, ipAddress, productLine, assetTag)
	{	
		
					<%-- After parsing ltpc and color values, go for displaying them in popup --%>
					showOverlay();
					dialog=jQuery('#pageCount').dialog({
						autoOpen: false,
						title: "<spring:message code='requestInfo.title.updatePageCounts'/>",					
						modal: true,
						draggable: false,
						resizable: false,
						height: 550,
						width: 780,
						close: function(event,ui){
							hideOverlay();						
							dialog.dialog('destroy');
							dialog=null;
							clearContentsOfpopup1();
							}
						});				
					jQuery('#pageCount').show();
					dialog.dialog('open');	
					/*alert("in openpop up");
					alert(assetRowId);
					alert(serialNumber);
					alert(ipAddress);
					alert(productLine);
					alert(assetTag);*/
					initializePageCountGrid(assetRowId, serialNumber, ipAddress, productLine, assetTag);
					jQuery('#serialNumber').html(serialNumber);
					jQuery('#ipAddress').html(ipAddress);
					jQuery('#productLine').html(productLine);
					jQuery('#assetTag').html(assetTag);
					//alert("assetRowId "+assetRowId);
					//jQuery('#assetIdForPageCounts').html(assetRowId);
					window.document.getElementById("assetRowId").innerHTML=assetRowId;
					//pageCountGrid.loadXML("<portlet:resourceURL id='getPageCountPopUp'/>" + "&assetRowId=" + assetRowId + "&serialNumber=" + serialNumber + "&ipAddress=" + ipAddress + "&productLine=" + productLine +"&assetTag="+ assetTag);
					//hideOverlayPopup();
						
	return false;
	}		


	//brmandal ended for Page count changes - MPS phase 2.1
    
    function hideSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "hidden";
		}      
	}  

	function showSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "visible";
		} 
	}   
	function closeCustomizedWindow(){
		if(document.getElementById('grid_menu')!=null)
		{
	   	  document.getElementById('grid_menu').style.display = 'none';
		}
	   }
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Page Counts List";
     addPortlet(portletName);
     pageTitle="Page Counts List";
</script>
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});


</script>