package dlugolecki.pawel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

    

}