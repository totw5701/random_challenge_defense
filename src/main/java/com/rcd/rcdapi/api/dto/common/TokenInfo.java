package com.rcd.rcdapi.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class TokenInfo {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}
