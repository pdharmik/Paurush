<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<portlet:renderURL var="confirmCatalogOrderURL">
	<portlet:param name="action" value="confirmCatalogOrder"/>
</portlet:renderURL>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include>
<div class="main-wrap">
	<div class="content">
		<div id="content-wrapper">
			<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.suppliesRequests"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.orderSupplies"/></h2>
			</div><!-- End journal-content-article -->
			 <%--Changes for BRD12 CI 7 --%>
        <div id="accNameAgreeName">
        <h3><strong>
        <jsp:include page="/WEB-INF/jsp/common/commonAccountAgreementNameInfo.jsp"></jsp:include>
        </strong>
        </h3>
        
        </div> 
        <%--Ends Changes for BRD12 CI 7 --%>
			<div class="portletBlock">
				<div class="portletBlock center">
					<ul class="processSteps shadow">
						<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step1SelectItems"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.message.selectItems"/></a></li>
						<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
						<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
						<li class="selected last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
					</ul>
				</div><!-- End portletBlock center -->
			</div><!-- portletBlock -->
			<div class="portletBodyWrap">
				<form:form modelAttribute="catalogDetailPageForm" commandName="catalogDetailPageForm">
			 <h3 class="pageTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.orderConfirmation"/></h3>	
				  <div class="utilities printPage floatR">
			          <ul>
			            <li class="first"><img src="<html:imagesPath/>transparent.png" class="ui_icon_sprite email-icon cursor-pointer" alt="<spring:message code='requestInfo.alt.emailThisPage'/>" title="<spring:message code='requestInfo.alt.emailThisPage'/>" onClick="return email();hideSelect();" /></li>
			            <li> <img src="/LexmarkServicesPortal/images/transparent.png" class="ui_icon_sprite print-icon cursor-pointer" alt="<spring:message code='requestInfo.alt.printThisPage'/>" title="<spring:message code='requestInfo.alt.printThisPage'/>" onClick="return print()"/></li>
			          </ul>
			      </div>	
        
 <div id="confirmOrderPrintWrapper">
 	<c:if test="${serviceRequestNumber == null || serviceRequestNumber == ''}">
		<p class="info banner err"><spring:message code="requestInfo.error.unableToAccept"/></p>
	</c:if>
	<c:if test="${serviceRequestNumber != null && serviceRequestNumber != ''}">
		<p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/><span id="reqNumber"> ${serviceRequestNumber}</span> <spring:message code="requestInfo.message.confirmMessage2"/></p>
	</c:if>			
<%@ include file="/WEB-INF/jsp/ordermanagement/catalogOrder/commonContactInfoReviewForCatalogOrder.jsp"%>				
			
<!-- Parts Detail Start -->
	<hr class="separator" />
        <p class="inlineTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></p> 
        <div class="columnInner" id="partTableDiv">
        <div class="wFull displayGrid columnInnerTable rounded shadow">
          <table>
						<thead>
							<tr>
							 	<th class="w100"><spring:message code="requestInfo.heading.partNumber"/></th>
								<th><spring:message code="requestInfo.heading.description"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.partType"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.yield"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.model"/></th>
								<th class="w100 right"><spring:message code="requestInfo.heading.orderQuantity"/></th>
								<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
									<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
										<th class="w100 right"><spring:message code="requestInfo.heading.price"/></th>
										<th class="w100 right"><spring:message code="requestInfo.heading.total"/></th>
									</c:if>	
								</c:if>							
							</tr>
						</thead>
						<tbody>	  

						<c:forEach items="${reviewCatalogOrderForm.catalogPartList}" var="catalogPartListDetail" varStatus="counter" begin="0">
								<c:choose>
									<c:when test="${counter.count % 2 == 0}">
									   <tr class="altRow">
									</c:when>
									<c:otherwise>
									   <tr>
									</c:otherwise>
								</c:choose>
									<td class="w100">${catalogPartListDetail.partNumber}</td>
									<td>${catalogPartListDetail.partDesc}</td>
									<td class="w100">${catalogPartListDetail.partType}</td>
									<td class="w100">${catalogPartListDetail.yield}</td>
									<td class="w100">${catalogPartListDetail.model}</td>
									<td class="w100 right">${catalogPartListDetail.orderQuantity}</td>
									<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
										<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
											<td class="w100 right">${catalogPartListDetail.unitPrice} (${catalogPartListDetail.currency})</td>
											<td class="w100 right">${catalogPartListDetail.total} (${catalogPartListDetail.currency})</td>		
										</c:if>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
        	   		</table>
        	   		</div>
        	   		<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
	        	   		<c:if test="${sessionScope.catalogFinalFlags['finalTaxCalcFlag'] == 'true' && sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}"> 
	  	   					<div class="lineClear"></div>
	  	   					<div class="floatR w250">
							    <table>
							    <tr height="25">
							    	<td class="right w150 strong"><spring:message code="requestInfo.label.partsPriceSubTotal"/></td>
							    	<td class="right w110">${catalogDetailPageForm.subTotal}</td>
							    </tr>
							    <tr height="25">
							    	<td class="right strong">
							    	<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.label.partsTaxesEstimated"/>' >&nbsp; <%-- Added on 11June --%>
							    	<spring:message code="requestInfo.label.partsTaxes"/></td>
							    	<c:choose>
								    	<c:when test="${catalogDetailPageForm.tax == 'Unavailable'}">
								    		<td class="right"><spring:message code="requestInfo.tax.unavailable"/></td>
								    	</c:when>
								    	<c:otherwise>
								    		<td class="right">${catalogDetailPageForm.tax}</td>
								    	</c:otherwise>
								    </c:choose>
							    </tr>
							    <tr height="25">
							    	<td class="right strong"><spring:message code="requestInfo.label.partsTotalAmountPayable"/></td>
							    	<td class="right">${catalogDetailPageForm.totalAmt}</td>
							    </tr>
							    </table>
						    </div>
						</c:if>
					</c:if>
				</div><!-- portlet-body-table table-left -->
			<!-- Parts Detail End -->
				<!-- Shipping & Billing Information Start -->	  
	 <p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
				  <div class="portletBlock">
					<div class="columnsTwo">
						<div class="columnInner rounded infoBox">
							<h4>
								<spring:message code="requestInfo.heading.shipToAddress"/>
							</h4>
							<div id="shipToAddrSpan">
							<ul class="roDisplay">
								<li><div>${catalogDetailPageForm.shipToAddress.storeFrontName}</div>
								<util:addressOutput value="${catalogDetailPageForm.shipToAddress}"></util:addressOutput>
													               <%--   <div>${catalogDetailPageForm.shipToAddress.addressLine1}</div>
													                  <div>${catalogDetailPageForm.shipToAddress.officeNumber}</div>
													                  <c:if test="${catalogDetailPageForm.shipToAddress.addressLine2 !='' && catalogDetailPageForm.shipToAddress.addressLine2!=null}">
													                  <div>${catalogDetailPageForm.shipToAddress.addressLine2}, </div>
													                  </c:if>
													                  <span>${catalogDetailPageForm.shipToAddress.city},
													                  <c:if test="${catalogDetailPageForm.shipToAddress.county != '' && catalogDetailPageForm.shipToAddress.county!=null}">
													                  ${catalogDetailPageForm.shipToAddress.county},
													                  </c:if>
													                  <c:if test="${catalogDetailPageForm.shipToAddress.stateCode != '' && catalogDetailPageForm.shipToAddress.stateCode != null}">
													                  ${catalogDetailPageForm.shipToAddress.stateCode}
													                  </c:if>
													                  <c:if test="${(catalogDetailPageForm.shipToAddress.province !='' && catalogDetailPageForm.shipToAddress.province !=' ') && catalogDetailPageForm.shipToAddress.province != null}">
													                  ,${catalogDetailPageForm.shipToAddress.province}
													                  </c:if>
													                  <%-- region changed to district for MPS 2.1 
													                  <c:if test="${(catalogDetailPageForm.shipToAddress.district !='' && catalogDetailPageForm.shipToAddress.district !=' ') && catalogDetailPageForm.shipToAddress.district != null}">
													                  ,${catalogDetailPageForm.shipToAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${catalogDetailPageForm.shipToAddress.postalCode}</div> 
													                  <div>${catalogDetailPageForm.shipToAddress.country}</div>--%>
															</li>
												
								
								<li><spring:message code="requestInfo.addressInfo.label.building"/> <span id="shipToAddr_building">${catalogDetailPageForm.shipToAddress.physicalLocation1}</span></li>
								<li><spring:message code="requestInfo.addressInfo.label.floor"/> <span id="shipToAddr_floor">${catalogDetailPageForm.shipToAddress.physicalLocation2}</span></li>
								<li><spring:message code="requestInfo.addressInfo.label.office"/><span id="shipToAddrOffice">${catalogDetailPageForm.shipToAddress.physicalLocation3}</span></li>
							</ul>
							</div>
						</div><!-- columnInner rounded infoBox -->
					</div><!-- columnsone -->
				 <div class="columnsTwo">
            		<div class="infoBox columnInner rounded shadow">
            				<h4><spring:message code="requestInfo.heading.orderInstructions"/></h4>
							<ul class="form">
								<li>
									<label for="desc2"><spring:message code="requestInfo.label.specialInstructions"/></label> 
									<span id="spclInstruction" class="textScroll">${catalogDetailPageForm.specialInstruction}</span>
								</li>
								<%-- <li>
									<label><spring:message code="requestInfo.label.defaultSpecialInstructions"/></label>
									<span id="defaultSpclInstruction" class="multiLine">${catalogDetailPageForm.defaultSpecialInstruction}</span>
								</li> --%>
								<%--
								Changes for JAN release Request Expedite order--%> 
								<c:if test="${catalogDetailPageForm.expediteOrderAllowed == 'true'}">
								<li>
									<label for="chck"><spring:message code="requestInfo.label.requestExpediteOrder"/></label> 
									<span id="reqExpdtOrder"> 
										<c:choose>
											<c:when test="${catalogDetailPageForm.requestExpediteOrder eq \"true\"}">
					                  					<spring:message code="requestInfo.option.yes"/>
					                  				</c:when>
					                  				<c:otherwise>
					                  					<spring:message code="requestInfo.option.no"/>
					                  				</c:otherwise>
					                  			</c:choose>
									</span>
								</li>
								</c:if>
								<%--
								Changes for JAN release Request Expedite order ENDS --%>
								<li>
									<label for="date1"><spring:message code="requestInfo.label.requestedDeliveryDate"/></label> 
									<span id="reqDeliveryDate"> ${catalogDetailPageForm.requestedDeliveryDate}</span>
								</li>
							</ul>
						</div><!-- columnInner rounded infoBox -->
					</div><!-- columnsTwo -->
				</div><!-- portletBlock -->
				<!-- Shipping & Billing Information End -->
				<!-- Payment Details Start -->
				<div class="portletBlock">
          <div class="columnsOne">
			 <div class="infoBox columnInner rounded shadow">
						<h4><spring:message code="requestInfo.heading.paymentDetails"/></h4>
							<c:choose>
							<c:when test="${creditFlag == 'true'}">
							<ul class="form">
								<li>
									<label for="ponum"><spring:message code="requestInfo.label.creditCardNo"/></label>									
										${catalogDetailPageForm.creditCardEncryptedNo} 									
								</li>
							</ul>
							</c:when>
							</c:choose>	
							<c:if test="${catalogDetailPageForm.poNumber ne null && not empty catalogDetailPageForm.poNumber}">
							<ul class="form">
								<li>
									 <label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label>									
										${catalogDetailPageForm.poNumber}  
								</li>
							</ul>
							</c:if>	
							
													
						</div><!-- columnsTwo -->
					</div><!-- columnInner rounded infoBox -->
				</div><!-- portletBlock -->
				<!-- Payment Details End -->
				<!-- Attachment Description Start -->
				<div id="div_attachmentInfoPrint">
				<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow bgSolid">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p id="notes" class="multiLine">${catalogDetailPageForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
				</div>
				<!-- Attachment Description End -->
				<!-- Attachment Start -->
				<div id="div_attachmentListPrint">
				<c:if test="${not empty displayAttachmentList}">
				<div class="portletBlock">
					<div class="wHalf displayGrid columnInnerTable rounded shadow" id="attachmentDetailsTable">
					
						<table>
						<thead><tr><th><spring:message code="requestInfo.heading.attachmentFileName"/></th><th class="w120 right"><spring:message code="requestInfo.heading.attachmentFileSizeKiloBytes"/></th></tr></thead>
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
				</c:if>
				<div id="attachmentFailed" class="error width-50per" style="display: none;">
				</div>
				</div>
				</div>
				<!-- Attachment End -->
			</form:form>
			<div id="attachmentHidden" style="display: none;">${attachmentException}</div>
			
		
		 <div class="filterLinks floatR">
		  <ul>
		    <li class="first"><a href="javascript:email();"><img class="ui_icon_sprite email-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.email"/>" height="23" width="27"/><spring:message code="requestInfo.title.emailThisPage"/></a></li>
		    <li><a href="javascript: print();"><img class="ui_icon_sprite print-icon" src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.title.print"/>" height="27" width="27"/><spring:message code="requestInfo.title.printThisPage"/></a></li>
		  </ul>
		</div>
		</div><!-- portletBodyWrap -->
		</div><!-- content-wrapper -->
	</div><!-- content -->
</div><!-- main-wrap -->
<script type="text/javascript">

jQuery(document).ready(function() {
	if("${attachmentException}"=="attachfailed"){
		jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
		 jQuery("#attachmentFailed").show();					
		}
});



function print(){
	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='catalogOrderConfirmPrint' /></portlet:renderURL>";         
    var iWidth=900;
    var iHeight=5600;
    var iTop = (window.screen.availHeight-30-iHeight)/2;        
    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
    window.open(url,
            'ConfirmCatalogOrder',
            'height='+iHeight+
            ',innerHeight='+
            iHeight+',width='+
            iWidth+',innerWidth='+
            iWidth+',top='+iTop+
            ',left='+iLeft+
            ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};

function email() {
	//alert("Catalog order email..");
	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='catalogOrderEmailConfirmationPage' /></portlet:renderURL>" ;
	
    var iWidth=1000;
    var iHeight=600;
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    window.open(url,
    		'historyList',
		    'height='+iHeight+
		    ',innerHeight='+
		    iHeight+',width='+
		    iWidth+',innerWidth='+
		    iWidth+',top='+iTop+
		    ',left='+iLeft+
		    ',status=no,toolbar=yes,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};

	function getEmailSubject(){
        var serviceRequestNumber = '12345';
        var emailSubject = "<spring:message code='message.orderSupplies.detail'/>"+"("+ serviceRequestNumber +")";
        return emailSubject;
    };
    
	function showSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "visible";
		} 
	}
</script>