package project.developmentcomunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.developmentcomunity.repository.JdbcTemplateUserRepository;
import project.developmentcomunity.repository.UserRepository;
import project.developmentcomunity.service.UserService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcTemplateUserRepository(dataSource);
    }
}
