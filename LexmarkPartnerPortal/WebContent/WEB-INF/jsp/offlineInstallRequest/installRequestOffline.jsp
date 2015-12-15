<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridConfigurationValues" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_COMBO_FILTER"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.JSON_RESOURCE_URL"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRTYPE"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRNUMBER"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.viewOrCloseout"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.ACTIVITYID"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.OFFLINEDEBRIEF"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.BACKURL"%>

<portlet:resourceURL id="generateInstallationDoc" var="generateInstallDocURL"></portlet:resourceURL>
<portlet:resourceURL id="acceptActivity" var="acceptActivity"></portlet:resourceURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
<![endif]-->

<style>.buttonWide{width:225px;}</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />


<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.offlineInstallRequest.heading"/></h1>
      <h2 class="step" id="subHeading1" style="display:none;"><spring:message code="requestInfo.offlineInstallRequest.subHeading"/></h2>
    </div>
<div class="wFull rtrnRequests div-style17" >
<a class="floatR anchor-style1" onclick="returnToAllGrid();" ><< <spring:message code="link.return.to.requests"/></a>
</div>
	<div class="main-wrap div-style18">	
		<div class="left-nav">
			<div id="filterContainer" class="left-nav-inner overflow-hidden" >
				<div>
				<div class="left-nav-header"><h3><spring:message code="request.filters.filterByStatus" /></h3></div>
					<div class="radioCss">
						<input id="filter3All" type="radio" name="status" value="All">
						<label for="filter3All"><spring:message code="request.filter.all" /></label>
						<br>
						<input id="filter3Open" type="radio" name="status" value="Open"  checked="checked">
						<label for="filter3Open"><spring:message code="request.filter.open" /></label>
						<br>
						<input id="filter3Closed" type="radio" name="status" value="Closed">
						<label for="filter3Closed"><spring:message code="request.filter.Closed" /></label>
						<br>
					</div>
				</div>
			
				<div class="doLine"></div>
				<div class="radioCss">
					<table id="dateRangeFilterContainer">
						<tr>
							<td class="padding-top-1px">
								<img src="<html:imagesPath/>treeImgs/minus5.gif" class="cursor-pointer" />
							</td>
							<td><spring:message code="request.filter.byDateRange"/></td>
						</tr>
						<tr>
							<td class="padding-top-3px"></td>
							<td>
								<div class="text-align-left">
									<div class="div-style19" >
										<span class="div-style20">
										<spring:message code="request.filter.from"/></span>
										<input class="localized-begin-date" type="text" name="localizedBeginDate" id="localizedBeginDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedBeginDate"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedBeginDate"/>
									</div>
									<div class="div-style19">
										<span class="div-style20"><spring:message code="request.filter.to"/></span>
										<input class="localized-end-date" type="text" name="localizedEndDate" id="localizedEndDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedEndDate"  src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedEndDate"/>
									</div>
									<div>
										<div class="buttonContainerIn">
								<%-- 		<a href="javascript:void(0)" class="button"><span><spring:message code="request.button.go"/></span></a> --%>
										 <button class="button"><span><spring:message code="request.button.go"/></span></button>
							            </div>
										<!-- </a> -->
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="left-nav-footer"></div>
		</div> <%-- left nav ends --%>
		<div class="right-column">
			<div class="grid-controls">
				<div id="offlineInstallRequestGrid">
					<div id="activityListDiv">
						<div id="requestInformation" style="display: none;">
						<div class="portletBlock infoBox rounded shadow">
						<div  class="w70p float-left" >
						<ul class="form">
						<li>
							<label for="requestNumberSpan" class="labelText"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.reqNo"/></label><span id="requestNumberSpan"></span>
						</li>
						<li>
							<label for="requestStatusSpan" class="labelText"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.requestInformation.requestStatus"/></label><span id="requestStatusSpan"></span>
						</li>
						</ul>
						</div>	
							
							
						
						<div class="w30p div-style21" >
						<button type="button" class="button" id="generateInstallDocBut" onclick="generateDoc();"><spring:message code="requestInfo.offlineInstallRequest.generateDownload"/></button>
						</div>
						
						<div class="clear-both"></div>
						</div>
						</div>
							<div class="grid-controls" >
									<div id="utilDivs" class="utilities" style="display: none;">
										<ul>
											<li class="first">
												<img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer" height="23px" width="23px" onclick="download('pdf')" title="<spring:message code="image.title.exportPDF"/>" />
											</li>
											<li>
												<img src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon cursor-pointer" height="23px" width="23px" onclick="download('csv')" title="<spring:message code="image.title.exportExcel"/>" />
											</li>
											<li>
												<img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" onclick="javascript:window.print();"; title="<spring:message code="image.title.print"/>" />
											</li>
										</ul>
									</div><!-- utilities -->
									<div class="expand-min" >
										<ul>
											<li class="first">
												<img src="<html:imagesPath/>transparent.png" height="25px" class="ui-icon minimize-icon cursor-pointer" width="25px" onclick="minimizeAll()" title="<spring:message code="requestInfo.label.minimizeAll"/>"/>
												&nbsp;<a  class="cursor-pointer" onclick="minimizeAll()" title="<spring:message code="requestInfo.label.minimizeAll"/>"><spring:message code="requestInfo.label.minimizeAll"/></a>
											</li>
											<li>
												<img src="<html:imagesPath/>transparent.png" class="ui-icon expand-icon cursor-pointer" height="25px" width="25px" onclick="expandAll()" title="<spring:message code="requestInfo.label.expandAll"/>"/>
												&nbsp;<a  class="cursor-pointer" onclick="expandAll()" title="<spring:message code="requestInfo.label.expandAll"/>"><spring:message code="requestInfo.label.expandAll"/></a>
											</li>
											<li id="refreshLink">
											
												<a  class="anchor-style2" onclick="reloadGrid()">
													<spring:message	code="requestInfo.uploadHistory.link.refresh" />
												</a>
											
											</li>
											<!-- Changes for defect no.10844 -->
											<li class="customLinksDiv" style="display: none;">
												<a  class="cursor-pointer" id='headerMenuButton' title="<spring:message code="image.title.customize"/>">
												<img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" height="25" width="25" />
												<spring:message	code="label.customize" />
											</a>
											</li>
											<!-- END -->
											<li class="customLinksDiv" style="display: none;">
												<img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon cursor-pointer" height="25px" width="25px" onclick="doReset()" title="<spring:message code="image.title.reset"/>"/>
												<a class="cursor-pointer" href="javascript:doReset();" id="resetGridSetting" title="<spring:message code="image.title.reset"/>"><spring:message code="label.reset"/></a>
											</li>
										</ul>
									</div><!-- expand-min -->
									<div class="clear-right"></div><!-- clear -->
									
								</div><!-- grid-controls -->
								
								
								<div class="portlet-wrap rounded">
									<div class="portlet-wrap-inner">
										<div id="loadingNotification" class='gridLoading'>
											<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
										</div>
										<div id="pagingSection">
											<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
										</div>
									
										
										
									</div>
									<div class="clear-both"></div>
									<!-- portlet-wrap-inner -->
									<div class="portlet-footer">
										<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
									</div><!-- portlet-footer -->
								</div><!-- portlet-wrap -->
								
								
								
							
							
						</div>
				</div>
			</div>
		</div>
	</div>
	<div class="wFull rtrnRequests height-20px" style="display:none;">
<a class="floatR anchor-style1" onclick="returnToAllGrid();" ><< <spring:message code="link.return.to.requests" /></a>
</div>
<form action="hardwareinstallation" id="hwInstallForm" method="post">
		<input type="hidden" name="<%=SRTYPE%>" id="<%=SRTYPE%>"/>
		<input type="hidden" name="<%=SRNUMBER%>" id="<%=SRNUMBER%>"/>
		<input type="hidden" name="<%=viewOrCloseout%>" id="<%=viewOrCloseout%>"/>
		<input type="hidden" name="<%=ACTIVITYID%>" id="<%=ACTIVITYID%>"/>
		<input type="hidden" name="<%=TIMEZONE_OFFSET%>" id="<%=TIMEZONE_OFFSET%>"/>
		<input type="hidden" name="<%=OFFLINEDEBRIEF%>" id="<%=OFFLINEDEBRIEF%>" value="true"/>
		<input type="hidden" name="<%=BACKURL%>" value="/group/partner-portal/request-offline"/>
		
	</form>
<input type="hidden" id="srID"/>
<input type="hidden" id="offlineRequests" value="true"/>
</div>
<div id="requestConfirmDiv" style="display: none;">
      		
			 <div class="div-style22" >
			 	<img src="<html:imagesPath/>green-check.png" alt="green check" width="50" height="50">
			 </div>
			 <div class="div-style23" >
			 	<spring:message code="requestInfo.offlineInstallRequest.generateInstallDoc.message"/>
			
			 </div>
			 <div class="clear-both"></div>
			 <div class="buttonContainer">
	          <button class="button" onClick="dialog.dialog('close')"><spring:message code="button.ok"/></button>
	        </div>
	
      			
      	</div>	
<script>
	var gridCreationId=null;
	var dialog;
</script>
	<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>


		<script type="text/javascript">
		<%-- Below arrays are for status , status detail and request type dropdown --%>
		var hwDebriefRequestType = [];
		<c:forEach items="${hwDebriefRequestType}" var="hwDebriefRequestTypeVar" varStatus = "status" >
		hwDebriefRequestType[${status.index}] = ["${hwDebriefRequestTypeVar.key}","${hwDebriefRequestTypeVar.value}"];
		</c:forEach>
		
		var hwdebriefRequestStatus = [];
		<c:forEach items="${hwdebriefRequestStatus}" var="hwdebriefRequestStatusVar" varStatus = "status" >
		hwdebriefRequestStatus[${status.index}] = ["${hwdebriefRequestStatusVar.key}","${hwdebriefRequestStatusVar.value}"];
		</c:forEach>
		
		var hwdebriefRequestStatusDetails = [];
		<c:forEach items="${hwdebriefRequestStatusDetails}" var="hwdebriefRequestStatusDetailsVar" varStatus = "status" >
		hwdebriefRequestStatusDetails[${status.index}] = ["${hwdebriefRequestStatusDetailsVar.key}","${hwdebriefRequestStatusDetailsVar.value}"];
		</c:forEach>
		
		var serviceRequestStatus = [];
		<c:forEach items="${serviceRequestStatus}" var="serviceRequestStatusVar" varStatus = "status" >
		serviceRequestStatus[${status.index}] = ["${serviceRequestStatusVar.key}","${serviceRequestStatusVar.value}"];
		</c:forEach>
		
		var gridInitActivityList_defaultActivity={};
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[0]%>"]=",,<spring:message code='requestInfo.requests.listHeader.MADCRequestsOfflineDebriefs'/>,<spring:message code="requestInfo.offlineInstallRequest.gridColumn.download"/>";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[1]%>"]=",,#text_filter,,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[3]%>"]="30,20,130,130,110,100,100,100,100,130,110,100,130,100,200,100,100,140,140,200,200,100,80,80,80,80,80,80,80,80,80,80,200";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[4]%>"]="ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,na";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[6]%>"]="1";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[7]%>"]="2,des";
		gridInitActivityList_defaultActivity["<%=gridConfigurationValues[8]%>"]="0,4,5,6,7,8,9,10,12,13,14,15,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
		gridInitActivityList_defaultActivity["<%=JSON_COMBO_FILTER%>1"]=hwDebriefRequestType;
		gridInitActivityList_defaultActivity["<%=JSON_COMBO_FILTER%>2"]=serviceRequestStatus;
		gridInitActivityList_defaultActivity["<%=JSON_COMBO_FILTER%>3"]=hwdebriefRequestStatusDetails;
		gridInitActivityList_defaultActivity["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveOrderHardwareList"></portlet:resourceURL>";
		gridInitActivityList_defaultActivity["creationId"]="activityListGrid_defaultActivity";	
		gridInitActivityList_defaultActivity["setDbpersistentFlag"]=false;
		gridInitActivityList_defaultActivity["<%=gridSavingParams[0]%>"]="";
		gridInitActivityList_defaultActivity["<%=gridSavingParams[1]%>"]="";
		gridInitActivityList_defaultActivity["<%=gridSavingParams[2]%>"]="";
		gridInitActivityList_defaultActivity["<%=gridSavingParams[3]%>"]="";
	
		
		var gridInitActivityList={};
		gridInitActivityList["<%=gridConfigurationValues[0]%>"]=",,<spring:message code='requestInfo.requests.listHeader.MADCRequestsOfflineDebriefs'/>";
		gridInitActivityList["<%=gridConfigurationValues[1]%>"]=",,#text_filter,,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
		gridInitActivityList["<%=gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
		gridInitActivityList["<%=gridConfigurationValues[3]%>"]="30,20,130,130,110,100,100,100,100,130,110,100,130,100,100,100,100,140,140,200,200,100,80,80,80,80,80,80,80,80,80,80";
		gridInitActivityList["<%=gridConfigurationValues[4]%>"]="ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
		gridInitActivityList["<%=gridConfigurationValues[5]%>"]="na,na,str,str,str,str,str,str,str,str,str,str,str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str";
		gridInitActivityList["<%=gridConfigurationValues[6]%>"]="6";
		gridInitActivityList["<%=gridConfigurationValues[7]%>"]="6,des";
		gridInitActivityList["<%=gridConfigurationValues[8]%>"]="2,4,6,8,9,10,12,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
		gridInitActivityList["<%=JSON_COMBO_FILTER%>1"]=hwDebriefRequestType;
		gridInitActivityList["<%=JSON_COMBO_FILTER%>2"]=hwdebriefRequestStatus;
		gridInitActivityList["<%=JSON_COMBO_FILTER%>3"]=hwdebriefRequestStatusDetails;
		gridInitActivityList["<%=JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveOrderHardwareList"></portlet:resourceURL>";
		gridInitActivityList["headingMessage"]="Activity List";
		gridInitActivityList["creationId"]="hardwareActivityRequestList";	
		gridInitActivityList["downloadURL"]="<portlet:resourceURL id="downloadHardwareRequestsURL"/>&fromInstallDebrief=true";
		gridInitActivityList["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
		gridInitActivityList["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
		gridInitActivityList["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
		gridInitActivityList["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";
		
		var generateInstallDOC={
				clickonSR:false,
				fileName:"",
				fileSourcePath:"",
				fileSourceType:"",
				activityId:"",
				rowid:"",
				buttonObj:null
		}
		
		
		jQuery(document).ready(function(){
			JSON_Param=gridInitActivityList_defaultActivity;
			jQuery('#loadingNotification').before('<div id="'+JSON_Param["creationId"]+'" class="gridbox gridbox_light" style="position:relative;"></div>');
			gridCreationId=JSON_Param["creationId"];
			initialiseGrid();		
		});
		
		var gridCreationId=gridInitActivityList["creationId"];
		initURLParams = function(){
			xmlURLQueryParams={
					isFavorite:false,
					urlParameters:["direction","orderBy","filterCriterias","status","timezoneOffset","requestType","offlineRequests","srID","beginDate","endDate"],
					setParameters : function(){
											this["direction"]=requestListGrid.a_direction;
											this["orderBy"]=requestListGrid.getSortColByOffset()-1;
											xmlURLQueryParams['offlineRequests']=jQuery('#offlineRequests').val();
											xmlURLQueryParams['srID']=jQuery('#srID').val();
											this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
											this["timezoneOffset"] = timezoneOffset;	
											this["requestType"]="MADCRequests";
											this["beginDate"] = formatDateToDefault(jQuery("#localizedBeginDate").val());
											this["endDate"] = formatDateToDefault(jQuery("#localizedEndDate").val());
											jQuery("#filterContainer").find("input[type='radio']:checked").each(function(index,element){
												xmlURLQueryParams['status'] = element.value;
											});
											},
					setLoadXMLUrl : function(){
													xmlURL=new String(JSON_Param["<%=JSON_RESOURCE_URL%>"]);
													this.setParameters();
													for(i=0;i<this.urlParameters.length;i++){
														if(this[this.urlParameters[i]]!=null){
															
															xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
														}
															
													}	
											}
					};
			
				
				
				
			};
			reloadGrid=function(){
				//clearMessage();
				//alert('in reload Grid');
				<%-- This will prevent the saving of params in the first grid--%>
				
				 
				refreshGridSettingOnPage(requestListGrid);
				xmlURLQueryParams.setLoadXMLUrl();
				requestListGrid.clearAndLoad(xmlURL);
				 
			};
			
			
			 jQuery("#filterContainer").find("input[type=radio]").each(function(index,radioElement){
				jQuery(radioElement).click(function(){
					reloadGrid();
				}) ;
			 });
			 jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
			 jQuery("#localizedEndDate").val(localizeDate(todayStr));
			 jQuery("#dateRangeFilterContainer").find("tr > td:first").click(function(){
					var dateSelectBox=jQuery("#dateRangeFilterContainer").find("tr:last");
					if(dateSelectBox.is(":hidden")){
						jQuery(this).find("img").attr("src","<html:imagesPath/>treeImgs/minus5.gif");
						dateSelectBox.fadeIn();
					}else{
						jQuery(this).find("img").attr("src","<html:imagesPath/>treeImgs/plus5.gif");
						dateSelectBox.fadeOut();
					}
				});
				
				jQuery("#dateRangeFilterContainer").find(".button").click(reloadGrid);

				jQuery("#dateRangeFilterContainer").find("input").mouseup(function(){
					var beginDate=this.id=="localizedBeginDate" ? convertDateToString(new Date().addDays(-180)) : formatDateToDefault(jQuery("#localizedBeginDate").val());
					var endDate=this.id=="localizedEndDate" ? todayStr : formatDateToDefault(jQuery("#localizedEndDate").val());
					show_cal(this.id, beginDate, endDate);
				});

				jQuery("#dateRangeFilterContainer").find("input ~ img").mouseup(function(){
					jQuery(this).prev().mouseup();
				});
		onrowselectfunction=function(id,ind){
			if(JSON_Param["creationId"]=="activityListGrid_defaultActivity"){
				generateInstallDOC.clickonSR=true;
				//alert('isGenerateButClicked='+requestListGrid.cells(id,2).getAttribute("isGenerateButClicked"));
				
				
				if(jQuery(requestListGrid.cellById(id,1).getValue().toString()).find("button").html()==null || requestListGrid.cells(id,2).getAttribute("isGenerateButClicked") == true){
					  jQuery('#generateInstallDocBut').hide();		
					  //alert('hiding');
				}else{
					//alert('showing');
						jQuery('#generateInstallDocBut').show();
						jQuery(requestListGrid.cellById(id,1).getValue().toString()).find("button").click();
						
				}
				
					if(ind==2){
						var cellObj = requestListGrid.cellById(id,ind);
						var value=cellObj.getValue().substring(cellObj.getValue().indexOf('>')+1,cellObj.getValue().indexOf('</'));
						filterGridBySR(value,requestListGrid.cellById(id,5).getValue());
						return false;
					}else{
							return false;
					}
			}
		}		
		function filterGridBySR(srID,status){
			
			
			
			
			<%-- Setting values request number and request status--%>
			jQuery('#requestNumberSpan').html(srID);
			jQuery('#requestStatusSpan').html(status);
			
			
			
			
			jQuery('#utilDivs,.customLinksDiv,#requestInformation,#subHeading1').show();
			jQuery('.rtrnRequests').show();
			jQuery('#refreshLink').hide();
				
			jQuery('#srID').val(srID);
			jQuery('#offlineRequests').val(false);
			
		
		
		
			jQuery('#'+JSON_Param["creationId"]).remove();
			jQuery('#grid_menu').remove();
			jQuery('#pagingArea,#infoArea').empty();
			
			
				if(requestListGrid!=null){
					
					requestListGrid.destructor();
					requestListGrid=null;
				}
				JSON_Param=gridInitActivityList;
				jQuery('#loadingNotification').before('<div id="'+JSON_Param["creationId"]+'" class="gridbox gridbox_light" style="position:relative;"></div>');
				gridCreationId=JSON_Param["creationId"];
				initialiseGrid();
				jQuery('.filter:first').hide();
				
			return false;
		}
		function returnToAllGrid(){
			
			jQuery('#srID').val('');
			jQuery('#offlineRequests').val(true);
			
			jQuery('#utilDivs,.customLinksDiv,#requestInformation,#subHeading1').hide();
			jQuery('.rtrnRequests').hide();
			jQuery('#refreshLink').show();
			
			jQuery('#'+JSON_Param["creationId"]).remove();
			jQuery('#grid_menu').remove();
			jQuery('#pagingArea,#infoArea').empty();
			
			generateInstallDOC.clickonSR=false;
			refreshGridSettingOnPage(requestListGrid);
			
			
			gridInitActivityList["<%=gridSavingParams[0]%>"]=colsOrder;
			gridInitActivityList["<%=gridSavingParams[1]%>"]=colsWidth;
			gridInitActivityList["<%=gridSavingParams[2]%>"]=colsSorting;
			gridInitActivityList["<%=gridSavingParams[3]%>"]=colsHidden;
			
			
				if(requestListGrid!=null){
					
					requestListGrid.destructor();
					requestListGrid=null;
				}
				JSON_Param=gridInitActivityList_defaultActivity;
				jQuery('#loadingNotification').before('<div id="'+JSON_Param["creationId"]+'" class="gridbox gridbox_light" style="position:relative;"></div>');
				gridCreationId=JSON_Param["creationId"];
				initialiseGrid();
			
			
			
		}
		function redirectToDebriefViewAndCloseOut(srNumber,srType,viewOrCloseOut,activityId){
			
			jQuery('#<%=SRNUMBER%>').val(srNumber);
			jQuery('#<%=SRTYPE%>').val(srType);
			jQuery('#<%=viewOrCloseout%>').val(viewOrCloseOut);
			jQuery('#<%=ACTIVITYID%>').val(activityId);
			jQuery('#<%=TIMEZONE_OFFSET%>').val(timezoneOffset);
			jQuery('#hwInstallForm').submit();
		}
		
		  function download(type){
				
				if(requestListGrid.getRowId(0)==null){
					jAlert("<spring:message code='download.noDataFound'/>");
					return false;
				}
				var downloadURL=JSON_Param["downloadURL"]+ "&timezoneOffset="+timezoneOffset;
				window.location=downloadURL+"&downloadType="+type;
			}
		  function downloadOfflineDoc(){
			  jAlert("Download not Yet available");
		  }
		  function generateDoc(fileName,fileSourcePath,fileSourceType,activityId,butObj,rowID){
			 
			  if(generateInstallDOC.clickonSR){
				  generateInstallDOC.clickonSR=false;
				  generateInstallDOC.fileName=fileName;
				  generateInstallDOC.fileSourcePath=fileSourcePath;
				  generateInstallDOC.fileSourceType=fileSourceType;
				  generateInstallDOC.activityId=activityId;
				  generateInstallDOC.buttonObj=butObj;
				  generateInstallDOC.rowid=rowID;
				  return;
					  
			  }
			  if(fileName==null&&fileName==null&&fileSourcePath==null&&fileSourceType==null&&activityId==null){
				  fileName=generateInstallDOC.fileName;
				  fileSourcePath=generateInstallDOC.fileSourcePath;
				  fileSourceType=generateInstallDOC.fileSourceType;
				  activityId=generateInstallDOC.activityId;
				  butObj=document.getElementById('generateInstallDocBut');
				  rowID=generateInstallDOC.rowid;
			  }
			  if(requestListGrid.getAllRowIds().indexOf(rowID)!=-1){
				  requestListGrid.cells(rowID,2).setAttribute("isGenerateButClicked",true);  
			  }
			  
			  
			  jQuery(butObj).hide();
			 
			  jQuery.ajax({
					url:'${generateInstallDocURL}',			
					type:'POST',
					data: {
						 	//Ajax call with selected row data
							fileName :  fileName,
							fileSourcePath:  fileSourcePath,
							fileSourceType:  fileSourceType,
							activityId:  activityId
							
					},
					success: function(results){
						},
					failure: function(){
							}
					});
			  
			  dialog=jQuery('#requestConfirmDiv').dialog({
					autoOpen: false,
					title: "<spring:message code="requestInfo.hardwareDebreief.common.closeOut.confirmMessageTitle"/>",
					modal: true,
					height: 360,
					width: 400,
					position: 'center',
					resizable: false,
					open:function(){
							jQuery('#requestConfirmDiv').show();
						},
					close: function(event,ui){
				
						 dialog.dialog('destroy');
						 
						}
					});
				
				dialog.dialog('open');
			  
				}
		  
		 function acceptActivityWS(srNumber){
			 
			 jQuery.ajax({
					url:'${acceptActivity}',			
					type:'POST',
					data: {
						 	//Ajax call with selected row data
							srNumber :  srNumber
							
					},
					success: function(results){
						},
					failure: function(){
							}
					});
		 }
		</script>
