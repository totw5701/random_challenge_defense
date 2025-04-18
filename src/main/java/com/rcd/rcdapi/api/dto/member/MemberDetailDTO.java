package com.rcd.rcdapi.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailDTO {

    private Long id;
    private String socialId;
    private String createDtm;
}
