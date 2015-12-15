package com.lexmark.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.util.DBUtil;

public class ProductImage {

	private static Logger logger = LogManager.getLogger(ProductImage.class);

	public static String retrieveProductImageUrl(String partNumber) throws SQLException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String productImageUrl = null;
		try{
			connection = DBUtil.getProductImageConnection();
			StringBuffer sql = new StringBuffer();
		
			sql.append("SELECT 'http://images.lexmark.com'||scene7_link image_url, ");
			sql.append("'https://www.lexmark.com'||scene7_link secure_image_url ");
			sql.append("FROM EF_DAT_APP_EF_PROD_PART_NUMBER prod_pn ");
			sql.append("JOIN EF_DAT_APP_EF_LINK link ON prod_pn.ef_product_revenue_pid=link.ef_product_revenue_pid ");
			sql.append("WHERE scene7_link is not null ");
			sql.append("AND scene7_link not like '%_none_' ");
			sql.append("AND ROWNUM = 1 ");
			sql.append("AND pn = ? ");
			sql.append("order by link.updated_date desc");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, partNumber);
			rs = ps.executeQuery();
			while (rs != null && rs.next()){
				productImageUrl = rs.getString(2);
			}
		}catch(SQLException e){
			logger.error("Error getting upload dates");
			throw e;
		}finally{
			if (rs != null){
				try{rs.close();}catch(Exception e){rs = null;}
			}
			if (ps != null){
				try{ps.close();}catch(Exception e){ps = null;}
			}
			if (connection != null){
				try{connection.close();}catch(Exception e){connection = null;}
			}
		}
		
		return productImageUrl;
		}
}
