package com.lexmark.creditcard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentAuthorize extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String tokenNumber = request.getParameter("payment_token");
		String creditId = request.getParameter("creditId");
		HttpSession session = request.getSession();
		//session.setAttribute("creditId", creditId);
		if(null!=tokenNumber && !tokenNumber.equals("")){
			
			response.sendRedirect("creditcard/generateToken.jsp?token="+tokenNumber);
		}else{
			
			//response.sendRedirect("creditcard/generateToken.jsp");
		}
		
	}

}
