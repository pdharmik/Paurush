package com.lexmark.domain;

import java.io.Serializable;

public class DocumentSource implements Serializable {

	private static final long serialVersionUID = 7592993744110472577L;

	private String host;
	private int port;
	private String path;
	private byte[] key;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public byte[] getKey() {
		return key;
	}
	public void setKey(byte[] key) {
		this.key = key;
	}
}
