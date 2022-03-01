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
import project.developmentcomunity.domain.Question;

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
class QuestionServiceTest {

    @Autowired QuestionService questionService;

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void 질문ID_채번() {
        long questionId = questionService.numberingQuestionId();
        assertThat(questionService.inqQuestionId(questionId).isPresent()).isEqualTo(false);
    }

    @Test
    void 질문_등록() {
        Question question = new Question();
        long questionId = questionService.numberingQuestionId();
        question.setQuestionId(questionId);
        question.setCategoryId(1100);
        question.setQuestionTitle("registration quest backend title ver1.1");
        question.setUserId(1);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("registration quest backend description test ver1.1");

        questionService.registrationQuestion(question);

        assertThat(question.getQuestionId()).isEqualTo(questionId);
    }

    @Test
    void 질문_상세_조회() {
        long questionId = 1L;
        long categoryId = 1100L;
        Question question = questionService.inqQuestionDetail(questionId, categoryId).get();

        assertThat(question.getDescription()).isEqualTo("backend question description test ver1.0");
    }

    @Test
    void 질문_삭제() {
        Question question = new Question();
        long questionId = questionService.numberingQuestionId();
        question.setQuestionId(questionId);
        question.setCategoryId(1200L);
        question.setQuestionTitle("registration quest backend title ver1.2");
        question.setUserId(1L);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("registration quest backend description test ver1.2");

        questionService.registrationQuestion(question);
        questionService.delQuestion(question);
        assertThat(questionService.inqQuestionDetail(questionId, 1100L).isPresent()).isEqualTo(false);
    }

    @Test
    void 질문_수정() {
        Question question = new Question();
        long questionId = questionService.numberingQuestionId();
        question.setQuestionId(questionId);
        question.setCategoryId(1300L);
        question.setQuestionTitle("registration quest backend title ver1.3");
        question.setUserId(1L);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("registration quest backend description test ver1.3");

        questionService.registrationQuestion(question);

        Question question2 = questionService.inqQuestionDetail(questionId, 1300L).get();
        question2.setDescription("registration quest backend description update test ver1.3");
        questionService.updQuestionDetail(question2);

        Question question3 = questionService.inqQuestionDetail(question2.getQuestionId(), 1300L).get();
        assertThat(question3.getDescription()).isEqualTo("registration quest backend description update test ver1.3");
    }

    @Test
    void 다른_계정_질문_삭제_오류() {
        Question question = new Question();
        long questionId = questionService.numberingQuestionId();
        question.setQuestionId(questionId);
        question.setCategoryId(1300L);
        question.setQuestionTitle("registration quest backend title ver1.3");
        question.setUserId(1L);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("registration quest backend description test ver1.3");

        questionService.registrationQuestion(question);

        Question question1 = new Question();
        question1.setQuestionId(questionId);
        question1.setCategoryId(1300L);
        question1.setUserId(2L);

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> questionService.delQuestion(question1));
        assertThat(e.getMessage()).isEqualTo("작성자와 로그인 계정이 다릅니다(본인의 게시물만 삭제할 수 있습니다)");
    }

    @Test
    void 다른_계정_질문_수정_오류() {
        Question question = new Question();
        long questionId = questionService.numberingQuestionId();
        question.setQuestionId(questionId);
        question.setCategoryId(1300L);
        question.setQuestionTitle("registration quest backend title ver1.3");
        question.setUserId(1L);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("registration quest backend description test ver1.3");

        questionService.registrationQuestion(question);

        Question question1 = new Question();
        question1.setQuestionId(questionId);
        question1.setCategoryId(1300L);
        question1.setUserId(2L);
        question1.setDescription("registration quest backend description test ver1.3 update");

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> questionService.updQuestionDetail(question1));
        assertThat(e.getMessage()).isEqualTo("작성자와 로그인 계정이 다릅니다(본인의 게시물만 수정할 수 있습니다)");
    }

    @Test
    void 질문_조회수_증가() {
        long bfViews = questionService.inqQuestionView(1L, 1100L);

        Question question = questionService.inqQuestionDetail(1L, 1100L).get();
        assertThat(question.getViews()).isEqualTo(bfViews + 1);
    }

    @Test
    void 질문_null_체크() {
        Question question = new Question();
        question.setQuestionId(questionService.numberingQuestionId());
        question.setCategoryId(1100);
        question.setQuestionTitle("질문 null 입력 체크");
        question.setUserId(1);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("");

        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("질문 내용을 필수로 입력해주세요.");
        });
    }

    @Test
    void 게시글_유효성_체크() {
        Question question = new Question();
        question.setQuestionId(questionService.numberingQuestionId());
        question.setCategoryId(1100);
        question.setQuestionTitle("질문 유효성 입력 체크");
        question.setUserId(1);
        question.setEnabledYn("Y");
        question.setViews(0L);
        question.setDescription("질문 유효성 입력 체크");

        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        assertThat(violations).isEmpty();
    }
}