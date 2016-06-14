<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<portlet:resourceURL var="retrieveGrid" id="retrieveGrid"></portlet:resourceURL>
<div id="portlet-wrap">
      <div class="pageTitle"><spring:message code="changemanagement.common.button.requestHistory"/></div>
      
      <div id="requestHistoryGrid_container" class="gridbox gridbox_light"></div>
      <div id="loadingNotification" class='gridLoading'>
	        	<br/><img src="/lexmark-punchout-theme/images/custom/loading_big.gif"/><br/>
	    	  </div>
	  <div class="pagination"><span id="pagingArea"></span><span id="infoArea"></span></div>
    </div>
   <div id="requestDetailsPopup"></div>
<script>

var requestHistoryGrid;
var srDetailsObj={srNumber:"",srType:""};
var url= "${retrieveGrid}"


	requestHistoryGrid = new dhtmlXGridObject('requestHistoryGrid_container');
	requestHistoryGrid.setImagePath("/LexmarkServicesPunchout/images/gridImgs/");
	requestHistoryGrid.setHeader("<spring:message code='requestInfoB2B.requestHistory.header'/>");
	requestHistoryGrid.setInitWidths("100,140,120,140,120,140,140,140,140,*");
	requestHistoryGrid.setColumnMinWidth("100,120,120,120,120,120,120,120,120,5");
	requestHistoryGrid.setColAlign("left,left,left,left,left,left,left,left,left");
	requestHistoryGrid.setSkin("light");
	requestHistoryGrid.setColSorting("str,str,str,str,str,str,str,str,str,na");
	requestHistoryGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	requestHistoryGrid.enableAutoHeight(true);
	requestHistoryGrid.enableMultiline(true);
	requestHistoryGrid.enableColumnMove(true);
	requestHistoryGrid.enableAlterCss("even_row","odd_row");
	requestHistoryGrid.init();
	requestHistoryGrid.enablePaging(true,15, 2, "pagingArea", true, "infoArea");
	requestHistoryGrid.setPagingSkin("bricks");
    requestHistoryGrid.attachEvent("onXLE", function() {
    	document.getElementById('loadingNotification').style.display = 'none';
	});
    
    function refreshHistoryGrid(){
    	requestHistoryGrid.clearAndLoad(url);
    	
    }

        function goToRequestDetails(srNumber, requestType){
        
       	if(requestType!='Draft'){
        	var url = "<portlet:resourceURL id='loadRequestDetails'>"+
        	"</portlet:resourceURL>"
        	+"&srNumber="+srNumber;
        	url= encodeURI(url);
        	
        	showOverlay();	
			jQuery('#requestDetailsPopup').load(url,function(){

			dialog=jQuery('#requestDetails').dialog({
				autoOpen: false,
				title: '',
				modal: true,
				draggable: false,
				resizable: false,
				position: 'top',
				height: '600',
				width: '950',
				open: function(){	
					//jQuery(".ui-dialog-titlebar-close").hide();
					//jQuery(".ui-dialog-titlebar").hide();						
					//changeRqstDetails(srNumber);
				},
				close: function(event,ui){
					hideOverlay();
					//dialog.dialog('destroy');					 
					dialog=null;
					//alert('1');
					jQuery('#requestDetails').remove();
					}
				});
			dialog.dialog('open');
			});
           
       	}
       	else{
       		global_click_msgs.clickedFrom="srDetails";
       		srDetailsObj.srNumber=srNumber;
       		srDetailsObj.srType=requestType;
          	calledFromLEftNav("printerProduct");
        		
       		
       	}
	}
</script>



