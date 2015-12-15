
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<style type="text/css">
       .ie7 .button, .ie7 .button_cancel {
               behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;                
        }
        .buttonContainer{position:relative!important;}
        .button_subrow1{margin-left:81%!important;padding: 0.27em 0.5em 0.34em!important;}
</style>
<![endif]-->

<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<jsp:include page="vendorSelection.jsp"/>
<style type="text/css">
div.left-nav-inner ul.filters li span{
margin-top: 0px !important;
}
.radio_Span.radio_Off,.radio_Span.radio_On {
    float: none !important;
}
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<c:choose>
<c:when test="${accountSelectionDone eq 'true'}">
	<%@ include file="/WEB-INF/jsp/dateUtil.jsp"%>
	<jsp:include page="/WEB-INF/jsp/includeDatePicker.jsp"></jsp:include>







<portlet:resourceURL var="showInvoiceListURL" id="showInvoiceListURL">
</portlet:resourceURL>

<portlet:renderURL var="invoicePageURL">
	<portlet:param name="action" value="showInvoicePageAR"/>
	<portlet:param name="invoiceNumber" value="${invoiceNumber}" />	
</portlet:renderURL>

<portlet:renderURL var="redirectToInvoicePage">
<portlet:param name="action" value="redirectToInvoicePage"></portlet:param>
</portlet:renderURL>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<div id="wraper">
<div id="content-wrapper">

<!-- Added for defect no.10432 -->
<div class="journal-content-article" >
      <h1><spring:message code="requestInfo.title.invoices"/></h1>
     </div>
     <!-- END -->
     
<div id="customerInvoicePageData" style="display: none"></div>
	
	<!-- Changed for defect no.10432 -->
      <h3 class="pageTitle"><spring:message code="customerInvoicePayments.title.customerHistoryLexmark"/>
      &nbsp;${sessionScope.accRecivablDetails["account"]}
      <c:if test='${sessionScope.accRecivablDetails["accountCount"] != "1"}'>
      <a href="#" onclick="createGrid()" class="edit"><spring:message code="customerInvoicePayments.label.changeAccount"/></a>
      </c:if>
      </h3>
      <!-- END -->
      
  	<div class="main-wrap">
     <div class="content"><a name="scrollTo"></a>
     <div class="left-nav">
          <div class="left-nav-inner"> 
            <!-- LEFT COLUMN CONTENT GOES HERE -->
            <div class="left-nav-header">
              <h3><spring:message code="requestInfo.heading.customerInvoice.filterByDateRange"/></h3>
            </div>
			<div class="bottomPush"></div>
			<div id="dateErrorDiv" style="display: none;" class="color-red"></div>
            <ul  class="filters">
              <li>
                <label for="fromDate"><spring:message code="requestInfo.label.customerInvoice.from"/></label>
                <span>
                <input  id="fromDate" class="w100" readonly="readonly"/>
                <img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" title="Select a Date" width="23" height="23"
                onClick="show_cal('fromDate',null,convertDateToString(new Date()),null);"
                 /></span></li>
              <li>
                <label for="toDate"><spring:message code="requestInfo.label.customerInvoice.to"/></label>
                <span>
                <input  id="toDate" class="w100" readonly="readonly"/>
                <img class="ui_icon_sprite calendar-icon" src="<html:imagesPath/>transparent.png" title="Select a Date" width="23" height="23"
                 onclick="show_cal('toDate',null,convertDateToString(new Date()),null);"
                /></span></li>
            </ul>
            <div class="buttonContainerIn">
              <button class="button" id="go_btn" onClick="sendDateValues();"><spring:message code="requestInfo.button.customerInvoice.go"/></button>
            </div>
          
          
          
          <%-- Filter by status --%>
          <div class="left-nav-header inlineTitle">
             <h3><spring:message code="requestInfo.requestHistory.heading.filterByView"/></h3>
           </div>
            <input type="hidden" id="statusValue" value="OPEN"/>
           <ul id="filterStatus" class="filters radio">
          
             <li> <span>
               <input type="radio" name="viewBy" id="myReq" checked="checked" value="OPEN" />
               </span>
               <label for="myReq"><spring:message code="requestInfo.customerInvoice.filter.open"/></label>
             </li>
             <li> <span>
               <input type="radio" id="bookAssetReq" name="viewBy" value="CLOSE"/>
               </span>
               <label for="bookAssetReq"><spring:message code="requestInfo.customerInvoice.filter.closed"/></label>
             </li>
             <li> <span>
               <input type="radio" id="allReq" name="viewBy" value="ALL"/>
               </span>
               <label for="allReq"><spring:message code="requestInfo.customerInvoice.filter.all"/></label>
             </li>
           </ul>
          <%-- Filter by status ends --%>
          </div>
          <div class="left-nav-footer">
		<!-- NO CONTENT HERE PLS -->
	</div>
        </div>
     	<div class="right-column">
		  <div class="portletBlock infoBox rounded shadow split">
		  <h4>
			<div class="columnInner"><spring:message code="customerInvoiceDetail.label.PaymentInformation"/></div>
		  </h4>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="customerInvoiceDetail.label.customertAccount"/></label>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span>${sessionScope.accRecivablDetails["account"]}</span>
                  </c:if>
                </li>
                 <li>
                  <label><spring:message code="customerInvoiceDetail.label.soldTo"/></label>
                  <span>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span>${sessionScope.accRecivablDetails["companycode"]}</span>
                  </c:if>
					</span>
				</li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="customerInvoiceDetail.label.billtoAddress"/></label>
                  <span>
                 <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                 ${sessionScope.accRecivablDetails["billToAddress"]}
                  <%-- 	<c:if test="${!empty sessionScope.accRecivablDetails['addressLine1']}">
						${sessionScope.accRecivablDetails["addressLine1"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['addressLine2']}">
						${sessionScope.accRecivablDetails["addressLine2"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['addressLine3']}">
						${sessionScope.accRecivablDetails["addressLine3"]}<br>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['city']}">
						${sessionScope.accRecivablDetails["city"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['state']}">
							,
						</c:if>
					</c:if>	
					<c:if test="${!empty sessionScope.accRecivablDetails['state']}">
						${sessionScope.accRecivablDetails["state"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['province']}">
							,
						</c:if>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['province']}">	
						${sessionScope.accRecivablDetails["province"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['country']}">
							,
						</c:if>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['country']}">	
						${sessionScope.accRecivablDetails["country"]}
						<c:if test="${!empty sessionScope.accRecivablDetails['postalCode']}">
							,
						</c:if>
					</c:if>
					<c:if test="${!empty sessionScope.accRecivablDetails['postalCode']}">	
						${sessionScope.accRecivablDetails["postalCode"]}&nbsp;
					</c:if>--%>
				</c:if>                  
                </span></li>
              </ul>
            </div>
          </div><!--
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="customerInvoiceDetail.label.soldTo"/></label>
                  <span>
                  <c:if test="${not empty sessionScope.accRecivablDetails}"> 
                  	<span>${sessionScope.accRecivablDetails["companycode"]}</span>
                  </c:if>
					</span>
				</li>
              </ul>
            </div>
          </div>
        --></div>
		
		<div class="grid-controls"> 
            <!-- Utilities Start -->
            <div class="utilities">
              <ul>
                <li class="first"><a href="#"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite spreadsheet-icon" alt="Export to Excel" title="Export to Excel" onclick="download('CSV');"></a></li>
                <li><a href="#"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite pdf-icon" alt="Export to PDF" title="Export to PDF" onclick="download('PDF');" /></a></li>
                <li><a href="#"><img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="Print" title="Print" onclick="print();"></a></li>
              </ul>
            </div>
            <!-- utilities End --> 
		</div>
		<div class="bottomPush"></div>
		<div id="dateErrorDiv" style="display: none;" class="color-red"></div>
		<div id="InvoiceGridPrint">
          <div class="portlet-wrap rounded">
            <div class="portlet-wrap-inner">
              <div id="arInvoiceHistoryGrid_container" class="gridbox gridbox_light "></div>
             <div id="loadingNotification" class='gridLoading'>
	        		<img src="<html:imagesPath/>gridloading.gif"/><br>
	    	   </div>
            </div>
          </div>
          </div>
          <div class="pagination" id="paginationId"><span id="pagingArea"></span><span id="infoArea"></span></div>
          <!-- mygrid_container --> 
        </div>
     </div>   
     
  	</div>
</div>
<div id="pdfDiv" style="display:none" class="height-900px">
<iframe name="iframeTarget" height=500px width = 100%  frameborder="0"></iframe>
</div>
</div>
<script type="text/javascript">



var xmlURLQueryParams={
		urlParameters:["fromDate","endDate","timeZoneOffset","filterCriterias","status"],
		setParameters : function(){
							
								this["fromDate"]=jQuery("#fromDate").val();
								this["endDate"]=jQuery("#toDate").val();
								this["timeZoneOffset"]=timezoneOffset;
								this["filterCriterias"]=arInvoiceHistoryGrid.filterValues==null?"":arInvoiceHistoryGrid.filterValues;
								this["status"]=jQuery('#statusValue').val();
								
							},
		setLoadXMLUrl : function(){
										
										xmlURL=new String("<portlet:resourceURL id="showInvoiceListURL"></portlet:resourceURL>");
										this.setParameters();
										for(i=0;i<this.urlParameters.length;i++){
											xmlURL+="&"+this.urlParameters[i]+"="+this[this.urlParameters[i]];
												
										}
										return xmlURL; 
										
											
								}
		};
		
		var sessionFromDate = "";
		var sessionToDate = "";
		sessionFromDate = "${sessionScope.fromDateAR}";
		sessionToDate = "${sessionScope.toDateAR}";
		var invoiceGrid;
		var arInvoiceHistoryGrid;
		var fromDate;
		var endDate;
		jQuery(document).ready(function(){				
			
			/* alert("${sessionScope.fromDate}");
			alert("${sessionScope.toDate}"); */
			jQuery("#fromDate").val(localizeFormatDate(new Date().addDays(-180)));
			jQuery("#toDate").val(localizeDate(todayStr));
			
			createListGrid();
			
		});
		jQuery('#filterStatus input').click(function(){
			jQuery('#statusValue').val(jQuery(this).val());
			arInvoiceHistoryGrid.clearAndLoad(xmlURLQueryParams.setLoadXMLUrl());
			});
		
		
		function createListGrid(){
			arInvoiceHistoryGrid = new dhtmlXGridObject('arInvoiceHistoryGrid_container');
			//arInvoiceHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
			invoiceGrid=arInvoiceHistoryGrid;
			arInvoiceHistoryGrid.setImagePath("<html:imagesPath/>gridImgs/");
			arInvoiceHistoryGrid.setHeader("<spring:message code="customerInvoicePayments.listHeader.invoiceLists"/>"); 
			
			arInvoiceHistoryGrid.attachHeader("#text_filter,,,,#combo_filter,,");
			arInvoiceHistoryGrid.setInitWidths("100,80,80,80,80,80,*");
			arInvoiceHistoryGrid.setColAlign("left,left,left,left,left,left,left"); 
			//arInvoiceHistoryGrid.enableResizing("true,true,true,ture,true,true,true");
			
			arInvoiceHistoryGrid.setSkin("light");
			arInvoiceHistoryGrid.setColSorting("str,date,date,str,na,int,na");
			arInvoiceHistoryGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
			//arInvoiceHistoryGrid.setLockColVisibility("false,false,false,false,false,false,false");
			arInvoiceHistoryGrid.enableAutoHeight(true);
			arInvoiceHistoryGrid.enableMultiline(true);
			arInvoiceHistoryGrid.enableColumnMove(false);
			arInvoiceHistoryGrid.sortIndex = null;
			arInvoiceHistoryGrid.a_direction = arInvoiceHistoryGrid.a_direction||"asc";
			<%-- Changes by arksarkar for sorting invoice number--%>
			arInvoiceHistoryGrid.setCustomSorting(sort_invoice,0);
			arInvoiceHistoryGrid.setCustomSorting(sort_amount,5);
			arInvoiceHistoryGrid.setCustomSorting(sort_paidDate,3);
			function sort_paidDate(dateA,dateB,order){
				var date1 = dateA.trim();
				var datearrayA = date1.split("/");
				var newdateA = datearrayA[1] + '/' + datearrayA[0] + '/' + datearrayA[2];

				var date2 = dateB.trim();
				var datearrayB = date2.split("/");
				var newdateB = datearrayB[1] + '/' + datearrayB[0] + '/' + datearrayB[2];
				return date_custom(newdateA,newdateB,order);
			}
			 function date_custom(a,b,order){ 
		            a=a.split("/")
		            b=b.split("/")
		            if (a[2]==b[2]){
		                if (a[1]==b[1])
		                    return (a[0]>b[0]?1:-1)*(order=="asc"?1:-1);
		                else
		                    return (a[1]>b[1]?1:-1)*(order=="asc"?1:-1);
		            } else
		                 return (a[2]>b[2]?1:-1)*(order=="asc"?1:-1);
		        }
			
			function sort_invoice(valA,valB,order){
								
				valA=valA.indexOf('<a')==-1?valA:jQuery(valA).text();
				valB=valB.indexOf('<a')==-1?valB:jQuery(valB).text();
			
				return checkAndreturn(parseInt(valA),parseInt(valB),order);
			}	
			function sort_amount(valA,valB,order){				
				var tvalA=parseFloat(valA.substring(0,valA.indexOf('(')));
				var tvalB=parseFloat(valB.substring(0,valB.indexOf('(')));
				
				return checkAndreturn(tvalA,tvalB,order);
			}	
			function checkAndreturn(valA,valB,order){
				
				if(order=="asc")
					return valA>valB?1:-1;
				else
					return valA<valB?1:-1;
				
			}
			<%-- Ends Changes by arksarkar for sorting invoice number--%>
			arInvoiceHistoryGrid.attachEvent("onXLS", function() {
				document.getElementById('loadingNotification').style.display = 'block';
				document.getElementById('paginationId').style.display = 'none'; 
				});       
			
			arInvoiceHistoryGrid.attachEvent("onXLE", function() {
				arInvoiceHistoryGrid.sortRows(3,"date","des");
				//arInvoiceHistoryGrid.filterByAll();
				jQuery('.invoicelink').bind('click',function(){
						if(jQuery(this).attr('href')=='#'){
								jAlert("<spring:message code='requestInfo.customerInvoice.alert.noDataFound'/>");
								return false;
							}	
					});
				document.getElementById('loadingNotification').style.display = 'none';
				document.getElementById('paginationId').style.display = 'block';
				setTimeout(function(){
		    		rebrandPagination();
		    	
		    	},100);
			});
			arInvoiceHistoryGrid.attachEvent("onFilterStart", function(indexes,values){
				for(key in values){
					values[key]=values[key].trim();
				}
				arInvoiceHistoryGrid.filterValues = values;
				setGridFilerTimer(reloadGrid);
				
			});
			arInvoiceHistoryGrid.init();
			arInvoiceHistoryGrid.prftInit();
			arInvoiceHistoryGrid.enablePaging(true, 20, 10, "pagingArea", true, "infoArea");
			arInvoiceHistoryGrid.setPagingSkin("bricks");
			arInvoiceHistoryGrid.setFiltersValue("${sessionScope.accRecivablDetails.invoiceNumber}");

			var statusTypeMap = [];
			<c:forEach items="${requestStatusLOVMap}" var="statusTypeMap" varStatus = "status" >
			statusTypeMap[${status.index}] = ["${statusTypeMap.key}","${statusTypeMap.value}"];
			</c:forEach>
			arInvoiceHistoryGrid.setCustomizeCombo(statusTypeMap,4);
			
			arInvoiceHistoryGrid.loadXML(xmlURLQueryParams.setLoadXMLUrl());
			
		}
		function reloadGrid(){
			arInvoiceHistoryGrid.clearAndLoad(xmlURLQueryParams.setLoadXMLUrl())
			}
		
		function sendDateValues(){
			
			fromDate = jQuery("#fromDate").val();
			endDate = jQuery("#toDate").val();
			
			var deFlag = false;
			if(fromDate!=null && fromDate!="" && endDate!=null && endDate!="")
			{	
				
				var startDate;
				var endDate;
				
				var tgtSeperator = dateFormat.substring(2,3);
				today = convertDateFormat("mm/dd/yyyy","/", todayStr, dateFormat, tgtSeperator);
				
		    	if(fromDate.substring(2,3)=="."){
		    		startDate = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",fromDate, "mm/dd/yyyy","/"));
		    		endDate = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",endDate, "mm/dd/yyyy","/"));
		    		today = convertStringToDate(convertDateFormat("dd.mm.yyyy",".",today, "mm/dd/yyyy","/"));
		    		deFlag =true;
		    	}else{
		    		today = convertStringToDate(today);
		    	}
		    	
		    	if(!deFlag){
		    		fromDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, fromDate, "mm/dd/yyyy", "/")));
		    		endDat_converted = convertStringToDate((convertDateFormat(dateFormat, tgtSeperator, endDate, "mm/dd/yyyy", "/")));
		    	}
		    	else{
		    		fromDat_converted = startDate;
		    		endDat_converted = endDate;
		    	}
				
				var oneDay = 24*60*60*1000; /* hours*minutes*seconds*milliseconds*/		    	
		    	var diffDays = Math.round(Math.abs((endDat_converted.getTime() - fromDat_converted.getTime())/(oneDay)));
				
		    	if(endDat_converted > today){
					 jQuery('#dateErrorDiv').html("<spring:message code='customerInvoicePayments.validation.toDate.invalid'/>");
					 jQuery('#dateErrorDiv').show();
				     return false;
				}
		    	else if(diffDays>180){
					jAlert('<spring:message code="requestInfo.invoice.warning.dateRange"/>');
					return false;
				}
				else if(fromDat_converted > endDat_converted)
				{
					jQuery('#dateErrorDiv').html("<spring:message code='customerInvoicePayments.validation.fromDate.invalid'/>");
					jQuery('#dateErrorDiv').show();
					return false;
				}
				else{
					jQuery("#dateErrorDiv").hide();
					arInvoiceHistoryGrid.clearAndLoad(xmlURLQueryParams.setLoadXMLUrl());
				}
			}else{
				jAlert('<spring:message code="requestInfo.invoice.warning.emptydate"/>');
			}
		}
		
		function showInvoicePDF(invoiceNumber){
			//alert('inside showInvoiceListURL');
			//showOverlay();
			
			var url1 = '/SAPBinary_WebProject/SAPData?repId='+invoiceNumber;
			 var iLeft = (window.screen.availWidth-850)/2; 
			 var iHeight = (window.screen.availHeight-100); 
			 var iTop= (window.screen.availHeight-650)/2;
			
			  jQuery('#pdfDiv').dialog({
			
				title: "<spring:message code='requestInfo.title.customerInvoiceDetail'/>",
					
				  	width: 850, 
				  	position: [iLeft,iTop],
				  	resizable:true,  
				  	open:function(){
					jQuery('#pdfDiv').show();
				  	window.open(url1,"iframeTarget");
				  	}
			 
				  });	
			  
			  return false;
			//window.open(url1,"iframeTarget");
			
			//window.location.href="${invoicePageURL}&invoiceNumber=" +invoiceNumber+"&invoiceDt=" +invoiceDt +"&reqType=AR&amount="+amount+"&currency="+currency;
		};

		function download(type){
			window.location.href="<portlet:resourceURL id="download"></portlet:resourceURL>&timeZoneOffset="+timezoneOffset+"&downloadType="+type;
		}

		// added for MPS 2.1
		function print() {
			var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
				"<portlet:param name='action' value='printInvoiceList' />" +
				"</portlet:renderURL>";
			url = url + "&pageNumber=" + arInvoiceHistoryGrid.currentPage;
		    var iWidth=1000;
		    var iHeight=600;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;
		    window.open(url,
		    		'InvoiceList',
				    'height='+iHeight+
				    ',innerHeight='+
				    iHeight+',width='+
				    iWidth+',innerWidth='+
				    iWidth+',top='+iTop+
				    ',left='+iLeft+
				    ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
		};

</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
	createGrid();
</script>
</c:otherwise>
</c:choose>
