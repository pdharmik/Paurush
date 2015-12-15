package com.lexmark.service.impl.mock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.MeterReadStatus;
import com.lexmark.result.AssetMeterReadResult;
import com.lexmark.result.MeterReadAssetListResult;
import com.lexmark.result.MeterReadStatusFileResult;
import com.lexmark.result.MeterReadStatusListResult;
import com.lexmark.result.UpdateAssetMeterReadResult;
import com.lexmark.service.api.MeterReadService;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

/**
 * Implement to update import, or retrieve device.
 * @author Eagle.Kong
 *
 */
public class MeterReadServiceImpl implements MeterReadService {
	
	private static final boolean ASSET_UPDATE_SUCCESS = true;
	
	private static List<MeterReadStatus> meterReadStatusList =  new ArrayList<MeterReadStatus>();
	static{
		meterReadStatusList =  DomainMockDataGenerator.getMeterReadStatusList();
	}
	
	public MeterReadAssetListResult retrieveMeterReadAssetList(
			MeterReadAssetListContract contract) throws Exception {
		MeterReadAssetListResult meterReadAssetListResult = new MeterReadAssetListResult();
		List<Asset> devices = DomainMockDataGenerator.getDeviceList();
		String sortCriteria=null;
		for(String sort : contract.getSortCriteria().keySet()) {
			sortCriteria = sort + ":" + contract.getSortCriteria().get(sort);
			break;
		}
		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		CollectionSorter sorter = new CollectionSorter();
		List<Asset> filterResult = filter.filter(devices, contract.getFilterCriteria(), contract.getSearchCriteria());
		List<Asset> sortResult = sorter.sort(filterResult, sortCriteria);
		
		if(contract.isFavoriteFlag()){
			List<Asset> tempResult= new ArrayList<Asset>();
			for(Asset asset:sortResult){
				if(asset.getUserFavoriteFlag())
					tempResult.add(asset);
			}
			sortResult =tempResult;
		}
		
		int start = contract.getStartRecordNumber();
		if(contract.getIncrement() == -1) {
			contract.setIncrement(100);
		}
		int end = (contract.getIncrement()+ start)> sortResult.size()? sortResult.size(): (contract.getIncrement()+start);
		meterReadAssetListResult.setTotalCount(sortResult.size());
		meterReadAssetListResult.setAccountAssets(sortResult.subList(start, end));
		return meterReadAssetListResult;
	}

	public AssetMeterReadResult importAssetMeterRead(
			AssetMeterReadContract contract) throws Exception {
		AssetMeterReadResult result = new AssetMeterReadResult();
		MeterReadStatus meterReadStatus = new MeterReadStatus();
		
		File file =new File("c:\\temp-" + new Date().getTime() +".csv");
		 
		//if file doesnt exists, then create it
		if(!file.exists()){
		   file.createNewFile();
		}

		FileOutputStream fop=new FileOutputStream(file);
		BufferedWriter out
		   = new BufferedWriter(new OutputStreamWriter(fop));
		InputStreamReader read = new InputStreamReader(contract.getFileStream());
		BufferedReader reader=new BufferedReader(read);
		
		  try {
			String line = null;
			while ((line = reader.readLine()) != null) {
			    	out.write(line);
			    	out.newLine();
			}
			out.flush();
		  }
		  finally {
			out.close();
			fop.close();
			read.close();
			reader.close();
	  }
		  
		meterReadStatus.setAttachmentName(contract.getUserFileName());
		meterReadStatus.setSubmittedOn(formatDate());
		meterReadStatus.setCompletedOn(formatDate());
		meterReadStatus.setSize(5500);
		meterReadStatus.setStatus("Succesful");
		meterReadStatus.setComment("File imported successfully");
		meterReadStatusList.add(meterReadStatus);
		result.setUpDateSuccess(true);
		return result;
	}

	public MeterReadStatusListResult retrieveMeterReadStatusList(
			MeterReadStatusContract contract) throws Exception {
		// TODO need more logic
		MeterReadStatusListResult result = new MeterReadStatusListResult();
		result.setMeterReadStatusList(meterReadStatusList);
		return result;
	}

	public UpdateAssetMeterReadResult updateAssetMeterRead(
			UpdateAssetMeterReadContract contract) throws Exception {
		// TODO need more logic for judge if it is success or not
		UpdateAssetMeterReadResult updateAssetMeterReadResult = new  UpdateAssetMeterReadResult();
		updateAssetMeterReadResult.setResult(ASSET_UPDATE_SUCCESS);
		return updateAssetMeterReadResult;
	}

	public MeterReadStatusFileResult retrieveMeterReadStatusFile(
			MeterReadStatusContract contract) throws Exception {
		MeterReadStatusFileResult result = new MeterReadStatusFileResult();
		try{
			result.setFileStream(new FileInputStream("c:\\test2.csv"));
		}catch (Exception e) {
			return null;
		}
		return result;
	}
	private  String formatDate(){
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return dateFormat.format(currentDate.getTime());
	}
	private  String formatFileName(String fileName){
		int sep = fileName.lastIndexOf(".");
		String preStr = fileName.substring(0, sep);
		String lastStr = fileName.substring(sep+1, fileName.length());
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssmmm");
		
		String attachName = "123456~"+preStr+"~"+dateFormat.format(currentDate.getTime());
	    return attachName;
	}

}
