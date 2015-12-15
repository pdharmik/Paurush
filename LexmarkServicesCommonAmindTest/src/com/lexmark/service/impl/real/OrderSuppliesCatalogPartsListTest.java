package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.result.CatalogListResult;

@RunWith(Parameterized.class)
public class OrderSuppliesCatalogPartsListTest extends OrderSuppliesCatalogServiceTestBase {

	private final CatalogListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		//list.add(new Object[]{ "1-41HXJGF", "Product", "20T4000","Transparency", null});
		list.add(new Object[]{ "1-42PVCHI", "", "","", "34015HA"});
		return list;
	}//"12A5745"

	public OrderSuppliesCatalogPartsListTest( String agreementId, String productType, String productModel, String partType, String partNumber) {
		contract = new CatalogListContract();
		contract.setAgreementId(agreementId);
		contract.setProductType(productType);
		contract.setProductModel(productModel);
		contract.setPartType(partType);
		contract.setPartNumber(partNumber);
		contract.setSessionHandle(handle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(1);
	}

	@Test
	public void testRetrieveCatalogPartsList() throws Exception {
		CatalogListResult result = service.retrieveCatalogList(contract);
		List<OrderPart> partList = result.getPartsList();
		logger.debug("Count:" + result.getTotalCount());
		for (OrderPart orderPart : partList) {
		/*	logger.debug("name: " + orderPart.getPartNumber());
			logger.debug("value: " + orderPart.getPartDesc());
			logger.debug("Model: " + orderPart.getModel());
			logger.debug("Description" + orderPart.getDescription());*/
			//logger.debug("Product type" + orderPart.getPr);
			logger.debug("Part Number" +  orderPart.getPartNumber());
		}
	}

}
