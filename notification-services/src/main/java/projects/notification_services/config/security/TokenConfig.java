package projects.notification_services.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import projects.notification_services.dto.JWTUserData;
import projects.notification_services.entity.User;


@Service  
public class TokenConfig {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withClaim("userId", user.getId()) // Adiciona o ID do usuário como claim
                    .withIssuer("auth-api") // Identificador de quem gerou o token
                    .withSubject(user.getEmail()) // Dono do token (o username)
                    .withExpiresAt(genExpirationDate()) // Tempo de expiração (Ex: 2 horas)
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token JWT", exception);
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public Optional<JWTUserData> validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                                    .withIssuer("auth-api")
                                    .build()
                                    .verify(token);            
            
            return Optional.of(JWTUserData.builder()
                            .id(decodedJWT.getClaim("userId").asLong())
                            .email(decodedJWT.getSubject())
                            .build());

        } catch (JWTVerificationException exception) {
            return Optional.empty(); // Se o token for inválido, retorna vazio para barrar o acesso
        }
    } 

}
