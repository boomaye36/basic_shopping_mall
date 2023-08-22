package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.domain.Item;
import com.shopping.shopping_mall.domain.Review;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.dto.ReviewDto;
import com.shopping.shopping_mall.repository.ItemRepository;
import com.shopping.shopping_mall.repository.ReviewRepository;
import com.shopping.shopping_mall.repository.UserRepository;
import com.shopping.shopping_mall.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;

    private final ReviewService reviewService;
    @PostMapping("/review")
    public ReviewDto.CreateReviewResponse CreateReview(@RequestBody @Valid ReviewDto.CreateReveiwRequest createReveiwRequest){
        Review review = new Review();
        Users users = userRepository.findUserById(createReveiwRequest.getUserId());
        Optional<Item> itemOptional  = itemRepository.findItemByName(createReveiwRequest.getItemName());
        review.setContent(createReveiwRequest.getContent());
        review.setTitle(createReveiwRequest.getTitle());
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            review.setUser(users);
            review.setItem(item);
            String result = reviewService.createReview(review);
            return new ReviewDto.CreateReviewResponse(result);
        }
        else return new ReviewDto.CreateReviewResponse("아이템이 없습니다.");
    }
    @GetMapping("/review/{item_name}")
    public UserApiController.Result findItemReview(@PathVariable("item_name") String name){
        List<Review> reviews = reviewRepository.findByItem_Name(name);
        List<ReviewDto> collect = new ArrayList<>();
        for (Review review : reviews){
            collect.add(new ReviewDto(review.getUser().getId(), review.getItem().getName()
            ,review.getTitle(), review.getContent(), review.getCreatedAt()));
        }
        return new UserApiController.Result(collect, collect.size());
    }
}
