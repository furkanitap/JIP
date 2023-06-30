package tr.com.obss.jip2022.jip_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions.UserNotFoundException;
import tr.com.obss.jip2022.jip_backend.model.Role;
import tr.com.obss.jip2022.jip_backend.model.User;
import tr.com.obss.jip2022.jip_backend.model.enums.RoleType;
import tr.com.obss.jip2022.jip_backend.service.RoleService;
import tr.com.obss.jip2022.jip_backend.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class AppInitConfig {
    private final RoleService roleService;
    private final UserService userService;
    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            final List<RoleType> allRoles = roleService.getAllRoles()
                    .stream().map(Role::getName).collect(Collectors.toList());

            Arrays.stream(RoleType.values()).filter(roleType -> !allRoles.contains(roleType)).forEach(roleType -> {
                Role role = new Role();
                role.setName(roleType);
                roleService.createNewRole(role);
            });

            try {
                final User adminUser = userService.findUserByUsername("admin");
            } catch (UserNotFoundException e) {
                User adminUser = new User();
                adminUser.setName("System");
                adminUser.setUsername("admin");
                adminUser.setPassword("admin");
                adminUser.setRoles(roleService.getAllRoles());
                userService.createUser(adminUser);
            }
        };

    }
}
