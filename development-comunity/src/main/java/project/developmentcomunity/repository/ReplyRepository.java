package project.developmentcomunity.repository;

import jdk.javadoc.doclet.Reporter;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    Long inqMaxReplyId(long questionId, long categoryId);
    void regReplyByQuestion(Reply reply);
    Optional<Reply> inqReplyId(long questionId, long categoryId, long replyId);
    void delReplyByQuestion(long questionId, long categoryId, long replyId);
    List<Reply> inqReplyList(long questionId, long categoryId);

}
