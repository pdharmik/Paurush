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
<portlet:renderURL  var="paymentListURL">
	<portlet:param name="action" value="showPaymentListPage" />
</portlet:renderURL>
<portlet:resourceURL var="retrievePartnerAgreementListURL" id="retrievePartnerAgreementList"/>
<portlet:renderURL var="paymentDetailURL">
	<portlet:param name='action' value='showPaymentDetail' />
</portlet:renderURL>
<portlet:renderURL var="serviceRequestDetailURL">
	<portlet:param name='action' value='showRequestDetailPage' />
</portlet:renderURL>

<div id="dialogPaymentDetails" style="display: none;"></div>
<div class="main-wrap">
<!-- <div class="journal-content-article">
      <h2>Payments</h2>
</div> -->
	<div class="content">
	<!-- Filter Container Start -->
		<div class="left-nav">
			<div id="filterContainer" class="left-nav-inner overflow-hidden" >
				
<table class="displayGrid">
              <thead>
                <tr><th>&nbsp;</th>
                </tr></thead>
              
            </table><!-- left-nav-header -->
				<div class="margin-top-10px">
					<div class="left-nav-header"><h3><spring:message code="paymentRequest.link.viewRequests" /></h3></div>
					<A href="javascript:showPaymentList();">&nbsp;<spring:message code="paymentRequest.link.viewPayments" /></A>
				</div>
				<div class="margin-top-10px">
					<div class="left-nav-header"><h3><spring:message code="request.filters" /></h3></div>
					<div class="radioCss">
						<input id="filter1AllRequests" type="radio" name="paymentStatus" value="All" checked="checked">
						<label for="filter1AllRequests"><spring:message code="paymentRequest.filter.allRequests" /></label>
						<br>
						<input id="filter1PaidRequests" type="radio" name="paymentStatus" value="Paid">
						<label for="filter1PaidRequests"><spring:message code="paymentRequest.filter.paidRequests" /></label>
						<br>
						<input id="filter1UnpaidRequests" type="radio" name="paymentStatus" value="Not Paid">
						<label for="filter1UnpaidRequests"><spring:message code="paymentRequest.filter.unpaidRequests" /></label>
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
									<div class="div-style19">
										<span class="div-style20">
										<spring:message code="request.filter.from"/></span>
										<input class="localized-begin-date" type="text" name="localizedBeginDate" id="localizedBeginDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedBeginDate" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedBeginDate"/>
									</div>
									<div class="div-style19">
										<span class="div-style20"><spring:message code="request.filter.to"/></span>
										<input class="localized-end-date" type="text" name="localizedEndDate" id="localizedEndDate" size="5" readOnly="readonly"/>
										<img id="imgLocalizedEndDate" src="<html:imagesPath/>transparent.png" class="ui-icon calendar-icon imgLocalizedEndDate"/>
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
	<!-- Filter Container End -->
	
		<div class="right-column">
			<div class="grid-controls">
				<div class="utilities">
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
				<div class="expand-min">
					<ul>
						<li class="first">
							<a href="#grid" id='headerImageButton' title="<spring:message code="image.title.customize"/>"><img	src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" /></a>
							&nbsp;<a href="#grid" id='headerMenuButton' title="<spring:message code="image.title.customize"/>"><spring:message code="label.customize"/></a>
						</li>
						<li>
							<img src="<html:imagesPath/>transparent.png" class="ui-icon reset-icon cursor-pointer" height="25px" width="25px" onclick="doReset()" title="<spring:message code="image.title.reset"/>"/>
							<a href="javascript:doReset();" id="resetGridSetting" title="<spring:message code="image.title.reset"/>" ><spring:message code="label.reset"/></a>
						</li>
					</ul>
				</div><!-- expand-min -->
				<div class="clear-right"></div><!-- clear -->
			</div><!-- grid-controls -->
		
			<div class="portlet-wrap">
				<div class="portlet-wrap-inner">
					<div id="gridRLVPaymentRequestList" class="star-width100per-height100px"></div>
					<div id="loadingNotification" class='gridLoading'>
						<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
					</div>
					<div>
						<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
					</div>
				</div>
				<!-- portlet-wrap-inner -->
				<div class="portlet-footer">
					<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
				</div><!-- portlet-footer -->
			</div><!-- portlet-wrap -->
		</div><!-- right-column -->
	</div><!-- content -->
</div><!-- main-wrap -->

<!-- function for onLoad grid when first come into this page Start  -->

<script type="text/javascript">
	var retrievePaymentRequestURL='<portlet:resourceURL id="retrievePaymentRequestList"></portlet:resourceURL>'+ "&timezoneOffset="+timezoneOffset;

	dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
	paymentListGrid = new dhtmlXGridObject('gridRLVPaymentRequestList');
	paymentListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	paymentListGrid.setHeader(autoAppendPlaceHolder("<spring:message code='paymentRequest.headers'/>",22));//21
	var attachHeader = ",,#text_filter,#text_filter,#text_filter,#combo_filter,#combo_filter,#text_filter,#text_filter,#text_filter,,,,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"
	paymentListGrid.attachHeader(attachHeader);
	paymentListGrid.setColAlign("center,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
	paymentListGrid.setInitWidths("120,130,120,200,120,100,100,110,150,200,100,100,150,100,100,150,150,150,100,100,150,100");
	paymentListGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	paymentListGrid.setColSorting("na,str,str,str,str,str,na,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
	paymentListGrid.setLockColVisibility("true,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
	paymentListGrid.enableResizing("false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true");
	paymentListGrid.i18n.paging.notfound = "<spring:message code='page.listNotfound'/>";
	
	var requestStatusList = [];
	<c:forEach items="${paymentRequestStatusMap}" var="paymentRequestStatus" varStatus = "status" >
	requestStatusList[${status.index}] = ["${paymentRequestStatus.key}","${paymentRequestStatus.value}"];
	</c:forEach>
	paymentListGrid.setCustomizeCombo(requestStatusList,5); // add request status 

	var paymentStatusList = [];
	<c:forEach items="${paymentStatusMap}" var="paymentStatus" varStatus = "status" >
		paymentStatusList[${status.index}] = ["${paymentStatus.key}","${paymentStatus.value}"];
	</c:forEach>
	paymentListGrid.setCustomizeCombo(paymentStatusList,6); // add payment status	
	//Drop down list End
	paymentListGrid.enableAutoHeight(true);
  	paymentListGrid.enableMultiline(true);
  	paymentListGrid.setSkin("light");
  	paymentListGrid.enableColumnMove(true);
	paymentListGrid.init();
	paymentListGrid.prftInit();
	paymentListGrid.enablePaging(true, 20, 10, "pagingArea", true);
	paymentListGrid.setPagingSkin("bricks");
	paymentListGrid.sortIndex = null;
	if(colsSorting.length==0){
		paymentListGrid.a_direction = paymentListGrid.a_direction||"asc";
		paymentListGrid.setSortImgState(true,1,paymentListGrid.a_direction);
	}else{
		paymentListGrid.setSortImgState(true,colsSorting.split(",")[0],colsSorting.split(",")[1]);
	}
	paymentListGrid.columnChanged = true;
	paymentListGrid.enableHeaderMenuForButton('headerMenuButton','headerImageButton');
	paymentListGrid.enableHeaderMenuForButton('headerImageButton','headerImageButton');

	dBPersistentFlag = false;
	paymentListGrid.loadOrder(colsOrder);
	paymentListGrid.loadPagingSorting(colsSorting,1);
	paymentListGrid.loadSize(colsWidth);

	if(!paymentListGrid.loadHiddenColumns(colsHidden) && colsWidth=="") {
		setDefaultHiddenColumns();
	}
	dBPersistentFlag = true;
	
	paymentListGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");

	paymentListGrid.attachEvent("onXLS", function() {
		document.getElementById('loadingNotification').style.display = 'block';
		if(paymentListGrid.a_direction=='asc'){
			paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,2), 'asc');
        }else{
        	paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,2), 'des');
        }
	});
	
	paymentListGrid.attachEvent("onXLE", function() {
		setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	    document.getElementById('loadingNotification').style.display = 'none';
	});

	paymentListGrid.attachEvent("onMouseOver", function(id,ind){
		if(ind!=0&&ind!=1){
			var style = paymentListGrid.cells(id,ind).cell.style;
		    style.cssText += ";cursor: pointer;";
			return true;
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
	
	var paymentRequestsQueryOperator = {
			queryParams:["timezoneOffset","paymentStatus","beginDate","endDate","partnerAgreement","filterCriterias","direction","orderBy"],
			needSavedParams:["paymentStatus","beginDate2","endDate2","partnerAgreementId","filterCriterias","page"],
			page:0,

			initializePage:function(savedKeyValues){
				if(!savedKeyValues){
					//initialize date input with default value
					jQuery("#localizedBeginDate").val(localizeFormatDate(new Date().addDays(-30)));
					jQuery("#localizedEndDate").val(localizeDate(todayStr));
					return;
				}

				// initialize left filters
				jQuery("#filterContainer").find("input[type='radio']").each(function(index,element){
					if(savedKeyValues[element.name] == element.value){
						jQuery(element).attr("checked","checked");
					}
				});

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
				
				// sets the queryType, requestType, status
				jQuery("#filterContainer").find("input[type='radio']:checked").each(function(index,element){
					paymentRequestsQueryOperator[element.name] = element.value;
				});
								
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
				
				var queryURL = retrievePaymentRequestURL;
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
		retrieveQueryParamsUrl += "&gridId=gridRLVPaymentRequestList&timestamp=" + (new Date()).valueOf();
		jQuery.getJSON(retrieveQueryParamsUrl,function(result){
			paymentRequestsQueryOperator.initializePage(result.data);
			paymentListGrid.loadXML(paymentRequestsQueryOperator.getQueryURL());
			//attach "onSelect" event to partnerAgreementTree after initializePage
			partnerAgreementTree.attachEvent("onSelect", function(id){
				callOmnitureAction('Payment List - requests view', 'By Parnter Agreement');
				reloadGrid();
			});


			//blind "onBeforeSorting","onFilterStart","onPaging" events to paymentListGrid after paymentRequestsQueryOperator.initializePage
			paymentListGrid.attachEvent("onBeforeSorting", function(index){
				callOmnitureAction('Payment List - requests view', 'Payment Request List Sort');
				var a_state = paymentListGrid.getSortingState();
				if(a_state[0] == (index)){
					paymentListGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
				}else {
					paymentListGrid.a_direction = "asc" ;
					paymentListGrid.columnChanged = true;
				}
				paymentListGrid.sortIndex = index;
				if(paymentListGrid.a_direction=='asc'){
					paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,2), 'asc');
		        }else{
		        	paymentListGrid.setSortImgState(true, paymentListGrid.getDefaltSortImagIndex(paymentListGrid.sortIndex,2), 'des');
		        }		
				reloadGrid();	
				return true;
			});

			paymentListGrid.attachEvent("onFilterStart", function(indexes,values){
				paymentListGrid.filterValues = values;
				setGridFilerTimer(reloadGrid);	
		    });
		    
			paymentListGrid.attachEvent("onPaging", function(count){
				saveGridQueryParams("gridRLVPaymentRequestList",paymentRequestsQueryOperator.getNeedSavedKeyValues(),function(){
					refreshGridSettingOnPage(paymentListGrid);
				});
			});						
		});
	})();

	
	function reloadGrid(){
		clearMessage();
		refreshGridSettingOnPage(paymentListGrid);
		saveGridQueryParams("gridRLVPaymentRequestList",paymentRequestsQueryOperator.getNeedSavedKeyValues(),function(){
			paymentListGrid.clearAndLoad(paymentRequestsQueryOperator.getQueryURL());
		});
	}


	function setDefaultHiddenColumns(){
		paymentListGrid.setColumnHidden(3,true);
		paymentListGrid.setColumnHidden(8,true);
		paymentListGrid.setColumnHidden(9,true);
		paymentListGrid.setColumnHidden(10,true);
		paymentListGrid.setColumnHidden(11,true);
		paymentListGrid.setColumnHidden(12,true);
		paymentListGrid.setColumnHidden(13,true);
		paymentListGrid.setColumnHidden(15,true);
		paymentListGrid.setColumnHidden(16,true);
		paymentListGrid.setColumnHidden(17,true);
		paymentListGrid.setColumnHidden(18,true);
		paymentListGrid.setColumnHidden(19,true);
		paymentListGrid.setColumnHidden(20,true);
		paymentListGrid.setColumnHidden(21,true);
    }
    
	function doReset(){
		callOmnitureAction('Payment List - requests view', 'Payment Request List Reset');
		resetGridSetting('gridRLVPaymentRequestList', resetCallback);  
	};

    function resetCallback() {
        dBPersistentFlag = false;
        clearMessage();
       	colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21";
       	colsWidth = "px:120,200,120,200,120,100,100,200,150,200,100,100,150,100,100,150,150,150,100,100,150,100";
        colsSorting = "1,asc";
        colsHidden = "";
        paymentListGrid.sortIndex = 0;
        gridToBeReset = true;
        paymentListGrid.loadOrder(colsOrder);
        paymentListGrid.loadPagingSorting(colsSorting,1);
        paymentListGrid.setSortImgState(true,1,paymentListGrid.a_direction);
        paymentListGrid.loadSize(colsWidth);
        paymentListGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
		setDefaultHiddenColumns();
		paymentListGrid.clearFiltersValue();		
		reloadGrid();
        dBPersistentFlag = true;		
    };	

    function download(type){
    	callOmnitureAction('Payment List - requests view', 'payment Request List Download - ' + type);
		if(paymentListGrid.getRowId(1)==null){
			jAlert("<spring:message code='download.noDataFound'/>");
			return false;
		}
		var downloadURL='<portlet:resourceURL id="downloadPaymentRequests"/>'+ "&timezoneOffset="+timezoneOffset;
		window.location=downloadURL+"&downloadType="+type;
	};


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

	
	jQuery("#filterContainer").find("input[type=radio]").each(function(index,radioElement){
		jQuery(radioElement).click(reloadGrid);
	});
	
	jQuery("#dateRangeFilterContainer").find("input").mouseup(function(){
		var beginDate=this.id=="localizedBeginDate" ? convertDateToString(new Date().addDays(-180)) : formatDateToDefault(jQuery("#localizedBeginDate").val());
		var endDate=this.id=="localizedEndDate" ? todayStr : formatDateToDefault(jQuery("#localizedEndDate").val());
		show_cal(this.id, beginDate, endDate);
	});

	jQuery("#dateRangeFilterContainer").find("input ~ img").mouseup(function(){
		jQuery(this).prev().mouseup();
	});
	
	function showPaymentList(){
		callOmnitureAction('Payment List - requests view', 'Show Payment List Page');
		window.location.href="${paymentListURL}";
	}

	function showPaymentDetail(paymentId){
		callOmnitureAction('Payment List - requests view', 'Show Payment Detail');
		window.location.href="${paymentDetailURL}&paymentId=" + paymentId+ "&timezoneOffset="+timezoneOffset+"&from=paymentRequestList";
	}

	function showRequestDetailPage(serviceRequestId,activityId){
		callOmnitureAction('Payment List - requests view', 'Show Request Detail');
		window.location.href="${serviceRequestDetailURL}&serviceRequestId=" + serviceRequestId + "&activityId=" + activityId+ "&timezoneOffset="+timezoneOffset;
	}

	<%-- function showRequestDetailPopupPage(serviceRequestNumber){
		var serviceRequestNumber = serviceRequestNumber;
		var iLeft = (window.screen.availWidth-820)/2; 
	      new Liferay.Popup({
	           title: "<spring:message code='paymentDetail.label.PaymentDetails'/>",
	           position: [iLeft,50], 
	           modal: true,
	           width: 820, 
	           height: 'auto',
	           xy: [100, 100],
	           //onClose: function() {showSelect();},
	           url: "<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='paymentRequestDrillDown' /></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox"
	           });
	} --%>
	
	function showRequestDetailPopupPage(serviceRequestNumber){
		var serviceRequestNumber = serviceRequestNumber;
		var url="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name='action' value='paymentRequestDrillDown'/></portlet:renderURL>&serviceRequestNumber="+ serviceRequestNumber+"&lightBox=lightBox";
			showOverlay();	
				jQuery('#dialogPaymentDetails').load(url,function(){
					dialog=jQuery('#contentDetailsPayment').dialog({
					autoOpen: false,
					title: '<spring:message code="paymentDetail.label.PaymentDetails"/>',
					modal: true,
					draggable: false,
					resizable: false,
					position: 'center',
					height: 'auto',
					width: 820,
					open: function(){	
						jQuery('#contentDetailsPayment').show();
					},
					close: function(event,ui){
						hideOverlay();
						dialog.dialog('destroy');					 
						dialog=null;
						jQuery('#contentDetailsPayment').remove();
						},
					error: function (jqXHR, textStatus, errorThrown) {
						hideOverlay();
						dialog.dialog('destroy');					 
						dialog=null;
						jQuery('#contentDetailsPayment').remove();
						}
					});
				dialog.dialog('open');
				});						

	}

</script>