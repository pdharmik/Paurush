var addressListGridDisplay;
function initializeAddressGrid(){
	
	addressListGridDisplay='<portlet:resourceURL id="addressListPopulate"/>'+'&ispopup=true';
	alert('in url of address list grid');
	
	serviceAddressGrid = new dhtmlXGridObject('serviceAddressGridbox'); 	 
    serviceAddressGrid.setImagePath("<html:imagesPath/>/gridImgs/");
    serviceAddressGrid.setHeader("&nbsp;,<spring:message code='serviceRequest.listHeader.serviceAddressListPopUp'/>"+",&nbsp;,&nbsp,&nbsp,&nbsp");
    serviceAddressGrid.attachHeader(",#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,");
    serviceAddressGrid.setInitWidths("60,50,90,90,90,90,90,50,50,80,80,80,10,10,10,100");
    serviceAddressGrid.setColAlign("left,left,left,left,left,left,left,left,left,left,left,left,left,left,left,left");
    serviceAddressGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
    serviceAddressGrid.setColSorting("str,str,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
    serviceAddressGrid.init();
    serviceAddressGrid.prftInit();
    
    serviceAddressGrid.enablePaging(true, 5, 6, "pagingArea5", true, "infoArea");
    serviceAddressGrid.setPagingSkin("bricks");
    serviceAddressGrid.enableAutoWidth(true);
    serviceAddressGrid.enableAutoHeight(true);
    serviceAddressGrid.enableMultiline(true);
    serviceAddressGrid.a_direction = "ASCENDING";
    serviceAddressGrid.setSizes();
    serviceAddressGrid.setColumnHidden(1,true); // indicate column index and set the state to true to hire the column
    serviceAddressGrid.setColumnHidden(13,true);
    serviceAddressGrid.setColumnHidden(14,true);
    serviceAddressGrid.setColumnHidden(15,true);
    serviceAddressGrid.setSkin("light");
    serviceAddressGrid.attachEvent("onXLS", function() {
		//document.getElementById('loadingNotification_addr').style.display = '';
    	
	});
    serviceAddressGrid.attachEvent("onMouseOver", function(id,ind){		
		return false;
	});
    serviceAddressGrid.attachEvent("onXLE", function() {
    	
    	serviceAddressGrid.setSortImgState(true, 2, "asc");
    	document.getElementById('loadingNotification_addr').style.display = 'none';
    	
	});
    
    serviceAddressGrid.loadXML(addressListGridDisplay);
    
   
    
}	

function showAllAccountAddress(){
	jQuery('#alladdress').html('All Account Addresses');
	jQuery('#myBookmarkAddress').html('<a href="javascript:showBookmarkedAddress();">My Bookmarked Addresses</a>');
	serviceAddressGrid.clearAndLoad(addressListGridDisplay);
}
function showBookmarkedAddress(){
	//disable my bookmarked addresses
	jQuery('#myBookmarkAddress').html('My Bookmarked Addresses');
	//change All Account Address to Link
	jQuery('#alladdress').html('<a href="javascript:showAllAccountAddress();">All Account Addresses</a>');
	serviceAddressGrid.clearAndLoad(addressListGridDisplay+'&searchCriterias='+'${contactid}');
}

var favStatus = {};
function setServiceAddressFavourite(favoriteAddressId, doSelectUnfavoriteFlag){        
	var url = '<portlet:resourceURL id="setServiceAddressFavouriteFlag"/>';
    url += "&favoriteAddressId="+favoriteAddressId;
    url += "&contactId=${contactid}";

    // if user selects an unfavorite contact
    if (doSelectUnfavoriteFlag) {
        url += "&flagStatus=" + "false";
        doAjax(url, null, null, "");
        return;
    }
    // if the favorite flag is being updated
    if (favStatus[favoriteAddressId]&&favStatus[favoriteAddressId].isLoading) {
        return;
    }
    
    var starImg = window.document.getElementById("starImg_address_" + favoriteAddressId);
    var isFavorite=(jQuery("#starImg_address_"+favoriteAddressId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
    //var isFavorite = !(starImg.src.indexOf("unfavorite.png") > -1);
    favStatus[favoriteAddressId] = {isFavorite:isFavorite,isLoading:true};
    starImg.src = "<html:imagesPath/>loading-icon.gif";
    url += "&flagStatus=" + isFavorite;
    
    doAjax(url, callbackGetServiceAddressResult, callbackGetServiceAddressFailure, "");
}

// call back, when successfully update favorite status of service address
function callbackGetServiceAddressResult(result) {
	var addressId = result.data;
	if (document.getElementById("serviceAddressGridbox").style.display!="none") {		
		var starImg = document.getElementById('starImg_address_' + addressId);
		if (favStatus[addressId].isFavorite) {
			jQuery('#starImg_address_'+assetId).removeClass('bookmark-star-icon');
			jQuery('#starImg_address_'+assetId).addClass('removebookmark-icon');
			starImg.src = "<html:imagesPath/>transparent.png";
			//starImg.src = "<html:imagesPath/>unfavorite.png";
		} else {
			jQuery('#starImg_address_'+assetId).removeClass('removebookmark-icon');
			jQuery('#starImg_address_'+assetId).addClass('bookmark-star-icon');
			starImg.src = "<html:imagesPath/>transparent.png";
			//starImg.src = "<html:imagesPath/>favorite.png";
		}
	}
	if (favStatus[addressId].isFavorite) {
		favStatus[addressId].isFavorite = false;
	} else {
		favStatus[addressId].isFavorite = true;
	}
	favStatus[addressId].isLoading = false;
}

// call back, when fail to update favorite status of service address
function callbackGetServiceAddressFailure(result) {
    var addressId = result.data;
    if (document.getElementById("serviceAddressGridbox").style.display!="none") {      
        var starImg = document.getElementById('starImg_address_' + addressId);
        if (favStatus[addressId].isFavorite) {
            starImg.src = "<html:imagesPath/>transparent.png";
        } else {
            starImg.src = "<html:imagesPath/>transparent.png";
        }
    }
    favStatus[addressId].isLoading = false;
}



