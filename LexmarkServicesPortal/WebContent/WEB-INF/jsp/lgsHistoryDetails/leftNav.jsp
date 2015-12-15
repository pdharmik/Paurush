<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.ALL_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SUPPLY_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.CHANGE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.SERVICE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.HARDWARE_REQUESTS"%>
<%@page import=" static com.lexmark.services.util.ChangeMgmtConstant.B2B_REQUESTS"%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>

<script type="text/javascript"><%@ include file="/js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@include file="../../css/tree/dhtmlxtree.css"%></style>
<portlet:renderURL var="showCHLTreePopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />


<div class="left-nav">
	<div class="left-nav-inner">
		<!-- LEFT COLUMN CONTENT GOES HERE -->
		<div class="left-nav-header" id="requestTypeHeader">
			<h3><spring:message code="requestInfo.requestHistory.heading.requestType" /></h3>
		</div>
		<ul class="filters" id="allHistoryLinks">
		
		
		<!-- Style added to links for CI BRD13-10-12 -->
		 <c:if test="${ not empty sessionScope.userAccessMapForSr}"> 		
					 	 
					 	
			 	  <c:if test='${sessionScope.userAccessMapForSr ["SHOW ALL REQUEST"]=="true"}'> 
			 	 	<li  id="<%=ALL_REQUESTS%>" style="overflow:hidden;" class="word-wrap-break-word"><a href="#" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTALLREQUEST%>');"><spring:message code="requestInfo.requestHistory.heading.allRequestTypes" /></a>
					</li>	
			 	 </c:if>			 	  
			 	 <c:if test='${sessionScope.userAccessMapForSr["SHOW CHANGE MGMT REQUEST"]=="true"}'> 
			 	 	<li  id="<%=CHANGE_REQUESTS%>" style="overflow:hidden;" class="word-wrap-break-word"><a href="#" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTCHANGEREQUEST%>');"><spring:message code="requestInfo.requestHistory.heading.changeRequests" /></a>
					</li>	
			 	 </c:if>
			 	 <c:if test='${sessionScope.userAccessMapForSr["SHOW HARDWARE REQUEST"]=="true"}'>
				 <!--c:if test='${sessionScope.userAccessMapForSr["SHOW SERVICE REQUEST"]=="true"}'--> 
			 	 	<li  id="<%=HARDWARE_REQUESTS%>" style="overflow:hidden;" class="word-wrap-break-word"><a href="#" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTHARDWAREEQUEST%>');"><spring:message code="requestInfo.header.hardwareReq"/></a>
					</li>	
			 	 </c:if>
			 	<c:if test='${sessionScope.userAccessMapForSr["SHOW SERVICE REQUEST"]=="true"}'> 
			 	 	<li  id="<%=SERVICE_REQUESTS%>" style="overflow:hidden;" class="word-wrap-break-word"><a href="#" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTSERVICEREQUEST%>');"><spring:message code="requestInfo.requestHistory.heading.serviceRequests" /></a>
					</li>	
			 	 </c:if>
			 	 
				 <c:if test='${sessionScope.userAccessMapForSr["SHOW SUPPLIES REQUEST"]=="true"}'> 
			 	 	<li  id="<%=SUPPLY_REQUESTS%>" style="overflow:hidden;" class="word-wrap-break-word"><a href="#" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTSUPPLYREQUEST%>');"><spring:message code="requestInfo.requestHistory.heading.supplyRequests" /></a>
					</li>	
			 	 </c:if>
			  <c:if test='${sessionScope.userAccessMapForSr["SHOW B2B FLAG"]=="true"}'> 
			 	 	<li  style="d" id="<%=B2B_REQUESTS%>" style="overflow:hidden;" class="word-wrap-break-word"><a href="#" onClick="callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTB2BREQUEST%>');"><spring:message code="requestInfo.requestHistory.heading.B2BRequests" /></a>
			 
					</li>	
			 	 </c:if>	 
			
		 </c:if>
		
		</ul>

	    <!-- Structure Changed for CI BRD13-10-12 -->
		<div class="left-nav-header inlineTitle">
			<h3><spring:message code="requestInfo.requestHistory.heading.filterByView" />:</h3>
		</div>
		<ul id="radioFilters" class="filters radio">
			<li style="overflow:hidden;"><div class="div-style56"><input type="radio" name="filterByView" id="myRequest" value="myRequest" /> </div><div class="div-style57">
					  <label for="myRequest"><spring:message code="requestInfo.options.myRequests" /></label></div>
			</li>
			<li style="overflow:hidden;"><div class="div-style56"><input type="radio" name="filterByView" id="bookmarkedAsset" value="bookmarkedAsset"/></div><div class="div-style58">
					<label for="bookmarkedAsset"><spring:message code="requestInfo.options.bookmarkedAssetRequests" /></label></div>
			</li style="overflow:hidden;">
			<li style="overflow:hidden;"><div class="div-style56"><input type="radio" name="filterByView" id="showAll"  value="showAll" checked="checked"/></div><div class="div-style57"> <label
				for="showAll"><spring:message code="requestInfo.options.showAll" /></label></div>
			</li>
		</ul>


		<div class="left-nav-header inlineTitle">
			<h3><spring:message code="requestInfo.requestHistory.heading.filterByDateRange" />:</h3>
		</div>
		<div id="dateErrorDiv" style="display: none;" class="color-red"></div>
		 <ul class="filters">
              <li>
                <label for="from" class="word-wrap-break-word"><spring:message code="requestInfo.requestHistory.label.from" />:</label>
                <span>
               <input id="localizedBeginDate" type="text" size=10  class="w100" readOnly="readonly"></input>
                <img src="<html:imagesPath/>transparent.png" id="imgLocalizedBeginDate" class="ui_icon_sprite calendar-icon"  title="Select a Date"/></span></li>
              <li>
                <label for="to" class="word-wrap-break-word"><spring:message code="requestInfo.requestHistory.label.to" />:</label>
                <span>
               <input id="localizedEndDate" type="text" size=10  class="w100" readOnly="readonly"></input>
                <img src="<html:imagesPath/>transparent.png" id="imgLocalizedEndDate" class="ui_icon_sprite calendar-icon"   /></span></li>
            </ul>
            <div class="buttonContainerIn">
              <button class="button" id="dateFilterBtn" onClick = "callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.REQUESTHISTORYLISTGOBUTTON%>');"><spring:message code="button.go" /></button>
            </div>
            

		
		<div class="left-nav-header">
			<h3><spring:message code="requestInfo.requestHistory.heading.filter" />:</h3>
		</div>
			
			<ul class="filters">
              <li id='deviceLocLinkDiv'><a href="javascript:void(0);"	onclick="return openTreeDiv('location');callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.DEVICELOCATION%>');"><spring:message code="requestInfo.link.deviceLocation" /></a></li>
              <li id='chlLinkDiv'> <a	href="javascript:void(0);" onclick="return openTreeDiv('chl');callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.HIERARCHYLEVEL%>');" id="chlTreeLink"><spring:message code="requestInfo.link.customerHierarchy" /></a>
              </li>
            </ul>
            
			
			<div id='deviceLocInfoDiv' style="display:none;">
					<span><spring:message code="requestInfo.requestHistory.label.deviceLocation" /></span><br/>
					<span id="deviceLocNodeValueLabel"></span>
					<div class="div-style39"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" id="deleteDeviceLocBtn" height="18" width="18" /></div>
				</div>
				<input type="hidden" id="deviceLocNodeId" value="" />
			<br/>
			
			
			<div id='chlInfoDiv' style="display:none;">
				<span><spring:message code="requestInfo.requestHistory.label.customerHierarchy" /></span><br/>
				<span id="chlNodeValueLabel"></span>
				<div class="div-style39"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" id="deleteChlBtn" height="18" width="18" /></div>
			</div>
			<input type="hidden" id="chlNodeId" value="" />
			<input type="hidden" id="chlNodeValue" value="" />
			
				

</div>
	<div class="left-nav-footer">
		<!-- NO CONTENT HERE PLS -->
	</div>
</div>
<!-- Below div is for CHL popup -->
	
			<!-- tree overlay starts here -->
				<div class="leftOverlay rounded shadow" style="position: absolute;">
				<div class="columnInner">
				<div class="float-right">
					<img class="chlClose ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png"  onclick="closeTreeDiv();" />
				</div>
				<div id="errorDiv" style="display: none;" class="color-red"></div>
					<div id="locationTreeDiv">
						<h4><strong><spring:message code="requestInfo.heading.filterByLocation" /></strong></h4>						
						<div id="locationTreeLoadingNotification_loc" class='treeLoading'>
		                    <img src="<html:imagesPath/>loading-icon.gif"/>
		                </div>
		                <div id="location_tree_container" style="display: none"></div>
	                </div>
					
					<div id="chlTreeDiv">
							<h4><strong><spring:message code="requestInfo.heading.filterByChl" /></strong></h4>						
							<div id="locationTreeLoadingNotification_chl" class='treeLoading'>
			                    <img src="<html:imagesPath/>loading-icon.gif"/>
			                </div>
			                <div id="chl_node_tree_container" style="display: none"></div>			               
					</div>
					<div class="buttonContainerIn">
		              <button class="button_cancel chlClose" id="btnCancel" onclick="closeTreeDiv();"><spring:message code="button.cancel" /></button>
		              <button class="button" onclick="return showSelectedNode();" id="btnSelect"><spring:message code="button.select" /></button>
		          </div>
				</div>
				</div>
			<!-- tree overlay ends here -->

<!-- LEFT COLUMN  ends  HERE -->
<script type="text/javascript">
var chlQueryParams={
		urlParameters:["chlNodeId"],
		setTreeObject:function(chlTreeObject){
									this["treeObject"]=chlTreeObject;
									},
		loadUrl:function(){
					var url='<portlet:resourceURL id="chlTreeXMLURL"/>&timezoneOffset='+timezoneOffset;
					return url;
				},
		resetParams:function(){
					for(i=0;i<this.urlParameters.length;i++)
						this[this.urlParameters[i]]="";
				},
		copyToOriginalValues: function(){
						for(i=0;i<this.urlParameters.length;i++)
							this[this.urlParameters[i]]=this["temp_"+this.urlParameters[i]];
						this.chlNodeValue=this.temp_chlNodeValue;
					},
		treeObject:null,
		getFilterUrl:getTreeURLFilters,
		initializeCHLTree:initialiseTree,

		isCurrentlyOpened: false,
		divId:"chl_node_tree_container",
		loadingId:"locationTreeLoadingNotification_chl",
		isFilterApplied:false,
		chlNodeId:"",
		chlNodeValue:"",
		/*Below variables are for temporary purpose
		and used for the value selection from popup
		*/
		temp_chlNodeId:"",
		temp_chlNodeValue:"" 
		
};
var deviceLocationQueryParams={
	urlParameters:	["locationId","country","province","state","city"],
	
	setTreeObject:	function(deviceLocationTreeObject){
						this["treeObject"]=deviceLocationTreeObject;
					},
	loadUrl:		function(){
						var url='<portlet:resourceURL id="deviceLocationXMLURLforRequest"/>&timezoneOffset='+timezoneOffset;
						return url;
					},
	resetParams:	function(){
						for(i=0;i<this.urlParameters.length;i++)
							this[this.urlParameters[i]]="";
					},
	initializeDeviceLocationTree:	initialiseTree,

	copyToOriginalValues: function(){
						for(i=0;i<this.urlParameters.length;i++)
							this[this.urlParameters[i]]=this["temp_"+this.urlParameters[i]];
						this.deviceLocNodeValue=this.temp_deviceLocNodeValue;
					},
	treeObject: null,
	getFilterUrl:	getTreeURLFilters,
	isCurrentlyOpened: false,
	divId:			"location_tree_container",
	loadingId:		"locationTreeLoadingNotification_loc",
	isFilterApplied:false,
	deviceLocNodeValue:"",
	locationId:"",
	country:"",
	province:"",
	state:"",
	city:"",
	
	/*Below variables are for temporary purpose
	and used for the value selection from popup
	*/
	temp_deviceLocNodeValue:"",
	temp_locationId:"",
	temp_country:"",
	temp_province:"",
	temp_state:"",
	temp_city:""
	
	
};

function getTreeURLFilters(){
	var xmlURL="";
		for(i=0;i<this.urlParameters.length;i++)
		{
			if(this.isCurrentlyOpened==true){
				if(this["temp_"+this.urlParameters[i]]!="")	
					xmlURL+="&"+this.urlParameters[i]+"="+this["temp_"+this.urlParameters[i]];
			}if(this.isFilterApplied==true){
					if(this[this.urlParameters[i]]!="")
						xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
				}
		}
	return xmlURL; 
}
var dialog_chTree;

var deviceLocNodeId='';
var deviceLocFilterString='';
var deviceLocNodeValue='';
var prevChlNodeId='';
var chlNodeValue='';
var viewChlFlag = false;
var viewLocationFlag = false;
var targetURL;
var prevHierarchyFilter = '';
var chlNodeId='';


var dialog;
var dialog_tree_url;

var locationTree;

jQuery('.leftOverlay').hide();

function closeTreeDiv(){
	if(deviceLocationQueryParams.isCurrentlyOpened==true){
		deviceLocationQueryParams.isCurrentlyOpened=false;
		}
	else if(chlQueryParams.isCurrentlyOpened==true){
		chlQueryParams.isCurrentlyOpened=false;
		}
	
	reloadGrid();
	jQuery('#dateErrorDiv').hide();
	jQuery('.leftOverlay').hide();
}
var lObj = jQuery('.left-nav');
var position = lObj.position();
jQuery('.leftOverlay').css('left',position.left);
jQuery('.leftOverlay').css('top',position.top);
jQuery('.leftOverlay').css('height',lObj.height());

function openTreeDiv(tree){

	if(tree=='location'){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.DEVICELOCATIONSELECT%>');
		}else{
			callOmnitureAction('<%=LexmarkSPOmnitureConstants.REQUESTHISTORY%>','<%=LexmarkSPOmnitureConstants.SELECTCUSTOMERHIERARCHYBUTTON%>');
			}
	
	if(tree=="chl"){
		
		jQuery('#locationTreeDiv').hide();
		chlQueryParams.isCurrentlyOpened= true;
		if(chlQueryParams.treeObject==null)
			chlQueryParams.initializeCHLTree();
		else
			chlQueryParams.treeObject.clearSelection();
		// chl_node_tree_container	
		jQuery('#'+chlQueryParams.divId).show();
		jQuery('#chlTreeDiv').show();
	}else{
		jQuery('#chlTreeDiv').hide();
		deviceLocationQueryParams.isCurrentlyOpened= true;
		
		if(deviceLocationQueryParams.treeObject==null)
			deviceLocationQueryParams.initializeDeviceLocationTree();
		else
			deviceLocationQueryParams.treeObject.clearSelection();
		jQuery('#'+deviceLocationQueryParams.divId).show();
		jQuery('#locationTreeDiv').show();	
	}
			
	var lObj = jQuery('.left-nav');
    var position = lObj .position();

      jQuery('.leftOverlay').show(function() {
          jQuery(this).css('left',position.left);
          jQuery(this).css('top',position.top);
          jQuery(this).css('height',lObj.height());
          jQuery("#"+chlQueryParams.divId).css('height',"480px");
          jQuery(this).resizable();
      });

      if (window.PIE) {
          document.getElementById("btnSelect").fireEvent("onmove");
          document.getElementById("btnCancel").fireEvent("onmove");
	}

      $(".right-column").css({"min-height":"510px"});
}

jQuery("#deleteChlBtn").click(function(){
	jQuery('#chlNodeValueLabel').empty();
	jQuery('#chlInfoDiv').hide();
	jQuery("#chlLinkDiv").show();
	chlQueryParams.isFilterApplied=false;
	reloadGrid();
	jQuery('#dateErrorDiv').hide();
});
jQuery("#deleteDeviceLocBtn").click(function(){
 	jQuery('#deviceLocNodeValueLabel').empty();
 	jQuery('#deviceLocInfoDiv').hide();
 	jQuery("#deviceLocLinkDiv").show();
	deviceLocationQueryParams.isFilterApplied=false;
	reloadGrid();
 	jQuery('#dateErrorDiv').hide();
 });
 function showSelectedNode(){
	 if(chlQueryParams.isCurrentlyOpened==true){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.HIERARCHYLEVEL%>', '<%=LexmarkSPOmnitureConstants.SELECTCUSTOMERHIERARCHYBUTTON%>');
		 if(chlQueryParams.temp_chlNodeId=="" || chlQueryParams.temp_chlNodeId=="0_0"){
			 jQuery('#errorDiv').show();
			//changes for the AIR LEX:AIR00091951 by AMS Manish
			  jQuery('#errorDiv').html("<spring:message code='requestInfo.requestHistory.label.customerHierarchyMessage'/>");
			// jQuery('#errorDiv').html("Please select a node");
			return false;
		 }else{
				 chlQueryParams.isCurrentlyOpened=false;
				 chlQueryParams.isFilterApplied=true;
				 chlQueryParams.copyToOriginalValues();
				 jQuery('#errorDiv').empty();
				 jQuery('#errorDiv').hide();
			 	 jQuery("#chlNodeValueLabel").html(chlQueryParams.chlNodeValue);
				 jQuery("#chlLinkDiv").hide();
				 jQuery('#chlInfoDiv').show();	
				jQuery('.leftOverlay').hide();
	 }
	 }else if(deviceLocationQueryParams.isCurrentlyOpened==true){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICELOCATION%>', '<%=LexmarkSPOmnitureConstants.DEVICELOCATIONSELECT%>');
		 if(deviceLocationQueryParams.temp_deviceLocNodeValue ==""){
			 jQuery('#errorDiv').show();
			//changes Pending for the AIR LEX:AIR00091951 by AMS Manish
			jQuery('#errorDiv').html("<spring:message code='requestInfo.requestHistory.label.customerHierarchyMessage'/>");
			//jQuery('#errorDiv').html("Please select a node");
			 return false;
		 }else{		 
			 deviceLocationQueryParams.isCurrentlyOpened=false;
			 deviceLocationQueryParams.isFilterApplied=true; 
			 deviceLocationQueryParams.copyToOriginalValues();
		 jQuery("#deviceLocLinkDiv").hide();
		 jQuery("#deviceLocNodeValueLabel").html(deviceLocationQueryParams.deviceLocNodeValue);
		 jQuery("#deviceLocInfoDiv").show();
		 jQuery('.leftOverlay').hide();
		 
		 }
	 }
 }
 function viewDeviceListByCHL(chlNode, chlValue) {
		
		if(chlNode!="" && chlNode!="0_0"){
			jQuery('#errorDiv').empty();
			 jQuery('#errorDiv').hide();
				
		}
		chlQueryParams.temp_chlNodeId=chlNode;
		chlQueryParams.temp_chlNodeValue=chlValue;
		
		reloadGrid();
	};
	
 function viewDeviceListByLocation(level, locationValue) {
   var provinceOrState;
	deviceLocationQueryParams.resetParams();
   jQuery('#errorDiv').empty();
   jQuery('#errorDiv').hide();
   switch(level)
   {
   case 0:
	
	   deviceLocationQueryParams.temp_deviceLocNodeValue = "";
     
       break;
   case 1:
	   	deviceLocationQueryParams.temp_deviceLocNodeValue = locationValue;
	   	deviceLocationQueryParams.temp_locationId=locationValue;
	   	deviceLocationQueryParams.temp_country=locationValue;
	   	    
   	break;
   case 21:
	   deviceLocationQueryParams.temp_country = deviceLocationQueryParams.treeObject.getParentId(locationValue);    
   		deviceLocationQueryParams.temp_deviceLocNodeValue = deviceLocationQueryParams.temp_country + ","+locationValue;
   		deviceLocationQueryParams.temp_locationId=locationValue;
   		deviceLocationQueryParams.temp_province=locationValue;
       break;
   case 22:
	   deviceLocationQueryParams.temp_country = deviceLocationQueryParams.treeObject.getParentId(locationValue);
       deviceLocationQueryParams.temp_deviceLocNodeValue = deviceLocationQueryParams.temp_country + ","+locationValue;
       deviceLocationQueryParams.temp_locationId=locationValue;
       deviceLocationQueryParams.temp_state=locationValue;
       
       break;
   case 31:
   	   provinceOrState = deviceLocationQueryParams.treeObject.getParentId(locationValue);   	  
   	   deviceLocationQueryParams.temp_country = deviceLocationQueryParams.treeObject.getParentId(provinceOrState);
       deviceLocationQueryParams.temp_deviceLocNodeValue = deviceLocationQueryParams.temp_country + ","+provinceOrState+","+locationValue;
       deviceLocationQueryParams.temp_locationId=locationValue;
       deviceLocationQueryParams.temp_province=provinceOrState;
       deviceLocationQueryParams.temp_city=locationValue;
       
       break;
   case 32:
   	    provinceOrState = deviceLocationQueryParams.treeObject.getParentId(locationValue);
   		deviceLocationQueryParams.temp_country = deviceLocationQueryParams.treeObject.getParentId(provinceOrState);       
       deviceLocationQueryParams.temp_deviceLocNodeValue = deviceLocationQueryParams.temp_country + ","+provinceOrState+","+locationValue;
       deviceLocationQueryParams.temp_locationId=locationValue;
       deviceLocationQueryParams.temp_state=provinceOrState;
       deviceLocationQueryParams.temp_city=locationValue;
       
       break;
     //Added for CI-6 Start  : PARTHA  
   case 33:
	   deviceLocationQueryParams.temp_country = deviceLocationQueryParams.treeObject.getParentId(locationValue);
       deviceLocationQueryParams.temp_deviceLocNodeValue = deviceLocationQueryParams.temp_country + ","+","+locationValue;
       deviceLocationQueryParams.temp_locationId=locationValue;
       deviceLocationQueryParams.temp_city=locationValue;
       break;
    // Added for CI-6 End : PARTHA
   }
   reloadGrid();
};

function initialiseTree()
{
		
	var reportingTree = new dhtmlXTreeObject(this.divId,"100%","100%",0);
	var loadingId=this.loadingId;
	var divId=this.divId;
	reportingTree.setSkin('dhx_skyblue');
	reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
	reportingTree.enableTreeLines(true);
	
	
		if(jQuery.browser.ie) {
		reportingTree.enableIEImageFix(true);
	}
		
		reportingTree.attachEvent("onXLS", function(tree,id) {
		if(id == null){
			jQuery('#'+loadingId).show();
			jQuery('#'+divId).css('height','0px');
		}
		else
			reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
	});
		
	reportingTree.attachEvent("onXLE", function(tree,id) {
	
		jQuery('#'+loadingId).hide();
		jQuery('#'+divId).css('height','460px');
		
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});
	
	reportingTree.setXMLAutoLoading(this.loadUrl());
	this.setTreeObject(reportingTree);
	reportingTree.loadXML(this.loadUrl());

}
jQuery(document).ready(function() {
	if (window.PIE) {
        document.getElementById("dateFilterBtn").fireEvent("onmove");
	}
});
var alphaOnly = /[A-Z]/g;
function restrictCharacters(myfield, e, restrictionType) {

	if (!e) var e = window.event
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	var character = String.fromCharCode(code);

	// if they pressed esc... remove focus from field...
	if (code==27) { this.blur(); return false; }		

	if (!e.ctrlKey && code!=9 && code!=8 && code!=36 && code!=37 && code!=38 && (code!=39 || (code==39 && character=="'")) && code!=40) {

		if (character.match(restrictionType)) {
			return true;
		} else {
			return false;
		}

	}

}
</script>