package project.developmentcomunity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class QuestionServiceTest {

    @Autowired QuestionService questionService;

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
//
//    @Test
//    void 질문_상세_조회() {
//
//    }
//
//    @Test
//    void 질문_삭제() {
//
//    }
//
//    @Test
//    void 질문_수정() {
//
//    }
}