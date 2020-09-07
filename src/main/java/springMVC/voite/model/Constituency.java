package springMVC.voite.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "constituencies")
public class Constituency {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "constituency")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Candidate> candidates;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "constituency")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Voter> voters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Set<Voter> getVoters() {
		return voters;
	}

	public void setVoters(Set<Voter> voters) {
		this.voters = voters;
	}
    
    
}