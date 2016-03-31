package com.lexmark.hook.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.util.ControllerUtil;

public class KaiserSetup implements Filter{

	private static Logger LOGGER = LogManager.getLogger(KaiserSetup.class);
	private static String KAISER_ACNT;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		LOGGER.debug(" IN here ... Filter ");
		 
		HttpServletRequest httpReq=(HttpServletRequest) request;
		HttpSession session=httpReq.getSession();
		
		if(session.getAttribute(PunchoutConstants.ARIBA_PARAMS)==null){
			LOGGER.debug(" IN here ... Filter ARIBA");
			LOGGER.info("This request is not from ARIBA... Need to show normal Page.." );
			Map<String,String> aribaParamMap=new HashMap<>();
		    aribaParamMap.put("fromAriba", String.valueOf(false));
		    session.setAttribute(PunchoutConstants.ARIBA_PARAMS, aribaParamMap);
		    aribaParamMap.put("supplierId", KAISER_ACNT);
		    aribaParamMap.put("isKaiser", String.valueOf(true));		
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		KAISER_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.kaiser");
		
	}

}
