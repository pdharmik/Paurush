package com.lexmark.util;

public class KeyGenerator {
	public static String generateKey(String... args){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<args.length;i++){
			if(args[i]!=null){
				sb.append(args[i]);
			}
		}
		return sb.toString();
	}
}
