package com.rcd.rcdapi.api.dto.common;

import com.rcd.rcdapi.api.advice.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private boolean isSuccess;
    private String code;
    private String msg;
    private T data;

    // 성공 응답 (데이터 포함)
    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .isSuccess(true)
                .code("200")
                .msg("요청에 성공했습니다.")
                .data(data)
                .build();
    }

    // 성공 응답 (데이터 없이)
    public static <T> CommonResponse<T> success() {
        return CommonResponse.<T>builder()
                .isSuccess(true)
                .code("200")
                .msg("정상 처리되었습니다.")
                .build();
    }

    // 실패 응답
    public static CommonResponse fail(ExceptionCode exceptionCode) {
        return CommonResponse.builder()
                .isSuccess(false)
                .code(exceptionCode.getCode())
                .msg(exceptionCode.getDescription())
                .build();
    }

    // 실패 응답
    public static CommonResponse fail(String code, String msg) {
        return CommonResponse.builder()
                .isSuccess(false)
                .code(code)
                .msg(msg)
                .build();
    }
}
