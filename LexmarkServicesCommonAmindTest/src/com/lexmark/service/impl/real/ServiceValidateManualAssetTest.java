package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.ManualAssetContract;
import com.lexmark.result.ManualAssetResult;

@RunWith(Parameterized.class)
public class ServiceValidateManualAssetTest extends RequestServiceStatelessBase {
	private final ManualAssetContract contract;
	private final boolean expected;
	private final String assertMessage;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		ManualAssetContract contract;

		contract = new ManualAssetContract();
		contract.setModelNumber("4062-43a");
		contract.setSessionHandle(null);
		list.add(new Object[] { contract, true, "Model only failed to validate" });

		contract = new ManualAssetContract();
		contract.setModelNumber("4062-43a");
		contract.setSessionHandle(null);
		contract.setProductTLI("20T1057");
		list.add(new Object[] { contract, true, "Model and product failed to validate" });

		contract = new ManualAssetContract();
		contract.setModelNumber("4062-43a");
		contract.setSessionHandle(null);
		contract.setProductTLI("    10A0030   ");
		list.add(new Object[] { contract, false, "Model and product failed to validate false" });

		return list;
	}

	public ServiceValidateManualAssetTest(ManualAssetContract contract, boolean expected, String assertMessage) {
		this.contract = contract;
		this.expected = expected;
		this.assertMessage = assertMessage;
	}

	@Test
	public void testValidateManualAsset() throws Exception {
		ManualAssetResult result = service.validateManualAsset(contract);
		assertEquals(assertMessage, expected, result.getResult().booleanValue());
	}

}
