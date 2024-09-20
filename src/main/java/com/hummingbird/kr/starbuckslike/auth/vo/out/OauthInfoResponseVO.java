package com.hummingbird.kr.starbuckslike.auth.vo.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import com.hummingbird.kr.starbuckslike.auth.domain.OauthInfo;

@Getter
@AllArgsConstructor
public class OauthInfoResponseVO {

    private List<OauthInfo> oauthInfoList;

}
