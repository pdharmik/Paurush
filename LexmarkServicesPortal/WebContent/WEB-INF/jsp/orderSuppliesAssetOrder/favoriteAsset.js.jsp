<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.lexmark.services.util.LexmarkSPOmnitureConstants"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<portlet:resourceURL var="updateURL" id="updateUserFavoriteAsset"/>
<script type="text/javascript">
var assetStatus = {};
function updateUserFavoriteAsset(assetId) {
	callOmnitureAction('Favorite Asset', 'Update Favorite Asset');
	
	if(assetStatus[assetId]&&assetStatus[assetId].isLoading){
		return;
	}
	var starImg = window.document.getElementById("starIMG_"+assetId);
	var isFavorite=(jQuery("#starIMG_"+assetId).attr('class').indexOf('bookmark-star-icon')!=-1?true:false);
	//var isFavorite = starImg.name == "favorite.png";
	assetStatus[assetId] = {isFavorite:isFavorite,isLoading:true};
	newFlagStatus = isFavorite? "false" : "true";
	starImg.src = "<html:imagesPath/>loading-icon.gif";
	var url = "${updateURL}"+"&favoriteAssetId="+assetId+"&favoriteFlag="+newFlagStatus;
	doAjax(url, updateFavoriteSuccessCallBack, updateFavoriteFailCallBack);
};


function updateFavoriteSuccessCallBack(loader) {
	var assetId = loader.data;
	var starImg = window.document.getElementById("starIMG_"+assetId);
	var starTxt = window.document.getElementById("starTXT_"+assetId).value;
	//just change the star image
	if(assetStatus[assetId].isFavorite){
		jQuery('#starIMG_'+assetId).removeClass('bookmark-star-icon');
		jQuery('#starIMG_'+assetId).addClass('removebookmark-icon');
		starImg.src = "<html:imagesPath/>transparent.png";
		//starImg.name = "unfavorite.png";
		document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='deviceFinder.label.bookmarkThisDevice'/>";
		assetStatus[assetId].isFavorite = false;
	}
	else{
		jQuery('#starIMG_'+assetId).removeClass('removebookmark-icon');
		jQuery('#starIMG_'+assetId).addClass('bookmark-star-icon');
		starImg.src = "<html:imagesPath/>transparent.png";
		//starImg.name = "favorite.png";
		document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='orderSupplies.label.unbookmarkThisDevice'/>";
		assetStatus[assetId].isFavorite = true;
	}
	assetStatus[assetId].isLoading = false;
	return true;
};

function updateFavoriteFailCallBack(loader) {
    var assetId = loader.data;
    var starImg = window.document.getElementById("starIMG_"+assetId);
    var starTxt = window.document.getElementById("starTXT_"+assetId).value;
    
    //just change the star image
    if(!assetStatus[assetId].isFavorite){
        starImg.src = "<html:imagesPath/>transparent.png";
        document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='deviceFinder.label.bookmarkThisDevice'/>";
    }
    else{
        starImg.src = "<html:imagesPath/>transparent.png";
        document.getElementById("starTXT_"+assetId).innerHTML = "<spring:message code='orderSupplies.label.unbookmarkThisDevice'/>";
    }
    assetStatus[assetId].isLoading = false;
    return true;
}
</script>