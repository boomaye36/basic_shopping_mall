package com.shopping.shopping_mall.api;

import com.shopping.shopping_mall.domain.Comment;
import com.shopping.shopping_mall.dto.CommentDto;
import com.shopping.shopping_mall.dto.ReviewDto;
import com.shopping.shopping_mall.repository.CommentRepository;
import com.shopping.shopping_mall.repository.ReviewRepository;
import com.shopping.shopping_mall.repository.UserRepository;
import com.shopping.shopping_mall.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/comment/{userId}")
    public String createComment(@RequestBody @Valid CommentDto.CommentDtoRequest request,
                                @PathVariable("userId") Long userId){
        return commentService.createComment(request, userId);
    }

}
