<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css" />
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style>
  .positionUp2{position:relative;top:-70px;}/*Added on Aug06,2013*/
</style>
<![endif]-->
<script type="text/javascript">	
	<%@ include file="../../../../js/expand.js"%>
	jQuery(function() {
	    jQuery("h4.expand").toggler();
	});
</script>
<style>
.w100mod{width:100px!important;}
	.w200mod{width:200px!important;}
	.w250mod{width:250px!important;}
</style>
<% request.setAttribute("subTabSelected","createNewRequest"); %>
<jsp:include page="../../common/subTab.jsp"></jsp:include>
<div class="main-wrap">
	<div class="content">
		<div id="content-wrapper">
			<div class="journal-content-article">
				<h1><spring:message code="requestInfo.heading.hardwareRequest"/></h1>
				<h2 class="step"><spring:message code="requestInfo.heading.requestHardware"/></h2>
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
				<form:form modelAttribute="hardwareDetailPageForm" commandName="hardwareDetailPageForm">
			 <h3 class="pageTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.orderConfirmation"/></h3>	
				  <div class="utilities printPage floatR">
			          <ul>
			            <li class="first"><img src="<html:imagesPath/>/transparent.png" class="ui_icon_sprite email-icon" alt="<spring:message code="requestInfo.alt.emailThisPage"/>" title="<spring:message code="requestInfo.alt.emailThisPage"/>" onClick="return email();hideSelect();"; style="cursor:pointer";/></li>
			            <li> <img src="/LexmarkOPSPortal/images/transparent.png" class="ui_icon_sprite print-icon" alt="<spring:message code="requestInfo.alt.printThisPage"/>" title="<spring:message code="requestInfo.alt.printThisPage"/>" onClick="return print()"; style="cursor:pointer"/></li>
			          </ul>
			      </div>	
        
 <div id="confirmOrderPrintWrapper">
 	<c:if test="${serviceRequestNumber == null || serviceRequestNumber == ''}">
		<p class="info banner err"><spring:message code="requestInfo.error.unableToAccept"/></p>
	</c:if>
	<c:if test="${serviceRequestNumber != null && serviceRequestNumber != ''}">
		<p class="info banner ok"><spring:message code="requestInfo.message.confirmMessage1"/><span id="reqNumber"> ${serviceRequestNumber}</span> <spring:message code="requestInfo.message.confirmMessage2"/></p>
	</c:if>			
<%@ include file="/WEB-INF/jsp/ordermanagement/hardwareOrder/commonContactInfoReviewHardware.jsp"%>				
		
		
		 
			
<!-- Parts Detail Start -->
	<hr class="separator" />
        <p class="inlineTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.ItemsInyourOrder"/></p> 
        <c:if test="${not empty hardwareDetailPageForm.bundleList}">
        <div class="portletBlock infoBox rounded shadow">
        <div class="columnsOne">
	        <div class="columnInner">
	          <h4 class="expand"><spring:message code="requestInfo.heading.preConfhardware"/></h4>
	          <div class="collapse staticPos" id="preConf">
	          <div class="columnInner">       
          <table id="bundlesTab" class="displayGrid noBorder rounded shadow wFull">
          <c:forEach items="${hardwareDetailPageForm.bundleList}" var="hardwareBundleListDetail" varStatus="counter" begin="0">
          	<input type="hidden" id="bundleId[${counter.index}]" name="bundleId[${counter.index}]" value='${hardwareBundleListDetail.catalogId}'/>
            <tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>" id= "bundleRow${counter.index}">
              <td class="w300">
				${hardwareBundleListDetail.partDesc}
			  </td>
              <td class="w500">
				<div>
					<table class="displayGrid rounded shadow wFull w500" >
						<thead>
							<tr>
								<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.partNumber"/></th>
								<th><spring:message code="orderSupplies.placeOrderHeader.description"/></th>
								<th class="w100"><spring:message code="requestInfo.heading.Qty"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${hardwareBundleListDetail.bundlePartList}" var="bundlePart" varStatus="partCounter" begin="0">
							<tr class="<c:if test="${partCounter.index % 2 ne 0}">altRow</c:if>" id= "bundlePartRow${counter.index}">
								<td class="w100 borderTableLeft">${bundlePart.partNumber}</td>
								<td class="borderTableLeft">${bundlePart.description}</td>
								<td class="w100 borderTableLeft">${bundlePart.qty}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			  </td>
              <td class="w200">
              	<c:if test="${sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true'}">
					<c:choose>
	              		 <c:when test="${hardwareBundleListDetail.unitPrice ne '' && hardwareBundleListDetail.unitPrice ne null && hardwareBundleListDetail.unitPrice ne '0' && hardwareBundleListDetail.unitPrice ne '0.0' && hardwareBundleListDetail.unitPrice ne '0.00'}">
	              		 <c:set var="price" value="${fn:split(hardwareBundleListDetail.unitPrice, '.')}" />
	              		 <div><span class="f200">${price[0]}</span><span class="f150">${price[1]}</span> <span class="f200">(${hardwareBundleListDetail.currency})</span></div>
	              		 </c:when>
	              		 </c:choose>
				</c:if>
				<div class="lineClear" id="quantityId">
				<span style="font-weight:bold;font-size:14px;">Qty</span><span class="spaceClear"/>${hardwareBundleListDetail.orderQuantity}</td>
             
            </tr>
            </c:forEach>
          </table>
        </div>
	        </div>
	        </div>
	    </div>
	    </div>
	</c:if>
	<!-- Accessories List -->
	<c:if test="${not empty hardwareDetailPageForm.accessoriesList}">
	<div class="portletBlock infoBox rounded shadow">
	        <div class="columnInner">
	          <h4 class="expand"><spring:message code="requestInfo.heading.accessories"/></h4>
	          <div class="collapse staticPos" id="accessories">
	         <div class="columnInner">       
          <table id="accessoriesTab" class="displayGrid noBorder rounded shadow wFull">
          <c:forEach items="${hardwareDetailPageForm.accessoriesList}" var="hardwareAccessories" varStatus="counter" begin="0">
            	<tr class="<c:if test="${counter.index % 2 ne 0}">altRow</c:if>">
	              <td class="w100 w100mod">
	              	<c:if test="${hardwareAccessories.partImg ne ''}">
	 				 <img src="${hardwareAccessories.partImg}" alt="Change" width="100" height="100"> 
	 			    </c:if>
				  </td>
	              <td class="w500 w250mod">
					<ul class="form">
						<li>
							<label for="refId"><spring:message code="requestInfo.heading.hardwarePartNo"/></label>
							<span>${hardwareAccessories.partNumber}</span></li><li>
							<label for="refId"><spring:message code="requestInfo.label.description"/></label>
							<span>${hardwareAccessories.partDesc}</span></li>
					</ul>
				  </td>
				  <td class="w200mod">
					<ul class="form">
						<li><label for="refId"><spring:message code="requestInfo.heading.deviceType"/></label><span>${hardwareAccessories.partType}</span></li>
						<li><label for="refId"><spring:message code="requestInfo.label.model"/></label><span>${hardwareAccessories.model}</span></li></ul>
				  </td>
	              <td class="w200 w100mod">
	              	<c:if test="${sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true'}">
						<c:choose>
	              		 <c:when test="${hardwareAccessories.unitPrice ne '' && hardwareAccessories.unitPrice ne null}">
	              		 <c:set var="price" value="${fn:split(hardwareAccessories.unitPrice, '.')}" />
	              		 <div><span class="f200">${price[0]}</span><span class="f150">${price[1]}</span> <span class="f200">(${hardwareAccessories.currency})</span></div>
	              		 </c:when>
	              		 </c:choose>
					</c:if>
					<div class="lineClear"><spring:message code="requestInfo.heading.Qty"/><span class="spaceClear"/>${hardwareAccessories.orderQuantity}
				  </td>
             
            	</tr>
	            </c:forEach>
	          </table>
	        </div>	          
	        </div>
	        </div>
	    </div>
	    </c:if>
	    
		<c:if test="${sessionScope.hardwareFinalFlags['finalTaxCalcFlag'] == 'true' && sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true'}"> 
			<div class="lineClear"></div>
			<div class="floatR w250" style="height:90px;margin-right:11px;">
		    <table class="positionUp2">
		    <tr height="25">
		    	<td class="right w150 strong"><spring:message code="requestInfo.label.partsPriceSubTotal"/></td>
		    	<td class="right w110">${hardwareDetailPageForm.subTotal}</td>
		    	<form:hidden path="subTotal"/>
		    </tr>
		    <tr height="25">
		    	<td class="right strong">
		    	<img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title='<spring:message code="requestInfo.label.partsTaxesEstimated"/>'>&nbsp; <%-- Added on 11June --%>
		    	<spring:message code="requestInfo.label.partsTaxes"/></td>
		    	<c:choose>
			    	<c:when test="${hardwareDetailPageForm.tax == 'Unavailable'}">
			    		<td class="right"><spring:message code="requestInfo.tax.unavailable"/></td>
			    	</c:when>
			    	<c:otherwise>
			    		<td class="right">${hardwareDetailPageForm.tax}</td>
			    	</c:otherwise>
			    </c:choose>
		    	<form:hidden path="tax"/>
		    </tr>
		    <tr height="25">
		    	<td class="right strong"><spring:message code="requestInfo.label.partsTotalAmountPayable"/></td>
		    	<td class="right">${hardwareDetailPageForm.totalAmt}</td>
		    	<form:hidden path="totalAmt"/>
		    </tr>
		    </table>
	    </div>
	    </c:if>
		<!-- Parts Detail End -->
		<!-- Shipping & Billing Information Start -->	 
		<c:if test="${sessionScope.hardwareFinalFlags['finalShowPriceFlag'] == 'true' && sessionScope.hardwareFinalFlags['finalTaxCalcFlag'] == 'true'}"> 
			<div class="wFull" style="float:left;"><label style="color: #000000;margin:0px 10px;display: inline-block;float: left;font-weight: bold;line-height: 1.8em;"><spring:message code="requestInfo.label.hardwareReviewNote"/></label><span><i><spring:message code="requestInfo.message.hardwareTax"/></i></span></div>
		</c:if>
						
						<!-- Project fields -->
          
           
           <c:if test="${hardwareDetailPageForm.serviceRequest.project eq 'true'}">
            <div class="infoBox columnInner rounded shadow">
          
             <h4><spring:message code="ops.heading.project"/></h4>
            
            <ul class="roDisplay">
                  <c:if test="${hardwareDetailPageForm.serviceRequest.projectName ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectName"/></label>
                    <span id="projectName">${hardwareDetailPageForm.serviceRequest.projectName} </span></li>
                  </c:if>
                  <c:if test="${hardwareDetailPageForm.serviceRequest.projectPhase ne ''}">
                  <li>
                    <label><spring:message code="requestInfo.label.projectPhase"/></label>
                    <span id="projectPhase">${hardwareDetailPageForm.serviceRequest.projectPhase}</span></li>
                   </c:if>
                   </ul>
            
            </div>
            
            
          </c:if>
						
						
						<div style="clear:both"></div>
	 <p class="inlineTitle"><spring:message code="requestInfo.heading.shippingInformation"/></p>
				  <div class="portletBlock" id="hwShipToAddr">
					<div class="columnsTwo">
						<div class="columnInner rounded infoBox">
							<h4>
								<spring:message code="requestInfo.heading.shipToAddress"/>
							</h4>
							<div id="shipToAddrSpan">
							<ul class="roDisplay">
								<li><div>${hardwareDetailPageForm.shipToAddress.storeFrontName}</div>
								<util:addressOutput value="${hardwareDetailPageForm.shipToAddress}"></util:addressOutput>
													                <%--  <div>${hardwareDetailPageForm.shipToAddress.addressLine1}</div>
													                  <div>${hardwareDetailPageForm.shipToAddress.officeNumber}</div>
													                  <c:if test="${hardwareDetailPageForm.shipToAddress.addressLine2 !='' && hardwareDetailPageForm.shipToAddress.addressLine2!=null}">
													                  <div>${hardwareDetailPageForm.shipToAddress.addressLine2}, </div>
													                  </c:if>
													                  <div>${hardwareDetailPageForm.shipToAddress.addressLine3}</div>
													                  <span>${hardwareDetailPageForm.shipToAddress.city},
													                  <c:if test="${hardwareDetailPageForm.shipToAddress.county != '' && hardwareDetailPageForm.shipToAddress.county!=null}">
													                  ${hardwareDetailPageForm.shipToAddress.county},
													                  </c:if>
													                  <c:if test="${hardwareDetailPageForm.shipToAddress.stateCode != '' && hardwareDetailPageForm.shipToAddress.stateCode != null}">
													                  ${hardwareDetailPageForm.shipToAddress.stateCode}
													                  </c:if>
													                  <c:if test="${(hardwareDetailPageForm.shipToAddress.province !='' && hardwareDetailPageForm.shipToAddress.province !=' ') && hardwareDetailPageForm.shipToAddress.province != null}">
													                  ,${hardwareDetailPageForm.shipToAddress.province}
													                  </c:if>
													                   region changed to district for MPS 2.1 
													                  <c:if test="${(hardwareDetailPageForm.shipToAddress.district !='' && hardwareDetailPageForm.shipToAddress.district !=' ') && hardwareDetailPageForm.shipToAddress.district != null}">
													                  ,${hardwareDetailPageForm.shipToAddress.district}
													                  </c:if>
													                  </span>
													                  <div>${hardwareDetailPageForm.shipToAddress.postalCode}</div> 
													                  <div>${hardwareDetailPageForm.shipToAddress.country}</div>--%>
															</li>
												
								
								<li><spring:message code="requestInfo.addressInfo.label.building"/> <span id="shipToAddr_building">${hardwareDetailPageForm.shipToAddress.physicalLocation1}</span></li>
								<li><spring:message code="requestInfo.addressInfo.label.floor"/> <span id="shipToAddr_floor">${hardwareDetailPageForm.shipToAddress.physicalLocation2}</span></li>
								<li><spring:message code="requestInfo.addressInfo.label.office"/><span id="shipToAddrOffice">${hardwareDetailPageForm.shipToAddress.physicalLocation3}</span></li>
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
									<span id="spclInstruction" style="word-break:break-all" class="multiLine">${hardwareDetailPageForm.specialInstruction}</span>
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
										${hardwareDetailPageForm.creditCardEncryptedNo}									
								</li>
							</ul>
							</c:when>
							</c:choose>
							<c:if test="${hardwareDetailPageForm.poNumber ne null && not empty hardwareDetailPageForm.poNumber}">
							<ul class="form">
								<li>
									 <label for="ponum"><spring:message code="requestInfo.label.poNumber"/></label>									
										${hardwareDetailPageForm.poNumber}  
								</li>
							</ul>	
							</c:if>
														
						</div><!-- columnsTwo -->
					</div><!-- columnInner rounded infoBox -->
				</div><!-- portletBlock -->
				<!-- Payment Details End -->
				<!-- COVERED SERVICES BLOCK STARTS -->
        
		        <div class="portletBlock">
		          <div class="columnsOne">
		            <div class="infoBox columnInner rounded shadow">
		              <h4><spring:message code="requestInfo.heading.coveredService"/></h4>
		              <ul class="form">
		                <li>
		                  <label><spring:message code="requestInfo.label.coveredService"/></label>
		                  <span><c:choose><c:when test="${hardwareDetailPageForm.installationOnlyFlag eq 'true'}"><spring:message code="requestInfo.option.yes"/></c:when><c:otherwise><spring:message code="requestInfo.option.no"/></c:otherwise></c:choose></span></li>
		              </ul>
		            </div>
		          </div>
		        </div>
		       
		        <!-- COVERED SERVICES BLOCK ENDS -->
				<!-- Attachment Description Start -->
				<div id="div_attachmentInfoPrint">
				<p class="inlineTitle"><spring:message code="requestInfo.heading.notesAndAttachments"/></p>
				<div class="portletBlock">
          			<div class="columnsOne">
            			<div class="infoBox columnInner rounded shadow bgSolid">
            				<h4><spring:message code="requestInfo.heading.notes"/></h4>
            				 <p id="notes" class="multiLine noteWrap notesOverflow">${hardwareDetailPageForm.attachmentDescription}</p>
            			</div>
            		</div>
            	</div>
				</div>
				<!-- Attachment Description End -->
				<!-- Attachment Start -->
				<c:if test="${not empty hardwareDetailPageForm.attachmentList}">
				<div id="div_attachmentListPrint">
				<div class="portletBlock">
					<div class="wHalf displayGrid columnInnerTable rounded shadow" style="margin-left: 4px;" id="attachmentDetailsTable">
					
						<table id="attachmentDiv">
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
				</div>
				</c:if>
				<div id="attachmentFailed" class="error" style="display: none; width: 50%">
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
	<c:if test="${hardwareDetailPageForm.pageFlow != null && hardwareDetailPageForm.pageFlow != '' && hardwareDetailPageForm.pageFlow =='map'}">
		jQuery('#quantityId').hide();
	</c:if>
	
function print(){
	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='hardwareOrderConfirmPrint' /></portlet:renderURL>";         
    var iWidth=900;
    var iHeight=5600;
    var iTop = (window.screen.availHeight-30-iHeight)/2;        
    var iLeft = (window.screen.availWidth-10-iWidth)/2;           
    window.open(url,
            'ConfirmHardwareOrder',
            'height='+iHeight+
            ',innerHeight='+
            iHeight+',width='+
            iWidth+',innerWidth='+
            iWidth+',top='+iTop+
            ',left='+iLeft+
            ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
};

function email() {
	//alert("Hardware order email..");
	var url ="<portlet:renderURL windowState='<%= LiferayWindowState.POP_UP.toString() %>'><portlet:param name='action' value='hardwareOrderEmailConfirmationPage' /></portlet:renderURL>" ;
	
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
	
	jQuery(document).ready( function() {
		jQuery('#preConf').show();
		jQuery('#accessories').show();
	
		if("${attachmentException}"=="attachfailed"){
			jQuery('#attachmentFailed').append("<li><strong><spring:message code='validation.Asset.attachmentFailed'/></strong></li>");
			 jQuery("#attachmentFailed").show();					
			}
	
	});
</script>