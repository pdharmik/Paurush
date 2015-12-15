package com.lexmark.services.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
 
/**
 * is used to intercept all the requests
 * for fetching the session id from it and put the session id to the Log4j Mapped
 * Diagnostic Context (MDC), so that the session id could be used for
 * differentiating log messages.
 *
 *
 */
public class AddSessionIdToLogFilter implements Filter {
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
 
        try {
        	if (request instanceof HttpServletRequest) { 
                HttpServletRequest httpRequest = (HttpServletRequest) request; 
                String sessionID = httpRequest.getSession().getId(); 
                ThreadContext.put("SessionID", sessionID); 
            }  
            chain.doFilter(request, response);
        } finally {
//            MDC.remove("SessionID");
        }
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
 
}