package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.UserGridSetting;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.UserGridSettingService;

public class UserGridSettingServiceImpl implements UserGridSettingService {
	
	private static Logger logger = LogManager.getLogger(UserGridSettingService.class);

	public UserGridSettingResult retrieveUserGridSettings(UserGridSettingContract contract)
			throws Exception {
		UserGridSettingResult result = new UserGridSettingResult();
		Connection conn = null;
		String settingType;
		String setting;
		String colsOrder;
		String colsWidth;
		String colsHidden;
		String colsSorting;
		try {		
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_grid_settings_enhancement s WHERE s.user_number = ? AND s.GRID_ID = ?");
			stmt.setString(1, contract.getUserNumber());
			stmt.setString(2, contract.getGridId());
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				colsOrder = rs.getString("colsOrder");
				colsWidth = rs.getString("colsWidth");
				colsHidden = rs.getString("colsHidden");
				colsSorting = rs.getString("colsSorting");
				result.setColsHidden(colsHidden);
				result.setColsOrder(colsOrder);
				result.setColsSorting(colsSorting);
				result.setColsWidth(colsWidth);
				result.setGridId(contract.getGridId());
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}
	
	/**
	 * This method is for retrieving user grid settings for History Grids in Device Management
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	public UserGridSettingResult retrieveUserGridSettingsForDM(UserGridSettingContract contract)
			throws Exception {
		UserGridSettingResult result = new UserGridSettingResult();
		Connection conn = null;
		String settingType;
		String setting;
		String colsOrder;
		String colsWidth;
		String colsHidden;
		String colsSorting;
		try {		
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_grid_settings_enhancement s WHERE s.user_number = ? AND s.GRID_ID = ?");
			stmt.setString(1, contract.getUserNumber());
			stmt.setString(2, contract.getGridId());
			java.sql.ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				colsOrder = rs.getString("colsOrder");
				colsWidth = rs.getString("colsWidth");
				colsHidden = rs.getString("colsHidden");
				colsSorting = rs.getString("colsSorting");
				result.setColsHidden(colsHidden);
				result.setColsOrder(colsOrder);
				result.setColsSorting(colsSorting);
				result.setColsWidth(colsWidth);
				result.setGridId(rs.getString("GRID_ID"));
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		return result;
	}
	
	public boolean saveUserGridSettings(UserGridSettingContract contract) throws Exception{
		try{
			logger.debug("===== Start Try bolck in saveUserGridSettings");
			logger.debug("=====gridId      ="+contract.getGridId());
			logger.debug("=====colsOrder   ="+contract.getColsOrder());
			logger.debug("=====colsHidden  ="+contract.getColsHidden());
			logger.debug("=====colsWidth   ="+contract.getColsWidth());
			logger.debug("=====colsSorting ="+contract.getColsSorting());
			logger.debug("=====UserNumber  ="+contract.getUserNumber());
			UserGridSetting userGridSetting = new UserGridSetting();
			userGridSetting.setGridId(contract.getGridId());
			userGridSetting.setUserNumber(contract.getUserNumber());
			userGridSetting.setColsHidden(contract.getColsHidden());
			userGridSetting.setColsOrder(contract.getColsOrder());
			userGridSetting.setColsWidth(contract.getColsWidth());
			userGridSetting.setColsSorting(contract.getColsSorting());
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(userGridSetting);
			
			HibernateUtil.commitTransaction();
			logger.debug("===== End Try bolck in saveUserGridSettings");
		}catch(HibernateException ex){
			
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
		return true;
	}
	
	public boolean deleteUserGridSettings(UserGridSettingContract contract) throws Exception{
		Connection conn = null;
		try{
			UserGridSetting userGridSetting = new UserGridSetting();
			userGridSetting.setUserNumber(contract.getUserNumber());
			HibernateUtil.beginTransaction();	
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("DELETE user_grid_settings_enhancement s WHERE s.user_number = ? AND s.grid_id = ?");
			stmt.setString(1,contract.getUserNumber());
			stmt.setString(2,contract.getGridId());
			stmt.executeUpdate();
			HibernateUtil.commitTransaction();
			logger.debug("contract.userNumber = "+contract.getUserNumber());
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
		return true;
	}

}
