package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Document;
import com.lexmark.domain.Report;
import com.lexmark.services.DocumentListService;
import com.lexmark.util.DBUtil;

public class DocumentListServiceImpl implements DocumentListService {

	private static Logger logger = LogManager.getLogger(DocumentListServiceImpl.class);
	private static int PUBLIC_DOCUMENT_MOCK_DEFINTION_ID = -1; 
	private static final String QUERY_DOCUMENT_LIST_PREFIX = 
			"select R_OBJECT_ID, OBJECT_NAME, A_WEBC_URL, FILE_DATA_LINK, file_content_date, R_CONTENT_SIZE from lxk_portal_doc_view lpd " + 
			"where " + 
			"not exists (select r_object_id from report_delete_status rds where lpd.r_object_id = rds.r_object_id and rds.is_deleted = 'T') and ";  

	public List<Document> listDocumentByPath(String path) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Document> documentList = new ArrayList<Document>();
		try {
			con = DBUtil.getPortalDBConnection();
			logger.debug(con);
			ps = con.prepareStatement(QUERY_DOCUMENT_LIST_PREFIX + " lower(A_WEBC_URL) like ?");
			ps.setString(1, path.toLowerCase() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Document doc = rowToDocument(rs);
				documentList.add(doc);
			}
			return documentList;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	private Document rowToDocument(ResultSet rs) throws SQLException {
		Document doc = new Document();
		doc.setFileObjectId(rs.getString("R_OBJECT_ID"));
		doc.setFileName(rs.getString("OBJECT_NAME"));
		doc.setFilePath(rs.getString("A_WEBC_URL"));
		doc.setFiledataLink(rs.getLong("FILE_DATA_LINK"));
		doc.setLastUpdateTime(rs.getDate("file_content_date"));
		doc.setFileSize(rs.getInt("R_CONTENT_SIZE"));
		return doc;
	}

	@Override
	public List<Document> listDocumentByDefinitionId(int definitionId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Document> documentList = new ArrayList<Document>();
		try {
			con = DBUtil.getPortalDBConnection();
			if(definitionId != PUBLIC_DOCUMENT_MOCK_DEFINTION_ID) {
				ps = con.prepareStatement(QUERY_DOCUMENT_LIST_PREFIX + " DEFINITION_ID=?");
				ps.setInt(1, definitionId);
			} else {
				ps = con.prepareStatement(QUERY_DOCUMENT_LIST_PREFIX + " R_FOLDER_PATH = '/Services Web/servicesPortal/publicDocuments'");
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Document doc = rowToDocument(rs);
				documentList.add(doc);
			}
			return documentList;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}
}
