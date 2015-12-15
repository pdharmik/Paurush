package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.util.LangUtil.isNotEmpty;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.domain.MassUploadTemplateOrderLineItem;
import com.lexmark.domain.MassUploadTemplateShipment;
import com.lexmark.service.impl.real.domain.MassUploadTemplateOrderLineItemDo;
import com.lexmark.service.impl.real.domain.MassUploadTemplateShipmentDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class AmindMassUploadTemplateService {

	private Session session;
	private Map<String, String> fieldMap;
	private Map<String, Object> filterCriteria;
	private Map<String, Object> sortCriteria;
	private String searchExpression;
	private String mdmId;
	private String mdmLevel;
	private String status;
	private QueryObject criteria;
	private boolean isMassUploadRequest;

	public AmindMassUploadTemplateService(MassUploadTemplateContract contract) {
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		status = contract.getStatus();
		filterCriteria = contract.getFilterCriteria();
		sortCriteria = contract.getSortCriteria();
		isMassUploadRequest = contract.isMassUploadRequest();
		fieldMap = populateFieldMap();
	}

	public void checkRequredField() {
		if(LangUtil.isBlank(mdmId) || LangUtil.isBlank(mdmLevel)) {
			throw new IllegalArgumentException("Both, mdmId and mdmLevel must be specified!");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(mdmId, mdmLevel, fieldMap, false, "LXK MPS Account Level"));
		builder.append(appendStatusFilter());
		builder.append(appendFilterCriteria());
		builder.append(")");
		builder.append(appendBillingModelFilter());
		builder.append(appendMPSStatusFilter());
		builder.append(" AND [LXK MPS Type] ='MPS'");
		builder.append(" AND [Order Type] LIKE 'Consumable*'");
		
		searchExpression = builder.toString();
		
		buildQueryObject();
	}

	private void buildQueryObject() {
		criteria = new QueryObject(MassUploadTemplateOrderLineItemDo.class, ActionEvent.QUERY);
		if (sortCriteria != null && !sortCriteria.isEmpty()) {
			criteria.setSortString(AmindServiceUtil.buildSortString(sortCriteria, fieldMap));
		}
		criteria.addComponentSearchSpec(MassUploadTemplateOrderLineItemDo.class, searchExpression);
	}

	public List<MassUploadTemplateOrderLineItem> queryAndGetResult() {
		IDataManager dataManager = session.getDataManager();

		List<MassUploadTemplateOrderLineItemDo> templateList = dataManager.query(criteria);
		if (LangUtil.isEmpty(templateList)) {
			return new ArrayList<MassUploadTemplateOrderLineItem>();
		}

		return convertMassUploadTemplateDoToMassUploadTemplate(templateList);
	}

	private List<MassUploadTemplateOrderLineItem> convertMassUploadTemplateDoToMassUploadTemplate(List<MassUploadTemplateOrderLineItemDo> templateList) {
		
		List<MassUploadTemplateOrderLineItem> orderLineList = new ArrayList<MassUploadTemplateOrderLineItem>();
		
		for (MassUploadTemplateOrderLineItemDo itemDo : templateList) {
			MassUploadTemplateOrderLineItem orderLineItem = new MassUploadTemplateOrderLineItem();
			orderLineItem.setPartNumber(itemDo.getPartNumber());
			orderLineItem.setLineNumber(itemDo.getLineNumber());
			orderLineItem.setPartDescription(itemDo.getPartDescription());
			orderLineItem.setQuantitiyRequested(LangUtil.convertStringToInt(itemDo.getQuantitiyRequested()));
			orderLineItem.setRemainingQuantity(LangUtil.convertStringToInt(itemDo.getRemainingQuantity()));
			orderLineItem.setBackOrderQuantity(LangUtil.convertStringToInt(itemDo.getBackOrderQuantity()));
			orderLineItem.setOrderNumber(itemDo.getOrderNumber());
			orderLineItem.setSerialNumber(itemDo.getSerialNumber());
			orderLineItem.setOrderStatus(itemDo.getOrderStatus());
			orderLineItem.setPartnerOrderReferenceNumber(itemDo.getPartnerOrderReferenceNumber());
			
			List<MassUploadTemplateShipment> shipments = new ArrayList<MassUploadTemplateShipment>();
			if(LangUtil.isNotEmpty(itemDo.getShipments())) {
				for (MassUploadTemplateShipmentDo shipmentDo : itemDo.getShipments()) {
					MassUploadTemplateShipment shipment = new MassUploadTemplateShipment();
					shipment.setShippedQuantity(LangUtil.convertStringToInt(shipmentDo.getShippedQuantity()));
					shipment.setShippedDate(LangUtil.convertStringToGMTDate(shipmentDo.getShippedDate()));
					shipment.setTrackingNumber(shipmentDo.getTrackingNumber());
					shipment.setCarrier(shipmentDo.getCarrier());
					shipment.setShipmentStatus(shipmentDo.getShipmentStatus());
					shipment.setDeliveryDate(LangUtil.convertStringToGMTDate(shipmentDo.getDeliveryDate()));
					
					shipments.add(shipment);
				}
			}
			orderLineItem.setShipments(shipments);
			
			orderLineList.add(orderLineItem);
		}
		
		return orderLineList;
	}
	
	private String appendMPSStatusFilter() {
		
		StringBuilder searchSpec = new StringBuilder();
    	searchSpec.append("AND ([LXK MPS Status] <> 'Ready for Order' OR [LXK MPS Status] <> 'Pending' OR [LXK MPS Status] <> 'Assignment Failed'");
  
    	if(!"Closed".equalsIgnoreCase(status))
    	{
	    	if (isMassUploadRequest) {
				searchSpec.append(" AND [LXK MPS Status] <> 'Complete'");
			} else {
				searchSpec.append(" AND [LXK MPS Status] = 'Complete'");
			}
    	}
    	searchSpec.append(")");
    	return searchSpec.toString();
//		return " AND ([LXK MPS Status] <> 'Ready for Order' OR [LXK MPS Status] <> 'Pending' OR [LXK MPS Status] <> 'Assignment Failed')";
//		change for 12103
	}
	
	private String appendFilterCriteria() {
		if(filterCriteria!=null && !filterCriteria.isEmpty()) {
			
			String startDate = (String) filterCriteria.remove("order.startDate");
	        String endDate = (String) filterCriteria.remove("order.endDate");
	        
	        StringBuilder stringBuilder = new StringBuilder();
	        if (isNotEmpty(startDate)) {
	        	stringBuilder.append(format(" AND ([LXK MPS Created] >= '%s')", startDate));
	        }
	        if (isNotEmpty(endDate)) {
	        	stringBuilder.append(format(" AND ([LXK MPS Created] <= '%s')", endDate));
	        }
			
	        if(filterCriteria!=null && !filterCriteria.isEmpty()) {
		        stringBuilder.append(" AND ");
		        stringBuilder.append(AmindServiceUtil.buildSimpleCriteria(filterCriteria, fieldMap, true, true));
	        }
	        
			return stringBuilder.toString();
		}
		
		return "";
	}
	
	private String appendBillingModelFilter() {
		String billingModelField = fieldMap.get("readyForBilling"); 
		if("Open".equalsIgnoreCase(status) || "Closed".equalsIgnoreCase(status)){
			return " AND ([" + billingModelField + "] IS NULL)";
    	}
		return "";
	}

	private String appendStatusFilter() {
		StringBuilder searchSpec = new StringBuilder();
		
		if("Open".equalsIgnoreCase(status)){
			searchSpec.append("AND ([Status] = 'Routed' OR [Status] = 'Order Accepted' " +
    		" OR [Status] = 'Shipped'" +
    		" OR [Status] = 'Back Ordered'" +
    		" OR [Status] = 'Ship Pending' )");
			return searchSpec.toString();
		}
    	else if("Closed".equalsIgnoreCase(status)){
    		searchSpec.append("AND ([Status] = 'Routed' OR [Status] = 'Order Accepted' " +
    		" OR [Status] = 'Shipped' OR [Status] = 'Delivered' " +
    		" OR [Status] = 'Back Ordered'" +
    		" OR [Status] = 'Cancelled' OR [Status] = 'Ship Pending')");
    		return searchSpec.toString();
    		
    	}
    	else{
    		searchSpec.append("AND ([Status] = 'Routed' OR [Status] = 'Order Accepted' " +
    		" OR [Status] = 'Shipped' OR [Status] = 'Delivered' " +
    		" OR [Status] = 'Back Ordered'" +
    		" OR [Status] = 'Cancelled' OR [Status] = 'Ship Pending' )");
    		return searchSpec.toString();
    	}
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	private Map<String, String> populateFieldMap() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("mdmLevel1AccountId", "LXK MPS Global Account #");
		fieldMap.put("mdmLevel2AccountId", "LXK MPS Domestic Account #");
		fieldMap.put("mdmLevel3AccountId", "LXK MPS Legal Account #");
		fieldMap.put("mdmLevel4AccountId", "LXK MPS MDM Account #");
		fieldMap.put("mdmLevel5AccountId", "LXK MPS Account Level");
		fieldMap.put("requestNumber", "SR Number");
		fieldMap.put("orderNumber", "Order Number");
		fieldMap.put("customerAccount", "LXK MPS Order Account Name");
		fieldMap.put("responseMetric", "LXK MPS Response Time");
		fieldMap.put("asset.SerialNumber", "LXK MPS Serial Number");
		fieldMap.put("asset.ProductLine", "LXK MPS SR Product Line");
		fieldMap.put("asset.machineTypeModel", "LXK MPS Machine Model");
		fieldMap.put("serviceProviderReferenceNumber","LXK MPS Customer Ref Number");
		fieldMap.put("customerAddress.StreetAddress","LXK MPS Service Address1");
		fieldMap.put("customerAddress.City", "LXK MPS Service City");
		fieldMap.put("customerAddress.State", "LXK MPS Service State");
		fieldMap.put("customerAddress.Province", "LXK MPS Service Province");
		fieldMap.put("customerAddress.PostalCode","LXK MPS Service Postal Code");
		fieldMap.put("customerContact.FirstName","LXK MPS Requestor First Name");
		fieldMap.put("customerContact.LastName", "LXK MPS Requestor Last Name");
		fieldMap.put("createdDate", "LXK MPS Created");
		fieldMap.put("customerResponseDate", "LXK MPS Requested Delivery date");
		fieldMap.put("status", "Status");
		fieldMap.put("readyForBilling", "LXK MPS Ready for Billing");
		fieldMap.put("customerAddress.HouseNumber","LXK MPS Service Address Office");
		fieldMap.put("customerAddress.County", "LXK MPS Service Address County");
		fieldMap.put("customerAddress.District","LXK MPS Service Address District");
		return Collections.unmodifiableMap(fieldMap);
	}
}
