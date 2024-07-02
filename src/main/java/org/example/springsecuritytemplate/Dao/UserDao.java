package org.example.springsecuritytemplate.Dao;


import org.example.springsecuritytemplate.Dto.User.UserResponseDTO;
import org.example.springsecuritytemplate.Mapper.UserMapper;
import org.example.springsecuritytemplate.Model.User;
import org.example.springsecuritytemplate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@RequiredArgsConstructor
public class UserDao implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> findAllDto() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::fromEntity)
                .toList();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<Set<User>> findAllByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }


}
