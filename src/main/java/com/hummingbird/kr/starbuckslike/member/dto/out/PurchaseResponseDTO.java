package com.hummingbird.kr.starbuckslike.member.dto.out;

import com.hummingbird.kr.starbuckslike.member.vo.out.PurchaseResponseVO;
import java.util.List;

public class PurchaseResponseDTO {
    private List<Long> purchaseIds;

    public PurchaseResponseDTO(List<Long> purchaseIds) {
        this.purchaseIds = purchaseIds;
    }

    public List<Long> getPurchaseIds() {
        return purchaseIds;
    }

    public PurchaseResponseVO toVO() {
        return new PurchaseResponseVO(purchaseIds);
    }
}
