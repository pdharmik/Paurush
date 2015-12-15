package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2013-01-04
 */
public class UserFavoriteAssetDoTest {
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(UserFavoriteAssetDo.class, "", 5);
    }

}
