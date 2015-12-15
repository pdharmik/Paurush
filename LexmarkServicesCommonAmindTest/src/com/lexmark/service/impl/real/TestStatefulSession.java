/*******************************************************************************
 * Unpublished work, copyright (c) aMind Solutions LLC 2008-2010. All rights 
 * reserved. This software code and any commented materials or notations 
 * (&#xfffd;Materials&#xfffd;) constitute proprietary and confidential information of aMind
 * Solutions LLC. The Materials (and any  or material derived therefrom) may not 
 * be reproduced or used, and may not be disclosed or otherwise  made available 
 * to any person, in whole or in part, except in accordance with a written 
 * agreement with aMind or as otherwise expressly authorized in writing by aMind
 ******************************************************************************/

/**
 * 
 */
package com.lexmark.service.impl.real;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TestStatefulSession extends TestCase {
	protected static final Log logger = 
		LogFactory.getLog(TestStatefulSession.class);
	PartnerActivityListTest activityTest = new PartnerActivityListTest();
	
    /**
     * Used in testCleanUpSessionsMultiThreaded. Specifies the number of threads. Value should
     * be set at less than 10 to avoid exceeding siebel max tasks.
     */
    private static final int THREADS_COUNT = 3;
    private int threadCount = 0;


	/**
	 * @param name
	 */
	public TestStatefulSession(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	/**
	 * A helper class to handle thread errors to JUnit. Makes JUnit fail rather than just a message in console.
	 * @author robink
	 *
	 */
	public class AsynchTester{
			    private Thread thread;
			    private volatile Error error;
			    private volatile RuntimeException runtimeExc;
			 
			    public AsynchTester(final Runnable runnable) {
			        thread = new Thread(new Runnable() {
			            public void run() {
			                try {
			                    runnable.run();
			                } catch (Error e) {
			                    error = e;
			                } catch (RuntimeException e) {
			                    runtimeExc = e;
			                }
			            }
			        });
			    }
			 
			    public void start() {
			        thread.start();
			    }
			 
			    public void test() throws InterruptedException {
			        thread.join();
			        if (error != null)
			            throw error;
			        if (runtimeExc != null)
			            throw runtimeExc;
			    }
			}
  	
  	/**
  	 * Runnable similar to testCleanUpIdleStatefulSessions. Attaches, Detaches, sleeps until siebel expires, then tests for expected error.
  	 * Used in testCleanUpSessionsMultiThreaded
  	 * @author robink
  	 *
  	 */
 	private class AmindServiceRunnable implements Runnable 
 	{
		public void run()
		{
				try
				{

		/*			System.out.println("sleeping");
					Thread.sleep(10); //sleep in ms, greater than siebelMaxIdleTime
					System.out.println("done sleeping");*/
					System.out.println(Thread.currentThread().getName() + " acuired");

					try {
							activityTest.testRetrieveActivityList();

					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					activityTest.tearDown();
				}
				catch(Exception ie)
				{
                  System.out.println("interrupted by another thread");
				}
			}
 	}
  	/**
  	 * Runs concurrent thread tests on clean-up.
  	 * @throws InterruptedException
  	 */
  	public void testCleanUpSessionsMultiThreaded() throws InterruptedException {

  		AsynchTester[] testers = new AsynchTester[THREADS_COUNT];
		activityTest.setUp();
	    for(int i = 0;i < THREADS_COUNT; i++) {
	        testers[i] = new AsynchTester(new AmindServiceRunnable() );
	        testers[i].start();
	    }
	 
	    for(AsynchTester tester : testers) {
	        tester.test();
	    }
		activityTest.tearDown();
	}
  
    

}
