package com.lexmark.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBException;

import com.lexmark.framework.logging.LEXLogger;

public class ImageUtil {
	private static final String IMAGE_URL = "http://www1.lexmark.com/common/xml/";
	//private static final String IMAGE_URL_LOCAL = "/docroot/liferay-portal-5.2.3/mediaxml/";
	private static final String IMAGE_NOT_FOUND = "Not found";
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ImageUtil.class);
	
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
}
