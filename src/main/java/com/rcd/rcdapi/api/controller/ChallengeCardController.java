package com.rcd.rcdapi.api.controller;

import com.rcd.rcdapi.api.dto.challengecard.ChallengeCardDetailDTO;
import com.rcd.rcdapi.api.dto.challengecard.ChallengeCardRecReqDTO;
import com.rcd.rcdapi.api.dto.common.CommonResponse;
import com.rcd.rcdapi.api.cservice.ChallengeCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge-card")
@RequiredArgsConstructor
public class ChallengeCardController {

    private final ChallengeCardService challengeCardService;

    @GetMapping("/recommend")
    public CommonResponse<ChallengeCardDetailDTO> recommendChallengeCard(@RequestBody ChallengeCardRecReqDTO form) throws Exception {
        ChallengeCardDetailDTO result = challengeCardService.getRandomChallengeCard(form.getTagIds());
        return CommonResponse.success(result);
    }

}
