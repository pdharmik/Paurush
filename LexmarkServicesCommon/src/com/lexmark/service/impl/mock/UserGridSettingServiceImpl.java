package com.lexmark.service.impl.mock;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.UserGridSetting;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.UserGridSettingService;

public class UserGridSettingServiceImpl implements UserGridSettingService {

	private static Logger logger = LogManager.getLogger(UserGridSettingServiceImpl.class);
	private ConcurrentHashMap<GridContract, UserGridSetting> settings = new ConcurrentHashMap<GridContract, UserGridSetting>();

	private static class GridContract implements Serializable {
		private static final long serialVersionUID = 8037604965189266835L;
		private String userNumber;
		private String gridId;
		public GridContract(String userNumber, String gridId) {
			this.userNumber = userNumber;
			this.gridId = gridId;
		}
		public String getUserNumber() {
			return userNumber;
		}
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public String getGridId() {
			return gridId;
		}
		public void setGridId(String gridId) {
			this.gridId = gridId;
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof GridContract)) {
				return false;
			}
			if(this == o) {
				return true;
			}
			
			GridContract other = (GridContract)o;
			return this.userNumber.equals(other.userNumber) && this.gridId.equals(other.gridId);
		}
		
		@Override
		public int hashCode() {
			return this.userNumber.hashCode() * this.gridId.hashCode()/133;
		}

	}
	public UserGridSettingResult retrieveUserGridSettings(UserGridSettingContract contract)
	throws Exception {
		UserGridSettingResult result = new UserGridSettingResult();

		GridContract  gridContract = new GridContract(contract.getUserNumber(), contract.getGridId());
		UserGridSetting setting = settings.get(gridContract);
		if(setting != null) {
			result.setColsHidden(setting.getColsHidden());
			result.setColsOrder(setting.getColsOrder());
			result.setColsSorting(setting.getColsSorting());
			result.setColsWidth(setting.getColsWidth());
			result.setColsFilter(setting.getColsFilter());
		}
		return result;
	}

	public boolean saveUserGridSettings(UserGridSettingContract contract) throws Exception{
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
		userGridSetting.setColsFilter(contract.getColsFilter());

		GridContract  gridContract = new GridContract(contract.getUserNumber(), contract.getGridId());

		settings.put(gridContract, userGridSetting);
		return true;
	}

	public boolean deleteUserGridSettings(UserGridSettingContract contract) throws Exception{
		GridContract  gridContract = new GridContract(contract.getUserNumber(), contract.getGridId());
		return settings.remove(gridContract)!= null;
	}

}
