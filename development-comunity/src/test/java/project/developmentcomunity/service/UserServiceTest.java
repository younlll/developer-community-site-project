package project.developmentcomunity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    void 회원번호_채번() {
        int userId = userService.inqMaxUserId().get();
        List<User> findUserId = userService.inqUserId(userId).get();

        assertThat(findUserId.size()).isEqualTo(0);
    }

    @Test
    void 회원가입() {
        User user = new User();
        int userId = userService.inqMaxUserId().get();
        user.setIdUser(userId);
        user.setName("이유나");
        user.setPassword("password");
        user.setNickName("youn");
        user.setEmail("youn.lee96@gmail.com");

        userService.joinUser(user);

        List<User> findUserId = userService.inqUserId(userId).get();
        assertThat(findUserId.get(0).getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void 중복회원_예외() {
        User user1 = new User();
        int userId1 = userService.inqMaxUserId().get();
        user1.setIdUser(userId1);
        user1.setName("이유나");
        user1.setPassword("password");
        user1.setNickName("youn");
        user1.setEmail("youn.lee96@gmail.com");

        User user2 = new User();
        int userId2 = userService.inqMaxUserId().get();
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