package com.lexmark.service.impl.real.domain;
import java.util.Date;
import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: "do-partneractivitynoteinstruction-mapping.xml" 
 */
public class PartnerActivityNoteInstructionDo extends BaseEntity{
	private String id;
	private String authorId;
	private String authorFirstName;
	private String authorLastName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getInstructionType() {
		return instructionType;
	}
	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}
	private Date created;
	private String instruction;
	private String instructionType;

	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	
}
