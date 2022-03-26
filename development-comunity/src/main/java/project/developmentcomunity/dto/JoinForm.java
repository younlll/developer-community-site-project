package project.developmentcomunity.dto;

import project.developmentcomunity.domain.User;

public class JoinForm {

    private String email;
    private String password;
    private String nickName;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User toEntity(Long userId, String email, String password, String nickName, String name) {
        return new User(userId
            , name
            , email
            , password
            , nickName
            , ""
            , ""
            , ""
            , "");
    }
}
