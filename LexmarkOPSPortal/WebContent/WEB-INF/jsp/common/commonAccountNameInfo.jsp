<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import=" static com.lexmark.constants.LexmarkConstants.SERVICES_USER_COM" %>
<c:set var = "accountName" value = "${sessionScope.servicesUserCom.companyName }"/>
<c:if test="${not empty accountName }">
<h3><strong>&nbsp;&nbsp;&nbsp;${accountName }</strong></h3>
</c:if>

