package project.developmentcomunity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity
public class Category {

    private long categoryId;
    private String categoryName;
    private String linkPageUrl;

    @Id
    @Column(name="category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLinkPageUrl() {
        return linkPageUrl;
    }

    public void setLinkPageUrl(String linkPageUrl) {
        this.linkPageUrl = linkPageUrl;
    }
}
