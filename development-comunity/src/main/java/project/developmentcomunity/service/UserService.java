package project.developmentcomunity.service;

import project.developmentcomunity.controller.LoginForm;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public void joinUser(User user) {
        user.setIdUser(numberingUserId());
        validateDuplicateUser(user);
        userRepository.joinUser(user);
    }

    /**
     * 회원ID 채번
     */
    public long numberingUserId() {
        return userRepository.inqMaxUserId() + 1;
    }

    /**
     * 중복 회원 검사
     * 동일한 email 주소로 가입이 불가
     */
    private void validateDuplicateUser(User joinUser) {
        userRepository.inqUserEmail(joinUser.getEmail())
                .ifPresent(m -> {
                   throw new IllegalStateException("이미 가입되어 있는 이메일 주소입니다.");
                });
    }

    /**
     * 회원 email을 통한 회원 조회
     */
    public Optional<User> inqUserEmail(String userEmail) {
        return userRepository.inqUserEmail(userEmail);
    }

    /**
     * 회원ID 채번 조회
     */
    public Optional<User> inqUserId(Long userId) {
        return userRepository.inqUserId(userId);
    }

    /**
     * 로그인 처리
     */
    public Boolean userLogin(LoginForm loginForm) {
        return loginForm.getPassword().equals(userRepository.inqUserPassword(loginForm.getEmail()));
    }
}
