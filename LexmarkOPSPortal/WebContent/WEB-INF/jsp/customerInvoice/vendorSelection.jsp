
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>#button1 {
    
    margin-left:0!important;
   
}</style>

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



<div id="totalContent">
<div class="portletBodyWrap" id="accountRcvblInitialize" style="display: none;">
<div id="errorDiv_popup1" class="error" style="display: none;">
<ul>
<li><strong><spring:message code='requestInfo.accountSelection.noAccountFound'/></strong></li>
</ul>
</div>
	
					<div class="portlet-wrap rounded">
  						<div class="portlet-wrap-inner">	
						 	 <div id="acntRcvblListGrid" class="gridbox gridbox_light"></div>
							<div id="loadingNotificationPopup_AR" class="gridLoading" style="display: none;">
										<br><spring:message code="requestInfo.popup.loading"/><img src="<html:imagesPath/>gridloading.gif" /><br>
			  				</div>
						</div>
					</div>
					
					<div class="pagination" style="" >
						<span id="pagingArea2"></span><span id="infoArea2"></span>
					</div>

				<div class="buttonContainer">
				<button class="button_cancel" type="button" onclick="dialog.dialog('close')"><spring:message code="button.cancel"/></button>
				</div>


</div>
</div>
<form id="customerAccForm" method="post" action="customerinvoice">
	<input type="hidden" name="accSelDn" value="true" />
	<input type="hidden" name="account" id="account" />
	<input type="hidden" name="billToAddress" id="billToAddress" />
	<input type="hidden" name="companycode" id="companycode"  />
	<input type="hidden" name="customerNumber" id="customerNumber"  />
	<input type="hidden" name="accountCount" id="accountCount"/>
</form>
<script type="text/javascript">

var vendorListpopulateURLvar;
var vendorGrid;
var linkObj_accSelect;
var ajaxAccountSelection="";
var ajaxSuccessFunction;
var headerVal;
var dialog=null;


function setPayeeAccount(rowId)
{
	//alert("inside set payee");
	if(vendorGrid.getAllRowIds().split(',').length!=1){
		showOverlayPopup();
	}
	var idArr=["account","billToAddress","companycode","customerNumber"];
 	for(i=0;i<4;i++){
 	 	<%--alert(vendorGrid.cellById(rowId,i).getValue());--%>
 	 		jQuery('#'+idArr[i]).val(vendorGrid.cellById(rowId,i).getValue());
 	 
 	}
 	
	<%-- For temporary test
	 	jQuery('#customerNumber').val("5097");//1027");//5050");
	 	jQuery('#companycode').val("0000219251");//0000140886");--%>
	 	jQuery('#accountCount').val(vendorGrid.getAllRowIds().split(',').length);
	 	 
 	jQuery('#customerAccForm').submit();
  
}

function createGrid()
{	showOverlay();
	//alert("createGrid " + gridName);	
	
	vendorGrid = new dhtmlXGridObject("acntRcvblListGrid");
    vendorGrid.setImagePath("<html:imagesPath/>gridImgs/");        
    vendorGrid.setHeader("<spring:message code='requestInfo.accountsPayable.accountsRecivableListPopUp'/>,");
    vendorGrid.attachHeader("#text_filter,#text_filter,#text_filter,,#text_filter,");
    vendorGrid.setInitWidths("100,150,90,0,90,100");    
    vendorGrid.setColAlign("left,left,left,left,left,left");    
    vendorGrid.setColTypes("ro,ro,ro,ro,ro,ro");    
    vendorGrid.setColSorting("str,str,str,str,str,na");    
    vendorGrid.enableMultiline(true);  
    vendorGrid.enableColumnMove(false);  
    vendorGrid.init();    
    vendorGrid.prftInit();    
   	vendorGrid.enablePaging(true,5, 10, "pagingArea2", true, "infoArea2");
	<%--
    vendorGrid.a_direction = "asc";	
    vendorGrid.loadPagingSorting(0,'asc');
    vendorGrid.sortIndex = 0;

    vendorGrid.setSortImgState(true,'asc',0);
	--%>
	
    vendorGrid.setPagingSkin("bricks");
    vendorGrid.enableAutoWidth(true);
    vendorGrid.enableAutoHeight(true);
    vendorGrid.setSkin("light");
    vendorGrid.attachEvent("onXLS", function() {
    	jQuery('#loadingNotificationPopup_AR').show(); 
    	
	});
    vendorGrid.attachEvent("onXLE", function() {
    	jQuery('#loadingNotificationPopup_AR').hide(); 
    	if(vendorGrid.getAllRowIds() != "" ){
    		if(vendorGrid.getAllRowIds().split(',').length == 1){
        		//alert('only 1 account');
        		//alert(gridName=="acntRcvblListGrid"?"AR":"AP");
        		//hideShowDivs();
        		jQuery('#button'+vendorGrid.getAllRowIds()).click();
        	}else{
            	
        		hideShowDivs();
            	}
    	}else{
    		jQuery('#errorDiv_popup1').show();
    		hideShowDivs();
        	}
    	
    	
    	
	});
	<%--var url=updateGridURL("", vendorGrid.getSortColByOffset(), vendorGrid.a_direction, vendorGrid.filterValues==null?"":vendorGrid.filterValues);
	--%>
	
    vendorGrid.loadXML("${accountsRecvblURL}");
	
}
function hideShowDivs(){
	//alert('gridType'+gridType+'vendorGrid'+vendorGrid);
	
	dialog=jQuery('#totalContent').dialog({
		autoOpen: false,
		modal: true,
		width: 580,
		height: 100,
		position: ['center','top'],
		open: function(){
			
				jQuery("#accountRcvblInitialize").show();
				jQuery('#totalContent').show();				
				jQuery('span.ui-dialog-title').text("<spring:message code="partnerPayments.heading.selectAnAccount"/>");
				
				},
		close: function(event,ui){
	   				  dialog.dialog('destroy');
					  dialog=null;
					  vendorGrid=null;
					  jQuery('#acntRcvblListGrid,#vendorListGrid').empty();
					  jQuery('#accountInitialize,#accountRcvblInitialize').hide();
					  jQuery('#errorDiv_popup1').hide();
					  
					  hideOverlay();
					  //jQuery('#accountInitialize').hide();
					  
					}
				});
	//alert('vendorGrid'+vendorGrid);
	jQuery(document).scrollTop('0');
	dialog.dialog('open');		
	
	
}


</script>