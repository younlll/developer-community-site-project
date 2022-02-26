package project.developmentcomunity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.repository.ReplyRepository;
import project.developmentcomunity.repository.UserRepository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ReplyServiceTest {

    @Autowired ReplyService replyService;
    @Autowired ReplyRepository replyRepository;

    @Test
    void 답글_채번() {
        long replyId = replyService.numberingReplyId(1L, 1100L);
        assertThat(replyService.inqReplyId(replyId).isPresent()).isEqualTo(false);
    }

    @Test
    void 답글_등록() {

        Reply reply = new Reply();
        long replyId = replyService.numberingReplyId(1L, 1100L);
        reply.setReply_id(replyId);
        reply.setQuestion_id(1L);
        reply.setCategory_id(1100L);
        reply.setUser_id(2L);
        reply.setReplyDescription("reply description test");

        replyService.regReplyByQuesiton(reply);

        assertThat(replyService.inqReplyId(replyId).get().getReplyDescription()).isEqualTo(reply.getReplyDescription());
    }

    @Test
    void 답글_삭제() {
        Reply reply = new Reply();
        long replyId = replyService.numberingReplyId(1L, 1100L);
        reply.setReply_id(replyId);
        reply.setQuestion_id(1L);
        reply.setCategory_id(1100L);
        reply.setUser_id(2L);
        reply.setReplyDescription("reply description test");

        replyService.regReplyByQuesiton(reply);
        replyService.delReplyByQuestion(replyId);
        
        assertThat(replyService.inqReplyId(replyId).isPresent()).isEqualTo(false);
    }
}
