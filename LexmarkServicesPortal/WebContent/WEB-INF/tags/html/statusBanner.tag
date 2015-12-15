<%@ tag isELIgnored="false" body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="id" description="" required="false" type="java.lang.String" %>
<%@ attribute name="showError" description="" required="false" type="java.lang.Boolean" %>
<div>
	<ul class="serviceSuccess" id="message_banner_${id}">
		<c:if test="${showError}">
			<c:forEach items="${serviceSuccessMessages}" var="serviceSuccess" varStatus="loop">
				<li>${serviceSuccess}</li>
			</c:forEach>
		</c:if>	
	</ul>
	<ul class="serviceError" id="error_banner_${id}">
		<c:if test="${showError}">
			<c:forEach items="${serviceErrors}" var="serviceError" varStatus="loop">
				<li>${serviceError}</li>
			</c:forEach>
		</c:if>
	</ul>
</div>