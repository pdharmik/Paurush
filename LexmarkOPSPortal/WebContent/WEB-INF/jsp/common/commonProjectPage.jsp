<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="portletBlock" id="projectBlock" style="display:none;">
<div class="columnsTwo">
              <div class="infoBox columnInner rounded shadow">                	

                <h4><spring:message code="ops.heading.project"/></h4>             	
				<div id="projectDetails" align="left" >
                    	<form:checkbox  id="projectDetailsCheckBox" path="serviceRequest.project"  onclick="projectDetails()"/>
						<spring:message code="ops.text.project"/>
						<br/>
                    </div>
				
                    
				<!-- Project Section -->
				<div id="project" style="display:none;">
                <ul class="form wordBreak">
                
                  <li>
                    <label><spring:message code="requestInfo.label.projectName"/></label>

                    <span><form:input id="projectName" path="serviceRequest.projectName" maxlength="64"/></span></li>
                  <li>
                    <label><spring:message code="requestInfo.label.projectPhase"/></label>
                     <span><form:input id="projectPhase" path="serviceRequest.projectPhase" maxlength="64"/></span></li>
                  
                   
                                     
                </ul>
                </div>
                
					
              </div>
              
              
			  
			  
            </div>

</div>
<script>
jQuery(document).ready(function() {
	
			if($("#projectDetailsCheckBox").is(':checked')){
		
		$("#projectDetailsCheckBox").prop('checked',true).trigger("click");
		
	}
});



function projectDetails(){
	
	
	if($("#projectDetailsCheckBox").is(':checked')){
	    $("#project").show();  // checked
	    $("#projectDetailsCheckBox").val(true);
	}
	else{
	    $("#project").hide();  // unchecked
	    
	    $("#projectDetailsCheckBox").val(false);
	  
	}
}
</script>