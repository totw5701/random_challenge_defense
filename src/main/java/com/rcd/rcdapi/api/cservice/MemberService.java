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

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(String code) {
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

    public MemberDetailDTO getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, Map.class);

        Long id = (Long) response.getBody().get("id");

        return MemberDetailDTO.builder()
                .id(id)
                .build();
    }

    public TokenInfo processUser(MemberDetailDTO userInfo) {
        Long memberId = userInfo.getId();
        memberRepository.findById(memberId)
                .orElseGet(() -> memberRepository.save(
                        Member.builder().id(memberId).createdDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))).build()
                ));

        return jwtTokenProvider.generateTokenWithId(memberId);
    }

}
