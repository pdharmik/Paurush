<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxgrid_excell_sub_row.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/portletRedirection.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/jQueryAlert/jquery.alerts.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/validation.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo.js"></script>
<script type="text/javascript" src="<html:rootPath/>js/combo/dhtmlxcombo_whp.js"></script>
<style type="text/css"><%@ include file="/WEB-INF/css/combo/dhtmlxcombo.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/jQueryAlert/jquery.alerts.css" %></style> 
<style type="text/css">
<%@ include file="../../../css/grid/dhtmlxdataview.css"%>
</style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<style type="text/css">
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style>.relativeI{position:relative!important;}</style>
<![endif]-->
<style>#paymentType
{width:162px!important;}</style>
<script type="text/javascript" src="<html:rootPath/>js/grid/ext/dhtmlxdataview.js"></script>
<portlet:resourceURL var="catalogDetailsSession" id="setCatalogValuesToSession"></portlet:resourceURL>
<portlet:resourceURL var="catalogDetailsRemoveSession" id="removeCatalogValuesFromSession"></portlet:resourceURL>
	
		<% request.setAttribute("subTabSelected","createNewRequest"); %>
		<jsp:include page="../../common/subTab.jsp"></jsp:include> 
		<jsp:include page="../../common/validationMPS.jsp" />
	  <div id="content-wrapper-inner">
		<div class="journal-content-article">
 		<!-- CART BEGIN -->
   		<div class="cart">
     		<a class="rounded" href="javascript:cartView();"><spring:message code="requestInfo.label.order"/><b><span id ="cartQuantity">${cartQuantity}</span></b><spring:message code="requestInfo.label.items"/></a>
    	</div>
        <!-- CART END -->
		<h2><spring:message code="requestInfo.heading.suppliesRequests"/></h2>
		<span class="step"><spring:message code="requestInfo.heading.orderSupplies"/></span>
	  </div>
    <div class="main-wrap">
      <div class="content"> 
      <!-- PROCESS STEPS START -->
	  <div class="portletBlock center">
		  <ul class="processSteps shadow">
		  	<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step1SelectItems"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.message.selectItems"/></a></li>
			<li ><a href="#" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
			<li><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
			<li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
		  </ul>
	  </div>
      <!-- PROCESS STEPS END -->
      <div class="wFull">
      <div class="error wFull" id="errorMsgPopup" style="display:none"></div>
      </div>
<%-- 	  <c:if test='${sessionScope.accountCurrentDetails["rowCount"]!="1"}'> --%>
        <h3 class="pageTitle"><spring:message code="requestInfo.heading.catalogFor"/>
        
        <c:set var = "accountName" value = "${accountName}"/>
      <c:set var = "agreementName" value = "${sessionScope.accountCurrentDetails['agreementName'] }"/>
      
    	<c:if test="${accountName ne '' && agreementName ne '' }">
    	${accountName } / ${agreementName }
    	</c:if>
    	<c:if test="${accountName ne '' && agreementName == '' }">
    	${accountName }
    	</c:if>
    	<c:if test="${accountName == '' && agreementName ne '' }">
    	${agreementName }
    	</c:if>
<%--     	</c:if>         --%>
        <c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        	<a href="#" onclick="showValidation();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        </c:if>
        <c:if test="${not empty sessionScope.accountCurrentDetails['splitterFlag']}">
	      <c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}"> 
        	<div><span><spring:message code="requestInfo.label.fieldsMarked"/> <span class="req">*</span> <spring:message code="requestInfo.label.areRequired"/></span></div> <!-- added condition for defect 10433 -->
          </c:if>
        </c:if>
        </h3>
        
        	<c:if test="${not empty sessionScope.accountCurrentDetails['splitterFlag']}">
	        	<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">  
		        	<div class="portletBlock infoBox rounded shadow split backgroundLight">
			          <div class="columnsTwo">
			            <div class="columnInner">
			               <h4><spring:message code="requestInfo.header.selection"/></h4>
			               <ul class="form">
			                <li>
			                  <label><spring:message code="requestInfo.label.billingAddress"/> <span class="req">*</span> </label>
			                  <span id="combo_zone1" name="combo_zone1"></span>
			                  <span id="billToSingle"></span>
			                </li>
			              </ul>
			            </div>
			          </div>
			          <div class="columnsTwo">
			            <div class="columnInner">
			            <!-- changed on June 11, 2013 for adding X image in header   <h4>&nbsp;<img src="<html:rootPath/>images/close.png" id="closeImage" style="display:none"  class="floatR positionUp" onclick="deleteCatalogDetails();" /></h4>-->	             
			              <!-- changed for defect 7992--> <div id="hideNewSelection"><h4>&nbsp;<a href="#" title='<spring:message code="requestInfo.link.newselection"/>' id="closeImage" style="display:none"  class="floatR positionUp" onclick="deleteCatalogDetails();">
              				 <spring:message code="requestInfo.link.newselection"/></a></h4></div>
              				 <div id="showaltSelection"><h4>&nbsp;</h4></div>
			              <div class="div-style60"><ul class="form" >
			                <li>
			                  <label for="payType"><spring:message code="requestInfo.label.paymentType"/> <span class="req">*</span></label>
			                  <span id="showPaymentType"><select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span><span id="paymentLoading" class="treeLoading div-style61" style="display:none;"><img src="<html:imagesPath/>loading-icon.gif"/></span>
			                </li>
			              </ul></div>
						  <div class="div-style62">
			                
								<button class="button" onClick="return showSearch();" id="applyButton"><spring:message code="button.apply"/> </button>
								
			                </div><div class="clear-both"></div>
			              
			            </div>
			          </div>
			         
			        </div>
		        </c:if>
	        </c:if>
	        
			<div class="portletBlock infoBox rounded shadow split relativeI" id="searchCatalog" style="display:none">
				<div class="columnsTwo">
           			<div class="columnInner">
						<h4><spring:message code="requestInfo.heading.search"/></h4>
						<ul class="form">
							<li><label for="productType"><spring:message code="requestInfo.label.productType"/> </label> 
							<!--<div id="productTypeLoading" class="treeLoading" style="display:none;width:auto!important;float:left!important;"><img src="<html:imagesPath/>loading-icon.gif"/></div>
							--><span id="showProductType">
							<c:if test="${not empty sessionScope.accountCurrentDetails['splitterFlag']}">
								<c:choose>
    							<c:when test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'false'}">
    								${productTypeData}
    							</c:when>
    							<c:otherwise>
    								<select id="productType"><option value=""><spring:message code="requestInfo.option.select"/></option></select>
    							</c:otherwise>
    							</c:choose>
							</c:if>								
							</span><span id="productTypeLoading" class="treeLoading div-style61" style="display:none;"><img src="<html:imagesPath/>loading-icon.gif"/></span>
							</li>
							<li><label for="productModel"><spring:message code="serviceRequest.label.productModel"/> </label>
							<span id="showProductModel"><select id="productModel"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span><span id="productModelLoading" class="treeLoading div-style61" style="display:none;"><img src="<html:imagesPath/>loading-icon.gif"/></span>	
							</li>
						</ul>
					</div>
				</div>
				<div class="columnsTwo">
					<div class="columnInner">
						<h4>&nbsp;</h4>
						<ul class="form">
							<li><label for="partType"><spring:message code="requestInfo.label.partType"/> </label> 
							<span id="showPartType"><select id="partType"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span><span id="partTypeLoading" class="treeLoading div-style61" style="display:none;"><img src="<html:imagesPath/>loading-icon.gif"/></span>
							</li>
							<li><label for="partNumber"><spring:message code="requestInfo.label.partNumber"/> </label>
							<input id="partNumber"  type="text" maxlength="20" onChange="doSearchCatalogPart();"> <img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.tooltip.partNumber"/>" ></li>
						</ul>
					</div>
				</div>
				<div class="buttonContainer inPortlet ">
					<button onClick="doSearchCatalogPart();" class="button_cancel " id="findCatalogList"><spring:message code="button.find"/></button>
				</div>
			</div> <!-- portletBlock info -->
			
			<div id = "catalogListContatiner" class="lineClear">
			<p id="showResultText" class="info inlineTitle"><spring:message code="requestInfo.orderSupplies.orderDetails.heading.resultsFoundFor"/>&nbsp;&nbsp;<span id="productHeader"></span></p>	
			<br>
				<div class="colunmInner rounded shadow">
					<div id="catalogListGridObj" class="width-100per"></div>
					<div id="loadingNotification" class='gridLoading'>
	      				<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
	  				</div>
	  				<div class="pagination">
	  					<span id="pagingArea"></span>
	  					
	  				</div>
				</div>
			</div> <!-- catalogListContatiner -->
			<div id="buttonListDiv" class="buttonContainer">
				<button class="button_cancel" type="button" onclick="javascript:redirectToHistory('catalog');"><spring:message code="button.cancel"/></button>
				<button class="button" type="button" onClick="javascript:testMethod();"><spring:message code="button.continue"/></button>
			</div>
			
			
		</div>	 <!-- content-->
		<div id="cartLoad" style="display:none"></div>
	</div> <!-- main-wrap -->
</div>  <!-- content-wrapper-->
<!-- wrapper -->
<script type="text/javascript">
//alert("${cartQuantity}");
<%-- Changes for MPS 2.1--%>
var isCartUpdated=false;
var singlePaymentType=false;
var orderQtyFlag=true;
var orderCurrency = "${orderedCurrentcy}";
<%--Ends Changes for MPS 2.1--%>
document.getElementById("cartQuantity").value = '${cartQuantity}';

var catalogGridRowNum;
var rowsNumFlag = [];

//alert('order quantity is '+'${cartQuantity}');

	<c:if test="${not empty sessionScope.accountCurrentDetails['splitterFlag']}">
    	<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
    	<c:if test="${empty singleBillToAddress}">
			var singleBillToAddress = "";
			var singleSoldToNumber = "";
			var billToSelected="";
			var soldTo="";
			window.dhx_globalImgPath = "<html:imagesPath/>comboImgs/";
			var combo = new dhtmlXCombo("combo_zone1","billToCombo",218);//changed on june 11,2013 for better look n feel
			combo.setOptionWidth(212);
			/*combo.setOptionHeight(50);*/
			combo.enableOptionAutoHeight(false); //To enable scrollbar on 11June
			combo.loadXMLString('${billToAddressList}');
			soldTo=combo.getSelectedValue();
			combo.readonly(true);
			combo.attachEvent("onChange", function(){
				jQuery("#paymentLoading").show();
				//jQuery("#showPaymentType").hide();
				jQuery("#paymentType").attr('disabled','disabled');
				soldTo = combo.getSelectedValue();				
				billToSelected = combo.getSelectedText();
				if(soldTo!=null && soldTo!=""){
					document.getElementById('errorMsgPopup').style.display = "none";
					hideDiv("catalogListContatiner");
					hideDiv("buttonListDiv");
					var paymentTypeURL="<portlet:resourceURL id="getPaymentType"/>";
					/* jQuery("#showPaymentType").load(paymentTypeURL,function(response){
						jQuery("#paymentLoading").hide();
					    jQuery("#showPaymentType").show();
						document.getElementById("showPaymentType").innerHTML = response;
						}); */
					
					jQuery.ajax({
						url:paymentTypeURL,
						data:{
								soldTo:soldTo,
								random:Math.random()*99999
							 },
						type:'POST',
						dataType: 'JSON',		
						success: function(results){
							jQuery("#paymentLoading").hide();
						    jQuery("#showPaymentType").show();
						    jQuery("#paymentType").removeAttr('disabled');
							var resultJSON;
							try{
								 var userAgent = window.navigator.userAgent;
								 var msie = userAgent.indexOf("MSIE 7.0");
								 if(msie==-1)
								 	var stringified = JSON.stringify(results);
								 //resultJSON=jQuery.parseJSON(results);
								 resultJSON=results;
							}catch(e){}
							if(resultJSON !=null){
								if(resultJSON.paymentTypeHtml != null){
									jQuery('#showPaymentType').html(resultJSON.paymentTypeHtml);
								}else{
									jQuery('#showPaymentType').html('');
								}
								if(resultJSON.singlePaymentType != null && resultJSON.singlePaymentType=='true'){
									jQuery("#searchCatalog").show();
									document.getElementById('applyButton').style.display='none';
									document.getElementById('closeImage').style.display='block';	
									getProductType();	
									doSearchCatalogPart();
								}
							}
						}
					});	 
				}else{
					jQuery("#paymentLoading").hide();				    
					//jQuery("#showPaymentType").html('<select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select>');
					jQuery("#showPaymentType").show();
					jQuery("#paymentType").removeAttr('disabled');
				}
			});
		</c:if>	
			
		</c:if>
	</c:if>
	
	var headerString=",,,";
	catalogListGrid = new dhtmlXGridObject('catalogListGridObj');
	catalogListGrid.setImagePath("<html:imagesPath/>gridImgs/");
	catalogListGrid.setHeader(headerString);
	catalogListGrid.setNoHeader(true);
	catalogListGrid.setInitWidths("175,*,250,275");
	catalogListGrid.setColumnMinWidth("50,150,150,150");
	catalogListGrid.setColAlign("left,left,left,center");
	catalogListGrid.setSkin("light");
	catalogListGrid.setColTypes("ro,ro,ro,ro,ro");
	catalogListGrid.enableAutoHeight(true);
    catalogListGrid.enableMultiline(true);
    catalogListGrid.enableColumnMove(true);
    catalogListGrid.init();
    catalogListGrid.enablePaging(true, 5, 5, "pagingArea", true, "infoArea");
    catalogListGrid.setPagingSkin("bricks");

    catalogListGrid.attachEvent("onXLE", function() {
    	catalogGridRowNum = catalogListGrid.getRowsNum();
    	for(var i=0;i<catalogGridRowNum;i++){
    		rowsNumFlag[i] = true;
    	}
        document.getElementById('loadingNotification').style.display = 'none';
        jQuery('#catalogListGridObj input').bind('mousedown focus',function(){
			jQuery(this).removeClass('errorColor');
			});
    });
    
    function getProductType(){
		//var soldTo = combo.getSelectedValue();
		//var paymentType = document.getElementById("paymentType").value;
		jQuery("#productTypeLoading").show();
		//jQuery("#showProductType").hide();
		jQuery("#productType").attr('disabled','disabled');
		document.getElementById('errorMsgPopup').style.display = "none";		
		document.getElementById("showProductModel").innerHTML = "<select id=\"productModel\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
		document.getElementById("showPartType").innerHTML = "<select id=\"partType\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
		document.getElementById("partNumber").value = '';
			var productTypeURL="<portlet:resourceURL id="getProductType"/>&random="+Math.random()*99999;
		//alert('productModelURL is '+productModelURL);
		jQuery("#showProductType").load(productTypeURL,function(response){
		//alert('getProductModel is called');
			//alert(response);
			jQuery("#productTypeLoading").hide();
			jQuery("#showProductType").show();
			jQuery("#productType").removeAttr('disabled');
			document.getElementById("showProductType").innerHTML = response;
			});
	}
	
	function getProductModel(){
		var productType = document.getElementById("productType").value;
		document.getElementById('errorMsgPopup').style.display = "none";
		jQuery('#productType').removeClass('errorColor');
		hideDiv("catalogListContatiner");
		hideDiv("buttonListDiv");
		jQuery("#productModelLoading").show();
		//jQuery("#showProductModel").hide();
		jQuery("#productModel").attr('disabled','disabled');
		document.getElementById("showPartType").innerHTML = "<select id=\"partType\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
		document.getElementById("partNumber").value = '';
		document.getElementById("productHeader").innerHTML = decodeURIComponent(productType);
		//alert('productType value is '+productType);
		if(productType==""){
			//alert('This time you should not do anything');
			return false;
		}
		//alert('Product type value is '+productType);
		var productModelURL="<portlet:resourceURL id="getProductModel"/>&productType="+productType+"&random="+Math.random()*99999;
		//alert('productModelURL is '+productModelURL);
		jQuery("#showProductModel").load(productModelURL,function(response){
		//alert('getProductModel is called');
			//alert(response);
			jQuery("#productModelLoading").hide();
			jQuery("#showProductModel").show();
			jQuery("#productModel").removeAttr('disabled');
			document.getElementById("showProductModel").innerHTML = response;
			});
	}
	
	
	
	function getPartType(){
		var productType = document.getElementById("productType").value;
		var productModel = document.getElementById("productModel").value;
		jQuery('#productType').removeClass('errorColor');
		jQuery('#productModel').removeClass('errorColor');
		jQuery("#partTypeLoading").show();
		//jQuery("#showPartType").hide();
		jQuery("#partType").attr('disabled','disabled');
		document.getElementById('errorMsgPopup').style.display = "none";
		if(productModel==""){
			//alert('This time you should not do anything');
			return false;
		}
		//alert('productType '+productType);
		//alert('productModel '+productModel); 
		var partTypeURL="<portlet:resourceURL id="getPartType"/>&productType="+productType+"&productModel="+productModel+"&random="+Math.random()*99999;//Here add another parameter
		//alert('partTypeURL is '+partTypeURL);
		jQuery("#showPartType").load(partTypeURL,function(response){
		//alert('getPartType is called');
			jQuery("#partTypeLoading").hide();
			jQuery("#showPartType").show();
			jQuery("#partType").removeAttr('disabled');
			});
	}
	jQuery(document).ready(function() {
		jQuery("#hideNewSelection").show();
		jQuery("#showaltSelection").hide();	
		var currentURL = window.location.href;
		if(currentURL.indexOf('/partner-portal') != -1)
		  {	
			  jQuery('#topnavigation li a').each(function(){
				  if(jQuery(this).attr('href')=="/group/partner-portal/customer-requests")
				  jQuery(this).parent().addClass('selected');
				  });
		  }
// 		Change Account Link Hide/Show for CI-7 Defect #12274
		if(currentURL.indexOf('/partner-portal') == -1)
		  {	
			  jQuery('#changeaccount').show();
		  }
		else
			{
			jQuery('#changeaccount').hide();
			}
		jQuery(':input').bind('mousedown focus',function(){
			jQuery(this).removeClass('errorColor');
			});
		//hideDiv("catalogListContatiner");
		if("${cartQuantity}">0){
			showDiv("buttonListDiv");
		}else{
			hideDiv("buttonListDiv");
		}
		hideDiv("catalogListContatiner");
		hideDiv("buttonListDiv");
		<c:if test="${not empty sessionScope.catalogCurrentDetails}">
			jQuery("#searchCatalog").show();
			var soldToNumber = "${sessionScope.catalogCurrentDetails['soldToNumber']}";
			//var billToAddress = "${sessionScope.catalogCurrentDetails['billToAddress']}";
			
			var billToAddress = "";
			<c:if test="${not empty sessionScope.selectedBillToAddress}"> 
	          	<c:if test="${!empty sessionScope.selectedBillToAddress.addressLine1}">
	          		billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.addressLine1}"+"<br>";
				</c:if>
				<c:if test="${!empty sessionScope.selectedBillToAddress.addressLine2}">
					billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.addressLine2}"+"<br>";
				</c:if>
				<c:if test="${!empty sessionScope.selectedBillToAddress.city}">
					billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.city}";
					<c:if test="${(!empty sessionScope.selectedBillToAddress.state) || (!empty sessionScope.selectedBillToAddress.country)}">
						billToAddress = billToAddress + ",";
					</c:if>
				</c:if>	
				<c:choose>
					<c:when test="${!empty sessionScope.selectedBillToAddress.state}">
						billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.state}";
						<c:if test="${!empty sessionScope.selectedBillToAddress.country}">
							billToAddress = billToAddress + ",";
						</c:if>
					</c:when>
					<c:when test="${!empty sessionScope.selectedBillToAddress.province}">
						billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.province}";
						<c:if test="${!empty sessionScope.selectedBillToAddress.country}">
							billToAddress = billToAddress + ",";
						</c:if>
					</c:when>
					<c:when test="${!empty sessionScope.selectedBillToAddress.county}">
						billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.county}";
						<c:if test="${!empty sessionScope.selectedBillToAddress.country}">
							billToAddress = billToAddress + ",";
						</c:if>
					</c:when>
				</c:choose>
				
				<c:if test="${!empty sessionScope.selectedBillToAddress.country}">	
					billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.country}";
					<c:if test="${!empty sessionScope.selectedBillToAddress.postalCode}">
						billToAddress = billToAddress + "<br/>";
					</c:if>
				</c:if>
				<c:if test="${!empty sessionScope.selectedBillToAddress.postalCode}">	
					billToAddress = billToAddress + "${sessionScope.selectedBillToAddress.postalCode}"+"&nbsp";
				</c:if>
			</c:if>
			//alert(billToAddress);
			var paymentType = "${sessionScope.catalogCurrentDetails['paymentTypeText']}";
			jQuery("#combo_zone1").hide();
			jQuery("#billToSingle").html(billToAddress);
			jQuery("#billToSingle").show();
			jQuery("#showPaymentType").html(paymentType);
			getProductType();
			document.getElementById("applyButton").style.display="none";
			document.getElementById("closeImage").style.display="block";
			doSearchCatalogPart();
			
		</c:if>
		/**
		* The following section is used to load the selected billing address as prepopulated if there is only one
		* billing address
		*/
		<c:if test="${not empty singleBillToAddress && empty sessionScope.catalogCurrentDetails}">
			singleSoldToNumber = "${singleBillToAddress.soldToNumber}";
			singleBillToAddress = "";		
	        <c:if test="${!empty singleBillToAddress.addressLine1}">
	        	singleBillToAddress = singleBillToAddress + "${singleBillToAddress.addressLine1}"+"<br>";
			</c:if>
			<c:if test="${!empty singleBillToAddress.addressLine2}">
				singleBillToAddress = singleBillToAddress + "${singleBillToAddress.addressLine2}"+"<br>";
			</c:if>
			<c:if test="${!empty singleBillToAddress.city}">
				singleBillToAddress = singleBillToAddress + "${singleBillToAddress.city}";
				<c:if test="${(!empty singleBillToAddress.state) || (!empty singleBillToAddress.country)}">
					singleBillToAddress = singleBillToAddress + ",";
				</c:if>
			</c:if>	
			<c:choose>
				<c:when test="${!empty singleBillToAddress.state}">
				singleBillToAddress = singleBillToAddress + "${singleBillToAddress.state}";
					<c:if test="${!empty singleBillToAddress.country}">
					singleBillToAddress = singleBillToAddress + ",";
					</c:if>
				</c:when>
				<c:when test="${!empty singleBillToAddress.province}">
				singleBillToAddress = singleBillToAddress + "${singleBillToAddress.province}";
					<c:if test="${!empty singleBillToAddress.country}">
					singleBillToAddress = singleBillToAddress + ",";
					</c:if>
				</c:when>
				<c:when test="${!empty singleBillToAddress.county}">
				singleBillToAddress = singleBillToAddress + "${singleBillToAddress.county}";
					<c:if test="${!empty singleBillToAddress.country}">
					singleBillToAddress = singleBillToAddress + ",";
					</c:if>
				</c:when>
			</c:choose>
			
			<c:if test="${!empty singleBillToAddress.country}">	
			singleBillToAddress = singleBillToAddress + "${singleBillToAddress.country}";
				<c:if test="${!empty singleBillToAddress.postalCode}">
				singleBillToAddress = singleBillToAddress + "<br/>";
				</c:if>
			</c:if>
			<c:if test="${!empty singleBillToAddress.postalCode}">	
			singleBillToAddress = singleBillToAddress + "${singleBillToAddress.postalCode}"+"&nbsp";
			</c:if>
			jQuery("#combo_zone1").hide();
			billToSelected = singleBillToAddress;
			jQuery("#billToSingle").html(singleBillToAddress);
			jQuery("#billToSingle").show();
			jQuery("#paymentLoading").show();
			//jQuery("#showPaymentType").hide();
			jQuery("#paymentType").attr('disabled','disabled');
			soldTo = "singleAddress";
			document.getElementById('errorMsgPopup').style.display = "none";
			
			var paymentTypeURL="<portlet:resourceURL id="getPaymentType"/>";
			jQuery.ajax({
				url:paymentTypeURL,
				data:{
						soldTo:soldTo,
						random:Math.random()*99999
					 },
				type:'POST',
				dataType: 'JSON',		
				success: function(results){
					jQuery("#paymentLoading").hide();
				    jQuery("#showPaymentType").show();
				    jQuery("#paymentType").removeAttr('disabled');
					var resultJSON;
					try{
						 var userAgent = window.navigator.userAgent;
						 var msie = userAgent.indexOf("MSIE 7.0");
						 if(msie==-1)
						 	var stringified = JSON.stringify(results);
						 resultJSON=results;
					}catch(e){}
					if(resultJSON !=null){
						if(resultJSON.paymentTypeHtml != null){
							jQuery('#showPaymentType').html(resultJSON.paymentTypeHtml);
						}else{
							jQuery('#showPaymentType').html('');
						}
						if(resultJSON.singlePaymentType != null && resultJSON.singlePaymentType=='true'){
							jQuery("#hideNewSelection").hide();
							jQuery("#showaltSelection").show();
							jQuery("#searchCatalog").show();
							document.getElementById('applyButton').style.display='none';
							document.getElementById('closeImage').style.display='block';	
							getProductType();	
							doSearchCatalogPart();
						}
					}
				}
			});	
		</c:if>
		/* End */
		<c:if test="${not empty sessionScope.accountCurrentDetails['splitterFlag']}">
    		<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'false'}">
    		    jQuery("#searchCatalog").show();
				doSearchCatalogPart();
			</c:if>
		</c:if>
	});
	
	function removeErrorMesssage(){
		document.getElementById('errorMsgPopup').style.display = "none";
		jQuery('#partType').removeClass('errorColor');
	}
	
   

function doSearchCatalogPart(){
	//alert('shoiwng loading div');
	showDiv('loadingNotification');
	document.getElementById('showResultText').style.display = "block";
		var partNumber = document.getElementById("partNumber").value;
	//alert('partNumber '+partNumber);
	if(partNumber==null||partNumber==''){
		//alert('part number is null');
		var productType = document.getElementById("productType").value;
		//alert('productType is '+productType);
		var productModel = document.getElementById("productModel").value;
		//alert('productModel is '+productModel);
		var partType = document.getElementById("partType").value;
		//alert('partType is '+partType);
		var validationFlagPopup = true;
		document.getElementById('errorMsgPopup').style.display = "none";
		document.getElementById('errorMsgPopup').innerHTML = "";
		if(productType==null ||productType=='' ) {
			var retrieveCatalogList="<portlet:resourceURL id='retriveCatalogPartListURL'></portlet:resourceURL>";
			var accountName = encodeURIComponent("${accountName}");
			document.getElementById("productHeader").innerHTML = decodeURIComponent(accountName);
			}else if(productModel==null||productModel==''){
			var retrieveCatalogList="<portlet:resourceURL id='retriveCatalogPartListURL'></portlet:resourceURL>&productType="+productType;
			document.getElementById("productHeader").innerHTML = decodeURIComponent(productType);
				}else if(partType==null||partType==''){
			var retrieveCatalogList="<portlet:resourceURL id='retriveCatalogPartListURL'></portlet:resourceURL>&productModel="+productModel+"&productType="+productType;
			}else{
			var retrieveCatalogList="<portlet:resourceURL id='retriveCatalogPartListURL'></portlet:resourceURL>&partType="+partType+"&productModel="+productModel+"&productType="+productType;
		}
		if (!validationFlagPopup)
		{	
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);
			hideDiv("loadingNotification");
			return false;
			//alert("block error");
		}
		//alert('partNumber==>'+partNumber+'partType===>'+partType+'productModel==>'+productModel+'productType==>'+productType);
		
	}else{
		document.getElementById('errorMsgPopup').style.display = "none";
		document.getElementById("productHeader").innerHTML = "Part Number "+partNumber;
		var retrieveCatalogList="<portlet:resourceURL id='retriveCatalogPartListURL'></portlet:resourceURL>&partNumber="+partNumber;
	}
	jQuery('#productType').removeClass('errorColor');
	jQuery('#productModel').removeClass('errorColor');
	jQuery('#partType').removeClass('errorColor');
	//alert('after if else block');
	catalogListGrid.clearAndLoad(retrieveCatalogList);
		showDiv("catalogListContatiner");
	showDiv("buttonListDiv");
	return true;
}

function checkParts(inputboxId,maxOrderQty,id,count){
	catalogGridRowNum = catalogListGrid.getRowsNum();
	var ordQty = document.getElementById(inputboxId).value;	
	var newID = inputboxId;
	if(maxOrderQty != "" && maxOrderQty != null){
		if(ordQty>maxOrderQty){
			 rowsNumFlag[count] = false;
			 jQuery('#'+newID).addClass('errorColor');
			 document.getElementById("MsgassetPartList["+id+"].orderQuantity").style.display = 'block';
		 }
		 else{
			 rowsNumFlag[count] = true;
			 jQuery('#'+newID).removeClass('errorColor');
			// orderQtyFlag = true;
			 document.getElementById("MsgassetPartList["+id+"].orderQuantity").style.display = 'none';
		 }
	}

 }

function removeErrorMessage(id){
	document.getElementById('errorMsgPopup').style.display = "none";
	document.getElementById('errorMsgPopup').innerHTML = "";
		jQuery('#partQuantity'+id).removeClass('errorColor');
		 //orderQtyFlag = true;
			//alert("Remove = " + orderQtyFlag);	


}
function addToCart(rowID, partNumber, partDesc, partType, yield, consumableType, supplyId, productId, proModel, catalogId, lineId, currency,salesOrg,providerOrderNumber,maxquantity,element) {
	orderQtyFlag = true;
	var inputBoxId = "partQuantity"+rowID;
		var ordQty = document.getElementById(inputBoxId).value;	
		jQuery(this).removeClass('errorColor');
		var validationFlagPopup = true;
		var queryParam = "partQuantity"+rowID;
		var quantity = document.getElementById(queryParam).value;
		document.getElementById('errorMsgPopup').style.display = "none";
		document.getElementById('errorMsgPopup').innerHTML = "";
		var queryParam = "partQuantity"+rowID;
		//alert("orderCurrency:: "+orderCurrency);
		if(orderCurrency == null || orderCurrency == ''){
			orderCurrency = currency;
		}	
		if(quantity==null || quantity==''|| quantity<=0) {
			if(quantity==null || quantity==''){
				jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.quantity.empty"/></strong></li>');
				validationFlagPopup = false;
				jQuery('#'+queryParam).addClass('errorColor');
			}else{
				jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.quantity.notZero"/></strong></li>');
				validationFlagPopup = false;
				jQuery('#'+queryParam).addClass('errorColor');
			}
		} 
		else{
				if(!validateFormat(quantity,'quantity','errorMsgPopup')){
					validationFlagPopup = false;
					jQuery('#'+queryParam).addClass('errorColor');
				}
			}
		if(currency != ''){
			if(currency != orderCurrency){
				jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.currency.typeMismatch"/></strong></li>');
				validationFlagPopup = false;
				jQuery('#'+queryParam).addClass('errorColor');
			}
		}
		
		var maxQty = parseInt(maxquantity);
		var orderQuantity = parseInt(ordQty);
		if(orderQuantity > maxQty){
			orderQtyFlag = false;
		}
		
		if(!orderQtyFlag) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
			validationFlagPopup = false;
		}
		if (!validationFlagPopup)
		{	
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);

			//Added for find button movement issue
			if (window.PIE) {
				document.getElementById("findCatalogList").fireEvent("onmove");
			}
			
			return false;
			//alert("block error");
		}
		jQuery(element).val("<spring:message code='requestInfo.button.updateOrder'/>");
	var selectedItem = "&random="+Math.random()*99999+ "&partNumber=" + encodeURIComponent(partNumber) + "&partDesc=" + encodeURIComponent(partDesc) + "&partType=" + partType + "&yield=" + yield + "&consumableType=" + consumableType + "&partQty="+quantity +"&catalogId="+rowID+ "&supplyId="+supplyId +"&productId="+productId+"&proModel="+proModel+"&currency="+currency+"&salesOrg="+encodeURIComponent(salesOrg)+"&providerOrderNumber="+providerOrderNumber+"&lineId="+lineId+"&maxQuantity="+maxquantity;
	//alert('selectedItem is '+selectedItem);
	var catalogSessionURL = '<portlet:resourceURL id="addToCartURL"/>'+selectedItem;
	//alert(catalogSessionURL);
	<%-- CHanges for MPS 2.1 defect 7954--%>
	showOverlay();
	jQuery("#cartQuantity").load(catalogSessionURL,function(response){
	//alert('catalogSessionURL resourcemapping called');
		//alert('response '+response);
		hideOverlay();
		document.getElementById("cartQuantity").value = response;
		});
	<%-- Ends CHanges for MPS 2.1 defect 7954--%>
	//Added for find button movement issue
	if (window.PIE) {
		document.getElementById("findCatalogList").fireEvent("onmove");
	}

	}
	
function testMethod(){
	orderQtyFlag = true;
	//alert('calling the render URL ');
	var validationFlagPopup = true;
	document.getElementById('errorMsgPopup').style.display = "none";
	document.getElementById('errorMsgPopup').innerHTML = "";
	var cartValue = document.getElementById("cartQuantity").value;//${cartQuantity};//cartQuantity
	//alert('cartValue '+cartValue);
	if(cartValue == 0) {
		jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.quantity.cartIsEmpty"/></strong></li>');
		validationFlagPopup = false;
	}
	for(var i=0;i<catalogGridRowNum;i++){
		if(rowsNumFlag[i]==false){
			orderQtyFlag = false;
		}
		
	}
	if(!orderQtyFlag) {
		jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
		validationFlagPopup = false;
	}
	if (!validationFlagPopup)
	{	
		document.getElementById('errorMsgPopup').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
		//alert("block error");
	}
	showOverlay();
	window.location.href='<portlet:renderURL><portlet:param name="action" value="showCatalogDetailPage" /></portlet:renderURL>';
	//alert('end calling');
}


function populateURLCriterias(){

	//alert(EnterPopulateURL);
	var url = "${searchCatalogList}";
	  return url;
}
function showDiv(id){		
	var elm = document.getElementById(id);	
	elm.style.display = "block";		
	}
	function hideDiv(id){	
	var elm = document.getElementById(id);
	elm.style.display = "none";	
		
	}
//Cart Related Function Starts here

function getXMLObject()  //Create XML OBJECT
	{
	   var xmlHttp = false;
	   try {
	     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
	   }
	   catch (e) {
	     try {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
	     }
	     catch (e2) {
	       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
	     }
	   }
	   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
	     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
	   }
	   return xmlHttp;  // Mandatory Statement returning the ajax object created
	}

	

		function showSelect(){   
			var selects = document.getElementsByTagName('select');
			for (var i=0; i < selects.length; i++){
				var select = selects[i];
				select.style.visibility = "visible";
			} 
		}
		
	function goToCatalogDetailPage(){
		window.location.href='<portlet:renderURL><portlet:param name="action" value="showCatalogDetailPage"/></portlet:renderURL>';
	}
	<%-- Commented for MPS 2.1 
	function goToContinueShoppingPageParent(){
		var cancelAction = 'cancelAction';
		window.location.href = "<portlet:renderURL></portlet:renderURL>&pageFrom="+cancelAction;
	}
	--%>
	function showValidation(){
		jConfirm('<spring:message code="common.country.alert"/>', "", function(confirmResult) {
			if(confirmResult){
				showAccountPopup();
			}else{
				return false;
			}
	});
	}
function showAccountPopup(){
	isCatalogPage="true";
	showOverlay();
	if(accountGrid==null){
			initializeAccountGrid(showAccGridinPopup);
	}else{

			if(accountGrid.getAllRowIds()=="")
				reloadAccountPopupGrid();
			else{
				accountGrid.filterBy(0,"true",true);
	    		if(accountGrid.getAllRowIds() != "" ){
					if(accountGrid.getAllRowIds().split(',').length == 1){
	    	        	jQuery('#button'+accountGrid.getAllRowIds()).click();
	    		 	}else{
	    		 		showAccGridinPopup();
		    		 	}
				}else{
					showAccGridinPopup();
					}
			}
				
		}
	
		
		
}

function showAccGridinPopup(){
	var mdmLevel="${mdmLevelForAssetDetails}";
	if(mdmLevel != "Account")
	{
			dialog=jQuery('#totalContent').dialog({
				autoOpen: false,
				modal: true,
				height: 460,
				width: 700,
				position: ['center','top'],
				open: function(){
						jQuery('#totalContent').show();
							jQuery('#accountInitialize').show();
	  							jQuery('span.ui-dialog-title').text("Select An Account");
			  					 						
						
						},
				close: function(event,ui){
	   				  //jQuery('.ui-dialog').empty().remove();
	   				  dialog.dialog('destroy');
						hideOverlay();
	   				 // jQuery('#accountInitialize').appendTo(jQuery('#totalContent'));
					  dialog=null;
					  /*alert('in change Request close');*/
					  jQuery('#accountInitialize').hide();
					  /*alert(buttName);*/
					  if(ajaxAccountSelection=="success")
					  {
						  ajaxSuccessFunction();
					  }
					}
				});
			
			jQuery(document).scrollTop('0');
			dialog.dialog('open');
			
	}else{
		ajaxSuccessFunction();
	}
}
ajaxSuccessFunction=function updateRequest(){
		var currentURL = window.location.href;
		if(currentURL.indexOf('/partner-portal') != -1)
		{	
			
			var partnerURLWithoutParams;
			var partnerIndexOfQuestionMark = currentURL.indexOf("?");
			if (partnerIndexOfQuestionMark > 0) {
				partnerURLWithoutParams = currentURL.substring(0, partnerIndexOfQuestionMark);
			} else {
				partnerURLWithoutParams = currentURL;
			}
			partnerBaseURL = partnerURLWithoutParams.substring(0, partnerURLWithoutParams.lastIndexOf('/partner-portal') + '/partner-portal'.length) + "/catalogorder";
			showOverlay();
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+partnerBaseURL;
			return;
		}
		else{
			window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+populateURL("catalogorder",[],[]);
		}			
	}
	
	function cancelRequest(){
		dialog.dialog('close');
	}
	function focusOnInput(id){
		if (window.navigator.userAgent.indexOf("MSIE")>=1){
		//document.getElementById(id).focus();
		setTimeout(function() { document.getElementById(id).focus(); }, 500);
        }
	}
	

//Cart related Function End here.

	function showSearch() {
	//alert('showSearch');
		var validationFlagPopup = true;
		jQuery('#errorMsgPopup').hide();
		jQuery('#errorMsgPopup').html("");		
		var paymentType = jQuery("#paymentType").val();
		if(soldTo==""){
			validationFlagPopup = false;
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.validation.billTo.empty"/></strong></li>');			
		}
		if(paymentType == null || paymentType == ""){
			validationFlagPopup = false;
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.validation.paymentType.empty"/></strong></li>');
		}
		if(!validationFlagPopup){
			jQuery('#errorMsgPopup').show();
			jQuery(document).scrollTop(0);
			return false;
		}else{					
			
			billToSelected = billToSelected.replace(/<div class=/g, '');
			var idx = billToSelected.indexOf("comboColor");
		    if (idx != -1) {
		    	billToSelected = billToSelected.replace(/\"comboColor\">/g, '');
		    } else {
		    	billToSelected = billToSelected.replace(/\"comboAlterColor\">/g, '');
		    }
			billToSelected = billToSelected.replace(/<\/div>/g, '');
			var paymentTypeText = jQuery('select[name="paymentType"] option:selected').text();
			jQuery.ajax({			
				url:'${catalogDetailsSession}',			
				type:'POST',
				data: {
					 	//Ajax call with selected row data
						paymentType:  decodeURIComponent(document.getElementById("paymentType").value),
						paymentTypeText: paymentTypeText
				},
				success: function(results){
						if(results=="success"){
							jQuery("#combo_zone1").hide();
							jQuery("#billToSingle").html(billToSelected);
							jQuery("#billToSingle").show();
							jQuery("#showPaymentType").html(paymentTypeText);
							jQuery("#searchCatalog").show();	
							document.getElementById("applyButton").style.display="none";
							document.getElementById("closeImage").style.display="block";
							getProductType();
							//document.getElementById("cartQuantity").value = "0";						
						}else{
							jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.message.errorSaveValue"/></strong></li>');
							jQuery('#errorMsgPopup').show();
							}
					},
				failure: function(){
						jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.message.errorSaveValue"/></strong></li>');
						jQuery('#errorMsgPopup').show();
						}
				});
			doSearchCatalogPart();
			
		}
	}
	
	function deleteCatalogDetails(){
		jConfirm("<spring:message code='requestInfo.message.sessionClearConfirmation'/>", "", function(confirmResult) {
			if(confirmResult){
				hideSearch();
			}
		});
	}
	
	function hideSearch() {
		//alert('showSearch');
			document.getElementById("showProductType").innerHTML = "<select id=\"productType\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
			document.getElementById("showProductModel").innerHTML = "<select id=\"productModel\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
			document.getElementById("showPartType").innerHTML = "<select id=\"partType\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
			
			jQuery("#searchCatalog").hide();
			jQuery("#showPaymentType").html('');
			jQuery.ajax({
				url:'${catalogDetailsRemoveSession}',			
				type:'POST',
				success: function(results){
						if(results=="success"){	
							<c:choose>
								<c:when test="${not empty singleBillToAddress}">									
									showOverlay();
									window.location.href = "<portlet:renderURL></portlet:renderURL>";										
								</c:when>
								<c:otherwise>
									combo.clearAll();
									combo.setComboText("");
									combo.loadXMLString('${billToAddressList}');	
									jQuery("#showPaymentType").html('<select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select>');
									hideDiv("catalogListContatiner");
									hideDiv("buttonListDiv");			
									document.getElementById("applyButton").style.display="block";
									document.getElementById("closeImage").style.display="none";
								</c:otherwise>
							</c:choose>	
							jQuery("#combo_zone1").show();
							jQuery("#billToSingle").hide();								
							jQuery("#cartQuantity").html("0");
						}else{
							jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.message.errorRemoveValue"/></strong></li>');
							jQuery('#errorMsgPopup').show();
							}
					},
				failure: function(){
						jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.message.errorRemoveValue"/></strong></li>');
						jQuery('#errorMsgPopup').show();
						}
				});
			
			
			jQuery("#showPaymentType").html('<select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select>');
			//alert('after'+jQuery("#showPaymentType").html());
			hideDiv("catalogListContatiner");
			hideDiv("buttonListDiv");			
			document.getElementById("applyButton").style.display="block";
			document.getElementById("closeImage").style.display="none";
		}
	
	var titleStr="Cart View"
		/*The following function is used for opening the cart View popup*/
		function cartView(){
			
			var url="<portlet:renderURL windowState='<%= LiferayWindowState.EXCLUSIVE.toString() %>'><portlet:param name='action' value='showCartViewPage' /></portlet:renderURL>";
			showOverlay();	
				jQuery('#cartLoad').load(url,function(){
					dialog=jQuery('#cartLoad').dialog({
					autoOpen: false,
					title: titleStr,
					modal: true,
					draggable: false,
					resizable: false,
					position: 'center',
					height: 'auto',
					width: 940,
					open: function(){	
						jQuery('#cartLoad').show();
					},
					close: function(event,ui){
						if(isCartUpdated){            		 
			           		 goToContinueShoppingPage();
						}
						hideOverlay();
						dialog.dialog('destroy');					 
						dialog=null;
						//jQuery('#cartLoad').remove();
					}
					});
				dialog.dialog('open');
				});	
				
		}
</script>