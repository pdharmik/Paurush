

<%@ include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>/css/text-scroller.css"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>/css/jquery-ui.css"/>
<%@ include file="/WEB-INF/jsp/includeGrid.jsp"%>
<script src="<html:rootPath/>/js/bundle.js?v=<html:fileCacheVersion/>"></script>


<portlet:resourceURL var="loadHistoryList" id="loadHistoryList"></portlet:resourceURL>
<portlet:resourceURL var="loadPrinterList" id="loadPrinterList"></portlet:resourceURL>
<portlet:resourceURL var="loadSuppliesList" id="loadSuppliesList"></portlet:resourceURL>
<portlet:resourceURL var="loadGlobalSearchList" id="loadGlobalSearchList"></portlet:resourceURL>

<portlet:resourceURL var="loadPrinterProducts" id="loadPrinterProducts"></portlet:resourceURL>

<portlet:resourceURL var="loadShoppingCart" id="loadShoppingCart"></portlet:resourceURL>
<portlet:resourceURL var="loadSuppliesProduct" id="loadSuppliesProduct"></portlet:resourceURL>
<portlet:resourceURL var="clearCartSession" id="clearCartSession"></portlet:resourceURL>

<portlet:resourceURL var="getCXMLencodedTextvar" id="getCXMLencodedText"></portlet:resourceURL>

<script>

var global_click_msgs={clickedFrom:""};//This will be used in certifiedProducts.jsp and printerlist.jsp this will help to differentiate the calls of the grid
//Whether it came from certifiedproducts.jsp or printerlist.jsp 

var divResourceMapping={
		divId:[ "historyList",       "printersList",      "suppliesList",       "printerProducts",       "suppliesProduct","shoppingCart","globalSearchList"],
		linkId:["historyLink",       "reqPrinterLink",    "reqSupplyLink",      "printerProduct",        "suppliesProduct","shoppingCart","globalSearchList"],
		activeLinkId:["historyLink",       "reqPrinterLink",    "reqSupplyLink",      "reqPrinterLink",  "reqSupplyLink","shoppingCart","globalSearchList"],
		url:[   "${loadHistoryList}","${loadPrinterList}","${loadSuppliesList}",		"",				  "${loadSuppliesProduct}","${loadShoppingCart}","${loadGlobalSearchList}"]
		
};
var cartCheckObj={cartType:"printers",qty:0};
var flag=true;
var continueShoppingFlag=true;
var searchNumber = "";

jQuery(document).bind("loadOtherPortlet",eventHandler);
function eventHandler(e,params){
	
	switch(params){
		case "historyLink":
			 $("#storeContent").hide();
			showHideDivs('historyList');
			refreshHistoryGrid();
			break;
		case "reqPrinterLink":
			break;
		case "reqSupplyLink":
			break;
		case "globalSearchList":
			$("#storeContent").hide();
			showHideDivs('globalSearchList');
			break;
					
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
	 
	 if("${sessionScope.aribaParamMap["fromAriba"]}"=="false"){
		 continueCallFromLeftNav(linkIdFromLeftNav);
	 }
	 else{
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
	if(!flagForCartPopup){
			hideOverlay();
			return;
		}
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
					jQuery('.backBtn').hide();
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
		if(linkIdFromLeftNav.cartType == "printers"){
			cartCheckObj.cartType = "printers";
		}
		if(linkIdFromLeftNav.cartType == "supplies"){
			cartCheckObj.cartType = "supplies";
		}
		if(linkIdFromLeftNav.cartType == "globalSearch"){
			cartCheckObj.cartType = "globalSearch";
		}
		jQuery.ajax({
			  type: "POST",
			  url: divResourceMapping.url[index],
			  data:{linkIdFromLeftNav: linkIdFromLeftNav,
					cartType: cartCheckObj.cartType,
					searchNumber: searchNumber},
			  success:function(content){
				  hideOverlay();
				  jQuery("#storeContent").hide();
				  jQuery('#mainRightNavContainer>div').each(function(){
					  jQuery(this).hide();
				  });
				  if(linkIdFromLeftNav != 'globalSearchList'){
					  jQuery('.leftNavLinks li').each(function(){
						  jQuery(this).find("a").removeClass("active");
					  }); 
				  }
				  jQuery('#'+divResourceMapping.divId[index]).html(content);
				  jQuery('#'+divResourceMapping.divId[index]).show();
				  if(linkIdFromLeftNav != 'globalSearchList'){
				  	jQuery('#'+divResourceMapping.activeLinkId[index]).addClass("active");
				  }
				  if(global_click_msgs.clickedFrom === "showShoppingCart")
				  {
					  jQuery('.backBtn').show();
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
	showHideDivs();
	<c:if test='${sessionScope.aribaParamMap["isKaiser"]=="true"}'>
		jQuery("#storeContent").show();
	</c:if>
	<c:if test='${sessionScope.aribaParamMap["isRepublic"]=="true"}'>
			calledFromLEftNav("reqPrinterLink");
	</c:if>
	
}
function showHideDivs(exceptDiv){
	$('#shoppingCartDetails').remove();
	flagForCartPopup = true;
	jQuery('#mainRightNavContainer').children('div').each(function(){
		
		if(exceptDiv != undefined && exceptDiv==$(this).attr('id')){
			$(this).show();
		}else{
			$("#"+$(this).attr('id')).hide();
		}
		
	});
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
	$("#storeContent").hide();
    
    showHome(false);
});

var objLinkProducts={id:"",partType:"",isPrinter:""};
var printerObject={printerType:"",id:"printerProduct"};

jQuery('.printer-links a').click(function(){

	
	showHideDivs('printerProducts');
	$("#storeContent").hide();
	
	 if($(this).attr('id')=='dot_matrix'){
		 
		 getDataForPrinter_Types({
			 	"isProduct":true,
				"cType":"printers",
				"partType":"Dot Matrix"		
			});	 
		
	 }else{
		 getDataForPrinter_Types({
			 	"isProduct":false,
				"cType":"printers",
				"partType":$(this).attr('id')		
			});	 
	 }
	
	return false;
});

jQuery('.health-connect-links a').click(function(){
	 objLinkProducts={id:"",certType:"",isPrinter:""};
	objLinkProducts.id="printerProduct";
	objLinkProducts.certType=jQuery(this).attr('id');
	global_click_msgs.clickedFrom="certProducts";//defined in rightNavHome.jsp
	calledFromLEftNav(objLinkProducts.id); 
	
});

</script>

<c:if test='${sessionScope.aribaParamMap["fromAriba"]=="true"}'>
	<jsp:include page="/WEB-INF/jsp/shoppingCart/totalItems.jsp"/>
</c:if>
<div id="mainRightNavContainer">
<%-- Mind before adding DIV over here. will get hidden on page load and other conditions...  --%>
		<div id="historyList">
			<jsp:include page="/WEB-INF/jsp/requests/history/gridRequestHistory.jsp"/>
		</div>
		<c:if test='${sessionScope.aribaParamMap["isKaiser"]=="false"}'>
			<div id="suppliesList" class="noDisplay"></div>
		</c:if>	
		<div id="printerProducts" class="noDisplay">
				<jsp:include page="/WEB-INF/jsp/requests/product/printerProduct.jsp"/>
		</div>
		
		<div id="shoppingCart" class="noDisplay"></div>
		<div id="suppliesProduct" class="noDisplay"></div>
		<div id="eQuote" class="noDisplay"></div>
		<c:if test='${sessionScope.aribaParamMap["isKaiser"]=="false"}'>
		<div id="printersList" class="noDisplay">
			<jsp:include page="/WEB-INF/jsp/requests/product/printersList.jsp"/>
		</div>
		</c:if>
		<div id="globalSearchList" class="noDisplay">
			<jsp:include page="/WEB-INF/jsp/requests/globalSearchList.jsp"/>
		</div>
		<div id="showPrinterDetails" class="noDisplay"></div><%-- this div shows the printer details from (Buy Options) cart --%>
</div>
<div id="shoppingCartPopup" class="noDisplay"></div>
<div class="noDisplay" id="cxmlDiv"></div>
