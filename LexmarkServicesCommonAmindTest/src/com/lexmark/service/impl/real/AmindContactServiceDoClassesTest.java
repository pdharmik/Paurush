package com.lexmark.service.impl.real;

import org.junit.Test;

import com.lexmark.service.impl.real.domain.AccountContactDo;
import com.lexmark.service.impl.real.domain.AccountFavouriteContactDo;
import com.lexmark.service.impl.real.domain.UserFavoriteContactDo;

/**
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-19
 */
public class AmindContactServiceDoClassesTest extends AmindSiebelQueryTest {

//    static AmindContactService contactService = null;

    /*
    @BeforeClass
    public static void setUp() {
        contactService = new AmindContactService();
        contactService.setSessionFactory(AmindServiceTest.statelessSessionFactory);
    }
    */

    @Test
    public void queryAccountContactDo() {
        sampleSiebelQuery(AccountContactDo.class, 2, 1);
    }

    @Test
    public void queryAccountFavouriteContactDo() {
        sampleSiebelQuery(AccountFavouriteContactDo.class, 2, 1);
    }

    @Test
    public void queryUserFavoriteContactDo() {
        sampleSiebelQuery(UserFavoriteContactDo.class, 2, 1);
    }

    @Test
    public void queryUserFavoriteContactDo2() {
        sampleSiebelQuery(UserFavoriteContactDo.class, 2, 10); 
    }
}
