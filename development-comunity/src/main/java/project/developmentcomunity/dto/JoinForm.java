package project.developmentcomunity.dto;

import lombok.Getter;
import lombok.Setter;
import project.developmentcomunity.domain.User;

@Getter
@Setter
public class JoinForm {

    private String email;
    private String password;
    private String nickName;
    private String name;

    public static User toEntity(Long userId, String email, String password, String nickName, String name) {
        return new User(userId
            , name
            , email
            , password
            , nickName
            , ""
            , ""
            , null
            , null);
    }
}
