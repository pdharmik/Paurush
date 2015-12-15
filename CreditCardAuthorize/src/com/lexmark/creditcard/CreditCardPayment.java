package com.lexmark.creditcard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delegosoftware.definitions.CreditCard;

public class CreditCardPayment extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		StringBuffer responseBody=new StringBuffer();
		try{
			//CreditCard creditCard = new CreditCard();
			String creditCardNumber = request.getParameter("creditCard");
	        //String encryptedCardNumber = creditCard.getEncryptedCardNumber(creditCardNumber);
			String encryptedCardNumber = creditCardNumber;
	        //response.getOutputStream().print("Encrypted Card No is:"+ encryptedCardNumber);
	        if(encryptedCardNumber!=null && !encryptedCardNumber.equals("")){
	        	responseBody.append("\"creditsuccess\":\"Success\"");
	        }else{
	        	responseBody.append("\"creditsuccess\":\"Failure\"");
	        }
			responseBody.append(","+"\"encryptedNumber\":\""+encryptedCardNumber+"\"");
			responseBody.insert(0, "{");
			responseBody.insert(responseBody.length(), "}");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(responseBody.toString());
			out.flush();
			out.close();
			responseBody.delete(0, responseBody.length());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
