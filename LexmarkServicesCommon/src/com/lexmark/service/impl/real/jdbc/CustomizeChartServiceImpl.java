package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;

import com.lexmark.domain.ChartDetail;
import com.lexmark.domain.ChartInfo;
import com.lexmark.service.api.CustomizeChartService;

public class CustomizeChartServiceImpl implements CustomizeChartService{
	
	private static Logger logger = LogManager.getLogger(CustomizeChartServiceImpl.class);
	
	public List<ChartInfo> getReportList()throws Exception{
		logger.debug("-- Entry : getReportList()----- ");
		List<ChartInfo> results = new ArrayList<ChartInfo>();
		Connection conn = null;
		String cell1;
		try {		
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CHART_SETTINGS_INFO s order by s.CHART_SEQUENCE");
			
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ChartInfo chartInfo = new ChartInfo();
					cell1 = rs.getString("CHART_ID");
						chartInfo.setChartId(cell1);
					cell1 = rs.getString("CHART_NAME");
						chartInfo.setChartName(cell1);
					cell1 = rs.getString("CHART_URL");
						chartInfo.setChartUrl(cell1);
						chartInfo.setFlag("Y");
				results.add(chartInfo);
			}
			logger.debug("-- Inside : getReportList()----- "+results.size());
		} catch (HibernateException ex) {
			logger.error("-- HibernateException : getReportList()----- "+ex);
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		logger.debug("-- Exit : getReportList()----- ");
		return results;
	}
	
	public List<ChartInfo> getWindowBasedReportList(ChartInfo contract)throws Exception{
		logger.debug("-- Entry : getWindowBasedReportList()----- ");
		List<ChartInfo> results = new ArrayList<ChartInfo>();
		Connection conn = null;
		String cell1;
		
		try {		
			conn = HibernateUtil.getSession().connection(); 
			String query = "SELECT * FROM CHART_WINDOW_SETTINGS s WHERE s.MDM_ID = ? ";
			
			PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, contract.getMdmId().trim());
			
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ChartInfo chartInfo = new ChartInfo();
					chartInfo.setFlag("Y");
					if("L".equals(contract.getWindow())){
						cell1 = rs.getString("LEFT_WINDOW_CHART_ID");
						chartInfo.setChartId(cell1);
					}else 
					if("R".equals(contract.getWindow())){
						cell1 = rs.getString("RIGHT_WINDOW_CHART_ID");
						chartInfo.setChartId(cell1);
					}
				results.add(chartInfo);
			}
			logger.debug("-- Inside : getWindowBasedReportList()----- "+results.size());
		} catch (HibernateException ex) {
			logger.error("-- HibernateException : getWindowBasedReportList()----- "+ex);
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		logger.debug("-- Exit : getReportList()----- ");
		return results;
	}
	
	public boolean saveChartCustomization(ChartDetail contract)throws Exception{
		logger.debug("-- Entry : saveChartCustomization()----- ");
		boolean flag= false;
		Connection conn = null;
		try {	
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(contract);
			HibernateUtil.commitTransaction();
			flag = true;
			logger.debug("-- Inside : saveChartCustomization()----- "+flag);
		} catch (HibernateException ex) {
			logger.error("-- HibernateException : saveChartCustomization()----- "+ex);
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		logger.debug("-- Exit : getReportList()----- ");
		return flag;
	}
	
	public ChartInfo getChartDetails(ChartInfo contract)throws Exception{
		logger.debug("-- Entry : getChartDetails()----- ");
		ChartInfo result = null;
		Connection conn = null;
		String cell1;
		try {		
			conn = HibernateUtil.getSession().connection(); 
			String query = "";
			if("L".equals(contract.getWindow())){
				query = "select * from CHART_SETTINGS_INFO outer where outer.CHART_ID in (SELECT inner.LEFT_WINDOW_CHART_ID FROM CHART_WINDOW_SETTINGS inner WHERE inner.MDM_ID = ? )";
			}else
			if("R".equals(contract.getWindow())){
				query = "select * from CHART_SETTINGS_INFO outer where outer.CHART_ID in (SELECT inner.RIGHT_WINDOW_CHART_ID FROM CHART_WINDOW_SETTINGS inner WHERE inner.MDM_ID = ? )";
			}
			
			PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, contract.getMdmId().trim());
			
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				result = new ChartInfo();
					result.setMdmId(contract.getMdmId().trim());
				cell1 = rs.getString("CHART_ID");
					result.setChartId(cell1);
				cell1 = rs.getString("CHART_NAME");
					result.setChartName(cell1);
				cell1 = rs.getString("CHART_URL");
					result.setChartUrl(cell1.trim());
				cell1 = rs.getString("MDM_REQUIRED");
					result.setMdmRequired(cell1.trim());
			}
		} catch (HibernateException ex) {
			logger.error("-- HibernateException : getChartDetails()----- "+ex);
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		logger.debug("-- Exit : getChartDetails()----- ");
		return result;
	}
	
	public ChartInfo getChartDetailsByChartName(ChartInfo contract)throws Exception{
		logger.debug("-- Entry : getChartDetailsByChartName()----- ");
		ChartInfo result = null;
		Connection conn = null;
		String cell1;
		try {		
			conn = HibernateUtil.getSession().connection(); 
			String query = "select * from CHART_SETTINGS_INFO s where s.CHART_NAME = '"+contract.getChartName()+"'";
			PreparedStatement stmt = conn.prepareStatement(query);
			java.sql.ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				result = new ChartInfo();
					result.setMdmId(contract.getMdmId());
				cell1 = rs.getString("CHART_ID");
					result.setChartId(cell1);
				cell1 = rs.getString("CHART_NAME");
					result.setChartName(cell1);
				cell1 = rs.getString("CHART_URL");
					result.setChartUrl(cell1.trim());
				cell1 = rs.getString("MDM_REQUIRED");
					result.setMdmRequired(cell1.trim());
			}
		} catch (HibernateException ex) {
			logger.error("-- HibernateException : getChartDetailsByChartName()----- "+ex);
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		logger.debug("-- Exit : getChartDetailsByChartName()----- "+result);
		return result;
	}
	
	public String getWindowPosition(String portletInstanceId)throws Exception{
		logger.debug("-- Entry : getWindowPosition()----- "+portletInstanceId);
		String result = null;
		Connection conn = null;
		String cell1;
		try {		
			conn = HibernateUtil.getSession().connection(); 
			String query = "SELECT * from PORTLET_INSTANCE_POSITION s where s.PORTLET_INSTANCE_ID = '"+portletInstanceId+"'";
			
			logger.debug("====================================== : "+query);
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				
				result = rs.getString("WINDOW_POSITION");
				
			}
		} catch (HibernateException ex) {
			logger.error("-- HibernateException : getWindowPosition()----- "+ex);
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		logger.debug("-- Exit : getWindowPosition()----- "+result);
		return result;
	}
}
