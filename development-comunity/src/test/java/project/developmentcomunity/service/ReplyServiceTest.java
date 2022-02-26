package project.developmentcomunity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.repository.ReplyRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ReplyServiceTest {

    @Autowired ReplyService replyService;
    @Autowired ReplyRepository replyRepository;

    @Test
    void 답글_채번() {
        long replyId = replyService.numberingReplyId(1L, 1100L);
        assertThat(replyService.inqReplyId(1L, 1100L, replyId).isPresent()).isEqualTo(false);
    }

    @Test
    void 답글_등록() {

        Reply reply = new Reply();
        long replyId = replyService.numberingReplyId(1L, 1100L);
        reply.setReplyId(replyId);
        reply.setQuestionId(1L);
        reply.setCategoryId(1100L);
        reply.setUserId(2L);
        reply.setReplyDescription("reply description test");

        replyService.regReplyByQuesiton(reply);
        assertThat(replyService.inqReplyId(1L, 1100L, replyId).get().getReplyDescription()).isEqualTo(reply.getReplyDescription());
    }

    @Test
    void 답글_삭제() {
        Reply reply = new Reply();
        long replyId = replyService.numberingReplyId(1L, 1100L);
        reply.setReplyId(replyId);
        reply.setQuestionId(1L);
        reply.setCategoryId(1100L);
        reply.setUserId(2L);
        reply.setReplyDescription("reply description test");

        replyService.regReplyByQuesiton(reply);
        replyService.delReplyByQuestion(1L, 1100L, replyId);

        assertThat(replyService.inqReplyId(1L, 1100L, replyId).get().getEnabledYn()).isEqualTo("N");
    }
}
