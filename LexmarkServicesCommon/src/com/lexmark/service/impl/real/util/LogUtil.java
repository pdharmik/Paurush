package com.lexmark.service.impl.real.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-12-13
 */
public class LogUtil {
    
    //private static final Log LOGGER = LogFactory.getLog(LogUtil.class); 
	private static final Logger LOGGER = LogManager.getLogger(LogUtil.class); 
    
    private LogUtil() {
    }
    
    public static String dump(Object obj) {
        return dump(obj, false);
    }
    
    public static String dump(Object obj, boolean multilineStyle) {
        // Simple top-level support for Map and Collection
        if ( obj instanceof Map
              || obj instanceof Collection
              || obj instanceof CharSequence
              || obj instanceof Integer
              || obj instanceof Long) {
            return String.format("%s (%s)", obj.toString(), obj.getClass());
        }
        
        RecursiveToStringStyle style = new RecursiveToStringStyle(5);
        if (multilineStyle) {
            style.setMultilineMode();
        }
        return ToStringBuilder.reflectionToString(obj, style);
    }

    public static void logAmindServiceCallException(String source, Throwable throwable, Object... params) {
        logException(LOGGER, "~~ AMIND-SERVICE-CALL-ERROR ~~", source, throwable, params);
    }
    
    public static void logException(Logger logger, String description, String source, Throwable throwable, Object... params) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        
        StringBuilder sb = new StringBuilder(); 
        if (LangUtil.isNotEmpty(description)) { 
            sb.append(String.format("\n<%s>", description)); 
        }
        
        sb.append("\n\nSOURCE: ").append(source);
        for (Object param : params) {
           sb.append("\n\nINPUT PARAMETER: ").append(dump(param, true));
        }
        sb.append("\n\nEXCEPTION: ").append(getStackTrace(throwable)); 
        
        if (LangUtil.isNotEmpty(description)) {
            sb.append(String.format("\n</%s>", description)); 
        }
        logger.debug(sb); // TODO(Viktor): discuss log level
    }    
    
    public static String getStackTrace(Throwable throwable) {
        return ExceptionUtils.getStackTrace(throwable);
    }
    
    public static String stackTraceExecutionPoint() {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        StackTraceElement ste = stackTraces[2]; // index 0 - getStackTrace() call, index 1 - this method invocation, index 2 - a caller method invocation
        String s = "className.methodName(fileName:lineNumber)"
                .replace("className", ste.getClassName())
                .replace("methodName", ste.getMethodName())
                .replace("fileName", ste.getFileName())
                .replace("lineNumber", "" + ste.getLineNumber());
        return s;
    }
}
