package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.domain.Item;
import com.shopping.shopping_mall.domain.Nice;
import com.shopping.shopping_mall.domain.Users;
import com.shopping.shopping_mall.exception.LikeException;
import com.shopping.shopping_mall.repository.ItemRepository;
import com.shopping.shopping_mall.repository.LikeRepository;
import com.shopping.shopping_mall.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    @Transactional
    public Long create(Item item){
        validateDuplicateMember(item);
        itemRepository.save(item);
        return item.getId();
    }

    private void validateDuplicateMember(Item item){
        Optional<Item> findItem = itemRepository.findItemByName(item.getName());
        if (findItem.isPresent()) {
            throw new IllegalStateException("이미 존재하는 물건입니다.");
        }
    }

    //좋아요
    @Transactional
    public void addLike(Long itemId, Long userId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("해당 아이템이 존재하지 않습니다."));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다."));
        Optional<Nice> likeOptional = likeRepository.findByUsersAndItem(user, item);
        if (likeOptional.isPresent()) {
            throw new LikeException("이미 좋아요를 눌렀습니다.");
            //return;
        }
        Nice nice = new Nice();
        nice.setItem(item);
        nice.setUsers(user);
        likeRepository.save(nice);
        item.setLikeCnt(item.addLike(user));
    }
    @Transactional
    public void removeLike(Long itemId, Long userId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("해당 아이템이 존재하지 않습니다."));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다."));

        item.setLikeCnt(item.removeLike(user));
    }
}
