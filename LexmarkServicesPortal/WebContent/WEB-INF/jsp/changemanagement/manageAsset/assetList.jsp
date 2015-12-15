<%@page import="javax.portlet.RenderRequest"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ include file="../../common/subTab.jsp"%>
<%@ include file="/WEB-INF/jsp/deviceFinder/favoriteAsset.js.jsp"%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<%@ include file="../../includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/deviceFinder/favoriteAsset.js.jsp"%>
<script type="text/javascript" src='<html:rootPath/>js/tree/dhtmlxtree.js'></script>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<style>
.ie7 .button_cancel {
position: static !important;
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />			
<div id="content-wrapper">
	<div class="journal-content-article">
  <h1><spring:message code="requestInfo.cm.heading.changeRequest"/></h1>
  <h2 class="step"><spring:message code="requestInfo.cm.manageAsset.heading.assets"/></h2>
</div>

<!-- Added for CI7 BRD14-02-12 -->
<div><h3><strong>
      <%@ include file="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"%>
      <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'> 
        		<a href="#" onclick="javascript:showChangeAccountPopup();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
      </c:if> 
        	</strong>
        	</h3>
        	</div>
        	<!-- END -->
        	
    <div class="main-wrap">
      <div class="content">
          <!-- MAIN CONTENT GOES HERE -->
		  
		  <%--Error message to be shown in error div while trying to retrieve asset list from siebel --%> 
		  <div class="error" style="display: none;" id="errorDiv"> 
				<strong><spring:message code="exception.unableToRetrieveDeviceDetail"/></strong>
     	  </div>
     	<%-- End --%>
     	
     	<%--Section for multiple assets/template based sr's --%>	
		  <div class="portlet-wrap rounded shadow">
			
				<div class="columnsTwo w70p">
					<div class="columnInner">
						  <p class="info add"><spring:message code="requestInfo.cm.manageAsset.heading.toChangeAsingleAsset"/> <br />
							<spring:message code="requestInfo.cm.manageAsset.heading.orSelectAlink"/> </p>
						  <p>
							<button class="button" type="button" onclick="javascript:forwardToAddAssetJSP('addone');"><spring:message code="button.addANewAsset"/></button>
						  </p>
				</div>
					
					
					
				</div>
				<div class="columnsTwo splitter w30p">				
					<div class="columnInner">
					<h4><spring:message code="requestInfo.cm.manageAsset.heading.manageMultipleAssets"/></h4>
					<ul class="link">
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Add_Assets','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAsset.link.addMultipleAssets"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Change_Assets','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAsset.link.changeMultipleAssets"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Decommission_Assets','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAsset.link.decommissionMultipleAssets"/></a></label>
						</li>
						<li>
							<label><a href="javascript:forwardToTemplateSRpage('Swap_Assets','${serviceRequestNumber}');"><spring:message code="requestInfo.cm.manageAsset.link.swapAssets"/></a></label>
						</li>
					</ul>
					</div>
				</div>
			
		  </div>
		  <%--End of the section --%>
		  
		  <%--Section for asset list div--%>
          <h3 class="pageTitle"><spring:message code="requestInfo.cm.manageAsset.heading.assetList"/></h3>
          <div class="grid-controls"> 
            <!-- Utilities Start --> 
            <!-- utilities End --> 
            <!-- Expand-min Start -->
            <div class="filterLinks">
            <ul>
              <li class="first"> <span id="allAsset"><spring:message code="requestInfo.cm.manageAsset.link.allAssets"/></span></li>
              <li><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite bookmark-star-icon" width="15" height="15">
			<span id="favBookmark">
			<a id= "linkBookmarked" href="javascript:void(0);" onClick="reloadGrid('CATEGORY', 'BOOKMARKED')"><spring:message code="requestInfo.link.myBookmarkedAssets"/></a>
			</span></li>
            </ul>
          </div>
           
			
            
            <div class="expand-min">
              <ul>
              <li class="first"><img class="ui_icon_sprite minimize-icon" src="<html:imagesPath/>transparent.png" alt="Minimize All"
							title="<spring:message code="requestInfo.link.minimizeAll"/>" height="25" width="25" /><a href="javascript:void(0)"
							onClick="minimizeAll()"><spring:message code="requestInfo.link.minimizeAll"/></a></li>
						<li><img class="ui_icon_sprite expand-icon" src="<html:imagesPath/>transparent.png"
							alt="<spring:message code="requestInfo.link.expandAll"/>" title="<spring:message code="requestInfo.link.expandAll"/>"  height="25" width="25" /><a
							href="javascript:void(0)" onClick="expandAll()"><spring:message code="requestInfo.link.expandAll"/></a></li>
						<li><!-- <a href="#grid" id='headerImageButton'> --><img class="ui_icon_sprite customize-icon cursor-pointer" src="<html:imagesPath/>transparent.png"  height="25" width="25" />
						<!-- </a>&nbsp; --><a href="#grid" id='headerMenuButton'><spring:message code="requestInfo.link.customizeColumns"/></a></li>
						<li><img class="ui_icon_sprite reset-icon" src="<html:imagesPath/>transparent.png"
							alt="<spring:message code="requestInfo.link.reset"/>" title="<spring:message code="requestInfo.link.reset"/>" height="25" width="25" /><a
							href="javascript:doReset();"><spring:message code="requestInfo.link.reset"/></a></li>
              </ul>
            </div>
            <!-- Expand-min End --> 
			
          </div>
          <!-- grid-controls -->
          
          <div class="portlet-wrap rounded">
            <div class="portlet-wrap-inner">
              <div id="assetListGrid" class="gridbox gridbox_light"></div>
              	<div id="loadingNotification" class='gridLoading'>
	        	<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    		</div>
            
            <div class="pagination" id="paginationId">
          	<span id="pagingArea"></span><span id="infoArea"></span>          
          	</div>
          	</div>
          </div>
          
          
          <!-- mygrid_container --> 
          <div class="buttonContainer">
            <button id="cancelButton" class="button_cancel" type="button" onclick="javascript:redirectToHistory('ListPage');"><spring:message code="button.cancel"/></button>
          </div>
      </div>
    </div>
  </div>
  <!-- <div id="footer">Footer</div> -->
<%--End of the section --%>
</body>
			

<script type="text/javascript">

var portlet_name ="Asset list"
var action_favoriteAsset ="MADC manage assets favorite asset"
var prevSrNo="${serviceRequestNumber}";
var isUpdateFlag="${isUpdateFlag}";


		var assetId;	
		var deviceListGrid;
		jQuery(document).ready(function(){
			
			$('#grid_menu h4 a').html('');
			
			var currentURL = window.location.href;
			var isError="${exceptn}";
			if(isError)
				{
				jQuery("#errorDiv").show();
				//customizedGridURL=populateURLCriterias();
				//alert("${assetResultList}");
				//deviceListGrid.loadXML("${assetResultList}");
				}
//			Change Account Link Hide/Show for CI-7 Defect #12274
			if(currentURL.indexOf('/partner-portal') == -1)
			  {	
				  jQuery('#changeaccount').show();
			  }
			else
				{
				jQuery('#changeaccount').hide();
				}
		deviceListGrid = new dhtmlXGridObject('assetListGrid');
		
		deviceListGrid.setImagePath("<html:imagesPath/>gridImgs/");
		deviceListGrid.setHeader("&nbsp;,&nbsp;,"+"<spring:message code='deviceFinder.listHeader.assetFields'/>");
		deviceListGrid.attachHeader(",,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
		deviceListGrid.setInitWidths("25,25,30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120");
		deviceListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
		deviceListGrid.setColTypes("ro,ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
		deviceListGrid.setColSorting("na,na,na,str,str,str,str,str,str,str,str,na,na,str,str,str,str,str,str,str,na,na,str,na,na");
		
        deviceListGrid.enableAutoHeight(true);
        deviceListGrid.enableMultiline(true);
       
        deviceListGrid.enableHeaderMenuForButton('headerMenuButton');
        
        deviceListGrid.setSkin("light");
        deviceListGrid.setLockColVisibility("true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
        deviceListGrid.enableColumnMove(true);
        
        deviceListGrid.sortIndex = null;
        deviceListGrid.columnChanged = true;
        
        deviceListGrid.attachEvent("onXLS", function() {
        	document.getElementById('loadingNotification').style.display = 'block'; 
        	document.getElementById('paginationId').style.display = 'none';       	
        	resetValue = false;
        	if(colsWidth==""){
        		resetValue = true;
        	}
            refreshGridSettingOnPage(deviceListGrid);
            
            dBPersistentFlag = false;
            //jQuery("#loadingNotification").show();
            //document.getElementById("loadingNotification").style.display = 'block';
            
        	if(!deviceListGrid.loadHiddenColumns(colsHidden)  && resetValue || gridToBeReset) {
                setDefaultHiddenColumns();
                gridToBeReset = false;
                resetValue = false;
                
            }
            if(deviceListGrid.a_direction=='asc'){
           	 deviceListGrid.setSortImgState(true, deviceListGrid.getDefaltSortImagIndex(deviceListGrid.sortIndex,1), 'asc');
           	
            }else{
           	 deviceListGrid.setSortImgState(true, deviceListGrid.getDefaltSortImagIndex(deviceListGrid.sortIndex,1), 'des');
            }
            //document.getElementById('loadingNotification').style.display = 'block';
    		dBPersistentFlag = true;
        });
        
        deviceListGrid.attachEvent("onXLE", function() {
        	document.getElementById('loadingNotification').style.display = 'none';
        	document.getElementById('paginationId').style.display = 'block';
        	showToolTipList('helpIconList');
        	deviceListGrid.clearSelection();
        	//jQuery("#loadingNotification").hide();
            //document.getElementById("loadingNotification").style.display = 'none';
        	setTimeout(function(){
        		rebrandPagination();
        	
        	},100);
        });
         
        deviceListGrid.init();
        deviceListGrid.prftInit();
        
       //deviceListGrid.enableHeaderMenuForButton('headerMenuButton');
        
        //************************ Grid filer**********************/
      	deviceListGrid.attachEvent("onFilterStart", function(indexes,values){
      	// The value of filtering Criterias can be get from the values by the position
      	// e.g.if values=" ,,,,," than 7 is the filtering value for second columns
      	// Add additional , for the subrow of grid
      	 
          	deviceListGrid.filterValues = ",,,"+values;
          	customizedGridURL = updateGridURL(customizedGridURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
          	<%--//changes for JAN release
          	saveGridQueryParams('assetListGrid',
        	    	generateGridQueryParams(deviceListGrid,null,null,null),--%>
        	    	setGridFilerTimer(reloadData);
        	    	<%--    	);
          	//End JAN release--%>
          	
          });
          
      	
      	deviceListGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
      	deviceListGrid.setPagingSkin("bricks");
      	
      	deviceListGrid.loadOrder(colsOrder);
      	
      	deviceListGrid.loadPagingSorting(colsSorting,3);
      	
      	deviceListGrid.loadSize(colsWidth);
      	
      	//deviceListGrid.filterValues = ",,,,,,,,,,,,,";
      	deviceListGrid.filterValues = ",,,,,,,,,,,,,,,,";
      	
      	deviceListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");

    <%--  //changes for JAN release
      	jQuery.getJSON("${retrieveGridParamsForCM}&gridId=assetListGrid",function(result){
			if(result.status!='blank'){
			      
				
				var filVal=result.filterValues.substring(3,result.filterValues.length);
				deviceListGrid.setFiltersValue(filVal);
				deviceListGrid.filterValues = result.filterValues;
				
	    	}--%>
			customizedGridURL = populateURLCriterias();
			deviceListGrid.loadXML(customizedGridURL);
		<%--});
      //End JAN release--%>



      	
      	
		
				
		
			 
			 
			deviceListGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
				//alert("inside row select");
				if (cellIndex > 1) {
					var cell=deviceListGrid.cellById(rowId,3);
					var row = deviceListGrid.getRowById(rowId);
					if(!row._expanded){
						cell.open();
					}else{
						cell.close();
					}
				}
		    });
			
			
			deviceListGrid.attachEvent("onMouseOver", function(id,ind){
				if(ind!=0&&ind!=1){
					var style = deviceListGrid.cells(id,ind).cell.style;
				    style.cssText += ";cursor: pointer;";
					return true;
				}
		    });
			
			deviceListGrid.attachEvent("onBeforeSorting", customColumnSort);
			deviceListGrid.sortRows = function(col, type, order) {};
			
			deviceListGrid.attachEvent("onPaging", function(count){
				refreshGridSettingOnPage(deviceListGrid);
			});
		});
			function customColumnSort(ind) {
				//callOmnitureAction('Device Finder', 'Device Finder List Sort');
				
				var a_state = deviceListGrid.getSortingState();
				if(a_state[0] == (ind)){
					deviceListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
				}else {
					deviceListGrid.a_direction = "asc" ;
					deviceListGrid.columnChanged = true;
				}
				deviceListGrid.sortIndex = ind;
				
				customizedGridURL = updateGridURL(customizedGridURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
			 	reloadData();
				return true;
			};
			
			function setDefaultHiddenColumns() {
				
				deviceListGrid.setColumnHidden(9,true);
				deviceListGrid.setColumnHidden(11,true);
				deviceListGrid.setColumnHidden(12,true);
				deviceListGrid.setColumnHidden(13,true);
				deviceListGrid.setColumnHidden(14,true);
				deviceListGrid.setColumnHidden(15,true);
				deviceListGrid.setColumnHidden(16,true);
				deviceListGrid.setColumnHidden(17,true);
				deviceListGrid.setColumnHidden(18,true);
				deviceListGrid.setColumnHidden(19,true);
				deviceListGrid.setColumnHidden(20,true);
				deviceListGrid.setColumnHidden(21,true);
				deviceListGrid.setColumnHidden(22,true);
				deviceListGrid.setColumnHidden(23,true);
				deviceListGrid.setColumnHidden(24,true);
			};
			
			function onSelectChange(){
				clearMessage();
				document.getElementById("txtSearchValue").value="";
			};
		   
			function reloadData() {
				clearMessage();
				refreshGridSettingOnPage(deviceListGrid);
				deviceListGrid.clearAndLoad(customizedGridURL);
			};
			
			function doReset(){
				//callOmnitureAction('Device Finder', 'Device Finder List Reset');
				//resetCallback();
				//resetGridSetting('assetListGrid', resetCallback);
				resetGridSettingForDMCM('assetListGrid', resetCallback);
			};

			function resetCallback() {
				clearMessage();
				colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24";
				colsWidth = "px:25,25,30,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
				colsSorting = "3,asc";
				colsHidden = "";
				gridToBeReset = true;
				deviceListGrid.sortIndex = 3;
				dBPersistentFlag = false;
				deviceListGrid.setColumnsVisibility("true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
				deviceListGrid.loadOrder(colsOrder);
				deviceListGrid.loadPagingSorting(colsSorting,3);
				
				deviceListGrid.loadSize(colsWidth);
				customizedGridURL = updateGridURL(customizedGridURL, 3, 'asc', deviceListGrid.filterValues);
				deviceListGrid.clearAndLoad(customizedGridURL);
				/*clearMessage();
				colsOrder = "0,1,2,3,4,5,6,7,8,10";
				colsWidth = "px:30,25,20,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
				colsSorting = "3,asc";
				colsHidden = "";
				gridToBeReset = true;
				deviceListGrid.sortIndex = 1;
				dBPersistentFlag = false;
				deviceListGrid.setColumnsVisibility("true,true,false,false,false,false,false,false,false,false");
				deviceListGrid.loadOrder(colsOrder);
				deviceListGrid.loadPagingSorting(colsSorting,3);
				
				deviceListGrid.loadSize(colsWidth);
				customizedGridURL = updateGridURL(customizedGridURL, 3, 'asc', deviceListGrid.filterValues);
				deviceListGrid.clearAndLoad(customizedGridURL);*/
				//setDefaultHiddenColumns();
			};
			
			function populateURLCriterias(){
				
				
				var url="${assetResultList}";				
				
				if(deviceListGrid.filterValues != null && deviceListGrid.filterValues != "") {
					url = url + "&filterCriterias=" + deviceListGrid.filterValues;
				}
				
			    if(deviceListGrid.a_direction != null && deviceListGrid.a_direction != "") {
			        url = url + "&direction=" + deviceListGrid.a_direction;
			    }
			    
			    url = url + "&orderBy=" + deviceListGrid.getSortColByOffset();
			    
			    return url;
			};

			function minimizeAll() {
				deviceListGrid.forEachRow(function(id){
			   var cell=deviceListGrid.cellById(id,2);
			   cell.close();
			   subRowOpenFlag=false;
			   });
			};

			function expandAll() {
				var state = deviceListGrid.getStateOfView(); //get details about current page

				for (var i = state[1]; i<state[2] ; i++){ //for all rows on the page
				var cell = deviceListGrid.cellByIndex(i,2);
				if (cell.open) cell.open(); //open sub
				}
			};
			
			/* function hideSelect() {
				var selects = document.getElementsByTagName('select');
				for (var i=0; i < selects.length; i++){
					var select = selects[i];
					select.style.visibility = "hidden";
				}
			};

			function showSelect(){
				var selects = document.getElementsByTagName('select');
				for (var i=0; i < selects.length; i++){
					var select = selects[i];
					select.style.visibility = "visible";
				}
			} */
			
	/* function requestService(assetId, requestType){
		
	//alert(assetId);
	//window.location.href="<portlet:renderURL><portlet:param name='action' value='showServiceRequestPage2' /></portlet:renderURL>&assetId="
	//+assetId + "&notMyPrinterFlag=" + "false" + "&machineType="+ "" + "&productTLI=" + "" + "&serialNumber=";
	
} */
	
	function forwardToAddAssetJSP(value)
	{
		//alert(value);
		showOverlay();
		window.location.href="<portlet:renderURL><portlet:param name='action' value='redirectToAddAsset' /></portlet:renderURL>&selectedVal="+value
		+"&prevSrNo=" + prevSrNo+ "&isUpdateFlag=" + isUpdateFlag;
	}
	
	function manageAssetRequestService(requestType, assetId)
	{
		var finalUrl='<portlet:renderURL><portlet:param name="action" value="forwardtoJSP" /></portlet:renderURL>&assetId='+assetId
		+'&requestType='+requestType+"&prevSrNo=" + prevSrNo + "&isUpdateFlag=" + isUpdateFlag;
		
		showOverlay();
		window.location.href=finalUrl;
	}
	
	//This is for the bookmarked assets
	function reloadGrid(type, value) {
		jQuery('#allAsset').html('<a id="linkAllAsset" href="javascript:populateAllAsset();"><spring:message code="requestInfo.cm.manageAsset.link.allAssets"/></a>');
		jQuery('#favBookmark').html('<spring:message code="requestInfo.link.myBookmarkedAssets"/>');
		//alert("reload grid " + value);
		customizedGridURL = updateGridURL(getURLWithViewType(value), deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
		deviceListGrid.clearAndLoad(customizedGridURL);
		
		}
	function populateAllAsset(){
		//alert('in pop');
		jQuery('#favBookmark').html('<a id= "linkBookmarked" href="javascript:void(0);" onClick="reloadGrid(\'CATEGORY\', \'BOOKMARKED\')"><spring:message code="requestInfo.link.myBookmarkedAssets"/></a>');
		jQuery('#allAsset').html('<spring:message code="requestInfo.cm.manageAsset.link.allAssets"/>');
		//alert('after here');
		customizedGridURL = updateGridURL(getURLWithViewType(''), deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
		//deviceListGrid.clearAndLoad("${assetResultList}");
		
		deviceListGrid.clearAndLoad(customizedGridURL);
	}
	//This url hits the commonController
	function getURLWithViewType(value) {
		
		var url = "${assetResultList}" + "&view=" + value;
		
		//alert("URL for bookmark "+url);
		return url;
		}
	
	image_error= function (MyPicture) {
		   document.getElementById(MyPicture).src = '<html:imagesPath/>dummy_noimg.jpg'; 
		};
		
		/* Added for CI7 BRD14-02-12 */
		ajaxSuccessFunction=function updateRequest(){
	 		window.location.href="<portlet:actionURL><portlet:param name='action' value='showAssetPage'/></portlet:actionURL>&friendlyURL="+populateURL("manageassets",[],[]);					
	 }
		/* END */
	</script>


