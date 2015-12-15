package com.lexmark.result;

import java.io.FileInputStream;
import java.io.Serializable;


public class MeterReadStatusFileResult implements Serializable {
	private static final long serialVersionUID = 2886857752869754083L;
	private FileInputStream fileStream;
	public FileInputStream getFileStream() {
		return fileStream;
	}
	public void setFileStream(FileInputStream fileStream2) {
		this.fileStream = fileStream2;
	}
}
