package com.lexmark.service.impl.real;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.MassUploadTemplateContract;
import com.lexmark.domain.MassUploadTemplateOrderLineItem;
import com.lexmark.result.MassUploadTemplateResult;

public class MassUploadTemplateTest extends AmindServiceTest{

	private AmindPartnerOrderService service = new AmindPartnerOrderService();
	
	@Before
	public void setUp() throws Exception {
		service = new AmindPartnerOrderService();
		service.setStatelessSessionFactory(statelessSessionFactory);
	}

	@Test
	public void testRetrieveMassUploadTemplateList_defect12265() throws Exception {
		MassUploadTemplateContract contract = new MassUploadTemplateContract();
		contract.setStatus("Open");
		contract.setMdmId("151064821");
		contract.setMdmLevel("Global");
		
		Map<String,Object> sortCriteria = new HashMap<String,Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("order.startDate", "03/01/2014 13:00:00");
		filterCriteria.put("order.endDate", "04/01/2014 13:00:00");
		contract.setFilterCriteria(filterCriteria);
		
		MassUploadTemplateResult result = service.retrieveMassUploadTemplateList(contract);
		
		List<MassUploadTemplateOrderLineItem> items = result.getOrderLineItemsList();
		
		System.out.println("Size: " + items.size());
		
	}
	
	@Test
	public void testRetrieveMassUploadTemplateList_Prod_Exception() throws Exception {
		MassUploadTemplateContract contract = new MassUploadTemplateContract();
		contract.setStatus("Open");
		contract.setMdmId("65500");
		contract.setMdmLevel("Legal");
		
		Map<String,Object> sortCriteria = new HashMap<String,Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("order.startDate", "06/29/2014 13:00:00");
		filterCriteria.put("order.endDate", "07/30/2014 13:00:00");
		filterCriteria.put("status", "Shipped");
		filterCriteria.put("customerAccount", "dsg");
		contract.setFilterCriteria(filterCriteria);
		contract.setMassUploadRequest(true);
		
		MassUploadTemplateResult result = service.retrieveMassUploadTemplateList(contract);
		
		List<MassUploadTemplateOrderLineItem> items = result.getOrderLineItemsList();
		
		System.out.println("Size: " + items.size());
		
	}
	
	@Test
	public void testRetrieveMassUploadTemplateList_defect12265_2() throws Exception {
		MassUploadTemplateContract contract = new MassUploadTemplateContract();
		contract.setStatus("Open");
		contract.setMdmId("151064821");
		contract.setMdmLevel("Global");
		
		Map<String,Object> sortCriteria = new HashMap<String,Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("order.startDate", "03/03/2014 09:00:00");
		filterCriteria.put("order.endDate", "04/03/2014 08:00:00");
		contract.setFilterCriteria(filterCriteria);
		
		MassUploadTemplateResult result = service.retrieveMassUploadTemplateList(contract);
		
		List<MassUploadTemplateOrderLineItem> items = result.getOrderLineItemsList();
		
		System.out.println("Size: " + items.size());
		
	}
	
	
	@Test
	public void testRetrieveMassUploadTemplateList_defect12265_3() throws Exception {
		MassUploadTemplateContract contract = new MassUploadTemplateContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("151064821");
		
		Map<String,Object> sortCriteria = new HashMap<String,Object>();
		sortCriteria.put("createdDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("order.startDate", "03/03/2014 13:00:00");
		filterCriteria.put("order.endDate", "04/03/2014 13:00:00");
		contract.setFilterCriteria(filterCriteria);
		
		MassUploadTemplateResult result = service.retrieveMassUploadTemplateList(contract);
		
		List<MassUploadTemplateOrderLineItem> items = result.getOrderLineItemsList();
		
		System.out.println("Size: " + items.size());
		
		for (MassUploadTemplateOrderLineItem item : items) {
			System.out.println(item.getShipments().size());
		}
		
	}


}
