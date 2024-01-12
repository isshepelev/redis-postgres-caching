package ru.isshepelev.redispostgrescaching.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.isshepelev.redispostgrescaching.dto.CreateUserDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateUserDto;
import ru.isshepelev.redispostgrescaching.model.User;
import ru.isshepelev.redispostgrescaching.repository.UserRepository;
import ru.isshepelev.redispostgrescaching.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "UserService::getUser", key = "#id")
    @SneakyThrows
    @Override
    public User getUser(Long id) {

        Thread.sleep(2000);

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        return userRepository.findById(id).orElse(null);
    }

    @SneakyThrows
    @Override
    @Cacheable("users")    // тяп ляп сделал кэширование листа, потом переделаю
    public List<User> getAllUsers() {

        Thread.sleep(2000);

        return userRepository.findAll();
    }

    @SneakyThrows
    @Caching(put = {
            @CachePut(value = "UserService::getUser", key = "#result.id")
    }, evict = {@CacheEvict(value = "users", allEntries = true)})
    @Override
    public User createUser(CreateUserDto createUserDto) {

        Thread.sleep(2000);

        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        user.setLastname(createUserDto.getLastname());
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    @Caching(put = {
            @CachePut(value = "UserService::getUser", key = "#result.id")
    }, evict = {@CacheEvict(value = "users", allEntries = true)})
    public User updateUser(UpdateUserDto updateUserDto, Long id) {

        Thread.sleep(2000);

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        User user = userOptional.get();
        user.setLastname(updateUserDto.getLastname());
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    @Caching(evict = {
            @CacheEvict(value = "UserService::getUser", key = "#id"),
            @CacheEvict(value = "users", allEntries = true)
    })
    public void deleteUser(Long id) {

        Thread.sleep(2000);

        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("user not found");
        }
        userRepository.deleteById(id);
    }


}
