<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>

<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ALL_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SUPPLY_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.CHANGE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SERVICE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.HARDWARE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY"%>




<% request.setAttribute("subTabSelected","requestHistory"); %>
<jsp:include page="/WEB-INF/jsp/common/subTab.jsp"></jsp:include>
<portlet:resourceURL var="getGridSettingsByAjaxVar" id="getGridSettingsByAjax"></portlet:resourceURL>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>

<style>#grid_menu{left:725px!important;}</style>
<style>.dhx_sub_row .displayGrid{width:700px!important;}</style>
<!--[if IE 7]>
	
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/ps_ie7.css"/>
<![endif]-->

  <div class="journal-content-article">
      <h1><spring:message code='requestInfo.requestHistory.heading.requestHistory'/></h1>
      <h2 class="step" id="historyTypeName"></h2>
 </div>
    <div class="main-wrap">   
      <div class="content">	
		
		<jsp:include page="leftNav.jsp"></jsp:include>

		<div id="gridPageContainer">
		 <div class="right-column">
          <!-- MAIN CONTENT GOES HERE -->         
          <div class="grid-controls"> 
            <!-- Utilities Start --> 
			<div class="utilities">
	            <ul>
	              <li class="first"><a href="#" onclick="return  downloadCMHistory('csv');"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite spreadsheet-icon"  alt="Export to Excel" title="<spring:message code="requestInfo.tooltip.exporttoexcel"/>"/><spring:message	code="rebranding.label.XLS" /></a></li>
	              <li><a href="#" onclick="return  downloadCMHistory('pdf');"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite pdf-icon"  alt="Export to PDF" title="<spring:message code="requestInfo.tooltip.exporttopdf"/>"/><spring:message	code="rebranding.label.PDF" /></a></li>
	              <li><a href="#" onclick="return print();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite print-icon"  alt="Print" title="<spring:message code="requestInfo.tooltip.print"/>"/><spring:message	code="requestInfo.tooltip.print" /></a></li>
	            </ul>
			</div>
	
            <!-- utilities End --> 
            <!-- Expand-min Start -->
            <div class="expand-min">
              <ul>
              		<li class="first" id="minimize-All" style="display: none;">
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite minimize-icon" onClick="minimizeAll()"; style="cursor:pointer;" />
	       		 			&nbsp;<a href="javascript:void(0)" onClick="minimizeAll()"; ><spring:message code="deviceFinder.label.minimizeAll"/></a>
					</li>
					<li id="expand-All" style="display:none; ">
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite expand-icon" onClick="expandAll()"; style="cursor:pointer;" />
           					&nbsp;<a href="javascript:void(0)" onClick="expandAll()"; ><spring:message code="deviceFinder.label.expandAll"/></a>
					</li>
             		<li>
						<a href="#grid" id='headerMenuButton'>
							<img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite customize-icon" style="cursor: pointer" ; />
							<spring:message	code="serviceRequest.label.customize" />
						</a>
					</li>
				
					<li>
						<a href="javascript:doReset();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite reset-icon" style="cursor: pointer" ; /></a>
						<a href="javascript:doReset();" id="resetGridSetting">
							<spring:message	code="serviceRequest.label.reset" />
						</a>
					</li>
			 </ul>
            </div>
			<!-- Expand-min End --> 
			<div style="clear: right"></div><!-- clear -->
          </div>
		   
          <div class="portlet-wrap rounded">
            <div class="portlet-wrap-inner"> 
               
               
              <div id="loadingNotification" class='gridLoading'>
	        	<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	    	  </div>
	    	  <div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div>
           
            </div>
           
          </div> 
         </div>	
	</div>

	</div>
</div>
<%--
 <div id="allRequestHistory" class="gridbox gridbox_light"></div>
               <div id="changeRequestHistory" class="gridbox gridbox_light"></div>
               <div id="supplyRequestHistory" class="gridbox gridbox_light"></div>
               <div id="serviceRequestListGrid"  class="gridbox gridbox_light"></div> 

 --%>

<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>

<input type="hidden" id="searchFilterCriteria" value="showAll"/>

 <script type="text/javascript">

	var gridInitParams_All_requests={};
	var gridInitParams_Change_requests={};
	var gridInitParams_Supply_requests={};
	var gridInitParams_Service_requests={};
	var gridInitParams_Hardware_requests={};
	

	
	
	var gridCreationId="";
	var requestListGrid;
	var gridType="";
	//The grid Type is sent from controller as the 
	//Default grid to be loaded
	gridType="${gridType}";
	//This status map will be used by all grids
	//This will be set to true if the page is returned from details
	var backFromDetails="${backHistory}";
	var retrieveGridParams="${retrieveGridParamsForCM}";
	
	var requestStatusList = [];	
	var srOPSStatusList = [];
	var srSubStatusList = [];
	<c:forEach items="${requestStatusLOVMap}" var="requestStatus" varStatus = "status" >
	requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
	</c:forEach>
	<c:forEach items="${srOPSStatus}" var="requestStatus" varStatus = "status" >
	srOPSStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
	</c:forEach>
	<c:forEach items="${srSubStatus}" var="requestStatus" varStatus = "status" >
	srSubStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
	</c:forEach>
	

	
	jQuery(document).ready(function(){
		
		setGridInitParams();
		setDates();
		setCheckbox();
		setLinksHiddenForPartnerRequest();
		
		//This grid will be loaded by default according to access
		loadDefaultGrid();
			
		jQuery('#allHistoryLinks li a').each(function(){
				jQuery(this).bind('click',function(){
					
						//This check is for same linked has been clicked again or not
						if(getGridIdFromLinkId(jQuery(this).parent().attr('id'))==gridCreationId && gridCreationId!="")
							return;
						/*if(isGridLoading==true)
							return;*/
						backFilterValues="";
						
						checkAndDestroyGrid();
						gridType=jQuery(this).parent().attr('id');
						removeAndAddClass(jQuery(this).parent());
						
						gridCreationId=getGridIdFromLinkId(gridType);
						
						JSON_Param=getInitGridParamsObj(gridType);
						
						if(JSON_Param==null)return;
							               
			            jQuery('#loadingNotification').before('<div id="'+gridCreationId+'" class="gridbox gridbox_light"></div>');
			            
			            if(JSON_Param["settingsAlreadyAvailable"]==false){
				            showOverlay();
							jQuery.getJSON("${getGridSettingsByAjaxVar}&gridId="+gridCreationId,function(gridSettings){
								//alert(gridSettings);
								hideOverlay();
								if(gridSettings.status=='available'){						      
									jQuery.each(gridSettings,function(key,value){
										
										if(key=='filterValues' && backFromDetails=="true"){
											backFilterValues=value;	
										}
										JSON_Param[key]=value;	
										
										
										
									});
									JSON_Param["settingsAlreadyAvailable"]=true;
									setMinimizeAllAndExpandAllHidden();
									initialiseGrid();
								}
							});
							
			            }else{
				            //alert('in else'+gridType);
			            	//alert('gridSavingParams[3]'+JSON_Param["<%=gridSavingParams[3]%>"]);
			            	//alert('gridConfigurationValues[0]'+JSON_Param["<%=gridConfigurationValues[0]%>"]);
			            	setMinimizeAllAndExpandAllHidden();
			            	backFilterValues=JSON_Param["filterValues"].toString();
			            				            	
							initialiseGrid();
				            }
					
						
					});
			});





		
	});

	function setCheckbox(){
		 	jQuery("input:radio[name=filterByView]").bind('click',function(){
					 	 jQuery('#searchFilterCriteria').val(jQuery(this).val());
					 	reloadGrid();
			 });
		}



	
	function setDates(){
		
		/*jQuery('#imgLocalizedBeginDate,#imgLocalizedEndDate').click(function(){
			
			var beginDate,endDate,textId;
			beginDate=convertDateToString(new Date().addDays(-90));
			endDate=convertDateToString(new Date().addDays(null));
	
			textId=this.id;
			textId='l'+textId.toString().substring(4,textId.length);
			
			show_cal(textId, beginDate, endDate);
			
		});*/
		
		jQuery('#imgLocalizedBeginDate,#imgLocalizedEndDate').click(function(){

			var beginDate,endDate,textId,isBeginDateEmpty,isEndDateEmpty;
			
			textId=this.id;
			textId='l'+textId.toString().substring(4,textId.length);
			endDate=convertDateToString(new Date());
			isBeginDateEmpty = (jQuery("#localizedBeginDate").val()=="");
			isEndDateEmpty = (jQuery("#localizedEndDate").val()=="");
			if((isBeginDateEmpty && isEndDateEmpty)||(jQuery('#'+textId).val()!="" && (isBeginDateEmpty || isEndDateEmpty))){
			beginDate=null;
			}
			else if(!isBeginDateEmpty && !isEndDateEmpty)
			{
				if(textId=="localizedBeginDate")
				{
					beginDate=convertDateToString(new Date(jQuery("#localizedEndDate").val()).addDays(-90));
					endDate=convertDateToString(new Date(beginDate).addDays(90));
				}
				else
				{
					beginDate=convertDateToString(new Date(jQuery("#localizedBeginDate").val()));
					endDate=convertDateToString(new Date(beginDate).addDays(90));
				}
				//beginDate=null;
			}
			else if(!isBeginDateEmpty && isEndDateEmpty){
				beginDate=convertDateToString(new Date(jQuery("#localizedBeginDate").val()));
				endDate=convertDateToString(new Date(beginDate).addDays(90));
				jQuery("#localizedEndDate").val(beginDate);
				}
			else if(!isEndDateEmpty && isBeginDateEmpty){
				beginDate=convertDateToString(new Date(jQuery("#localizedEndDate").val()).addDays(-90));
				endDate=convertDateToString(new Date(beginDate).addDays(90));
				jQuery("#localizedBeginDate").val(endDate);
				}
		
			if((new Date(endDate).getTime()-new Date().getTime())>0)
			{
				endDate=convertDateToString(new Date());
			}		
			//alert(beginDate+" "+endDate);	
			
			show_cal(textId, beginDate, endDate);bindCloseCal();
			
		});
		
		jQuery('#dateFilterBtn').click(function(){

			var fromDate=jQuery("#localizedBeginDate").val();
			var toDate=jQuery("#localizedEndDate").val();
			var deFlag = false;
	    	if(fromDate =="" && toDate==""){
	    		jQuery('#dateErrorDiv').html("<spring:message code='validation.requestHistory.dates.required'/>");
	    		jQuery('#dateErrorDiv').show();
	    		setTimeout(function(){jQuery('#dateErrorDiv').hide();},2000);
	    		return false;
	    	}else if(toDate =="" ){
	    		jQuery('#dateErrorDiv').html("<spring:message code='validation.requestHistory.toDate.required'/>");
	    		jQuery('#dateErrorDiv').show();
	    		setTimeout(function(){jQuery('#dateErrorDiv').hide();},2000);
	    		return false;
	    	}else if(fromDate =="" ){
	    		jQuery('#dateErrorDiv').html("<spring:message code='validation.requestHistory.fromDate.required'/>");
	    		jQuery('#dateErrorDiv').show();
	    		setTimeout(function(){jQuery('#dateErrorDiv').hide();},2000);
	    		return false;
	    	}else {
	    		var startDate;
	    		var endDate;
	    		var today;
	    		var tgtSeperator = dateFormat.substring(2,3);
	    		var today = convertDateFormat("mm/dd/yyyy","/", todayStr, dateFormat, tgtSeperator);
		    	if(fromDate.substring(2,3)=="."){
		    		startDate = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",fromDate, "mm/dd/yyyy","/"));
		    		endDate = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",toDate, "mm/dd/yyyy","/"));
		    		today = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",today, "mm/dd/yyyy","/"));
		    		deFlag =true;
		    	}
		    	
		    	if(!deFlag){
		    	fromDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, fromDate, "mm/dd/yyyy", "/")));
		    	endDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, toDate, "mm/dd/yyyy", "/")));
		    	}
		    	else{
			    	fromDat_converted = startDate;
		    		endDat_converted = endDate;
		    	}
		    	
		    	if(fromDat_converted>endDat_converted)
		    	{
		    		jQuery('#dateErrorDiv').html("<spring:message code='validation.requestHistory.fromDate.invalid'/>");
	    			jQuery('#dateErrorDiv').show();
	    			setTimeout(function(){jQuery('#dateErrorDiv').hide();},2000);
	    		    return false;
		    	}
		    	if(endDat_converted > today){
					
					 jQuery('#dateErrorDiv').html("<spring:message code='validation.requestHistory.toDate.invalid'/>");
	    			 jQuery('#dateErrorDiv').show();
	    			 setTimeout(function(){jQuery('#dateErrorDiv').hide();},2000);
	    		       return false;
				}
		    	
		    	var oneDay = 24*60*60*1000; /* hours*minutes*seconds*milliseconds*/		    	
		    	var diffDays = Math.round(Math.abs((endDat_converted.getTime() - fromDat_converted.getTime())/(oneDay)));
				if(diffDays>90)
					{
						jQuery('#dateErrorDiv').html("<spring:message code='validation.requestHistory.dateRange.exceed'/>");
	    			  	jQuery('#dateErrorDiv').show();
	    			  	setTimeout(function(){jQuery('#dateErrorDiv').hide();},900);
	    		       	return false;
					}
		    	
	
	    	}
			
				reloadGrid();
			});
		}
	function removeAndAddClass(linkObj){
		//remove all the selected class
		jQuery('.left-nav-inner ul li').each(function(){
				jQuery(this).removeClass('selected');
			});
		//add selected class
		jQuery(linkObj).addClass('selected');
		
		//Add Page title 
		jQuery('#historyTypeName').html(getPageTitleFromLinkId(jQuery(linkObj).attr('id')));
		
		
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
	
	function getInitGridParamsObj(linkId){
		
		
		
		if(linkId=='<%=ALL_REQUESTS%>')
			return gridInitParams_All_requests;
		else if(linkId=='<%=SUPPLY_REQUESTS%>')
			return gridInitParams_Supply_requests;
		else if(linkId=='<%=CHANGE_REQUESTS%>')
			return gridInitParams_Change_requests;
		else if(linkId=='<%=SERVICE_REQUESTS%>')
			return gridInitParams_Service_requests;
		else if(linkId=='<%=HARDWARE_REQUESTS%>')
				return gridInitParams_Hardware_requests;
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
				else if(linkId=='<%=HARDWARE_REQUESTS%>')
					return "<%=GRID_CREATION_IDS_FOR_HISTORY[4]%>";
					
		}

	function getPageTitleFromLinkId(linkId){
			if(linkId=='<%=ALL_REQUESTS%>')
				return "<spring:message code='requestInfo.requestHistory.heading.allRequests'/>";
			else if(linkId=='<%=SUPPLY_REQUESTS%>')
				return "<spring:message code='requestInfo.requestHistory.heading.supplyRequests'/>";
			else if(linkId=='<%=CHANGE_REQUESTS%>')
				return "<spring:message code='requestInfo.requestHistory.heading.changeRequests'/>";
			else if(linkId=='<%=SERVICE_REQUESTS%>')
				return "<spring:message code='serviceRequest.title.serviceRequest'/>";
			else if(linkId=='<%=HARDWARE_REQUESTS%>') 
				return "<spring:message code='requestInfo.header.hardwareReq'/>";
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
		gridInitParams_Change_requests["<%=gridConfigurationValues[1]%>"]="#text_filter,&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
		gridInitParams_Change_requests["<%=gridConfigurationValues[2]%>"]="center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitParams_Change_requests["<%=gridConfigurationValues[3]%>"]="130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130";
		gridInitParams_Change_requests["<%=gridConfigurationValues[4]%>"]="ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitParams_Change_requests["<%=gridConfigurationValues[5]%>"]="str,str,str,str,str,str,str,str,na,str,str,na,str,na,na,str,str,str,str,str,str,na,na,str,na,na,na,na,na,na,na,na,na,na,na,na,na,na";
		gridInitParams_Change_requests["<%=gridConfigurationValues[6]%>"]="0,1";
		gridInitParams_Change_requests["<%=gridConfigurationValues[7]%>"]="0,des";
		gridInitParams_Change_requests["<%=gridConfigurationValues[8]%>"]="4,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37";
		gridInitParams_Change_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
		gridInitParams_Change_requests["<%=JSON_COMBO_FILTER%>2"]=srOPSStatusList;
		gridInitParams_Change_requests["<%=JSON_COMBO_FILTER%>3"]=srSubStatusList;
		gridInitParams_Change_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveCMHistoryList"/>";
		gridInitParams_Change_requests["settingsAlreadyAvailable"]=false;
		
		gridInitParams_Supply_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='requestInfo.history.supplyRequestHistory.fields'/>";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[1]%>"]=",,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[3]%>"]="80,30,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130,130";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[4]%>"]="ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,na,str,na,na,str,str,str,str,str,str,na,na,str,na,na,na,str,na,na,na,na,na,na,na";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[6]%>"]="0,1,2";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[7]%>"]="2,des";
		gridInitParams_Supply_requests["<%=gridConfigurationValues[8]%>"]="0,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36";
		gridInitParams_Supply_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
		gridInitParams_Supply_requests["<%=JSON_COMBO_FILTER%>2"]=srOPSStatusList;
		gridInitParams_Supply_requests["<%=JSON_COMBO_FILTER%>3"]=srSubStatusList;
		gridInitParams_Supply_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveCMHistoryList"/>";
		gridInitParams_Supply_requests["settingsAlreadyAvailable"]=false;
		
		gridInitParams_Service_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='serviceRequest.listHeader.serviceRequests'/>";
		gridInitParams_Service_requests["<%=gridConfigurationValues[1]%>"]=",#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter";
		gridInitParams_Service_requests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitParams_Service_requests["<%=gridConfigurationValues[3]%>"]="25,120,80,120,140,200,100,100,140,110,110,100,100,100,110,110,110,100,100,100,100,100,100,100,100,100,100,100,100,100";
		gridInitParams_Service_requests["<%=gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitParams_Service_requests["<%=gridConfigurationValues[5]%>"]="na,str,str,str,str,na,str,str,str,na,na,str,str,str,str,str,str,str,na,na,str,na,na,na,na,na,str,na,na,na";
		gridInitParams_Service_requests["<%=gridConfigurationValues[6]%>"]="0,1";
		gridInitParams_Service_requests["<%=gridConfigurationValues[7]%>"]="1,des";
		gridInitParams_Service_requests["<%=gridConfigurationValues[8]%>"]="5,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28";
		gridInitParams_Service_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
		gridInitParams_Service_requests["<%=JSON_COMBO_FILTER%>2"]=srOPSStatusList;
		gridInitParams_Service_requests["<%=JSON_COMBO_FILTER%>3"]=srSubStatusList;
		gridInitParams_Service_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveBreakfixSRList"/>";
		gridInitParams_Service_requests["settingsAlreadyAvailable"]=false;
		
				
		
		//House No. field added for CI Defect #9183
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[0]%>"]="<spring:message code='hardwareHistory.listHeader.hardwareHistoryList'/>";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[1]%>"]=",#text_filter,,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[3]%>"]="25,120,80,120,140,200,100,100,140,100,110,110,100,100,100,110,110,110,100,100,100,100,100,100,100,100,100,100,100,100";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[5]%>"]="na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[6]%>"]="0,1";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[7]%>"]="1,des";
		gridInitParams_Hardware_requests["<%=gridConfigurationValues[8]%>"]="6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29";
		gridInitParams_Hardware_requests["<%=JSON_COMBO_FILTER%>1"]=requestStatusList;
		gridInitParams_Hardware_requests["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveCMHistoryList"/>";
		gridInitParams_Hardware_requests["settingsAlreadyAvailable"]=false;
		}
				
		function saveGridSettingsAlreadyAvailable(){
		//Save current setting in the object
		refreshGridSettingOnPage(requestListGrid);
		
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
			
			
		}else if(gridType=='<%=HARDWARE_REQUESTS%>'){
			gridInitParams_Hardware_requests["<%=gridSavingParams[0]%>"]=colsOrder;
			gridInitParams_Hardware_requests["<%=gridSavingParams[1]%>"]=colsWidth;
			gridInitParams_Hardware_requests["<%=gridSavingParams[2]%>"]=colsSorting;
			gridInitParams_Hardware_requests["<%=gridSavingParams[3]%>"]=colsHidden;
			gridInitParams_Hardware_requests["filterValues"]=('filterValues' in requestListGrid)?requestListGrid.filterValues:"";
		} 
			
				
		//alert('gridSavingParams[3]'+JSON_Param["<%=gridSavingParams[3]%>"]);
		}

	function loadDefaultGrid(){
			
			if(gridType!=null && gridType!='') {
			removeAndAddClass(jQuery('#'+gridType));
			gridCreationId=getGridIdFromLinkId(gridType);
			JSON_Param=getInitGridParamsObj(gridType);
			jQuery('#loadingNotification').before('<div id="'+gridCreationId+'" class="gridbox gridbox_light"></div>');
			JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
			JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
			JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
			JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
			JSON_Param["settingsAlreadyAvailable"]=true;
			setMinimizeAllAndExpandAllHidden();
			setValuesIfBackFromDetails();
			} else {
				jQuery('#loadingNotification').hide();
			}
			
			
		}

	
	function onSRNmbrClick(serviceRequestNumber, requestType,srStatus,srSubArea,srArea, coveredService){
		if(requestType=="Consumables Management" && srStatus=="Draft"){
			if(srSubArea=="Catalog Request"){
				// - sourcePage and history added for back button in asset details (MPS Phase 2)
				window.location.href= "catalogorder?requestNumber=" + serviceRequestNumber+ "&reqStatus="+srStatus + "&sourcePage="+gridType +"&history="+'history';
		
			}else{
				window.location.href= "consumableorder?requestNumber=" + serviceRequestNumber+ "&sourcePage="+gridType+"&reqStatus="+srStatus + "&history="+'history';
			}
		}
		else if(requestType=="Fleet Management" && srSubArea=="BAU" && srStatus=="Draft"){			
			showOverlay();
			window.location.href= "hardwareorder?requestNumber=" + serviceRequestNumber+ "&reqStatus="+srStatus;		
		}
		else if(requestType=="Fleet Management" || requestType=="Consumables Management"){
			<%-- Changes for defect 8083 MPS 2.1--%>
			if((srArea=='HW Order' || srArea=='Hardware-Ship and Install') && srSubArea =='BAU'){
				showOverlay();
				var link = "<portlet:renderURL windowState='<%= LiferayWindowState.NORMAL.toString() %>'><portlet:param name='action' value='showHardwareRequestDetails' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset +"&requestType=hardware&sourcePage="+gridType+"&srArea="+srArea;
				window.location.href= link;
			
			}else{<%--Ends Changes for defect 8083 MPS 2.1--%>
				showOverlay();
				var link = "<portlet:renderURL windowState='<%= LiferayWindowState.NORMAL.toString() %>'><portlet:param name='action' value='showRequestDetails' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset +"&requestType="+requestType +"&sourcePage="+gridType+"&srArea="+srArea+"&srSubArea="+srSubArea +"&coveredService="+coveredService;
				window.location.href= link;
			}
		
		
		}else if(requestType=='hardware'){
			if(srStatus=="Draft"){
				showOverlay();
				window.location.href= "hardwareorder?requestNumber=" + serviceRequestNumber+ "&reqStatus="+srStatus;
				}else{
					showOverlay();
					var link = "<portlet:renderURL windowState='<%= LiferayWindowState.NORMAL.toString() %>'><portlet:param name='action' value='showHardwareRequestDetails' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber + "&timeZoneOffset=" + timezoneOffset +"&requestType="+requestType +"&sourcePage="+gridType+"&srArea="+srArea;
					window.location.href= link;
				}
			
		
		}
		else{
			showOverlay();
			var link = "<portlet:renderURL windowState='<%= LiferayWindowState.NORMAL.toString() %>'><portlet:param name='action' value='showServiceRequestDrillDownPage' /></portlet:renderURL>&serviceRequestNumber=" + serviceRequestNumber+"&sourcePage="+gridType+"&timeZoneOffset=" + timezoneOffset;
			window.location.href= link;
		}
	}
	 function downloadCMHistory(type){
			//alert('inside  downloadCMHistory:'+type);
			if(requestListGrid.getRowId(0)==null){
				alert("<spring:message code='serviceRequest.download.noDataFound'/>");
				return false;
			}

			if(gridType=="<%=SERVICE_REQUESTS%>")		
				window.location="<portlet:resourceURL id="downloadServiceRequestsURL"/>"+"&downloadType=" + type+"&pageType="+gridType+"&timezoneOffset="+timezoneOffset;
			else
				window.location="${downloadHistoryList}&downloadType=" + type+"&pageType="+gridType+"&timezoneOffset="+timezoneOffset;
		}
		function print(){  
			//alert("In Print..");
			if(requestListGrid.getRowId(0)==null){
				alert("<spring:message code='serviceRequest.download.noDataFound'/>");
				return false;
			}
			url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
				"<portlet:param name='action' value='printSRHistoryList' />" + 
				"</portlet:renderURL>";
			    var iWidth=900;
			    var iHeight=600;
			    var iTop = (window.screen.availHeight-30-iHeight)/2;        
			    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
			    window.open(url,
			    		'ServiceRequestList',
					    'height='+iHeight+
					    ',innerHeight='+
					    iHeight+',width='+
					    iWidth+',top='+iTop+
					    ',left='+iLeft+
					    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
		}
		function setValuesIfBackFromDetails(){
			
			if(backFromDetails=="true"){
				jQuery.getJSON(retrieveGridParams+"&gridId="+gridCreationId,function(result){
					if(result.status!='blank'){
						backFilterValues=result.filterValues;
						
						if(result.optionChosen!=null && result.optionChosen!=""){
					    	//This sets the radio button selected for this grid
					    	jQuery('#'+result.optionChosen).attr('checked',true);
					    	//setting the values in the hidden textbox
							jQuery('#searchFilterCriteria').val(result.optionChosen);
				    	}else{
					    	//by default showall will be checked.
				    		jQuery('#showAll').attr('checked',true);
				    		jQuery('#searchFilterCriteria').val('showAll');
				    		
					    	}
						 if(result.startDate!=null && result.endDate!=null && result.startDate!="" && result.endDate!=""){
								//setting the start date and end date to the text box
								//alert('setting start date end date');
								jQuery('#localizedBeginDate').val(result.startDate);
				    			jQuery('#localizedEndDate').val(result.endDate); 
						 }
						 initialiseGrid();		
					}else{
						 initialiseGrid();
						}
				});
				
			}else{
				initialiseGrid();
			}
		}
	function setLinksHiddenForPartnerRequest(){
		
		 var currentURL = window.location.href;
		 if(currentURL.indexOf('/partner-portal') != -1)
		  {	
			  jQuery('#topnavigation li a').each(function(){
				  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
				  jQuery(this).parent().addClass('selected');
				  });
			  jQuery('#requestTypeHeader').hide();
			  jQuery('#SUPPLY_REQUESTS').hide();
			  
		  }
	}
</script>

