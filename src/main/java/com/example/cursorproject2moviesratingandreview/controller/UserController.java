package com.example.cursorproject2moviesratingandreview.controller;


import com.example.cursorproject2moviesratingandreview.dto.UserDto;
import com.example.cursorproject2moviesratingandreview.models.User;
import com.example.cursorproject2moviesratingandreview.service.UserService;
import com.example.cursorproject2moviesratingandreview.util.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest auth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        final UserDetails userDetails = userService.login(auth.getUsername(), auth.getPassword());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @RequestMapping(value = "/users/dummy", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken() {
        return ResponseEntity.ok("user");

    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping(
            value = "/admin/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> changeRole(
            @RequestBody final UserDto userDto
    ) {
        User user = userService.getUserByUsername(userDto.getUsername());
        user.setPermissions(userDto.getUserPermission());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> createUser(
            @RequestBody final UserDto userDto
    ) {
        userService.createDtoUser(userDto.getUsername(), userDto.getPassword());
        final User user = userService.getUserByUsername(userDto.getUsername());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Data
    public static class AuthenticationRequest implements Serializable {
        private String username;
        private String password;
    }
}