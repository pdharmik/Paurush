package com.lexmark.service.impl.real.util;

import static com.lexmark.util.LangUtil.isNotEmpty;
import static java.lang.String.format;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.reflect.FieldUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.amind.common.domain.BaseEntity;
import com.amind.common.domain.HierarchicalBaseEntity;
import com.amind.data.service.QueryObject;
import com.lexmark.util.LangUtil;
import com.lexmark.util.StringUtil;
import com.siebel.data.SiebelPropertySet;

/**
 * @author vpetruchok
 * @version 1.0, 2012-04-09
 */
public class DoFileUtil {

    private DoFileUtil() {
    }

    
    public static void checkDoFile(String doFilePath) throws Exception {
        File doFile = new File(doFilePath); 
        checkDoFile(doFile);
    }
    
    public static void checkDoFile(File doFile) throws Exception {
        System.out.println("= Checking " + doFile.getCanonicalPath());

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
        String beanId = (String) xpath.compile("/beans/bean/@id").evaluate(doc, XPathConstants.STRING);
        
        String packageName = (String) xpath.compile("/beans/bean/property[@name=\"packageName\"]/value").evaluate(doc, XPathConstants.STRING); 
        Class<?> doClass = Class.forName(packageName + "." + beanId);
        System.out.println(doClass);

        List<FieldEntry> fieldEntries = new ArrayList<FieldEntry>();
        XPathExpression expr2 = xpath.compile("/beans/bean/property[@name='doFieldNameToDsFieldNameMap']/map/entry");
        NodeList result2 = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < result2.getLength(); i++) {
            Element el = (Element) result2.item(i);
            String key = (String) xpath.compile("key/value").evaluate(el, XPathConstants.STRING);
            String value = (String) xpath.compile("value").evaluate(el, XPathConstants.STRING);
            fieldEntries.add(new FieldEntry(key, value));
        }
        
        checkClassType(doClass);
        checkFieldTypes(doClass, fieldEntries);
        checkDuplicates(fieldEntries, doFile.getName());
        compareFields(doClass, fieldEntries.toArray(new FieldEntry[fieldEntries.size()]));
    }

    private static void checkClassType(Class<?> doClass) {
        if (!BaseEntity.class.isAssignableFrom(doClass)) {
            String msg = format("Class `%s' is not derived from com.amind.common.domain.BaseEntity class" , doClass.getName());
            System.out.println("ERROR: " + msg);
            fail(msg);
        }
    }

    /**
     * @see com.amind.data.service.SiebelEAIDataServiceBase#fromPropertySetHierarchical(SiebelPropertySet, QueryObject, HierarchicalBaseEntity, boolean) 
     */
    private static void checkFieldTypes(Class<?> doClass, List<FieldEntry> fieldEntries) {
        for (FieldEntry fe : fieldEntries) {
            String fieldName = fe.getKey();
            Field field = FieldUtils.getField(doClass, fieldName, true);
             
            if (field == null) {
                String msg = format("No such field `%s.%s'" , doClass.getSimpleName(), fieldName);
                System.out.println("ERROR: " + msg);
                fail(msg);
            }
            Class<?> fieldClass = field.getType();
            if (fieldClass.isInterface()) {
                try {
                    fieldClass.newInstance();
                } catch (Exception ex) {
                    String msg = format("field=`%s', fieldClass=`%s': fieldClass is an interface or cannot be instantiated via no args public constuctor", fieldName, fieldClass);
                    System.out.println("ERROR: " + msg);
                    fail(msg);
                }
            }
        }
    }


    public static void compareFields(Class<?> class1, FieldEntry[] fieldEntries) throws Exception {

        Class<?>[] methArgsTypes = {};
        Object[] methArgs = {};
        Object obj = class1.newInstance();

        System.out.printf("== %s\n", class1.getSimpleName());
        for (FieldEntry fe : fieldEntries) {
            String fieldName = fe.getKey();
            if (StringUtil.isStringEmpty(fieldName)) {
                continue;
            }

            if (isMethodCallable("get" + titlecase(fieldName), methArgs, methArgsTypes, class1, obj)
                    || (isLogicalType(class1, fieldName) && isMethodCallable("is" + titlecase(fieldName), methArgs, methArgsTypes, class1, obj))
                    )
            {
                continue;
            }

            fail(String.format("%s: `%s' no such property", class1.getSimpleName(), fieldName));
        }
    }

    private static boolean isLogicalType(Class<?> class1, String fieldName) {
        Field f = FieldUtils.getField(class1, fieldName, true);
        if (f == null) {
           return false;
        }
        
        return f.getType().equals(Boolean.class) ||  f.getType().equals(Boolean.TYPE);
    }


    private static boolean isMethodCallable(String methName, Object[] methArgs, Class<?>[] methArgsTypes, Class<?> class1, Object obj)
            throws IllegalAccessException, InvocationTargetException {
        System.out.printf("processing %s() ...", methName);
        try {
            Method meth = class1.getMethod(methName, methArgsTypes);
            meth.invoke(obj, methArgs);
            System.out.printf("OK \n");
            return true;
        } catch (NoSuchMethodException ex) {
            System.out.printf("Failed (no such method) \n");
            return false;
        }
    }

    
     static void  checkDuplicates(List<FieldEntry> fieldEntries, String assocDoFile) {
         FieldEntry[] fieldEntriesArray = fieldEntries.toArray(new FieldEntry[fieldEntries.size()]);
         checkDuplicates(fieldEntriesArray, assocDoFile);
     }
    
    static void checkDuplicates(FieldEntry[] fieldEntries, String assocDoFile) {
        System.out.printf("== Checking for the field duplication \n");
        Set<String> domainNames = new HashSet<String>();
        Set<String> sblNames = new HashSet<String>();
        for (FieldEntry fe : fieldEntries) {
            String k = fe.getKey();
            String v = fe.getValue();

            if (isNotEmpty(k)) {
                boolean added = domainNames.add(k);
                if (!added) {
                    String msg = format("%s: the domain field `%s' is not unique", assocDoFile, k);
                    System.out.println(msg);
                    throw new AssertionError(msg);
                }
            }

            if (isNotEmpty(v)) {
                boolean added = sblNames.add(v);
                if (!added) {
                    System.out.printf("WARNING: %s: the siebel field `%s' is not unique\n", assocDoFile, v);
                }
            }
        }

    }

    static String titlecase(String s) {
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1);
        return s1.toUpperCase() + s2;
    }

    public static FieldEntry comment(String value) {
        return new FieldEntry(value);
    }

    public static FieldEntry xmltag(String key, String value) {
        return new FieldEntry(key, value);
    }

    public static class FieldEntry {
        private String key;
        private String value = "";
        private String comment;

        public FieldEntry(String comment) {
            super();
            if (hasValue(comment)) {
                this.comment = comment;
            }
        }

        public FieldEntry(String key, String value) {
            super();
            this.key = key;
            if (hasValue(value)) {
                this.value = value.trim();
            }
        }

        private static final String ENTRY_TAG_TMPL =
                "<entry>\n" +
                        "    <key><value>%key%</value></key>\n" +
                        "    <value>%value%</value>\n" +
                        "</entry>";

        public String toXmlTag() {
            StringBuilder sb = new StringBuilder();
            if (hasValue(comment)) {
                sb.append("\n");
                sb.append("<!--").append(comment).append("-->");
                sb.append("\n");
            }
            if (hasValue(key) || hasValue(value)) {
                sb.append(ENTRY_TAG_TMPL.replace("%key%", key).replace("%value%", value));
            }
            return sb.toString();
        }

        private boolean hasValue(String s) {
            return !StringUtil.isStringEmpty(s);
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String getComment() {
            return comment;
        }

        public String toJavaFieldDeclaration() {
            if (LangUtil.isEmpty(this.key) && LangUtil.isEmpty(this.comment)) {
                return "";
            }
            
            String s = "";
            if (LangUtil.isNotEmpty(this.comment)) {
                s += format("\n// %s", this.comment);
            }
            if (LangUtil.isNotEmpty(this.key)) {
                String type = "String";
                if (this.key.endsWith("Date") 
                      || this.key.endsWith("Timestamp")) {
                    type = "Date";
                } else if (this.key.endsWith("Flag")) {
                    type = "Boolean";
                }
                s +=  format("private %s %s;", type, this.key); 
            }
            
            return s; 
        }
    }
}
