package com.bilal.store.services;

import com.bilal.store.entities.*;
import com.bilal.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");


    }

    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getBio());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    public void persistRelated() {
         var user = User.builder()
                 .name("Name")
                 .email("email@email.com")
                 .password("password")
                 .build();

         var address = Address.builder()
                 .state("state")
                 .street("street")
                 .zip("zip")
                 .city("city")
                 .build();

         user.addAddress(address);

         userRepository.save(user);
    }

    public void deleteRelated() {
        userRepository.deleteById(2L);
    }

    @Transactional
    public void createProduct() {
        var category = categoryRepository.findById((byte)1).orElseThrow();

        var product = Product.builder()
                .name("product 2")
                .description("description 2")
                .price(BigDecimal.valueOf(4.50))
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void addProductsToUserWishlist() {
        var user = userRepository.findById(5L).orElseThrow();

        var products = productRepository.findAll();

        products.forEach(user::addFavoriteProduct);

        userRepository.save(user);
    }

    @Transactional
    public void deleteProductAndWishlist() {
        productRepository.deleteById(1L);
    }

//    @Transactional
//    public void deleteRelated() {
//        var user = userRepository.findById(3L).orElseThrow();
//        var address = user.getAddresses().getFirst();
//        user.removeAddress(address);
//        userRepository.save(user);
//    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1);
    }

    @Transactional
    public void fetchProducts() {
//        var products = productRepository.findByCategory(new Category((byte)1));
//        var products = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(20));
//        products.forEach(System.out::println);
        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id","description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("bilal@yahoo.com").orElseThrow();
        System.out.println(user.getId());
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void finalExercise() {
//        var user1 = User.builder()
//                .name("User 1")
//                .email("email1@example.com")
//                .password("password1")
//                .build();
//        var user2 = User.builder()
//                .name("User 2")
//                .email("email2@example.com")
//                .password("password2")
//                .build();
//        var user3 = User.builder()
//                .name("User 3")
//                .email("email3@example.com")
//                .password("password3")
//                .build();
//
//        var profile1 = Profile.builder()
//                .bio("I am User 1")
//                .phoneNumber("123456789")
//                .dateOfBirth(LocalDate.of(1995, 6, 28))
//                .loyaltyPoints(5)
//                .build();
//
//        var profile2 = Profile.builder()
//                .bio("I am User 2")
//                .phoneNumber("123456789")
//                .dateOfBirth(LocalDate.of(1996, 6, 28))
//                .loyaltyPoints(10)
//                .build();
//
//        var profile3 = Profile.builder()
//                .bio("I am User 3")
//                .phoneNumber("123456789")
//                .dateOfBirth(LocalDate.of(1997, 6, 28))
//                .loyaltyPoints(10)
//                .build();
//
//        user1.setProfile(profile1);
//        user2.setProfile(profile2);
//        user3.setProfile(profile3);
//
//        List<User> users = List.of(user1, user2, user3);
//
//        userRepository.saveAll(users);
//        var profiles = profileRepository.findProfilesWithLoyaltyPointsGreaterThan(2);
//        profiles.forEach(profile -> {
//            System.out.println("Profile ID: " + profile.getId());
//            System.out.println("User Email: " + profile.getEmail().getEmail());
//        });

        var profiles = userRepository.findUsersByProfileLoyaltyPointsGreaterThan(2);
        profiles.forEach(profile -> {
            System.out.println("Profile ID: " + profile.getId());
            System.out.println("User Email: " + profile.getEmail());
        });
    }
}