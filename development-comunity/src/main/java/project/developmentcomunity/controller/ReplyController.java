package project.developmentcomunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.service.QuestionService;
import project.developmentcomunity.service.ReplyService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReplyController {

    @Autowired QuestionService questionService;
    @Autowired ReplyService replyService;

    @PostMapping("/page/registration/reply")
    public String registrationReply(@RequestParam("linkId") long linkId, @RequestParam("questionId") long questionId, @RequestParam("idUser") long idUser, @Valid Reply reply, Model model) {
        Reply insReply = new Reply();
        insReply.setQuestionId(questionId);
        insReply.setCategoryId(linkId);
        insReply.setUserId(idUser);
        insReply.setReplyDescription(reply.getReplyDescription());
        replyService.regReplyByQuesiton(insReply);

        Question questionDetail = questionService.inqQuestionDetail(questionId, linkId).get();
        model.addAttribute("questionDetail", questionDetail);
        model.addAttribute("linkId", linkId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("idUser", idUser);

        List<Reply> replies = replyService.inqReplyList(questionId, linkId);
        model.addAttribute("replies", replies);

        return "page/questionDetail";
    }

    @GetMapping("/page/delReply")
    public String deleteReply(@RequestParam("linkId") long linkId, @RequestParam("questionId") long questionId, @RequestParam("idUser") long idUser, @RequestParam("replyId") long replyId, Reply reply, Model model) {
        replyService.delReplyByQuestion(questionId, linkId, replyId);

        Question questionDetail = questionService.inqQuestionDetail(questionId, linkId).get();
        model.addAttribute("questionDetail", questionDetail);
        model.addAttribute("linkId", linkId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("idUser", idUser);

        List<Reply> replies = replyService.inqReplyList(questionId, linkId);
        model.addAttribute("replies", replies);

        return "page/questionDetail";
    }
}
