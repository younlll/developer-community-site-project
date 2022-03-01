package project.developmentcomunity.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import project.developmentcomunity.domain.User;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
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
        jdbcTemplate.update("insert into user (user_id, email, name, password, nick_name) values (?, ?, ?, ?, ?)"
            , user.getIdUser(), user.getEmail(), user.getName(), user.getPassword(), user.getNickName());
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

    @Override
    public String inqUserPassword(String userEmail) {
        return jdbcTemplate.queryForObject("select password from user where email = ?", String.class, userEmail);
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
