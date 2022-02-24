package project.developmentcomunity.service;

import project.developmentcomunity.domain.Question;
import project.developmentcomunity.repository.QuestionRepository;
import project.developmentcomunity.repository.UserRepository;

import java.util.Optional;

public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * 질문ID 채번
     */
    public long numberingQuestionId() {
        return questionRepository.inqMaxQuestionId() + 1;
    }

    /**
     * 질문ID를 통한 조회
     */
    public Optional<Question> inqQuestionId(long questionId) {
        return questionRepository.inqQuestionId(questionId);
    }

    /**
     * 질문 등록
     */
    public void registrationQuestion(Question question) {
        long questionId = numberingQuestionId();
        question.setQuestionId(questionId);
        questionRepository.regQuestion(question);
    }

    /**
     * 질문 상세조회
     */
    public Optional<Question> inqQuestionDetail(long questionId, long categoryId) {
        return questionRepository.inqQuestionDetail(questionId, categoryId);
    }

    /**
     * 질문 수정
     */
    public void updQuestionDetail(Question question) {
        questionRepository.updQuestionDetail(question);
    }
}