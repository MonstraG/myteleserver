package com.teleone.mytele.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserMapper userMapper = new UserMapper();

    public User getUserById(int id) {
        String SQL_GET = "SELECT * FROM users_t WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL_GET, new Object[]{id}, userMapper);
    }

    public boolean userExists(String username) {
        //todo: ask, is it better to "cast" via queryForObject, or to check if result > 0?
        String SQL_USER_EXISTS = "select count(*) from users_t where username = ?";
        Boolean result = jdbcTemplate.queryForObject(SQL_USER_EXISTS, new Object[]{username}, Boolean.class);
        if (result == null) {
            return false;
        }
        return result;
    }

    public boolean userExists(int id) {
        //todo: ask, is it better to "cast" via queryForObject, or to check if result > 0?
        String SQL_USER_EXISTS = "select count(*) from users_t where id = ?";
        Boolean result = jdbcTemplate.queryForObject(SQL_USER_EXISTS, new Object[]{id}, Boolean.class);
        if (result == null) {
            return false;
        }
        return result;
    }

    public void createUser(User user) {
        String SQL_INSERT_USER = "insert into users_t values(?)";
        jdbcTemplate.update(SQL_INSERT_USER, user.getUsername());
    }
}