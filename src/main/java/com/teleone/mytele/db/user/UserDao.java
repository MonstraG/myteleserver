package com.teleone.mytele.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserMapper userMapper = new UserMapper();

    public User getUserById(int id) {
        final String SQL_GET = "SELECT * FROM users_t WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL_GET, new Object[]{id}, userMapper);
    }

    public boolean userExists(String username) {
        //todo: ask, is it better to "cast" via queryForObject, or to check if result > 0?
        final String SQL_USER_EXISTS = "SELECT COUNT (*) FROM users_t WHERE username = ?";
        Boolean result = jdbcTemplate.queryForObject(SQL_USER_EXISTS, new Object[]{username}, Boolean.class);
        if (result == null) {
            return false;
        }
        return result;
    }

    public boolean userExists(int id) {
        //todo: ask, is it better to "cast" via queryForObject, or to check if result > 0?
        final String SQL_USER_EXISTS = "SELECT COUNT(*) FROM users_t WHERE id = ?";
        Boolean result = jdbcTemplate.queryForObject(SQL_USER_EXISTS, new Object[]{id}, Boolean.class);
        if (result == null) {
            return false;
        }
        return result;
    }

    public void createUser(User user) {
        final String SQL_INSERT_USER = "INSERT INTO users_t VALUES(?, ?)";
        jdbcTemplate.update(SQL_INSERT_USER, user.getUsername(), user.getPassword());
    }
}