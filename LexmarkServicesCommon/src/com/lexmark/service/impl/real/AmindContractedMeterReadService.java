package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadConversionUtil.convertMeterReadStatusDotoMeterReadStatus;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.buildAssetMeterReadFileName;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.buildAssetMeterReadSearchExpression;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.buildCharacteristicsSearchExpression;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.buildMeterReadStatusSearchExpression;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.checkMeterReadStatusFile;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.checkUpdateAssetMeterReadContract;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.queryAttachment;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.queryCharacteristicsList;
import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadServiceUtil.queryEmployeeList;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.IAttachmentManager;
import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.PageCounts;
import com.lexmark.result.AssetMeterReadResult;
import com.lexmark.result.MeterReadAssetListResult;
import com.lexmark.result.MeterReadStatusFileResult;
import com.lexmark.result.MeterReadStatusListResult;
import com.lexmark.result.UpdateAssetMeterReadResult;
import com.lexmark.service.api.MeterReadService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.AccountAssetReadingDo;
import com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBCDo;
import com.lexmark.service.impl.real.domain.ImportEmployeeDo;
import com.lexmark.service.impl.real.domain.MeterReadStatus;
import com.lexmark.service.impl.real.meterreadService.MeterReadAssetListService;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelPropertySet;

/**
 * do-classes:
 * AccountAssetReadingDo: "do-accountassetreadingdo-mapping.xml"
 * ImportEmployeeDo: "do-importEmployeeDo-mapping.xml"
 * AssetMeasurementCharacteristicsBCDo: "do-assetmeasurementcharacteristicsbcdo-mapping.xml"
 * AccountAssetFavorites: "do-assetFavorites-mapping.xml"
 * MeterReadStatus: "do-meterreadstatus-mapping.xml"
 */
public class AmindContractedMeterReadService extends AmindSiebelCrmService implements MeterReadService {

	public static final String NAME_COLOR = "Color";
	public static final String NAME_LTPC = "LTPC";
	public static final String NAME_MONO = "Mono";
	public static final String A3_COLOR = "A3 Color";
	public static final String A3_LTPC = "A3 LTPC";
	public static final String A4_COLOR = "A4 Color";
	public static final String A4_LTPC = "A4 LTPC";
	public static final String SCANS = "Scans";
	public static final String FAX = "Fax";
	
	
	private static final Map<String,String> PORTAL_METER_READ_TYPES_NAMES = populatePortalMeterReadTypes();

	private static final Map<String, String> BO_FIELD_MAP = populateBOFieldMap();
	private static final Map<String, String> FAV_FIELD_MAP = populateFavFieldMap();
	private static final Map<String, String> ATTACHMENT_FIELD_MAP = populateAttachmentFieldMap();

	/**
	 * TESTS: MeterReadImportAssetTest.class
	 */
	@Override
	public AssetMeterReadResult importAssetMeterRead(AssetMeterReadContract contract) throws Exception {
		logger.debug("[IN] importAssetMeterRead");

		AssetMeterReadResult result = new AssetMeterReadResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			session = crmSessionHandle.acquireMultiple();
			IDataManager dataManager = session.getDataManager();
			IAttachmentManager attachmentManager = session.getAttachmentManager();
			
			// query Employee Bc for Login = "PORTINTG"
			String searchSpec = buildAssetMeterReadSearchExpression();
			List<ImportEmployeeDo> employeeList = queryEmployeeList(dataManager, searchSpec);

			if (isNotEmpty(employeeList)) {
				String fileName = buildAssetMeterReadFileName(contract);
				
				String attId = attachmentManager.createAttachment(employeeList.get(0).getId(),
						fileName, null, contract.getFileStream(), ATTACHMENT_FIELD_MAP);

				callBusinessService(session.getSiebelBusServiceProxy(), attId);

				result.setUpDateSuccess(true);

			} else {
				result.setUpDateSuccess(false);
			}
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("importAssetMeterRead failed", e);
		} finally {
			crmSessionHandle.releaseMultipleSession(session);
		}

		logger.debug("[OUT] importAssetMeterRead");
		return result;
	}
	
	/**
	 * TESTS: MeterReadAssetListFilterTest.class, MeterReadAssetListSearchTest.class,
	 * MeterReadAssetListSortTest.class, MeterReadAssetListPaginationTest.class,
	 * MeterReadAssetFavoriteListTest.class
	 */
	@Override
	public MeterReadAssetListResult retrieveMeterReadAssetList(MeterReadAssetListContract contract)
			throws Exception {
		logger.debug("[IN] retrieveMeterReadAssetList");

		MeterReadAssetListResult meterReadResult = new MeterReadAssetListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		Session chldSession = null;
		try {
		    
            MeterReadAssetListService service = new MeterReadAssetListService(contract, BO_FIELD_MAP,
                    FAV_FIELD_MAP);
            service.checkRequiredFields();
		    
			session = crmSessionHandle.acquireMultiple();
			chldSession= getStatelessSessionFactory().attachSession();
			service.setSession(session);
			service.setChldSession(chldSession);
			service.buildSearchExpression();
			
			meterReadResult.setAccountAssets(service.queryAndGetResultList());
			meterReadResult.setTotalCount(service.processTotalCount());
			
		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveMeterReadAssetList failed", e);
		} finally {
			 AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
			 AmindServiceUtil.releaseSession(chldSession);
		}

		logger.debug("[OUT] retrieveMeterReadAssetList");
		return meterReadResult;
	}

	/**
	 * Inserts new meter read readings. First searches for the characteristics
	 * defined for the specified asset and then inserts the readings under the
	 * found characteristics.
	 * 
	 * @param contract
	 *            parameters used to perform the insert
	 * @return result always returns true
	 * 
	 * TESTS: MeterReadUpdateFavoriteTest.class
	 */
	@Override
	public UpdateAssetMeterReadResult updateAssetMeterRead(UpdateAssetMeterReadContract contract)
			throws Exception {
		logger.debug("[IN] updateAssetMeterRead");

		UpdateAssetMeterReadResult result = new UpdateAssetMeterReadResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
		    checkUpdateAssetMeterReadContract(contract);
		    
			session = crmSessionHandle.acquireMultiple();
			IDataManager dataManager = session.getDataManager();

			String searchSpec = buildCharacteristicsSearchExpression(contract);
			List<AssetMeasurementCharacteristicsBCDo> characteristicList = queryCharacteristicsList(
					dataManager, searchSpec);
			
			List<PageCounts> pageCounts = contract.getAsset().getPageCounts();
			
			for (AssetMeasurementCharacteristicsBCDo characteristic : characteristicList) {
				
				String name = characteristic.getName();
				
				PageCounts pageCount = populatePageCount(pageCounts, PORTAL_METER_READ_TYPES_NAMES.get(name));
				boolean countIsNotEmpty = pageCountIsEmpty(pageCount);
				
				if (countIsNotEmpty) {
					
					int newCount = populatePageCountsCount(pageCount.getCount(), countIsNotEmpty);
					Date date = populatePageCountsDate(pageCount);
					
					AccountAssetReadingDo reading = makeAccountAssetReadingDo(date, newCount, characteristic);
					createAccountAssetReadingDo(dataManager, reading, newCount);
					
				} 
			}

		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("updateAssetMeterRead failed", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle,session);
		}
		logger.debug("[OUT] updateAssetMeterRead");
		result.setResult(true);
		return result;
	}

	/**
	 * TESTS: MeterReadStatusFile.class
	 */
	@Override
	public MeterReadStatusFileResult retrieveMeterReadStatusFile(MeterReadStatusContract contract)
			throws Exception {
		logger.debug("[IN] retrieveMeterReadStatusFile");
		MeterReadStatusFileResult fileResult = new MeterReadStatusFileResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
		    
			session = crmSessionHandle.acquireMultiple();
			String searchSpec = buildAssetMeterReadSearchExpression();
			logger.debug("searchSpec: " + searchSpec);
			List<ImportEmployeeDo> employeeList = queryEmployeeList(session.getDataManager(), searchSpec);
			String userFileName = contract.getUserFileName().replaceAll("[(|)]", "-");
			checkMeterReadStatusFile(contract, employeeList);
			FileInputStream fileStream = (FileInputStream) session.getAttachmentManager().retrieveAttachment(
					employeeList.get(0).getId(), userFileName);
			
			logger.debug("fileStream: " + fileStream);
			fileResult.setFileStream(fileStream);

		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveMeterReadStatusFile failed.", e);
		} finally {
			AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);
		}
		
		logger.debug("[OUT] retrieveMeterReadStatusFile");
		return fileResult;
	}

	/**
	 * TESTS: MeterReadStatusListTest.class
	 */
	@Override
	public MeterReadStatusListResult retrieveMeterReadStatusList(MeterReadStatusContract contract)
			throws Exception {

		logger.debug("[IN] retrieveMeterReadStatusList");

		MeterReadStatusListResult meterReadResult = new MeterReadStatusListResult();
		AmindCrmSessionHandle crmSessionHandle = (AmindCrmSessionHandle) contract.getSessionHandle();
		Session session = null;
		try {
			session = crmSessionHandle.acquireMultiple();
			String searchSpec = buildMeterReadStatusSearchExpression(contract);
			logger.debug("searchSpec: "+searchSpec);
			IAttachmentManager attachmentManager = session.getAttachmentManager();
			List<MeterReadStatus> meterReadStatusList = queryAttachment(attachmentManager, searchSpec);

			meterReadResult.setMeterReadStatusList(convertMeterReadStatusDotoMeterReadStatus(meterReadStatusList));

		} catch (Exception e) {
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveMeterReadStatusList Failed.", e);
		} finally {
		    AmindServiceUtil.releaseAmindCrmMultipleSessionHandle(crmSessionHandle, session);	
		}
		
		logger.debug("[OUT] retrieveMeterReadStatusList");
		return meterReadResult;
	}

	private AccountAssetReadingDo makeAccountAssetReadingDo(Date meterReadDate, int readingValue,
			AssetMeasurementCharacteristicsBCDo characteristic) {

		int prevReadingValue = 0;
		String latestReading = characteristic.getLatestReading();
		if (isNotEmpty(latestReading)) {
			prevReadingValue = Integer.parseInt(latestReading);
		}
		AccountAssetReadingDo reading = new AccountAssetReadingDo();
		reading.setAssetMeasurementId(characteristic.getId());
		reading.setReading(Integer.toString(readingValue));
		reading.setTimestamp(meterReadDate);
		if (readingValue < prevReadingValue) {
			reading.setExceptionFlag(true);
		}
		reading.setCompleteFlag(true);
		return reading;
	}

	private void createAccountAssetReadingDo(IDataManager dataManager, AccountAssetReadingDo reading,
			int readingValue) {
		dataManager.create(reading);
	}

	private void callBusinessService(SiebelBusinessServiceProxy proxy, String attId) {
		SiebelPropertySet input = new SiebelPropertySet();
		SiebelPropertySet child = new SiebelPropertySet();
		input.setProperty("Component", "WfProcMgr");
		input.setProperty("DelAmount", "2");
		input.setProperty("DelUnits", "WEEKS");
		input.setProperty("Method", "RunProcess");
		input.setProperty("Mode", "DirectDb");
		child.setProperty("ProcessName", "LXK UP LS Import");
		child.setProperty("AttachmentId", attId);
		input.addChild(child);
		proxy.InvokeMethod("Server Requests", "SubmitRequest", input);
	}

	private static Map<String, String> populateFavFieldMap() {
		Map<String, String> favFieldMap = new HashMap<String, String>();
		favFieldMap.put("chlNodeId", null);
		favFieldMap.put("serialNumber", "Serial Number");
		favFieldMap.put("modelNumber", "MTM Name");
		favFieldMap.put("hostName", "Host Name");
		favFieldMap.put("ipAddress", "IP Address");
		favFieldMap.put("deviceTag", "Device Tag Customer");
		favFieldMap.put("productLine", "LXK C Customer Reporting Name");
		favFieldMap.put("assetTag", "Owner Asset Number");
		favFieldMap.put("installAddress.addressLine1", "Install Address Line 1");
		favFieldMap.put("installAddress.city", "Install Address City");
		favFieldMap.put("installAddress.province", "Install Address Province");
		favFieldMap.put("installAddress.state", "Install Address State");
		favFieldMap.put("installAddress.country", "Install Address Country");
		favFieldMap.put("installAddress.postalCode", "Install Address Zip");
		favFieldMap.put("physicalLocation1", "Physical Location 1");
		favFieldMap.put("physicalLocation2", "Physical Location 2");
		favFieldMap.put("physicalLocation3", "Physical Location 3");
		favFieldMap.put("contactId", "Contact Id");
		//favFieldMap.put("parentChain", "LXK SW CHL Parent Chain");
		favFieldMap.put("installAddress.addressName", "Install Address Name");
		favFieldMap.put("macAddress", "MAC Address");
		favFieldMap.put("productTLI", "Product Name");
		favFieldMap.put("assetContact.firstName", "Primary Contact First Name");
		favFieldMap.put("assetContact.lastName", "Primary Contact Last Name");
		favFieldMap.put("assetContact.emailAddress", "Primary Contact Email Address");
		favFieldMap.put("assetContact.workPhone", "Primary Contact Work Phone");
		favFieldMap.put("installAddress.county", "LXK MPS County");
		favFieldMap.put("installAddress.officeNumber", "LXK MPS Office");
		favFieldMap.put("installAddress.district", "LXK MPS District");
		return favFieldMap;
	}

	private static Map<String, String> populateBOFieldMap() {
		Map<String, String> boFieldMap = new HashMap<String, String>();
		boFieldMap.put("chlNodeId", null);
		boFieldMap.put("serialNumber", "Serial Number");
		boFieldMap.put("modelNumber", "LXK C MTM Name");
		boFieldMap.put("hostName", "Host Name");
		boFieldMap.put("ipAddress", "IP Address");
		boFieldMap.put("deviceTag", "Device Tag Customer");
		boFieldMap.put("productLine", "LXK C Assignment Product Line");
		boFieldMap.put("assetTag", "Owner Asset Number");
		boFieldMap.put("installAddress.addressLine1", "LXK UP Install Address Line 1");
		boFieldMap.put("installAddress.city", "Install City");
		boFieldMap.put("installAddress.province", "Install Province");
		boFieldMap.put("installAddress.state", "Install State");
		boFieldMap.put("installAddress.country", "Install Country");
		boFieldMap.put("installAddress.postalCode", "Install Postal Code");
		boFieldMap.put("physicalLocation1", "Physical Location 1");
		boFieldMap.put("physicalLocation2", "Physical Location 2");
		boFieldMap.put("physicalLocation3", "Physical Location 3");
		boFieldMap.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
		boFieldMap.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
		boFieldMap.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
		boFieldMap.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		boFieldMap.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		boFieldMap.put("accountTransFlag", "LXK L5 Account Transactable Flag");
		boFieldMap.put("contactId", "Contact Id");
		boFieldMap.put("parentChain", "LXK SW Covered Asset CHL Parent Chain");
		boFieldMap.put("installAddress.addressName", "Install Address Name");
		boFieldMap.put("macAddress", "MAC Address");
		boFieldMap.put("productTLI", "Product Name");
		boFieldMap.put("assetContact.firstName", "LXK SW Primary Contact First Name");
		boFieldMap.put("assetContact.lastName", "LXK SW Primary Contact Last Name");
		boFieldMap.put("assetContact.emailAddress", "LXK SW Primary Contact Email");
		boFieldMap.put("assetContact.workPhone", "LXK SW Primary Contact Work Phone");
		boFieldMap.put("installAddress.county", "Install County");
		boFieldMap.put("installAddress.officeNumber", "Install Address House #");
		boFieldMap.put("installAddress.district", "Install District");
		return boFieldMap;
	}

	private static Map<String, String> populateAttachmentFieldMap() {
		Map<String, String> attachmentFieldMap = new HashMap<String, String>();
		attachmentFieldMap.put("LXK SD ImportStatFlg", "Y");
		attachmentFieldMap.put("LXK UP Status", "In Progress");
		attachmentFieldMap.put("LXM Attachment Classification", "Meter Read");
		return attachmentFieldMap;
	}
	
	
	private static Map<String, String> populatePortalMeterReadTypes() {
		Map<String, String> portalMeterReadTypes = new HashMap<String, String>();
		portalMeterReadTypes.put("Color", "Color");
		portalMeterReadTypes.put("LTPC", "LTPC");
		portalMeterReadTypes.put("Mono", "Mono");
		portalMeterReadTypes.put("A3 Color", "A3Color");
		portalMeterReadTypes.put("A3 LTPC", "A3LTPC");
		portalMeterReadTypes.put("A4 Color", "A4Color");
		portalMeterReadTypes.put("A4 LTPC", "A4LTPC");
		portalMeterReadTypes.put("PGS_SCAN_COPY", "PGS_SCAN_COPY");
		portalMeterReadTypes.put("PGS_SCAN_FAX", "PGS_SCAN_FAX");
		portalMeterReadTypes.put("PGS_SCAN_NETWORK", "PGS_SCAN_NETWORK");
		portalMeterReadTypes.put("PGS_SCAN_USB", "PGS_SCAN_USB");
		portalMeterReadTypes.put("Total Scans", "TotalScans");
		portalMeterReadTypes.put("Letter Color", "LetterColor");
		portalMeterReadTypes.put("Letter LTPC", "LetterLTPC");
		portalMeterReadTypes.put("Letter Mono", "LetterMono");
		portalMeterReadTypes.put("A4 Mono", "A4Mono");
		portalMeterReadTypes.put("A5 Color", "A5Color");
		portalMeterReadTypes.put("A5 LTPC", "A5LTPC");
		portalMeterReadTypes.put("A5 Mono", "A5Mono");
		portalMeterReadTypes.put("A3 Mono", "A3Mono");
		portalMeterReadTypes.put("Legal Color", "LegalColor");
		portalMeterReadTypes.put("Legal LTPC", "LegalLTPC");
		portalMeterReadTypes.put("Legal Mono", "LegalMono");
		portalMeterReadTypes.put("Statement Color", "StatementColor");
		portalMeterReadTypes.put("Statement LTPC", "StatementLTPC");
		portalMeterReadTypes.put("Statement Mono", "StatementMono");
		portalMeterReadTypes.put("Tabloid Color", "TabloidColor");
		portalMeterReadTypes.put("Tabloid LTPC", "TabloidLTPC");
		portalMeterReadTypes.put("Tabloid Mono", "TabloidMono");
		portalMeterReadTypes.put("Black", "Black");
		portalMeterReadTypes.put("Cyan", "Cyan");
		portalMeterReadTypes.put("Scans", "Scans");
		portalMeterReadTypes.put("Software", "Software");
		
		return portalMeterReadTypes;
	}
	
	
	private PageCounts populatePageCount(List<PageCounts> pageCounts, String pageCountName) {
		
		for (PageCounts pageCount : pageCounts) {
			if(pageCount.getName().equalsIgnoreCase(pageCountName)) {
				return pageCount;
			}
		}
		
		return null;
	}
	
	private int populatePageCountsCount(String newCount, boolean countIsNotEmpty) {
		
		if (countIsNotEmpty && !"undefined".equalsIgnoreCase(newCount)) {
			return Integer.parseInt(newCount);
		}
		
		return 0;
	}
	
	private Date populatePageCountsDate(PageCounts pageCount) {
		
		if(pageCount != null && LangUtil.isNotEmpty(pageCount.getDate())) {
			return LangUtil.convertStringToGMTDate(pageCount.getDate());
		}
		
		else {
			throw new NullPointerException("Date can not be null!");
		}
	}
	
	private boolean pageCountIsEmpty(PageCounts pageCount) {
		if(pageCount == null || LangUtil.isEmpty(pageCount.getCount())) {
			return false;
		}
		
		return true;
	}
}
