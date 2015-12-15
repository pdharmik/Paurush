package com.lexmark.services;

import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.service.impl.real.jdbc.UserServiceImpl;

public class UserServiceTest {

    public static void main(String[] args) throws Exception {
//        Logger.getLogger(UserServiceTest.class.getName()).info("Testing Logger");
//        Registry registry = LocateRegistry.createRegistry(1099);
//        registry.bind("java:", new UserServiceLdapContainer());
//        System.getProperties().setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
//        String ldapurl = "ldap://157.184.93.35:389/o=Lexmark";
//        String jdbcJndiUrl = "jdbc/userservice";
//        String ldapUserId ="uid=ldapapi,ou=System Accounts,o=lexmark";
//        String ldapPassword ="testtest";
//        UserService us = new UserService(ldapUserId,ldapPassword,ldapurl,jdbcJndiUrl);
//       //get user by email address
//       User userbean = us.getUserByEmail("t003@test.com");//Uncomment this line and comment below line,to get user by email id 
//     //   User userbean = us.getUserByCustId("BP10001347");
//        if(userbean != null & userbean.getUserid()!=null) {
//	    	
//	    	  printUserDetails(userbean);
//	      }else{
//	    	  

//	      }
    	UserServiceImpl userServiceImpl = new UserServiceImpl();
    	LdapUserDataContract ldapUserDataContract = new LdapUserDataContract();
    	ldapUserDataContract.setEmailAddress("portal1@lexmark.com");
    	LdapUserDataResult ldapUserDataResult = userServiceImpl.retrieveLdapUserData(ldapUserDataContract);
    	
    	printUserDetails(ldapUserDataResult);
	}
	//Added by raghuram
    
    public static void printUserDetails(LdapUserDataResult ldapUserDataResult){
    	//printStr("RemoteUser", userbean.getRemoteUser());
        printStr("Email", ldapUserDataResult.getEmailAddress());
        printStr("First Name", ldapUserDataResult.getFirstName());
        printStr("Last Name", ldapUserDataResult.getLastName());
        printStr("User Number", ldapUserDataResult.getUserNumber());
        printStr("MDM Id", ldapUserDataResult.getMdmId());
        printStr("MDM Level", ldapUserDataResult.getMdmLevel());
        
        printStr("Work Phone", ldapUserDataResult.getWorkPhone());
        for(String role: ldapUserDataResult.getUserRoles()){
            printStr("Role: ", role);

        }
        
		printStr("Seibel Contact Id", ldapUserDataResult.getContactId());
        
    	
    	
    	
    }

    private static void printStr(String name, String value) {
        }

   


}
