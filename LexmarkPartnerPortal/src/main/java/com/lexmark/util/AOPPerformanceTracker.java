package com.lexmark.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.LexmarkTransaction;

public class AOPPerformanceTracker {
	private static final  Logger logger = LogManager.getLogger(AOPPerformanceTracker.class);
	private String flag;
	private ThreadLocal<LexmarkTransaction>  localTransaction = new ThreadLocal<LexmarkTransaction>();
	
	public void setFlag(String flag){
		this.flag = flag;
	}
	
	public String getFlag(){
		return this.flag;
	}
	
	public void logBefore(JoinPoint joinPoint) {
		if(trackingNotRequired())
			return;
		String methodName=joinPoint.getSignature().getName();
		String invoker = joinPoint.getTarget().getClass().getName();

		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(
				LexmarkConstants.TARGET_SYSTEM_SIEBEL,
				methodName,
				"", 
				"");		
		lexmarkTran.setInvoker(invoker);
		
		localTransaction.set(lexmarkTran);
		logger.debug("-- Before Performance Tracking for " + methodName);
	}
	
	public void logAfter(JoinPoint joinPoint) {
		if(trackingNotRequired())
			return;
		LexmarkTransaction lexmarkTran = localTransaction.get();
		if(lexmarkTran != null) {
			PerformanceTracker.endTracking(lexmarkTran);

			logger.debug("-- END of Performance Tracking for " + lexmarkTran.getWebServiceName());
			localTransaction.remove();
		}
	}
	
	private boolean trackingNotRequired(){
		if(flag ==null || flag.equalsIgnoreCase("off"))
			return true;
		else
			return false;
	}
}
