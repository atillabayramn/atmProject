package com.atilla.atmProject.security;

import com.atilla.atmProject.business.AuthenticationService;
import com.atilla.atmProject.business.UserService;
import com.atilla.atmProject.business.requests.UserDto;
import com.atilla.atmProject.business.requests.UserRequest;
import com.atilla.atmProject.business.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

}
