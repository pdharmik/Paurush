package com.lexmark.service.impl.real.partnerPaymentService;


import static com.lexmark.service.impl.real.MiscTest.sformat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.domain.AccountPayableDetail;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.result.AccountReceivableListResult;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.AccountPayableDetailDo;
import com.lexmark.service.impl.real.domain.AccountPayableDo;
import com.lexmark.service.impl.real.domain.AccountReceivableDo;


/**
 * @author vpetruchok
 * @version 1.0, 2012-09-13
 */
public class AmindPartnerPaymentServiceTest extends AmindServiceTest {

    static AmindPartnerPaymentService service = new AmindPartnerPaymentService();

    @BeforeClass
    public static void setUp() throws Exception {
        service.setStatelessSessionFactory(statelessSessionFactory);
    }


    @Test
    public void testRetrieveAccountPayableList() throws Exception {
        AccountPayableListContract contract = new AccountPayableListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("11634");
        contract.setMdmLevel("Account");
        contract.setNewQueryIndicator(true);
        AccountPayableListResult result;
        long t0 = System.currentTimeMillis();
        try {
            result = service.retrieveAccountPayableList(contract);
            MiscTest.print(result.getAccountPayableList());
            System.out.println("totalCount = " + result.getTotalCount());

//            MiscTest.print(result.getAccountPayableList(), "payeeAddress:str");
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }

    @Test
    public void testRetrieveAccountPayableList_Production_Defect5547() throws Exception {
        AccountPayableListContract contract = new AccountPayableListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSessionHandle(crmSessionHandle);
//        contract.setMdmId("23259");
        contract.setMdmId("27711");
        contract.setMdmLevel("Legal"); // no data
//        contract.setMdmLevel("Account"); // 1 record
        contract.setNewQueryIndicator(true);
        AccountPayableListResult result;
        long t0 = System.currentTimeMillis();
        try {
            result = service.retrieveAccountPayableList(contract);
            MiscTest.print(result.getAccountPayableList());
            System.out.println("totalCount = " + result.getTotalCount());

//            MiscTest.print(result.getAccountPayableList(), "payeeAddress:str");
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }


    @Test
    public void queryAP_Production_Defect5547() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDo.class,
//                "[LXK Partner DUNS] = '27711' AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"
//                "[LXK SAP Vendor MDM Account#] = '19044' AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"
                "[LXK SAP Vendor MDM Account#] = '27711' AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"
//                "[LXK SAP Vendor MDM Account#] = '23259' AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"
//                "([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"
                , 10);
    }

    @Test
    public void queryAP() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDo.class,
                " [mdmLevel1AccountId] <> '' AND  ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"

                , 10);
    }

    @Test
    public void queryAP_testData() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDo.class,
                " ([mdmLevel1AccountId] <> '' OR [mdmLevel2AccountId] <> '' OR [mdmLevel3AccountId] <> '' " +
                 " OR [mdmLevel4AccountId] <> '' OR [mdmLevel5AccountId] <> '') " + "" +
                 " AND  ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')"
                , 10);
    }

    @Test
    public void testRetrieveAccountReceivableList() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("018978783");
        contract.setMdmLevel("Global");
        contract.setNewQueryIndicator(true);
        AccountReceivableListResult result;
        long t0 = System.currentTimeMillis();
        try {
            result = service.retrieveAccountReceivableList(contract);
            MiscTest.print(result.getAccountReceivableList());
            System.out.println("totalCount = " + result.getTotalCount());

//            MiscTest.print(result.getAccountPayableList(), "payeeAddress:str");
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }

    @Test
    public void queryAR() throws Exception {
        MiscTest.sampleSiebelQuery(AccountReceivableDo.class,
//                "[LXK Partner Global DUNS] = '018978783' AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Payee')"
//                "[LXK Partner Global DUNS] = '018978783' AND ([LXK SAP Vendor Account Type] = 'Vendor')"
//                "[LXK Partner Global DUNS] = '018978783'" // 10 records
                " [mdmLevel5AccountId] <> '' AND  ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Payee')"

                , 10);
    }

    @Test
    public void queryAR_testData() throws Exception {
        MiscTest.sampleSiebelQuery(AccountReceivableDo.class,
                " ([mdmLevel1AccountId] <> '' OR [mdmLevel2AccountId] <> '' OR [mdmLevel3AccountId] <> '' " +
                 " OR [mdmLevel4AccountId] <> '' OR [mdmLevel5AccountId] <> '') " + "" +
                 " AND  ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Payee')"
                , 10);

        /*
        List<?> list1 = MiscTest.querySiebel(AccountReceivableDo.class,
                " ([mdmLevel1AccountId] <> '' OR [mdmLevel2AccountId] <> '' OR [mdmLevel3AccountId] <> '' " +
                 " OR [mdmLevel4AccountId] <> '' OR [mdmLevel5AccountId] <> '') " + "" +
                 " AND  ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] <> '')"
                , 10,0);
        MiscTest.print(list1, "vendorAccountType", "vendorClass", "mdmLevel1AccountId", "mdmLevel2AccountId", "mdmLevel3AccountId", "mdmLevel4AccountId", "mdmLevel5AccountId");
        */
    }


    @Test
    public void queryAR_payee() throws Exception {
        List<?> list2 = MiscTest.querySiebel(AccountReceivableDo.class,
                "[LXK SAP Vendor Account Type] = 'Payee' AND [LXK SAP Vendor Class] = 'SAP Vendor'"
                , 10,0);
        MiscTest.print(list2, "vendorAccountType", "vendorClass", "mdmLevel1AccountId", "mdmLevel2AccountId", "mdmLevel3AccountId", "mdmLevel4AccountId", "mdmLevel5AccountId");
    }


    @Test
    public void testRetrieveAccountPayableDetail() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
        contract.setServiceRequestNumber("1-478620759");
        contract.setMdmLevel("Global");
        contract.setMdmId("108023003");
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        System.out.println(str(r.getAccountPayableDetail()));
        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }

//   @Test
    public void queryAccountPayableDetailDoWithFee() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class,
                    "[partFee] <> '' OR [fulfillmentFee] <> '' OR [laborPayment] <> '' OR [additionalPayments] <> '' "
                    , 10);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }

    }

    @Test
    public void testRetrieveAccountPayableDetail2() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
        contract.setServiceRequestNumber("1-9959129995");
        contract.setMdmLevel("Global");
        contract.setMdmId("20026962");
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        System.out.println(str(r.getAccountPayableDetail()));
//        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }

    @Test
    public void testRetrieveAccountPayableDetail_QA() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
        contract.setServiceRequestNumber("1-8693413016");
        contract.setMdmLevel("Legal");
        contract.setMdmId("62682");
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        System.out.println(str(r.getAccountPayableDetail()));
        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }
    
    
    @Test
    public void testRetrieveAccountPayableDetail_QA2() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
        contract.setServiceRequestNumber("1-11060251802");
//        contract.setServiceRequestNumber("1-11060251731");
        contract.setMdmLevel("Legal");
        contract.setMdmId("60067");
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        System.out.println(str(r.getAccountPayableDetail()));
        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }    
    
    @Test
    public void testRetrieveAccountPayableDetail_QA_Defect6609() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
        contract.setServiceRequestNumber("1-10929747361");
//        contract.setServiceRequestNumber("1-11060251731");
        contract.setMdmLevel("Legal");
        contract.setMdmId("23259");
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        AccountPayableDetail d = r.getAccountPayableDetail();
        System.out.println(str(d));
        System.out.printf("totalPartFee = %s, totalFulfillmentFee = %s \n", 
                d.getTotalPartFee(), d.getTotalFulfillmentFee());
//        MiscTest.print(r.getAccountPayableDetail().getActivities(), "partNumber", "partDescription", "quantity", "partFee");
        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }        
       

    @Test
    public void testRetrieveAccountPayableDetail_Production_Defect5882() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
        contract.setServiceRequestNumber("1-10172076499");
        contract.setMdmLevel("Legal");
        contract.setMdmId("23259");
//        contract.setMdmId("61481");
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        System.out.println(str(r.getAccountPayableDetail()));
        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }
    
    // Portal: Invoice Detail 
    @Test
    public void testRetrieveAccountPayableDetail_Production_InvoiceIssues() throws Exception {
        AccountPayableDetailContract contract = new AccountPayableDetailContract();
//        contract.setServiceRequestNumber("1-10211967384");
        contract.setServiceRequestNumber("1-10211967726");
        contract.setMdmLevel("Legal");
        contract.setMdmId("23259");
//        contract.setMdmLevel("Siebel");
//        contract.setMdmId("1-BC68VN");
//        contract.setMdmLevel("Account");
//        contract.setMdmId("27711");
        
        AccountPayableDetailResult r = service.retrieveAccountPayableDetail(contract);
        System.out.println(str(r.getAccountPayableDetail()));
        MiscTest.print(r.getAccountPayableDetail().getActivities());
    }
    
    @Test
    public void testRetreivePaymentList_Production_InvoiceIssues() throws Exception {
        PaymentListContract c = new PaymentListContract();
        c.setSessionHandle(crmSessionHandle);
        c.setMdmId("23259");
        c.setMdmLevel("Legal");

        PaymentListResult r = service.retreivePaymentList(c);
        MiscTest.print(r.getPaymentList());
    }
    
    // Portal: Payments tab
    @Test
    public void testRetrievePaymentRequestList_Production() throws Exception {
        PaymentRequestListContract contract = new PaymentRequestListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("23259");
        contract.setMdmLevel("Legal");
        contract.setPaymentStatus("Both");

        PaymentRequestListResult result = service.retrievePaymentRequestList(contract);
        MiscTest.print(result.getPaymentRequestList());
    }
    

    @Test
    public void queryAccountPayableDetail_Production_Defect5882() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class,
                //"[LXK SR Account MDMAccount] = '23259' AND [SR Number] = '1-10172076499'"
                "[LXK SW SP Legal Entity Number] = '23259' AND [SR Number] = '1-10172076499'"
//                "[LXK SW SP Legal Entity Number] <> '' AND [SR Number] = '1-10172076499'"
//                "[LXK SW SP Legal Entity Number] <> '' AND [SR Number] <> ''"
//                "[LXK SR Account MDMAccount] = '23259'"
//                "[SR Number] = '1-10172076499'"
//                "[LXK SW SP Legal Entity Number] = '23259' AND [LXK SW SP MDM Account Number] = '1-10172076499'"
                , 10);
    }

    @Test
    public void queryAccountPayableDetail_misc() throws Exception {
        List<AccountPayableDetailDo> r = MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class,
                //"[LXK SR Account MDMAccount] = '23259' AND [SR Number] = '1-10172076499'"
//                "[LXK SW SP Legal Entity Number] = '23259' AND [SR Number] = '1-10172076499'"
//                "[LXK SW SP Legal Entity Number] <> '' AND [SR Number] = '1-10172076499'"
//                "[LXK SW SP Legal Entity Number] <> '' AND [SR Number] <> ''"
//                "[LXK SR Account MDMAccount] = '23259'"
//                   "[SR Number] = '1-10176759071'"
//                   "[SR Number] = '1-10211967384'"
                   "[SR Number] = '1-10929747361'"
//                "[LXK SW SP Legal Entity Number] = '23259' AND [LXK SW SP MDM Account Number] = '1-10172076499'"
                , 10);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        for (AccountPayableDetailDo item: r) {
            System.out.printf("mdmLevel1AccountId=%s, mdmLevel2AccountId=%s, mdmLevel3AccountId=%s, mdmLevel4AccountId=%s, mdmLevel5AccountId=%s\n", 
                    item.getMdmLevel1AccountId(),
                    item.getMdmLevel2AccountId(),
                    item.getMdmLevel3AccountId(),
                    item.getMdmLevel4AccountId(),
                    item.getMdmLevel5AccountId());
            MiscTest.print(item.getActivities());
        }
    }

    @Test
    public void queryAccountPayableDetail_mdmLevelAccountIds() throws Exception {
        for (String s : Arrays.asList("mdmLevel1AccountId",
                                      "mdmLevel2AccountId",
                                      "mdmLevel3AccountId",
                                      "mdmLevel4AccountId",
                                      "mdmLevel5AccountId")) {

            List<?> r = MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class, sformat("[%s] <> ''", s) , 1);
            assertEquals(1, r.size());
        }

    }

    @Test
    public void testRetrievePaymentRequestList() throws Exception {
        PaymentRequestListContract contract = new PaymentRequestListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Siebel");
        contract.setPaymentStatus("mock-paymentStatus");

        PaymentRequestListResult result = service.retrievePaymentRequestList(contract);
        MiscTest.print(result.getPaymentRequestList());
    }

    @Test
    public void testRetreivePaymentList() throws Exception {
        PaymentListContract c = new PaymentListContract();
        c.setSessionHandle(crmSessionHandle);
        c.setMdmId("mock-mdmId");
        c.setMdmLevel("Global");

        PaymentListResult r = service.retreivePaymentList(c);
        MiscTest.print(r.getPaymentList());
    }

    @Test
    public void testRetreivePaymentLineItemList() throws Exception {
        PaymentLineItemDetailsContract c = new PaymentLineItemDetailsContract();
        c.setSessionHandle(crmSessionHandle);
        c.setPaymentId("mock-paymentId");

        PaymentLineItemDetailsResult r = service.retreivePaymentLineItemList(c);
        MiscTest.print(r.getActivities());
    }

    @Test
    public void testRetrievePaymentDetails() throws Exception {
        PaymentDetailsContract c = new PaymentDetailsContract();
        c.setPaymentId("mock-paymentId");
//        c.setServicesUser(new ServicesUser());

        PaymentDetailsResult r = service.retrievePaymentDetails(c);
        MiscTest.print(r.getAuthorizedAccounts());
    }
}
