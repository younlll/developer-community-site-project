package project.developmentcomunity.service;

import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.ReplyRepository;
import project.developmentcomunity.repository.UserRepository;

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
        long replyId = numberingReplyId(reply.getQuestion_id(), reply.getCategory_id());
        reply.setReply_id(replyId);
        replyRepository.regReplyByQuestion(reply);
    }

    /**
     * 답변ID 채번 조회
     */
    public Optional<Reply> inqReplyId(Long replyId) {
        return replyRepository.inqReplyId(replyId);
    }
}
