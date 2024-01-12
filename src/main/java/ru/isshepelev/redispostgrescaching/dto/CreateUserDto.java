package ru.isshepelev.redispostgrescaching.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String name;
    private String lastname;
    private String email;
}
