package com.lexmark.service.impl.real.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Int32DBParamter extends DBParameter {

	public Int32DBParamter(Object value, int type) {
		super(value, type);
	}
	public Int32DBParamter(Object value, int type, ParameterDirection direction) {
		super(value, type, direction);
	}
	@Override
	protected void setValue(PreparedStatement stmt, int index, Object value)
			throws SQLException {
		stmt.setInt(index, toInt32(value));
	}
	
	@Override
	protected Object getValue(CallableStatement stmt, int index)
			throws SQLException {
		int result = stmt.getInt(index);
		return stmt.wasNull() ? null : result;
	}

}
