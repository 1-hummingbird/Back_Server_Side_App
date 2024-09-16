package com.hummingbird.kr.starbuckslike.common.Exception;

import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}