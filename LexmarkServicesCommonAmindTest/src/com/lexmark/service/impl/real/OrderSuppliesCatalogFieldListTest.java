package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.CatalogListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.result.CatalogListResult;

@RunWith(Parameterized.class)
public class OrderSuppliesCatalogFieldListTest extends OrderSuppliesCatalogServiceTestBase {

	private final CatalogListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		
//		list.add(new Object[]{ "accountId", "agreementId", "productType", "productModel" });
		
		list.add(new Object[]{ "1-7823PB", null, null, null });
		list.add(new Object[]{ null, "1-7APY6T", null, null });
		list.add(new Object[]{ null, "1-7APY6T", "4=Med./Large Offce B/W Laser", null });
		list.add(new Object[]{ null, "1-7APY6T", "4=Med./Large Offce B/W Laser", "4061-410" });
		
		return list;
	}

	public OrderSuppliesCatalogFieldListTest(String accountId, String agreementId, String productType, String productModel) {
		contract = new CatalogListContract();
		contract.setAccountId(accountId);
		contract.setAgreementId(agreementId);
		contract.setProductType(productType);
		contract.setProductModel(productModel);
	}

	@Test
	public void testRetrieveCatalogFieldList() throws Exception {
		CatalogListResult result = service.retrieveCatalogFieldList(contract);
		List<ListOfValues> lovList = result.getLovList();

		for (ListOfValues lov : lovList) {
			logger.debug("name: " + lov.getName());
			logger.debug("value: " + lov.getValue());
		}
	}

}
