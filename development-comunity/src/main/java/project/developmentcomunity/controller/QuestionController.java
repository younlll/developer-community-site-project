package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.repository.CategoryRepository;
import project.developmentcomunity.service.CategoryService;
import project.developmentcomunity.service.QuestionService;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired QuestionService questionService;
    @Autowired CategoryService categoryService;

    @GetMapping("/page/registration")
    public String moveToRegPage(@RequestParam("linkId") long linkId, @RequestParam("idUser") long idUser, Model model) {
        model.addAttribute("linkId", linkId);
        model.addAttribute("idUser", idUser);
        return "page/regQuestion";
    }

    @PostMapping("/page/registration/mainPage")
    public String regDescription(@RequestParam("linkId") long linkId, @RequestParam("idUser") long idUser, Question question, Model model) {
        question.setCategoryId(linkId);
        question.setUserId(idUser);
        questionService.registrationQuestion(question);
        List<Category> categories = categoryService.inqAllCategoryList();
        model.addAttribute("categories", categories);

        return "page/mainPage";
    }
}
