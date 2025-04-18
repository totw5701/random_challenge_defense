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
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/kakao/callback")
    public CommonResponse<TokenInfo> kakaoCallback(@RequestParam("code") String code) {
        return CommonResponse.success(memberService.login(code));
    }
}
