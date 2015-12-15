package com.lexmark.service.impl.real;

import static com.lexmark.util.LangUtil.isEmpty;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.reflect.FieldUtils;

import com.lexmark.result.AssetListResult;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-18
 */
public class SortUtil {

    public static class SortCriteria {

        private final String portalField;
        private final String fieldname;

        /**
         * true - ascending order, false - descending order
         */
        private final boolean ascendingOrder;
        
        private final boolean caseInsensitive;

        public SortCriteria(String fieldname, boolean ascendingOrder) {
            this(fieldname, fieldname, ascendingOrder, false);
        }
        
        public SortCriteria(String fieldname, boolean ascendingOrder, boolean caseInsensitive) {
            this(fieldname, fieldname, ascendingOrder, caseInsensitive);
        }
        
        public SortCriteria(String portalField, String fieldname, boolean ascendingOrder, boolean caseInsensitive) {
            this.portalField = portalField; 
            this.fieldname = fieldname;
            this.ascendingOrder = ascendingOrder;
            this.caseInsensitive = caseInsensitive;
        }
        
        
        public String getFieldname() {
            return fieldname;
        }

        public boolean isAscendingOrder() {
            return ascendingOrder;
        }
        
        public boolean isCaseInsensitive() {
            return caseInsensitive;
        }

        public Map<String, Object> toMap() {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put(this.portalField, this.ascendingOrder ? "ASCENDING" : "DESCENDING");
            return m;
        }

        @Override
        public String toString() {
            return String.format("[portalField=%s, fieldname=%s, ascendingOrder=%s, caseInsensitive=%s]", portalField, fieldname, ascendingOrder, caseInsensitive);
        }
    }

    public static void checkSortOrder(List<?> list, SortCriteria sortCriteria) throws IllegalAccessException {
        checkSortOrder(list, sortCriteria, true);
    }
    
    public static void checkSortOrder(List<?> list, SortCriteria sortCriteria, boolean nullsComeFirst) throws IllegalAccessException {
        if (isEmpty(list) || list.size() == 1) {
            return;
        }
        if (sortCriteria == null || isEmpty(sortCriteria.getFieldname())) {
            return;
        }

        String fieldname = sortCriteria.getFieldname();
        boolean ascendingOrder = sortCriteria.isAscendingOrder();

        for (int i = 0; i < list.size() - 1; i++) {
            Object obj1 = list.get(i);
            Object obj2 = list.get(i + 1);
            Object v1 = readField(obj1, fieldname, ".");
            Object v2 = readField(obj2, fieldname, ".");
            
            String s1 = toString(v1);
            String s2 = toString(v2);
            System.out.printf("[%s] %s,  %s=\"%s\"\n", i, sortCriteria, fieldname, s1);
            System.out.printf("[%s] %s,  %s=\"%s\"\n", i + 1, sortCriteria, fieldname, s2);
            
            if (sortCriteria.isCaseInsensitive()) {
                s1 = toUpperCase(s1);
                s2 = toUpperCase(s2);
            }
            
            int compareCode = compare(s1, s2);

            if (ascendingOrder || (nullsComeFirst && LangUtil.isEmpty(s1)) /* nulls come first */) {
                assertTrue(describeContext(sortCriteria, nullsComeFirst, s1, s2), compareCode <= 0);
            } else {
                assertTrue(describeContext(sortCriteria, nullsComeFirst, s1, s2), compareCode >= 0);
            }
        }
    }

    
    private static String toUpperCase(String s) {
        return s == null ? null: s.toUpperCase();
    }

    
    private static String describeContext(SortCriteria sortCriteria, boolean nullsComeFirst, String s1, String s2) {
        String s = String.format("%s nullsComeFirst=%s, s1=\"%s\", s2=\"%s\"", sortCriteria, nullsComeFirst, s1, s2);
        return s;
    } 

    private static <T extends Comparable<? super T>> int compare(T v1, T v2) {
        if (v1 == null && v2 == null) {
            return 0;
        }
        if (v1 == null) {
            return -1;
        }
        if (v2 == null) {
            return 1;
        }
        return v1.compareTo(v2);
    }

    private static String toString(Object obj) {
        return obj == null ? null: obj.toString();
    }
    
    public static Object readField(Object obj, String fieldnameExpr, String fieldnameSep)  {
        try {
            if (LangUtil.isEmpty(fieldnameSep)) {
                return FieldUtils.readField(obj, fieldnameExpr, true);
            }
            Object result = obj;
            for (String fieldname : fieldnameExpr.split(Pattern.quote(fieldnameSep))) {
                result = FieldUtils.readField(result, fieldname, true);
            }
            return result;
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    
   public static interface AssetProvider {
        public AssetListResult get(SortCriteria sortCriteria) throws Exception; 
   }
    
    public static void checkSortOrder2(AssetProvider assetProvider, String... sortFields) throws Exception {
        List<Throwable> errors = new ArrayList<Throwable>();
        for (String field : sortFields) {
            if (LangUtil.isEmpty(field)) {
                continue;
            }
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field, ascendingOrder);
                try {
                    AssetListResult result = assetProvider.get(sortCriteria);
                    checkSortOrder(result.getAssets(), sortCriteria, true);
                } catch (Throwable ex) {
                    errors.add(ex);
                }
            }

        }

        if (errors.size() > 0) {
            System.out.println("Errors:");
            for (Throwable thr : errors) {
                System.out.println("==================");
                System.out.println(thr);
            }
            throw new AssertionError("Sort test failed: " + errors);
        }
    }

}
