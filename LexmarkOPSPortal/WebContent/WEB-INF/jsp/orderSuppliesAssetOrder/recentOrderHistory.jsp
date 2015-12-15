<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%--@ include file="/WEB-INF/jsp/includeGrid.jsp"--%>  		  	  	 
	  <div style="color:gray">
	  	<span><spring:message code="orderSupplies.recentOrderHistory.header"/></span>
	  </div>
	    <div class="portletBlock infoBox rounded shadow split">
         
          <div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="orderSupplies.placeOrderHeader.model"/> : </label>
                  <span id="model_popup"></span></li><li>
                  <label><spring:message code="deviceDetail.label.serialNumber"/>: </label>
                  <span id="serialNo_popup"></span></li><li>
                  <label><spring:message code="deviceDetail.label.ipAddress"/>: </label>
                  <span id="ipAddress_popup"></span></li></ul></div></div><div class="columnsTwo">
            <div class="columnInner">
              <ul class="form">
                <li>
                  <label><spring:message code="deviceFinder.listHeader.assetTag"/>: </label>
                  <span id="assetTag_popup"></span></li><li>
                  <label><spring:message code="serviceRequest.listHeader.deviceTag"/>: </label>
                  <span id="deviceTag_popup"></span></li></ul></div></div></div>
	  
	  <div class="portlet-wrap-inner">
				<div id="consumableAssetlistGrid_popup" ></div>
				      		
								        		<div id="loadingNotification_popup_history" class='gridLoading'>
								        			<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
								    			</div>
								    			<div class="pagination" >
								    			<span id="pagingArea2"></span>&nbsp;<span id="infoArea_pop"></span>
								    			</div><!-- mygrid_container -->
											</div><!-- portlet-wrap-inner -->
	 <div class="buttonContainer" id="buttonContact_popup">
  						<button class="button_cancel" id="cancelAddressButton" onclick="dialog.dialog('close');" type="button"><spring:message code="button.cancel"/></button>
  						<button class="button" id="createNewAddressButton" onclick="confirmFromPopup();" type="button"><spring:message code="button.continue"/></button>
  						
  						
  					</div>	
	 
	  
	  <script type="text/javascript">
	  var org_assetId;
	   var detailsPaymentsGrid;
	   var contractNumberInjsp;
	function initGridPopup(assetId,serialNumber,assetTag,ipAddress,modelNo,deviceTag,contractNumber){
		contractNumberInjsp = contractNumber;
		org_assetId=assetId;
		jQuery('#model_popup').html(modelNo);
		jQuery('#serialNo_popup').html(serialNumber);
		jQuery('#assetTag_popup').html(assetTag);
		jQuery('#ipAddress_popup').html(ipAddress);
		jQuery('#deviceTag_popup').html(deviceTag);
	var url="<portlet:resourceURL id='retrieveOrderHistory'></portlet:resourceURL>&assetId=${assetId}";
	
   detailsPaymentsGrid = new dhtmlXGridObject('consumableAssetlistGrid_popup');
   detailsPaymentsGrid.setImagePath("<html:imagesPath/>gridImgs/");
   detailsPaymentsGrid.setHeader("<spring:message code='orderSupplies.recentOrderHistory.gridHeader'/>");   
   detailsPaymentsGrid.setInitWidths("80,80,80,80,80,80,80");
   detailsPaymentsGrid.setColAlign("left,left,left,left,left,left,left");
   detailsPaymentsGrid.setSkin("light");
   detailsPaymentsGrid.setColSorting("na,na,na,na,na,na,na");
   detailsPaymentsGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
   detailsPaymentsGrid.enableAutoHeight(true);
   detailsPaymentsGrid.enableMultiline(true);

   detailsPaymentsGrid.init();
   detailsPaymentsGrid.prftInit();
  
   detailsPaymentsGrid.enablePaging(true, 5, 10, "pagingArea2", true, "infoArea_pop");
   detailsPaymentsGrid.setPagingSkin("bricks"); 	
   detailsPaymentsGrid.attachEvent("onXLS",function(){
	   	jQuery('#loadingNotification_popup_history').show();
	   });
   detailsPaymentsGrid.attachEvent("onXLE",function(){
	   	jQuery('#loadingNotification_popup_history').hide();
	   
	   	if(detailsPaymentsGrid.getAllRowIds() == "" ){
	   	  		//jQuery('#createNewAddressButton').click();
	   		confirmFromPopup();
	   	}
	   	else{
	   		showDialogForDMOrderSupplies();
	   		hideOverlayPopup();
	   	}
	   	setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
	    
	   }); 

		detailsPaymentsGrid.loadXML(url);
   		
	}
	function confirmFromPopup(){
		if(detailsPaymentsGrid.getAllRowIds() != "" ){
			showOverlayPopup();
		}
		jQuery('<form></form>').attr('action','consumableorder').attr('method','POST').append("<input name='assetId' type='hidden' value='"+org_assetId+"'/>").append("<input name='contractNumber' type='hidden' value='"+contractNumberInjsp+"'/>").appendTo('#buttonContact_popup').submit();
		
		}
	
	
	function showDialogForDMOrderSupplies(){
		dialog=jQuery('#dialog-form').dialog({	
			position: 'top',
			autoOpen: false,
			modal: true,
			height: 600,
			width: 700,
			draggable: false,
			resizable: false,
			open: function(){
					
						jQuery('#dialog-form').show();
					},
			close: function(event,ui) {
					dialog.dialog('destroy');
					dialog=null;
					hideOverlay();
				}	
											
		});
		dialog.dialog('open');
	}
   </script>