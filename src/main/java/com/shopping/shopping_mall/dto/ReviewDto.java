package com.shopping.shopping_mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long userId;
    private String itemName;
    private String title;
    private String content;
    private LocalDateTime time;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReveiwRequest{
        private Long userId;
        private String itemName;
        private String title;
        private String content;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReviewResponse{
        private String title;
    }
}
