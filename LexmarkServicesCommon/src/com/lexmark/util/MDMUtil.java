package com.lexmark.util;

import static com.lexmark.constants.LexmarkConstants.MDM_LEVEL_ACCOUNT;
import static com.lexmark.constants.LexmarkConstants.MDM_LEVEL_DOMESTIC;
import static com.lexmark.constants.LexmarkConstants.MDM_LEVEL_GLOBAL;
import static com.lexmark.constants.LexmarkConstants.MDM_LEVEL_LEGAL;
import static com.lexmark.constants.LexmarkConstants.MDM_LEVEL_SIEBEL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.Role;

public class MDMUtil {
	public static boolean isRoleMatch(List<String> roles1, List<Role> roles2) {
		for (Role role1 : roles2) {
			for (String role2 : roles1) {
				if(role1.getName().equals(role2)){
					return true;
				}
			}
		}
		return false;
	}
	
	private static Map<String, Integer> numericeMDMLevels = new HashMap<String, Integer>();
	static {

		numericeMDMLevels.put(MDM_LEVEL_GLOBAL, 5);
		numericeMDMLevels.put(MDM_LEVEL_DOMESTIC, 4);
		numericeMDMLevels.put(MDM_LEVEL_LEGAL, 3);
		numericeMDMLevels.put(MDM_LEVEL_ACCOUNT, 2);
		numericeMDMLevels.put(MDM_LEVEL_SIEBEL, 1);

	}

	public static int compareMDMLevel(String level1, String level2) {
		int l1 = mdmLevelToInt(level1);
		int l2 = mdmLevelToInt(level1);
		return l1-l2;
	}

	private static Integer mdmLevelToInt(String level1) {
		Integer integer = numericeMDMLevels.get(level1);
		return integer == null ? 0 : integer.intValue();
	}
}
