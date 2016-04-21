/**
 * 
 */
var prevTime=0;
function loadCartSize(obj){
	
	jQuery('#'+obj.loadingId).show();
	jQuery('#'+obj.cartSizeId).hide();
	jQuery.getJSON(obj.url+"&ct="+obj.cartType,function(result){
		jQuery('#'+obj.loadingId).hide();
		jQuery('#'+obj.cartSizeId).show();	
		showValueinCart(result,obj);
	});
}
//result should be json object
function showValueinCart(result,obj){
	
	
	if(result.time>prevTime){
		prevTime=result.time;
		
		jQuery('#'+obj.cartSizeId).html(result.size);
		cartCheckObj.qty=result.size;// cartcheckobj is defined in rightnavhome.jsp
	}
}

function removeFromCART(obj){
	jQuery.getJSON(obj.url+"&pr="+obj.bundleId+"&ct="+obj.cartType,function(result){
		if(result.status=="success"){
			jQuery('#cart_totalPrice').html(result.total);
			cartCheckObj.qty=result.total;// cartcheckobj is defined in rightnavhome.jsp
			jQuery('#cartItem_'+result.id).remove();
			jQuery('#quantity'+result.id).val('');//this will clean the grid text box in main page
			jQuery('#cartButton'+result.id).val('ADD TO CART');
			jQuery('#quantity_optn_warran'+result.id).val('');//this will clean the grid text box in main page
			cartCheckObj.qty=result.total;// cartcheckobj is defined in rightnavhome.jsp
			
			if(result.size === "0")
			{
				jQuery("#shoppingCartDetails").html("");
				jQuery("#shoppingCartDetails").html("<div class=\"info ok banner\">Your cart is Empty</div>");
			}
		}
		
	});
	
}

function addCartQty(obj){
	var quantity=obj.qty;
	bId=obj.bundleId;
	var numberPatrn = /^\s*\d+\s*$/;
	jQuery('#quantity_cart'+bId).removeClass('errorColor');
    jQuery('#errorMsgPopupCart').hide();
    jQuery('#errorMsgPopupCart').html('');
    if(quantity==null || $.trim(quantity).length<=0 || parseInt(quantity) <= 0) {
		
			jQuery('#errorMsgPopupCart').show();
			jQuery('#errorMsgPopupCart').append("<li><strong>"+validationmsg.qtyInvalid+"</strong></li>");
			jQuery('#quantity_cart'+bId).addClass('errorColor');
			jQuery("#submit_order").prop("disabled", true);
			return false;
	}
	else{
		if(!numberPatrn.exec(quantity)){
			jQuery('#errorMsgPopupCart').show();
			jQuery('#errorMsgPopupCart').append("<li><strong>"+validationmsg.qtyInvalid+"</strong></li>");
			jQuery('#quantity_cart'+bId).addClass('errorColor');
			jQuery("#submit_order").prop("disabled", true);
			return false;	
		}
		else
			jQuery("#submit_order").prop("disabled", false);
	}
jQuery.getJSON(obj.url+"&pr="+obj.bundleId+"&qt="+obj.qty+"&ct="+obj.cartType,function(result){
		
		if(result.status=="success"){
			
			jQuery('#cart_totalPrice').html(result.total);
			cartCheckObj.qty=result.total;// cartcheckobj is defined in rightnavhome.jsp
			jQuery('#quantity'+result.id).val(result.quantity);//this will update the grid text box in main page
			jQuery('#successMessage').html('Cart Updated Successfully');
		
			jQuery('#successMessage').show();


			setTimeout('$("#successMessage").hide()',1500);


		}
		
	})
}



function moveToCart(productId,buttonId){	
	//Keep a copy of the object in cartt...
	cartItems.addtoItems(bundlesObj.bundlesData[productId]);
	var objectDet={quantityId:"quantity",productId:productId,bundleId:"",optionWarranty:false,cartType:"printers"};
	doCart(objectDet,buttonId);
}
function moveToCartSupplies(productId,buttonId){	
	var objectDet={quantityId:"quantity",productId:productId,bundleId:"",optionWarranty:false,cartType:"supplies"};
	doCart(objectDet,buttonId);
}

function doCart(detailsObj,buttonId){
	var queryParam=detailsObj.quantityId+detailsObj.productId;
	
	var quantity=jQuery('#'+queryParam).val().trim();
    
    var numberPatrn = /^\s*\d+\s*$/;
    jQuery('#'+queryParam).removeClass('errorColor');
    jQuery('#errorMsgPopup').hide();
    jQuery('#errorMsgPopup').html('');
    if(quantity==null || $.trim(quantity).length<=0 || parseInt(quantity) <= 0) {
		
			jQuery('#errorMsgPopup').show();
			jQuery('#errorMsgPopup').append("<li><strong>"+validationmsg.qtyInvalid+"</strong></li>");
			jQuery('#'+queryParam).addClass('errorColor');
			return false;
	}
	else{
		if(!numberPatrn.exec(quantity)){
			jQuery('#errorMsgPopup').show();
			jQuery('#errorMsgPopup').append("<li><strong>"+validationmsg.qtyInvalid+"</strong></li>");
			jQuery('#'+queryParam).addClass('errorColor');
			return false;	
		}
	}
    var obj={
			 cartSizeId:"totItems"
	 };
    if(buttonId.indexOf('addToCartOptnWarran') == -1){
    	jQuery("#"+buttonId).val("Update Cart");
        jQuery("#"+buttonId).html("Update Cart");
    }
    
    var id=jQuery('#printerId').html();
    
    // added for UNSPSC start  
    var cartType=detailsObj.cartType;
    var unspsc ="";
    var printerTypeNum="";
    var marketingName = "";
  
    if(detailsObj.quantityId=="quantity_optn_warran"){
    	printerTypeNum=detailsObj.productId;
    }else{
    	printerTypeNum=$('#'+detailsObj.productId+"_partType").val();
    }
    
    //printerTypeNum="28E0100";
    // this ajax call will be made only for printers to get the unspsc code.
    // addToCart() to be call for other scenarios from outside the ajax
    if(cartType == "printers"){
    	 jQuery.ajax({ 
    	         url:"https://www.lexmark.com/en_US/epg/products/"+printerTypeNum+".json",  // currnetly url is hardcoded. this will be dynamic later based on part number
    	         dataType : 'json',
    	          success: function (data) {
    	        	 unspsc = data.UNSPSCCode;
    	        	 marketingName = data.marketing;
    	        	 addToCart();
    	          },
    				failure:function (data){
    					jAlert("UNSPSC Code is blank","");    					
    				},
    				error:function(){
    					jAlert("UNSPSC Code is blank","");    					
    				}
    	       });
    }
    else{
    	addToCart();
    }
   
    // end for UNSPSC
    
    function addToCart(){
    	jQuery.ajax({
        	url:addToCartObj.url,
        	data:{
    	    	quantity:quantity,
    	    	prodId:detailsObj.productId,
    	    	isOptnWarr:detailsObj.optionWarranty,
    	    	cartType:detailsObj.cartType,
    	    	bundleId:detailsObj.bundleId,
    	    	unspscCode:unspsc,
    	    	marketingName:marketingName
        	},
        	type:'POST',
        	dataType:'JSON',
        	success:function(cartResponse){
        		showValueinCart(cartResponse,obj);
        	}
        });
    }
    
}
function addOptnsWarranties(index,bId){
	var familyParts=bundlesObj.bundlesData[bId].accessories[index].list;
	var buttonId='addToCartOptnWarran'+bId;
	var numberPatrn = /^\s*\d+\s*$/;
	var validationReq = true;
	var invalidParts = [];
	for(var i=0;i<familyParts.length;i++){		
		var objectDet={quantityId:"quantity_optn_warran",productId:familyParts[i].pNo,bundleId:bId,optionWarranty:true,cartType:"printers"};
		var queryParam=objectDet.quantityId+objectDet.productId;
		var quantity=jQuery('#'+queryParam).val().trim();
		if(quantity!=null && $.trim(quantity).length>0 && parseInt(quantity) > 0 && numberPatrn.exec(quantity)) {
			validationReq = false;
			doCart(objectDet,buttonId);
			if(invalidParts.indexOf(queryParam) != -1){
				invalidParts.splice(invalidParts.indexOf(queryParam), 1);
			}			
				
		}
		else{
			jQuery('#'+queryParam).removeClass('errorColor');
			invalidParts.push(queryParam);
		}
		
	}
	if(validationReq){
		jQuery('#errorMsgPopup').show();
		jQuery('#errorMsgPopup').append("<li><strong>"+validationmsg.qtyInvalid+"</strong></li>");
		for(var i=0;i<invalidParts.length;i++){		
			jQuery('#'+invalidParts[i]).addClass('errorColor');
		}
	}
	
}
var flagForCartPopup = true;
function showShoppingCart(cartType){
	var shoppingCartObject={cart:"",id:"shoppingCart",cartType:cartType};
    
    	//printerObject.printerType=jQuery(this).attr('id');
    	global_click_msgs.clickedFrom="showShoppingCart";//defined in rightNavHome.jsp
    	calledFromLEftNav(shoppingCartObject);
    	flagForCartPopup = false;
}
var cartItems={
		items:[],
		addtoItems:function(item){
			this.items.push(item);			
		},
		getItem:function (itemNo){
			for(var i=0;i<this.items.length;i++){
				if(this.items[i].bundleId==itemNo){
					return this.items[i];
				}
			}
		}
}

