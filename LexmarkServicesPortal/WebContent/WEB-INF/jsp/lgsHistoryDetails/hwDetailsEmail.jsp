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
          <td width="130" height="20" class="table-td-style11"><strong><spring:message code="hardwareDetails.label.hardwareReqDetails"/> </strong> &raquo; <strong><span class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> <span id="reqNumber"></span></span></strong></td>
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
		<span id="reqNo2"></span> <spring:message code="requestInfo.message.confirmMessage2"/>
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
                <td class="table-td-style16" id="subarea"></td>
              </tr>
              <tr id="orderSource1">
                <td class="table-td-style16"><strong> <spring:message code="Details.supplyRequestDetails.label.orderSource"/></strong></td>
                <td class="table-td-style16" id="orderSource"></td>
              </tr>
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.status"/> </strong></td>
                <td class="table-td-style16" id="status"></td>
              </tr>
               <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.accountName"/> </strong></td>
                <td class="table-td-style16" id="accountName"></td>
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
                <td class="table-td-style16" id="requestorName"></td>
              </tr>
             
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.itemSubTotalBeforeTax"/></strong></td>
                <td class="table-td-style16" id="beforeTax"></td>
              </tr>
             
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.tax"/> </strong></td>
                <td class="table-td-style16" id="tax"></td>
              </tr>
              
              <tr>
                <td class="table-td-style16"><strong><spring:message code="Details.changeRequestDetails.label.total"/> </strong></td>
                <td class="table-td-style16" id="total"></td>
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
        <div class="width-75per" id="tab_statusGrid"></div></td>
        </tr>
            </table></td>
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
                <td valign="top"  class="table-td-style16" id="costCntr"></td>
              </tr>
              <tr>
                <td valign="top"  class="table-td-style16"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  class="table-td-style16" id="addDesc"></td>
              </tr>
            
            </table></td>
        </tr>
        <tr id="secondarySection" style="display: none;">
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
        <div id="tab_inprocessGrid"></div></td>
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
	<tr >
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              
          <tr><td>
        <div id="cancelBlockID" style="display: none;"></div></td>
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
           			<td colspan="2" valign="top"  class="table-td-style16" id="shipToAddress">
           				
					</td>
				</tr>
				
				    	 <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.building"/></strong></td>
                <td valign="top"  class="table-td-style16" id="building"></td>
              </tr>
               <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.floor"/></strong></td>
                <td valign="top"  class="table-td-style16" id="floor"></td>
              </tr>
               <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.addressInfo.label.office"/></strong></td>
                <td valign="top"  class="table-td-style16" id="office"></td>
              </tr>              
            </table></td>
          <td rowspan="2" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.orderInstructions"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" class="table-td-style16"><strong><spring:message code="requestInfo.label.specialInstructions"/></strong></td>
                <td valign="top"  class="table-td-style16" id="specialInst"></td>
              </tr>
              </table></td>
        </tr>
        
      </table>  
	  
	  </td>
	  </tr>
	  
     <tr>
  	<td>
      	<table width="100%" border="0" cellspacing="5" cellpadding="0" id="paymentBlock" style="display: none;">
	
		<tr>
   <td width="50%" valign="top" class="table-td-style14">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" class="table-td-style15"><spring:message code="requestInfo.heading.paymentDetails"/></th>
              </tr>
              <tr id="poNumber1" style="display: none;">
                <td valign="top" width="100" class="table-td-style16"><strong>
    			<spring:message code="requestInfo.label.poNumber"/></strong></td>
                <td valign="top"  class="table-td-style16" id="poNumber"></td>
              </tr>
               <tr id="creditCard1" style="display: none;">
                <td valign="top" width="100" class="table-td-style16"><strong>
    			<spring:message code="requestInfo.heading.credit"/>:</strong></td>
                <td valign="top"  class="table-td-style16" id="creditCard"></td>
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
              <tr> <td valign="top" class="table-td-style16" id="notes"></td>
              </tr>
            </table></td>
        </tr>
      </table>
	 </td>
	 </tr>
	 <tr>
  	<td>
<br>
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

<script type="text/javascript">


window.document.getElementById("tab_inprocessGrid").innerHTML = window.opener.window.document.getElementById("tab_inprocessGrid").innerHTML;
if(window.opener.window.document.getElementById("suppliesShipment") != null){
	window.document.getElementById("suppliesShipment").innerHTML = window.opener.window.document.getElementById("suppliesShipment").innerHTML;
}
window.document.getElementById("showAttachment").innerHTML = window.opener.window.document.getElementById("showAttachment").innerHTML;

	if(window.opener.window.document.getElementById("cancelBlockID")!=null){
		window.document.getElementById("cancelBlockID").innerHTML = window.opener.window.document.getElementById("cancelBlockID").innerHTML;
	}

<%--	

		Commented for Adding AUI popup

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
	function minimize() {
		var SRInProgressGrid = window.opener.SRInProgressGrid;
		var pageSize = SRInProgressGrid.rowsBufferOutSize;
		var begin = (SRInProgressGrid.currentPage-1) * pageSize;
		var end =  SRInProgressGrid.currentPage * pageSize - 1;
		var index_sub_row=0;
		SRInProgressGrid.forEachRow(function(id){
			if(id >= begin && id <= end)
				SRInProgressGrid.cellById(id,index_sub_row).close();
		});
		
	};  --%>
  </script>
<script type="text/javascript">

//start new 
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
window.document.getElementById("reqNo2").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
window.document.getElementById("area").innerHTML = window.opener.window.document.getElementById("area").innerHTML;
window.document.getElementById("subarea").innerHTML = window.opener.window.document.getElementById("subarea").innerHTML;
if(window.opener.window.document.getElementById("orderSource") != null){
	jQuery('#orderSource1').show();
	window.document.getElementById("orderSource").innerHTML = window.opener.window.document.getElementById("orderSource").innerHTML;
}else{
	jQuery('#orderSource1').remove();
}
window.document.getElementById("status").innerHTML = window.opener.window.document.getElementById("serviceRequestStatus").innerHTML;
window.document.getElementById("accountName").innerHTML = window.opener.window.document.getElementById("accountName").innerHTML;
window.document.getElementById("requestorName").innerHTML = window.opener.window.document.getElementById("requestorName").innerHTML;
if(window.opener.window.document.getElementById("beforeTax") != null){
	window.document.getElementById("beforeTax").innerHTML = window.opener.window.document.getElementById("beforeTax").innerHTML;
}
if(window.opener.window.document.getElementById("tax")!= null){
	window.document.getElementById("tax").innerHTML = window.opener.window.document.getElementById("tax").innerHTML;
}
if(window.opener.window.document.getElementById("total") != null){
	window.document.getElementById("total").innerHTML = window.opener.window.document.getElementById("total").innerHTML;
}

window.document.getElementById("primaryName").innerHTML = window.opener.window.document.getElementById("primaryname").innerHTML;
window.document.getElementById("primaryPhone").innerHTML = window.opener.window.document.getElementById("primaryphone").innerHTML;
window.document.getElementById("primaryEmail").innerHTML = window.opener.window.document.getElementById("primaryemail").innerHTML;
window.document.getElementById("custRefNo").innerHTML = window.opener.window.document.getElementById("customerReferenceNumber").innerHTML;
window.document.getElementById("costCntr").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("addDesc").innerHTML = window.opener.window.document.getElementById("addtnlDescription").innerHTML;
if(window.opener.window.document.getElementById("secondarySection") !=null){
	jQuery('#secondarySection').show();
	window.document.getElementById("secondaryName").innerHTML = window.opener.window.document.getElementById("secondaryname").innerHTML;
	window.document.getElementById("secondaryPhone").innerHTML = window.opener.window.document.getElementById("secondaryphone").innerHTML;
	window.document.getElementById("secondaryEmail").innerHTML = window.opener.window.document.getElementById("secondaryemail").innerHTML;
}else{
	jQuery('#secondarySection').remove();
}
window.document.getElementById("shipToAddress").innerHTML = window.opener.window.document.getElementById("shipToAddress").innerHTML;
window.document.getElementById("building").innerHTML = window.opener.window.document.getElementById("building").innerHTML;
window.document.getElementById("floor").innerHTML = window.opener.window.document.getElementById("floor").innerHTML;
window.document.getElementById("office").innerHTML = window.opener.window.document.getElementById("office").innerHTML;
window.document.getElementById("specialInst").innerHTML = window.opener.window.document.getElementById("specialInstructions").innerHTML;
if(window.opener.window.document.getElementById("paymentBlock") !=null){
	jQuery('#paymentBlock').show();
	if(window.opener.window.document.getElementById("poNumber") !=null){
		jQuery('#poNumber1').show();
		window.document.getElementById("poNumber").innerHTML = window.opener.window.document.getElementById("poNumber").innerHTML;
		}else{
			jQuery('#poNumber1').remove();	
			}
	if(window.opener.window.document.getElementById("creditCard") !=null){
		jQuery('#creditCard1').show();
		window.document.getElementById("creditCard").innerHTML = window.opener.window.document.getElementById("creditCard").innerHTML;
		}else{
			jQuery('#creditCard1').remove();
			}
}else{
	jQuery('#paymentBlock').remove();
}
window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;
//end new
window.document.getElementById("tab_statusGrid").innerHTML = window.opener.window.document.getElementById("tab_statusGrid").innerHTML;
window.document.getElementById("creationTime").innerHTML = window.opener.window.document.getElementById("creationTime").innerHTML;
window.document.getElementById("associatedGridID").innerHTML = window.opener.window.document.getElementById("associatedGridID").innerHTML;
minimize();
</script>
<!-- Aui Popup Added -->
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