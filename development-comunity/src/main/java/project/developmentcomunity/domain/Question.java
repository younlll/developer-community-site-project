package project.developmentcomunity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "question_by_category")
public class Question implements Serializable {

    @Id
    @Column(name = "question_id")
    private long questionId;

    @Id
    @Column(name = "category_id")
    private long categoryId;

    @NotBlank(message = "제목을 필수로 입력해주세요.")
    private String questionTitle;

    private long views;
    private String regDttm;
    private String updDttm;
    private long userId;

    @NotBlank(message = "질문 내용을 필수로 입력해주세요.")
    private String description;

    private String enabledYn;
    private String nickName;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getRegDttm() {
        return regDttm;
    }

    public void setRegDttm(String regDttm) {
        this.regDttm = regDttm;
    }

    public String getUpdDttm() {
        return updDttm;
    }

    public void setUpdDttm(String updDttm) {
        this.updDttm = updDttm;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
