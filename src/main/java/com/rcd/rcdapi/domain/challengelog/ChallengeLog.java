package com.rcd.rcdapi.domain.challengelog;

import com.rcd.rcdapi.api.dto.challengelog.ChallengeLogDetailDTO;
import com.rcd.rcdapi.domain.challengecard.ChallengeCard;
import com.rcd.rcdapi.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChallengeCard challengeCard;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private ChallengeLogStatus status;
    private String memo;
    private String startDtm;
    private String endDtm;


    // detailDto 변환 메서드
    public ChallengeLogDetailDTO toDetailDto() {
        return ChallengeLogDetailDTO.builder()
                .id(this.id)
                .challengeCardId(this.challengeCard.getId())
                .memberId(this.member.getId())
                .status(this.status.getStatus())
                .memo(this.memo)
                .startDtm(this.startDtm)
                .endDtm(this.endDtm)
                .build();
    }

    // ChallengeCard -> ChallengeLog 변환 메서드
    public static ChallengeLog createFromCard(ChallengeCard card, Member member) {
        String nowDtm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return ChallengeLog.builder()
                .challengeCard(card)
                .status(ChallengeLogStatus.READY)
                .member(member)
                .startDtm(nowDtm)
                .build();
    }

    // 챌린지 성공 처리
    public void success(String memo) {
        this.status = ChallengeLogStatus.END;
        this.memo = memo;
        this.endDtm = getNowDtm();
    }

    // 메모 업데이트
    public void changeMemo(String memo) {
        this.memo = memo;
    }

    // 챌린지 보류 처리
    public void skip() {
        this.status = ChallengeLogStatus.HOLD;
    }

    // 현재 시간을 yyyyMMddHHmmss 형태로 받아온다.
    public String getNowDtm() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

}
