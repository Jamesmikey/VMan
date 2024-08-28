package tz.ac.iact.va.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.ac.iact.va.dto.auth.LoginRequest;
import tz.ac.iact.va.dto.auth.LoginResponse;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.security.JwtService;
import tz.ac.iact.va.service.AuthenticationService;

@Tag(name = "Authentication", description = "")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Value("${tz.ac.iact.va.jwtExpirationMs}")
    private long jwtExpiryMs;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

//    @PostMapping("/signup")
//    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
//        User registeredUser = authenticationService.signup(registerUserDto);
//
//        return ResponseEntity.ok(registeredUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {

        User user = User.builder().email(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();
        User authenticatedUser = authenticationService.authenticate(user);
        String jwtToken = jwtService.generateToken(authenticatedUser.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setName(authenticatedUser.getFullName());
        loginResponse.setUsername(authenticatedUser.getUsername());
        loginResponse.setExpiresIn(System.currentTimeMillis()+jwtExpiryMs);
        return ResponseEntity.ok(loginResponse);

    }
}