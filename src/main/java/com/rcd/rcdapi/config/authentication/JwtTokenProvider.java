package com.rcd.rcdapi.config.authentication;

import com.rcd.rcdapi.api.dto.common.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

    }

    /**
     * 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
     */
    public TokenInfo generateTokenWithId(Long id) {

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + 86400000);
        String accessToken = Jwts.builder()
                //.setSubject((String) att.get("email"))
                .claim("id", id)
                .claim("type", "ATK")
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                //.setSubject((String) att.get("email"))
                .claim("id", id)
                .claim("type", "RTK")
                .setExpiration(new Date(now + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT Token" + e);
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT Token" + e);
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT Token" + e);
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty." + e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // Request Header 에서 토큰 정보 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getClaimValue(String token, String value) {
        Claims claims = parseClaims(token);
        Object claimValue = claims.get(value);
        if (claimValue instanceof String) {
            return (String) claimValue;
        } else if (value.equals("sub") && claimValue instanceof Integer) { // 'sub' 클레임 값이 숫자형으로 들어온 경우 처리
            return String.valueOf(claimValue);
        }
        return null;
    }

}
