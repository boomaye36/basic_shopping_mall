package com.shopping.shopping_mall.service;

import com.shopping.shopping_mall.domain.*;
import com.shopping.shopping_mall.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.categoryInit();

        initService.dbInit1();
        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final CategoryRepository categoryRepository;
        private final EntityManager em;
        public void dbInit1(){
            Users user = createUser("userA", "서울", "1", "1111");
            em.persist(user);
            createCoupon(user, 0.3f, CouponState.USED);
            Coupon coupon = createCoupon(user, 0.2f, CouponState.SELECTED);
            List<Category> categories = categoryRepository.findByIdIn(Arrays.asList(1L, 2L));

            Item item1 = createItem("item1", 10000, 100, categories);

//            for (Category category : item1.getCategories()) {
//                System.out.println("item categories = " + category.getName());
//            }
            em.persist(item1);

//            Category category1 = categoryRepository.findById(2L)
//                    .orElseThrow(() -> new EntityNotFoundException("카테고리가 존재하지 않습니다. categoryId: " + 2L));
            List<Category> categories1 = categoryRepository.findByIdIn(Arrays.asList(3L, 2L));

            Item item2 = createItem("item2", 12000, 100, categories1);
            em.persist(item2);

            OrderItem orderItem1 = OrderItem.createOrderitem(item1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderitem(item2, 12000, 2);

            Delivery delivery = createDelivery(user, "롯데");
            Order order = Order.createOrder(user, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2(){
            Users user = createUser("userB", "전주", "2", "3222");
            em.persist(user);

            //Category category = categoryRepository.findById(3L)
//                    .orElseThrow(() -> new EntityNotFoundException("카테고리가 존재하지 않습니다. categoryId: " + 3L));
            List<Category> categories = categoryRepository.findByIdIn(Arrays.asList(3L, 4L));

            Item book1 = createItem("Spring1 Book", 20000, 100, categories);
            em.persist(book1);
//            Category category1 = categoryRepository.findById(4L)
//                    .orElseThrow(() -> new EntityNotFoundException("카테고리가 존재하지 않습니다. categoryId: " + 4L));
            List<Category> categories1 = categoryRepository.findByIdIn(Arrays.asList(1L, 4L));

            Item book2 = createItem("Spring2 Book", 42000, 100, categories1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderitem(book1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderitem(book2, 42000, 4);

            Delivery delivery = createDelivery(user, "한진");
            Order order = Order.createOrder(user, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
        public void categoryInit(){
            Category category1 = createCategory("상의");
            em.persist(category1);
            Category category2 = createCategory("하의");
            em.persist(category2);
            Category category3 = createCategory("가방");
            em.persist(category3);
            Category category4 = createCategory("악세사리");
            em.persist(category4);
        }

        private static Delivery createDelivery(Users member, String name) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setName(name);
            return delivery;
        }

        private static Item createItem(String name, int price, int stockQuantity, List<Category> categories) {
            Item item= new Item();
            item.setName(name);
            item.setPrice(price);
            item.setStockQuantity(stockQuantity);
            item.setCategories(categories);

            return item;
        }
    }

    private static Users createUser(String name, String city, String street, String zipcode) {
        Users member = new Users();
        member.setName(name);
        member.setAddress(new Address(city, street, zipcode));
        return member;
    }

    private static Category createCategory(String name){
        Category category = new Category();
        category.setName(name);
        return category;
    }

    private static Coupon createCoupon(Users user, float percent, CouponState couponState){
        Coupon coupon = new Coupon();
        coupon.setUsers(user);
        coupon.setPercent(percent);
        coupon.setCouponState(couponState);
        return coupon;
    }
}
