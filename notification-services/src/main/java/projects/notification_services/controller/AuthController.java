package projects.notification_services.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projects.notification_services.dto.LoginRequestDto;
import projects.notification_services.dto.LoginResponseDto;
import projects.notification_services.dto.RegisterDto;
import projects.notification_services.dto.TokenDto;
import projects.notification_services.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> registerUser(@RequestBody @Valid RegisterDto data){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> loginUser(@RequestBody @Valid LoginRequestDto data){
        
        return ResponseEntity.ok(authService.loginUser(data));
    }



}
