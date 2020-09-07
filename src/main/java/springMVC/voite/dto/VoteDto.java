package springMVC.voite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDto {

    private Long id;
    private LocalDateTime dateTime;
    private CandidateDto candidateDto;
    private VoterDto voterDto;
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
	public CandidateDto getCandidateDto() {
		return candidateDto;
	}
	public void setCandidateDto(CandidateDto candidateDto) {
		this.candidateDto = candidateDto;
	}
	public VoterDto getVoterDto() {
		return voterDto;
	}
	public void setVoterDto(VoterDto voterDto) {
		this.voterDto = voterDto;
	}
    
    
}