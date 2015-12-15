package com.lexmark.service.impl.real.jdbc;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatetimeDBParameter extends DBParameter {

	public DatetimeDBParameter(Object value, int type) {
		super(value, type);
	}
	
	public DatetimeDBParameter(Object value, int type, ParameterDirection direction) {
		super(value, type, direction);
	}
	
	@Override
	protected void setValue(PreparedStatement stmt, int index, Object value)
			throws SQLException {
		stmt.setTimestamp(index, toTimestamp(value));
	}
	
	@Override
	protected Object getValue(CallableStatement stmt, int index)
			throws SQLException {
		Date result = stmt.getDate(index);
		return stmt.wasNull() ? null : result;
	}

}
