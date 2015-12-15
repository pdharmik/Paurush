<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="userAuthorization"/></title>
</head>
<%
String jSessionNum = request.getParameter("jsession_num");
String operId = request.getParameter("operid");
String type ="default";
if(request.getParameter("type")!=null){
	type = request.getParameter("type");
}
String url = "/LexmarkServicesCHL/accountCHLViewIframe.jsp?operid=" + operId + "&type=" + type +"&jsession_num=" + jSessionNum;

%>
<iframe src ="<%=url %>" width="100%" height="350">
  <p>Your browser does not support iframes.</p>
</iframe>