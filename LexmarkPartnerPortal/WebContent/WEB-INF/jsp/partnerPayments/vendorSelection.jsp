<%-- <% request.setAttribute("subTabSelected","accountsPayable"); %> --%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>--%>

<portlet:resourceURL var="vendorListpopulateURL" id="populateVendorList">
</portlet:resourceURL>
<portlet:resourceURL var="accountsRecvblURL" id="accountsRecvblList">
</portlet:resourceURL>
<portlet:resourceURL var="vendorDetailsSession" id="setCurrentAccountsPayableValuesToSession">
</portlet:resourceURL>

<portlet:resourceURL var="setAccRecvblsUrl" id="setAccountsRecivableValues">
</portlet:resourceURL>

<%-- <portlet:renderURL var="setAccRecvblsUrl">
<portlet:param name="action" value="setAccountsRecivableValues"></portlet:param>
</portlet:renderURL> --%>


<div id="tabs-1">

<div id="errorDiv_NoRecord_AP" class="error" style="display: none;">
	<b><spring:message code="vendor.no.record"/></b>
</div>


<div class="portletBodyWrap" id="accountInitialize" style="display: none;">
<div id="errorDiv_popup" class="error" style="display: none;"></div>
	
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	 <div id="vendorListGrid" class="gridbox gridbox_light"></div>
							<div id="loadingNotificationPopup_AP" class="gridLoading">
										<br><img src="<html:imagesPath/>gridloading.gif" /><br>
			  				</div>
						</div>
					</div>
					
					<div class="pagination" >
						<span id="pagingAreaPopup"></span><span id="infoArea_pop"></span>
					</div>

				<div class="buttonContainer">
				<button class="button_cancel" type="button" onclick="dialog.dialog('close');"><spring:message code="button.cancel"/></button>
				</div>


</div>
</div>

<div id="tabs-2">

<div id="errorDiv_NoRecord_AR" class="error" style="display: none;">
	<b><spring:message code="vendor.no.record"/></b>
</div>


<div class="portletBodyWrap" id="accountRcvblInitialize" style="display: none;">
<div id="errorDiv_popup1" class="error" style="display: none;"></div>
	
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	 <div id="acntRcvblListGrid" class="gridbox gridbox_light"></div>
							<div id="loadingNotificationPopup_AR" class="gridLoading" style="display: none;">
										<br><img src="<html:imagesPath/>gridloading.gif" /><br>
			  				</div>
						</div>
					</div>
					
					<div class="pagination" >
						<span id="pagingAreaPopup1"></span><span id="infoArea_pop"></span>
					</div>

				<div class="buttonContainer">
				<button class="button_cancel" type="button" onclick="dialog.dialog('close');"><spring:message code="button.cancel"/></button>
				</div>


</div>
</div>
<script>

var vendorListpopulateURLvar;
var vendorGrid;
var linkObj_accSelect;
var ajaxAccountSelection="";
var ajaxSuccessFunction;
var headerVal;
var dialog=null;

//errorShow();

function setPayeeAccount(payeeNo,companyCode,rowId,addressLine1,addressLine2,addressLine3,city,state,province,country,postalCode)
{

 
jQuery.ajax({
	url:'${setAccRecvblsUrl}',			
	type:'POST',
	data: {
		 	//Ajax call with selected row data
			payeeNo :   payeeNo,
			soldToNo:  vendorGrid.cellById(rowId,2).getValue(),
			billToAddr:  vendorGrid.cellById(rowId,1).getValue(),
			payeeAccnt : vendorGrid.cellById(rowId,0).getValue(),
			companyCode: companyCode,
			addressLine1: addressLine1,
			addressLine2: addressLine2,
			addressLine3: addressLine3,
			city: city,
			state: state,
			province: province,
			country: country,
			postalCode: postalCode
		},
	success: function(results){
		//alert("success");
			//If ajax call is success, go to change to remove contact page
			if(results=="success"){
				//alert("if succss");
				ajaxAccountSelection="success";
				if(dialog!=null)
			   		dialog.dialog('close');
				ajaxSuccessFuncForAR('AR');
			
				
		}else{
				jQuery('#errorDiv_popup').append('<li><strong>Unable to save value</strong></li>');
				jQuery('#errorDiv_popup').show();
				}
		},
	failure: function(){
			jQuery('#errorDiv_popup').append('<li><strong>Unable to save value</strong></li>');
			jQuery('#errorDiv_popup').show();
			}
	});
  
}

function showAccountPopup(gridType)
{	
	if(gridType=="AR"){
		//alert('grid type is if ' + gridType);
		gridName="acntRcvblListGrid";
		headerVal = "<spring:message code='requestInfo.accountsPayable.accountsRecivableListPopUp'/>";
		vendorListpopulateURLvar="${accountsRecvblURL}";
		
	
		createGrid();
	}else{
		//alert('grid type is else ' + gridType);	
		gridName="vendorListGrid";
		headerVal = "<spring:message code='requestInfo.accountsPayable.accountsPayableListPopUp'/>";
		vendorListpopulateURLvar="${vendorListpopulateURL}";
		
		createGrid();
	}
}
function createGrid()
{	
	//alert("createGrid " + gridName);	
	
	vendorGrid = new dhtmlXGridObject(gridName);
    vendorGrid.setImagePath("<html:imagesPath/>gridImgs/");        
    vendorGrid.setHeader(headerVal+",");    
    vendorGrid.setInitWidths("100,150,150,100");    
    vendorGrid.setColAlign("left,left,left,left");    
    vendorGrid.setColTypes("ro,ro,ro,ro");    
    vendorGrid.setColSorting("str,str,str,na");    
    vendorGrid.enableMultiline(true);    
    vendorGrid.init();    
    vendorGrid.prftInit();    
    if(gridName == 'vendorListGrid')
    	vendorGrid.enablePaging(true,5, 10, "pagingAreaPopup", true, "infoArea_pop");
    else
    	vendorGrid.enablePaging(true,5, 10, "pagingAreaPopup1", true, "infoArea_pop");
    vendorGrid.setPagingSkin("bricks");
    vendorGrid.enableAutoWidth(true);
    vendorGrid.enableAutoHeight(true);
    vendorGrid.setSkin("light");
    vendorGrid.attachEvent("onXLS", function() {
    	if(gridName == 'vendorListGrid')
    	{
    		jQuery('#loadingNotificationPopup_AP').show();
    	}else{
    		jQuery('#loadingNotificationPopup_AR').show(); 
    	}
	});
    vendorGrid.attachEvent("onXLE", function() {
    	
    	if(vendorGrid.getAllRowIds() != "" ){
    		if(vendorGrid.getAllRowIds().split(',').length == 1){
        		//alert('only 1 account');
        		//alert(gridName=="acntRcvblListGrid"?"AR":"AP");
        		jQuery('#button'+vendorGrid.getAllRowIds()).click();
        	}else{
            	
        		hideShowDivs(gridName=="acntRcvblListGrid"?"AR":"AP");
            	}
    	}else{
    		hideShowDivs(gridName=="acntRcvblListGrid"?"AR":"AP");
        	}
    	if(gridName == 'vendorListGrid')
    	{
    		jQuery('#loadingNotificationPopup_AP').hide();   		
    			if(vendorGrid.getRowsNum()==0){
    				jQuery('#errorDiv_NoRecord_AP').show();
    				jQuery('#errorDiv_NoRecord_AR').hide();
    			}else{
    				jQuery('#errorDiv_NoRecord_AP').hide();
    				jQuery('#errorDiv_NoRecord_AR').hide();
    			}
    	}else{
    		jQuery('#loadingNotificationPopup_AR').hide();    
    			if(vendorGrid.getRowsNum()==0){
    				jQuery('#errorDiv_NoRecord_AR').show();
    				jQuery('#errorDiv_NoRecord_AP').hide();
    			}else{
    				jQuery('#errorDiv_NoRecord_AR').hide();
    				jQuery('#errorDiv_NoRecord_AP').hide();
    			}
    	}	
	});
    vendorGrid.loadXML(vendorListpopulateURLvar);
    
   
}
function hideShowDivs(gridType){
	//alert('gridType'+gridType+'vendorGrid'+vendorGrid);
	dialog=jQuery('#totalContent').dialog({
		autoOpen: false,
		modal: true,
		width: 550,
		position: ['center','top'],
		open: function(){
			//alert('in open'+gridType);
			if(gridType=="AR"){
				//jQuery("#acntRcvblListGrid").show();
				/*jQuery("#vendorListGrid").empty();
				jQuery("#vendorListGrid").hide();
				jQuery('#accountInitialize').hide();*/
				jQuery("#accountRcvblInitialize").show();
			}else{
				//jQuery("#vendorListGrid").show();
				/*jQuery("#acntRcvblListGrid").empty();
				jQuery("#acntRcvblListGrid").hide();*/
				//jQuery("#accountRcvblInitialize").hide();
				jQuery('#accountInitialize').show();
				}
				jQuery('#totalContent').show();				
				jQuery('span.ui-dialog-title').text("<spring:message code="partnerPayments.heading.selectAnAccount"/>");
				
				},
		close: function(event,ui){
	   				  dialog.dialog('destroy');
					  dialog=null;
					  vendorGrid=null;
					  jQuery('#acntRcvblListGrid,#vendorListGrid').empty();
					  jQuery('#accountInitialize,#accountRcvblInitialize').hide();
					  
					  hideOverlay();
					  //jQuery('#accountInitialize').hide();
					  
					}
				});
	//alert('vendorGrid'+vendorGrid);
	jQuery(document).scrollTop('0');
	dialog.dialog('open');		
	
	
}

function setVendorAccount(vendorId,companyCode,rowId,payeeName,addressLine1,addressLine2,addressLine3,city,state,province,country,postalCode){
	//alert('Account Selected with Id'+accountId);
	/*if(dialog!=null){
		//alert('dialog is not null');
			showOverlay();
    	  }*/
	//alert('in setVendorAccount'+vendorGrid);
	//fire ajax to save the data to session from grid
	//alert(vendorGrid.cellById(rowId,0).getValue()+'vendorgrid');
	jQuery.ajax({
			url:'${vendorDetailsSession}',			
			type:'POST',
			data: {
				 	//Ajax call with selected row data
					vendorId :   vendorId,
					vendorName:  vendorGrid.cellById(rowId,0).getValue(),
					country:  vendorGrid.cellById(rowId,2).getValue(),
					companyCode:  companyCode,
					payeeName: payeeName,
					addressLine1: addressLine1,
					addressLine2: addressLine2,
					addressLine3: addressLine3,
					city: city,
					state: state,
					province: province,
					country: country,
					postalCode: postalCode
				},
			success: function(results){
					//If ajax call is success, go to change to remove contact page
					if(results=="success"){
						ajaxAccountSelection="success";
						if(dialog!=null)
							dialog.dialog('close');
						ajaxSuccessFunction();
						
					}else{
						jQuery('#errorDiv_popup').append('<li><strong>Unable to save value</strong></li>');
						jQuery('#errorDiv_popup').show();
						}
				},
			failure: function(){
					jQuery('#errorDiv_popup').append('<li><strong>Unable to save value</strong></li>');
					jQuery('#errorDiv_popup').show();
					}
			});
	
	
}

/* 
	var errorMsg = "${sessionScope.isError}";
	alert("errorMsg"+errorMsg);
	if('TRUE' == errorMsg){
		jQuery('#errorDiv_NoRecord').show();
	}else{
		jQuery('#errorDiv_NoRecord').hide();
	}
 */
</script>