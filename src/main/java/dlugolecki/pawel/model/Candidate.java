package dlugolecki.pawel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String photo;
    private Integer votes = 0;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "political_party_id")
    private PoliticalParty politicalParty;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "constituency_id")
    private Constituency constituency;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public PoliticalParty getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(PoliticalParty politicalParty) {
		this.politicalParty = politicalParty;
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
    
    
}