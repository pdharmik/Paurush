<style type="text/css"><%@ include file="../../css/jquery/jquery-ui.css" %></style>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../common/subTab.jsp"></jsp:include>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%--<jsp:include page="../includeGrid.jsp"></jsp:include> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<html:rootPath/>js/tree/dhtmlxtree.js"></script>
<%--<script type="text/javascript"><%@ include file="../../../js/tree/dhtmlxtree.js"%></script> --%>
<style type="text/css"><%@ include file="../../css/tree/dhtmlxtree.css" %></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<%--<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style> --%>
<!--[if IE 7]>
	<style type="text/css"><%--@ include file="/WEB-INF/css/mps_ie7.css" --%></style>
<![endif]-->
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%--<script type="text/javascript"><%@ include file="../../../js/portletRedirection.js"%></script> --%>
<script type="text/javascript" src="<html:rootPath/>js/portletRedirection.js"></script>
<%@page import="jsx3.gui.Alerts"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.lexmark.services.util.ChangeMgmtConstant"%>

<portlet:resourceURL var="assetRequestHistoryURLvar" id="assetRequestHistory"></portlet:resourceURL>
		
<%--<script type="text/javascript"><%@ include file="../../../js/grid/ext/dhtmlxgrid_excell_sub_row.js"%></script> --%>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js"></script>
		 

		<div id="content-wrapper">
			<div class="journal-content-article" id="journal">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
			
			<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div>
			<div id="partnerError" style="display: none;">
				<div class="error">
					<ul><li><strong><spring:message code="validation.selectAccount"/></strong></li></ul>
				</div>
				<div class="portletBodyWrap rounded infoBox">
					<a href="#" onclick="showAccountPopup();"><span><spring:message code="requestInfo.heading.selectAccount"/></span></a>
				</div>
			</div>
			<div class="main-wrap" id="main-div">
				<div class="content">
					<%--@ include file="/WEB-INF/jsp/orderSuppliesAssetOrder/assetOrderLeft.jsp"--%>
					<jsp:include page="../orderSuppliesAssetOrder/assetOrderLeft.jsp" /> 
					<div class="right-column">
								<div class="portletBlock center">
										<ul class="processSteps shadow">
											<li class="selected"><a href="#" title="<spring:message code="requestInfo.tooltip.step1SelectAsset"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.link.selectAsset"/></a></li>
											<li><a href="#"
												title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
											<li><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
											<li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
										</ul>
								</div>
								<div class="detailsBodyWrap">
								<!-- MAIN CONTENT GOES HERE -->
								<h3 class="pageTitle" id="allAsset"><spring:message code="requestInfo.heading.selectFromAllAssets"/></h3>
								<h3 class="pageTitle" id="bookmarkedAsset"><spring:message code="requestInfo.heading.selectFromBookmarkedAssets"/></h3>
								<h3 class="pageTitle" id="deviceLocationAsset"><spring:message code="requestInfo.heading.selectFromDeviceLocation"/></h3>
								<h3 class="pageTitle" id="customerHierarchyAsset"><spring:message code="requestInfo.heading.selectFromCustomerHierarchy"/></h3>
										<div class="grid-controls">
											<div class="expand-min">
												<ul>
													<li class="first">
														<img class="ui_icon_sprite minimize-icon" src="<html:imagesPath/>/transparent.png" onClick="minimizeAll()"; style="cursor:pointer;" />
						       		 				&nbsp;<a href="javascript:void(0)" onClick="minimizeAll()"; ><spring:message code="requestInfo.link.minimizeAll"/></a>
													</li>
													<li>
														<img class="ui_icon_sprite expand-icon" src="<html:imagesPath/>/transparent.png" onClick="expandAll()"; style="cursor:pointer;" />
					           			 			&nbsp;<a href="javascript:void(0)" onClick="expandAll()"; ><spring:message code="requestInfo.link.expandAll"/></a>
													</li>
													<li>
														<a href="#grid" id='headerImageButton'><img class="ui_icon_sprite customize-icon" src="<html:imagesPath/>transparent.png" style="cursor:pointer"; /></a>
														&nbsp;<a href="#grid" id='headerMenuButton'><spring:message code="requestInfo.link.customizeColumns"/></a>
													</li>
													<li>
														<a href="javascript:doReset();" id="resetGridSetting" ><img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite reset-icon"  style="cursor:pointer"; />
														<spring:message code="requestInfo.link.reset"/></a>
													</li>
												</ul>
											</div><!-- expand-min -->  
											<div style="clear: right"></div><!-- clear -->
										</div><!-- grid-controls -->	
										<div class="portlet-wrap rounded infoBox">
											<div class="portlet-wrap-inner">
												<div id="consumableAssetlistGrid" style="*width:100%;"></div>
								        		
								        		<div id="loadingNotification" class='gridLoading'>
								        			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
								    			</div>
								    			<div class="pagination" id="paginationId">
								    			<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
								    			</div><!-- mygrid_container -->
											</div><!-- portlet-wrap-inner -->	
											<div class="portlet-footer">
												<div class="portlet-footer-inner">
												</div><!-- portlet-footer-inner -->
											</div><!-- portlet-footer -->
										</div><!-- portlet-wrap -->
										<%--
										Commented for Code revert
										Changes for JAN release CatalogEntitlement flag  --%>
										<%
											String catalogEntitlement_assetPage = "";
											String currURL=PortalUtil.getCurrentCompleteURL(request);
											boolean isPartnerRequest=currURL.indexOf(ChangeMgmtConstant.ISPARTNERPORTAL)!=-1;
											if(isPartnerRequest){
												//HashMap<?,?> accountSelectedDetails= (HashMap<?,?>)request.getSession().getAttribute("accountCurrentDetails"); 
												HashMap<?,?> accountSelectedDetails= (HashMap<?,?>)request.getSession().getAttribute("userAccessMapForSr"); 
												if(accountSelectedDetails!=null)
													catalogEntitlement_assetPage = (String)accountSelectedDetails.get(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG);
												
											}else{
												HashMap<?,?> hMap= (HashMap<?,?>)request.getSession().getAttribute("userAccessMapForSr");
												if(hMap!=null)
													catalogEntitlement_assetPage = (String)hMap.get(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG);
											}
											
											if(catalogEntitlement_assetPage!=null && catalogEntitlement_assetPage.equalsIgnoreCase("true")){
										%>
										
										<div class="portletBodyWrap rounded infoBox">
													<a href="#" onclick="showAccountPopup();"><span class="info"><spring:message code="requestInfo.link.openCatalogOrderPage"/></span></a>
										</div>
										
										<%}%>
										
										
								</div><!-- detailsBodyWrap -->
						</div><!-- right-column -->
					<div class="clear"></div><!-- clear -->
				</div><!-- content -->
			</div><!-- main-wrap -->
			<!--  <div id="dialog-form"></div>-->
	</div>
	<div id="dialog-form" style="display:none" title='<spring:message code="header.recentOrderHistory"/>'>		
	</div>
	<!-- New adition -->
    
	<!-- End  -->
<script type="text/javascript">
var confirmUrl="";
document.getElementById('bookmarkedAsset').style.display = 'none';
document.getElementById('allAsset').style.display = 'block';
document.getElementById('deviceLocationAsset').style.display = 'none';
document.getElementById('customerHierarchyAsset').style.display = 'none';
function showAccountPopup(){

	// this flag is declared in include.jsp and is set true to identify the popup data is populated for catalog order page.
	isCatalogPage = "true";
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTCONTINUEWITHOUTSELECTINGANASSET%>');
	//alert('isCatalogPage'+isCatalogPage);
	var currentURL = window.location.href;
		if(currentURL.indexOf('/partner-portal') != -1)
		{			
			var partnerURLWithoutParams;
			var partnerIndexOfQuestionMark = currentURL.indexOf("?");
			if (partnerIndexOfQuestionMark > 0) {
				partnerURLWithoutParams = currentURL.substring(0, partnerIndexOfQuestionMark);
			} else {
				partnerURLWithoutParams = currentURL;
			}
			partnerBaseURL = partnerURLWithoutParams.substring(0, partnerURLWithoutParams.lastIndexOf('/partner-portal') + '/partner-portal'.length) + "/catalogorder";
			showOverlay2();//Added for UI modification on July05 2013 by Pranav
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+partnerBaseURL;
			return;
		}
	
		//alert(dialog);
		var callAccountSelectionForAssetList="${callAccountSelectionForAssetList}";
		//alert('callAccountSelectionForAssetList'+callAccountSelectionForAssetList);
		showOverlay2();//Added for UI modification on July05 2013 by Pranav
		if(accountGrid==null){
				initializeAccountGrid(showAccGridinPopup);
		}else{

				if(accountGrid.getAllRowIds()=="")
					reloadAccountPopupGrid();
				else{
					
		    		accountGrid.filterBy(0,"true",true);
		    		if(accountGrid.getAllRowIds() != "" ){
						if(accountGrid.getAllRowIds().split(',').length == 1){
		    	        	jQuery('#button'+accountGrid.getAllRowIds()).click();
		    		 	}else{
		    		 		showAccGridinPopup();
			    		 	}
					}else{
						showAccGridinPopup();
						}
										 
				}
					
			}
}
function showAccGridinPopup(){
	var mdmLevel="${mdmLevelForAssetDetails}";
	
			dialog=jQuery('#totalContent').dialog({
				autoOpen: false,
				modal: true,
				height: 460,
				width: 700,
				position: ['center','top'],
				open: function(){
						/*alert('in open');*/
						jQuery('#totalContent').show();
						jQuery('#accountInitialize').show();
						jQuery('span.ui-dialog-title').text("<spring:message code="requestInfo.heading.accountSelection"/>");
		  			},
				close: function(event,ui){
   				  	//jQuery('.ui-dialog').empty().remove();
   					  hideOverlay();
   				  	  //dialog.dialog('destroy');
   				  	  dialog.removeClass( "Test" );
				 	  dialog=null;
				  	/*alert('in change Request close');*/
				  	jQuery('#accountInitialize').hide();
				  	
				  	if(ajaxAccountSelection=="success")
				    {
				  		ajaxSuccessFunction();
				  	}
					}
			});
		
		jQuery(document).scrollTop('0');
		dialog.dialog('open');
	
}
ajaxSuccessFunction=function updateRequest(){
	//alert('in update Request');
		showOverlay();
		//else{
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+populateURL("catalogorder",[],[]);
		//}
	};
	
	function cancelRequest(){
		dialog.dialog('close');
	}
	var searchCriteria="";
	function getURLWithViewType(type, value) {
		var url = "<portlet:resourceURL id='assetLiseLandingPageURL'></portlet:resourceURL>";
		if (type == 'CATEGORY') {
	        if(value == 'VIEWALL') {
	            url = "<portlet:resourceURL id='assetLiseLandingPageURL'><portlet:param name='view' value='VIEWALL' /></portlet:resourceURL>";
	            document.getElementById('allAsset').style.display = 'block';
	            document.getElementById('bookmarkedAsset').style.display = 'none';
	            document.getElementById('deviceLocationAsset').style.display = 'none';
	            document.getElementById('customerHierarchyAsset').style.display = 'none';
	        } else if(value == 'DFM') {
	            url = "<portlet:resourceURL id='assetLiseLandingPageURL'><portlet:param name='view' value='DFM' /></portlet:resourceURL>";
	        } else if(value == 'CSS') {
	            url = "<portlet:resourceURL id='assetLiseLandingPageURL'><portlet:param name='view' value='CSS' /></portlet:resourceURL>";
	        } else if(value == 'BOOKMARKED') {
	            url = "<portlet:resourceURL id='assetLiseLandingPageURL'><portlet:param name='view' value='BOOKMARKED' /></portlet:resourceURL>";
	            document.getElementById('allAsset').style.display = 'none';
	            document.getElementById('bookmarkedAsset').style.display = 'block';
	            document.getElementById('deviceLocationAsset').style.display = 'none';
	            document.getElementById('customerHierarchyAsset').style.display = 'none';
	        }
		}
		return url;
	};

	function openCatalogOrderPage(){
		//alert(populateURL("catalogorder",[],[]));
		var currentURL = window.location.href;
		if(currentURL.indexOf('/partner-portal') != -1)
		{
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL=service-requests";
			return;
		}
		else{
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+populateURL("catalogorder",[],[]);
		}
		
	};
	
	function viewAssetDetail(assetId, serialNumber){
		window.location.href='<portlet:renderURL><portlet:param name="action" value="showAssetDetailPage" /></portlet:renderURL>&assetId=' + assetId;
	};
	assetlistGrid = new dhtmlXGridObject('consumableAssetlistGrid');
	assetlistGrid.setImagePath("<html:imagesPath/>/gridImgs/");
	assetlistGrid.setHeader(autoAppendPlaceHolder("<spring:message code='orderSupplies.listHeader.assetFields'/>",18));
	assetlistGrid.attachHeader(",,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	assetlistGrid.setInitWidths("120,30,110,150,110,120,110,110,110,100,100,80,80,80,100,100,100,100,100,100,100,10,100,100");
	assetlistGrid.setColAlign("center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	assetlistGrid.setColTypes("ro,sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	assetlistGrid.setColSorting("na,na,str,str,str,str,str,str,str,str,str,na,na,str,na,na,na,na,str,na,na,str,na,na");
	assetlistGrid.enableResizing("false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true");
	//assetlistGrid.setColumnMinWidth("65","0");
	//assetlistGrid.setColumnMinWidth("30","1");
	assetlistGrid.enableAutoHeight(true);
    assetlistGrid.enableMultiline(true);
    assetlistGrid.enableHeaderMenuForButton('headerImageButton');
    assetlistGrid.setSkin("light");
    assetlistGrid.setLockColVisibility("true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
    assetlistGrid.enableColumnMove(true);
    assetlistGrid.sortIndex = null;
    assetlistGrid.columnChanged = true;
    assetlistGrid.attachEvent("onXLS", function() {
    	resetValue = false;
    	if(colsWidth==""){
    		resetValue = true;
    	}
        refreshGridSettingOnPage(assetlistGrid);
        dBPersistentFlag = false;
        document.getElementById('loadingNotification').style.display = 'block';
        
        /**** Added by Sourav ************/
        document.getElementById('paginationId').style.display = 'none';
        
    	if(!assetlistGrid.loadHiddenColumns(colsHidden)  && resetValue || gridToBeReset) {
            setDefaultHiddenColumns();
            gridToBeReset = false;
            resetValue = false;
        }
        if(assetlistGrid.a_direction=='asc'){
            assetlistGrid.setSortImgState(true, assetlistGrid.getDefaltSortImagIndex(assetlistGrid.sortIndex,2), 'asc');
        }else{
            assetlistGrid.setSortImgState(true, assetlistGrid.getDefaltSortImagIndex(assetlistGrid.sortIndex,2), 'des');
        }
		dBPersistentFlag = true;
    });
    assetlistGrid.attachEvent("onXLE", function() {
        document.getElementById('loadingNotification').style.display = 'none';
        
        /**** Added by Sourav ************/
        document.getElementById('paginationId').style.display = 'block'; 
        setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    });

	assetlistGrid.init();
	assetlistGrid.prftInit();
	assetlistGrid.enableHeaderMenuForButton('headerMenuButton');
	assetlistGrid.attachEvent("onFilterStart", function(indexes,values){
    	assetlistGrid.filterValues = ","+values;
    	customizedGridURL = updateGridURL(customizedGridURL, assetlistGrid.getSortColByOffset(), assetlistGrid.a_direction, assetlistGrid.filterValues);
    	setGridFilerTimer(reloadData);
    });
	assetlistGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
	assetlistGrid.setPagingSkin("bricks");
    assetlistGrid.loadOrder(colsOrder);
    assetlistGrid.loadPagingSorting(colsSorting,2);
    assetlistGrid.loadSize(colsWidth);
    assetlistGrid.filterValues = ",,,,,,,,,,,,,,,,,,";
	if(!"${searchValue}"){
		customizedGridURL = populateURLCriterias();
		assetlistGrid.loadXML(customizedGridURL);
	}
	else{
		searchCriteria = "${searchName}" + "^" + "${searchValue}";
		customizedGridURL = updateGridURL("<portlet:resourceURL id='assetLiseLandingPageURL'></portlet:resourceURL>&searchCriterias=" + searchCriteria, assetlistGrid.getSortColByOffset(), assetlistGrid.a_direction, assetlistGrid.filterValues);
		assetlistGrid.loadXML(customizedGridURL);
	}
	
	assetlistGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
	assetlistGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
		if (cellIndex > 1) {
			var cell=assetlistGrid.cellById(rowId,0);
			var row = assetlistGrid.getRowById(rowId);
			if(!row._expanded){
				cell.open();
			}else{
				cell.close();
			}
		}
    });
	assetlistGrid.attachEvent("onMouseOver", function(id,ind){
		if(ind!=0&&ind!=1){
			var style = assetlistGrid.cells(id,ind).cell.style;
		    style.cssText += ";cursor: pointer;";
			return true;
		}
    });
    
	assetlistGrid.attachEvent("onBeforeSorting", customColumnSort);
	assetlistGrid.sortRows = function(col, type, order) {}
	assetlistGrid.attachEvent("onPaging", function(count){
		refreshGridSettingOnPage(assetlistGrid);
	});
	function customColumnSort(ind) {
		callOmnitureAction('Device Finder', 'Device Finder List Sort');
		var a_state = assetlistGrid.getSortingState();
		if(a_state[0] == (ind)){
			assetlistGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
		}else {
			assetlistGrid.a_direction = "asc" ;
			assetlistGrid.columnChanged = true;
		}
		assetlistGrid.sortIndex = ind;
		customizedGridURL = updateGridURL(customizedGridURL, assetlistGrid.getSortColByOffset(), assetlistGrid.a_direction, assetlistGrid.filterValues);
	 	reloadData();
		return true;
	};
	
	function viewAssetDetails(assetId,serialNumber,assetTag,ipAddress,modelNo,deviceTag,contractNumber){
		callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTSELECTASSET%>');
		showOverlay2();//Added for UI modification on July05 2013 by Pranav
		
		var url="${assetRequestHistoryURLvar}&assetId="+assetId+"&t="+new Date().getTime();
		
		jQuery('#dialog-form').load(url,function(data){
			jQuery(document).scrollTop('0');
			
			initGridPopup(assetId,serialNumber,assetTag,ipAddress,modelNo,deviceTag,contractNumber);
		});

		
		return false;
		
	};

	
	function setDefaultHiddenColumns() {
		assetlistGrid.setColumnHidden(8,true);
		assetlistGrid.setColumnHidden(9,true);
		assetlistGrid.setColumnHidden(10,true);
		assetlistGrid.setColumnHidden(11,true);
		assetlistGrid.setColumnHidden(12,true);
		assetlistGrid.setColumnHidden(13,true);
		assetlistGrid.setColumnHidden(14,true);
		assetlistGrid.setColumnHidden(15,true);
		assetlistGrid.setColumnHidden(16,true);
		assetlistGrid.setColumnHidden(17,true);
		assetlistGrid.setColumnHidden(18,true);
		assetlistGrid.setColumnHidden(19,true);
		assetlistGrid.setColumnHidden(20,true);
		assetlistGrid.setColumnHidden(21,true);
		assetlistGrid.setColumnHidden(22,true);
		assetlistGrid.setColumnHidden(23,true);
	};
	function onSelectChange(){
		clearMessage();
		document.getElementById("txtSearchValue").value="";
	};
	function reloadData() {
		clearMessage();
		refreshGridSettingOnPage(assetlistGrid);
		assetlistGrid.clearAndLoad(customizedGridURL);
	};
    function populateURLCriterias(){
    	var url = "<portlet:resourceURL id='assetLiseLandingPageURL'></portlet:resourceURL>";
    	if(searchCriteria != null && searchCriteria != "") {
			url = url + "&searchCriterias=" + searchCriteria;
		}
		if(assetlistGrid.filterValues != null && assetlistGrid.filterValues != "") {
			url = url + "&filterCriterias=" + assetlistGrid.filterValues;
		}
        if(assetlistGrid.a_direction != null && assetlistGrid.a_direction != "") {
            url = url + "&direction=" + assetlistGrid.a_direction;
        }
        url = url + "&orderBy=" + assetlistGrid.getSortColByOffset();
        return url;
    };
    function download(type){
        window.location="${downloadAssetListURL}&downloadType=" + type;
	};
	function minimizeAll() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTMINIMIZEALL%>');
		assetlistGrid.forEachRow(function(id){
	   		var cell=assetlistGrid.cellById(id,1);
	   		cell.close();
	   		subRowOpenFlag=false;
	   	});
	};
	function expandAll() {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTEXPANDALL%>');
		
		/***Added By Sourav****/
		var state = assetlistGrid.getStateOfView();

		for (var i = state[1]; i<state[2] ; i++){ 
		var cell = assetlistGrid.cellByIndex(i,1);
		if (cell.open) cell.open(); 
		}
	};
	
	function doReset(){
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLIST%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESASSETLISTRESET%>');
		resetGridSetting('consumableAssetlistGrid', resetCallback);
	};
	function resetCallback() {
		clearMessage();
		colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23";
		colsWidth = "px:120,30,110,150,110,120,110,110,110,100,100,80,80,80,100,100,100,100,100,100,100,100,100,100";
		colsSorting = "2,asc";
		colsHidden = "";
		gridToBeReset = true;
		assetlistGrid.sortIndex = 1;
		dBPersistentFlag = false;
		assetlistGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
        assetlistGrid.loadOrder(colsOrder);
        assetlistGrid.loadPagingSorting(colsSorting,2);
        assetlistGrid.loadSize(colsWidth);
		customizedGridURL = updateGridURL(customizedGridURL, 2, 'asc', assetlistGrid.filterValues);
		assetlistGrid.clearAndLoad(customizedGridURL);
	};
	function print() {
		url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
			"<portlet:param name='action' value='printAssetList' />" + 
			"</portlet:renderURL>";
		url = url + "&pageNumber=" + assetlistGrid.currentPage;
	    var iWidth=1000;
	    var iHeight=600;
	    var iTop = (window.screen.availHeight-30-iHeight)/2;
	    var iLeft = (window.screen.availWidth-10-iWidth)/2;
	    window.open(url,
	    		'AssetList',
			    'height='+iHeight+
			    ',innerHeight='+
			    iHeight+',width='+
			    iWidth+',innerWidth='+
			    iWidth+',top='+iTop+
			    ',left='+iLeft+
			    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	};
    function hideSelect() {
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
	}  

	image_error= function (MyPicture) {
	  
	  document.getElementById(MyPicture).src = '<html:imagesPath/>/dummy_noimg.jpg'; 
	  
	};

</script>
<%@ include file="/WEB-INF/jsp/orderSuppliesAssetOrder/favoriteAsset.js.jsp"%>

<script type="text/javascript">
//---- Ominture script 
     portletName = "Device Finder List";
     addPortlet(portletName);
     pageTitle="<spring:message code="deviceFinder.label.allDevices" />";
</script>