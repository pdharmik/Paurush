<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="main-wrap">
<%-- <link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" /> --%>
<div class="journal-content-article div-style44" >
<h1 ><spring:message code='documentViewPage.lhs.title'/></h1>
</div>
<div class="content">
<table width="800" height="600" border="0">
  <tr>
    <td width="25%" height="100%"  valign="top">
    	<%@ include file="/WEB-INF/jsp/documentManagement/user/documentCategoryTree.jsp"%>
    </td>
    <td width="75%" height="100%"  valign="top"><%@ include file="/WEB-INF/jsp/documentManagement/user/documentUserList.jsp"%></td>
  </tr> 
</table>
</div></div>