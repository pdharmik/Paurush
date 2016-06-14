 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <c:if test='${sessionScope.aribaParamMap["isKaiser"]=="true"}'>
 <div class="left-nav-inner">
<div class="left-nav-header">
<h3><spring:message code="products.certifiedProducts.header"/></h3>
</div>
	<ul id="navLinks">
		<li><a id="ncal_printer" href="#" title="NCAL HealthConnect Certified">NCAL HealthConnect Certified</a></li>
		<li><a id="scal_printer" href="#" title="SCAL HealthConnect Certified">SCAL HealthConnect Certified</a></li>
	</ul>

</div>
</c:if>
     
      <script>
      var objLinkProducts={id:"",partType:""};
      var printerObject={printerType:"",id:"printerProduct"};
      jQuery('#navLinks li a').click(function(){
    	  showHideDivs('printerProducts');
    	  $("#storeContent").hide();
    	  if($(this).attr('id')=="ncal_printer"){
    		  $('#pageTitle').html('NCAL HealthConnect Certified');
    	  }else{
    		  $('#pageTitle').html('SCAL HealthConnect Certified');
    	  }
      	  getDataForNCAL_SCAL({
      		  "cType":"printers",
      		  "certType":$(this).attr('id')
      	  });
    	  
  	  });
      
      </script>