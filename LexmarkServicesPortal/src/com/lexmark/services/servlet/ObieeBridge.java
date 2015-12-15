package com.lexmark.services.servlet;

import com.lexmark.contract.ObieeConnectionData;
import com.lexmark.util.JndiLookupUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Servlet implementation class ObieeBridge
 */
public class ObieeBridge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ObieeBridge.class);
	private static ObieeConnectionData obieeConnectionData = getConfigProperties();
	private static String SERVICE_END_POINT=obieeConnectionData.getHostName();

	   private static boolean bEnableLog;
	  
	 /** Initializes the servlet.
	 * @param config 
	 * @throws ServletException 
	 */
	public void init(ServletConfig config) throws ServletException 
	   {
	      super.init(config);
	      String sEnableLog = config.getInitParameter("com.siebel.analytics.EnableLog");
	      bEnableLog = (sEnableLog != null &&
	         (
	         sEnableLog.compareToIgnoreCase("Y") == 0 ||
	         sEnableLog.compareToIgnoreCase("YES") == 0 ||
	         sEnableLog.compareToIgnoreCase("true") == 0));
	        
	   }
	    
	   /** Destroys the servlet.
	    */
	   public void destroy() 
	   {
	        
	   }
	    
	   /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	    * @param request servlet request
	    * @param response servlet response
	    */
       private static final String[][] STATIC_RESOURCES = new String[][]{
               new String[] {"copyiframe.js","text/javascript"},
               new String[] {"menus.css","test/css"},
       };
	   /**
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException 
	   {
		      String strURL  = decodeURL(request);
		      //This check will make sure that no other end point is retrieved except for our end point
		      if ( strURL.indexOf(SERVICE_END_POINT) == 0 ) {
		 		   //Some files from OBIEE cause issues with portal, this re-routes them to locally "fixed" copies.
                  for(String[] resource : STATIC_RESOURCES) {
                      if(strURL.indexOf(resource[0])>=0) {
                          logger.debug("Serving static resource: "+ resource[0]+" for "+strURL);
                          serveStatic(response, resource[1], resource[0]);
                          return;
                      }
                  }
                  HttpURLConnection urlCon = forwardRequest(request);
                  forwardResponse(response,urlCon);
		    	}

	   }
        /**
         * @param response 
         * @param mime 
         * @param path 
         * @throws IOException 
         */
        private void serveStatic(HttpServletResponse response, String mime, String path) throws IOException{
            response.setContentType(mime);
            OutputStream out = response.getOutputStream();
            InputStream is = ObieeBridge.class.getResourceAsStream(path);
            try {
                copyStreams(out,is);
                is.close();
            } finally {
                out.close();
            }
        }

        /**
         * @param os 
         * @param is 
         * @throws IOException 
         */
        private void copyStreams(OutputStream os, InputStream is) throws IOException{
            byte[] buffer = new byte[4096];
            while(true) {
                int read = is.read(buffer);
                if(read<0){ return;}
                os.write(buffer,0,read);
            }
        }

	 /** Handles the HTTP <code>GET</code> method. 
	 * @param request servlet request 
	 * @param response servlet response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException 
	   {
	      logger.debug("doGet");
	      processRequest(request, response);
	   }
	    
	 /** Handles the HTTP <code>POST</code> method. 
	 * @param request servlet request 
	 * @param response servlet response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException 
	   {
	      logger.debug("doPost");
	      processRequest(request, response);
	   }
	    
	   /** Returns a short description of the servlet.
	    * @return String 
	    */
	   public String getServletInfo() 
	   {
	      return "Short description";
	   }
	    
	   /**
	 * @param request 
	 * @return String 
	 */
	String decodeURL(HttpServletRequest request)
	   {
	            
	      //HttpSession session = request.getSession();
	      String strQuesryString = request.getQueryString();
	      logger.debug("Query String is:"+strQuesryString);
	      Hashtable params = HttpUtils.parseQueryString(strQuesryString);
	      String[] arrURL =  (String[])params.get("RedirectURL");            
	      String strURL =  arrURL==null || arrURL.length ==0?null:arrURL[0];            
	      String[] arrServer =  (String[])params.get("SAWServer");            
	      String strServer =  arrServer==null || arrServer.length ==0?null:arrServer[0];            
	      StringBuffer bufURL = new StringBuffer();
	      if (!strURL.startsWith("http:") && !strURL.startsWith("https:"))//full url
	      {
	         bufURL.append(getServerURL(request,strServer));
	      }
	      params.remove("SAWServer");
	      params.remove("RedirectURL");
	      bufURL.append(strURL); 
	            
	      int nQIndex = strURL.lastIndexOf('?');
	            
	      if (!params.isEmpty() )
	      {                
	         bufURL.append(((nQIndex >= 0 )? "&" : "?"));
	         Set keys = params.keySet();
	         Iterator it = keys.iterator();
	         while (it.hasNext())
	         {
	            String strKey =  (String)it.next();
	            String strEncodedKey = URLEncoder.encode(strKey);
	            String[] arrParamValues =  (String[])params.get(strKey);
	            int paramArrLen = arrParamValues.length;
	            for (int i=0;i<paramArrLen;++i)
	            {                                
	               bufURL.append(strEncodedKey);
	               bufURL.append("=");
	               bufURL.append(URLEncoder.encode(arrParamValues[i]));
	               bufURL.append("&");
	            }
	         }
	         bufURL.deleteCharAt(bufURL.length()-1);
	      }
	                    
	      return bufURL.toString();
	             
	   } 

	   /**
	 * @param request 
	 * @return HttpURLConnection 
	 * @throws IOException 
	 */
	HttpURLConnection forwardRequest(HttpServletRequest request) throws IOException
	   {
	      logger.debug("forwardRequest");
	      String strURL  = decodeURL(request);
	      logger.debug("decodeURL returned:" + strURL);
	      //  logger.debug("Processing URL:" + strURL);
	      //logger.info.flush();
	      URL url = new URL(strURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      logger.debug("Connection opened  OK" );
	      con.setRequestMethod(request.getMethod());
	      Enumeration en = request.getHeaderNames();
	      while(en.hasMoreElements())
	      {
	         String strHeader = (String)en.nextElement();
	         String strHeaderValue = request.getHeader(strHeader);
	         logger.debug("Set header " + strHeader + "=" + strHeaderValue);
	         con.setRequestProperty(strHeader,strHeaderValue);
	      }

	      String strMethod = request.getMethod();
	      if (strMethod.compareTo("GET") != 0)
	      {
	         logger.debug("Try to copy request body");
	         ServletInputStream inputStream = request.getInputStream();
	         con.setDoOutput(true);
	         OutputStream forwardStream =  con.getOutputStream();
	         try
	         {
	            copyStreams(inputStream,forwardStream,request.getContentLength());
	         }
	         finally
	         {
	            forwardStream.close();
	         }
	      }

	      return con;
	   }
			
	   /**
	 * @param response 
	 * @param con 
	 * @throws IOException 
	 */
	void forwardResponse(HttpServletResponse response, HttpURLConnection con) throws IOException
	   {
	      logger.debug("forwardResponse");
	      int nContentLen = -1;
	      try
	      {
	         response.setStatus( con.getResponseCode());
	         for (int i=1;true;++i)
	         {
	            String strKey = con.getHeaderFieldKey(i);
	            if (strKey == null){
	               break;
	            }
	            String check = "Content-Length";
	            if (check.equals(strKey) )
	            {
	               nContentLen = Integer.parseInt(con.getHeaderField(i));
	               continue;
	            }
	            if (strKey .equalsIgnoreCase( "Connection")  
	                || strKey.equalsIgnoreCase("Server")
	                || strKey.equalsIgnoreCase("Transfer-Encoding")
	                || strKey.equalsIgnoreCase("Content-Length")){
	               continue; //skip certain headers
	            }
	            String strValue = con.getHeaderField(i);
	            response.setHeader(strKey,strValue);
	            logger.debug("Set header " + strKey + " = " + strValue);
	         }

	         copyStreams(con.getInputStream(),response.getOutputStream(),nContentLen);
	      }
	      finally
	      {
	         response.getOutputStream().close();
	         con.getInputStream().close();
	      }
	   }

	   /**
	 * @param inputStream 
	 * @param forwardStream 
	 * @param nContentLen 
	 * @throws IOException 
	 */
	void copyStreams(InputStream inputStream,OutputStream forwardStream, int nContentLen) throws IOException
	   {
	      logger.debug("copyStreams called nContentLen=" + nContentLen);
	      byte[] buf = new byte[1024] ;
	      int nCount =0;
	      int nBytesToRead =1024;
	      int nTotalCount =0;
	      do 
	      {
	         if (nContentLen != -1 ){
	            nBytesToRead = nContentLen - nTotalCount > 1024 ? 1024:nContentLen - nTotalCount;
	         }
	         if (nBytesToRead == 0){
	            break;
	         }
	         logger.debug("copyStreams. About to call inputStream.read " + nBytesToRead);
	         nCount = inputStream.read(buf,0,nBytesToRead);
	         logger.debug("copyStreams.read returned  " + nCount);
	         if (nCount < 0){
	            break;
	         }
	         nTotalCount += nCount;
	         logger.debug("copyStreams. About to call forwardStream.write " + nCount);
	         forwardStream.write(buf, 0, nCount);
	         logger.debug("copyStreams. write  done");
	      }
	      while(true);
	   }

	   /**
	 * @param request 
	 * @param key 
	 * @return String 
	 */
	String getServerURL(HttpServletRequest request,String key)
	   {
	      String strURL =  request.getSession().getAttribute(key).toString();
	      int nIndex = strURL.lastIndexOf('/');
	      if (nIndex >0){
	          return strURL.substring(0,nIndex+1);
	      }
	      else{
	          return strURL;
	      }
	   }

		/**
		 * @return ObieeConnectionData 
		 */
		private static ObieeConnectionData getConfigProperties(){
			ObieeConnectionData obieeConnectionData = null;
			try{
				obieeConnectionData = JndiLookupUtil.getObieeServiceResult();
				logger.debug("UserName:" + obieeConnectionData.getUserName());
				logger.debug("Password:" + obieeConnectionData.getPassword());
				logger.debug("HostName:" + obieeConnectionData.getHostName());
			}catch (Exception e){
				logger.debug("Exception "+e.getMessage());
				}
			return obieeConnectionData;			
		} 

}
