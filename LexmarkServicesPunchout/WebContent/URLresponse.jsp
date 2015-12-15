<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>Let send the processed URL to servlet</h4>
	<form action="http://localhost:8080/LexmarkPunchoutPortal/punchoutcatalog" name="cxmlForm2" method="post">
   	
   	<input type="button" value="Click this button" onclick="javascript:doSubmit();">
	</form>
	<script language="JavaScript" type="text/javascript">
	function doSubmit(){
		document.cxmlForm2.submit();
	}
	</script>
	
</body>
</html>