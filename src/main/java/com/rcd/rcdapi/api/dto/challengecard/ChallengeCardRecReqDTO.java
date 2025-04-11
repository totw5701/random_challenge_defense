package com.rcd.rcdapi.api.dto.challengecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeCardRecReqDTO {

    private Long[] tagIds;
}
