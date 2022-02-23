package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.service.CategoryService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired CategoryService categoryService;

    @GetMapping("/page/qnaList")
    public String qnaList(@RequestParam("linkId") long linkId, @RequestParam("idUser") long idUser, Category category, Model model) {
        List<Question> qnas = categoryService.inqQuestionByCategory(category.getCategoryId());
        model.addAttribute("qnas", qnas);
        model.addAttribute("linkId", linkId);
        model.addAttribute("idUser", idUser);
        return "page/qnaList";
    }
}
