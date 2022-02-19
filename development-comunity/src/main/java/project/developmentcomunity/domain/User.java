package project.developmentcomunity.domain;

import javax.persistence.Id;

public class User {

    @Id
    private Long idUser;

    private String name;
    private String email;
    private String password;
    private String createdYmd;
    private String updatedYmd;
    private String enabledYn;
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
