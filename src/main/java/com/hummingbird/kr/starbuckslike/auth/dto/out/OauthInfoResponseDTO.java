package com.hummingbird.kr.starbuckslike.auth.dto.out;

import com.hummingbird.kr.starbuckslike.auth.domain.OauthInfo;
import com.hummingbird.kr.starbuckslike.auth.vo.out.OauthInfoResponseVO;
import java.util.List;
import lombok.Getter;

@Getter
public class OauthInfoResponseDTO {

    private List<OauthInfo> oauthInfoList;

    public OauthInfoResponseDTO(List<OauthInfo> oauthInfoList) {
        this.oauthInfoList = oauthInfoList;
    }

    public OauthInfoResponseVO toVO() {
        return new OauthInfoResponseVO(this.oauthInfoList);
    }
}
