<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.lexmark.constants.LexmarkPPOmnitureConstants"%>
<%@page import="com.lexmark.constants.MassUploadFlags"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<portlet:resourceURL var="accountListPopulateURL" id="populateAccountList">
</portlet:resourceURL>
<portlet:resourceURL var="accountDetailsSession" id="setCurrentAccountValuesToSession"></portlet:resourceURL>


<div class="portletBodyWrap" id="accountInitialize" style="display: none;">
<div id="errorDiv_popup" class="error" style="display: none;"></div>
	
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	 <div id="accountListGrid" class="gridbox gridbox_light"></div>
							<div id="loadingNotificationPopup_createNewRequest" class="gridLoading" style="display: none;">
										<br><!--spring:message code="requestInfo.popup.loading"/--><img src="<html:imagesPath/>gridloading.gif" /><br>
			  				</div>
						</div>
					</div>
					
					<div class="pagination" >
						<span id="pagingAreaPopup"></span><span id="infoArea_pop"></span>
					</div>

				<div class="buttonContainer">
				<button class="button_cancel" type="button" onclick="dialog.dialog('close')"><spring:message code="button.cancel"/></button>
				</div>


</div>

<script>

var accountListpopulateURLvar="${accountListPopulateURL}";
var accountGrid;
var ajaxAccountSelection="";
var ajaxSuccessFunction;


function setAccount(vendorAccountId,rowId){
	//alert('Account Selected with Id'+vendorAccountId);
	if(dialog!=null){
			showOverlayPopup();
    	  }
	
	//fire ajax to save the data to session from grid	
	jQuery.ajax({
			url:'${accountDetailsSession}',			
			type:'POST',
			data: {
				 	//Ajax call with selected row data. changes for defect 8463 ,  accCount added
					vendorAccountId:  vendorAccountId,
					accountName: accountGrid.cellById(rowId,3).getValue(),
					<%=MassUploadFlags.MASSUPLOADCONSUMABLES.getFlagName()%>:accountGrid.cellById(rowId,5).getValue(),
					<%=MassUploadFlags.MASSUPLOADHARDWARE.getFlagName()%>:accountGrid.cellById(rowId,6).getValue(),
					accCount: accountGrid.getAllRowIds().split(',').length
					 
			},
			success: function(results){
				callOmnitureAction('<%=LexmarkPPOmnitureConstants.ACCOUNTSELECTIONPOPUP%>','<%=LexmarkPPOmnitureConstants.ACCOUNTPOPUPSELECTACCOUNT%>');
					//If ajax call is success, go to change to remove contact page
					if(results=="success"){
						ajaxAccountSelection="success";
										    
						if(dialog!=null){
							hideOverlayPopup();
							dialog.dialog('close');
						}
						else
							ajaxSuccessFunction();
						
					}else{
						jQuery('#errorDiv_popup').append('<li><strong><spring:message code='exception.cm.common.webServiceCall'/></strong></li>');
						jQuery('#errorDiv_popup').show();
						}
				},
			failure: function(){
					jQuery('#errorDiv_popup').append('<li><strong><spring:message code='exception.cm.common.webServiceCall'/></strong></li>');
					jQuery('#errorDiv_popup').show();
					}
			});
	
	
}


function initializeAccountGrid(loadXLEfunc){
	//no. of cols = 7
	<%--Added for Showing no accounts in popup MPS 2.1--%>
			jQuery('#errorDiv_popup').hide();
	<%-- Ends Showing no accounts in popup MPS 2.1--%>
	accountGrid = new dhtmlXGridObject('accountListGrid'); 	 
    accountGrid.setImagePath("<html:imagesPath/>gridImgs/");
    accountGrid.setHeader("<spring:message code='massUpload.listHeader.partnerAccountListPopUp'/>,,");
    accountGrid.attachHeader(",#text_filter,#text_filter,#text_filter,,,");
    accountGrid.setInitWidths("0,150,100,*,100,0,0");
    accountGrid.setColAlign("left,left,left,left,center,left,left");
    accountGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
    accountGrid.setColSorting("na,str,str,str,na,na,na");
 
    accountGrid.enableMultiline(true);  
    accountGrid.init();
    accountGrid.prftInit();
    accountGrid.enablePaging(true,5, 10, "pagingAreaPopup", true, "infoArea_pop");
    accountGrid.setPagingSkin("bricks");
    accountGrid.enableAutoWidth(true);
    accountGrid.enableAutoHeight(true);
    accountGrid.setSkin("light");
    accountGrid.setColumnHidden(0,true);
    accountGrid.setColumnHidden(5,true);
    accountGrid.setColumnHidden(6,true);
   
    accountGrid.attachEvent("onXLS", function() {
    	jQuery('#loadingNotificationPopup_createNewRequest').show();	
    		
	});
       
    accountGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
    accountGrid.attachEvent("onXLE", function() {
    	setTimeout(function(){
    		rebrandPagination();
    	
    	},100);
    	jQuery('#loadingNotificationPopup_createNewRequest').hide();
    	if(typeof isMassUploadPage === 'boolean'){        	       	
    		accountGrid.filterBy(5,"true",true);
    		if(accountGrid.getAllRowIds()==""){
    			accountGrid.filterByAll(); 
    			accountGrid.filterBy(6,"true",true);
    		}
    	}
		
      	if(accountGrid.getAllRowIds() != "" ){
    	  if(accountGrid.getAllRowIds().split(',').length == 1){
        	//alert('only 1 account');
        	jQuery('#button'+accountGrid.getAllRowIds()).click();
        	//loadXLEfunc();
        	}
    	  else{
        	  //alert('multipleacc');
       		loadXLEfunc();
      	  }
    	} else{
    		<%--Added for Showing no accounts in popup MPS 2.1--%>
    		jQuery('#errorDiv_popup').empty();
    		jQuery('#errorDiv_popup').append('<li><strong><spring:message code='requestInfo.accountSelection.noAccountFound'/></strong></li>');
			jQuery('#errorDiv_popup').show();
			<%--ENDS Showing no accounts in popup MPS 2.1--%>
       		loadXLEfunc();
    	  }
    	
    	
	});

	
	
	
		accountGrid.loadXML(accountListpopulateURLvar);
	
    
	
}

function reloadAccountPopupGrid(){
	 accountGrid.clearAndLoad(accountListpopulateURLvar);
}
</script>