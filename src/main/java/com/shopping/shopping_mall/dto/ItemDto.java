package com.shopping.shopping_mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ItemDto {

    private String name;
    private int stockQuantity;
    private int price;
    private int likeCnt;
    private List<String> categoryNames;
    @Data

    public static class CreateItemRequest{
        private String name;
        private int stockQuantity;
        private int price;
        private ArrayList<Long> categoryIds;
    }
    @Data
    @AllArgsConstructor

    public static class CreateItemResponse{
        private Long id;
    }
}
