package ru.isshepelev.redispostgrescaching.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.isshepelev.redispostgrescaching.dto.CreateTaskDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateTask;
import ru.isshepelev.redispostgrescaching.model.Task;
import ru.isshepelev.redispostgrescaching.service.TaskService;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;


    @GetMapping("/get-task/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/create-task/{userId}")
    public Task createTask(@RequestBody CreateTaskDto createTaskDto, @PathVariable Long userId){
        return taskService.createTask(createTaskDto, userId);
    }

    @PostMapping("/update-task/{id}")
    public Task updateTask(@RequestBody UpdateTask updateTask, @PathVariable Long id){
        return taskService.updateTask(updateTask, id);
    }

    @PostMapping("/delete-task/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
