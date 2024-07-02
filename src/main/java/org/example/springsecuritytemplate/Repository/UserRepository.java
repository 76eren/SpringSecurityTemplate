package org.example.springsecuritytemplate.Repository;

import org.example.springsecuritytemplate.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<Set<User>> findAllByUsername(String username);
}
