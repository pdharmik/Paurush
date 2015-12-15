package com.lexmark.reportScheduler.util;

import java.util.UUID;

public class BatchIdGenerator {
	public static String getBatchId() {
		return UUID.randomUUID().toString();
	}
}
