package com.lexmark.services.reports.util;

import java.util.Comparator;

import com.lexmark.services.reports.bean.ReportListRecord;

public class ReportListRowsComparator implements Comparator<ReportListRecord> {

	@Override
	public int compare(ReportListRecord rec1, ReportListRecord rec2) {
		try {
			if (null == rec1.getScheduleCreateDate() || null == rec2.getScheduleCreateDate()) {
				if (null == rec1.getCreatedDate() || null == rec2.getCreatedDate()) {
					return -1;
				} else {
					if (rec1.getCreatedDate().before(rec2.getCreatedDate())) {
						return 1;
					} else {
						return -1;
					}
				}
			} else {
				if (rec1.getScheduleCreateDate().before(rec2.getScheduleCreateDate())) {
					return 1;
				} else {
					return -1;
				}
			}
		} catch (Exception e) {
			return -1;
		}
	}

}
