package com.lexmark.service.impl.real.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class StringDBParameter extends DBParameter {

	
	public StringDBParameter(Object value, int type) {
		super(value, type);
	}
	
	public StringDBParameter(Object value, int type, ParameterDirection direction) {
		super(value, type, direction);
	}

	@Override
	protected void setValue(PreparedStatement stmt, int index, Object value)
			throws SQLException {
		if (value == null)
			stmt.setNull(index, Types.VARCHAR);
		else
			stmt.setString(index, value.toString());
	}
	
	@Override
	protected Object getValue(CallableStatement stmt, int index)
			throws SQLException {
		String result = stmt.getString(index);
		return stmt.wasNull() ? null : result;
	}

}
