<%@page import="java.net.URLDecoder"%>
<%@ page language="java" errorPage="error.jsp" import="java.util.*,
                                                       org.apache.logging.log4j.Logger,
                                                       org.apache.logging.log4j.LogManager"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.xml.parsers.*"%>
<%@ page import="org.xml.sax.*"%>
<%@ page import="org.apache.xml.serialize.OutputFormat"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="org.apache.xml.serialize.XMLSerializer"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="java.util.Enumeration"%>
<%! private static Logger log = LogManager.getLogger("punchout.jsp");%>
<%
// 	System.out.println("jsp rendered..first line");
	 String rsp = "<cXML version=\"1.2.024\">\n"
			+ "<PunchOutSetupRequest operation=\"create\">\n" 
			+ "<BuyerCookie>MPS</BuyerCookie>\n" 
			+ "</PunchOutSetupRequest>\n" 
			+ "<BrowserFormPost>\n" + "<URL>URL123456</URL>" + "</BrowserFormPost>\n"
			+ "<From>\n" + "<Credential>\n"	+ "<Identity>AN01000008546</Identity>" + "</Credential>\n" + "</From>\n"
			+ "<Sender>\n" + "<Credential>\n"	+ "<Identity>UYTERIO</Identity>" + "<SharedSecret>BCDWHFEUH</SharedSecret>" + "</Credential>\n" + "</Sender>\n"
			+ "<ItemID>\n" + "<SupplierPartAuxiliaryID>SUP324454645</SupplierPartAuxiliaryID>" + "</ItemID>\n"
			+ "</cXML>"; 
				
			
     //String rsp="test";
   /* try {

	   /* DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document document =  builder.parse(new InputSource(new StringReader(rsp)));

      //Serialize DOM
      OutputFormat format = new OutputFormat(document);
      format.setDoctype(null, "http://xml.cxml.org/schemas/cXML/1.2.009/cXML.dtd");
      //format.setEncoding("UTF-8");
      format.setEncoding("ISO-8859-1");
      format.setVersion("1.0");
      StringWriter stringOut = new StringWriter();
      XMLSerializer serial = new XMLSerializer(stringOut, format);
      serial.serialize(document);
      log.debug("cxml=\n"+stringOut.toString());
      
      String encodedText = new String(Base64.encodeBase64(stringOut.toString().getBytes("UTF8")));
      String decodedText = new String(Base64.decodeBase64(encodedText.getBytes("UTF8")));
     
      String msg = encodedText != null ? encodedText : "*****encodedText text is "+encodedText+"****";
	  log.debug(msg);
	  
  }
   catch (Exception ex) {
      ex.printStackTrace();
      log.error(ex.getMessage());
      response.setStatus(500);
   } */
//    System.out.println("Jsp ended.....!!!!");
%>
<form action="http://localhost:8080/LexmarkPunchoutPortal/punchout" name="cxmlForm" method="post">
   <input type="hidden" name="requestXML" value='<%=rsp%>'/>
</form>
<script language="JavaScript" type="text/javascript">
document.cxmlForm.submit();
</script>
