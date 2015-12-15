package com.lexmark.service.impl.real.util;

import static java.util.ResourceBundle.*;
import static org.apache.commons.lang.StringUtils.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttachmentProperties {
	//private static final Log logger = LogFactory.getLog(AttachmentProperties.class);
	private static final Logger logger = LogManager.getLogger(AttachmentProperties.class);

	private String busObjectName;
	private String busCompName;
	private String siebelFileDestination;
	private String fileUploadDestination;
	private String fileDownloadDestination;
	private String bcFileFieldName;
	private String bcParentFieldName;
	private String bcFileNameExtensionFieldName;
	private String bcDescription;
	private String bcVisibility;
	private String bcDeferFlag;
	private String bcFileSourceType;
	private String bcDockFlag;
	private String bcUploadFlag;
	private String bcDockStarFlag;
	private String bcDisplayName;
	
	
	public AttachmentProperties(String requestType) {
		ResourceBundle bundle = getBundle("attachment");
		if (bundle == null) {
			throw new RuntimeException("resource bundle is null!");
		}
		if ("Claim Create".equalsIgnoreCase(requestType)) {
			setAttachmentPropertiesForServiceRequest(bundle);
		} else if ("Service Request".equalsIgnoreCase(requestType)) {
			setAttachmentPropertiesForServiceRequest(bundle);
		} else if ("Claim Update".equalsIgnoreCase(requestType)) {
			setAttachementPropertiesForClaimUpdate(bundle);
		} else if ("Hardware Debrief".equalsIgnoreCase(requestType)) {
			setAttachementPropertiesForHardwareDebrief(bundle);
		} else if ("Sr Update".equalsIgnoreCase(requestType)) {
			setAttachementPropertiesForServiceRequestUpdate(bundle);
		} else {
			throw new IllegalArgumentException(
					"RequestType should be Claim Create, Service Request, Sr Update, Claim Update or Hardware Debrief");
		}
	}

	private void setAttachmentPropertiesForServiceRequest(ResourceBundle bundle) {
		logger.debug("In setAttachmentPropertiesForServiceRequest");
		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (isNotEmpty(key) && key.startsWith("ServiceRequest")) {
			String value = bundle.getString(key);
				if ("ServiceRequestBO".equals(key)) {
					busObjectName = value;
				} else if ("ServiceRequestBC".equals(key)) {
					busCompName = value;
				} else if ("ServiceRequestUploadFileDestination".equals(key)) {
					fileUploadDestination = value;
				} else if ("ServiceRequestDownloadFileDestination".equals(key)) {
					fileDownloadDestination = value;
					logger.debug("fileDownloadDestination = " + fileDownloadDestination);
				} else if ("ServiceRequestSiebelFileDestination".equals(key)) {
					siebelFileDestination = value;
					logger.debug("siebelFileDestination = " + siebelFileDestination);
				} else if ("ServiceRequestBCFileName".equals(key)) {
					bcFileFieldName = value;
					logger.debug("bcFileFieldName = " + bcFileFieldName);
				} else if ("ServiceRequestBCParentFieldName".equals(key)) {
					bcParentFieldName = value;
					logger.debug("bcParentFieldName = " + bcParentFieldName);
				} else if ("ServiceRequestBCFileNameExtensionFieldName".equals(key)) {
					bcFileNameExtensionFieldName = value;
					logger.debug("bcFileNameExtensionFieldName = " + bcFileNameExtensionFieldName);
				} else if ("ServiceRequestBCDescription".equals(key)) {
					bcDescription = value;
				} else if ("ServiceRequestBCVisibility".equals(key)) {
					bcVisibility = value;
				}
				else if ("ServiceRequestBCDeferFlag".equals(key)) {
					bcDeferFlag = value;
				}
				else if ("ServiceRequestBCFileSourceType".equals(key)) {
					bcFileSourceType = value;
				}
				else if ("ServiceRequestBCDockFlag".equals(key)) {
					bcDockFlag = value;
				}
				else if ("ServiceRequestBCUploadFlag".equals(key)) {
					bcUploadFlag = value;
				}
				else if ("ServiceRequestBCDockStarFlag".equals(key)) {
					bcDockStarFlag = value;
				}
				else if ("ServiceRequestBCDisplayFileName".equals(key)) {
					bcDisplayName = value;
				}
				else {
					logger.warn("unknown key: " + key + " with value: " + value);
				}
			}
		}
	}

	private void setAttachementPropertiesForHardwareDebrief(ResourceBundle bundle) {

		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (isNotEmpty(key) && key.startsWith("HardwareDebrief")) {
				String value = bundle.getString(key);
				if ("HardwareDebriefBO".equals(key)) {
					busObjectName = bundle.getString(key);
				} else if ("HardwareDebriefBC".equals(key)) {
					busCompName = value;
				} else if ("HardwareDebriefFileDestination".equals(key)) {
					fileUploadDestination = value;
					fileDownloadDestination = value;
				} else if ("HardwareDebriefSiebelFileDestination".equals(key)) {
					siebelFileDestination = value;
				} else if ("HardwareDebriefBCFileFieldName".equals(key)) {
					bcFileFieldName = value;
				} else if ("HardwareDebriefBCParentFieldName".equals(key)) {
					bcParentFieldName = value;
				} else if ("HardwareDebriefBcFileNameExtensionFieldName".equals(key)) {
					bcFileNameExtensionFieldName = value;
				} 
				else if ("HardwareDebriefBcVisibility".equals(key)) {
					bcVisibility = value;
				} 
				else {
					logger.warn("unknown key: " + key + " with value: " + value);
				}
			}
		}
	}
	
	private void setAttachementPropertiesForClaimUpdate(ResourceBundle bundle) {

		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (isNotEmpty(key) && key.startsWith("Claim")) {
				String value = bundle.getString(key);
				if ("ClaimBO".equals(key)) {
					busObjectName = bundle.getString(key);
				} else if ("ClaimBC".equals(key)) {
					busCompName = value;
				} else if ("ClaimFileDestination".equals(key)) {
					fileUploadDestination = value;
					fileDownloadDestination = value;
				} else if ("ClaimSiebelFileDestination".equals(key)) {
					siebelFileDestination = value;
				} else if ("ClaimBCFileFieldName".equals(key)) {
					bcFileFieldName = value;
				} else if ("ClaimBCParentFieldName".equals(key)) {
					bcParentFieldName = value;
				} else if ("ClaimBcFileNameExtensionFieldName".equals(key)) {
					bcFileNameExtensionFieldName = value;
				} else {
					logger.warn("unknown key: " + key + " with value: " + value);
				}
			}
		}
	}

	private void setAttachementPropertiesForServiceRequestUpdate(ResourceBundle bundle) {

		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (isNotEmpty(key) && key.startsWith("SrUpdate")) {
				String value = bundle.getString(key);
				if ("SrUpdateBO".equals(key)) {
					busObjectName = bundle.getString(key);
				} else if ("SrUpdateBC".equals(key)) {
					busCompName = value;
				} else if ("SrUpdateFileDestination".equals(key)) {
					fileUploadDestination = value;
					fileDownloadDestination = value;
				} else if ("SrUpdateSiebelFileDestination".equals(key)) {
					siebelFileDestination = value;
				} else if ("SrUpdateBCFileFiledName".equals(key)) {
					bcFileFieldName = value;
				} else if ("SrUpdateBCParentFieldName".equals(key)) {
					bcParentFieldName = value;
				} else if ("SrUpdateBcFileNameExtensionFieldName".equals(key)) {
					bcFileNameExtensionFieldName = value;
				} else {
					logger.warn("unknown key: " + key + " with value: " + value);
				}
			}
		}
	}
	
	public String getBusObjectName() {
		return busObjectName;
	}

	public String getBusCompName() {
		return busCompName;
	}

	public String getSiebelFileDestination() {
		return siebelFileDestination;
	}

	public String getFileUploadDestination() {
		return fileUploadDestination;
	}

	public String getBcFileFieldName() {
		return bcFileFieldName;
	}

	public String getBcParentFieldName() {
		return bcParentFieldName;
	}

	public String getBcFileNameExtensionFieldName() {
		return bcFileNameExtensionFieldName;
	}

	public String getFileDownloadDestination() {
		return fileDownloadDestination;
	}

	public String getBcDescription() {
		return bcDescription;
	}

	public void setBcVisibility(String bcVisibility) {
		this.bcVisibility = bcVisibility;
	}

	public String getBcVisibility() {
		return bcVisibility;
	}

	public String getBcDeferFlag() {
		return bcDeferFlag;
	}

	public void setBcDeferFlag(String bcDeferFlag) {
		this.bcDeferFlag = bcDeferFlag;
	}

	public String getBcFileSourceType() {
		return bcFileSourceType;
	}

	public void setBcFileSourceType(String bcFileSourceType) {
		this.bcFileSourceType = bcFileSourceType;
	}

	public String getBcDockFlag() {
		return bcDockFlag;
	}

	public void setBcDockFlag(String bcDockFlag) {
		this.bcDockFlag = bcDockFlag;
	}

	public String getBcUploadFlag() {
		return bcUploadFlag;
	}

	public void setBcUploadFlag(String bcUploadFlag) {
		this.bcUploadFlag = bcUploadFlag;
	}

	public String getBcDockStarFlag() {
		return bcDockStarFlag;
	}

	public void setBcDockStarFlag(String bcDockStarFlag) {
		this.bcDockStarFlag = bcDockStarFlag;
	}

	public void setBcFileFieldName(String bcFileFieldName) {
		this.bcFileFieldName = bcFileFieldName;
	}

	public void setBcParentFieldName(String bcParentFieldName) {
		this.bcParentFieldName = bcParentFieldName;
	}

	public void setBcFileNameExtensionFieldName(String bcFileNameExtensionFieldName) {
		this.bcFileNameExtensionFieldName = bcFileNameExtensionFieldName;
	}

	public void setBcDescription(String bcDescription) {
		this.bcDescription = bcDescription;
	}

	public void setBcDisplayName(String bcDisplayName) {
		this.bcDisplayName = bcDisplayName;
	}

	public String getBcDisplayName() {
		return bcDisplayName;
	}
}
