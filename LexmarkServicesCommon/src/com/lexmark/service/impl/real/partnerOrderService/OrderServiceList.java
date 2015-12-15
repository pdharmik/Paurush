package com.lexmark.service.impl.real.partnerOrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Order;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.source.OrderListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.OrderLineItemDo;
import com.lexmark.service.impl.real.domain.PartnerRequestOrderDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

/**
 * @see com.lexmark.service.impl.real.AmindPartnerOrderService
 * @see do-partnerrequestorder-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-22
 */
public class OrderServiceList {
    
   // private static Log logger = LogFactory.getLog(OrderServiceList.class);
	 private static Logger logger = LogManager.getLogger(OrderServiceList.class);
    private static final Class<?> DO_CLASS = PartnerRequestOrderDo.class;
    
    static final FieldCriteria fieldCriteria = populateFieldCriteria();
    private static final Map<String, String> ORDER_ITEM_FIELD_MAP = populateOrderItemFieldCriteria();
    private final Session session;
    private final AmindCrmSessionHandle crmSessionHandle;
    private final Session totalCountSession;
    
    public OrderServiceList(Session session, Session totalCountSession, AmindCrmSessionHandle crmSessionHandle) {
        this.session = session;
        this.totalCountSession = totalCountSession;
        this.crmSessionHandle = crmSessionHandle;
    }

    private static FieldCriteria populateFieldCriteria() {
        return new FieldCriteria()
                .add("mdmLevel1AccountId", "LXK MPS Global Account #")
                .add("mdmLevel2AccountId", "LXK MPS Domestic Account #")
                .add("mdmLevel3AccountId", "LXK MPS Legal Account #")
                .add("mdmLevel4AccountId", "LXK MPS MDM Account #")
                .add("mdmLevel5AccountId", "LXK MPS Account Id")

                .add("requestNumber", "SR Number")
                .add("orderNumber", "Order Number")
                .add("customerAccount", "Account Name")
                .add("responseMetric", "LXK MPS Response Time")  
                .add("asset.SerialNumber", "LXK MPS Serial Number") 
                .add("asset.ProductLine", "LXK MPS SR Product Line", false) 
                .add("asset.machineTypeModel", "LXK MPS Machine Model", false)
                .add("serviceProviderReferenceNumber", "LXK MPS Customer Ref Number", true)
                .add("customerAddress.StreetAddress", "LXK MPS Service Address1") 
                .add("customerAddress.City", "LXK MPS Service City") 
                .add("customerAddress.State", "LXK MPS Service State") 
                .add("customerAddress.Province", "LXK MPS Service Province")
                .add("customerAddress.PostalCode", "LXK MPS Service Postal Code") 
                .add("customerContact.FirstName", "LXK MPS Requestor First Name")
                .add("customerContact.LastName", "LXK MPS Requestor Last Name")
                .add("createdDate", "LXK MPS Created")  
                .add("customerResponseDate", "LXK MPS Requested Delivery date")  
                .add("status", "LXK MPS Status", true)
        		.add("readyForBilling", "LXK MPS Ready for Billing", true)
        		.add("customerAddress.HouseNumber", "LXK MPS Service Address Office") 
        		.add("customerAddress.County", "LXK MPS Service Address County") 
        		.add("customerAddress.District", "LXK MPS Service Address District"); 
    }
    
    private static  Map<String, String> populateOrderItemFieldCriteria() {
    	  Map<String, String> orderItemMap = new HashMap<String, String>();
    	  orderItemMap.put("mdmLevel1AccountId", "LXK MPS Global Account #");
    	  orderItemMap.put("mdmLevel2AccountId", "LXK MPS Domestic Account #");
    	  orderItemMap.put("mdmLevel3AccountId", "LXK MPS Legal Account #");
    	  orderItemMap.put("mdmLevel4AccountId", "LXK MPS MDM Account #");
    	  orderItemMap.put("mdmLevel5AccountId", "LXK MPS Service Provider Id");
    	  orderItemMap.put("status", "LXK MPS Status");
    	  orderItemMap.put("readyForBilling", "LXK MPS Ready for Billing"); 
          return  orderItemMap;      
    }
    
    
    public OrderListResult retrieveOrderList(final OrderListContract contract) throws InterruptedException, ExecutionException {
        logger.debug("[IN] retrieveOrderList");
        ExecutorService executor = null;
        try {
            checkRequiredFields(contract);

            final QueryObject queryObject = new QueryObject(DO_CLASS, ActionEvent.QUERY);
            AmindServiceUtil.buildBasicQueryObject(queryObject, contract.getIncrement(), contract.getStartRecordNumber());
            
            
            final String searchExpression = buildSearchExpression(contract, fieldCriteria);
            queryObject.addComponentSearchSpec(DO_CLASS, searchExpression);
            
            String ordedeItemsearchExpression = buildSearchExpForOrderItem(contract,ORDER_ITEM_FIELD_MAP);
            queryObject.addComponentSearchSpec(OrderLineItemDo.class, ordedeItemsearchExpression);
 
            if (hasSortCriteria(contract)) {
                queryObject.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), fieldCriteria.allFieldMap()));
            }
            

            executor = Executors.newFixedThreadPool(2);

            Future<List<PartnerRequestOrderDo>> doListFuture = executor.submit(new Callable<List<PartnerRequestOrderDo>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<PartnerRequestOrderDo> call() throws Exception {
						return (List<PartnerRequestOrderDo>) querySiebel(session, queryObject, contract);
					}
				});
    		
            Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return processRecordsTotalCount(contract, crmSessionHandle, totalCountSession, searchExpression);
				}
			});
            
			//List<PartnerRequestOrderDo> doList = (List<PartnerRequestOrderDo>) querySiebel(this.session, queryObject, contract);
            List<PartnerRequestOrderDo> doList = doListFuture.get();
    		int recordsTotalCount = totalCountFuture.get();
    		
    		executor.shutdown();
    		
    		if(LangUtil.isEmpty(doList)) {
            	return new OrderListResult();
            }
    		
    		OrderListResult result = toOrderListResult(recordsTotalCount, doList);
    		
            //int recordsTotalCount = processRecordsTotalCount(contract, this.crmSessionHandle, this.session, searchExpression);

            return result;
        } finally {
        	if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
        	logger.debug("[OUT] retrieveOrderList");
        }
    }
    
    private static void checkRequiredFields(OrderListContract contract) {
        if (!OrderServiceUtil.isSearchByMdm(contract)) {
            throw new SiebelCrmServiceException("No mdmId  or mdmLevel specified");
        }
    }
    
    static String buildSearchExpression(OrderListContract contract, FieldCriteria fieldCriteria) {
        return OrderServiceUtil.buildOrderListSearchExpression(contract, fieldCriteria); 
    }
    
    static String buildSearchExpForOrderItem(OrderListContract contract, Map<String, String> ORDER_ITEM_FIELD_MAP) {
        return OrderServiceUtil.buildOrderItemSearchExpression(contract, ORDER_ITEM_FIELD_MAP); 
    }
    
    
    private OrderListResult toOrderListResult(int recordsTotalCount, List<PartnerRequestOrderDo> doList) {
        List<Order> orders = new ArrayList<Order>();
        for (PartnerRequestOrderDo orderDo : LangUtil.notNull(doList))  {
        	
            Order order = new Order();
            order.setId(orderDo.getId());
            order.setCreatedDate(orderDo.getCreatedDate());
            order.setStatus(orderDo.getStatus()); 
            order.setContactMethod(orderDo.getContactMethod());
            order.setOrderNumber(orderDo.getOrderNumber());
            order.setRequestNumber(orderDo.getRequestNumber());
            order.setCustomerAccount(orderDo.getCustomerAccount());
            
            AccountContact contact = new AccountContact();
            contact.setFirstName(orderDo.getFirstName());
            contact.setLastName(orderDo.getLastName());
            contact.setWorkPhone(orderDo.getPhoneNumber()); 
            contact.setEmailAddress(orderDo.getEmailAddress());
            order.setCustomerContact(contact);

            order.setResponseMetric(orderDo.getResponseMetric());
            order.setCustomerRequestedResponseDate(orderDo.getCustomerRequestedResponseDate());
  
            order.setServiceRequestNumber(orderDo.getServiceRequestNumber());
            
            Asset asset = new Asset();
            asset.setSerialNumber(orderDo.getSerialNumber());
            asset.setMachineTypeModel(orderDo.getMachineTypeModel());
            asset.setProductLine(orderDo.getProductLine());
            order.setAsset(asset);

            GenericAddress customerAddress = new GenericAddress();
            customerAddress.setAddressId(orderDo.getAddressId());
            customerAddress.setAddressLine1(orderDo.getAddressLine1());
            customerAddress.setAddressLine2(orderDo.getAddressLine2());
            customerAddress.setAddressLine3(orderDo.getAddressLine3());
            customerAddress.setCity(orderDo.getCity());
            customerAddress.setState(orderDo.getState());
            customerAddress.setPostalCode(orderDo.getPostalCode());
            customerAddress.setProvince(orderDo.getProvince());
            customerAddress.setCounty(orderDo.getCounty());
            customerAddress.setDistrict(orderDo.getDistrict());
            customerAddress.setOfficeNumber(orderDo.getOfficeNumber());
            order.setCustomerAddress(customerAddress);
           	logger.debug("Order Number:" + orderDo.getOrderNumber());  
           	
            List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
            for (OrderLineItemDo lineItemDo : LangUtil.notNull(orderDo.getOrderLineItems())) {
              
            	ServiceRequestOrderLineItem lineItem = new ServiceRequestOrderLineItem();
            	logger.debug("Billing" + lineItemDo.getReadyForBilling());
            	logger.debug("Status" +  lineItemDo.getStatus());
             	lineItem.setId(lineItemDo.getId());
                lineItem.setPartnumber(lineItemDo.getPartNumber()); 
                lineItem.setProductDescription(lineItemDo.getDescription());
                lineItem.setPartType(lineItemDo.getPartType()); 
                lineItem.setQuantity(lineItemDo.getOrderedQuantity());
                lineItem.setStatus(lineItemDo.getStatus());
                lineItem.setVendorId(lineItemDo.getVendorId());
                lineItem.setContactMethod(lineItemDo.getContactMethod()); 
                lineItem.setId(lineItemDo.getId());
                lineItem.setOrderFultillmentStatus(lineItemDo.getOrderFultillmentStatus());
                lineItem.setPortalFulfillmentStatus(lineItemDo.getPortalFulfillmentStatus());                
                
                lineItem.setQuantityRequested(convertStringToInt(lineItemDo.getQuantityRequested()));
                lineItem.setRemainingQuantity(convertStringToInt(lineItemDo.getRemainingQuantity()));
                lineItem.setBackOrderQty(convertStringToInt(lineItemDo.getBackOrderQty()));
                lineItem.setShippedQuantity(convertStringToInt(lineItemDo.getShippedQuantity()));                
                lineItem.setShippedDate(lineItemDo.getShippedDate());
                lineItem.setBackOrderedDate(lineItemDo.getBackOrderedDate());
                lineItem.setDeliveryDate(lineItemDo.getDeliveryDate());
                lineItem.setCustomerReqItemId(lineItemDo.getCustomerReqItemId());
                lineItem.setTracking(lineItemDo.getTracking());
                lineItem.setCarrier(lineItemDo.getCarrier());
                lineItem.setShipmentStatus(lineItemDo.getShipmentStatus());
                lineItem.setLineNumber(lineItemDo.getLineNumber());
                lineItem.setSerialNumber(lineItemDo.getSerialNumber());
                lineItem.setFulfillmentStatus(lineItemDo.getFulfillmentStatus());
                
                order.setServiceProviderReferenceNumber(lineItemDo.getOrderReferenceNumber());
                orderLineItems.add(lineItem);
            }

            order.setPendingShipments(orderLineItems); 
            
            String status = totalOrderSatus(orderLineItems);
            order.setStatus(status);
            
            String fulfillmentStatus = totalOrderFulfillmentSatus(orderLineItems);
            order.setFulfillmentStatus(fulfillmentStatus);
            
            orders.add(order);
        }
        
        OrderListResult result = new OrderListResult();
        result.setTotalCount(recordsTotalCount);
        result.setOrderList(orders);
        return result;
    }
    


	public static String totalOrderSatus(List<ServiceRequestOrderLineItem> orderLineItems) {
        String[] statuses =  {
	//Changed by Ranjan, 13.4 release
                "Routed",
                "Back Ordered",
                "Order Accepted", 
                "Ship Pending",
                "Shipped",
                "Delivered", 
                "Cancelled",
                "In Transit",
        };
        
        for (String status : statuses) {
            if (anyHasStatus(orderLineItems, status)) {
                return status;
            }
        }
        
        return null;
    }
	
	public static String totalOrderFulfillmentSatus(List<ServiceRequestOrderLineItem> orderLineItems) {
        String[] statuses =  {
	//Changed by Ranjan, 13.4 release
                "Routed",
                "Back Ordered",
                "Order Accepted", 
                "Ship Pending",
                "Shipped",
                "Delivered", 
                "Cancelled",
                "In Transit",
        };
        
        for (String status : statuses) {
            if (anyHasFulfillmentStatus(orderLineItems, status)) {
                return status;
            }
        }
        
        return null;
    }

    private static boolean anyHasStatus(List<ServiceRequestOrderLineItem> orderLineItems, String status) {
        for (ServiceRequestOrderLineItem orderLineItem : LangUtil.notNull(orderLineItems)) {
        	if (LangUtil.equal(status, orderLineItem.getStatus())) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean anyHasFulfillmentStatus(List<ServiceRequestOrderLineItem> orderLineItems, String status) {
        for (ServiceRequestOrderLineItem orderLineItem : LangUtil.notNull(orderLineItems)) {
         	if (LangUtil.equal(status, orderLineItem.getFulfillmentStatus())) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSortCriteria(SearchContractBase contract) {
        return (contract.getSortCriteria() != null &&  !contract.getSortCriteria().isEmpty());
    } 
    
    private static List<?> querySiebel(Session session, QueryObject criteria, OrderListContract contract) {
        IDataManager dataManager =  session.getDataManager();
        List<?> assetList = null;
        if (contract.isNewQueryIndicator()) {
            logger.debug("[IN] Order List");
            assetList = dataManager.query(criteria);
            logger.debug("[OUT] Order List");
        } else {
            assetList = dataManager.queryNext(criteria);
       }
        return assetList;
    }    
    
    private int processRecordsTotalCount(OrderListContract contract, AmindCrmSessionHandle crmSessionHandle, Session session, String searchExpression) {
        int count = 0;
        if (contract.isNewQueryIndicator()) {
            logger.debug("[IN] Count Method");
            count = countRecords(session, searchExpression);
            crmSessionHandle.setPartnerRequestOrderCount(count);
            logger.debug("[OUT] Count Method");
        }
        else {
            count = crmSessionHandle.getPartnerRequestOrderCount(); 
        }

        return count;
    }
    
    static int countRecords(Session session, String searchExpression) {
        String businessObject    =  PartnerRequestOrderDo.BO;
        String businessComponent =  PartnerRequestOrderDo.BC;
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        return AmindServiceUtil.getBCTotalCount(businessObject, businessComponent, searchExpression, businessServiceProxy);
    }
    
    private int convertStringToInt(String intValue) {
		
    	if(LangUtil.isBlank(intValue)) return 0;
		
		try {
			return Integer.parseInt(intValue);
		} catch (NumberFormatException e) {
			
			return 0;
		}
	}
}
