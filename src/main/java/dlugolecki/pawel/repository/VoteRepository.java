package dlugolecki.pawel.repository;

import dlugolecki.pawel.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}