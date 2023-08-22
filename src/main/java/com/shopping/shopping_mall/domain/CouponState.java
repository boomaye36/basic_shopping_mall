package com.shopping.shopping_mall.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponState {
    EXPIRED, USED, SELECTED, USABLE
}
