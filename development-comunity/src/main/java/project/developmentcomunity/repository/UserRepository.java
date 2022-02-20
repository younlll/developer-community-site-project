package project.developmentcomunity.repository;

import project.developmentcomunity.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface UserRepository {

    // 회원가입
    void joinUser(User user);

    // 회원ID 채번
    Long inqMaxUserId();

    // 회원Email 조회
    Optional<User> inqUserEmail(String userEmail);

    // 회원ID 조회
    Optional<User> inqUserId(Long userId);

    // 회원 password 조회
    String inqUserPassword(String userEmail);
}
