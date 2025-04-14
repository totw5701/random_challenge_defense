package com.rcd.rcdapi.api.cservice;

import com.rcd.rcdapi.api.advice.ExceptionCode;
import com.rcd.rcdapi.api.advice.exception.CustomException;
import com.rcd.rcdapi.api.dto.challengelog.ChallengeLogDetailDTO;
import com.rcd.rcdapi.api.dto.common.PagingDTO;
import com.rcd.rcdapi.domain.challengecard.ChallengeCard;
import com.rcd.rcdapi.domain.challengecard.ChallengeCardRepository;
import com.rcd.rcdapi.domain.challengelog.ChallengeLog;
import com.rcd.rcdapi.domain.challengelog.ChallengeLogRepository;
import com.rcd.rcdapi.domain.challengelog.ChallengeLogStatus;
import com.rcd.rcdapi.domain.member.Member;
import com.rcd.rcdapi.domain.member.MemberRepository;
import com.rcd.rcdapi.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeLogService {

    private final ChallengeCardRepository challengeCardRepository;
    private final ChallengeLogRepository challengeLogRepository;
    private final MemberRepository memberRepository;

    /**
     * 챌린지 도전
     */
    public void createChallengeLog(Long challengeCardId) throws Exception {
        Long memberId = SecurityUtil.getLoginMemberId();

        long ongoingCartCnt = challengeLogRepository.countByStatusAndMember_Id(ChallengeLogStatus.READY.getStatus(), memberId);
        if(ongoingCartCnt >= 5) { //Todo: 옵션으로 뺄 것.
            throw new CustomException(ExceptionCode.SERVICE_USAGE_LIMIT_EXCEEDED);
        }

        ChallengeCard challengeCard = challengeCardRepository.findById(challengeCardId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_CHALLENGE_CARD));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER));

        ChallengeLog log = ChallengeLog.createFromCard(challengeCard, member);
        challengeLogRepository.save(log);
    }

    /**
     * 챌린지 성공 처리
     */
    public void successChallenge(Long challengeLogId, String memo) throws Exception {
        Long memberId = SecurityUtil.getLoginMemberId();
        ChallengeLog log = getOwnedLog(memberId, challengeLogId);
        log.success(memo);
    }

    /**
     * 성공한 챌린지의 메모를 변경한다.
     */
    public void changeMemo(Long challengeLogId, String memo) throws Exception {
        Long memberId = SecurityUtil.getLoginMemberId();
        ChallengeLog log = getOwnedLog(memberId, challengeLogId);
        log.changeMemo(memo);
    }

    /**
     * 챌린지를 보류한다.
     */
    public void skipChallenge(Long challengeLogId) throws Exception {
        Long memberId = SecurityUtil.getLoginMemberId();
        ChallengeLog log = getOwnedLog(memberId, challengeLogId);
        if(log.getStatus() == ChallengeLogStatus.END) {
            throw new CustomException(ExceptionCode.INVALID_REQUEST);
        }
        log.skip();
    }

    /**
     * 챌린지 이력을 페이징 하여 받아온다.
     */
    @Transactional(readOnly = true)
    public PagingDTO<ChallengeLogDetailDTO> getChallengeLogHistory(Integer nowPage) {
        Long memberId = SecurityUtil.getLoginMemberId();

        Pageable pageable = PageRequest.of(nowPage, 15, Sort.by("endDtm").descending()); //Todo: 출력 수 옵션으로 뺄 것.
        Page<ChallengeLog> page = challengeLogRepository.findAllByMember_Id(memberId, pageable);
        List<ChallengeLogDetailDTO> contents = page.stream().map(ChallengeLog::toDetailDto).collect(Collectors.toList());

        return PagingDTO.<ChallengeLogDetailDTO>builder()
                .currentPage(page.getNumber() + 1)
                .totalPage(page.getTotalPages())
                .pageSize(page.getSize())
                .totalCount(page.getTotalElements())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .contents(contents)
                .build();
    }

    /**
     * 현재 진행중인 챌린지 리스트를 조회한다.
     */
    @Transactional(readOnly = true)
    public List<ChallengeLogDetailDTO> getOngoingChallengeLog() {
        Long memberId = SecurityUtil.getLoginMemberId();
        return challengeLogRepository.findAllByStatusAndMember_Id(ChallengeLogStatus.READY.getStatus(), memberId)
                .stream().map(ChallengeLog::toDetailDto).collect(Collectors.toList());
    }

    /**
     * 챌린지 로그를 조회하고 사용자의 소유가 맞는지 검증한다.
     */
    private ChallengeLog getOwnedLog(Long memberId, Long challengeLogId) {
        ChallengeLog log = challengeLogRepository.findById(challengeLogId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_CHALLENGE_LOG));
        if(!memberId.equals(log.getMember().getId())) {
            throw new CustomException(ExceptionCode.ACCESS_DENIED);
        }
        return log;
    }

}
