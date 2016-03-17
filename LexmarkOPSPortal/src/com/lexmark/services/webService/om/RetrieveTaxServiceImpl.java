package com.lexmark.services.webService.om;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.TaxContract;
import com.lexmark.domain.Price;
import com.lexmark.result.TaxResult;
import com.lexmark.services.api.om.RetrieveTaxService;
import com.lexmark.taxCalc.DocumentMetaData;
import com.lexmark.taxCalc.Header;
import com.lexmark.taxCalc.LineInformation;
import com.lexmark.taxCalc.LineInformation2;
import com.lexmark.taxCalc.TaxCalculateWS;
import com.lexmark.taxCalc.TaxCalculateWSLocator;
import com.lexmark.taxCalc.TaxCalculateWS_PortType;
import com.lexmark.taxCalc.TaxCalculationWSInput;
import com.lexmark.taxCalc.TaxCalculationWSInput2;
import com.lexmark.taxCalc.TaxCalculationWSOutput;
import com.lexmark.taxCalc.TaxCalculationWSOutput2;
import com.lexmark.taxCalc.TaxData;
import com.lexmark.taxCalc.holders.TaxCalculationWSOutputHolder;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class RetrieveTaxServiceImpl implements RetrieveTaxService{

	private static final Logger LOGGER = LogManager.getLogger(RetrieveTaxServiceImpl.class);
	
	private String address;
	private String senderId; 
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	private String accountType;
	
	@Override
	public TaxResult retrieveTaxList(TaxContract contract)
			throws Exception {
		
		TaxResult taxResult = new TaxResult();
		List<Price> taxInfoList = new ArrayList<Price>();
		Map<String, Price> partMap = new HashMap<String, Price>(); //temporary Map
		
		TaxCalculateWS locator = new TaxCalculateWSLocator();
		LOGGER.debug("WSDL Endpoint Address is: " +  address );
		//TaxCalculateWS_PortType port = locator.getLXKTaxCalculationWS_webservices_taxCalculateWS_Port((new URL(getAddress())));
		TaxCalculateWS_PortType port = locator.getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port((new URL(getAddress())));
		
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
				
		String originalPartnerId = "";
		String originalPartnerName = "";
		String receiverName = "";
		//String senderName = getSenderName();
		//String synchOrAsynch = "asynch";
		//String returnDetail_x003F_ = "no";
		//String sourceSystem = "SERVICES WEB PORTAL";
		String debug="$null";
		String sourceSystem = "Web";
		
		DocumentMetaData documentMetaData  = new DocumentMetaData(sourceSystem, getSenderName(), originalPartnerId, originalPartnerName, receiverName, receiverName);
		
		TaxData taxData = new TaxData();
		Header header = new Header();
		
	
		header.setSalesOrganization(contract.getSalesOrganization());
		header.setCountry(contract.getCountry());
		header.setCity(contract.getCity());
		header.setRegion(contract.getRegion());
		header.setPostalCode(contract.getPostalCode());
		header.setAccountType(getAccountType());
		taxData.setHeader(header);
		
		int catalogSize = 0;
		for(Price lineInfo: contract.getLineInformationList()){	
			if(lineInfo.getPrice()==null || lineInfo.getPrice().equalsIgnoreCase("0")){
				continue;
			}
			catalogSize++;
		}
		
		LineInformation[] lineInformation = new LineInformation[catalogSize];
		
		int index = 0;
		for(Price lineInfo: contract.getLineInformationList()){	
			if(lineInfo.getPrice()==null || lineInfo.getPrice().equalsIgnoreCase("0")){
				continue;
			}
			LineInformation lineInfoElement = new LineInformation();
			//lineInfoElement.setSourceReferenceLineId("1234");
			lineInfoElement.setMaterialNumber(lineInfo.getPartNumber());
			lineInfoElement.setNetPrice(lineInfo.getPrice());	
			lineInfoElement.setSourceReferenceLineId(lineInfo.getSourceReferenceLineId());
			lineInformation[index] = lineInfoElement;
			index ++;
			
			partMap.put(lineInfo.getSourceReferenceLineId(), lineInfo);
		}
				
		
		
		
		taxData.setLineInformation(lineInformation);
		
		TaxCalculationWSInput2  taxCalculationWSInput2 = new TaxCalculationWSInput2(); 
		taxCalculationWSInput2.setDocumentMetaData(documentMetaData);
		taxCalculationWSInput2.setTaxData(taxData);
		
		TaxCalculationWSInput taxCalculationWSInput = new TaxCalculationWSInput();
		taxCalculationWSInput.setTaxCalculationWSInput(taxCalculationWSInput2);
		
		TaxCalculationWSOutputHolder taxCalculationWSOutputHolder = new TaxCalculationWSOutputHolder();
		StringHolder integrationResponse = new StringHolder();
		
		try{
			port.calculateTax(debug, taxCalculationWSInput, taxCalculationWSOutputHolder, integrationResponse);
			
			LOGGER.debug("After Service Call: ");
			TaxCalculationWSOutput taxCalculationWSOutput = taxCalculationWSOutputHolder.value ;
			TaxCalculationWSOutput2 taxCalculationWSOutput2 = taxCalculationWSOutput.getTaxCalculationWSOutput();
			
			LineInformation2[] outputLineInfo = taxCalculationWSOutput2.getTaxData().getLineInformation();
			LOGGER.debug("After Service Call: outputLineInfo:: "+ outputLineInfo);
			int outputLineLen = outputLineInfo.length;
			for(int i=0; i < outputLineLen; i++){
				
				LineInformation2 lineInfo = outputLineInfo[i];
				
				Price taxInfo= partMap.get(lineInfo.getSourceReferenceLineId());
				taxInfo.setTax(lineInfo.getTaxAmount().trim());
				partMap.remove(taxInfo);
				//partMap.put(lineInfo.getMaterialNumber(), taxInfo);
				taxInfoList.add(taxInfo);
				LOGGER.debug("After Service Call: Tax amt for 1st Line item:: " + outputLineInfo[i].getTaxAmount());
				
			}			
			taxResult.setLineInformationList(taxInfoList);
		}catch(Exception e){
			for(Price lineInfo: contract.getLineInformationList()){					
				Price taxInfo= partMap.get(lineInfo.getSourceReferenceLineId());
				taxInfo.setTax("Unavailable");
				partMap.remove(taxInfo);
				//partMap.put(lineInfo.getMaterialNumber(), taxInfo);
				taxInfoList.add(taxInfo);
				LOGGER.debug("Exception occured. Tax unavailable");				
			}
			taxResult.setLineInformationList(taxInfoList);
		}
		
		return taxResult;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
