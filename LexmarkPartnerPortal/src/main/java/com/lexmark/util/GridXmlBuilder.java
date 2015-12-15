package com.lexmark.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GridXmlBuilder {

	private List<Row> rows = null;

	private int pos = 0;
	
	private Row currentRow = null;

	public GridXmlBuilder(int rowNum, int pos) {
		this.rows = new ArrayList<Row>(rowNum);
		this.pos = pos;
	}
	
	public GridXmlBuilder(int rowNum) {
		this.rows = new ArrayList<Row>(rowNum);
	}
	
	public String getGridXml() {
		finish();
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" ?>");
		sb.append("<rows total_count=\"" + rows.size() + "\" pos=\"" + pos +"\">");
		for (Row row : rows) {
			sb.append("").append(row.toXML());
		}
		sb.append("</rows>");
		return sb.toString();
	}

	public String getGridXmlString() {
		return getGridXml().replaceAll("\"", "\\\\\"");
	}

	public void addCells(Collection<String> collection) {
		this.currentRow.addCells(collection);
	}

	public void addCells(String... cellValues) {
		this.currentRow.addCell(cellValues);
	}

	public void nextRow(String rowId) {
		if (currentRow != null) {
			rows.add(currentRow);
		}
		this.currentRow = new Row(rowId);
	}

	public void finish() {
		if (this.currentRow != null) {
			rows.add(this.currentRow);
			this.currentRow = null;
		}
	}

	static class Row {
		private String id = null;
		private List<Cell> cells = new ArrayList<Cell>();

		public Row(String rowId) {
			this.id = rowId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void addCell(Cell cell) {
			cells.add(cell);
		}

		public void addCells(Collection<String> collection) {
			for (String cellValue : collection) {
				cells.add(new Cell(cellValue));
			}
		}

		public void addCell(String... cellValues) {
			for (String cellValue : cellValues) {
				cells.add(new Cell(cellValue));
			}
		}

		public List<Cell> getCells() {
			return cells;
		}

		public String toXML() {
			StringBuilder rowXML = new StringBuilder("<row id=\"").append(id).append("\">");
			for (Cell cell : cells) {
				rowXML.append(cell.toXML());
			}
			rowXML.append("</row>");
			return rowXML.toString();
		}
	}

	static class Cell {
		private String value = "";

		public Cell(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			if (value != null)
				this.value = value;
		}

		public String toXML() {
			return new StringBuilder("<cell><![CDATA[").append(value).append("]]></cell>").toString();
		}
	}

}
