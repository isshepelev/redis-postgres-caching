package ru.isshepelev.redispostgrescaching.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.isshepelev.redispostgrescaching.dto.CreateTaskDto;
import ru.isshepelev.redispostgrescaching.dto.UpdateTask;
import ru.isshepelev.redispostgrescaching.model.Task;
import ru.isshepelev.redispostgrescaching.model.User;
import ru.isshepelev.redispostgrescaching.repository.TaskRepository;
import ru.isshepelev.redispostgrescaching.service.TaskService;
import ru.isshepelev.redispostgrescaching.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @SneakyThrows
    @Override
    @Cacheable(value = "TaskService::getTaskById", key = "#id")
    public Task getTaskById(Long id) {

        Thread.sleep(2000);

        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()){
            throw new RuntimeException("task not found");
        }
        return taskOptional.get();
    }

    @Override
    @Caching(put = {
            @CachePut(value = "TaskService::getTaskById", key = "#id"),
            @CachePut(value = "UserService::getUser") //TODO asdf
    })
    public Task updateTask(UpdateTask updateTask, Long id) {
        Task task = getTaskById(id);
        if (task == null){
            throw new RuntimeException("task not found");
        }
        task.setDescription(updateTask.getDescription());
        task.setTitle(updateTask.getTitle());
        return taskRepository.save(task);
    }

    @Override
    @CacheEvict(value = "TaskService::getTaskById", key = "#id")
    public void deleteTask(Long id) {

        if (taskRepository.findById(id).isEmpty()){
            throw new RuntimeException("task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task createTask(CreateTaskDto createTaskDto, Long userId) {
        User user = userService.getUser(userId);
        if (user == null){
            throw new RuntimeException("user not found");
        }

        Task task = new Task();
        task.setTitle(createTaskDto.getTitle());
        task.setDescription(createTaskDto.getDescription());
        task.setUser(user);
        taskRepository.save(task);

        user.getTaskList().add(task);
        userService.saveTaskForUser(user);
        return task;
    }


}
