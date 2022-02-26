package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.service.CategoryService;
import project.developmentcomunity.service.QuestionService;
import project.developmentcomunity.service.ReplyService;
import project.developmentcomunity.service.UserService;

import java.util.List;

@Controller
public class IndividualController {

    @Autowired UserService userService;
    @Autowired CategoryService categoryService;
    @Autowired QuestionService questionService;
    @Autowired ReplyService replyService;

    @GetMapping("/individual")
    public String myPage(@RequestParam("idUser") long idUser, Model model) {
        User user = userService.inqUserId(idUser).get();
        model.addAttribute("userInfo", user);

        List<Question> qnas = questionService.inqQuestionByUser(idUser);
        model.addAttribute("qnas", qnas);

        List<Reply> replies = replyService.inqReplyByUser(idUser);
        model.addAttribute("replies", replies);

        return "individual/myPage";
    }

    @GetMapping("/individual/main")
    public String returnMainPage(@RequestParam("idUser") long idUser, Model model) {
        User loginUser = userService.inqUserId(idUser).get();
        model.addAttribute("loginUser",  loginUser);
        List<Category>categories = categoryService.inqAllCategoryList();
        model.addAttribute("categories", categories);

        return "page/mainPage";
    }
}
