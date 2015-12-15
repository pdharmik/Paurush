/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AssetAcceptanceController.java
 * Package     		: com.lexmark.services.portlet.cm
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		1.0             Initial Version
 * 
 */

package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.cm.AssetAcceptanceService;
import com.lexmark.services.form.AssetAcceptanceForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * This AssetAcceptanceController class is used for AssetAcceptance Module which
 * extends BaseController
 * 
 * @version 2.1
 * @author wipro
 * 
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value = { "assetAcceptForm" })
public class AssetAcceptanceController extends BaseController {

	/** Holds the reference of AssetAcceptanceServiceImpl bean **/
	@Autowired
	private AssetAcceptanceService assetAcceptanceService;

	/** . Holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;

	/** . This variable holds AmindGlobalService bean reference **/
	@Autowired
	private GlobalService globalService;

	/** . Holds AmindAttachmentService bean reference **/
	@Autowired
	private AttachmentService attachmentService;

	/** Holds the reference of FileUploadFormValidator bean **/
	@Autowired
	private FileUploadFormValidator fileUploadFormValidator;
	/**
	 * variable declaration
	 */
	private String listOfFileTypes;
	/**
	 * variable declaration
	 */
	private String attMaxCount;

	/** . Instance variable of wrapper logger class **/
	private static final LEXLogger LOGGER = LEXLogger
			.getLEXLogger(AssetAcceptanceController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME = "AssetAcceptanceController.java";
	/**
	 * static variable declaration
	 */
	private static final String FILEMAP = "fileMap";
	/**
	 * static variable declaration
	 */
	public static final String CUSTOMER_ACCEPTANCE = "Customer Acceptance";
	/**
	 * static variable declaration
	 */
	public static final String URL_SUB_AREA = "subAreaURL";
	/**
	 * static variable declaration
	 */
	public static final String REQ_SUB_AREA_MAP = "aaSubAreaMap";
	/**
	 * static variable declaration
	 */
	public static final String PARAM_FILE_NAME = "fileName";
	/**
	 * static variable declaration
	 */
	public static final String PARAM_FILE_TYPE = "fileType";
	/**
	 * static variable declaration
	 */
	public static final String URL_ATTACHMENT = "attachmentURL";
	/**
	 * static variable declaration
	 */
	public static final String URL_CHL_TEMPLATE = "chlTemplateURL";
	/**
	 * static variable declaration
	 */
	public static final String REQ_ACCOUNT_CONTACT = "accountContact";
	/**
	 * static variable declaration
	 */
	public static final String URL_DISPLAY_ATTACHMENT = "displayAttachment";
	/**
	 * static variable declaration
	 */
	public static final String FILE_TYPE_ATTACHMENT = "Attachment";
	/**
	 * static variable declaration
	 */
	public static final String ASSET_ACCEPT_FORM = "assetAcceptForm";
	/**
	 * static variable declaration
	 */
	public static final String FILE_TYPE_CHL_TEMPLATE = "CHLTemplate";
	/**
	 * static variable declaration
	 */
	private static final String FILEUPLOADFORM = "fileUploadForm";

	/**
	 * This method is used to bind the validator for form beans 
	 * 
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value = { FILEUPLOADFORM, ASSET_ACCEPT_FORM })
	protected void initBinder(WebDataBinder binder, Locale locale) {

		LOGGER.debug("Language is " + locale.getLanguage() + " country is " + locale.getCountry() + "format is" + DateUtil.getDateFormatByLang(locale.getLanguage()));
		DateFormat sdf = new SimpleDateFormat(DateUtil
				.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder
				.registerCustomEditor(Date.class, new CustomDateEditor(sdf,
						true));
		if ((!(binder.getTarget() instanceof AssetAcceptanceForm))
				&& (binder.getTarget() instanceof FileUploadForm)) {
			binder.setValidator(fileUploadFormValidator);
		}

	}

	/**
	 * Default method for Asset Acceptance portlet populates the form, requester
	 * information and 
	 *  
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 * @throws Exception 
	 */
	@RequestMapping
	public String viewAssetAcceptance(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException,
			LGSRuntimeException, Exception {
		String METHOD_NAME = "viewAssetAcceptance";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		AssetAcceptanceForm assetAcceptForm = new AssetAcceptanceForm();
		FileUploadForm fileUploadForm = new FileUploadForm();
		List<String> exceptionList = new ArrayList<String>();
		Map<String, String> aaSubAreaMap = new LinkedHashMap<String, String>();
		AccountContact accContact = null;
		ResourceURL resURL1 = null;
		PortletSession session = request.getPortletSession();
		try {
			HttpServletRequest httpReq = PortalUtil
					.getOriginalServletRequest(PortalUtil
							.getHttpServletRequest(request));
			String srNumber = httpReq
					.getParameter(ChangeMgmtConstant.SERVICEREQNO);
			if (srNumber != null) {
				assetAcceptForm.setPrevSRNumber(srNumber);
			}
			aaSubAreaMap = commonController
					.retrieveLocalizedLOVMap(
							SiebelLocalizationOptionEnum.CUSTOMER_ACCEPTANCE
									.getValue(), request.getLocale());
			accContact = commonController.getContactInformation(request,
					response);
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setRequestor(accContact);
			serviceRequest.setPrimaryContact(accContact);
			assetAcceptForm.setServiceRequest(serviceRequest);
			assetAcceptForm.setAttMaxCount(attMaxCount);
			assetAcceptForm.setListOfFileTypes(listOfFileTypes);
			resURL1 = response.createResourceURL();
			resURL1.setResourceID(URL_DISPLAY_ATTACHMENT);
			session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		} catch (LGSDBException dbExcption) {
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(
					ERROR_MESSAGE_BUNDLE, "exception.portal.database.10001",
					null, request.getLocale()));
			dbExcption.getMessage();
		} catch (Exception e) {
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(
					ERROR_MESSAGE_BUNDLE, "exception.portal.general", null,
					request.getLocale()));
			e.getMessage();
		}
		if (resURL1 != null) {
			model.addAttribute("chlTemplateURL", resURL1.toString());
		}
		if (!exceptionList.isEmpty()) {
			model
					.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
							exceptionList);
		}
		if (accContact != null) {
			model.addAttribute("accountContact", accContact);
		}
		model.addAttribute("aaSubAreaMap", aaSubAreaMap);
		model.addAttribute(ASSET_ACCEPT_FORM, assetAcceptForm);
		model.addAttribute(FILEUPLOADFORM, fileUploadForm);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/assetAcceptance/assetAcceptanceDetails";
	}

	/**
	 * The method is called when "Continue" button is clicked from
	 * acceptanceRequestDetails 
	 * 
	 * @param model 
	 * @param response 
	 * @param request 
	 * @param assetAcceptForm 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 * @throws Exception 
	 */
	@ActionMapping(params = "action=showAssetAcceptanceReview")
	public void showAssetAcceptanceReview(
			Model model,
			ActionResponse response,
			ActionRequest request,
			@ModelAttribute(ASSET_ACCEPT_FORM) AssetAcceptanceForm assetAcceptForm)
			throws LGSCheckedException, LGSRuntimeException, Exception {

		PortletSession session = request.getPortletSession();

		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		model.addAttribute(ASSET_ACCEPT_FORM, assetAcceptForm);
		request.setAttribute(FILEMAP, fileMap);
		model.addAttribute(FILEMAP, fileMap);
		assetAcceptForm.refreshSubmitToken(request);
		assetAcceptForm.refreshSubmitToken(request);
		commonController.getContactInformation(request, response);
		response.setRenderParameter("action", "backToEdit");
		response.setRenderParameter("action", "showAssetAcceptanceReview");

	}

	/**
	 * The method is called from confirmRequest method to render the review page
	 * for AssetAcceptance
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param assetAcceptForm 
	 * @param result 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 * @throws Exception 
	 */
	@RenderMapping(params = "action=showAssetAcceptanceReview")
	public String showAssetAcceptanceReview(
			Model model,
			RenderRequest request,
			RenderResponse response,
			@ModelAttribute(ASSET_ACCEPT_FORM) AssetAcceptanceForm assetAcceptForm,
			BindingResult result) throws LGSCheckedException,
			LGSRuntimeException, Exception {
		String METHOD_NAME = "showAssetAcceptanceReview";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		List<String> exceptionList = new ArrayList<String>();
		PortletSession session = request.getPortletSession();
		FileUploadForm fileUploadForm = new FileUploadForm();
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		if (result.hasErrors()) {

			fileUploadForm.setFileMap(fileMap);
			if (fileMap != null) {
				fileUploadForm.setFileCount(fileMap.size());
			}
		}
		if (!exceptionList.isEmpty()) {
			model
					.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
							exceptionList);
		}
		String timezoneOffset = request
				.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		if (!StringUtil.isEmpty(timezoneOffset)) {
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(
					timezoneOffset));
		}
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(URL_DISPLAY_ATTACHMENT);
		model.addAttribute(URL_ATTACHMENT, resURL.toString());
		model.addAttribute(FILEUPLOADFORM, fileUploadForm);
		model.addAttribute(ASSET_ACCEPT_FORM, assetAcceptForm);
		model.addAttribute(FILEMAP, fileMap);
		request.setAttribute(FILEMAP, fileMap);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/assetAcceptance/assetAcceptanceReview";
	}

	/**
	 * The method is called when "Back" button is clicked from
	 * acceptRequestReview page 
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param assetAcceptForm 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 * @throws Exception 
	 */
	@ActionMapping(params = "action=backToEdit")
	public void backToEdit(
			Model model,
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute(ASSET_ACCEPT_FORM) AssetAcceptanceForm assetAcceptForm)
			throws LGSCheckedException, LGSRuntimeException, Exception {
		String METHOD_NAME = "backToEdit";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		FileUploadForm fileUploadForm = new FileUploadForm();
		PortletSession session = request.getPortletSession();
		List<String> exceptionList = new ArrayList<String>();

		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		fileUploadForm.setFileMap(fileMap);
		if (fileMap != null) {
			fileUploadForm.setFileCount(fileMap.size());
		}
		if (!exceptionList.isEmpty()) {
			model
					.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
							exceptionList);
		}
		Map<String, String> aaSubAreaMap = new LinkedHashMap<String, String>();
		try {
			aaSubAreaMap = commonController
					.retrieveLocalizedLOVMap(
							SiebelLocalizationOptionEnum.CUSTOMER_ACCEPTANCE
									.getValue(), request.getLocale());
		} catch (LGSDBException dbExcption) {
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(
					ERROR_MESSAGE_BUNDLE, "exception.portal.database.10001",
					null, request.getLocale()));
			dbExcption.getMessage();
		} catch (LGSRuntimeException e) {
			exceptionList.add(e.getMessage());
		}
		model.addAttribute(REQ_SUB_AREA_MAP, aaSubAreaMap);
		model.addAttribute(ASSET_ACCEPT_FORM, assetAcceptForm);
		model.addAttribute(FILEUPLOADFORM, fileUploadForm);
		commonController.getContactInformation(request, response);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		response.setRenderParameter("action", "backToEdit");
	}

	/**
	 * The method is called from render method to render the asset acceptance
	 * home page 
	 * 
	 * @param model 
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 * @throws Exception 
	 */
	@RenderMapping(params = "action=backToEdit")
	public String backToEdit(Model model, RenderResponse response)
			throws LGSCheckedException, LGSRuntimeException, Exception {

		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(URL_SUB_AREA);
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID(URL_DISPLAY_ATTACHMENT);
		model.addAttribute(URL_CHL_TEMPLATE, resURL1.toString());
		model.addAttribute(URL_SUB_AREA, resURL.toString());

		return "changemanagement/assetAcceptance/assetAcceptanceDetails";
	}

	/**
	 * The method is called when "Attach" button is clicked from
	 * assetAcceptanceDetails page 
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param fileUploadForm 
	 * @param result 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@ActionMapping(params = "action=attachDocument")
	public void attachDocument(
			Model model,
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute(FILEUPLOADFORM) @Valid FileUploadForm fileUploadForm,
			BindingResult result) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME = "attachDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		PortletSession session = request.getPortletSession();

		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		if (result.hasErrors()) {

			fileUploadForm.setFileMap(fileMap);
			session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
			request.setAttribute(FILEUPLOADFORM, fileUploadForm);
			model.addAttribute(FILEUPLOADFORM, fileUploadForm);
			response.setRenderParameter("action", "attachDocument");
		} else {
			List<String> exceptionList = new ArrayList<String>();
			try {
				// List of exceptions to be displayed to user
				if (fileMap == null) {
					fileMap = new LinkedHashMap<String, FileObject>();
				}
				double fileSize = fileUploadForm.getFileData().getSize();
				fileSize = fileSize / 1024;
				DecimalFormat df = new DecimalFormat("0.0000");
				LOGGER.debug("File Size is " + df.format(fileSize));
				FileObject fileObject = new FileObject();
				FileItem fileItem = fileUploadForm.getFileData().getFileItem();
				String fileName = fileItem.getName();
				int index = fileName.indexOf("\\");
				if (index != 0) {
					int index1 = fileName.lastIndexOf("\\");
					fileName = fileName.substring(index1 + 1);
				}
				String diplayFileName = fileName.toString();
				fileObject.setDisplayFileName(diplayFileName);
				// Added to prevent upload of duplicate attachment
				Set keys = fileMap.keySet();
				Iterator i = keys.iterator();
				while (i.hasNext()) {
					String key = (String) i.next();
					FileObject value = (FileObject) fileMap.get(key);
					if (value.getDisplayFileName().equalsIgnoreCase(
							diplayFileName)) {
						fileUploadForm.setFileMap(fileMap);
						session.setAttribute(
								ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
						request.setAttribute(FILEUPLOADFORM, fileUploadForm);
						model.addAttribute(FILEUPLOADFORM, fileUploadForm);
						throw new LGSRuntimeException(
								PropertiesMessageUtil
										.getPropertyMessage(
												LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
												"validation.fileUpload.attachmentDuplicate.errorMsg",
												request.getLocale()));
					}
				}
				fileName = setTimestampInAttachment(fileName);
				fileObject.setFileName(fileName);
				fileObject.setFileSize(String.valueOf(df.format(fileSize)));
				fileObject.setFileSizeInBytes(String.valueOf(fileUploadForm
						.getFileData().getSize()));

				/** Code for file transfer begins */
				AttachmentProperties fileProperties = new AttachmentProperties(
						ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
				LOGGER.debug("Starting File Transfer" + fileProperties.getFileUploadDestination() + fileName);
				fileItem.write(new File(fileProperties
						.getFileUploadDestination()
						+ fileName));
				/** Code for file upload ends */

				fileMap.put(fileName, fileObject);
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());
				session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP,
						fileMap);

			} catch (LGSRuntimeException e) {
				exceptionList.add(e.getMessage());
			} catch (Exception e) {
				exceptionList.add(e.getMessage());
			}
			if (!exceptionList.isEmpty()) {
				model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
						exceptionList);
			}
			model.addAttribute(FILEUPLOADFORM, fileUploadForm);
			response.setRenderParameter("action", "attachDocument");
		}
	}

	/**
	 * The method is called from attachDocument method to render the target page
	 * 
	 * @param model 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping(params = "action=attachDocument")
	public String attachDocument(Model model) throws LGSCheckedException,
			LGSRuntimeException {

		model.addAttribute("source", "attachment");

		return "changemanagement/assetAcceptance/assetAcceptanceDetails";
	}

	/**
	 * The method is called when Remove icon is clicked for any attachment from
	 * assetAcceptanceDetails page
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param fileUploadForm  
	 * @param fileName 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@ActionMapping(params = "action=removeDocument")
	public void removeDocument(Model model, ActionRequest request,
			ActionResponse response,
			@ModelAttribute(FILEUPLOADFORM) FileUploadForm fileUploadForm,
			@RequestParam(value = PARAM_FILE_NAME) String fileName)
			throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME = "removeDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		PortletSession session = request.getPortletSession();
		List<String> exceptionList = new ArrayList<String>();
		try {
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session
					.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			if (fileName != null && fileMap != null
					&& fileMap.containsKey(fileName)) {
				AttachmentProperties fileProperties = new AttachmentProperties(
						ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
				File removableFile = new File(fileProperties
						.getFileUploadDestination()
						+ fileName);
				boolean delStatus = removableFile.delete();
				if (delStatus == false) {
					throw new LGSRuntimeException(
							"The attachment could not be removed.");
				} else {
					fileMap.remove(fileName);
				}
			}
			if (fileMap == null || fileMap.isEmpty()) {
				fileUploadForm.setFileMap(null);
				fileUploadForm.setFileCount(0);
				session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			} else {
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());
			}
		} catch (LGSRuntimeException e) {
			exceptionList.add(e.getMessage());
		}
		if (!exceptionList.isEmpty()) {
			model
					.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
							exceptionList);
		}
		model.addAttribute(FILEUPLOADFORM, fileUploadForm);
		response.setRenderParameter("action", "removeDocument");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * The method is called from removeDocument method to render the target page
	 * in the iframe
	 * 
	 * @param model 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping(params = "action=removeDocument")
	public String removeDocument(Model model) throws LGSCheckedException,
			LGSRuntimeException {

		model.addAttribute("source", "attachment");

		return "changemanagement/assetAcceptance/assetAcceptanceDetails";
	}

	/**
	 * to display attachment
	 * 
	 * @param request 
	 * @param response 
	 * @throws LGSRuntimeException 
	 */
	@ResourceMapping(value = "displayAttachment")
	public void displayAttachment(ResourceRequest request,
			ResourceResponse response) throws LGSRuntimeException {

		String fileType = request.getParameter(PARAM_FILE_TYPE);
		String fileName = request.getParameter(PARAM_FILE_NAME);
		if (FILE_TYPE_ATTACHMENT.equalsIgnoreCase(fileType)) {
			AttachmentProperties fileProperties = new AttachmentProperties(
					ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);

			openAttachment(request, response, fileName, fileProperties
					.getFileUploadDestination()
					+ fileName);
		}

	}

	/**
	 * This method is called from displayAttachment method to open the
	 * attachment
	 * 
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param fullPath 
	 */

	private void openAttachment(ResourceRequest request,
			ResourceResponse response, String fileName, String fullPath) {
		String METHOD_NAME = "openAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		HttpServletRequest httpReq = PortalUtil
				.getOriginalServletRequest(PortalUtil
						.getHttpServletRequest(request));
		String userAgent = httpReq.getHeader("User-Agent");
		if (userAgent.contains("MSIE 7.0")) {
			fileName = fileName.replace(" ", "%20");
		}
		response.setProperty("Content-disposition", "attachment; filename="
				+ fileName);

		if (fileName.indexOf("csv") > 0) {
			response.setContentType("application/vnd.ms-excel");
		} else if (fileName.indexOf("pdf") > 0) {
			response.setContentType("application/pdf");
		} else if (fileName.indexOf("xls") > 0) {
			response.setContentType("application/vnd.ms-excel");
		} else if (fileName.indexOf("doc") > 0) {
			response.setContentType("application/msword");
		} else if (fileName.indexOf("zip") > 0) {
			response.setContentType("application/zip");
		} else if (fileName.indexOf("xlsx") > 0) {
			response
					.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		} else if (fileName.indexOf("docx") > 0) {
			response
					.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		} else if (fileName.indexOf("ppt") > 0) {
			response.setContentType("application/vnd.ms-powerpoint");
		} else if (fileName.indexOf("pptx") > 0) {
			response
					.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		}
		
		try {
			InputStream inputStream = new FileInputStream(fullPath);
			OutputStream out = response.getPortletOutputStream();
			byte buf[] = new byte[1024];
			int len=0;
			while ((len = inputStream.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			inputStream.close();
		} catch (IOException e) {
			LOGGER.debug("exception in openAttachment");
			e.getMessage();
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Renders the print confirmation page of asset acceptance.
	 * 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printAssetAcceptance")
	public String showPrintAssetAcceptance() throws Exception {

		return "changemanagement/assetAcceptance/printAssetAcceptance";
	}

	/**
	 * Renders the email confirmation page for assetAcceptance
	 * 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=emailAssetAcceptance")
	public String showEmailAssetAcceptance() throws Exception {

		return "changemanagement/assetAcceptance/emailAssetAcceptance";
	}

	/**
	 * The method is called when "Submit" button is clicked from
	 * reviewAssetAcceptancePage
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param assetAcceptForm 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=createRequestAction")
	public void createRequestAction(
			Model model,
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute(ASSET_ACCEPT_FORM) AssetAcceptanceForm assetAcceptForm)
			throws Exception {
		String METHOD_NAME = "createRequestAction";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);

		/**************** This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(request, assetAcceptForm)) {
			LOGGER.debug("ManageAssetsController duplicated submit, do nothing!");
			model.addAttribute("error", ServiceStatusErrorMessageUtil
					.getErrorMessage(ERROR_MESSAGE_BUNDLE,
							"exception.serviceRequest.duplicateSubmission",
							null, request.getLocale()));
			PortletSession session = request.getPortletSession();

			Map<String, FileObject> fileMap = (Map<String, FileObject>) session
					.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			request.setAttribute(FILEMAP, fileMap);
			model.addAttribute(FILEMAP, fileMap);
			response.setRenderParameter("action", "createRequestActionSuccess");
			model.addAttribute(assetAcceptForm);
		} else {
			List<String> exceptionList = new ArrayList<String>();
			ServiceRequest serviceRequest = assetAcceptForm.getServiceRequest();
			serviceRequest.setServiceRequestNumber(assetAcceptForm
					.getPrevSRNumber());
			PortletSession session = request.getPortletSession();
			serviceRequest.setNotes(assetAcceptForm.getNotes());

			Map<String, FileObject> fileMap = (Map<String, FileObject>) session
					.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			CrmSessionHandle crmSessionHandle = globalService
					.initCrmSessionHandle(PortalSessionUtil
							.getSiebelCrmSessionHandle(request));
			try {
				CreateServiceRequestContract contract = commonController
						.getServiceReqContract(assetAcceptForm, request);
				Map<String, String> accDetails = (Map<String, String>) session
						.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
								PortletSession.APPLICATION_SCOPE);
				if (accDetails != null) {
					LOGGER.debug("AccountId:" + accDetails.get("accountId") + "AccountName:" + accDetails.get("accountName") + "AccountOrg:" + accDetails.get("accountOrganization") + "AgreementId:" + accDetails.get("agreementId"));
				} else {
					accDetails = new HashMap<String, String>(); // Go for
																// creation of
																// an empty map
				}
				if (assetAcceptForm.getRequestedFrom() != null
						&& assetAcceptForm.getRequestedFrom() != "") {
					contract.setRequestedFrom(assetAcceptForm
							.getRequestedFrom());
				}
				String userType = PortalSessionUtil.getUserType(session);
				long timeBeforeCall = System.currentTimeMillis();
				
				CreateServiceRequestResult result = assetAcceptanceService
						.createCMRequest(contract, accDetails);
				long timeAfterCall=System.currentTimeMillis();
				LOGGER.info("start printing lex logger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ASSETACC_MSG_CREATECMREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
				LOGGER.info("end printing lex loggger");
				
				if (result != null) {
					assetAcceptForm.getServiceRequest()
							.setServiceRequestNumber(
									result.getServiceRequestNumber());
				}

				/** Code for File Upload begins */
				if (fileMap != null) {
					List<Attachment> attachmentList = new ArrayList<Attachment>();
					Set<String> fileNames = fileMap.keySet();
					Iterator<String> iterator = fileNames.iterator();
					while (iterator.hasNext()) {
						String fileName = iterator.next();
						FileObject fileDetails = fileMap.get(fileName);
						Attachment file=null;
						file = new Attachment();
						file.setVisibility(userType);
						file.setAttachmentName(fileDetails.getFileName());
						file.setSize(Integer.parseInt(fileDetails
								.getFileSizeInBytes()));
						attachmentList.add(file);
					}
					AttachmentContract atchmntContract = new AttachmentContract();
					atchmntContract.setSessionHandle(crmSessionHandle);
					if (result != null) {
						atchmntContract.setIdentifier(result
								.getServiceRequestRowId());
					}
					atchmntContract
							.setRequestType(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE); // For
																							// service
																							// request
																							// you
																							// have
																							// to
																							// send
					atchmntContract.setAttachments(attachmentList);
					
					try {
						attachmentService.uploadAttachments(atchmntContract);
					} catch (Exception exception) {
						exceptionList.add(exception.getMessage());
					}
				}
			} catch (SiebelCrmServiceException e) {
				exceptionList.add(e.getMessage());
			} catch (LGSBusinessException e) {
				exceptionList.add(e.getMessage());
			} catch (Exception e) {
				exceptionList.add(e.getMessage());
			} finally {
				globalService.releaseSessionHandle(crmSessionHandle);
			}
			/** Code for File Upload ends */

			request.setAttribute(FILEMAP, fileMap);
			model.addAttribute(FILEMAP, fileMap);
			model.addAttribute(assetAcceptForm);
			model.addAttribute(ASSET_ACCEPT_FORM, assetAcceptForm);
			if (exceptionList.isEmpty()) {
				response.setRenderParameter("action",
						"createRequestActionSuccess");
				// this is to enable re-submit of SR form on submit/draft
				// exception
				Long tokenInSession = (Long) session.getAttribute(
						LexmarkConstants.SUBMIT_TOKEN,
						PortletSession.PORTLET_SCOPE);
				assetAcceptForm.setSubmitToken(tokenInSession);
			} else {
				model.addAttribute("exceptionList", exceptionList);
				response.setRenderParameter("action",
						"createRequestActionError");
				// this is to enable re-submit of SR form on submit/draft
				// exception
				Long tokenInSession = (Long) session.getAttribute(
						LexmarkConstants.SUBMIT_TOKEN,
						PortletSession.PORTLET_SCOPE);
				assetAcceptForm.setSubmitToken(tokenInSession);
			}
		}
	}

	/**
	 * The method is called from createAssetAcceptanceRequest method to render
	 * the confirmation page for assetAcceptance
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params = "action=createRequestActionSuccess")
	public String createRequestActionSuccess(Model model,
			RenderRequest request, RenderResponse response) {

		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("displayAttachment");
		model.addAttribute("attachmentURL", resURL.toString());

		return "changemanagement/assetAcceptance/assetAcceptanceConfirm";
	}

	/**
	 * The method is called from createAssetAcceptanceRequest method to render
	 * the confirmation page for assetAcceptance
	 * 
	 * @param model 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params = "action=createRequestActionError")
	public String createRequestActionError(Model model, RenderResponse response) {

		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("displayAttachment");
		model.addAttribute("attachmentURL", resURL.toString());

		return "changemanagement/assetAcceptance/assetAcceptanceReview";
	}

	/**
	 * This method is called from attachDocument method to set the fileName 
	 * 
	 * @param fName 
	 * @return String 
	 * @throws LGSBusinessException 
	 */
	private String setTimestampInAttachment(String fName)
			throws LGSBusinessException {

		int index = fName.lastIndexOf(".");
		String fExtension = fName.substring(index);
		fName = fName.substring(0, index);
		String fNameFinal = fName + "_" + System.currentTimeMillis()
				+ fExtension;

		return fNameFinal;
	}

	/**
	 * 
	 * @return String
	 */
	public String getListOfFileTypes() {

		return listOfFileTypes;
	}

	/**
	 * 
	 * @param listOfFileTypes 
	 */
	public void setListOfFileTypes(String listOfFileTypes) {

		this.listOfFileTypes = listOfFileTypes;
	}

	/**
	 * 
	 * @return String
	 */
	public String getAttMaxCount() {

		return attMaxCount;
	}

	/**
	 * 
	 * @param attMaxCount 
	 */
	public void setAttMaxCount(String attMaxCount) {

		this.attMaxCount = attMaxCount;
	}

	/* Added for CI7 BRD14-02-12 */
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAcceptancePage")
	public void showAcceptancePage(ActionRequest request,
			ActionResponse response, Model model) throws Exception {
		
		LOGGER.debug("------------------ RequestMapping of the showAcceptancePage -----------------------");
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	/* END */
}
