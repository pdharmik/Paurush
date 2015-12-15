package com.lexmark.service.impl.real.meterreadService;

import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBase;
import com.lexmark.service.impl.real.domain.AssetReadingLatestBase;
import com.lexmark.service.impl.real.domain.MeterReadBase;
import com.lexmark.service.impl.real.domain.MeterReadStatus;
import com.lexmark.util.LangUtil;

public class AmindContractedMeterReadConversionUtil {

	public static List<Asset> convertMeterReadListtoAssetList(List<MeterReadBase> meterReadList,
			Set<String> favoriteSet) {

		List<Asset> assetList = new ArrayList<Asset>();

		if (meterReadList == null) {
			return assetList;
		}

		for (MeterReadBase meterInstance : meterReadList) {
			Asset asset = new Asset();
			String assetId = meterInstance.getAssetId();
			
			asset.setAssetId(assetId);
			asset.setAssetTag(meterInstance.getAssetTag());
			asset.setDeviceTag(meterInstance.getDeviceTagCustomer());
			asset.setHostName(meterInstance.getHostName());
			asset.setIpAddress(meterInstance.getIpAddress());
			asset.setProductTLI(meterInstance.getProductTLI());
			asset.setMacAddress(meterInstance.getMacAddress());
			if (LangUtil.isBlank(meterInstance.getDescriptionLocalLang()) // changes here for 13403
					&& LangUtil.isBlank(meterInstance.getCustomerReportingName())) {
				asset.setDescriptionLocalLang(meterInstance.getMdmModel());
			} else {
				asset.setDescriptionLocalLang(meterInstance.getCustomerReportingName());
				if (LangUtil.isNotBlank(meterInstance.getDescriptionLocalLang())) {
					asset.setDescriptionLocalLang(meterInstance
							.getDescriptionLocalLang());
				}
			}
			convertMeasurementCharacteristicList(asset , meterInstance.getMeasurementCharacteristics());
			asset.setSerialNumber(meterInstance.getSerialNumber());
			asset.setModelNumber(meterInstance.getModelNumber());
			
		    GenericAddress physicalLocationAddress = new GenericAddress();
		    physicalLocationAddress.setPhysicalLocation1(meterInstance.getPhysicalLocation1());
		    physicalLocationAddress.setPhysicalLocation2(meterInstance.getPhysicalLocation2());
		    physicalLocationAddress.setPhysicalLocation3(meterInstance.getPhysicalLocation3());
		    asset.setPhysicalLocationAddress(physicalLocationAddress);
			
		    asset.setProductLine(meterInstance.getProductLine());

			asset.setInstallAddress(convertInstallAddress(meterInstance));
			asset.setAssetContact(convertAssetContact(meterInstance));

			if ((favoriteSet != null && favoriteSet.contains(assetId))
					|| (meterInstance instanceof AccountAssetFavorites)) {
				asset.setUserFavoriteFlag(true);
			}

			assetList.add(asset);
		}

		return assetList;
	}

	public static List<com.lexmark.domain.MeterReadStatus> convertMeterReadStatusDotoMeterReadStatus(
			List<MeterReadStatus> attachmentList) {

		List<com.lexmark.domain.MeterReadStatus> statusList = new ArrayList<com.lexmark.domain.MeterReadStatus>();
		
		if (attachmentList == null) {
			return statusList;
		}

		for (MeterReadStatus statusDo : attachmentList) {
			com.lexmark.domain.MeterReadStatus status = new com.lexmark.domain.MeterReadStatus();

			StringBuilder fileName = new StringBuilder();
			fileName.append(statusDo.getFileName());
			String fileExt = statusDo.getFileExt();
			if (isNotEmpty(fileExt)) {
				fileName.append(".");
				fileName.append(fileExt);
			}
			status.setAttachmentName(fileName.toString());
			status.setComment(statusDo.getComments());
			status.setCompletedOn(statusDo.getCompletedOn());
			status.setSubmittedOn(statusDo.getSubmittedOn());
			status.setSize(statusDo.getFileSize());
			status.setStatus(statusDo.getStatus());
			statusList.add(status);

		}

		return statusList;
	}

	private static void convertMeasurementCharacteristicList(Asset asset,
			List<AssetMeasurementCharacteristicsBase> characteristicList) {
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		if (characteristicList == null || asset == null) {
			return;
		}
		for (AssetMeasurementCharacteristicsBase measurementCharacteristic : characteristicList) {
			if (measurementCharacteristic == null || measurementCharacteristic.getAssetReading() == null) {
				continue;
			}
			
			ArrayList<AssetReadingLatestBase> readingList = measurementCharacteristic.getAssetReading();
			PageCounts pageCount = new PageCounts();
			pageCount.setName(measurementCharacteristic.getName());
			for (AssetReadingLatestBase reading : readingList) {
				Date maxDate = null;
				Date readingDate = reading.getTimestamp();
				if (maxDate == null || (readingDate != null && maxDate.before(readingDate))) {
					maxDate = readingDate;
					pageCount.setCount(reading.getReading());
					pageCount.setDate(readingDate.toString());
				}
			}
			pageCounts.add(pageCount);
		}
		asset.setPageCounts(pageCounts);
		
	}




	private static GenericAddress convertInstallAddress(MeterReadBase meterInstance) {
		GenericAddress installAddress = new GenericAddress();
		installAddress.setAddressId(meterInstance.getAddressId());
		installAddress.setAddressLine1(meterInstance.getAddress1());
		installAddress.setAddressLine2(meterInstance.getAddress2());
		installAddress.setAddressLine3(meterInstance.getAddress3());
		installAddress.setAddressName(meterInstance.getAddressName());
		installAddress.setCity(meterInstance.getInstallCity());
		installAddress.setCountry(meterInstance.getInstallCountry());
		installAddress.setPostalCode(meterInstance.getInstallPostalCode());
		installAddress.setProvince(meterInstance.getInstallProvince());
		installAddress.setState(meterInstance.getInstallState());
		installAddress.setOfficeNumber(meterInstance.getOfficeNumber());
		installAddress.setCounty(meterInstance.getCounty());
	    installAddress.setDistrict(meterInstance.getDistrict());
		return installAddress;
	}

	private static AccountContact convertAssetContact(MeterReadBase meterInstance) {
		AccountContact assetContact = new AccountContact();
		assetContact.setFirstName(meterInstance.getContactFirstName());
		assetContact.setLastName(meterInstance.getContactLastName());
		assetContact.setWorkPhone(meterInstance.getContactPhone());
		assetContact.setEmailAddress(meterInstance.getContactEmail());
		return assetContact;
	}

	private AmindContractedMeterReadConversionUtil() {
	}
}
