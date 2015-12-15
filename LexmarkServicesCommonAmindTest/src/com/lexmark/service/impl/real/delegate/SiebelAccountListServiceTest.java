package com.lexmark.service.impl.real.delegate;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import util.TestSessionFactories;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.AccountByVendorIdDo;
import com.lexmark.service.impl.real.domain.AccountDo;
import com.lexmark.util.LangUtil;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-26
 */
public class SiebelAccountListServiceTest {

    /**
     * com.lexmark.service.impl.real.domain.AccountDo / do-accountdo-mapping.xml
     *  
     * @see com.lexmark.service.impl.real.AmindContractedServiceRequestService#retrieveSiebelAccountList(SiebelAccountListContract)
     * @see com.lexmark.service.impl.real.domain.AccountDo
     */
    @Test
    public void testQueryAndGetResultList_by_vendorId() throws Exception {
        SiebelAccountListContract contract = new SiebelAccountListContract();
//        contract.setVendorAccountId("1-1B1DIK");
        contract.setVendorFlag(true);
        contract.setMdmId("1-8AOEMQ");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_SIEBEL);        
        SiebelAccountListService service = new SiebelAccountListService(contract);
        StatelessSessionFactory statelessSessionFactory = TestSessionFactories.newStatelessSessionFactory();
        Session session = null;
        try {
            session = statelessSessionFactory.attachSession();
            service.setSession(session);
            service.checkRequiredFields();
            service.buildSearchExpression();
            List<Account> result = service.queryAndGetResultList();
            MiscTest.print(result);
//            System.out.println("result=" + result);

        } finally {
            statelessSessionFactory.detachSession(session);
        }
    }
    

    @Test
    public void testQuery() throws Exception {
        List<?> r = MiscTest
                .queryBySearchExpression(AccountDo.class,
//                       "[LXK Account Service Web Portal.Domestic Ultimate DUNS]='DUN1-L4BTZC' AND [LXK Account Service Web Portal.LXK SW MDM Account Level] ='Siebel'");
                        "EXISTS ([LXK MPS Partner Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y')"); //error
//                       "[LXK Account Service Web Portal.LXK MPS Partner Flag] = 'Y'");                
        System.out.println("********");
        MiscTest.print(r);
    }

    @Test
    public void testQuery2() throws Exception {
//             searchspec:EXISTS ([LXK MPS Partner Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y')
//        String searchSpec = "EXISTS ([vendorId] = '1-1B1DIK' AND [partnerFlag] = 'Y')";
//        String searchSpec = "EXISTS ([LXK MPS Partner Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y' AND [LXK MPS Primary Account] = 'Y' AND [LXK MPS Partner Account Id] <> [Id])"; 
//        String searchSpec = "EXISTS ([LXK MPS Partner Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y' AND [LXK MPS Primary Account] = 'Y')"; 
//        String searchSpec = "EXISTS ([LXK MPS Partner Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y' ) AND [LXK MPS Primary Account] = 'Y')"; 
//        String searchSpec = "EXISTS ([LXK MPS Partner Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y' ) AND EXISTS ([LXK MPS Primary Account] = 'Y')"; 
//        String searchSpec = "[vendorId] = '1-1B1DIK' AND [partnerFlag] = 'Y'"; // error
//        String searchSpec = "EXISTS ([vendorId] = '1-1B1DIK') AND [partnerFlag] = 'Y'"; // error
//        String searchSpec = "([Account Id] = '1-1B1DIK' AND [LXK MPS Partner Flag] = 'Y')";
        String searchSpec = "[LXK MPS Partner Flag] = 'Y";
        Class<?> doClass =  AccountByVendorIdDo.class;
        QueryObject queryReq = new QueryObject(doClass, ActionEvent.QUERY);
//		queryReq.setExecutionMode(ExecutionMode.BiDirectional);
        queryReq.setQueryString(searchSpec);
        queryReq.addComponentSearchSpec(doClass, searchSpec);

        List<AccountByVendorIdDo> accountDos = (List<AccountByVendorIdDo>) MiscTest.query(queryReq);
        System.out.println("accountDos="  + accountDos);
        for (AccountByVendorIdDo accountDo : LangUtil.notNull(accountDos)) {
            System.out.printf("id=%s, vendorId=`%s', partnerFlag=`%s', agreementName=`%s' ...\n",
                    accountDo.getId(), accountDo.getVendorAccountId(), accountDo.getPartnerFlag(), accountDo.getAgreementName());
            System.out.println(MiscTest.str(accountDo));
        }
    }

    @Test
    public void testCheckRequiredFields_mdmId_and_mdmLevel() throws Exception {
        {
            SiebelAccountListContract c = new SiebelAccountListContract();
            c.setMdmId("mock-mdmId");
//        c.setMdmLevel("mock-mdmLevel");
            SiebelAccountListService srv = new SiebelAccountListService(c);
            try {
                srv.checkRequiredFields();
                fail();
            } catch (IllegalArgumentException ok) {
            }
        }

        {
            SiebelAccountListContract c = new SiebelAccountListContract();
//            c.setMdmId("mock-mdmId");
            c.setMdmLevel("mock-mdmLevel");
            SiebelAccountListService srv = new SiebelAccountListService(c);
            try {
                srv.checkRequiredFields();
                fail();
            } catch (IllegalArgumentException ok) {
            }
        }

        {
            SiebelAccountListContract c = new SiebelAccountListContract();
            c.setMdmId("mock-mdmId");
            c.setMdmLevel("mock-mdmLevel");
            SiebelAccountListService srv = new SiebelAccountListService(c);
            srv.checkRequiredFields();
        }
    }

    @Test
    public void testCheckRequiredFields_vendorId() throws Exception {
        {
            SiebelAccountListContract c = new SiebelAccountListContract();
            SiebelAccountListService srv = new SiebelAccountListService(c);
            try {
                srv.checkRequiredFields();
                fail();
            } catch (IllegalArgumentException ok) {
            }
        }

        {
            SiebelAccountListContract c = new SiebelAccountListContract();
            c.setMdmId("mock-mdmId");
            c.setMdmLevel("mock-mdmLevel");
//            c.setVendorAccountId("mock-vendorId");
            SiebelAccountListService srv = new SiebelAccountListService(c);
            srv.checkRequiredFields();
        }
    }

}
