<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<portlet:renderURL var="confirmAssetOrderURL">
	<portlet:param name="action" value="confirmAssetOrder"/>
</portlet:renderURL>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../common/subTab.jsp"></jsp:include>
<div class="main-wrap">
	<div class="content">
		<div id="content-wrapper">
			<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div><!-- End journal-content-article -->
			<div class="portletBlock">
				<div class="portletBlock center">
					<ul class="processSteps shadow">
						<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step1SelectAsset"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.link.selectAsset"/></a></li>
						<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
						<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
						<li class="selected"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
					</ul>
				</div><!-- End portletBlock center -->
			</div><!-- portletBlock -->
			<div class="portletBodyWrap">
			<form:form modelAttribute="assetDetailPageForm" commandName="assetDetailPageForm">
			<div id="confirmOrderPrintWrapperTop">
				<div id="div_confirmOrderPrint">
				<h3 class="pageTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.orderConfirmation"/></h3>
				  <div class="utilities printPage floatR" id="printIcons">
		  	<ul>
              <li class="first"><a href="javascript:email();"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="requestInfo.alt.emailThisPage"/>" title="<spring:message code="requestInfo.title.emailThisPage"/>"></a></li>
              <li><a href="javascript:print();"><img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="requestInfo.alt.printThisPage"/>" title="<spring:message code="requestInfo.title.printThisPage"/>"></a></li>
            </ul>
          </div>
		          <c:if test="${serviceRequestNumber == null || serviceRequestNumber == ''}">
				  <p class="info banner err"><spring:message code="requestInfo.error.unableToAccept"/></p>
				  </c:if>
				<c:if test="${serviceRequestNumber != null && serviceRequestNumber != ''}">
				<p class="info banner ok">
					<spring:message code="requestInfo.message.confirmMessage1"/>
					<span id="reqNumber">${serviceRequestNumber}</span>
					<spring:message code="requestInfo.message.confirmMessage2"/>
				</p>
				</c:if>
				
				<%@ include file="/WEB-INF/jsp/orderSuppliesAssetOrder/commonContactInfoReviewForAssetOrder.jsp"%>
				</div>
				<hr class="separator" />
				<!-- Selected Asset Start -->
				<div id="div_selectedAssetPrint">
				<p class="inlineTitle"><spring:message code="requestInfo.heading.selectedAsset"/></p>
				<div class="portletBlock infoBox rounded shadow bgSolid">
					<!-- Outter table: 1 row & 2 colums -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail detailInfoList">
						<tr>
							<!-- Outter Table > column 1: Product image & Name -->
							<td class="pModel"><ul>
								<li class="center"><img src='${assetDetailPageForm.asset.productImageURL}' width="100" height="100"  id="orderImage" class='printer' onError="image_error();"/></li>
                  				<li class="pModelName" id="imageNo">${assetDetailPageForm.asset.productLine}</li>
							</ul></td>
							<!-- Outter Table > Column 2: Inner table for details -->
							<td class="pDetail"><!-- Inner table: 4 row2 & 2 colums -->
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="h4"><spring:message code="requestInfo.heading.assetInformation"/></td>
                    					<td class="h4 separatorV"><spring:message code="requestInfo.heading.serviceAddress"/></td>
									</tr>
									<tr>
										<td class="infoDsiplay">
											<ul class="form roDisplay">
												<li>
						                          <label><spring:message code="requestInfo.assetInfo.label.serialNumber"/></label>
						                          <span>${assetDetailPageForm.asset.serialNumber}</span></li>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.productName"/></label>
						                          <span>${assetDetailPageForm.asset.productTLI}</span></li>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.installDate"/></label>
						                          <span>${assetDetailPageForm.installDate}</span></li>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.ipAddress"/></label>
						                          <span>
						                          <%--Start: Added for Defect 3924-Jan Release--%>
						                          <a href="javascript:gotoControlPanel('${assetDetailPageForm.asset.controlPanelURL}')">
						                          ${assetDetailPageForm.asset.ipAddress}</a>
						                          <%--End: Added for Defect 3924-Jan Release--%>
						                          </span></li>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
						                          <span>${assetDetailPageForm.asset.hostName}</span></li>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
						                          <span>${assetDetailPageForm.asset.deviceTag}</span></li>
											</ul>
										</td>
										<td class="separatorV"><ul class="roDisplay">
				                        <li><div>${assetDetailPageForm.serviceAddress.storeFrontName}</div>
				                        <util:addressOutput value="${assetDetailPageForm.serviceAddress}"></util:addressOutput>
													                <%--  <div>${assetDetailPageForm.serviceAddress.addressLine1}</div>
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
															</li>
				                        <li><span><spring:message code="requestInfo.addressInfo.label.building"/> ${assetDetailPageForm.physicalLocationAddress.physicalLocation1}</span></li>
				                        <li><span><spring:message code="requestInfo.addressInfo.label.floor"/> ${assetDetailPageForm.physicalLocationAddress.physicalLocation2}</span></li>
				                        <li><span><spring:message code="requestInfo.addressInfo.label.office"/> ${assetDetailPageForm.physicalLocationAddress.physicalLocation3}</span></li>
				                      </ul></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				</div>
				<!-- Selected Asset End -->
				<!-- New page counts start -->
				</div>
				
				<div id="div_pageCountPrint">
				<jsp:include page="/WEB-INF/jsp/orderSuppliesAssetOrder/pageCountsForAsset.jsp" />
				</div><!-- columnsTwo -->
			<div id="confirmOrderPrintWrapperBottom">
				
				<!-- New page counts end -->
				
				<!-- Part details Start -->
				<%-- Added for JAN release --%>
			
				<c:if test='${assetDetailPageForm.asset.installationOnlyFlag == "true" }'>
							<div class="portletBlock">
								<div class="columnsOne">
								<div class="infoBox columnInner rounded shadow">
									<h4><spring:message code="requestInfo.label.partsToBeInstalled"/> </h4>
									<ul class="form">
									<li>
										<label><spring:message code="requestInfo.label.partsToBeInstalled"/>:</label>
										<span>
											<span id="description" class="multiLine">${assetDetailPageForm.asset.partsToBeInstalled}</span>
										</span>
									</li>
									</ul>
								</div>
								</div>
								</div>
				
			
				</c:if>
				
				<c:if test='${assetDetailPageForm.asset.installationOnlyFlag != "true" }'>
				
							<c:if test="${assetDetailPageForm.splitterFlag == true}">
							<span style="display: none"><form:input path="dsplPaymentTypeFlag" id="dsplPaymentTypeFlag"/></span>
								<c:if test='${assetDetailPageForm.dsplPaymentTypeFlag == true}' >
								<div class="portletBodyWrap" style="min-width:700px;">
							  	<div class="portletBlock infoBox rounded shadow">
							  		<div class="columnsTwo">
									<div class="columnInner">
							  		<ul class="form">
												<li>
													<label for="paymentType"><spring:message code="requestInfo.label.paymentType"/> </label> 
													<span>
													
														${assetDetailPageForm.selectedPaymentType}
												          		 
													</span>
												</li>
									</ul>
									</div>
									</div>
							  	</div>
							    </div>
							    </c:if>
								</c:if>
				
				<div id="div_partDetailsPrint">
				<p class="inlineTitle"><spring:message code="requestInfo.heading.listOfParts"/></p>
				<%-- <p><b><spring:message code="requestInfo.label.preferredNote"/></b></p> --%>
				<div class="columnInner" id="suppliesTable">
				<div id="partListTable" class="w displayGrid columnInnerTable rounded shadow">
					<table>
						<thead>
							<tr>
								<th class="w90"><spring:message code="requestInfo.heading.partNumber"/></th>
								<%-- <th class="w10"><spring:message code="requestInfo.heading.preferredPart"/></th> --%>
								<th><spring:message code="requestInfo.heading.description"/></th>
								<th class="w200"><spring:message code="requestInfo.heading.partType"/></th>
								<th class="w80 right"><spring:message code="requestInfo.heading.orderQuantity"/></th>
								<!-- MPS Phase 2.1 changes start -->
								<c:if test="${assetDetailPageForm.finalShowPriceFlag}">
									<th class="w100 right"><spring:message code="requestInfo.heading.unitPrice"/></th>
									<th class="w100 right"><spring:message code="requestInfo.heading.partTotal"/></th>
								</c:if>
								<!-- MPS Phase 2.1 changes end -->
							</tr>
						</thead>
						<tbody>
						<%--Changes for 13771 --%>
						<c:set var="altRowVar" value="false"/>
						<%--Ends Changes for 13771 --%>
							<c:forEach items="${reviewAssetOrderForm.assetPartList}" var="assetPartListDetail" varStatus="counter" begin="0">
								<c:if test="${not empty assetPartListDetail.orderQuantity && assetPartListDetail.orderQuantity ne '0'}">
								<c:choose>
									<c:when test="${altRowVar}">
									   <tr class="altRow">
									   <%--Changes for 13771 --%>
									     <c:set var="altRowVar" value="false"/>
									     <%--Ends Changes for 13771 --%>
									</c:when>
									<c:otherwise>
									   <tr>
									   <%--Changes for 13771 --%>
									   <c:set var="altRowVar" value="true"/>
									   <%--Ends Changes for 13771 --%>
									</c:otherwise>
								</c:choose>
									
									<%-- <c:choose>
					                  	<c:when test="${assetPartListDetail.prefferedPartFlag == true}">
					                  	 <td class="w10"><font size = 4px><b><spring:message code="requestInfo.heading.preferredSymbol"/></b></font></td>
					                  	</c:when>
				                  	<c:otherwise>
				                  	 <td class="w10"></td>
				                  	</c:otherwise>
				                  </c:choose> --%>
				                  <td class="w90">${assetPartListDetail.partNumber}</td>
									<td>${assetPartListDetail.description}</td>
									<td class="w200">${assetPartListDetail.partType}</td>
									<td class="w80 right">${assetPartListDetail.orderQuantity}</td>
									<!-- MPS Phase 2.1 changes start -->
									<c:if test="${assetDetailPageForm.splitterFlag}">
										<c:if test="${assetDetailPageForm.finalShowPriceFlag}">
											<td class="w100 right">${assetPartListDetail.unitPrice}</td>
											<td class="w100 right">${assetPartListDetail.totalPrice}</td>
										</c:if>
									</c:if>
									<!-- MPS Phase 2.1 changes end -->
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
					</div>
					
					<c:if test="${assetDetailPageForm.splitterFlag}">
						<c:if test="${assetDetailPageForm.finalTaxCalcFlag}">
						<div class="lineClear"></div>
						<div class="floatR w250">
						    <table>
						    <tr height="25">
						    	<td class="right w150 strong"><spring:message code="requestInfo.label.partsPriceSubTotal"/></td>
						    	<td class="right w110">${assetDetailPageForm.subTotalPrice}</td>
						    </tr>
						    <tr height="25"><!--  changed on june 11,2013-->
						    	<td class="right strong"><img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.label.partsTaxesEstimated"/>' >&nbsp;&nbsp;<spring:message code="requestInfo.label.partsTaxes"/></td>
						    	<c:choose>
							    	<c:when test="${assetDetailPageForm.totalTax == 'Unavailable'}">
							    		<td class="right"><spring:message code="requestInfo.tax.unavailable"/></td>
							    	</c:when>
							    	<c:otherwise>
							    		<td class="right">${assetDetailPageForm.totalTax}</td>
							    	</c:otherwise>
							    </c:choose>
						    </tr>
						    <tr height="25">
						    	<td class="right strong"><spring:message code="requestInfo.label.partsTotalAmountPayable"/></td>
						    	<td class="right">${assetDetailPageForm.totalPrice}</td>
						    </tr>
						    </table>
					    </div>
					    </c:if>
				    </c:if>
				</div>
				</div>
				<!-- Part details End -->
				<!-- Shipping & Billing Information Start -->
				
				<div id="div_shipBillInfoPrint">
				<p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
				<div class="portletBlock">
          			<div class="columnsTwo">
            			<div class="infoBox columnInner rounded shadow bgSolid">
            				<h4><spring:message code="requestInfo.heading.shipToAddress"/></h4>
            				<ul class="roDisplay">
            					<li><div>${assetDetailPageForm.shipToAddress.storeFrontName}</div>
            					<util:addressOutput value="${assetDetailPageForm.shipToAddress}"></util:addressOutput>
													               <%--   <div>${assetDetailPageForm.shipToAddress.addressLine1}</div>
													                  <div>${assetDetailPageForm.shipToAddress.officeNumber}</div>
													                  <c:if test="${assetDetailPageForm.shipToAddress.addressLine2 !='' && assetDetailPageForm.shipToAddress.addressLine2!=null}">
													                  <div>${assetDetailPageForm.shipToAddress.addressLine2}, </div>
													                  </c:if>
													                  <div>${assetDetailPageForm.shipToAddress.addressLine3}</div>
													                  <span>${assetDetailPageForm.shipToAddress.city},
													                  <c:if test="${assetDetailPageForm.shipToAddress.county != '' && assetDetailPageForm.shipToAddress.county!=null}">
													                  ${assetDetailPageForm.shipToAddress.county},
													                  </c:if>
													                  <c:if test="${assetDetailPageForm.shipToAddress.stateCode != '' && assetDetailPageForm.shipToAddress.stateCode != null}">
													                  ${assetDetailPageForm.shipToAddress.stateCode}
													                  </c:if>
													                  <c:if test="${(assetDetailPageForm.shipToAddress.province !='' && assetDetailPageForm.shipToAddress.province !=' ') && assetDetailPageForm.shipToAddress.province != null}">
													                  ,${assetDetailPageForm.shipToAddress.province}
													                  </c:if>
													                   region changed to district for MPS 2.1 
													                  <c:if test="${(assetDetailPageForm.shipToAddress.district !=' ' && assetDetailPageForm.shipToAddress.district !='')&& assetDetailPageForm.shipToAddress.district != null}">
													                  ,${assetDetailPageForm.shipToAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${assetDetailPageForm.shipToAddress.postalCode}</div> 
													                  <div>${assetDetailPageForm.shipToAddress.country}</div>--%>
															</li>
				                <li><span><spring:message code="requestInfo.addressInfo.label.building"/>${assetDetailPageForm.shipToAddress.physicalLocation1}</span></li>
				                <li><span><spring:message code="requestInfo.addressInfo.label.floor"/>${assetDetailPageForm.shipToAddress.physicalLocation2}</span></li>
				                <li><span><spring:message code="requestInfo.addressInfo.label.office"/>${assetDetailPageForm.shipToAddress.physicalLocation3}</span></li>	
            				</ul>
            			</div>
            		</div>
            		<div class="columnsTwo">
            			<div class="infoBox columnInner rounded shadow bgSolid">
            				<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
            				<ul class="form">
            					<li>
				                  <label for="desc"><spring:message code="requestInfo.label.specialInstructions"/></label>
				                  <span class="textScroll">${assetDetailPageForm.specialInstruction}</span></li>
				                <%-- <li>
				                  <label for="date1"><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
				                  <span class="multiLine">${assetDetailPageForm.defaultSpecialInstruction}</span></li> --%>
				                  <%--
				                  Changes for JAN release Request Expedite order --%>
				                 <c:if test='${assetDetailPageForm.expediteOrderAllowed == "true" }'>
				                <li>
				                  <label for="expOrder"><spring:message code="requestInfo.label.requestExpediteOrder"/></label>
				                  <span>
				                   <c:choose>
				                  	<c:when test="${assetDetailPageForm.requestExpediteOrder eq \"true\"}">
				                  	<spring:message code="requestInfo.option.yes"/>
				                  	</c:when>
				                  	<c:otherwise>
				                  	<spring:message code="requestInfo.option.no"/>
				                  	</c:otherwise>
				                  </c:choose>
				                  </span>
				                  </li>
				                 <%--
				                 Ends --%>
				                 </c:if>
				                  
				                <li>
				                  <label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label>
				                  <span>${assetDetailPageForm.requestedDeliveryDate}</span></li>
            				</ul>
            			</div>
            		</div>
            	</div>
				</div>
				</c:if>
				<%-- Added for JAN release Ends --%>
				<!-- Shipping & Billing Information End -->
				<!-- Payment Details Start -->
				<div id="div_paymentDetailsPrint">
				<div class="portletBlock">
          			<div>
            			<div class="infoBox columnInner rounded shadow bgSolid">
            				<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
            				<div class="columnsTwo">            				
            				<c:if test="${creditFlag == 'true'}">
							<ul class="form">
								<li id="creditCard">
									<label for="ponum"><spring:message code="requestInfo.heading.credit"/></label>									
										${assetDetailPageForm.creditCardEncryptedNo} 									
								</li>
							</ul>
							</c:if>
							
							<c:if test="${assetDetailPageForm.asset.poNumber ne null && not empty assetDetailPageForm.asset.poNumber}">
							<ul class="form">
								<li id="poNumber">
									 <label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label>									
										${assetDetailPageForm.asset.poNumber}  
								</li>
							</ul>	
							</c:if>
							
            				</div>
            			</div>
            		</div>
            	</div>
				</div>
				<!-- Payment Details End -->
				<!-- Attachment Description Start -->
				<div id="div_attachmentInfoPrint">
				<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow bgSolid">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine wFull noteWrap notesOverflow">${assetDetailPageForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
				</div>
				<!-- Attachment Description End -->
				<!-- Attachment Start -->
				<div id="div_attachmentListPrint">
				<c:if test="${not empty displayAttachmentList}">
				<div class="portletBlock">
					<div class="columnInner">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
						<table>
							<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th>
							<th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${displayAttachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td class="w70p">${attachmentListDetail.fileName}</td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.fileSize div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
						</div>
					</div>
				</div>
				</c:if>
				<div id="attachmentFailed" class="error" style="display: none; width: 50%">
				</div>
				</div>
				</div>
				<!-- Attachment End -->
			</form:form>
			<div id="attachmentHidden" style="display: none;">${attachmentException}</div>
			<div class="filterLinks floatR">
	          <ul>
        <%--  <c:if test='${fleetManagementFlag ne "true"}'> --%>
					<li class="cloumnsTwo" style="display: inline; float: left; ">
					<div class="cloumnsTwo" id="backtomap" style="display: none; float: left; width: 26%;">
						<button class="button_cancel" onclick="javascript:backToMapView();"	type="button">Back to Map</button>
						</div>
					</li>
	<%--	</c:if> --%>
			
	            <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>/transparent.png" title="<spring:message code="requestInfo.title.emailThisPage"/>"height="23" width="27"/><spring:message code="requestInfo.link.emailThisPage"/></a></li>
	            <li><a href="javascript:print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>/transparent.png" title="<spring:message code="requestInfo.title.printThisPage"/>" height="27" width="27"/><spring:message code="requestInfo.link.printThisPage"/></a></li>
	          </ul>
	        </div>
        </div><!-- content-wrapper -->
        </div><!-- portletBodyWrap -->
	</div><!-- content -->
</div><!-- main-wrap -->

<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${assetDetailPageForm.backToMap}"/>
</form>

<script type="text/javascript">
jQuery(document).ready(function() {
	var currentURL = window.location.href;
	jQuery('#pageCountInfo').hide();
	if("${attachmentException}"=="attachfailed"){
		jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
		 jQuery("#attachmentFailed").show();			
		}
	if(!(currentURL.indexOf('/fleet-management') == -1)){
		jQuery('#backtomap').show();
		}
});

function print(){
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESCONFIRMATIONASSET%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESCONFIRMATIONPRINTTHISPAGELINK%>');
	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='orderConfirmPrint' /></portlet:renderURL>";         
    var iWidth=900;
    var iHeight=5600;
    var iTop = (window.screen.availHeight-30-iHeight)/2;        
    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
    window.open(url,
            'ConfirmAssetOrder',
            'height='+iHeight+
            ',innerHeight='+
            iHeight+',width='+
            iWidth+',innerWidth='+
            iWidth+',top='+iTop+
            ',left='+iLeft+
            ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};
function email() {
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESCONFIRMATIONASSET%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESCONFIRMATIONEMAILTHISPAGELINK%>');
	url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		"<portlet:param name='action' value='orderAssetEmail' />" + 
		"</portlet:renderURL>";

    var iWidth=1000;
    var iHeight=600;
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    window.open(url,
    		'orderAssetList',
		    'height='+iHeight+
		    ',innerHeight='+
		    iHeight+',width='+
		    iWidth+',innerWidth='+
		    iWidth+',top='+iTop+
		    ',left='+iLeft+
		    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};
	/* function getEmailSubject(){
        var serviceRequestNumber = "${serviceRequestNumber}";
        var emailSubject = "<spring:message code='message.orderSupplies.detail'/>"+"("+ serviceRequestNumber +")";
        return emailSubject;
    }; */
    
	function showSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "visible";
		} 
	}
    image_error= function () {
		
		  document.getElementById("orderImage").src = '<html:imagesPath/>dummy_noimg.jpg'; 
		  
		};
		
		/*Method gotoControlPanel added for Defect 3924- Jan Release*/
		function gotoControlPanel(url) {
			 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESCONFIRMATIONASSET%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESCONFIRMATIONIPADDRESSHYPERLINK%>');
		/*	alert('gotoControlPanel');*/
			controlPanelPopUpWindow.show();
			controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
			controlPanelPopUpWindow.io.start();
		<%--	new Liferay.Popup({
		        title: "",
		        position: [400,150], 
		        modal: true,
		        width: 400, 
		        height: 150,
		        xy: [100, 100],
		        onClose: function() {showSelect();},
		        url:"<portlet:renderURL 
		             windowState='<%= LiferayWindowState.POP_UP.toString() %>'>" + 
		            "<portlet:param name='action' value='gotoControlPanel' />" + 
		            "</portlet:renderURL>&controlPanelURL=" + url
		        });  --%>
		/*	alert('URL----->>'+url);*/
		};
</script>
<script>
var controlPanelPopUpWindow;
AUI().use('aui-base',
'aui-io-plugin-deprecated',
'liferay-util-window',
function(A) {

	controlPanelPopUpWindow=Liferay.Util.Window.getWindow(
	{
	dialog: {
	centered: true,
	constrain2view: true,
	//cssClass: 'yourCSSclassName',
	modal: true,
	resizable: false,
	width: 400,
	height: 195,
	visible: false
	}
	}
	).plug(
	A.Plugin.IO,
	{
	autoLoad: false
	})



});
function backToMapView(){
	showOverlay();
	//window.location.href='fleet-management';
	
	var lat="${assetDetailPageForm.shipToAddress.lbsLatitude}";
	var lon="${assetDetailPageForm.shipToAddress.lbsLongitude}";
	//var lat=49.474685633790784;
	//var lon=1.112907825559887;
	
					var defaultArea={											
				                lat: lat,
				                lng: lon,							                		           
				               

							
							};
					
	$('#backJson').val(JSON.stringify(defaultArea));
	$('#backFormMap').submit();
	
}

</script>