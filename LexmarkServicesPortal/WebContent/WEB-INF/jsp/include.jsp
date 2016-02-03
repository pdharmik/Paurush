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

<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/jquery/jquery-ui.css"/>
<% pageContext.setAttribute("newLineChar", "\n"); %>


<%@page import="com.lexmark.services.util.PaginationUtil"%><c:set var="regional" value="${pageContext.request.locale.language}"/>
<c:set var="language" value="${pageContext.request.locale.language}"/>
<c:set var="country" value="${pageContext.request.locale.country}"/>
<c:if test="${language == 'zh'}">
      <c:set var="regional" value="${language}-${country}"/>
</c:if>
<c:set var="showDefaultError" value="false" scope="request"></c:set>
<c:if test="${not included}">

<script type="text/javascript"><%@ include file="../../js/validation.js"%></script>
<script type="text/javascript"><%@ include file="../../js/reskin.js"%></script>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/lexmark.css"/>
<script type="text/javascript">
var isCatalogPage="false";
var isHardwarePage="false";

var offsetMinute = new Date().getTimezoneOffset();
var timezoneOffset = (offsetMinute/60);//It will make it positive value


	var defaultBannerId = null;
	function showMessage(msg, bannerId, keepOld){
		showHeaderMessage(true, msg, bannerId, keepOld);
	}
	function showError(msg, bannerId, keepOld){
		showHeaderMessage(false, msg,bannerId, keepOld);
	}
	function popMessage(msg, title){
		if(!title){
			title = "<spring:message code='exception.generalError.title'/>";
		}
		dialog=jQuery('#reportPopup').dialog({
			autoOpen: false,
			title: title,
			modal: true,
			draggable: false,
			resizable: false,
			position: [450,150],
			height: 150,
			width: 520,
			xy: [100, 100],
			
			open: function(){	
				jQuery('#reportPopupSpan').html(msg);
			},
			close: function(event,ui){
				showSelect();
				dialog.dialog('destroy');					 
				dialog=null;
			}
			});
		
		dialog.dialog('open');
	
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
		var text = document.createTextNode(msg);

		li.appendChild(text);
		li.className = msgClassName;
		<%--Changes MPS 2.1    --%>
		if(container!=null){
		<%--Ends Changes MPS 2.1    --%>
			container.appendChild(li);
		}
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
				loader.data = eval(returnDataNode.nodeValue);
			}
			var group = groupNode ? groupNode.nodeValue : null;
			return {succeed : "true" == statusAtt.nodeValue, message:message, group:group};
		}
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
		});
	}

	function doAjaxPopup(url, successCalBack, failBack, bannerId){
		//alert("in doajax url="+url);		
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
				<%--Changes MPS 2.1    --%>
				showError(status.message,bannerId);
				<%--Ends Changes MPS 2.1    --%>
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
				<%--  alert("before setting msg banner"); --%>
				hideOverlay();
				<%--//alert("status.message="+status.message+"bannerId="+bannerId);--%>
				<%--Changes MPS 2.1    --%>
				showMessage(status.message, bannerId);
				<%-- Ends Changes MPS 2.1    --%>
				<%--jQuery('#msgBanner').show();--%>
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
/*Edited for UI modification on July05, 2013 by Pranav*/
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
			top: document.documentElement.scrollTop + document.body.scrollTop + document.documentElement.clientHeight/2 - 50 +"px"
		}).addClass("waiting").html('&nbsp;&nbsp;<img style="margin-top: 10px;" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp;<spring:message code="processing"/><br>');
		jQuery(window.document.body).addClass("waiting");
	}
	function showOverlay2(){
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
			top: document.documentElement.clientHeight/2 - 50 +"px"
		}).addClass("waiting").html('&nbsp;&nbsp;<img style="margin-top: 10px;" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp;<spring:message code="processing"/><br>');
		jQuery(window.document.body).addClass("waiting");
	}
	function hideOverlay(){
		jQuery("#overlay").css({
			display:"none" 
		});
		jQuery("#processingHint").css({
			display:"none"
		}).html('');
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
	
	/***	var foo = window.open;
	window.open = function(){
		showOverlay();
		sh=setInterval(function(){
			if(!window.opened){
				hideOverlay();
			}
		},1000);
		window.opened = foo(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]);
	}***/
	
	
	 /** Function added, to invoke Multiple Request Controller **/
    function forwardToTemplateSRpage(requestCategory,prevSrNumber)
    {
        //alert('in forward to template srpage');
    	if(requestCategory.length == 0 || requestCategory.indexOf('_')<1){
    		//alert('Wrong requestCategory:'+requestCategory);
    		return;
    	}
    	
    	var reqType = requestCategory.substring(0, requestCategory.indexOf('_'));
    	var reqSubType = requestCategory.substring(requestCategory.indexOf('_')+1,requestCategory.length );
    	
	
    	//var actionValue=dropdwnTask+dropdwnNoOfAsset;
    	
    	var actUrl="<portlet:renderURL><portlet:param name='action' value='showManageTemplateSRPage'></portlet:param></portlet:renderURL>";
    	
    	actUrl=actUrl+ "&requestType="+reqType+"&requestSubType="+reqSubType+"&prevSrNumber="+prevSrNumber;
    	//alert(actUrl);
    	window.location.href=actUrl;
    	//window.location.href=renderUrl;
    	/* code ends */
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
<c:set var="included" value="true" scope="request"></c:set>
<c:set var="showDefaultError" value="true" scope="request"></c:set>
<c:set var="searchCriteriasSeperator" value="<%=com.lexmark.services.util.PaginationUtil.SEARCH_CRITERIAS_SEPERATOR %>" scope="request"></c:set>
</c:if>
<div id="overlay" style="display:none">
</div>
<div id="processingHint" tabindex="-1" >
   &nbsp;&nbsp;<img class="margin-top-10px" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp;<spring:message code="processing"/><br>
</div>
<div id="overlayPopup" style="display:none">
</div>
<div id="processingHintPopup" tabindex="-1" style="display:none">
   &nbsp;&nbsp;<img class="margin-top-10px" src="<html:imagesPath/>loading-icon.gif">&nbsp;&nbsp; <spring:message code="processing"/><br>
</div>
<div id="reportPopup" style="display:none"><span id="reportPopupSpan" style="font-weight: bold"></span></div>
<html:statusBanner id="${pageId}" showError="${showDefaultError}"/>

