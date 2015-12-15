package com.lexmark.service.impl.real.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public abstract class DBParameter {
	private Object value;
	private int type;
	private ParameterDirection direction = ParameterDirection.Input;
	private int index;
	private String name;
	
	public DBParameter(Object value, int type) {
		this.value = value;
		this.type = type;
	}
	
	public DBParameter(Object value, int type, ParameterDirection direction) {
		this.value = value;
		this.type = type;
		this.direction = direction;
	}

	public void setValue(PreparedStatement statement, int index) {
		try {
			if (this.value == null)
				statement.setNull(index, this.type);
			else 
				setValue(statement, index, this.value);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	protected abstract void setValue(PreparedStatement stmt, int index,
			Object value) throws SQLException;

	protected abstract Object getValue(CallableStatement stmt, int index) throws SQLException;
	
	public static int toInt32(String s) {
		return Integer.parseInt(s);
	}

	public static int toInt32(Object o) {
		if (o instanceof Integer)
			return (Integer) o;
		else
			return toInt32(o.toString());
	}
	
	public static java.sql.Date toDate(Object o) {
		if (o instanceof java.sql.Date) {
			return (java.sql.Date) o;
		} else {
			return new java.sql.Date(((Date) o).getTime());
		}
	}
	
	public static java.sql.Timestamp toTimestamp(Object o) {
		if (o instanceof java.sql.Timestamp) {
			return (java.sql.Timestamp) o;
		} else {
			return new java.sql.Timestamp(((Date) o).getTime());
		}
	}
	
	public ParameterDirection getDirection() {
		return direction;
	}

	public void setDirection(ParameterDirection direction) {
		this.direction = direction;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	


	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public enum ParameterDirection {
		Input,
		InputOutput,
		Output,
		ReturnValue
	}

}
