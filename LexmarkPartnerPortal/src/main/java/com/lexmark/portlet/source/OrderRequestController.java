/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: OrderRequestController
 * Package     		: com.lexmark.portlet.source
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * @author 			Date				@version  		@comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.portlet.source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.MassUploadTemplateOrderLineItem;
import com.lexmark.domain.Order;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.PartnerTypeEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.exporter.DataExporter;
import com.lexmark.exporter.DataExporterFactory;
import com.lexmark.exporter.PdfDataExporter;
import com.lexmark.form.source.OrderDetailForm;
import com.lexmark.form.source.OrderRequestListForm;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.portlet.BaseController;
import com.lexmark.portlet.GridSettingController;
import com.lexmark.result.MassUploadTemplateResult;
import com.lexmark.result.source.OrderAcceptResult;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerOrderService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Suman from MPS Offshore Team
 */

@Controller
@RequestMapping("VIEW")
public class OrderRequestController extends BaseController{

	private static final ArrayList<Order> EMPTY_LIST = new ArrayList<Order>();
	@Autowired
	private PartnerOrderService partnerOrderService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private GridSettingController gridSettingController;
	private static Logger logger = LogManager.getLogger(OrderRequestController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME = "OrderRequestController.java";
	private static final String EXCEPTION_ACCEPT_SERVICE_ORDER = "exception.serviceOrder.acceptServiceOrder";

	private static final String SUCCESS_ACCEPT_SERVICE_ORDER = "message.serviceOrder.acceptServiceOrder";
	private static final String REQUESTLISTFAILURE = "Failed to open request list page: <br/> userRoles is null";

	
	/**
	 * Displays the initial Service Order Request List Grid.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return String Grid page
	 * @throws LGSCheckedException
	 *             , LGSRuntimeException
	 */
	@RequestMapping
	public String showOrderRequestList(RenderRequest request, RenderResponse response, Model model) throws LGSCheckedException,LGSRuntimeException
	{
		String METHOD_NAME = "showOrderRequestList";

		
		/*
		 * Added for Massupload
		 * Phase 2
		 * */
		HttpServletRequest httpServletReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		if(httpServletReq.getParameter(LexmarkPPConstants.VENDORACC_ID)!=null && !StringUtils.isEmpty(httpServletReq.getParameter(LexmarkPPConstants.VENDORACC_ID))){
			request.setAttribute("fromMassUpload", LexmarkPPConstants.TRUE_ATTR);
			return retrieveOrderRequest(model,request,response);
		}
		
		
		/*Ends Phase 2 Changes*/
		
		
		
		
		
		OrderRequestListForm requestListForm = new OrderRequestListForm();
		final PortletSession session = request.getPortletSession();

		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		List<String> userRoles = PortalSessionUtil.getUserRoles(session);

		if (userRoles == null) {
			throw new IllegalArgumentException(REQUESTLISTFAILURE);
		}			
		

		Locale locale = request.getLocale();
		String partnerType = getPartnerType(isIndirectPartner, isDirectPartner);
		

		Map<String, String> requestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		requestListForm.setOrderRequestStatusMap(requestStatusMap);

		Map<String, String> serviceTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue(), null, serviceRequestLocaleService,
				locale);
		requestListForm.setServiceTypeMap(serviceTypeMap);

		Map<String, String> requestStatusDetailMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		Map<String, String> orderRequestStatus= ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_ORDER_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		requestListForm.setOrderRequestStatusMap(orderRequestStatus);
		requestListForm.setOrderRequestStatusDetailMap(requestStatusDetailMap);
		

		gridSettingController.retrieveGridSetting(LexmarkPPConstants.GRIDORDER_REQUESTLIST, request, model);
		
		model.addAttribute(LexmarkPPConstants.REQUESTLISTFORM, requestListForm);
		
		HttpServletRequest httpRequest=PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		if(httpRequest.getParameter("back")!=null && "true".equalsIgnoreCase(httpRequest.getParameter("back"))){
			
			model.addAttribute("back", "true");
			request.removeAttribute("back");
			
		}
		
		
		

		return LexmarkPPConstants.ORDERREQUESTLISTVIEW;
	}    
	
	/**
	 * Retrieves the Service Order Request List.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return Grid XML String in the form of a JSP Page
	 * @throws LGSCheckedException
	 *             , LGSRuntimeException
	 */
	@ResourceMapping("retrieveOrderRequestList")
	public String getOrderRequestList(ResourceRequest request, ResourceResponse response, Model model,@RequestParam(value="isMassUpload" ,required=false) Boolean isMassUpload) throws LGSCheckedException,LGSRuntimeException
	{
		String METHOD_NAME = "getOrderRequestList";


		OrderListContract contract = ContractFactory.createOrderListContract(request);
		
		PortletSession session = request.getPortletSession();
		boolean showProcessedUpdateFlag = processedUpdateRole(session);
		OrderListResult orderListResult = new OrderListResult();		
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		logger.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.info("end printing lex loggger");
		//Changes MPS2.1
		session.setAttribute(LexmarkPPConstants.SERVICEORDERGRIDID, contract.clone());
		//Ends Changes MPS 2.1
		try {
			long timeBeforeCall=System.currentTimeMillis();			
			orderListResult = partnerOrderService.retrieveOrderList(contract);	
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_ORDERLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		} catch (Exception e) {
			logger.error("Exception while retrieving order list, The root cause is " + e.getMessage());
			ServiceStatusUtil.responseResult(response, LexmarkPPConstants.GENERAL_ERRORTITLE,
					LexmarkPPConstants.RETRIEVELISTEXCEPTION, request.getLocale());
			return null;
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		

		List<Order> orderList = orderListResult.getOrderList();
		logger.debug("orderList size------>"+orderList.size());
		for(int i=0;i<orderList.size();i++){
			Order order = orderList.get(i);

			StringBuffer vendorIds = new StringBuffer();
			  List<ServiceRequestOrderLineItem> pendingShipments = order.getPendingShipments();
			  List<ServiceRequestOrderLineItem> processedParts = order.getProcessedParts();
			  String fulfillmentStatus = "";
			  if( pendingShipments!= null){
				  if(pendingShipments.size()>0)
				  {
					  if(pendingShipments.get(0)!=null)				  
					  {
						  if(pendingShipments.get(0).getPortalFulfillmentStatus()!=null && !pendingShipments.get(0).getPortalFulfillmentStatus().equalsIgnoreCase(""))				  
						  {
							  fulfillmentStatus = pendingShipments.get(0).getPortalFulfillmentStatus();
						  }
					  }
				  }
				  for(ServiceRequestOrderLineItem pendingLineItem:pendingShipments){
					  logger.debug("fulfillment status pending------>"+pendingLineItem.getPortalFulfillmentStatus());
					  
					  if(vendorIds.indexOf(pendingLineItem.getVendorId()) == -1){
						  vendorIds.append(pendingLineItem.getVendorId() + ",");
					  }
				  }
			  }
			  
			  if(processedParts != null){
				  for(ServiceRequestOrderLineItem processedLineItem:processedParts){
					  if(vendorIds.indexOf(processedLineItem.getVendorId()) == -1){
						  vendorIds.append(processedLineItem.getVendorId() + ",");
					  }
				  }
			  }
	
			  orderList.get(i).setVendorId(vendorIds.toString());
			  orderList.get(i).setFulfillmentStatus(fulfillmentStatus);
			  
			  logger.debug("Vendor ID list for order ["+orderList.get(i).getOrderNumber()+"]--->"+orderList.get(i).getVendorId());
		}
		
	
		logger.debug("Total Count Received from Service Call "+ orderListResult.getTotalCount());
		model.addAttribute(LexmarkPPConstants.DIRECTPARTNER, PortalSessionUtil.getDirectPartnerFlag(session));
		model.addAttribute(LexmarkPPConstants.STARTPOS, contract.getStartRecordNumber());
		model.addAttribute(LexmarkPPConstants.TOTALCOUNT, orderListResult.getTotalCount());
		model.addAttribute("isMassUpload", isMassUpload);
		
		model.addAttribute("showProcessedUpdateFlag", showProcessedUpdateFlag);	
		logger.debug("orderList.size()---->"+orderList.size());
		model.addAttribute(LexmarkPPConstants.ORDERLIST, orderList);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, 
				request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		response.setContentType(LexmarkPPConstants.XMLCONTENT);

		return LexmarkPPConstants.ORDERREQUESTLISTXML;
	}
	
	/**
	 * Retrieves and displays Service Order Request Detail.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return Grid XML String in the form of a JSP Page
	 * @throws Exception
	 */
	@RequestMapping(params = "action=retrieveOrderRequest")
	public String retrieveOrderRequest(Model model, RenderRequest request,RenderResponse response) 
	{
		String METHOD_NAME = "retrieveOrderRequest";

		logger.debug("OrderRequestController.retrieveOrderRequest Enter : VendorIds:"+request.getParameter("vendorAccountId"));
		OrderDetailResult OrderDetailResult = null;
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
		
		PortletSession session = request.getPortletSession();
		boolean showProcessedUpdateFlag = processedUpdateRole(session);
		Order orderDetail = OrderDetailResult.getOrder();
		Locale locale = request.getLocale();

		
		
		logger.debug("Before createOrderDetailForm [orderDetail]-->"+orderDetail);
		logger.debug("retrieveOrderRequest.vendorAccountId----------------------->"+request.getParameter("vendorAccountId"));
		orderDetail.setVendorId(request.getParameter(LexmarkPPConstants.VENDORACC_ID));
		OrderDetailForm orderDetailForm =null;
		try{
			orderDetailForm = createOrderDetailForm(orderDetail, locale, request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		}catch(Exception e){
			logger.error("Exception occured while retrieving service order details ");
			model.addAttribute(LexmarkPPConstants.EXCEPTIONOCCURED, LexmarkPPConstants.TRUE_ATTR);
			return LexmarkPPConstants.ORDERDETAILVIEW;
		}
		String fulfillmentStatus = "";
		
		if(orderDetail.getPendingShipments() != null)
		{
			List<ServiceRequestOrderLineItem> partList = orderDetail.getPendingShipments();			
			if (partList == null || partList.size() == 0) {
				logger.debug("pending shipment list size --->"+partList.size());
				model.addAttribute(LexmarkPPConstants.SHOW_PENDINGHIPMENTPARTSFLAG, false);
			} else {
				logger.debug("pending shipment list size --->"+partList.size());
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
				
				if(partList.size()>0)
				{
					if(partList.get(0)!=null)
				    {
						if(partList.get(0).getPortalFulfillmentStatus()!=null && !partList.get(0).getPortalFulfillmentStatus().equalsIgnoreCase(""))
					    {
						   fulfillmentStatus = partList.get(0).getPortalFulfillmentStatus();
					    }
				    }
				}
			    
			    logger.debug("fulfillmentStatus --->"+fulfillmentStatus);
			}
		}
	
		if(orderDetail.getProcessedParts() != null)
		{
			List<ServiceRequestOrderLineItem> partList = orderDetail.getProcessedParts();
			if (partList == null || partList.size() == 0) {
				logger.debug("processed parts list size --->"+partList.size());
				model.addAttribute(LexmarkPPConstants.SHOW_PROCESSEDPARTSFLAG, false);
			} else {
				logger.debug("processed parts list size --->"+partList.size());
				model.addAttribute(LexmarkPPConstants.SHOW_PROCESSEDPARTSFLAG, true);
				
				if(partList.size()>0)
				{
					if(partList.get(0)!=null)
				    {
						if(partList.get(0).getPortalFulfillmentStatus()!=null && !partList.get(0).getPortalFulfillmentStatus().equalsIgnoreCase(""))
					    {
						   fulfillmentStatus = partList.get(0).getPortalFulfillmentStatus();
					    }
				    }
				}
			    
			    logger.debug("fulfillmentStatus --->"+fulfillmentStatus);
			}	
		}			    
		
		model.addAttribute(LexmarkPPConstants.ORDERDETAILFORM, orderDetailForm);
		model.addAttribute(LexmarkPPConstants.FULFILLMENTSTATUS, fulfillmentStatus);
		model.addAttribute(LexmarkPPConstants.SHOWPROCESSEDUPDATEFLAG, showProcessedUpdateFlag);	
		PortletURL url = response.createRenderURL();			
		model.addAttribute(LexmarkPPConstants.RETURN_URL, url.toString());
		String timeZoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		logger.debug("timeZoneOffset---------> " + timeZoneOffset);
		if(timeZoneOffset!=null){
		logger.debug("timezone offset is not null");	
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timeZoneOffset);
		}
		
		return LexmarkPPConstants.ORDERDETAILVIEW;
	}

	/**
	 * Creates the Order Detail Form.
	 * 
	 * @param orderDetail
	 * @param locale
	 * @param timeZoneOffset 
	 * @return OrderDetailForm object
	 * @throws Exception
	 */
	private OrderDetailForm createOrderDetailForm(Order orderDetail, Locale locale, String timeZoneOffset) throws Exception {
		String METHOD_NAME = "createOrderDetailForm";

		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		OrderDetailForm orderDetailForm = new OrderDetailForm();
		orderDetailForm.setPendingShipmentPartListXML(xmlOutputGenerator.convertPendingShipmentPartListToXML(
		orderDetail.getPendingShipments(),orderDetail, timeZoneOffset));
		List<List<ServiceRequestOrderLineItem>> processedPartList = listProcessedShipments(orderDetail.getProcessedParts());
		orderDetailForm.setProcessedPartListXML(xmlOutputGenerator.convertProcessedPartListToXML(processedPartList, timeZoneOffset));
		orderDetailForm.setEmailNotificationListXML(xmlOutputGenerator.convertEmailNotificationListToXML(orderDetail.getEmailActivities()));
		orderDetailForm.setOrderDetail(orderDetail);

		return orderDetailForm;
	}
	
	private List<List<ServiceRequestOrderLineItem>> listProcessedShipments(
			List<ServiceRequestOrderLineItem> shipmentOrderLines) {
		String METHOD_NAME = "listProcessedShipments";

		List<List<ServiceRequestOrderLineItem>> returnShipments = new ArrayList<List<ServiceRequestOrderLineItem>>();
		if (shipmentOrderLines != null) {
			List<ServiceRequestOrderLineItem> srOrderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
			returnShipments.add(srOrderLineItems);
			logger.debug("Processed List Size --->" + shipmentOrderLines.size());
			for (int i = 0; i < shipmentOrderLines.size(); i++) {
				ServiceRequestOrderLineItem partLineItem = shipmentOrderLines.get(i);
				ObjectDebugUtil.printObjectContent(shipmentOrderLines.get(i), logger);
				boolean isContaint = false;
				for (int j = 0; j < returnShipments.size(); j++) {
					for (int k = 0; k < returnShipments.get(j).size(); k++) {
						if(returnShipments.get(j).get(k).getTrackingNumber()!=null)
						{
							if (returnShipments.get(j).get(k).getTrackingNumber().equals(partLineItem.getTrackingNumber()))
							{
								isContaint = true;
							}
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

		return returnShipments;
	}
	
	

	
	@RequestMapping(params = "action=printOrderRequestDetail")
	public String printOrderRequestDetailPage() {
		String METHOD_NAME = "printOrderRequestDetailPage";

		return LexmarkPPConstants.ORDERDETAILPRINTVIEW;
	}
	
	@RequestMapping(params = "action=printOrderListDetail")
	public String printOrderListDetailPage() {
		String METHOD_NAME = "printOrderListDetailPage";


		return LexmarkPPConstants.ORDERLISTPRINTVIEW;
	}
	
	/**
	 * Download Service Order Detail in form of PDF.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return Service Order Detail in the form of PDF
	 * @throws Exception
	 */
	@ResourceMapping("downOrderDetailPdfURL")
	public void downOrderRequestDetailPdfURL(Model model, ResourceRequest request, ResourceResponse response) throws Exception
	{
		String METHOD_NAME = "downOrderRequestDetailPdfURL";

		OrderDetailContract contract = ContractFactory.createOrderDetailContract(request);
		logger.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.info("end printing lex logger");
		OrderDetailResult orderDetailResult = null;
		
		try
		{
			long timeBeforeCall=System.currentTimeMillis();
			orderDetailResult = partnerOrderService.retrieveOrderDetail(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_ORDERDETAILS, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			orderDetailResult = partnerOrderService.retrieveOrderDetail(contract);			
			Order orderDetail = orderDetailResult.getOrder();
			Locale locale = request.getLocale();
			float timezoneOffset = 0;
			String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
			if (!StringUtils.isEmpty(timezoneOffsetStr)) {
				timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
			}
			PdfDataExporter pdfDataExporter = new PdfDataExporter();
			pdfDataExporter.setLocale(locale);
			pdfDataExporter.setTimezoneOffset(timezoneOffset);			
			pdfDataExporter.generateOrderDetailPdf(orderDetail,response);
			
			
		}
		catch(Exception e)
		{
			logger.error("Exception occured while retrieving service order details ");
			model.addAttribute(LexmarkPPConstants.DOWNLOADEXCEPTIONOCCURED, LexmarkPPConstants.TRUE_ATTR);
		}


	}
	
	
	public boolean containsIgnoreCase(List <String> l, String s){
		String METHOD_NAME = "containsIgnoreCase";


		 Iterator <String> it = l.iterator();
		 while(it.hasNext()){
		  if(it.next().equalsIgnoreCase(s))
		  {return true;}
		 }

		 return false;
	}
	
	private String getPartnerType(boolean isIndirectPartner, boolean isDirectPartner) {
		String METHOD_NAME = "getPartnerType";


		String partnerType = null;
		if (isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.BOTH.getValue();
		} else if (isDirectPartner && !isIndirectPartner) {
			partnerType = PartnerTypeEnum.DIRECT.getValue();
		} else if (!isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.INDIRECT.getValue();
		}

		return partnerType;
	}
	
	
	
	
	/**
	 * Accept Service Order Request.
	 * 
	 * @param activityId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResourceMapping("acceptOrderRequest")
	public void acceptOrderRequest(@RequestParam("orderId") String orderId,@RequestParam("vendorId") String vendorId, ResourceRequest request,
			ResourceResponse response) throws Exception {
		String METHOD_NAME = "acceptOrderRequest";

		logger.debug("OrderRequestController.acceptOrderRequest.vendorId--->"+vendorId);
		OrderAcceptResult result = new OrderAcceptResult();
		OrderAcceptContract contract = ContractFactory.createOrderAcceptContract(orderId,vendorId);
		logger.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.info("end printing lex logger");
		try {
			long timeBeforeCall=System.currentTimeMillis();
			result = partnerOrderService.acceptServiceOrderRequest(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ACCEPTSERVICEORDERREQUEST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
		} catch (Exception e) {
			result.setResult(Boolean.FALSE);			
		}
		
		String errorCode = result.getResult() ? SUCCESS_ACCEPT_SERVICE_ORDER
				: EXCEPTION_ACCEPT_SERVICE_ORDER;
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(), "\"" + orderId + "\"");


	}
	
	/**
	 * retrieve Services Order Requests and download it csv and pdf format.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@ResourceMapping("downloadOrderRequestsURL")
	public void downloadOrderRequestsURL(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		String METHOD_NAME = "downloadOrderRequestsURL";

		logger.debug("[START]download order request");
		String downloadType = request.getParameter(LexmarkPPConstants.DOWNLOADTYPE);
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		
		PortletSession session = request.getPortletSession();
		//Changes MPS 2.1
		OrderListContract contract = (OrderListContract) session.getAttribute(LexmarkPPConstants.SERVICEORDERGRIDID);
		session.setAttribute(LexmarkPPConstants.SERVICEORDERGRIDID, contract.clone());
		//Ends CHanges MPS 2.1
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			OrderListResult orderListResult = partnerOrderService.retrieveOrderList(contract);
			List<Order> requestList = orderListResult.getOrderList();
			
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			dataExporter.exportOrder(response, LexmarkPPConstants.ORDER, LexmarkPPConstants.PATTERNS_ORDER_EXPORT, requestList);
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("[END] download order request");


	}
	
	/**
	 * Localize Date.
	 * 
	 * @param activityList
	 * @param timezoneOffset
	 */
	private void localizeDate(List<Activity> activityList, float timezoneOffset){
		String METHOD_NAME = "localizeDate";

		for (Activity activity : activityList){
			TimezoneUtil.adjustDate(activity.getActivityDate(),(0-timezoneOffset));
		}

	}

	
	/**
	 * Retrieve User Roles based on Session to check whether the user has necessary access for updating processed parts section.
	 * 
	 * @param session
	 */
	private boolean processedUpdateRole(PortletSession session){
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			return true;
		}else {
			return false;
		}
	}
	/**
	 * This method is used by Massupload to download the template
	 * @param request
	 * @param response
	 * */
	public List<MassUploadTemplateOrderLineItem> getMassUploadTemplate(PortletRequest request,PortletResponse response){
		/*
		 * 
		 * Get List Starts
		 * */
		String gridId=request.getParameter("gridId");
		PortletSession session = request.getPortletSession();
		OrderListContract contract = (OrderListContract)session.getAttribute(gridId);
		MassUploadTemplateContract templateContract=ContractFactory.getMassUploadTemplateContract(contract);
		
		
		
		
		logger.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(templateContract, logger);
		logger.info("end printing lex loggger");
		MassUploadTemplateResult templateResult=null;
			
		try {
			long timeBeforeCall=System.currentTimeMillis();		
			
			templateResult = partnerOrderService.retrieveMassUploadTemplateList(templateContract);	
			
			logger.debug("size of the list is "+ templateResult.getOrderLineItemsList().size());
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVEMASSUPLOADTEMPLATELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,templateContract);
			
		} catch (Exception e) {
			logger.error("Exception while retrieving order list, The root cause is " + e.getMessage());
			//Blank result to be returned.....
			templateResult=new MassUploadTemplateResult();
			templateResult.setOrderLineItemsList(new ArrayList<MassUploadTemplateOrderLineItem>());				
			
			
		} finally {
			
		}
		

		List<MassUploadTemplateOrderLineItem> templateList = templateResult.getOrderLineItemsList();
		
		return templateList;
	}

}
