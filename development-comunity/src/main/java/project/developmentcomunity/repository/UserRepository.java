package project.developmentcomunity.repository;

import project.developmentcomunity.domain.User;

import java.util.Optional;

public interface UserRepository {

    // 회원가입
    void save(User user);

    // 회원ID 채번
    Long inqMaxUserId();

    // 회원Email 조회
    boolean findByEmail(String email);

    // 회원ID 조회
    Optional<User> findById(Long userId);

    // 회원 password 조회
    String inqUserPassword(String userEmail);
}
