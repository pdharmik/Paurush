package com.lexmark.creditcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessCSResponse
 */
public class ProcessCSResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*InputStream is=request.getInputStream();
		OutputStream os=response.getOutputStream();
		byte[] buf = new byte[1000];
		for (int nChunk = is.read(buf); nChunk!=-1; nChunk = is.read(buf))
		{
		    os.write(buf, 0, nChunk);
		} */
		
		}

}
