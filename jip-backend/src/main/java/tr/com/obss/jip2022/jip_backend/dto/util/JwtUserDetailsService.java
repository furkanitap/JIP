package tr.com.obss.jip2022.jip_backend.dto.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.jip_backend.model.Role;
import tr.com.obss.jip2022.jip_backend.model.User;
import tr.com.obss.jip2022.jip_backend.service.UserService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                rolesToAuthority(user.getRoles()));
    }

    public static Collection<? extends GrantedAuthority> rolesToAuthority(Set<Role> roles) {
        final Set<String> stringRoles = roles.stream().map(role -> role.getName().name()).collect(Collectors.toSet());
        return stringRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
