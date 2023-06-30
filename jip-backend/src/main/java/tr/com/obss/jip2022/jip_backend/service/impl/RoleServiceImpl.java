package tr.com.obss.jip2022.jip_backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions.RoleNotFoundException;
import tr.com.obss.jip2022.jip_backend.model.Role;
import tr.com.obss.jip2022.jip_backend.model.enums.RoleType;
import tr.com.obss.jip2022.jip_backend.repositories.RoleRepository;
import tr.com.obss.jip2022.jip_backend.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<Role> getAllRoles() {
        Set<Role> roles = new HashSet<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findByName(RoleType roleType) {
        return roleRepository.findRoleByName(roleType).orElseThrow(() ->
                new RoleNotFoundException("Role not found with name: " + roleType.name()));
    }

    @Override
    public void createNewRole(Role role) {
        roleRepository.save(role);
    }
}
