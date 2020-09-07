package springMVC.voite.mappers;

import java.util.HashSet;

import springMVC.voite.dto.CandidateDto;
import springMVC.voite.dto.ConstituencyDto;
import springMVC.voite.dto.PoliticalPartyDto;
import springMVC.voite.dto.TokenDto;
import springMVC.voite.dto.VoteDto;
import springMVC.voite.dto.VoterDto;
import springMVC.voite.model.Candidate;
import springMVC.voite.model.Constituency;
import springMVC.voite.model.PoliticalParty;
import springMVC.voite.model.Token;
import springMVC.voite.model.Vote;
import springMVC.voite.model.Voter;

public class ModelMapper {


    public static CandidateDto fromCandidateToCandidateDto(Candidate candidate) {

        return candidate == null ? null : CandidateDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .age(candidate.getAge())
                .photo(candidate.getPhoto())
                .constituencyDto(candidate.getConstituency() == null ? null : fromConstituencyToConstituencyDto(candidate.getConstituency()))
                .politicalPartyDto(candidate.getPoliticalParty() == null ? null : fromPoliticalPartyToPoliticalPartyDto(candidate.getPoliticalParty()))
                .votes(candidate.getVotes())
                .build();
    }

    public static Candidate fromCandidateDtoToCandidate(CandidateDto candidateDto) {

        return candidateDto == null ? null : Candidate.builder()
                .id(candidateDto.getId())
                .name(candidateDto.getName())
                .surname(candidateDto.getSurname())
                .age(candidateDto.getAge())
                .photo(candidateDto.getPhoto())
                .constituency(candidateDto.getConstituencyDto() == null ? null : fromConstituencyDtoToConstituency(candidateDto.getConstituencyDto()))
                .politicalParty(candidateDto.getPoliticalPartyDto() == null ? null : fromPoliticalPartyDtoToPoliticalParty(candidateDto.getPoliticalPartyDto()))
                .votes(candidateDto.getVotes())
                .build();
    }

    public static ConstituencyDto fromConstituencyToConstituencyDto(Constituency constituency) {

        return constituency == null ? null : ConstituencyDto.builder()
                .id(constituency.getId())
                .name(constituency.getName())
                .build();
    }

    public static Constituency fromConstituencyDtoToConstituency(ConstituencyDto constituencyDto) {

        return constituencyDto == null ? null : Constituency.builder()
                .id(constituencyDto.getId())
                .name(constituencyDto.getName())
                .candidates(new HashSet<>())
                .build();
    }

    public static PoliticalPartyDto fromPoliticalPartyToPoliticalPartyDto(PoliticalParty politicalParty) {

        return politicalParty == null ? null : PoliticalPartyDto.builder()
                .id(politicalParty.getId())
                .name(politicalParty.getName())
                .description(politicalParty.getDescription())
                .build();
    }

    public static PoliticalParty fromPoliticalPartyDtoToPoliticalParty(PoliticalPartyDto politicalPartyDto) {

        return politicalPartyDto == null ? null : PoliticalParty.builder()
                .id(politicalPartyDto.getId())
                .name(politicalPartyDto.getName())
                .candidates(new HashSet<>())
                .description(politicalPartyDto.getDescription())
                .build();
    }

    public static VoterDto fromVoterToVoterDto(Voter voter) {

        return voter == null ? null : VoterDto.builder()
                .id(voter.getId())
                .age(voter.getAge())
                .gender(voter.getGender())
                .education(voter.getEducation())
                .constituencyDto(voter.getConstituency() == null ? null : fromConstituencyToConstituencyDto(voter.getConstituency()))
                .build();
    }

    public static Voter fromVoterDtoToVoter(VoterDto voterDto) {

        return voterDto == null ? null : Voter.builder()
                .id(voterDto.getId())
                .age(voterDto.getAge())
                .gender(voterDto.getGender())
                .education(voterDto.getEducation())
                .constituency(voterDto.getConstituencyDto() == null ? null : fromConstituencyDtoToConstituency(voterDto.getConstituencyDto()))
                .build();
    }

    public static TokenDto fromTokenToTokenDto(Token token) {

        return token == null ? null : TokenDto.builder()
                .id(token.getId())
                .expirationDate(token.getExpirationDate())
                .tokenValue(token.getTokenValue())
                .voterDto(token.getVoter() == null ? null : fromVoterToVoterDto(token.getVoter()))
                .build();
    }

    public static Token fromTokenDtoToToken(TokenDto tokenDto) {

        return tokenDto == null ? null : Token.builder()
                .id(tokenDto.getId())
                .expirationDate(tokenDto.getExpirationDate())
                .tokenValue(tokenDto.getTokenValue())
                .voter(tokenDto.getVoterDto() == null ? null : fromVoterDtoToVoter(tokenDto.getVoterDto()))
                .build();
    }

    public static VoteDto fromVoteToVoteDto(Vote vote) {

        return vote == null ? null : VoteDto.builder()
                .id(vote.getId())
                .candidateDto(vote.getCandidate() == null ? null : fromCandidateToCandidateDto(vote.getCandidate()))
                .voterDto(vote.getVoter() == null ? null : fromVoterToVoterDto(vote.getVoter()))
                .dateTime(vote.getDateTime())
                .build();
    }

    public static Vote fromVoteDtoToVote(VoteDto voteDto) {

        return voteDto == null ? null : Vote.builder()
                .id(voteDto.getId())
                .candidate(voteDto.getCandidateDto() == null ? null : fromCandidateDtoToCandidate(voteDto.getCandidateDto()))
                .voter(voteDto.getVoterDto() == null ? null : fromVoterDtoToVoter(voteDto.getVoterDto()))
                .dateTime(voteDto.getDateTime())
                .build();
    }
}