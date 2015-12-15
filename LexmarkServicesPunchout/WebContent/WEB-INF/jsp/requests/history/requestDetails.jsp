<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="wrapper-popup">
<div id="requestDetails" style="max-height: 500px; overflow-y: auto">
    <div class="journal-content">
      <h1><spring:message code="changemanagement.assetSummary.heading.requestDetails"/></h1>
      <h2 class="stepContent"><spring:message code="Details.supplyRequestDetails.label.rquestNumber"/><span id="SupplyRqstNumber">${requestDetails.serviceRequest.serviceRequestNumber}</span></h2>
       </div>
    <div class="popup-table-cntnr">
      <table width="460" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-black-txt"><spring:message code="changemanagement.common.label.area"/></td>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-gray-txt">${requestDetails.serviceRequest.area.value}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt"><spring:message code="changemanagement.common.label.subArea"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt">${requestDetails.serviceRequest.subArea.value}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-black-txt"><spring:message code="Details.supplyRequestDetails.label.orderSource"/></td>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-gray-txt">${requestDetails.serviceRequest.orderSource}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt"><spring:message code="customerInvoicePayments.label.status"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt">${requestDetails.serviceRequest.serviceRequestStatus}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-black-txt" style="border-bottom:none;"><spring:message code="Details.changeRequestDetails.label.accountName"/></td>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.accountName}</td>
        </tr>
      </table>
    </div>
    <div class="popup-table-cntnr" style="margin-right:0;">
      <table width="460" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-black-txt"><spring:message code="Details.supplyRequestDetails.label.dateTimeCreated"/></td>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-gray-txt">${requestDetails.serviceRequest.serviceRequestDate}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt"><spring:message code="Details.supplyRequestDetails.label.requestorName"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt">${requestDetails.serviceRequest.requestor.firstName} ${requestDetails.serviceRequest.requestor.lastName }</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-black-txt"><spring:message code="Details.changeRequestDetails.label.itemSubTotalBeforeTax"/></td>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-gray-txt">${requestDetails.serviceRequest.itemSubTotalBeforeTax}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt"><spring:message code="Details.changeRequestDetails.label.total"/>Total</td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt">${requestDetails.serviceRequest.totalAmount}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-black-txt" style="border-bottom:none;"></td>
          <td height="25" align="left" valign="middle" bgcolor="#eff0f6" class="popup-gray-txt" style="border-bottom:none;"></td>
        </tr>
      </table>
    </div>
    <div style="float: left;">
    <div class="popup-cntct-ship-cntnr">
      <div class="popup-title-black h4"><spring:message code="changemanagement.common.heading.primaryContactForThisRequest"/></div>
      <table width="440" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px; float:left;">
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="commonContactInfo.label.name"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.primaryContact.firstName}&nbsp;${requestDetails.serviceRequest.primaryContact.lastName}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="commonContactInfo.label.phone"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.primaryContact.workPhone}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="commonContactInfo.label.emailAddress"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.primaryContact.emailAddress}</td>
        </tr>
        
      </table>
    </div>
        <div class="popup-cntct-ship-cntnr">
      <div class="popup-title-black h4"><spring:message code="changemanagement.common.heading.additionalInformationForThisRequest"/></div>
      <table width="440" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px; float:left;">
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="changemanagement.common.label.customerReferenceid"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.customerReferenceId}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="changemanagement.commonAddress.label.costCenter"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.costCenter}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="changemanagement.commonAddress.label.description"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.addtnlDescription}</td>
        </tr>
      </table>
    </div>
 </div>
 <div style="float: left;">
 <div class="popup-cntct-ship-cntnr">
  <div class="popup-title-black h4"><spring:message code="orderSupplies.placeOrder.assetInformation"/></div>
  <table class="assetDetail" width="100%" cellspacing="0" cellpadding="0" border="0">
  <tbody>
<tr>
<td class="pModel">
<ul>
                  <li class="center"><img width="100" height="100" onerror="image_error();" id="MyPicture_Popup" src="/LexmarkServicesPunchout/images/printer_na_color.png">                  
                  </li><li class="pModelName">${requestDetails.serviceRequest.productModel}</li></ul>
</td>
<td class="pDetail">
      <table width="440" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px; float:left;">
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.assetInfo.listHeader.serialNumber"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.asset.serialNumber}</td>
        </tr>
       <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.assetInfo.listHeader.productName"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.asset.productTLI}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="deviceDetail.label.installDate"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.asset.installDate}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.assetInfo.listHeader.ipAddress"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.asset.ipAddress}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="serviceRequest.listHeader.hostName"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.asset.hostName}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="deviceDetail.label.customerAssetTag"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.asset.assetTag}</td>
        </tr>
      </table>
      </td>
      </tr>
      </tbody>
      </table>
    </div>
 
 <div class="popup-cntct-ship-cntnr">
 <div class="popup-title-black h4"><spring:message code="requestInfo.heading.serviceAddress"/></div>
      <table width="440" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px; float:left;">
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.addressInfo.listHeader.pstoreFrontName"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.serviceAddress.storeFrontName}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.addressInfo.listHeader.addressLine1"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.serviceAddress.addressLine1}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.addressInfo.listHeader.stateProvince"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.serviceAddress.city}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"><spring:message code="requestInfo.addressInfo.listHeader.postalCode"/></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;">${requestDetails.serviceRequest.serviceAddress.postalCode}</td>
        </tr>
        <tr>
          <td height="25" align="left" valign="middle" class="popup-black-txt" style="border-bottom:none;"></td>
          <td height="25" align="left" valign="middle" class="popup-gray-txt" style="border-bottom:none;"></td>
        </tr>
      </table>
 </div>
 </div>
    <div class="popup-cntct-ship-cntnr">
      <div class="popup-title-black h4"><spring:message code="Details.supplyRequestDetails.label.shipToAddress"/></div>
      <table width="440" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px; float:left;">
        <ul class="roDisplay">
                <li id="shipToAddress"><div>${requestDetails.serviceRequest.serviceAddress.storeFrontName}</div>
                  <util:addressOutput value="${requestDetails.serviceRequest.serviceAddress}"></util:addressOutput>
                  </li>
                <li><label for="building"><spring:message code="requestInfo.addressInfo.label.building"/> </label><span id="building"> ${requestDetails.serviceRequest.serviceAddress.physicalLocation1}</span></li>
                <li><label for="floor"><spring:message code="requestInfo.addressInfo.label.floor"/> </label><span id="floor"> ${requestDetails.serviceRequest.serviceAddress.physicalLocation2}</span></li>
                <li><label for="office"><spring:message code="requestInfo.addressInfo.label.office"/>  </label><span id="office"> ${requestDetails.serviceRequest.serviceAddress.physicalLocation3}</span></li>
              </ul>
      </table>
    </div>
   
    <div class="popup-cntct-ship-cntnr" style="width: 96%">
     <div class="h4" id="popup-title" style="width:98%;"><spring:message code="Details.supplyRequestDetails.heading.pendingshipment"/></div>
     <table class="assetDetail" width="100%" cellspacing="0" cellpadding="0" border="0">
     <tbody>
     <tr>
     <td style="width: 10%; padding-right: 10px;">
    <div class="request-img"><img src="<html:imagesPath/>icon-image.jpg" width="84" height="70" alt="Request Items Image"></div>
    </td>
    <td style="width: 90%">
  <div class="columnInner separatorV">
                         <div id="div_inprocessPrintTable">
							<div id="tab_inprocessGrid"  style="display:block;">
								<div id="div_inprocessGridContainer" style="width:100%;"></div>
	  							<div class="pagination"><span id="pagingAreaPopup"></span><span id="infoAreaPopup"></span></div>
	  						</div>
	    				</div><!-- mygrid_container -->
	              	 	</div>
    </td>
    </tr>
    </tbody>
    </table>
    </div>
    <div class="popup-shipment-cntnr" style="width: 96%">
    <div id="popup-title" class="h4" style="width: 98%"><spring:message code="serviceRequest.label.shipment"/><a href="#">XXXXXXXXXXXX</a></div>
    <table class="assetDetail" width="100%" cellspacing="0" cellpadding="0" border="0">
     <tbody>
     <tr>
     <td style="width: 10%; padding-right: 10px;">
    <div class="request-img"><img src="<html:imagesPath/>icon-image.jpg" width="84" height="70" alt="Request Items Image"></div>
    </td>
    <td style="width: 90%;">
    <table width="88%" border="0" cellspacing="0" cellpadding="0" style="border-bottom:1px dashed #c0c0c0;">
  <tr>
    <td width="450" height="25" align="center" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="450" height="25" align="left" valign="bottom"><img src="<html:imagesPath/>processbar.jpg" width="428" height="13" alt="Progress Bar" /></td>
      </tr>
      <tr>
        <td><table width="95%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="top" class="table-txt"><spring:message code="serviceRequest.label.inProcess"/></td>
            <td align="center" valign="top" class="table-txt"><spring:message code="serviceRequest.label.shipped"/></td>
            <td align="center" valign="top" class="table-txt"><spring:message code="serviceRequest.label.delivered"/></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="450" align="center" valign="top"><table width="90%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="table-txt"><spring:message code="serviceRequest.label.carrier"/></td>
        <td class="table-txt"></td>
        </tr>
      <tr>
        <td class="table-txt"><spring:message code="serviceRequest.label.shippedDate"/></td>
        <td class="table-txt"></td>
        </tr>
      <tr>
        <td class="table-txt"><spring:message code="Details.supplyRequestDetails.label.actualDeliveryDate"/></td>
        <td align="right" class="table-txt"></td>
        </tr>
    </table></td>
  </tr>
  </table>
</td></tr></tbody></table>
    </div>
  </div>
  </div>
  
  
	<script>
			SRInProgressGrid = new dhtmlXGridObject('div_inprocessGridContainer');
			SRInProgressGrid.setImagePath("<html:imagesPath/>gridImgs/");
			SRInProgressGrid.setHeader("<spring:message code='serviceRequest.listHeader.pendingshipment'/>");
			SRInProgressGrid.setColAlign("left,left,left,left,left,right");
			SRInProgressGrid.setColTypes("ro,ro,ro,ro,ro,ro");
			SRInProgressGrid.setInitWidths("140,200,170,110,100,120");
			SRInProgressGrid.enableAutoWidth(true);
			SRInProgressGrid.enableMultiline(true);
			SRInProgressGrid.enableAutoHeight(true);
			SRInProgressGrid.init(); 
			SRInProgressGrid.prftInit();
			SRInProgressGrid.enablePaging(true,15, 2, "pagingAreaPopup", true, "infoAreaPopup");
			SRInProgressGrid.setPagingSkin("bricks");
			SRInProgressGrid.setSkin("light");
			SRInProgressGrid.setColumnHidden(4,true);
			SRInProgressGrid.loadXMLString('${requestDetails.pendingRequest.shipmentXML}'); 
	</script>