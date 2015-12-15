package com.lexmark.webservice.impl.real;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.source.OrderUpdateContract;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.source.OrderUpdateResult;
import com.lexmark.util.DateUtil;
import com.lexmark.webservice.api.source.OrderService;
import com.lexmark.SiebelShared.createServiceRequest.client.DocumentMetaData3;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.DocumentMetaData;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusData;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.BackOrderedQuantity;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.OrderedQuantity;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput2;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrder_salesOrderWS_BinderStub;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWSLocator;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers;
import com.lexmark.SiebelShared.source.updateServiceOrders.client.ShippedQuantity;

public class OrderServiceImpl implements OrderService{
	
	private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
	
	private String address;
	private String senderId; 
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	 private static void putDataInList(List<String> list,String data)
	 {
		StringTokenizer st=new StringTokenizer(data, ",");
		while(st.hasMoreTokens())
			{
				list.add(st.nextToken());
			}
	 }

	@Override
	public OrderUpdateResult updateOrder(OrderUpdateContract contract) throws Exception {
		OrderUpdateResult result = new OrderUpdateResult();
		LOGGER.debug("Beginning Service call for Order Update Contract");
//		printValueToBeSentToWM(contract);
		Date dateNow = new Date ();
		SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MMddyyyy");
 
        StringBuilder nowMMDDYYYY = new StringBuilder( dateformatMMDDYYYY.format( dateNow ) );
        LOGGER.debug( "DEBUG: Today in MMDDYYYY: '" + nowMMDDYYYY + "'");
 
        SalesOrderWS locator = new SalesOrderWSLocator();		
        SalesOrderWS_PortType port = locator.getSalesOrder_salesOrderWS_Port(new URL(getAddress()));	
		
        org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
        
		final String synchOrAsynch = "asynch";
		final String debug="$null";
		
		String originalPartnerId = ""; 
		String originalPartnerName = "";
		String receiverName = "";
		String sourceSystem = "SERVICES WEB PORTAL";
				
		
		
		//Setting up DocumentMetaData
		//DocumentMetaData4
		DocumentMetaData documentMetaData = new DocumentMetaData();
		documentMetaData.setSenderId(getSenderId());
		documentMetaData.setSenderName(getSenderName());
		documentMetaData.setReceiverId(getReceiverId());	
		documentMetaData.setReceiverName(receiverName);
		documentMetaData.setOriginalPartnerId(originalPartnerId);
		documentMetaData.setOriginalPartnerName(originalPartnerName);
	
		
		
		LOGGER.debug("getSenderId:: "+ getSenderId() +" getReceiverId:: " + getReceiverId());
		
		
		////////////////////////////////////////////////////////////////////////
		////AMS change LEX:AIR00073949
		String serviceProviderRefNumber = null;		
		if(String.valueOf(contract.getOrder().getServiceProviderReferenceNumber()) != null || String.valueOf(contract.getOrder().getServiceProviderReferenceNumber().trim()) != "")
		   {
			   serviceProviderRefNumber = contract.getOrder().getServiceProviderReferenceNumber();
			   LOGGER.debug("********serviceProviderRefNumber "+serviceProviderRefNumber);
		   }
		//AMS change LEX:AIR00073949 -- Ends here	
		
	   LineDetails[] partLineList = null;
	   if(contract.getOrder().getPendingShipments() != null || contract.getOrder().getProcessedParts() != null){
		   int listSize = 0;
		   if(contract.getOrder().getPendingShipments() != null)
		   {
			   for(ServiceRequestOrderLineItem lineItem : contract.getOrder().getPendingShipments()) 
			   {
				   if((String.valueOf(lineItem.getShippedQuantity()) == null || String.valueOf(lineItem.getShippedQuantity()).equalsIgnoreCase("null") ||  lineItem.getShippedQuantity() == 0 || lineItem.getShippedQuantity()!=-999) && (String.valueOf(lineItem.getBackOrderQuantity()) == null || String.valueOf(lineItem.getBackOrderQuantity()).equalsIgnoreCase("null")))
				   {
					   continue;
				   }else if((lineItem.getShippedQuantity()==0 || lineItem.getShippedQuantity()==-999) && (lineItem.getCarrier()!=null || lineItem.getTrackingNumber()!=null || lineItem.getActualShipDate()!=null)){
					   continue;
				   }else{
					   listSize = listSize + 1;
				   }
			   }
		   }
		   
		   if(contract.getOrder().getProcessedParts() != null)
		   {
			   for(ServiceRequestOrderLineItem lineItem : contract.getOrder().getProcessedParts()) 
			   {
				   if(lineItem.getId() == null ||  lineItem.getStatus() == null || lineItem.getTrackingNumber() == null || lineItem.getActualDeliveryDate()== null)
				   {
					   continue;
				   }else{
					   listSize = listSize + 1;
				   }
			   }
		   }
		   
		   LOGGER.debug("listSize ------>"+listSize);
		   if(listSize > 0)
		   {
			   partLineList  = new LineDetails[listSize];		   
			   int i = 0;	
			   
			   /************** Pending Shipment Line Details ************/
			   if(contract.getOrder().getPendingShipments() != null)
			   {
				   for(ServiceRequestOrderLineItem lineItem : contract.getOrder().getPendingShipments()) 
				   {
					   ShippedQuantity shippedQuantity = null;					   
					   OrderedQuantity orderedQuantity = null;
					   BackOrderedQuantity backOrderedQuantity = null;
					   
					   
					   String status = "Shipped";
					   String statusDate = DateUtil.convertDateToGMTString(new Date());
					   
//					   Commented for back order quantity 0 value allow
//					   if((String.valueOf(lineItem.getShippedQuantity()) == null || String.valueOf(lineItem.getShippedQuantity()).equalsIgnoreCase("null") ||  lineItem.getShippedQuantity() == 0) && (String.valueOf(lineItem.getBackOrderQuantity()) == null || String.valueOf(lineItem.getBackOrderQuantity()).equalsIgnoreCase("null") ||  lineItem.getBackOrderQuantity() == 0))
					   
					   LOGGER.debug("Shipped Quantyity "+lineItem.getShippedQuantity());
					   LOGGER.debug("BackOrder Quantyity "+lineItem.getBackOrderQuantity());
					   if((String.valueOf(lineItem.getShippedQuantity()) == null || String.valueOf(lineItem.getShippedQuantity()).equalsIgnoreCase("null") ||  lineItem.getShippedQuantity() == 0 || lineItem.getShippedQuantity()!=-999) && (String.valueOf(lineItem.getBackOrderQuantity()) == null || String.valueOf(lineItem.getBackOrderQuantity()).equalsIgnoreCase("null")))
					   {
						   continue;
					   }else if((lineItem.getShippedQuantity()==0 || lineItem.getShippedQuantity()==-999) && (lineItem.getCarrier()!=null || lineItem.getTrackingNumber()!=null || lineItem.getActualShipDate()!=null)){
						   continue;
					   }
						   
					   
					   /************** Shipped Quantity ************/
					   if(String.valueOf(lineItem.getShippedQuantity()) != null && lineItem.getShippedQuantity()!=-999)
					   {
						   if(lineItem.getShippedQuantity() != 0)
						   {						   
							   shippedQuantity = new ShippedQuantity(null,String.valueOf(lineItem.getShippedQuantity()));
						   }else{
							   status = null;
							   statusDate = null;
						   }
					   }
					   //commented by sagar
					   /* else{
						   shippedQuantity = new ShippedQuantity(null,String.valueOf(0));
					   }*/
					   
					   
					   /*************** End ***************/
					   
					   /************** Ordered Quantity ************/
					   if(String.valueOf(lineItem.getQuantity()) != null)
					   {
						   if(!lineItem.getQuantity().equals("0") && shippedQuantity!=null)
						   {
							   orderedQuantity = new OrderedQuantity(null,String.valueOf(lineItem.getQuantity()));
						   }
					   }
					   /*************** End ***************/
					   
					   /************** Back Ordered Quantity ************/
					   
					   String partNumber = lineItem.getProductTLI();
					  // String serviceProviderRefNumber = null;
					   /*if(contract.getOrder().getServiceProviderReferenceNumber() != null || contract.getOrder().getServiceProviderReferenceNumber().trim() != "")
					   {
						   serviceProviderRefNumber = contract.getOrder().getServiceProviderReferenceNumber();
						   LOGGER.debug("********serviceProviderRefNumber "+serviceProviderRefNumber);
					   }*/
					   
					   if(String.valueOf(lineItem.getBackOrderQuantity()) != null && lineItem.getBackOrderQuantity()!=-999)
					   {
						   if(shippedQuantity==null)
						   {
							   partNumber = null;
							   //serviceProviderRefNumber = null; //AMS change LEX:AIR00073949
							   serviceProviderRefNumber = contract.getOrder().getServiceProviderReferenceNumber();
							   status = null;
							   statusDate = null;
						   }
						   if(lineItem.getBackOrderQuantity() != 0)						  
						   {
							   backOrderedQuantity = new BackOrderedQuantity(null,String.valueOf(lineItem.getBackOrderQuantity()));
						   }else if(lineItem.getBackOrderQuantity() == 0){
							   backOrderedQuantity = new BackOrderedQuantity(null,String.valueOf("0"));							   
						   }
					   } 
					   /*************** End ***************/
					   
					   /************** Line Details ************/
					   
					   SerialNumbers[] serialNumbers = null;
					   
					  
					   
					   if(lineItem.getSerialNumber() != null)
					   {
						   List<String> serialNumberList = new ArrayList<String>();
						   putDataInList(serialNumberList,lineItem.getSerialNumber());
						   
						   int j=0;
						   int SNListsize=0;
						   
						   for(String serialNumber : serialNumberList) 
						   {
							   if(!serialNumber.trim().equalsIgnoreCase("empty") && !serialNumber.trim().equalsIgnoreCase("empty|"))
							   {
								   SNListsize = SNListsize + 1;
							   }
						   }
						   
						   serialNumbers  = new SerialNumbers[SNListsize];	
						   
						   for(String serialNumber : serialNumberList) 
						   {
							   if(!serialNumber.trim().equalsIgnoreCase("empty") && !serialNumber.trim().equalsIgnoreCase("empty|"))
							   {
								   LOGGER.debug("************SerialNumber before********"+serialNumber+"**********");
								   if(serialNumber.contains("|")){
										if(serialNumber.endsWith("|")){
											serialNumber = serialNumber.trim();
											serialNumber = serialNumber.substring(0, serialNumber.length()-1);
										}
								   }
								   
								   LOGGER.debug("************SerialNumber after********"+serialNumber+"**********");
								   
								   SerialNumbers orderSN = new SerialNumbers(serialNumber);							   
								   serialNumbers[j] = orderSN;
								   j++;
							   }
						   }
						    
					   }
					   
					  
					   
					   if(serialNumbers == null)
					   {
						   LOGGER.debug("***********Serial Number is null***************");
					   }
					   
					  
					   LineDetails lineDetails = null;
					   
					   LOGGER.debug("line item id under orderservice ---->"+lineItem.getId());
					   
					   String ActualShipDate = null;
					   String ETA = null;
					   
					   
					   if(lineItem.getActualShipDate()!=null)
					   {
						   ActualShipDate = DateUtil.convertDateToGMTString(lineItem.getActualShipDate());
					   }else{
						   ActualShipDate = null;
					   }
					   
					   if(lineItem.getEta()!=null)
					   {
						   final String TIMEZONE="GMT";
						   final String DATEFORMAT="MM/dd/yyyy HH:mm:ss";
							SimpleDateFormat dateFormatGMT = new SimpleDateFormat(DATEFORMAT);
						    ETA = DateUtil.convertDateToGMTString(dateFormatGMT.parse(lineItem.getEta()));
					   }else{
						   ETA = null;
					   }
					   
					   if(shippedQuantity !=null){
						   LOGGER.debug("Shipment Qnty--->"+shippedQuantity);
						   LOGGER.debug("ShippedQuantityUnitOfMeasure--->"+shippedQuantity.getShippedQuantityUnitOfMeasure());
						   
					   }
					   if(backOrderedQuantity != null){
						   LOGGER.debug("BackOrderedQuantity--->"+backOrderedQuantity);
						   LOGGER.debug("BackOrderedQuantityUnitOfMeasure--->"+backOrderedQuantity.getBackOrderedQuantityUnitOfMeasure());
						   
					   }
					   if(lineItem!= null){
						   LOGGER.debug("*********************************** Printing PendingShipments Line Items ["+i+"]********************************************************************");
						   LOGGER.debug("lineItem.getId()-->"+lineItem.getId());
						   LOGGER.debug("lineItem.getName()-->"+lineItem.getName());
						   LOGGER.debug("lineItem.getSerialNumber()-->"+lineItem.getSerialNumber());
						   LOGGER.debug("lineItem.getPartName()-->"+lineItem.getPartName());
						   LOGGER.debug("lineItem.getPartnumber()-->"+lineItem.getPartnumber());
						   LOGGER.debug("ShippedQuantity()-->"+shippedQuantity);
						   LOGGER.debug("BackOrderQuantity()-->"+backOrderedQuantity);
						   LOGGER.debug("lineItem.getActualShipDate()-->"+lineItem.getActualShipDate());
						   LOGGER.debug("lineItem.getActualDeliveryDate()-->"+lineItem.getActualDeliveryDate());
						   LOGGER.debug("lineItem.getCarrier()-->"+lineItem.getCarrier());
						   LOGGER.debug("lineItem.getContactMethod()-->"+lineItem.getContactMethod());
						   LOGGER.debug("lineItem.getCurrencyCode()-->"+lineItem.getCurrencyCode());						   
						   LOGGER.debug("lineItem.getEta()-->"+lineItem.getEta());						  
						   LOGGER.debug("lineItem.getIndent()-->"+lineItem.getIndent());
						   LOGGER.debug("lineItem.getIntegrationId()-->"+lineItem.getIntegrationId());						  
						   LOGGER.debug("lineItem.getQuantity()-->"+lineItem.getQuantity());
						   LOGGER.debug("lineItem.getProductDescription()-->"+lineItem.getProductDescription());						   
						   LOGGER.debug("lineItem.getServiceRequestId()-->"+lineItem.getServiceRequestId());
						   
						   LOGGER.debug("lineItem.getStatus()-->"+lineItem.getStatus());
						   LOGGER.debug("lineItem.getTrackingNumber()-->"+lineItem.getTrackingNumber());
						   LOGGER.debug("lineItem.getVendorId()-->"+lineItem.getVendorId());
						   
						   LOGGER.debug("lineItem.getOrderedDate()-->"+lineItem.getOrderedDate());
						   LOGGER.debug("lineItem.getSerialNumbers()-->"+lineItem.getSerialNumbers());
						   LOGGER.debug("lineItem.getStatusDate()-->"+lineItem.getStatusDate());
						   
						   LOGGER.debug("***********************************PendingShipments Printing Line Items ["+i+"] ends***********************************************************");
						   
						   LOGGER.debug("***********************************Sent Values***********************************************************");
						   LOGGER.debug("ID-->"+lineItem.getId());
						   LOGGER.debug("PartNumber-->"+lineItem.getProductTLI());
						   LOGGER.debug("status-->"+status);
						   LOGGER.debug("statusDate-->"+statusDate);
						   LOGGER.debug("ShippedQuantity()-->"+shippedQuantity);
						   LOGGER.debug("orderedQuantity()-->"+orderedQuantity);
						   LOGGER.debug("BackOrderQuantity()-->"+backOrderedQuantity);
						   LOGGER.debug("lineItem.getCarrier()-->"+lineItem.getCarrier());
						   LOGGER.debug("lineItem.getTrackingNumber()-->"+lineItem.getTrackingNumber());
						   
						   
						   LOGGER.debug("ETA-->"+ETA);
						   LOGGER.debug("ActualShipDate-->"+ActualShipDate);
						  
						   LOGGER.debug("serviceProviderRefNumber-->"+serviceProviderRefNumber);
						   LOGGER.debug("***********************************Sent Values End***********************************************************");
					   }
					   
					   
					   
					   lineDetails = new LineDetails(
							   	   lineItem.getId(),
//							   	   String.valueOf(i),
							   	   lineItem.getProductTLI(),
						           status,
//							   	   null,
//						           lineItem.getStatusDate().toString(),
						           statusDate,
						           shippedQuantity,
						           orderedQuantity,
						           backOrderedQuantity,
						           lineItem.getCarrier(),	
						           lineItem.getTrackingNumber(),
						           null, //Need to confirm Customer Requested Date
						           ETA, 
						           ActualShipDate,
						           null,
//						           lineItem.getContactMethod(), //Need to confirm Shipment Method
						           null,
//						           lineItem.getServiceRequestId(),//Need to confirm shipmentLineReferenceNumber
						           serviceProviderRefNumber,
						           serialNumbers);
										   		
					   partLineList[i] = lineDetails;
				        i++;
				        /*************** End ***************/
				   }					   
			   }
			   /*************** End ***************/
			   
			   /************** Processed Parts Line Details ************/
			   if(contract.getOrder().getProcessedParts() != null)
			   {
				   for(ServiceRequestOrderLineItem lineItem : contract.getOrder().getProcessedParts()) 
				   {
					   
					   if(lineItem.getId() == null ||  lineItem.getStatus() == null || lineItem.getTrackingNumber() == null || lineItem.getActualDeliveryDate()== null)
					   {
						   continue;
					   }
					   ShippedQuantity shippedQuantity = null;					   
					   OrderedQuantity orderedQuantity = null;
					   BackOrderedQuantity backOrderedQuantity = null;
					   
					   /************** Shipped Quantity ************/
					   if(String.valueOf(lineItem.getShippedQuantity()) != null)
					   {
						   shippedQuantity = new ShippedQuantity(null,String.valueOf(lineItem.getShippedQuantity()));
					   }
					   
					   /*************** End ***************/
					   
					   /************** Ordered Quantity ************/
					   if(String.valueOf(lineItem.getQuantity()) != null)
					   {
						   orderedQuantity = new OrderedQuantity(null,String.valueOf(lineItem.getQuantity()));
					   }
					   
					   /*************** End ***************/
					   
					   /************** Back Ordered Quantity ************/
					   if(String.valueOf(lineItem.getBackOrderQuantity()) != null)
					   {
						   backOrderedQuantity = new BackOrderedQuantity(null,String.valueOf(lineItem.getBackOrderQuantity()));
					   }
					   /*************** End ***************/
					   
					   /************** Line Details ************/
					   
					   SerialNumbers[] serialNumbers = null;
					   String ActualDeliveryDate = null;					   
					   if(lineItem.getActualDeliveryDate()!=null)
					   {
						   ActualDeliveryDate = DateUtil.convertDateToGMTString(lineItem.getActualDeliveryDate());
					   }else{
						   ActualDeliveryDate = null;
					   }
					   
					   LineDetails lineDetails = null;
					   
					   if(orderedQuantity != null){
						   LOGGER.debug("OrderedQuantity--->"+orderedQuantity.getOrderedQuantity());
						   LOGGER.debug("OrderedQuantityUnitOfMeasure--->"+orderedQuantity.getOrderedQuantityUnitOfMeasure());
					   }
					   if(shippedQuantity !=null){
						   LOGGER.debug("Shipment Qnty--->"+shippedQuantity.getShippedQuantity());
						   LOGGER.debug("ShippedQuantityUnitOfMeasure--->"+shippedQuantity.getShippedQuantityUnitOfMeasure());
						   
					   }
					   if(backOrderedQuantity != null){
						   LOGGER.debug("BackOrderedQuantity--->"+backOrderedQuantity.getBackOrderedQuantity());
						   LOGGER.debug("BackOrderedQuantityUnitOfMeasure--->"+backOrderedQuantity.getBackOrderedQuantityUnitOfMeasure());
						   
					   }
					   if(lineItem!= null){
						   LOGGER.debug("*********************************** Printing ProcessedParts Line Items ["+i+"]********************************************************************");
						   LOGGER.debug("lineItem.getId()-->"+lineItem.getId());
						   LOGGER.debug("lineItem.getName()-->"+lineItem.getName());
						   LOGGER.debug("lineItem.getSerialNumber()-->"+lineItem.getSerialNumber());
						   LOGGER.debug("lineItem.getPartName()-->"+lineItem.getPartName());
						   LOGGER.debug("lineItem.getPartnumber()-->"+lineItem.getPartnumber());
						   LOGGER.debug("lineItem.getShippedQuantity()-->"+lineItem.getShippedQuantity());
						   LOGGER.debug("lineItem.getActualShipDate()-->"+lineItem.getActualShipDate());
						   LOGGER.debug("lineItem.getActualDeliveryDate()-->"+lineItem.getActualDeliveryDate());
						   LOGGER.debug("lineItem.getBackOrderQuantity()-->"+lineItem.getBackOrderQuantity());
						   LOGGER.debug("lineItem.getCarrier()-->"+lineItem.getCarrier());
						   LOGGER.debug("lineItem.getContactMethod()-->"+lineItem.getContactMethod());
						   LOGGER.debug("lineItem.getCurrencyCode()-->"+lineItem.getCurrencyCode());						   
						   LOGGER.debug("lineItem.getEta()-->"+lineItem.getEta());						  
						   LOGGER.debug("lineItem.getIndent()-->"+lineItem.getIndent());
						   LOGGER.debug("lineItem.getIntegrationId()-->"+lineItem.getIntegrationId());						  
						   LOGGER.debug("lineItem.getQuantity()-->"+lineItem.getQuantity());
						   LOGGER.debug("lineItem.getProductDescription()-->"+lineItem.getProductDescription());						   
						   LOGGER.debug("lineItem.getServiceRequestId()-->"+lineItem.getServiceRequestId());
						   
						   LOGGER.debug("lineItem.getStatus()-->"+lineItem.getStatus());
						   LOGGER.debug("lineItem.getTrackingNumber()-->"+lineItem.getTrackingNumber());
						   LOGGER.debug("lineItem.getVendorId()-->"+lineItem.getVendorId());
						   
						   LOGGER.debug("lineItem.getOrderedDate()-->"+lineItem.getOrderedDate());
						   LOGGER.debug("lineItem.getSerialNumbers()-->"+lineItem.getSerialNumbers());
						   LOGGER.debug("lineItem.getStatusDate()-->"+lineItem.getStatusDate());
						   
						   LOGGER.debug("*********************************** ProcessedParts Printing Line Items ["+i+"] ends***********************************************************");
						   
					   }
					  
					   
					   lineDetails = new LineDetails(
							   lineItem.getId(),
						   	   null,
					           lineItem.getStatus(),
					           null,
					           null,
					           null,
					           null,
					           null,						           
					           lineItem.getTrackingNumber(),
					           null,
					           null, 
					           null,
					           ActualDeliveryDate,
					           null, 
					           serviceProviderRefNumber,
					           null);
										   		
					    partLineList[i] = lineDetails;
				        i++;
				        /*************** End ***************/
				   }					   
			   }
			   /*************** End ***************/
		   }
	   }
	   
	   

	   SalesOrderStatusData salesOrderStatusData = new SalesOrderStatusData(
			   contract.getOrder().getOrderNumber(),
	           contract.getSourceReferenceNumber(),
	           "",
	           "", //TO DO Need to confirm about this field
	           partLineList);
	   
	   SalesOrderStatusWSInput2 salesOrderStatusWSInput2 = new SalesOrderStatusWSInput2();
	   salesOrderStatusWSInput2.setDocumentMetaData(documentMetaData);
	   salesOrderStatusWSInput2.setSalesOrderStatusData(salesOrderStatusData);
	   
	   SalesOrderStatusWSInput salesOrderStatusWSInput = new SalesOrderStatusWSInput();
	   salesOrderStatusWSInput.setSalesOrderStatusWSInput(salesOrderStatusWSInput2);
	   
	   LOGGER.debug("WS URL ---->"+getAddress());
	  
	   try
	   { 
		   LOGGER.debug("Invoking WS ---->"+System.currentTimeMillis());
		   port.processOrderStatus(debug, salesOrderStatusWSInput);
		   LOGGER.debug("Invoking WS completed successfully---->"+System.currentTimeMillis());
	   }
	   catch(Exception e)
	   {
		   LOGGER.debug("Just After Actual Call,call failed reason "+e.getMessage());
		   result.setSuccess(false);
		   return result;
	   }
	   LOGGER.debug("Just After Actual Call");
		

	   result.setSuccess(true);
	   return result;
		
	}

}
