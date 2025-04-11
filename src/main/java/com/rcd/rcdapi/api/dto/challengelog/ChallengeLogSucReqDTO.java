package com.rcd.rcdapi.api.dto.challengelog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeLogSucReqDTO {
    private Long challengeLogId;
    private String memo;
}
