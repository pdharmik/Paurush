<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_drag.js"></script>
<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
 

<table width="800" border="0" cellspacing="0" cellpadding="0" align="center" style="border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">
	<tr>
    	<td style="background-color:#dadada"><img src="<html:imagesPath/>/logoLexEmail.png" width="598" height="65" /></td>
  	</tr>
	<br></br>
	
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff"><strong><spring:message code="requestInfo.heading.suppliesRequests"/> </strong> &raquo; <strong><spring:message code="requestInfo.popup.label.orderSupplies"/>   </strong></td>
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
		<span id="reqNumber"> </span>  <spring:message code="requestInfo.message.confirmMessage2"/>
		</td>
  </tr></td>
  </tr>
  <tr>
    <td><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.primaryContact.firstName} &nbsp; 
                    ${assetDetailPageForm.serviceRequest.primaryContact.lastName}</td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.primaryContact.workPhone}</td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.primaryContact.emailAddress}</td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.customerReferenceId}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.costCenter}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.addtnlDescription}</td>
              </tr>
            
            </table></td>
        </tr>
        <c:if test='${assetDetailPageForm.serviceRequest.secondaryContact.firstName ne ""}'>
        <tr>
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><strong><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></strong></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.secondaryContact.firstName } &nbsp;
					${assetDetailPageForm.serviceRequest.secondaryContact.lastName }</td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.secondaryContact.workPhone }</td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.serviceRequest.secondaryContact.emailAddress }</td>
              </tr>
            </table></td>
        </tr>
        </c:if>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
     
	
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
	    	  <tr>
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.selectedAsset"/></strong></td>
  </tr>
        <tr>
        
          <td width="20%">
       			<div id="orderImage">	</div>
       			<div id="imageNo">	</div>
        
        </td>
          <td width="40%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.assetInformation"/></th>
              </tr>
              <tr>
                <td width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.serialNumber"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.serialNumber}</td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.productName"/></strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.productTLI}</td>
              </tr>
              <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.installDate"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.installDate}</td>
              </tr>
			   <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.ipAddress"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.ipAddress}</td>
              </tr>
			   <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.hostName"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.hostName}</td>
              </tr>
			   <tr>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/> </strong></td>
                <td style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.deviceTag}</td>
              </tr>
            </table></td>
			<td width="40%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.serviceAddress"/></th>
              </tr>
             <tr><td colspan="2" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
            		<div>${assetDetailPageForm.serviceAddress.storeFrontName}</div>
            		<util:addressOutput value="${assetDetailPageForm.serviceAddress}"></util:addressOutput>
													                  <%-- <div>${assetDetailPageForm.serviceAddress.addressLine1}</div>
													                  <div>${assetDetailPageForm.serviceAddress.officeNumber}</div>
													                  <c:if test="${assetDetailPageForm.serviceAddress.addressLine2 !='' && assetDetailPageForm.serviceAddress.addressLine2!=null}">
													                  <div>${assetDetailPageForm.serviceAddress.addressLine2}, </div>
													                  </c:if>
													                  <div>${assetDetailPageForm.serviceAddress.addressLine3}</div>
													                  <span>${assetDetailPageForm.serviceAddress.city},
													                  <c:if test="${assetDetailPageForm.serviceAddress.county != '' && assetDetailPageForm.serviceAddress.county!=null}">
													                  ${requestForm.serviceRequest.serviceAddress.county},
													                  </c:if>
													                  <c:if test="${assetDetailPageForm.serviceAddress.state != '' && assetDetailPageForm.serviceAddress.state != null}">
													                  ${assetDetailPageForm.serviceAddress.state}
													                  </c:if>
													                  <c:if test="${assetDetailPageForm.serviceAddress.province !='' && assetDetailPageForm.serviceAddress.province != null}">
													                  , ${requestForm.serviceRequest.serviceAddress.province}
													                  </c:if>
													                  region changed to district for MPS 2.1 
													                  <c:if test="${assetDetailPageForm.serviceAddress.district !='' && assetDetailPageForm.serviceAddress.district != null}">
													                  , ${assetDetailPageForm.serviceAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${assetDetailPageForm.serviceAddress.postalCode}</div> 
													                  <div>${assetDetailPageForm.serviceAddress.country}</div>--%>
				    </td>
				 </tr>
				 <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.building"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.physicalLocationAddress.physicalLocation1}</td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.floor"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.physicalLocationAddress.physicalLocation2}</td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.office"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.physicalLocationAddress.physicalLocation3}</td>
              </tr>
				                        
            </table></td>
         
		 
        </tr>
        
      </table>
	  	<table width="100%" border="0" cellspacing="5" cellpadding="0">
          
           
           <c:forEach items="${reviewAssetOrderForm.pageCountsList}" var="assetPageCountsList" varStatus="counter" begin="0">
		  <c:choose>
		  <c:when test="${counter.index == 0}">
		  <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.pageCount"/></th>
              </tr>
              	</c:when>
              	<c:otherwise>
              	</c:otherwise>
              	</c:choose>
			 
		<tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
           
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong>${assetPageCountsList.name}:</strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPageCountsList.count}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.dateAndTime"/>:</strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPageCountsList.date}</td>
              </tr>
              
            </table></td>
          
        </tr>
       </c:forEach>
      </table>
       </td></tr>
      
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
       <c:choose>
       			<c:when test='${assetDetailPageForm.asset.installationOnlyFlag == "true" }'>
							<td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
							 <table width="100%" border="0" cellspacing="0" cellpadding="5">
							<tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.label.partsToBeInstalled"/></th>
              </tr>
					<tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsToBeInstalled"/>:</strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.asset.partsToBeInstalled}</td>
              </tr>	
			</table>
									
									
							
			
				</c:when>
				<c:otherwise>
				
				
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <thead>
                <tr>
                  <th align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.listOfParts"/></th>
                </tr>
              </thead>
              <tr>
              <td>
              <table>
                <tr>
	                  <th width="100" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
	                  	<spring:message code="requestInfo.heading.partNumber"/>
	                  </th>
					  <th align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
					   	<spring:message code="requestInfo.heading.description"/>
					  </th>
					  <th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
					  	<spring:message code="requestInfo.heading.partType"/>
					  </th>
					  <th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
						 <spring:message code="requestInfo.heading.orderQuantity"/>
					  </th>
					  <c:if test="${assetDetailPageForm.finalShowPriceFlag}">
					  <th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
						 <spring:message code="requestInfo.heading.unitPrice"/>
					  </th>
					  <th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
						 <spring:message code="requestInfo.heading.partTotal"/>
					  </th>
					  </c:if>
                </tr>
                <c:forEach items="${reviewAssetOrderForm.assetPartList}" var="assetPartListDetail" varStatus="counter" begin="0">
                <c:if test="${assetPartListDetail.orderQuantity ne '' && assetPartListDetail.orderQuantity ne '0'}">
                <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.partNumber}</td>
					<td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.description}</td>
					<td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.partType}</td>
					<td  valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.orderQuantity}</td>
					<td  valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.unitPrice}</td>
					<td  valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.totalPrice}</td>
                </tr>
                </c:if>
                </c:forEach>
                </table>
                </td>
              </tr>
              
            </table></td>
        
				
				
				</c:otherwise>
       </c:choose>
	 </tr>
	
	
		<tr>
  	<td>
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
              	
			<c:if test="${assetDetailPageForm.finalTaxCalcFlag}"> 
		<tr>
          <td width="50%" valign="top" style="border-color:#cccccc ;float:right;">
		  <table width="50%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsPriceSubTotal"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.subTotalPrice}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsTaxes"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.totalTax}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsTotalAmountPayable"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.totalPrice}</td>
              </tr>
              
            </table></td>
          
        </tr>
        </c:if>
      </table>
      </td>
      </tr>
      <tr>
		<td>&nbsp;</td>
		</tr>
	<tr>
  	<td>
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
	
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.shippingInformation"/></th>
              </tr>
           		<tr> 
           			<td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
           				<div>${assetDetailPageForm.shipToAddress.storeFrontName}</div>
           				<util:addressOutput value="${assetDetailPageForm.shipToAddress}"></util:addressOutput>
													         <%--         <div>${assetDetailPageForm.shipToAddress.addressLine1}</div>
													                  <div>${assetDetailPageForm.shipToAddress.officeNumber}</div>
													                  <c:if test="${assetDetailPageForm.shipToAddress.addressLine2 !='' && assetDetailPageForm.shipToAddress.addressLine2!=null}">
													                  <div>${assetDetailPageForm.shipToAddress.addressLine2}, </div>
													                  </c:if>
													                  <div>${assetDetailPageForm.shipToAddress.addressLine3}</div>
													                  <span>${assetDetailPageForm.shipToAddress.city},
													                  <c:if test="${assetDetailPageForm.shipToAddress.county != '' && assetDetailPageForm.shipToAddress.county!=null}">
													                  ${assetDetailPageForm.shipToAddress.county},
													                  </c:if>
													                  <c:if test="${assetDetailPageForm.shipToAddress.state != '' && assetDetailPageForm.shipToAddress.state != null}">
													                  ${assetDetailPageForm.shipToAddress.stateCode}
													                  </c:if>
													                  <c:if test="${(assetDetailPageForm.shipToAddress.province !='' && assetDetailPageForm.shipToAddress.province !=' ') && assetDetailPageForm.shipToAddress.province != null}">
													                  ,${assetDetailPageForm.shipToAddress.province}
													                  </c:if>
													                   region changed to district for MPS 2.1 
													                 <c:if test="${(assetDetailPageForm.shipToAddress.district !='' && assetDetailPageForm.shipToAddress.district !=' ') && assetDetailPageForm.shipToAddress.district != null}">
													                  ,${assetDetailPageForm.shipToAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${assetDetailPageForm.shipToAddress.postalCode}</div> 
													                  <div>${assetDetailPageForm.shipToAddress.country}</div>--%>
					</td>
				</tr>
				
				    	 <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.building"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.shipToAddress.physicalLocation1}</td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.floor"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.shipToAddress.physicalLocation2}</td>
              </tr>
               <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.office"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.shipToAddress.physicalLocation3}</td>
              </tr>              
            </table></td>
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.orderInstructions"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.specialInstructions"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.specialInstruction}</td>
              </tr>
			   <%-- <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.defaultSpecialInstructions"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.defaultSpecialInstruction}</td>
              </tr> --%>
              <c:if test='${assetDetailPageForm.expediteOrderAllowed == "true" }'>
			   <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.requestExpediteOrder"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
                <c:choose>
				  <c:when test="${assetDetailPageForm.requestExpediteOrder eq \"true\"}">
				  	<spring:message code="requestInfo.option.yes"/>
				  </c:when>
				  <c:otherwise>
				  	<spring:message code="requestInfo.option.no"/>
				  </c:otherwise>
				</c:choose>
               </td>
              </tr>
              </c:if>
			   <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.requestedDeliveryDate"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetDetailPageForm.requestedDeliveryDate}</td>
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
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.paymentDetails"/></th>
              </tr>
           
              <tr id="poNumberEmail">
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong>
    <spring:message code="requestInfo.label.poNumber"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
    ${assetDetailPageForm.asset.poNumber}</td>
              </tr>
              
               <tr id="creditCardEmail">
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong>
    <spring:message code="requestInfo.heading.credit"/>:</strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
    ${assetDetailPageForm.creditCardToken}</td>
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
              <tr> <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
         		   ${assetDetailPageForm.attachmentDescription}
            </td></tr>
            </table></td>
        </tr>
      </table>
	 </td>
	 </tr>
	 
	 <tr>
  	<td>
	 		<table width="50%" border="0" cellspacing="5" cellpadding="0">
	
		<tr>
   <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
             <c:if test="${assetDetailPageForm.displayAttachmentList !=null && not empty assetDetailPageForm.displayAttachmentList}">
              <tr>
                <th colspan="" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachmentFileName"/></th>
                <th colspan="" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th>
              </tr>
              </c:if>
              <c:forEach items="${assetDetailPageForm.displayAttachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
					<c:choose>
						<c:when test="${counter.count % 2 == 0}">
						   <tr valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
						</c:when>
						<c:otherwise>
						   <tr valign="top" bgcolor="#efefef" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
						</c:otherwise>
					</c:choose>
							<td width="220px">${attachmentListDetail.fileName}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.fileSize div 1024}"/></td>
						</tr>
					</c:forEach>
              
              
            </table></td>
            <tr>
              	<td><div id="attachmentFailedEmail" class ="error"  style="display: none;"><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></div></td>
              </tr>
        </tr>
      </table>
      
      </td>
  </tr><br></br>
  <br></br><br></br>
</table>
<br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
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
<script type="text/javascript">

window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;
window.document.getElementById("orderImage").innerHTML = "<img src='" + window.opener.window.document.getElementById('orderImage').src + "' width='100' height='100'/>";
window.document.getElementById("imageNo").innerHTML = window.opener.window.document.getElementById("imageNo").innerHTML;
if(window.opener.window.document.getElementById("attachmentHidden").innerHTML=='attachfailed'){
	jQuery('#attachmentFailedEmail').show();
}
else{
	jQuery('#attachmentFailedEmail').hide();
}

if(window.opener.window.document.getElementById("poNumber")!= null){
	jQuery('#poNumberEmail').show();
}
else{
	jQuery('#poNumberEmail').hide();
}
if(window.opener.window.document.getElementById("creditCard")!= null){
	jQuery('#creditCardEmail').show();
}
else{
	jQuery('#creditCardEmail').hide();
}

function doEmail(){
	
	//alert('typeofflow='+${typeOfFlow});
	
};
<%-- commented for adding AUI popup
function email(){
	if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
	//alert('typeofflow='+'${typeOfFlow}'');
	//doEmail();
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

	};  --%>
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
	if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
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
	if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
	jQuery(window).scrollTop(0);
	emailPopUpWindow.show();
	jQuery(".aui button.close, .aui button.btn.close").hide();
	emailPopUpWindow.titleNode.html("<spring:message code='requestInfo.title.emailConfirm'/>");
	emailPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='emailConfirmationPage' /></portlet:renderURL>");
	emailPopUpWindow.io.start();
	
	}

</script>