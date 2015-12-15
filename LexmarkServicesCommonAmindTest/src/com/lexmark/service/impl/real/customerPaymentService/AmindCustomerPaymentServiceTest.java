package com.lexmark.service.impl.real.customerPaymentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.domain.AccountCustomerReceivable;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.result.AccountCustomerReceivableListResult;
import com.lexmark.service.impl.real.AmindServiceTest;

public class AmindCustomerPaymentServiceTest extends AmindServiceTest {
	
    static AmindCustomerPaymentService service = new AmindCustomerPaymentService();

    @BeforeClass
    public static void setUp() throws Exception {
        service.setStatelessSessionFactory(statelessSessionFactory);
    }
    
    @Test
    public void testRetrieveCustomerAccountReceivableList() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("018978783");
        contract.setMdmLevel("Global");
        contract.setNewQueryIndicator(true);
        AccountCustomerReceivableListResult result;
        long t0 = System.currentTimeMillis();
        try {
            result = service.retrieveAccountReceivableList(contract);
//            MiscTest.print(result.getAccountReceivableList());
            System.out.println("totalCount = " + result.getTotalCount());
            System.out.println("Size = " + result.getAccountReceivableList().size());
            
            for (AccountCustomerReceivable acc : result.getAccountReceivableList()) {
				System.out.println(acc.getCustomerNumber());
			}

//            MiscTest.print(result.getAccountPayableList(), "payeeAddress:str");
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }

    @Test
    public void testRetrieveCustomerAccountReceivableList_defect9735() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        contract.setMdmLevel("Legal");
        contract.setMdmId("43800");
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        
        Map<String,Object> sortCriteria = new HashMap<String,Object>();
        sortCriteria.put("accountName", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
        Map<String,Object> filterCriteria = new HashMap<String, Object>();
        filterCriteria.put("accountName", "EveryThing");
        contract.setFilterCriteria(filterCriteria);
        
        contract.setSessionHandle(crmSessionHandle);
        
        AccountCustomerReceivableListResult result = service.retrieveAccountReceivableList(contract);
        
        System.out.println(result.getAccountReceivableList() == null ? "Null" : "Size: " + result.getAccountReceivableList().size());
        System.out.println("Total count: " + result.getTotalCount());
        
        List<AccountCustomerReceivable> accountReceivableList = result.getAccountReceivableList();
        
        for (AccountCustomerReceivable accountCustomerReceivable : accountReceivableList) {
			System.out.println("Account name: " + accountCustomerReceivable.getAccountName());
		}
        
    }
    
    
    @Test
    public void testRetrieveCustomerAccountReceivableList_defect9798() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        contract.setMdmLevel("Legal");
        contract.setMdmId("58971");
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        
        Map<String,Object> sortCriteria = new HashMap<String,Object>();
        sortCriteria.put("accountName", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
        contract.setSessionHandle(crmSessionHandle);
        
        AccountCustomerReceivableListResult result = service.retrieveAccountReceivableList(contract);
        
        System.out.println(result.getAccountReceivableList() == null ? "Null" : "Size: " + result.getAccountReceivableList().size());
        System.out.println("Total count: " + result.getTotalCount());
        
        List<AccountCustomerReceivable> accountReceivableList = result.getAccountReceivableList();
        
        for (AccountCustomerReceivable accountCustomerReceivable : accountReceivableList) {
			System.out.println("Account name: " + accountCustomerReceivable.getAccountName());
			System.out.println("Bill to:" + accountCustomerReceivable.getBillTo());
			System.out.println("Sold to: " + accountCustomerReceivable.getCustomerNumber());
			System.out.println("Customer number: " + accountCustomerReceivable.getCustomerNumber());
			System.out.println("--------------------------------");
		}
        
    }
    
    
    @Test
    public void testRetrieveCustomerAccountReceivableList_defect11062() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        contract.setMdmLevel("Legal");
        contract.setMdmId("110899");
//        contract.setNewQueryIndicator(true);
//        contract.setStartRecordNumber(0);
//        contract.setIncrement(40);
        
        Map<String,Object> sortCriteria = new HashMap<String,Object>();
        sortCriteria.put("accountName", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
        contract.setSessionHandle(crmSessionHandle);
        
        AccountCustomerReceivableListResult result = service.retrieveAccountReceivableList(contract);
        
        System.out.println(result.getAccountReceivableList() == null ? "Null" : "Size: " + result.getAccountReceivableList().size());
        System.out.println("Total count: " + result.getTotalCount());
        
    }

    @Test
    public void testRetrieveCustomerAccountReceivableList_defect11062_newIssue() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        
        long t = System.currentTimeMillis();
        
        contract.setMdmLevel("Global");
        contract.setMdmId("315000554");
//        contract.setNewQueryIndicator(true);
//        contract.setStartRecordNumber(0);
//        contract.setIncrement(40);
        contract.setSessionHandle(crmSessionHandle);
        
//        Map<String,Object> sortCriteria = new HashMap<String,Object>();
//        sortCriteria.put("accountName", "ASCENDING");
//        contract.setSortCriteria(sortCriteria);
        
        AccountCustomerReceivableListResult result = service.retrieveAccountReceivableList(contract);
        
        System.out.println(result.getAccountReceivableList() == null ? "Null" : "Size: " + result.getAccountReceivableList().size());
        System.out.println("Total count: " + result.getTotalCount());
        
        
        System.out.println("Exac time: " + (System.currentTimeMillis() - t) / 1000.0);
        
    }
    
    
    @Test
    public void testRetrieveCustomerAccountReceivableList_defect11048() throws Exception {
        AccountReceivableListContract contract = new AccountReceivableListContract();
        
        
        contract.setMdmLevel("Legal");
        contract.setMdmId("61630");
        contract.setSessionHandle(crmSessionHandle);
        
        Map<String,Object> sortCriteria = new HashMap<String,Object>();
        sortCriteria.put("accountName", "ASCENDING");
        contract.setSortCriteria(sortCriteria);
        
        AccountCustomerReceivableListResult result = service.retrieveAccountReceivableList(contract);
        
        System.out.println("Accounts size: " + result.getAccountReceivableList() == null ? "Null" : "Accounts size: " + result.getAccountReceivableList().size());
//        System.out.println("Total count: " + result.getTotalCount());
        
        for (AccountCustomerReceivable account : result.getAccountReceivableList()) {
			System.out.println("----------------");
			System.out.println("Sold to: " + account.getSoldTo());
			System.out.println("Bill to: " + account.getBillTo());
		}
        
    }
    
    @Test
    public void testRetrieveCustomerAccountReceivableList_defect16503() throws Exception {
    	AccountReceivableListContract contract = new AccountReceivableListContract();
    	contract.setMdmLevel("Global");
    	contract.setMdmId("489334806");
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String,Object> sortCriteria = new HashMap<String,Object>();
    	sortCriteria.put("accountName", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	contract.setIncrement(40);
    	
    	AccountCustomerReceivableListResult result = service.retrieveAccountReceivableList(contract);
    	System.out.println(result);
    }
    
    @Test
    public void testRetrieveAccountAgreementSoldTo() throws Exception {
    	AccountAgreementSoldToContract contract = new AccountAgreementSoldToContract();
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AccountAgreementSoldToResult result = service.retrieveMPSB2BList(contract);
    	
    	logger.debug(result.getMpsB2bList());
    }
    
    @Test
    public void testRetrieveMPSB2BList_PunchOutAccountInformation() throws Exception {
    	AccountAgreementSoldToContract contract = new AccountAgreementSoldToContract();
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AccountAgreementSoldToResult result = service.retrieveMPSB2BList(contract);
    	
    	logger.debug(result.getMpsB2bList());
    }
    
    @Test
    public void testRetrieveMPSB2BList_Punchout_AccountAgreemnetType() throws Exception {
    	AccountAgreementSoldToContract contract = new AccountAgreementSoldToContract();
    	contract.setAccountType("KAISER"); //REPUBLIC
    	contract.setIncrement(0);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(false);
    	contract.setSessionHandle(crmSessionHandle);
    	
    	AccountAgreementSoldToResult result = service.retrieveMPSB2BList(contract);
    	
    	logger.debug(result.getMpsB2bList());
    }

    
}
