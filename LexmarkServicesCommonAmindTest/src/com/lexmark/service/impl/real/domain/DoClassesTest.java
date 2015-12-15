package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-05
 */
public class DoClassesTest {

    @Test
    public void checkDoMappingsAll() throws Exception {
        List<File> doFiles = parseDoFiles("do-mappings.xml");
        for (File doFile : doFiles) {
            checkDoFile(doFile);
        }
    }

    @Test
    public void checkDoMappings() throws Exception {
        String[] files = {
                "contactService/do-accountcontactdo-mapping.xml",
                "deviceService/do-assetFavorites-mapping.xml",
                "partnerService/do-partneractivitydetail-mapping.xml"
        };
        for (String f : files) {
            checkDoFile(new File("../LexmarkServicesCommon/src/DoMappings/", f));
        }
    }

    @Test
    public void printDoMappingFiles() throws Exception {
        List<File> doFiles = parseDoFiles("do-mappings.xml");
        for (File doFile : doFiles) {
//            System.out.printf("\"%s\",\n", doFile.getName());
            System.out.printf("\"%s\",\n", doFile.getPath());
        }
    }

    private List<File> parseDoFiles(String string) throws Exception {
        File dir = new File("../LexmarkServicesCommon/src/DoMappings/");
        File doFile = new File(dir, "do-mappings.xml");
        System.out.println(doFile.getCanonicalPath());

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        builder.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                InputSource is = new InputSource(new StringReader(""));
                return is;
            }
        });
        Document doc = builder.parse(doFile);
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("/beans/import");
        NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        List<File> files = new ArrayList<File>();
        for (int i = 0; i < result.getLength(); i++) {
            Element el = (Element) result.item(i);
            String resource = el.getAttribute("resource");
            File resourceFile = new File(dir, resource);
            files.add(resourceFile);
        }
        return files;
    }

    @Test
    public void checkAmindDoClassesSerialization() throws Exception {
        checkSerializable("../LexmarkServicesCommon/src/com/lexmark/service/impl/real/domain");
    }

    @Test
    public void checkApiDomainClassesSerialization() throws Exception {
        checkSerializable("../LexmarkServicesCommon/src/com/lexmark/domain");
    }
    
    @Test
    public void checkApiContractClassesSerialization() throws Exception {
        checkSerializable("../LexmarkServicesCommon/src/com/lexmark/contract");
    } 
    
    @Test
    public void checkApiResultClassesSerialization() throws Exception {
        checkSerializable("../LexmarkServicesCommon/src/com/lexmark/result");
    }  

    private void checkSerializable(String rootPath) throws Exception {
        int serializableCount = 0;
        int nonSerializableCount = 0;
        for (File f : listJavaFiles(rootPath)) {
            String className = f.getName().substring(0, f.getName().length() - ".java".length());
            String fcn =  toFcn(f);
            Class<?> cl = Class.forName(fcn);
            if (cl.isInterface()) {
                System.out.println("skip " + cl);
                continue;
            }
            
            if (Serializable.class.isAssignableFrom(cl)) {
//                System.out.println("Serializable: " + cl);
                serializableCount++;
            } else {
                System.out.println("WARNING: non-serializable " + cl);
                nonSerializableCount++;
            }
        }

        System.out.println("==============");
        System.out.printf("Serializable classes count = %s\n", serializableCount);
        System.out.printf("Non Serializable classes count = %s\n", nonSerializableCount);
        System.out.println("==============");
        
        if (nonSerializableCount >  0) {
           fail("There are non-serializable classses: see console log for more detail.");
        }
    }
    
    private List<File> listJavaFiles(String rootPath) {
        List<File> result = new ArrayList<File>();
        listFiles(new File(rootPath), result);
        return result;
    }
    
    private void listFiles(File startDirecotory, List<File> result) {
        for (File f : startDirecotory.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.getName().endsWith(".java") || pathname.isDirectory();
                        }
                    })) 
        {
            if (f.isFile()) {
                result.add(f);
            } else if (f.isDirectory()) {
                listFiles(f, result);
            } else {
                // skip, no actions
            }
        }
    }

    private String toFcn(File f) throws IOException {
        String s = f.getCanonicalPath();
        s = s.replace(File.separatorChar, '.');
        s = s.split("src.")[1];
        s = s.substring(0, s.length() - ".java".length());
        return s;
    }

    @Test
    public void testStringSplit() throws Exception {
        String[] a = "../LexmarkServicesCommon/src/com/lexmark/service/impl/real/domain".split("src/");
        for (String s : a) {
            System.out.println(s);
        }
    }
    
}
