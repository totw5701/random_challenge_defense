package com.rcd.rcdapi.api.controller;

import com.rcd.rcdapi.api.cservice.MemberService;
import com.rcd.rcdapi.api.dto.common.CommonResponse;
import com.rcd.rcdapi.api.dto.common.TokenInfo;
import com.rcd.rcdapi.api.dto.member.MemberDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/kakao/callback")
    public CommonResponse<TokenInfo> kakaoCallback(@RequestParam("code") String code) {
        // 인가 코드로 토큰 받기
        String accessToken = memberService.getAccessToken(code);

        // 사용자 정보 조회
        MemberDetailDTO userInfo = memberService.getUserInfo(accessToken);

        // 사용자 회원가입 or 로그인 처리 후 토큰 발급
        TokenInfo tokenInfo = memberService.processUser(userInfo);
        return CommonResponse.success(tokenInfo);
    }
}
