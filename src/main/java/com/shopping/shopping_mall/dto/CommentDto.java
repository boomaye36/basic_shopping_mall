package com.shopping.shopping_mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    @Data
    @AllArgsConstructor
    public static class CommentDtoRequest{
        private String type;
        private String content;
        private Long id;
    }
}
