<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<script type="text/javascript" src="<html:rootPath/>/js/dhtmlxaccordion.js"></script>
<style type="text/css"><%@ include file="/WEB-INF/css/dhtmlxaccordion.css" %></style>
<style type="text/css">
.popup-product-txt{
	position:relative;
}
</style>
<script>
$(document).ready(function(){
	$("#processingHint").hide();
	$("#overlay").css({"cursor":'default'});
	$(window).scrollTop(0);
});
</script>
<div id="learnMorePopUp" style="display:none">
                  
                  <!--HTML Popup-->
                  <div id="popup-title" style="margin-top:20px !important;"><spring:message code="product.popupTitle.productSpecifications"/></div>
                  <div id="wrapper-popup">
                    <div id="accObj4" style="width:84%; float:right; height:auto;"></div>
                    <c:choose>
                    <c:when test="${productImage ne null}">
                    <img alt="" src="${productImage}" height="250px" width="165px">
                    </c:when>
                    <c:otherwise>
                    <div class="popup-product-img" style="width:15%"><img src="<html:imagesPath/>popup-img.jpg" width="101" height="112" alt="Printer Image"></div>
                    </c:otherwise>
                    </c:choose>
                    <c:forEach items="${partNumber}" var="parts" varStatus="counter">
                    <%--   <c:forEach items="${learnMoreForm}" var="learnMore" varStatus="counter">--%>
                   <div style="width:100%; float:left; overflow:auto; height:100%;" id="accor${counter.index}" class="popup-product-txt">
                   <%--  <c:set var="markertingResult" value="${learnMore.marketing}" />
                   <c:set var="bulletResult" value="${learnMore.bullet}" />
                   <c:set var="techSpecResult" value="${learnMore.techSpec}" />--%>
                   <div class="loadingIcon"><img src="/LexmarkServicesPunchout/images/gridloading.gif" alt="Loading Image"></div>
                      <table border="0" cellspacing="5" cellpadding="0">
                      <tbody>
                      <tr>
                      <td class="strong" id="mrkt_desc${counter.index}">
                      </td>
                      </tr>
                      <%-- <tr>
                      <td class="strong">
                      ${markertingResult.mkt_desc}
                      </td>
                      </tr>--%>
                      </tbody>
                      </table>
                      </br>
                      </br>
                      <table style="width: 100%" border="0" cellspacing="5" cellpadding="0">
                      <tbody>
                      <tr>
                       <td class="strong"><spring:message code="product.learnMorePopUP.partId"/></td>
                      <td id="partId${counter.index}">
                      
                      </td>
                      </tr>
                      <tr>
                       <td class="strong" valign="top"><spring:message code="product.learnMorePopUp.PPMInformation"/></td>
                      <td id="ppnInfo${counter.index}">                      
                      </td>
                      </tr>
                      <tr>
                       <td class="strong"><spring:message code="product.learnMorePopup.catagory"/></td>
                      <td id="partCatagory${counter.index}">                     
                      </td>
                      </tr>
                      <tr>
                       <td class="strong"><spring:message code="product.learnMorePopUp.partName"/></td>
                      <td id="partName${counter.index}">                     
                      </td>
                      </tr>
                      <tr>
                       <td class="strong"><spring:message code="requestInfo.label.partNumber"/></td>
                      <td id="partNumber${counter.index}">                      
                      </td>
                      </tr>
                      </tbody>
                      </table>
                      </br>
                      </br>
                       <%--<div>
                     <ul>
                      	 <c:forEach items="${learnMore.bulletList}" var="bullets" varStatus="bulletsCounter">
                      	<li>
                      		<label>${bullets.value}</label>
                      	</li> 
                      	</c:forEach>
                      	</ul>
                      	</div>--%>
                      	 </br>
                      	<table style="width: 100%" border="0" cellspacing="5" cellpadding="0">
                      <tbody>	
                      <tr>
                       <td class="strong" valign="top" style="width: 20%"><spring:message code="serviceRequest.label.description"/>:</td>
                      <td id="partDesc${counter.index}">                     
                      </td>
                      </tr>
                      </tbody>
                      </table>
                  
                  </div>
                  </c:forEach>
                    
                    </div> 
                    <div style="clear:both"></div>
                    </div>
             <script>
            jQuery(document).ready(function(){
        			dialog=jQuery('#learnMorePopUp').dialog({
        				autoOpen: false,
        				title: 'Learn More',
        				modal: true,
        				draggable: false,
        				resizable: false,
        				position: 'center',
        				height: 'auto',
        				width: 940,
        				open: function(){	
        					jQuery('#learnMorePopUp').show();
        					
        				},
        				close: function(event,ui){
        					hideOverlay();
        					dialog=null;
        				jQuery('#learnMorePopUp').remove();
        					
        					}
        				
        				});
        			dialog.dialog('open');
        		
					openPopup();
                });
            function openPopup() {
        		myAcc4 = new dhtmlXAccordion({
        			parent: "accObj4",
        			icons_path: "../common/icons/",
        			multi_mode: true,
        			items: [<c:forEach items="${partNumber}" var="parts" varStatus="counter">
        					{id: "${counter.index}", text: '${parts}', height:350, open: false}
        			<c:if test="${counter.index < fn:length(partNumber) }">
        			,
					</c:if>
        		 </c:forEach>]
        		});
        		<c:forEach items="${partNumber}" var="parts" varStatus="counter">
        		myAcc4.cells("${counter.index}").attachObject("accor${counter.index}");
        		 </c:forEach>
        		 myAcc4.attachEvent("onActive", function(id, state){
        			 var parNum = "";
        			 var text = myAcc4.cells(id).getText();
        			 if(text.indexOf('-') >= 0){        				 
        				 parNum = text.substring(0, text.indexOf('-')); 
        				 parNum.trim();
        			 }
        			
        			  doAjaxForPartDetails(id,parNum);
        			});
        	}
        	
        	function closePopup(statusIndexAccor) {
        		<c:forEach items="${partNumber}" var="parts" varStatus="counter">
        		myAcc4.cells("${counter.index}").detachObject("accor${counter.index}");
        		 </c:forEach>
        	}
        	
        	function doAjaxForPartDetails(id,parNum){
        		 //showOverlay();        	
        		//var url = "https://www.lexmark.com/en_US/epg/products/28E0100.json";
        		//parNum="28E0100";
        		var url = "https://www.lexmark.com/en_US/epg/products/"+parNum.trim()+".json";
        		//$.getJSON(url,function(result){
        		//	document.getElementById('partId'+id).innerHTML = result.pid;
        		//	document.getElementById('partCatagory'+id).innerHTML = result.category;
        		//	document.getElementById('partName'+id).innerHTML = result.name;
        		//	document.getElementById('partNumber'+id).innerHTML = result.pn;
        		//	var ppnInfo = "<B>Print Resolution Black : </B>"+result.printResolutionBlack+ " </br><B>Print Resolution Color : </B>"+result.printResolutionColor;
        		//	document.getElementById('ppnInfo'+id).innerHTML = ppnInfo;
        		//	document.getElementById('partDesc'+id).innerHTML = result.description;
        		//	//hideOverlay();
        		//	$('#accor'+id).children('.loadingIcon').hide();
        		//});
        		
        		$.ajax({
        		    url: url,
        		    dataType: 'json',
        		    success: function( result ) {        	
        		      document.getElementById('partId'+id).innerHTML = result.pid;
              			document.getElementById('partCatagory'+id).innerHTML = result.category;
              			document.getElementById('partName'+id).innerHTML = result.name;
              			document.getElementById('partNumber'+id).innerHTML = result.pn;
              			var ppnInfo = "<B>Print Resolution Black : </B>"+result.printResolutionBlack+ " </br><B>Print Resolution Color : </B>"+result.printResolutionColor;
              			document.getElementById('ppnInfo'+id).innerHTML = ppnInfo;
              			document.getElementById('partDesc'+id).innerHTML = result.description;
              			$('#mrkt_desc'+id).html(result.marketing);
              			//hideOverlay();
              			$('#accor'+id).children('.loadingIcon').hide();
        		    },
        		    error: function( data ) {        		
        		      $('#accor'+id).children('.loadingIcon').hide();
        		    }
        		  });
        	}
        	
        	
        	
            
            
            </script>         