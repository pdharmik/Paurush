package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amind.data.source.ISiebelDataSource;
import com.amind.session.Session;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.siebel.data.SiebelDataBean;
import com.siebel.data.SiebelException;

public class ServiceUtil 

{
	protected static final Log logger = LogFactory.getLog(ServiceUtil.class);
	
	public static void getSiebelLogNumber(CrmSessionHandle handle) throws InterruptedException
	{
		Session dataSession = ((AmindCrmSessionHandle) handle).acquire();
		ISiebelDataSource datasource = dataSession.getDataSource();
		SiebelDataBean dataBean = (SiebelDataBean) TestUtils.getField(datasource, "dataBean");
		try 
		{
			  String dataBeanhandle = dataBean.detach();
			  dataBean.attach(dataBeanhandle);
			  String[] logNumbers = dataBeanhandle.split("\\.");
			  String log = logNumbers[logNumbers.length - 2];
			  int number = Integer.parseInt(log,16);
			  logger.info("Siebel Log Number for Session:" + number);
			  ((AmindCrmSessionHandle) handle).release();
		 } 
		
		catch (SiebelException e) 
		{
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		 }
	}
	
	public static void getSiebelLogNumber(Session session) throws InterruptedException
	{
		Session dataSession = session;
		ISiebelDataSource datasource = dataSession.getDataSource();
		SiebelDataBean dataBean = (SiebelDataBean) TestUtils.getField(datasource, "dataBean");
		try 
		{
			  String dataBeanhandle = dataBean.detach();
			  dataBean.attach(dataBeanhandle);
			  String[] logNumbers = dataBeanhandle.split("\\.");
			  String log = logNumbers[logNumbers.length - 2];
			  int number = Integer.parseInt(log,16);
			  logger.info("Siebel Log Number for Session:" + number);
			
		 } 
		
		catch (SiebelException e) 
		{
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		 }
	}

}
