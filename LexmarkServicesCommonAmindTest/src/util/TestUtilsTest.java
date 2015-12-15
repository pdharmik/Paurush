package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.TestUtils.deserialize;
import static util.TestUtils.serialize;

import java.io.Serializable;
import java.util.Arrays;

import org.junit.Test;


/**
 * @author vpetruchok
 * @version 1.0, 2012-04-06
 */
public class TestUtilsTest {


    @SuppressWarnings("unchecked")
    @Test
    public void testSerialization() {
        for (Serializable obj : Arrays.asList(null, 1, "hello")) {
           assertEquals(obj, deserialize(serialize(obj)));
        }
        byte[] bytes = {1,2} ;
        assertTrue(Arrays.equals(bytes, (byte[]) deserialize(serialize(bytes))));
    }
}
