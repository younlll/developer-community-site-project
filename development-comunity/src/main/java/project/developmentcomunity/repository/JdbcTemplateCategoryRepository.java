package project.developmentcomunity.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.Question;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateCategoryRepository implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCategoryRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Category> inqCategoryList() {
        return jdbcTemplate.query("select * from category where use_yn = 'Y'", categoryRowMapper());
    }

    @Override
    public List<Question> inqQuestionList(long categoryId) {
        return jdbcTemplate.query("select qbc.*\n" +
                "   , u.nick_name\n" +
                "from question_by_category qbc\n" +
                "   , user u\n" +
                "where qbc.user_id = u.user_id\n" +
                "   and qbc.enabled_yn = 'Y'\n" +
                "   and qbc.category_id = ?", questionRowMapper(), categoryId);
    }

    private RowMapper<Category> categoryRowMapper() {
        return (rs, rowNum) -> {
            Category category = new Category();
            category.setCategoryId(rs.getLong("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            category.setLinkPageUrl(rs.getString("link_page_url"));

            return category;
        };
    }

    private RowMapper<Question> questionRowMapper() {
        return (rs, rowNum) -> {
            Question question = new Question();
            question.setQuestionId(rs.getLong("question_id"));
            question.setCategoryId(rs.getLong("category_id"));
            question.setQuestionTitle(rs.getString("question_title"));
            question.setViews(rs.getLong("views"));
            question.setRegDttm(rs.getString("reg_dttm"));
            question.setUpdDttm(rs.getString("upd_dttm"));
            question.setNickName(rs.getString("nick_name"));

            return question;
        };
    }
}
