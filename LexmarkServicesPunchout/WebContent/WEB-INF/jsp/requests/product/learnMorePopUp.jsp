<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<script type="text/javascript" src="<html:rootPath/>/js/dhtmlxaccordion.js"></script>
<style type="text/css"><%@ include file="/WEB-INF/css/dhtmlxaccordion.css" %></style>
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
                   <c:forEach items="${learnMoreForm}" var="learnMore" varStatus="counter">
                   <div style="width:100%; float:left; overflow:auto; height:100%;" id="accor${counter.index}" class="popup-product-txt">
                   <c:set var="markertingResult" value="${learnMore.marketing}" />
                   <c:set var="bulletResult" value="${learnMore.bullet}" />
                   <c:set var="techSpecResult" value="${learnMore.techSpec}" />
                      <table border="0" cellspacing="5" cellpadding="0">
                      <tbody>
                      <tr>
                      <td class="strong">
                      ${markertingResult.description}
                      </td>
                      </tr>
                      <tr>
                      <td class="strong">
                      ${markertingResult.mkt_desc}
                      </td>
                      </tr>
                      </tbody>
                      </table>
                      </br>
                      </br>
                      <table style="width: 50%" border="0" cellspacing="5" cellpadding="0">
                      <tbody>
                      <tr>
                       <td class="strong" style="width: 25%"><spring:message code="product.learnMorePopUP.partId"/></td>
                      <td>
                      ${markertingResult.partId}
                      </td>
                      </tr>
                      <tr>
                       <td class="strong" style="width: 25%"><spring:message code="product.learnMorePopUp.PPMInformation"/></td>
                      <td>
                      ${markertingResult.ppmInfo}
                      </td>
                      </tr>
                      <tr>
                       <td class="strong" style="width: 25%"><spring:message code="product.learnMorePopup.catagory"/></td>
                      <td>
                      ${markertingResult.catagory}
                      </td>
                      </tr>
                      <tr>
                       <td class="strong" style="width: 25%"><spring:message code="product.learnMorePopUp.partName"/></td>
                      <td>
                      ${markertingResult.partName}
                      </td>
                      </tr>
                      <tr>
                       <td class="strong" style="width: 25%"><spring:message code="requestInfo.label.partNumber"/></td>
                      <td>
                      ${markertingResult.partNumber}
                      </td>
                      </tr>
                      </tbody>
                      </table>
                      </br>
                      </br>
                      <div>
                      <ul>
                      <c:forEach items="${learnMore.bulletList}" var="bullets" varStatus="counter">
                      <li>
                      	<label>${bullets.value}</label>
                      	</li> 
                      	</c:forEach>
                      	</ul>
                      	</div>
                      	 </br>
                      	<table style="width: 50%" border="0" cellspacing="5" cellpadding="0">
                      <tbody>	
                      <tr>
                       <td class="strong" style="width: 40%"> ${techSpecResult.attribute}:</td>
                      <td>
                      ${techSpecResult.value}
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
        	}
        	
        	function closePopup(statusIndexAccor) {
        		<c:forEach items="${partNumber}" var="parts" varStatus="counter">
        		myAcc4.cells("${counter.index}").detachObject("accor${counter.index}");
        		 </c:forEach>
        	}
        	
            
            
            </script>         