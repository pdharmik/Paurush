package com.lexmark.services.api;

import java.util.List;

import javax.portlet.ResourceRequest;

public interface LPMDService {
	/**.
	 * 
	 * This method returns the Marketing List if overriden 
	 * 
	 * @param request 
	 * @param partNo 
	 * @return List 
	 * @throws Exception Exception 
	 */
	 List getMarketingList(ResourceRequest request,String partNo) throws Exception;
	
	/**.
	 * 
	 * This method returns the Bullet List if overriden 
	 * 
	 * @param request 
	 * @param partNo 
	 * @return List 
	 * @throws Exception Exception 
	 */
	
	 List getBulletList(ResourceRequest request,String partNo) throws Exception;
	/**.
	 * 
	 * This method returns the Tech Specification List if overriden 
	 * 
	 * @param request 
	 * @param partNo 
	 * @return List 
	 * @throws Exception Exception 
	 */
	 List getTechSpecList(ResourceRequest request,String partNo) throws Exception;

}
