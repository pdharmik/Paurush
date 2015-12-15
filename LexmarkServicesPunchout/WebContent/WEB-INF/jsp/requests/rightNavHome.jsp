<style type="text/css"><%@ include file="/WEB-INF/css/style.css" %></style>
<style type="text/css"><%@ include file="/WEB-INF/css/text-scroller.css" %></style>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>


<portlet:resourceURL var="loadHistoryList" id="loadHistoryList"></portlet:resourceURL>
<portlet:resourceURL var="loadPrinterList" id="loadPrinterList"></portlet:resourceURL>
<portlet:resourceURL var="loadSuppliesList" id="loadSuppliesList"></portlet:resourceURL>

<portlet:actionURL var="loadPrinterProducts" windowState="<%=LiferayWindowState.EXCLUSIVE.toString()%>">
	<portlet:param name="action" value="loadPrinterProducts"/>
</portlet:actionURL>

<portlet:resourceURL var="loadShoppingCart" id="loadShoppingCart"></portlet:resourceURL>
<portlet:resourceURL var="loadSuppliesProduct" id="loadSuppliesProduct"></portlet:resourceURL>
<portlet:resourceURL var="clearCartSession" id="clearCartSession"></portlet:resourceURL>

<portlet:resourceURL var="getCXMLencodedTextvar" id="getCXMLencodedText"></portlet:resourceURL>
<portlet:resourceURL var="createServiceRequestvar" id="createServiceRequest"></portlet:resourceURL>

<div id="mainRightNavContainer">
		<div id="historyList"></div>
		<div id="suppliesList"></div>
		<div id="printerProducts"></div>
		<div id="shoppingCart"></div>
		<div id="suppliesProduct"></div>
		<div id="eQuote"></div>
		<div id="printersList"><!--<jsp:include page="/WEB-INF/jsp/requests/product/printersList.jsp"/>--></div>
</div>
<div id="shoppingCartPopup" style="display:none" ></div>
<div style="display: none;" id="cxmlDiv"></div>
<script>

var global_click_msgs={clickedFrom:""};//This will be used in certifiedProducts.jsp and printerlist.jsp this will help to differentiate the calls of the grid
//Whether it came from certifiedproducts.jsp or printerlist.jsp 

var divResourceMapping={
		divId:[ "historyList",       "printersList",      "suppliesList",       "printerProducts",       "suppliesProduct","shoppingCart"],
		linkId:["historyLink",       "reqPrinterLink",    "reqSupplyLink",      "printerProduct",        "suppliesProduct","shoppingCart"],
		activeLinkId:["historyLink",       "reqPrinterLink",    "reqSupplyLink",      "reqPrinterLink",        "reqSupplyLink","shoppingCart"],
		url:[   "${loadHistoryList}","${loadPrinterList}","${loadSuppliesList}","${loadPrinterProducts}","${loadSuppliesProduct}","${loadShoppingCart}"]
		
};
var cartCheckObj={cartType:"printers",qty:0};
var flag=true;
var acntType;
var continueShoppingFlag=true;


jQuery(document).bind("loadOtherPortlet",eventHandler);
function eventHandler(e,params){
	var qty = jQuery('#totItems').html();

	if((params=="reqSupplyLink" && cartCheckObj.cartType=="printers" && cartCheckObj.qty>0)||(params=="reqPrinterLink" && cartCheckObj.cartType=="supplies" && cartCheckObj.qty>0)){
		flag=false;
		calledFromLEftNav(params);
	}else{
		
		calledFromLEftNav(params);
	}
	if(flag){
	jQuery("#historyLink,#reqPrinterLink,#reqSupplyLink").removeClass("active");
	jQuery("#"+params).addClass("active");
	}else if(params=="historyLink"){
		jQuery("#historyLink,#reqPrinterLink,#reqSupplyLink").removeClass("active");
		jQuery("#"+params).addClass("active");
		flag=true;
	}
}



var index;

function calledFromLEftNav(linkIdFromLeftNav){
	
	if(typeof linkIdFromLeftNav == 'string'){		
		index=divResourceMapping.linkId.indexOf(linkIdFromLeftNav);	
	}else{		
		index=divResourceMapping.linkId.indexOf(linkIdFromLeftNav.id);
	}	
	var linkClicked=divResourceMapping.activeLinkId[index];
	var activeLink;
	jQuery('.leftNavLinks li').each(function(){
		if(jQuery(this).find("a").hasClass("active"))
		{
			activeLink=jQuery(this).find("a").attr("id");
        }		  
	});
	
	 var qty = jQuery('#totItems').html();
	
	 if((activeLink=="reqPrinterLink" && (linkClicked=="reqSupplyLink" || linkClicked=="historyLink") && qty!=0 && !continueShoppingFlag) || (activeLink=="reqSupplyLink" && (linkClicked=="reqPrinterLink" || linkClicked=="historyLink") && qty!=0 && !continueShoppingFlag))
	 {		
		jConfirm("Selected Quantities will be lost. Do you want to continue?", "", function(confirmResult) {
		
		if(confirmResult){
			jQuery.ajax({
				  type: "POST",
				  url: "${clearCartSession}",
				  success:function(){					
					continueCallFromLeftNav(linkIdFromLeftNav);
				  }
			});			
		}else{
			 jQuery('#'+activeLink).addClass("active");
			 jQuery('#historyLink').removeClass("active");
			 
			 return false;
		}
		});		 
	}	
	else{
		continueCallFromLeftNav(linkIdFromLeftNav);
	}	
}

function continueCallFromLeftNav(linkIdFromLeftNav){
	if(typeof linkIdFromLeftNav == 'string'){
		if(linkIdFromLeftNav=="reqSupplyLink"){
			cartCheckObj.cartType="supplies";
		}else if(linkIdFromLeftNav=="reqPrinterLink" || linkIdFromLeftNav == "printerProduct"){
			cartCheckObj.cartType="printers";
		}
		
	}else{
		if(linkIdFromLeftNav.id=="reqSupplyLink"){
			cartCheckObj.cartType="supplies";
		}else if (linkIdFromLeftNav.id=="reqPrinterLink"){
			cartCheckObj.cartType="printers";
		}
		
	}	
	showOverlay();

	if(typeof linkIdFromLeftNav != "string" && 
			linkIdFromLeftNav.id === "shoppingCart" &&
			global_click_msgs.clickedFrom === "orderCart")
	{
		var shoppingCartPopupUrl = divResourceMapping.url[index];
		jQuery('#shoppingCartPopup').load(shoppingCartPopupUrl, {cartType: cartCheckObj.cartType}, function(){

			dialog=jQuery('#shoppingCartDetails').dialog({
				autoOpen: false,
				title: 'Your Shopping Cart',
				modal: true,
				draggable: false,
				resizable: false,
				position: 'top',
				height: '600',
				width: '950',
				open: function(event, ui){
					jQuery('#backBtn').hide();
				},
				close: function(event,ui){
					closeShoppingCartPopup();
					loadCartSize(cartObj);					
				}
			});
			dialog.dialog('open');
		});
	}
	else
	{
		jQuery.ajax({
			  type: "POST",
			  url: divResourceMapping.url[index],
			  data:{linkIdFromLeftNav: linkIdFromLeftNav,
					cartType: cartCheckObj.cartType},
			  success:function(content){
				  hideOverlay();
				  jQuery("#storeContent").hide();
				  jQuery('#mainRightNavContainer div').each(function(){
					 
					  jQuery(this).hide();
					  jQuery(this).html('');
				  });
				  jQuery('.leftNavLinks li').each(function(){
					  jQuery(this).find("a").removeClass("active");
				  });
				  jQuery('#'+divResourceMapping.divId[index]).html(content);
				  jQuery('#'+divResourceMapping.divId[index]).show();
				  jQuery('#'+divResourceMapping.activeLinkId[index]).addClass("active");

				  if(global_click_msgs.clickedFrom === "showShoppingCart")
				  {
					  jQuery('#backBtn').show();
				  }
			  }
		});
	}
}

function submitOrder(){
	jQuery.ajax({
		  type: "POST",
		  url: "${getCXMLencodedTextvar}",
		  data: {cartType: cartCheckObj.cartType},
		  success:function(content){
			  jQuery('#cxmlDiv').append(content);
			  jQuery("#cXmlForm").submit();
		  }
		});
}

function showHome(flag)
{   
	continueShoppingFlag=flag;
	hideOverlay();
	jQuery("#historyLink,#reqPrinterLink,#reqSupplyLink").removeClass("active");
	jQuery('#mainRightNavContainer div').each(function(){
		jQuery(this).hide();
		jQuery(this).html('');
	});

	if(acntType === "KAISER")
		jQuery("#storeContent").show();
	else if(acntType === "REPUBLIC")
		calledFromLEftNav("reqPrinterLink");
	
}

function exitCart()
{
	jConfirm("Selected Quantities will be lost. Do you want to continue?", "", function(confirmResult) {
		
		if(confirmResult){
			jQuery.ajax({
				  type: "POST",
				  url: "${clearCartSession}",
				  success:function(){
					//jQuery("#historyLink,#reqPrinterLink,#reqSupplyLink").removeClass("active");
					showHome(false);
					closeShoppingCartPopup();
				  }
			});
			
		}else{
			return false;
		}
	});
}

function closeShoppingCartPopup()
{
	hideOverlay();
	dialog=null;
	jQuery('#shoppingCartDetails').remove();
}

$( document ).ready(function() {
	var cartCheckObj={cartType:"printers",qty:0};
    jQuery("#storeContent").hide();
    acntType = "${acntType}";
    showHome(false);
});

var objLinkProducts={id:"",partType:"",isPrinter:""};
var printerObject={printerType:"",id:"printerProduct"};

jQuery('.printer-links a').click(function(){
	objLinkProducts={id:"",partType:"",isPrinter:""};
	if(jQuery(this).attr('id')=='dot_matrix'){
		objLinkProducts.partType="Dot Matrix"
		objLinkProducts.isPrinter="true";		
	}
	else
	{
		objLinkProducts.partType=jQuery(this).attr('id');
		objLinkProducts.isPrinter="false";
	}
	objLinkProducts.id="printerProduct";
	
	global_click_msgs.clickedFrom="homePageProducts";//defined in rightNavHome.jsp
	calledFromLEftNav(objLinkProducts.id);
});

jQuery('.health-connect-links a').click(function(){
	objLinkProducts={id:"",certType:"",isPrinter:""};
	objLinkProducts.id="printerProduct";
	objLinkProducts.certType=jQuery(this).attr('id');
	global_click_msgs.clickedFrom="certProducts";//defined in rightNavHome.jsp
	calledFromLEftNav(objLinkProducts.id);
});

</script>
