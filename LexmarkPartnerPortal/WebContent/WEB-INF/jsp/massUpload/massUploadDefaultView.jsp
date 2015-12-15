
<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants"%>
<%@page import=" static com.lexmark.constants.LexmarkConstants.gridSavingParams" %>
<%@page import="com.lexmark.constants.MassUploadFlags"%>

<%@page import=" static com.lexmark.constants.LexmarkConstants.TIMEZONE_OFFSET"%>


<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRTYPE"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.SRNUMBER"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.viewOrCloseout"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.ACTIVITYID"%>
<%@page import=" static com.lexmark.constants.LexmarkPPConstants.BACKURL"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<script type="text/javascript">
var isMassUploadPage = true;
</script>

<div>
<jsp:include page="/WEB-INF/jsp/accountSelection/selectAccountPopUp.jsp"/>
</div>
<c:if test="${accountSelectionDone eq 'true'}">
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import="com.lexmark.constants.LexmarkPPConstants" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps_ie7.css?version=<html:fileCacheVersion/>" />
	<style>
	#gridIDhardwareRequestList{width:auto!important;min-width:100px!important;}	
	</style>
<![endif]-->
<!--[if IE 8]>
	<style>
	#content-wrapper{width:auto!important;min-width:400px!important;}
	.main-wrap{width:auto!important;min-width:400px!important;}	
	</style>
<![endif]-->

<style type="text/css">
	.ie7 .button, .ie7 .button_cancel { 
		/*position: static !important;*/
	 	behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;		
	 }
	 .greyBorder{border:solid 1px #e6e6f0;}
	 div .left-nav1{background:none!important;min-width:204px!important;width:100%!important;}
	 div .left-nav-inner{min-width:193px!important;width:96%!important;}
	 .left-nav1 .inlineTitle {
	border-top:0 none;
	margin:5px 0
}
#localizedBeginDate{width:100px!important;}
	#localizedEndDate{width:100px!important;}

</style>

<portlet:resourceURL var="getGridSettingsByAjaxVar" id="getGridSettingsByAjax"/>
<portlet:renderURL var="orderRequestDetailURL">
	<portlet:param name='action' value='retrieveOrderRequest' />
</portlet:renderURL>
<portlet:actionURL var='attachDocumentAction' windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name='action' value='attachDocument' />
</portlet:actionURL>
<portlet:renderURL var="accountSelectionURL">
	<portlet:param name='action' value='showAccountPopup' />
</portlet:renderURL>
<portlet:actionURL var ="removeAttachmentURL" windowState="<%=LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name='action' value='removeAttachment' />
</portlet:actionURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div id="content-wrapper">
    <div class="journal-content-article">
      <h1><spring:message code="requestInfo.heading.massUpload"/></h1>
      <h2 class="step" id="subHeading1"><spring:message code="requestInfo.heading.hardwareOrder.hardwareOrders"/></h2>
    </div>
    
    	<div id="exceptionDiv" class="error" style="display:none">
    		<ul>
    			<li><spring:message code="exception.system.error2"></spring:message></li>
    		</ul>
    	</div>
    
    <div class="main-wrap">
       <div class="content relativePos" >
       <table class="table-style6" >
       <tr class="width-100per">
       <td class="greyBorder table-td-style2" >
    		<jsp:include page="massUploadLeftNav.jsp"></jsp:include>
      	</td>
      	<td class="greyBorder table-td-style3" >
      	<div>
      		<p class="wFull strong"><span id="subHeading2"><spring:message code="requestInfo.hardwareDebreief.heading.install"/></span> <spring:message code="requestInfo.massUpload.heading.for"/> <span id="accountName">${sessionScope.accountCurrentDetails ["accountName"]}</span>
      		 <c:if test='${sessionScope.accountCurrentDetails ["accCount"]!="1"}'> 
      		<span><a href="#" id="accountSelectionId">Change Account</a></span>
      		</c:if></p>
      		<hr class="separator"/>
      		<p class="strong"><spring:message code="requestInfo.massUpload.importDataToSpreadSheet"/></p>
      		<hr class="separator"/>               
      		<p class="wFull"><spring:message code="requestInfo.massUpload.info.saveUpdatesNew"/></p>
			<hr class="separator"/>
      	</div>
      <%--<div class="gray">
        	
        	 
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid relativePos" id="dataGridTable">
                <tr>
                  <td width="50" class="label"><spring:message code="request.filter.from"/></td>
                  <td width="200">
                                                  									
	                  <span>
	                 	 <input class="w150 positionShift" type="text" name="localizedBeginDate" id="localizedBeginDate" size="5" readOnly="readonly"/>
					  	<img id="imgLocalizedBeginDate" width="23" height="23" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon positionFit cursor-pointer" />
	                       	
	                  </span>
                  </td>
                  <td width="50" class="label"><spring:message code="request.filter.to"/></td>
                  <td width="200">
                  
                  	<span>
                  	    <input class="w150 positionShift" type="text" name="localizedEndDate" id="localizedEndDate" size="5" readOnly="readonly"/>
						<img id="imgLocalizedEndDate"  width="23" height="23" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon positionFit cursor-pointer" />
           
                  	</span>
                  </td>
                  <td class="relativePos">
                  	<button id="go_btn" class="button"><spring:message code="request.button.go"/></button>
                  	
                  </td>
                </tr>
            </table>
             
         </div><!-- Div class gray ends -->--%>
         
      
          <!-- MAIN CONTENT GOES HERE -->
  
          <div class="grid-controls"> 
            <!-- Expand-min Start -->
            <div class="utilities">
              <ul id="gridDownloadUtils">
                <li class="first">
                	<a href="#"><img name="excel" src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon" alt="<spring:message code="requestInfo.tooltip.exportToExcel"/>" title="<spring:message code="requestInfo.tooltip.exportToExcel"/>"></a>
                </li>
                <li>
                	<a href="#"><img name="pdf" src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon" alt="<spring:message code="requestInfo.tooltip.exportToPDF"/>" title="<spring:message code="requestInfo.tooltip.exportToPDF"/>"></a>
                </li>
                <li>
                	<a href="#"><img name="print" src="<html:imagesPath/>transparent.png" class="ui-icon print-icon" alt="<spring:message code="requestInfo.tooltip.printThisPage"/>" title="<spring:message code="requestInfo.tooltip.printThisPage"/>"></a>
                </li>
              </ul>
            </div>
            <div class="expand-min">
              <ul>
              	<li class="first">
              		<img src="<html:imagesPath/>transparent.png" class="ui-icon minimize-icon" alt="<spring:message code="requestInfo.label.minimizeAll"/>" onclick="minimizeAll()" title="<spring:message code="requestInfo.label.minimizeAll"/>" />
              		<a href="javascript:void(0)" onClick="minimizeAll()";><spring:message code="requestInfo.label.minimizeAll"/></a>
              	</li>
                <li>
                	<img src="<html:imagesPath/>transparent.png" class="ui-icon expand-icon" alt="<spring:message code="requestInfo.label.expandAll"/>" onclick="expandAll()" title="<spring:message code="requestInfo.label.expandAll"/>" />
                	<a href="javascript:void(0)" onClick="expandAll()";><spring:message code="requestInfo.label.expandAll"/></a>
                </li>
                <li>
                	<img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon" alt="<spring:message code="label.customize"/>" title="<spring:message code="label.customize"/>"/>
                	<a href="javascript:void(0);" id='headerMenuButton' title="<spring:message code="label.customize"/>"><spring:message code="label.customize"/></a>
                </li>
                <li>
                	<img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon" alt="<spring:message code="requestInfo.label.reset"/>" title="<spring:message code="requestInfo.label.reset"/>" />
                	<a href="javascript:doReset();"><spring:message code="requestInfo.label.reset"/></a> 
                </li>
                
               
              </ul>
            </div>
            <!-- Expand-min End --> 
            
           
           </div> <!-- Div class grid control end -->
         
         <!-- grid-controls Ends -->
         
         
         		<div class="portlet-wrap rounded" >
					<div class="portlet-wrap-inner infoBox div-style16" id="dynamicGridLoadDiv" >
						<!-- the dynamic div should come here -->
						<div id="loadingNotification" class='gridLoading' >
						<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
					  </div>
					 <div class="pagination" id="paginationId"> <span id="pagingArea"></span><span id="infoArea"></span> </div>
				</div>
		              
				 
			    </div>
         
         
         
          
          
          
          
          
          
          <!-- mygrid_container --> 
            <div class="collapse height-100per" >
                <div class="infoBox" >
                <p class="strong"><spring:message code="requestInfo.massUpload.ImportData"/></p>
                <p class="wHalf">
                <spring:message code="requestInfo.massUpload.uploadInfo1"/>
               
                </p>
                <label for="textInput" class="labelText"> <spring:message code="requestInfo.massUpload.selectFileToUpload"/><span class="req">*</span></label>
      	 <form:form id="fileUploadFormId" commandName="fileUploadForm" method="post" enctype="multipart/form-data" target="upload_target">
      	 <form:input id="pageTypeUpload" type="hidden" path="pageType" />
      	 <form:input path="sessionFileKey" type="hidden" />
      	 <form:input path="fileIndex" type="hidden" id="fileIndex"/>
      	  <div class="floatL wFull">
        <table class="relativePos floatL">
        			<tr></tr>
                   	<tr class="floatL">
        			<td width="225" class="relativePos"><input type="text" size="70" name="txtFilePath" id="textInput" readonly="readonly" class="relativePos">&nbsp;</td>
                    <td width="100" class="relativePos"><form:input type="file" id="fileUploadInput" path="fileData" class="requestsUploader requestsUploader2" size="1" />
                    <button class="button relativePos" type="button" id="browseButton" ><spring:message code="button.browse"/></button></td>
                    <td width="100" class="relativePos">
                    <button class="button relativePos" type="button" id="uploadButn"><spring:message code="requestInfo.button.upload"/></button> </td>
                    </tr>
            </table>
         </div>
        </form:form>
         <div id="uploadInformationDiv" class="wHalf floatL margin-top-20px" ></div>
          <div class="clear-both"></div> 
         </div>
       </div>
       <div class="wFull marginT10" ><iframe id="upload_target" name="upload_target" style="display: none;" frameBorder="0" scrolling="no"></iframe></div>
        <div class="buttonContainer relativePos">
          		<button class="button" id="submitBtn"><spring:message code="requestInfo.button.submitRequest"/></button>
        </div>
        </td></tr></table>
      </div>
      <%--  This div is for showing the popup for service Request number --%>
     
      	<div id="requestConfirmDiv" style="display: none;">
      		
			 <div class="columnsThree " >
			 	<img src="<html:imagesPath/>green-check.png" alt="green check" width="50" height="50">
			 </div>
			 <div>
			 	<spring:message code="requestInfo.message.serviceOrder.confirmMessage1"/> <span id="serviceReqNum"></span>&nbsp; 
			 	<spring:message code="requestInfo.message.massUpload.confirmMessage2"/>
				<br/><br/>
				<spring:message code="requestInfo.message.massUpload.accessUploadHistory"/>
			 </div>
			 <div class="buttonContainer">
	          <button class="button" onClick="dialog.dialog('close')"><spring:message code="button.ok"/></button>
	        </div>
	
      			
      	</div>	
    <%--  popup div ENDS --%>
      
      <form action="hardwareinstallation" id="hwInstallForm" method="post">
		<input type="hidden" name="<%=SRTYPE%>" id="<%=SRTYPE%>"/>
		<input type="hidden" name="<%=SRNUMBER%>" id="<%=SRNUMBER%>"/>
		<input type="hidden" name="<%=viewOrCloseout%>" id="<%=viewOrCloseout%>"/>
		<input type="hidden" name="<%=ACTIVITYID%>" id="<%=ACTIVITYID%>"/>
		<input type="hidden" name="<%=TIMEZONE_OFFSET%>" id="<%=TIMEZONE_OFFSET%>"/>
		<input type="hidden" name="<%=BACKURL%>" value="/group/partner-portal/mass-upload"/>
	</form>
      		
      
      
    </div>
</div><!-- Content Wrappper -->





<jsp:include page="/WEB-INF/jsp/common/dynamicGridInitialize.jsp"></jsp:include>
				 
<script type="text/javascript">

var gridCreationId;
//following variables are declared in dynamicGridInitialize
pagingArea="pagingArea";infoArea="infoArea";headerMenuButton="headerMenuButton";loadingNotification="loadingNotification";backFilterValues="";
pageSize=20,pagesIngrp=10;
<%--This is for Heading messages --%>
var gridInitParamsServiceOrder={};
var gridInitParamsHardwareOrder={};



var dialog;
var isFileUploaded=false;

function initURLParams(){


	xmlURLQueryParams={
			urlParameters:["beginDate","endDate","direction","orderBy","status","timezoneOffset","filterCriterias","gridId","isMassUpload"],
			
			setParameters : function(){
									
									this["beginDate"]=formatDateToDefault(jQuery("#localizedBeginDate").val());
									this["endDate"]=formatDateToDefault(jQuery("#localizedEndDate").val());
									this["direction"]=requestListGrid.a_direction;
									this["orderBy"]=requestListGrid.getSortColByOffset();
									this["timezoneOffset"]=timezoneOffset;
									this["filterCriterias"]=requestListGrid.filterValues==null?"":requestListGrid.filterValues;
									this["gridId"]=gridCreationId;
									this["isMassUpload"]=true;
								},
			setLoadXMLUrl : function(){
											var oldVal;
											xmlURL=new String(JSON_Param["<%=LexmarkPPConstants.JSON_RESOURCE_URL%>"]);
											this.getFilterByStatus();
											this.setParameters();
											
											for(i=0;i<this.urlParameters.length;i++){
												xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
													
											}
									},
			getFilterByStatus: function(){
										
										var filterStatus=["Show All","Open","Closed"];
											jQuery('#filterByStatus input[type=radio]').each(function(index){
												if(jQuery(this).attr('checked')){
													xmlURLQueryParams.status=filterStatus[index];
													return;
												}
													
											});
										}
			};
	
		
		
		
	}
reloadGrid=function(){
	//clearMessage();
	//alert('in reload Grid');
	refreshGridSettingOnPage(requestListGrid);
	xmlURLQueryParams.setLoadXMLUrl();
	requestListGrid.clearAndLoad(xmlURL);
	
};


<%--
//requestListGrid.setCustomizeCombo(requestStatusList,7);//Customize combo to be done later
 header="<spring:message code='requestInfo.serviceOrder.listHeader.orderList'/>";
 filter=",&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
 colAlign="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
 default_colWidths="30,165,165,165,100,150,130,150,140,170,200,200,200,100,80,80,90,180,180";
 colType="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
 sortTypeCol="na,str,str,str,na,na,na,na,str,str,str,na,str,str,str,str,str,str,str";
// lockColVisibility=new String("2");
 defaultColSorting="1,des";
 defaultColsHidden="4,5,6,10,11,12,13,14,15,16,17,18";
 
//These values we will get from database
var gridId = 
var colsOrder = 
var colsWidth = 
var colsSorting = 
var colsHidden=--%>




jQuery(document).ready(function(){
	
	setGridInitParams();
	<c:if test="${exceptionOccured == true}">
		jQuery('#exceptionDiv').show();
	</c:if>
	jQuery('#go_btn').click(function(){
		reloadGrid();
	});
	jQuery('#filterByStatus input[type=radio]').click(function(){
		reloadGrid();
	});

	jQuery('#gridDownloadUtils li a img').click(function(){
			if(jQuery(this).attr('name')=='excel'){
				download('csv');
			}
			if(jQuery(this).attr('name')=='pdf'){
					download('pdf')
				}
			if(jQuery(this).attr('name')=='print'){
				print();
				}
		});

	jQuery('#accountSelectionId').click(function(){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_ACTION_CHANGE_ACCOUNT%>');
		jConfirm('<spring:message code="common.country.alert"/>', "", function(confirmResult) {
			if(confirmResult){
				showAccountPopup();
			}else{
				return false;
			}
		});
		
	});

	
	
	
	//This if for date selection not for all grids
	jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
	jQuery("#localizedEndDate").val(localizeDate(todayStr));

	jQuery('#imgLocalizedBeginDate,#imgLocalizedEndDate').click(function(){
		//alert(this.id);
		
		var beginDate,endDate,textId;
		if(this.id=='imgLocalizedBeginDate')
			beginDate=convertDateToString(new Date().addDays(-180));
		else
			beginDate=formatDateToDefault(jQuery("#localizedBeginDate").val());
		if(this.id=='imgLocalizedEndDate')
			endDate=todayStr;
		else
			endDate=formatDateToDefault(jQuery("#localizedEndDate").val());
		/*
		textId=(this.id).subString(4,this.id.length);
		textId="l"+textId;
		alert(textId);
		*/
		var textId=jQuery(this).prev("input");
			 
		//alert('beginDate='+beginDate+'endDate'+endDate);
		show_cal(textId.attr('id'), beginDate, endDate);

		});

	
	
	jQuery('#leftNavLinks a').click(function(){

		checkAndDestroyGrid();
		
		//Create a div for Grid which is equal to the value selected
		//remove the old div present
		/*if(gridCreationId!=null && gridCreationId!=""){
			jQuery('#'+gridCreationId).remove();//pagination also emptied
			jQuery('#pagingArea').empty();
			
		}*/
		clearAndResetUploadForm();
		JSON_Param=getObjFromLinkId(this.id);
		
		gridCreationId=JSON_Param["creationId"];

		<%--Below change is for Sub Heading --%>
		setSubHeadingToBreadCrump();
		<%--Ends Below change is for Sub Heading --%>
		
		//Below to be commented later
		//if(gridCreationId=='ServiceOrderHW')return;
		jQuery('#loadingNotification').before('<div id="'+gridCreationId+'" class="gridbox gridbox_light"></div>');
		
		
		jQuery.getJSON("${getGridSettingsByAjaxVar}&gridId="+gridCreationId,function(gridSettings){
			if(gridSettings.status=='available'){
			      
				jQuery.each(gridSettings,function(key,value){
					JSON_Param[key]=value;
					
					
					});
				
				
				
				initialiseGrid();
				
			}
			
		});
	});
	
	
	jQuery('#submitBtn').click(createServiceRequest);
	uploadEventBinding();//This method is for upload Event Binds
	initGridAsDefault();//This is for loading Hardware Order by default
	<c:if test='${serviceRequestNumber != "" and serviceRequestNumber != null }'>
		showServiceRequestNumber();
	</c:if>
	
});

	function checkAndDestroyGrid(){
	
		//saveGridSettingsAlreadyAvailable();
		//Remove the old Div
		jQuery('#'+gridCreationId).remove();
		jQuery('#grid_menu').remove();
		jQuery('#pagingArea,#infoArea').empty();
		
		
			if(requestListGrid!=null){
				requestListGrid.destructor();
				requestListGrid=null;
			}
			<%-- 	
		jQuery('#showAll').attr('checked',true);
		jQuery('#searchFilterCriteria').val('showAll');
		
		jQuery('#localizedBeginDate,#localizedEndDate').val('');
		--%>
	}

	function setGridInitParams(){
		
			var requestStatusList = [];
			<c:forEach items="${orderRequestStatusMap}" var="requestStatus" varStatus = "status" >
				requestStatusList[${status.index}] = ["${requestStatus.key}","${requestStatus.value}"];
			</c:forEach>
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
			
	
				
			 
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[0]%>"]=",<spring:message code="requestInfo.serviceOrder.listHeader.orderList" />";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[1]%>"]=",&nbsp;,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#text_filter,#text_filter,,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[3]%>"]="30,150,150,150,100,150,130,150,140,170,200,200,200,100,80,80,90,180,180,90,180,180";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[5]%>"]="na,str,str,str,str,str,str,na,str,na,str,na,na,na,na,na,na,na,na,na,na,na";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[6]%>"]="2";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[7]%>"]="1,des";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.gridConfigurationValues[8]%>"]="4,5,6,10,11,12,13,14,15,16,17,18";
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.JSON_COMBO_FILTER%>1"]=requestStatusList;
			gridInitParamsServiceOrder["<%=LexmarkPPConstants.JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveOrderRequestList"/>";
			gridInitParamsServiceOrder["headingMessage1"]="<spring:message code="requestInfo.massUpload.heading.serviceOrder"/>";
			gridInitParamsServiceOrder["headingMessage2"]="<spring:message code="requestInfo.massUpload.heading2.serviceOrder"/>";
			gridInitParamsServiceOrder["creationId"]="<%=LexmarkPPConstants.SERVICEORDERGRIDID%>";	
			gridInitParamsServiceOrder["downloadURL"]="<portlet:resourceURL id="downloadOrderRequestsURL"/>";
			gridInitParamsServiceOrder["printURL"]="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printOrderListDetail' /></portlet:renderURL>";

			
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[0]%>"]=",<spring:message code="requestInfo.requests.listHeader.MADCRequestsOfflineDebriefs"/>,<spring:message code="hardware.massUploadTemplate.expectedStartDate"/>,<spring:message code="hardware.massUploadTemplate.expectedEndDate"/>";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[1]%>"]=",#text_filter,,#text_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[2]%>"]="left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[3]%>"]="30,120,120,150,100,100,150,130,150,140,170,200,200,200,100,80,80,90,180,180,180,180,180,180,180,180,180,180,180,180,180,180,180";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[4]%>"]="sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[5]%>"]="na,str,str,str,str,str,str,str,na,str,na,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[6]%>"]="5";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[7]%>"]="1,des";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.gridConfigurationValues[8]%>"]="3,5,7,8,9,11,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31";
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.JSON_COMBO_FILTER%>1"]=hwDebriefRequestType;
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.JSON_COMBO_FILTER%>2"]=hwdebriefRequestStatus;
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.JSON_COMBO_FILTER%>3"]=hwdebriefRequestStatusDetails;
			gridInitParamsHardwareOrder["<%=LexmarkPPConstants.JSON_RESOURCE_URL%>"]="<portlet:resourceURL id="retrieveOrderHardwareList"/>";
			gridInitParamsHardwareOrder["headingMessage1"]="<spring:message code="requestInfo.heading.hardwareOrder.hardwareOrders"/>";
			gridInitParamsHardwareOrder["headingMessage2"]="<spring:message code="requestInfo.massUpload.heading2.hardwareInstall"/>";
			gridInitParamsHardwareOrder["creationId"]="<%=LexmarkPPConstants.HARDWAREORDERGRIDID%>";
			gridInitParamsHardwareOrder["downloadURL"]="<portlet:resourceURL id="downloadHardwareRequestsURL"/>&isMassUpload=true";
			gridInitParamsHardwareOrder["printURL"]="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='printHardwareOrderList' /></portlet:renderURL>";
			
		}
	function getObjFromLinkId(linkId){
			if(linkId=="<%=MassUploadFlags.MASSUPLOADHARDWARE.getLinkID()%>")
				return gridInitParamsHardwareOrder;
			if(linkId=="<%=MassUploadFlags.MASSUPLOADCONSUMABLES.getLinkID()%>")
				return gridInitParamsServiceOrder;
		}
	function downloadMassUploadTemplate(){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_ACTION_DOWNLOAD_TEMPLATE%>');
		if(requestListGrid.getRowId(0)==null){
			jAlert("<spring:message code='download.noDataFound'/>");
			return false;
		}

		window.location="<portlet:resourceURL id="downloadCSVFile"/>&gridId="+gridCreationId+"&t="+new Date().getTime();
	};

	

	

	function showOrderRequestDetail(vendorId,orderId,srId){
		
		showOverlay();
		window.location.href="service-orders?vendorAccountId=" +vendorId+ "&orderNumber=" + orderId + "&requestNumber=" + srId + "&timezoneOffset=" + timezoneOffset;
	};

	function clearAndResetUploadForm(){
		document.getElementById('fileUploadFormId').reset();
		 jQuery('#textInput').val("");	
		}
	
	function uploadEventBinding(){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_ACTION_UPLOAD%>');
		
		clearAndResetUploadForm();

		

	        
		 	
		
		jQuery('#uploadButn').click(function(){
			
				
				if(jQuery('#textInput').val()!=""){					
					var actionURL="${attachDocumentAction}&timezoneOffset="+timezoneOffset;
					if(gridCreationId=="<%=LexmarkPPConstants.HARDWAREORDERGRIDID%>"){
						jQuery('#pageTypeUpload').val("<%=LexmarkPPConstants.MASSUPLOADHW%>");
					}else{
						//Setting blank for service ORders
						jQuery('#pageTypeUpload').val("");
						}
					jQuery('#fileUploadFormId').attr('action',actionURL);
					showOverlay();
					jQuery('#fileUploadFormId').submit();	
				}else{
					jAlert("<spring:message code='requestInfo.message.massUpload.errorMsg.pleaseUploadFile'/>");
					}
				
				
			});

		 jQuery('#fileUploadInput').change(function(){
				jQuery('#textInput').val(jQuery('#fileUploadInput').val());	
			});
		
		}
	function showUploadedDetails(){
		hideOverlay();

		
		
		jQuery('#uploadInformationDiv').html(jQuery('#upload_target').contents().find('div:first').html()==null?"":jQuery('#upload_target').contents().find('div:first').html());
		jQuery('#textInput').val('');
			//jQuery('#upload_target').attr('style','width:500px;height:70px;border:0px;overflow:hidden!important;background-color:white;');
		}

	function initGridAsDefault(){
		//alert(jQuery('#leftNavLinks li:first a').attr('id'));
		
			JSON_Param=getObjFromLinkId("${link_Id}");
			
			setSubHeadingToBreadCrump();
			
			JSON_Param["<%=gridSavingParams[0]%>"]="${gridSettings.colsOrder}";
			JSON_Param["<%=gridSavingParams[1]%>"]="${gridSettings.colsWidth}";
			JSON_Param["<%=gridSavingParams[2]%>"]="${gridSettings.colsSorting}";
			JSON_Param["<%=gridSavingParams[3]%>"]="${gridSettings.colsHidden}";


			
			gridCreationId=JSON_Param.creationId;
			jQuery('#loadingNotification').before('<div id="'+gridCreationId+'" class="gridbox gridbox_light"></div>');
			initialiseGrid();
		}
	function createServiceRequest(){
		callOmnitureAction('<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_PORTLET_NAME%>','<%=LexmarkPPOmnitureConstants.MASS_UPLOAD_ACTION_SUBMIT_REQUEST%>');
		if(!isFileUploaded)
			jAlert("<spring:message code='requestInfo.message.massUpload.errorMsg.pleaseUploadFile'/>");
		else{
			showOverlay();
			var type;
			if(gridCreationId=="<%=LexmarkPPConstants.HARDWAREORDERGRIDID%>"){
				type="<%=LexmarkPPConstants.MASSUPLOADHW%>";<%-- FOR HARDWARE--%>
			}else{
					type="massUploadSO";<%-- FOR SERVICE ORDER--%>
				}
			var actURL="<portlet:resourceURL id="submitServiceRequest"/>&utime="+new Date().getTime()+"&type="+type;
			//alert(actURL);
			
			jQuery.getJSON(actURL,function(jsonResult){
				//alert("hererer");
				if(jsonResult.status=='<%=LexmarkPPConstants.JSON_STATUS_AVAILABLE%>'){
				    jQuery('#serviceReqNum').html(jsonResult.<%=LexmarkPPConstants.SERVICEREQUESTNUMBER%>);
				    showServiceRequestNumber();
					
				}else{
					hideOverlay();
					jQuery('#exceptionDiv').show();
				}
			
			});
			
			}
		}
	function showServiceRequestNumber(){
			
		dialog=jQuery('#requestConfirmDiv').dialog({
			autoOpen: false,
			title: "<spring:message code='requestInfo.message.massUpload.requestConfirmation'/>",
			modal: true,
			height: 460,
			width: 600,
			position: 'center',
			resizable: false,
			open:function(){
					jQuery('#requestConfirmDiv').show();
				},
			close: function(event,ui){
				 //hideOverlay();
				 dialog.dialog('destroy');
				 window.location.href="mass-upload?<%=LexmarkPPConstants.VENDORACCID_AVAIL%>=true";
				 //confirmRfresh();
				}
			});
		//jQuery(document).scrollTop(0);
		dialog.dialog('open');
		
		}
	function downloadUploadedFile(fileIndex){
		
		var isHw=false;
		<%-- 
		if(gridCreationId=="<%=LexmarkPPConstants.HARDWAREORDERGRIDID%>"){
			isHw=true;
		}--%>
			window.location="<portlet:resourceURL id="downloadUploadedAttachment"/>&t="+new Date().getTime()+"&sessionKey="+jQuery("[name='sessionFileKey']").val()+"&index="+fileIndex+"&isHw="+isHw;
		}

	function removeFile(fileIndex){

		var actionURL="${removeAttachmentURL}&t="+new Date().getTime();
		jQuery('#fileIndex').val(fileIndex);
		jQuery('#fileUploadFormId').attr('action',actionURL);
		if(gridCreationId=="<%=LexmarkPPConstants.HARDWAREORDERGRIDID%>"){
			jQuery('#pageTypeUpload').val("<%=LexmarkPPConstants.MASSUPLOADHW%>");
		}else{
			//Setting blank for service ORders
			jQuery('#pageTypeUpload').val("");
			}
		showOverlay();
		jQuery('#fileUploadFormId').submit();	
		
		
	}



	function download(type){
	   	if(requestListGrid.getRowId(0)==null){
			jAlert("<spring:message code='download.noDataFound'/>");
			return false;
		}
		
		window.location=JSON_Param.downloadURL+ "&timezoneOffset="+timezoneOffset+"&downloadType="+type;;
		
	}

	function print(){

			var url=JSON_Param.printURL;
			var iWidth=1000;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;
		    window.open(url,
		    		'historyList',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',innerWidth='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
		};

		function setSubHeadingToBreadCrump(){
			<%--Below change is for Sub Heading --%>
			jQuery('#subHeading1').html(JSON_Param.headingMessage1);
			jQuery('#subHeading2').html(JSON_Param.headingMessage2);
			<%--Ends Below change is for Sub Heading --%>
			}
		function redirectToDebriefViewAndCloseOut(srNumber,srType,viewOrCloseOut,activityId){
			
			jQuery('#<%=SRNUMBER%>').val(srNumber);
			jQuery('#<%=SRTYPE%>').val(srType);
			jQuery('#<%=viewOrCloseout%>').val(viewOrCloseOut);
			jQuery('#<%=ACTIVITYID%>').val(activityId);
			jQuery('#<%=TIMEZONE_OFFSET%>').val(timezoneOffset);
			jQuery('#hwInstallForm').submit();
		}
	
	
</script>
</c:if>
<c:if test="${accountSelectionDone eq 'false'}">
<script>
showAccountPopup();
</script>
</c:if>
<script>
ajaxSuccessFunction=function updateRequest(){
	var url="mass-upload";
	url+="?<%=LexmarkPPConstants.VENDORACCID_AVAIL%>=true";
	
	window.location.href=url;
}
</script>
