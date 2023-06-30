package tr.com.obss.jip2022.jip_backend.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jip2022.jip_backend.config.utils.JwtTokenUtil;
import tr.com.obss.jip2022.jip_backend.dto.util.CreateNewUserRequest;
import tr.com.obss.jip2022.jip_backend.dto.util.JwtRequest;
import tr.com.obss.jip2022.jip_backend.dto.util.JwtResponse;
import tr.com.obss.jip2022.jip_backend.dto.util.JwtUserDetailsService;
import tr.com.obss.jip2022.jip_backend.service.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/home")
public class JwtAuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        //burasi
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody @Valid CreateNewUserRequest user) {
        userService.createUser(user);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
