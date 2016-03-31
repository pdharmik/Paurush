package com.lexmark.util;

public class KeyGenerator {
	public static String generateKey(String... args){
		int lengthArgs=args.length;
		StringBuilder sb=new StringBuilder(40*lengthArgs);
		for(int i=0;i<lengthArgs;i++){
			if(args[i]!=null){
				sb.append(args[i]);
			}
		}
		return sb.toString();
	}
}
