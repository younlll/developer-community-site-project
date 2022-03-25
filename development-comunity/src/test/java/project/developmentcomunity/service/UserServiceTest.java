package project.developmentcomunity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.dto.LoginForm;
import project.developmentcomunity.domain.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired UserService userService;

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void 회원번호_채번() {
        long userId = userService.numberingUserId();
        assertThat(userService.inqUserId(userId).isPresent()).isEqualTo(false);
    }

    @Test
    void 회원가입() {
        User user = new User();
        long userId = userService.numberingUserId();
        user.setIdUser(userId);
        user.setName("이유나");
        user.setPassword("password1!");
        user.setNickName("youn");
        user.setEmail("jointestmail@gmail.com");

        userService.joinUser(user);

        User findUser = userService.inqUserEmail(user.getEmail()).get();
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    void 중복회원_예외() {
        User user1 = new User();
        long userId1 = userService.numberingUserId();
        user1.setIdUser(userId1);
        user1.setName("이유나");
        user1.setPassword("password");
        user1.setNickName("youn");
        user1.setEmail("testmail@gmail.com");

        User user2 = new User();
        long userId2 = userService.numberingUserId();
        user2.setIdUser(userId2);
        user2.setName("이유나");
        user2.setPassword("password");
        user2.setNickName("youn");
        user2.setEmail("testmail@gmail.com");

        userService.joinUser(user1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> userService.joinUser(user2));
        assertThat(e.getMessage()).isEqualTo("이미 가입되어 있는 이메일 주소입니다.");
    }

    @Test
    void 로그인_성공() {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("abc@gmail.com");
        loginForm.setPassword("1234");
        assertThat(userService.userLogin(loginForm)).isEqualTo(true);
    }

    @Test
    void 로그인_실패() {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("abc@gmail.com");
        loginForm.setPassword("12346789");
        userService.userLogin(loginForm);
        assertThat(userService.userLogin(loginForm)).isEqualTo(false);
    }

    @Test
    void 회원가입_입력값_null_체크() {
        User user = new User();
        long userId = userService.numberingUserId();
        user.setIdUser(userId);
        user.setName("이유나");
        user.setPassword("password1!");
        user.setNickName("youn");
        user.setEmail("");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("이메일 입력은 필수입니다.");
        });
    }

    @Test
    void 이메일_유효성_체크() {
        User user = new User();
        long userId = userService.numberingUserId();
        user.setIdUser(userId);
        user.setName("이유나");
        user.setPassword("password1!");
        user.setNickName("youn");
        user.setEmail("abc@naver.com");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).isEmpty();
    }
}