package com.lexmark.services.hook;

import com.amind.session.StatelessSessionFactory;

public class aMindStatelessSessionFactory extends StatelessSessionFactory {

	//private static StatelessSessionFactory instance = new StatelessSessionFactory();
	private static StatelessSessionFactory instance = (StatelessSessionFactory) new aMindStatelessSessionFactory();
	private static int id;
	
	/**
	 * @return StatelessSessionFactory 
	 */
	public static StatelessSessionFactory getInstance() {
		return instance ;
	}
	
	/**
	 * @return int 
	 */
	public int getNextId() {
		return id++; 
	}
}
