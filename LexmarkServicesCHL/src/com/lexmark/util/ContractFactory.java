package com.lexmark.util;

import java.util.List;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DAUserListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.SaveServicesUserContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.contract.SiebelAccountListContract;

public class ContractFactory {
	
	public static LocationReportingHierarchyContract getCustomerHierarchyTreeContract(
			String mdmId, String mdmLevel, String chlNodeId) {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setChlNodeId(chlNodeId);
		return contract;
	}
	
	// create contract to retrieve chold node of provided nodeId
	public static LocationReportingHierarchyContract getCustomerHierarchyChildNodeContract(
			String selectedNodeId) {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		String mdmId = null;
		String mdmLevel = null;
		String chlNodeId = null;
		if (selectedNodeId.endsWith("_" + LexmarkConstants.MDM_LEVEL_GLOBAL)) {
			mdmId = selectedNodeId.replaceFirst("_" + LexmarkConstants.MDM_LEVEL_GLOBAL, "");
			mdmLevel = LexmarkConstants.MDM_LEVEL_GLOBAL;
		} else if (selectedNodeId.endsWith("_" + LexmarkConstants.MDM_LEVEL_DOMESTIC)) {
			mdmId = selectedNodeId.replaceFirst("_" + LexmarkConstants.MDM_LEVEL_DOMESTIC, "");
			mdmLevel = LexmarkConstants.MDM_LEVEL_DOMESTIC;
		} else if (selectedNodeId.endsWith("_" + LexmarkConstants.MDM_LEVEL_LEGAL)) {
			mdmId = selectedNodeId.replaceFirst("_" + LexmarkConstants.MDM_LEVEL_LEGAL, "");
			mdmLevel = LexmarkConstants.MDM_LEVEL_LEGAL;
		} else if (selectedNodeId.endsWith("_" + LexmarkConstants.MDM_LEVEL_ACCOUNT)) {
			mdmId = selectedNodeId.replaceFirst("_" + LexmarkConstants.MDM_LEVEL_ACCOUNT, "");
			mdmLevel = LexmarkConstants.MDM_LEVEL_ACCOUNT;
		} else if (selectedNodeId.endsWith("_" + LexmarkConstants.MDM_LEVEL_SIEBEL)) {
			mdmId = selectedNodeId.replaceFirst("_" + LexmarkConstants.MDM_LEVEL_SIEBEL, "");
			mdmLevel = LexmarkConstants.MDM_LEVEL_SIEBEL;
		} else {
			chlNodeId = selectedNodeId;
		}
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setChlNodeId(chlNodeId);
		return contract;
	}
	
	public static DAUserListContract getDAUserListContract(Integer operId, String jSessionNum) {
		DAUserListContract contract = new DAUserListContract();
		contract.setOperId(operId);
		contract.setJSessionNum(jSessionNum);
		return contract;
	}
	
	public static SiebelAccountListContract getSiebelAccountListContract(String mdmId, String mdmLevel){
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setNewQueryIndicator(true);
		return contract;
	}
	
	public static PartnerAccountListContract getPartnerAccountListContract(String mdmId, String mdmLevel){
		PartnerAccountListContract contract = new PartnerAccountListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		return contract;
	}
	
	public static SaveServicesUserContract getSaveServicesUserContract(String mdmId,
			String mdmLevel, String chlNodeId, List<String> userNumberList) {
		SaveServicesUserContract contract = new SaveServicesUserContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setChlNodeId(chlNodeId);
		contract.setUserNumberList(userNumberList);
		return contract;
	}

	public static ServicesUserContract getServicesUserContract(
			String userNumber) {
		ServicesUserContract contract = new ServicesUserContract();
		contract.setUserNumber(userNumber);
		return contract;
	}
}
