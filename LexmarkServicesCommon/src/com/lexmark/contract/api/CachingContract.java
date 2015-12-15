package com.lexmark.contract.api;

import java.io.Serializable;

public abstract class CachingContract implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public abstract String getCacheKey();
	
	@Override
    public String toString(){
		return this.getClass().getName() + getCacheKey();
	}
}
