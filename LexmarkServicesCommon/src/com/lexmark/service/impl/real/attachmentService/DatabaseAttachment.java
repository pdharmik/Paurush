package com.lexmark.service.impl.real.attachmentService;

public class DatabaseAttachment {

	public String AttachmentName;
	public int Size;
	public String SizeForDisplay;
	public String Visibility;
	public String DisplayAttachmentName; 
	public String identifier;
	public String requestType;
	public String row_Id;
	
	public String getAttachmentName() {
		return AttachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		AttachmentName = attachmentName;
	}
	public int getSize() {
		return Size;
	}
	public void setSize(int size) {
		Size = size;
	}
	public String getSizeForDisplay() {
		return SizeForDisplay;
	}
	public void setSizeForDisplay(String sizeForDisplay) {
		SizeForDisplay = sizeForDisplay;
	}
	public String getVisibility() {
		return Visibility;
	}
	public void setVisibility(String visibility) {
		Visibility = visibility;
	}
	public String getDisplayAttachmentName() {
		return DisplayAttachmentName;
	}
	public void setDisplayAttachmentName(String displayAttachmentName) {
		DisplayAttachmentName = displayAttachmentName;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRow_Id() {
		return row_Id;
	}
	public void setRow_Id(String row_Id) {
		this.row_Id = row_Id;
	}

}
