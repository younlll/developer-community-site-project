package project.developmentcomunity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

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
        user.setPassword("password");
        user.setNickName("youn");
        user.setEmail("youn.lee96@gmail.com");

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
        user1.setEmail("youn.lee96@gmail.com");

        User user2 = new User();
        long userId2 = userService.numberingUserId();
        user2.setIdUser(userId2);
        user2.setName("이유나");
        user2.setPassword("password");
        user2.setNickName("youn");
        user2.setEmail("youn.lee96@gmail.com");

        userService.joinUser(user1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> userService.joinUser(user2));
        assertThat(e.getMessage()).isEqualTo("이미 가입되어 있는 이메일 주소입니다.");
    }
}