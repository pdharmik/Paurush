<%-- <script type="text/javascript"><%@ include file="/js/commonCHL&DeviceLoc.js"%></script> --%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<script type="text/javascript"><%@ include file="/js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@include file="../../css/tree/dhtmlxtree.css"%></style>
<%-- <script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script> --%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<portlet:renderURL var="showCHLTreePopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>

<%-- This section is added by Sourav --%>
<div id="dialogChlTree" style="display: none;">&nbsp;</div>
<%-- End of the section  --%>

<div class="left-nav" height="100%">
	<div class="left-nav-inner" style="overflow:hidden;">
		<!-- LEFT COLUMN CONTENT GOES HERE -->
		<div class="left-nav-header">
			<h3><spring:message code="requestInfo.heading.selectFrom"/></h3>
		</div>
		<ul class="filters">
			<li><a id= "linkViewAll" style="font-weight: bold;" onClick="reloadGrid('CATEGORY', 'VIEWALL');callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESALLASSETLIST%>');" href="javascript:void(0);" /><spring:message code="requestInfo.link.allAssets"/></a></li>
			<!-- <li><a id= "linkManagedDevices" onClick="reloadGrid('CATEGORY', 'DFM')" href="javascript:void(0);" /><spring:message code="orderSupplies.label.myBookmarkedAsset"/></a></li> -->
			<li><a id= "linkBookmarked" onClick="reloadGrid('CATEGORY', 'BOOKMARKED');callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTMYBOOKMARKEDASSETS%>');" href="javascript:void(0);" /><spring:message code="requestInfo.link.myBookmarkedAssets" /></a></li>
			
			
<!-- <li><div id='deviceLocLinkDiv'><a href="javascript:void(0);" onclick="return openTreeDiv('location');">Device Location</a></div></li>
<li><div id='chlLinkDiv'><a	href="javascript:void(0);" onclick="return openTreeDiv('chl');" id="chlTreeLink">Customer Hierarchy</a></div></li> -->


<%-- Below section is changed for chl and device location tree --%>			
<li><a id='deviceLocLinkDiv' href="javascript:void(0);" onclick="return openTreeDiv('location');"><spring:message code="requestInfo.link.deviceLocation"/></a></li>
<li id="chlLinkDiv"><a href="javascript:void(0);" onclick="return openTreeDiv('chl');" id="chlTreeLink"><spring:message code="requestInfo.link.customerHierarchy"/></a></li>
<%-- End --%>				
		</ul>
		<div id='chlInfoDiv' style="display:none;">
				<span><spring:message code="requestInfo.link.customerHierarchy"/></span><br/>
				<span id="chlNodeValueLabel"></span>
				<div style="float:right;padding-right:10px;"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" id="deleteChlBtn" height="15" width="15" /></div>
		</div>

		<div id='deviceLocInfoDiv' style="display:none;">
		<span><spring:message code="requestInfo.link.deviceLocation"/></span><br/>
		<span id="deviceLocNodeValueLabel"></span>
		<div style="float:right;padding-right:10px;"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" id="deleteDeviceLocBtn" height="15" width="15" /></div>
		</div>

</div>
	<div class="left-nav-footer">
		<!-- NO CONTENT HERE PLS -->
	</div>
</div>
<!-- Below div is for CHL popup -->
	
			<!-- tree overlay starts here -->
				<div class="leftOverlay rounded shadow">
				<div class="columnInner">
				<div style="float: right;">
					<img class="ui_icon_sprite ui-icon-closethick" src="<html:imagesPath/>transparent.png" height="15" width="15"  class="chlClose"  id="closeBtn_Chl" onclick="closeTreeDiv(this.id);" />
				</div>
					<div id="locationTreeDiv" style="position: absolute;">
						<h4><strong><spring:message code="requestInfo.heading.filterByLocation"/></strong></h4>													
						<div id="location_tree_container" style="display: none"></div>
						<div id="locationTreeLoadingNotification" class='treeLoading'>
		                    <img src="<html:imagesPath/>loading-icon.gif"/>
		                </div>
	                </div>
					<!-- <br> -->
					<div id="chlTreeDiv">
							<h4><strong><spring:message code="requestInfo.heading.filterByChl"/></strong></h4>
							<div id="chl_node_tree_container" style="display: none"></div>
			                <div id="reportingTreeLoadingNotification" class='treeLoading'>
			                    <img src="<html:imagesPath/>loading-icon.gif"/>
			                </div>
					
					</div>
					<div class="buttonContainerIn">
		              <button class="button_cancel chlClose" id="btn_assetLeft_cancel" onclick="closeTreeDiv(this.id);"><spring:message code="button.cancel"/></button>
		              <button class="button" id="btn_assetLeft_select" onclick="return showSelectedNode();"><spring:message code="button.select"/></button>
		          </div>
		          
		      
		          
		          
		</div>
		</div>
		
			
			<!-- tree overlay ends here -->

<!-- LEFT COLUMN  ends  HERE -->
<script type="text/javascript">
var viewCategory="VIEWALL";

var dialog_chTree;
var deviceLocNodeId='';
var deviceLocNodeValue='';
var chlNodeId='';
var chlNodeValue='';
var selectBtnFlag = false;
var deviceLocationFilterVal='';

var chlValue=''; var viewChlFlag = false; var viewLocationFlag = false; var locationValue=''; var level;

var dialog; var dialog_tree_url; var cId,cValue; var queue; var isChange; var isSearch; var found; 
var searchItem; var locationTree;

var resourceURL = "<portlet:resourceURL id='assetLiseLandingPageURL'></portlet:resourceURL>"; var deleteChlTree=false;
var deleteDeviceTree=false; var showSelected=false;

jQuery('.leftOverlay').hide();

/******* Below method would be called if the user clicked on Cancel/Close button *****/ 
function removeCHLFilter(){
	dialog_tree_url = null;
	locationTree = null;
	chlNodeId="0_0";
	chlValue="";
	deleteChlTree=true;
	jQuery('#chlNodeValueLabel').html("");
	jQuery("#chlInfoDiv").hide();
	jQuery("#chlLinkDiv").show();
	viewDeviceListByCHLforAsset();
	document.getElementById('allAsset').style.display = 'block';
    document.getElementById('bookmarkedAsset').style.display = 'none';
    document.getElementById('deviceLocationAsset').style.display = 'none';
    document.getElementById('customerHierarchyAsset').style.display = 'none';
}

function removeDeviceLocFilter(){
	dialog_tree_url = null;
	locationTree = null;
	level=0;//For all assets
	locationValue="";
	deleteDeviceTree=true;
	jQuery('#deviceLocNodeValueLabel').html("");
	jQuery("#deviceLocInfoDiv").hide();
 	jQuery("#deviceLocLinkDiv").show();
 	viewDeviceListByLocationforAsset();
 	document.getElementById('allAsset').style.display = 'block';
    document.getElementById('bookmarkedAsset').style.display = 'none';
    document.getElementById('deviceLocationAsset').style.display = 'none';
    document.getElementById('customerHierarchyAsset').style.display = 'none';
}

function closeTreeDiv(btnId){
	if(chl_cancel){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.HIERARCHYLEVEL%>','<%=LexmarkSPOmnitureConstants.CUSTOMERHIERARCHYPOPUPCLOSE%>');
	}else if(deviceloc_cancel){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICELOCATION%>','<%=LexmarkSPOmnitureConstants.DEVICELOCATIONCANCEL%>');
	}
	

	if(btnId!=null){			
		if(viewChlFlag){
			removeCHLFilter();		 
		}
		 else if(viewLocationFlag){ 
			 removeDeviceLocFilter();    	 
		}
	}
	jQuery('.leftOverlay').hide();	
}
function openTreeDiv(tree){
	deleteChlTree=false;
	deleteDeviceTree=false;
	showSelected = false;
	var lObj = jQuery('.left-nav');
    var position = lObj .position();
    jQuery('.leftOverlay').css('left',position.left);
    jQuery('.leftOverlay').css('top',position.top);
    jQuery('.leftOverlay').css('height',lObj.height());        
	jQuery('.leftOverlay').hide();	
	/****** Below section is changed for chl and device loc *******/
	/* jQuery('#chl_node_tree_container').hide();
	jQuery('#location_tree_container').hide(); */
	jQuery('#chl_node_tree_container').empty();
	jQuery('#location_tree_container').empty();
	/****** End of the section *************/
	
	if(tree=="chl"){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTCUSTOMERHIERARCHY%>');
		chl_cancel=true;
		deviceloc_cancel=false;
		jQuery('#locationTreeDiv').hide();
		/***Added below line*****/
		jQuery('#location_tree_container').empty();
		/*******End*******/		
		dialog_tree_url='${chlTreeXMLURL}';		
		initialiseCHLTree('chl_node_tree_container','reportingTreeLoadingNotification');	
		jQuery('#chl_node_tree_container').show();
		jQuery('#chlTreeDiv').show();
		
	}else{	
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTDEVICELOCATION%>');
		chl_cancel=false;
		deviceloc_cancel=true;	
		jQuery('#chlTreeDiv').hide();		
		/***Added below line*****/
		jQuery('#chl_node_tree_container').empty();
		/*******End*******/
		dialog_tree_url='${deviceLocationTreeXMLURL}';
		initialiseCHLTree('location_tree_container','locationTreeLoadingNotification');			
		jQuery('#location_tree_container').show();
		jQuery('#locationTreeDiv').show();
	}
			
	jQuery('.leftOverlay').show(function() {
		jQuery(this).resizable();
	});
	if (window.PIE) {
	document.getElementById("btn_assetLeft_select").fireEvent("onmove");
    document.getElementById("btn_assetLeft_cancel").fireEvent("onmove");
	}
    
	return false;

    $(".right-column").css({"min-height":"510px"});
}

/***Added by Sourav ********/ 
jQuery("#deleteChlBtn").click(function(){
	removeCHLFilter();
	/* dialog_tree_url = null;
	locationTree = null;
	chlNodeId="0_0";
	chlValue="";
	deleteChlTree=true;
	jQuery('#chlNodeValueLabel').html("");
	jQuery("#chlInfoDiv").hide();
	jQuery("#chlLinkDiv").show();
	viewDeviceListByCHLforAsset();
	document.getElementById('allAsset').style.display = 'block';
    document.getElementById('bookmarkedAsset').style.display = 'none';
    document.getElementById('deviceLocationAsset').style.display = 'none';
    document.getElementById('customerHierarchyAsset').style.display = 'none'; */
});

jQuery("#deleteDeviceLocBtn").click(function(){
	removeDeviceLocFilter();
	/* dialog_tree_url = null;
	locationTree = null;
	level=0;//For all assets
	locationValue="";
	deleteDeviceTree=true;
	jQuery('#deviceLocNodeValueLabel').html("");
	jQuery("#deviceLocInfoDiv").hide();
 	jQuery("#deviceLocLinkDiv").show();
 	viewDeviceListByLocationforAsset();
 	document.getElementById('allAsset').style.display = 'block';
    document.getElementById('bookmarkedAsset').style.display = 'none';
    document.getElementById('deviceLocationAsset').style.display = 'none';
    document.getElementById('customerHierarchyAsset').style.display = 'none'; */
});


function initialiseCHLTree(divId,loadingId)
{
	//Below code is to set the flags for Change in CHL location
	//Below queue is used for searching the node
	queue=new Array();
	queue.push('0_0');//adding the first node
	if(chlNodeId!="" && divId=='chl_node_tree_container'){
		isChange=true;
		isSearch=true;
		found=false;
		searchItem=chlNodeId;
	}
	var chlTreeXMLUrl=dialog_tree_url;
	var reportingTree = new dhtmlXTreeObject(divId,"100%","100%",0);
	reportingTree.setSkin('dhx_skyblue');
	reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
	reportingTree.enableTreeLines(true);
	
	if(jQuery.browser.msie) {
		reportingTree.enableIEImageFix(true);
	}	
	reportingTree.attachEvent("onXLS", function(tree,id) {
	if(id == null){
		jQuery("#"+loadingId).show();
		jQuery('#'+divId).css('height','0px');
	}
	else
		reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
	});
		
	reportingTree.attachEvent("onXLE", function(tree,id) {
	jQuery("#"+loadingId).hide();		
	jQuery('#'+divId).css('height','460px');
	if(isChange == true){
		var popV=queue.shift();
		var childItems=reportingTree.hasChildren(popV);
		while(childItems==0){
			popV=queue.shift();
			childItems=reportingTree.hasChildren(popV);
			if(childItems==true)
				break;
		}
		if(popV==searchItem){
			found=true;
			isChange=false;
		}
		else{
			reportingTree.openItem(popV);
			}
		}
	
		if(found==true && isSearch==true){
		
		reportingTree.selectItem(searchItem,false);
		reportingTree.focusItem(searchItem);
		isSearch=false;
		}
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	});	
	
	reportingTree.attachEvent("onOpenEnd",function(id){
		if(isChange == true){
			var subItems = reportingTree.getAllSubItems(id);			
			if(subItems.search(searchItem)== -1){
				var tempArr= new Array();
				var dataArr=subItems.split(",");
				for(i=0;i<dataArr.length;i++){
					tempArr.push(dataArr[i]);
				}
				//attach rest from the queue
				for(i=0;i<queue.length;i++){
					tempArr.push(queue[i]);
				}
				queue=tempArr;
			}else{				
				found=true;
				isChange=false;
			}
		}
		});
	reportingTree.setXMLAutoLoading(chlTreeXMLUrl);
	reportingTree.loadXML(chlTreeXMLUrl);
	locationTree=reportingTree;	
};
  
function viewDeviceListByLocation(devicelevel,devicelocationId){
	locationValue=devicelocationId;	
    level=devicelevel;	    
    viewLocationFlag=true;
    viewChlFlag=false;
    chlNodeId='';
    chlValue='';
    
    /****Calling this method to dynamically change the grid load on selecting a node******/
    viewDeviceListByLocationforAsset();
}

function viewDeviceListByCHL(chlNode, chlNodeValue) {
	
	if(chlNode!="" && chlNode!="0_0"){
		jQuery('#errorDiv').empty();
		 jQuery('#errorDiv').hide();		
	}
	 viewChlFlag = true;
	 viewLocationFlag = false;
	chlNodeId=chlNode;
	chlValue=chlNodeValue;
	locationValue='';
	level='';
	
	/****Calling this method to dynamically change the grid load on selecting a node******/
	viewDeviceListByCHLforAsset();	
};

function viewDeviceListByCHLforAsset() {
	var targetURL;
    var viewType;
    var breadcrumb;
    if (chlNodeId == '0_0') {
    	
    	targetURL = resourceURL + "&view=viewAll";
    	viewType = 'CATEGORY';
    	breadcrumb = 'VIEWALL';
    } else {
        targetURL = resourceURL + "&chlNodeId=" + chlNodeId;
        viewType = 'CHL';
        breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' + '<spring:message code="tree.label.customerHierarchy"/>' +
        ':' + chlValue;
    }
    targetURL = updateGridURL(targetURL, assetlistGrid.getSortColByOffset(), assetlistGrid.a_direction, assetlistGrid.filterValues);
    updateLinkBreadcrumb(viewType, breadcrumb);
    customizedGridURL = targetURL;
    
    /****Added By Sourav ******/
    if(!deleteChlTree && showSelected){
    jQuery("#chlNodeValueLabel").html(chlValue);    
    jQuery("#deviceLocInfoDiv").hide();    
	jQuery("#chlInfoDiv").show();
	jQuery("#deviceLocLinkDiv").show();	
    } else{
    assetlistGrid.clearAndLoad(targetURL);
    }   
    document.getElementById('bookmarkedAsset').style.display = 'none';
    document.getElementById('allAsset').style.display = 'none';
    document.getElementById('deviceLocationAsset').style.display = 'none';
    document.getElementById('customerHierarchyAsset').style.display = 'block';
};

function showSelectedNode(){
	/*******On click of the select button, the last selected node would be shown on the left hand side****/
 showSelected = true;	 
 if(viewChlFlag){
	 <%-- callOmnitureAction('<%=LexmarkSPOmnitureConstants.HIERARCHYLEVEL%>', '<%=LexmarkSPOmnitureConstants.SELECTCUSTOMERHIERARCHYBUTTON%>'); --%>
	 viewDeviceListByCHLforAsset();
	 
}
 else if(viewLocationFlag){ 
	 <%-- callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICELOCATION%>', '<%=LexmarkSPOmnitureConstants.DEVICELOCATIONSELECT%>'); --%>
	viewDeviceListByLocationforAsset();    	 
}
    closeTreeDiv();
}

function reloadGrid(type, value) {
	//changes for defect 5117 starts
    
    jQuery('#deviceLocNodeValueLabel').html("");
	jQuery("#deviceLocInfoDiv").hide();
 	jQuery("#deviceLocLinkDiv").show();

 	
    
    jQuery('#chlNodeValueLabel').html("");
	jQuery("#chlInfoDiv").hide();
	jQuery("#chlLinkDiv").show();
    
    //changes for defect 5117 Ends
	updateLinkBreadcrumb(type, value);
	viewCategory = value;
	customizedGridURL = updateGridURL(getURLWithViewType('CATEGORY', value), assetlistGrid.getSortColByOffset(), assetlistGrid.a_direction, assetlistGrid.filterValues);
	assetlistGrid.clearAndLoad(customizedGridURL);
};

function updateLinkBreadcrumb(type, value) {
 var anchors = document.links;
 for (i=0; i<anchors.length; i++) {
        anchors[i].style.fontWeight = '';
      }

      if (type == 'CATEGORY') {
	        var link;
	        var currentSelection;
	        if(value == 'VIEWALL') {
	            link = document.getElementById('linkViewAll');
	            currentSelection = '<spring:message code="deviceFinder.title.results"/>';
	        } else if(value == 'BOOKMARKED') {
		        link = document.getElementById('linkBookmarked');
	            currentSelection = '<spring:message code="orderSupplies.label.myBookmarkedAsset"/>';
	        } link.style.fontWeight = 'Bold';
      }
  };
  
  function viewDeviceListByLocationforAsset() {
      // text and id of location node are same
      var country;
      var provinceOrState;
      var city;
      var breadcrumb;
      var viewType;
      var targetURL;
      // Options of level: 0 means root node, 1 means Country, 21 means Province,
      // and 22 means State, 31 means City in Province, and 32 means City in State.
      switch(level)
      {
      case 0:
          targetURL = resourceURL + "&view=viewAll";          
          viewType = 'CATEGORY';
          breadcrumb = 'VIEWALL';
          break;
      case 1:
      	targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + locationValue;
      	viewType = 'DEVICE_LOCATION';
        breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                  '<spring:message code="tree.label.deviceLocation"/>' + ':' + locationValue;
        deviceLocationFilterVal= locationValue;
      	break;
      case 21:
          country = locationTree.getParentId(locationValue);
          targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + locationValue;
          viewType = 'DEVICE_LOCATION';
          breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                  '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                  locationValue + ',' + country;
          deviceLocationFilterVal=country + ","+locationValue;
          break;
      case 22:
          country = locationTree.getParentId(locationValue);
          targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&state=" + locationValue;
          viewType = 'DEVICE_LOCATION';
          breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                  '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                  locationValue + ',' + country;
          deviceLocationFilterVal = country + ","+locationValue;
          break;
      case 31:
      	provinceOrState = locationTree.getParentId(locationValue);
      	country = locationTree.getParentId(provinceOrState);
        
        targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + provinceOrState + "&city=" + locationValue;
        
        viewType = 'DEVICE_LOCATION';
        breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                  '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                  locationValue + ' ' + provinceOrState + ',' + country;
        deviceLocationFilterVal = country + ","+provinceOrState+","+locationValue;
         
        break;
      case 32:          
      	  provinceOrState = locationTree.getParentId(locationValue);      	  
          country = locationTree.getParentId(provinceOrState);          
          targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&state=" + provinceOrState + "&city=" + locationValue;
          viewType = 'DEVICE_LOCATION';
          breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                  '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                  locationValue + ' ' + provinceOrState + ',' + country;
          deviceLocationFilterVal = country + ","+provinceOrState+","+locationValue;
          break;
          
        //Added for CI-6 Start  : PARTHA  
      case 33:
      	  country = locationTree.getParentId(locationValue);
          targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&city=" + locationValue;
          viewType = 'DEVICE_LOCATION';
          breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                  '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                  locationValue + ',' + country;
          deviceLocationFilterVal = country + ","+","+locationValue;
          break;
       // Added for CI-6 End : PARTHA
      }
      targetURL = updateGridURL(targetURL, assetlistGrid.getSortColByOffset(), assetlistGrid.a_direction, assetlistGrid.filterValues);
      updateLinkBreadcrumb(viewType, breadcrumb);
      customizedGridURL = targetURL;
      
      /********Added By Sourav ********/
      if(! deleteDeviceTree && showSelected){
	  jQuery('#chlInfoDiv').hide();
	 // jQuery("#deviceLocNodeValueLabel").html(locationValue);
	 jQuery("#deviceLocNodeValueLabel").html(deviceLocationFilterVal);
	  jQuery("#deviceLocInfoDiv").show();
	  jQuery("#chlLinkDiv").show();	  
      }else{
      assetlistGrid.clearAndLoad(targetURL);
      }
      document.getElementById('bookmarkedAsset').style.display = 'none';
      document.getElementById('allAsset').style.display = 'none';
      document.getElementById('deviceLocationAsset').style.display = 'block';
      document.getElementById('customerHierarchyAsset').style.display = 'none';
      
  };
  dhtmlxError.catchError("LoadXML", myErrorHandler); 
  dhtmlxError.catchError("ALL",myErrorHandler);
  
</script>