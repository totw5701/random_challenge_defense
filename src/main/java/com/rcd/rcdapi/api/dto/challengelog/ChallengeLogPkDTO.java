package com.rcd.rcdapi.api.dto.challengelog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeLogPkDTO {
    private Long challengeCardId;
}
