package dlugolecki.pawel.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "political_parties")
public class PoliticalParty {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "politicalParty")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Candidate> candidates;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
    
    
}