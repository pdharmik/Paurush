<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>

<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_drag.js"></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
 <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />

<table width="953px" border="0" cellspacing="0" cellpadding="0" align="center" class="table-style14" id="emailPrintWraper">
	<tr>
    	<td class="table-td-style23"><img src="<html:imagesPath/>logoLexEmail.png" width="100%" height="65" /></td>
  	</tr>
	<br></br>
  <tr>
    <td class="table-td-style10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" class="table-td-style11"><strong><spring:message code="Details.changeRequestDetails.heading.supplyReqDeails"/> </strong> &raquo; <strong><span class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="reqNumber"></span></span></strong></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.requestConfirmation"/></strong></td>
  </tr>
    <tr>
    <td valign="top" class="table-td-style13">
      <tr>
    <td valign="top" class="table-td-style13">  <spring:message code="requestInfo.message.confirmMessage1"/>   
		<span id="reqNo1"></span> <spring:message code="requestInfo.message.confirmMessage2"/>
		</td>
  </tr></td>
  </tr>

  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.area"/> </strong></td>
                <td class="table-td-style16" id="area"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.subArea"/></strong></td>
                <td class="table-td-style16" id = "subArea"></td>
              </tr>
             
              <tr>
                <td class="table-td-style16" style="display: none;" id="ordersourceheader"><strong> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></strong></td>
                <td class="table-td-style16" id = "orderSource"></td>
              </tr>
             
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.status"/> </strong></td>
                <td class="table-td-style16" id = "status"></td>
              </tr>
               <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.accountName"/> </strong></td>
                <td class="table-td-style16" id = "accountName"></td>
              </tr>
            </table>
            </td>
          <td width="50%" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="3">
              
             <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></strong></td>
                <td class="table-td-style16" id="creationTime"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.requesterName"/></strong></td>
                <td class="table-td-style16" id="reqFnameLname"></td>
              </tr>               		
               		
              <tr>
                <td class="table-td-style16" style="display: none;" id="subTotalheader"><strong><spring:message code="Details.changeRequestDetails.label.itemSubTotalBeforeTax"/></strong></td>
                <td class="table-td-style16" id="subTotal"></td>
              </tr>             
              
              <tr>
                <td class="table-td-style16" style="display: none;" id="taxheader"><strong><spring:message code="Details.changeRequestDetails.label.tax"/> </strong></td>
                <td class="table-td-style16" id="tax"></td>
              </tr>
              
              
              <tr>
                <td class="table-td-style16" style="display: none;" id="totalheader"><strong><spring:message code="Details.changeRequestDetails.label.total"/> </strong></td>
                <td class="table-td-style16" id="totalAmount"></td>
              </tr>  
                       
            </table>
            </td>
        </tr>
      
      </table>
      <br>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="Details.supplyRequestDetails.heading.requestStatusHistory"/></th>
              </tr>
          <tr><td>
        <div id="tab_statusGrid"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
       <br>
        
       <table width="100%" border="0" cellspacing="5" cellpadding="0" id="selectedAsset" style="display: none;">
	    	  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.selectedAsset"/></strong></td>
  		</tr>
        <tr>
        
          <td width="20%">
       			<div id="MyPicture">	</div>
       			<div id="imageNo">	</div>
        
        </td>
        
          <td width="40%" valign="top" class="table-td-style14">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.assetIdentifiers"/></th>
              </tr>
              <tr>
                <td width="100" class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.serialNumber"/></strong></td>
                <td class="table-td-style16" id="serialNo"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.productName"/></strong></td>
                <td class="table-td-style16" id="productName"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.installDate"/> </strong></td>
                <td class="table-td-style16" id="installDate"></td>
              </tr>
			   <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.ipAddress"/> </strong></td>
                <td class="table-td-style16" id="ipAddress"></td>
              </tr>
			   <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.hostName"/> </strong></td>
                <td class="table-td-style16" id="hostName"></td>
              </tr>
			   <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/> </strong></td>
                <td class="table-td-style16" id="assetTag"></td>
              </tr>
            </table></td>
            
			<td width="40%" valign="top" class="table-td-style14">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.serviceAddress"/></th>
              </tr>
             <tr>
             	<td colspan="2" valign="top" class="table-td-style16" id="serviceAddressID"></td>      		
			 </tr>				                        
            </table></td>
        
		 <td>
		 </table>
		 <table width="100%" border="0" cellspacing="5" cellpadding="0" id="pageCountHide" style="display: none;">
		 <tr>
		 <th align="left" class="table-td-style15"><spring:message code="requestInfo.heading.pageCountsAtTimeOfOrder"/></th>
		 </tr>   		 
		<tr>
          <td width="50%" valign="top" class="table-td-style14" id="pageCounts">
		  </td>          
        </tr>
      
      </table>
		 
      <br>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="Details.supplyRequestDetails.label.associatedRequests"/></th>
              </tr>
          <tr><td>
        <div id="associatedGridID"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
		
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="Details.manageAsset.heading.requestHistoryOnThisAsset"/></th>
              </tr>
          <tr><td>
        <div style="display: none;" id="pastHistoryList"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
      
	  	 <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td class="table-td-style16" id="primaryName"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td class="table-td-style16" id="primaryPhone"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td class="table-td-style16" id="primaryEmail"></td>
              </tr>
            </table></td>
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  class="table-td-style16" id="custRefNo"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  class="table-td-style16" id="costCenter"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style16" id="description"></td>
              </tr>
            
            </table></td>
        </tr>
 
        <tr style="display: none;" id="secondaryContact">
          <td  valign="top" class="table-td-style14"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td class="table-td-style16" id="secondaryName"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td class="table-td-style16" id="secondaryPhone"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td class="table-td-style16" id="secondaryEmail"></td>
              </tr>
            </table></td>
        </tr>
       
      </table>
       </td></tr>
      
		<tr>
		<td>&nbsp;</td>
		</tr>
		
		<tr>
		<td>
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="Details.supplyRequestDetails.heading.pendingshipment"/></th>
              </tr>
          <tr><td>
        <div style="display: none;" id="tab_inprocessGrid"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
		</td>
	</tr>
		
	<tr>
	<td>
	<div id="suppliesShipment"></div>
	</td>
	</tr>
	<br>
	
	<tr style="display: none;" id="sr_technician_h4">
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="Details.supplyRequestDetails.heading.activity"/></th>
              </tr>
          <tr><td>
        <div id="sr_technician_div"></div></td>
        </tr>
            </table></td>
        </tr>
      
	<br>
	
	<tr style="display: none;" id="cancelBlockID">
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              
          <tr><td>
        <div id="cancelBlock"></div></td>
        </tr>
            </table></td>
        </tr>
     
        <br>
	<tr>
  	<td>
  	<br>
  	<div colspan="2" align="left" class="div-style53"><spring:message code="requestInfo.heading.shippingAndBillingInformation"/></div>
  	<br>
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
	
        <tr>
          <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.shipToAddress"/></th>
              </tr>
           		<tr> 
           			<td colspan="2" valign="top"  class="table-td-style16" id="shipToAddr">
           				
					</td>
				</tr>
				
			   <tr>                
                <td valign="top"  class="table-td-style16" id="physicalLocation1"></td>
              </tr>
               <tr>                
                <td valign="top"  class="table-td-style16" id="physicalLocation2"></td>
              </tr>
               <tr>                
                <td valign="top"  class="table-td-style16" id="physicalLocation3"></td>
              </tr>              
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.orderInstructions"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.specialInstructions"/></strong></td>
                <td valign="top"  class="table-td-style16" id="specialInstructions"></td>
              </tr>
			   <%-- <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.defaultSpecialInstructions"/></strong></td>
                <td valign="top"  class="table-td-style16">${assetDetailPageForm.defaultSpecialInstruction}</td>
              </tr> --%>
			   <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.requestExpediteOrder"/></strong></td>
                <td valign="top"  class="table-td-style16"  id="expediteOrder"></td>     
               </tr>
			   <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.requestedDeliveryDate"/></strong></td>
                <td valign="top"  class="table-td-style16" id="effectiveDate"></td>
              </tr>
			  
              </table></td>
        </tr>
        
      </table>  
	  
	  </td>
	  </tr>
	  
     <tr>
  	<td>
      	<table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5" id="paymentBlock">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.paymentDetails"/></th>
              </tr>
              
              <tr>
                <td valign="top" width="100" class="table-td-style16" id="poNumber">
              </td>
                
              </tr>
            
               <tr>
                <td valign="top" width="100" class="table-td-style16" id="creditCardToken"></td>   
                
              </tr>
              
            </table></td>
        </tr>
      </table>
	
	   </td>
	   </tr>
	   <tr>
  	<td>
	    		<table width="100%" border="0" cellspacing="5" cellpadding="0">
					  <tr>
    <td valign="top" class="table-td-style12"><strong><spring:message code="requestInfo.heading.notesAndAttachments"/></strong></td>
  </tr>
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.notes"/></th>
              </tr>
              <tr> <td valign="top" class="table-td-style16" id="notes">         		 
            </td></tr>
            </table></td>
        </tr>
      </table>
	 </td>
	 </tr>
	 
	 <tr>
  	<td>
	 		<table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.attachments"/></th>
              </tr>
              <tr>
              <td>
              <div id="showAttachment">
              
              </div>
              </td>
              </tr>
              
              
            </table></td>
        </tr>
      </table>
      
      </td>
  </tr>
  </table>

      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
          <tr><td>
        <table width="800" border="0" cellspacing="5" cellpadding="0" align="center">
	<tr id="topEmail">
		<td width="55%"></td>
		<td align="right" width="25%">
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
                <img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon cursor-pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
                <span class="cursor-pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
            </a>
		</td>
		<td align="right">
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code="button.cancel"/></button>
		</td>
	</tr>
</table>
        </tr>
            </table></td>
        </tr>

      </table>
 

<br>
<%-- Commented for adding AUI popup
<script type="text/javascript">	
function email(){
	new Liferay.Popup({
	title: "<spring:message code='requestInfo.title.emailConfirm'/>",
  	position: ['center','top'],
  	modal: true,
  	width: 550, 
  	height: 'auto',
  	xy: [100, 100],
  	onClose: function() {showSelect();},
  	url:"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>"
  	});

	};
</script>
 --%>
<script type="text/javascript">
window.document.getElementById("tab_statusGrid").innerHTML = window.opener.window.document.getElementById("tab_statusGrid").innerHTML;
window.document.getElementById("creationTime").innerHTML = window.opener.window.document.getElementById("creationTime").innerHTML;
window.document.getElementById("associatedGridID").innerHTML = window.opener.window.document.getElementById("associatedGridID").innerHTML;
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
window.document.getElementById("reqNo1").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
window.document.getElementById("area").innerHTML = window.opener.window.document.getElementById("area").innerHTML;
window.document.getElementById("subArea").innerHTML = window.opener.window.document.getElementById("subArea").innerHTML;
window.document.getElementById("status").innerHTML = window.opener.window.document.getElementById("status").innerHTML;
window.document.getElementById("accountName").innerHTML = window.opener.window.document.getElementById("accountName").innerHTML;
window.document.getElementById("reqFnameLname").innerHTML = window.opener.window.document.getElementById("reqFnameLname").innerHTML;
window.document.getElementById("primaryName").innerHTML = window.opener.window.document.getElementById("primaryName").innerHTML;
window.document.getElementById("primaryPhone").innerHTML = window.opener.window.document.getElementById("primaryPhone").innerHTML;
window.document.getElementById("primaryEmail").innerHTML = window.opener.window.document.getElementById("primaryEmail").innerHTML;
window.document.getElementById("custRefNo").innerHTML = window.opener.window.document.getElementById("custRefNo").innerHTML;
window.document.getElementById("costCenter").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("description").innerHTML = window.opener.window.document.getElementById("description").innerHTML;
window.document.getElementById("shipToAddr").innerHTML = window.opener.window.document.getElementById("shipToAddr").innerHTML;
window.document.getElementById("physicalLocation1").innerHTML = window.opener.window.document.getElementById("physicalLocation1").innerHTML;
window.document.getElementById("physicalLocation2").innerHTML = window.opener.window.document.getElementById("physicalLocation2").innerHTML;
window.document.getElementById("physicalLocation3").innerHTML = window.opener.window.document.getElementById("physicalLocation3").innerHTML;
window.document.getElementById("specialInstructions").innerHTML = window.opener.window.document.getElementById("specialInstructions").innerHTML;
window.document.getElementById("expediteOrder").innerHTML = window.opener.window.document.getElementById("expediteOrder").innerHTML;
window.document.getElementById("effectiveDate").innerHTML = window.opener.window.document.getElementById("effectiveDate").innerHTML;
window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;
window.document.getElementById("serviceAddressID").innerHTML = window.opener.window.document.getElementById("serviceAddressID").innerHTML;

if(window.opener.window.document.getElementById("pageCounts") != null){
	window.document.getElementById("pageCounts").innerHTML = window.opener.window.document.getElementById("pageCounts").innerHTML;
    jQuery('#pageCountHide').show();
}else{
	 jQuery('#pageCountHide').remove();
}




</script>
<script type="text/javascript">

if(window.opener.window.document.getElementById("tab_inprocessGrid") != null){
	jQuery('#tab_inprocessGrid').show();
window.document.getElementById("tab_inprocessGrid").innerHTML = window.opener.window.document.getElementById("tab_inprocessGrid").innerHTML;
}
else{
	jQuery('#tab_inprocessGrid').remove();
}
</script>
<script type="text/javascript">
if(window.opener.window.document.getElementById("selectedAsset")!= null){
		jQuery('#selectedAsset').show();
window.document.getElementById("MyPicture").innerHTML = "<img src='" + window.opener.window.document.getElementById('MyPicture').src + "' width='100' height='100'/>";
window.document.getElementById("imageNo").innerHTML = window.opener.window.document.getElementById("imageNo").innerHTML;
window.document.getElementById("serialNo").innerHTML = window.opener.window.document.getElementById("serialNo").innerHTML;
window.document.getElementById("productName").innerHTML = window.opener.window.document.getElementById("productName").innerHTML;
window.document.getElementById("installDate").innerHTML = window.opener.window.document.getElementById("installDate").innerHTML;
window.document.getElementById("ipAddress").innerHTML = window.opener.window.document.getElementById("ipAddress").innerHTML;
window.document.getElementById("hostName").innerHTML = window.opener.window.document.getElementById("hostName").innerHTML;
window.document.getElementById("assetTag").innerHTML = window.opener.window.document.getElementById("assetTag").innerHTML;

}else{
jQuery('#selectedAsset').remove();
}
</script>
<script type="text/javascript">
if(window.opener.window.document.getElementById("pastReqExpand") != null){
	 jQuery('#pastHistoryList').show();
	window.document.getElementById("pastHistoryList").innerHTML = window.opener.window.document.getElementById("pastReqExpand").innerHTML;
}
else{
	jQuery('#pastHistoryList').remove();
}

if(window.opener.window.document.getElementById("secondaryContact")!= null){
	 jQuery('#secondaryContact').show();
	window.document.getElementById("secondaryName").innerHTML = window.opener.window.document.getElementById("secondaryName").innerHTML;
	window.document.getElementById("secondaryPhone").innerHTML = window.opener.window.document.getElementById("secondaryPhone").innerHTML;
	window.document.getElementById("secondaryEmail").innerHTML = window.opener.window.document.getElementById("secondaryEmail").innerHTML;	
}
else{
	 jQuery('#secondaryContact').remove();
}

if(window.opener.window.document.getElementById("sr_technician_h4") != null){
	 jQuery('#sr_technician_h4').show();
	window.document.getElementById("sr_technician_div").innerHTML = window.opener.window.document.getElementById("sr_technician_div").innerHTML;	
}
else{
	 jQuery('#sr_technician_h4').remove();
}

if(window.opener.window.document.getElementById("cancelBlockID") != null){
	 jQuery('#"cancelBlockID"').show();
	window.document.getElementById("cancelBlock").innerHTML = window.opener.window.document.getElementById("cancelBlockID").innerHTML;	
}
else{
	 jQuery('#"cancelBlockID"').remove();
}



if(window.opener.window.document.getElementById("orderSource") != null){
jQuery('#ordersourceheader').show();
window.document.getElementById("orderSource").innerHTML = window.opener.window.document.getElementById("orderSource").innerHTML;
}
else{
	jQuery('#ordersourceheader').remove();
}


if(window.opener.window.document.getElementById("subTotal") != null){
	 jQuery('#subTotalheader').show();
	window.document.getElementById("subTotal").innerHTML = window.opener.window.document.getElementById("subTotal").innerHTML;
}
else{
	jQuery('#subTotalheader').remove();
}

if(window.opener.window.document.getElementById("tax") != null){
	jQuery('#taxheader').show();
	window.document.getElementById("tax").innerHTML = window.opener.window.document.getElementById("tax").innerHTML;
}
else{
	jQuery('#taxheader').remove();
}

if(window.opener.window.document.getElementById("totalAmount") != null ){
	jQuery('#totalheader').show();
	window.document.getElementById("totalAmount").innerHTML = window.opener.window.document.getElementById("totalAmount").innerHTML;
}
else{
	jQuery('#totalheader').remove();
}

if(window.opener.window.document.getElementById("servAddrLine2") != null){
	jQuery('#servAddrLine2').show();
	window.document.getElementById("servAddrLine2").innerHTML = window.opener.window.document.getElementById("servAddrLine2").innerHTML;
}

if(window.opener.window.document.getElementById("paymentBlock") !=null){
	jQuery('#paymentBlock').show();
	if(window.opener.window.document.getElementById("poNumber") !=null){
		jQuery('#poNumber').show();
		window.document.getElementById("poNumber").innerHTML = window.opener.window.document.getElementById("poNumber").innerHTML;
		}else{
			jQuery('#poNumber').remove();	
			}
	if(window.opener.window.document.getElementById("creditCardToken") !=null){
		jQuery('#creditCardToken').show();
		window.document.getElementById("creditCardToken").innerHTML = window.opener.window.document.getElementById("creditCardToken").innerHTML;
		}else{
			jQuery('#creditCardToken').remove();
			}
}else{
	jQuery('#paymentBlock').remove();
}
</script>
<%-- <script>
var emailPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 650,
	height: 350,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function showEmailPopup(){
	emailPopUpWindow.show();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script> --%>


<script type="text/javascript">
emailFunction();
var emailPopUpWindow;
function emailFunction(){
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	emailPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	position: [-80,20],
	resizable: false,
	width: 650,
	height: 350,
	xy: [-80, 20],
	destroyOnClose: true,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
}
function showEmailPopup(){
	jQuery(window).scrollTop(0);
	emailPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script>