package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AmindServiceUtilBuildMdmTest {
	private static final Log logger = LogFactory.getLog(AmindServiceUtilBuildMdmTest.class);
	private static Map<String,String> fieldMap; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fieldMap = new HashMap<String, String>();
		fieldMap.put("mdmLevel1AccountId", "Level1" );
		fieldMap.put("mdmLevel2AccountId", "Level2" );
		fieldMap.put("mdmLevel3AccountId", "Level3" );
		fieldMap.put("mdmLevel4AccountId", "Level4" );
		fieldMap.put("mdmLevel5AccountId", "Level5" );
		fieldMap.put("accountTransFlag","TransFlag");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testBuildMdmSearchExpression() {
		String globalSearchSpec = buildmdmSearchExpression( "mdmId123", GLOBAL_MDMLEVEL, fieldMap, false, false);
		String domesticSearchSpec = buildmdmSearchExpression( "mdmId123", DOMESTIC_MDMLEVEL, fieldMap, false, false);
		String legalSearchSpec = buildmdmSearchExpression( "mdmId123", LEGAL_MDMLEVEL, fieldMap, false, false);
		String accountSearchSpec = buildmdmSearchExpression( "mdmId123", ACCOUNT_MDMLEVEL, fieldMap, false, false);
		String siebelSearchSpec = buildmdmSearchExpression( "mdmId123", SIEBEL_MDMLEVEL, fieldMap, false, false);
		
		logger.debug("accountTransFlag = false");
		logger.debug("includeMdmLevel = false");
		logger.debug("globalSearchSpec: " + globalSearchSpec);
		logger.debug("domesticSearchSpec: " + domesticSearchSpec);
		logger.debug("legalSearchSpec: " + legalSearchSpec);
		logger.debug("accountSearchSpec: " + accountSearchSpec);
		logger.debug("siebelSearchSpec: " + siebelSearchSpec);
	}

	@Test
	public void testBuildMdmSearchExpressionAccountTrans() {
		String globalSearchSpec = buildmdmSearchExpression( "mdmId123", GLOBAL_MDMLEVEL, fieldMap, true, false);
		String domesticSearchSpec = buildmdmSearchExpression( "mdmId123", DOMESTIC_MDMLEVEL, fieldMap, true, false);
		String legalSearchSpec = buildmdmSearchExpression( "mdmId123", LEGAL_MDMLEVEL, fieldMap, true, false);
		String accountSearchSpec = buildmdmSearchExpression( "mdmId123", ACCOUNT_MDMLEVEL, fieldMap, true, false);
		String siebelSearchSpec = buildmdmSearchExpression( "mdmId123", SIEBEL_MDMLEVEL, fieldMap, true, false);

		logger.debug("accountTransFlag = true");
		logger.debug("includeMdmLevel = false");
		logger.debug("globalSearchSpec: " + globalSearchSpec);
		logger.debug("domesticSearchSpec: " + domesticSearchSpec);
		logger.debug("legalSearchSpec: " + legalSearchSpec);
		logger.debug("accountSearchSpec: " + accountSearchSpec);
		logger.debug("siebelSearchSpec: " + siebelSearchSpec);
	}

	@Test
	public void testBuildMdmSearchExpressionIncludeMdmLevel() {
		String globalSearchSpec = buildmdmSearchExpression( "mdmId123", GLOBAL_MDMLEVEL, fieldMap, false, true);
		String domesticSearchSpec = buildmdmSearchExpression( "mdmId123", DOMESTIC_MDMLEVEL, fieldMap, false, true);
		String legalSearchSpec = buildmdmSearchExpression( "mdmId123", LEGAL_MDMLEVEL, fieldMap, false, true);
		String accountSearchSpec = buildmdmSearchExpression( "mdmId123", ACCOUNT_MDMLEVEL, fieldMap, false, true);
		String siebelSearchSpec = buildmdmSearchExpression("mdmId123", SIEBEL_MDMLEVEL, fieldMap, false, true);
		
		logger.debug("accountTransFlag = false");
		logger.debug("includeMdmLevel = true");
		logger.debug("globalSearchSpec: " + globalSearchSpec);
		logger.debug("domesticSearchSpec: " + domesticSearchSpec);
		logger.debug("legalSearchSpec: " + legalSearchSpec);
		logger.debug("accountSearchSpec: " + accountSearchSpec);
		logger.debug("siebelSearchSpec: " + siebelSearchSpec);
	}
	

}
