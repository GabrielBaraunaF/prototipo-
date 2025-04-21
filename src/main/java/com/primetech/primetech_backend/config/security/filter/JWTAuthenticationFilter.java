package com.primetech.primetech_backend.config.security.filter;

import com.primetech.primetech_backend.config.security.TokenAuthenticator;
import com.primetech.primetech_backend.config.security.WebSecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(req)) {

            Authentication authentication = TokenAuthenticator.getAuthentication(req);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication != null && authentication.isAuthenticated()) {
                filterChain.doFilter(req, res);
            }

            if((req).getServletPath().contains("swagger") || (req).getServletPath().contains("api-docs")
            ){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(req, res);
            }

            res.setStatus(HttpStatus.UNAUTHORIZED.value());
        }else{
            filterChain.doFilter(req, res);
        }
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(WebSecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}
