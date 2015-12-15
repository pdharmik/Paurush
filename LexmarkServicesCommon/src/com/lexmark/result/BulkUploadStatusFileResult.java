package com.lexmark.result;

import java.io.FileInputStream;
import java.io.Serializable;

public class BulkUploadStatusFileResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6238319936781800474L;
	private FileInputStream fileStream;
	public FileInputStream getFileStream() {
		return fileStream;
	}
	public void setFileStream(FileInputStream fileStream) {
		this.fileStream = fileStream;
	}
}
