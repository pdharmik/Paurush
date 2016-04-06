<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<portlet:resourceURL var="loadProductModelvar" id="retrieveProductModel"></portlet:resourceURL>
<script>
global_click_msgs.clickedFrom="suppliesList";
var suppliesLinkObject={
		reset:function(){this.prodTyp="";this.prodMod="";this.partNum=""},
		id:"suppliesProduct",prodTyp:"",prodMod:"",partNum:"",
		getURL:function(){
			var parms=["prodTyp","prodMod","partNum"];
			var str="";
			for(i=0;i<parms.length;i++){
				if(this[parms[i]]!=""){
					str+="&"+parms[i]+"="+this[parms[i]];	
				}
				
			}
			return str;
		}};

function loadPrinterSupplies(isDropDown){
	suppliesLinkObject.reset();
	if(isDropDown){
		
		suppliesLinkObject.prodTyp=jQuery('#productType').val();
		suppliesLinkObject.prodMod=jQuery('#printerModel').val();
		
		calledFromLEftNav(suppliesLinkObject.id);
	}else{
		
		suppliesLinkObject.partNum=jQuery('#productNumberSupplies').val();
		
		calledFromLEftNav(suppliesLinkObject.id);
	}
	
}

function loadPrinterModel(value){
	
	jQuery('#loadingPrintType').show();
	jQuery('#printerModel').hide();
	jQuery('#printerModel').html('');
	jQuery.getJSON("${loadProductModelvar}&prodTyp="+value,function(result){
		jQuery('#loadingPrintType').hide();
		var html="<option value=\"\"><spring:message code='suppliesList.secondStep.value'/></option>" ;
	
		
		for(i=0;i<result.length;i++){
			html+="<option value=\""
			html+=(result[i].value);
			html+="\">";
			html+=result[i].value;
			html+="</option>";
		}
		
		jQuery('#printerModel').html(html);
		jQuery('#loadingPrintType').hide();
		jQuery('#printerModel').show();
		});
}

</script>

    <div id="portlet-wrap" style="width:100%!important">
     <c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
      <div id="breadcrum-cart-cntnr">
        <jsp:include page="/WEB-INF/jsp/shoppingCart/totalItems.jsp"/>
      </div>
      </c:if>
      <div class="pageTitle"><spring:message code="changemanagement.popup.label.orderSupplies"/></div>
      <div class="supplies-mid-cntnr">
        <div class="supplies-title"><spring:message code="suppliesList.title.value"/></div>
        <div id="printer-type">
          <div class="title-blue"><spring:message code="suppliesList.title.findByPrinterType"/></div>
          <ul class="roDisplay">
          <li>
          <select name="productType" id="productType" onChange="loadPrinterModel(this.value)">
            <option value=""><spring:message code="suppliesList.firstStep.value"/></option>
            <c:forEach items="${productType}" var="product">
            	<option value="${product.key}">${product.key}</option>
            </c:forEach>
          </select>
          </li>
          <li>
          <select name="Find Printer Model" style="display: none;" id="printerModel" onchange="loadPrinterSupplies(true)">
            <option value=""><spring:message code="suppliesList.secondStep.value"/></option>           
          </select>
          <img id="loadingPrintType"  style="display: none;" src="<html:imagesPath/>loading-icon.gif"/>
          </li>
          </ul>
        </div>
        <div id="Product-no">
          <div class="title-blue"><spring:message code="suppliesList.title.productNo"/></div>
          <spring:message code="supplies.suppliesList.value"/><br>
          <input type="text" id="productNumberSupplies" placeholder="Enter Product Number" class="product-no-form">
          <div class="supplies-btn-cntnr">
            <input name="Add to Cart"  type="button" class="button" value="FIND" border="0" onClick="loadPrinterSupplies(false)">
          </div>
        </div>
      </div>
    </div>
  
 

