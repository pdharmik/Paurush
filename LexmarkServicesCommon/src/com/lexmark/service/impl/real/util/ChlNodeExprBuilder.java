package com.lexmark.service.impl.real.util;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static java.lang.String.format;

import com.amind.data.service.IDataManager;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-06-18
 */
public class ChlNodeExprBuilder {
    
    private CHLDo chl;
    private StringBuilder expr = new StringBuilder();
    
    public  ChlNodeExprBuilder(CHLDo chl)  {
        if(chl == null) {
            throw new SiebelCrmServiceException("Chl Domian Object is null");
        }        
        this.chl = chl;
    }
    
    public ChlNodeExprBuilder(IDataManager dataManager, String chlNodeId) {
    	chl = AmindServiceUtil.getParentChainFromCHLNodeId(chlNodeId, dataManager);
        if(chl == null) {
            throw new SiebelCrmServiceException("Chl Domian Object is null");
        }        
    }


    @Override
    public String toString() {
        return expr.toString();
    }
    
    public  ChlNodeExprBuilder and() {
        if (LangUtil.isNotEmpty(expr)) {
            expr.append(" AND ");
        }
        return this;
    }
    
    public  ChlNodeExprBuilder existsBegin() {
        expr.append("EXISTS (");
        return this;
    }
    
    public  ChlNodeExprBuilder existsEnd() {
        expr.append(")");
        return this;
    }
    
    public ChlNodeExprBuilder topLevelAccountIdExpr(String ownerAccountIdField) {
        String topLevelAccountId = chl.getTopLevelAccountId();
        if (isNotEmpty(topLevelAccountId))
        {
            expr.append(format("[%s] = '%s'", ownerAccountIdField, topLevelAccountId));
        }
        return this;
    }
    
    
    public  ChlNodeExprBuilder parentChainExpr(String parentChainField) {
		String parentChain = chl.getParentChain();
		if(isEmpty(parentChain)) {
			throw new SiebelCrmServiceException("No Parent Chain found using CHL Node ID: " + chl.getId());
		}
        expr.append(format("[%s] LIKE '%s*'", parentChainField, parentChain));
        return this;
    }
    
    
    public  ChlNodeExprBuilder assetTypeExpr(String operator, String assetTypeExpr) {
        if (isNotEmpty(assetTypeExpr)) { 
             expr.append(operator).append(assetTypeExpr); 
        }
        return this;
    }
}
