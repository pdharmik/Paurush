<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
<%@ include file="/WEB-INF/jsp/includeDatePicker.jsp"%>
<style type="text/css"><%@ include file="../../../css/tree/dhtmlxtree.css" %></style>
<script type="text/javascript" src="<html:rootPath/>js/tree/dhtmlxtree.js?version=<html:fileCacheVersion/>"></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<style type="text/css"><%@ include file="/WEB-INF/css/ci6.css" %></style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL  var="paymentRequestsListPageURL"/>
<portlet:resourceURL var="retrievePartnerAgreementListURL" id="retrievePartnerAgreementList"/>
<portlet:renderURL var="paymentDetailURL">
	<portlet:param name='action' value='showPaymentDetail' />
</portlet:renderURL>
	
<div class="main-wrap">
<!-- <div class="journal-content-article">
      <h2>Payments</h2>
</div> -->


	<div class="content">
		<div class="left-nav">
			<div class="left-nav-inner overflow-hidden">
				
<table class="displayGrid">
              <thead>
                <tr><th>&nbsp;</th>
                </tr></thead>
              
            </table>
				<!-- left-nav-header -->
				<div class="margin-top-10px">
					&nbsp;<a href="javascript:showPaymentRequestsListPage();"><spring:message code="paymentRequest.link.viewRequests" /></a><br>
					&nbsp;<b><spring:message code="paymentRequest.link.viewPayments" /></b>
				</div>
				<div class="margin-top-10px">
					<b>&nbsp;<spring:message code="request.filters" /></b>
				</div>
				<div class="radioCss">
					<table id="dateRangeFilterContainer" class="margin-top-5px">
						<tr>
							<td class="padding-top-1px">
								<img src="<html:imagesPath/>treeImgs/minus5.gif" class="cursor-pointer"/>
							</td>
							<td>
								<spring:message code="request.filter.byDateRange"/>
							</td>
						</tr>
						<tr>
							<td class="padding-top-3px"></td>
							<td>
								<div class="text-align-left">
									<div class="div-style19">
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
<%-- 							             <a href="javascript:void(0)" class="button"><span><spring:message code="request.button.go"/></span></a> --%>
										 <button class="button"><span><spring:message code="request.button.go"/></span></button>
							            </div>
										<!-- </a> -->
									</div>
								</div>
							</td>
						</tr>
					</table>
					<table class="margin-top-10px">
						<div id="treePartnerAgreement" class="treePartnerAgreement"></div>
					</table>
				</div>
			</div><!-- left-nav-inner -->
			<div class="left-nav-footer"></div><!-- left-nav-footer -->
		</div><!-- left-nav -->
		<div class="right-column">
			<div class="grid-controls">
				<div class="utilities">
				<ul>
					<li class="first"><img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer" onclick="download('pdf')"	height="23px" width="23px" title="<spring:message code='image.title.exportPDF'/>" /></li>
					<li><img src="<html:imagesPath/>transparent.png" class="ui-icon spreadsheet-icon cursor-pointer" height="23px" onclick="download('csv')" width="23px" title="<spring:message code='image.title.exportExcel'/>" /></li>
					<li><img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" onclick="javascript:window.print();" height="23px" width="23px" title="<spring:message code='image.title.print'/>" /></li>
				</ul>
				</div><!-- utilities -->
				<div class="expand-min">
				<ul>
				<li class="first">
					<a href="#grid" id='headerImageButton' title="<spring:message code="image.title.customize"/>">
						<img src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" />
					</a>&nbsp;
					<a href="#grid" id='headerMenuButton' title="<spring:message code="image.title.customize"/>">
						<spring:message code="label.customize"/>
					</a>
				</li>
				<li>
					<img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon cursor-pointer" height="25px" width="25px" onclick="doReset()" title="<spring:message code="image.title.reset"/>"/>
					<a href="javascript:doReset();" id="resetGridSetting" title="<spring:message code="image.title.reset"/>" ><spring:message code="label.reset"/></a>
				</li>
				</ul>
				</div><!-- expand-min -->
				<div class="clear-right"></div><!-- clear -->
			</div><!-- grid-controls -->
			
			<!-- Payment List Grid Start -->
			<div class="portlet-wrap">
				<div class="portlet-wrap-inner">
					<div id="gridPLVPaymentListGrid" class="star-width100per-height100px"></div>
					<div id="loadingGrid" class='gridLoading'><br>
						<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
					</div>
					<div>
						<span id="gridPLVPaymentListGridPagingArea"></span>&nbsp;<span id="gridPLVPaymentListGridInfoArea"></span>
					</div>
				</div><!-- portlet-wrap-inner -->
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div><!-- portlet-wrap -->
			<!-- Payment List Grid End -->
			
		</div><!-- right-column -->
		<div class="clear"></div><!-- clear -->
	</div><!-- content -->
</div><!-- main-wrap -->

<script type="text/javascript">
	var retrievePaymentListURL = "<portlet:resourceURL id='retrievePaymentList'/>"+ "&timezoneOffset="+timezoneOffset;
	dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	var paymentListGrid = new dhtmlXGridObject('gridPLVPaymentListGrid');
	paymentListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	paymentListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='payment.payment.headers'/>",14));
	paymentListGrid.attachHeader(",#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter");
	paymentListGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	paymentListGrid.setInitWidths("100,130,130,130,130,130,130,130,130,130,100,100,100,100");
	paymentListGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	paymentListGrid.setColSorting("str,str,str,str,str,str,str,str,str,str,str,str,str,str");
	paymentListGrid.setLockColVisibility("false,true,false,false,false,false,false,false,false,false,false,false,false,false");
	paymentListGrid.enableAutoHeight(true);
  	paymentListGrid.enableMultiline(true);
  	paymentListGrid.setSkin("light");
  	paymentListGrid.enableColumnMove(true);
	paymentListGrid.init();
	paymentListGrid.prftInit();
	paymentListGrid.enablePaging(true, 20, 10, "gridPLVPaymentListGridPagingArea", true, "gridPLVPaymentListGridPagingArea");
	paymentListGrid.setPagingSkin("bricks");
	paymentListGrid.sortIndex = null;
	paymentListGrid.i18n.paging.notfound = "<spring:message code='page.listNotfound'/>";
	if(colsSorting.length==0){
		paymentListGrid.a_direction = paymentListGrid.a_direction||"asc";
		paymentListGrid.setSortImgState(true,0,paymentListGrid.a_direction);
	}else{
		paymentListGrid.setSortImgState(true,colsSorting.split(",")[0],colsSorting.split(",")[1]);
	}	
	paymentListGrid.columnChanged = true;
	paymentListGrid.enableHeaderMenuForButton('headerMenuButton','headerImageButton');
	paymentListGrid.enableHeaderMenuForButton('headerImageButton','headerImageButton');

	dBPersistentFlag = false;
	paymentListGrid.loadOrder(colsOrder);
	paymentListGrid.loadPagingSorting(colsSorting,0);
	paymentListGrid.loadSize(colsWidth);

	paymentListGrid.attachEvent("onXLS", function() {
		document.getElementById('loadingGrid').style.display = 'block';
	});
	paymentListGrid.attachEvent("onXLE", function() {
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	    document.getElementById('loadingGrid').style.display = 'none';
	});
	paymentListGrid.attachEvent("onMouseOver", function(id,ind){
		if(ind!=0&&ind!=1){
			var style = paymentListGrid.cells(id,ind).cell.style;
		    style.cssText += ";cursor: pointer;";
			return true;
		}
    });

	if(!paymentListGrid.loadHiddenColumns(colsHidden) && colsWidth=="") {
		setDefaultHiddenColumns();
	}
	dBPersistentFlag = true; 

	paymentListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
	
	paymentListGrid.attachEvent("onXLS", function() {
		document.getElementById('loadingGrid').style.display = 'block';

		if(paymentListGrid.a_direction=='asc'){
			paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,0), 'asc');
        }else{
        	paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,0), 'des');
        }
	});


	var partnerAgreementTree = new dhtmlXTreeObject("treePartnerAgreement","100%","100%",0);
	partnerAgreementTree.setSkin('dhx_skyblue');
	partnerAgreementTree.setImagePath("<html:imagesPath/>treeImgs/");
	partnerAgreementTree.enableTreeLines(true);
	if(jQuery.browser.ie) {
		partnerAgreementTree.enableIEImageFix(true);
	}

	
	var partnerAgreementsXML = '${partnerAgreementsXML}';
	if(partnerAgreementsXML){
		partnerAgreementTree.loadXMLString(partnerAgreementsXML);
	}

	var paymentsQueryOperator = {
			queryParams:["timezoneOffset","beginDate","endDate","partnerAgreement","filterCriterias","direction","orderBy"],
			needSavedParams:["beginDate2","endDate2","partnerAgreementId","filterCriterias","page"],
			page:0,

			initializePage:function(savedKeyValues){
				if(!savedKeyValues){
					//initialize date input with default value
					jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
					jQuery("#localizedEndDate").val(localizeDate(todayStr));
					return;
				}

				if(savedKeyValues["partnerAgreementId"]){
					partnerAgreementTree.selectItem(savedKeyValues["partnerAgreementId"]);
				}
				
				// initialize date range filter
				if(savedKeyValues["beginDate2"] && savedKeyValues["beginDate2"].length > 0)
					jQuery("#localizedBeginDate").val(savedKeyValues["beginDate2"]);
				if(savedKeyValues["endDate2"] && savedKeyValues["endDate2"].length > 0)
					jQuery("#localizedEndDate").val(savedKeyValues["endDate2"]);

				//revert pageNum
				var first=true;
				if(savedKeyValues["page"]){
					paymentListGrid.attachEvent("onXLE", function() {
						if(first){
							paymentListGrid.changePage(savedKeyValues["page"]);
							first = false;
						}
					});
				}
				
				// initialize Grid Header filter
				if(savedKeyValues["filterCriterias"] && savedKeyValues["filterCriterias"].length > 0)
					paymentListGrid.setFiltersValue(savedKeyValues["filterCriterias"]);
			},
			
			_clear:function(){
				for(var k=0;k<this.needSavedParams.length;k++){
					this[this.needSavedParams[k]] = null;
				}
				for(var k=0;k<this.queryParams.length;k++){
					this[this.queryParams[k]] = null;
				}
			},
			
			_refresh:function(){
				this._clear();
				// sets the beginDate and endDate, This two date values for query 
				this["beginDate"] = formatDateToDefault(jQuery("#localizedBeginDate").val());
				this["endDate"] = formatDateToDefault(jQuery("#localizedEndDate").val());
				// This two date values for store and restore
				this["beginDate2"] = jQuery("#localizedBeginDate").val();
				this["endDate2"] = jQuery("#localizedEndDate").val();

				if(partnerAgreementTree != null && partnerAgreementTree.getSelectedItemId() != 1 && partnerAgreementTree.getSelectedItemText()){
					if(partnerAgreementTree.getOpenState(1) == 1){
						this["partnerAgreementId"] = partnerAgreementTree.getSelectedItemId();
						this["partnerAgreement"] = partnerAgreementTree.getSelectedItemText();
					}	
				}
				
				// sets properties that from Grid
				this["filterCriterias"] = paymentListGrid.filterValues;
				this["direction"] = paymentListGrid.a_direction;
				paymentListGrid.columnChanged = true;			
				this["orderBy"] = paymentListGrid.getSortColByOffset();
				this["page"] = paymentListGrid.currentPage;

				this["timezoneOffset"] = timezoneOffset;
			},
			
			//return a string of JSON that contains several key-value pairs which need to persistent
			getNeedSavedKeyValues:function(){
				this._refresh();
				
				var keyValus =	'{' ;
				for(var k=0;k<this.needSavedParams.length;k++){
					if(this[this.needSavedParams[k]]){
						keyValus += "\""+this.needSavedParams[k]+"\"" +':"'+this[this.needSavedParams[k]]+'"';
						if(k != this.needSavedParams.length-1)
							keyValus += ',';
					}
				}
				keyValus += '}';
				return keyValus;
			},
			
			getQueryURL:function(){
				this._refresh();
				
				var queryURL = retrievePaymentListURL;
				for(var k=0;k<this.queryParams.length;k++){
					if(this[this.queryParams[k]]){
						queryURL += "&" + this.queryParams[k] + "=" + this[this.queryParams[k]];
					}
				}
			    return queryURL;
			}
		};	

		(function(){
			var retrieveQueryParamsUrl = '<portlet:resourceURL id="retrieveQueryParams"></portlet:resourceURL>';
			retrieveQueryParamsUrl += "&gridId=gridPLVPaymentListGrid&timestamp=" + (new Date()).valueOf();
			jQuery.getJSON(retrieveQueryParamsUrl,function(result){
				paymentsQueryOperator.initializePage(result.data);
				paymentListGrid.loadXML(paymentsQueryOperator.getQueryURL());
				
				//attach these events should after initializePage
				partnerAgreementTree.attachEvent("onSelect", function(id){
					callOmnitureAction('Payment List - Payments View', 'By Parnter Agreement');
					reloadGrid();
				});

				paymentListGrid.attachEvent("onBeforeSorting", function(index){
					callOmnitureAction('Payment List - Payments View', 'Payment List Sort');
					var a_state = paymentListGrid.getSortingState();
					if(a_state[0] == (index)){
						paymentListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
					}else {
						paymentListGrid.a_direction = "asc" ;
						paymentListGrid.columnChanged = true;
					}
					paymentListGrid.sortIndex = index;
					if(paymentListGrid.a_direction=='asc'){
						paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,0), 'asc');
			        }else{
			        	paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,0), 'des');
			        }			
					reloadGrid();	
					return true;
				});

				paymentListGrid.attachEvent("onFilterStart", function(indexes,values){
			    	paymentListGrid.filterValues = values;
					setGridFilerTimer(reloadGrid);	
			    });
			    
				paymentListGrid.attachEvent("onPaging", function(count){
					saveGridQueryParams("gridPLVPaymentListGrid",paymentsQueryOperator.getNeedSavedKeyValues(),function(){
						refreshGridSettingOnPage(paymentListGrid);
					});
				});
								
			});
		})();
		
	//blinding click event
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
		
	function reloadGrid(){
		clearMessage();
		refreshGridSettingOnPage(paymentListGrid);
		saveGridQueryParams("gridPLVPaymentListGrid",paymentsQueryOperator.getNeedSavedKeyValues(),function(){
			paymentListGrid.clearAndLoad(paymentsQueryOperator.getQueryURL());
		});		
	}
	
	function setDefaultHiddenColumns(){
		paymentListGrid.setColumnHidden(5,true);
		paymentListGrid.setColumnHidden(6,true);
		paymentListGrid.setColumnHidden(7,true);
		paymentListGrid.setColumnHidden(8,true);
		paymentListGrid.setColumnHidden(9,true);
		paymentListGrid.setColumnHidden(10,true);
		paymentListGrid.setColumnHidden(11,true);
		paymentListGrid.setColumnHidden(12,true);
		paymentListGrid.setColumnHidden(13,true);
    }
    
	function doReset(){
		callOmnitureAction('Payment List - Payments View', 'Payment List Reset');
		resetGridSetting('gridPLVPaymentListGrid', resetCallback);  
	};

    function resetCallback() {
        dBPersistentFlag = false;
        clearMessage();
        colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13";
        colsWidth = "px:100,130,130,130,130,130,130,130,130,130,100,100,100,100";
        colsSorting = "0,asc";
        colsHidden = "";
        paymentListGrid.sortIndex = 0;
        gridToBeReset = true;
        paymentListGrid.loadOrder(colsOrder);
        paymentListGrid.loadPagingSorting(colsSorting,0);
        paymentListGrid.setSortImgState(true,0,paymentListGrid.a_direction);
        paymentListGrid.loadSize(colsWidth);
        paymentListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false");
		setDefaultHiddenColumns();
		paymentListGrid.clearFiltersValue();		
		reloadGrid();
        dBPersistentFlag = true;
    };

    function download(type){
		callOmnitureAction('Payment List - Payments View', 'payment List Download - ' + type);
		if(paymentListGrid.getRowId(0)==null){
			jAlert("<spring:message code='download.noDataFound'/>");
			return false;
		}
		var downloadURL='<portlet:resourceURL id="downloadPayments"/>'+ "&timezoneOffset="+timezoneOffset;
		window.location=downloadURL+"&downloadType="+type;
	};
	

	
	function showPaymentRequestsListPage(){
		callOmnitureAction('Payment List - Payments View', 'Show Payment - requests list Page');
		window.location.href="${paymentRequestsListPageURL}";
	}

	function showPaymentDetail(paymentId){
		callOmnitureAction('Payment List - Payments View', 'Show Payment Detail');
		window.location.href="${paymentDetailURL}&paymentId=" + paymentId+ "&timezoneOffset="+timezoneOffset;
	}
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Payment List Page";
     addPortlet(portletName);
</script>