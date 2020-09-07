package springMVC.voite.controllers;

import springMVC.voite.dto.TokenDto;
import springMVC.voite.dto.VoterDto;
import springMVC.voite.service.AppService;
import springMVC.voite.validators.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/voters")
public class VoterController {

    private final AppService appService = new AppService();
    private final TokenValidator tokenValidator = new TokenValidator();

    @InitBinder("/voterDetails")
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(tokenValidator);
    }

    @GetMapping("/enterToken")
    public String askForTokenGET(Model model, TokenDto tokenDto) {
        model.addAttribute("token", new TokenDto());
        model.addAttribute("errors", new HashMap<>());
        return "voters/enterToken";
    }

    @PostMapping("/voterDetails")
    public String askForTokenPOST(@Valid @ModelAttribute TokenDto tokenDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));
            model.addAttribute("token", tokenDTO);
            model.addAttribute("errors", errors);
            appService.deleteToken(tokenDTO.getTokenValue());
            return "/voters/enterToken";
        }
        VoterDto voterDto = appService.getVoterByToken(tokenDTO);
        model.addAttribute("voter", voterDto);
        model.addAttribute("constituency", appService.getConstituencyForVoter(voterDto.getId()));
        appService.deleteToken(tokenDTO.getTokenValue());
        return "voters/voterDetails";
    }

    @PostMapping("/wrongToken")
    public String wrongToken() {
        return "voters/wrongToken";
    }
}