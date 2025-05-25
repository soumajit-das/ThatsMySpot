package com.thatsmyspot.userservice.services;

import com.thatsmyspot.commonlib.exceptions.BadRequestException;
import com.thatsmyspot.commonlib.exceptions.InternalServerException;
import com.thatsmyspot.commonlib.exceptions.NotFoundException;
import com.thatsmyspot.commonlib.redis.redisKey.RedisKeyType;

import com.thatsmyspot.userservice.customAnnotations.redis.RedisCacheDelete;
import com.thatsmyspot.userservice.customAnnotations.redis.RedisCacheUpdate;
import com.thatsmyspot.userservice.customAnnotations.redis.RedisCache;
import com.thatsmyspot.userservice.dto.CreateUserDto;
import com.thatsmyspot.userservice.dto.UpdateUserDto;
import com.thatsmyspot.userservice.entities.User;
import com.thatsmyspot.userservice.infrastructure.UserStatus;
import com.thatsmyspot.userservice.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Iterable<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userRepository.findAll());
        } catch (RuntimeException e) {
            throw new InternalServerException("Something went wrong", e.getMessage());
        }
    }

    @RedisCache(keyType = RedisKeyType.USER_ID, templateComponent = "id=$0", context = User.class)
    public ResponseEntity<User> getUserById(String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.map(ResponseEntity::ok)
                    .orElseThrow(() -> new NotFoundException(String.format("User not found with id %s", id)));
        } catch (RuntimeException e) {
            throw new InternalServerException("Something went wrong", e.getMessage());
        }
    }

    @RedisCache(keyType = RedisKeyType.USER_USERNAME, templateComponent = "username=$0", context = User.class)
    public ResponseEntity<User> getUserByUsername(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            return user.map(ResponseEntity::ok)
                    .orElseThrow(() -> new NotFoundException(String.format("User not found with username %s", username)));
        } catch (RuntimeException e) {
            throw new InternalServerException("Something went wrong", e.getMessage());
        }
    }

    public ResponseEntity<User> getUserByEmail(String email) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            return user.map(ResponseEntity::ok)
                    .orElseThrow(() -> new NotFoundException(String.format("User not found with email %s", email)));
        } catch (RuntimeException e) {
            throw new InternalServerException("Something went wrong", e.getMessage());
        }
    }

    public ResponseEntity<User> saveUser(CreateUserDto userDto) {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setStatus(UserStatus.ACTIVE);
            user.setRoles(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        } catch (RuntimeException e) {
            throw new InternalServerException("Something went wrong", e.getMessage());
        }
    }

    @RedisCacheUpdate(keyType = RedisKeyType.USER_ID, templateComponent = "id=$0", context = User.class)
    public ResponseEntity<User> updateUser(String id, UpdateUserDto userDto) {
        try {
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isEmpty()) {
                throw new BadRequestException(String.format("User not found with id %s", id));
            }

            User existing = existingUser.get();
            existing.setUsername(userDto.getUsername());
            existing.setEmail(userDto.getEmail());
            existing.setFirstName(userDto.getFirstName());
            existing.setLastName(userDto.getLastName());
            existing.setPhoneNumber(userDto.getPhoneNumber());

            return ResponseEntity.ok().body(userRepository.save(existing));
        } catch (RuntimeException e) {
            throw new InternalServerException("Something went wrong", e.getMessage());
        }
    }

    @RedisCacheDelete(keyType = RedisKeyType.USER_ID, templateComponent = "id=$0")
    public ResponseEntity<Void> deleteUser(String id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new BadRequestException("Something went wrong", e.getMessage());
        }
    }
}
