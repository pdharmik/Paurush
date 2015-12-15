package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.BulkUploadStatus;

public class BulkUploadStatusListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3585962481547189159L;
	private List<BulkUploadStatus> bulkUploadStatusList;
	public List<BulkUploadStatus> getBulkUploadStatusList() {
		return bulkUploadStatusList;
	}
	public void setBulkUploadStatusList(List<BulkUploadStatus> bulkUploadStatusList) {
		this.bulkUploadStatusList = bulkUploadStatusList;
	}
}
