package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.service.QuestionService;
import project.developmentcomunity.service.ReplyService;

@Controller
public class ReplyController {

    @Autowired QuestionService questionService;
    @Autowired ReplyService replyService;

    @PostMapping("/page/registration/reply")
    public String registrationReply(@RequestParam("linkId") long linkId, @RequestParam("questionId") long questionId, @RequestParam("idUser") long idUser, Reply reply, Model model) {
        Reply insReply = new Reply();
        insReply.setQuestion_id(questionId);
        insReply.setCategory_id(linkId);
        insReply.setUser_id(idUser);
        insReply.setReplyDescription(reply.getReplyDescription());
        replyService.regReplyByQuesiton(insReply);

        Question questionDetail = questionService.inqQuestionDetail(questionId, linkId).get();
        model.addAttribute("questionDetail", questionDetail);
        model.addAttribute("linkId", linkId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("idUser", idUser);
        return "page/questionDetail";
    }
}
