package com.lexmark.util;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lexmark.domain.LexmarkTransaction;

public class LexmarkTransactionTest extends TestCase {
 private static final Log logger = LogFactory.getLog(LexmarkTransactionTest.class);	

	class LexmarkTestThread extends Thread {
          long sleepTime;
		 public LexmarkTestThread(long sleepTime){
		   this.sleepTime = sleepTime;
		 }
         public void run() {
		    try{
				testLexmarkTransactionData(sleepTime);
			 }catch (Exception e)
			 {
			   logger.error("We got little problem here");
			 }
         }
     }
	
	

	// ********************************************************** //
	public void testRun() {
		LexmarkTestThread p1 = new LexmarkTestThread(5000);
        p1.start();
	    
		LexmarkTestThread p2 = new LexmarkTestThread(3000);
        p2.start();
		
		LexmarkTestThread p3 = new LexmarkTestThread(6000);
        p3.start();
	}

	
	private void testLexmarkTransactionData(long sleepTime) throws Exception {
	    
		PerformanceTracker.startTracking("Siebel","requestInstallBaseLocation",
			"869D9","Alex");		
        logger.debug("the sleep time is " + sleepTime + " milliseconds");			
		Thread.sleep(sleepTime);
		PerformanceTracker.endTracking();
	}

	// ********************************************************** //
   public void testGetLexmarkTransactionData() throws Exception {
		logger.debug("The session has been closed and jdbc connection has been released, let's see what we got in the db");
		logger.debug("let's wait for a second");
		Thread.sleep(10000);
		Collection result = PerformanceTracker.getLexmarkTransaction("Alex");
         logger.debug("the result size is " +result.size());	
		
        for (Iterator it = result.iterator(); it.hasNext();) {
			LexmarkTransaction lex = (LexmarkTransaction) it.next();
			  
			  logger.debug("the ID is " + lex.getId());
			  logger.debug("the accountID of " + lex.getLoginUser() + " is " + lex.getAccountID());
			  logger.debug("the invoker is " + lex.getInvoker());
			  logger.debug("the targetSystem is " + lex.getTargetSystem());
			  logger.debug("the webServiceName is " + lex.getWebServiceName());
			  logger.debug("the startTime is " + lex.getStartTime());
			  logger.debug("the endTime is " + lex.getEndTime());
			  logger.debug("the time duration used is " + lex.getDuration());
			  logger.debug("---------------------------------------------------------------");
			}
	}

}
