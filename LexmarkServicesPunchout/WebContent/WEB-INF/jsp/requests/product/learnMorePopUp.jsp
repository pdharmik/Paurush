<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<style type="text/css">
.popup-product-txt{
	position:relative;
}
</style>

<div id="learnMorePopUp" class="noDisplay">
                  
                  
                  <div id="popup-title" class="marginT20px"><spring:message code="product.popupTitle.productSpecifications"/></div>
                  <div id="wrapper-popup">
                    <div id="accObj4"></div>
                     
                  	
                  		<div id="learn-more-content">
                  		</div>
                    <div align="center" id="learn-more-popup-loading-img">
						<img src="/lexmark-punchout-theme/images/custom/loading_big.gif"><br><br><br> 
					</div>
                    	
                   
                    
                    </div> 
                    <div class="clearBoth"></div>
</div>
             <script>
             var dialogLearnMore;
            jQuery(document).ready(function(){
            	dialogLearnMore=jQuery('#learnMorePopUp').dialog({
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
        					
        				}
        				
        				});
        			
        		
					
                });
 
            </script>         