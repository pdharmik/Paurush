package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class TechnicianInstruction implements Serializable {

	private static final long serialVersionUID = -2902568060841868935L;
	private String instructionId;
	private Date instructionDate;
	private String instructionType;
	private String actualInstruction;
	
	public String getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(String instructionId) {
		this.instructionId = instructionId;
	}
	public Date getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public String getInstructionType() {
		return instructionType;
	}
	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}
	public String getActualInstruction() {
		return actualInstruction;
	}
	public void setActualInstruction(String actualInstruction) {
		this.actualInstruction = actualInstruction;
	}
}
