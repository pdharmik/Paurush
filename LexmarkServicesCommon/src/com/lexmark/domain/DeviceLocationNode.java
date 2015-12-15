package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

/**
 * An instance of this class is a node of device location that
 * could be country, province, state, or city.
 * @author roger.lin
 *
 */
public class DeviceLocationNode implements Serializable {

	private static final long serialVersionUID = 529174877493570380L;

	private String name;
	/*
	 * level indicates the level of location.
     * 0 means root node, 1 means Country, 21 means Province,
     * and 22 means State, 31 means City in Province, and 32 means City in State.
	 */
	private int level;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	private List<DeviceLocationNode> childLocation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DeviceLocationNode> getChildLocation() {
		return childLocation;
	}
	public void setChildLocation(List<DeviceLocationNode> childLocation) {
		this.childLocation = childLocation;
	}
	/*Added by sankha for LEX:AIR00065992 start*/
	private String parentname;
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	/*Added by sankha for LEX:AIR00065992 end*/
	
}
