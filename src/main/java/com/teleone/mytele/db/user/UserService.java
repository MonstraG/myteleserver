package com.teleone.mytele.db.user;

import com.teleone.mytele.db.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    public User find(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }

    public Set<User> getUsers() {
        HashSet<User> set = new HashSet<>();
        userRepository.findAll().forEach(set::add);
        return set;
    }

    public Long getUsersCount() {
        return userRepository.count();
    }

}