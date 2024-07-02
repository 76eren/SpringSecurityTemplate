package org.example.springsecuritytemplate.Repository;

import org.example.springsecuritytemplate.Model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
}