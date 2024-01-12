package ru.isshepelev.redispostgrescaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isshepelev.redispostgrescaching.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
