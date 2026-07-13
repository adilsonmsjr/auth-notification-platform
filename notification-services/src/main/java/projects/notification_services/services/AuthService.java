package projects.notification_services.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projects.notification_services.config.security.TokenConfig;
import projects.notification_services.dto.LoginRequestDto;
import projects.notification_services.dto.LoginResponseDto;
import projects.notification_services.dto.RegisterDto;
import projects.notification_services.dto.TokenDto;
import projects.notification_services.entity.User;
import projects.notification_services.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;  

    public AuthService(
                        UserRepository userRepository, 
                        PasswordEncoder passwordEncoder, 
                        AuthenticationManager authenticationManager, 
                        TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }


    public LoginResponseDto registerUser(RegisterDto data){
        
        // Valida se o usuário já existe
        if (userRepository.findByEmail(data.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail already in use");
        }

        // Criptografa a senha usando BCrypt
        String encryptedPassword = passwordEncoder.encode(data.password());

        // Cria a entidade com a senha criptografada 
        User newUser = new User();
        newUser.setName(data.name());
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(data.email());

        userRepository.save(newUser);

        LoginResponseDto response = new LoginResponseDto(newUser.getName(), newUser.getEmail());

        return response;
    }

    public TokenDto loginUser(LoginRequestDto data){

        // token com as credenciais enviadas no request
        UsernamePasswordAuthenticationToken authToken = 
                                new UsernamePasswordAuthenticationToken(data.email(), data.password());

        // AuthenticationManager chama o AuthConfig (UserDetailsService) 
        // busca o usuário no banco e valida a senha com BCrypt
        Authentication authentication = authenticationManager.authenticate(authToken);

        //usuario logado
        User user = (User) authentication.getPrincipal();

        //gera token JWT
        String token = tokenConfig.generateToken(user);

        // retorna dto com token
        TokenDto tokenDto = new TokenDto(token, user.getName(), user.getEmail());

        return tokenDto;

    }




}

