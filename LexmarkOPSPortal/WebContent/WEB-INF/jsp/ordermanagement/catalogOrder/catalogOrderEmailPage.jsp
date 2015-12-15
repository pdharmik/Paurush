<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->

<style type="text/css" >
	td {padding:0 5px 0 5px;}
	th {padding:0 5px 0 5px;}
</style>
<body>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center" style="border-width:1px; border-color:#3A7FA8; border-style:solid; background-color:#ffffff" id="emailPrintWraper">
  <tr>
    <td style="background-color:#dadada"><img src="<html:imagesPath/>/logoLexEmail.png" width="598" height="65" /></td>
  </tr>
  <tr>
    <td style="background-color:#3A7FA8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="130" height="20" style="padding-top:3px; padding-bottom:3px; padding-left:10px; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#ffffff">
          <strong><spring:message code="requestInfo.heading.suppliesRequests"/></strong> &raquo; <strong><spring:message code="requestInfo.heading.orderSupplies"/></strong></td>
        </tr>
      </table></td>
  </tr>
  <%--Changes BRD 12 CI 7--%>
  <tr><td><span id="accNameAgreeName"> </span></td></tr>
  <%--Ends  Changes BRD 12 CI 7--%>
  <tr>
    <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px">
    <strong><spring:message code="catalogOrder.requestNo.heading"/><span id="reqNumber"> </span></strong></td>
  </tr>
  <tr>
    <td valign="top" style="padding:10px;font-family:Arial, Helvetica, sans-serif; font-size:11px" id="message"></td>
  </tr>
  <tr>
    <td valign="top"><!-- PRIMARY SECONDARY CONTACTS & ADDITIONAL INFO - START -->
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.primaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> <span id="primaryCont_name"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="primaryCont_phone"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="primaryCont_email"> </span></td>
              </tr>
            </table></td>
          <td rowspan="2" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
                <spring:message code="requestInfo.heading.additionalInformationForThisRequest"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.customerRefereceId"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="addtnlInfo_customerRefId"> </span></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.costCentre"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="addtnlInfo_costCenter"> </span></td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.description"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="addtnlInfo_description"> </span></td>
              </tr>
            </table></td>
        </tr>
        <tr id="secondaryContactTR">
          <td  valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.secondaryContactForThisRequest"/></th>
              </tr>
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.name"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="secondaryCont_name"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.phoneNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="secondaryCont_phone"> </span></td>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.emailAddress"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="secondaryCont_email"> </span></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      <!-- PRIMARY SECONDARY CONTACTS & ADDITIOANL INFO - END -->
      
      <hr style="margin:5px; height:1px; border:none; border-top-width: 1px;  border-top-style:dotted; border-top-color: #aaa;" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></strong></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"">
         	<!-- Part Table is placed here dynamically -->
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
					<spring:message code="requestInfo.heading.yield"/>
				</th>
				<th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
					<spring:message code="requestInfo.heading.model"/>
				</th>
				<th width="60" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
					<spring:message code="requestInfo.heading.orderQuantity"/>
				</th>
				<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
									<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
				 <th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
						 <spring:message code="requestInfo.heading.unitPrice"/>
					  </th>
					  <th width="80" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;">
						 <spring:message code="requestInfo.heading.partTotal"/>
					  </th>
					  </c:if>
					  </c:if>
                </tr>
                <c:forEach items="${reviewCatalogOrderForm.catalogPartList}" var="assetPartListDetail" varStatus="counter" begin="0">
               <tr>
	                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.partNumber}</td>
					<td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.description}</td>
					<td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.partType}</td>
					<td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.yield}</td>
					<td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.model}</td>
					<td align="right" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.orderQuantity}</td>
                	<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
										<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
                	<td  valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.unitPrice}  (${catalogPartListDetail.currency})</td>
					<td  valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${assetPartListDetail.total}  (${catalogPartListDetail.currency})</td>
					
					</c:if>
					</c:if>
                </tr>
                </c:forEach>
                </table>
         </td>
        </tr>
      </table>
      
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
              	
		<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
	        	   		<c:if test="${sessionScope.catalogFinalFlags['finalTaxCalcFlag'] == 'true' && sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">	
		<tr>
          <td width="50%" valign="top" style="border-color:#cccccc ;float:right;">
		  <table width="50%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsPriceSubTotal"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${catalogDetailPageForm.subTotal}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsTaxes"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${catalogDetailPageForm.tax}</td>
              </tr>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.partsTotalAmountPayable"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${catalogDetailPageForm.totalAmt}</td>
              </tr>
              
            </table></td>
          
        </tr>
        </c:if>
        </c:if>
      </table>
      
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
      <tr><td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.shippingInformation"/></strong></td></tr>
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
          <table width="100%" border="0" cellspacing="0" cellpadding="5" id="shipToAddrTable">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.shipToAddress"/></th>
              </tr>
              <tr>
                <td colspan="2" valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
                <span id="shipToAddrSpan"><div>${catalogDetailPageForm.shipToAddress.storeFrontName}</div>
                <util:addressOutput value="${catalogDetailPageForm.shipToAddress}"></util:addressOutput>
													                  <%--<div>${catalogDetailPageForm.shipToAddress.addressLine1}</div>
													                  <div>${catalogDetailPageForm.shipToAddress.officeNumber}</div>
													                  <c:if test="${catalogDetailPageForm.shipToAddress.addressLine2 !='' && catalogDetailPageForm.shipToAddress.addressLine2!=null}">
													                  <div>${catalogDetailPageForm.shipToAddress.addressLine2}, </div>
													                  </c:if>
													                  <div>${catalogDetailPageForm.shipToAddress.addressLine3}</div>
													                  <span>${catalogDetailPageForm.shipToAddress.city},
													                  <c:if test="${catalogDetailPageForm.shipToAddress.county != '' && catalogDetailPageForm.shipToAddress.county!=null}">
													                  ${catalogDetailPageForm.shipToAddress.county},
													                  </c:if>
													                  <c:if test="${catalogDetailPageForm.shipToAddress.state != '' && catalogDetailPageForm.shipToAddress.state != null}">
													                  ${catalogDetailPageForm.shipToAddress.stateCode}
													                  </c:if>
													                  <c:if test="${(catalogDetailPageForm.shipToAddress.province !='' && catalogDetailPageForm.shipToAddress.province !=' ') && catalogDetailPageForm.shipToAddress.province != null}">
													                  ,${catalogDetailPageForm.shipToAddress.province}
													                  </c:if>
													                   region changed to district for MPS 2.1 
													                  <c:if test="${(catalogDetailPageForm.shipToAddress.district !='' && catalogDetailPageForm.shipToAddress.district !=' ') && catalogDetailPageForm.shipToAddress.district != null}">
													                  ,${catalogDetailPageForm.shipToAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${catalogDetailPageForm.shipToAddress.postalCode}</div> 
													                  <div>${catalogDetailPageForm.shipToAddress.country}</div></span>--%>
                	
				</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.building"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${catalogDetailPageForm.shipToAddress.physicalLocation1} </td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.floor"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> ${catalogDetailPageForm.shipToAddress.physicalLocation2}</td>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.addressInfo.label.office"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${catalogDetailPageForm.shipToAddress.physicalLocation3}</td>
              </tr>
            </table></td>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.orderInstructions"/></th>
              </tr>
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.specialInstructions"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"> ${catalogDetailPageForm.specialInstruction}</td>
              </tr>
              <%-- <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.defaultSpecialInstructions"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="defaultSpclInstruction"></span></td>
              </tr> --%>
              <c:if test="${catalogDetailPageForm.expediteOrderAllowed == 'true'}">
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.requestExpediteOrder"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><c:choose>
											<c:when test="${catalogDetailPageForm.requestExpediteOrder eq \"true\"}">
					                  					<spring:message code="requestInfo.option.yes"/>
					                  				</c:when>
					                  				<c:otherwise>
					                  					<spring:message code="requestInfo.option.no"/>
					                  				</c:otherwise>
					                  			</c:choose></td>
              </tr>
              </c:if>
              <tr>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong><spring:message code="requestInfo.label.requestedDeliveryDate"/></strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">${catalogDetailPageForm.requestedDeliveryDate}</td>
              </tr>
            </table></td>
        </tr>
      </table>
     <br/>
      <table width="100%" border="0" cellspacing="0" cellpadding="5" id="shipToAddrTable">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.paymentDetails"/></th>
              </tr>
        <tr>
          <td valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="100" valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><strong><spring:message code="requestInfo.label.poNumber"/></strong></td>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; padding-top:10px; padding-bottom:10px; padding-left:5px; padding-right:5px"><span id="poNumber">${ catalogDetailPageForm.poNumber}</span></td>
              </tr>
              <c:if test="${not empty catalogDetailPageForm.creditCardEncryptedNo}">
              <tr>
                <td valign="top" width="100" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><strong>
    <spring:message code="requestInfo.heading.credit"/>:</strong></td>
                <td valign="top"  style="font-family:Arial, Helvetica, sans-serif; font-size:11px;">
    ${catalogDetailPageForm.creditCardEncryptedNo}</td>
              </tr>
              </c:if>
            </table></td>
        </tr>
        
      </table>
    
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top"style="padding:10px; padding-bottom:5px; font-family:Arial, Helvetica, sans-serif; font-size:12px; border-bottom-style:dotted; border-bottom-color:#aaaaaa; border-bottom-width:1px"><strong><spring:message code="requestInfo.heading.notesAndAttachments"/></strong></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <th colspan="2" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.notes"/></th>
              </tr>
              <tr>
                <td valign="top" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;"><span id="notes">${catalogDetailPageForm.attachmentDescription}</span></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="50%" border="0" cellspacing="5" cellpadding="0">
        <tr>
         <td width="50%" valign="top" style="border-color:#cccccc; border-width:1px; border-style:solid">
		  <table width="100%" border="0" cellspacing="0" cellpadding="5">
		  <c:if test="${catalogDetailPageForm.displayAttachmentList !=null && not empty catalogDetailPageForm.displayAttachmentList}">
              <tr>
                <th  width="220px" colspan="0" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachmentFileName"/></th>
                <th  width="220px" colspan="0" align="left" style="font-family:Arial, Helvetica, sans-serif; font-size:11px;background-color:#e7e7e7;border-bottom-color:#aaaaaa; border-bottom-width:1px; border-bottom-style:dotted;"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th>
              </tr>
              </c:if>
              <c:forEach items="${catalogDetailPageForm.displayAttachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
					<c:choose>
						<c:when test="${counter.count % 2 ne 0}">
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
          		
           
      </table></td>
  </tr>
  <!-- 
  <tr>
    <td style="background-color:#ebebe4; font-family:Arial, Helvetica, sans-serif; font-size:11px; padding:10px; border-top-color:#aaaaaa; border-top-style:dotted; border-top-width:1px">Footer    
      information goes here</td>
  </tr> -->
</table>
<br>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr id="topEmail">
		<td width="55%"></td>
		<td align="right" width="25%">
			<a id="btmEmail" onclick="javascript:showEmailPopup();">
                <img src="<html:imagesPath/>/transparent.png"  class="ui_icon_sprite email-icon" style="cursor:pointer" title="<spring:message code='requestInfo.tooltip.emailThisPage'/>" />
                <span style="cursor:pointer"><spring:message code='requestInfo.link.emailThisPage'/></span>
            </a>&nbsp;
		</td>
		<td align="right">
			<button id="btnClose" class="button_cancel" onClick="window.close(this);"><spring:message code="button.cancel"/></button>
		</td>
	</tr>
	
</table>
</body>
<script type="text/javascript">
<%--  Changes BRD 12 CI 7--%>
window.document.getElementById("accNameAgreeName").innerHTML = window.opener.window.document.getElementById("accNameAgreeName").innerHTML;
<%--Ends  Changes BRD 12 CI 7--%>
window.document.getElementById("reqNumber").innerHTML = window.opener.window.document.getElementById("reqNumber").innerHTML;

window.document.getElementById("primaryCont_name").innerHTML = window.opener.window.document.getElementById("primaryCont_name").innerHTML;
window.document.getElementById("primaryCont_phone").innerHTML = window.opener.window.document.getElementById("primaryCont_phone").innerHTML;
window.document.getElementById("primaryCont_email").innerHTML = window.opener.window.document.getElementById("primaryCont_email").innerHTML;
if(window.opener.window.document.getElementById("addiContact").style.display!="none"){
window.document.getElementById("secondaryCont_name").innerHTML = window.opener.window.document.getElementById("secondaryCont_name").innerHTML;
window.document.getElementById("secondaryCont_phone").innerHTML = window.opener.window.document.getElementById("secondaryCont_phone").innerHTML;
window.document.getElementById("secondaryCont_email").innerHTML = window.opener.window.document.getElementById("secondaryCont_email").innerHTML;
}else{
	jQuery('#secondaryContactTR').remove();
}


window.document.getElementById("addtnlInfo_customerRefId").innerHTML = window.opener.window.document.getElementById("addtnlInfo_customerRefId").innerHTML;
window.document.getElementById("addtnlInfo_costCenter").innerHTML = window.opener.window.document.getElementById("addtnlInfo_costCenter").innerHTML;
window.document.getElementById("addtnlInfo_description").innerHTML = window.opener.window.document.getElementById("addtnlInfo_description").innerHTML;


//window.document.getElementById("defaultSpclInstruction").innerHTML = window.opener.window.document.getElementById("defaultSpclInstruction").innerHTML;

if(window.opener.window.document.getElementById("attachmentHidden").innerHTML=='attachfailed'){
	jQuery('#attachmentFailedEmail').show();
}
else{
	jQuery('#attachmentFailedEmail').hide();
}

	<%-- Commented for adding AUI Popup
function email(){
	if(window.opener.window.document.getElementById("attachmentHidden").innerHTML!='attachfailed'){
		jQuery('#attachmentFailedEmail').remove();
	}
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

--%>

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