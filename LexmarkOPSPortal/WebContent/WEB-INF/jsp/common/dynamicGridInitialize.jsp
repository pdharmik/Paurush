<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>

<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<portlet:resourceURL var="saveCustomizedGridSettingURL" id="saveCustomizedGridSetting"/>

<script type="text/javascript">
/*
 *@Author wipro 
 *this is common functionality  
 * used in all history grids
 */
var xmlURL,JSON_Param={},xmlURLQueryParams,isGridLoading,pagingArea="pagingArea",infoArea="infoArea",headerMenuButton="headerMenuButton",loadingNotification="loadingNotification",backFilterValues,requestListGrid;
var pageSize=20,pagesIngrp=10;
function initURLParams(){

	//alert('in dynamic grid init');
		xmlURLQueryParams={
				urlParameters:["startDate","endDate","direction","orderBy","searchFilterCriteria","timezoneOffset","filterCriterias","gridType"],
				setParameters : function(){
										this["startDate"]=jQuery("#localizedBeginDate").val();
										this["endDate"]=jQuery("#localizedEndDate").val();
										this["direction"]=requestListGrid.a_direction;
										this["orderBy"]=requestListGrid.getSortColByOffset();
										this["searchFilterCriteria"]=jQuery('#searchFilterCriteria').val();
										this["timezoneOffset"]=timezoneOffset;
										this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
										this["gridType"]=gridType;
										
									},
				setLoadXMLUrl : function(){
												
												xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
												this.setParameters();
												for(var i=0;i<this.urlParameters.length;i++){
													xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														
												}
												//alert('in setLoadXMLUrl bfore setting tree urls');
												//if(chlQueryParams.isCurrentlyOpened==true)
													xmlURL+=chlQueryParams.getFilterUrl();
												//if(deviceLocationQueryParams.isCurrentlyOpened==true)
													xmlURL+=deviceLocationQueryParams.getFilterUrl();
													
										}
				};
		
			
			
			
		}
	afterXLEfunction=function(){}
	editCellfunction=function(stage,rId,cInd,nValue,oValue){}
	subRowFunction=function(id,state){}
	onMouseOverFunction=function(id,ind){
		if(ind==1){				
			return false;
		}else{
			return true;
			}
		}
	onPageChangedFunction=function(){}
			
	function initialiseGrid(){
		
		
		dhx_globalImgPath = "<html:imagesPath/>gridImgs/";
		requestListGrid = new dhtmlXGridObject(gridCreationId);
		requestListGrid.setImagePath("<html:imagesPath/>gridImgs/");

		
	
		requestListGrid.setHeader(JSON_Param["<%=gridConfigurationValues[0]%>"].toString());
				//header);
		requestListGrid.attachHeader(JSON_Param["<%=gridConfigurationValues[1]%>"].toString());
				//filter);
		requestListGrid.setColAlign(JSON_Param["<%=gridConfigurationValues[2]%>"].toString());
				//colAlign);
		requestListGrid.setInitWidths(JSON_Param["<%=gridConfigurationValues[3]%>"].toString());
				//default_colWidths);
		requestListGrid.setColTypes(JSON_Param["<%=gridConfigurationValues[4]%>"].toString());
				//colType);
		requestListGrid.setColSorting(JSON_Param["<%=gridConfigurationValues[5]%>"].toString());
				//sortTypeCol);
			
				
		lockColVisibility=JSON_Param["<%=gridConfigurationValues[6]%>"].toString();
		defaultColSorting=JSON_Param["<%=gridConfigurationValues[7]%>"].toString();
		defaultColsHidden=JSON_Param["<%=gridConfigurationValues[8]%>"].toString();
		colsOrder=JSON_Param["<%=gridSavingParams[0]%>"].toString();
		colsWidth=JSON_Param["<%=gridSavingParams[1]%>"].toString();
		colsSorting=JSON_Param["<%=gridSavingParams[2]%>"].toString();
		colsHidden= JSON_Param["<%=gridSavingParams[3]%>"].toString();
		
		
		requestListGrid.enableAutoHeight(true);
		if("multiline" in JSON_Param){
			requestListGrid.enableMultiline(JSON_Param["multiline"]);
		}else{
			requestListGrid.enableMultiline(true);
			}
		requestListGrid.setSkin("light");
		requestListGrid.enableColumnMove(true);
		requestListGrid.init();
		requestListGrid.prftInit();

		
		requestListGrid.enableEditEvents(true,false,false);	
		requestListGrid.setLockColVisibility(setLockColVisibilityArray());
		requestListGrid.enablePaging(true, pageSize, pagesIngrp, pagingArea, true, infoArea);
		requestListGrid.setPagingSkin("bricks");
		requestListGrid.sortIndex = null;
		
		//Dynamic combo filters
		var comboFilterIndex=calculateComboFilter();
	
	
		var comboFilterIndexSplit=comboFilterIndex.split(",");
		//alert('1');
	
		var countComboFilter=0;
		for(var i=0;i<comboFilterIndexSplit.length;i++){
			if(comboFilterIndexSplit[i]!=""){
				countComboFilter++;
				requestStatusList=JSON_Param["<%=JSON_COMBO_FILTER%>"+countComboFilter];
				////alert(requestStatusList);
				requestListGrid.setCustomizeCombo(requestStatusList,comboFilterIndexSplit[i]);
				
			}
		}
		//Ends dynamic combo filters
		//alert('2');
		if(colsSorting.length==0){
			requestListGrid.a_direction = requestListGrid.a_direction||"des";	
			colsSorting = defaultColSorting;		
		
			requestListGrid.setSortImgState(true,colsSorting.split(",")[0],requestListGrid.a_direction);
		}else{
			requestListGrid.setSortImgState(true,colsSorting.split(",")[0],colsSorting.split(",")[1]);
		}
		//alert('3');
		requestListGrid.columnChanged = true;
		requestListGrid.enableHeaderMenuForButton(headerMenuButton,headerMenuButton);
		//alert('4');
		dBPersistentFlag = false; 
		
		requestListGrid.loadOrder(colsOrder);
		
		requestListGrid.loadPagingSorting(colsSorting,defaultColSorting.split(",")[0]);
		
		requestListGrid.loadSize(colsWidth);
		
	
		if(!requestListGrid.loadHiddenColumns(colsHidden) && colsWidth=="") {
		
			setDefaultHiddenColumns();
		}
		dBPersistentFlag = true;
		requestListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
		
		requestListGrid.attachEvent("onXLS", function() {
			
			//alert('on xls');
			isGridLoading=true;
			jQuery('#'+pagingArea).hide();
			jQuery('#'+loadingNotification).show();
			if(requestListGrid.a_direction=='asc'){
				requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,defaultColSorting.split(",")[0]), 'asc');
		    }else{
		    	requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,defaultColSorting.split(",")[0]), 'des');
		    }
		});
		requestListGrid.attachEvent("onPageChanged",onPageChangedFunction);
		requestListGrid.attachEvent("onXLE", function() {
			//alert('onXLE');
			isGridLoading=false;
			jQuery('#'+pagingArea).show();
			jQuery('#'+loadingNotification).hide();
				afterXLEfunction();
				setTimeout(function(){
		    		rebrandPagination();
		    	
		    	},100);
		});
		requestListGrid.attachEvent("onBeforeSorting", function(index){
			//alert('on sorting');
			var a_state = requestListGrid.getSortingState();
		
			if(a_state[0] == (index)){
				requestListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
			}else {
				requestListGrid.a_direction = "asc" ;
				requestListGrid.columnChanged = true;
			}
			requestListGrid.sortIndex = index;
		
			if(requestListGrid.a_direction=='asc'){
				requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,defaultColSorting.split(",")[0]), 'asc');
		    }else{
		    	requestListGrid.setSortImgState(true, requestListGrid.getDefaltSortImagIndex(requestListGrid.sortIndex,defaultColSorting.split(",")[0]), 'des');
		    }		
			reloadGrid();	
			return true;
		});
		
		requestListGrid.attachEvent("onFilterStart", function(indexes,values){
			//alert('onFilter');
			requestListGrid.filterValues = values;
			setGridFilerTimer(reloadGrid);	
		});
	
		requestListGrid.attachEvent("onPaging", function(count){
			//saveGridQueryParams("gridOrderRequestList",requestsQueryOperator.getNeedSavedKeyValues(),function(){
			//alert('on paginng');
				refreshGridSettingOnPage(requestListGrid);
			//});		
		});
		requestListGrid.attachEvent("onMouseOver", onMouseOverFunction);
		requestListGrid.attachEvent("onSubRowOpen", subRowFunction);
		requestListGrid.attachEvent("onEditCell",editCellfunction);
		
		/*
		Below section is  
		back from Details page*/
		
		if(backFilterValues!=null && backFilterValues!=""){
			//alert(backFilterValues);
			requestListGrid.filterValues = backFilterValues;
			requestListGrid.setFiltersValue(backFilterValues);
		}
		initURLParams();
		
		xmlURLQueryParams.setLoadXMLUrl();
	
		requestListGrid.loadXML(xmlURL);
	}

	function calculateComboFilter(){
			var filterTypes=JSON_Param["<%=gridConfigurationValues[1]%>"];
			var filterTypesSplit=filterTypes.split(",");
			var comboFilterIndexes="";
			for(var i=0;i<filterTypesSplit.length;i++)
				if(filterTypesSplit[i]=='#combo_filter')
					comboFilterIndexes=comboFilterIndexes+i+",";
			return comboFilterIndexes;
			
		}
	
	function reloadGrid(){
		clearMessage();
		refreshGridSettingOnPage(requestListGrid);
		
		//alert('b4 calling set load xml url');
			xmlURLQueryParams.setLoadXMLUrl();
			saveGridQueryParams(gridCreationId,
					generateGridQueryParams(requestListGrid,
							xmlURLQueryParams.startDate,
							xmlURLQueryParams.endDate,
							xmlURLQueryParams.searchFilterCriteria),function(){});
			//alert(xmlURL);
			requestListGrid.clearAndLoad(xmlURL);
		//});
	};
	function getIndexOfSub_row(){
		var col_type=JSON_Param["<%=gridConfigurationValues[4]%>"].toString();
		var splitCol_type=col_type.split(',');
		var indexOfSub_row=0;
		for(var i=0;i<splitCol_type.length;i++){
			if(splitCol_type[i]=='sub_row'){
				indexOfSub_row=i;
				break;
			}
		}
		return indexOfSub_row;
	}
	function minimizeAll() {
		
				
		var pageSize = requestListGrid.rowsBufferOutSize;
		var begin = (requestListGrid.currentPage-1) * pageSize;
		var end =  requestListGrid.currentPage * pageSize - 1;
		var index_sub_row=getIndexOfSub_row();
		requestListGrid.forEachRow(function(id){
			if(id >= begin && id <= end)
				requestListGrid.cellById(id,index_sub_row).close();
		});
	};
	
	function expandAll() {
		
		var pageSize = requestListGrid.rowsBufferOutSize;
		var begin = (requestListGrid.currentPage-1) * pageSize;
		var end =  requestListGrid.currentPage * pageSize - 1;
		var index_sub_row=getIndexOfSub_row();
		requestListGrid.forEachRow(function(id){
			if(id >= begin && id <= end)
				requestListGrid.cellById(id,index_sub_row).open();
		});
	};
	
	function setDefaultHiddenColumns(){
		//alert('setDefaultHiddenColumns');
		if(defaultColsHidden==""){
			return;
		}
		var split_defaultColsHidden=defaultColsHidden.split(",");
		//alert(split_defaultColsHidden);
		for(var i=0;i<split_defaultColsHidden.length;i++){
			//alert(split_defaultColsHidden[i]);
			requestListGrid.setColumnHidden(split_defaultColsHidden[i],true);
			}
		
	
	};
	
	function doReset(){
		
		resetGridSetting(gridCreationId, resetCallback);  
	};
	
	function resetCallback() {
	    dBPersistentFlag = false;
	    clearMessage();
	    colsOrder = setDefaultColsOrder();
	    colsWidth = "px:"+JSON_Param["<%=gridConfigurationValues[3]%>"].toString();
	    
	    colsSorting = defaultColSorting;
	    colsHidden = defaultColsHidden;
	    requestListGrid.sortIndex = defaultColSorting.split(",")[0];
	    gridToBeReset = true;
	    requestListGrid.loadOrder(colsOrder);
	   
	    requestListGrid.loadPagingSorting(colsSorting,defaultColSorting.split(",")[0]);
	    
	    requestListGrid.setSortImgState(true,defaultColSorting.split(",")[0],requestListGrid.a_direction);

	    requestListGrid.loadSize(colsWidth);
	    requestListGrid.setColumnsVisibility(setColumnVisibilityString());
		setDefaultHiddenColumns();
		requestListGrid.clearFiltersValue();
		dBPersistentFlag = true;
		var hasCombo=false;
		for (var i=0;i < requestListGrid.filters.length;i++){
			if(requestListGrid.filters[i][0].combo){
				hasCombo=true;
				break;
			}
		}
		if(!hasCombo)
			reloadGrid();	
	}
	
	

	function setDefaultColsOrder(){
		var defaultColsOrder=new Array(requestListGrid.getColumnsNum());
		for(var i=0;i<defaultColsOrder.length;i++)
			defaultColsOrder[i]=i;
		return defaultColsOrder.toString();

		
		}

	function setColumnVisibilityString(){


		   var colVisibilityArray=new Array(requestListGrid.getColumnsNum());
		   var split_defaultColsHidden=defaultColsHidden.split(",");
		   for(var i=0;i<colVisibilityArray.length;i++)
			   colVisibilityArray[i]="false";
		   for(var i=0;i<split_defaultColsHidden.length;i++)
			   colVisibilityArray[split_defaultColsHidden[i]]="true";
		   return colVisibilityArray.toString();
			   
		}
	function setLockColVisibilityArray(){
				
				var lockColVisibilityArray=new Array(requestListGrid.getColumnsNum());
				
				var split_lockColVisibility=lockColVisibility.split(",");
				 for(var i=0;i<lockColVisibilityArray.length;i++)
					 lockColVisibilityArray[i]="false";
				
				 for(var i=0;i<split_lockColVisibility.length;i++)
					 lockColVisibilityArray[split_lockColVisibility[i]]="true";
				
				 return lockColVisibilityArray.toString();
				 
				 
		}
	function setMinimizeAllAndExpandAllHidden(){
		var col_type=JSON_Param["<%=gridConfigurationValues[4]%>"].toString();
			if(col_type.indexOf("sub_row")<0)
				jQuery('#expand-All,#minimize-All').hide();
			else
				jQuery('#expand-All,#minimize-All').show();
		}	
	

	

	
	
</script>