<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="top-notification">
      <div id="slider"> <a href="#" class="control_next"><img src="<html:imagesPath/>arrow-right.jpg" width="7" height="14" alt="Arrow right" border="0"></a> 
      <a href="#" class="control_prev"><img src="<html:imagesPath/>arrow-left.jpg" width="7" height="14" alt="Arrow left" border="0"></a>
        <ul>
          <li><spring:message code="notifications.user.value"/></li>          
        </ul>
      </div>
    </div>