package com.lexmark.exceptionimpl.checked;

/**
 * This class covers all the checked exceptions
 * @author Sourav
 *
 */
public class LGSCheckedException extends Exception{

	/**
	 * Serialization Version Variable.
	 */
	private static final long serialVersionUID = 2465426862672849188L;

	/**
	 * 
	 */
	public LGSCheckedException() {
		super();
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public LGSCheckedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 */
	public LGSCheckedException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public LGSCheckedException(Throwable arg0) {
		super(arg0);
		
	}
}
