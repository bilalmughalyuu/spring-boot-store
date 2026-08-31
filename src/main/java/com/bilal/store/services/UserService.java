package com.bilal.store.services;

import com.bilal.store.entities.Address;
import com.bilal.store.entities.Product;
import com.bilal.store.entities.User;
import com.bilal.store.repositories.AddressRepository;
import com.bilal.store.repositories.ProductRepository;
import com.bilal.store.repositories.ProfileRepository;
import com.bilal.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

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

    public void createProduct() {
        var product = Product.bu
        productRepository
    }

//    @Transactional
//    public void deleteRelated() {
//        var user = userRepository.findById(3L).orElseThrow();
//        var address = user.getAddresses().getFirst();
//        user.removeAddress(address);
//        userRepository.save(user);
//    }
}