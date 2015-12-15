package com.lexmark.service.impl.real.domain;

import static util.TestUtils.serialize;
import static util.TestUtils.deserialize;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.amind.common.domain.EntityState;

/**
 * @author vpetruchok
 * @version 1.0, 2012-04-06
 */
public class MeterReadBaseTest {


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testSerialization() {
        MeterReadBase obj = new MeterReadBase();
        obj.setMeasurementCharacteristics(new ArrayList(Arrays.asList(new AssetMeasurementCharacteristicsBase())));
        serialize(obj);
    }
    
    @Test
    public void testSerialization2() {
        AssetReadingLatestBase  a1 = new AssetReadingLatestBase();
        a1.setParent(null);
        a1.setUpdateState(EntityState.CREATED);
        serialize(a1);
        deserialize(serialize(a1));
        
        AssetMeasurementCharacteristicsBase a2 = new AssetMeasurementCharacteristicsBase();
        a2.setAssetReading(newArrayList(new AssetReadingLatestBase(), new AssetReadingLatestBase()));
        serialize(a2);
        AssetMeasurementCharacteristicsBase a2Copy =  (AssetMeasurementCharacteristicsBase) deserialize(serialize(a2));
        assertEquals(2, a2Copy.getAssetReading().size());
    }
    
    private static <T extends Object & Serializable> ArrayList<T> newArrayList(T... objects) {
        return new ArrayList<T>(Arrays.asList(objects));
    }

}
