package com.rcd.rcdapi.api.cservice;

import com.rcd.rcdapi.api.dto.common.TokenInfo;
import com.rcd.rcdapi.api.dto.member.MemberDetailDTO;
import com.rcd.rcdapi.config.authentication.JwtTokenProvider;
import com.rcd.rcdapi.domain.member.Member;
import com.rcd.rcdapi.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUri;

    @Value("${kakao.user-info-uri}")
    private String userInfoUri;

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final RestTemplate restTemplate;

    public TokenInfo login(String code) {
        // 인가 코드로 토큰 받기
        String accessToken = getAccessToken(code);

        // 사용자 정보 조회
        MemberDetailDTO userInfo = getUserInfo(accessToken);

        // 사용자 회원가입 or 로그인 처리 후 토큰 발급
        return processUser(userInfo);
    }

    private String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUri, request, Map.class);

        return (String) response.getBody().get("access_token");
    }

    private MemberDetailDTO getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, Map.class);

        Long id = (Long) response.getBody().get("id");

        return MemberDetailDTO.builder()
                .socialId(String.valueOf(id))
                .build();
    }

    private TokenInfo processUser(MemberDetailDTO userInfo) {
        String socialId = userInfo.getSocialId();
        Long memberId = upsertMember(socialId);
        return jwtTokenProvider.generateTokenWithId(memberId);
    }

    private Long upsertMember(String socialId) {
        return memberRepository.findBySocialId(socialId)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .socialId(socialId)
                                .socialType("KAKAO")
                                .createdDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                                .build()
                )).getId();
    }
}
