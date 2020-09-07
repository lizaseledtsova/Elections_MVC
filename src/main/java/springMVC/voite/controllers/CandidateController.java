package springMVC.voite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springMVC.voite.service.AppService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/candidates")
public class CandidateController {

    private final AppService appService = new AppService();

    @GetMapping("/politicalParty/{id}")
    public String getCandidatesFromPoliticalParty(@PathVariable Long id, Model model) {
        model.addAttribute("candidates", appService.getAllCandidatesByPoliticalPartyId(id));
        return "candidates/all";
    }

    @GetMapping("/constituency/{id}/voter/{vid}")
    public String getCandidatesFromConstituency(
            @PathVariable Long id, @PathVariable Long vid, Model model) {
        model.addAttribute("candidates", appService.getAllCandidatesByConstituencyId(id));
        model.addAttribute("voterId", appService.getVoterById(vid));
        return "candidates/all";
    }

    @GetMapping("/vote/{id}")
    public String addVoteToThisCandidate(@PathVariable Long id) {

        appService.addVoteToCandidate(id);
        return "redirect:/vote/{id}";
    }

    @PostMapping("/vote")
    public String addVoteToThisCandidatePost(
            @RequestParam Long id, @RequestParam Long vid) {
        appService.addVoteToThisCandidate(id, vid);
        return "redirect:/voters/enterToken";
    }

}