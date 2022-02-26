package project.developmentcomunity.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import project.developmentcomunity.domain.Reply;
import project.developmentcomunity.domain.User;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateReplyRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long inqMaxReplyId(long questionId, long categoryId) {
        return jdbcTemplate.queryForObject("select coalesce(max(reply_id), 0) max_reply_id from reply_by_question", Long.class);
    }

    @Override
    public void regReplyByQuestion(Reply reply) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("reply_by_question");
        Map<String, Object> parm = new HashMap<>();

        parm.put("reply_id", reply.getReplyId());
        parm.put("question_id", reply.getQuestionId());
        parm.put("category_id", reply.getCategoryId());
        parm.put("user_id", reply.getUserId());
        parm.put("description", reply.getReplyDescription());
        parm.put("enabled_yn", 'Y');
        parm.put("reg_dttm", new Timestamp(System.currentTimeMillis()));
        parm.put("upd_dttm", new Timestamp(System.currentTimeMillis()));

        jdbcInsert.execute(new MapSqlParameterSource(parm));
    }

    @Override
    public Optional<Reply> inqReplyId(long questionId, long categoryId, long replyId) {
        List<Reply> result = jdbcTemplate.query("select * from reply_by_question\n" +
                "where reply_id = ?\n" +
                "   and question_id = ?\n" +
                "   and category_id = ?", replyRowMapper(), replyId, questionId, categoryId);
        return result.stream().findAny();
    }

    @Override
    public void delReplyByQuestion(long questionId, long categoryId, long replyId) {
        jdbcTemplate.update("update reply_by_question\n" +
                "set enabled_yn = 'N'\n" +
                "where question_id = ?\n" +
                "   and category_id = ?\n" +
                "   and reply_id = ?", questionId, categoryId, replyId);
    }

    @Override
    public List<Reply> inqReplyList(long questionId, long categoryId) {
        return jdbcTemplate.query("select u.nick_name nick_name\n" +
                "   , rbq.*\n" +
                "from reply_by_question rbq\n" +
                "   , user u\n" +
                "where rbq.question_id = ?\n" +
                "   and rbq.category_id = ?\n" +
                "   and rbq.user_id = u.user_id\n" +
                "   and rbq.enabled_yn = 'Y'", replyListRowMapper(), questionId, categoryId);
    }

    @Override
    public List<Reply> inqReplyByUser(long userId) {
        return jdbcTemplate.query("select qbc.question_title question_title\n" +
                "   , rbq.*\n" +
                "from reply_by_question rbq\n" +
                "   , question_by_category qbc\n" +
                "where rbq.user_id = ?\n" +
                "   and rbq.question_id = qbc.question_id\n" +
                "   and rbq.category_id = qbc.category_id\n" +
                "   and rbq.enabled_yn = 'Y'", replyByUserListRowMapper(), userId);
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setReplyId(rs.getLong("reply_id"));
            reply.setCategoryId(rs.getLong("category_id"));
            reply.setQuestionId(rs.getLong("question_id"));
            reply.setUserId(rs.getLong("user_id"));
            reply.setReplyDescription(rs.getString("description"));
            reply.setEnabledYn(rs.getString("enabled_yn"));
            return reply;
        };
    }

    private RowMapper<Reply> replyListRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setReplyId(rs.getLong("reply_id"));
            reply.setCategoryId(rs.getLong("category_id"));
            reply.setQuestionId(rs.getLong("question_id"));
            reply.setUserId(rs.getLong("user_id"));
            reply.setNickName(rs.getString("nick_name"));
            reply.setReplyDescription(rs.getString("description"));
            reply.setRegDttm(rs.getString("reg_dttm"));
            return reply;
        };
    }

    private RowMapper<Reply> replyByUserListRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setReplyId(rs.getLong("reply_id"));
            reply.setCategoryId(rs.getLong("category_id"));
            reply.setQuestionId(rs.getLong("question_id"));
            reply.setQuestionTitle(rs.getString("question_title"));
            reply.setReplyDescription(rs.getString("description"));
            reply.setRegDttm(rs.getString("reg_dttm"));
            return reply;
        };
    }
}
