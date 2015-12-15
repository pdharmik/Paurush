package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.result.MeterReadStatusFileResult;

@RunWith(Parameterized.class)
public class MeterReadStatusFile extends MeterReadServiceTestBase {

	private final MeterReadStatusContract contract = new MeterReadStatusContract();;

	@Parameters
	public static List<Object[]> fileNameParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "971966122~MeterReadList (1)~20110329012504025.csv" });
		// TODO add other filenames
		return list;
	}

	public MeterReadStatusFile(String userFileName) {
		contract.setUserFileName(userFileName);
	}

	@Test
	public void retrieveMeterReadStatusFile() throws Exception {
		contract.setSessionHandle(handle);
		MeterReadStatusFileResult result = service.retrieveMeterReadStatusFile(contract);

		assertNotNull("result is null!", result);
		FileInputStream stream = result.getFileStream();

		assertNotNull("file input stream is null!", stream);

		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		logger.debug("=======logging result stream=======");

		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			logger.debug(line);
		}
		logger.debug("=====end logging result stream======");
	}

}
