 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 <c:if test="${acntType eq 'KAISER'}">
 <div class="left-nav-inner">
<div class="left-nav-header">
<h3><spring:message code="products.certifiedProducts.header"/></h3>
</div>
	<ul id="navLinks">
		<li><a id="ncal_printer" href="#" title="NCAL HealthConnect Certified">NCAL HealthConnect Certified</a></li>
		<li><a id="scal_printer" href="#" title="SCAL HealthConnect Certified">SCAL HealthConnect Certified</a></li>
	</ul>
<!--<ul id="navLinks">
        <c:forEach items="${accountList}" var="account">
        	<c:if test="${account.contractName ne 'Base'}">
        	<li><a id="${account.contractNumber}" href="#" title="${account.contractName}">${account.contractName}</a></li>
        	</c:if>
          
        
        </c:forEach>
        </ul>
-->
</div>
</c:if>
     
      <script>
      var objLinkProducts={id:"",partType:""};
      var printerObject={printerType:"",id:"printerProduct"};
      jQuery('#navLinks li a').click(function(){
    	  objLinkProducts={id:"",partType:"",certType:"",isPrinter:""};
    	  objLinkProducts.id="printerProduct";
    	  //objLinkProducts.contractNumber=jQuery(this).attr('id');
    	  objLinkProducts.certType=jQuery(this).attr('id');
    	  global_click_msgs.clickedFrom="certProducts";//defined in rightNavHome.jsp
      	  calledFromLEftNav(objLinkProducts.id);
  	  });
      
      </script>