<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@page import="com.lexmark.services.util.ChangeMgmtConstant"%>

<style>
div.gridbox table.obj tr td{
word-break: break-all !important;
}
</style>

<%--<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>--%>
<portlet:renderURL var="countryListPopulateURL_popup" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" >
	<portlet:param name="action" value="countryDropDownPopulate"></portlet:param>
</portlet:renderURL>


<portlet:resourceURL var="accountListPopulateURL" id="populateAccountList">
</portlet:resourceURL>
<portlet:resourceURL var="accountDetailsSession" id="setCurrentAccountValuesToSession"></portlet:resourceURL>


<div class="portletBodyWrap" id="accountInitialize" style="display: none;">
<div id="errorDiv_popup" class="error" style="display: none;"></div>
	
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	 <div id="accountListGrid" class="gridbox gridbox_light"></div>
							<div id="loadingNotificationPopup_createNewRequest" class="gridLoading" style="display: none;">
										<spring:message code="requestInfo.popup.loading"/><img src="<html:imagesPath/>gridloading.gif" />
			  				</div>
						</div>
					</div>
					
					<div class="pagination" >
						<span id="pagingAreaPopup"></span><span id="infoArea_pop"></span>
					</div>

				<div class="buttonContainer">
				<button class="button_cancel" id="accountSelection_cancel_btn" type="button" onclick="dialog.dialog('close')"><spring:message code="button.cancel"/></button>
				</div>


</div>

<script type="text/javascript">

var accountListpopulateURLvar="${accountListPopulateURL}";
var accountGrid;
var linkObj_accSelect;
var ajaxAccountSelection="";
var ajaxSuccessFunction;
var lbsFlagAccSelect,mapRequest=false;
function showHideDivs(){
	//alert('in show Hide divs');
	jQuery('#createContentPopup').slideUp(function(){
		// Below code changes the dialog title to select an account
		jQuery('#accountInitialize').slideDown();
		jQuery('span.ui-dialog-title').text("<spring:message code='requestInfo.heading.accountSelection'/>");
		
		});
	hideOverlayPopup();
}
function showAccountSelection(){
	var bindIDs = '#changeManagementLinks a,#returnSupplies,#catalogorder,#others,#hardwareorder';
	if(typeof pageType != 'undefined' && pageType=="deviceFinder"){
		bindIDs = '#changeManagementLinks a,#catalogorder,#others,#hardwareorder';
	}
	jQuery(bindIDs).bind('click',function(){
		
		showOverlayPopup();
		
		/*Commented for Asset Acceptance MPS 2.1*/
				
		if(this.id=="catalogorder")
			isCatalogPage="true";
		else
			isCatalogPage="false";
		
		/*Added for Hardware Order MPS Phase 2.1*/
		if(this.id=="hardwareorder")
			isHardwarePage="true";
		else
			isHardwarePage="false";
		/*Add end*/
		if(this.id=="mapsRequestLink"){
			mapRequest=true;
		}else{
			mapRequest=false;
		}
		if(accountGrid==null){
			//jQuery('#accountListGrid').addClass('gridbox gridbox_light');
			initializeAccountGrid(showHideDivs);
		}else{
			reloadAccountPopupGrid();
		<%--	if(accountGrid.getAllRowIds()==""){
				reloadAccountPopupGrid();
			}else{
				if(this.id=="catalogorder"){
					accountGrid.filterBy(0,"true",true);
				}else{
					accountGrid.filterByAll();
					}
				if(accountGrid.getAllRowIds() != "" ){
				    	  if(accountGrid.getAllRowIds().split(',').length == 1){
				        	//alert('only 1 account');
				        	jQuery('#button'+accountGrid.getAllRowIds()).click();
				        	}
				    	  else{
				    		  showHideDivs();
				      	  }
				}else{					
					showHideDivs();
				}
				
			}--%>
		}
				
			
		
		
			
			
			//});
		//alert(accountListpopulateURLvar);
		//loadCountryListPopup();	
		
		linkObj_accSelect=this;
		return false;
	});
}

function setAccount(accountId,vendorAccountId,isCreateClaimFlag,isViewOrderFlag,rowId,isRequestExpedite,soldToList,showPrice,poFlag,creditCardFlag,splitterFlag,salesOrg,contractNumber,contractLine){
	//alert('Account Selected with Id'+accountId);
	if(dialog!=null){
			showOverlayPopup();
    	  }
	
	//fire ajax to save the data to session from grid	
	jQuery.ajax({
			url:'${accountDetailsSession}',			
			type:'POST',
			data: {
				 	//Ajax call with selected row data
					accountId :   accountId,
					vendorAccountID:  vendorAccountId,
					isCreateClaimFlag:  isCreateClaimFlag,
					isViewOrderFlag:  isViewOrderFlag,
					accountName:  accountGrid.cellById(rowId,3).getValue(),
					accountOrganization : '',
					agreementId : accountGrid.cellById(rowId,4).getValue(),
					agreementName : accountGrid.cellById(rowId,5).getValue(),
					rowCount : accountGrid.getAllRowIds().split(',').length,
					country : accountGrid.cellById(rowId,2).getValue(),
					<%=ChangeMgmtConstant.REQUESTEXPEDITE%> : isRequestExpedite,
					soldToList : soldToList	,
					showPrice : showPrice,
					poFlag : poFlag,
					creditCardFlag : creditCardFlag,
					splitterFlag : splitterFlag,
					salesOrg : salesOrg,
					contractNumber : contractNumber,
					contractLine : contractLine
			},
			success: function(results){
				callOmnitureAction('<%=LexmarkSPOmnitureConstants.ACCOUNTSELECTIONPOPUP%>','<%=LexmarkSPOmnitureConstants.ACCOUNTPOPUPSELECTACCOUNT%>');
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
    accountGrid.setHeader("<spring:message code='serviceRequest.listHeader.serviceAccountListPopUp'/>");
    accountGrid.attachHeader(",,#text_filter,#text_filter,,#text_filter,#text_filter,");
    <%-- Changes for defect 12603 Changed the width of Account name to * --%>
    accountGrid.setInitWidths("0,0,150,*,0,100,*,225");
    accountGrid.setColAlign("left,left,left,left,left,left,left,center");
    accountGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
    accountGrid.setColSorting("str,str,str,str,str,str,str,na");
 
    accountGrid.enableMultiline(true);  
    accountGrid.init();
    accountGrid.prftInit();
    accountGrid.enablePaging(true,5, 10, "pagingAreaPopup", true, "infoArea_pop");
    accountGrid.setPagingSkin("bricks");
    accountGrid.enableAutoWidth(true);
    accountGrid.enableAutoHeight(true);
    accountGrid.setSkin("light");
    accountGrid.setColumnHidden(0,true);
    accountGrid.setColumnHidden(1,true);
    accountGrid.setColumnHidden(4,true);
    <%-- Changes for defect 11337 hide agreement name column --%>
    accountGrid.setColumnHidden(5,true);
    if(isCatalogPage=="true" || isHardwarePage=="true"){
 	   accountGrid.setColumnHidden(5,false);
 	}
    <%--Ends Changes for defect 11337 hide agreement name column --%>
    if(isCatalogPage=="false" && isHardwarePage=="false"){
    	accountGrid.setColumnHidden(6,true);
    }
   
    accountGrid.attachEvent("onXLS", function() {
    	jQuery('#loadingNotificationPopup_createNewRequest').show();	
    		
	});
    /*accountGrid.attachEvent("onBeforePageChanged", function(){alert('in onBeforePageChanged');});
    accountGrid.attachEvent("onPaging", function(){alert('in onpaging');});
    accountGrid.attachEvent("onPageChanged", function(){alert('in onPageChanged');});*/
    
    accountGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
    accountGrid.attachEvent("onXLE", function() {
    	jQuery('#loadingNotificationPopup_createNewRequest').hide();
    	if(isCatalogPage=="true")
    		accountGrid.filterBy(0,"true",true);
      	if(accountGrid.getAllRowIds() != "" ){
    	  if(accountGrid.getAllRowIds().split(',').length == 1){
        	//alert('only 1 account');
        	//loadXLEfunc();
        	jQuery('#button'+accountGrid.getAllRowIds()).click();
        	}
    	  else{
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

	
	//alert("isCatalogPage-->"+isCatalogPage);

	//alert("resource url in account selection-->"+tempUrl);
	lbsFlagAccSelect=location.href.indexOf("fleet-management")!=-1?true:false;
    accountGrid.loadXML(accountListpopulateURLvar+"&isCatalogPage="+isCatalogPage+"&isHardwarePage="+isHardwarePage+"&isLbs="+lbsFlagAccSelect+"&mapReq="+mapRequest);
	
}

function reloadAccountPopupGrid(){
	if(isCatalogPage=="true" || isHardwarePage=="true"){
	 	   accountGrid.setColumnHidden(5,false);
	 	  accountGrid.setColumnHidden(6,false);
	 	}else{
	 		accountGrid.setColumnHidden(5,true);
		 	  accountGrid.setColumnHidden(6,true);
	 	}
	lbsFlagAccSelect=location.href.indexOf("fleet-management")!=-1?true:false;
	 accountGrid.clearAndLoad(accountListpopulateURLvar+"&isCatalogPage="+isCatalogPage+"&isHardwarePage="+isHardwarePage+"&isLbs="+lbsFlagAccSelect+"&mapReq="+mapRequest);
}
</script>