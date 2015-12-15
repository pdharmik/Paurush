<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="/WEB-INF/jsp/includeGrid.jsp"/>
<script type="text/javascript"><%@ include file="../../../../js/portletRedirection.js"%></script>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<style type="text/css"><jsp:include page="/WEB-INF/css/jquery/jquery-ui.css" /></style>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/mps.css"/>
<!--[if IE 7]>
	<style type="text/css"><%@ include file="/WEB-INF/css/mps_ie7.css" %></style>
	<style>.relativeI{position:relative!important;}
	.margin2pr{margin-bottom:1%!important;}
	#partTableBundle tr td{border-left:0px solid #ccc!important;}
	#partTableBundle tr{border-top:0px solid #ccc!important;border-bottom:0px solid #ccc!important;}
	#partTableBundle tr td{border-top:0px solid #ccc!important;border-bottom:0px solid #ccc!important;}
	</style>
<![endif]-->
<style>
.cart
{
  min-width: 200px!important;
    width: auto!important;
}
.error {
	color:#F00;
	border:2px #F00 solid;
	margin:0 5px 10px 5px;
	padding:5px 10px;
	overflow:hidden;
	background-color:#ffe9e9;
	list-style-type: none !important;/*By offshore, for error list */
}
.error li{padding-left:0px!important;}
ul.form{margin:0px!important}
#productType, #productModel{width:190px;}
#accessories_container table tbody tr td p{font-size:12px!important;}
#supplies_container table tbody tr td p{font-size:12px!important;}
.fontNoData{font-size:14px;}
#partTableBundle tr td{border-left:1px solid #ccc;}
.margTop{margin-top:3%;}
.fontFindPartNumber{
 line-height: 16px;
  margin-bottom: 7.2px;
}

#bundles_container table tr:hover{background-color:#ffffcc;};
#accessories_container table tr:hover{background-color:#ffffcc;};
#supplies_container table tr:hover{background-color:#ffffcc;};
</style>
<%--Changes for defect 7960 MPS 2.1 --%>
<style>
@media screen and (-webkit-min-device-pixel-ratio:0) {
 .margTop{margin-top:2%!important;}
}
</style><style>
.ie7 .button, .ie7 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<style>
.ie8 .button, .ie8 .button_cancel {
behavior: url(/LexmarkServicesPortal/WEB-INF/css/PIE.htc) !important;
}
</style>
<%-- Ends Changes for defect 7960 MPS 2.1 --%>
<style type="text/css">
.ui-dialog-content {
width: 95%!important;
}
#paymentType
{width:162px!important;}

/* fix for defect 9956 */
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
background: none repeat scroll 0 0 #FFF;
}
.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active {
background: none repeat scroll 0 0 #e6e6f0!important;
}
</style>

<portlet:resourceURL var="hardwareDetailsSession" id="setHardwareValuesToSession"></portlet:resourceURL>
<portlet:resourceURL var="hardwareDetailsRemoveSession" id="removeHardwareValuesFromSession"></portlet:resourceURL>

<%request.setAttribute("subTabSelected","createNewRequest"); %>
		<jsp:include page="../../common/subTab.jsp"></jsp:include> 
		<jsp:include page="../../common/validationMPS.jsp" />
	  <div id="content-wrapper-inner">
		<div class="journal-content-article">
 		<!-- CART BEGIN -->
   		<div class="cart">
     		<a class="rounded" href="javascript:cartView();"><spring:message code="requestInfo.label.order"/><b><span id ="cartQuantity">${cartQuantity}</span></b><spring:message code="requestInfo.label.items"/></a>
    	</div>
        <!-- CART END -->
		<h1><spring:message code="requestInfo.heading.hardwareRequest"/></h1>
		<h2 class="step"><spring:message code="requestInfo.heading.requestHardware"/></h2>
	  </div>
  <div id="content-wrapper">
   
    <div class="main-wrap">
      <div class="content"> 
        <!-- PROCESS STEPS START -->
        <div class="portletBlock center">
          <div class="portletBlock center">
			  <ul class="processSteps shadow">
			  	<li class="active"><a href="#" title="<spring:message code="requestInfo.tooltip.step1SelectItems"/>"><span><spring:message code="requestInfo.label.one"/></span><spring:message code="requestInfo.message.selectItems"/></a></li>
				<li ><a href="#" title="<spring:message code="requestInfo.tooltip.step2OrderDetails"/>"><span><spring:message code="requestInfo.label.two"/></span><spring:message code="requestInfo.link.orderDetails"/></a></li>
				<li><a href="#" title="<spring:message code="requestInfo.tooltip.step3Review"/>"><span><spring:message code="requestInfo.label.three"/></span><spring:message code="requestInfo.link.review"/></a></li>
				<li class="last"><a href="#" title="<spring:message code="requestInfo.tooltip.stepConfirmation"/>"><span><spring:message code="requestInfo.label.four"/></span><spring:message code="requestInfo.link.confirmation"/></a></li>
			  </ul>
		  </div>
        </div>
        <!-- PROCESS STEPS END -->
       	
      		<div><ul class="form error wFull error-Msg-Popup" id="errorMsgPopup" ></ul></div>
      
<%-- 		<c:if test='${sessionScope.accountCurrentDetails["rowCount"]!="1"}'> --%>
        <h3 class="pageTitle"><spring:message code="requestInfo.heading.catalogFor"/>
        
        <c:set var = "accountName" value = "${sessionScope.accountCurrentDetails['accountName'] }"/>
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
<%--     	</c:if> --%>
        	<c:if test='${sessionScope.accountCurrentDetails["rowCount"]>"1"}'>
        		<a href="#" onclick="showValidation();" class="edit" id="changeaccount"><spring:message code="requestInfo.link.changeAccount"/></a>
        	</c:if>
       		
       		       
        </h3>
        <div style="position:relative;">
        <div class="portletBlock infoBox rounded shadow split backgroundLight" style="position:relative;">
          <div class="columnsTwo" style="position:relative;">
            <div class="columnInner" style="position:relative;">
               <h4><spring:message code="requestInfo.header.selection"/></h4>
               <ul class="form">
                <li>
                  <label><spring:message code="requestInfo.label.billingAddress"/> <span class="req">*</span> </label>
                  <span id="combo_zone1" name="combo_zone1" style="position:static!important;"></span>
                  <span id="billToSingle"></span>
                </li>
              </ul>
            </div>
          </div>
          <div class="columnsTwo">
            <div class="columnInner">
            <!-- changed on June 11, 2013 for adding X image in header  <h4>&nbsp;<img src="<html:rootPath/>images/close.png" id="closeImage" style="display:none"  class="floatR positionUp" onclick="deleteCatalogDetails();" /></h4>-->              
              <!-- changed for defect 7992--> <div id="hideNewSelection"><h4>&nbsp;<a href="#" title='<spring:message code="requestInfo.link.newselection"/>' id="closeImage" style="display:none"  class="floatR positionUp" onclick="deleteCatalogDetails();">
               <spring:message code="requestInfo.link.newselection"/></a></h4></div>
               <div id="showaltSelection"><h4>&nbsp;</h4></div>
              <table><tr><td style="width:80%;"><ul class="form">
                <li>
                  <label for="payType"><spring:message code="requestInfo.label.paymentType"/> <span class="req">*</span></label>
                  
                  <span id="showPaymentType"><select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span><span id="paymentLoading" class="treeLoading" style="display:none;width:auto!important;float:left!important;"><img src="<html:imagesPath/>loading-icon.gif"/></span>
                </li>
              </ul>
			  </td><td style="width:15%;">
					<button class="button" onClick="return showSearch();" id="applyButton" style="position:static!important;"><spring:message code="button.apply"/></button>
					
              </td></tr></table>
            </div>
          </div>
         
        </div></div>
        <div class="portletBlock infoBox rounded shadow split" id="searchCatalog" style="display:none;">
          <div class="columnsTwo">
            <div class="columnInner margin2pr">
              <h4><spring:message code="serviceRequest.description.search"/></h4>
              <ul class="form">
				<li><label for="productType"><spring:message code="requestInfo.label.productType"/> </label> 
				
				<span id="showProductType"><select id="productType"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span><span id="helpProductType"><img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.label.productType"/>" ></span><span id="productTypeLoading" class="treeLoading" style="display:none;width:auto!important;float:left!important;"><img src="<html:imagesPath/>loading-icon.gif"/></span>
				</li>
<!-- 				Changes for Defect 14957 -->
				<li><label for="productModel"><spring:message code="requestInfo.label.productModel"/> </label>
				
				<span id="showProductModel"><select id="productModel"><option value=""><spring:message code="requestInfo.option.select"/></option></select></span><span id="helpProductModel"><img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.label.productModel"/>"></span><span id="productModelLoading" class="treeLoading" style="display:none;width:auto!important;float:left!important;"><img src="<html:imagesPath/>loading-icon.gif"/></span>	
				</li>
			</ul>
              <button class="button floatR margTop" id="noAccButton" onClick="doSearchHardwareByModel('bundleMain');"><spring:message code="button.find"/></button>
            </div>
             
          </div>
          <div class="columnsTwo">
            <div class="columnInner" id="partNoAcc">
              <h4>&nbsp;</h4>
              <p class="inlineTitle compact"><spring:message code="requestInfo.label.enterPartNumber"/></p>
              <!-- <div class="wHalf fontFindPartNumber"><spring:message code="requestInfo.heading.findPartNumber"/></div>  -->
              <div class="lineClear"></div>
              <div style="float:left;width:100%;">
              <input type="text" class="w200" id="partNumber" maxlength="20" /><img class="helpIcon ui_icon_sprite info-icon"  src="<html:imagesPath/>transparent.png" title="<spring:message code="requestInfo.heading.findPartNumber"/>" ></div>
              <div style="float:right;width:auto;">
              <button class="button"  onClick="doSearchHardwareByPartNumber();"><spring:message code="button.find"/></button></div>
              <div style="clear:both;"></div>             
          	</div>
          </div>
        </div>
        <div id="loadingNotification" class='gridLoading' style="display:none">
  				<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
		</div>
        <!-- Search result begins -->
        <div id="showBundle" style="display:none" class="relativeI"> 
        <p class="info inlineTitle" id="removeAccheader"><spring:message code="requestInfo.heading.selectedHardware"/></p>
        <input type="hidden" id="isBundle" value="false"/>
        <div class="columnInner" id="removeDescSection">       
          <table class="displayGrid noBorder rounded shadow wFull">
            <tr>
              <td class="pModel w100" id="productImage"></td>
              <td class="w500"><ul class="form">
                  <li>
                  <!-- 				Changes for Defect 14957 -->
                    <label><spring:message code="serviceRequest.label.productModel"/>:</label>
                    <span id="proModel"></span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.description"/></label>
                    <span class="multiLine" id="proDesc"></span></li>
                </ul></td>
			  <td class="w50"></td>
              <td class="w200"><ul class="form">
                  <li>
                    <label><spring:message code="requestInfo.heading.deviceType"/></label>
                    <span id="deviceType"></span></li>
                  <li>
                    <label><spring:message code="requestInfo.heading.colorMono"/></label>
                    <span id="color_mono"></span></li>
                </ul></td>
             
            </tr>
          </table>
        </div>
       	 <div id="tabs" class="nestedTabs ui-tabs ui-widget ui-widget-content ui-corner-all">
          <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-al">
          	<li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" id="tabLi1"><a onclick="doSearchBundle();" href="#tabs-1"><spring:message code="requestInfo.heading.preConf"/></a></li>
            <li class="ui-state-default ui-corner-top" id="tabLi2" ><a id="tab2anchor" onclick="doSearchHardwareByModel('accessories');" href="#tabs-2"><spring:message code="requestInfo.heading.accessories"/></a></li>
            
          </ul>
          <div class="tabContent">
            <div id="tabs-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
              <div id="bundles_container"></div> 
<!--   		      <div class="pagination"> <span id="bundlesPagingArea"></span><span id="bundlesInfoArea"></span> </div> -->
            </div>
            <div id="tabs-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hiden">            
             <div id="accessories_container"></div>
   		     <div class="pagination"> <span id="pagingArea"></span><span id="infoArea"></span> </div>		
			<div id="accessoriesLoading" class='gridLoading' style="display:none">
	  				<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
			</div>	
			<p id="noRecordsAcc" style="display:none;font-size:15px!important;margin:5px!important;"></p>		
            </div>
            <div id="tabs-3" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hiden">
             <div id="supplies_container"></div> 
<!--   		      <div class="pagination"> <span id="pagingArea3"></span><span id="infoArea3"></span> </div>  -->
			<div id="suppliesLoading" class='gridLoading' style="display:none">
	  				<!--spring:message code='loadingNotification' />&nbsp;&nbsp;--><img src="<html:imagesPath/>gridloading.gif"/>
			</div>
			<div id="noRecordsSuppl" style="display:block;font-size:14px!important;"></div>
            </div>
          </div>
          </div>
	     
	     <div id="buttonListDiv" class="buttonContainer relativeI" style="display:none">
			<button class="button_cancel relativeI" type="button" onclick="javascript:redirectToHistory('hardware');"><spring:message code="button.cancel"/></button>
			<button class="button relativeI" type="button" onClick="javascript:submitCatalog();"><spring:message code="button.continue"/></button>
		</div></div>
      </div>
    </div>
    <div id="cartLoad" style="display:none"></div>
  </div>
<div id="fakeId"></div>
<div id="fakeId2"></div>
<div style="display: none!important;">
<input type="text" id="paymentTypeBox"/>
</div>
<script type="text/javascript">
<%-- Changes for MPS 2.1--%>
var isCartUpdated=false;
var singlePaymentType=false;
var payNowFlag= false;

var orderCurrency = "${orderedCurrentcy}";
<%--Ends Changes for MPS 2.1--%>
	/*This function is used to redirect the user to the hardware details page*/
	function submitCatalog(){
		
		var validationFlagPopup = true;
		document.getElementById('errorMsgPopup').style.display = "none";
		document.getElementById('errorMsgPopup').innerHTML = "";
		var cartValue = document.getElementById("cartQuantity").value;//${cartQuantity};//cartQuantity
		
		if(cartValue == 0) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.quantity.cartIsEmpty"/></strong></li>');
			validationFlagPopup = false;
		}
		
		if (!validationFlagPopup)
		{	
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);
			return false;
		}
		showOverlay();
		window.location.href='<portlet:renderURL><portlet:param name="action" value="showHardwareDetailPage" /></portlet:renderURL>';
	}
	/* End of hardware details page function */
	
	/**
	 *The following section is used for populating the Bill To Address List based on the selected Agreement
	 *And on selection of an address select the specific Sold To number and 
	 *retrieve the Billing models/Payments types based on that
	 */
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
				hideDiv("showBundle");
				hideDiv("buttonListDiv");
				//combo.disable(true);
				//jQuery("#combo_zone1").addClass('disableInput');
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
							 resultJSON=results;
							// alert(resultJSON.paymentTypeHtml);
						}catch(e){}
						if(resultJSON !=null){
							if(resultJSON.paymentTypeHtml != null){
								jQuery('#showPaymentType').html(resultJSON.paymentTypeHtml);
							}else{
								jQuery('#showPaymentType').html('');
							}
							if(resultJSON.singlePaymentType != null && resultJSON.singlePaymentType=='true'){
								jQuery("#paymentTypeBox").val(resultJSON.paymentTypeHtml);
								singlePaymentType = true;
								jQuery("#billToSingle").html(jQuery(billToSelected).html());
								jQuery("#billToSingle").show();
								jQuery("#combo_zone1").hide();
								jQuery("#searchCatalog").show();
								document.getElementById('applyButton').style.display='none';
								document.getElementById('closeImage').style.display='block';	
								getProductType();	
							}
						}
					}
				});	 
			}else{
				jQuery("#paymentLoading").hide();
				jQuery("#paymentType").removeAttr('disabled');				    
				//jQuery("#showPaymentType").html('<select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select>');
				jQuery("#showPaymentType").show();
			}
		});
	</c:if>	
	/* End of bill To address selection and payment type population section */

	function show_load(li_id)
	{
		jQuery("#"+li_id).show();
	}
	jQuery(document).ready(function(){
		var currentURL = window.location.href;
//		Change Account Link Hide/Show for CI-7 Defect #12274
	if(currentURL.indexOf('/partner-portal') == -1)
	  {	
		  jQuery('#changeaccount').show();
	  }
	else
		{
		jQuery('#changeaccount').hide();
		}
		jQuery("#hideNewSelection").show();
		jQuery("#showaltSelection").hide();
		<%-- CHanges for MPS 2.1 defect 7954 --%>
		document.getElementById("cartQuantity").value = '${cartQuantity}';
		<%--ENds CHanges for MPS 2.1 defect 7954 --%>
		/*The following section is used for Tab settings*/
		jQuery("#tabs .ui-tabs-nav li").hover(function(){
			jQuery(this).addClass("ui-state-hover");
		},function() {
			//jQuery(this).removeClass("ui-state-hover");
		});

		jQuery("#tabs .ui-tabs-nav li").focus(function(){
			jQuery(this).addClass("ui-state-focus");
		},function() {
			jQuery(this).removeClass("ui-state-focus");
		});

		jQuery("#tabs .ui-tabs-nav li").click(function(){
			jQuery("#tabs .ui-tabs-nav li").removeClass("ui-tabs-selected");
			jQuery("#tabs .ui-tabs-nav li").removeClass("ui-state-active");
			jQuery(this).addClass("ui-tabs-selected");
			jQuery(this).addClass("ui-state-active");

			jQuery("#tabs .tabContent .ui-tabs-panel").addClass("ui-tabs-hiden");
			jQuery("#tabs .tabContent .ui-tabs-panel[0]").removeClass("ui-tabs-hiden");
			var Objid = jQuery(this).children().attr("href");
			jQuery("#tabs .tabContent "+Objid).removeClass("ui-tabs-hiden");
			
		});
		/*End of Tab setting section */
		
		/**
		* The following section is used to load the selected billing address and payment type 
		* If user navigates to the Hardware selection page without clearing the session
		*/
		<c:if test="${not empty sessionScope.hardwareCurrentDetails}">
		
			jQuery("#searchCatalog").show();
			var soldToNumber = "${sessionScope.hardwareCurrentDetails['soldToNumber']}";
			var paymentType = "${sessionScope.hardwareCurrentDetails['paymentTypeText']}";
			var billToAddress = "";
			<c:if test="${not empty sessionScope.selectedHWBillToAddress}"> 
	          	<c:if test="${!empty sessionScope.selectedHWBillToAddress.addressLine1}">
	          		billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.addressLine1}"+"<br>";
				</c:if>
				<c:if test="${!empty sessionScope.selectedHWBillToAddress.addressLine2}">
					billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.addressLine2}"+"<br>";
				</c:if>
				<c:if test="${!empty sessionScope.selectedHWBillToAddress.city}">
					billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.city}";
					<c:if test="${(!empty sessionScope.selectedHWBillToAddress.state) || (!empty sessionScope.selectedHWBillToAddress.country)}">
						billToAddress = billToAddress + ",";
					</c:if>
				</c:if>	
				<c:choose>
					<c:when test="${!empty sessionScope.selectedHWBillToAddress.state}">
						billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.state}";
						<c:if test="${!empty sessionScope.selectedHWBillToAddress.country}">
							billToAddress = billToAddress + ",";
						</c:if>
					</c:when>
					<c:when test="${!empty sessionScope.selectedHWBillToAddress.province}">
						billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.province}";
						<c:if test="${!empty sessionScope.selectedHWBillToAddress.country}">
							billToAddress = billToAddress + ",";
						</c:if>
					</c:when>
					<c:when test="${!empty sessionScope.selectedHWBillToAddress.county}">
						billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.county}";
						<c:if test="${!empty sessionScope.selectedHWBillToAddress.country}">
							billToAddress = billToAddress + ",";
						</c:if>
					</c:when>
				</c:choose>
				
				<c:if test="${!empty sessionScope.selectedHWBillToAddress.country}">	
					billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.country}";
					<c:if test="${!empty sessionScope.selectedHWBillToAddress.postalCode}">
						billToAddress = billToAddress + "<br/>";
					</c:if>
				</c:if>
				<c:if test="${!empty sessionScope.selectedHWBillToAddress.postalCode}">	
					billToAddress = billToAddress + "${sessionScope.selectedHWBillToAddress.postalCode}"+"&nbsp";
				</c:if>
			</c:if>
			jQuery("#combo_zone1").hide();
			jQuery("#billToSingle").html(billToAddress);
			jQuery("#billToSingle").show();
			jQuery("#showPaymentType").html(paymentType);
			jQuery("#paymentTypeBox").val("${sessionScope.hardwareCurrentDetails['paymentType']}");
			getProductType();
			document.getElementById("applyButton").style.display="none";
			document.getElementById("closeImage").style.display="block";
			showDiv("buttonListDiv");
		</c:if>
		/* End */
		
		
		/**
		* The following section is used to load the selected billing address as prepopulated if there is only one
		* billing address
		*/
		<c:if test="${not empty singleBillToAddress && empty sessionScope.hardwareCurrentDetails}">
			
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
			hideDiv("showBundle");
			hideDiv("buttonListDiv");
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
							jQuery("#paymentTypeBox").val(resultJSON.paymentTypeHtml.trim());
							jQuery('#showPaymentType').html(resultJSON.paymentTypeHtml);
						}else{
							jQuery('#showPaymentType').html('');
						}
						if(resultJSON.singlePaymentType != null && resultJSON.singlePaymentType=='true'){
							jQuery("#paymentTypeBox").val(resultJSON.paymentTypeHtml);
							jQuery("#hideNewSelection").hide();
							jQuery("#showaltSelection").show();
							singlePaymentType = true;
							jQuery("#searchCatalog").show();
							document.getElementById('applyButton').style.display='none';
							document.getElementById('closeImage').style.display='block';
							getProductType();							
						}
					}
				}
			});	
		</c:if>
		/* End */
	});

	
	
	
	
	var bundlesGrid;
	var headerString = ",,,,";	
	bundlesGrid = new dhtmlXGridObject('bundles_container');
	bundlesGrid.setImagePath("../../../../images/gridImgs/");
	bundlesGrid.setHeader(headerString);
	
	bundlesGrid.setInitWidths("305,100,100,*,295");
	bundlesGrid.setColumnMinWidth("110,100,100,110,110");
	bundlesGrid.enableResizing("true,false,false,true,true");
	bundlesGrid.setColAlign("left,left,left,left,left");
	bundlesGrid.setSkin("light");
	bundlesGrid.setNoHeader("true");
	bundlesGrid.setColTypes("ro,ro,ro,ro,ro"); 	
	bundlesGrid.enableAutoHeight(true);
	bundlesGrid.enableMultiline(true);
	bundlesGrid.enableColumnMove(true);	
	bundlesGrid.init();
	bundlesGrid.prftInit();
	//bundlesGrid.enablePaging(true, 2, 3, "bundlesPagingArea", true, "bundlesInfoArea");
	//bundlesGrid.setPagingSkin("bricks"); 	 
	//bundlesGrid.parse(ar,"jsarray");

	var accessoriesGrid;
	var headerString = ",,,";
	accessoriesGrid = new dhtmlXGridObject('accessories_container');
	accessoriesGrid.setImagePath("../../../../images/gridImgs/");
	accessoriesGrid.setHeader(headerString);
	
	accessoriesGrid.setInitWidths("175,320,*,295");
	accessoriesGrid.setColumnMinWidth("110,110,110,110");
	accessoriesGrid.enableResizing("true,true,true,true");
	accessoriesGrid.setColAlign("left,left,left,left");
	accessoriesGrid.setSkin("light");
	accessoriesGrid.setNoHeader("true");
	accessoriesGrid.setColTypes("ro,ro,ro,ro");
	accessoriesGrid.enableAutoHeight(true);
	accessoriesGrid.enableMultiline(true);
	accessoriesGrid.enableColumnMove(true);
	accessoriesGrid.init();
	accessoriesGrid.enablePaging(true, 5,5, "pagingArea", true, "infoArea");
	accessoriesGrid.setPagingSkin("bricks"); 	 
	//accessoriesGrid.parse(ar,"jsarray");
	accessoriesGrid.attachEvent("onXLS", function() {
		jQuery('#accessories_container').hide();
		jQuery('#pagingArea').hide();
		showDiv('accessoriesLoading');
        });
	accessoriesGrid.attachEvent("onXLE", function() {	
		var noProductFlag=false;
		if(accessoriesGrid.getRowsNum()==0){
			jQuery('#accessoriesLoading').hide();
			showDiv('noRecordsAcc');
			jQuery('#noRecordsAcc').html('No Records Found');
		}else{
		jQuery('#accessoriesLoading').hide();
		showDiv('accessories_container');
		jQuery('#pagingArea').show();
		}
        });
	
	
	var suppliesGrid;
	var headerString = ",,,";
	suppliesGrid = new dhtmlXGridObject('supplies_container');
	suppliesGrid.setImagePath("../../../../images/gridImgs/");
	suppliesGrid.setHeader(headerString);	
	suppliesGrid.setInitWidths("175,320,*,295");
	suppliesGrid.setColumnMinWidth("110,110,110,110");
	suppliesGrid.enableResizing("true,true,true,true");
	suppliesGrid.setColAlign("left,left,left,left");
	suppliesGrid.setSkin("light");
	suppliesGrid.setNoHeader("true");
	suppliesGrid.setColTypes("ro,ro,ro,ro");
	suppliesGrid.enableAutoHeight(true);
	suppliesGrid.enableMultiline(true);
	suppliesGrid.enableColumnMove(true);
	suppliesGrid.init();
	//suppliesGrid.enablePaging(true, 2, 3, "pagingArea3", true, "infoArea3");
	suppliesGrid.setPagingSkin("bricks"); 	 
	//suppliesGrid.parse(ar,"jsarray");

	function show() {
		//alert(document.getElementById('prodType').value);
		if(document.getElementById('prodType').value !== "select") { 
			document.getElementById('showBundle').style.display = "block";
		}
		else {
			document.getElementById('showBundle').style.display = "none";
		}
	}
	
	function showDiv(id){		
		var elm = document.getElementById(id);	
		elm.style.display = "block";		
		}
	
	function hideDiv(id){	
		var elm = document.getElementById(id);
		elm.style.display = "none";	
			
		}
	
	
	/*The following function is used for getting the list of Product types based on the selected Sold To number and Payment type.*/
	function getProductType(){
		if(document.getElementById('searchCatalog')){
			jQuery("#searchCatalog").show();
		}
		
		if(document.getElementById('tabLi1')){
			jQuery('#tabLi1').show();	
		}
		if(document.getElementById('tabLi2')){
			jQuery('#tabLi2').show();	
		}
		
		payNowFlag=false;
		//var soldTo = combo.getSelectedValue();
		//var paymentType = document.getElementById("paymentType").value;
		jQuery("#productTypeLoading").show();
		//jQuery("#showProductType").hide();
		jQuery("#productType").attr('disabled','disabled');
		//jQuery("#helpProductType").hide();
		document.getElementById('errorMsgPopup').style.display = "none";		
		document.getElementById("showProductModel").innerHTML = "<select id=\"productModel\" onmousedown=\"jQuery(this).removeClass('errorColor');\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
		document.getElementById("partNumber").value = '';
		//alert('productType value is '+productType);
		/*if(productType==""){
			//alert('This time you should not do anything');
			return false;
		}*/
		//alert('Product type value is '+productType);
		var productTypeURL="<portlet:resourceURL id="getProductType"/>&random="+Math.random()*99999;
		//alert('productModelURL is '+productModelURL);
		jQuery("#showProductType").load(productTypeURL,function(response){
			var flag= false;
			if(response.indexOf("No Product Available") !=-1){
				flag=true;
			}
			if(jQuery("#paymentTypeBox").val().trim() !="Ship and Bill" && jQuery("#paymentTypeBox").val().trim() !="Pay Now"){
				jQuery('#tabLi2').hide();
			}else{
				if(flag) {
					jQuery("#searchCatalog").hide();
					jConfirm('<spring:message code="requestInfo.hardware.confirm"/>', "", function(confirmResult) {
						if(confirmResult){
							jQuery('#isBundle').val("false");
							payNowFlag = true;							
							doSearchHardwareByModel('accessories');
						}else{
							return false;
							}
					});
				}
			}
			
		//alert('getProductModel is called');
			//alert(response);
			jQuery("#productTypeLoading").hide();
			jQuery("#showProductType").show();
			jQuery("#helpProductType").show();
			jQuery("#productType").removeAttr('disabled');
			document.getElementById("showProductType").innerHTML = response;
			
			});
	}
	
	/*The following function is used for getting the list of Product Models based on the selected Sold To number, Payment type and Product Type.*/
	function getProductModel(){
		var productType = document.getElementById("productType").value;
		<%--Changes for Defect 8244 MPS 2.1 --%>
		if(productType==""){
			jQuery('#productModel').remove();
			return;
		}
		<%--Ends Changes for Defect 8244  MPS 2.1--%>
		document.getElementById('errorMsgPopup').style.display = "none";
		jQuery('#productType').removeClass('errorColor');
		hideDiv("showBundle");
		hideDiv("buttonListDiv");
		jQuery("#productModelLoading").show();
		jQuery("#productModel").attr('disabled','disabled');
		//jQuery("#showProductModel").hide();
		//jQuery("#helpProductModel").hide();
		jQuery("#productType").attr('disabled','disabled');
		document.getElementById("partNumber").value = '';
		
		//alert('Product type value is '+productType);
		var productModelURL="<portlet:resourceURL id="getProductModel"/>&productType="+productType+"&random="+Math.random()*99999;
		jQuery("#showProductModel").load(productModelURL,function(response){
			jQuery("#productModelLoading").hide();
			jQuery("#productModel").removeAttr('disabled');
			jQuery("#showProductModel").show();
			jQuery("#helpProductModel").show();
			document.getElementById("showProductModel").innerHTML = response;
			jQuery("#productType").removeAttr('disabled');
			<%--Changes for Defect 8244 MPS 2.1 --%>
			jQuery("#productModel").mousedown(function(){jQuery(this).removeClass('errorColor');jQuery('#errorMsgPopup').hide();});
			<%--Ends Changes for Defect 8244  MPS 2.1--%>
			});
	}
	
	function hideShowTab(){
		if(jQuery('#hardwareTypeBundle').val()==""){
			//alert('hide bundle');
			hideDiv("tabLi1");
		}
	}
	
	/*The following function is used for only searching the list of Bundles.*/
	function doSearchBundle(){
		if(jQuery("#productType").val()!=null && jQuery("#productModel").val()!=null){
			doSearchHardwareByModel('bundle');
		}else if(jQuery("#partNumber").val()!=null){
			doSearchHardwareByPartNumber();
		}
	};
	
	/*The following function is used for searching all kind of Hardwares based on Product Type and Product Model*/
	function doSearchHardwareByModel(hardwareType){	
		var partNumber;
		var productType;
		var productModel;
		if(document.getElementById("partNumber") != null){
			partNumber = document.getElementById("partNumber").value;	
		}
		if(document.getElementById("productType") != null){
			productType = document.getElementById("productType").value;
		}
		if(document.getElementById("productModel") != null){
			productModel = document.getElementById("productModel").value;
		}
		var validationFlagPopup = true;
		document.getElementById('errorMsgPopup').style.display = "none";
		document.getElementById('errorMsgPopup').innerHTML = "";
		if(hardwareType != 'accessories' && (productType==null ||productType=='' || productModel==null||productModel=='') && !payNowFlag) {
		
			if(hardwareType == 'bundle'){
				if(partNumber!=null && partNumber!= ""){
					hideDiv('accessories_container');
					show_load('bundles_container');
					return;
				}
			}
			if(hardwareType == 'accessories'){
				jQuery('#isBundle').val('false');
				if(partNumber!=null && partNumber!= ""){
					hideDiv('accessories_container');
					jQuery('#noRecordsAcc').html('No Records Found');
					showDiv('noRecordsAcc');
					//show_load('accessories_container');
					return;
				}
			}
			
			if(productType==null ||productType==''){
				jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.productType.empty"/></strong></li>');
				jQuery('#productType').addClass('errorColor');
				}
			if(productModel==null||productModel==''){
				jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.productModel.empty"/></strong></li>');
				jQuery('#productModel').addClass('errorColor');
				}			
			validationFlagPopup = false;
			
			
		}else{
			//window.location.href="${retriveHardwarePartListURL}&productModel=" +productModel+ "&productType=" + productType;
			
			if(hardwareType == 'bundleMain' || payNowFlag){
				
				if(payNowFlag){
					jQuery('#isBundle').val('false');
					jQuery('#removeAccheader').hide();
					jQuery('#removeDescSection').hide();
					jQuery('#tabLi1').removeClass('ui-state-active');
					jQuery('#tabLi2').addClass('ui-state-active');
					jQuery('#tabLi1').removeClass('ui-tabs-selected');
					jQuery('#tabLi2').addClass('ui-tabs-selected');
					
					jQuery('#tabs-1').addClass('ui-tabs-hiden');
					jQuery('#tabs-2').removeClass('ui-tabs-hiden');
					jQuery('#tabLi1').hide();
				}else{
					jQuery('#removeAccheader').show();
					jQuery('#removeDescSection').show();
					jQuery('#tabLi1').addClass('ui-state-active');
					jQuery('#tabLi2').removeClass('ui-state-active');
					jQuery('#tabLi1').addClass('ui-tabs-selected');
					jQuery('#tabLi2').removeClass('ui-tabs-selected');
					
					jQuery('#tabs-1').removeClass('ui-tabs-hiden');
					jQuery('#tabs-2').addClass('ui-tabs-hiden');
					showDiv('loadingNotification');
					hideDiv('showBundle');
				}
				
				
				
				
			}else if(hardwareType == 'accessories'){
				jQuery('#isBundle').val('false');
					hideDiv('accessories_container');
					jQuery('#noRecordsAcc').html('');
					hideDiv('noRecordsAcc');
					showDiv('accessoriesLoading');
			}
			
			document.getElementById('errorMsgPopup').style.display = "none";
			jQuery('#productType').removeClass('errorColor');
			jQuery('#productModel').removeClass('errorColor');
			var connectUrl="";
			if(hardwareType == 'bundle' || hardwareType == 'bundleMain'){
				showOverlay();
				connectUrl = "<portlet:resourceURL id='retriveHardwarePartListURL'></portlet:resourceURL>";
				jQuery.ajax({
					url:connectUrl,
					data:{
							productModel:productModel,
						  	productType:productType
						 },
					type:'POST',
					dataType: 'JSON',		
					success: function(results){
						hideOverlay();
						var resultJSON;
						try{
							 //alert('results='+results);
							 //alert('window.navigator.appName='+navigator.userAgent);
							 var userAgent = window.navigator.userAgent;
							 var msie = userAgent.indexOf("MSIE 7.0");
							 //alert('msie='+msie);
							 if(msie==-1)
								//fix for defect 7879
							 	var stringified = JSON.stringify(results);
							 //alert('stringified='+stringified);
							 //resultJSON=jQuery.parseJSON(results);
							 
							 resultJSON=results;
						}catch(e){
							
							}
						if(resultJSON !=null){						
								if(hardwareType == 'bundle' || hardwareType == 'bundleMain'){
									var count = resultJSON.recordCount;
									//count = 0;//To be commented
									//alert("I am here row count:: "+ count);
									if(count != null){
										if(count != 0){
											updateGridsBundle(resultJSON);
										}else{
											if(hardwareType == 'bundle'){
												hideDiv('accessories_container');
												show_load('bundles_container');
												updateGridsBundle(resultJSON);
											}
											else{
												showAccessories();
												jQuery('#tabLi2').addClass('ui-state-active');
												jQuery('#tabLi1').removeClass('ui-state-active');
												jQuery('#tabLi1').removeClass('ui-tabs-selected');
												jQuery('#tabLi2').addClass('ui-tabs-selected');
												jQuery('#tabs-1').addClass('ui-tabs-hiden');
												jQuery('#tabs-2').removeClass('ui-tabs-hiden');
												hideDiv('#accessoriesLoading');
												show_load('accessories_container');
											}
										}
									}
									
								}
								
							}
						}
					});
			}else if(hardwareType == 'accessories'){
				jQuery('#isBundle').val('false');
				connectUrl = "<portlet:resourceURL id='retriveAccessoriesPartListURL'></portlet:resourceURL>";
				accessoriesGrid.clearAndLoad(connectUrl);
			}
			if(hardwareType == 'bundleMain' || payNowFlag){
				hideDiv("loadingNotification");
				showDiv("showBundle");
				showDiv("buttonListDiv");
			}else if(hardwareType == 'accessories'){
				jQuery('#isBundle').val('false');
				hideDiv("accessoriesLoading");							
			}
			
		}
		if (!validationFlagPopup)
		{	
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);
			hideDiv("loadingNotification");
			return false;
		}			
		return true;
	}
	
	/*The following function is used for searching Bundles/Printers based on Part Number*/
	function doSearchHardwareByPartNumber(){
		
		jQuery('#tabLi1').addClass('ui-state-active');
		jQuery('#tabLi2').removeClass('ui-state-active');
		jQuery('#tabLi3').removeClass('ui-state-active');
		jQuery('#tabLi1').addClass('ui-tabs-selected');
		jQuery('#tabLi2').removeClass('ui-tabs-selected');
		jQuery('#tabLi3').removeClass('ui-tabs-selected');
		
		jQuery('#tabs-1').removeClass('ui-tabs-hiden');
		jQuery('#tabs-2').addClass('ui-tabs-hiden');
		jQuery('#tabs-3').addClass('ui-tabs-hiden');
		
		var partNumber = document.getElementById("partNumber").value;
		var validationFlagPopup = true;
		document.getElementById("productType").value = "";
		document.getElementById("productModel").value = "";
		document.getElementById('errorMsgPopup').style.display = "none";
		document.getElementById('errorMsgPopup').innerHTML = "";
		if(partNumber==null||partNumber=='' ) {
			jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.partNumber.empty"/></strong></li>');
			validationFlagPopup = false;
			jQuery('#partNumber').addClass('errorColor');
		}else{
			document.getElementById('errorMsgPopup').style.display = "none";
			showDiv('loadingNotification');
			hideDiv('showBundle');
			
			jQuery('#partNumber').removeClass('errorColor');
			jQuery.ajax({
				url:"<portlet:resourceURL id='retriveHardwarePartListURL'></portlet:resourceURL>",
				data:{
						partNumber:partNumber
					 },
				type:'POST',
				dataType: 'JSON',		
				success: function(results){
					//jQuery('#processing').hide();
					var resultJSON;
					try{
						 var userAgent = window.navigator.userAgent;
						 var msie = userAgent.indexOf("MSIE 7.0");
						 //alert('msie='+msie);
						 if(msie==-1)
						  //fix for defect 7879
						 var stringified = JSON.stringify(results);
						 //resultJSON=jQuery.parseJSON(results);
						 resultJSON=results;
					}catch(e){
						
						}
					if(resultJSON !=null){
							updateGridsBundle(resultJSON);
							hideDiv("loadingNotification");
							showDiv("showBundle");
							showDiv("buttonListDiv");
						}			
						else {	
							//alert('in error');
							jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.partNumber.notvalid"/></strong></li>');
							jQuery('#errorMsgPopup').show();
							hideDiv("loadingNotification");
							//jQuery("#errorDiv").show();
						}
					}
				});
		}
		if (!validationFlagPopup)
		{	
			document.getElementById('errorMsgPopup').style.display = "block";
			jQuery(document).scrollTop(0);
			hideDiv("loadingNotification");
			return false;
		}
		return true;		
	}
	
	/*The following function is used for updating the Bundle Grid based on the fetched data from Siebel/aMind*/
	function updateGridsBundle(resultJSON)
	{
		if(resultJSON.productModel != null){
			jQuery('#proModel').html(resultJSON.productModel);
		}else{
			jQuery('#proModel').html('');
		}
		if(resultJSON.productDesc != null){
			jQuery('#proDesc').html(resultJSON.productDesc);
		}else{
			jQuery('#proDesc').html('');
		}
		if(resultJSON.deviceType != null){
			jQuery('#deviceType').html(resultJSON.deviceType);
		}else{
			jQuery('#deviceType').html('');
		}
		if(resultJSON.color_mono != null){
			jQuery('#color_mono').html(resultJSON.color_mono);
		}else{
			jQuery('#color_mono').html('');
		}
		if(resultJSON.partImage != null){
			jQuery('#productImage').html(resultJSON.partImage);
		}else{
			jQuery('#productImage').html('');
		}
		if(resultJSON.bundleXML != null){
			jQuery('#isBundle').val('true');
			var bundleXML = resultJSON.bundleXML.replace(/='/g,'=\"');
			bundleXML = bundleXML.replace(/' /g,'\" ');
			bundleXML = bundleXML.replace(/'>/g,'\">');
			bundleXML = bundleXML.replace(/'\//g,'\"/');
			bundlesGrid.clearAll();
			bundlesGrid.parse(bundleXML); 
			bundlesGrid.setColumnHidden(1,true);
		 	bundlesGrid.setColumnHidden(2,true);
		}
		
		show_load('bundles_container');	
		
	}
	
	/*The following function is used for updating the Accessories Grid based on the fetched data from Siebel/aMind*/
	function updateGridsAccessories(resultJSON)
	{
		if(resultJSON.accessoriesXML != null){		
			jQuery('#isBundle').val('false');
			var accessoriesXML = resultJSON.accessoriesXML.replace(/='/g,'=\"');
			accessoriesXML = accessoriesXML.replace(/' /g,'\" ');
			accessoriesXML = accessoriesXML.replace(/'>/g,'\">');
			accessoriesXML = accessoriesXML.replace(/'\//g,'\"/');
			//alert(accessoriesXML);
			accessoriesGrid.clearAll();
			accessoriesGrid.clearAndLoad(accessoriesXML);
		}
		show_load('accessories_container');	
		
	}
	
	
	
	/*The following function is used for adding hardware items to cart*/
	function addToCart(rowID, partNumber, supplyId, partDesc, partType, proModel, catalogId, lineId, currency, hardwareType,partNumArr,descArr,qtyArr,salesOrg,imagePath, element) {
			//alert('inside addToCart main page');
			jQuery(this).removeClass('errorColor');
			var validationFlagPopup = true;
			var queryParam = "partQuantity"+rowID;
			var quantity = document.getElementById(queryParam).value;
			var mpsDesc=partDesc;
			var localDesc="";
			document.getElementById('errorMsgPopup').style.display = "none";
			document.getElementById('errorMsgPopup').innerHTML = "";
			jQuery('#'+queryParam).removeClass('errorColor');
			var queryParam = "partQuantity"+rowID;
			//alert(quantity);
			if(jQuery('#isBundle').val()=="true"){
				mpsDesc = bundlesGrid.cellById(rowID,1).getValue();
				localDesc = bundlesGrid.cellById(rowID,2).getValue();
			}
			if(orderCurrency == null || orderCurrency == ''){
				orderCurrency = currency;
			}
			if(quantity==null || quantity==''|| quantity<=0) {
				if(quantity==null || quantity==''){
					jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.quantity.format.errorMsg"/></strong></li>');
					validationFlagPopup = false;
					jQuery('#'+queryParam).addClass('errorColor');
				}else{
					jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.quantity.empty.listPage"/></strong></li>');
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
		
			if(currency !='' && currency != orderCurrency){
				jQuery('#errorMsgPopup').append('<li><strong><spring:message code="validation.currency.typeMismatch"/></strong></li>');
				validationFlagPopup = false;
				jQuery('#'+queryParam).addClass('errorColor');
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
			var queryParam = "partQuantity"+rowID;
			var quantity = document.getElementById(queryParam).value;
			jQuery('#'+queryParam).val(quantity);
			/*if(hardwareType == 'bundle')
			{
				//var cellObj = bundlesGrid.cellById(rowID,2);
				var queryParam = "partQuantity"+rowID;
				var quantity = document.getElementById(queryParam).value;
				jQuery('#'+queryParam).val(quantity);
				cellObj.cell.innerHTML="<div class=\"f200\">"+price+"</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\" onclick=\"focusOnInput('partQuantity"+rowID+"');\" id=\"partQuantity"+rowID+"\" value=\""+quantity+"\"/><span class=\"spaceClear\"/><input class=\"button w120 \" value=\"<spring:message code='requestInfo.button.addToCart'/>\" type=\"button\"  onclick=\"addToCart('"+rowID+"', '"+rowID+"', '"+partDesc+"', '', '', 'partQuantity"+rowID+"','"+price+"','bundle', this);\"/></div><div><input type=\"text\" id=\"partQuantity"+rowID+ "\" class=\"w50\" value=\""+quantity+"\"/></div>";
			}else if(hardwareType == 'accessories'){
				//alert('inside accessories');
				//alert(rowID);
				//alert(accessoriesGrid.cellById(rowID,1));
				//var cellObj = accessoriesGrid.cellById(rowID,3);
				//alert('after celobj');
				var queryParam = "partQuantity"+rowID;
				var quantity = document.getElementById(queryParam).value;
				cellObj.cell.innerHTML="<div class=\"f200\">"+price+"</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\" onclick=\"focusOnInput('partQuantity"+rowID+"');\" id=\"partQuantity"+rowID+"\" value=\""+quantity+"\"/><span class=\"spaceClear\"/><input class=\"button w120 \" value=\"<spring:message code='requestInfo.button.addToCart'/>\" type=\"button\"  onclick=\"addToCart('"+rowID+"', '"+partNumber+"', '"+partDesc+"', '"+partType+"','"+proModel+"', 'partQuantity"+rowID+"','"+price+"','accessories', this);\"/></div><div><input type=\"text\" id=\"partQuantity"+rowID+ "\" class=\"w50\" value=\""+quantity+"\"/></div>";
			}else{
				var cellObj = suppliesGrid.cellById(rowID,3);
				var queryParam = "partQuantity"+rowID;
				var quantity = document.getElementById(queryParam).value;
				cellObj.cell.innerHTML="<div class=\"f200\">"+price+"</div><div class=\"lineClear\"><input class=\"w50\" type=\"text\" name=\"quantity1\" onclick=\"focusOnInput('partQuantity"+rowID+"');\" id=\"partQuantity"+rowID+"\" value=\""+quantity+"\"/><span class=\"spaceClear\"/><input class=\"button w120 \" value=\"<spring:message code='requestInfo.button.addToCart'/>\" type=\"button\"  onclick=\"addToCart('"+rowID+"', '"+partNumber+"', '"+partDesc+"', '"+partType+"','"+proModel+"', 'partQuantity"+rowID+"','"+price+"','supplies', this);\"/></div><div><input type=\"text\" id=\"partQuantity"+rowID+ "\" class=\"w50\" value=\""+quantity+"\"/></div>";
			}*/
			
			//alert('Before selected item ');
			//alert(rowID);
			
			var selectedItem = "&random="+Math.random()*99999+ "&partNumber=" + encodeURIComponent(partNumber) + "&partDesc=" + encodeURIComponent(mpsDesc) + "&localDesc=" + encodeURIComponent(localDesc) + "&partType=" + encodeURIComponent(partType) + "&supplyId="+supplyId+ "&partQty="+quantity +"&catalogId="+rowID+ "&proModel="+encodeURIComponent(proModel) + "&hardwareType="+hardwareType+ "&currency="+currency+"&partNumArr="+partNumArr+"&descArr="+encodeURIComponent(descArr)+"&qtyArr="+encodeURIComponent(qtyArr)+"&salesOrg="+encodeURIComponent(salesOrg)+"&imagePath="+encodeURIComponent(imagePath)+"&lineId="+lineId;
			//alert('selectedItem is '+selectedItem);
			var hardwareSessionURL = '<portlet:resourceURL id="addToCartURL"/>'+selectedItem;
			//alert(catalogSessionURL);
			<%-- CHanges for MPS 2.1 defect 7954--%>
			showOverlay();
			jQuery("#cartQuantity").load(hardwareSessionURL,function(response){
			//alert('catalogSessionURL resourcemapping called');
				//alert('response '+response);
				hideOverlay();
				document.getElementById("cartQuantity").value = response;
				});
			<%-- Ends CHanges for MPS 2.1 defect 7954--%>
			//Added for find button movement issue
			/* if (window.PIE) {
				document.getElementById("findCatalogList").fireEvent("onmove");
			} */
	}
	
	function focusOnInput(id){
		if (window.navigator.userAgent.indexOf("MSIE")>=1){
		//document.getElementById(id).focus();
		setTimeout(function() { document.getElementById(id).focus(); }, 500);
        }
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
		
		/*The following function is used when user clicks on the Continue Shopping link from the Cart View page*/
		//function goToContinueShoppingPageParent(){
		//	var cancelAction = 'cancelAction';
		//	window.location.href = "<portlet:renderURL></portlet:renderURL>&pageFrom="+cancelAction;
		//}
		
		/*The following function is used for checkout link click from cart view page*/
		function goToHardwareDetailPage(){
			window.location.href='<portlet:renderURL><portlet:param name="action" value="showHardwareDetailPage"/></portlet:renderURL>';
		}
		
		/*The following function is used for storing the selected Sold To number, Billing address and payment type values to session*/
		function showSearch() {
				var validationFlagPopup = true;
				jQuery('#errorMsgPopup').hide();
				jQuery('#errorMsgPopup').html("");		
				var paymentType = jQuery("#paymentType").val();
				if(soldTo==""){
					validationFlagPopup = false;
					jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.validation.billTo.empty"/></strong></li>');			
				}
				if((paymentType == null || paymentType == "") && singlePaymentType!='true'){
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
					jQuery("#paymentTypeBox").val(decodeURIComponent(document.getElementById("paymentType").value));
					showOverlay();
					jQuery.ajax({			
						url:'${hardwareDetailsSession}',			
						type:'POST',
						data: {
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
								hideOverlay();
							},
						failure: function(){
								jQuery('#errorMsgPopup').append('<li><strong><spring:message code="requestInfo.message.errorSaveValue"/></strong></li>');
								jQuery('#errorMsgPopup').show();
								hideOverlay();
								}
						});
					
				}
			}
			
			function deleteCatalogDetails(){
				jConfirm("<spring:message code='requestInfo.message.sessionClearConfirmation'/>", "", function(confirmResult) {
					if(confirmResult){
						hideSearch();
						//combo.disable(false);
						//jQuery("#combo_zone1").removeClass('disableInput');
					}
				});
			}
			
			/*The following function is used for removing the selected Sold To number, Billing address and payment type values from session*/
			function hideSearch() {
				document.getElementById('errorMsgPopup').style.display = "none";
				document.getElementById('errorMsgPopup').innerHTML = "";
				document.getElementById("showProductType").innerHTML = "<select id=\"productType\" onmousedown=\"jQuery(this).removeClass('errorColor');\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
				document.getElementById("showProductModel").innerHTML = "<select id=\"productModel\"><option value=\"\"><spring:message code="requestInfo.option.select"/></option></select>";
				
				
				jQuery("#searchCatalog").hide();
				jQuery("#showPaymentType").html('');
				jQuery.ajax({
					url:'${hardwareDetailsRemoveSession}',			
					type:'POST',
					success: function(results){
							if(results=="success"){									
								<c:choose>
									<c:when test="${not empty singleBillToAddress}">
										/* alert(singleBillToAddress);
										jQuery("#billToSingle").html(singleBillToAddress);
										jQuery("#billToSingle").show();
										jQuery("#paymentLoading").show();
										jQuery("#showPaymentType").hide();
										soldTo = "singleAddress";
										document.getElementById('errorMsgPopup').style.display = "none";
										hideDiv("showBundle");
										hideDiv("buttonListDiv");
										document.getElementById("applyButton").style.display="block";
										document.getElementById("closeImage").style.display="none";
										var paymentTypeURL="<portlet:resourceURL id="getPaymentType"/>&soldTo="+soldTo+"&random="+Math.random()*99999;
										jQuery("#showPaymentType").load(paymentTypeURL,function(response){
											jQuery("#paymentLoading").hide();
										    jQuery("#showPaymentType").show();
											document.getElementById("showPaymentType").innerHTML = response;
										}); */
										showOverlay();
										window.location.href = "<portlet:renderURL></portlet:renderURL>";										
									</c:when>
									<c:otherwise>
										combo.clearAll();
										combo.setComboText("");
										combo.loadXMLString('${billToAddressList}');	
										jQuery("#showPaymentType").html('<select id="paymentType"><option value=""><spring:message code="requestInfo.option.select"/></option></select>');
										hideDiv("showBundle");
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
				
			}
			
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
			isHardwarePage="true";
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
			   				  dialog.dialog('destroy');
								hideOverlay();
							  dialog=null;
							  jQuery('#accountInitialize').hide();
							  if(ajaxAccountSelection=="success")
							  {
								  ajaxSuccessFunction();
							  }
							}
						});
					
					jQuery(document).scrollTop('0');
					dialog.dialog('open');
		}
		
		ajaxSuccessFunction=function updateRequest(){
				window.location.href="<portlet:actionURL><portlet:param name='action' value='showCatalogOrderPage'/></portlet:actionURL>&friendlyURL="+populateURL("hardwareorder",[],[]);					
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
			function showAccessories(){
				var productType = document.getElementById("productType").value;
				var productModel = document.getElementById("productModel").value;
				hideDiv('accessories_container');
				jQuery('#noRecordsAcc').html('');
				hideDiv('noRecordsAcc');
				showDiv('accessoriesLoading');
				var connectUrl = "<portlet:resourceURL id='retriveAccessoriesPartListURL'></portlet:resourceURL>";				
				accessoriesGrid.clearAndLoad(connectUrl);
				hideDiv("accessoriesLoading");
				
			}
	
</script>

