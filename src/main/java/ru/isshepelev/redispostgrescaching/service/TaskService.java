package ru.isshepelev.redispostgrescaching.service;

import ru.isshepelev.redispostgrescaching.dto.CreateTaskDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateTask;
import ru.isshepelev.redispostgrescaching.model.Task;

public interface TaskService {
    Task createTask(CreateTaskDto createTaskDto, Long userId);

    Task getTaskById(Long id);

    Task updateTask(UpdateTask updateTask, Long id);

    void deleteTask(Long id);

}
