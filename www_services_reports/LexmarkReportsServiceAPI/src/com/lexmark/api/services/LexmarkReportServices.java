package com.lexmark.api.services;

import javax.ws.rs.core.Response;

/**
 * @author AN312454
 *
 */
public interface LexmarkReportServices {

	/**
	 * The below method is used for uploading the documents on the ImageNow
	 * 
	 * @param username 
	 * @param password 
	 * @param baseUrl 
	 * @param input 
	 * @return Response
	 */
	public Response fileUpload(String username, String password, String baseUrl, String input);

	/**
	 * The below method is used to the fetch the list of documents from ImageNow
	 * 
	 * @param username 
	 * @param password 
	 * @param baseUrl 
	 * @param input 
	 * @return Response 
	 */
	public Response getDocList(String username, String password, String baseUrl, String input);

	/**
	 * The below method is used to delete the document from ImageNow
	 * 
	 * @param username 
	 * @param password 
	 * @param baseUrl 
	 * @param input 
	 * @return response 
	 */
	public Response fileDelete(String username, String password, String baseUrl, String input);

	/**
	 * The below method is used for downloading the document from ImageNow
	 * 
	 * @param username 
	 * @param password 
	 * @param baseUrl 
	 * @param input 
	 * @return response 
	 */
	public Response fileDownload(String username, String password, String baseUrl, String input);
	/**
	 * The below method is used for creating Schedule in the BO server
	 * 
	 * @param jsonInput
	 * @return response 
	 */
	public Response scheduleReport(String jsonInput);
}
