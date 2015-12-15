package standard;

import org.apache.commons.codec.binary.Base64;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SAPToBinaryStream extends DefaultHandler {
    public static void parse(InputStream is, OutputStream os) throws SAXException, ParserConfigurationException, IOException {
        
    	new SAPToBinaryStream(is, os).parse();
    }

    private static final SAXParserFactory factory = SAXParserFactory.newInstance();
    InputStream in;
    OutputStream out;
    SAXParser parser = factory.newSAXParser();
    boolean inLine = false;
    StringBuffer data = new StringBuffer();

    private SAPToBinaryStream(InputStream is, OutputStream os) throws SAXException, ParserConfigurationException {
    	
    	this.in = is;
        out = os;
    }

    private void parse() throws IOException, SAXException {
    	
        parser.parse(in, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
    	if ("LINE".equalsIgnoreCase(qName)) {        	
            inLine = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	
        if (inLine) {
            try {
            	
                out.write(Base64.decodeBase64(data.toString()));
                data.delete(0, data.length());
            } catch (IOException e) {
                throw new SAXException("Base64 Decode Error", e);
            }
            inLine = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	
        if (inLine) {
            data.append(ch, start, length);
        }
    }
}
