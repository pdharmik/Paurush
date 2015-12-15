<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="toggleView">
	<div id="breadCrumpMap"></div>
	<div id="toggleGridView" onClick="changeView('map')" style="display: none;"><div id="viewText"><span id="View_viewIcon" class="ui_icon_sprite map-view-icon"></span><spring:message code='lbs.label.mapview'/></div></div>
 	<div id="toggleMapsView" onClick="changeView('grid')" style="display: none;"><div id="viewText"><span id="View_viewIcon" class="ui_icon_sprite list_view-icon"></span><spring:message code='lbs.label.gridview'/></div></div>
</div>	
<form action="fleet-management" id="backFormMap" method="post">
	<input type="hidden" name="backJson" id="backJson"/>
</form>
 
 <script>
 /*Changes for CR 17284*/
	function changeView(param){
		//alert("Param:: "+ param);
		if(param == 'grid'){
			zoomProcessor.saveSession=true;
			zoomProcessor.postZoom();
					
		}else if(param == 'map'){
			var zoomInfo = '${zoomLevelInfo}';
			if(zoomInfo!=null && zoomInfo!=""){
				$('#backJson').val(zoomInfo);				
			}
			$('#backFormMap').submit();
			//window.location.href="/group/global-services/fleet-management";
		}
		
		
	}
/*End Change for CR 17284*/
	jQuery(document).ready(function() {
		//Added for lbs toggling view for device finder and fleet management tabs
		var currentURL = window.location.href;
		//alert("currentURL:: "+ currentURL);
		if(currentURL.indexOf('/device-finder') > -1){
			jQuery("#toggleGridView").hide();
			jQuery("#toggleMapsView").hide();
			jQuery("#subnavigation").hide();
			
		}	
		else if(currentURL.indexOf('/fleet-management') > -1){	
			jQuery("#toggleMapsView").show();
			jQuery("#toggleGridView").hide();
			jQuery("#subnavigation").hide();
		}else if(currentURL.indexOf('/grid-view') > -1){
			jQuery("#toggleMapsView").hide();
			jQuery("#toggleGridView").show();
			jQuery("#subnavigation").hide();
		}
	});
 </script>