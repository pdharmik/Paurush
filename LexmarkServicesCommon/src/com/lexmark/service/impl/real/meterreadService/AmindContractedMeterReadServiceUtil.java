package com.lexmark.service.impl.real.meterreadService;

import static com.lexmark.util.LangUtil.notNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import com.amind.data.service.IAttachmentManager;
import com.amind.data.service.IDataManager;
import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBCDo;
import com.lexmark.service.impl.real.domain.ImportEmployeeDo;
import com.lexmark.service.impl.real.domain.MeterReadStatus;

public class AmindContractedMeterReadServiceUtil {

	public static void checkUpdateAssetMeterReadContract(UpdateAssetMeterReadContract contract) {
		Asset asset = contract.getAsset();
		if (asset == null) {
			throw new IllegalArgumentException("Asset is null");
		}
	}

	public static void checkMeterReadStatusFile(MeterReadStatusContract contract,
			List<ImportEmployeeDo> employeeList) {

		String userFileName = contract.getUserFileName();
		if (userFileName == null) {
			throw new IllegalArgumentException("userFileName is null!");
		} else if (userFileName.isEmpty()) {
			throw new IllegalArgumentException("userFileName is empty!");
		}
		if (employeeList == null) {
			throw new IllegalArgumentException("employeeList is null!");
		} else if (employeeList.isEmpty()) {
			throw new IllegalArgumentException("employeeList is empty!");
		}
	}

	public static String buildAssetMeterReadSearchExpression() {
		StringBuilder result = new StringBuilder();
		ResourceBundle amindweb = ResourceBundle.getBundle("amindweb");
		result.append("[name] = '");
		result.append(amindweb.getString("user"));
		result.append("'");
		return result.toString();
	}

	public static String buildAssetMeterReadFileName(AssetMeterReadContract contract) {
		checkAssetMeterRead(contract);

		StringBuilder result = new StringBuilder();
		String userFileName = contract.getUserFileName().replaceAll("[(|)]", "-");
		int mid = userFileName.lastIndexOf(".");
		String fileName = userFileName.substring(0, mid);
		String fileExtension = userFileName.substring(mid + 1);

		result.append(contract.getMdmId());
		result.append("~");
		result.append(fileName);
		result.append("~");

		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssmmm");
		result.append(dateFormat.format(currentDate.getTime()));

		result.append(".");
		result.append(fileExtension);

		return result.toString();
	}

	public static String buildCharacteristicsSearchExpression(UpdateAssetMeterReadContract contract) {
		StringBuilder builder = new StringBuilder("[assetId]='");
		builder.append(contract.getAsset().getAssetId());
		builder.append("'");
		return builder.toString();
	}

	public static String buildMeterReadStatusSearchExpression(MeterReadStatusContract contract) {
		checkMeterReadStatusList(contract);
		StringBuilder builder = new StringBuilder("[fileName] LIKE '");
		builder.append(contract.getMdmId());
		builder.append("~*' AND [category] = 'Meter Read'");
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	public static List<ImportEmployeeDo> queryEmployeeList(IDataManager dataManager, String searchSpec) {
		List<ImportEmployeeDo> employeeList = dataManager.queryBySearchExpression(ImportEmployeeDo.class,
				searchSpec);

		return notNull(employeeList);
	}

	@SuppressWarnings("unchecked")
	public static List<MeterReadStatus> queryAttachment(IAttachmentManager attachmentManager,
			String searchSpec) {
		List<MeterReadStatus> meterReadStatusList = (List<MeterReadStatus>) attachmentManager
				.queryAttachmentBySearchExpression(searchSpec);

		return notNull(meterReadStatusList);
	}

	@SuppressWarnings("unchecked")
	public static List<AssetMeasurementCharacteristicsBCDo> queryCharacteristicsList(
			IDataManager dataManager, String searchSpec) {
		List<AssetMeasurementCharacteristicsBCDo> characteristicList = dataManager.queryBySearchExpression(
				AssetMeasurementCharacteristicsBCDo.class, searchSpec);

		return notNull(characteristicList);
	}

	private static void checkMeterReadStatusList(MeterReadStatusContract contract) {
		String mdmId = contract.getMdmId();
		if (mdmId == null) {
			throw new IllegalArgumentException("Mdm Id is null");
		} else if (mdmId.isEmpty()) {
			throw new IllegalArgumentException("Mdm Id is Empty");
		}
	}

	private static void checkAssetMeterRead(AssetMeterReadContract contract) {
		String mdmId = contract.getMdmId();
		if (mdmId == null) {
			throw new IllegalArgumentException("Mdm Id is null");
		} else if (mdmId.isEmpty()) {
			throw new IllegalArgumentException("Mdm Id is Empty");
		}
		String userFileName = contract.getUserFileName();
		if (userFileName == null) {
			throw new IllegalArgumentException("UserFileName is null");
		} else if (userFileName.isEmpty()) {
			throw new IllegalArgumentException("UserFileName is Empty");
		}
	}

	private AmindContractedMeterReadServiceUtil() {
	}
}
