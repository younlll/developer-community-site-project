package project.developmentcomunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.developmentcomunity.repository.JpaUserRepository;
import project.developmentcomunity.repository.UserRepository;
import project.developmentcomunity.service.UserService;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
//        return new JdbcTemplateUserRepository(dataSource);
        return new JpaUserRepository(em);
    }

//    @Bean
//    public CategoryService categoryService() {
//        return new CategoryService(categoryRepository());
//    }
//
//    @Bean
//    public CategoryRepository categoryRepository() {
//        return new JdbcTemplateCategoryRepository(dataSource);
//    }
//
//    @Bean
//    public QuestionService questionService() {
//        return new QuestionService(questionRepository());
//    }
//
//    @Bean
//    public QuestionRepository questionRepository() {
//        return new JdbcTemplateQuestionRepository(dataSource);
//    }
//
//    @Bean
//    public ReplyService replyService() {
//        return new ReplyService(replyRepository());
//    }
//
//    @Bean
//    public ReplyRepository replyRepository() {
//        return new JdbcTemplateReplyRepository(dataSource);
//    }
}
