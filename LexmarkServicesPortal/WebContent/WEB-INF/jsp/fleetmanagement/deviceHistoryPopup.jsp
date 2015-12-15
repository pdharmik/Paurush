<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ISPOPUP"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ALL_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SUPPLY_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.CHANGE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SERVICE_REQUESTS"%>
<portlet:resourceURL var="getGridSettingsByAjaxVar" id="getGridSettingsByAjax"></portlet:resourceURL>
<portlet:resourceURL var="viewDeviceChangeHistoryPopup" id="viewDeviceChangeHistoryPopup"></portlet:resourceURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div id="content_history">
<div>
    <ul id="popUpRequestTab" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix  ui-corner-al" >
	     <li id="RequestHistoryTab"  class="ui-state-default ui-corner-top  selectedTab">
	     	<a  onclick="changeTabs('allRequest')"><spring:message code="requestInfo.requestHistory.heading.requestHistory"/></a>
	     </li> 
	     <li id="ChangeRequestTab" class="ui-state-default ui-corner-top tabbed" style="display:block">
	     	<a onclick="changeTabs('changeRequest')" ><spring:message code="requestInfo.requestHistory.heading.changeRequests"/></a>
	     </li>
    </ul>
 </div>
 
<div id="allHistoryTab">
<div class="portlet-wrap">

      <div class="portletBlock bgSolid">
       <div class="portlet-wrap rounded popupInner">
        <div class="expand-min">
              <ul>
              		<li>
						<a href="#grid" id='headerMenuButton'>
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon cursor-pointer"  />
							<spring:message	code="serviceRequest.label.customize" />
						</a>
					</li>
				
					<li>
						<a href="javascript:doReset();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon cursor-pointer"  /></a>
						<a href="javascript:doReset();" id="resetGridSetting">
							<spring:message	code="serviceRequest.label.reset" />
						</a>
					</li>
			 </ul>
            </div>
          <div class="portlet-wrap-inner"><div id="deviceHistoryGrid" class="bg-white"></div></div>
          <div id="loadingNotification" class='gridLoading'>
	        	<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    	  </div>
	    	  <div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
          </div>
        </div>
      </div>
      
      <div id="detailsView" class="portlet-wrap rounded popupInner height-400px"> 
      <div class="journal-content-article div-style50">
      <h1><spring:message	code="requestInfo.heading.requestDetails" /></h1>
</div>
      
      <div id="detailDiv" style="display:none"> <iframe src="" id="detailFrame" class="width-100per iframe-style1" ></iframe> </div></div>
  </div>

</div>


<script>
var pageSize=5,pagesIngrp=4;
initURLParams = function(){
	
	xmlURLQueryParams={
			isFavorite:false,
			urlParameters:["direction","orderBy","filterCriterias","isFavorite","<%=ISPOPUP%>","assetId","gridType","timezoneOffset","fromFleetManager","searchCriterias","cmType"],
			setParameters : function(){
									this["direction"]=requestListGrid.a_direction;
									this["orderBy"]=requestListGrid.getSortColByOffset();
									this["<%=ISPOPUP%>"]=false;
									this["isFavorite"]=this.isFavorite==true?true:false;
									this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
									this["assetId"]=requestHistoryObj.getDeviceId();
									this["gridType"]=requestHistoryObj.getGridType();
									this["timezoneOffset"]=timezoneOffset;
									this["fromFleetManager"]="true";
									this["searchCriterias"]=requestHistoryObj.getWebStatus();
									this["cmType"]=requestHistoryObj.getCMType();
									},
			setLoadXMLUrl : function(){
											xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
											this.setParameters();
											for(var i=0;i<this.urlParameters.length;i++){
												if(this[this.urlParameters[i]]!=null){
													xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
												}
													
											}	
									}
			};
	
		
		
		
	};
	var gridInitParams_All_requests={};
	var gridInitParams_Change_requests={};
	var gridInitParams_Supply_requests={};
	var gridInitParams_Service_requests={};
	var fromFleetManager=true;
	
	
var gridCreationId="";
var requestListGrid;
var gridType="";
gridType="${gridType}";
var originalRequestType;
originalRequestType=gridType;

var requestStatusList = [];		
<c:forEach items="${requestStatusLOVMap}" var="requestStatus" varStatus = "status" >
requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
</c:forEach>


jQuery(document).ready(function(){
	if(originalRequestType!="ALL_REQUESTS"){
		jQuery('#ChangeRequestTab').hide();
		
	}
	setGridInitParams();
	loadDefaultGrid();
	initialiseGrid();
	requestListGrid.attachEvent("onXLE", function() {
		//alert('onXLE');
		isGridLoading=false;
		jQuery('#'+pagingArea).show();
		jQuery('#'+loadingNotification).hide();
		var count=requestListGrid.getRowsNum();
		
		if(count==1)
			{
			
			jQuery('#srNumber').click();
			}
			afterXLEfunction();
			setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
	});
	});
	
	
function getInitGridParamsObj(linkId){
	
	
	
	if(linkId=='<%=ALL_REQUESTS%>')
		return gridInitParams_All_requests;
	else if(linkId=='<%=SUPPLY_REQUESTS%>')
		return gridInitParams_Supply_requests;
	else if(linkId=='<%=CHANGE_REQUESTS%>')
		return gridInitParams_Change_requests;
	else if(linkId=='<%=SERVICE_REQUESTS%>')
		return gridInitParams_Service_requests;
	else 
		return null;	
	
}

function getGridIdFromLinkId(linkId){
	if(linkId=='<%=ALL_REQUESTS%>')
		return "<%=GRID_CREATION_IDS_FOR_HISTORY[0]%>";
	else if(linkId=='<%=SUPPLY_REQUESTS%>')
		return "<%=GRID_CREATION_IDS_FOR_HISTORY[2]%>";
	else if(linkId=='<%=CHANGE_REQUESTS%>')
		return "<%=GRID_CREATION_IDS_FOR_HISTORY[1]%>";
	else if(linkId=='<%=SERVICE_REQUESTS%>')
		return "<%=GRID_CREATION_IDS_FOR_HISTORY[3]%>";
	
}


function loadDefaultGrid(){
	
	if(gridType!=null && gridType!='') {
	//removeAndAddClass(jQuery('#'+gridType));
	gridCreationId=getGridIdFromLinkId(gridType);
	JSON_Param=getInitGridParamsObj(gridType);
	
	JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
	JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
	JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
	JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
	jQuery('#loadingNotification').before('<div id="'+gridCreationId+'" class="gridbox gridbox_light"></div>');

	
	//setMinimizeAllAndExpandAllHidden();
	//setValuesIfBackFromDetails();
	} else {
		jQuery('#loadingNotification').hide();
	}
	
}

function setGridInitParams(){

	
	gridInitParams_All_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='requestInfo.history.allRequests.fields'/>";
	gridInitParams_All_requests["<%=gridConfigurationValues[1]%>"]="#text_filter,,,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
	gridInitParams_All_requests["<%=gridConfigurationValues[2]%>"]="center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
	gridInitParams_All_requests["<%=gridConfigurationValues[3]%>"]="100,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120";
	gridInitParams_All_requests["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
	gridInitParams_All_requests["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str,str,str,str,na,str,na,na,na,na,na,na";
	gridInitParams_All_requests["<%=gridConfigurationValues[6]%>"]="0,1";
	gridInitParams_All_requests["<%=gridConfigurationValues[7]%>"]="0,des";
	gridInitParams_All_requests["<%=gridConfigurationValues[8]%>"]="5,6,7,8,9,10,11,12,13,14,15";
	gridInitParams_All_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
	gridInitParams_All_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveCMHistoryList"/>";
	gridInitParams_All_requests["settingsAlreadyAvailable"]=false;
	
	gridInitParams_Change_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='requestInfo.history.changeRequestHistory.fields'/>";
	gridInitParams_Change_requests["<%=gridConfigurationValues[1]%>"]="#text_filter,&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
	gridInitParams_Change_requests["<%=gridConfigurationValues[2]%>"]="center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
	gridInitParams_Change_requests["<%=gridConfigurationValues[3]%>"]="130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130";
	gridInitParams_Change_requests["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
	gridInitParams_Change_requests["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str,str,str,str,na,str,str,na,str,na,na,str,str,str,str,str,str,na,na,str,na,na,na,na,na,na,na";
	gridInitParams_Change_requests["<%=gridConfigurationValues[6]%>"]="0,1";
	gridInitParams_Change_requests["<%=gridConfigurationValues[7]%>"]="0,des";
	gridInitParams_Change_requests["<%=gridConfigurationValues[8]%>"]="4,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
	gridInitParams_Change_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
	gridInitParams_Change_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveCMHistoryList"/>";
	gridInitParams_Change_requests["settingsAlreadyAvailable"]=false;
	
	gridInitParams_Supply_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='requestInfo.history.supplyRequestHistory.fields'/>";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[1]%>"]=",,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[3]%>"]="80,30,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[4]%>"]="ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,na,str,na,na,str,str,str,str,str,str,na,na,str,na,na,na,str,na,na";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[6]%>"]="0,1,2";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[7]%>"]="2,des";
	gridInitParams_Supply_requests["<%=gridConfigurationValues[8]%>"]="0,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31";
	gridInitParams_Supply_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
	gridInitParams_Supply_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveCMHistoryList"/>";
	gridInitParams_Supply_requests["settingsAlreadyAvailable"]=false;
	
	gridInitParams_Service_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='serviceRequest.listHeader.serviceRequests'/>";
	gridInitParams_Service_requests["<%=gridConfigurationValues[1]%>"]=",#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
	gridInitParams_Service_requests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
	gridInitParams_Service_requests["<%=gridConfigurationValues[3]%>"]="25,120,80,120,140,200,100,100,140,110,110,100,100,100,110,110,110,100,100,100,100,100,100,100,100,100,100";
	gridInitParams_Service_requests["<%=gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
	gridInitParams_Service_requests["<%=gridConfigurationValues[5]%>"]="na,str,str,str,str,na,str,str,str,na,na,str,str,str,str,str,str,str,na,na,str,na,na,na,na,na,str";
	gridInitParams_Service_requests["<%=gridConfigurationValues[6]%>"]="0,1";
	gridInitParams_Service_requests["<%=gridConfigurationValues[7]%>"]="1,des";
	gridInitParams_Service_requests["<%=gridConfigurationValues[8]%>"]="5,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25";
	gridInitParams_Service_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
	gridInitParams_Service_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveBreakfixSRList"/>";
	gridInitParams_Service_requests["settingsAlreadyAvailable"]=false;
	
	
}
function saveGridSettingsAlreadyAvailable(){
	//Save current setting in the object
	//refreshGridSettingOnPage(requestListGrid);
	
	
	if(gridType=='<%=ALL_REQUESTS%>'){
		gridInitParams_All_requests["<%=gridSavingParams[0]%>"]=colsOrder;
		gridInitParams_All_requests["<%=gridSavingParams[1]%>"]=colsWidth;
		gridInitParams_All_requests["<%=gridSavingParams[2]%>"]=colsSorting;
		gridInitParams_All_requests["<%=gridSavingParams[3]%>"]=colsHidden;
		gridInitParams_All_requests["filterValues"]=('filterValues' in requestListGrid)?requestListGrid.filterValues:"";
		
	}else if(gridType=='<%=SUPPLY_REQUESTS%>'){
		gridInitParams_Supply_requests["<%=gridSavingParams[0]%>"]=colsOrder;
		gridInitParams_Supply_requests["<%=gridSavingParams[1]%>"]=colsWidth;
		gridInitParams_Supply_requests["<%=gridSavingParams[2]%>"]=colsSorting;
		gridInitParams_Supply_requests["<%=gridSavingParams[3]%>"]=colsHidden;
		gridInitParams_Supply_requests["filterValues"]=('filterValues' in requestListGrid)?requestListGrid.filterValues:"";
		
		
	}else if(gridType=='<%=CHANGE_REQUESTS%>'){
		gridInitParams_Change_requests["<%=gridSavingParams[0]%>"]=colsOrder;
		gridInitParams_Change_requests["<%=gridSavingParams[1]%>"]=colsWidth;
		gridInitParams_Change_requests["<%=gridSavingParams[2]%>"]=colsSorting;
		gridInitParams_Change_requests["<%=gridSavingParams[3]%>"]=colsHidden;
		gridInitParams_Change_requests["filterValues"]=('filterValues' in requestListGrid)?requestListGrid.filterValues:"";
		
		
	}else if(gridType=='<%=SERVICE_REQUESTS%>'){
		gridInitParams_Service_requests["<%=gridSavingParams[0]%>"]=colsOrder;
		gridInitParams_Service_requests["<%=gridSavingParams[1]%>"]=colsWidth;
		gridInitParams_Service_requests["<%=gridSavingParams[2]%>"]=colsSorting;
		gridInitParams_Service_requests["<%=gridSavingParams[3]%>"]=colsHidden;
		gridInitParams_Service_requests["filterValues"]=('filterValues' in requestListGrid)?requestListGrid.filterValues:"";
		
		
	}
		
		
		
	
	}
function checkAndDestroyGrid(){
	
	saveGridSettingsAlreadyAvailable();
	//Remove the old Div
	jQuery('#'+gridCreationId).remove();
	jQuery('#grid_menu').remove();
	jQuery('#pagingArea,#infoArea').empty();
	
	
		if(requestListGrid!=null){
			requestListGrid.destructor();
			requestListGrid=null;
		}
		
	jQuery('#showAll').attr('checked',true);
	jQuery('#searchFilterCriteria').val('showAll');
	jQuery('#localizedBeginDate,#localizedEndDate').val('');
	
	}
function onSRNmbrClick(serviceRequestNumber, requestType,srStatus,srSubArea,srArea, coveredService){
	
	var gridType="ALL_REQUESTS";
	if(requestType=="Consumables Management" && srStatus=="Draft"){
		if(srSubArea=="Catalog Request"){
			
			// - sourcePage and history added for back button in asset details (MPS Phase 2)
			link= "catalogorder?requestNumber=" + serviceRequestNumber+ "&reqStatus="+srStatus + "&sourcePage="+gridType +"&history="+'history';
			showDetails(link,requestType);
		}else{
			link= "consumableorder?requestNumber=" + serviceRequestNumber+ "&sourcePage="+gridType+"&reqStatus="+srStatus + "&history="+'history';
			showDetails(link,requestType);
		}
	}
	else if(requestType=="Fleet Management" && srSubArea=="BAU" && srStatus=="Draft"){			
		
		link= "hardwareorder?requestNumber=" + serviceRequestNumber+ "&reqStatus="+srStatus;	
		showDetails(link,requestType);
	}
	else if(requestType=="Fleet Management" || requestType=="Consumables Management"){
		<%-- Changes for defect 8083 MPS 2.1--%>
		if((srArea=='HW Order' || srArea=='Hardware-Ship and Install') && srSubArea =='BAU'){
			
			var link = "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='showHardwareRequestDetails' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset +"&requestType=hardware&sourcePage="+gridType+"&srArea="+srArea;
			showDetails(link,requestType);
		
		}else{<%--Ends Changes for defect 8083 MPS 2.1--%>
			
			var link = "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='showRequestDetails' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset +"&requestType="+requestType +"&sourcePage="+gridType+"&srArea="+srArea+"&srSubArea="+srSubArea +"&coveredService="+coveredService;
			showDetails(link,requestType);
		}
	
	
	}else if(requestType=='hardware'){
		if(srStatus=="Draft"){
			
			link= "hardwareorder?requestNumber=" + serviceRequestNumber+ "&reqStatus="+srStatus;
			showDetails(link,requestType);
			}else{
				
				var link = "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='showHardwareRequestDetails' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset +"&requestType="+requestType +"&sourcePage="+gridType+"&srArea="+srArea;
				showDetails(link,requestType);
			}
		
	
	}
	else{
		
		var link = "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='showServiceRequestDrillDownPage' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber+"&sourcePage="+gridType;
		showDetails(link,'service');
	}
	
}


function showDetails(link,requestType){
	
	showOverlayPopup();
	jQuery('#detailFrame').attr("src",link);
	//$(function(){
		var frame=$('#detailFrame');
		jQuery('#detailDiv').show();
		frame.load(function(){ 
	
			if(requestType=="service"){
				frame.contents().find('#serviceUtilityBox').hide();
				frame.contents().find('#serviceButtonContainer').hide();
				hideOverlayPopup();
				
				
			}
			else{
				frame.contents().find('.utilities').hide();
				frame.contents().find('.buttonContainer').hide();
				hideOverlayPopup();
	
			}
			
		});
		//});
	
	
	
	
}
function changeTabs(type){
	
	
	       
	        jQuery('#detailDiv').hide();
	        $('#RequestHistoryTab').addClass("selectedTab");
			$('#ChangeRequestTab').removeClass("selectedTab");
			//alert(type)
			if(type=='changeRequest')
				{ 
				$('#RequestHistoryTab').removeClass("selectedTab");
				$('#ChangeRequestTab').addClass("selectedTab");
				gridType="CHANGE_REQUESTS";
				requestHistoryObj.setGridType(6);
				}
			else if(originalRequestType=="ALL_REQUESTS")
			   {
				gridType="ALL_REQUESTS";
				requestHistoryObj.setGridType("ALL_REQUESTS");
				
			   }
			else if(originalRequestType=="SUPPLY_REQUESTS")
			   {
				gridType="SUPPLY_REQUESTS";
				requestHistoryObj.setGridType(1);
				
			   }
			else if(originalRequestType=="SERVICE_REQUESTS")
			   {
				gridType="SERVICE_REQUESTS";
				
				requestHistoryObj.setGridType(2);
			   }
				checkAndDestroyGrid();
				
					  
				gridCreationId=getGridIdFromLinkId(gridType);
				
				JSON_Param=getInitGridParamsObj(gridType);
				
				if(JSON_Param==null)return;
					               
	            jQuery('#loadingNotification').before('<div id="'+gridCreationId+'" class="gridbox gridbox_light"></div>');
	            
	           
		            showOverlay();
					jQuery.getJSON("${getGridSettingsByAjaxVar}&gridId="+gridCreationId,function(gridSettings){
						
						hideOverlay();
						if(gridSettings.status=='available'){						      
							jQuery.each(gridSettings,function(key,value){
								
								//if(key=='filterValues' && backFromDetails=="true"){
								//	backFilterValues=value;	
								//}
								JSON_Param[key]=value;	
								
								
								
							});
							JSON_Param["settingsAlreadyAvailable"]=true;
							//setMinimizeAllAndExpandAllHidden();
							initialiseGrid();
							requestListGrid.attachEvent("onXLE", function() {
								//alert('onXLE');
								isGridLoading=false;
								jQuery('#'+pagingArea).show();
								jQuery('#'+loadingNotification).hide();
								var count=requestListGrid.getRowsNum();
								if(count==1)
									{
									
									jQuery('#srNumber').click();
									}
									afterXLEfunction();
									setTimeout(function(){
							    		rebrandPagination();
							    	
							    	},100);
							});
							
						}
					});
					
	            
	            
	          
				



}
	
	
	

</script>