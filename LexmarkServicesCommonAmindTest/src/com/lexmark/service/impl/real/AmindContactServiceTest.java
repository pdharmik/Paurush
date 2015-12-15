package com.lexmark.service.impl.real;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.StatefulSessionFactory;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.FavoriteResult;

/**
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-05
 */
public class AmindContactServiceTest extends AmindServiceTest {

    static AmindContactService contactService = null;

    @BeforeClass
    public static void setUp() {
        contactService = new AmindContactService();
        contactService.setSessionFactory(AmindServiceTest.statelessSessionFactory);
    }

    @Test
    public void testRetrieveContactList() throws Exception {
        ContactListContract c = new ContactListContract();
        c.setContactId("1-1GXO");
        c.setMdmId("mock-MdmId");
        c.setMdmLevel("Global");
        c.setSessionHandle(crmSessionHandle);
        
        ContactListResult r = contactService.retrieveContactList(c);
        System.out.println("=================");
        MiscTest.showStructure(r);
        System.out.println("=================");
    }
    
    @Test
    public void testRetrieveContactList_QA() throws Exception {
        ContactListContract c = new ContactListContract();
        c.setContactId("mock-contactId");
        c.setMdmId("1-P7PY6O");
        c.setMdmLevel("Siebel");
//        c.setStartRecordNumber(0);
//        c.setIncrement(309);
        c.setSessionHandle(crmSessionHandle);
        c.setNewQueryIndicator(true);
        
        ContactListResult r = contactService.retrieveContactList(c);
        MiscTest.print(r.getContacts(), "contactId", "alternatePhone", "address");
        MiscTest.print("totalCount = ", r.getTotalCount());
    }    
    
    
    @Test
    public void testRetrieveContactList_fav() throws Exception {
        ContactListContract c = new ContactListContract();
        c.setContactId("1-1GXO");
        c.setMdmId("mock-MdmId");
        c.setMdmLevel("Global");
        c.setSessionHandle(crmSessionHandle);
        c.setFavoriteFlag(true);
        
        ContactListResult r = contactService.retrieveContactList(c);
        System.out.println("=================");
        MiscTest.showStructure(r);
        System.out.println("=================");
    }
    
    
    @Test
    public void testRetrieveContactList_testdata() throws Exception {
        ContactListContract c = new ContactListContract();
        c.setContactId("1-1DTJMA");
        c.setMdmId("mock-MdmId");
        c.setMdmLevel("Global");
        c.setSessionHandle(crmSessionHandle);
        
        ContactListResult r = contactService.retrieveContactList(c);
        System.out.println("=================");
        MiscTest.showStructure(r);
        System.out.println("=================");
    }

    @Test
    public void testUpdateUserFavoriteContact() throws Exception {
        FavoriteContract c = new FavoriteContract();
        c.setContactId("mock-contactId");
        c.setFavoriteContactId("mock-favoriteContactId");
        FavoriteResult r = contactService.updateUserFavoriteContact(c);
         
    }
    @Test
    public void testUpdateUserFavoriteContact_defect9899() throws Exception {
    	FavoriteContract c = new FavoriteContract();
    	c.setContactId("1-6DV03JT");
    	c.setFavoriteFlag(true);
    	c.setFavoriteContactId("1-3QHPBY3");
    	FavoriteResult r = contactService.updateUserFavoriteContact(c);
    	 
    }
    
    @Test
    public void testUpdateUserFavoriteContact_Prod_defect12777() throws Exception {
    	FavoriteContract c = new FavoriteContract();
    		c.setContactId("1-80TDME");
    		c.setFavoriteFlag(false);
    		c.setFavoriteContactId("1-40RXY6A");
    		FavoriteResult r = contactService.updateUserFavoriteContact(c);
    		logger.debug(r);
    }
    
    @Test
    public void testUpdateUserFavoriteContact_Prod_Sequental_defect12777() throws Exception {
    	FavoriteContract c = new FavoriteContract();
    	boolean flag = true;
    	int count = 0;
    	for (int i = 0; i < 5; i++) {
    		flag = !flag;
    		count++;
    		c.setContactId("1-80TDME");
    		c.setFavoriteFlag(flag);
    		c.setFavoriteContactId("1-40RXY6A");
    		FavoriteResult r = contactService.updateUserFavoriteContact(c);
    		logger.debug(r);
    		logger.debug("LoopCount: " + count);
//    		Thread.sleep(5000);
		}
    }
    
    @Test
    public void testUpdateUserFavoriteContact_defect12777() throws Exception {
    	FavoriteContract c = new FavoriteContract();
//		c.setContactId("1-80TDME");
//		c.setFavoriteFlag(flag);
//		c.setFavoriteContactId("1-40RXY6A");
//		FavoriteResult r = contactService.updateUserFavoriteContact(c);
//		logger.debug(r);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					FavoriteContract c = new FavoriteContract();
					c.setContactId("1-80TDME");
					c.setFavoriteFlag(true);
					c.setFavoriteContactId("1-40RXY6A");
					FavoriteResult r;
					try {
						r = contactService.updateUserFavoriteContact(c);
						System.out.println("Thread1 R U N I N G");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).get();

			executor.submit(new Runnable() {

				@Override
				public void run() {
					FavoriteContract c = new FavoriteContract();
					c.setContactId("1-80TDME");
					c.setFavoriteFlag(false);
					c.setFavoriteContactId("1-40RXY6A");
					FavoriteResult r;
					try {
						Thread.sleep(3000);
						r = contactService.updateUserFavoriteContact(c);
						System.out.println("Thread2 R U N I N G");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).get();
		}

		executor.shutdown();

		System.out.println("End");
    }
    
    
    @Test
    public void testRetrieveContactList_AlternatePhone_Defect7564() throws Exception {
        ContactListContract contract = new ContactListContract();
        contract.setLocale(new Locale("en_US"));
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-YFNVUX");
        contract.setContactId("1-5HR5CYV");
        contract.setFavoriteFlag(false);
        contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"lastName", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
//				"country", "LXK MPS Personal Country"
//				,
				"alternatePhone", "201"
//				,
//				"lastName", "LXK MPS Personal Country"
				));
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
        
        ContactListResult result = contactService.retrieveContactList(contract);
        for (AccountContact contr : result.getContacts()) {
        	System.out.println("-------------");
			System.out.println("Alternate phone: "+contr.getAlternatePhone());
			System.out.println("Work phone: "+contr.getWorkPhone());
		}
        MiscTest.print(result.getContacts(), "contactId", "alternatePhone", "address");
        MiscTest.print("totalCount = ", result.getTotalCount());
    }    
    
    
    @Test
    public void testRetrieveContactList_MPS_QA_defect7779() throws Exception {
        ContactListContract c = new ContactListContract();
        c.setContactId("1-5HR5CYV");
        c.setMdmId("1-1-YFNVUX");
        c.setMdmLevel("Siebel");        
        c.setNewQueryIndicator(true);
        c.setStartRecordNumber(0);
        c.setIncrement(40);
        c.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("firstName", "ASCENDING"));        
        c.setSessionHandle(crmSessionHandle);
        
        ContactListResult r = contactService.retrieveContactList(c);
        MiscTest.print(r.getContacts(), "contactId", "alternatePhone", "address");
        MiscTest.print("totalCount = ", r.getTotalCount());
    }
    
    @Test
    public void testRetrieveContactList_MPS_QA_defect7986() throws Exception {
        ContactListContract contract = new ContactListContract();
        contract.setLocale(new Locale("en_US"));
        contract.setContactId("1-5VMXS0R");
        contract.setMdmId("1-YFNVUX");
        contract.setMdmLevel("Siebel");    
        contract.setFavoriteFlag(false);
        contract.setLoadAllFlag(false);
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(100);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("emailAddress", "DESCENDING"));       
        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("emailAddress", "a"));        
        contract.setSessionHandle(crmSessionHandle);
        
        ContactListResult result = contactService.retrieveContactList(contract);
        
//        System.out.println("Start");
        
        for (AccountContact accountContract : result.getContacts()) {
			System.out.println("Email Address: " + accountContract.getEmailAddress());
		}
        
//        System.out.println("End");
        
        MiscTest.print(result.getContacts(), "contactId", "alternatePhone", "address");
        MiscTest.print("totalCount = ", result.getTotalCount());
    }
    
    
    @Test
    public void testRetrieveContactList_QA_defect10834() throws Exception {
    	ContactListContract contract = new ContactListContract();
    	contract.setSessionHandle(crmSessionHandle);
    	contract.setLocale(new Locale("en_US"));
    	contract.setContactId("1-7C6YYWL");
    	contract.setMdmId("63019");
    	contract.setMdmLevel("Legal");    
    	contract.setFavoriteFlag(false);
    	contract.setLoadAllFlag(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("lastName", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("county", "cherokee");
		contract.setFilterCriteria(filterCriteria );
    	ContactListResult result = contactService.retrieveContactList(contract);
    	for (AccountContact accountContact : result.getContacts()) {
			System.out.println("county: " + accountContact.getAddress().getCounty());
		}
    }
    
    
    @Test
    public void testRetrieveContactList_ContactListContract() throws Exception {
        ContactListContract contract = new ContactListContract();
        contract.setLocale(new Locale("en_US"));
        contract.setContactId("1-73O5NHR");
        contract.setMdmId("30145");
        contract.setMdmLevel("Account");    
        contract.setFavoriteFlag(false);
        contract.setLoadAllFlag(false);
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("lastName", "ASCENDING"));       
        contract.setSessionHandle(crmSessionHandle);
        
        ContactListResult result = contactService.retrieveContactList(contract);
        
        List<AccountContact> contacts = result.getContacts();
        int totalCount = result.getTotalCount();
        
        System.out.println("Size: " + contacts.size());
        System.out.println("Total count: " + totalCount);
    }
    
    @Test
    public void testRetrieveContactList_INC0091846() throws Exception {
    	ContactListContract contract = new ContactListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setContactId("1-3LEPYGX");
    	contract.setMdmId("330871646");
    	contract.setMdmLevel("Global");    
    	contract.setFavoriteFlag(false);
    	contract.setLoadAllFlag(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("lastName", "ASCENDING"));       
    	contract.setSessionHandle(crmSessionHandle);
    	
    	ContactListResult result = contactService.retrieveContactList(contract);
    	List<AccountContact> contacts = result.getContacts();
    	int totalCount = result.getTotalCount();
    	System.out.println("Size: " + contacts.size());
    	System.out.println("Total count: " + totalCount);
    }
    
    @Test
    public void testRetrieveContactList_defect18674() throws Exception {
    	ContactListContract contract = new ContactListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setContactId("1-16X93QX");
    	contract.setMdmId("1-DZ9CDJ");
    	contract.setMdmLevel("Siebel");    
    	contract.setFavoriteFlag(false);
    	contract.setLoadAllFlag(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("lastName", "ASCENDING"));       
    	contract.setSessionHandle(crmSessionHandle);
    	
    	ContactListResult result = contactService.retrieveContactList(contract);
    	List<AccountContact> contacts = result.getContacts();
    	int totalCount = result.getTotalCount();
    	System.out.println("Size: " + contacts.size());
    	System.out.println("Total count: " + totalCount);
    }

    @Test
    public void testUpdateUserFavoriteContact_defect12390() throws Exception {
        FavoriteContract c = new FavoriteContract();
        c.setContactId("1-80J75U7");
        c.setFavoriteContactId("1-711HEMT");
        c.setFavoriteFlag(false);
//        c.setSessionHandle(crmSessionHandle);
        FavoriteResult r = contactService.updateUserFavoriteContact(c);
        System.out.println("result = " + r.isResult()); 
    }
}
