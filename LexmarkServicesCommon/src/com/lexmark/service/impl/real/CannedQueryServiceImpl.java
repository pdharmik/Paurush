package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.CannedQueryContract;
import com.lexmark.domain.CannedQuery;
import com.lexmark.result.CannedQueriesResult;
import com.lexmark.service.api.CannedQueryService;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;

public class CannedQueryServiceImpl implements CannedQueryService {
	private static Logger LOGGER = LogManager.getLogger(CannedQueryServiceImpl.class);
	private String queryAll="select * from canned_queries";
	private String queryById="select * from canned_queries where canned_id= :cannedId";
	@Override
	public CannedQueriesResult retrieveCannedQueries(
			CannedQueryContract contract) {
		
		try {		
			//LOGGER.debug("contract id="+contract.getCannedId());
			Query query = HibernateUtil.getSession().createSQLQuery(contract.getCannedId()!=-1?queryById:queryAll);
			if(contract.getCannedId()!=-1){
				query.setParameter("cannedId",contract.getCannedId());
			}
			
			
			List results = query.list();
			CannedQueriesResult result=new CannedQueriesResult();
			List<CannedQuery> cannedList=new ArrayList<CannedQuery>();
			result.setCannedQueries(cannedList);
			//LOGGER.debug("result ==" + results + " is empty "+results.isEmpty() );
			if(results!=null && !results.isEmpty()){
				for(int i = 0; i < results.size(); i++){
					///try{
					CannedQuery cannedQuery=new CannedQuery();
					Object[] rowTmp = (Object[]) results.get(i);
					//LOGGER.debug(rowTmp[0].toString());
					cannedQuery.setId(Integer.parseInt(rowTmp[0].toString()));
					cannedQuery.setName(rowTmp[1].toString());
					cannedQuery.setJsonString(rowTmp[2].toString());
					cannedList.add(cannedQuery);
					//}catch(Exception e){
					//	e.printStackTrace();
					//}
				}				
			}
			return result;
				
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
	}

}
