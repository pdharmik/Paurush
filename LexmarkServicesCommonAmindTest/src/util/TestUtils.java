/*******************************************************************************
 * Unpublished work, copyright (c) aMind Solutions LLC 2008-2010. All rights 
 * reserved. This software code and any commented materials or notations 
 * ("Materials") constitute proprietary and confidential  information of aMind 
 * Solutions LLC. The Materials (and any  or material derived therefrom) may not 
 * be reproduced or used, and may not be disclosed or otherwise  made available 
 * to any person, in whole or in part, except in accordance with a written 
 * agreement with aMind or as otherwise expressly authorized in writing by aMind
 ******************************************************************************/

/**
 *
 */
package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author $Author: mansi $
 * @since 02/26/2010
 */
public class TestUtils
{
    /** Hide constructor */
    private TestUtils()
    {}

    /**
     * Inject values directly into fields regardless of access
     * @param target Object that contains the field
     * @param fieldName Name of the field
     * @param fieldValue Value to inject
     */
    public static void setField(Object target, String fieldName,
            Object fieldValue)
    {
        try
        {
            Field targetField = target.getClass().getDeclaredField(fieldName);
            targetField.setAccessible(true);
            targetField.set(target, fieldValue);
        }
        catch (NoSuchFieldException e)
        {
            throw new IllegalArgumentException("No such field, " + fieldName);
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalArgumentException("Access denied to field, "
                    + fieldName);
        }
    }
    
    public static Object getField(Object target, String fieldName)
    {
    	Field targetField = null;
    	Object resultTarget = null;
        try
        {
        	targetField   = target.getClass().getDeclaredField(fieldName);
            targetField.setAccessible(true);
            resultTarget = targetField.get(target);
        }
        catch (NoSuchFieldException e)
        {
            throw new IllegalArgumentException("No such field, " + fieldName);
        } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
        	throw e;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("getField failed", e);
		}
        return resultTarget;
    }
    
    
    public static byte[] serialize(Serializable obj) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException("cannot serialize " + obj.getClass(), ex);
        } finally {
            close(oos);
        }
    }

    public static Object deserialize(byte[] bytes) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        } catch (Exception ex) {
            throw new RuntimeException("cannot deserialize", ex);
        } finally {
            close(ois);
        }
    }
    
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
