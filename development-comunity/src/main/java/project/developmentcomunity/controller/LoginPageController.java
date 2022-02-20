package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.service.CategoryService;
import project.developmentcomunity.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginPageController {

    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join/joinMemberForm";
    }

    @RequestMapping(value ="/page/mainPage", method = RequestMethod.POST)
    public String loginCheck(LoginForm loginForm, Model model, HttpSession session) throws Exception {
        if(!userService.userLogin(loginForm)) {
            throw new IllegalStateException("가입되어 있지 않은 계정입니다.(이메일 또는 비밀번호를 확인해 주세요)");
        }

        User loginUser = userService.inqUserEmail(loginForm.getEmail()).get();
        model.addAttribute("loginUser", loginUser);

        List<Category> categories = categoryService.inqAllCategoryList();
        model.addAttribute("categories", categories);

        return "page/mainPage";
    }
}
