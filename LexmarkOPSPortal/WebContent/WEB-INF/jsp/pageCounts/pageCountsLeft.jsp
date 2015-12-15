<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%-- <script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@ include file="../../css/tree/dhtmlxtree.css" %></style> --%>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<script type="text/javascript"><%@ include file="/js/tree/dhtmlxtree.js"%></script>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<div class="left-nav" height="100%">
			<div class="left-nav-inner" style="overflow:hidden;">
 			<!--Below heading added for UI change-CI-7 14.02 Release-->
				 <h4 style="padding-left:20px!important;padding-top:10px!important;"><spring:message code="meterRead.title.filters"/></h4>
					<table class="displayGrid">
            </table>
				<!-- left-nav-header -->
				<ul class="filters">
					<li><a  href="#" style="font-weight: bold;" id="BookMarked" onclick="reLoadGrid('BookMarked');"><spring:message code="meterRead.label.bookmarkedDevices"/></a></li>
					<li><a  href="#" id="Manual" onclick="reLoadGrid('Manual');"><spring:message code="meterRead.label.manualReads"/></a></li>
					<!-- brmandal added the below 2 lines -->
					
					<!-- Added for "Device Location" & "Customer Hierarchy" popup -- CI17_BRD_16 -- Start-->
					
					<li><a href="#" id="chlTreeLink_device" onclick="return openTreeDiv('deviceLoc');"><spring:message code="requestInfo.link.deviceLocation"/></a></li>
					<li><a href="#" id="chlTreeLink" onclick="return openTreeDiv('chl');"><spring:message code="requestInfo.link.customerHierarchy"/></a></li>
					
					<!-- Added for "Device Location" & "Customer Hierarchy" popup -- CI17_BRD_16 -- Start-->
					
					
				</ul>
				<br>
				
				<div id='chlInfoDiv' style="display:none;">
					<span>Customer Hierarchy</span><br/>
					<span id="chlNodeValueLabel"></span>
					<div style="float:right;padding-right:10px;"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" height="15px" width="15px" id="deleteChlBtn" onClick="removeFilters(this.id);"/></div>
				</div>

				<div id='deviceLocInfoDiv' style="display:none;">
					<span>Device Location</span><br/>
					<span id="deviceLocNodeValueLabel"></span>
					<div style="float:right;padding-right:10px;"><img class="ui_icon_sprite trash-icon" src="<html:imagesPath/>transparent.png" height="15px" width="15px" id="deleteDeviceLocBtn" onClick="removeFilters(this.id);"/></div>
				</div>
				<!--
				<div id="location_tree_container" style="display: none"></div>
                <div id="locationTreeLoadingNotification" class='treeLoading'>
                    <img src="<html:imagesPath/>loading-icon.gif"/>
                </div>
				<br>
				<div id="chl_node_tree_container" style="display: none"></div>
                <div id="reportingTreeLoadingNotification" class='treeLoading'>
                    <img src="<html:imagesPath/>loading-icon.gif"/>
                </div>
                -->
			</div><!-- left-nav-inner -->
			<div class="left-nav-footer"></div><!-- left-nav-footer -->
		</div><!-- left-nav -->
	
	<!-- Added for "Device Location" & "Customer Hierarchy" popup -- CI17_BRD_16 -- Start-->
	<!-- tree overlay starts here -->
	<div class="leftOverlay rounded shadow" >
				<div class="columnInner" style="padding:5px 5px!important;">
				<div style="float: right;margin-right:5px;">
					<img src="<html:imagesPath/>transparent.png" height="15px" width="15px"  class="chlClose ui_icon_sprite ui-icon-closethick" id="closeCHLBTN" onclick="closeTreeDiv(this.id);" />
				</div> 
				<!--<div id="errorDiv" style="display: none;color:red;"></div>  -->
					<div id="locationTreeDiv" >
						<h4 style="margin:0px;"><strong><spring:message code="requestInfo.heading.filterByLocation"/></strong></h4>													
						
						<div id="locationTreeLoadingNotification_loc" class='treeLoading'>
		                    <img src="<html:imagesPath/>loading-icon.gif"/>
		                </div>
						<div id="location_tree_container" style="display: none"></div>
	                </div>
					
					<div id="chlTreeDiv">
						<h4 style="margin:0px;"><strong><spring:message code="requestInfo.heading.filterByChl"/></strong></h4>
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
	<!-- Added for "Device Location" & "Customer Hierarchy" popup -- CI17_BRD_16 -- End-->
<script type="text/javascript">
	//var locationTree = new dhtmlXTreeObject("location_tree_container","100%","100%",0);
	//var dialog_tree_url;
	//locationTree.setSkin('dhx_skyblue');
	//locationTree.setImagePath("<html:imagesPath/>treeImgs/");
	//locationTree.enableTreeLines(true);
	/*if(jQuery.browser.ie) {
		locationTree.enableIEImageFix(true);
	}
	
    locationTree.attachEvent("onXLS", function() {
        document.getElementById('locationTreeLoadingNotification').style.display = 'block';
    });
    locationTree.attachEvent("onXLE", function() {
        document.getElementById('locationTreeLoadingNotification').style.display = 'none';
        document.getElementById('location_tree_container').style.display = 'block';
    });
	locationTree.loadXML("${pageCountsListForm.deviceLocationTreeXMLURL}"+"&deviceLocType=pgcnts");//This parameter would be required for device loc call in pg cnts
    
    var reportingTree = new dhtmlXTreeObject("chl_node_tree_container","100%","100%",0);
    reportingTree.setSkin('dhx_skyblue');
    reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
    reportingTree.enableTreeLines(true);
   	if(jQuery.browser.ie) {
		reportingTree.enableIEImageFix(true);
	}
    reportingTree.attachEvent("onXLS", function(tree,id) {
    	if(id == null)
    		document.getElementById('reportingTreeLoadingNotification').style.display = 'block';
		else
			reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
    });
    reportingTree.attachEvent("onXLE", function() {
        document.getElementById('reportingTreeLoadingNotification').style.display = 'none';
        document.getElementById('chl_node_tree_container').style.display = 'block';
    });
    reportingTree.setXMLAutoLoading("${pageCountsListForm.chlTreeXMLURL}");
    reportingTree.loadXML("${pageCountsListForm.chlTreeXMLURL}");
*/
    var resourceURL = "<portlet:resourceURL id='retrievePageCountsList'></portlet:resourceURL>&orderBy=1&direction=ASCENDING";

    

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
    var viewChlFlag = false;
    var viewLocationFlag = false;

    /*function viewDeviceListByCHL(chlNodeId, chlValue) {
		callOmnitureAction('Page Counts', 'Page Count View by CHL');
		//alert("in viewDeviceListByCHL");
        var targetURL;
        if (chlNodeId == '0_0') {
        	targetURL = resourceURL + "&view=viewAll";
            turnBold (document.getElementById('BookMarked'));
        } else {targetURL = resourceURL + "&chlNodeId=" + chlNodeId;
            trunTwoBole();
        }
        targetURL = updateGridURL(targetURL, meterReadGrid.getSortColByOffset(), meterReadGrid.a_direction, meterReadGrid.filterValues);
        customizedGridURL = targetURL;
        //alert("in viewDeviceListByCHL "+customizedGridURL);
        meterReadGrid.clearAndLoad(targetURL);
    };*/

    //Added for "Device Location" & "Customer Hierarchy" popup -- CI17_BRD_16 -- Start
    
     function openTreeDiv(tree){
        // alert("1");
		jQuery("#errorDiv1").hide();
		//alert("2");
    	/* to position the CHL leftside overlay */
        var lObj = jQuery('.left-nav');
        //alert("3");
        var position = lObj .position();
       // alert("4");
        jQuery('.leftOverlay').css('left',position.left);
        jQuery('.leftOverlay').css('top',position.top);
        jQuery('.leftOverlay').css('height',lObj.height());
        //jQuery('.leftOverlay').css('left','14px');
       // jQuery('.leftOverlay').css('top','208px');
        //jQuery('.leftOverlay').css('height','535px');
       // jQuery('.leftOverlay').css('display','block');
       // jQuery('.leftOverlay').css('position','absolute');
       // jQuery('.leftOverlay').css('width','214px');
        //alert("5");
    	jQuery('.leftOverlay').hide();
    	jQuery('#chl_node_tree_container').empty();
    	jQuery('#location_tree_container').empty();
    	//alert("6");
    	whichTree=tree;
    	if(tree=="chl"){
    		//alert("7");
    		jQuery('#leftOverlay rounded shadow').show();
    		jQuery('#locationTreeDiv').hide();
    		jQuery('#location_tree_container').empty();
    		//alert("8");
    		dialog_tree_url='${pageCountsListForm.chlTreeXMLURL}';
    		//alert("9");
    		initialiseCHLTree('chl_node_tree_container','locationTreeLoadingNotification_chl');	
    		jQuery('#chl_node_tree_container').show();
    		jQuery('#chlTreeDiv').show();
    		//alert("10");
    	}else{
    		//alert("11");
    		jQuery('#leftOverlay rounded shadow').show();
    		jQuery('#chlTreeDiv').hide();
    		jQuery('#chl_node_tree_container').empty();
    		//alert("12");
    		//jQuery('#chlInfoDiv').hide();
    		//dialog_tree_url='${deviceLocationTreeXMLURL}';
    		dialog_tree_url='${pageCountsListForm.deviceLocationTreeXMLURL}+&deviceLocType=pgcnts';
    		initialiseCHLTree('location_tree_container','locationTreeLoadingNotification_loc');			
    		jQuery('#location_tree_container').show();
    		jQuery('#locationTreeDiv').show();	
    		//alert("13");
    	}
    			

          jQuery('.leftOverlay').show(function() {
                 jQuery('.leftOverlay').resizable();
          });
          //alert("14");
          if (window.PIE) {
            document.getElementById("btnSelect_leftnav").fireEvent("onmove");
            document.getElementById("btnCancel_leftnav").fireEvent("onmove");
		  }
          //alert("15");
          return false;

          $(".right-column").css({"min-height":"510px"});
    }

     function initialiseCHLTree(divId,loadingId)
     {	
     	var chlTreeXMLUrl=dialog_tree_url;
     	//alert(dialog_tree_url);
     	//alert('A');
     	var reportingTree = new dhtmlXTreeObject(divId,"100%","100%",0);
     	reportingTree.setSkin('dhx_skyblue');
     	reportingTree.setImagePath("<html:imagesPath/>treeImgs/");
     	reportingTree.enableTreeLines(true);
     	//alert('B');
     	
     		if(jQuery.browser.ie) {
     		reportingTree.enableIEImageFix(true);
     	}
     		//alert('C');
     		reportingTree.attachEvent("onXLS", function(tree,id) {
     		if(id == null){
     			jQuery("#"+loadingId).show();
     			jQuery('#'+divId).css('height','0px');
     		}
     		else
     			reportingTree.setItemImage2(id,'loading.gif','folderOpen.gif','folderClosed.gif');
     	});
     		//alert('D');	
     	reportingTree.attachEvent("onXLE", function(tree,id) {
     		jQuery("#"+loadingId).hide();
     		jQuery('#'+divId).css('height','460px');
     		setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
     	});
     	//alert('E');
     	//alert(chlTreeXMLUrl);
     	reportingTree.setXMLAutoLoading(chlTreeXMLUrl);
     	reportingTree.loadXML(chlTreeXMLUrl);
     	locationTree=reportingTree;
     }

     function closeTreeDiv(btnId){
         //alert('F');
         //alert(btnId);
         if (btnId !=null){
         	if(viewChlFlag){
     			removeCHLFilter();		 
     		}
     		 else if(viewLocationFlag){ 
     			 removeDeviceLocFilter();    	 
     		}
          }
         //alert('G');
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
     	//alert('H');
     	viewDeviceListByCHL();
     	
     	//jQuery('#allAsset').show();
     	//jQuery('#bookmarkedAsset').hide();
     	//jQuery('#deviceLocationAsset').hide();
     	//jQuery('#customerHierarchyAsset').hide();
     }
     
     function removeDeviceLocFilter(){
     	dialog_tree_url = null;
     	locationTree = null;
     	level=0;//For all assets
     	locationValue="";
     	viewDeviceListByLocation();
     	//jQuery('#allAsset').show();
     	//jQuery('#bookmarkedAsset').hide();
     	//jQuery('#deviceLocationAsset').hide();
     	//jQuery('#customerHierarchyAsset').hide();
         
     	/*deleteDeviceTree=true;
     	jQuery('#deviceLocNodeValueLabel').html("");
     	jQuery("#deviceLocInfoDiv").hide();
      	jQuery("#deviceLocLinkDiv").show();
      	viewDeviceListByLocationforAsset();*
      	*/
     }

     function showSelectedNode(){
    	    //alert("in");
    	  /*if(viewChlFlag){
    		 viewDeviceListByCHLforDM();
    		 
    	 }
    	 else if(viewLocationFlag){ 
    		alert("in viewLocationFlag");
    		 viewDeviceListByLocationforDM();    	 
    	 } */
    	 //alert("in1");
		if(whichTree=='chl'){
			//alert("in2");
			 if(chlValue.length == 0){
				 jAlert('<spring:message code="meterRead.msg.alert.CHL"/>', "");
					 return false;
			 }
			 //callOmnitureAction('<%=LexmarkSPOmnitureConstants.HIERARCHYLEVEL%>', '<%=LexmarkSPOmnitureConstants.SELECTCUSTOMERHIERARCHYBUTTON%>'); 
    	 jQuery('#chlInfoDiv').show();
    	 jQuery('#chlNodeValueLabel').html(chlValue);
    	 jQuery('#deviceLocInfoDiv').hide();
    	 jQuery('#deviceLocNodeValueLabel').html('');
    	 //alert("in3");
		}else{
			if(deviceLocationFilterVal.length == 0){
				// alert("<spring:message code="meterRead.msg.alert.DeviceLocation"/>");
				 jAlert('<spring:message code="meterRead.msg.alert.DeviceLocation"/>', "");
				 return false;
			}
			//alert("in4");
			//callOmnitureAction('<%=LexmarkSPOmnitureConstants.DEVICELOCATION%>', '<%=LexmarkSPOmnitureConstants.DEVICELOCATIONSELECT%>'); 
			jQuery('#deviceLocInfoDiv').show();
			//alert(deviceLocationFilterVal);
	    	 jQuery('#deviceLocNodeValueLabel').html(deviceLocationFilterVal);
	    	 jQuery('#chlInfoDiv').hide();
        	 jQuery('#chlNodeValueLabel').html('');
        	 //alert("in5");
			}
		//alert("in betn");
    	 closeTreeDiv();
    	 //alert("out");
     }

     function viewDeviceListByLocation(devicelevel,devicelocationId,deviceparentnode){    	
         //alert("in viewDeviceListByLocation..");
         //alert(devicelevel);
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
         targetURL = updateGridURL(targetURL, meterReadGrid.getSortColByOffset(), meterReadGrid.a_direction, meterReadGrid.filterValues);
        // updateLinkBreadcrumb(viewType, breadcrumb);
         customizedGridURL = targetURL;
         meterReadGrid.clearAndLoad(targetURL);
         
         /*jQuery('#bookmarkedAsset').hide();
         jQuery('#allAsset').hide();
         jQuery('#deviceLocationAsset').hide();
         jQuery('#customerHierarchyAsset').show();*/
     };
    

    /*function viewDeviceListByLocationforDM(level, locationValue) {
		callOmnitureAction('Page Counts', 'Page Count View by Location');
		alert("in viewDeviceListByLocationforDM");
        // text and id of location node are same
        var country;
        var provinceOrState;
        var city;
        var viewType;
        var targetURL;

        // Options of level: 0 means root node, 1 means Country, 21 means Province,
        // and 22 means State, 31 means City in Province, and 32 means City in State.
        switch(level)
        {
        case 0:
            targetURL = resourceURL + "&view=viewAll";
            
            break;
        case 1:
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + locationValue;
            break;
        case 21:
            country = locationTree.getParentId(locationValue);
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + locationValue;
            break;
        case 22:
            country = locationTree.getParentId(locationValue);
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&state=" + locationValue;
            break;
        case 31:
            provinceOrState = locationTree.getParentId(locationValue);
            country = locationTree.getParentId(provinceOrState);
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + provinceOrState + "&city=" + locationValue;
            break;
        case 32:
            provinceOrState = locationTree.getParentId(locationValue);
            country = locationTree.getParentId(provinceOrState);
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&state=" + provinceOrState + "&city=" + locationValue;
            break;
        }
        targetURL = updateGridURL(targetURL, meterReadGrid.getSortColByOffset(), meterReadGrid.a_direction, meterReadGrid.filterValues);
        customizedGridURL = targetURL;
        alert(customizedGridURL);
        meterReadGrid.clearAndLoad(targetURL);
        if (level == 0) {
        	turnBold (document.getElementById('BookMarked'));
        } else {
        	trunTwoBole();
        }
        alert('end');
    };*/

    function viewDeviceListByLocationforDM() {
		//callOmnitureAction('Device Finder', 'Device Finder view device by location');
        // text and id of location node are same
        var country;
        var provinceOrState;
        var city;
        var breadcrumb;
        var viewType;
        var targetURL;
		//alert("in viewDeviceListByLocationforDM");
		//alert(level);
        // Options of level: 0 means root node, 1 means Country, 21 means Province,
        // and 22 means State, 31 means City in Province, and 32 means City in State.
        switch(level)
        {
        case 0:
            targetURL = resourceURL + "&view=viewAll";
            viewType = 'CATEGORY';
            breadcrumb = 'VIEWALL';
            deviceLocationFilterVal= locationValue;
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
        	//alert(country);
            targetURL = resourceURL + "&locationId=" + locationValue + "&country=" + country + "&city=" + locationValue;
            viewType = 'DEVICE_LOCATION';
            breadcrumb = '<spring:message code="deviceFinder.label.allDevices" />&gt;' +
                    '<spring:message code="tree.label.deviceLocation"/>' + ':' +
                    locationValue + ',' + country;
            deviceLocationFilterVal = country + ","+","+locationValue;
            //alert("33 "+deviceLocationFilterVal);
            break;
         // Added for CI-6 End : PARTHA
        }
        targetURL = updateGridURL(targetURL, meterReadGrid.getSortColByOffset(), meterReadGrid.a_direction, meterReadGrid.filterValues);
        //updateLinkBreadcrumb(viewType, breadcrumb);
        customizedGridURL = targetURL;
        meterReadGrid.clearAndLoad(targetURL);
        //alert("deviceLocationFilterVal ... viewDeviceListByLocationforDM.. "+deviceLocationFilterVal);
        /*jQuery('#bookmarkedAsset').hide();
        jQuery('#allAsset').hide();
        jQuery('#customerHierarchyAsset').hide();
        jQuery('#deviceLocationAsset').show();*/
    };


    function removeFilters(btnid){
   	 //alert('in remove filters'+whichTree);
   	 //alert(btnid);
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
	
    //Added for "Device Location" & "Customer Hierarchy" popup -- CI17_BRD_16 -- End
    
    function turnBold (speclink) {
   	 var anchors = document.links;
   	 for (i=0; i<anchors.length; i++) {
       anchors[i].style.fontWeight = '';
   	 }
   	 speclink.style.fontWeight = 'Bold';
   }
   function trunTwoBole(){
	  var anchors = document.links;
	  for (i=0; i<anchors.length; i++) {
		  if(anchors[i].id=="Manual"||anchors[i].id=="BookMarked"){
	       anchors[i].style.fontWeight = '';
		  	}
	   	 }
	}
   function reLoadGrid(type){
	   callOmnitureAction('Page Counts', 'Page Count List by ' + type);
	   var href= document.getElementById(type);
	   var url;
	   turnBold (href);
	   if(type=='Manual'){
		   pageName = "Manual";
		}else{
		   pageName = "BookMarked";
	  }
	   customizedGridURL = populateURLCriterias();
	   meterReadGrid.clearAndLoad(customizedGridURL);
	}
	dhtmlxError.catchError("LoadXML", myErrorHandler); 
    dhtmlxError.catchError("ALL",myErrorHandler);
</script>