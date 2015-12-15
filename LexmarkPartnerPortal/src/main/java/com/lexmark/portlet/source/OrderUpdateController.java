/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: OrderUpdateController
 * Package     		: com.lexmark.portlet.source
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.portlet.source;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.RequestUpdateContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderUpdateContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Order;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.form.ClaimDetailForm;
import com.lexmark.form.ServiceRequestDetailForm;
import com.lexmark.form.source.ErrorContainer;
import com.lexmark.form.source.OrderDetailForm;
import com.lexmark.form.source.ProcessedPartGridError;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.PartnerClaimCreateIdResult;
import com.lexmark.result.RequestUpdateResult;
import com.lexmark.result.source.OrderAcceptResult;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.result.source.OrderUpdateResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerOrderService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerRequestsServiceImpl;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.MailUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.RequestService;
import com.lexmark.webservice.api.source.OrderService;
import com.lexmark.form.source.validator.ServiceOrderValidator;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.util.PerformanceUtil;

@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={"orderDetailForm","showPendingShipmentPartsFlag","showProcessedPartsFlag"})

public class OrderUpdateController {
	
	@Autowired
	private PartnerOrderService partnerOrderService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ServiceOrderValidator serviceOrderValidator;
	
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private AttachmentService attachmentService;

	private static LEXLogger logger = LEXLogger.getLEXLogger(OrderUpdateController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME = "OrderUpdateController.java";

	private static final String ORDERDETAILRESULT_NULL = "Failed to open Order Detail page: <br/> orderDetailResult is null";
	private static final String ORDER_NULL = "Failed to open Order Detail page: <br/> Order is null";
	private static final String SR_NULL = "Failed to open Order Detail page: <br/> ServiceRequest is null";
	
	

	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");
	@InitBinder(value = { "orderDetailForm" })
	protected void initBinder(WebDataBinder binder, Locale locale) {
		String METHOD_NAME = "initBinder";
		logger.enter(CLASS_NAME, METHOD_NAME);
		logger.debug("target is " + binder.getTarget().toString());
		/* Bind the form with validator */
		
		//This is done for date fields
		
		
		
		logger.debug("Language is " + locale.getLanguage());
		logger.debug("country is " + locale.getCountry());
		
		logger.info("format is " + DateUtil.getDateFormatByLang(locale.getLanguage()));
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if (binder.getTarget() instanceof OrderDetailForm) {
			logger.debug("Setting Address validator");
			binder.setValidator(serviceOrderValidator);
		}
		logger.exit(CLASS_NAME, METHOD_NAME);

	}
	
	@ActionMapping(params= "action=updateOrderUpdateView")
	public void submitOrderUpdate(ActionRequest request,ActionResponse response,Model model,@ModelAttribute("orderDetailForm") OrderDetailForm orderDetailForm,BindingResult bindingResult,SessionStatus sessionStatus) throws Exception
	{
		String METHOD_NAME = "submitOrderUpdate";
		logger.enter(CLASS_NAME, METHOD_NAME);
		logger.debug("submitOrderUpdate.orderDetailForm.getOrderDetail()--->"+orderDetailForm.getOrderDetail());
		StringBuffer vendorIds = new StringBuffer();
		  List<ServiceRequestOrderLineItem> pendingShipments = orderDetailForm.getOrderDetail().getPendingShipments();
		  List<ServiceRequestOrderLineItem> processedParts = orderDetailForm.getOrderDetail().getProcessedParts();
		  
		  if(pendingShipments!= null){
			  for(ServiceRequestOrderLineItem pendingLineItem:pendingShipments){
				  
				  if(vendorIds.indexOf(pendingLineItem.getVendorId()) == -1){
					  vendorIds.append(pendingLineItem.getVendorId() + ",");
				  }
			  }
		  }
		  
		  if(processedParts!= null){
			  for(ServiceRequestOrderLineItem processedLineItem:processedParts){
				  if(vendorIds.indexOf(processedLineItem.getVendorId()) == -1){
					  vendorIds.append(processedLineItem.getVendorId() + ",");
				  }
			  }
		  }
		  
		serviceOrderValidator.validate(orderDetailForm,bindingResult);
		List<ErrorContainer> errorList=serviceOrderValidator.getErrorList();
		List<ProcessedPartGridError> processGridError=serviceOrderValidator.processedGridError();
		if(bindingResult.hasErrors()||errorList.size()>0||processGridError.size()>0)
		{
			Map<String, String> carrierDropDown = new HashMap<String, String>() ;
			try {
				carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
						SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("In Exception"+e.getMessage());;
			}
			
			request.setAttribute(LexmarkPPConstants.ERRORLIST, errorList);
			logger.debug("error list:"+errorList.toString());
			
			model.addAttribute(LexmarkPPConstants.ORDERDETAILFORM,orderDetailForm);
			if(request.getParameter(LexmarkPPConstants.ACCEPTORDER)!=null && request.getParameter(LexmarkPPConstants.ACCEPTORDER).equalsIgnoreCase(LexmarkPPConstants.ACCEPT))
			{
				response.setRenderParameter(LexmarkPPConstants.ACCEPTFLAG,LexmarkPPConstants.ACCEPT);
			}
			
			request.setAttribute(LexmarkPPConstants.CARRIERDROPDOWN,carrierDropDown);
			response.setRenderParameter(LexmarkPPConstants.VENDORACC_ID,vendorIds.toString());
			response.setRenderParameter(LexmarkPPConstants.REQUESTNO,orderDetailForm.getOrderDetail().getRequestNumber());
			response.setRenderParameter(LexmarkPPConstants.ORDERNO, orderDetailForm.getOrderDetail().getOrderNumber());
			response.setRenderParameter(LexmarkPPConstants.ACTION, LexmarkPPConstants.SHOWUPDATEORDERREQUESTPAGE);
			
		}
		else{
			logger.debug("submitOrderUpdate.orderDetailForm.getOrderDetail().getVendorId--->"+orderDetailForm.getOrderDetail().getVendorId());
			OrderUpdateResult result = null;
			Map<String, String> carrierDropDown = new HashMap<String, String>() ;
			if(request.getParameter(LexmarkPPConstants.ACCEPTORDER)!=null && request.getParameter(LexmarkPPConstants.ACCEPTORDER).equalsIgnoreCase(LexmarkPPConstants.ACCEPT))
			{
				logger.debug("Inside Accept Order Section in Update page");
				String orderId = request.getParameter(LexmarkPPConstants.ORDERID);
				String vendorId = request.getParameter(LexmarkPPConstants.VENDORACC_ID);
				logger.debug("submitOrderUpdate.vendorId-------->"+vendorId);
				OrderAcceptResult acceptResult = new OrderAcceptResult();
				OrderAcceptContract contract = ContractFactory.createOrderAcceptContract(orderId,vendorIds.toString());
				try {
					logger.info("start printing lex logger");
					ObjectDebugUtil.printObjectContent(contract, logger);
					logger.info("end printing lex logger");
					long timeBeforeCall=System.currentTimeMillis();
					acceptResult = partnerOrderService.acceptServiceOrderRequest(contract);
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ACCEPTSERVICEORDERREQUEST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
				} catch (Exception e) {
					acceptResult.setResult(Boolean.FALSE);	
					model.addAttribute(LexmarkPPConstants.ACCEPTMSG,LexmarkPPConstants.FAILURE);
					
					
					try {
						carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
								SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
					response.setRenderParameter(LexmarkPPConstants.ACCEPTFLAG,LexmarkPPConstants.ACCEPT);
					
					model.addAttribute(LexmarkPPConstants.CARRIERDROPDOWN,carrierDropDown);
					response.setRenderParameter(LexmarkPPConstants.VENDORACC_ID,vendorIds.toString());
					response.setRenderParameter(LexmarkPPConstants.REQUESTNO,orderDetailForm.getOrderDetail().getRequestNumber());
					response.setRenderParameter(LexmarkPPConstants.ORDERNO, orderDetailForm.getOrderDetail().getOrderNumber());
					sessionStatus.setComplete();
					clearOrderDetailForm(orderDetailForm);
					response.setRenderParameter(LexmarkPPConstants.ACTION, LexmarkPPConstants.SHOWUPDATEORDERREQUESTPAGE);
				}
			}
			
			try{
				logger.debug("before creating update contract");
				
				OrderUpdateContract contract = ContractFactory.createOrderUpdateContract(request, orderDetailForm);
				
				logger.debug("after creating update contract");
				logger.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(contract, logger);
				logger.info("end printing lex logger");
				long timeBeforeCall=System.currentTimeMillis();
				result = orderService.updateOrder(contract);
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_UPDATEORDER, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			}catch(Exception e){
				
				model.addAttribute(LexmarkPPConstants.UPDATEMSG,LexmarkPPConstants.FAILURE);
				
				
				response.setRenderParameter(LexmarkPPConstants.REQUESTNO,orderDetailForm.getOrderDetail().getRequestNumber());
				response.setRenderParameter(LexmarkPPConstants.ORDERNO, orderDetailForm.getOrderDetail().getOrderNumber());
				response.setRenderParameter(LexmarkPPConstants.VENDORACC_ID,vendorIds.toString());
				
				sessionStatus.setComplete();
				clearOrderDetailForm(orderDetailForm);
				response.setRenderParameter(LexmarkPPConstants.ACTION, LexmarkPPConstants.RETRIEVEORDERREQUEST);
			}
			
			if(result.isSuccess()){
				
				model.addAttribute(LexmarkPPConstants.UPDATEMSG,LexmarkPPConstants.SUCCESS);
				model.addAttribute(LexmarkPPConstants.ERRORMSG,LexmarkPPConstants.ERRORMSG);
				
				logger.debug("[END] retrieveOrderDetail");
				try {
					carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
							SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				model.addAttribute(LexmarkPPConstants.CARRIERDROPDOWN,carrierDropDown);
				response.setRenderParameter(LexmarkPPConstants.REQUESTNO,orderDetailForm.getOrderDetail().getRequestNumber());
				response.setRenderParameter(LexmarkPPConstants.ORDERNO, orderDetailForm.getOrderDetail().getOrderNumber());
				response.setRenderParameter(LexmarkPPConstants.VENDORACC_ID,vendorIds.toString());
				sessionStatus.setComplete();
				clearOrderDetailForm(orderDetailForm);
				response.setRenderParameter(LexmarkPPConstants.ACTION, LexmarkPPConstants.RETRIEVEORDERREQUEST);
				
			}
			else
			{
				model.addAttribute(LexmarkPPConstants.UPDATEMSG,LexmarkPPConstants.FAILURE);
				
				
				
				try {
					carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
							SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				model.addAttribute(LexmarkPPConstants.CARRIERDROPDOWN,carrierDropDown);
				response.setRenderParameter(LexmarkPPConstants.REQUESTNO,orderDetailForm.getOrderDetail().getRequestNumber());
				response.setRenderParameter(LexmarkPPConstants.ORDERNO, orderDetailForm.getOrderDetail().getOrderNumber());
				response.setRenderParameter(LexmarkPPConstants.VENDORACC_ID,vendorIds.toString());
				sessionStatus.setComplete();
				clearOrderDetailForm(orderDetailForm);
				response.setRenderParameter(LexmarkPPConstants.ACTION, LexmarkPPConstants.RETRIEVEORDERREQUEST);
			}
		}
		String timeZoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		logger.debug("timeZoneOffset---------> " + timeZoneOffset);
		if(timeZoneOffset!=null){
		logger.debug("timezone offset is not null");	
		response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,timeZoneOffset);
		}
		logger.exit(CLASS_NAME, METHOD_NAME);
	}
	
	public void clearOrderDetailForm(OrderDetailForm orderDetailForm)
	{
		String METHOD_NAME = "clearOrderDetailForm";
		logger.enter(CLASS_NAME, METHOD_NAME);
		orderDetailForm.setOrderDetail(null);
		orderDetailForm.setActualShippedDateList(null);
		orderDetailForm.setBackOrderedQtyList(null);
		orderDetailForm.setCarrierList(null);
		orderDetailForm.setETAList(null);
		orderDetailForm.setLineIdList(null);
		orderDetailForm.setOrderNumber(null);
		orderDetailForm.setPartNumberList(null);
		orderDetailForm.setPendingOrderPartListXML(null);
		orderDetailForm.setDeliveryDateList(null);
		orderDetailForm.setPendingShipmentPartListXML(null);
		orderDetailForm.setProcessedLineIdList(null);		
		orderDetailForm.setProcessedLineIdList(null);
		orderDetailForm.setProcessedPartListXML(null);
		orderDetailForm.setProcessedTrackingList(null);
		orderDetailForm.setQuantityList(null);
		orderDetailForm.setSerialNumberList(null);
		orderDetailForm.setServiceProviderOrderRefNo(null);
		orderDetailForm.setShipQuantityList(null);
		orderDetailForm.setStatusList(null);
		orderDetailForm.setTrackingList(null);
		logger.exit(CLASS_NAME, METHOD_NAME);
	}
	
	
	@RequestMapping(params = "action=showUpdateOrderRequestPage")
	public String showUpdateOrderRequestPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		String METHOD_NAME = "showUpdateOrderRequestPage";
		logger.enter(CLASS_NAME, METHOD_NAME);
		OrderDetailResult OrderDetailResult = null;
		logger.debug("inside show update request page");
		
		//Returning to Order Detail page due to errorList
		if(model.containsAttribute(LexmarkPPConstants.ORDERDETAILFORM) && request.getAttribute(LexmarkPPConstants.ERRORLIST)!=null)
		{
			Map<String, String> carrierDropDown = new HashMap<String, String>() ;
			logger.debug("Stuck inside error");
			carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
			model.addAttribute(LexmarkPPConstants.CARRIERDROPDOWN,carrierDropDown);
			return LexmarkPPConstants.ORDERUPDATEVIEW;
		}
		
		try{
			OrderDetailContract contract = ContractFactory.createOrderDetailContract(request);
			
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, logger);
			logger.info("end printing lex logger");
			long timeBeforeCall=System.currentTimeMillis();
			OrderDetailResult = partnerOrderService.retrieveOrderDetail(contract);	
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_ORDERDETAILS, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		}
		catch(Exception e)
		{
			logger.error("Exception occured while retrieving service order details ");
			model.addAttribute(LexmarkPPConstants.EXCEPTIONOCCURED, LexmarkPPConstants.TRUE_ATTR);
			return LexmarkPPConstants.ORDERDETAILVIEW;
		}
			
		if(OrderDetailResult==null) {
			throw new IllegalArgumentException(ORDERDETAILRESULT_NULL);
		}
		if(OrderDetailResult.getOrder() == null){
			throw new IllegalArgumentException(ORDER_NULL);
		}
		if(OrderDetailResult.getOrder().getServiceRequestNumber() == null){
			throw new IllegalArgumentException(SR_NULL);
		}
		Order orderDetail = OrderDetailResult.getOrder();
		logger.debug("vendorAccountId----------------->"+request.getParameter(LexmarkPPConstants.VENDORACC_ID));
		orderDetail.setVendorId(request.getParameter(LexmarkPPConstants.VENDORACC_ID));
		Locale locale = request.getLocale();


		Map<String, String> statusDropDown = new HashMap<String, String>() ;
		statusDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
		
		Map<String, String> carrierDropDown = new HashMap<String, String>() ;
		carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
		
		String contextPath = request.getContextPath();		
		
		if(orderDetail.getPendingShipments() != null)
		{
			List<ServiceRequestOrderLineItem> partList = orderDetail.getPendingShipments();
			if (partList == null || partList.size() == 0) {
				model.addAttribute(LexmarkPPConstants.SHOW_PENDINGHIPMENTPARTSFLAG, false);
			} else {
				int pendingQty = 0;
				int backQty = 0;
				
				for(int i=0;i<partList.size();i++)
				{
					pendingQty = pendingQty + partList.get(i).getPendingQuantity();
					backQty = backQty + partList.get(i).getBackOrderQuantity();
				}
				
				if(pendingQty == 0 && backQty ==0)
				{
					model.addAttribute(LexmarkPPConstants.SHOW_PENDINGHIPMENTPARTSFLAG, false);
				}
				else{			
					model.addAttribute(LexmarkPPConstants.SHOW_PENDINGHIPMENTPARTSFLAG, true);
				}
			}
		}
		
		if(orderDetail.getProcessedParts() != null)
		{
			List<ServiceRequestOrderLineItem> partList = orderDetail.getProcessedParts();
			if (partList == null || partList.size() == 0) {
				model.addAttribute(LexmarkPPConstants.SHOW_PROCESSEDPARTSFLAG, false);
			} else {
				model.addAttribute(LexmarkPPConstants.SHOW_PROCESSEDPARTSFLAG, true);
			}	
		}
		
		logger.debug("Before createOrderDetailForm [orderDetail]-->"+orderDetail);
		logger.debug("retrieveOrderRequest.vendorAccountId----------------------->"+request.getParameter("vendorAccountId"));
		orderDetail.setVendorId(request.getParameter(LexmarkPPConstants.VENDORACC_ID));
		OrderDetailForm orderDetailForm = createOrderDetailForm(orderDetail, locale, statusDropDown, carrierDropDown,request,contextPath, request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
			    
		model.addAttribute(LexmarkPPConstants.ORDERDETAILFORM, orderDetailForm);		
		
		if(request.getParameter(LexmarkPPConstants.ACCEPTFLAG)!=null && request.getParameter(LexmarkPPConstants.ACCEPTFLAG).equalsIgnoreCase(LexmarkPPConstants.ACCEPT))
		{
			model.addAttribute(LexmarkPPConstants.ACCEPTORDER, LexmarkPPConstants.ACCEPT);
		}else{
			model.addAttribute(LexmarkPPConstants.ACCEPTORDER, LexmarkPPConstants.UPDATE);
		}
		
		PortletURL url = response.createRenderURL();		
		model.addAttribute(LexmarkPPConstants.RETURN_URL, url.toString());
		model.addAttribute(LexmarkPPConstants.CARRIERDROPDOWN,carrierDropDown);
		String timeZoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		logger.debug("timeZoneOffset---------> " + timeZoneOffset);
		if(timeZoneOffset!=null){
		logger.debug("timezone offset is not null");	
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,timeZoneOffset);
		}
		logger.exit(CLASS_NAME, METHOD_NAME);
		return LexmarkPPConstants.ORDERUPDATEVIEW;
	}

	private OrderDetailForm createOrderDetailForm(Order orderDetail, Locale locale, Map<String, String> statusDropDown, Map<String, String> carrierDropDown, PortletRequest request, String contextPath, String timeZoneOffset) throws Exception {
		String METHOD_NAME = "createOrderDetailForm";
		logger.enter(CLASS_NAME, METHOD_NAME);
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		OrderDetailForm orderDetailForm = new OrderDetailForm();		
		orderDetailForm.setOrderNumber(orderDetail.getOrderNumber());
		orderDetailForm.setPendingShipmentPartListXML(xmlOutputGenerator.convertPendingShipmentPartListForEditToXML(
		orderDetail.getPendingShipments(),carrierDropDown,contextPath, timeZoneOffset));
		List<List<ServiceRequestOrderLineItem>> processedPartList = listProcessedShipments(orderDetail.getProcessedParts()); 		
		orderDetailForm.setProcessedPartListXML(xmlOutputGenerator
				.convertProcessedPartListForEditToXML(processedPartList, statusDropDown, contextPath , PortalSessionUtil.getUserRoles(request.getPortletSession()), timeZoneOffset));
		orderDetailForm.setEmailNotificationListXML(xmlOutputGenerator.convertEmailNotificationListToXML(orderDetail.getEmailActivities()));
		orderDetailForm.setOrderDetail(orderDetail);
		logger.exit(CLASS_NAME, METHOD_NAME);
		return orderDetailForm;
	}
	
	
	private List<List<ServiceRequestOrderLineItem>> listProcessedShipments(
			List<ServiceRequestOrderLineItem> shipmentOrderLines) {
		String METHOD_NAME = "listProcessedShipments";
		logger.enter(CLASS_NAME, METHOD_NAME);
		List<List<ServiceRequestOrderLineItem>> returnShipments = new ArrayList<List<ServiceRequestOrderLineItem>>();
		if (shipmentOrderLines != null) {
			List<ServiceRequestOrderLineItem> srOrderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
			returnShipments.add(srOrderLineItems);
			logger.debug("Processed List Size ----->" + shipmentOrderLines.size());
			for (int i = 0; i < shipmentOrderLines.size(); i++) {
				ServiceRequestOrderLineItem partLineItem = shipmentOrderLines.get(i);
				ObjectDebugUtil.printObjectContent(shipmentOrderLines.get(i), logger);
				boolean isContaint = false;
				for (int j = 0; j < returnShipments.size(); j++) {
					for (int k = 0; k < returnShipments.get(j).size(); k++) {
						if(returnShipments.get(j).get(k).getTrackingNumber()!=null)
						{
							if (returnShipments.get(j).get(k).getTrackingNumber().equals(partLineItem.getTrackingNumber()))
								{isContaint = true;}
						}
						
					}
					if (returnShipments.size() == 0
							|| j == (returnShipments.size() - 1)) {
						if (isContaint == false) {
							List<ServiceRequestOrderLineItem> srOrderLineItem = new ArrayList<ServiceRequestOrderLineItem>();
							srOrderLineItem.add(partLineItem);
							returnShipments.add(srOrderLineItem);
							break;
						}
					}
					if (isContaint) {
						returnShipments.get(j).add(partLineItem);
						break;
					}
				}
			}
			returnShipments.remove(0);
		}
		logger.exit(CLASS_NAME, METHOD_NAME);
		return returnShipments;
	}

}
