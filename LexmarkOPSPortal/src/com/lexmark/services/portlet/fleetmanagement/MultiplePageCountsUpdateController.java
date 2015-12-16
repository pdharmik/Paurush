package com.lexmark.services.portlet.fleetmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.PageCounts;
import com.lexmark.result.PageCountsResult;
import com.lexmark.result.UpdateAssetMeterReadResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.MeterReadService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.HTMLOutputGenerator;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.PropertiesMessageUtil;
@Controller
@RequestMapping("VIEW")
public class MultiplePageCountsUpdateController {
	
	
	private static Logger LOGGER = LogManager.getLogger(MultiplePageCountsUpdateController.class);
	
	@Autowired
    private OrderSuppliesAssetService orderSuppliesAssetService;
	
	@Autowired
    private GlobalService  globalService;
	
	@Autowired
	private MeterReadService meterReadService;
	
	/**
     * this method is used for getPageCount data
     * @param request 
     * @param response 
     * @param model 
     * @throws Exception 
     */
	@ResourceMapping ("getPageCountAssetData")
	public void getPageCountData(ResourceRequest request,@RequestParam("assetRowId") String id, ResourceResponse response){
		LOGGER.debug("[ inside Multiple Pagecounts update Data]");
				
	
		try{
			
			PageCountsContract contract = ContractFactory.getPageCountsContract(request);
			//contract.setAssetId("1-6MSC-75");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			PortletSession session = request.getPortletSession();
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
				"retrievePageCounts", PortalSessionUtil.getMdmId(session), 
				PortalSessionUtil.getServiceUser(session).getEmailAddress());
			PerformanceTracker.endTracking(lexmarkTran);
			PageCountsResult deviceResult = orderSuppliesAssetService.retrievePageCounts(contract);
			PerformanceTracker.endTracking(lexmarkTran);
			String resp=HTMLOutputGenerator.generatePageCountsJson(deviceResult.getPageCounts(),id);
			writeResponse(response,resp);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	

	/**
     * this method is used for updatePageCount popUp
     * @param asset 
     * @param request 
     * @param response 
     */	
	@ResourceMapping("postPgCountData")
	public void updatePageCount(@ModelAttribute("asset") Asset asset,
			ResourceRequest request, ResourceResponse response){
		LOGGER.debug("[In Update Page Counts multiple]");
		//asset.setAssetId("1-6MSC-75");
		try{
		LOGGER.debug("Asset id: "+asset.getAssetId());
		for(PageCounts pg:asset.getPageCounts()){
			ObjectDebugUtil.printObjectContent(pg, LOGGER);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		PortletSession session=request.getPortletSession();
		UpdateAssetMeterReadResult meterReadResult =null;
		try{
			
			UpdateAssetMeterReadContract contract = ContractFactory.getUpdateAssetMeterReadContract(request);
			contract.setSessionHandle(crmSessionHandle);
			contract.setAsset(asset);
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"updateAssetMeterRead", PortalSessionUtil.getMdmId(session), 
					PortalSessionUtil.getServiceUser(session).getEmailAddress());
			meterReadResult = meterReadService.updateAssetMeterRead(contract);
			PerformanceTracker.endTracking(lexmarkTran);	
			
		}catch(Exception e){
			LOGGER.debug("[Exception occurred ]");
			e.printStackTrace();
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
			StringBuffer resp=new StringBuffer();/* asdfsadf $$$  TEMP PPP */boolean success=true;//addred temp @@@@@@@@@
			if(meterReadResult!=null && meterReadResult.getResult()){
				success=true;
			}
			resp.append("{ \"success\":").append(String.valueOf(success)).append(",\"id\":\"").
			append(asset.getAssetId()).append("\"}");
			writeResponse(response, resp.toString());
		}
	}
	
	/**
	 * @param response 
	 * @param val 
	 */
	private void writeResponse(ResourceResponse response,String val){
			try {
				final PrintWriter out = response.getWriter();
				response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
				response.setProperty("Expires", "max-age=0,no-cache,no-store");
				response.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
				out.write(val);
				out.flush();
				out.close();
			} catch (IOException e) {
				LOGGER.error("IOException while invoking response#getWriter(),"
						+ e.getMessage());
		}
	}
	
}
