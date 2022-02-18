package project.developmentcomunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginPageController {

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/page")
    public String joinPage() {
        return "page/mainPage";
    }
}
