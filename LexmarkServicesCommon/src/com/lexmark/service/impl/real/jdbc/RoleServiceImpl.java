package com.lexmark.service.impl.real.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.domain.Role;
import com.lexmark.service.api.RoleService;

public class RoleServiceImpl implements RoleService {
	private static final String HQL_SEARCH_ROLE_LIST = "from Role order by name";

	public List<Role> retrieveRoleList() throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		try {
			Query queryRolesList = HibernateUtil.getSession().createQuery(HQL_SEARCH_ROLE_LIST);
			for(Iterator it = queryRolesList.iterate(); it.hasNext();){
				Role role = (Role) it.next();
				roleList.add(role);
			}
		} catch (HibernateException e) {
			throw new InfrastructureException(e);
		} finally {
			HibernateUtil.closeSession();
		}
		return roleList;
	}
}
