<%@ include file="/WEB-INF/jsp/include.jsp"%>
<table width="100%" border="0">
  <tr>
    <td class='orangeSectionTitles'><spring:message code="documentViewPage.configure.titile"/></td>
  </tr>
  <tr>
	<td class='description'><spring:message code="documentViewPage.configure.description" /></td>
  </tr>
  <tr>
    <td>
        <portlet:actionURL var="url">
        	<portlet:param name="action" value="saveCofiguration"/>
        </portlet:actionURL>
        <form:form action="${url}" >
        	<input type="text" name="path" value="${path}" size="50">
        	<button type="submit" class="button"><spring:message code="button.submit" /></button>
        </form:form>
    </td>
  </tr>
</table>