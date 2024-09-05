package com.hummingbird.kr.starbuckslike.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseEntity<T> {

    private HttpStatus status;

    private String message;
    private T data;


}