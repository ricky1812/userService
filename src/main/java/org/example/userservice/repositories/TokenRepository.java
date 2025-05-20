package org.example.userservice.repositories;

import java.util.Date;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
  Token save(Token token);
  Optional<Token> findByValue(String tokenValue);

  Optional<Token> findByValueAndDeletedAndExpiryDtGreaterThan(
      String tokenValue,
      Boolean deleted,
      Date currentTime
  );

}
