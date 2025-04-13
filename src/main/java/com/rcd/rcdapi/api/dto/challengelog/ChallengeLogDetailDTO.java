package com.rcd.rcdapi.api.dto.challengelog;

import com.rcd.rcdapi.domain.challengecard.ChallengeCard;
import com.rcd.rcdapi.domain.challengelog.ChallengeLogStatus;
import com.rcd.rcdapi.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeLogDetailDTO {
    private Long id;

    private Long challengeCardId;
    private Long memberId;

    private String status;
    private String memo;
    private String startDtm;
    private String endDtm;
}
