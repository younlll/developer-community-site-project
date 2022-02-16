package project.developmentcomunity.domain;

public class User {

    private int idUser;
    private String email;
    private String password;
    private String createdYmd;
    private String updatedYmd;
    private String enabledYn;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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
}
