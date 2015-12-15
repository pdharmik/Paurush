package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import com.lexmark.contract.DatabaseAttachmentContract;
import com.lexmark.domain.DatabaseAttachment;
import com.lexmark.domain.UserGridSetting;
import com.lexmark.result.DatabaseAttachmentResult;
import com.lexmark.service.api.DatabaseAttachmentService;

public class DatabaseAttachmentServiceImpl implements DatabaseAttachmentService {
	
	private static Logger logger = LogManager.getLogger(DatabaseAttachmentServiceImpl.class);

	public DatabaseAttachmentResult retrieveDatabaseAttachmentList()
			throws Exception {
		DatabaseAttachmentResult result = new DatabaseAttachmentResult();
		List<DatabaseAttachment> attachmentList = new ArrayList<DatabaseAttachment>();
		Connection conn = null;
		String attachmentName;
		int size;
		String sizeForDisplay;
		String visibility;
		String displayAttachmentName;
		String identifier;
		String requestType;
		String row_Id;
		try {		
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM DATABASEATTACHMENT");
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				DatabaseAttachment databaseAttachment = new DatabaseAttachment();
				attachmentName = rs.getString("attachment_Name");
				size = rs.getInt("attachment_Size");
				sizeForDisplay = rs.getString("sizeForDisplay");
				visibility = rs.getString("visibility");
				displayAttachmentName = rs.getString("displayAttachmentName");
				identifier = rs.getString("identifier");
				requestType = rs.getString("requestType");
				row_Id = rs.getString("ATTACHMENTID");
				databaseAttachment.setAttachment_Name(attachmentName);
				databaseAttachment.setAttachment_size(size);
				databaseAttachment.setSizeForDisplay(sizeForDisplay);
				databaseAttachment.setVisibility(visibility);
				databaseAttachment.setDisplayAttachmentName(displayAttachmentName);
				databaseAttachment.setAttch_identifier(identifier);
				databaseAttachment.setRequestType(requestType);
				databaseAttachment.setAttachmentid(row_Id);
				attachmentList.add(databaseAttachment);
			}
			result.setDatabaseAttachmentList(attachmentList);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}
	
	
	
	public boolean saveDatabaseAttachment(DatabaseAttachmentContract contract) throws Exception{
		try{
			logger.debug("===== begin Try block in saveDatabaseAttachment");
			logger.debug("row_id "+contract.getDatabaseAttchment().getAttachmentid());
			logger.debug("getAttachmentName "+contract.getDatabaseAttchment().getAttachment_Name());
			logger.debug("getDisplayAttachmentName "+contract.getDatabaseAttchment().getDisplayAttachmentName());
			logger.debug("getIdentifier "+contract.getDatabaseAttchment().getAttch_identifier());
			logger.debug("getRequestType "+contract.getDatabaseAttchment().getRequestType());
			logger.debug("getSize "+contract.getDatabaseAttchment().getAttachment_size());
			logger.debug("getSizeForDisplay "+contract.getDatabaseAttchment().getSizeForDisplay());
			logger.debug("getVisibility "+contract.getDatabaseAttchment().getVisibility());
			DatabaseAttachment databaseAttachment = new DatabaseAttachment();
			databaseAttachment.setAttachmentid(contract.getDatabaseAttchment().getAttachmentid());
			databaseAttachment.setAttachment_Name(contract.getDatabaseAttchment().getAttachment_Name());
			databaseAttachment.setDisplayAttachmentName(contract.getDatabaseAttchment().getDisplayAttachmentName());
			databaseAttachment.setAttch_identifier(contract.getDatabaseAttchment().getAttch_identifier());
			databaseAttachment.setRequestType(contract.getDatabaseAttchment().getRequestType());
			databaseAttachment.setAttachment_size(contract.getDatabaseAttchment().getAttachment_size());
			databaseAttachment.setSizeForDisplay(contract.getDatabaseAttchment().getSizeForDisplay());
			databaseAttachment.setVisibility(contract.getDatabaseAttchment().getVisibility());
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().save(databaseAttachment);
			
			HibernateUtil.commitTransaction();
			logger.debug("===== End Try block in saveDatabaseAttachment");
		}catch(HibernateException ex){
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
		return true;
	}
	
	public boolean deleteDatabaseAttachment(DatabaseAttachmentContract contract) throws Exception{
		Connection conn = null;
		try{
			DatabaseAttachment databaseAttachment = new DatabaseAttachment();
			HibernateUtil.beginTransaction();	
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("DELETE DATABASEATTACHMENT s WHERE s.ATTACHMENTID = ?");
			stmt.setString(1,contract.getDatabaseAttchment().getAttachmentid());
			stmt.executeUpdate();
			HibernateUtil.commitTransaction();
			logger.debug("contract.Row_ID = "+contract.getDatabaseAttchment().getAttachmentid());
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
		return true;
	}

}
