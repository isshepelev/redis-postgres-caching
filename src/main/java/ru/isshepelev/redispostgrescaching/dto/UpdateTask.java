package ru.isshepelev.redispostgrescaching.dto;

import lombok.Data;

@Data
public class UpdateTask {
    private String title;
    private String description;
}
