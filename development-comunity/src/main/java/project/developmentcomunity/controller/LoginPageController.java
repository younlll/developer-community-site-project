package project.developmentcomunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join/joinMemberForm";
    }
}
