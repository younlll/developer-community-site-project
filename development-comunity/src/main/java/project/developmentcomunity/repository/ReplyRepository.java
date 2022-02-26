package project.developmentcomunity.repository;

import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;

import java.util.Optional;

public interface ReplyRepository {

    // 답변ID 채번
    Long inqMaxReplyId(long questionId, long categoryId);

    // 답변 등록
    void regReplyByQuestion(Reply reply);

    // 답변ID 조회
    Optional<Reply> inqReplyId(Long replyId);
}
