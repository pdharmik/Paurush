/*
 * Created on Jan 4, 2005
 */
package com.lexmark.commons;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





/**
 * @author sterrell
 * Cookie manager
 * 
 * Modified Chandra 05/18
 * When no cookies present, removed code which redirected to 
 *  other page and added code to create a cookie
 * 
 */
public class LexmarkIDManager {
    private static Logger log=Logger.getLogger(LexmarkIDManager.class.getName());
    
    /**.
	 * 
	 * This method renders the LexmarkId.
	 * 
	 * @param req 
	 * @param resp 
	 * @return String  
	 */
    public static String getLexmarkID(HttpServletRequest req, HttpServletResponse resp){
        String userSessionID=null;
        String id =null;
        
       String cust_id=req.getParameter("cust_id");
       String cust_sess_id=(String)req.getSession().getAttribute("cust_id");
       log.info("The value of the SessionId is="+cust_sess_id);
       log.info("The value of the Customer Id="+cust_id);
       //String sess_id=(String)req.getSession().getAttribute("cust_id");
       boolean b = new SessionUtility().sessionLookUp(cust_id,cust_sess_id);     
        
           if (b){
            log.info("$$$$$$$$$$$$$$$$$$ Customer_id found in the property file $$$$$$$$$$$$$$$");
            if(null==req.getSession(false)&&(null==id)){
                log.info("$$$$$$$$$$$$$ Case1 $$$$$$$$$$$$");
                HttpSession session = req.getSession();
                id = session.getId();
                return id;
            }
            else if(null!=id){
                log.info("$$$$$$$$$$$$ Case 2 $$$$$$$$$$");
               return id;
            }
            else {
                log.info("$$$$$$$$$$$$ Case 3 $$$$$$$$$$$$");
                id = req.getSession().getId();
                return id;
            }
        }
        else
        {
        boolean cookieexists= false;
        Cookie[] cookies = req.getCookies();
          if(cookies==null) {
            log.info("No cookies.");
            HttpSession session = req.getSession(true);
            userSessionID = session.getId();
            log.info("making cookie:LEXMARKUSERID:"+userSessionID);
            Cookie lexmarkcookie=new Cookie("LEXMARKUSERID",userSessionID);
            lexmarkcookie.setMaxAge(120000304);
            lexmarkcookie.setPath("/");
            resp.addCookie(lexmarkcookie);
            cookieexists = true;
            return userSessionID;
        }
        for(int i=0;i<cookies.length;i++){
            log.info("Found cookie \""+cookies[i].getName()+"\" with value \""+cookies[i].getValue()+"\"");
            if((cookies[i].getName()).equals("JSESSIONID")){
                userSessionID = cookies[i].getValue();
                log.info("Found JSESSIONID:"+userSessionID);
            }
            if((cookies[i].getName()).equals("LEXMARKUSERID")){
                String lexID=cookies[i].getValue();
                //Weed out bogus cookies
                if(lexID!=null && lexID.length()>0 && !lexID.equals("null")) {
                    userSessionID = lexID;
                    log.info("Found LEXMARKUSERID:"+userSessionID);
                    log.info("LEXMARKUSERID expires in "+cookies[i].getMaxAge()+" seconds.");
                    cookieexists = true;
                    break;
                }
            }
        

        if(!cookieexists){
            if(userSessionID == null) {
            	userSessionID = req.getSession(true).getId();
            }
            log.info("making cookie:LEXMARKUSERID:"+userSessionID);
            Cookie lexmarkcookie=new Cookie("LEXMARKUSERID",userSessionID);
            lexmarkcookie.setMaxAge(120000304);
            lexmarkcookie.setPath("/");
            resp.addCookie(lexmarkcookie);
        }
        }
        return userSessionID;

    
    }
}
}