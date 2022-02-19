package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.service.UserService;

@Controller
public class JoinPageController {

    private final UserService userService;

    @Autowired
    public JoinPageController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public String joinMember(JoinForm joinForm) {
        User user = new User();
        user.setEmail(joinForm.getEmail());
        user.setPassword(joinForm.getPassword());
        user.setNickName(joinForm.getNickName());
        user.setName(joinForm.getName());

        userService.joinUser(user);

        return "redirect:/";
    }
}
