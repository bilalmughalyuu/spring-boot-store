package com.bilal.store;

import com.bilal.store.entities.Address;
import com.bilal.store.entities.Profile;
import com.bilal.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var user = User.builder()
                .name("John")
                .password("password")
                .email("john@codewithmosh.com")
                .build();

        var profile = Profile.builder()
                        .bio("bio")
                .build();


        user.setProfile(profile);
        profile.setUser(user);



        System.out.println(user);

//        var orderService = context.getBean(OrderService.class);
//        orderService.placeOrder();
//        var userService = context.getBean(UserService.class);
//        userService.registerUser(new User(1L,"bilal@gmail.com","12345", "Bilal"));
//        userService.registerUser(new User(1L,"bilal@yahoo.com","54321", "Muhammad"));
    }

}
