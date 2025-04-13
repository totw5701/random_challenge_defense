package com.rcd.rcdapi.api.dto.challengecard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeCardDetailDTO {
    private Long id;
    private String title;
    private String description;
    private String createDtm;
}
