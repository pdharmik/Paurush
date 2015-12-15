<%--
 * Copyright 2005-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
--%>

<%@ page contentType="text/html" isELIgnored="false" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" uri="http://lexmark.com/tld/util" %> 

<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>


<%@page import="com.lexmark.util.PaginationUtil"%><c:set var="regional" value="${pageContext.request.locale.language}"/>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/jquery-ui.css?version=<html:fileCacheVersion/>"/>
<c:set var="language" value="${pageContext.request.locale.language}"/>
<c:set var="country" value="${pageContext.request.locale.country}"/>
<c:set var="mdmId" value="${pageContext.request.session}"/>
<c:if test="${language == 'zh' || language == 'pt'}">
      <c:set var="regional" value="${language}-${country}"/>
</c:if>
<c:set var="showDefaultError" value="false" scope="request"></c:set>
<c:if test="${not included}">

<script type="text/javascript"><%@ include file="../../js/validation.js"%></script>
<script type="text/javascript"><%@ include file="../../js/reskin.js"%></script>
<style type="text/css"><%@ include file="../../css/lexmark.css" %></style> 

<script type="text/javascript">

	

	var global_paging_size = 5;
	var offsetMinute = new Date().getTimezoneOffset();
	var timezoneOffset = offsetMinute/60;
	var defaultBannerId = null;
	function showMessage(msg, bannerId, keepOld){
		showHeaderMessage(true, msg, bannerId, keepOld);
	}
	function showError(msg, bannerId, keepOld){
		showHeaderMessage(false, msg,bannerId, keepOld);
	}
	function showSystemError(msg){
		if(msg==null){			
			msg="";
		}
		msg+=" <spring:message code='exception.contactAdmin'/>";;
		popMessage(msg);
	}
	function popMessage(msg, title){
		if(msg==null){			
			msg="<spring:message code='exception.contactAdmin'/>";
		}
		if(!title){
			title = "<spring:message code='exception.generalError.title'/>";
		}
		new Liferay.Popup({
	        title: title,
	        position: [450,150], 
	        modal: true,
	        width: 480, 
	        xy: [100, 100],
	        onClose: function() {showSelect();},
	        message:"<div class='content'><table><tr><td Valign='top'><img src='/LexmarkPartnerPortal/images/error.png'></td><td>"
				+"<td><div style='margin:0px 0px 10px 10px'>"+msg+"</div></td></tr></table>"
				+"<div style='text-align:center'><button class='button' onclick='Liferay.Popup.close(this)'><spring:message code='button.close'/></button></div></div>"
	    });
	}
	function hideSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "hidden";
		}      
	}  

	function showSelect(){   
		var selects = document.getElementsByTagName('select');
		for (var i=0; i < selects.length; i++){
			var select = selects[i];
			select.style.visibility = "visible";
		} 
	}  
	function showHeaderMessage(suc, msg, bannerId, keepOld){
		if(!keepOld)clearMessage(); //default to clear old message, even if keepOld is not specified.

		var container = null;
		var msgClassName = suc ? "portlet-msg-success" : "portlet-msg-error";
		if(!bannerId){
			bannerId = defaultBannerId;
		}
		if(!bannerId){
			className = suc ? "serviceSuccess" : "serviceError";
			container = myGetBannersByClassName(className)[0];
		}else{
			var idpre = suc ? "message_banner_" : "error_banner_";
			var containerId = idpre+bannerId;
			container = document.getElementById(containerId);
		}
		var li = document.createElement("li");
		li.innerHTML = msg;
		li.className = msgClassName;
		container.appendChild(li);
	}
	
	function clearMessage(){
		var classNames = ["serviceSuccess" , "serviceError"];
		for(var i=0;i<classNames.length;i++){
			className = classNames[i];
			var containers = myGetBannersByClassName(className);
			for(var j=0;j<containers.length;j++){
				container = containers[j];
				while(container.firstChild) {
					container.removeChild( container.firstChild );
				}
			}
		}
	}
	function parseLoader(loader){
		var statusAtt = loader.doXPath("/result/@succeed", null, null, "single");
		if(!statusAtt){
			return {succeed : false, message:loader.xmlDoc.responseText};			
		}else{
			var message = loader.doXPath("/result/message/text()", null, null, "single").nodeValue;
			var groupNode =  loader.doXPath("/result/group/text()", null, null, "single") ;
			var returnDataNode =  loader.doXPath("/result/data/text()", null, null, "single") ;
			
			var data = null;
			if(returnDataNode!=null && returnDataNode.nodeValue!=null){
				if(isFF()){
					returnDataNode =  loader.doXPath("/result/data", null, null, "single");
				}
				loader.data = eval(getNodeValue(returnDataNode));
			}
			var group = groupNode ? groupNode.nodeValue : null;
			return {succeed : "true" == statusAtt.nodeValue, message:message, group:group};
		}
	}

	function isFF(){
		 	var Sys = {};
	        var ua = navigator.userAgent.toLowerCase();
	        var s;
	        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 0;

	        if (Sys.firefox) {
		        return true;
		    }else{
			    return false;
			}
	}
	//Mozilla has many textnodes with a size of 4096 
	function getNodeValue(node){ 
		var result = "";
		if(node.hasChildNodes()){ 
			for(var j=0;j<node.childNodes.length;j++){ 
				result+=new String(node.childNodes.item(j).nodeValue); 
			} 
		}else{
			result = node.nodeValue;
		}
		return result; 
	} 
	
	function doAjax(url, successCalBack, failBack, bannerId){		
		dhtmlxAjax.get(url, function(loader){
			var status = parseLoader(loader);
			if(!status.succeed){		
				if(typeof failBack == 'function'){
					var cancelPop = failBack(loader);			
					if(cancelPop){
						hideOverlay();		
						return;
					}
				}else{
					if(failBack){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				popMessage(status.message,status.group);
				hideSelect();	
			}else{
				if(typeof successCalBack == 'function'){
					var cancelPop = successCalBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}else{
					if(successCalBack){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				showMessage(status.message, bannerId);
			}
		});
	}
	
	function doAjaxSyncGet(url, successCalBack, failBack, bannerId){		
		dhtmlxAjax.get(url, function(loader){
			var status = parseLoader(loader);
			if(!status.succeed){		
				if(typeof failBack == 'function'){
					var cancelPop = failBack(loader);			
					if(cancelPop){
						hideOverlay();		
						return;
					}
				} 
				else{
					if(failBack){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				popMessage(status.message,status.group);	
				hideSelect();
			}else{
				if(typeof successCalBack == 'function'){
					var cancelPop = successCalBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}else{
					if(successCalBack){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				showMessage(status.message, bannerId);
			}
		}, true);
	}

	function doAjaxPost(url, params, successCalBack, failBack, bannerId){
		dhtmlxAjax.post(url, params, function(loader){
			var status = parseLoader(loader);
			if(!status.succeed){
				if(typeof failBack == 'function'){
					var cancelPop = failBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}else{
					if(failBack){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				popMessage(status.message,status.group);
				hideSelect();	
			}else{
				if(typeof successCalBack == 'function'){
					var cancelPop = successCalBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				showMessage(status.message, bannerId);
			}
		});
	}

	function doAjaxObjVar(params){
		var url = params.url;
		var requestParams = params.requestParams;
		var paramStr = "";
		if(requestParams){
			for(p in requestParams){
				if(paramStr){
					paramStr+="&";
				}
				paramStr += p+"="+encodeURIComponent(requestParams[p]);
			}
		}
		var successParams = params.successParams;
		var failedParams = params.failedParams;
		var failBack = params.failBack;
		var successCallBack = params.successCallBack;
		var bannerId = params.bannerId;
		
		dhtmlxAjax.post(url, paramStr, function(loader){
			var status = parseLoader(loader);
			
			if(!status.succeed){
				if(typeof failBack == 'function'){
					if(failedParams)loader = failedParams;
					var cancelPop = failBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}else{
					if(failBack){
						hideOverlay();
						return;
					}
				}
				popMessage(status.message,status.group);
				hideSelect();	
			}else{
				if(typeof successCallBack == 'function'){
					if(successParams)loader = successParams;
					var cancelPop = successCallBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				showMessage(status.message, bannerId);
			}
		});
		
		
	}
	function myGetBannersByClassName(className){ //only for ul element
		var uls = document.getElementsByTagName("ul");
		var temparr = [];
		for(i=0;i<uls.length;i++){
			if(uls[i].className==className){
				temparr[temparr.length] =  uls[i];
			}
		}
		return temparr;
	}

	function showOverlay(){
		jQuery("#overlay").css({
			display:"block",
			top:0,
			left:0,
			width : jQuery.ui.dialog.overlay.width(),
			height : jQuery.ui.dialog.overlay.height()
		}).addClass("waiting");
		jQuery("#processingHint").css({
			display:"block",
			width: "300px",
			height:"50px",
			left: parseInt( jQuery.ui.dialog.overlay.width() )/2-100,
			top: document.documentElement.scrollTop + document.body.scrollTop + document.documentElement.clientHeight/2 - 50 +"px" // IE, firefox: document.body.scrollTop returns 0; chrome: document.documentElement.scrollTop returns 0
		}).addClass("waiting");
		jQuery(window.document.body).addClass("waiting");
	}
	function hideOverlay(){
		jQuery("#overlay").css({
			display:"none" 
		});
		jQuery("#processingHint").css({
			display:"none"
		});
	}
/*Added for Device Mgmt LTPC Popup*/
function showOverlayPopup(){
		jQuery("#overlayPopup").css({
			display:"block",
			top:0,
			left:0,
			zIndex:1004,
			width : jQuery.ui.dialog.overlay.width(),
			height : jQuery.ui.dialog.overlay.height()
		}).addClass("waiting");
		jQuery("#processingHintPopup").css({
			display:"block",
			width: "300px",
			height: "50px",
			zIndex:1005,
			left: parseInt( jQuery.ui.dialog.overlay.width() )/2-100,
			top: document.documentElement.scrollTop + document.documentElement.clientHeight/2 - 100 +"px"
		}).addClass("waiting");
		jQuery(window.document.body).addClass("waiting");
	}
/*Added for Device Mgmt LTPC Popup*/
	function hideOverlayPopup(){
		jQuery("#overlayPopup").css({
			display:"none" 
		});
		jQuery("#processingHintPopup").css({
			display:"none"
		});
	}

    function getArg(url, arg) {
        var sValue=url.match(new RegExp("[\?\&]" + arg + "=([^\&]*)(\&?)","i"));
        if (sValue != null) {
            return decodeURI(sValue?sValue[1]:sValue);
        }
        return null;
    };
    
	jQuery(document).ready(function(){
		if(window.opener){
			window.opener.hideOverlay();
		}
		jQuery("form").each(function(){
		    var fun = this.onsubmit;
		    jQuery(this).submit(function(){
		        var toShowMask = true;
		        if(typeof fun =='function'){
		          toShowMask = fun(); 
		        }
		        if((typeof toShowMask=='boolean') && toShowMask ){
		        showOverlay();
		        }
		    });

		   
		});
		
	});
	
	 /*
	 * Generates new grid url after the grid is reset, or filter of the grid is changed.
	 * gridURL: the original grid url
	 * orderIndex: the index of column by which the grid will be sorted (from 0)
	 * direction: 'asc' or 'des'
	 * filterValues: new values of "filterCriterias"
	 */
	function updateGridURL(gridURL, orderIndex, direction, filterValues) {
	    if (direction != 'asc' && direction != 'des') {
	        alert('Invalid direction!');
	        return false;
	    }
	    if (orderIndex < 0) {
	        alert('Invalid orderIndex');
	        return false;
	    }
	    oldOrderBy = getArg(gridURL, 'orderBy');
	    oldDirection = getArg(gridURL, 'direction');
	    oldFilterValues = getArg(gridURL, 'filterCriterias');
	    if (oldOrderBy != null && oldOrderBy != '') {
	        gridURL = gridURL.replace('orderBy=' + oldOrderBy, 'orderBy=' + orderIndex);
	    } else {
	        gridURL = gridURL + '&orderBy=' + orderIndex;
	    }

	    if (oldDirection != null && oldDirection != '') {
	        gridURL = gridURL.replace('direction=' + oldDirection, 'direction=' + direction); 
	    } else {
	        gridURL = gridURL + '&direction=' + direction;
	    }

	    // don't need to deal with filterCriterias, if the grid has no filter
	    if (filterValues == null) {
	        return gridURL;
	    }
	    
	    if (oldFilterValues != null && oldFilterValues != '') {
	        gridURL = gridURL.replace('filterCriterias=' + oldFilterValues, 'filterCriterias=' + filterValues);         
	    } else {
	        gridURL = gridURL + '&filterCriterias=' + filterValues;
	    }
	    return gridURL;
	};
	
	

	function viewServiceHistoryPopup(title,activityId,serviceRequestId){
		callOmnitureAction('View Service History', 'Request Number');
		//showOverlayIE6();
		showSRPopupMethod(title,activityId,serviceRequestId);
		//bindingClosePopupIE6();
	}
	
	viewServiceHistoryFunction();
	var showSRPopup;
	function viewServiceHistoryFunction(){
	AUI().use('aui-base',
	'aui-io-plugin-deprecated',
	'liferay-util-window',
	function(A) {

		showSRPopup=Liferay.Util.Window.getWindow(
		{
		dialog: {
		centered: true,
		constrain2view: true,
		//cssClass: 'yourCSSclassName',
		modal: true,
		position: [-80,20],
		resizable: false,
		width: 950,
		height: 550,
		xy: [-80, 20],
		destroyOnClose: true,
		visible: false
		}
		}
		).plug(
		A.Plugin.IO,
		{
		autoLoad: false
		})



	});
	}
	
	var serviceHistoryUrl="<portlet:renderURL windowState='<%=LiferayWindowState.EXCLUSIVE.toString()%>'>"+
	   "<portlet:param name='action' value='showServiceHistoryDetailPage'/></portlet:renderURL>";
	   
		function showSRPopupMethod(title,activityId,serviceRequestId){
		serviceHistoryUrl=serviceHistoryUrl+"&activityId="+activityId+ "&timezoneOffset="+timezoneOffset + "&serviceRequestId="+serviceRequestId
		jQuery(window).scrollTop(0);
		showSRPopup.show();
		//jQuery(".aui button.close, .aui button.btn.close").hide();
		//showSRPopup.titleNode.html("<spring:message code='title.serviceRequestDetail'/><div class=\"close-btn\"><a href=\"javascript:closePopup();\"><img src=\"<html:imagesPath/>close.png\" width=\"27\" height=\"27\"></a></div>");
		showSRPopup.titleNode.html("<spring:message code='title.serviceRequestDetail'/>");
		showSRPopup.io.set('uri',serviceHistoryUrl);
		showSRPopup.io.start();
		
		}
	
	function showOverlayIE6(){
		if(jQuery.browser.msie && parseInt(jQuery.browser.version.major) <= 6 ){
			jQuery("BODY").append('<iframe id="lifeRayPopup_overlay" src="javascript:\'<html><\/html>\';" scrolling="no" frameborder="0" />');
			jQuery("#lifeRayPopup_overlay").css({
				position: 'absolute',
				zIndex: 300,
				top: '0px',
				left: '0px',
				width: '100%',
				height: jQuery(document).height(),
				background: '#FFF',
				opacity: .1
			});
		}
	}
	function hideOverlayIE6(){
		if(jQuery.browser.msie && parseInt(jQuery.browser.version.major) <= 6 ){
			jQuery("#lifeRayPopup_overlay").remove();
		}
	}
	function bindingClosePopupIE6(){
		if(jQuery.browser.msie && parseInt(jQuery.browser.version.major) <= 6 ){
			jQuery(".ui-dialog-titlebar-close").click(hideOverlayIE6);
		}
	}
	function escapeStringForNote(noteDetails){
		noteDetails = noteDetails.replace(/</g, "&lt;");
		noteDetails = noteDetails.replace(/>/g, "&gt;");
		noteDetails = noteDetails.replace(/\"/g, "&quot;");
		noteDetails = noteDetails.replace(/\r/g,"\\r");
		noteDetails = noteDetails.replace(/\n/g,"\\n");
		return noteDetails; 
	}
	function escapeStringForNotePopup(noteDetails){
		noteDetails = noteDetails.replace(/</g, "&lt;");
		noteDetails = noteDetails.replace(/>/g, "&gt;");
		noteDetails = noteDetails.replace(/\"/g, "&quot;");
		return noteDetails; 
	}
	function escapeStringForNoteSubRow(subRow){
		var subDetailStr = subRow.replace(/\r/g,"");
		subDetailStr = subDetailStr.replace(/\n/g,"<br>");
		subDetailStr = subDetailStr.replace(/\s/g,"&nbsp");
		return subDetailStr;
	}

	function retrieveErrorCode2(id){
		var errorCode1Key = document.getElementById("errorCode1_"+id).value;
		var selectedErrorCode1 = errorCode1Value[errorCode1Key];
		var select = document.getElementById("errorCode2_"+id);
		if(selectedErrorCode1 && selectedErrorCode1 != ""){
			document.getElementById("errorCode2_"+id).style.display="none";
			document.getElementById("errorCode2Loading_"+id).style.display="block";
			jQuery.ajax({
				url: '<portlet:resourceURL id="retrieveErrorCode2"></portlet:resourceURL>&errorCode1='+selectedErrorCode1+'&errorCode2Value=',
				success: function(data) {
					clearList(select);
					if((jQuery.trim(data)) == ""){
						select.options[0] = new Option("","");
						document.getElementById("errorCode2Loading_"+id).style.display="none";
						select.style.display="block";
						var msg = "<spring:message code='exception.claimCloseOut.retrieveErrorCode2'/>";
						popMessage(msg);
					}else{
						transToErrorCode2Options(select, data);
						document.getElementById("errorCode2Loading_"+id).style.display="none";
						select.style.display="block";
					}
		  		}
			});
		}else{
			select.style.display="none";
		}
			
	}
	function retrieveErrorCode2ForExsit(id, selectedErrorCode1, errorCode2Value){
		var select = document.getElementById("errorCode2_"+id);
		if(selectedErrorCode1 && selectedErrorCode1 != ""){
			jQuery.ajax({
				url: '<portlet:resourceURL id="retrieveErrorCode2"></portlet:resourceURL>&errorCode1='+selectedErrorCode1+'&errorCode2Value='+errorCode2Value,
				success: function(data) {
					clearList(select);
					if(data.trim() == ""){
						select.options[0] = new Option("","");
						select.options[1] = new Option(errorCode2Value,errorCode2Value);
						select.options[1].selected = true;
			  			var msg = "<spring:message code='exception.claimCloseOut.retrieveErrorCode2'/>";
						popMessage(msg);
					}else{
						transToErrorCode2Options(select, data);
					}
		  		}
			});	
		}else{
			select.style.display="none";
		}
	}

	function transToErrorCode2Options(select,errorCode2Map){
		var errorCode2 = new Array();
		select.options[0] = new Option("","");
		errorCode2 = errorCode2Map.split(",");
		for(var i=0; i<errorCode2.length-1; i++){
			if(errorCode2[i].split("=").length == 3){
				select.options[i+1] = new Option(errorCode2[i].split("=")[1],errorCode2[i].split("=")[0]);
				select.options[i+1].selected = true;
			}else{
				select.options[i+1] = new Option(errorCode2[i].split("=")[1],errorCode2[i].split("=")[0]);
			}
		}
	}

	function clearList(list){  
		while (list.options.length > 0){  
			list.options[0] = null;  
		}  
	}
	function validateEmail(ele){
		if(!validateEmailAddress(ele.value)){
			jAlert('<spring:message code="claim.errorMsg.invalidEmail"/>', "");
			ele.value = "";
		}
	}  
	function doAjaxPost(url, params, successCalBack, failBack, bannerId){
		dhtmlxAjax.post(url, params, function(loader){
			var status = parseLoader(loader);
			if(!status.succeed){
				if(typeof failBack == 'function'){
					var cancelPop = failBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}else{
					if(failBack){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				popMessage(status.message,status.group);
				hideSelect();	
			}else{
				if(typeof successCalBack == 'function'){
					var cancelPop = successCalBack(loader);
					if(cancelPop){
						hideOverlay();
						return;
					}
				}
				hideOverlay();
				showMessage(status.message, bannerId);
			}
		});
	}
	//added for trim() in IE
	if(navigator.appName.toString()=="Microsoft Internet Explorer"){
		if(typeof String.prototype.trim !== 'function') {
			  String.prototype.trim = function() {
			    return this.replace(/^\s+|\s+$/g, ''); 
			  };
			}	
	}
	
</script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<c:set var="included" value="true" scope="request"></c:set>
<c:set var="showDefaultError" value="true" scope="request"></c:set>
<c:set var="searchCriteriasSeperator" value="<%=com.lexmark.util.PaginationUtil.SEARCH_CRITERIAS_SEPERATOR %>" scope="request"></c:set>
</c:if>
<div id="overlay" style="display:none">
</div>
<div id="processingHint" tabindex="-1" style="display:none">
   &nbsp;&nbsp;<img class="margin-top-10px" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp; <spring:message code="processing"/><br>
</div>
<html:statusBanner id="${pageId}" showError="${showDefaultError}"/>