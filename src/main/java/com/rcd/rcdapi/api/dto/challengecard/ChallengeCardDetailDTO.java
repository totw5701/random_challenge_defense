package com.rcd.rcdapi.api.dto.challengecard;

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
public class ChallengeCardDetailDTO {
    private Long id;

    private List<TagDetailDTO> tags;

    private String title;
    private String description;
    private String createDtm;

}
