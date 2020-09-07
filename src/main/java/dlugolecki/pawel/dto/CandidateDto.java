package dlugolecki.pawel.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "builder")
public class CandidateDto {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private MultipartFile file;
    private String photo;
    private PoliticalPartyDto politicalPartyDto;
    private ConstituencyDto constituencyDto;
    private Integer votes = 0;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public PoliticalPartyDto getPoliticalPartyDto() {
		return politicalPartyDto;
	}
	public void setPoliticalPartyDto(PoliticalPartyDto politicalPartyDto) {
		this.politicalPartyDto = politicalPartyDto;
	}
	public ConstituencyDto getConstituencyDto() {
		return constituencyDto;
	}
	public void setConstituencyDto(ConstituencyDto constituencyDto) {
		this.constituencyDto = constituencyDto;
	}
	public Integer getVotes() {
		return votes;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
    
    
}