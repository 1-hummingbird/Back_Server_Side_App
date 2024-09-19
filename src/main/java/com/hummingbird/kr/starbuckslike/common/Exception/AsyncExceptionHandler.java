package com.hummingbird.kr.starbuckslike.common.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    /** 비동기 작업에서 예외가 발생했을 때 처리 */

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        if (ex instanceof BaseException baseException) {
            log.error("BaseException: [{}] {}", baseException.getStatus(), baseException.getStatus().getMessage());
        }
        log.error("EventException: ", ex);
    }

}
