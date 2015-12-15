package com.lexmark.service.impl.real;

import static java.lang.String.format;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.reflect.FieldUtils;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.common.service.SpringDomainObjMappingService;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.DomainObjBrokerDao;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.repository.domain.SiebelICFieldDefinition;
import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.api.SecureContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.service.impl.real.domain.AccountAssetDetailDo;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, Mar 20, 2012
 */
public class MiscTest {

    // @Test
    public void test() {
        fail("Not yet implemented");
    }

    @Test
    public void printAllFields() {
        Class<?> cl = AssetBase.class;
        while (cl != null) {
            printDeclaredFields(cl);
            cl = cl.getSuperclass();
        }
    }

    @Test
    public void showStructureOfClass_asc() throws Exception {
        showStructure(AssetBase.class);
    }

    static class Parent {
        int id = 1;

    }

    static class Child extends Parent {
        int id = 2;
    }

    @Test
    public void showStructureOfObject() throws Exception {
        AssetBase ab = new AssetBase();
        ab.setAccountTransFlag("true");
        // showStructure(ab);
        showStructure(new Child());
    }

    public static void showStructure(Class<?> cl) {
        if (cl == null) {
            return;
        } else {
            showStructure(cl.getSuperclass());
            printDeclaredFields(cl);
        }
    }

    public static void showStructure(Object obj) {
        try {
            _showStructure(obj.getClass(), obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void _showStructure(Class<?> cl, Object obj) throws Exception {
        if (cl == null) {
            return;
        } else {
            _showStructure(cl.getSuperclass(), obj);
            printDeclaredFields(cl, obj);
        }
    }

    public static void printDeclaredFields(Class<?> cl) {
        System.out.println(cl);
        TreeSet<String> fiedlnames = new TreeSet<String>(); // to view fields
                                                            // sorted
        for (Field f : cl.getDeclaredFields()) {
            fiedlnames.add(f.getName());
        }
        for (String fieldname : fiedlnames) {
            System.out.println("\t" + fieldname);
        }
    }

    public static void printDeclaredFields(Class<?> cl, Object obj) throws IllegalArgumentException, IllegalAccessException {
        System.out.println(cl);
        TreeMap<String, Field> fields = new TreeMap<String, Field>(); // to view
                                                                      // fields
                                                                      // sorted
        for (Field f : cl.getDeclaredFields()) {
            fields.put(f.getName(), f);
        }
        for (Map.Entry<String, Field> e : fields.entrySet()) {
            Field f = e.getValue();
            f.setAccessible(true);
            System.out.printf("\t%s=%s\n", f.getName(), f.get(obj));
        }
    }

    @Test
    public void reflectionMethodCall() throws Exception {
        AccountAssetDetailDo obj = new AccountAssetDetailDo();
        String id = "_id_";
        obj.setId(id);
        Method meth = obj.getClass().getMethod("getId", new Class[] {});
        Object result = meth.invoke(obj, new Object[] {});
        assertEquals(id, result);
    }

    @Test
    public void colletionsUnmodifiableMap() throws Exception {
        Map<Object, Object> m = new HashMap<Object, Object>();
        m.put(1, 1);
        m.put(2, 2);
        Map<Object, Object> m2 = Collections.unmodifiableMap(m);
        try {
            m2.put(3, 3); // adding new entry
            fail();
        } catch (UnsupportedOperationException ok) {
        }

        try {
            m2.put(1, 11); // changing existing entry
            fail();
        } catch (UnsupportedOperationException ok) {
        }
    }

    
    /**
     * 
     * @see #toMap(Object...)
     */
    public static Map<?, ?> newHashMap(Object... keysAndValues) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int i = 0; i < keysAndValues.length - 1; i = i + 2) {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];
            map.put(key, value);
        }
        return map;
    }
    
    /**
     *  Creates {@linkplain java.util.Map} object.
     *  
     *  @param keysAndValues sequence of key/value pairs in form: key1, value1, key2, value2 ... keyN, valueN
     */
    public static <K, V> Map<K, V> toMap(Object... keysAndValues) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int i = 0; i < keysAndValues.length - 1; i = i + 2) {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];
            map.put(key, value);
        }
        return (Map<K, V>) map;
    }
    
    
    
    @SuppressWarnings("unchecked")
    private static Map<String, String> stringMap(String... keysAndValues) {
        return (Map<String, String>) MiscTest.newHashMap(keysAndValues);
    }    

    @Test
    public void testNewHashMap() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        assertEquals(m, newHashMap());
        assertEquals(m, newHashMap("k1"));
        m.put("k1", "v1");
        assertEquals(m, newHashMap("k1", "v1"));
        m.put("k2", "v2");
        assertEquals(m, newHashMap("k1", "v1", "k2", "v2"));
        m.put("k3", "v3");
        assertEquals(m, newHashMap("k1", "v1", "k2", "v2", "k3", "v3"));
    }
    
    @Test
    public void testToMap() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        assertEquals(m, toMap());
        assertEquals(m, toMap("k1"));
        m.put("k1", "v1");
        assertEquals(m, toMap("k1", "v1"));
        m.put("k2", "v2");
        assertEquals(m, toMap("k1", "v1", "k2", "v2"));
        m.put("k3", "v3");
        assertEquals(m, toMap("k1", "v1", "k2", "v2", "k3", "v3"));
    }    
    
    
    @Test
    public void reflectionReadField() throws Exception {
        AccountAssetDetailDo obj = new AccountAssetDetailDo();
        String id = "_id_";
        obj.setId(id);
        long t0 = System.nanoTime();
        Object val = FieldUtils.readField(obj, "id", true);
        long t1 = System.nanoTime();
        Object val2 = obj.getId();
        long t2 = System.nanoTime();
        double nanosInMillis = 1000000.0;
        System.out.printf("(t1 - t0) = %s ms., (t2 - t1) = %s ms. \n", (t1 - t0)/nanosInMillis, (t2 -t1)/nanosInMillis); 
        assertEquals(id, val);
        assertEquals(id, val2);
    }
    
    
    
    public static void checkFilterResult(Map<String, Object> filterSpec, String filterCriteria, Object actualValue, boolean caseSensitiveMode) {
        Object fuzzyFilterValue = filterSpec.get(filterCriteria);
        String substring = (fuzzyFilterValue == null) ? null : fuzzyFilterValue.toString();
        String string = (actualValue == null) ? null : actualValue.toString();
        
        if (substring == null) {
            return;
        }
        
//        if (substring == null && string == null) {
//            return;
//        }
        if (substring == null || string == null) {
            fail(String.format("filter criteria=`%s': `%s' (actual value)  does not contain `%s' (fuzzy filter value)", filterCriteria,
                    actualValue, fuzzyFilterValue));
        }
        if (!caseSensitiveMode) {
            substring = substring.toLowerCase();
            string = string.toLowerCase();
        }
        if (!string.contains(substring)) {
            fail(String.format("filter criteria=`%s', case sensitive mode=`%s': `%s' (actual value)  does not contain `%s' (fuzzy filter value)", filterCriteria,
                   caseSensitiveMode, actualValue, fuzzyFilterValue ));
        }
    }    
    
    public static void checkFilterResult(Map<String, Object> filterSpec, String filterCriteria, Object actualValue) {
        checkFilterResult(filterSpec, filterCriteria, actualValue, false);
    }    
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> list(T... args) {
        if (args.length == 0) {
            return Collections.EMPTY_LIST;
        }
        List<T> result = new ArrayList<T>(); 
        for (T a: args) {
           result.add(a); 
        }
        return result;
    }
    
    
    public static void printIcFieldDefinitions(IDataManager dataManager, String objectName, String objectComponent) {
        DomainObjBrokerDao.setDomainObjMappingService(new SpringDomainObjMappingService(
                "TestDoMappings\\do-mappings.xml"));

        String searchSpec = format(
                "[name] like '*' " +
                        " AND [grandParentName] = '%s'" +
                        " AND [parentName] = '%s' " +
                        " AND [repositoryName] = 'Siebel Repository'" +
                        " AND ([inactive] = 'N' " +
                        "       OR [inactive] IS NULL)", objectName, objectComponent);

        QueryObject queryReq = new QueryObject(SiebelICFieldDefinition.class, ActionEvent.QUERY);
        queryReq.setQueryString(searchSpec);
        //        queryReq.setSortString("type(ASCENDING)"); // seems not working
        //        queryReq.setSortString("type(DESCENDING)");
        //        queryReq.setSortString("name");
        //        queryReq.setSortString("name(DESCENDING)");

        long t0 = System.currentTimeMillis();
        try {
            List<?> r = dataManager.query(queryReq);
            //        List<?> r = dataManager.queryBySearchExpression(SiebelICFieldDefinition.class, searchSpec);
            System.out.println("********");
            System.out.printf("Object Name: %s\n", objectName);
            System.out.printf("Object Component: %s\n", objectComponent);
            System.out.printf("Fields:\n");
            
            for (Object obj : r) {
                //            System.out.println(str(obj));
                SiebelICFieldDefinition f = (SiebelICFieldDefinition) obj;
                System.out.printf("id=%s, name=%s, type=%s\n", f.getId(), f.getName(), f.getType());
//                System.out.println(str(f));
            }
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    } 
    
    public static void printIcFieldDefinitions(String objectName, String objectComponent) {
        try {
            printIcFieldDefinitions(dataManager(), objectName, objectComponent);
        } finally {
            StatelessSessionFactoryHolder.statelessSessionFactory.releaseAllStatelessSessions();
        }
    } 
        
    
    
    static <T> T[] skipNulls(T[] objects) {
        List<T> lst = new ArrayList<T>();
        for (T t : objects) {
            if (t != null) {
                lst.add(t);
            }
        }
        
        T[] zeroArray = (T[])java.lang.reflect.Array.newInstance(objects.getClass().getComponentType(), 0);
        return  lst.toArray(zeroArray);
    }
    
    @Test
    public void testSkipNulls() throws Exception {
        try {
            skipNulls(null);
            fail();
        } catch (NullPointerException ex) {
        }
        assertEquals(new String[] {"1", "2"}, skipNulls(new String[] {"1", null, "2"}));
    }
    
    public static <T extends Object> ArrayList<T> newArrayList(T... objects) {
        return new ArrayList<T>(Arrays.asList(objects));
    }
    
    @Test
    public void testNewArrayList() throws Exception {
        ArrayList<Object> lst = new ArrayList<Object>();
        lst.add("1");
        lst.add("2");
        assertEquals(lst, newArrayList("1", "2"));
        lst.add(3);
        assertEquals(lst, newArrayList("1", "2", 3));
        
    }
    
    public static void print(Collection<?> coll) {
        print("", coll);
    } 
    
    public static void print(String ns, Collection<?> coll) {
        if (ns == null) {
            ns = "";
        }
        if (LangUtil.isEmpty(coll)) {
            System.out.println(ns + "no data");
        
        } else {
            int i = 1;
            for (Object  obj : coll) {
                System.out.printf(ns + "[%s] =============================\n", i++);
                System.out.println(ns + str(obj));
//                MiscTest.showStructure(asset);
            }
            System.out.println(ns + "======");
            System.out.println(ns + "    size = " + coll.size()); 
            System.out.println(ns + "======");
        }
    } 
    
    public static void print(String ns, Collection<?> coll, String... fields) {
        if (ns == null) {
            ns = "";
        }
        if (LangUtil.isEmpty(coll)) {
            System.out.println(ns + "no data");
        
        } else {
            int i = 1;
            for (Object  obj : coll) {
                System.out.printf(ns + "[%s] =============================\n", i++);
                StringBuilder sb = new StringBuilder();
                for (String fieldRec : fields ) {
                    int idx = fieldRec.lastIndexOf(':');
                    String field = fieldRec; 
                    boolean generateToString = false;
                    if (idx != -1) {
                        field = fieldRec.substring(0, idx);
                        String options = fieldRec.substring(idx + 1);
                        if (options.contains("str")) {
                            generateToString = true;
                        }
                    }
                    
                    Object fieldValue = SortUtil.readField(obj, field, ".");
                    Object fieldValueFormatted =  fieldValue == null ? "<null>": fieldValue;
                    if (generateToString) {
                        fieldValueFormatted = str(fieldValueFormatted); 
                    }
                    sb.append(String.format("%s=%s,", field, fieldValueFormatted));
                }
                System.out.printf(ns + "%s[%s...]\n", obj.getClass().getName(), sb.toString());
            }
            System.out.println(ns + "======");
            System.out.println(ns + "    size = " + coll.size()); 
            System.out.println(ns + "======");
        }
    }
    
    /**
     * @param fields  fields to print (nested fields are described via XPath dot notation)
     * 
     */
    public static void print(Collection<?> coll, String... fields) {
        print("", coll, fields);
    }
    
    public static void print(String ns, Object obj) {
        if (ns == null) {
            ns = "";
        }
        
        if (obj == null) {
            System.out.println(ns + "<null>");
        } else {
            System.out.println(ns + obj);
        }        
    }
    
    public static void print(String ns, Object obj, String... fields) {
        if (ns == null) {
            ns = "";
        }

        if (obj == null) {
            System.out.println(ns + "<null>");
        } else {

            StringBuilder sb = new StringBuilder();
            for (String fieldRec : fields) {
                int idx = fieldRec.lastIndexOf(':');
                String field = fieldRec;
                boolean generateToString = false;
                if (idx != -1) {
                    field = fieldRec.substring(0, idx);
                    String options = fieldRec.substring(idx + 1);
                    if (options.contains("str")) {
                        generateToString = true;
                    }
                }

                Object fieldValue = SortUtil.readField(obj, field, ".");
                Object fieldValueFormatted = fieldValue == null ? "<null>" : fieldValue;
                if (generateToString) {
                    fieldValueFormatted = str(fieldValueFormatted);
                }
                sb.append(String.format("%s=%s,", field, fieldValueFormatted));
            }
            System.out.printf(ns + "%s[%s...]\n", obj.getClass().getName(), sb.toString());
        }
    }
    
    
    public static String str(Object obj) {
        return ToStringBuilder.reflectionToString(obj);
    }
   
    
    private static class StatelessSessionFactoryHolder {
         static final StatelessSessionFactory statelessSessionFactory = TestSessionFactories.newStatelessSessionFactory();
         static {
             Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    statelessSessionFactory.releaseAllStatelessSessions();
                    System.out.println("\n[JVM shutdown-hook] after calling statelessSessionFactory.releaseAllStatelessSessions()");
                }
             });
         }
    }

    private static IDataManager dataManager() {
        return statelessSession().getDataManager();
    }
    
    public static Session statelessSession() {
          return StatelessSessionFactoryHolder.statelessSessionFactory.attachSession();
    }
    
    public static <T> List<T> sampleSiebelQuery(Class<T> doClass, String query, int numRows, int startRowIndex) {
        List<T> result = querySiebel(doClass, query, numRows, startRowIndex);
        print(result); 
        return result;
    }    
    
    public static <T> List<T> sampleSiebelQuery(Class<T> doClass, String query, int numRows) {
        return sampleSiebelQuery(doClass, query, numRows, 0 /*startRowIndex*/);
    }    
    
    public static <T> List<T> sampleSiebelQuery(Class<T> doClass, int numRows, int startRowIndex) {
        List<T> result = querySiebel(doClass, "", numRows, startRowIndex);
        print(result); 
        return result;
    }
    
    public static <T> List<T> sampleSiebelQuery(Class<T> doClass, int numRows) {
        return sampleSiebelQuery(doClass, numRows, 0 /*startRowIndex*/);
    }

    public static List<?> querySiebel(Class<?> class1, String searchSpec) {
        return querySiebel(class1, searchSpec, -1 /*default value*/, 0 /*default value*/);
    }
    
    public static <T> List<T> querySiebel(Class<T> class1, String searchSpec, int numRows, int startRowIndex) {
        try {
            QueryObject qo = new QueryObject(class1, ActionEvent.QUERY);
            qo.addComponentSearchSpec(class1, searchSpec);
            qo.setQueryString(searchSpec);
            qo.setNumRows(numRows);
            qo.setStartRowIndex(startRowIndex);
            long t0 = System.currentTimeMillis();
            try {
            	List<T> result = dataManager().query(qo);
            	return result;
            } finally {
            	System.out.printf("[dataManager call] Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
            }
        } finally {
            StatelessSessionFactoryHolder.statelessSessionFactory.releaseAllStatelessSessions();
            System.out.println("releasing All Stateless Sessions ...Ok");
        }
    }
   
    public static boolean not(boolean b) {
        return !b;
    }
    
    static boolean equals(Object obj1, Object obj2) {
        return ObjectUtils.equals(obj1, obj2); 
     }
    
    public static Map<String, Object> toMap(Map.Entry<String, Object> mapEntry) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(mapEntry.getKey(), mapEntry.getValue());
        return map;
    }

    public static <T> List<T> queryBySearchExpression(Class<T> doClass, String searchSpec) {
        try {
            return dataManager().queryBySearchExpression(doClass, searchSpec);
        } finally {
            MiscTest.StatelessSessionFactoryHolder.statelessSessionFactory.releaseAllStatelessSessions();
        }  
    }

    public static List<?> query(QueryObject qo) {
        try {
            return dataManager().query(qo);
        } finally {
            MiscTest.StatelessSessionFactoryHolder.statelessSessionFactory.releaseAllStatelessSessions();
        }          
    }
    
    public static void releaseAllStatelessSessions() {
       StatelessSessionFactoryHolder.statelessSessionFactory.releaseAllStatelessSessions(); 
    }
    
    static String join(String separator, Object... objects) {
        if (objects == null
            || objects.length == 0) {
            return "";
        }
       
        Object first = objects[0];
        if (objects.length == 1) {
            return ""  + first;
        } else {
            String s = "" + first;
            for (int i = 1; i < objects.length; i++) {
                s += separator + objects[i];
            }
            return s;
        }
    }
    
    @Test
    public void testJoin() throws Exception {
        assertEquals("", join(","));
        assertEquals("1", join(",", 1));
        assertEquals("1,2", join(",", 1,2));
        assertEquals("1*2*3", join("*", 1,2,3));
    }
    
    @Test
    public void testStringFormat() throws Exception {
        assertEquals("1,012400", String.format("%f", 1.0124));
        assertEquals("1,01", String.format("%.2f", 1.0124));
        assertEquals("1,02", String.format("%.2f", 1.0174));
    }
    
    @Test
    public void testToSiebelXml() throws Exception {
        System.out.println(toSibelXmlFormat("FS Agreement Entitlement Service Detail"));
        System.out.println(toSibelXmlFormat("Service Agreement"));
    }
    
    @Test
    public void testFromSiebelXml() throws Exception {
        System.out.println(fromSibelXmlFormat("LXK_spcMPS_spcService_spcRequest_spcReadings_spcBC"));
        System.out.println(fromSibelXmlFormat("LXK_spcMPS_spcSR_spcShipped_Delivered_spcOrder_spcLine_spc-_spcService_spcWeb"));
        System.out.println(fromSibelXmlFormat("LXK_spcSD_spcOrder_spcItem_spcPart_spcNumber"));
        System.out.println(fromSibelXmlFormat("LXK_spcMPS_spcSR_spcOrder_spcLine_spc-_spcService_spcWeb"));
        System.out.println(fromSibelXmlFormat("LXK_spcMPS_spcSR_spcShipped_Delivered_spcOrder_spcLine_spc-_spcService_spcWeb"));
        System.out.println(fromSibelXmlFormat("LXK_spcMPS_spcContracted_spcConsumable_spcAsset_spcList_LXK_spcMPS_spcAsset_spcEntitlement"));
        System.out.println(fromSibelXmlFormat("LxkMpsSrOrderLine-ServiceWeb2"));
    }

    
    private String toSibelXmlFormat(String s) {
        return s.replace(" ", "_spc");
    }
    
    private String fromSibelXmlFormat(String s) {
        return s.replace("_spc", " ");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testToLogString() throws Exception {
        System.out.println(toLogString(null));
        
        OrderListContract c = new OrderListContract();
        c.setEmployeeFlag(true);
        c.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("k1", "v1")); // inherited
        c.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("k1", "v1")); // inherited 
        System.out.println(toLogString(c));
        
        System.out.println(toLogString(new A()));
        ActivityDetailContract adc = new ActivityDetailContract();
        adc.setDebriefFlag(true);
        ServicesUser user = new ServicesUser();
        user.setAccountName("mock-accountName");
        adc.setServicesUser(user);
        System.out.println(toLogString(adc));
        
        MiscTest.showStructure(adc);
//        MiscTest.showStructure(null);
    }
    
    public static String toLogString(Object obj) {
        StringBuilder sb = new StringBuilder(); 
        sb.append("<toLogString>\n");
        sb.append(str(obj));
        processServicesUser(sb, obj);
        sb.append("\n</toLogString>");
        return sb.toString();
    }
    
    private static void processServicesUser(StringBuilder sb, Object obj) {
        if (obj instanceof SecureContract) {
            sb.append("\n");
            SecureContract secureContract = (SecureContract) obj;
            sb.append("\t");
            sb.append(str(((SecureContract) obj).getServicesUser()));
        }
    }

    @SuppressWarnings("unused")
    static class A {
        private final int field1 = 12;
        private final A2 a2 = new A2();
        transient private int transientField1 = 12;
        transient private int transientField2 = 12;
        
        @Override
        public String toString() {
            return "A[<custom toString>]";
        }
    }
    
    static class A2 {
        
        @Override
        public String toString() {
            return "A2[<custom toString>]";
        }
        
    }
    
    /** 
     *  Alias for {@link String#format(String, Object...)}.
     */
    public static String sformat(String format, Object ... args) {
        return String.format(format, args);
    }
}
