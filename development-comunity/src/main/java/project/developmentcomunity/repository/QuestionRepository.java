package project.developmentcomunity.repository;

import project.developmentcomunity.domain.Question;

import java.util.Optional;

public interface QuestionRepository {

    Long inqMaxQuestionId();
    Optional<Question> inqQuestionId(long questionId);
    void regQuestion(Question question);
    Optional<Question> inqQuestionDetail(long questionId, long categoryId);
    void updQuestionDetail(Question question);
    void delQuestion(Question question);
}
