package com.teleone.mytele.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public User find(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> find(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }

    public List<User> getUsers() {
        ArrayList<User> set = new ArrayList<>();
        userRepository.findAll().forEach(set::add);
        return set;
    }

    public Long getUsersCount() {
        return userRepository.count();
    }
}