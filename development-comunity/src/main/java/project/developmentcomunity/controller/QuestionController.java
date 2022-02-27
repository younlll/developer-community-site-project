package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.CategoryRepository;
import project.developmentcomunity.service.CategoryService;
import project.developmentcomunity.service.QuestionService;
import project.developmentcomunity.service.ReplyService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class QuestionController {

    @Autowired QuestionService questionService;
    @Autowired CategoryService categoryService;
    @Autowired ReplyService replyService;

    @GetMapping("/page/registration")
    public String moveToRegPage(@RequestParam("linkId") long linkId, @RequestParam("idUser") long idUser, Model model) {
        model.addAttribute("linkId", linkId);
        model.addAttribute("idUser", idUser);
        return "page/regQuestion";
    }

    @PostMapping("/page/registration/mainPage")
    public String regDescription(@RequestParam("linkId") long linkId, @RequestParam("idUser") long idUser, @Valid Question question, Model model) {
        question.setCategoryId(linkId);
        question.setUserId(idUser);
        questionService.registrationQuestion(question);
        List<Category> categories = categoryService.inqAllCategoryList();
        User loginUser = new User();
        loginUser.setIdUser(idUser);
        model.addAttribute("categories", categories);
        model.addAttribute("loginUser", loginUser);

        return "page/mainPage";
    }

    @GetMapping("/page/questionDetail")
    public String inqQuestionDetail(@RequestParam("linkId") long linkId, @RequestParam("questionId") long questionId, @RequestParam("idUser") long idUser, Model model) {
        Question questionDetail = questionService.inqQuestionDetail(questionId, linkId).get();
        model.addAttribute("questionDetail", questionDetail);
        model.addAttribute("linkId", linkId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("idUser", idUser);

        List<Reply> replies = replyService.inqReplyList(questionId, linkId);
        model.addAttribute("replies", replies);

        return "page/questionDetail";
    }

    @PostMapping("/page/updQuestion")
    public String updQuestion(@RequestParam("linkId") long linkId, @RequestParam("questionId") long questionId, @RequestParam("idUser") long idUser, Question question, Model model) {
        question.setQuestionId(questionId);
        question.setCategoryId(linkId);
        question.setUserId(idUser);
        questionService.updQuestionDetail(question);

        List<Category> categories = categoryService.inqAllCategoryList();
        User loginUser = new User();
        loginUser.setIdUser(idUser);
        model.addAttribute("categories", categories);
        model.addAttribute("loginUser", loginUser);

        return "page/mainPage";
    }

    @GetMapping("/page/delQuestion")
    public String delQuestion(@RequestParam("linkId") long linkId, @RequestParam("questionId") long questionId, @RequestParam("idUser") long idUser, Question question, Model model) {
        question.setQuestionId(questionId);
        question.setCategoryId(linkId);
        question.setUserId(idUser);
        questionService.delQuestion(question);

        List<Category> categories = categoryService.inqAllCategoryList();
        User loginUser = new User();
        loginUser.setIdUser(idUser);
        model.addAttribute("categories", categories);
        model.addAttribute("loginUser", loginUser);

        return "page/mainPage";
    }

    @PostMapping("/search")
    public String searchQuestion(@RequestParam("linkId") long linkId, @RequestParam("idUser") long idUser, SearchForm searchForm, Model model) {
        String searchNum = searchForm.getSearch();
        List<Question> qnas;

        switch (searchNum) {
            case "1":
                qnas = categoryService.inqQuestionByCategory(linkId);
                model.addAttribute("qnas", qnas);
                model.addAttribute("linkId", linkId);
                model.addAttribute("idUser", idUser);
                break;
            case "2":
                qnas = questionService.inqQuestionbyTitle(linkId, searchForm.getSearchContent());
                model.addAttribute("qnas", qnas);
                model.addAttribute("linkId", linkId);
                model.addAttribute("idUser", idUser);
                break;
            case "3":
                qnas = questionService.inqQuestionbyDescription(linkId, searchForm.getSearchContent());
                model.addAttribute("qnas", qnas);
                model.addAttribute("linkId", linkId);
                model.addAttribute("idUser", idUser);
                break;
        }

        return "page/qnaList";
    }
}
