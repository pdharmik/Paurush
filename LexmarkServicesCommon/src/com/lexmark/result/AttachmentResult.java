package com.lexmark.result;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

public class AttachmentResult implements Serializable {

	private static final long serialVersionUID = 5551739096493351592L;

	private InputStream fileStream;
	private String fileName;
	
	/**
	 * This field should be used in contract for deleteTempFileAfterDownload();
	 */
	private File file;
	
	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
