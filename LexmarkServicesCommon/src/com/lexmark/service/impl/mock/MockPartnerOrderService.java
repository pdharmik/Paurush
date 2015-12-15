package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.BundlePart;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Order;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.MassUploadTemplateResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.source.OrderAcceptResult;
import com.lexmark.result.source.OrderDetailResult;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.api.PartnerOrderService;

public class MockPartnerOrderService implements PartnerOrderService{
	final String orderId[]={"1-5DYBHCJ","1-5CXM71J","1-5CTOUHS"};
	final String date[]={"06/01/2013 01:46:44","05/29/2013 18:29:49","05/28/2013 23:22:23"};
	final String orderNumber[]={"1-11727614611","1-11665968679","1-11659373920"};
	final String serviceRequestNumber[]={"1-11713902601","1-11665928401","1-11657937161"};
	final String serialNumber[]={"0254508","2401505","2401503"};
	final String productLine[]={"Lexmark C935dtn","Lexmark X864dhe4","Lexmark X864dhe4"};
	final String machineTypeModel[]={"5057-234","5057-230","7500-832"};
	final String catalogId[]={"41G0100","27S2100","27S2400"};
	final String desc[]={"Lexmark C746dtn","550-SHEET DRAWER","2,000-Sheet High Capacity Feeder"};
	final String srType[]={"INSTALL","DEINSTALL","HWMOVE"};
	@Override
	public OrderAcceptResult acceptServiceOrderRequest(
			OrderAcceptContract contract) throws LGSCheckedException,
			LGSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailResult retrieveOrderDetail(OrderDetailContract contract)
			throws LGSCheckedException, LGSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderListResult retrieveOrderList(OrderListContract contract)
			throws Exception {
		OrderListResult orderListResult=new OrderListResult();
		orderListResult.setOrderList(generateMockData());
		orderListResult.setTotalCount(20);
		return orderListResult;
	}
	
	
	public ActivityListResult retrieveHardwareList(SearchContractBase contract)
			throws Exception {
		ActivityListResult activityListResult=new ActivityListResult();
		activityListResult.setTotalcount(55);
		activityListResult.setActivityList(getHardwareMockData((activityListResult.getTotalcount()-contract.getStartRecordNumber()>40?40:activityListResult.getTotalcount()-contract.getStartRecordNumber())));
		
		return activityListResult;
	}	
	

	@Override
	public AttachmentListResult retrieveRequestAttachmentList(
			AttachmentContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Activity> getHardwareMockData(int increment){
		List<Activity> orderList=new ArrayList<Activity>();
		int index=0;
		for(int i=0;i<increment;i++){
			Activity order=new Activity();
			index=i%3;
			ListOfValues lov=new ListOfValues();
			lov.setValue(srType[index]);
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setServiceRequestType(lov);
			serviceRequest.setServiceRequestNumber(serviceRequestNumber[index]);
			serviceRequest.setServiceRequestDate(new Date(date[index]));
			
			
			
			Asset asset=new Asset();
			asset.setSerialNumber(serialNumber[index]);
			asset.setActivityNumber("12345");
			asset.setMachineTypeModel(machineTypeModel[index]);
			asset.setProductLine(productLine[index]);
			asset.setProductTLI("productTLI");
			Account partnerAccount=new Account();
			partnerAccount.setAccountName("Partner Account");
			Account customerAccount=new Account();
			partnerAccount.setAccountName("Branch & banking");
			asset.setCustomerAccount(customerAccount);
			asset.setPartnerAccount(partnerAccount);
			
			serviceRequest.setAsset(asset);
			serviceRequest.setServiceRequestStatus("Pending");
			
			
			
			AccountContact technicianContact=new AccountContact();
			technicianContact.setFirstName("Technician First Name");
			technicianContact.setLastName("Technician Last Name");
			order.setTechnician(technicianContact);
			order.setResponseMetric("10");
			order.setCustomerRequestedResponseDate(new Date());
			
			
			
			
			AccountContact customerContact=new AccountContact();
			customerContact.setFirstName("Customer First Name");
			customerContact.setLastName("Customer Last Name");
			serviceRequest.setPrimaryContact(customerContact);
			//order.setPrimaryContact(customerContact);
			
			order.setServiceRequest(serviceRequest);
			
			order.setServiceAddress(PartnerDomainMockDataGenerator.getGenericAddress(i));
			
			
			//part.setDescription("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy.Installation and Warranty");
			List<PartLineItem> orderPartList=new ArrayList<PartLineItem>();
			for(int j=0;j<(i%2==0?3:4);j++){
				PartLineItem part=new PartLineItem();
				part.setPartNumber(catalogId[j%3]);
				part.setDescription(desc[j%3]);
				part.setQuantity(1);
				orderPartList.add(part);
				
			}
			
			
			order.setOrderPartList(orderPartList);
			
			
			
			orderList.add(order);
		}
		return orderList;
	}
	
	private List<Order> generateMockData(){
		List<Order> orderList=new ArrayList<Order>();
		
		//final String status[]={"Routed"};
		int index=0;
		for(int i=0;i<20;i++){
			Order order=new Order();
			index=i%3;
			order.setId(orderId[index]);
			order.setStatus("Routed");
			order.setCustomerAccount("Innovapost Inc.");
			order.setCustomerAddress(PartnerDomainMockDataGenerator.getGenericAddress(i));
			List<ServiceRequestOrderLineItem> newItem= new ArrayList<ServiceRequestOrderLineItem>();
			for(ServiceRequestOrderLineItem item : PartnerDomainMockDataGenerator.getServiceRequestOrderLines("ship") ){
				item.setVendorId("1-35FVDQ3");
				newItem.add(item);
			}
			order.setPendingShipments(newItem);
			order.setCreatedDate(new Date(date[index]));
			order.setVendorId("1-35FVDQ3");
			order.setOrderNumber(orderNumber[index]);
			order.setServiceRequestNumber(serviceRequestNumber[index]);
			Asset asset=new Asset();
			asset.setSerialNumber(serialNumber[index]);
			asset.setProductLine(productLine[index]);
			asset.setMachineTypeModel(machineTypeModel[index]);
			order.setAsset(asset);
			order.setResponseMetric("5");
			order.setCustomerRequestedResponseDate(new Date(date[index]));
			
			orderList.add(order);
			
		}
		return orderList;
	}
	
	public void generateHardwareViewData(){
		ServiceRequest dummyServiceRequestDetails=new ServiceRequest();
		Asset asset=new Asset();
		asset.setActivityNumber("1234567890");
		Account customerAccount=new Account();
		customerAccount.setAccountName("target");
		customerAccount.setAddress(PartnerDomainMockDataGenerator.getGenericAddress(5));
		asset.setCustomerAccount(customerAccount);
		
		dummyServiceRequestDetails.setAsset(asset);
		dummyServiceRequestDetails.setServiceRequestNumber("1-1234567890");
		dummyServiceRequestDetails.setReferenceNumber("1234567");
		dummyServiceRequestDetails.setServiceRequestDate(new Date());
		dummyServiceRequestDetails.setServiceRequestStatus("Accepted");
		dummyServiceRequestDetails.setStatusDetail("Tech on site");
		
		Part part=new Part();
		part.setDescription("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy.Installation and Warranty");
		List<BundlePart> bPartList=new ArrayList<BundlePart>();
		for(int j=0;j<2;j++){
			BundlePart bPart=new BundlePart();
			bPart.setPartNumber("123456SDFGH");
			bPart.setDescription("Description of the item goes here");
			bPart.setQty("1");
			bPartList.add(bPart);
		}
		part.setBundlePartList(bPartList);
		List<Part> parts=new ArrayList<Part>();
		parts.add(part);
		
		dummyServiceRequestDetails.setParts(parts);
		
		
		
		
		
		AccountContact primaryAccount=new AccountContact();
		primaryAccount.setFirstName("primaryAccount First Name");
		primaryAccount.setLastName("primaryAccount Last Name");
		
		AccountContact secondaryAccount=new AccountContact();
		secondaryAccount.setFirstName("secondaryAccount First Name");
		secondaryAccount.setLastName("secondaryAccount Last Name");
	}

	@Override
	public MassUploadTemplateResult retrieveMassUploadTemplateList(
			MassUploadTemplateContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
