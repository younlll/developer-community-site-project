package project.developmentcomunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/page/qnaList")
    public String qnaList() {
        return "page/qnaList";
    }
}
