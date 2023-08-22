package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.domain.Comment;
import com.shopping.shopping_mall.domain.CommentType;
import com.shopping.shopping_mall.dto.CommentDto;
import com.shopping.shopping_mall.dto.Notification;
import com.shopping.shopping_mall.repository.CommentRepository;
import com.shopping.shopping_mall.repository.ReviewRepository;
import com.shopping.shopping_mall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final NotificationService notificationService;
    public String createComment(CommentDto.CommentDtoRequest request, Long userId) {
        String result = "";

        if (request.getType().equals("리뷰")){
            Comment comment = new Comment();
            comment.setContent(request.getContent());
            comment.setUser(userRepository.findUserById(userId));
            comment.setReview(reviewRepository.findReviewById(request.getId()));
            comment.setType(CommentType.Review);

            result = "리뷰 댓글 작성완료";
            commentRepository.save(comment);
            Notification notification = createNotification(comment);
            notificationService.sendNotification(comment.getReview().getUser().getId(), notification);
        }
        else {
            Comment parentComment = commentRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("해당 댓글이 없습니다."));

            Comment childComment = new Comment();
            childComment.setContent(request.getContent());
            childComment.setUser(userRepository.findUserById(userId));
            childComment.setParentComment(parentComment);
            childComment.setType(CommentType.Comment);
            commentRepository.save(childComment);
            result = "대댓글 작성 완료";
        }
        return result;
    }

    private com.shopping.shopping_mall.dto.Notification createNotification(Comment comment){
        String not =comment.getType().equals(CommentType.Review)?
                comment.getReview().getTitle() + "글에 댓글이 달렸습니다." :
                comment.getParentComment().getReview().getTitle() + "글에 작성하진 댓글에 댓글이 달렸습니다.";
        return new Notification(not, comment.getReview().getId());
    }
}
