<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="status-wrap">
		<h4><spring:message code="claim.label.ordered.parts"/></h4>
	<div class="status-block overflow-auto-important" >
		  	<div id="orderedPartsLoadingNotification" class="gridLoading" style="display: none;">
                     <br><!--spring:message code="label.loadingNotification"/-->&nbsp;&nbsp;<img src="<html:imagesPath/>gridloading.gif"/><br>
            </div>
			<div id="gridOrderedParts1" class="width-100per"></div>
			<div><span id="pagingArea"></span>&nbsp;<span id="infoArea"></span></div><!-- mygrid_container -->
	</div>
</div>

<script type="text/javascript">
	jQuery(document).ready(function() {
		gridOrderedPartsGrid = new dhtmlXGridObject('gridOrderedParts1');
		gridOrderedPartsGrid.setImagePath("<html:imagesPath/>gridImgs/");
		setOrdersHeaderForExchange();
		//gridOrderedPartsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.orderedParts.detail"/>',10));
		
		//CI-6 Chnages Start
		//gridOrderedPartsGrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
		//gridOrderedPartsGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro");
		//gridOrderedPartsGrid.setColSorting("na,str,str,str,str,str,str,str,str,str");
		//gridOrderedPartsGrid.setInitWidths("20,110,50,120,120,120,120,120,120,210");
		//Changes for CI7 Claim Update Order parts grid
		gridOrderedPartsGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left");
		gridOrderedPartsGrid.setColTypes("sub_row,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
		gridOrderedPartsGrid.setColSorting("na,str,str,str,str,str,str,str,str,str,str,str,str");
		gridOrderedPartsGrid.setLockColVisibility("false,false,false,false,false,false,false,false,false,false,false,false,false");
		gridOrderedPartsGrid.setInitWidths("20,80,50,80,120,120,100,120,80,100,100,120,100");
		//CI-6 Chnages End
		
		gridOrderedPartsGrid.a_direction = "ASCENDING";
		gridOrderedPartsGrid.enableAutoWidth(true);
		gridOrderedPartsGrid.enableAutoHeight(true);
		gridOrderedPartsGrid.enableMultiline(true);
		gridOrderedPartsGrid.setSizes();
		gridOrderedPartsGrid.init();
		gridOrderedPartsGrid.prftInit();
		gridOrderedPartsGrid.enablePaging(true, 5, 6, "pagingArea", true, "infoArea");
		gridOrderedPartsGrid.setPagingSkin("bricks");
		gridOrderedPartsGrid.setColumnHidden(12,true);
		gridOrderedPartsGrid.setSkin("light");
		// move enabled for CI 14.10 CRM-DAmster201408041344
		gridOrderedPartsGrid.enableColumnMove(true);
		gridOrderedPartsGrid.loadXMLString("${orderPartListXML}");

		// changes for db saving and retrieving	start
		//gridOrderedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");
		gridOrderedPartsGrid.attachEvent("onAfterCMove",function(a,b){								
			gridOrderedPartsGrid.enableAutoSavingToDB("${saveCustomizedGridSettingURL}");				
		});	
		<c:forEach var="gridSettingVar" items="${gridSettingList}" varStatus="status">
		<c:if test="${gridSettingVar.gridId == 'gridOrderedParts1'}">					
		var colsOrder = "${gridSettingVar.colsOrder}";
		gridOrderedPartsGrid.loadOrder(colsOrder);
		
		</c:if>
		</c:forEach>
		// changes for db saving and retrieving	end
		
		gridOrderedPartsGrid.attachEvent("onXLS", function() {
			document.getElementById('orderedPartsLoadingNotification').style.display = '';
		});
		gridOrderedPartsGrid.attachEvent("onXLE", function() {
			customerAccountInfoGrid.setSortImgState(true, 2, "asc");
			document.getElementById('orderedPartsLoadingNotification').style.display = 'none';
			setTimeout(function(){
	    		rebrandPagination();
	    	
	    	},100);
		});
		gridOrderedPartsGrid.attachEvent("onMouseOver", function(id,ind) {
			return false;
		});
	});
	
	function setOrdersHeaderForExchange(){
		//alert(exchangeflag);
		if(exchangeflag==true || exchangeflag=='true'){
			gridOrderedPartsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.deviceOrderedParts.detail"/>',12));
		}else{
			gridOrderedPartsGrid.setHeader(autoAppendPlaceHolder('<spring:message code="claim.headerList.orderedParts.detail"/>',12));
		}
	}
</script>