<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%-- <script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@ include file="../../css/tree/dhtmlxtree.css" %></style>
<!-- added for MPS -->
<script type="text/javascript"><%@ include file="../../../js/commonCHL&DeviceLoc.js"%></script> --%>
<script type="text/javascript"><%@ include file="/js/tree/dhtmlxtree.js"%></script>
<%-- <style type="text/css"><%@include file="../../css/tree/dhtmlxtree.css"%></style> --%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>

<portlet:renderURL var="showCHLTreePopUp" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
<portlet:param name="action" value="showCHLTreePopUp"></portlet:param>
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div id="dialogChlTree" style="display: none;">&nbsp;</div>

		<div class="left-nav" height="100%">
			<div class="left-nav-inner" style="overflow:hidden;">
				<div class="left-nav-header" >
					<h3><spring:message code="deviceFinder.title.filters"/></h3>
				</div><!-- left-nav-header -->
				<ul class="filters">
					<li><a id= "linkViewAll" class="font-weight-bold" onClick="reloadGrid('CATEGORY', 'VIEWALL')" href="javascript:void(0);" >
					<spring:message code="deviceFinder.label.allDevices" /></a></li>
					
					<%-- This is not needed as part of MPS Implementation --%>
					
					<%-- <c:if test="${accountTypeMPSFlag=='DFM'}" > 
						<li><a id= "linkManagedDevices" onClick="reloadGrid('CATEGORY', 'DFM')" href="javascript:void(0);" /><spring:message code="deviceFinder.label.managedDevices" /></a></li>
					</c:if>
					<c:if test="${accountTypeCSSFlag=='CSS'}" > 
						<li><a id= "linkNonManagedDevices" onClick="reloadGrid('CATEGORY', 'CSS')" href="javascript:void(0);" /><spring:message code="deviceFinder.label.nonManagedDevices" /></a></li>
					</c:if> --%>
					
					
					<li><a id= "linkBookmarked" onClick="reloadGrid('CATEGORY', 'BOOKMARKED')" href="javascript:void(0);" /><spring:message code="requestInfo.link.myBookmarkedAssets" /></a></li>
					
					<!-- added for MPS -->
					<li><a href="#" id="chlTreeLink_device" onclick="return openTreeDiv('deviceLoc');"><spring:message code="requestInfo.link.deviceLocation"/></a></li>
					<li><a href="#" id="chlTreeLink" onclick="return openTreeDiv('chl');"><spring:message code="requestInfo.link.customerHierarchy"/></a></li>
					
				</ul>
				<br>
				<div id='chlInfoDiv' style="display:none;">
			         <!-- Changes Need to be done for Russian languages AMS Manish-->
			         <!-- <span>Customer Hierarchy</span><br/> -->
			           <span><spring:message code="requestInfo.link.customerHierarchy"/></span><br/>
			         
					<span id="chlNodeValueLabel"></span>
					<div class="div-style39"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" height="15px" width="15px" id="deleteChlBtn" onClick="removeFilters(this.id);"/></div>
				</div>

				<div id='deviceLocInfoDiv' style="display:none;">
				<!-- <span>Device Location </span><br/> -->
					<span><spring:message code="requestInfo.link.deviceLocation"/></span><br/>
					<span id="deviceLocNodeValueLabel"></span>
					<div class="div-style39"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" height="15px" width="15px" id="deleteDeviceLocBtn" onClick="removeFilters(this.id);"/></div>
				</div>
                
			</div><!-- left-nav-inner -->
			<div class="left-nav-footer">
			
			</div><!-- left-nav-footer -->
		</div><!-- left-nav -->
		
		<!-- Below div is for CHL popup -->
		<!-- <div id="dialogChlTree" style="display: none;">&nbsp;</div> -->
		<!-- Below div is for CHL popup -->
	
			<!-- tree overlay starts here -->
				<div class="leftOverlay rounded shadow position-absolute">
				<div class="columnInner">
				<div class="float-right">
					<img src="<html:imagesPath/>transparent.png" height="15px" width="15px"  class="chlClose ui_icon_sprite ui-icon-closethick" id="closeCHLBTN" onclick="closeTreeDiv(this.id);" />
				</div>
				<div id="errorDiv" style="display: none;" class="color-red"></div>
					<div id="locationTreeDiv">
						<h4><strong><spring:message code="requestInfo.heading.filterByLocation"/></strong></h4>													
						<div id="locationTreeLoadingNotification_loc" class='treeLoading'>
		                    <img src="<html:imagesPath/>loading-icon.gif"/>
		                </div>
						<div id="location_tree_container" style="display: none"></div>
	                </div>
					
					<div id="chlTreeDiv">
						<h4><strong><spring:message code="requestInfo.heading.filterByChl"/></strong></h4>
		                <div id="locationTreeLoadingNotification_chl" class='treeLoading'>
		                    <img src="<html:imagesPath/>loading-icon.gif"/>
		                </div>
						<div id="chl_node_tree_container" style="display: none"></div>
					
					</div>
					<div class="buttonContainerIn">
		              <button class="button_cancel chlClose" onclick="closeTreeDiv(this.id);" id="btnCancel_leftnav"><spring:message code="button.cancel"/></button>
		              <button class="button" onclick="return showSelectedNode();" id="btnSelect_leftnav"><spring:message code="button.select"/></button>
		          </div>
				</div>
				</div>
			<!-- tree overlay ends here -->
		
		<!-- LEFT COLUMN  ends  HERE -->
	
<script type="text/javascript">
    var viewCategory="VIEWALL";
    var pageNm="devicemgmt";
	
    
    var portletURL = "<portlet:renderURL></portlet:renderURL>";
    var resourceURL = "<portlet:resourceURL id='retrieveDeviceListinDM'></portlet:resourceURL>";
    
    var chlNodeId='';
    var chlValue='';
    var viewChlFlag = false;
    var viewLocationFlag = false;
    var locationValue='';
    var level;
    var parentnode;
    var dialog_chTree;
    var dialog;
    var dialog_tree_url;
    
    var cId,cValue;
    var queue;
    var isChange;
    var isSearch;
    var found;
    var searchItem;
    var locationTree;
	var whichTree;
	var deviceLocationFilterVal='';
    function viewDeviceListByLocationforDM() {
		//callOmnitureAction('Device Finder', 'Device Finder view device by location');
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
            //alert('in 0');
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
        	if(parentnode != "default"){
				provinceOrState = parentnode;//Added by sankha for LEX:AIR00065992
			}
			else{
				provinceOrState = locationTree.getParentId(locationValue);
			}
            country = locationTree.getParentId(provinceOrState);
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + provinceOrState + "&city=" + locationValue;
            viewType = 'DEVICE_LOCATION';
            breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                    '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                    locationValue + ' ' + provinceOrState + ',' + country;
            deviceLocationFilterVal = country + ","+provinceOrState+","+locationValue;
            break;
        case 32:
        	if(parentnode != "default"){
				provinceOrState = parentnode;//Added by sankha for LEX:AIR00065992
			}
			else{
				provinceOrState = locationTree.getParentId(locationValue);
			}
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
        targetURL = updateGridURL(targetURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
        updateLinkBreadcrumb(viewType, breadcrumb);
        customizedGridURL = targetURL;
        deviceListGrid.clearAndLoad(targetURL);
        
        jQuery('#bookmarkedAsset').hide();
        jQuery('#allAsset').hide();
        jQuery('#customerHierarchyAsset').hide();
        jQuery('#deviceLocationAsset').show();
    };

    function reloadGrid(type, value) {
    	if(value=='VIEWALL')
    		{
    		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERALLDEVICES%>'); 
    		}
    	else if(value=='BOOKMARKED')
    		{
    		callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICEFINDER%>', '<%=LexmarkSPOmnitureConstants.DEVICEFINDERLISTMYBOOKMARKASSETS%>');
    		}
    	
    	//alert(value);
        //changes for defect 5117 starts
        jQuery('#deviceLocInfoDiv').hide();
        jQuery('#chlInfoDiv').hide();
        //changes for defect 5117 Ends
        
		jQuery("#errorDiv1").hide();
    	updateLinkBreadcrumb(type, value);
		viewCategory = value;
		customizedGridURL = updateGridURL(getURLWithViewType('CATEGORY', value), deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
		deviceListGrid.clearAndLoad(customizedGridURL);
		
		if(value=="VIEWALL"){
			jQuery('#bookmarkedAsset').hide();
			jQuery('#allAsset').show();
		}
		else{
		jQuery('#bookmarkedAsset').show();
        jQuery('#allAsset').hide();
		}
        jQuery('#customerHierarchyAsset').hide();
        jQuery('#deviceLocationAsset').hide();
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
	        } else if(value == 'DFM') {
	            link = document.getElementById('linkManagedDevices');
	            currentSelection = '<spring:message code="deviceFinder.label.managedDevices"/>';
	        } else if(value == 'CSS') {
	           link = document.getElementById('linkNonManagedDevices');
	           currentSelection = '<spring:message code="deviceFinder.label.nonManagedDevices"/>';
	        } else if(value == 'BOOKMARKED') {
	           link = document.getElementById('linkBookmarked');
	           currentSelection = '<spring:message code="deviceFinder.label.bookmarkedDevices"/>';
	        }
	        link.style.fontWeight = 'Bold';
        }
    };
    dhtmlxError.catchError("LoadXML", myErrorHandler); 
    dhtmlxError.catchError("ALL",myErrorHandler);
    
    function closeTreeDiv(btnId){
        if (btnId !=null){
        	if(viewChlFlag){
    			removeCHLFilter();		 
    		}
    		 else if(viewLocationFlag){ 
    			 removeDeviceLocFilter();    	 
    		}
         }
    	jQuery('.leftOverlay').hide();    	
    	
    }
    
    function removeCHLFilter(){
    	dialog_tree_url = null;
    	locationTree = null;
    	chlNodeId="0_0";
    	chlValue="";
    	/*deleteChlTree=true;
    	jQuery('#chlNodeValueLabel').html("");
    	 jQuery("#chlInfoDiv").hide();
    	jQuery("#chlLinkDiv").show(); */
    	viewDeviceListByCHLforDM();
    	
    	jQuery('#allAsset').show();
    	jQuery('#bookmarkedAsset').hide();
    	jQuery('#deviceLocationAsset').hide();
    	jQuery('#customerHierarchyAsset').hide();
    }
    
    function removeDeviceLocFilter(){
    	dialog_tree_url = null;
    	locationTree = null;
    	level=0;//For all assets
    	locationValue="";
    	viewDeviceListByLocationforDM();
    	jQuery('#allAsset').show();
    	jQuery('#bookmarkedAsset').hide();
    	jQuery('#deviceLocationAsset').hide();
    	jQuery('#customerHierarchyAsset').hide();
        
    	/*deleteDeviceTree=true;
    	jQuery('#deviceLocNodeValueLabel').html("");
    	jQuery("#deviceLocInfoDiv").hide();
     	jQuery("#deviceLocLinkDiv").show();
     	viewDeviceListByLocationforAsset();*
     	*/
    }
    
    function openTreeDiv(tree){
		jQuery("#errorDiv1").hide();
    	/* to position the CHL leftside overlay */
        var lObj = jQuery('.left-nav');
        var position = lObj .position();
        jQuery('.leftOverlay').css('left',position.left);
        jQuery('.leftOverlay').css('top',position.top);
        jQuery('.leftOverlay').css('height',lObj.height());

    	jQuery('.leftOverlay').hide();
    	jQuery('#chl_node_tree_container').empty();
    	jQuery('#location_tree_container').empty();
    	whichTree=tree;
    	if(tree=="chl"){
    		
    		jQuery('#locationTreeDiv').hide();
    		jQuery('#location_tree_container').empty();
    		dialog_tree_url='${chlTreeXMLURL}';
    		initialiseCHLTree('chl_node_tree_container','locationTreeLoadingNotification_chl');	
    		jQuery('#chl_node_tree_container').show();
    		jQuery('#chlTreeDiv').show();
    	}else{
    		jQuery('#chlTreeDiv').hide();
    		jQuery('#chl_node_tree_container').empty();
    		//jQuery('#chlInfoDiv').hide();
    		dialog_tree_url='${deviceLocationTreeXMLURL}';
    		initialiseCHLTree('location_tree_container','locationTreeLoadingNotification_loc');			
    		jQuery('#location_tree_container').show();
    		jQuery('#locationTreeDiv').show();	
    	}
    			

          jQuery('.leftOverlay').show(function() {
                 jQuery(this).resizable();
          });
          
          if (window.PIE) {
            document.getElementById("btnSelect_leftnav").fireEvent("onmove");
            document.getElementById("btnCancel_leftnav").fireEvent("onmove");
		  }
          return false;

          $(".right-column").css({"min-height":"510px"});
    }
    
    function initialiseCHLTree(divId,loadingId)
    {	
    	var chlTreeXMLUrl=dialog_tree_url;
    	
    	var reportingTree = new dhtmlXTreeObject(divId,"100%","100%",0);
    	reportingTree.setSkin('dhx_skyblue');
    	reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
    	reportingTree.enableTreeLines(true);
    	
    	
    		if(jQuery.browser.ie) {
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
    		setTimeout(function(){
        		rebrandPagination();
        	
        	},100);
    	});
    	
    	reportingTree.setXMLAutoLoading(chlTreeXMLUrl);
    	reportingTree.loadXML(chlTreeXMLUrl);
    	locationTree=reportingTree;
    }
    
    function viewDeviceListByLocation(devicelevel,devicelocationId,deviceparentnode){    	
    	locationValue=devicelocationId;
        level=devicelevel;
		parentnode=deviceparentnode;
        viewLocationFlag=true;    
        
        /****Calling this method to dynamically change the grid load on selecting a node******/
        viewDeviceListByLocationforDM();
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
    	
    	/****Calling this method to dynamically change the grid load on selecting a node******/
    	viewDeviceListByCHLforDM();
    };
    
    function viewDeviceListByCHLforDM() {
		//callOmnitureAction('Device Finder', 'Device Finder view device by CHL');
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
        targetURL = updateGridURL(targetURL, deviceListGrid.getSortColByOffset(), deviceListGrid.a_direction, deviceListGrid.filterValues);
        updateLinkBreadcrumb(viewType, breadcrumb);
        customizedGridURL = targetURL;
        deviceListGrid.clearAndLoad(targetURL);
        
        jQuery('#bookmarkedAsset').hide();
        jQuery('#allAsset').hide();
        jQuery('#deviceLocationAsset').hide();
        jQuery('#customerHierarchyAsset').show();
    };

     function showSelectedNode(){
    
    	 /* if(viewChlFlag){
    		 viewDeviceListByCHLforDM();
    		 
    	 }
    	 else if(viewLocationFlag){ 
    		
    		 viewDeviceListByLocationforDM();    	 
    	 } */
		if(whichTree=='chl'){
			 callOmnitureAction('<%=LexmarkSPOmnitureConstants.HIERARCHYLEVEL%>', '<%=LexmarkSPOmnitureConstants.SELECTCUSTOMERHIERARCHYBUTTON%>'); 
    	 jQuery('#chlInfoDiv').show();
    	 jQuery('#chlNodeValueLabel').html(chlValue);
    	 jQuery('#deviceLocInfoDiv').hide();
    	 jQuery('#deviceLocNodeValueLabel').html('');
		}else{
			callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICELOCATION%>', '<%=LexmarkSPOmnitureConstants.DEVICELOCATIONSELECT%>'); 
			jQuery('#deviceLocInfoDiv').show();
	    	 jQuery('#deviceLocNodeValueLabel').html(deviceLocationFilterVal);
	    	 jQuery('#chlInfoDiv').hide();
        	 jQuery('#chlNodeValueLabel').html('');
			}
    	 
    	 closeTreeDiv();
     }
     function removeFilters(btnid){
    	 //alert('in remove filters'+whichTree);
    	 if(jQuery('#chlNodeValueLabel').html()!='')
    		 {
    		 jQuery('#chlInfoDiv').hide();
        	 jQuery('#chlNodeValueLabel').html('');
    		 }
    	 if(jQuery('#deviceLocNodeValueLabel').html()!=''){
    		 jQuery('#deviceLocInfoDiv').hide();
	    	 jQuery('#deviceLocNodeValueLabel').html('');
    	 }
    	 	/*if(whichTree=='chl'){
        	 jQuery('#chlInfoDiv').hide();
        	 jQuery('#chlNodeValueLabel').html('');
    		}else{
    			jQuery('#deviceLocInfoDiv').hide();
    	    	 jQuery('#deviceLocNodeValueLabel').html('');
    			}*/
    	 	closeTreeDiv(btnid);
         }
     
</script>
