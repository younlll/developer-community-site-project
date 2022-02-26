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

        parm.put("reply_id", reply.getReply_id());
        parm.put("question_id", reply.getQuestion_id());
        parm.put("category_id", reply.getCategory_id());
        parm.put("user_id", reply.getUser_id());
        parm.put("description", reply.getReplyDescription());
        parm.put("enabled_yn", 'Y');
        parm.put("reg_dttm", new Timestamp(System.currentTimeMillis()));
        parm.put("upd_dttm", new Timestamp(System.currentTimeMillis()));

        jdbcInsert.execute(new MapSqlParameterSource(parm));
    }

    @Override
    public Optional<Reply> inqReplyId(Long replyId) {
        List<Reply> result = jdbcTemplate.query("select * from reply_by_question where reply_id = ?", replyRowMapper(), replyId);
        return result.stream().findAny();
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setReply_id(rs.getLong("reply_id"));
            reply.setCategory_id(rs.getLong("category_id"));
            reply.setQuestion_id(rs.getLong("question_id"));
            reply.setUser_id(rs.getLong("user_id"));
            reply.setReplyDescription(rs.getString("description"));
            return reply;
        };
    }
}
