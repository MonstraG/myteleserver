package com.teleone.mytele.db.user;

import com.teleone.mytele.db.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
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

}