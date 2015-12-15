package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil.setPartnerFlags;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lexmark.domain.Account;
import com.lexmark.service.impl.real.domain.AccountFlag;


/**
 * @author vpetruchok
 * @version 1.0, 2012-08-28
 */
public class AmindPartnerDataManagerUtilTest {

    @Test
    public void testSetPartnerFlags() throws Exception {
        List<? extends AccountFlag> flagList = Arrays.asList(new AccFlag("Can View Invoice", true), new AccFlag("Can Fulfill Orders", true));
        Account acc = new Account();
        setPartnerFlags(flagList, acc);
        assertTrue(acc.isViewInvoiceFlag());
        assertTrue(acc.isAccessServiceOrderFlag());
    }
    
    static class AccFlag extends AccountFlag {
        
        AccFlag(String flag, boolean setted) {
            this.setType(flag);
            this.setValue(setted);
        }
    }

}
