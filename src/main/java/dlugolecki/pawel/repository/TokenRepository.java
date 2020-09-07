package dlugolecki.pawel.repository;

import dlugolecki.pawel.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByTokenValue(String tokenValue);

    List<Token> findTokensByExpirationDateBefore(LocalDateTime expirationDate);
}