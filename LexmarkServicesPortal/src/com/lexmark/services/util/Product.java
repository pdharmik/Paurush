package com.lexmark.services.util;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.*;
import javax.xml.transform.sax.SAXSource;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@XmlRootElement
public class Product {
    public static class Media {
        public static class Thumbnail {
            @XmlAttribute
            String src;
        }
        /**
         * 
         */
        @XmlElement
        public Thumbnail thumbnail;
    }
    /**
     * 
     */
    @XmlElement
    public Media media;
    
    private static final Unmarshaller UNMARSHALLER;
    static {
        try {
            UNMARSHALLER = JAXBContext.newInstance(Product.class).createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
/**
 * 
 * @param file 
 * @return Product 
 * @throws FileNotFoundException 
 * @throws JAXBException 
 */
    public static Product parse(InputStream file) throws FileNotFoundException, JAXBException {
        BufferedInputStream fis = new BufferedInputStream(file);
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            InputSource is = new InputSource(fis);
            XmlNSFilter filter = new XmlNSFilter();
            filter.setParent(reader);
            SAXSource source = new SAXSource(filter,is);
            return (Product) UNMARSHALLER.unmarshal(source);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
            	e.getMessage();
                //Must not be open?
            }
        }
    }
}
