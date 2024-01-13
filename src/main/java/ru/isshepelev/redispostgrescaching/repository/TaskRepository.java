package ru.isshepelev.redispostgrescaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isshepelev.redispostgrescaching.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
