<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script type="text/javascript">
//alert('started');
document.getElementById('changeQuantityParentPage').style.display = "none";
//alert('entered 1');
document.getElementById('changeQuantityParentPage').innerHTML = "";
//alert('entered 2');
</script>
	<c:choose>
		<c:when test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
			<c:set var="priceFlag" value="true"/>
		</c:when>
		<c:otherwise>
		   	<c:set var="priceFlag" value="false"/>
		</c:otherwise>
	</c:choose>
	<div class="main-wrap">
		<div class="content">
			<div id="content-wrapper">
				<div class="error" id="errorMsgPopupCartView" style="display:none"></div>
				<div id="cartViewUpdate"></div>
				<div id="cartViewUpdateDelete"></div>
				<div id="changeQuantityParentPage" class="info banner ok" style="display: none;"></div>
				<div class="portletBodyWrap">
					<div class="columnInner">
						<p class="para"><spring:message code="requestInfo.orderSupplies.cartView.label.instruction1"/></p>
						<p><spring:message code="requestInfo.orderSupplies.cartView.label.instruction2"/></p>
						<p><spring:message code="requestInfo.orderSupplies.cartView.label.instruction3"/></p>
					</div>
				</div>
				
				<div class="portletBodyWrap">
					<div class="columnInner">
					<div class="wFull displayGrid columnInnerTable rounded shadow">
       					<table id="cartViewTable">
							<thead>
								<tr class="tableHeader">
									<th class="w15"></th>
									<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.partNumber"/></th>
									<th><spring:message code="orderSupplies.placeOrderHeader.description"/></th>
									<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.partType"/></th>
									<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.yield"/></th>
									<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.model"/></th>
									<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
										<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
											<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.price"/></th>
										</c:if>
									</c:if>
									<th class="w100"><spring:message code="orderSupplies.placeOrderHeader.orderQuantity"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cartViewForm.catalogPartList}" var="catalogPartListDetail" varStatus="counter" begin="0">
									<c:choose>
										<c:when test="${counter.count % 2 == 0}">
											<tr class="altRow" id='${catalogPartListDetail.catalogId}'>
										</c:when>
										<c:otherwise>
										   	<tr id='${catalogPartListDetail.catalogId}'>
										</c:otherwise>
									</c:choose>												
												<td class="w15"><img title="Delete" height="18" width="18" src="<html:imagesPath/>delete.gif" onClick="deletePartFromCart(${counter.index},'${catalogPartListDetail.catalogId}',${priceFlag});"></td>
												<input type="hidden" id="catalogId[${counter.index}]" name="catalogId[${counter.index}]" value='${catalogPartListDetail.catalogId}'/>
												
												<td id="partNumber[${counter.index}]" class="w100">${catalogPartListDetail.partNumber}</td>
												<td id="partDesc[${counter.index}]">${catalogPartListDetail.partDesc}</td>
													
												<td id="partType[${counter.index}]" class="w100">${catalogPartListDetail.partType}</td>
												<td id="yield[${counter.index}]" class="w100">${catalogPartListDetail.yield}</td>
												<td id="model[${counter.index}]" class="w100">${catalogPartListDetail.model}</td>
												<c:if test="${sessionScope.accountCurrentDetails['splitterFlag'] == 'true'}">
													<c:if test="${sessionScope.catalogFinalFlags['finalShowPriceFlag'] == 'true'}">
														<td id="price[${counter.index}]" class="w100">${catalogPartListDetail.unitPrice} (${catalogPartListDetail.currency})</td>
													</c:if>
												</c:if>
												<td class="w100" id="assetPartListDiv${counter.index}"><input type="text" class="w90 right" 
												onkeyUp="checkParts(this.id,'${catalogPartListDetail.mpsQuantity}','${counter.index}')" onchange="changePartQuantity('${catalogPartListDetail.catalogId}',${counter.index}); removeErrorMessage();" id="assetPartList[${counter.index}].orderQuantity" 
												name="assetPartList[${counter.index}].orderQuantity" value='${catalogPartListDetail.partQuantity}' maxlength="4"/>
												<div style='display: none; color: red;' id='MsgassetPartList[${counter.index}].orderQuantity'><B><spring:message code="maxOrderQtyValidation"/>&nbsp;${catalogPartListDetail.mpsQuantity}</B></div></td>
											</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
					</div>
					
					<div class="buttonContainer">
						<button type="button" class="button_cancel" onclick="updateCompleteCartView(${priceFlag});"><spring:message code="button.updateCart"/></button>
						<button type="button" class="button" onclick="goToContinueShoppingPage();"><spring:message code="button.continueShopping"/></button>
						<button onClick="retriveDetailCatalogPart();" class="button_cancel"><spring:message code="button.checkout"/></button>
					</div>
					</div>
					
				</div>
			</div>
		</div>
<script type="text/javascript">
var updateCart = true;
var orderQtyFlag=true;
var tabRowNum = document.getElementById("cartViewTable").rows.length;
var rowsNumFlagCartView = [];
for(var i=0;i<tabRowNum-1;i++){
	rowsNumFlagCartView[i] = true;
}
function deletePartFromCart(partNumberIndex, catalogId, priceFlag) {
	//alert('entered deletePartFromCart ');
	var tableCart = document.getElementById('cartViewTable');
	//alert('partNumberIndex is '+partNumberIndex);
	var tableCartRowNum = tableCart.rows.length;
	//alert('tableCartRowNum is '+tableCartRowNum);
	var selectedItem = "&random="+Math.random()*99999+ "&catalogId=" + catalogId + "&jobType=delete";
	var updateCartViewURL = '<portlet:resourceURL id="updateCartView"/>'+selectedItem;
	//alert(updateCartViewURL);
	
	jQuery("#cartViewUpdateDelete").load(updateCartViewURL,function(response){
	//alert('updateCartViewURL resourcemapping called');
		//alert('after call');
		<%--Changes for MPS 2.1 --%>
		parent.isCartUpdated=true;
		
	var newCatalogId;
	for (var i = partNumberIndex; i < tableCartRowNum - 2; i ++) {
		//alert('old catalogid '+document.getElementById('catalogId[' + i +']').value);
		document.getElementById('assetPartList[' + i +'].orderQuantity').value = document.getElementById('assetPartList[' + (i + 1) +'].orderQuantity').value;
		//document.getElementById('img[' + i +']').innerHTML = document.getElementById('img[' + (i + 1) +']').innerHTML;
		document.getElementById('partNumber[' + i +']').innerHTML = document.getElementById('partNumber[' + (i + 1) +']').innerHTML;
		document.getElementById('partDesc[' + i +']').innerHTML = document.getElementById('partDesc[' + (i + 1) +']').innerHTML;
		document.getElementById('partType[' + i +']').innerHTML = document.getElementById('partType[' + (i + 1) +']').innerHTML;
		document.getElementById('yield[' + i +']').innerHTML = document.getElementById('yield[' + (i + 1) +']').innerHTML;
		document.getElementById('model[' + i +']').innerHTML = document.getElementById('model[' + (i + 1) +']').innerHTML;
		document.getElementById('price[' + i +']').innerHTML = document.getElementById('price[' + (i + 1) +']').innerHTML;
		//alert('lets call catalog id');
		document.getElementById('catalogId[' + i +']').value = document.getElementById('catalogId[' + (i + 1) +']').value;
		//prepare innerhtml for quantity field
		//alert('catalog id assignement done');
		//prepare innerhtml for delete field
		newCatalogId = document.getElementById('catalogId[' + i +']').value;
		//alert('catalog id printing '+newCatalogId);
		partQuantity = document.getElementById('assetPartList[' + i +'].orderQuantity').value;
		//alert('newCatalogId is '+newCatalogId);
		newCatalogId = "'"+newCatalogId+"'";
		//alert('formatted '+newCatalogId);
		if(priceFlag==true){
			tableCart.rows[i+1].cells[7].innerHTML ='<input type="text" class="w90 right" onchange="changePartQuantity('+newCatalogId+','+(i)+')" id="assetPartList['+ i +'].orderQuantity" name="assetPartList['+i+'].orderQuantity" value='+partQuantity+' maxlength="4"/>';
		}else{
			tableCart.rows[i+1].cells[6].innerHTML ='<input type="text" class="w90 right" onchange="changePartQuantity('+newCatalogId+','+(i)+')" id="assetPartList['+ i +'].orderQuantity" name="assetPartList['+i+'].orderQuantity" value='+partQuantity+' maxlength="4"/>';
		}
		
		//alert('input type is done');
		//please change the above to 6 once the development done
		tableCart.rows[i+1].cells[0].innerHTML = '<img title="Delete" src="<html:imagesPath/>delete.gif" height="18" width="18" onClick="deletePartFromCart('+(i)+','+newCatalogId+')"/>';
		//alert('image is done');
	}
	//alert('changing done about to delete '+tableCartRowNum);

	tableCart.deleteRow(tableCartRowNum-1);
	if(window.parent.document.getElementById('partQuantity' + catalogId) != null) {
		//alert('parent not null while delete');
		window.parent.document.getElementById('partQuantity' + catalogId).value = '';
		window.parent.document.getElementById('partQuantity' + catalogId).nextSibling.firstChild.value = '<spring:message code="requestInfo.button.addToCart"/>';
		
	}
	//alert(tableCartRowNum);
	
	//alert('table column deleted');
	//var parameterFieldsId = new Array('partNumber','description','partType','yield','orderQuantity');
	<%--Changes for MPS 2.1 --%>
	
	});
	window.parent.document.getElementById('cartQuantity').value = window.parent.document.getElementById('cartQuantity').value-1;
	window.parent.document.getElementById('cartQuantity').innerHTML = window.parent.document.getElementById('cartQuantity').value;
	
	
	
};

function changePartQuantity(catalogId,index){
	//alert('entered changePartQuantity');
	document.getElementById('changeQuantityParentPage').style.display = "none";
	orderQuantity = document.getElementById('assetPartList['+index+'].orderQuantity').value;
	//alert('orderQuantity is '+orderQuantity);
	var validationFlagPopup = true;
	document.getElementById('errorMsgPopupCartView').style.display = "none";
	document.getElementById('errorMsgPopupCartView').innerHTML = "";
	partRowId = "assetPartListDiv"+index;
	if(orderQuantity==null ||orderQuantity=='' ) {
		//alert('orderquantity is null');
		validationFlagPopup = true;
	}else{
			if(!validateFormat(orderQuantity,'quantity','errorMsgPopupCartView')){
				validationFlagPopup = false;
				jQuery('#'+partRowId).addClass('errorColor');
			}
		}
	if (!validationFlagPopup)
	{	
		document.getElementById('errorMsgPopupCartView').style.display = "block";
		
		jQuery(document).scrollTop(0);
		return false;
		//alert("block error");
	}else{
		jQuery('#'+partRowId).removeClass('errorColor');
	}
	//alert('Need to update based on catalogId '+catalogId+' and quantity '+orderQuantity);
	var selectedItem = "&random="+Math.random()*99999+ "&orderQuantity=" + orderQuantity + "&catalogId=" + catalogId + "&jobType=update";
	var updateCartViewURL = '<portlet:resourceURL id="updateCartView"/>'+selectedItem;
	//alert(updateCartViewURL);
	jQuery("#cartViewUpdate").load(updateCartViewURL,function(response){
	//alert('updateCartViewURL resourcemapping called');

	});
};

function checkParts(inputboxId,maxOrderQty,id){
	var ordQty = document.getElementById(inputboxId).value;	
	var newID = inputboxId;
	var maxQty = parseInt(maxOrderQty);
	var orderQuantity = parseInt(ordQty);
	if(maxQty != "" && maxQty != null){
		 if(orderQuantity>maxQty){
			 rowsNumFlagCartView[id] = false;
			 orderQtyFlag = false;
			 jQuery('#'+newID).addClass('errorColor');
			 document.getElementById("MsgassetPartList["+id+"].orderQuantity").style.display = 'block';
			
		 }
		 else{
			 rowsNumFlagCartView[id] =true;
			 jQuery('#'+newID).removeClass('errorColor');
			 orderQtyFlag = true;
			 document.getElementById("MsgassetPartList["+id+"].orderQuantity").style.display = 'none';
		 }
	}
	 
 }

function removeErrorMessage(id){
	document.getElementById('errorMsgPopupCartView').style.display = "none";
	document.getElementById('errorMsgPopupCartView').innerHTML = "";
		jQuery('#partQuantity'+id).removeClass('errorColor');
		 

}

function updateCompleteCartView(priceFlag){
	//alert('Inside updateCompleteCartView');
	var validationFlagPopup = true;
	var canUpdateCart = false;
	var tableCart = document.getElementById('cartViewTable');
	//alert('partNumberIndex is '+partNumberIndex);
	var tableCartRowNum = tableCart.rows.length;
	//alert('tableCartRowNum is '+tableCartRowNum);
	//need to check at least one quantity should not be zero.Then only you can update the cart
	
	//alert('tableCartRowNum is '+tableCartRowNum);
	
	document.getElementById('errorMsgPopupCartView').style.display = "none";
	document.getElementById('errorMsgPopupCartView').innerHTML = "";
	//check started
	if(tableCartRowNum != 1){
		for (var i = 1; i < tableCartRowNum; i ++) {
			if(priceFlag==true){
				var quantity = tableCart.rows[i].cells[7].firstChild.value;
			}else{
				var quantity = tableCart.rows[i].cells[6].firstChild.value;
			}
			//alert('quantity is '+quantity);
			if(!(quantity==''||quantity==0)){
				canUpdateCart = true;
				//alert('at least one quantity is non zero so breaking the loop');
				break;
			}
		}
		
		if(!canUpdateCart){
			//alert('all quantity is zero so showing the error message');
			jQuery('#errorMsgPopupCartView').append('<li><strong><spring:message code="validation.catalogDetail.emptyTable"/></strong></li>');
			validationFlagPopup = false;
		}
	}

	var orderQuantity;	
	for (var i = 0; i < tableCartRowNum-1; i++) {
		orderQuantity = document.getElementById('assetPartList[' + i +'].orderQuantity').value;
		if(orderQuantity==null || orderQuantity=='' ||orderQuantity=='0') {			
			jQuery('#errorMsgPopupCartView').append('<li><strong><spring:message code="validation.quantity.empty.cart"/></strong></li>');
			validationFlagPopup = false;
		}
	}
	for(var i=0;i<tabRowNum-1;i++){
		if(rowsNumFlagCartView[i]==false){
			orderQtyFlag = false;
		}
		
	}
	
	//check ended
	//alert(tableCartRowNum);
	
	if(tableCartRowNum == 1 ){
		jQuery('#errorMsgPopupCartView').append('<li><strong><spring:message code="validation.cartView.nothingToUpdate"/></strong></li>');
		validationFlagPopup = false;
	}
	if(!orderQtyFlag) {
		jQuery('#errorMsgPopupCartView').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
		validationFlagPopup = false;
	}
	if (!validationFlagPopup)
	{		<%-- Changes for single Item deletion for Cart view MPS 2.1  --%>
		jQuery('#changeQuantityParentPage').hide();
		<%--Ends Changes for single Item deletion for Cart view MPS 2.1  --%>
		updateCart = false;
		document.getElementById('errorMsgPopupCartView').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
		//alert("block error");
	}
	//alert('inside updateCompleteCartView');
	updateCart = true;
	var updateCompleteCartViewURL = '<portlet:resourceURL id="updateCompleteCartView"/>&random='+Math.random()*99999;
	<%-- Changes for single Item deletion for Cart view MPS 2.1  --%>
	showOverlay();
	
	jQuery("#changeQuantityParentPage").load(updateCompleteCartViewURL,function(response){
		//alert('updateCompleteCartView resourcemapping called');
		//window.parent.window.updateCartViewCount();//Lets see if we can able to update the cart 
		<%-- Changes for single Item deletion for Cart view MPS 2.1  --%>
		hideOverlay();
		parent.isCartUpdated=true;
		<%--Ends Changes for single Item deletion for Cart view MPS 2.1  --%>
		document.getElementById('changeQuantityParentPage').style.display = "block";
	});
};
function retriveDetailCatalogPart(){
	var tableCart = document.getElementById('cartViewTable');
	//alert('partNumberIndex is '+partNumberIndex);
	var tableCartRowNum = tableCart.rows.length;
	//alert('tableCartRowNum is '+tableCartRowNum);
	var validationFlagPopup = true;
	document.getElementById('errorMsgPopupCartView').style.display = "none";
	document.getElementById('errorMsgPopupCartView').innerHTML = "";
	if(tableCartRowNum == 1){
		jQuery('#errorMsgPopupCartView').append('<li><strong><spring:message code="validation.cartView.nothingToUpdate"/></strong></li>');
		validationFlagPopup = false;
	}
	if(!orderQtyFlag) {
		jQuery('#errorMsgPopupCartView').append('<li><strong><spring:message code="requestInfo.label.validation.number"/></strong></li>');
		validationFlagPopup = false;
	}
	if (!validationFlagPopup)
	{	
		document.getElementById('errorMsgPopupCartView').style.display = "block";
		jQuery(document).scrollTop(0);
		return false;
		//alert("block error");
	}
	
	window.parent.window.goToCatalogDetailPage();
	<%-- Changes for single Item deletion for Cart view MPS 2.1  --%>
	dialog.dialog('close');
	<%--Ends Changes for single Item deletion for Cart view MPS 2.1  --%>
}
function goToContinueShoppingPage(){
	if(updateCart){
		document.getElementById('errorMsgPopup').style.display = "none";
		for(var i=0;i<catalogGridRowNum;i++){
    		rowsNumFlag[i] = true;
    	}
	}
	<%-- Changes for single Item deletion for Cart view MPS 2.1  --%>
	<%--showOverlay();
	
	window.parent.window.goToContinueShoppingPageParent();
	Liferay.Popup.close(this);--%>
	//alert('in goToContinueShoppingPage');
	// Changes for continue shopping in cart
	if (parent.isCartUpdated == true) {
		//showOverlay();
		var tableCart = document.getElementById('cartViewTable');
		var tableCartRowNum = tableCart.rows.length-1;
		//alert('tableCartRowNum is '+tableCartRowNum);
		var catalogId;
		var orderQuantity;	
		for (var i = 0; i < tableCartRowNum; i++) {
			catalogId = document.getElementById('catalogId[' + i +']').value;
			//alert('catalogId='+catalogId);
			orderQuantity = document.getElementById('assetPartList[' + i +'].orderQuantity').value;
			//alert('orderQuantity='+orderQuantity);
			if(window.parent.document.getElementById('partQuantity' + catalogId) != null) {
				//alert('parent not null in goToContinueShoppingPage');
				window.parent.document.getElementById('partQuantity' + catalogId).value = orderQuantity;
			}
		}
		parent.isCartUpdated = false;
		//hideOverlay();
		window.parent.document.getElementById('errorMsgPopup').style.display = "none";
		window.parent.document.getElementById('errorMsgPopup').innerHTML = "";
		
	}

	dialog.dialog('close');


	
	<%--Ends Changes for single Item deletion for Cart view MPS 2.1  --%>
}

</Script>	