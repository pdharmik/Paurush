package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ReportSchedule;

public class ScheduleReportResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private ReportSchedule schedule;

	public ReportSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(ReportSchedule schedule) {
		this.schedule = schedule;
	}
}
