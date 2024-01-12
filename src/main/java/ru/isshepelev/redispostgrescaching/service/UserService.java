package ru.isshepelev.redispostgrescaching.service;

import org.springframework.http.ResponseEntity;
import ru.isshepelev.redispostgrescaching.dto.CreateUserDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateUserDto;
import ru.isshepelev.redispostgrescaching.model.User;

public interface UserService {
    User getUser(Long id);

    User createUser(CreateUserDto createUserDto);

    void deleteUser(Long id);

    User updateUser(UpdateUserDto updateUserDto, Long id);
}
