package projects.notification_services.config.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projects.notification_services.dto.JWTUserData;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    private final TokenConfig tokenConfig;

    public SecurityFilter (TokenConfig tokenConfig){
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response, 
                        FilterChain filterChain) throws ServletException, IOException {
       
       String authorizedHeader = request.getHeader("Authorization");

       if(Strings.isNotEmpty(authorizedHeader) && authorizedHeader.startsWith("Bearer ")){

        String token = authorizedHeader.substring("Bearer ".length());

        // chama a validação que retorna o Optional
        Optional<JWTUserData> optUser = tokenConfig.validateToken(token);

        // se o Optional estiver presente (ifPresent), executa a autenticação
        if(optUser.isPresent()){
            
            JWTUserData userData = optUser.get();

            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(userData, null, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

       }

        filterChain.doFilter(request, response);
       }

       
    }
