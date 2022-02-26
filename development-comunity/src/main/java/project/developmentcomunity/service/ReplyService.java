package project.developmentcomunity.service;

import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.ReplyRepository;
import project.developmentcomunity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    /**
     * 답변ID 채번
     */
    public Long numberingReplyId(long questionId, long linkId) {
        return replyRepository.inqMaxReplyId(questionId, linkId) + 1;
    }

    /**
     * 답변등록
     */
    public void regReplyByQuesiton(Reply reply) {
        long replyId = numberingReplyId(reply.getQuestionId(), reply.getCategoryId());
        reply.setReplyId(replyId);
        replyRepository.regReplyByQuestion(reply);
    }

    /**
     * 답변ID 채번 조회
     */
    public Optional<Reply> inqReplyId(long questionId, long categoryId, long replyId) {
        return replyRepository.inqReplyId(questionId, categoryId, replyId);
    }

    /**
     * 답변ID를 통한 삭제
     */
    public void delReplyByQuestion(long questionId, long categoryId, long replyId) {
        replyRepository.delReplyByQuestion(questionId, categoryId, replyId);
    }

    /**
     * 게시물에 대한 답변 리스트 조회
     */
    public List<Reply> inqReplyList(long questionId, long categoryoId) {
        return replyRepository.inqReplyList(questionId, categoryoId);
    }

    /**
     * 사용자별 등록 답변 리스트 조회
     */
    public List<Reply> inqReplyByUser(long userId) {
        return replyRepository.inqReplyByUser(userId);
    }
}
