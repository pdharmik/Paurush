package com.lexmark.service.impl.real;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsDo;

public class MeterReadAssetMeasuresTest 
{
	protected static final Log logger = LogFactory.getLog(MeterReadAssetMeasuresTest.class);
	

	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void testMeterReadAssetMeasures()
	{
		String mdmId = "'L1-A9MDLL";
		Calendar currentDate = GregorianCalendar.getInstance();
		currentDate.add(Calendar.DATE, -30);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String expiredDate = dateFormat.format(currentDate.getTime());
		
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		CrmSessionHandle handle = null;

		try {
			handle = globalService.initCrmSessionHandle(null);
			Session session = ((AmindCrmSessionHandle) handle).acquire();
			IDataManager dataManager = session.getDataManager();

			//setting criteria
			QueryObject criteria = new QueryObject(AssetMeasurementCharacteristicsDo.class,ActionEvent.QUERY);
			StringBuilder expression = new StringBuilder();
			expression.append("[LXK SW FS Asset Measurenment Characteristics.LXK SW Account MDM Legal Entity ID #]");
			expression.append(" = 'L1-A9MDLL' AND " );
			expression.append("([LXK SW FS Asset Measurenment Characteristics.LXK SD Asset Reading Timestamp Max]");
			expression.append(" = " + expiredDate + " OR ");
			expression.append("[LXK SW FS Asset Measurenment Characteristics.LXK SD Asset Reading Timestamp Max] IS Null)");
			criteria.setQueryString(expression.toString());
			logger.debug("IN DataManager Query");
			List<AssetMeasurementCharacteristicsDo> measureCharacteristics = dataManager.query(criteria);
			logger.debug("OUT DataManager Query");
			
		}
		catch (Exception e) {
			logger.error("testUpdateAssetMeterRead caught exception", e);
			throw new RuntimeException(e);
		}
		finally {
			if (handle != null) {
				try {
					Session session = ((AmindCrmSessionHandle) handle).acquire();
					if (session != null) {
						session.release();
					}
				}
				catch (InterruptedException e) {
					//squash
				}
			}
		}
	}
	

}
