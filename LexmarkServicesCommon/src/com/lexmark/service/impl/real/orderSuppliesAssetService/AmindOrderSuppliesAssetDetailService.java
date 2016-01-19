package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.util.LangUtil.isBlank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.AssetContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.result.AssetResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.MPSMeterRead;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetOrderPartDo;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;


/**
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class AmindOrderSuppliesAssetDetailService {
    
    //private static Log logger = LogFactory.getLog(AmindOrderSuppliesAssetDetailService.class);
	private static Logger logger = LogManager.getLogger(AmindOrderSuppliesAssetDetailService.class);
    private final Session session;
    private final Session sessionConsumableDD;
    private ExecutorService executor;
    public AmindOrderSuppliesAssetDetailService(Session session, Session sessionConsumableDD) {
        super();
        this.session = session;
        this.sessionConsumableDD = sessionConsumableDD;
        
    }

    // Originally copied from  com.lexmark.service.impl.real.util.AmindContractedDeviceServiceUtil.retrieveDeviceDetail(IDataManager, Log, AssetContract)
    public AssetResult retrieveDeviceDetail(final AssetContract contract) {
        checkRequiredFields(contract);
        final Asset asset = new Asset();
        
        AssetResult assetResult = new AssetResult();
        final IDataManager dataManager = this.session.getDataManager();
        final IDataManager dataManagerConsumableDD = this.sessionConsumableDD.getDataManager();
        String searchExpression = String.format("[assetId]='%s'", contract.getAssetId());
        
        if(!contract.getPageName().equalsIgnoreCase("CmDeviceDetail")) {
        	searchExpression = searchExpression + " AND ([LXK MPS Asset Contact.LXK MPS  Primary Role] = 'Y')";
        }
        
		if (contract.getPageName() != null 
				&& (contract.getPageName().equalsIgnoreCase("DeviceDetail")
						|| contract.getPageName().equalsIgnoreCase(
								"CmDeviceDetail")
						|| contract.getPageName().equalsIgnoreCase(
								"CmDecommissionDeviceDetail") || contract
						.getPageName().equalsIgnoreCase("BreakfixDeviceDetail")))        {
        	searchExpression = searchExpression+ "AND ([LXK MPS Pick Supplies.Id] IS NULL AND  [Order Entry - Line Items.Id] IS NULL AND [Agreement Entitlement.LXK MPS Entitlement Status]='Valid') ";
        }
        else if(contract.getPageName()!= null && contract.getPageName().equalsIgnoreCase("ConsumableDeviceDetail"))
        {
        	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    		String effectiveDate = formatter.format(contract.getEffectiveDate());
    		final ArrayList<OrderSuppliesAssetDetailDo> assetList = new ArrayList<OrderSuppliesAssetDetailDo>(); 
    		final ArrayList<OrderSuppliesAssetDetailDo> partList = new ArrayList<OrderSuppliesAssetDetailDo>(); 
    		
        	executor = Executors.newFixedThreadPool(2);
        	final String tempStr = searchExpression+"AND [LXK MPS Pick Supplies.Id] IS NULL  AND ([Agreement Entitlement.LXK MPS Catalog Flag]  IS NULL OR  [Agreement Entitlement.LXK MPS Catalog Flag] = 'N') AND [Agreement Entitlement.LXK MPS Entitlement Status] = 'Valid' "
        			+ " AND ([LXK MPS Pick Supplies.LXK MPS Effective End Date] >= '" + effectiveDate + "'"
        			+ " OR [LXK MPS Pick Supplies.LXK MPS Effective End Date] IS NULL)";  
        	List<Future<?>> list = new ArrayList<Future<?>>();
    		list.add(executor.submit(new Runnable() {
    			@Override
    			public void run() {
    				   List<OrderSuppliesAssetDetailDo> list = queryBySearchExpression(dataManager, OrderSuppliesAssetDetailDo.class, tempStr,
    			                contract, "query by Asset Id");
    				   assetList.addAll(list);
    		    		
    				  }
    		}));
    		
    		final String tempStr2 = searchExpression+"AND ([LXK MPS Pick Supplies.LXK MPS Type]='Supply Components' or [LXK MPS Pick Supplies.LXK MPS Type] IS NULL) AND [Agreement Entitlement.Id] IS NULL AND [Order Entry - Line Items.Id] IS NULL AND [LXK MPS Asset Contact.Id] IS NULL AND [LXK SW FS Asset Measurement Characteristics - Service Web.Id] IS NULL"
    				+ " AND ([LXK MPS Pick Supplies.LXK MPS Effective End Date] >= '" + effectiveDate + "'"
        			+ " OR [LXK MPS Pick Supplies.LXK MPS Effective End Date] IS NULL) AND [LXK MPS Pick Supplies.LXK MPS Display On Portal] = 'Y'";  
        	list.add(executor.submit(new Runnable() {
    			@Override
    			public void run() {
    				List<OrderSuppliesAssetDetailDo> list = queryBySearchExpression(dataManagerConsumableDD, OrderSuppliesAssetDetailDo.class, tempStr2,
			                contract, "query by Asset Id");
    				partList.addAll(list);
    				
    			}
    		}));
    		
    		try{
    				for (Future<?> future : list) {
    					future.get();
    				}
    		}
    		catch(Exception e)
    		{
    			LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract); 		    
    			throw new SiebelCrmServiceException("retreievDeviceDetail failed", e);
    		}
    		OrderSuppliesAssetDetailDo detailDo = LangUtil.first(assetList);
	        AmindOrderSuppliesAssetServiceUtil.toAsset(asset, detailDo, contract);
	        populateLastOrderPartList(asset, contract, detailDo.getOrderParts());
	        populatePageCounts(asset, contract, dataManager, detailDo); 
	        OrderSuppliesAssetDetailDo detailDoPart = LangUtil.first(partList);
			AmindOrderSuppliesAssetServiceUtil.processPartDetailDoList(asset, detailDoPart,contract);
    		executor.shutdown();
    	}
        else
        {
        	searchExpression = searchExpression + " AND ([LXK MPS Pick Supplies.LXK MPS Type]='Supply Components' or [LXK MPS Pick Supplies.LXK MPS Type] IS NULL) "; 
        }
       
		if(!contract.getPageName().equalsIgnoreCase("ConsumableDeviceDetail") ||  contract.getPageName().equalsIgnoreCase("")){
			
			List<OrderSuppliesAssetDetailDo> list = queryBySearchExpression(dataManager, OrderSuppliesAssetDetailDo.class, searchExpression,
	             contract, "query by Asset Id");
		  	OrderSuppliesAssetDetailDo detailDo = LangUtil.first(list);
	        if (LangUtil.isEmpty(list)) {
	        logger.warn("No records with Assed Id: " + contract.getAssetId());
		          return assetResult;
		    }
		
		      if (list != null && list.size() > 1) {
		          logger.warn("More than 1 record returned when querying with Assed Id: " + contract.getAssetId());
		    }
	        AmindOrderSuppliesAssetServiceUtil.toAsset(asset, detailDo,contract);
	        populateLastOrderPartList(asset, contract, detailDo.getOrderParts());
	        populatePageCounts(asset, contract, dataManager, detailDo); 
		}
	assetResult.setAsset(asset);
	return assetResult;
    }
    

    static void populateLastOrderPartList(Asset asset, AssetContract contract, ArrayList<OrderSuppliesAssetOrderPartDo> orderPartDOs) {
        Date currentDate = contract.getCurrentDate();
        if (currentDate == null) {
            logger.warn("contract currentDate is null, using local date as sysdate");
            currentDate = new Date();
        }
        
        Date fromDate = new Date(currentDate.getTime() - TimeUnit.DAYS.toMillis(15)); // 15 days period 
  
        List<Part> parts = new ArrayList<Part>();
        for (OrderSuppliesAssetOrderPartDo partDo : filterOrderParts(orderPartDOs, fromDate)) {
        		Part part = new Part();
        		part.setLastOrderDate(partDo.getOrderDate());
	            part.setShippedDate(partDo.getShippedDate());
	            part.setOrderNumber(partDo.getOrderNumber());
	            part.setOrderQuantity(partDo.getOrderQuantity());
	            part.setPartNumber(partDo.getPartNo());
	            part.setPartType(partDo.getPartType());
	            part.setStatus(partDo.getStatus());
	            part.setDescription(partDo.getDescription());
	            part.setSrNumber(partDo.getSrNumber()); //Updatd for #7423
	            part.setVendorPartNumber(partDo.getVendorPartNumber()); //added for vendor part number
	            
            parts.add(part);
        }
        
        asset.setLastOrderPartList(parts);        
    }

    
    /**
     * Returns orderParts with orderDate >= beginDate. 
     */
    static  List<OrderSuppliesAssetOrderPartDo> filterOrderParts(List<OrderSuppliesAssetOrderPartDo> orderParts, Date fromDate) {
        List<OrderSuppliesAssetOrderPartDo> result = new ArrayList<OrderSuppliesAssetOrderPartDo>(); 
        for (OrderSuppliesAssetOrderPartDo partDo : LangUtil.notNull(orderParts)) {
            boolean lineTypeAccepted = matchAny(partDo.getLineType(), "Ship", "Email Only");   
            Date orderDate = partDo.getOrderDate(); 
            boolean recent =  (orderDate != null && orderDate.getTime() >= fromDate.getTime()); 
            
            if (lineTypeAccepted && recent) { 
                result.add(partDo); 
            }
        }
        return result;
    }    
    
    /**
     *  Returns true if `s' equalsIgnoreCase to any value from `values', otherwise returns false.
     */
    static boolean matchAny(String s, String... values) {
        if (values == null || values.length == 0)
                return false;
            
        for (String val : values) {
            if (LangUtil.equalIgnoreCase(s, val)) 
                return true;
        }
       
        return false;
    }

    private void populatePageCounts(Asset asset, AssetContract contract, IDataManager dataManager, OrderSuppliesAssetDetailDo detailDo) {
//        String meterType = detailDo.getMeterType();
        List<PageCounts> pageCountsList = AmindOrderSuppliesAssetServiceUtil.processPageCounts(asset, detailDo.getPageCounts());
        asset.setPageCounts(pageCountsList);

//        if (!"manual".equalsIgnoreCase(meterType)) {
//            String searchExpressionMpsMeterRead = String.format("[assetId]='%s'", contract.getAssetId());
//            List<MPSMeterRead> mpsMeterReads = queryBySearchExpression(dataManager, MPSMeterRead.class, searchExpressionMpsMeterRead,
//                    contract, "query MPSMeterRead by Asset Id");
//            List<PageCounts> pageCountsListFromUPS = AmindOrderSuppliesAssetServiceUtil.processMpsMeterReads(asset, mpsMeterReads);
//
//            if (!LangUtil.isEmpty(pageCountsListFromUPS)) {
//                asset.setPageCounts(pageCountsListFromUPS);
//            }
//        }        
    }

    private void checkRequiredFields(AssetContract contract) {
        if (isBlank(contract.getAssetId()) || isBlank(contract.getPageName())) {
            throw new IllegalArgumentException("assetId or pagename is empty or null!");
        }
        if(contract.getPageName().equalsIgnoreCase("ConsumableDeviceDetail") && contract.getEffectiveDate() == null) {
        	 throw new IllegalArgumentException("Effective date is null!");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> queryBySearchExpression(IDataManager dataManager, Class<T> class1, String searchExpression, AssetContract contract, String description) {
        List<T> list = null;
        list = dataManager.queryBySearchExpression(class1, searchExpression);
        return list;
    }    
        
    public void shutDownExecutor() {
    	if (executor!=null && !executor.isShutdown()) {
    		executor.shutdown();
    	}
    }
}
