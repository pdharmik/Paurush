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
 

<table width="953px" border="0" cellspacing="0" cellpadding="0" align="center" style="border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">
	<tr>
    	<td style="background-color:#dadada"><img src="<html:imagesPath/>/logoLexEmail.png" width="100%" height="65" /></td>
  	</tr>
	<br></br>
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><spring:message code="Details.changeRequestDetails.heading.changeReqDeails"/> </strong> &raquo; <strong><span class="step"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/> <span id="reqNumber"></span></span></strong></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.requestConfirmation"/></strong></td>
  </tr>
  <tr>
    <td valign="top" style="padding:10px;font-family:Arial, Helvetica, sans-serif; font-size:11px">
      <tr>
    <td valign="top" style="padding:10px;font-family:Arial, Helvetica, sans-serif; font-size:11px">  <spring:message code="requestInfo.message.confirmMessage1"/>   
		<span id="reqNo2"></span> <spring:message code="requestInfo.message.confirmMessage2"/>
		</td>
  </tr></td>
  </tr>
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.area"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="area"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.subArea"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="subarea"></td>
              </tr>
              
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="Details.changeRequestDetails.label.status"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="status"></td>
              </tr>
               
            </table>
            </td>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="3">
              
             <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="Details.changeRequestDetails.label.dateAndTime"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="creationTime"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="Details.changeRequestDetails.label.requesterName"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="requestorName"></td>
              </tr>
             
            </table>
            </td>
        </tr>
      
      </table>
      <br>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<br>
            <tr>
            <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="5" cellpadding="0">
          <tr><td width="200">
             <div class="icon"><img class="ui_icon_sprite widgets inProcess-icon" src="<html:imagesPath/>transparent.png" width="100" height="100" /></div></td>
             <td class="status">
              
               <table><tr><td style="border-left: 0px solid #000000!important; border-right: 0px solid #000000!important">
                <div id="div_progressBar" style="border-left: 0px solid #000000!important; border-right: 0px solid #000000!important"><!-- PROGRESS BAR WILL COME HERE --></div>
                </td></tr></table>
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><spring:message code="Details.changeRequestDetails.process.submitted"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.inProgress"/></td>
                    <td><spring:message code="Details.changeRequestDetails.process.Complete"/></td>
                  </tr>
                </table>
                
              </td></tr>
              </table></td>
            </tr>
             <tr>
             <td>
             &nbsp;
             </td>
             </tr>
            <tr>
            <td>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="Details.supplyRequestDetails.heading.requestStatusHistory"/></th>
              </tr>
          <tr><td>
        <div style=" width : 75%" id="tab_statusGrid"></div></td>
        </tr>
            </table></td>
        </tr>

      </table>
      </td>
      </tr>
      <br>

      </table> 
      <br>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	
		

      </table>
	  	 <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="primaryName"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="primaryPhone"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="primaryEmail"></td>
              </tr>
            </table></td>
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="custRefNo"></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="costCntr"></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="addDesc"></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.dateOfChange"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="effDateChange"></td>
              </tr>
            
            </table></td>
        </tr>
        <tr id="secondarySection" style="display: none;">
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="secondaryName"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="secondaryPhone"></td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="secondaryEmail"></td>
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
         
	
        <br>
        <tr>
        <td>
        <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
         <td width="20%">
       			<div id="changeImage">	</div>
       			<div id="productTLIold">	</div>
        </td>
          <td width="40%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.cm.manageAsset.heading.selectedAssetDetails" /></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.serialNumber" /></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="serialNumber"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.productName" /></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="productLine"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.installDate" /> </strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="installDate"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.ipAddress" /> </strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="ipAddress"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.hostName" /> </strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="hostName"></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.customerAssetTag" /> </strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="deviceTag"></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.cm.manageAsset.heading.billingDetails" /></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.assetCostCenter" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="assetCostCenter"></td>
              </tr>
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.customerHierarchy" /></th>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.cm.manageAsset.label.assetHierercyLevel" /></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="chlNodeValue"></td>
              </tr>
            </table></td>
        </tr>
      </table>
        </td>
        </tr>
        <tr>
  	<td>
        <div id="activityBlock"></div>  
        </td>
	  </tr>
        <tr>
  	<td>
        <div id="pageCountsSection"></div>  
        </td>
	  </tr>
	<tr>
  	<td>
	<div id="addressSection"></div>  
	  
	  </td>
	  </tr>
	  <tr>
  	<td>
	<div id="deviceContactSection"></div>  
	  
	  </td>
	  </tr>
	  
     <tr>
  	<td>
      	<table width="100%" border="0" cellspacing="5" cellpadding="0" id="paymentBlock" style="display: none;">
	
		<tr>
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.paymentDetails"/></th>
              </tr>
              <tr id="poNumber1" style="display: none;">
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong>
    			<spring:message code="requestInfo.label.poNumber"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="poNumber"></td>
              </tr>
               <tr id="creditCard1" style="display: none;">
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong>
    			<spring:message code="requestInfo.heading.credit"/>:</strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="creditCard"></td>
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
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.notesAndAttachments"/></strong></td>
  </tr>
		<tr>
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.notes"/></th>
              </tr>
              <tr> <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;" id="notes"></td>
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
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachments"/></th>
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
                <img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
                <span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
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

if(window.opener.window.document.getElementById("tab_inprocessGrid") != null){
window.document.getElementById("tab_inprocessGrid").innerHTML = window.opener.window.document.getElementById("tab_inprocessGrid").innerHTML;
}
if(window.opener.window.document.getElementById("suppliesShipment") != null){
	window.document.getElementById("suppliesShipment").innerHTML = window.opener.window.document.getElementById("suppliesShipment").innerHTML;
}
window.document.getElementById("showAttachment").innerHTML = window.opener.window.document.getElementById("showAttachment").innerHTML;

	if(window.opener.window.document.getElementById("cancelBlockID")!=null){
		window.document.getElementById("cancelBlockID").innerHTML = window.opener.window.document.getElementById("cancelBlockID").innerHTML;
	}

	<%-- Comented for adding aui popup
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

	};   --%>
</script>
<script type="text/javascript">
//start new 
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
window.document.getElementById("reqNo2").innerHTML = window.opener.window.document.getElementById("reqNo").innerHTML;
window.document.getElementById("area").innerHTML = window.opener.window.document.getElementById("area").innerHTML;
window.document.getElementById("subarea").innerHTML = window.opener.window.document.getElementById("subarea").innerHTML;

window.document.getElementById("status").innerHTML = window.opener.window.document.getElementById("serviceRequestStatus").innerHTML;
window.document.getElementById("creationTime").innerHTML = window.opener.window.document.getElementById("creationTime").innerHTML;
window.document.getElementById("requestorName").innerHTML = window.opener.window.document.getElementById("requestorName").innerHTML;
window.document.getElementById("div_progressBar").innerHTML = window.opener.window.document.getElementById("div_progressBar").innerHTML;
window.document.getElementById("tab_statusGrid").innerHTML = window.opener.window.document.getElementById("tab_statusGrid").innerHTML;

window.document.getElementById("primaryName").innerHTML = window.opener.window.document.getElementById("primaryCont_name").innerHTML;
window.document.getElementById("primaryPhone").innerHTML = window.opener.window.document.getElementById("primaryCont_phone").innerHTML;
window.document.getElementById("primaryEmail").innerHTML = window.opener.window.document.getElementById("primaryCont_email").innerHTML;
window.document.getElementById("custRefNo").innerHTML = window.opener.window.document.getElementById("addtnlInfo_customerRefId").innerHTML;
window.document.getElementById("costCntr").innerHTML = window.opener.window.document.getElementById("costCenter").innerHTML;
window.document.getElementById("addDesc").innerHTML = window.opener.window.document.getElementById("description").innerHTML;
window.document.getElementById("effDateChange").innerHTML = window.opener.window.document.getElementById("effDateChange").innerHTML;
if(window.opener.window.document.getElementById("secondarySection") !=null){
	jQuery('#secondarySection').show();
	window.document.getElementById("secondaryName").innerHTML = window.opener.window.document.getElementById("secondaryCont_name").innerHTML;
	window.document.getElementById("secondaryPhone").innerHTML = window.opener.window.document.getElementById("secondaryCont_phone").innerHTML;
	window.document.getElementById("secondaryEmail").innerHTML = window.opener.window.document.getElementById("secondaryCont_email").innerHTML;
}else{
	jQuery('#secondarySection').remove();
}
</script>
<script type="text/javascript">
if(window.opener.window.document.getElementById("pageCountsSection") !=null){
window.document.getElementById("pageCountsSection").innerHTML = window.opener.window.document.getElementById("pageCountsSection").innerHTML;
}
window.document.getElementById("addressSection").innerHTML = window.opener.window.document.getElementById("addressSection").innerHTML;

window.document.getElementById("notes").innerHTML = window.opener.window.document.getElementById("notes").innerHTML;
</script>
<script type="text/javascript">
window.document.getElementById("serialNumber").innerHTML = window.opener.window.document.getElementById("serialNumber").innerHTML;
window.document.getElementById("productLine").innerHTML = window.opener.window.document.getElementById("productLine").innerHTML;
window.document.getElementById("installDate").innerHTML = window.opener.window.document.getElementById("installDate").innerHTML;
window.document.getElementById("ipAddress").innerHTML = window.opener.window.document.getElementById("ipAddress").innerHTML;
window.document.getElementById("hostName").innerHTML = window.opener.window.document.getElementById("hostName").innerHTML;
window.document.getElementById("deviceTag").innerHTML = window.opener.window.document.getElementById("deviceTag").innerHTML;
window.document.getElementById("assetCostCenter").innerHTML = window.opener.window.document.getElementById("assetCostCenter").innerHTML;
if(window.opener.window.document.getElementById("deviceContactSection") != null){
window.document.getElementById("deviceContactSection").innerHTML = window.opener.window.document.getElementById("deviceContactSection").innerHTML;
}
if(window.opener.window.document.getElementById("activityBlock")!=null){
window.document.getElementById("activityBlock").innerHTML = window.opener.window.document.getElementById("activityBlock").innerHTML;
}
if(window.opener.window.document.getElementById("chlNodeValue")!=null){
window.document.getElementById("chlNodeValue").innerHTML = window.opener.window.document.getElementById("chlNodeValue").innerHTML;
}
</script>
<script type="text/javascript">
window.document.getElementById("changeImage").innerHTML = "<img src='" + window.opener.window.document.getElementById('changeImage').src + "' width='100' height='100'/>";
window.document.getElementById("productTLIold").innerHTML = window.opener.window.document.getElementById("productTLIold").innerHTML;

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

</script>
 --%>
 
 
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