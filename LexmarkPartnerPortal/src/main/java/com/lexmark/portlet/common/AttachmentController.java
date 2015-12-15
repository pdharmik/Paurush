/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AttachmentController
 * Package     		: com.lexmark.services.portlet.common
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.portlet.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.domain.FileObject;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.form.FileUploadForm;
import com.lexmark.form.validator.FileUploadFormValidator;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.ExcelToCSVConverter;
import com.lexmark.util.PropertiesMessageUtil;

/**
 * This is used for common Attachment
 * @version 1.0
 * @author Wipro
 * */
@Controller
@RequestMapping("VIEW")
public class AttachmentController  {
	
	private static final LEXLogger LOGGER = LEXLogger
	.getLEXLogger(AttachmentController.class);
	private static String CLASS_NAME = "AttachmentController.java";
	


	private static final String PARAM_FILE_NAME = "fileName";
	private static final String FILE_UPLOAD_FORM = "fileUploadForm";
	
	
	@Autowired
	private FileUploadFormValidator fileUploadFormValidator;

	
	
	/**
	 * This method sets the file upload form validator
	 * @param fileUploadFormValidator 
	 */

	public void setFileUploadFormValidator(
			FileUploadFormValidator fileUploadFormValidator) {
		this.fileUploadFormValidator = fileUploadFormValidator;
	}

	/**
	 * @param binder 
	 * 
	 * */
	@InitBinder(value = {FILE_UPLOAD_FORM})
	protected void initBinder(WebDataBinder binder) {
		String METHOD_NAME="initBinder";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		
		if (binder.getTarget() instanceof FileUploadForm) {
			LOGGER.debug("Setting FileUploadForm validator");
			binder.setValidator(fileUploadFormValidator);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}
	
	/**
	 * This method is used to attach document in attachment section
	 * @param request 
	 * @param response 
	 * @param fileUploadForm 
	 * @param result 
	 * @param model 
	 * */
	@SuppressWarnings("unchecked")
	@ActionMapping(params = "action=attachDocument")
	public void attachDocument(
			Model model,
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute(FILE_UPLOAD_FORM)  
			FileUploadForm fileUploadForm,
			BindingResult result){
		
		
		String METHOD_NAME = "attachDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside attachmentController >> attachDocument method");
		LOGGER.debug("file size is"+ fileUploadForm.getFileData().getSize());
		
		LOGGER.debug(" session key for upload map is "+fileUploadForm.getSessionFileKey());
		
		PortletSession session=request.getPortletSession();
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
		.getAttribute(fileUploadForm.getSessionFileKey());
		
		if(fileMap==null){
			fileMap = new LinkedHashMap<String, FileObject>();
		}else{
			//user already uploaded a file need to check how many files can be uploaded
			LOGGER.debug("fileMap.keySet()="+fileMap.keySet());
			fileUploadForm.setFileCount(fileMap.keySet().size());
		}
		
		fileUploadFormValidator.validate(fileUploadForm, result);	
		
			
		
		if (result.hasErrors()) {
			LOGGER.debug("Contains Validation Errors");
			model.addAttribute("errors", result);
			
		} else{
			try{
					double fileSize = fileUploadForm.getFileData().getSize();
					LOGGER.debug("file size before dividing"+fileSize);
					fileSize = fileSize / 1024;
					LOGGER.debug("File Size is " + fileSize);
					FileObject fileObject = new FileObject();
					
					FileItem fileItem = fileUploadForm.getFileData().getFileItem();
					String fileName = fileItem.getName();
					int index = fileName.indexOf("\\");
		
					if (index != 0) {
						int index1 = fileName.lastIndexOf("\\");
						fileName = fileName.substring(index1 + 1);
					}
					
					fileObject.setDisplayFileName(fileName);
					
					fileObject.setFileName(setTimestampInAttachment(fileName));
					fileObject.setFileSize(String.valueOf(fileSize));
					fileObject.setFileSizeInBytes(String.valueOf(fileUploadForm
							.getFileData().getSize()));
					
					String gmtDateString=DateUtil.convertDateToGMTString(new Date());
					fileObject.setUploadDate(gmtDateString);
					
					/** Code for file transfer begins */
					String requestType=LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE;
					if(StringUtils.isNotBlank(fileUploadForm.getPageType()) && fileUploadForm.getPageType().equalsIgnoreCase(LexmarkPPConstants.HARDWAREORDER)){
						requestType=LexmarkPPConstants.HARDWARE_DEBRIEF;
					}
						AttachmentProperties fileProperties = new AttachmentProperties(requestType);
						
						LOGGER.debug("Starting File Transfer"+ fileProperties.getFileUploadDestination() + fileObject.getFileName());
						
						fileItem.write(new File(fileProperties.getFileUploadDestination() + fileObject.getFileName()));
						
						
						
						LOGGER.debug("File Transfer is complete ");	
					
						fileMap.put(fileObject.getFileName(), fileObject);
						
						
						session.setAttribute(fileUploadForm.getSessionFileKey(),fileMap);
						
						
			
			}catch(Exception e){
				result.reject("validation.fileUpload.attachmentNotDone.errorMsg");
				model.addAttribute("errors", result);
				
				LOGGER.error("Exception occured while attaching"+e.getMessage());
				
			}finally{
				model.addAttribute("fileUploadDetailsMap", fileMap);
			}
			
						
			
		}
		model.addAttribute("fileUploadDetailsMap", fileMap);
		response.setRenderParameter("action", "uploadDetailsPage");
	}
	/**
	 * This method let us navigate to Mass Upload details Page
	 * @return jsp page
	 * */	
	@RequestMapping(params = "action=uploadDetailsPage")
	public String showUploadDetails(){
		LOGGER.enter(CLASS_NAME, "showUploadDetails");
		LOGGER.exit(CLASS_NAME, "showUploadDetails");
		return "massUpload/UploadDetails";
	}
	/**
	 * This method let us download the attached files
	 * @param request 
	 * @param response
	 * @param sessionKey
	 * @param index    
	 * @param isHardware 
	 * */
	@SuppressWarnings("unchecked")
	@ResourceMapping("downloadUploadedAttachment")
	public void downloadAttachment(ResourceRequest request,
			@RequestParam("sessionKey") String sessionKey,
			@RequestParam("index") int index,
			@RequestParam("isHw") boolean isHardware,
			ResourceResponse response) {
		
		
		String METHOD_NAME="downloadAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		PortletSession session=request.getPortletSession();
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
		.getAttribute(sessionKey);
		
		response.setContentType("text/csv");
		String requestType=LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE;
		if(isHardware){
			requestType=LexmarkPPConstants.HARDWARE_DEBRIEF;
		}
		AttachmentProperties fileProperties = new AttachmentProperties(requestType);
		LOGGER.debug("uploaded path "+ fileProperties.getFileUploadDestination());
		
		
		
		
		Set<String> fileNames=fileMap.keySet();
		StringBuffer uploadedFileName=new StringBuffer();
		int i=0;
		for(String fileName:fileNames){
			if(i==index){
				uploadedFileName.append(fileName);
				
				break;
			}
			i++;
			
		}
		
		final String fullPath=fileProperties.getFileUploadDestination()+uploadedFileName;
		response.setProperty("Content-disposition", "attachment; filename=\""
				+ uploadedFileName + "\"");
		response.setContentType(ControllerUtil.getContentTypeAccordingToFile(uploadedFileName.toString()));
		LOGGER.debug("fileName " + uploadedFileName + " fullPath " + fullPath);
		InputStream inputStream=null;
		OutputStream outputStream=null;
		try {
			LOGGER.debug("fullPath " + fullPath);
			inputStream = new FileInputStream(fullPath);
			outputStream = response.getPortletOutputStream();
			byte buf[] = new byte[1024];
			int len=0;
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			
			LOGGER.debug("File is created...................................");
			outputStream.close(); 
			inputStream.close(); 
		} catch (IOException e) {
			LOGGER.error("IOException occurred ::" + e);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	/**
	 * This method let us remove attached documents
	 * @param request 
	 * @param response 
	 * @param fileUploadForm 
	 * @param result 
	 * @param model 
	 * */
	@SuppressWarnings("unchecked")
	@ActionMapping(params="action=removeAttachment")
	public void removeAttachment(ActionRequest request,ActionResponse response,
			@ModelAttribute("fileUploadForm") FileUploadForm fileUploadForm,
			BindingResult result,Model model){
		final String METHOD_NAME="removeAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
	
		PortletSession session=request.getPortletSession();
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
		.getAttribute(fileUploadForm.getSessionFileKey());
		
		try{
					
				
			
				Set<String> fileNames=fileMap.keySet();
				String uploadedFileName=null;
				String tempuploadedFileName=null;
				int i=0;
				for(String fileName:fileNames){
					if(i==fileUploadForm.getFileIndex()){
						uploadedFileName=fileName;
						
						break;
					}
					i++;
				}
				LOGGER.debug("file name is "+uploadedFileName);
				String requestType=LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE;
				if(StringUtils.isNotBlank(fileUploadForm.getPageType()) && fileUploadForm.getPageType().equalsIgnoreCase(LexmarkPPConstants.HARDWAREORDER)){
					requestType=LexmarkPPConstants.HARDWARE_DEBRIEF;
				}
				AttachmentProperties fileProperties = new AttachmentProperties(requestType);
				File removableFile = new File(
						fileProperties.getFileUploadDestination() + uploadedFileName);
				boolean delStatus = removableFile.delete();
		
				if (delStatus == false) {
					result.reject("validation.fileUpload.removeAttachment.errorMsg");
				}else{
					/**
					 * Since the extension has been changed to csv so 
					 * have to remove the original file name from map.*/
					if(StringUtils.isNotBlank(fileUploadForm.getPageType())
							&& LexmarkPPConstants.MASSUPLOADHW.equalsIgnoreCase(fileUploadForm.getPageType())){
							tempuploadedFileName = uploadedFileName; 
							/** The above line is added
							 * to remove attachment in Mass Upload in Partner Portal does not work(CRM-ETabat201407251138) */
						fileMap.remove(tempuploadedFileName);
					}else{
						fileMap.remove(uploadedFileName);
					}
					
				}
				
		}catch (Exception exception){
				LOGGER.error("error occured"+exception.getMessage());
				result.reject("validation.fileUpload.removeAttachment.errorMsg");
				
		}finally{
			
			LOGGER.exit(CLASS_NAME, METHOD_NAME);
			model.addAttribute("errors", result);
			
			session.setAttribute(fileUploadForm.getSessionFileKey(),fileMap);
			model.addAttribute("fileUploadDetailsMap", fileMap);
			response.setRenderParameter("action", "uploadDetailsPage");
		}

		
	
		
		

		
		
	}
	/**
	 * This method let us set Time Stamp in the Attachment
	 *@param fName 
	 *@return fName file name 
	 *@throws LGSBusinessException 
	 * */
	
	private String setTimestampInAttachment(String fName)
			throws LGSBusinessException {
		String METHOD_NAME = "setTimestampInAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside setTimestampInAttachment method start ");
		int index = fName.lastIndexOf(".");
		String fExtension = fName.substring(index);
		fName = fName.substring(0, index);
		String fNameFinal = fName + "#_" + System.currentTimeMillis()
				+ fExtension;
		LOGGER.debug("Inside setTimestampInAttachment method end ");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return fNameFinal;
	}
	
	/**
	 * This method clears the file and the map
	 * @param request 
	 * @param sessionKey 
	 * @return boolean 
	 * @throws Exception 
	 */
	public boolean clearUploadedFileFromSession(PortletRequest request,String sessionKey)throws Exception{
		LOGGER.enter("AttachmentController.java","clearUploadedFileFromSession");
		boolean success=false;
		
		PortletSession session=request.getPortletSession();
		Map<?, ?> fileMap = (Map<?, ?>) session.getAttribute(sessionKey);
		if(fileMap!=null){
			Set<?> keys=fileMap.keySet();
			for(Object key:keys){
				String uploadedFileName=(String)key;
				AttachmentProperties fileProperties = new AttachmentProperties(
						LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE);
				File removableFile = new File(
						fileProperties.getFileUploadDestination() + uploadedFileName);
				boolean delStatus = removableFile.delete();
				LOGGER.debug("deleted file "+uploadedFileName);
				if(delStatus){
					success=true;
				}else{
					success=false;
				}
			}
			session.setAttribute(sessionKey, null);
		}
		LOGGER.exit("AttachmentController.java","clearUploadedFileFromSession");
		return success;
	}
}
