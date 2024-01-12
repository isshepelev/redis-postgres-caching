package ru.isshepelev.redispostgrescaching.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isshepelev.redispostgrescaching.dto.CreateUserDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateUserDto;
import ru.isshepelev.redispostgrescaching.model.User;
import ru.isshepelev.redispostgrescaching.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserDto createUserDto){
        return userService.createUser(createUserDto);
    }
    @PostMapping("/update/{id}")
    public User updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable Long id){
        return userService.updateUser(updateUserDto, id);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
