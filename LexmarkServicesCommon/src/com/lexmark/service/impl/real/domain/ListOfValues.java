package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

/**
 * mapping file: "do-lov-mapping.xml"
 */
public class ListOfValues extends com.amind.common.domain.BaseEntity implements Serializable {
	private static final long serialVersionUID = 4890798160167555444L;

	private String id;
	private String type;
	private String value;
	private String language;
	private String languageName;
	private String name;
	private String low;
	private String active;
	private String target;
	private String parentName;
	private String description;
	

	@Override
	public int hashCode() {
		return ((name == null) ? 0 : name.hashCode());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof ListOfValues)) {
			return false;
		}

		ListOfValues lof = (ListOfValues) o;

		if (name == null) {
			if (lof.name != null) {
				return false;
			}
		} else if (!name.equals(lof.name)) {
			return false;
		}

		return true;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentName() {
		return parentName;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
