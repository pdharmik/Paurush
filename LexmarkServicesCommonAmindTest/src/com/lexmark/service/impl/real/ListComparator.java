package com.lexmark.service.impl.real;

import static org.junit.Assert.assertTrue;
import java.util.Comparator;

public class ListComparator implements Comparator<Object> {
	private enum Order {
		ASCENDING, DESCENDING
	}

	private Order order;

	public ListComparator(String value) {
		if (value.toString().equalsIgnoreCase("DESCENDING")) {
			order = Order.DESCENDING;
		} else if (value.toString().equalsIgnoreCase("ASCENDING")) {
			order = Order.ASCENDING;
		} else {
			throw new IllegalArgumentException("Wrong sort parameter. Must be ASCENDING or DESCENDING");
		}
	}

	public int compare(Object o1, Object o2) {
		int result = 0;
		try {
			if (o1 == null && o2 == null) {
				return 0;
			}
			if (o1 == null) {
				return -1;
			}
			if (o2 == null) {
				return 1;
			}

			String string1 = o1.toString();
			String string2 = o2.toString();

			if (order == Order.DESCENDING) {
				String swap = string1;
				string1 = string2;
				string2 = swap;
			}

			if (!(string1.equals(string2)))
			{
				result = string1.compareTo(string2);

				StringBuilder assertMessage = new StringBuilder();
				assertMessage.append("For " + order + ": Sorting failed: First Argument: \"");
				assertMessage.append(string1);
				assertMessage.append("\" Second Argument: \"");
				assertMessage.append(string2);
				assertMessage.append("\"");

				assertTrue(assertMessage.toString(), result < 0);
			}

		}

		catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}

