package project.developmentcomunity.service;

import project.developmentcomunity.dto.JoinForm;
import project.developmentcomunity.dto.LoginForm;
import project.developmentcomunity.domain.User;
import project.developmentcomunity.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public void joinUser(JoinForm joinForm) {
        Long userId = numberingUserId();
        User user = JoinForm.toEntity(userId, joinForm.getEmail(), joinForm.getPassword(), joinForm.getNickName(), joinForm.getName());
        validateDuplicateUser(joinForm.getEmail());
        userRepository.save(user);
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
    private void validateDuplicateUser(String email) {
        if(userRepository.findByEmail(email)) {
            throw new IllegalStateException("이메일 중복 오류");
        }
    }

    /**
     * 회원 email을 통한 회원 조회
     */
    public boolean findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    /**
     * 회원ID 채번 조회
     */
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * 로그인 처리
     */
    public Boolean userLogin(LoginForm loginForm) {
        return loginForm.getPassword().equals(userRepository.inqUserPassword(loginForm.getEmail()));
    }
}
