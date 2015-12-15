package com.lexmark.service.api;

import java.util.List;

import com.lexmark.domain.Role;

public interface RoleService {
	public List<Role> retrieveRoleList() throws Exception;
}
