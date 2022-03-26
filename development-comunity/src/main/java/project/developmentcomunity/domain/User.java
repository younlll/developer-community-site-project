package project.developmentcomunity.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@AllArgsConstructor
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

    @NotBlank(message = "닉네임 입력은 필수입니다.")
    @Column(name = "nick_name")
    private String nickName;

    private String github;
    private String blog;

    @Column(name = "reg_dttm")
    private String regDttm;

    @Column(name = "upd_dttm")
    private String updDttm;


}
