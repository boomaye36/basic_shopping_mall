package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.domain.Category;
import com.shopping.shopping_mall.domain.Item;
import com.shopping.shopping_mall.dto.ItemDto;
import com.shopping.shopping_mall.repository.CategoryRepository;
import com.shopping.shopping_mall.repository.ItemRepository;
import com.shopping.shopping_mall.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    @PostMapping("/item")
    public ItemDto.CreateItemResponse saveItem(@RequestBody @Valid ItemDto.CreateItemRequest request){
        Item item = new Item();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setStockQuantity(request.getStockQuantity());
        List<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());
        item.setCategories(categories);

        Long id = itemService.create(item);
        return new ItemDto.CreateItemResponse(id);
    }

    @GetMapping("/item")
    public Result items(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Item> itemPage = itemRepository.findAll(pageable);
        List<ItemDto> result = getItemDtos(itemPage);
        //List<Item> itemList = itemRepository.findAll();
        return new Result(result, result.size());
    }



    @GetMapping("/item/category")
    public Result categoryItems(@RequestParam List<Long> categoryIds){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Item> itemPage = itemRepository.findByCategoryIds(categoryIds, pageable);
        List<ItemDto> result = getItemDtos(itemPage);
        //List<Item> itemList = itemRepository.findAll();
        return new Result(result, result.size());
    }
    @PutMapping("/like/add/{itemId}")
    public void addLike(@RequestParam("userId") Long userId,
                        @PathVariable("itemId") Long itemId){
        itemService.addLike(itemId, userId);
    }
    @PutMapping("/like/remove/{itemId}")
    public void removeLike(@RequestParam("userId") Long userId,
                        @PathVariable("itemId") Long itemId){
        itemService.removeLike(itemId, userId);
    }

    @Data
    @AllArgsConstructor
    public static class Result<T>{
        private T data;
        private int count;
    }
    private static List<ItemDto> getItemDtos(Page<Item> itemPage) {
        List<Item> itemList = itemPage.getContent();
        List<ItemDto> result = new ArrayList<>();
        for (Item item : itemList){
            List<String> categoryNames = item.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
            result.add(new ItemDto(item.getName()
                    ,item.getStockQuantity(), item.getPrice(), item.getLikeCnt(), categoryNames));
        }
        return result;
    }
}
