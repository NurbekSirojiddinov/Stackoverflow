package com.example.stackoverflow.repository;

import com.example.stackoverflow.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findOneByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}
