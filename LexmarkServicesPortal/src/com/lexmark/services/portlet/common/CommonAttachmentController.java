package com.lexmark.services.portlet.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.CatalogDetailPageForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.HardwareDetailPageForm;
import com.lexmark.services.form.ManageAddressForm;
import com.lexmark.services.form.ManageAssetForm;
import com.lexmark.services.form.ManageContactForm;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.cm.ManageAssetsController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.liferay.portal.util.PortalUtil;
/**
 * @author Wipro 
 * @version 2.1
 * Common Attachment Controller 
 *
 */
@Controller
@RequestMapping("VIEW")

public class CommonAttachmentController {
	
	/**
	 * fileUploadFormValidator 
	 */
	@Autowired
    public FileUploadFormValidator fileUploadFormValidator;

	/**
	 * globalService 
	 */
	@Autowired
    private GlobalService  globalService;
	/**
	 * attachmentService 
	 */
	@Autowired
	private AttachmentService attachmentService;
	/**
	 * variable declaration
	 */
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageAssetsController.class);
	/**
	 * variable declaration
	 */
	private static final String METH_INITBINDER = "initbinder";
	/**
	 * This method is used to bind the validator for form beans,
	 * Also custom date editor for LTPC fields are registered here
	 * @param binder 
	 */
	@InitBinder(value={ "attachmentForm"})
	protected void initBinder(WebDataBinder binder) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_INITBINDER);	
		if(binder.getTarget() instanceof FileUploadForm){
			LOGGER.debug("Setting FileUploadForm validator");
			binder.setValidator(fileUploadFormValidator);
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_INITBINDER);
	}
	
	
	
	/**
	 * This method is used to upload any attachment	
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param manageAssetForm 
	 * @param manageAssetFormForChange 
	 * @param manageAssetFormForDecom 
	 * @param addressForm 
	 * @param manageContactForm 
	 * @param assetDetailPageForm 
	 * @param catalogDetailPageForm 
	 * @param hardwareDetailPageForm 
	 * @param fileUploadForm 
	 * @param attachForm 
	 * @param result 
	 * @param path 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=addAttachmentsCreate")
	public void addAttachment(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("manageAssetForm") ManageAssetForm manageAssetForm,
			@ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM) ManageAssetForm manageAssetFormForChange,
			@ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM) ManageAssetForm manageAssetFormForDecom,
			@ModelAttribute("addressForm") ManageAddressForm addressForm,
			@ModelAttribute("manageContactForm") ManageContactForm manageContactForm,
			@ModelAttribute("assetDetailPageForm") AssetDetailPageForm assetDetailPageForm,
			@ModelAttribute("catalogDetailPageForm") CatalogDetailPageForm catalogDetailPageForm,
			@ModelAttribute("hardwareDetailPageForm") HardwareDetailPageForm hardwareDetailPageForm,
			@ModelAttribute("fileUploadForm") @Valid FileUploadForm 
			fileUploadForm, @ModelAttribute ("attachmentForm") AttachmentForm attachForm,
			BindingResult result, Object path) throws Exception {
		LOGGER.debug("Inside fileUploadmethod ");
		LOGGER.debug("Inside fileUploadmethod "+ attachForm.getFileData());
		if (result.hasErrors()) {
			LOGGER.debug("Contains Validation Errors");
			request.setAttribute("fileUploadForm",fileUploadForm);
			model.addAttribute("fileUploadForm",fileUploadForm);
		} else{
		PortletSession session = request.getPortletSession();
		double fileSizeDisplay=attachForm.getFileData().getSize();
		LOGGER.debug("File Size is " + fileSizeDisplay);
		fileSizeDisplay=fileSizeDisplay/1024;
		BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		LOGGER.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
		int fileSize=(int) attachForm.getFileData().getSize();
			LOGGER.debug("File Size is " + fileSize);
			FileItem fileItem=attachForm.getFileData().getFileItem();
			String inputFileName=returnFileName(fileItem.getName());
			String displayAttachmentName = "";
			LOGGER.debug("Inside fileUploadmethod fileItem.getFieldName()"+ fileItem.getFieldName()+" fileItem.getName is "+inputFileName);
			//put filename in the attachment list
			List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			if(attachmentList == null) {
				attachmentList = new ArrayList<Attachment>();
			}
			// Setting for duplicate File
			boolean fileExists=false;
			boolean fileSizeLimit=false;
			if(fileSize>1048576){
				LOGGER.debug("fileSizeLimit----------> "+fileSize);
				fileSizeLimit=true;
				model.addAttribute("fileSizeError","error");
			}
			// Checking for Duplicate File
			if(attachmentList!=null){
				if(!attachmentList.isEmpty()){
					
			for(int i=0;i<attachmentList.size();i++)
			{
				Attachment attachment =(Attachment)attachmentList.get(i);
				LOGGER.debug("attachment.getAttachmentName----------> "+attachment.getAttachmentName());
				LOGGER.debug("attachment.getDisplayAttachmentName----------> "+attachment. getDisplayAttachmentName());
				//Changed the below line for defect #4141
				
				if(inputFileName.equals(attachment.getDisplayAttachmentName())){
				fileExists=true;
				LOGGER.debug("File Already Exists breaking the loop :: "+fileExists);
				break;
				}
			}
			}}
			LOGGER.debug("Final FileExists :: "+fileExists);
			
			if(!fileExists && !fileSizeLimit){
			LOGGER.debug("Adding the file attachment !!!! "+inputFileName);
			displayAttachmentName = inputFileName;
			Attachment fileAttachment = new Attachment();
			inputFileName = setTimestampInAttachment(inputFileName);
			LOGGER.debug("Filename setting is "+inputFileName);
			fileAttachment.setAttachmentName(inputFileName);
			fileAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
			fileAttachment.setSize(fileSize);
			
			if(PortalSessionUtil.getUserType(session).equalsIgnoreCase("Vendor")){
				fileAttachment.setVisibility("Partner");
			}else{
				fileAttachment.setVisibility(PortalSessionUtil.getUserType(session));
			}
			
			fileAttachment.setDisplayAttachmentName(displayAttachmentName);
			attachmentList.add(fileAttachment);
			LOGGER.debug(" Attaching the File !!!!");
			attachForm.setAttachmentList(attachmentList);
			LOGGER.debug("Attachment size is "+attachmentList.size());
			attachForm.setFileCount(attachmentList.size());
			fileUploadForm.setFileCount(attachmentList.size());
			session.setAttribute("attachmentList", attachmentList);
			session.setAttribute("attachmentForm", attachForm);
			AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			LOGGER.debug("Starting File Transfer" + fileProperties.getFileUploadDestination());
			String path1 = fileProperties.getFileUploadDestination();
			try {
				fileItem.write(new File(path1 + inputFileName));
			} catch (Exception e) {
				model.addAttribute("fileUploadForm",fileUploadForm);
				request.setAttribute("attachmentForm",attachForm);
				model.addAttribute("attachmentForm",attachForm);
				model.addAttribute("manageAssetForm",manageAssetForm);
				model.addAttribute("manageAssetFormForChange",manageAssetFormForChange);
				model.addAttribute("manageAssetFormForDecommission",manageAssetFormForDecom);
				model.addAttribute("addressForm",addressForm);
				model.addAttribute("manageContactForm", manageContactForm);
				response.setRenderParameter("pageType", request.getParameter("pageType"));
				response.setRenderParameter("action", "attachDocumentListDisplay");
				e.getMessage();
				
				
			}
			}
			else{
				LOGGER.debug("Not Attached Filename already existing in the File share DUPLICATE !!!! "+inputFileName);
				attachForm.setAttachmentList(attachmentList);
				LOGGER.debug("Attachment size is "+attachmentList.size());
				attachForm.setFileCount(attachmentList.size());
				fileUploadForm.setFileCount(attachmentList.size());
				session.setAttribute("attachmentList", attachmentList);
				session.setAttribute("attachmentForm", attachForm);
				//Added for duplicate file upload with the same name - for defect 4141
				result.reject("validation.fileUpload.attachmentDuplicate.errorMsg");
			}
			
		}
			model.addAttribute("fileUploadForm",fileUploadForm);
			request.setAttribute("attachmentForm",attachForm);
			model.addAttribute("attachmentForm",attachForm);
			model.addAttribute("manageAssetForm",manageAssetForm);
			model.addAttribute("manageAssetFormForChange",manageAssetFormForChange);
			model.addAttribute("manageAssetFormForDecommission",manageAssetFormForDecom);
			model.addAttribute("addressForm",addressForm);
			model.addAttribute("manageContactForm", manageContactForm);
			LOGGER.debug("before-->"+request.getParameter("pageType"));
			response.setRenderParameter("pageType", request.getParameter("pageType"));
			response.setRenderParameter("action", "attachDocumentListDisplay");
	}
	
	/**
	 * @param request 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=attachDocumentListDisplay")
	public String attachDocument(RenderRequest request) throws Exception {
		LOGGER.debug("after-->"+request.getParameter("pageType"));
		if(request.getParameter("pageType").equals("addAsset"))
		{
			return "changemanagement/manageAsset/addAsset";
		}
		else if(request.getParameter("pageType").equals("changeAsset"))
		{
			return "changemanagement/manageAsset/changeAsset";	
		}
		else if(request.getParameter("pageType").equals("decommissionAsset"))
		{
			return "changemanagement/manageAsset/decommissionAsset";
		}
		else if(request.getParameter("pageType").equals("addAddress"))
		{
			return "changemanagement/manageAddress/addAddress";
		}
		else if(request.getParameter("pageType").equals("changeAddress"))
		{
			return "changemanagement/manageAddress/changeAddress";
		}
		else if(request.getParameter("pageType").equals("removeAddress"))
		{
			return "changemanagement/manageAddress/removeAddress";
		}
		else if(request.getParameter("pageType").equals("addContact"))
		{
			return "changemanagement/manageContact/addContact";
		}
		else if(request.getParameter("pageType").equals("changeContact"))
		{
			return "changemanagement/manageContact/changeContact";
		}
		else if(request.getParameter("pageType").equals("removeContact"))
		{
			return "changemanagement/manageContact/removeContact";
		}else if(request.getParameter("pageType").equals("catalogDetails"))
		{
			return "ordermanagement/catalogOrder/catalogDetail";
		}else if(request.getParameter("pageType").equals("assetDetails"))
		{
			return "orderSuppliesAssetOrder/assetDetail";
		}else if(request.getParameter("pageType").equals("hardwareDetails"))
		{
			return "ordermanagement/hardwareOrder/hardwareDetail";
		}
		else
		{
			LOGGER.debug("After displaying attachment");
			return "none";
		}
	}	
	
	/**
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param manageAssetForm 
	 * @param manageAssetFormForChange 
	 * @param manageAssetFormForDecom 
	 * @param addressForm 
	 * @param manageContactForm 
	 * @param assetDetailPageForm 
	 * @param catalogDetailPageForm 
	 * @param hardwareDetailPageForm 
	 * @param attachForm 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=removeAttachment")
	public void removeAttachment(ActionRequest request, ActionResponse response,
			@RequestParam("fileName") String fileName,
			@ModelAttribute("manageAssetForm") ManageAssetForm manageAssetForm,
			@ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM) ManageAssetForm manageAssetFormForChange,
			@ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM) ManageAssetForm manageAssetFormForDecom,
			@ModelAttribute("addressForm") ManageAddressForm addressForm,
			@ModelAttribute("manageContactForm") ManageContactForm manageContactForm,
			@ModelAttribute("assetDetailPageForm") AssetDetailPageForm assetDetailPageForm,
			@ModelAttribute("catalogDetailPageForm") CatalogDetailPageForm catalogDetailPageForm,
			@ModelAttribute("hardwareDetailPageForm") HardwareDetailPageForm hardwareDetailPageForm,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,
			Model model) throws Exception {
		LOGGER.debug("Inside removeAttachment ");
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		boolean draftAttachment = false;
		LOGGER.debug("Filename to be deleted "+ fileName);
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		List<Attachment> newAttachmentList = new ArrayList<Attachment>();
		String identifier = "";
		if("".equals(fileName)){
			return;
		}
		for(Attachment attachment:attachmentList){
			if(fileName.equalsIgnoreCase(attachment.getAttachmentName())){
				identifier = attachment.getId();
				if(identifier!=null){
					draftAttachment = true;
				}
				break;
			}
		}
		if (!draftAttachment){
			//Delete the file from the filesystem
			AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			LOGGER.debug("Starting File Transfer" + fileProperties.getFileUploadDestination());
			String path = fileProperties.getFileUploadDestination();
			String localWebPath = composeFilePath(path,fileName);
			
			File tempFile = new File(localWebPath);
			LOGGER.debug("------------------- localWebPath from where we have to delete the file "+localWebPath);
			try {
				tempFile.delete();
				
			}
			catch(Exception ex){
				LOGGER.error("Error occurred while trying to delete temporary file for Siebel file upload");
				LOGGER.error(ex.getMessage());
				if( ex.getCause() != null && ex.getCause().getMessage() != null) {
					LOGGER.error("Cause: " + ex.getCause().getMessage());
				}
			}
		}else{

			//Delete the file from siebel so call aMind's delete attachment service
				LOGGER.debug("Entered delete attachment for draft order block");
				for(Attachment attachment:attachmentList){
					if(fileName.equalsIgnoreCase(attachment.getAttachmentName())){
						identifier = attachment.getId();
						break;
					}
				}
				LOGGER.debug("Filename to be deleted is "+fileName+" and identifier is "+identifier);
				AttachmentContract attachmentContract = new AttachmentContract();
				attachmentContract.setIdentifier(identifier);
				attachmentContract.setSessionHandle(crmSessionHandle);
				LOGGER.debug("before calling delete attachment amind service method contact "+attachmentContract.getIdentifier());
				attachmentService.deleteAttachment(attachmentContract);
				LOGGER.debug("amind service call is done");
				LOGGER.debug("Exit from the delete attachment from draft order block");
			
		}
			//Delete the file from the list
			for(Attachment attachment:attachmentList){
				if(!fileName.equalsIgnoreCase(attachment.getAttachmentName())){
					newAttachmentList.add(attachment);
				}
			}
			
			session.setAttribute("attachmentList", newAttachmentList);
			//just check if the file is still there
			for(Attachment attachment:newAttachmentList){
				LOGGER.debug("@@@@@@@@@@@@@ After deleting the remaining filenames are "+attachment.getAttachmentName());
			}
			
			attachForm.setAttachmentList(newAttachmentList);
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("fileUploadForm", fileUploadForm);
		request.setAttribute("attachmentForm",attachForm);
		model.addAttribute("attachmentForm",attachForm);
		model.addAttribute("manageAssetForm",manageAssetForm);
		model.addAttribute("manageAssetFormForChange",manageAssetFormForChange);
		model.addAttribute("manageAssetFormForDecommission",manageAssetFormForDecom);
		model.addAttribute("addressForm",addressForm);
		model.addAttribute("manageContactForm", manageContactForm);
		response.setRenderParameter("pageType", request.getParameter("pageType"));
		response.setRenderParameter("action", "removeAttachmentRender");	
		LOGGER.debug("========== Edn removeAttachment ================");
	}
	
	/**
	 * @param request 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=removeAttachmentRender")
	public String removeAttachmentRender(RenderRequest request) throws Exception {

		LOGGER.debug("after-->"+request.getParameter("pageType"));
		if(request.getParameter("pageType").equals("addAsset"))
		{
			return "changemanagement/manageAsset/addAsset";
		}
		else if(request.getParameter("pageType").equals("changeAsset"))
		{
			return "changemanagement/manageAsset/changeAsset";	
		}
		else if(request.getParameter("pageType").equals("decommissionAsset"))
		{
			return "changemanagement/manageAsset/decommissionAsset";
		}
		else if(request.getParameter("pageType").equals("addAddress"))
		{
			return "changemanagement/manageAddress/addAddress";
		}
		else if(request.getParameter("pageType").equals("changeAddress"))
		{
			return "changemanagement/manageAddress/changeAddress";
		}
		else if(request.getParameter("pageType").equals("removeAddress"))
		{
			return "changemanagement/manageAddress/removeAddress";
		}
		else if(request.getParameter("pageType").equals("addContact"))
		{
			return "changemanagement/manageContact/addContact";
		}
		else if(request.getParameter("pageType").equals("changeContact"))
		{
			return "changemanagement/manageContact/changeContact";
		}
		else if(request.getParameter("pageType").equals("removeContact"))
		{
			return "changemanagement/manageContact/removeContact";
		}else if(request.getParameter("pageType").equals("catalogDetails"))
		{
			return "ordermanagement/catalogOrder/catalogDetail";
		}else if(request.getParameter("pageType").equals("assetDetails"))
		{
			return "orderSuppliesAssetOrder/assetDetail";
		}else if(request.getParameter("pageType").equals("hardwareDetails"))
		{
			return "ordermanagement/hardwareOrder/hardwareDetail";
		}
		else
		{
			LOGGER.debug("After removing attachment");
			return "none";
		}
		
	}
	
	
	/**
	 * @param request 
	 * @param response 
	 * @throws LGSRuntimeException 
	 */
	@ResourceMapping(value="displayAttachment")
	public void displayAttachment(ResourceRequest request,ResourceResponse response) throws LGSRuntimeException{			
		
		LOGGER.debug("Inside displayAttachment...");
		
		String fileName = request.getParameter("fileName");
		
		AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
		LOGGER.debug("Starting File Transfer" + fileProperties.getFileUploadDestination() + fileName);
		openAttachment(request,response, fileName, fileProperties.getFileUploadDestination() + fileName);
		
		LOGGER.debug("End of displayAttachment...");
		
	}
	/**
	 * This method is used to opening any attachment and called from displayAttachment method 
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param fullPath 
	 */
	private void openAttachment(ResourceRequest request, ResourceResponse response,String fileName, String fullPath){
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String userAgent = httpReq.getHeader("User-Agent");
	    if (userAgent.contains("MSIE 7.0")) {
	    	fileName = fileName.replace(" ", "%20");   
	    }   
	    response.setProperty("Content-disposition", "attachment; filename=\"" + fileName +"\"");
		LOGGER.debug("fileName " + fileName);
		LOGGER.debug("fullPath " + fullPath);
		if(fileName.indexOf("csv")>0){
			LOGGER.debug("...fileName.indexOf(csv)" + fileName.indexOf("csv"));
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("pdf") > 0) {
        	LOGGER.debug("...fileName.indexOf(pdf)" + fileName.indexOf("pdf"));
        	response.setContentType("application/pdf");
        }else if(fileName.indexOf("xls")>0){
        	LOGGER.debug("...fileName.indexOf(xls)" + fileName.indexOf("xls"));
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("doc")>0){
        	LOGGER.debug("...fileName.indexOf(doc)" + fileName.indexOf("doc"));
        	response.setContentType("application/msword");
        }else if(fileName.indexOf("zip") > 0) {
        	response.setContentType("application/zip");
        }else if(fileName.indexOf("xlsx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }else if(fileName.indexOf("docx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
        else if(fileName.indexOf("ppt")>0){
        	response.setContentType("application/vnd.ms-powerpoint");
        }
        else if(fileName.indexOf("pptx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        }
		
		try {
			 InputStream inputStream= new FileInputStream(fullPath);
			  LOGGER.debug("fullPath " + fullPath);
			  OutputStream out = response.getPortletOutputStream();
			  byte buf[]=new byte[1024];
			  int len=0;
			  while((len=inputStream.read(buf))>0){
			  out.write(buf,0,len);
			  }
			  out.close();
			  inputStream.close();
			  LOGGER.debug("File is created...................................");
			
		} catch (IOException e) {
			LOGGER.debug("IOException occurred ::" + e);
		}
		
	}
	/**
	 * @param inputStr 
	 * @return String 
	 */
	private String returnFileName(String inputStr){
		if(inputStr.indexOf("\\")!=-1){
    		int i=inputStr.lastIndexOf("\\");
    		String str_Return=inputStr.substring(i+1,inputStr.length());
    		return str_Return;
    		
	}   	
	else{
		return inputStr;
		
	}
	}
	/**
	 * @param fName 
	 * @return String 
	 * @throws LGSBusinessException 
	 */
	private String setTimestampInAttachment(String fName) throws LGSBusinessException{
		
		int index = fName.lastIndexOf(".");
		
		String fExtension = fName.substring(index);
		LOGGER.debug("fExtension ----> "+fExtension);
		
		fName = fName.substring(0, index);
		LOGGER.debug("fName----> "+fName);
		
		String fNameFinal = fName + "_" + System.currentTimeMillis()+ fExtension;
		LOGGER.debug("fNameFinal----> "+fNameFinal);
		
		return fNameFinal;
		
	
}
	 /** 
	 * @param path 
	 * @param fileName 
	 * @return String 
	 */
	private String composeFilePath(String path, String fileName)
		{
			StringBuilder filePathBuilder = new StringBuilder();
			filePathBuilder.append(path);
			filePathBuilder.append(fileName);
			return filePathBuilder.toString();
		}
}