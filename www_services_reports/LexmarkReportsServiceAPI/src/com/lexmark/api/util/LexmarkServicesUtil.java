package com.lexmark.api.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexmark.api.bean.DocListInfo;
import com.lexmark.api.bean.DocSearchCriteria;
import com.lexmark.api.bean.DocumentInformation;
import com.lexmark.api.bean.ImageNowDoc;
import com.lexmark.api.bean.InDocument;
import com.lexmark.api.bean.InProperty;
import com.lexmark.api.bean.LexmarkReportServiceBean;

/**
 * @author AN312454
 *
 */
/**
 * @author AN312454
 *
 */
public class LexmarkServicesUtil {

	private static final Logger logger = Logger.getLogger(LexmarkServicesUtil.class);

	/**
	 * xUsername Image now user name
	 */
	public static final String xUsername = "X-IntegrationServer-Username";
	/**
	 * xPassword Image now password
	 */
	public static final String xPassword = "X-IntegrationServer-Password";
	/**
	 * xSessionHash Image now Session code
	 */
	public static final String xSessionHash = "X-IntegrationServer-Session-Hash";
	/**
	 * xResourceId Image now resouce id
	 */
	public static final String xResourceId = "X-IntegrationServer-Resource-ID";
	/**
	 * xResourceName Image now resource name
	 */
	public static final String xResourceName = "X-IntegrationServer-Resource-Name";
	/**
	 * xResourceName Image now resource file size 
	 */
	public static final String xResourceFileSize = "X-IntegrationServer-File-Size";
	/**
	 * Webservice responce code 200 (success).
	 */
	public static final int RESPONSE_OK = 200;

	/**
	 * Creates connection with ImageNow
	 * 
	 * @param lexmarkReportServiceBean
	 *            LexmarkReportServiceBean
	 * @return lexmarkReportServiceBean LexmarkReportServiceBean
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static LexmarkReportServiceBean getConnection(LexmarkReportServiceBean lexmarkReportServiceBean) throws ClientProtocolException, IOException {
		logger.info("Inside getConnection-------");
		String url = lexmarkReportServiceBean.getBaseURL() + "/v1/connection";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(url);

		getRequest.addHeader(xUsername, lexmarkReportServiceBean.getUsername());
		getRequest.addHeader(xPassword, lexmarkReportServiceBean.getPassword());

		HttpResponse response = null;

		response = client.execute(getRequest);
		logger.info("Response is : " + response.getStatusLine());
		lexmarkReportServiceBean.setResponseStatusCode(response.getStatusLine().getStatusCode());

		if (lexmarkReportServiceBean.getResponseStatusCode() == 200) {
			Header[] headers = response.getAllHeaders();
			logger.info("Getting " + xSessionHash);
			for (int i = 0; i < headers.length; i++) {
				if (headers[i].getName().equalsIgnoreCase(xSessionHash)) {
					lexmarkReportServiceBean.setSessionCode(headers[i].getValue());
				}
			}
		}
		return lexmarkReportServiceBean;
	}

	/**
	 * Deletes connection with ImageNow
	 * 
	 * @param lexmarkReportServiceBean
	 *            LexmarkReportServiceBean
	 */
	public static void deteleConnection(LexmarkReportServiceBean lexmarkReportServiceBean) {
		logger.info("Inside deteleConnection-------");
		String url = lexmarkReportServiceBean.getBaseURL() + "/v1/connection";
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete deleteRequest = new HttpDelete(url);

		deleteRequest.addHeader(xSessionHash, lexmarkReportServiceBean.getSessionCode());

		HttpResponse response = null;

		try {
			response = client.execute(deleteRequest);
			logger.info("Response is : " + response.getStatusLine());
		} catch (ClientProtocolException e) {
			logger.error("Session is not deleted" + e.getMessage());

		} catch (IOException e) {
			logger.error("Session is not deleted" + e.getMessage());

		}
	}

	/**
	 * Creates a document in ImageNow
	 * 
	 * @param docInformation
	 *            DocumentInformation
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	public static String createDocument(DocumentInformation docInformation) throws ClientProtocolException, IOException, Exception {
		logger.info("Inside createDocument-------");
		String url = docInformation.getBaseURL() + "/v1/document";
		if (docInformation.getDrawerId() == null) {
			throw new Exception("Document cannot be created as DrawerId is not provided");
		}
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(url);

		InDocument inDocument = new InDocument();
		inDocument.getInfo().getKeys().setDrawer(docInformation.getDrawerId());
		inDocument.getInfo().getKeys().setField1(docInformation.getField1());
		inDocument.getInfo().getKeys().setField2(docInformation.getField2());
		inDocument.getInfo().getKeys().setField3(docInformation.getField3());
		inDocument.getInfo().getKeys().setField4(docInformation.getField4());
		inDocument.getInfo().getKeys().setField5(docInformation.getField5());
		inDocument.getInfo().getKeys().setDocumentType(docInformation.getType());
		if (docInformation.getPropertyMap() != null) {
			for (Map.Entry<String, String> entry : docInformation.getPropertyMap().entrySet()) {
				inDocument.getProperties().add(new InProperty(getPropertyID(entry.getKey(), docInformation), entry.getValue()));
			}
		}

		String jsonInput = "";
		jsonInput = objectToJson(inDocument);
		logger.info(jsonInput);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.toString().getBytes());
		entity.setContentType("application/json");

		postRequest.setEntity(entity);
		postRequest.addHeader(xSessionHash, docInformation.getSessionCode());
		HttpResponse response = null;
		response = client.execute(postRequest);
		Header[] headers = response.getAllHeaders();
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].getName().equalsIgnoreCase("Location")) {
				docInformation.setLocation(headers[i].getValue());
			}
		}
		String[] loc = docInformation.getLocation().split("/");
		logger.info("Doc Id  of uploaded document: " + loc[loc.length - 1]);
		return loc[loc.length - 1];
	}

	/**
	 * Creates a page inside the document in ImageNow i.e. the document
	 * submitted while upload
	 * 
	 * @param docInformation
	 *            DocumentInformation
	 * @throws Exception
	 */
	public static void createDocumentPage(DocumentInformation docInformation) throws Exception {
		logger.info("Inside createDocumentPage-------");
		// String url = docInformation.getLocation() + "/page";
		// System.out.println("url: "+url);
		String[] loc = docInformation.getLocation().split("/");
		String docId = loc[loc.length - 1];

		String url = docInformation.getBaseURL() + "/v1/document/" + docId + "/page";
//		System.out.println("url: " + url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(url);
//		System.out.println("-->" + String.valueOf(docInformation.getFileContentInBytes().length));
		ByteArrayEntity byteArrayEntity = new ByteArrayEntity(docInformation.getFileContentInBytes());
		byteArrayEntity.setContentType("application/octet-stream");
		postRequest.setEntity(byteArrayEntity);
		postRequest.addHeader(xSessionHash, docInformation.getSessionCode());
//		System.out.println("File Name :" + docInformation.getPropertyMap().get("FileName"));
		postRequest.addHeader(xResourceName, docInformation.getPropertyMap().get("FileName"));
		postRequest.addHeader(xResourceFileSize, String.valueOf(docInformation.getFileContentInBytes().length));
		HttpResponse resp = null;
		try{
			resp = client.execute(postRequest);
			logger.info("Response Message: "+ EntityUtils.toString(resp.getEntity()));
		}catch(Exception e){
			logger.info("Response Message: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Fetches the list of documents matching the inputParamVals like drawer,
	 * definitionId, docType property list is the list of custom properties
	 * associated that are fetched field list is the list of fields associated
	 * that are fetched
	 * 
	 * @param docListInfo
	 *            DocListInfo
	 * @return String
	 * @throws IOException
	 * @throws JAXBException
	 * @throws XPathExpressionException
	 */
	public static String getDocList(DocListInfo docListInfo) throws IOException, JAXBException, XPathExpressionException {

		logger.info("Inside getDocList-------");
		String url = docListInfo.getBaseURL() + "/serverAction";

		DocSearchCriteria docSearchCriteria = new DocSearchCriteria();
		List<String> inputParamsVals = new ArrayList<String>();
		inputParamsVals.add(docListInfo.getDrawerName());
		inputParamsVals.add(docListInfo.getDefinitionId());
		inputParamsVals.add(docListInfo.getDocType());
		inputParamsVals.add(docListInfo.getPropertyList());
		inputParamsVals.add(docListInfo.getFieldList());
		docSearchCriteria.setInputParams(inputParamsVals);
		docSearchCriteria.setMode("SCRIPT");
		docSearchCriteria.setFilePath("LX_WWWServices_getDocList.js");

		String jsonInput = null;
		jsonInput = objectToJson(docSearchCriteria);
		logger.info("Document list xml : " + jsonInput);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(url);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");

		postRequest.setEntity(entity);
		postRequest.addHeader(xSessionHash, docListInfo.getSessionCode());
		HttpResponse response = null;
		String result = null;
		response = client.execute(postRequest);
		result = processHttpResponse(response);

		return getOutputParam(result, "getDocList", "/serverActionResponse/outputParams/outputParam");
	}

	/**
	 * Fetches the details of particular document
	 * 
	 * @param imageNowDoc
	 *            ImageNowDoc
	 * @return LexmarkReportServiceBean
	 * @throws Exception
	 */
	public static LexmarkReportServiceBean getDocumentPageDetails(ImageNowDoc imageNowDoc) throws Exception {
		logger.info("Inside getDocumentPageDetails-------");
		String url = imageNowDoc.getBaseURL() + "/v1/document/" + imageNowDoc.getDocumentId();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader(xSessionHash, imageNowDoc.getSessionCode());
		getRequest.addHeader("Accept", "application/json");
		HttpResponse response = null;

		response = client.execute(getRequest);
		imageNowDoc.setResponseStatusCode(response.getStatusLine().getStatusCode());
		if (imageNowDoc.getResponseStatusCode() != 200) {
			throw new Exception("Error in download");
		}
		String result = null;
		result = processHttpResponse(response);
		logger.info("Document Details : " + result);
		imageNowDoc = getPageDetails(imageNowDoc, result);
		return imageNowDoc;

	}

	/**
	 * Get the page detail like filetype or filename
	 * 
	 * @param imageNowDoc
	 *            ImageNowDoc
	 * @param docPageDetails
	 *            String
	 * @return ImageNowDoc
	 */
	private static ImageNowDoc getPageDetails(ImageNowDoc imageNowDoc, String docPageDetails) {
		logger.info("Inside getPageDetails-------");

		InDocument inDocument = jsonToObject(docPageDetails, InDocument.class);
		if (inDocument.getPages().size() > 0) {

			imageNowDoc.setPageId(inDocument.getPages().get(0).getId());
			logger.info("Page Id: " + imageNowDoc.getPageId());

			imageNowDoc.setPageName(inDocument.getPages().get(0).getName());
			logger.info("Page Name: " + imageNowDoc.getPageName());
		}

		return imageNowDoc;
	}

	/**
	 * Gets the content in bytes
	 * 
	 * @param imageNowDoc
	 *            ImageNowDoc
	 * @return LexmarkReportServiceBean
	 * @throws Exception
	 */
	public static LexmarkReportServiceBean getDocumentContent(ImageNowDoc imageNowDoc) throws Exception {
		logger.info("Inside getDocumentContent-------");
		String url = imageNowDoc.getBaseURL() + "/v1/document/" + imageNowDoc.getDocumentId() + "/page/" + imageNowDoc.getPageId() + "/file";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader(xSessionHash, imageNowDoc.getSessionCode());

		HttpResponse response = null;

		response = client.execute(getRequest);
		imageNowDoc.setResponseStatusCode(response.getStatusLine().getStatusCode());
		logger.info("Response from server : " + response.getStatusLine());
		if (imageNowDoc.getResponseStatusCode() != RESPONSE_OK) {
			throw new Exception("Error in download");
		}
		InputStream content = response.getEntity().getContent();
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			byte[] bytes = null;
			byte[] buf = new byte[1024];

			for (int readNum; (readNum = content.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			bytes = bos.toByteArray();
			imageNowDoc.setFileContent(bytes);
		} catch (IOException e) {
			logger.info("Caught" + e);
		} finally {
			bos.close();
		}
		return imageNowDoc;
	}

	/**
	 * Deletes document from ImageNow
	 * 
	 * @param imageNowDoc
	 *            ImageNowDoc
	 * @return LexmarkReportServiceBean
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static LexmarkReportServiceBean deleteDocument(ImageNowDoc imageNowDoc) throws ClientProtocolException, IOException {
		logger.info("Inside deleteDocument-------");
		logger.info("Document Id to be deleted : " + imageNowDoc.getDocumentId());
		String url = imageNowDoc.getBaseURL() + "/v1/serverAction";
		DocSearchCriteria docSearchCriteria = new DocSearchCriteria();
		List<String> inputParamsVals = new ArrayList<String>();
		inputParamsVals.add(imageNowDoc.getDocumentId());
		docSearchCriteria.setInputParams(inputParamsVals);
		docSearchCriteria.setMode("SCRIPT");
		docSearchCriteria.setFilePath("LX_WWWServices_deleteDoc.js");
		String jsonInput = null;
		jsonInput = objectToJson(docSearchCriteria);
		logger.info("Document list xml : " + jsonInput);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(url);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");

		postRequest.setEntity(entity);
		postRequest.addHeader("X-IntegrationServer-Session-Hash", imageNowDoc.getSessionCode());
		HttpResponse response = null;
		// String result = null;
		response = client.execute(postRequest);
		// result = processHttpResponse(response);

		logger.info("Response : " + response.getStatusLine());
		imageNowDoc.setResponseStatusCode(response.getStatusLine().getStatusCode());
		return imageNowDoc;

	}

	/**
	 * Gets the property Id of the custom property
	 * 
	 * @param key
	 *            String
	 * @param lexmarkReportServiceBean
	 *            LexmarkReportServiceBean
	 * @return String
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws JAXBException
	 * @throws XPathExpressionException
	 */
	private static String getPropertyID(String key, LexmarkReportServiceBean lexmarkReportServiceBean) throws IOException, MalformedURLException, JAXBException, XPathExpressionException {
		logger.info("Inside getPropertyID-------");
		String url = lexmarkReportServiceBean.getBaseURL() + "/serverAction";

		DocSearchCriteria docSearchCriteria = new DocSearchCriteria();
		List<String> inputParamsVals = new ArrayList<String>();
		inputParamsVals.add(key);
		docSearchCriteria.setInputParams(inputParamsVals);
		docSearchCriteria.setMode("SCRIPT");
		docSearchCriteria.setFilePath("LX_WWWServices_getPropertyId.js");

		String jsonInput = null;
		jsonInput = objectToJson(docSearchCriteria);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(url);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");

		postRequest.setEntity(entity);
		postRequest.addHeader("X-IntegrationServer-Session-Hash", lexmarkReportServiceBean.getSessionCode());
		HttpResponse response = null;
		response = client.execute(postRequest);
		String result = null;
		result = processHttpResponse(response);
		logger.info("Property XML " + result);

		return getOutputParam(result, "getPropertyID", "/serverActionResponse/outputParams/outputParam");

	}

	/**
	 * Reads the response and converts it to String
	 * 
	 * @param response
	 * @return String
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String processHttpResponse(HttpResponse response) throws IllegalStateException, IOException {
		logger.info("Inside processHttpResponse-------");
		StringBuffer result = new StringBuffer();
		result.append(EntityUtils.toString(response.getEntity()));
//		System.out.println("Done");
		return result.toString();
	}

	/**
	 * 
	 * @param xml
	 * @param opName
	 * @param evalPath
	 * @return String
	 * @throws XPathExpressionException
	 */
	public static String getOutputParam(String xml, String opName, String evalPath) throws XPathExpressionException {
		logger.info("Inside getOutputParam-------");
		logger.info("result: " + xml);
		String message = null;

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(xml));
		Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
		message = xpath.evaluate(evalPath, doc);

		if (("getDocList").equals(opName)) {
			message = message.replace("&lt;", "<");
			message = message.replace("&gt;", ">");
		}
		logger.info("Message: " + message);
		return message;
	}

	/**
	 * 
	 * @param jsonInput
	 * @param type
	 * @param <T>
	 * @return <T>
	 */
	public static <T> T jsonToObject(String jsonInput, Class<T> type) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(jsonInput, type);
	}

	/**
	 * 
	 * @param obj
	 * @return String
	 */
	public static String objectToJson(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(obj);
	}
}
