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

    public User getUserByUsername(String username) {
        String SQL_GET = "select * from users_t where username = ?";
        return jdbcTemplate.queryForObject(SQL_GET, new Object[]{username}, userMapper);
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

    public void createUser(User user) {
        String SQL_INSERT_USER = "insert into users_t(username) values(?)";
        jdbcTemplate.update(SQL_INSERT_USER, user.getUsername());
    }
}