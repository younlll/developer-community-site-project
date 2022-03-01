package project.developmentcomunity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.domain.Reply;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
public class ReplyServiceTest {

    @Autowired ReplyService replyService;

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
        replyService.delReplyByQuestion(1L, 1100L, replyId, 2L);

        assertThat(replyService.inqReplyId(1L, 1100L, replyId).get().getEnabledYn()).isEqualTo("N");
    }

    @Test
    void 댓글_null_체크() {
        Reply reply = new Reply();
        reply.setReplyId(replyService.numberingReplyId(1L, 1100L));
        reply.setQuestionId(1L);
        reply.setCategoryId(1100L);
        reply.setUserId(2L);
        reply.setReplyDescription("");

        Set<ConstraintViolation<Reply>> violations = validator.validate(reply);

        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("댓글의 내용을 입력해주세요.");
        });
    }

    @Test
    void 댓글_유효성_체크() {
        Reply reply = new Reply();
        reply.setReplyId(replyService.numberingReplyId(1L, 1100L));
        reply.setQuestionId(1L);
        reply.setCategoryId(1100L);
        reply.setUserId(2L);
        reply.setReplyDescription("댓글 유효성 체크");

        Set<ConstraintViolation<Reply>> violations = validator.validate(reply);

        assertThat(violations).isEmpty();
    }

    @Test
    void 다른계정_댓글_삭제_오류() {
        Reply reply = new Reply();
        long replyId = replyService.numberingReplyId(1L, 1100L);
        reply.setReplyId(replyId);
        reply.setQuestionId(1L);
        reply.setCategoryId(1100L);
        reply.setUserId(2L);
        reply.setReplyDescription("reply description test");

        replyService.regReplyByQuesiton(reply);

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> replyService.delReplyByQuestion(1L, 1100L, replyId, 1L));
        assertThat(e.getMessage()).isEqualTo("로그인한 계정으로 작성된 댓글이 아닙니다. 본인의 댓글만 삭제할 수 있습니다.");
    }
}
