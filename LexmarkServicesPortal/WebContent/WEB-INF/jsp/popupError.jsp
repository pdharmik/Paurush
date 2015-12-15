<%--
 * Copyright 2005-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
--%>

<%@ page contentType="text/html" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>

<style type="text/css"><%@ include file="../css/lexmark.css" %></style> 
<script type="text/javascript">
	DD_roundies.addRule('.button', '12px', true);
	DD_roundies.addRule('.realbutton', '12px', true);
	DD_roundies.addRule('.paginate_active', '12px', true);
	DD_roundies.addRule('.paginate_button', '12px', true);
</script>
<c:if test="${serviceSuccessMessages != null}">
	<ul class="serviceSuccess">
		<c:forEach items="${serviceSuccessMessages}" var="serviceSuccess" varStatus="loop">
			<li>${serviceSuccess}</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${serviceErrors != null}">
	<ul class="error">
		<c:forEach items="${serviceErrors}" var="serviceError" varStatus="loop">
			<li>${serviceError}</li>
		</c:forEach>
	</ul>
</c:if>

<div id="errorContent">
</div>

<script type='text/javascript'>
	var errorContent = window.parent.window.errorContent;
		jQuery('#errorContent').html(errorContent);

</script>
