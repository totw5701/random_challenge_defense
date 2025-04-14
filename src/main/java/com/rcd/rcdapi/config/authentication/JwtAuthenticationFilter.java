package com.rcd.rcdapi.config.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JwtAuthenticationFilter extends GenericFilterBean {

    //private JwtTokenProvider jwtTokenProvider;

//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        ExceptionCode tokenException = null;

        System.out.println("token : " + token);

        if (token == null) {
            tokenException = ExceptionCode.TOKEN_IS_NULL;
        } else if (!jwtTokenProvider.validateToken(token)) {
            tokenException = ExceptionCode.TOKEN_VALIDATION_FAIL;
        } else {
            String tokenType = jwtTokenProvider.getClaimValue(token, "type");
            if ("ATK".equals(tokenType)) {
                // access token인 경우에만 Authentication 객체를 가지고 와서 SecurityContext에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        if (tokenException != null) {
            request.setAttribute("token-exception", tokenException);
        }*/

        Authentication token = new UsernamePasswordAuthenticationToken(
                1L, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }
}