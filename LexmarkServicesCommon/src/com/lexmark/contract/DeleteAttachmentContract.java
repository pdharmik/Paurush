package com.lexmark.contract;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

public class DeleteAttachmentContract implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private InputStream fileStream;
	private File file;
	
	public InputStream getFileStream() {
		return fileStream;
	}
	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
