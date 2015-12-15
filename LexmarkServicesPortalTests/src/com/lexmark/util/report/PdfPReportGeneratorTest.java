package com.lexmark.util.report;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lexmark.domain.Asset;
import com.lexmark.domain.ServiceRequest;

public class PdfPReportGeneratorTest {
	
	private List<ServiceRequest> getServiceRequestsList() {
		List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();
		int totalCount = 0;
		String[] optionExchangeList = {"test","tst"};
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 10000; i++) {
			calendar.set(Calendar.YEAR, 2000+ i%11);
			ServiceRequest sr = new ServiceRequest();
			Asset asset = new Asset();
			asset.setSerialNumber("7923X9");
			asset.setModelNumber("Lexmark xd464dfe");
			asset.setPhysicalLocation1("Lexington.KY");
//			sr.setCarrier("FedEx");
			sr.setServiceRequestDate(new Date());
			if(i%2==1){
				sr.setServiceRequestStatus("Shiped");
			}else{
				sr.setServiceRequestStatus("Scheduled");
			}
//			sr.setShipDate(calendar.getTime());
//			sr.setTrackingNumber("LZ181609PT07198655");
//			sr.setOptionExchangeList(optionExchangeList);
			sr.setOptionExchangeOtherDescription("Exchange this printer for");
			sr.setProblemDescription("My printer is jammed");
			sr.setReferenceNumber("216" + Integer.toString(i));
//			sr.setRequestedService("exchange");
//			sr.setReturnCarrier("FedEx");
//			sr.setReturnShipDate(calendar.getTime());
			sr.setServiceRequestNumber("123456789" + Integer.toString(i));
			sr.setAsset(asset);
			serviceRequests.add(sr);
			totalCount++;
		}
		return serviceRequests;
	}
	
	@Test
	public void testGenerateReport() throws Exception {
//		FileOutputStream output = new FileOutputStream("report.pdf");
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		String[] headers = new String[]{"Request Date", "Request Number",
				"Serial Number", "Model Number","Physical Location",
				"Request Status"};

		
		String[] generatorPatterns = new String[]{"serviceRequestDate", "serviceRequestNumber",
				"asset.serialNumber", "asset.modelNumber","asset.physicalLocation1",
				"serviceRequestStatus"};

		
		PdfPReportGenerator generator = new PdfPReportGenerator(headers, generatorPatterns, getServiceRequestsList());
		generator.generate(output);
		output.close();
		Assert.assertTrue(output.size() > 0);
	}
}
