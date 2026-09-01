package com.bilal.store.services;

import com.bilal.store.entities.Address;
import com.bilal.store.entities.Category;
import com.bilal.store.entities.Product;
import com.bilal.store.entities.User;
import com.bilal.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        var products = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(20));
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

    public void finalExercise() {

    }
}