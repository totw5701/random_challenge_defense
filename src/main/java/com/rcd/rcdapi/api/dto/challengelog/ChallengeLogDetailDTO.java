package com.rcd.rcdapi.api.dto.challengelog;

import com.rcd.rcdapi.api.dto.challengecard.ChallengeCardDetailDTO;
import com.rcd.rcdapi.api.dto.tag.TagDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeLogDetailDTO {
    private Long id;

    private Long memberId;
    private ChallengeCardDetailDTO challengeCard;
    private List<TagDetailDTO> tags;

    private String status;
    private String memo;
    private String startDtm;
    private String endDtm;
}
