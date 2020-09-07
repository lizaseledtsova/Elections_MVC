package dlugolecki.pawel.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dlugolecki.pawel.dto.CandidateDto;
import dlugolecki.pawel.dto.ConstituencyDto;
import dlugolecki.pawel.dto.PoliticalPartyDto;
import dlugolecki.pawel.dto.TokenDto;
import dlugolecki.pawel.dto.VoterDto;
import dlugolecki.pawel.exceptions.MyException;
import dlugolecki.pawel.mappers.ModelMapper;
import dlugolecki.pawel.model.Candidate;
import dlugolecki.pawel.model.Vote;
import dlugolecki.pawel.model.Voter;
import dlugolecki.pawel.repository.CandidateRepository;
import dlugolecki.pawel.repository.PoliticalPartyRepository;
import dlugolecki.pawel.repository.TokenRepository;
import dlugolecki.pawel.repository.VoteRepository;
import dlugolecki.pawel.repository.VoterRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppService {

    private final CandidateRepository candidateRepository = null;
    private final VoterRepository voterRepository = null;
    private final PoliticalPartyRepository politicalPartyRepository = null;
    private final TokenRepository tokenRepository = null;
    private final VoteRepository voteRepository = null;


    public VoterDto getVoterById(Long id) {

        return voterRepository
                .findById(id)
                .map(ModelMapper::fromVoterToVoterDto)
                .orElseThrow(() -> new MyException(
                        "Voter id " + id + " is null",
                        AppService.class.getCanonicalName(),
                        "Get voter by id"));
    }

    public PoliticalPartyDto getPartyById(Long id) {

        return politicalPartyRepository
                .findById(id)
                .map(ModelMapper::fromPoliticalPartyToPoliticalPartyDto)
                .orElseThrow(() -> new MyException(
                        "Political party id " + id + " is null",
                        AppService.class.getCanonicalName(),
                        "Get party by id"));
    }

    public VoterDto getVoterByToken(TokenDto tokenDTO) {
        if (tokenDTO == null) {
            throw new MyException(
                    "TOKEN DTO " + tokenDTO + " IS NULL",
                    AppService.class.getCanonicalName(),
                    "Get voter by token");
        }

        TokenDto tokenFromDB = tokenRepository
                .findAll()
                .stream()
                .map(ModelMapper::fromTokenToTokenDto)
                .filter(token -> token.getTokenValue().equals(tokenDTO.getTokenValue()))
                .findFirst()
                .orElseThrow(() -> new MyException(
                        "NO TOKEN WITH VALUE ",
                        AppService.class.getCanonicalName(),
                        "Get voter by token"));

        return voterRepository
                .findAll()
                .stream()
                .filter(voter -> voter.getId().equals(tokenFromDB.getVoterDto().getId()))
                .map(ModelMapper::fromVoterToVoterDto)
                .findFirst()
                .orElseThrow(() -> new MyException(
                        "NO VOTER WITH TOKEN ",
                        AppService.class.getCanonicalName(),
                        "Get voter by token"));
    }

    public List<CandidateDto> getAllCandidatesByPoliticalPartyId(Long id) {

        if (id == null) {
            throw new MyException(
                    "Id " + id + " is null",
                    AppService.class.getCanonicalName(),
                    "getAllCandidatesByPoliticalPartyId"
            );

        }
        return candidateRepository
                .findAll()
                .stream()
                .filter(c -> c.getPoliticalParty().getId().equals(id))
                .map(ModelMapper::fromCandidateToCandidateDto)
                .collect(Collectors.toList());

    }

    public Map<PoliticalPartyDto, List<CandidateDto>> getAllCandidatesByConstituencyId(Long id) {

        if (id == null) {
            throw new MyException(
                    "Id " + id + " is null",
                    AppService.class.getCanonicalName(),
                    "getAllCandidatesByConstituencyId"
            );
        }

        return candidateRepository
                .findAll()
                .stream()
                .filter(c -> c.getConstituency().getId().equals(id))
                .map(ModelMapper::fromCandidateToCandidateDto)
                .collect(Collectors.groupingBy(CandidateDto::getPoliticalPartyDto));
    }

    public List<CandidateDto> getAllCandidatesFromPoliticalParty(Long id) {
        if (id == null) {
            throw new MyException(
                    "ID " + id + " IS NULL",
                    AppService.class.getCanonicalName(),
                    "Get all candidates from politiacl party");
        }

        return candidateRepository
                .findAll()
                .stream()
                .filter(c -> c.getPoliticalParty().getId().equals(id))
                .map(ModelMapper::fromCandidateToCandidateDto)
                .collect(Collectors.toList());
    }

    public void addVoteToThisCandidate(Long id, Long vid) {

        if (id == null) {
            throw new MyException(
                    "Id is null",
                    AppService.class.getCanonicalName(),
                    "Add vote to this candidate");
        }

        if (vid == null) {
            throw new MyException(
                    "Voter id is null",
                    AppService.class.getCanonicalName(),
                    "Add vote to this candidate");
        }

        Voter voter = voterRepository.findById(vid).orElseThrow(() -> new MyException(
                "Voter with id " + id + " doesn't exist",
                AppService.class.getCanonicalName(),
                "Add vote to this candidate")
        );

        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new MyException(
                "Candidate with id " + id + " doesn't exist",
                AppService.class.getCanonicalName(),
                "Add vote to this candidate")
        );

        voteRepository.save(Vote.builder()
                .voter(voter)
                .candidate(candidate)
                .dateTime(LocalDateTime.now())
                .build());
    }

    public CandidateDto addVoteToCandidate(Long id) {

        if (id == null) {
            throw new MyException(
                    "ID IS NULL",
                    AppService.class.getCanonicalName(),
                    "ADD VOTE TO CANDIDATE");
        }

        Candidate candidate = candidateRepository
                .findById(id)
                .orElseThrow(NullPointerException::new);
        candidate.setVotes(candidate.getVotes() + 1);
        Candidate candidateFromDB = candidateRepository.save(candidate);

        return ModelMapper.fromCandidateToCandidateDto(candidateFromDB);
    }

    public List<PoliticalPartyDto> getAllPoliticalParties() {

        return politicalPartyRepository
                .findAll()
                .stream()
                .map(ModelMapper::fromPoliticalPartyToPoliticalPartyDto)
                .collect(Collectors.toList());
    }

    public ConstituencyDto getConstituencyForVoter(Long userId) {

        if (userId == null) {
            throw new MyException(
                    "USER ID " + userId + " IS NULL",
                    AppService.class.getCanonicalName(),
                    "GET CONSTITUENCY FOR VOTER");
        }
        return voterRepository
                .findById(userId)
                .map(voter -> ModelMapper.fromConstituencyToConstituencyDto(voter.getConstituency()))
                .orElseThrow(() -> new MyException(
                        "USER WITH " + userId + " NOT FOUND",
                        AppService.class.getCanonicalName(),
                        "GET CONSTITUENCY FOR VOTER"));
    }

    public void deleteToken(String tokenValue) {

        if (tokenValue == null) {
            throw new MyException(
                    "TOKEN VALUE " + tokenValue + " IS NULL",
                    AppService.class.getCanonicalName(),
                    "DELETE TOKEN");
        }

        tokenRepository.delete(tokenRepository
                .findTokenByTokenValue(tokenValue)
                .orElseThrow(NullPointerException::new));
    }

    public List<Candidate> winnersFromEachConstituency() {

        return candidateRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Candidate::getVotes, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        constituency -> constituency.getConstituency().getName(),
                        candidate -> candidate,
                        (v1, v2) -> v1,
                        LinkedHashMap::new))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

    }
}
