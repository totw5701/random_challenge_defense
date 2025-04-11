package com.rcd.rcdapi.api.advice;

import com.rcd.rcdapi.api.advice.exception.CustomException;
import com.rcd.rcdapi.api.advice.exception.StackTraceCustomException;
import com.rcd.rcdapi.api.dto.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(StackTraceCustomException.class)
    public CommonResponse stackTraceCustomException(StackTraceCustomException e) {
        log.error("code={}, errMsg={}", e.getCode(), e.getMessage(), e.getException());
        return CommonResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public CommonResponse customException(CustomException e) {
        // CustomException은 비즈니스 로직상 필요시 발생시킨 예외로 stackTrace 로그를 찍지 않는다.
        log.info("code={}, errMsg={}", e.getCode(), e.getMessage()); //Todo: 유저 정보도 함게 찍을 것
        return CommonResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse exception(Exception e) {
        log.error("알 수 없는 에러가 발생하였습니다.", e); //Todo: 유저 정보도 함게 찍을 것
        return CommonResponse.fail(ExceptionCode.UNKNOWN_EXCEPTION.getCode(), ExceptionCode.UNKNOWN_EXCEPTION.getDescription());
    }
}
