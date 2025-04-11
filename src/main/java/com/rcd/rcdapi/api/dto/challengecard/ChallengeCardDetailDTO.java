package com.rcd.rcdapi.api.dto.challengecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeCardDetailDTO {
    private Long id;
    private String title;
    private String description;
    private String createDtm;
}
