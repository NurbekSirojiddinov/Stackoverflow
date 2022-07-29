package com.example.stackoverflow.service.ServiceImpl;

import com.example.stackoverflow.entity.UserEntity;
import com.example.stackoverflow.dto.AddNewUserRequest;
import com.example.stackoverflow.dto.UserResponse;
import com.example.stackoverflow.repository.UserRepository;
import com.example.stackoverflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserResponse addUser(final AddNewUserRequest request) {
        Assert.notNull(request.getEmail(), "Email cannot be null");
        Assert.notNull(request.getUsername(), "Username cannot be null");
        // Assert.notNull(request.getRoles(), "Role cannot be null");

        final UserEntity entity = new UserEntity();
        entity.setConfirmed(true);
        entity.setCreatedDate(Instant.now());
        entity.setDeleted(false);
        entity.setEmail(request.getEmail());
        entity.setName(request.getName());
        entity.setRole(request.getRole());
        entity.setLastModifiedDate(Instant.now());
        entity.setUsername(request.getUsername());
        entity.setPassword(encoder.encode(request.getPassword()));

        log.info("Saving new User {} to the database", entity.getUsername());
        userRepository.save(entity);
        return entity.toPojo();
    }

    @Override
    public UserResponse getUser(String username) {
        log.info("Fetching user {}", username);
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                {
                    throw new NoSuchElementException(
                            String.format("User [%s] not found", username));
                });
        return user.toPojo();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database {}", username);
        }
        UserEntity user = userEntity.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
