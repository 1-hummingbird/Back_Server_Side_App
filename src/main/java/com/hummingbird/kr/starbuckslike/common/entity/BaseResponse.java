package com.hummingbird.kr.starbuckslike.common.entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus.SUCCESS;

public record BaseResponse<T>(HttpStatusCode httpStatus, Boolean isSuccess, String message, int code, T result) {

    /**
     * 필요값 : Http상태코드, 성공여부, 메시지, 에러코드, 결과값
     * 1. Return 객체가 필요한 경우 -> 성공
     * 2. Return 객체가 필요 없는 경우 -> 성공
     * 3. 요청에 실패한 경우
     */

    /**
     * 1. Return 객체가 필요한 경우 -> 성공
     * @param result
     */
    public BaseResponse(T result) {
        this(HttpStatus.OK, true, SUCCESS.getMessage(), SUCCESS.getCode(), result);
    }

    /**
     * 2. Return 객체가 필요 없는 경우 -> 성공
     */

    public BaseResponse() {
        this(HttpStatus.OK, true, SUCCESS.getMessage(), SUCCESS.getCode(), null);
    }

    /**
     * 3. 요청에 실패한 경우
     * @param status
     */
    public BaseResponse(BaseResponseStatus status) {
        this(status.getHttpStatusCode(), status.isSuccess(), status.getMessage(), status.getCode(), null);
    }

}