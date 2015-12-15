package com.lexmark.service.impl.real.util;

import java.util.HashMap;
import java.util.List;

import com.amind.common.domain.BaseEntity;
import com.amind.data.service.ContextInfo;
import com.amind.data.service.IDataManager;
import com.amind.data.service.InvokeMethodRequest;
import com.amind.data.service.QueryObject;

/**
 * Maybe better use some mock library (Easymock, Mockito, JMock).
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-03
 */
public class MockDataManager implements IDataManager {

   private BaseEntity queyrByIdResult;

   public void setQueryByIdResult(BaseEntity baseEntity) {
       this.queyrByIdResult = baseEntity;
   }
    
    
    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void exit() {
    }

    @Override
    public List query(QueryObject queryReq) {
        return null;
    }

    @Override
    public List queryNext(QueryObject queryReq) {
        return null;
    }

    @Override
    public boolean hasNext(QueryObject queryReq) {
        return false;
    }

    @Override
    public void update(QueryObject updateReq) {
    }

    @Override
    public BaseEntity queryById(Class doClass, String primaryId) {
        return queyrByIdResult;
    }

    @Override
    public BaseEntity queryById(Class doClass, String primaryId, ContextInfo context) {
        return null;
    }

    @Override
    public BaseEntity queryByPrimaryId(Class doClass, String primaryId) {
        return null;
    }

    @Override
    public BaseEntity queryByPrimaryId(Class doClass, String primaryId, ContextInfo context) {
        return null;
    }

    @Override
    public BaseEntity queryByUserKey(Class doClass, String userKeyField, String userKeyFieldValue) {
        return null;
    }

    @Override
    public BaseEntity queryByUserKey(Class doClass, String userKeyField, String userKeyFieldValue, ContextInfo context) {
        return null;
    }

    @Override
    public BaseEntity queryByMultipleUserKey(Class doClass, HashMap<String, String> queryFieldToValMap) {
        return null;
    }

    @Override
    public BaseEntity queryByMultipleUserKey(Class doClass, HashMap<String, String> queryFieldToValMap, ContextInfo context) {
        return null;
    }

    @Override
    public List queryBySearchExpression(Class doClass, String searchSpec) {
        return null;
    }

    @Override
    public List queryBySearchExpression(Class doClass, String searchSpec, ContextInfo context) {
        return null;
    }

    @Override
    public List transformFromResultSet(Class doClass, Object resultSet) {
        return null;
    }

    @Override
    public List queryByExample(BaseEntity domainObj) {
        return null;
    }

    @Override
    public List queryByExample(BaseEntity domainObj, ContextInfo context) {
        return null;
    }

    @Override
    public void create(BaseEntity domainObj) {
    }

    @Override
    public void update(BaseEntity domainObj) {
    }

    @Override
    public void delete(BaseEntity domainObj) {
    }

    @Override
    public String invokeBCMethod(InvokeMethodRequest request) {
        return null;
    }

    @Override
    public void setProfileAttribute(String name, String value) {
    }


	@Override
	public void startEaiTransaction(boolean checkForPendingTransaction) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void cancelEaiTransaction(boolean checkForPendingTransaction) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void endEaiTransaction(boolean checkForPendingTransaction) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isEaiTransactionPending() {
		// TODO Auto-generated method stub
		return false;
	}

}
