package com.lexmark.services.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import com.lexmark.framework.logging.LEXLogger;


public class URLImageUtil {
	private static final String IMAGE_URL = "http://www1.lexmark.com/common/xml/";
	private static final String IMAGE_URL_LOCAL = "/docroot/liferay-portal-6.2/mediaxml/";
	private static final String IMAGE_NOT_FOUND = "Not found";
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(URLImageUtil.class);
	
	public static String getPartImage(String partNumber){
		String formImageURL = IMAGE_URL+partNumber.substring(0,3)+"/"+partNumber+".xml";
		String imageURL = "";
		try{
		URL url = new URL(formImageURL);
        URLConnection conn = url.openConnection();
        Product product = Product.parse(conn.getInputStream());
        Product.Media.Thumbnail thumbnail = product.media.thumbnail;
        imageURL = "http:"+thumbnail.src;
		}catch(FileNotFoundException e){
        	imageURL = IMAGE_NOT_FOUND;
        }
		catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("IO Exception "+e.getMessage());
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			imageURL = IMAGE_NOT_FOUND;
			LOGGER.debug("(Null PointerException "+e.getMessage());
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("(JAXB Exception "+e.getMessage());
		}
        return imageURL;
	}
	
	
	/**
	 * Performance changes for retreving image file from local in device finder
	 *
	 */
	public static String getPartImageFromLocal(String partNumber) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{

		String formImageURL = IMAGE_URL_LOCAL+partNumber.substring(0,3)+"/"+partNumber+".xml";
		String imageURL = "";
		File fXmlFile = new File(formImageURL);
		if(fXmlFile.exists()){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		NodeList nList = doc.getElementsByTagName("thumbnail");
		for (int temp = 0; temp < nList.getLength();temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				imageURL=eElement.getAttribute("src");			
			}
			break;
			
		}
		}else{
			imageURL=IMAGE_NOT_FOUND;
		}
        return imageURL;
	
		
	}
}
