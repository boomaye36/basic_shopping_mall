package com.shopping.shopping_mall.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentType {
    Review("리뷰댓글"), Comment("대댓글");

    private final String description;

}
