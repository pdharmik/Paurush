package com.lexmark.service.impl.real;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.IAttachmentManager;
import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.BulkUploadContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.result.BulkUploadFileResult;
import com.lexmark.result.BulkUploadStatusFileResult;
import com.lexmark.result.BulkUploadStatusListResult;
import com.lexmark.service.api.BulkUploadService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.ImportEmployeeDo;
import com.lexmark.service.impl.real.domain.MeterReadStatus;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.LogUtil;
import com.siebel.data.SiebelPropertySet;

public class AmindPartnerBulkUploadService extends AmindSiebelCrmService implements BulkUploadService {
	
	private SessionFactory statelessSessionFactory;

	
	public void setStatelessSessionFactory(SessionFactory statelessSessionFactory) {
		this.statelessSessionFactory = statelessSessionFactory;
	}

	public SessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BulkUploadFileResult bulkUploadFile(BulkUploadContract contract) throws Exception {
		BulkUploadFileResult result = new BulkUploadFileResult();
		Session session = null;
		try
		{
		    checkRequiredFields(contract);
			session = getStatelessSessionFactory().attachSession();
			
			// query Employee Bc for Login = "PORTINTG"
			IDataManager dataManager = session.getDataManager();
			ResourceBundle amindweb = ResourceBundle.getBundle("amindweb");
			String username = amindweb.getString("user");
			String searchSpec = "[name] = '" + username + "'";
			List<ImportEmployeeDo> employeeList = dataManager.queryBySearchExpression(ImportEmployeeDo.class, searchSpec);
			
			IAttachmentManager attachmentDataManager = session.getAttachmentManager();
			Map<String,String> attachmentFieldMap = new HashMap<String,String>();
			Calendar currentDate = GregorianCalendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssmmm");
			String date = dateFormat.format(currentDate.getTime());
			List<String> fileParts = splitLastItem(".", contract.getUserFileName());
			
	 		String fileName = contract.getMdmId() + "~" + fileParts.get(0) + "~" + date + "." + fileParts.get(1);
			attachmentFieldMap.put("LXK SD ImportStatFlg", "Y");
			attachmentFieldMap.put("LXK UP Status", "In Progress");
			attachmentFieldMap.put("LXM Attachment Classification", contract.getUploadFileType());
			if(employeeList != null)
			{
				String attId = attachmentDataManager.createAttachment(employeeList.get(0).getId(), fileName, null, contract.getFileStream(), 
						attachmentFieldMap);

				/*Call Business Service*/
				SiebelPropertySet input = new SiebelPropertySet();
		 		SiebelPropertySet child = new SiebelPropertySet();
				input.setProperty("Component", "WfProcMgr");
				input.setProperty("DelAmount", "2");
				input.setProperty("DelUnits", "WEEKS");
				input.setProperty("Method", "RunProcess");
		        input.setProperty("Mode", "DirectDb");  
		        child.setProperty("ProcessName", "LXK UP Claims Import");                
		        child.setProperty("AttachmentId", attId);
		        child.setProperty("Object Id", attId);
		        input.addChild(child);
		        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
				businessServiceProxy.InvokeMethod("Server Requests", "SubmitRequest", input);

				result.setUpDateSuccess(true);
				
			}
			else
			{
				result.setUpDateSuccess(false);
			}
		}
		catch(Exception e)
		{
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("updateAssetMeterRead failed",e);
		}
		finally
		{
			AmindServiceUtil.releaseSession(session);
		}

		return result;
	}

    private void checkRequiredFields(BulkUploadContract contract) {
        if(contract.getMdmId() == null)
		{
			throw new IllegalArgumentException("Mdm Id is null");
		}
		if(contract.getMdmId() != null && contract.getMdmId().isEmpty())
		{
			throw new IllegalArgumentException("Mdm Id is Empty");
		}
		if(contract.getUserFileName() == null)
		{
			throw new IllegalArgumentException("UserFileName is null");
		}
		if(contract.getUserFileName() != null && contract.getUserFileName().isEmpty())
		{
			throw new IllegalArgumentException("UserFileName is Empty");
		}
		if(contract.getUploadFileType() != null && contract.getUploadFileType().isEmpty())
		{
			throw new IllegalArgumentException("Upload File Type is Empty");
		}
    }
	
	private List<String> splitLastItem(String regExpDelimiter, String input) {

		List<String> fileParts = new ArrayList<String>();
	    String fname="";
	    String ext="";
	    int mid= input.lastIndexOf(regExpDelimiter);
	    fname=input.substring(0,mid);
	    ext=input.substring(mid+1,input.length());  
	    fileParts.add(fname);
	    fileParts.add(ext);
		return fileParts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BulkUploadStatusFileResult retrieveBulkUploadStatusFile(BulkUploadStatusContract contract) throws Exception {
		BulkUploadStatusFileResult fileResult = new BulkUploadStatusFileResult();
		Session session = null;
		
		try
		{
			session = getStatelessSessionFactory().attachSession();
			IDataManager dataManager = session.getDataManager();
			ResourceBundle amindweb = ResourceBundle.getBundle("amindweb");
			String username = amindweb.getString("user");
			String searchSpec = "[name] = '" + username + "'";
			List<ImportEmployeeDo> employeeList = dataManager.queryBySearchExpression(ImportEmployeeDo.class, searchSpec);
			
			IAttachmentManager attachmentManager = session.getAttachmentManager();
			FileInputStream fileStream = (FileInputStream)attachmentManager.retrieveAttachment(employeeList.get(0).getId(), contract.getUserFileName());
			fileResult.setFileStream(fileStream);
		}
		catch(Exception e)
		{
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("Retrieve Meter Read Status File Failed.",e);
		}
		finally
		{
			AmindServiceUtil.releaseSession(session);
		}

		return fileResult;

	}

	@SuppressWarnings("unchecked")
	@Override
	public BulkUploadStatusListResult retrieveBulkUploadStatusList(BulkUploadStatusContract contract) throws Exception {
		logger.debug("[IN] retrieveBulkUploadStatusList");
		BulkUploadStatusListResult bulkUploadStatusListResult = new BulkUploadStatusListResult();
		Session session = null;
		try
		{
		    checkRequiredFields(contract);
		    
			session = getStatelessSessionFactory().attachSession();
			IAttachmentManager attachmentManager = session.getAttachmentManager();
			String searchSpec = "[fileName] LIKE '" + contract.getMdmId() + "~*' AND ([category] = 'Claim Upload' OR [category] = 'Request Upload') ";
			List<MeterReadStatus> bulkUploadStatusList = (List<MeterReadStatus>) attachmentManager.queryAttachmentBySearchExpression(searchSpec);
		    if(bulkUploadStatusList != null && bulkUploadStatusList.size() > 0)
		    {
		    	bulkUploadStatusListResult.setBulkUploadStatusList(convertMeterReadStatusDotoBulkUploadStatus(bulkUploadStatusList));
		    }
		    
		}
		catch(Exception e)
		{
		    LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
			throw new SiebelCrmServiceException("retrieveBulkUploadStatusList Failed.",e);
		}
		finally
		{
			AmindServiceUtil.releaseSession(session);
		}
		logger.debug("[OUT] retrieveBulkUploadStatusList");
		return bulkUploadStatusListResult;
	}

    private void checkRequiredFields(BulkUploadStatusContract contract) {
        if(contract.getMdmId() == null)
		{
			throw new IllegalArgumentException("Mdm Id is null");
		}
		if(contract.getMdmId() != null && contract.getMdmId().isEmpty())
		{
			throw new IllegalArgumentException("Mdm Id is Empty");
		}
    }

	private List<BulkUploadStatus> convertMeterReadStatusDotoBulkUploadStatus(List<MeterReadStatus> attachmentList)
	
	{
		List<BulkUploadStatus> statusList = new ArrayList<BulkUploadStatus>();
		for(MeterReadStatus statusDo : attachmentList)
		{
			BulkUploadStatus status = new BulkUploadStatus();
			String fileName  = statusDo.getFileName();
			if(statusDo.getFileExt() != null && !statusDo.getFileExt().isEmpty())
			{
				fileName = fileName + "." + statusDo.getFileExt();
				
			}
			status.setAttachmentName(fileName);
			status.setComment(statusDo.getComments());
			status.setCompletedOn(statusDo.getCompletedOn());
			status.setSubmittedOn(statusDo.getBulkUploadStartDate());
			status.setSize(statusDo.getFileSize());
			status.setStatus(statusDo.getStatus());
			status.setUploadFileType(statusDo.getCategory());
			statusList.add(status);
			
		}
		return statusList;
	}
}
