package com.lexmark.service.impl.real.domain;

import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partneractivitydetailinstruction-mapping.xml"
 */
public class PartnerActivityDetailInstructionDo extends BaseEntity  {
	
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
