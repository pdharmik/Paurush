package com.lexmark.service.impl.real.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.lexmark.service.impl.real.AmindCrmSessionHandle;

/**
 *
 * @author vpetruchok
 * @version 1.0, 2012-12-13
 */
public class RecursiveToStringStyle extends ToStringStyle {

    private static final long serialVersionUID = 1L;

    private int maxRecursionLevel = 5;
    private int recursionLevel = 0;

    public RecursiveToStringStyle(int recursionLevel) {
        this.maxRecursionLevel = recursionLevel;
        this.setArrayContentDetail(true);
        this.setUseIdentityHashCode(false);
    }
    
    public int getRecursionLevel() {
        return recursionLevel;
    }

    /**
     * @see org.apache.commons.lang.builder.ToStringStyle.MultiLineToStringStyle.MultiLineToStringStyle
     */
    public void setMultilineMode() {
        this.setContentStart("[");
        this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
        this.setFieldSeparatorAtStart(true);
        this.setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (recursionLevel >= maxRecursionLevel
             || value.getClass().getName().startsWith("java.lang.")
             || value.getClass().getName().startsWith("java.util.concurrent")
             || value instanceof AmindCrmSessionHandle) {
            super.appendDetail(buffer, fieldName, value);
            return;
        }

        recursionLevel++;
        if (value instanceof Date) {
            String s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(value);
            buffer.append(String.format("java.util.Date[%s]", s));
        } else if (value instanceof Collection) {
            this.appendDetail(buffer, fieldName, (Collection) value);
        } else if (value instanceof Map) {
            this.appendDetail(buffer, fieldName, (Map) value);
        } else if (value instanceof Map.Entry) {
            Map.Entry e = (Map.Entry) value;
            this.appendDetailMapEntry(buffer, fieldName, e);
        } else {
            buffer.append(ReflectionToStringBuilder.toString(value, this));
        }
        recursionLevel--;
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Collection coll) {
        appendDetail(buffer, fieldName, coll.toArray());
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Map map) {
        buffer.append(map.getClass().getName());
        appendDetail(buffer, fieldName, map.entrySet().toArray());
    }

    @SuppressWarnings("rawtypes")
    protected void appendDetailMapEntry(StringBuffer buffer, String fieldName, Map.Entry mapEntry) {
        Object key = mapEntry.getKey();
        Object value = mapEntry.getValue();
        if (isSimple(value)) {
            buffer.append(mapEntry);
        } else {
            buffer.append(key).append("=");
            this.appendDetail(buffer, fieldName, value);
        }
    }

    private boolean isSimple(Object value) {
        return value.getClass().isPrimitive() || value instanceof CharSequence;
    }

}