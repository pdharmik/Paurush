<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL var="paymentListURL">
	<portlet:param name="action" value="showPaymentListPage"></portlet:param>
</portlet:renderURL>

<portlet:renderURL  var="paymentRequestsListPageURL">
</portlet:renderURL>

<portlet:resourceURL var="downPaymentDetailPdfURL" id="downPaymentDetailPdfURL">
 <portlet:param name="timezoneOffset" value="${timezoneOffset}" />
</portlet:resourceURL>


<div class="main-wrap width-100per-important" >
	<div class="content">
		<div class = "grid-controls">
			<div class="utilities">
					<ul>
						<li><img src="<html:imagesPath/>transparent.png" class="ui-icon print-icon cursor-pointer" height="23px" width="23px" onClick="print()" title="<spring:message code='image.title.print'/>" /></li>
						<li>
							<img src="<html:imagesPath/>transparent.png" class="ui-icon pdf-icon cursor-pointer" height="23px" width="23px" onClick="downloadPdf()";  title="<spring:message code='image.title.exportPDF'/>"; />
						</li>
					</ul>
					
			</div><!-- utilities -->
			<div class="expand-min">
					<ul>
						<li class="first">
							<c:choose>
								<c:when test="${empty from}">
									<a href="${paymentListURL}">&lt;&lt;<spring:message code="link.return.to.paymentRequestList"/></a>
								</c:when>
								<c:otherwise>
									<a href="${paymentRequestsListPageURL}">&lt;&lt;<spring:message code="link.return.to.paymentRequestList"/></a>
								</c:otherwise>
							</c:choose>	
						</li>
					</ul>
			</div><!-- expand-min -->
			<div class="clear-right"></div><!-- clear -->
		</div><!-- grid-controls -->
		
		<!-- Payment Information start -->
		<div class="portlet-wrap" >
			<div class="portlet-header">
				<h3><spring:message code="paymentDetail.label.PaymentInformation"/></h3>
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div id="paymentInfo" class="columns two">
						<div class="first-column">
							<table>
							  <tr>
							  	<td width="800px" align="right" valign="top">
									<span ><B><spring:message code="paymentDetail.label.dateCreated"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>
										<util:dateFormat value="${paymentDetail.payment.dateCreated}" timezoneOffset="${timezoneOffset}" />
									</span>
		  	  				  	</td>
		  	  				  </tr>
		  	  				 <tr>
							  	<td width="800px" align="right" valign="top">
									<span><B><spring:message code="paymentDetail.label.paymentNumber"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>${paymentDetail.payment.paymentNumber}</span>
		  	  				  	</td>
		  	  				  </tr>
		  	  				    <tr>
							  	<td width="800px" align="right" valign="top">
									<span><B><spring:message code="paymentDetail.label.paymentStatus"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>${paymentDetail.localizedPaymentStatus.name}</span>
		  	  				  	</td>
		  	  				  </tr>
		  	  				    <tr>
							  	<td width="800px" align="right" valign="top">
									<span><B><spring:message code="paymentDetail.label.paymentTotal"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>${paymentDetail.payment.paymentTotal}</span>
		  	  				  	</td>
		  	  				  </tr>
	    					</table>
						</div><!-- first-column -->
						<div class="second-column">
							<table>
							  <tr>
							  	<td width="800px" align="right" valign="top">
									<span ><B><spring:message code="paymentDetail.label.paymentAccount"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>${paymentDetail.payment.partnerAccount.accountName}</span>
		  	  				  	</td>
		  	  				  </tr>
		  	  				 <tr>
							  	<td width="800px" valign="top" align="right" >
									<span><B><spring:message code="paymentDetail.label.paymentAgreement"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>${paymentDetail.payment.partnerAgreement}</span>
		  	  				  	</td>
		  	  				  </tr>
		  	  				    <tr>
							  	<td width="800px" valign="top" align="right" >
									<span><B><spring:message code="paymentDetail.label.payableTo"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>${paymentDetail.payment.payableToName}</span>
		  	  				  	</td>
		  	  				  </tr>
		  	  				    <tr>
							  	<td width="800px" valign="top" align="right" >
									<span><B><spring:message code="paymentDetail.label.payableAddress"/>&nbsp;&nbsp;&nbsp;&nbsp;</B></span>
		  	  				  	</td>
		  	  				  	<td width="800px" valign="top">
									<span>
										<util:addressOutput value="${paymentDetail.payment.partnerAccount.address}"/>
									</span>
		  	  				  	</td>
		  	  				  </tr>
	    					</table>
						</div><!-- second-column -->
					</div>
				</div>
			</div>	
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div>
			</div>			
		</div>
		<!-- Payment Information end -->		
		
		<div class="portlet-wrap">
			<div class="portlet-header">
				<h3><spring:message code="paymentDetail.label.PaymentDetails"/></h3>
			</div>
			<div class="portlet-wrap-inner">
				<div class="width-100per">
					<div class="grid-controls">
						<div class="expand-min">
							<ul>
								<li>
									<a href="#grid" id='headerImageButton' title="<spring:message code="image.title.customize"/>"><img	src="<html:imagesPath/>transparent.png" class="ui-icon customize-icon cursor-pointer" /></a>
									&nbsp;<a href="#grid" id='headerMenuButton' title="<spring:message code="image.title.customize"/>"><spring:message code="label.customize"/></a>
								</li>
								<li>
									<a href="javascript:doReset();" id="resetGridSetting" title="<spring:message code="image.title.reset"/>" ><spring:message code="label.reset"/></a>
								</li>
							</ul>
						</div><!-- expand-min -->
					</div><!-- grid-controls -->
					<div class="width-100per">
						<div id="gridPDVPaymentDetails" class="star-width100per-height100px"></div>
						<div id="loadingNotification" class='gridLoading'>
							<!--spring:message code="label.loadingNotification"/-->&nbsp;<img src="<html:imagesPath/>gridloading.gif" /><br>
						</div>
						<div>
							<span id="pagingArea"></span>&nbsp;<span id="infoArea"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-footer">
				<div class="portlet-footer-inner"></div><!-- portlet-footer-inner -->
			</div>
		</div>		
		

		<script type="text/javascript">	
		   	dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
			var attachHeader =",#text_filter,#text_filter,#text_filter,,,,,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter";
			var paymentLineItemGrid = new dhtmlXGridObject('gridPDVPaymentDetails');
			paymentLineItemGrid.setImagePath("<html:imagesPath/>gridImgs/");
			paymentLineItemGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.paymentDetails"/>',15));
			paymentLineItemGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
			paymentLineItemGrid.setInitWidths("170,150,150,150,135,135,135,135,135,150,150,150,150,150,150");
			paymentLineItemGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
			paymentLineItemGrid.setColSorting("str,str,str,str,price,price,price,price,price,str,str,str,str,str,str");
			paymentLineItemGrid.attachHeader(attachHeader);
			paymentLineItemGrid.enableAutoHeight(true);
			paymentLineItemGrid.enableMultiline(true);
			paymentLineItemGrid.enableColumnMove(true);
			paymentLineItemGrid.setSkin("light");
			paymentLineItemGrid.init(); 
			paymentLineItemGrid.prftInit();
			paymentLineItemGrid.enablePaging(true, 20, 10, "pagingArea", true);
			paymentLineItemGrid.setPagingSkin("bricks");
			paymentLineItemGrid.sortIndex = null;
			if(colsSorting.length==0){
				paymentLineItemGrid.a_direction = paymentLineItemGrid.a_direction||"asc";
				paymentLineItemGrid.setSortImgState(true,0,paymentLineItemGrid.a_direction);
			}else{
				paymentLineItemGrid.setSortImgState(true,colsSorting.split(",")[0],colsSorting.split(",")[1]);
			}
			paymentLineItemGrid.columnChanged = true;
			paymentLineItemGrid.enableHeaderMenuForButton('headerMenuButton','headerImageButton');
			paymentLineItemGrid.enableHeaderMenuForButton('headerImageButton','headerImageButton');		
			
			dBPersistentFlag = false;
			paymentLineItemGrid.loadOrder(colsOrder);
			paymentLineItemGrid.loadPagingSorting(colsSorting,0);
			paymentLineItemGrid.loadSize(colsWidth);

			if(!paymentLineItemGrid.loadHiddenColumns(colsHidden) && colsWidth=="") {
				setDefaultHiddenColumns();
			}
			dBPersistentFlag = true;
			
			paymentLineItemGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
			
			paymentLineItemGrid.attachEvent("onXLS", function() {
				document.getElementById('loadingNotification').style.display = 'block';
				if(paymentLineItemGrid.loadHiddenColumns(colsHidden) || colsWidth!="" || gridToBeReset) {
					<c:choose>
						<c:when test="${paymentDetail.allowAdditionalPaymentRequest}">
							paymentLineItemGrid.setLockColVisibility("false,true,false,false,false,false,false,false,false,false,false,false,false,false,false");
						</c:when>
						<c:otherwise>
							paymentLineItemGrid.setColumnHidden(6,true);
							paymentLineItemGrid.setLockColVisibility("false,true,false,false,false,false,true,false,false,false,false,false,false,false,false");
						</c:otherwise>	
					</c:choose>					
				}
				if(paymentLineItemGrid.a_direction=='asc'){
					paymentLineItemGrid.setSortImgState(true, paymentLineItemGrid.getDefaltSortImagIndex(paymentLineItemGrid.sortIndex,1), 'asc');
		        }else{
		        	paymentLineItemGrid.setSortImgState(true, paymentLineItemGrid.getDefaltSortImagIndex(paymentLineItemGrid.sortIndex,1), 'des');
		        }
			});
			
			paymentLineItemGrid.attachEvent("onXLE", function() {
				setTimeout(function(){
		    		rebrandPagination();
		    	
		    	},100);
			    document.getElementById('loadingNotification').style.display = 'none';
			});
			
			paymentLineItemGrid.attachEvent("onMouseOver", function(id,ind){
				if(ind!=0&&ind!=1){
					var style = paymentLineItemGrid.cells(id,ind).cell.style;
				    style.cssText += ";cursor: pointer;";
					return true;
				}
		    });
			
			
			paymentLineItemGrid.attachEvent("onBeforeSorting", function(index){
				callOmnitureAction('Request', 'Request List Sort');
				var a_state = paymentLineItemGrid.getSortingState();
				if(a_state[0] == (index)){
					paymentLineItemGrid.a_direction = ((a_state[1] == "asc") ? "des":"asc" );
				}else {
					paymentLineItemGrid.a_direction = "asc" ;
					paymentLineItemGrid.columnChanged = true;
				}
				paymentLineItemGrid.sortIndex = index;
				if(paymentLineItemGrid.a_direction=='asc'){
					paymentLineItemGrid.setSortImgState(true, paymentLineItemGrid.getDefaltSortImagIndex(paymentLineItemGrid.sortIndex,1), 'asc');
		        }else{
		        	paymentLineItemGrid.setSortImgState(true, paymentLineItemGrid.getDefaltSortImagIndex(paymentLineItemGrid.sortIndex,1), 'des');
		        }				
				reloadGrid();	
				return true;
			});
			

			paymentLineItemGrid.attachEvent("onFilterStart", function(indexes,values){
				paymentLineItemGrid.filterValues = values;
				setGridFilerTimer(reloadGrid);	
		    });	
			
			
			paymentLineItemGrid.attachEvent("onPaging", function(count){
				saveGridQueryParams("gridPDVPaymentDetails",paymentLineItemsQueryOperator.getNeedSavedKeyValues(),function(){
					refreshGridSettingOnPage(paymentLineItemGrid);
				});				
			});


			var paymentLineItemsQueryOperator = {
					queryParams:["timezoneOffset","filterCriterias","direction","orderBy"],
					needSavedParams:["filterCriterias","page"],
					page:0,

					initializePage:function(savedKeyValues){
						if(!savedKeyValues)
							return;

						//revert pageNum
						var first=true;
						if(savedKeyValues["page"]){
							paymentLineItemGrid.attachEvent("onXLE", function() {
								if(first){
									paymentLineItemGrid.changePage(savedKeyValues["page"]);
									first = false;
								}
							});
						}
						
						// initialize Grid Header filter
						if(savedKeyValues["filterCriterias"] && savedKeyValues["filterCriterias"].length > 0)
							paymentLineItemGrid.setFiltersValue(savedKeyValues["filterCriterias"]);
					},

					_refresh:function(){
						// sets properties that from Grid
						this["filterCriterias"] = paymentLineItemGrid.filterValues;
						this["direction"] = paymentLineItemGrid.a_direction;
						paymentLineItemGrid.columnChanged = true;
						this["orderBy"] = paymentLineItemGrid.getSortColByOffset();
						this["page"] = paymentLineItemGrid.currentPage;

						this["timezoneOffset"] = timezoneOffset;
					},
					
					//return a string of JSON that contains several key-value pairs which need to persistent
					getNeedSavedKeyValues:function(){
						this._refresh();
						
						var keyValus =	'{' ;
						for(var k=0;k<this.needSavedParams.length;k++){
							if(this[this.needSavedParams[k]]){
								keyValus += this.needSavedParams[k] +':"'+this[this.needSavedParams[k]]+'"';
								if(k != this.needSavedParams.length-1)
									keyValus += ',';
							}
						}
						keyValus += '}';
						return keyValus;
					},
					
					getQueryURL:function(){
						this._refresh();

						var queryURL = '<portlet:resourceURL id="getPaymentLineItemList"></portlet:resourceURL>' + '&paymentId=' + '${paymentDetail.payment.paymentId}';
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
					retrieveQueryParamsUrl += "&gridId=gridPDVPaymentDetails&timestamp=" + (new Date()).valueOf();
					jQuery.getJSON(retrieveQueryParamsUrl,function(result){
						paymentLineItemsQueryOperator.initializePage(result.data);
						paymentLineItemGrid.loadXML(paymentLineItemsQueryOperator.getQueryURL());
					});
				})();
			
			function reloadGrid(){
				clearMessage();
				refreshGridSettingOnPage(paymentLineItemGrid);
				saveGridQueryParams("gridPDVPaymentDetails",paymentLineItemsQueryOperator.getNeedSavedKeyValues(),function(){
					paymentLineItemGrid.clearAndLoad(paymentLineItemsQueryOperator.getQueryURL());
				});
			}
			
			
			function setDefaultHiddenColumns(){
				paymentLineItemGrid.setColumnHidden(2,true);
				paymentLineItemGrid.setColumnHidden(9,true);
				paymentLineItemGrid.setColumnHidden(10,true);
				paymentLineItemGrid.setColumnHidden(11,true);
				paymentLineItemGrid.setColumnHidden(12,true);
				paymentLineItemGrid.setColumnHidden(13,true);
				<c:choose>
					<c:when test="${paymentDetail.allowAdditionalPaymentRequest}">
						paymentLineItemGrid.setLockColVisibility("false,true,false,false,false,false,false,false,false,false,false,false,false,false,false");
					</c:when>
					<c:otherwise>
						paymentLineItemGrid.setColumnHidden(6,true);
						paymentLineItemGrid.setLockColVisibility("false,true,false,false,false,false,true,false,false,false,false,false,false,false,false");
					</c:otherwise>	
				</c:choose>
		    }
			
			function doReset(){
				callOmnitureAction('Payment Details', 'Payment Details List Reset');
				resetGridSetting('gridPDVPaymentDetails', resetCallback);  
			};
			
			
		    function resetCallback() {
		        dBPersistentFlag = false;
		        clearMessage();
		        colsOrder = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14";
		        colsWidth = "px:170,150,150,150,135,135,135,135,135,150,150,150,150,150,150";
		        colsSorting = "0,asc";
		        colsHidden = "";
		        paymentLineItemGrid.sortIndex = 1;
		        gridToBeReset = true;
		        paymentLineItemGrid.loadOrder(colsOrder);
		        paymentLineItemGrid.loadPagingSorting(colsSorting,0);
		        paymentLineItemGrid.setSortImgState(true,0,paymentLineItemGrid.a_direction);
		        paymentLineItemGrid.loadSize(colsWidth);
		        paymentLineItemGrid.setColumnsVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false,false,false");
				setDefaultHiddenColumns();
				paymentLineItemGrid.clearFiltersValue();
				reloadGrid();
		        dBPersistentFlag = true;				
		    };
		    
		    
		    function print(){
				callOmnitureAction('Payment Detail', 'Payment Detail List Print');
				url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>"+
					  	"<portlet:param name='action' value='printPaymentDetails' />"+
					  "</portlet:renderURL>";
				    var iWidth=900;
				    var iHeight=600;
				    var iTop = (window.screen.availHeight-30-iHeight)/2;        
				    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
				    window.open(url,
				    		'PaymentDetails',
						    'height='+iHeight+
						    ',innerHeight='+
						    iHeight+',width='+
						    iWidth+',innerWidth='+
						    iWidth+',top='+iTop+
						    ',left='+iLeft+
						    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
			}

			function showRequestDetail(serviceRequestId,activityId,paymentId){
				callOmnitureAction('Payment Detail', 'Service Request Detail');
				
				var serviceRequestDetailURL = "<portlet:renderURL><portlet:param name='action' value='showRequestDetailPage' /><portlet:param name='from' value='paymentDetail'/></portlet:renderURL>";
				serviceRequestDetailURL += "&serviceRequestId=" + serviceRequestId + "&activityId=" + activityId + "&paymentId=" + paymentId + "&timezoneOffset=" + timezoneOffset;
				window.location.href = serviceRequestDetailURL;
			}

			function downloadPdf(){
				window.location.href = "${downPaymentDetailPdfURL}" + '&paymentId=' + '${paymentDetail.payment.paymentId}';
			}
		
		</script>
		<!-- Payment Details end -->
							
	</div>
</div>