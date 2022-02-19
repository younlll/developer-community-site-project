package project.developmentcomunity.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import project.developmentcomunity.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void joinUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user");
        Map<String, Object> parm = new HashMap<>();

        parm.put("user_id", user.getIdUser());
        parm.put("email", user.getEmail());
        parm.put("name", user.getName());
        parm.put("password", user.getPassword());
        parm.put("nick_name", user.getNickName());
        parm.put("github", user.getGithubUrl());
        parm.put("blog", user.getBlogUrl());

        jdbcInsert.execute(new MapSqlParameterSource(parm));
    }

    @Override
    public Long inqMaxUserId() {
        return jdbcTemplate.queryForObject("select coalesce(max(user_id), 0) max_user_id from user", Long.class);
    }

    @Override
    public Optional<User> inqUserEmail(String userEmail) {
        List<User> result = jdbcTemplate.query("select * from user where email = ?", userRowMapper(), userEmail);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> inqUserId(Long userId) {
        List<User> result = jdbcTemplate.query("select * from user where user_id = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setIdUser(rs.getLong("user_id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setNickName(rs.getString("nick_name"));

            return user;
        };
    }
}
