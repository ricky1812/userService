package org.example.userservice.repositories;

import java.util.Optional;
import org.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
  Token save(Token token);
  Optional<Token> findByValue(String tokenValue);

}
