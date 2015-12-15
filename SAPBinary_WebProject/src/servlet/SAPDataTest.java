package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import standard.SAPInvoiceService;
import standard.SAPToBinaryStream;

/**
 * Servlet implementation class SAPDataTest
 */
public class SAPDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SAPDataTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResourceBundle rb = ResourceBundle.getBundle("resourceFile.sapResource");
		String endPointStr = rb.getString("sapProp.endPoint");
		
		String reportId = request.getParameter("repId");
		
		String strRepId ;
		if(reportId == null){
			
			strRepId = "9000002454";
			//strRepId = "9199468332";
		}else{
			strRepId = reportId;
			//strRepId = "9000002454";
		}
		
		URL endpoint = new URL(endPointStr);
		//URL endpoint = new URL("http://duslexecd02.na.ds.lexmark.com:8010/sap/bc/srt/rfc/sap/zg_invoice_arch_doc/220/zg_invoice_arch_doc/zg_invoice_arch_doc");
	        String userid = "ZSECSB1D";
	        String password = "down81Play";
	        SAPInvoiceService service = new SAPInvoiceService(endpoint,userid,password);
	        //FileOutputStream fos = new FileOutputStream("d:/testout.pdf");	        
	        //InputStream is = service.call("9000002454");
	        InputStream is = service.call(strRepId);
	        OutputStream os = response.getOutputStream();  // new addition 
	        try{
	            //SAPToBinaryStream.parse(is,fos);  // new comments
	            SAPToBinaryStream.parse(is,os); 
	        } catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            is.close();
	        }
	       
	        response.setHeader("Pragma", "no-cache");  
			response.setHeader("Cache-control", "private");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename=\"Report.pdf\""); 
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
