package tr.com.obss.jip2022.jip_backend.service;

import tr.com.obss.jip2022.jip_backend.model.Role;
import tr.com.obss.jip2022.jip_backend.model.enums.RoleType;

import java.util.Set;

public interface RoleService {

    Set<Role> getAllRoles();

    Role findByName(RoleType roleType);

    void createNewRole(Role role);
}
