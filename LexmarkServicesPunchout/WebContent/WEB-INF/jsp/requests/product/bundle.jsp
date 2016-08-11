<%@ include file="/WEB-INF/jsp/include2.jsp"%>		
<script src="<html:rootPath/>/js/accordian.js?v=<html:fileCacheVersion/>"></script>
<portlet:resourceURL var="retrieveBundleGrid" id="retrieveBundleGrid"></portlet:resourceURL>
<portlet:resourceURL var="getOptionsAndWarrantiesVar" id="getOptionsAndWarranties"></portlet:resourceURL>	
<style>
.gridSubrowHeader {
	background: url(/lexmark-punchout-theme/images/custom/nav-container.png);
    float: left;
    border-radius: 5px 5px 0 0;
    font-size: 14px;
    font-weight: bold;
    height: 27px;
    line-height: 26px;
    overflow: auto;
    padding: 5px;
    width: calc(100% - 10px);
}
div.gridbox_light table.obj td img {
    cursor: pointer;
    display: inline-block;
    float: right;
    padding: 7px 3px;
} 
tr.altRow td { background-color:#f0f0f0 !important; }
</style>    

<div class="mid-cntnr">
	<div class="columnInner separatorV">
		<div id="div_bundleGrid">
			<div id="tab_bundleGrid">
				<div id="printerBundle_container"></div>
				<div id="loadingNotification_printer" class='gridLoading'>
					<img src="/lexmark-punchout-theme/images/custom/loading_big.gif"/><br/>
  				</div>
			</div>
		</div>
	</div>
</div>
<div id="requestDetailsPopup"></div>

<script>

	
	
	
		
	if("${forGlobalSearch}"=="true"){
		bundleGrid.parse("${bundleListXml}");
	}
	
	
	function openSubRow(bundleId,imgObj){
		if(imgObj.src.indexOf("minus.png")==-1){
			imgObj.src="<html:imagesPath/>minus.png";
			$('#options_warr_content'+bundleId).siblings('.optn-warrn-content').show();
			getOptionsAndWarranties(bundleId);
		}else{			
			imgObj.src="<html:imagesPath/>plus.png";
			$('#options_warr_content'+bundleId).siblings('.optn-warrn-content').hide();
		}
		
	}
	
	
	function getOptionsAndWarranties(bundleId){
		var bundle=bundlesObj.bundlesData[bundleId];
		var url="${getOptionsAndWarrantiesVar}&cartType=printers&bundleId="+bundleId+"&cNum="+bundle.contractNo+"&model="+bundle.printerPartNo;
				$.getJSON(url,function(accessories){
				bundlesObj.bundlesData[bundleId].accessories=accessories;
				$('#options_warr_content'+bundleId).siblings('.optn-warrn-content').children('.gridLoading').hide();
					var keys=Object.keys(accessories);
					for(var i=0;i<keys.length;i++){
						$('#options_warr_content'+bundleId).siblings('.optn-warrn-content').children('.options-table').children('tbody').append(bundlesObj.accessoriesTemplateObj(accessories[i]));
					}			   	
			});	
		
		
	}
	
	function getDataForNCAL_SCAL(params){
		var type="SCAL";
		if(params.certType=="ncal_printer"){
			type="NCAL";
		}
			$("#loadingNotification_printer").show();
			$.getJSON("${retrieveBundleGrid}"+convertToParams(params),function(jsonBundles){
				$('#loadingNotification_printer').hide();
				bundlesObj.bundlesData=jsonBundles;
				bundlesObj.parseData();
								
			});
		
	}
	
	function getDataForPrinter_Types(params){
		
		$('#loadingNotification_printer').show();
		$('#printerBundle_container').html('');
			$.getJSON("${retrieveBundleGrid}"+convertToParams(params),function(jsonBundles){
				$('#loadingNotification_printer').hide();
				bundlesObj.bundlesData=jsonBundles;
				bundlesObj.parseData_PrinterType(bundlesObj.getPrinterTypeMessage(params.partType).type);
								
			});
		
	}
	YUI().use('node-base', function (Y) {
		Y.on('domready', function () {
			bundlesObj.initTemplate();
		});
	});
	</script>  
      
    <script id="learn-more-part-template" type="text/x-handlebars-template">
			<p class="not_bundle">{{marketing}}</p>
			<p class="blueColor">{{name}}</p>
			<h2  class="accordion-header">Product Description</h2>
				<div class="accordion-content">{{description}}</div>
			<h2  class="accordion-header not_bundle">What's Included</h2>
				<div class="accordion-content not_bundle">
					<ul class="included-list">
						{{#inTheBox}} 
							<li class="arrowLi">{{this}}</li>
						{{/inTheBox}}	
					</ul>
				</div>
			<h2  class="accordion-header">Specs</h2>
				<div class="accordion-content">
					<ul class="specs-list"> 
						               
						<li class="arrowLi size_in">Size (in. - H x W x D) :{{size_in}}</li>                       
						<li class="arrowLi packagedSize_in">Size (in. - H x W x D) :{{packagedSize_in}}</li>
						<li class= "arrowLi not_bundle">Print Speed(letter Black)Up to : {{printSpeedLetterBlack}}</li> 
						<li class= "arrowLi not_bundle">Print Speed(letter Black) Duplex Up to: {{printSpeedLetterBlackDuplex}}</li>
						<li class= "arrowLi not_bundle">Time to first Page Black(As Fast As) : {{timeToFirstPageBlack}}</li>                       
						<li class="arrowLi not_bundle">Print Resolution Black :{{printResolutionBlack}}</li>
						<li class="arrowLi not_bundle">Print Resolution Color :{{printResolutionColor}}</li>
						<li class="arrowLi">Country of Origin :{{countryOfOrigin}}</li>
						<li class="arrowLi not_bundle">Weight (lb.) : {{weight_lb}}</li>
						<li class="arrowLi ">UNSPSC Code :{{UNSPSCCode}}</li>
                        <li class="arrowLi not_bundle">Product Certifications :{{productCertifications}}</li></ul>
				</div>							
	 </script> 
	 
 <script id="printer-type-template" type="text/x-handlebars-template">
	<li> <img src="{{img}}" alt="{{name}}">
    <div class="description">
      <div class="name">{{name}}</div>
      <div class="type">{{genericType bundleType}}
      		{{#if parts}}
				{{#parts}}
                <span class="part">{{no}}</span>
         		{{/parts}}                
           {{else}}    
            	<span class="part"></span>        	
           {{/if}}
      	
      </div>
      <div class="itemDesc">{{marketing}}</div>
    </div>
	<div class="orderInfo">
	<input type="hidden" id="{{bundleId}}_partType" value="{{printerPartNo}}" />
        <div class="price"><sup>{{curr}}</sup>{{price}}</div>
        <label class="quantity">Quantity:</label>
		 <input class="quantityInputBox" type="text" name="Quantity" id="quantity{{bundleId}}" value="0">
         <input name="Add to Cart" title="" id="cartButton{{bundleId}}" type="button" class="button" value="Add to Cart" border="0" onclick="moveToCart('{{bundleId}}',this.id)">
     </div>
  </li>	
 </script> 
     
     <script id="bundle-list-template" type="text/x-handlebars-template">
       <div class="printer-cntnr">
           <div class="prdctpge-printerimg"><img src="{{img}}" width="89" height="72" alt="Mono Laser Printers"></div>
           <div class="printer-details-cntnr">
			<div class="product_title" id="printerId">{{bundleBrand}}  {{bundleModel}}</div>
         	<div class="printerDescription">{{configId}}</div>
         
         <table width="300" border="0" cellspacing="0" cellpadding="0" class="discrptn-table-parts">
               <tr>
             <td  class="table-title"><b><spring:message code="orderSupplies.placeOrderHeader.partNumber"/></b></td>
             <td  class="table-title"><b><spring:message code="orderSupplies.placeOrderHeader.description"/></b></td>
			 <td  class="table-title"><b><spring:message code="serviceRequest.listHeader.model"/></b></td>
             <td  class="table-title"><b><spring:message code="requestInfo.heading.Qty"/></b></td>
           </tr>
			        	
           {{#if parts}}
				{{#parts}}
                <tr class="">
                  <td class="table-txt borderRight">{{no}}</td>
                  <td class="table-txt borderRight">{{pdesc}}</td>
				  <td class="table-txt borderRight">{{model}}</td>
                  <td class="table-txt">{{qty}}</td>
                </tr>
         		{{/parts}}
                
           {{else}}    
            	<td bgcolor="#e6e6f0" color="#c82424">No Records Available</td>
                <td bgcolor="#e6e6f0"></td>
                <td bgcolor="#e6e6f0"></td>
        	
           {{/if}}
          	<input type="hidden" id="{{bundleId}}_partType" value="{{printerPartNo}}" />
             
             </table>
		 	<div><br/><a  class="button simplelink " href = "javascript:learnMorePopup('{{bundleId}}','bundle');">
         		&nbsp;&nbsp;<B><spring:message code="shoppingCart.printers.link"/></B></a><br/><br/>
         	</div>
		 <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>	
       	<div class="quntity-cart-cntnr">
            
         <div class="price-btn-cntnr">
         	<div>
         		{{#if price}}
         			{{price}}({{curr}})
         		{{else}}
         			<div class="price-btn-cntnr"><spring:message code="requestInfo.error.priceUnavailable"/></div>	
         		{{/if}}
         	</div>
          </div>
		<div class="quntyty-cntnr"> <span class="blueColor"><spring:message code="shoppingCart.printers.quantity"/></span>
         {{#if bQty}}
         	 <input type="text" name="Quantity" id="quantity{{bundleId}}" value="{{bQty}}"  class="quantityInputBox">
           {{else}} 
           	 <input type="text" name="Quantity" id="quantity{{bundleId}}" {{{billingModelCheck billingModel price}}} class="quantityInputBox">
            		 
          {{/if}}
          
         </div>
             
		  <div>
			<br/>
         	{{#if bQty}}
           	<input name="Update Cart" title="" id="cartButton{{bundleId}}" type="button" class="button" value="Update Cart" border="0" onclick="moveToCart('{{bundleId}}',this.id)">
           	{{else}}
            <input name="Add to Cart" title="" id="cartButton{{bundleId}}" type="button" class="button" value="Add to Cart" border="0" onclick="moveToCart('{{bundleId}}',this.id)">
           	{{/if}}
			<br/>
			<br/>
           </div>
           
       </div>
	   </c:if>
       </div>
       
             
       <div id="accObj"></div>
       	<div class="option_Warranties_Content">
		<div class="gridSubrowHeader" id="options_warr_content{{bundleId}}">
       		<div class="floatL"><spring:message code="product.bundleGridXml.optionsAndWarranties"/></div>
       		<div class="floatR">
       			<img  class="noDisplay" id="optn_warrnt_loading_{{bundleId}}" src="<html:imagesPath/>loading-icon.gif"/>
       			<img class="pointer" id="optn_warrnt_img_{{bundleId}}" src="<html:imagesPath/>plus.png" onClick="openSubRow('{{bundleId}}',this)"/>
			</div>
	   	</div>
		<div class="clearBoth"></div>
		<div class="optn-warrn-content">
			 
              <table class="options-table" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
               		<tr>
                  		<td class="supplies-title-small borderLeftGreen"><spring:message code="product.suppliesTitle.productList"/></td>
						<td class="supplies-title-small"><spring:message code="serviceRequest.listHeader.model"/></td>
                  		<td class="supplies-title-small"><spring:message code="product.suppliesTitle.partNumber"/></td>
                  		<c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
                  		<td class="supplies-title-small"><spring:message code="requestInfo.heading.unitPrice"/></td>                 
                  		<td class="supplies-title-small" align="right"><spring:message code="requestInfo.heading.Qty"/></td>
                   		</c:if>
                  		<td class="supplies-title-small">&nbsp;</td>
                	</tr>
				</tbody>
			 </table>
			<div class="gridLoading" id="loadingNotification_printer">
				<img src="/lexmark-punchout-theme/images/custom/loading_big.gif"><br>
	    	 </div>
		

		</div>
		</div>
      </div>
     </script>
     
      <script id="accessories-list-template" type="text/x-handlebars-template">
    
          <tr><td class="option_group_header">{{type}}</td></tr>
            {{#list}}
             <tr>
                  <td height="35" align="left" valign="middle"><table width="260" border="0" cellspacing="0" cellpadding="0" class="marginL10px">
                      <tr>
                      <td width="45" align="left" valign="top">
                      <img src="{{img}}" width="60" height="26" alt="Product List"/>
                      </td>
                      <td align="left" valign="top" class="supplies-product-list">
                      {{desc}}
						<br/><br/>
						<a  class="button simplelink " href = "javascript:learnMorePopup('{{pNo}}','accessories');">
         					&nbsp;&nbsp;<B><spring:message code="shoppingCart.printers.link"/></B></a>
						<br/><br/><br/>
                      </td>
                    </tr>
                    </table></td>
				  <td valign="top" style="padding-left:5px;">{{model}}</td>
                  <td valign="top" style="padding-left:5px;">{{pNo}}</td>
                   <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
                  {{#if price}}
                  	 <td valign="top" class="paddingL5px">{{price}}</td>
                 	 <td valign="top" align="right" ><input type="text" name="Quantity" id="quantity_optn_warran{{pNo}}" class="quantityInputBox">{{qty}}</input></td>
                 {{else}}
                 	 <td valign="top" class="paddingL5px price-btn-cntnr2">Price Not Available</td>
                 	 <td valign="top" align="right" ><input type="text" name="Quantity" id="quantity_optn_warran{{pNo}}" disabled="disabled" class="quantityInputBox"></td>
                 {{/if}}
                 </c:if>
              </tr>
            {{/list}}
			<c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
				<tr>
					<td height="30" align="right" colspan="5" valign="middle">
						<button  class="button" id="addToCartOptnWarran{{bId}}" onClick="addOptnsWarranties('{{groupId}}','{{bId}}')">Add To Cart</button>
					</td>
             	</tr>
			</c:if>
      </script>
  <jsp:include page="learnMorePopUp.jsp"></jsp:include>  