package project.developmentcomunity.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import project.developmentcomunity.domain.Category;
import project.developmentcomunity.domain.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateCategoryRepository implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCategoryRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Category> inqCategoryList() {
        return jdbcTemplate.query("select * from category where use_yn = 'Y'", categoryRowMapper());
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
}
