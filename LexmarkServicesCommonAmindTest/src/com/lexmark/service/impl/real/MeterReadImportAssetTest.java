package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.result.AssetMeterReadResult;

@RunWith(Parameterized.class)
public class MeterReadImportAssetTest extends MeterReadServiceTestBase {

	private final AssetMeterReadContract contract;

	@Parameters
	public static List<Object[]> filenameParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		// list.add(new Object[] {
		// "C:\\Documents and Settings\\Mansi\\My Documents\\Downloads\\LexmarkPCUploadTemplate_fi (2).csv",
		// "test23.csv" });
		list.add(new Object[] { "/home/pkozlov/tests/testfile.txt", "test1904.txt" });

		// TODO add other parameters

		return list;
	}

	public MeterReadImportAssetTest(String fileName, String userFileName) throws FileNotFoundException {
		contract = new AssetMeterReadContract();
		contract.setFileStream(new FileInputStream(fileName));
		contract.setUserFileName(userFileName);
	}

	@Test
	public void importMeterReadServiceFile() throws Exception {
		contract.setMdmId("236295");
		contract.setContactId("1-1LUIPWR");
		contract.setSessionHandle(handle);

		AssetMeterReadResult result = service.importAssetMeterRead(contract);
		assertNotNull("result is null!", result);
		assertTrue("result is false!", result.isUpDateSuccess());
	}
}
