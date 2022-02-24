package project.developmentcomunity.service;

import project.developmentcomunity.domain.Question;
import project.developmentcomunity.repository.QuestionRepository;
import project.developmentcomunity.repository.UserRepository;

import java.util.List;
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
        questionRepository.updQuestionView(questionId, categoryId);
        return questionRepository.inqQuestionDetail(questionId, categoryId);
    }

    /**
     * 질문 수정
     */
    public void updQuestionDetail(Question question) {
        Question questionDetail = questionRepository.inqQuestionDetail(question.getQuestionId(), question.getCategoryId()).get();
        if(questionDetail.getUserId() != question.getUserId()) {
            throw new IllegalStateException("작성자와 로그인 계정이 다릅니다(본인의 게시물만 수정할 수 있습니다)");
        }
        questionRepository.updQuestionDetail(question);
    }

    /**
     * 질문 삭제
     */
    public void delQuestion(Question question) {
        Question questionDetail = questionRepository.inqQuestionDetail(question.getQuestionId(), question.getCategoryId()).get();
        if(questionDetail.getUserId() != question.getUserId()) {
            throw new IllegalStateException("작성자와 로그인 계정이 다릅니다(본인의 게시물만 삭제할 수 있습니다)");
        }
        questionRepository.delQuestion(question);
    }

    /**
     * 질문 조회수 단건 조회
     */
    public Long inqQuestionView(long questionId, long categoryId) {
        return questionRepository.inqQuestionView(questionId, categoryId);
    }

    /**
     * 제목검색
     */
    public List<Question> inqQuestionbyTitle(long categoryId, String title) {
        return questionRepository.inqQuestionbyTitle(categoryId, title);
    }

    /**
     * 내용검색
     */
    public List<Question> inqQuestionbyDescription(long categoryId, String description) {
        return questionRepository.inqQuestionbyDescription(categoryId, description);
    }

    /**
     * 사용자별 질문 검색
     */
    public List<Question> inqQuestionByUser(long userId) {
        return questionRepository.inqQuestionByUser(userId);
    }
}
