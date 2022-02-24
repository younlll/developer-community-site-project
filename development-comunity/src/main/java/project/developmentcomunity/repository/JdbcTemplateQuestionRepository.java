package project.developmentcomunity.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import project.developmentcomunity.domain.Question;
import project.developmentcomunity.domain.User;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateQuestionRepository implements QuestionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateQuestionRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long inqMaxQuestionId() {
        return jdbcTemplate.queryForObject("select coalesce(max(question_id), 0) from question_by_category", Long.class);
    }

    @Override
    public Optional<Question> inqQuestionId(long questionId) {
        List<Question> result = jdbcTemplate.query("select u.nick_name, qbc.*\n" +
                "from question_by_category qbc\n" +
                "   , user u\n" +
                "where qbc.user_id = u.user_id\n" +
                "   and qbc.question_id = ?", questionRowMapper(), questionId);
        return result.stream().findAny();
    }

    @Override
    public void regQuestion(Question question) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("question_by_category");
        Map<String, Object> parm = new HashMap<>();

        parm.put("question_id", question.getQuestionId());
        parm.put("category_id", question.getCategoryId());
        parm.put("question_title", question.getQuestionTitle());
        parm.put("user_id", question.getUserId());
        parm.put("enabled_yn", "Y");
        parm.put("views", 0L);
        parm.put("description", question.getDescription());
        parm.put("reg_dttm", new Timestamp(System.currentTimeMillis()));
        parm.put("upd_dttm", new Timestamp(System.currentTimeMillis()));

        jdbcInsert.execute(new MapSqlParameterSource(parm));
    }

    @Override
    public Optional<Question> inqQuestionDetail(long questionId, long categoryId) {
        List<Question> result = jdbcTemplate.query("select u.nick_name, qbc.*\n" +
                "from question_by_category qbc\n" +
                "   , user u\n" +
                "where qbc.user_id = u.user_id\n" +
                "   and qbc.question_id = ?\n" +
                "   and qbc.category_id = ?", questionRowMapper(), questionId, categoryId);
        return result.stream().findAny();
    }

    @Override
    public void updQuestionDetail(Question question) {
        jdbcTemplate.update("update question_by_category\n" +
                "set description = ?\n" +
                "where question_id = ?\n" +
                "   and category_id = ?", question.getDescription(), question.getQuestionId(), question.getCategoryId());
    }

    @Override
    public void delQuestion(Question question) {
//        jdbcTemplate.update("delete from question_by_category\n" +
//                "where question_id = ?\n" +
//                "   and category_id = ?", question.getQuestionId(), question.getCategoryId());
        jdbcTemplate.update("update question_by_category\n" +
                "set enabled_yn = 'N'\n" +
                "where question_id = ?\n" +
                "   and category_id = ?", question.getQuestionId(), question.getCategoryId());
    }

    @Override
    public void updQuestionView(long questionId, long categoryId) {
        jdbcTemplate.update("update question_by_category\n" +
                "set views = views + 1\n" +
                "where question_id = ?\n" +
                "   and category_id = ?", questionId, categoryId);
    }

    @Override
    public Long inqQuestionView(long questionId, long categoryId) {
        Long result = jdbcTemplate.queryForObject("select views \n" +
                "from question_by_category\n" +
                "where question_id = ?\n" +
                "   and category_id = ?\n" +
                "   and enabled_yn = 'Y'", Long.class, questionId, categoryId);
        return result;
    }

    @Override
    public List<Question> inqQuestionbyTitle(long categoryId, String title) {
        String likeTitle = "%" + title + "%";
        return jdbcTemplate.query("select u.nick_name, qbc.*\n" +
                "from question_by_category qbc\n" +
                "   , user u\n" +
                "where qbc.user_id = u.user_id\n" +
                "   and qbc.enabled_yn = 'Y'\n" +
                "   and qbc.category_id = ?\n" +
                "   and qbc.question_title like ?", questionRowMapper(), categoryId, likeTitle);
    }

    @Override
    public List<Question> inqQuestionbyDescription(long categoryId, String description) {
        String likeDescription = "%" + description + "%";
        return jdbcTemplate.query("select u.nick_name, qbc.*\n" +
                "from question_by_category qbc\n" +
                "   , user u\n" +
                "where qbc.user_id = u.user_id\n" +
                "   and qbc.enabled_yn = 'Y'\n" +
                "   and qbc.category_id = ?\n" +
                "   and qbc.description like ?", questionRowMapper(), categoryId, likeDescription);
    }

    private RowMapper<Question> questionRowMapper() {
        return (rs, rowNum) -> {
            Question question = new Question();
            question.setQuestionId(rs.getLong("question_id"));
            question.setCategoryId(rs.getLong("category_id"));
            question.setQuestionTitle(rs.getString("question_title"));
            question.setViews(rs.getLong("views"));
            question.setRegDttm(rs.getString("reg_dttm"));
            question.setUserId(rs.getLong("user_id"));
            question.setDescription(rs.getString("description"));
            question.setNickName(rs.getString("nick_name"));

            return question;
        };
    }
}
