package ru.isshepelev.redispostgrescaching.dto;

import lombok.Data;

@Data
public class CreateTaskDto {
    private String title;
    private String description;
}
