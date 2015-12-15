package com.lexmark.reportScheduler.service.impl;

import com.crystaldecisions.sdk.occa.infostore.IDestinationPlugin;

public class IDestinationPluginObject {

	private IDestinationPlugin destinationPlugin;
	private String folderName;
	public IDestinationPlugin getDestinationPlugin() {
		return destinationPlugin;
	}
	public void setDestinationPlugin(IDestinationPlugin destinationPlugin) {
		this.destinationPlugin = destinationPlugin;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

}
