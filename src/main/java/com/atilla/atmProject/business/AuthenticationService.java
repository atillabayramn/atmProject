package com.atilla.atmProject.business;

import com.atilla.atmProject.business.requests.UserDto;
import com.atilla.atmProject.business.requests.UserRequest;
import com.atilla.atmProject.business.responses.UserResponse;
import com.atilla.atmProject.dataAccess.UserRepository;
import com.atilla.atmProject.entities.User;
import com.atilla.atmProject.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse save(UserDto userDto){
        UserResponse userResponse = new UserResponse();
        User user = User.builder().userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .balance(userDto.getBalance())
                .role(Role.USER).build();
        userService.saveOneUser(user);

        var token = jwtService.generateToken(user);
        userResponse.setMessage(token);

        return userResponse;
    }

    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("Bearer" + token);
        userResponse.setUserId(user.getId());
        return userResponse;
    }
}
