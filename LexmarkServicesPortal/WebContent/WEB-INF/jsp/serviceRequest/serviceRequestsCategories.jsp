<script type="text/javascript"><%@ include file="../../../js/grid/dhtmlxcommon.js"%></script>
<script type="text/javascript"><%@ include file="../../../js/tree/dhtmlxtree.js"%></script>
<style type="text/css"><%@ include file="../../css/tree/dhtmlxtree.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="left-nav">
			<div class="left-nav-inner" style="overflow:hidden;">
				<div class="left-nav-header rounded shadow">
				<table class="displayGrid">
              <thead>
                <tr><th><spring:message code="serviceRequest.label.filters"/></th>
                </tr></thead>
              
            </table>
					
				</div>
				
				<!-- left-nav-header -->
				<br>
				<c:if test="${serviceRequestListForm.createServiceRequestFlag}" > 
				  	<div>
				  	   <table width="100%">
							<tr>
								<td align="center">
				   					 <button  onClick="callOmnitureAction('Service Request', 'Create Service Request'); doSubmitSR();" class="button"><spring:message code="serviceRequest.description.submitServiceRquest"/></button>
								</td>
							</tr>
						</table>
	  				</div>        
  				</c:if>
				<ul class="filters">
					<li><a id="RecentSRList" class="font-weight-bold" onclick="callOmnitureAction('Service Request', 'Recent Service Requests'); reLoadGrid('RecentSRList');" href="#" ><spring:message code="serviceRequest.title.recentRequests"/></a></li>
					<c:if test="${accountTypeMPSFlag=='DFM'}" > 
						<li><a id="DFM"  onclick="callOmnitureAction('Service Request', 'DFM'); reLoadGrid('DFM');" href="#" ><spring:message code="serviceRequest.title.managedSRList"/></a></li>
					</c:if>
					<c:if test="${accountTypeCSSFlag=='CSS'}" > 
						<li><a id="CSS" onclick="callOmnitureAction('Service Request', 'CSS'); reLoadGrid('CSS');" href="#" ><spring:message code="serviceRequest.title.nonManagedSRList"/></a></li>
					</c:if>
					<li><a id="MySRList" onclick="callOmnitureAction('Service Request', 'My Service Requests'); reLoadGrid('MySRList');" href="#" ><spring:message code="serviceRequest.title.myServiceRequest"/></a></li>
				</ul>
				<br>
					<div id="location_tree_container" style="display: none"></div>
	                <div id="locationTreeLoadingNotification" class='treeLoading'>
	                    <img src="<html:imagesPath/>loading-icon.gif"/>
	                </div>
				<br>
					<div id="chl_node_tree_container" style="display: none"></div>
	                <div id="reportingTreeLoadingNotification" class='treeLoading'>
	                    <img src="<html:imagesPath/>loading-icon.gif"/>
	                </div>
			</div><!-- left-nav-inner -->
			<div class="left-nav-footer"></div><!-- left-nav-footer -->
		</div><!-- left-nav -->
	
<script type="text/javascript">

	var locationTree = new dhtmlXTreeObject("location_tree_container","100%","100%",0);
	locationTree.setSkin('dhx_skyblue');
	locationTree.setImagePath("<html:imagesPath/>treeImgs/");
	locationTree.enableTreeLines(true);
	if(jQuery.browser.ie) {
		locationTree.enableIEImageFix(true);
	}
	
    locationTree.attachEvent("onXLS", function() {
        document.getElementById('locationTreeLoadingNotification').style.display = 'block';
    });
    locationTree.attachEvent("onXLE", function() {
        document.getElementById('locationTreeLoadingNotification').style.display = 'none';
        document.getElementById('location_tree_container').style.display = 'block';
    });
	locationTree.loadXML("${serviceRequestListForm.deviceLocationTreeXMLURL}");
    
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
    reportingTree.setXMLAutoLoading("${serviceRequestListForm.chlTreeXMLURL}");
    reportingTree.loadXML("${serviceRequestListForm.chlTreeXMLURL}");
 

    var viewType = getArg(location.search, "view");
    if (viewType == "viewAll") {
        document.getElementById("linkViewAll").className = "blueBold1";
    }
    
    var portletURL = "<portlet:resourceURL></portlet:resourceURL>";

    function viewDeviceListByCHL(chlNodeId, chlValue) {
    	callOmnitureAction('Service Request', 'Service Request CHL Tree');
    	var targetURL;
        if (chlNodeId == '0_0') {
        	targetURL = portletURL + "&view=viewAll&pageType=branch";
        } else {
        	targetURL = portletURL + "&chlNodeId=" + chlNodeId + "&chl=" + chlValue+"&pageType=branch";
        }
        targetURL = updateGridURL(targetURL, srgrid.getSortColByOffset(), srgrid.a_direction, srgrid.filterValues);
        customizedGridURL = targetURL;
        srgrid.clearAndLoad(targetURL);
        trunTwoBole();
    };

    function viewDeviceListByLocation(level, locationValue) {
        // text and id of location node are same
        callOmnitureAction('Service Request', 'Service Request Location Tree');
        var country;
        var provinceOrState;
        var city;
        var targetURL;

        // Options of level: 0 means root node, 1 means Country, 21 means Province,
        // and 22 means State, 31 means City in Province, and 32 means City in State.
        switch(level)
        {
        case 0:
            targetURL = portletURL + "&view=viewAll";
            break;
        case 1:
        	targetURL = portletURL + "&locationId=" + locationValue + "&country=" + locationValue; 
        	break;
        case 21:
            country = locationTree.getParentId(locationValue);
            targetURL = portletURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + locationValue;
            break;
        case 22:
            country = locationTree.getParentId(locationValue);
            targetURL = portletURL + "&locationId=" + locationValue + "&country=" + country + "&state=" + locationValue;
            break;
        case 31:
        	provinceOrState = locationTree.getParentId(locationValue);
            country = locationTree.getParentId(provinceOrState);
            targetURL = portletURL + "&locationId=" + locationValue + "&country=" + country + "&province=" + provinceOrState + "&city=" + locationValue;
            break;
        case 32:
        	provinceOrState = locationTree.getParentId(locationValue);
            country = locationTree.getParentId(provinceOrState);
            targetURL = portletURL + "&locationId=" + locationValue + "&country=" + country + "&state=" + provinceOrState + "&city=" + locationValue;
            break;
        }
        targetURL = updateGridURL(targetURL, srgrid.getSortColByOffset(), srgrid.a_direction, srgrid.filterValues);
        customizedGridURL = targetURL;
        srgrid.clearAndLoad(targetURL);
        trunTwoBole();
    };

    function doSubmitSR(){
		window.location.href="<portlet:actionURL><portlet:param name='action' value='checkAccountSRPrivilege'/></portlet:actionURL>";
	}
	function gotoConfirmation(){
		window.location.href="<portlet:renderURL><portlet:param name='action' value='showServiceRequestConfirmation'/></portlet:renderURL>";
	}
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
			  if(anchors[i].id=="RecentSRList"||anchors[i].id=="MySRList"){
		       anchors[i].style.fontWeight = '';
			  	}
		   	 }
		}
	
	function reLoadGrid(type){
		   var href= document.getElementById(type);
		   turnBold (href);
		   viewType = type;
		   var url = getURLWithViewType(type);
		   customizedGridURL = updateGridURL(getURLWithViewType(type), srgrid.getSortColByOffset(), srgrid.a_direction, srgrid.filterValues);
		   srgrid.clearAndLoad(customizedGridURL);
	}
	dhtmlxError.catchError("LoadXML", myErrorHandler); 
    dhtmlxError.catchError("ALL",myErrorHandler);
</script>