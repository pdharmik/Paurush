<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<style type="text/css">
.popup-product-txt{
	position:relative;
}
</style>

<div id="learnMorePopUp" style="display:none">
                  
                  
                  <div id="popup-title" style="margin-top:20px !important;"><spring:message code="product.popupTitle.productSpecifications"/></div>
                  <div id="wrapper-popup">
                    <div id="accObj4" style="width:84%; float:right; height:auto;"></div>
                     
                  	
                  		<div id="learn-more-content">
                  		</div>
                    
                    	
                   
                    
                    </div> 
                    <div style="clear:both"></div>
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