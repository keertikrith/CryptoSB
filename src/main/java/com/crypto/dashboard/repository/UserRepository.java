package com.crypto.dashboard.repository;

import com.crypto.dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByname(String name);

    User findByEmailAndPassword(String email, String password);
    User findBynameAndPassword(String name, String password);
    ;

}
