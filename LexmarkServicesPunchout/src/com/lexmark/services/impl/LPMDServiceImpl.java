package com.lexmark.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ResourceRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;

import com.lexmark.service.impl.real.jdbc.HibernateUtil4;
import com.lexmark.services.api.LPMDService;

public class LPMDServiceImpl implements LPMDService{
	private static Logger LOGGER = LogManager.getLogger(LPMDServiceImpl.class);
	
	private static final String queryBullet="SELECT bullet.RANK as rank, bullet.BULLET as value " +
	"FROM EF_DAT_APP_EF_M_PILLAR pillar, EF_DAT_APP_EF_M_BULLET bullet " +
	"WHERE pillar.PILLAR_ID = bullet.PILLAR_ID AND pillar.EF_PRODUCT_REVENUE_PID =:partId " +
	"AND COUNTRY_ISOCODE =:countryIsocode and LANGUAGE_ISOCODE =:languageIsocode ORDER BY rank";
	
	private static final String queryMarketing=	"SELECT prod_pn.ef_product_revenue_pid as part_id, " +
			"DECODE(BULLET.bullet, null, 'PPM Information Not available',BULLET.bullet)  as ppm_Info, " +
			"NVL(SCENE7_LINK,'/static/null.gif') as product_image, " +
			"NVL(FILENET_LINK,'/static/null.gif') as family_image, pfs.product_number as part_name, " +
			"availability.country_isocode as country_isocode, availability.language_isocode as language_isocode, " +
			"nvl(prodtype_translation.translation, prodtype.prod_type_name) as category, " +
			"NVL(rpq.description, mkt_name) as name, pfs_curls.curl as url, pfs.product_price as price, " +
			"custom_description as description, prod_pn.PN as part_number, " +
			"general.mkt_paragraph, (SELECT  MAX(b.BUTTON_URL) brochure_url FROM LEXMARK_PRODUCT_LINK L, " +
			"LEXMARK_BUTTON_GENERIC B WHERE L.button_id = b.button_id AND general.ef_product_revenue_pid = l.PRODUCT_ID " +
			"AND general.language_isocode=language_isocode AND BUTTON_LABEL='Printable Product Brochure' ) " +
			"brochure_url, product_for_sale FROM PFS_PRODUCT_PRICING pfs LEFT JOIN pfs_rpq rpq ON rpq.rpq_product_number = pfs.product_number " +
			"JOIN EF_DAT_APP_EF_PROD_PART_NUMBER prod_pn ON ((LTRIM(prod_pn.pn,'0') = pfs.product_number) " +
			"OR (LTRIM(prod_pn.pn,'0') = rpq.base_product_number)) JOIN EF_DAT_APP_EF_PN_AVAILABILITY availability " +
			"ON prod_pn.pn = availability.pn JOIN EF_DAT_APP_EF_M_GENERAL general ON general.ef_product_revenue_pid = prod_pn.ef_product_revenue_pid " +
			"AND availability.country_isocode = general.country_isocode AND availability.language_isocode = general.language_isocode " +
			"JOIN EF_DAT_APP_EF_PRODUCT product ON product.revenue_pid = prod_pn.ef_product_revenue_pid LEFT JOIN EF_DAT_APP_EF_M_PILLAR PILLAR " +
			"ON prod_pn.ef_product_revenue_pid = PILLAR.ef_product_revenue_pid AND PILLAR.country_isocode = general.country_isocode " +
			"AND PILLAR.language_isocode = general.language_isocode	  LEFT JOIN EF_DAT_APP_EF_M_BULLET BULLET ON PILLAR.PILLAR_ID = BULLET.PILLAR_ID " +
			"AND BULLET.bullet like '%ppm%' JOIN EF_DIM_APP_EF_PROD_TYPE prodtype ON product.prod_type_id = prodtype.prod_type_id " +
			"LEFT JOIN EF_DIM_APP_EF_PROD_T_TRANSLAT prodtype_translation ON product.prod_type_id = prodtype_translation.prod_type_id " +
			"AND (prodtype_translation.country_isocode is null OR prodtype_translation.country_isocode =:country) AND (prodtype_translation.language_isocode is null " +
			"OR prodtype_translation.language_isocode =:language) JOIN EF_DAT_APP_EF_PROD_LOC_STATUS localstatus " +
			"ON localstatus.ef_product_revenue_pid = prod_pn.ef_product_revenue_pid AND localstatus.country_isocode = availability.country_isocode " +
			"AND localstatus.announce_date IS NOT NULL AND localstatus.announce_date < SYSDATE JOIN EF_DIM_APP_EF_PROD_STATUS prodstatus " +
			"ON prodstatus.prod_status_id = localstatus.prod_status_id AND prodstatus.prod_status_name IN ('Public','Not Public - B2B') " +
			"LEFT JOIN EF_DAT_APP_EF_LINK link ON prod_pn.ef_product_revenue_pid=link.ef_product_revenue_pid LEFT JOIN pfs_curls " +
			"ON LTRIM(pfs_curls.product_number,'0') = pfs.product_number AND pfs_curls.country_isocode = availability.country_isocode " +
			"AND pfs_curls.language_isocode = availability.language_isocode AND pfs_curls.curl like '%overview%' " +
			"WHERE pfs.customer_number =:customerNumber AND pfs.product_number = :partNumber AND availability.country_isocode =:country AND availability.language_isocode =:language AND " +
			"UPPER(pfs.enabled) != 'N'";	
	
	private static final String techSpec="SELECT NVL(ATT.ATTRIBUTE_TRANSLATION, A.ATTRIBUTE_NAME) ATTRIBUTE, " +
			"(RTRIM(V.VALUE) || ' ' ||  UT.TRANSLATION) VALUE FROM EF_DAT_APP_EF_T_TECH_SPEC_LIST T, EF_DAT_APP_EF_T_ATTRIBUTE A, " +
			"EF_DAT_APP_EF_T_VALUE V,EF_DAT_APP_EF_T_UNIT U, EF_DAT_APP_EF_T_ATTR_TRANSLAT ATT, EF_DAT_APP_EF_T_VALUE_TRANSLAT VT, " +
			"EF_DAT_APP_EF_T_UNIT_TRANSLAT UT WHERE T.EF_PRODUCT_REVENUE_PID =:partId AND A.ATTRIBUTE_ID = T.ATTRIBUTE_ID " +
			"AND ATT.ATTRIBUTE_ID(+) = T.ATTRIBUTE_ID AND ATT.COUNTRY_ISOCODE(+) =:countryIsocode AND ATT.LANGUAGE_ISOCODE(+) =:languageIsocode " +
			"AND V.VALUE_ID = T.VALUE_ID AND VT.VALUE_ID(+) = T.VALUE_ID AND VT.COUNTRY_ISOCODE(+) =:countryIsocode AND VT.LANGUAGE_ISOCODE(+) =:languageIsocode " +
			"AND U.UNIT_ID(+) = A.UNIT_ID AND UT.UNIT_ID(+) = A.UNIT_ID AND UT.COUNTRY_ISOCODE(+) =:countryIsocode AND UT.LANGUAGE_ISOCODE(+) =:languageIsocode " +
			"AND A.attribute_name IN ('Size (in. - H x W x D)','Weight (lb.)', 'Product Certifications') ORDER BY A.ATTRIBUTE_ID";	
	Query query;
	
	
	/**.
	 * 
	 * This method returns the Bullet List if overriden 
	 * 
	 * @param request 
	 * @param partNo 
	 * @return List 
	 * @throws Exception Exception 
	 */
	public List getBulletList(ResourceRequest request,String partNo) throws Exception {
		List list=new ArrayList();
		try {			
			query = HibernateUtil4.getSession().createSQLQuery(queryBullet);
			query.setParameter("partId", partNo);
			query.setParameter("countryIsocode", request.getLocale().getCountry());
			query.setParameter("languageIsocode", request.getLocale().getLanguage());
			 list = query.list();
		} catch (Exception e) {
			LOGGER.debug("Exception occured" + e.getMessage());
			//e.printStackTrace();
		}
		return list;
	}

	/**.
	 * 
	 * This method returns the Marketing List if overriden 
	 * 
	 * @param request 
	 * @param partNo 
	 * @return List 
	 * @throws Exception Exception 
	 */
	public List getMarketingList(ResourceRequest request,String partNo) throws Exception {
		LOGGER.debug("getMarketingList Started");
		List list=new ArrayList();
		try {			
			query = HibernateUtil4.getSession().createSQLQuery(queryMarketing);
			query.setParameter("customerNumber","CUST-KAISER");
			query.setParameter("partNumber", partNo);
			query.setParameter("country", request.getLocale().getCountry());
			query.setParameter("language", request.getLocale().getLanguage());
			 list = query.list();
		} catch (Exception e) {
			LOGGER.debug("Exception occured" + e.getMessage());
			//e.printStackTrace();
		}
		return list;
	}

	/**.
	 * 
	 * This method returns the TechSpec List if overriden 
	 * 
	 * @param request 
	 * @param partNo 
	 * @return List 
	 * @throws Exception Exception 
	 */
	public List getTechSpecList(ResourceRequest request,String partNo) throws Exception {
		List list=new ArrayList();
		try {			
			query = HibernateUtil4.getSession().createSQLQuery(techSpec);
			query.setParameter("partId", partNo);
			query.setParameter("countryIsocode", request.getLocale().getCountry());
			query.setParameter("languageIsocode", request.getLocale().getLanguage());
			 list = query.list();
		} catch (Exception e) {
			LOGGER.debug("Exception occured" + e.getMessage());
			//e.printStackTrace();
		}
		return list;
	}

}
