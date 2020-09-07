package springMVC.voite.dto;

import springMVC.voite.model.enums.Education;
import springMVC.voite.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoterDto {

    private Long id;
    private Integer age;
    private Gender gender;
    private Education education;
    private ConstituencyDto constituencyDto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public ConstituencyDto getConstituencyDto() {
		return constituencyDto;
	}
	public void setConstituencyDto(ConstituencyDto constituencyDto) {
		this.constituencyDto = constituencyDto;
	}
    
    
}


