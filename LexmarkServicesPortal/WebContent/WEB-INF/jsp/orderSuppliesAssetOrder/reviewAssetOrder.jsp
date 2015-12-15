<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%--MPS 2.1 Changes Added for address cleansing --%>
<%@page import ="static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT"%>
<%--ends --%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript" src="<html:rootPath/>js/addressPopup.js"></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<style>
.reqHeight{height:25px;}
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL var="confirmAssetOrderURL">
	<portlet:param name="action" value="confirmAssetOrder"/>
</portlet:renderURL>

<portlet:renderURL var="addressListPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showAddressListPopUp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="creditCardPopUpUrl" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="action" value="showCreditCardPage"></portlet:param>
</portlet:renderURL>

<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../common/subTab.jsp"></jsp:include>
<script type="text/javascript">
/*
 * From summary page back is disabled
 */
window.history.forward();
function backDisable() { 
	//alert('back disabled so not going to prev page');
	window.history.forward(); 
}
</script>
<body onload="backDisable();" onpageshow="if (event.persisted) backDisable();" onunload="">

<div id="showCredit"></div>
<div id="overlayPopup" style="display:none"></div>
<div id="creditcardPageData" style="display: none"></div>

<div id="dialogAddress"></div>
<div class="main-wrap">
	<div class="content">
		<form:form id="assetDetailPageForm" name="assetDetailPageForm" method="post" modelAttribute="assetDetailPageForm" commandName="assetDetailPageForm" action = "${confirmAssetOrderURL}">
		<span style="display:none;">
		<input type="text" name="timezoneOffset" id="timezoneOffset"/>
		</span>	
		<div id="content-wrapper">
			<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div><!-- End journal-content-article -->
			<div id="validationErrors" class="error" style="display: none;"></div>
			<div class="portletBlock">
				<div class="portletBlock center">
					<ul class="processSteps shadow">
						<li class="active"><a href="javascript:onCancelClick();" title="<spring:message code="requestInfo.tooltip.step1SelectAsset"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.link.selectAsset"/></a></li>
						<li class="active"><a href="javascript:goBack()" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
						<li class="selected"><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
						<li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
					</ul>
				</div><!-- End portletBlock center -->
			</div><!-- End portletBlock -->
			<div class="portletBodyWrap">
				<h3 class="pageTitle"><spring:message code="requestInfo.heading.reviewOrder"/></h3>
				<p class="info banner"><spring:message code="requestInfo.heading.reviewOrderDesc"/></p>
				<%@ include file="/WEB-INF/jsp/orderSuppliesAssetOrder/commonContactInfoReviewForAssetOrder.jsp"%>
				<hr class="separator" />
				<!-- Selected Asset Start -->
				<p class="inlineTitle"><spring:message code="requestInfo.heading.selectedAsset"/></p>
				<div class="portletBlock infoBox rounded shadow">
					<!-- Outter table: 1 row & 2 colums -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="assetDetail detailInfoList">
						<tr>
							<!-- Outter Table > column 1: Product image & Name -->
							<td class="pModel">
								<ul>
									<li class="center"><img src='${assetDetailPageForm.asset.productImageURL}' id="MyPicture" class='printer' onError="image_error();" width="100" height="100"></li>
                  					<li class="pModelName">${assetDetailPageForm.asset.productLine}</li>
								</ul>
							</td>
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
						                          <%--Start: Added for Defect 3924-Jan Release--%>
						                          <span><a href="javascript:gotoControlPanel('${assetDetailPageForm.asset.controlPanelURL}')">${assetDetailPageForm.asset.ipAddress}</span></li>
						                          <%--End: Added for Defect 3924-Jan Release--%>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.hostName"/></label>
						                          <span>${assetDetailPageForm.asset.hostName}</span></li>
						                        <li>
						                          <label><spring:message code="requestInfo.assetInfo.label.customerAssetTag"/></label>
						                          <span>${assetDetailPageForm.asset.deviceTag}</span></li>
											</ul>
										</td>
										<td class="separatorV">
											<ul class="roDisplay">
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
											</ul>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<!-- Selected Asset End -->
				<!-- New page counts start -->
				<jsp:include page="/WEB-INF/jsp/orderSuppliesAssetOrder/pageCountsForAsset.jsp" />
				<!-- changes for JAN release -->
		
				<c:if test='${assetDetailPageForm.asset.installationOnlyFlag == "true" }'>
				<div class="portletBlock min-width-900px">
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
								<div class="portletBodyWrap min-width-900px" >
							  	<div class="portletBlock infoBox rounded shadow">
							  		<div class="columnsTwo">
									<div class="columnInner">
							  		<ul class="form">
												<li>
													<label for="paymentType"><spring:message code="requestInfo.label.paymentType"/></label> 
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

				<!-- New page counts end -->
				<!-- Part details Start -->
				<p class="inlineTitle"><spring:message code="requestInfo.heading.listOfParts"/></p>
				<%-- <p><b><spring:message code="requestInfo.label.preferredNote"/></b></p> --%>
				<div class="columnInner">
				<div id="partListTable" class="wFull displayGrid columnInnerTable rounded shadow">
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
								<c:if test="${not empty assetPartListDetail.orderQuantity && assetPartListDetail.orderQuantity ne '0' }">
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
											<c:choose>
												<c:when test="${empty assetPartListDetail.unitPrice or assetPartListDetail.unitPrice eq '0'}">
													<td class="w100 right" colspan="2"><span class='errorText'>Price not available</span></td>
												</c:when>
												<c:otherwise>
													<td class="w100 right">${assetPartListDetail.unitPrice}</td>
													<td class="w100 right">${assetPartListDetail.totalPrice}</td>
												</c:otherwise>
											</c:choose>
												
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
						<c:if test="${not empty assetDetailPageForm.subTotalPrice}">	
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
				    </c:if>
					
				</div>
				
				<!-- Part details End -->
				<!-- Shipping & Billing Information Start -->
			
				
				
				<p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
				<div class="portletBlock">
          			<div class="columnsTwo">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.shipToAddress"/></h4>
            				<ul class="roDisplay">
            					<li><div>${assetDetailPageForm.shipToAddress.storeFrontName}</div>
            					
            					<util:addressOutput value="${assetDetailPageForm.shipToAddress}"></util:addressOutput>
													                 <%-- <div>${assetDetailPageForm.shipToAddress.addressLine1}</div>
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
													                  <c:if test="${(assetDetailPageForm.shipToAddress.district !='' && assetDetailPageForm.shipToAddress.district !=' ') && assetDetailPageForm.shipToAddress.district != null}">
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
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
            				<ul class="form wordBreak">
            					<li>
				                  <label for="spclinstru"><spring:message code="requestInfo.label.specialInstructions"/></label>
				                  <span id="spclinstru" class="textScroll">${assetDetailPageForm.specialInstruction}</span></li>
				                <%-- <li>
				                  <label for="defaultspclinstru"><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
				                  <span id="defaultspclinstru">${assetDetailPageForm.defaultSpecialInstruction}</span></li> --%>
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
				                  Ends--%>
				                  </c:if>
				                  
				                <li>
				                  <label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label>
				                  <span>${assetDetailPageForm.requestedDeliveryDate}</span></li>
            				</ul>
            			</div>
            		</div>
            	</div>
            	</c:if>
            	<!-- change for JAN release ends -->
				<!-- Shipping & Billing Information End -->
				<!-- Payment Details Start -->
				<!--  <div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
            				<ul class="form">
            					<li>
                  					<label for="poNo"><spring:message code="requestInfo.label.poNumber"/></label>
                  					<span>${assetDetailPageForm.asset.poNumber}</span></li>
            				</ul>
            			</div>
            		</div>
            	</div> -->
            	<div id="paymentBlock" style="display: none;">
            		<p class="inlineTitle"><spring:message code="requestInfo.label.paymentInfo"/></p>
			        <div class="portletBlock">
			          <div class="columnsTwo">
			            <div class="infoBox columnInner rounded shadow">
			              <h4><spring:message code="requestInfo.label.paymentOption"/></h4>          
			              <div class="lineClear"></div>
			              <ul class="form">
			                <li>
			                  <label for="poNo"><spring:message code="requestInfo.label.enterPO"/></label>
			                  <span>
			                  <input type="text"  id="poNumber2"/>
			                  </span>
			                 </li>
			              </ul>
			              <c:if test="${not empty assetDetailPageForm.subTotalPrice}">	
			              <ul class="form">
				               <li>
				                 <label><a href="javascript:void(0);" onclick="updateCreditInfo();"><spring:message code="requestInfo.label.selectCard"/></a> <span class="req">*</span></label>
				                  <div id="creditCardNo"></div>
				                  <form:hidden id="creditCardEncryptedNo" path="creditCardEncryptedNo"/>
				                  <form:hidden id="creditCardToken" path="creditCardToken"/>
				                  <form:hidden id="creditCardExpirationDate" path="creditCardExpirationDate"/>
				                  <form:hidden id="creditCardType" path="creditCardType"/>
				                  <form:hidden id="cardHoldername" path="cardHoldername"/>
				                  <form:hidden id="creditFlag" path="creditFlag"/>
				                  <form:hidden id="transactionId" path="transactionId"/>
				             </ul></c:if>
			            </div>
			          </div>
			          <div class="columnsTwo" style="display: none;" id="billToBlock">
			            <div class="infoBox columnInner rounded shadow">
			              <h4><spring:message code="requestInfo.heading.billToAddress"/></h4>
			                 <ul class="roDisplay">              	
			                  <li>
	            				<div id="pickupStoreFrontNameLblParent"></div>
		            		<div id="pickupAddressLine1LblParent"></div>
		            		<div id="pickupAddressofficeNumberLblParent"></div>
		                  	<div id="pickupAddressLine2LblParent"></div>			                  	
		                 	<div><div style="display:inline;" id="pickupAddressCityLblParent"></div>
		                 	<div style="display:inline;" id="pickupAddresscountyLblParent"></div>
		                 	<div style="display:inline;" id="pickupAddressStateLblParent"></div><div style="display:inline;" id="pickupAddressProvinceLblParent"></div>
		                 	<div style="display:inline;" id="pickupAddressRegionLBParent"></div></div>
		                 	<div><span id="pickupAddressPostCodeLblParent"></span></div>
		                 	<div><span id="pickupAddressCountryLblParent"></span></div>
	             			</li>
			              </ul>
			            </div>
			          </div>
			        </div>
		        </div>
		        
		        	<span style="display: none">
			         	<form:input id="billToAddressId" path="billToAddress.addressId" />
			         	<form:input id="billToStoreFrontName" path="billToAddress.storeFrontName" />
				     	<form:input id="billToAddressLine1" path="billToAddress.addressLine1" /> 
				        <form:input id="billToAddressLine2" path="billToAddress.addressLine2" />
				        <form:input id="billToAddressCity" path="billToAddress.city" />
				        <form:input id="billToAddressState" path="billToAddress.state" />
				        <form:input id="billToAddressProvince" path="billToAddress.province" />
				        <form:input id="billToAddressPostCode" path="billToAddress.postalCode" />
				        <form:input id="billToAddressCountry" path="billToAddress.country" />
				        
				        
				      	<!-- Below Fields for Cleansing address Don't change the
							input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						 -->
						<form:input id="billToAddresscounty" path="billToAddress.county" />
					    <form:input id="billToAddressofficeNumber" path="billToAddress.officeNumber" />
					    <form:input id="billToAddressstateCode" path="billToAddress.stateCode" />
					    <form:input id="billToAddressstateFullName" path="billToAddress.stateFullName" />
					    <form:input id="billToAddressdistrict" path="billToAddress.district" />
					    <form:input id="billToAddressregion" path="billToAddress.region" />
					    <form:input id="billToAddresslatitude" path="billToAddress.latitude" />
					    <form:input id="billToAddresslongitude" path="billToAddress.longitude" />
					    <form:input id="billToAddresscountryISOCode" path="billToAddress.countryISOCode" />
					    <form:input id="billToAddresssavedErrorMessage" path="billToAddress.savedErrorMessage" />
					    <form:input id="billToAddressisAddressCleansed" path="billToAddress.isAddressCleansed" value="false"/>
						<!-- Ends Cleansing address -->
				        
					</span>
					
					<span style="display: none">
						<div id="pickupAddressId"></div>
						<div id="pickupStoreFrontName"></div>
						<div id="pickupAddressLine1"></div>
						<div id="pickupAddressLine2"></div>
						<div id="pickupAddressCity"></div>
						<div id="pickupAddressState"></div>
						<div id="pickupAddressProvince"></div>
						<div id="pickupAddressPostCode"></div>
						<div id="pickupAddressCountry"></div>
						
						<!-- Below Fields for Cleansing address Don't change the
						input id's as it is related to ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT 
						 -->
						<div id="pickupAddresscounty"></div>
					    <div id="pickupAddressofficeNumber"></div>
					    <div id="pickupAddressstateCode"></div>
					    <div id="pickupAddressstateFullName"></div>
					    <div id="pickupAddressdistrict"></div>
					    <div id="pickupAddressregion"></div>
					    <div id="pickupAddresslatitude"></div>
					    <div id="pickupAddresslongitude"></div>
					    <div id="pickupAddresscountryISOCode"></div>
					    <div id="pickupAddresssavedErrorMessage"></div>
					    <div id="pickupAddressisAddressCleansed"></div>
						<!-- Ends Cleansing address -->
						
					</span>
		        <div id="paymentBlockPO" style="display: none;">
		        	<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            			    <form:hidden id="paymentMethod" path="paymentMethod"/>
            				<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
            				<ul class="form">
            					<li>
                  					<label for="poNo"><spring:message code="requestInfo.label.poNumber"/></label>
                  					<span>${assetDetailPageForm.asset.poNumber}</span></li>
                  					<div style="display:none">
																	
										<input type="text"  id="poNumber1"/>
									</div>
            				</ul>
            			</div>
            		</div>
            	</div>
		        	
		        </div>
		            	
            	
				<!-- Payment Details End -->
				<!-- Attachment Description Start -->
				<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p class="multiLine noteWrap">${assetDetailPageForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
            	<!-- Attachment Description End -->
				<!-- Attachment Start -->
				<c:if test="${not empty assetDetailPageForm.attachmentList}">
				<div class="portletBlock">
					<div class="columnInner">
					<div class="wHalf displayGrid columnInnerTable rounded shadow">
						<table>
							 <thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
							 <tbody>
								<c:forEach items="${assetDetailPageForm.attachmentList}" var="attachmentListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
										<td class="w70p"><a href="javascript:void(0);" onclick="openAttachment('${attachmentListDetail.attachmentName}');">${attachmentListDetail.displayAttachmentName}</a></td>
										<td class="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${attachmentListDetail.size div 1024}"/></td>
									</tr>
								</c:forEach>
							 </tbody>
						</table>
					</div>
					</div>
				</div>
				</c:if>
				<!-- Attachment End -->
		<div class="buttonContainer">
			<div style="display:none">				
				<form:input  path="attachmentDescription"/>
				<form:input  path="specialInstruction"/>
				<form:input  path="requestExpediteOrder"/>
				<form:input  path="requestedDeliveryDate"/>
				<form:input  path="shipToAddress.physicalLocation1"/>
				<form:input  path="shipToAddress.physicalLocation2"/>
				<form:input  path="shipToAddress.physicalLocation3"/>
				<form:input path="listOfFileTypes" id="listOfFileTypes"/>
				<form:input path="attMaxCount" id="attMaxCount"/> 
				<form:input id="relatedServiceRequestedNumber" path="relatedServiceRequestedNumber" />
				<form:input id="relatedServiceRequestedRowId" path="relatedServiceRequestedRowId" />
				<form:hidden path="submitToken"/>
				<!-- MPS 2.1 fields added -->
				<form:input path="noOfPart" id="noOfPart"/> 
				<form:input path="showPriceFlag" id="showPriceFlag"/>
				<form:input path="poNumberFlag" id="poNumberFlag"/>
				<form:input path="creditNumberFlag" id="creditNumberFlag"/>
				<form:input path="finalShowPriceFlag" id="finalShowPriceFlag"/>  
				<form:input path="finalCreditFlag" id="finalCreditFlag"/>
				<form:input path="finalPOFlag" id="finalPOFlag"/>
				<form:input path="finalTaxCalcFlag" id="finalTaxCalcFlag"/>	
				<form:input path="totalPrice" id="totalPrice"/>	
				<form:input path="subTotalPrice" id="subTotalPrice"/>	
				<form:input path="totalTax" id="totalTax"/>		
				<form:input path="splitterFlag" id="splitterFlag"/>	
				<form:input path="asset.poNumber" id="poNumber"/>
			    <form:input path="selectedPaymentType" id="selectedPaymentType"/> 
			    <form:input path="selectedPaymentTypeId" id="selectedPaymentTypeId"/>  
			    <form:input path="contractNumber" id="contractNumber"/>			           	
			</div>
			<span style="display: none;">
		<input type="text" name="pageCountValue" id="pageCountValue" />
        <input type="text" name="pageCountDateValid" id="pageCountDateValid" />
        </span>
			<button class="button_cancel" type="button" onclick="javascript:goBack()"><spring:message code="button.back"/></button>
			&nbsp;
			<c:if test="${fleetManagementFlag != true }">
			<button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
			</c:if>
			<c:if test="${fleetManagementFlag == true }">
			<button class="button_cancel" type="button" onclick="onCancelClick();"><spring:message code="button.cancel"/></button>
			</c:if>
			&nbsp;
			<button class="button" type="button" onclick="javascript:confirmAssetOrder()"><spring:message code="button.submit"/></button>
		</div>
		
		</form:form>
		</div>
		</div>
	</div><!-- content -->
</div><!-- main wrap -->
</body>
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson" value="${assetDetailPageForm.backToMap}"/>
</form>
<script type="text/javascript">
/*function onCancelClick() {
	showOverlay();
	window.location.href = "<portlet:renderURL></portlet:renderURL>";
};*/
function onCancelClick(){
	 var currentURL = window.location.href;
	 
	 if(currentURL.indexOf('/fleet-management') != -1)
		{	
		
		 jConfirm('<spring:message code="common.country.alert"/>', "", function(confirmResult) {
				if(confirmResult){
					showOverlay();
					 //window.location.href="<portlet:renderURL><portlet:param name='action' value='default' /></portlet:renderURL>"
					//var lat=document.getElementById("pickupAddresslatitude").value;
					
					//var lon=document.getElementById("pickupAddresslongitude").value;
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
				}else{
					
					return false;
					}
			});
		
		}else{
			redirectToHistory('others');
			//window.location.href="<portlet:renderURL><portlet:param name='action' value='default' /></portlet:renderURL>"
		}
		
	}

<c:if test="${srCreationError != null}">
	jQuery('#validationErrors').show();	
	jQuery('#validationErrors').append('${srCreationError}');
</c:if>

function confirmAssetOrder(){
	 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESREVIEWASSET%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESREVIEWSUBMIT%>');
	 jQuery("#validationErrors").html("");
		var validation_error_flag = false;
		var finalCreditFlag ="${assetDetailPageForm.finalCreditFlag}";
		var finalPOFlag ="${assetDetailPageForm.finalPOFlag}";
		//alert(jQuery("#poNumber2").val());
		if(finalCreditFlag=="true" && finalPOFlag=="true"){
			jQuery("#poNumber").val(jQuery("#poNumber2").val());
		}else {
			jQuery("#poNumber").val(jQuery("#poNumber1").val());
		}
		
		if(finalCreditFlag=="true"){
			if(jQuery("#creditCardNo").length!=0){			
				if(jQuery("#creditCardNo").val()==null || jQuery("#creditCardNo").val()==""){
					//alert('here');
					validation_error_flag = true;
					jQuery("#validationErrors").append("<li><strong><spring:message code="requestInfo.validation.credit"/></strong></li>");
				}	
			}
		}
		if(validation_error_flag == true){
			jQuery("#validationErrors").show();
			jQuery(document).scrollTop(0);
			return false;
		}else{
			if(jQuery("#poNumber").length!=0){
				if(jQuery("#creditCardNo").length!=0){
					if((jQuery("#poNumber").val().trim()==null || jQuery("#poNumber").val().trim()=="") && (jQuery("#creditCardNo").val().trim()!=null && jQuery("#creditCardNo").val().trim()!="")){
						jQuery("#paymentMethod").val('Credit Card');
					}else if((jQuery("#poNumber").val().trim()!=null && jQuery("#poNumber").val().trim()!="") && (jQuery("#creditCardNo").val().trim()!=null && jQuery("#creditCardNo").val().trim()!="")){
						jQuery("#paymentMethod").val('Credit Card');
					}else if((jQuery("#poNumber").val().trim()!=null && jQuery("#poNumber").val().trim()!="") && (jQuery("#creditCardNo").val().trim()==null || jQuery("#creditCardNo").val().trim()=="")){
						jQuery("#paymentMethod").val('Purchase Order');
					}
				}else{
					jQuery("#paymentMethod").val('Purchase Order');
				}
			}else{
				if(jQuery("#creditCardNo").length!=0){
					jQuery("#paymentMethod").val('Credit Card');
				}
			}
			
			if(jQuery("#creditCardNo").length!=0 && jQuery("#creditCardNo").val()!=""){
				jQuery("#creditCardEncryptedNo").val(jQuery("#creditCardNo").val());
				jQuery("#creditFlag").val("true");
			}
			if(jQuery("#creditCardType").val()!=''){
				if(jQuery("#creditCardType").val() == '001'){
					jQuery("#creditCardType").val('Visa');
				}
				if(jQuery("#creditCardType").val() == '002'){
					jQuery("#creditCardType").val('MasterCard');
				}
				if(jQuery("#creditCardType").val() == '003'){
					jQuery("#creditCardType").val('American Express');
				}
				if(jQuery("#creditCardType").val() == '004'){
					jQuery("#creditCardType").val('Discover');
				}
			}
			jQuery("#assetDetailPageForm").submit();
		}	
}
/*function onCancelClick() {
	window.location.href = "<portlet:renderURL></portlet:renderURL>";
};*/
/***********This is for the back button**************/
function goBack()
{
	var url='<portlet:renderURL><portlet:param name="action" value="goToAssetDetail"></portlet:param></portlet:renderURL>';
	//jQuery("#assetDetailPageForm").attr("action", url);
	//jQuery("#assetDetailPageForm").submit();
	window.location.href = url;
}
/******************end****************************/
	function openAttachment(fileName){
		//alert("displayAttachment12:: "+ '${displayAttachment}');
		window.location="${displayAttachment}&fileName="+fileName;
		
	}
    image_error= function () {
		
		  document.getElementById("orderImage").src = '<html:imagesPath/>dummy_noimg.jpg'; 
		  
		};
		
	jQuery(document).ready( function() {
		 var offsetMinute = new Date().getTimezoneOffset();
		  var timezoneOffset = (offsetMinute/60);
		  jQuery('#timezoneOffset').val(timezoneOffset);
		jQuery("#poNumber1").val('${assetDetailPageForm.asset.poNumber}')
		jQuery("#poNumber2").val('${assetDetailPageForm.asset.poNumber}')
		jQuery('#pageCountValue').val('${pageCountsOriginalval}');
		jQuery('#pageCountDateValid').val('${pageCountsOriginaldate}');
		jQuery('#pageCountInfo').hide();
		
			textScroll(document.getElementById('spclinstru'));
			textScroll(document.getElementById('defaultspclinstru'));
			<%-- Changes for JAN release--%>
			textScroll(document.getElementById('partsToBeInstalled'));
			<%-- Changes for JAN release Ends--%>
			
			//Changes for MPS 2.1
			var splitterFlag = "${assetDetailPageForm.splitterFlag}";
	   		if(splitterFlag == "true"){
				decidePaymentTypeDisplay();
	   		}else{
	   			jQuery('#paymentBlockPO').show();
	   		}
	   		
	   		
	   		addPickupAddress("${assetDetailPageForm.shipToAddress.addressId}","${assetDetailPageForm.shipToAddress.addressName}","${fn:escapeXml(assetDetailPageForm.shipToAddress.addressLine1)}","${fn:escapeXml(assetDetailPageForm.shipToAddress.addressLine2)}","${assetDetailPageForm.shipToAddress.city}","${assetDetailPageForm.shipToAddress.state}","${assetDetailPageForm.shipToAddress.province}",
	   				"${assetDetailPageForm.shipToAddress.country}","${assetDetailPageForm.shipToAddress.postalCode}","${assetDetailPageForm.shipToAddress.storeFrontName}","${assetDetailPageForm.shipToAddress.userFavorite}","${assetDetailPageForm.shipToAddress.officeNumber}","${assetDetailPageForm.shipToAddress.district}","${assetDetailPageForm.shipToAddress.county}","${assetDetailPageForm.shipToAddress.countryISOCode}","${assetDetailPageForm.shipToAddress.region}","${assetDetailPageForm.shipToAddress.stateCode}");
			
		});
	
	/*Method gotoControlPanel added for Defect 3924- Jan Release*/
	function gotoControlPanel(url) {
		 callOmnitureAction('<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESREVIEWASSET%>','<%=LexmarkSPOmnitureConstants.ORDERSUPPLIESREVIEWIPADDRESSHYPERLINK%>');
		 controlPanelPopUpWindow.show();
		 controlPanelPopUpWindow.io.set('uri',"<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='gotoControlPanel' /></portlet:renderURL>&controlPanelURL=" + url);
		 controlPanelPopUpWindow.io.start();
<%--
	    	new Liferay.Popup({
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
    };
    
    /*
     * Confirm Bill To address change
     */
    function confirmAddressChange(hyperlinkId){
    	jConfirm("<spring:message code='requestInfo.message.newCardAuthorize'/>", "", function(confirmResult) {
    		if(confirmResult){
    			jQuery('#creditCardNo').html('');
    			jQuery('#creditCardEncryptedNo').val('');
    			jQuery('#creditCardToken').val('');
    			jQuery('#creditCardExpirationDate').val('');
    			jQuery('#creditCardType').val('');
    			popupAddress(hyperlinkId);
    		}
    	});
    }

    /*
     * Confirm copy of Ship To Address To Bill To Address
     */
    function confirmAddressCopy(){
    	jConfirm("<spring:message code='requestInfo.message.newCardAuthorize'/>", "", function(confirmResult) {
    		
    		if(confirmResult){
    			jQuery('#creditCardNo').html('');
    			jQuery('#creditCardEncryptedNo').val('');
    			jQuery('#creditCardToken').val('');
    			jQuery('#creditCardExpirationDate').val('');
    			jQuery('#creditCardType').val('');
    			copyShipAddressToBillTo();
    		}else{
    			jQuery('#sameAsShiptoCheckBox').attr('checked', false);
    		}
    	});
    }
    
    /*
     * Open the select address popup
     */
    function popupAddress(hyperlinkId){
    	//alert('in popup address');
    	linkId=hyperlinkId;//This link id is for displaying inner htmls in address
    	//alert(linkId);
    	var addressFlag = "${assetDetailPageForm.addressFlag}";
    	showOverlay();
    	//alert('addressFlag is '+addressFlag);
    	var url="${addressListPopUpUrl}";
    	url+="&addressFlag="+addressFlag;
    	jQuery('#dialogAddress').load(url,function(){
    	//alert(jQuery('#'+hyperlinkId).attr('href'));	
    		dialog=jQuery('#content_addr').dialog({
    			autoOpen: false,
    			title: jQuery('#'+hyperlinkId).attr('title'),
    			modal: true,
    			draggable: false,
    			resizable: false,
    			height: 460,
    			width: 850,
    			close: function(event,ui){
    				hideOverlay();
    				dialog=null;
    				jQuery('#content_addr').remove();
    				jQuery('#totalContentCredit').show();
    				}
    			});
    		jQuery('#totalContentCredit').hide();
    		dialog.dialog('open');
    		initializeAddressGrid();
    		
    		});
    	return false;
    }
    
    
    
    
  //Close the select address popup
	function closeDialog()
	{
		dialog.dialog('close');			
		dialog=null;
		jQuery('#totalContentCredit').show();
	}
  
	//Changes for MPS 2.1
	function decidePaymentTypeDisplay(){
		var finalCreditFlag ="${assetDetailPageForm.finalCreditFlag}";
		var finalPOFlag ="${assetDetailPageForm.finalPOFlag}";
		//alert(finalCreditFlag);
		//alert(finalPOFlag);
		if(finalCreditFlag=="true" && finalPOFlag=="true"){
			//alert("show PO and CC");
			jQuery('#paymentBlock').show();
		}else if(finalCreditFlag!="true"){
			//alert("show Po");
			jQuery('#paymentBlockPO').show();
		}
	
	}
	
	function copyShipAddressToBillTo(){
		
		
		var checkFlag = jQuery('#sameAsShiptoCheckBox').is(':checked');
		
		//alert(checkFlag);
		if(checkFlag){
			jQuery('#billToStoreFrontName').val("${assetDetailPageForm.shipToAddress.storeFrontName}");
			jQuery('#billToAddressLine1').val('${fn:escapeXml(assetDetailPageForm.shipToAddress.addressLine1)}');
			jQuery('#billToAddressLine2').val("${fn:escapeXml(assetDetailPageForm.shipToAddress.addressLine2)}");
			jQuery('#billToAddressCity').val("${assetDetailPageForm.shipToAddress.city}");
			jQuery('#billToAddressState').val("${assetDetailPageForm.shipToAddress.state}");
			jQuery('#billToAddressProvince').val("${assetDetailPageForm.shipToAddress.province}");
			jQuery('#billToAddressPostCode').val("${assetDetailPageForm.shipToAddress.postalCode}");
			jQuery('#billToAddressCountry').val("${assetDetailPageForm.shipToAddress.country}");
			//Added for rest of the cleansed fields
			jQuery('#billToAddresscounty').val("${assetDetailPageForm.shipToAddress.county}");
			jQuery('#billToAddressofficeNumber').val("${assetDetailPageForm.shipToAddress.officeNumber}");
			jQuery('#billToAddressstateCode').val("${assetDetailPageForm.shipToAddress.stateCode}");
			jQuery('#billToAddressstateFullName').val("${assetDetailPageForm.shipToAddress.stateFullName}");
			jQuery('#billToAddressdistrict').val("${assetDetailPageForm.shipToAddress.district}");
			jQuery('#billToAddressregion').val("${assetDetailPageForm.shipToAddress.region}");
			jQuery('#billToAddresslatitude').val("${assetDetailPageForm.shipToAddress.latitude}"); 
			jQuery('#billToAddresslongitude').val("${assetDetailPageForm.shipToAddress.longitude}");
			jQuery('#billToAddresscountryISOCode').val("${assetDetailPageForm.shipToAddress.countryISOCode}"); 
			jQuery('#billToAddresssavedErrorMessage').val("${assetDetailPageForm.shipToAddress.savedErrorMessage}");
			jQuery('#billToAddressisAddressCleansed').val("${assetDetailPageForm.shipToAddress.isAddressCleansed}"); 
			//Ends

			
			//done for 12941	
			jQuery("#billToStoreFrontNameLbl").html("${assetDetailPageForm.shipToAddress.storeFrontName}");
			jQuery("#billToAddressLine1Lbl").html('${fn:escapeXml(assetDetailPageForm.shipToAddress.addressLine1)}');		
			jQuery("#billToAddressLine2Lbl").html("${fn:escapeXml(assetDetailPageForm.shipToAddress.addressLine2)}"+',');	
			<%--Added for MPS 2.1--%>
		
			jQuery("#billToofficeNumberLbl").html('${assetDetailPageForm.shipToAddress.officeNumber}');
			
			<%--Ends--%>	
			jQuery("#billToAddressCityLbl").html('${assetDetailPageForm.shipToAddress.city}');
			jQuery("#billToAddressStateLbl").html(',&nbsp;'+'${assetDetailPageForm.shipToAddress.state}');
			if(${assetDetailPageForm.shipToAddress.province != '' && assetDetailPageForm.shipToAddress.province!=null}){
				jQuery("#billToAddressProvinceLbl").html(',&nbsp;'+"${assetDetailPageForm.shipToAddress.province}");
				jQuery("#billToAddressProvinceLbl").show();
				}
			if(${assetDetailPageForm.shipToAddress.county != '' && assetDetailPageForm.shipToAddress.county!=null}){
				jQuery("#billToAddresscountyLbl").html(',&nbsp;'+'${assetDetailPageForm.shipToAddress.county}');
				jQuery("#billToAddresscountyLbl").show();
				}
			// region changed to district for MPS 2.1
			if(${assetDetailPageForm.shipToAddress.district != '' && assetDetailPageForm.shipToAddress.district!=null}){
				jQuery("#billToAddressRegionLB").html(',&nbsp;'+"${assetDetailPageForm.shipToAddress.district}"); // Modified for the CR CHG0004596 AMS 15.11 Release 
				jQuery("#billToAddressRegionLB").show();
				}

			if(${assetDetailPageForm.shipToAddress.province == '' || assetDetailPageForm.shipToAddress.province==null}){
				jQuery("#billToAddressProvinceLbl").hide();
				}
			if(${assetDetailPageForm.shipToAddress.county == '' || assetDetailPageForm.shipToAddress.county==null}){
				jQuery("#billToAddresscountyLbl").hide();
				}
			// region changed to district for MPS 2.1
			if(${assetDetailPageForm.shipToAddress.district == '' || assetDetailPageForm.shipToAddress.district==null}){
				jQuery("#billToAddressRegionLB").hide();
				}
			jQuery("#billToAddressPostCodeLbl").html('${assetDetailPageForm.shipToAddress.postalCode}');		
			jQuery("#billToAddressCountryLbl").html('${assetDetailPageForm.shipToAddress.country}');
			
		}
		else{
			jQuery('#billToStoreFrontName').val('');
			jQuery('#billToAddressLine1').val('');
			jQuery('#billToAddressLine2').val(null);
			jQuery('#billToAddressCity').val(null);
			jQuery('#billToAddressState').val(null);
			jQuery('#billToAddressProvince').val(null);
			jQuery('#billToAddressPostCode').val(null);
			jQuery('#billToAddressCountry').val(null);
			//Added for rest of the cleansed fields
			jQuery('#billToAddresscounty').val('');
			jQuery('#billToAddressofficeNumber').val('');
			jQuery('#billToAddressstateCode').val('');
			jQuery('#billToAddressstateFullName').val('');
			jQuery('#billToAddressdistrict').val('');
			jQuery('#billToAddressregion').val('');
			jQuery('#billToAddresslatitude').val(''); 
			jQuery('#billToAddresslongitude').val('');
			jQuery('#billToAddresscountryISOCode').val(''); 
			jQuery('#billToAddresssavedErrorMessage').val('');
			jQuery('#billToAddressisAddressCleansed').val('false'); 
			//Ends
					
			jQuery("#billToStoreFrontNameLbl").html('');
			jQuery("#billToAddressLine1Lbl").html('');		
			jQuery("#billToAddressLine2Lbl").html('');		
			jQuery("#billToAddressCityLbl").html('');
			jQuery("#billToAddressStateLbl").html('');
			jQuery("#billToAddressProvinceLbl").html('');
			jQuery("#billToAddressPostCodeLbl").html('');		
			jQuery("#billToAddressCountryLbl").html('');
			<%--Added for MPS 2.1--%>
			jQuery("#billToofficeNumberLbl").html('');
			jQuery("#billToAddressRegionLB").html('');
			jQuery("#billToAddresscountyLbl").html('');
			<%--Ends--%>	
			
		}
		
		
	}
	<%--Changes for Phase 2.1--%>
	function addServiceAddressElement(addressId,addressName,addressLine1,addressLine2, 
			addressCity,addressState,addressProvince,addressCountry,addressPostCode,storefrontName,physicalLoc1,physicalLoc2,physicalLoc3,favorite,officeNumber,district,county,countryISO,region,stateCode)
	<%--Ends--%>
	{
		//alert('addressProvince '+addressProvince);
		<%-- Changes MPS 2.1--%>
		if(addressId==null || addressId=="")
			jQuery('#billToAddressisAddressCleansed').val("false");
		else
			jQuery('#billToAddressisAddressCleansed').val("true");
		<%-- ENDS Changes MPS 2.1--%>
			addressChangeReq=true;
			if(linkId=="diffAddressLink")
			{
				
				//call to addConsumablesAddress in the commonAsset.js
				<%--Changes for Phase 2.1--%>
				addPickupAddress(addressId,addressName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
						addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode);
				<%--Ends--%>
			}
			closeDialog();
	}
	<%--Changes for Phase 2.1--%>
	function addPickupAddress(addressId,storeFrontName,addressLine1,addressLine2,addressCity,addressState,addressProvince,
			addressCountry,addressPostCode,storefrontName,favorite,officeNumber,district,county,countryISO,region,stateCode)
	<%--Ends--%>
	{	
		if(addressProvince == null){
			addressProvince = '';
		}
		if(addressId==null){
			addressId = '-1';
		}	
		
		jQuery("#pickupAddressId").val(addressId);
		jQuery("#pickupStoreFrontName").val(storefrontName);
//	 	addressLine1 = addressLine1.replace(/'/g, "\\'");
		jQuery("#pickupAddressLine1").val(addressLine1);		
		jQuery("#pickupAddressLine2").val(addressLine2);
				
		jQuery("#pickupAddressCity").val(addressCity);
		jQuery("#pickupAddressState").val(addressState);
		jQuery("#pickupAddressProvince").val(addressProvince);
		jQuery("#pickupAddressPostCode").val(addressPostCode);
		jQuery("#pickupAddressCountry").val(addressCountry);
		<%--Changes for Phase 2.1--%>
		jQuery("#pickupAddressofficeNumber").val(officeNumber);
		jQuery("#pickupAddressdistrict").val(district);
		jQuery("#pickupAddresscounty").val(county);
		jQuery("#pickupAddressregion").val(region);
		jQuery("#pickupAddresscountryISOCode").val(countryISO);
	    jQuery("#pickupAddressstateCode").val(stateCode);
		<%--Ends--%>
		
		//This is for display purpose pickup address
		jQuery("#pickupStoreFrontNameLbl").html(storefrontName);
		jQuery("#pickupStoreFrontNameLblParent").html(storefrontName);
		jQuery("#pickupAddressLine1Lbl").html(addressLine1);
		jQuery("#pickupAddressLine1LblParent").html(addressLine1);
		if(addressLine2!='' && addressLine2!=' ' && addressLine2!=null){
			jQuery("#pickupAddressLine2Lbl").html(addressLine2+',');
			jQuery("#pickupAddressLine2Lbl").show();
			jQuery("#pickupAddressLine2LblParent").html(addressLine2+',');
			jQuery("#pickupAddressLine2LblParent").show();
			}
		if(addressCity!='' && addressCity!=' ' && addressCity!=null){
			jQuery("#pickupAddressCityLbl").html(addressCity);
			jQuery("#pickupAddressCityLblParent").html(addressCity);
			}
		if(county!='' && county!=' ' && county!=null){
			jQuery("#pickupAddresscountyLbl").html(',&nbsp;'+county);
			jQuery("#pickupAddresscountyLbl").show();
			jQuery("#pickupAddresscountyLblParent").html(',&nbsp;'+county);
			jQuery("#pickupAddresscountyLblParent").show();
			}
		if(addressProvince!='' && addressProvince!=' ' && addressProvince!=null){
			jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+addressProvince);
			jQuery("#pickupAddressProvinceLbl").show();
			jQuery("#pickupAddressProvinceLblParent").html(',&nbsp;'+addressProvince);
			jQuery("#pickupAddressProvinceLblParent").show();
			}
		// region changed to district for MPS 2.1
		if(district!='' && district!=' ' && district!=null){
			jQuery("#pickupAddressRegionLB").html(',&nbsp;'+district);
			jQuery("#pickupAddressRegionLB").show();
			jQuery("#pickupAddressRegionLBParent").html(',&nbsp;'+district);
			jQuery("#pickupAddressRegionLBParent").show();
			}
		if(stateCode!='' && stateCode!=' ' && stateCode!=null){
			jQuery("#pickupAddressStateLbl").html(',&nbsp;'+stateCode);
			jQuery("#pickupAddressStateLbl").show();
			jQuery("#pickupAddressStateLblParent").html(',&nbsp;'+stateCode);
			jQuery("#pickupAddressStateLblParent").show();
			}
		if(addressProvince!='' && addressProvince!=' ' && addressProvince!=null){
		jQuery("#pickupAddressProvinceLbl").html(',&nbsp;'+addressProvince);
		jQuery("#pickupAddressProvinceLbl").show();
		jQuery("#pickupAddressProvinceLblParent").html(',&nbsp;'+addressProvince);
		jQuery("#pickupAddressProvinceLblParent").show();
		}
		jQuery("#pickupAddressPostCodeLblParent").html(addressPostCode);		
		jQuery("#pickupAddressCountryLblParent").html(addressCountry);
		
		if(county=='' || county==' ' || county==null){
			jQuery("#pickupAddresscountyLbl").hide();
			jQuery("#pickupAddresscountyLblParent").hide();
			}
		if(addressProvince=='' || addressProvince==' ' || addressProvince==null){
			jQuery("#pickupAddressProvinceLbl").hide();
			jQuery("#pickupAddressProvinceLblParent").hide();
			}
		// region changed to district for MPS 2.1
		if(district=='' || district==' ' || district==null){
			jQuery("#pickupAddressRegionLB").hide();
			jQuery("#pickupAddressRegionLBParent").hide();
			}
		if(stateCode=='' || stateCode==' ' || stateCode==null){
			jQuery("#pickupAddressStateLbl").hide();
			jQuery("#pickupAddressStateLblParent").hide();
			}
		if(addressLine2=='' || addressLine2==' ' || addressLine2==null){
			jQuery("#pickupAddressLine2Lbl").hide();
			jQuery("#pickupAddressLine2LblParent").hide();
			
			}
		jQuery("#pickupAddressofficeNumberLbl").html(officeNumber);
		jQuery("#pickupAddressofficeNumberLblParent").html(officeNumber);
		
		
		
		var addressValues={};

		addressValues["addressLine1"]=addressLine1;
		addressValues["officeNumber"]=officeNumber;
		addressValues["addressLine2"]=addressLine2;
		addressValues["city"]=addressCity;
		addressValues["county"]=county;
		addressValues["state"]=addressState;
		addressValues["province"]=addressProvince;
		addressValues["district"]=district;
		addressValues["postalCode"]=addressPostCode;
		addressValues["country"]=addressCountry;

		var html="<div>"+storefrontName+"</div>"+generateAddressDisplayHTML(addressValues);
		jQuery('#adddressLiHTML').html(html);
		
			
	}
	//Added for MPS 2.1 Extra fields for address cleansing
	function addRestFieldsOfCleanseAddress(){
		jQuery('#billToAddressisAddressCleansed').val("true");
		<%for(String cleanseOutputFields:ADDRESSCLEANSEFIELDS_OUTPUT){%>
			jQuery("#billToAddress<%=cleanseOutputFields%>").val(cleaseAddressFields["<%=cleanseOutputFields%>"]);
		<%}%>
		
		jQuery('#billToofficeNumberLbl').html(jQuery('#billToAddressofficeNumber').val());
		//Added for MPS 2.1
		if(jQuery('#billToAddresscounty').val() != '' && jQuery('#billToAddresscounty').val() != ' ' && jQuery('#billToAddresscounty').val() != null){  // added for MPS 2.1 defect no 8387
		jQuery("#billToAddresscountyLbl").html(',&nbsp;'+jQuery('#billToAddresscounty').val());
		jQuery("#billToAddresscountyLbl").show();
		}
		if(jQuery('#billToAddresscounty').val() == '' || jQuery('#billToAddresscounty').val() == ' ' || jQuery('#billToAddresscounty').val() == null){
			jQuery("#billToAddresscountyLbl").hide();
			}
		//jQuery("#billToAddressdistrictLbl").html(jQuery('#billToAddressdistrict').val());
		//END
		}	
	//Ends
	function updateCreditInfo(){
		popupCreditDialog("creditCardNo");
	}
	function popupCreditDialog(creditId){
		var creditCurrency = "${creditCurrency}";
		var shipToCountryName = "${assetDetailPageForm.shipToAddress.country}";
		var shipToCountryCode = "${assetDetailPageForm.shipToAddress.countryISOCode}";
		var state="";
		var country="";
		if(jQuery('#billToAddressregion').val()!=null && jQuery('#billToAddressregion').val()!=''){
			state = jQuery('#billToAddressregion').val();
		}else if(jQuery('#billToAddressState').val()!=null && jQuery('#billToAddressState').val()!=''){
			state = jQuery('#billToAddressState').val();
		}
		else if(jQuery('#billToAddressProvince').val()!=null && jQuery('#billToAddressProvince').val()!=''){
			state = jQuery('#billToAddressProvince').val();
		}
		
		if(jQuery('#billToAddresscountryISOCode').val()!=null && jQuery('#billToAddresscountryISOCode').val()!=''){
			country = jQuery('#billToAddresscountryISOCode').val();
		}
		var address_name = encodeURIComponent(jQuery('#billToStoreFrontName').val());

		
		var address_line1 = encodeURIComponent(jQuery('#billToAddressLine1').val());
		//alert("address_line1 "+ address_line1);
		
		var address_line2 = encodeURIComponent(jQuery('#billToAddressLine2').val());
		
		var address_office = encodeURIComponent(jQuery('#billToAddressofficeNumber').val());
		
		var address_city = encodeURIComponent(jQuery('#billToAddressCity').val());
		//alert("address_city "+ address_city);
		var address_state = encodeURIComponent(state);
		var address_country = encodeURIComponent(country);
		var address_postal_code = encodeURIComponent(jQuery('#billToAddressPostCode').val());
		
		var dispCountry=jQuery('#billToAddressCountry').val();
		dispCountry = encodeURIComponent(dispCountry);
		var county=encodeURIComponent(jQuery('#billToAddresscounty').val());
		var province=encodeURIComponent(jQuery('#billToAddressProvince').val());
		var district=encodeURIComponent(jQuery('#billToAddressdistrict').val());
		
		
		
		
		var url = "${creditCardPopUpUrl}";
		url=url+'&address_line1='+address_line1+'&address_city='+address_city+'&address_state='+address_state+'&address_country='+address_country+'&address_postal_code='+address_postal_code+'&currency='+creditCurrency+'&address_line2='+address_line2+'&address_office='+address_office+'&address_name='+address_name+'&dispCountry='+dispCountry+'&county='+county+'&province='+province+'&district='+district+'&shipToCountryName='+shipToCountryName+'&shipToCountryCode='+shipToCountryCode;
			showOverlay();
			/*alert('before ajax call');*/
			jQuery('#creditcardPageData').load(url,function(){
				/*alert('after ajax call');*/
				dialog=jQuery('#totalContentCredit').dialog({
					autoOpen: false,
					title: 'Credit Card Payment',
					modal: true,
					height: 460,
					width: 700,
					open: function(){
						showToolTipInPopup();
						jQuery('#totalContentCredit').show();					
					},
					position: ['center','top'],
					close: function(event,ui){
							/*alert('in close dialog of createnew');*/
							
						 hideOverlay();
						 //dialog.dialog('destroy');					 
						 dialog=null;
						 jQuery('#totalContentCredit').remove();
						 //jQuery('#creditCard').val('');
						 //jQuery('#creditcardPageData').empty();
						 
						}
					});
				//jQuery(document).scrollTop(0);
				dialog.dialog('open');
				});
			
			return false;
		}
function removeValidation(){
		
		jQuery('#poNumber2').removeClass('errorColor');
		jQuery('#validationErrors').html("");
		jQuery('#validationErrors').hide();
	 }
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


</script>