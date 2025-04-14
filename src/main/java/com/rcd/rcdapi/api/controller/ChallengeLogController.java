package com.rcd.rcdapi.api.controller;

import com.rcd.rcdapi.api.dto.challengelog.ChallengeLogDetailDTO;
import com.rcd.rcdapi.api.dto.challengelog.ChallengeLogPkDTO;
import com.rcd.rcdapi.api.dto.challengelog.ChallengeLogMemoUpdReqDTO;
import com.rcd.rcdapi.api.dto.challengelog.ChallengeLogSucReqDTO;
import com.rcd.rcdapi.api.dto.common.CommonResponse;
import com.rcd.rcdapi.api.dto.common.PagingDTO;
import com.rcd.rcdapi.api.cservice.ChallengeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenge-log")
@RequiredArgsConstructor
public class ChallengeLogController {

    private final ChallengeLogService challengeLogService;

    @PostMapping("/do")
    public CommonResponse doChallenge(@RequestBody ChallengeLogPkDTO form) throws Exception {
        challengeLogService.createChallengeLog(form.getChallengeCardId());
        return CommonResponse.success();
    }

    @GetMapping("/on-going")
    public CommonResponse onGoing() {
        List<ChallengeLogDetailDTO> data = challengeLogService.getOngoingChallengeLog();
        return CommonResponse.success(data);
    }

    @GetMapping("/history/{currentPage}")
    public CommonResponse history(@PathVariable(name = "currentPage") String currentPage) {
        PagingDTO<ChallengeLogDetailDTO> data = challengeLogService.getChallengeLogHistory(Integer.parseInt(currentPage));
        return CommonResponse.success(data);
    }

    @PostMapping("/success")
    public CommonResponse success(@RequestBody ChallengeLogSucReqDTO form) throws Exception {
        challengeLogService.successChallenge(form.getChallengeLogId(), form.getMemo());
        return CommonResponse.success();
    }

    @PostMapping("/skip")
    public CommonResponse skip(@RequestBody ChallengeLogPkDTO form) throws Exception {
        challengeLogService.skipChallenge(form.getChallengeCardId());
        return CommonResponse.success();
    }

    @PostMapping("/change-memo")
    public CommonResponse changeMemo(@RequestBody ChallengeLogMemoUpdReqDTO form) throws Exception {
        challengeLogService.changeMemo(form.getChallengeLogId(), form.getMemo());
        return CommonResponse.success();
    }
}
