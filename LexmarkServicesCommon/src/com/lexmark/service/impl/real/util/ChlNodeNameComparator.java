package com.lexmark.service.impl.real.util;

import java.util.Comparator;

import com.lexmark.domain.CHLNode;

/**
 * 
 * @author vpetruchok
 * @version 1.0, Mar 6, 2012
 */
public class ChlNodeNameComparator  implements Comparator<CHLNode> {
	
	/**
	 * {@inheritDoc}
	 */
	public int compare(CHLNode arg0, CHLNode arg1) {
		if (arg0 == null && arg1 == null) {
			return 0;
		}
		if (arg0 == null) {
			return -1;
		}
		if (arg1 == null) {
			return 1;
		}

		String name0 = arg0.getChlNodeName();
		String name1 = arg1.getChlNodeName();
		if (name0 == null && name1 == null) {
			return 0;
		}
		if (name0 == null) {
			return -1;
		}
		if (name1 == null) {
			return 1;
		}
		return name0.compareToIgnoreCase(name1);
	}
}
