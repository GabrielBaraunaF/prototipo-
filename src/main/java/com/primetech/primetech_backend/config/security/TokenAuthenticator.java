package com.primetech.primetech_backend.config.security;


import com.primetech.primetech_backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenAuthenticator {
    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "MySecret";
    public static final String HEADER_STRING = "Token";

    private static Logger log = LogManager.getLogger(TokenAuthenticator.class);

    public static String addAuthentication(Authentication auth) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;

        String tokenStr = null;

        String principal = auth.getPrincipal() == null ? "anonymous" : auth.getPrincipal().toString();

        User user = (auth.getPrincipal() instanceof User) ? (User) auth.getPrincipal() : null;


        Map<String, Object> claims = new HashMap<>();


        String authorities = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        claims.put("authorities", authorities);
        assert user != null;
        claims.put("username", user.getUsername());


        tokenStr = Jwts.builder().setClaims(claims).setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        return tokenStr;

    }

    public static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            try {
                // Cria um JwtParser
                JwtParser parser = Jwts.parser()
                        .setSigningKey(SECRET);

                // Analisa o token
                Claims claims = parser.parseClaimsJws(token).getBody();

                String user = claims.getSubject();

                if (user != null) {
                    String roles = claims.get("authorities", String.class);
                    List<GrantedAuthority> grantedAuths = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

                    return new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
                }

            } catch (Exception e) {
                log.error("EXCEPTION in getAuthentication: ", e);
                return null;
            }
        }
        return null;

    }

    private static Claims getBody(String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            } catch (Exception exception) {
                return null;
            }
        }
        return null;
    }
}

