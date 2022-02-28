package project.developmentcomunity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @Column(name="category_id")
    private long categoryId;

    private String categoryName;
    private String linkPageUrl;

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
