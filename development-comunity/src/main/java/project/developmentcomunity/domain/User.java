package project.developmentcomunity.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long idUser;

    @NotBlank(message = "이름 입력은 필수입니다.")
    private String name;

    @Id
    @Column(name = "email")
    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}",
            message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
    private String password;

    private String createdYmd;
    private String updatedYmd;
    private String enabledYn;

    @NotBlank(message = "닉네임 입력은 필수입니다.")
    private String nickName;

    private String githubUrl;
    private String blogUrl;
    private String image;
    private String description;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

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

    public String getCreatedYmd() {
        return createdYmd;
    }

    public void setCreatedYmd(String createdYmd) {
        this.createdYmd = createdYmd;
    }

    public String getUpdatedYmd() {
        return updatedYmd;
    }

    public void setUpdatedYmd(String updatedYmd) {
        this.updatedYmd = updatedYmd;
    }

    public String getEnabledYn() {
        return enabledYn;
    }

    public void setEnabledYn(String enabledYn) {
        this.enabledYn = enabledYn;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
