package com.lexmark.api.services;

import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.lexmark.api.bean.DocListInfo;
import com.lexmark.api.bean.DocumentInformation;
import com.lexmark.api.bean.ImageNowDoc;
import com.lexmark.api.bean.LexmarkReportServiceBean;
import com.lexmark.api.bean.ScheduleReportInfoBean;
import com.lexmark.api.enums.ScheduleEnum;
import com.lexmark.api.util.BOServiceUtil;
import com.lexmark.api.util.EncryptionUtil;
import com.lexmark.api.util.LexmarkServicesUtil;

@Path("/report")
public class LexmarkReportServicesImpl implements LexmarkReportServices {

	private static final Logger logger = Logger.getLogger(LexmarkReportServicesImpl.class);

	@POST
	@Path("/createdocument")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fileUpload(@HeaderParam("username") String userName, @HeaderParam("password") String password, @HeaderParam("baseUrl") String baseUrl, String jsonInput) {
		logger.info("Inside fileupload-------");
	//	logger.info("Document information : " + jsonInput);
		LexmarkReportServiceBean docInformation = null;
		String documentId = "";
		try {
			docInformation = LexmarkServicesUtil.jsonToObject(jsonInput, DocumentInformation.class);
			docInformation.setUsername(EncryptionUtil.decryptData(userName));
			docInformation.setPassword(EncryptionUtil.decryptData(password));
			docInformation.setBaseURL(EncryptionUtil.decryptData(baseUrl));

			docInformation = LexmarkServicesUtil.getConnection(docInformation);
			if (docInformation.getResponseStatusCode() == 401) {
				return Response.status(401).entity("Not authorised").build();
			}
			logger.info("Connected with session id : " + docInformation.getSessionCode());
			documentId = LexmarkServicesUtil.createDocument((DocumentInformation) docInformation);
			logger.info("Created Document with id : " + documentId);
			LexmarkServicesUtil.createDocumentPage((DocumentInformation) docInformation);

		} catch (Exception e) {
			logger.error("Document creation exception - Exception " + e.getMessage());
			return Response.status(400).entity("Not Created").build();
		} finally {
			if (docInformation.getSessionCode() != null) {
				LexmarkServicesUtil.deteleConnection(docInformation);
			}
		}
		return Response.status(200).entity(documentId).build();
	}

	@POST
	@Path("/getlistdocs")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocList(@HeaderParam("username") String userName, @HeaderParam("password") String password, @HeaderParam("baseUrl") String baseUrl, String jsonInput) {
		logger.info("Inside getDocList-------");
		logger.info("Document list to get : " + jsonInput);

		String resultDocList = null;
		LexmarkReportServiceBean docListInfo = null;

		try {
			docListInfo = LexmarkServicesUtil.jsonToObject(jsonInput, DocListInfo.class);
			docListInfo.setUsername(EncryptionUtil.decryptData(userName));
			docListInfo.setPassword(EncryptionUtil.decryptData(password));
			docListInfo.setBaseURL(EncryptionUtil.decryptData(baseUrl));

			docListInfo = LexmarkServicesUtil.getConnection(docListInfo);
			if (docListInfo.getResponseStatusCode() == 401) {
				return Response.status(401).entity("Not authorised").build();
			}
			logger.info("Connected with session id : " + docListInfo.getSessionCode());
			resultDocList = LexmarkServicesUtil.getDocList((DocListInfo) docListInfo);
			logger.info("Document list xml: " + resultDocList);

		} catch (Exception e) {
			logger.error("Document list exception - Exception" + e.getMessage());
			return Response.status(400).entity("Not get the list").build();
		} finally {
			if (docListInfo.getSessionCode() != null) {
				LexmarkServicesUtil.deteleConnection(docListInfo);
			}
		}

		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(resultDocList).build();
	}


	@POST
	@Path("/downloaddocument")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response fileDownload(@HeaderParam("username") String userName, @HeaderParam("password") String password, @HeaderParam("baseUrl") String baseUrl, String jsonInput) {
		logger.info("Inside fileDownload-------");
		logger.info("Document to download input : " + jsonInput);

		LexmarkReportServiceBean imageNowDoc = null;

		try {
			imageNowDoc = LexmarkServicesUtil.jsonToObject(jsonInput, ImageNowDoc.class);
			imageNowDoc.setUsername(EncryptionUtil.decryptData(userName));
			imageNowDoc.setPassword(EncryptionUtil.decryptData(password));
			imageNowDoc.setBaseURL(EncryptionUtil.decryptData(baseUrl));

			imageNowDoc = LexmarkServicesUtil.getConnection(imageNowDoc);
			if (imageNowDoc.getResponseStatusCode() == 401) {
				return Response.status(401).entity("Not authorised").build();
			}
			logger.info("Connected with session id : " + imageNowDoc.getSessionCode());
			imageNowDoc = LexmarkServicesUtil.getDocumentPageDetails((ImageNowDoc) imageNowDoc);
			if (imageNowDoc.getResponseStatusCode() == 404) {
				LexmarkServicesUtil.deteleConnection(imageNowDoc);
				return Response.status(404).entity("Document Not Found").build();
			}
			logger.info("Page id : " + ((ImageNowDoc) imageNowDoc).getPageId());
			imageNowDoc = LexmarkServicesUtil.getDocumentContent((ImageNowDoc) imageNowDoc);
			if (imageNowDoc.getResponseStatusCode() == 404) {
				LexmarkServicesUtil.deteleConnection(imageNowDoc);
				return Response.status(404).entity("Document Not Found").build();
			}
		} catch (Exception e) {
			logger.error("Document download exception - Exception " + e.getMessage());
			return Response.status(400).entity("Document not downloaded").build();
		} finally {
			if (imageNowDoc.getSessionCode() != null) {
				LexmarkServicesUtil.deteleConnection(imageNowDoc);
			}
		}

		return Response.ok(((ImageNowDoc) imageNowDoc).getFileContent(), MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = " + ((ImageNowDoc) imageNowDoc).getPageName()).build();
	}

	@POST
	@Path("/deletedocument")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fileDelete(@HeaderParam("username") String userName, @HeaderParam("password") String password, @HeaderParam("baseUrl") String baseUrl, String jsonInput) {
		logger.info("Inside fileDelete-------");
		logger.info("Document to delete input : " + jsonInput);

		LexmarkReportServiceBean imageNowDoc = null;

		try {

			imageNowDoc = LexmarkServicesUtil.jsonToObject(jsonInput, ImageNowDoc.class);
			imageNowDoc.setUsername(EncryptionUtil.decryptData(userName));
			imageNowDoc.setPassword(EncryptionUtil.decryptData(password));
			imageNowDoc.setBaseURL(EncryptionUtil.decryptData(baseUrl));

			imageNowDoc = LexmarkServicesUtil.getConnection(imageNowDoc);
			if (imageNowDoc.getResponseStatusCode() == 401) {
				return Response.status(401).entity("Not authorised").build();
			}
			logger.info("Connected with session id : " + imageNowDoc.getSessionCode());
			imageNowDoc = LexmarkServicesUtil.deleteDocument((ImageNowDoc) imageNowDoc);
			LexmarkServicesUtil.deteleConnection(imageNowDoc);
			if (imageNowDoc.getResponseStatusCode() == 200) {
				logger.info("Document deleted successfully");
				return Response.status(200).entity("Deleted successfully").build();
			} else {
				logger.info("Document not deleted");
				return Response.status(400).entity("Document Not Deleted").build();
			}

		} catch (Exception e) {
			logger.error("Document delete exception - Exception" + e.getMessage());
			return Response.status(400).entity("Document not deleted").build();
		} finally {
			if (imageNowDoc.getSessionCode() != null) {
				LexmarkServicesUtil.deteleConnection(imageNowDoc);
			}
		}
	}
	
	@POST
	@Path("/schedulereport")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response scheduleReport(String jsonInput) {
		logger.info("in scheduleReport");
		logger.info("json input: " + jsonInput);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		ScheduleReportInfoBean scheduleReportInfoBean = LexmarkServicesUtil.jsonToObject(jsonInput, ScheduleReportInfoBean.class);
		try {
			if (scheduleReportInfoBean.getScheduleType().equals(ScheduleEnum.RUNNOW.getValue())) {
				scheduleReportInfoBean = BOServiceUtil.runNowReportOnBO(scheduleReportInfoBean);
			} else if (scheduleReportInfoBean.getScheduleType().equals(ScheduleEnum.SCHEDULE.getValue())) {
				scheduleReportInfoBean = BOServiceUtil.scheduleReportOnBO(scheduleReportInfoBean);
			}
			if(scheduleReportInfoBean.getWsStatusCode() != 200){
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(400).type(MediaType.APPLICATION_JSON).entity(LexmarkServicesUtil.objectToJson(scheduleReportInfoBean)).build();
		}
		return Response.status(200).type(MediaType.APPLICATION_JSON).entity(LexmarkServicesUtil.objectToJson(scheduleReportInfoBean)).build();
	}
}
