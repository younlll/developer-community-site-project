package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import project.developmentcomunity.dto.JoinForm;
import project.developmentcomunity.service.UserService;

import javax.validation.Valid;

@Controller
public class JoinPageController {

    private final UserService userService;

    @Autowired
    public JoinPageController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public String joinMember(@Valid JoinForm joinForm) {
        userService.joinUser(joinForm);

        return "redirect:/";
    }
}
