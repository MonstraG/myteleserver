package com.teleone.mytele.db.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

    List<User> findByUsername(String username);

    boolean existsById(long id);

    boolean existsByUsername(String string);

    boolean existsByIdOrUsername(long id, String username);
}