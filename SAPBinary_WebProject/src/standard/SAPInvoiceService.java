package standard;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SAPInvoiceService {
    URL endpoint;
    String userid,password;
    public SAPInvoiceService(URL endpoint, String userid, String password) {
    	
        this.endpoint = endpoint;
        this.userid = userid;
        this.password = password;
    }

    public InputStream call(String billingDocumentId) throws IOException {
    	
        URLConnection connection = endpoint.openConnection();
        connection.setAllowUserInteraction(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.addRequestProperty("Content-Type","text/xml;charset=UTF-8");
        connection.addRequestProperty("SOAPAction","\"urn:sap-com:document:sap:rfc:functions:ZG_INVOICE_ARCH_DOC:APAR_EBPP_GET_SDINVOICE_ARCHIVRequest\"");
        connection.addRequestProperty("Authorization",formatAuthorization(userid,password));
        OutputStream os = connection.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        
        writer.write(formatBody(billingDocumentId));
        writer.close();
        return connection.getInputStream();
        
    }

    private String formatAuthorization(String userid, String password) throws UnsupportedEncodingException {
    	
    			//"Basic "+Base64.encodeBase64String((userid+":"+password).getBytes("UTF-8")));
        try {
        	return "Basic "+Base64.encodeBase64String((userid+":"+password).getBytes("UTF-8"));            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatBody(String billingDocumentId) {
    	
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:rfc:functions\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:APAR_EBPP_GET_SDINVOICE_ARCHIV>\n" +
                "         <I_BILLING_DOC>"+billingDocumentId+"</I_BILLING_DOC>\n" +
                "         <I_LANGUAGE_ISO></I_LANGUAGE_ISO>\n" +
                "         <I_PARTNER>\n" +
                "            <PARTNERTYPE></PARTNERTYPE>\n" +
                "            <PARTNERKEY></PARTNERKEY>\n" +
                "            <UNAME></UNAME>\n" +
                "            <AGENT_ACTIVE></AGENT_ACTIVE>\n" +
                "            <CC_ACTIVE></CC_ACTIVE>\n" +
                "            <DESCRIP_LONG></DESCRIP_LONG>\n" +
                "         </I_PARTNER>\n" +
                "         <T_MESSAGES>\n" +
                "            <item>\n" +
                "               <MSORT></MSORT>\n" +
                "               <MSGID></MSGID>\n" +
                "               <MSGTY></MSGTY>\n" +
                "               <MSGNO></MSGNO>\n" +
                "               <MSGV1></MSGV1>\n" +
                "               <MSGV2></MSGV2>\n" +
                "               <MSGV3></MSGV3>\n" +
                "               <MSGV4></MSGV4>\n" +
                "               <MSGPR></MSGPR>\n" +
                "               <MSGTX></MSGTX>\n" +
                "               <TABIX></TABIX>\n" +
                "            </item>\n" +
                "         </T_MESSAGES>\n" +
                "         <T_PDF>\n" +
                "            <item>\n" +
                "               <LINE></LINE>\n" +
                "            </item>\n" +
                "         </T_PDF>\n" +
                "      </urn:APAR_EBPP_GET_SDINVOICE_ARCHIV>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
