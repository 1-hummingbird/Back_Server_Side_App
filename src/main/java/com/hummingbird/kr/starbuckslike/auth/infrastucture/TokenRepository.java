package com.hummingbird.kr.starbuckslike.auth.infrastucture;

public interface TokenRepository {
    public void registerToken(Object token);
    public Object getToken();
}
