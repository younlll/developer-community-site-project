package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.service.QuestionService;

@Controller
public class QuestionController {

    @Autowired QuestionService questionService;

    @GetMapping("/page/registration")
    public String moveToRegPage() {
        return "page/regQuestion";
    }

    @PostMapping("/page/registration/mainPage")
    public String regDescription(Question question) {
        questionService.registrationQuestion(question);
        return "page/mainPage";
    }
}
