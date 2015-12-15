package com.lexmark.util;

import java.util.ArrayList;
import java.util.List;

public class TreeXmlBuilder {

	private List<Item> items = new ArrayList<Item>();

	public void addItem(Item item) {
		items.add(item);
	}
	// Fix by Service Portal AMS LEX:AIR00062572
	public static String quote(String value) {
		return "\"" + XMLEncodeUtil.escapeXML(value) + "\"";
	}

	public static String quote(int value) {
		return "\"" + value + "\"";
	}

	public String toXML() {
		StringBuilder xml = new StringBuilder("<tree");
		xml.append(" id=").append(quote(0));
		xml.append(">");
		for (Item item : items) {
			xml.append(item.toXML());
		}
		xml.append("</tree>");
		return xml.toString();
	}

	public static class Item {
		private static final int OPEN = 1;

		private List<Item> childItems = new ArrayList<Item>();

		public Item() {

		}

		public Item(String id, String text) {
			this.id = id;
			this.text = text;
			this.open = OPEN;
		}

		public Item(String id, String text, int open) {
			this(id, text);
			this.open = open;
		}

		private String id;
		private String text;
		private int open = OPEN;

		public void addChildItem(Item item) {
			childItems.add(item);
		}

		public List<Item> getChildItems() {
			return childItems;
		}

		public String toXML() {
			StringBuilder xml = new StringBuilder("<item");
			xml.append(" id=").append(TreeXmlBuilder.quote(id));
			xml.append(" text=").append(TreeXmlBuilder.quote(text));
			xml.append(" open=").append(TreeXmlBuilder.quote(open));
			xml.append(">");
			for (Item childItem : childItems) {
				xml.append(childItem.toXML());
			}
			xml.append("</item>");
			return xml.toString();
		}
	}
}
