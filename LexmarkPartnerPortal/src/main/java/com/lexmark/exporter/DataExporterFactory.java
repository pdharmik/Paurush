package com.lexmark.exporter;

public class DataExporterFactory {

	public static DataExporter getDataExporter(String type) {
		if ("CSV".equalsIgnoreCase(type)) {
			return new CsvDataExporter();
		} else if ("PDF".equalsIgnoreCase(type)) {
			return new PdfDataExporter();
		}
		throw new IllegalArgumentException("Can not found DataExporter of type[" + type + "]");
	}
}
