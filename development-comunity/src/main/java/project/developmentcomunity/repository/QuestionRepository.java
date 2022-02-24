package project.developmentcomunity.repository;

import project.developmentcomunity.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Long inqMaxQuestionId();
    Optional<Question> inqQuestionId(long questionId);
    void regQuestion(Question question);
    Optional<Question> inqQuestionDetail(long questionId, long categoryId);
    void updQuestionDetail(Question question);
    void delQuestion(Question question);
    void updQuestionView(long questionId, long categoryId);
    Long inqQuestionView(long questionId, long categoryId);
    List<Question> inqQuestionbyTitle(long categoryId, String tile);
    List<Question> inqQuestionbyDescription(long categoryId, String description);
}
