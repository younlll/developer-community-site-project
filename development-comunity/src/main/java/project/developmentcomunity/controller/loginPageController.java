package project.developmentcomunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginPageController {

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

}
