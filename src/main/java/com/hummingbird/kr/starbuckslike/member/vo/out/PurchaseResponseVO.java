package com.hummingbird.kr.starbuckslike.member.vo.out;

import java.util.List;

public class PurchaseResponseVO {
    private List<Long> purchaseIds;

    public PurchaseResponseVO(List<Long> purchaseIds) {
        this.purchaseIds = purchaseIds;
    }

    public List<Long> getPurchaseIds() {
        return purchaseIds;
    }
}
