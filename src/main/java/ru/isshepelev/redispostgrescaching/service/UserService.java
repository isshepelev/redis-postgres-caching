package ru.isshepelev.redispostgrescaching.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import ru.isshepelev.redispostgrescaching.dto.CreateUserDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateUserDto;
import ru.isshepelev.redispostgrescaching.model.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);

    User createUser(CreateUserDto createUserDto);

    void deleteUser(Long id);

    User updateUser(UpdateUserDto updateUserDto, Long id);

    List<User> getAllUsers();

    @Caching(put = {
            @CachePut(value = "UserService::getUser", key = "#user.id")
    })
    void saveTaskForUser(User user);
}
