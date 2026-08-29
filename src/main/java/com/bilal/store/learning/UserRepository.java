package com.bilal.store.learning;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
